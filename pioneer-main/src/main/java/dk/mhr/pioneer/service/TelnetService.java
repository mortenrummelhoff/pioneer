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
    private static final String MODE_TV_SAT = "05FN\r\n";
    private static final String MODE_APPLE_TV = "15FN\r\n";

    private static final String VOLUME_STATUS = "?V\r\n";

    public enum MODE {
        TUNER, TV_SAT, APPLE_TV;
    }

    public String getVolume() {
        return communicationManager.getTelnetCommandExecuter().sendCommand(VOLUME_STATUS);
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
        if (modeString.startsWith("FN05")) {
            return MODE.TV_SAT;
        }
        if (modeString.startsWith("FN15")) {
            return MODE.APPLE_TV;
        }
        return null;
    }

    public void setMode(MODE mode) {
        switch (mode) {
            case TUNER:
                communicationManager.getTelnetCommandExecuter().sendCommand(MODE_TUNER);
                break;
            case TV_SAT:
                communicationManager.getTelnetCommandExecuter().sendCommand(MODE_TV_SAT);
                break;
            case APPLE_TV:
                communicationManager.getTelnetCommandExecuter().sendCommand(MODE_APPLE_TV);
                break;
        }
    }



}
