package dk.mhr.ihc.controller;

import dk.mhr.ihc.entity.DataMessage;
import dk.mhr.ihc.entity.IhcData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;


/**
 * Created by mortenrummelhoff on 14/03/16.
 */
@Controller
public class IhcWebController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @MessageMapping("/chat")
    @SendTo("/topic/message")
    public DataMessage sendIhcDataMessage(DataMessage message) throws Exception {

        logger.info("sendIhcDataMessage called, message {}", message);
        return new DataMessage("Hello, " + message.getMessage() + "!");
    }

}
