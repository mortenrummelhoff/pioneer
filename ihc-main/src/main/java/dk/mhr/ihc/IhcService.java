package dk.mhr.ihc;

import dk.mhr.ihc.entity.DataMessage;
import dk.mhr.ihc.entity.IhcData;
import dk.mhr.ihc.wsdl.cxf.OpenAPIService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by mortenrummelhoff on 14/03/16.
 */
@Component
public class IhcService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private OpenAPIService openAPIService;

    @Autowired
    private SimpMessagingTemplate template;

    public void ping() {
     openAPIService.ping();
    }

    public Integer getApiVersion() {
        return openAPIService.getAPIVersion();
    }


    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        broadCast(new DataMessage("Hvad sker der bliver der broadCasted"));
    }

    public void broadCast(DataMessage message) {
        template.convertAndSend("/topic/message", new DataMessage(message.getMessage()));
    }


}
