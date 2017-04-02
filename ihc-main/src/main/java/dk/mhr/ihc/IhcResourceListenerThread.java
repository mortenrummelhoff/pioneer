package dk.mhr.ihc;

import dk.mhr.ihc.entity.DataMessage;
import dk.mhr.ihc.entity.Message;
import dk.mhr.ihc.persistence.LightEvent;
import dk.mhr.ihc.persistence.LightRepository;
import dk.mhr.ihc.wsdl.cxf.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.Date;
import java.util.List;

/**
 * Created by mortenrummelhoff on 25/03/16.
 */
public class IhcResourceListenerThread extends Thread {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private boolean isRunning;
    private OpenAPIService openAPIService;

    private ArrayOfint subscriptionList;
    private long lastUpTime;

    private SimpMessagingTemplate template;

    private LightRepository lightRepository;

    public IhcResourceListenerThread(OpenAPIService openAPIService, ArrayOfint subscriptionList, SimpMessagingTemplate template,
                                     LightRepository lightRepository) {
        this.openAPIService = openAPIService;
        this.subscriptionList = subscriptionList;
        this.template = template;
        this.lightRepository = lightRepository;

        openAPIService.enableSubscription(subscriptionList);

        lastUpTime = openAPIService.getUptime();

        isRunning = true;

        logger.debug("IHC OpenAPI Uptime: " + lastUpTime);
    }

    public void shutdown() {
        isRunning = false;
    }

    @Override
    public void run() {

        while (isRunning) {

            try {

                long uptime = openAPIService.getUptime();

                if (uptime < lastUpTime) {
                    logger.warn("IHC Controller has been restarted. Enable subcriptions");
                    openAPIService.enableSubscription(subscriptionList);
                } else {
                    lastUpTime = uptime;
                }

                //aLog.debug("WaitForEvent called start. Uptime {}", (lastUpTime/(1000*60)));
                WSEventPackage wsEventPackage = openAPIService.waitForEvents(10);

                int subscriptionAmount = wsEventPackage.getSubscriptionAmount();
                //aLog.debug("SubscriptionAmount: {}", subscriptionAmount);

                if (subscriptionAmount == 0) {
                    openAPIService.enableSubscription(subscriptionList);

                    //something happen in ihc. properly some timeout exception.
                    //Just call waitForEvent one more time to avoid fake event changes.
                    openAPIService.waitForEvents(1);

                    //done look at changes, as this call will have fake event due to the enableSubscription
                    continue;

                }

                List<WSResourceValueEvent> wsResourceValueEventList = wsEventPackage.getResourceValueEvents().getArrayItem();

                if (wsResourceValueEventList != null && wsResourceValueEventList.size() > 0) {

                    for (WSResourceValueEvent wsResourceValueEvent : wsResourceValueEventList) {

                        if (wsResourceValueEvent == null) {
                            continue;
                        }

                        int resourceId = wsResourceValueEvent.getMResourceID();
                        WSResourceValue wsResourceValue = wsResourceValueEvent.getMValue();

                        //logger.debug("wsResourceValue: {}", wsResourceValue);

                        if (wsResourceValue instanceof WSBooleanValue) {

                            WSBooleanValue boolValue = WSBooleanValue.class.cast(wsResourceValue);

                            switch (resourceId) {
                                case IhcService.KITCHEN_PUSH_UP_LEFT:
                                    logger.debug("Kitchen UP LEFT pushed, value: {}", boolValue.isValue());
                                    break;
                                case IhcService.KITCHEN_PUSH_UP_RIGHT:
                                    logger.debug("Kitchen UP RIGHT pushed, value: {}", boolValue.isValue());
                                    break;

                            }

                            if (resourceId == IhcService.ON_BUTTON_RESOURCE) {

                                if (boolValue.isValue()) {
                                    if (logger.isDebugEnabled()) {
                                        logger.debug("Resource {} has been pushed. Establishing connection to Pioneer Receiver.", resourceId);
                                    }

                                }

                            } else if (resourceId == IhcService.OFF_BUTTON_RESOURCE) {
                                if (boolValue.isValue()) {

                                    //aTelnetManager.setPower(false);
                                    //aTelnetManager.removeConnectionListener(connectionListener);
                                }
                            }


                            if (resourceId == IhcService.TEL_SWITCH_OUT) {
                                //aPushInterface.push("Television Powered " + wsResourceValue);
                                //aPushInterface.push("Television Powered " + (boolValue.isValue() ? "On" : "Off" ));
                            } else {
                                if (boolValue.isValue()) {
                                    //send push about Power switch has been pushed
                                    //aPushInterface.push("Power Switch Resource: " +  resourceId +" pushed");
                                }
                            }

                        } else if (wsResourceValue instanceof WSIntegerValue) {
                            WSIntegerValue integerValue = WSIntegerValue.class.cast(wsResourceValue);

                            logger.debug("Resource: {} -> Value changed to: {}", resourceId, integerValue.getInteger() );

                            LightEvent event = new LightEvent("Kitchen", false, new Date(), integerValue.getInteger());
                            lightRepository.saveAndFlush(event);

                            Message message = new Message();
                            DataMessage dataMessage = new DataMessage();
                            dataMessage.setValue(integerValue.getInteger());
                            dataMessage.setResource_method(DataMessage.RESOURCE_METHOD.SET);
                            dataMessage.setResource_type(DataMessage.RESOURCE_TYPE.KITCHEN);
                            message.setMessage(dataMessage);

                            template.convertAndSend("/topic/message", message);
                        }
                        else {
                            logger.debug("ResourceValue not a boolean type, was: {}, id: {}", wsResourceValue, resourceId);
                        }
                    }
                }

            } catch (Throwable e) {
                logger.warn("Something went wrong in ServiceAPI Continue anyway", e);
                //aPushInterface.push("Error occoured: " + e.getMessage());
            }

        }
    }
}
