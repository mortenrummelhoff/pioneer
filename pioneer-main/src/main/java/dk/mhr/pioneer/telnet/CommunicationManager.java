package dk.mhr.pioneer.telnet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by mortenrummelhoff on 18/02/16.
 */
@Component
@EnableScheduling
public class CommunicationManager {

    private Socket client;

    private final static String RECEIVER_IP = "192.168.0.45";
    private final static int RECEIVER_PORT = 23;

    private Logger logger = LoggerFactory.getLogger(getClass());

    private AtomicBoolean isConnection = new AtomicBoolean(false);

    private TelnetCommandExecuter telnetCommandExecuter;

    @Scheduled(fixedDelay = 1000*10, initialDelay = 0)
    public void checkPioneerConnection() {
        logger.info("Checking PioneerConnectionStatus");

        if (!isConnection.get()) {
            logger.info("Pioneer connection not established. Trying to Connect!!!");
            establishConnection();
        }

        //logger.info("Trying to get Pioneer POWER Status: " + telnetCommandExecuter.sendCommand("?P\r\n"));
    }

    private boolean establishConnection() {

        logger.info("Trying Connecting to IP {} on PORT {}", RECEIVER_IP, RECEIVER_PORT);

        //check is client is not null
        if (client != null) {
            try {
                logger.info("Clean up Previous Connection to Pioneer");
                client.close();
            } catch (IOException e) {
                //Ignore
                logger.info("Clean up Previous Connection to Pioneer", e);
            }
        }

        try {
            client = new Socket(RECEIVER_IP, RECEIVER_PORT);
            client.setTcpNoDelay(true);

            TelnetInputReader telnetInputReader = new TelnetInputReader(client.getInputStream());
            telnetInputReader.start();

            telnetCommandExecuter = new TelnetCommandExecuter(client.getOutputStream(), telnetInputReader);
            //everything okay. Set connection to true
            isConnection.set(true);

            logger.info("Successfully Connected to IP {} on PORT {}", RECEIVER_IP, RECEIVER_PORT);
        } catch (Exception e) {
            logger.info("Unable to establish Connection to Pioneer", e);
            return false;
        }
        return true;
    }

    public TelnetCommandExecuter getTelnetCommandExecuter() {
        return telnetCommandExecuter;
    }

    public class TelnetCommandExecuter {
        BufferedOutputStream bos;
        TelnetInputReader reader;

        private TelnetCommandExecuter(OutputStream os, TelnetInputReader reader) {
            bos = new BufferedOutputStream(os);
            this.reader = reader;
        }

        public synchronized String sendCommand(String command) {
            logger.info("sendCommand: " + command);
            try {
                //setup reader to get response
                reader.clearResponse();

                bos.write(command.getBytes());
                bos.flush();

                return reader.getResponse();

            } catch (IOException e) {
                logger.warn("Unable to send command: {}", command, e);
                return null;
            } finally {
                try {
                    Thread.currentThread().sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class TelnetInputReader extends Thread {

        private BufferedInputStream bis;
        private String response;

        private Object MUTEX = new Object();

        private TelnetInputReader(InputStream is) {
            bis = new BufferedInputStream(is);
        }

        private void clearResponse() {
            response = null;
        }

        private String getResponse() {
            int waitCount = 0;
            if (response != null) {
                return response;
            }
            else {
                while (waitCount++ < 5 && response == null) {
                    synchronized (MUTEX) {
                        try {
                            MUTEX.wait(100);
                        } catch (InterruptedException e) {
                            //ignore
                        }
                    }

                }
                return response;
            }
        }

        @Override
        public void run() {
            try {
                byte[] buff;
                int ret_read = 0;

                do {
                    buff = new byte[512];
                    ret_read = bis.read(buff);

                    if (ret_read > 0) {
                        final String line = new String(buff, 0, ret_read);

                        //filter out FL0 message(what is it??)
                        if (line.length() < 15 && !line.startsWith("FL0")) {
                            response = line;
                            logger.info(line);
                        }
                    }
                }
                while (ret_read >= 0);

            } catch (IOException e) {
                logger.error("Error reading message", e);
                //Here disconnect, and signal end
                isConnection.compareAndSet(true, false);
            }

        }
    }
}
