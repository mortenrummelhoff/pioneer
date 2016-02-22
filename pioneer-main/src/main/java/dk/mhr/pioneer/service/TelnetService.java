package dk.mhr.pioneer.service;

import dk.mhr.pioneer.telnet.CommunicationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mortenrummelhoff on 18/02/16.
 */
@Component
public class TelnetService {

    @Autowired
    private CommunicationManager communicationManager;

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final String POWER_STATUS_COMMAND = "?P\r\n";
    private static final String POWER_ON_COMMAND = "PO\r\n";
    private static final String POWER_OFF_COMMAND = "PF\r\n";

    private static final String MODE_STATUS = "?F\r\n";
    private static final String MODE_TUNER = "02FN\r\n";


    public enum MODE {
        TUNER;
    }

    public boolean isPoweredOn() {
        String powerStatus = communicationManager.getTelnetCommandExecuter().sendCommand(POWER_STATUS_COMMAND);
        return  (powerStatus.startsWith("PWR0"));
    }

    public void setPower(boolean on) {
        communicationManager.getTelnetCommandExecuter().sendCommand(on ? POWER_ON_COMMAND : POWER_OFF_COMMAND);
    }

    public MODE getMode() {
        String modeString = communicationManager.getTelnetCommandExecuter().sendCommand(MODE_STATUS);
        logger.info("Mode Status: {}", modeString);
        if (modeString.startsWith("FN02")) {
            return MODE.TUNER;
        }
        return null;
    }

    public void setMode(MODE mode) {
        switch (mode) {
            case TUNER:
                communicationManager.getTelnetCommandExecuter().sendCommand(MODE_TUNER);
                break;
        }
    }



}