package dk.mhr.ihc.controller;

import dk.mhr.ihc.IhcService;
import dk.mhr.ihc.entity.DataMessage;
import dk.mhr.ihc.entity.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;


/**
 * Created by mortenrummelhoff on 14/03/16.
 */
@Controller
@CrossOrigin("*")
public class IhcWebController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IhcService ihcService;

    @MessageMapping("/chat")
    @SendTo("/topic/message")
    public Message sendIhcDataMessage(Message message) throws Exception {

        logger.info("sendIhcDataMessage called, message {}", message);

        DataMessage dataMessage = message.getMessage();
        Message rspMessage = new Message(dataMessage);

        if (dataMessage.getResource_method() == DataMessage.RESOURCE_METHOD.GET) {
            rspMessage.getMessage().setValue(ihcService.getKithchenLight());
        } else {
            int val = Integer.parseInt(dataMessage.getValue().toString());

            ihcService.turnOnKitchenLight(val != 0);
        }

        return rspMessage;
    }

}
