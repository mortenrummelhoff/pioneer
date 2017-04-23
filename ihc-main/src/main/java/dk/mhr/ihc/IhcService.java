package dk.mhr.ihc;

import dk.mhr.ihc.entity.DataMessage;
import dk.mhr.ihc.entity.IhcData;
import dk.mhr.ihc.persistence.LightRepository;
import dk.mhr.ihc.wsdl.cxf.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * Created by mortenrummelhoff on 14/03/16.
 */
@Component
public class IhcService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public final static int KITCHEN_PUSH_UP_LEFT = 404060;
    public final static int KITCHEN_PUSH_UP_RIGHT = 404316;

    public final static int ON_BUTTON_RESOURCE = 404572;
    public final static int OFF_BUTTON_RESOURCE = 404828;

    public final static int KITCHEN_LIGHT_LEVEL = 405597;
    public final static int KITCHEN_LIGHT_INDICATOR = 405789;

    public final static int TEL_SWITCH_OUT = 20062;
    private final static int TEL_BUTTON_OFF = 755473;
    private final static int TEL_BUTTON_ON = 777489;

    private final static int KITCHEN_DIMMER = 521490;


    @Autowired
    private OpenAPIService openAPIService;

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private LightRepository lightRepository;

    public Integer getApiVersion() {
        return openAPIService.getAPIVersion();
    }

    @PostConstruct
    private void initListenForResourceEvents() {
        logger.debug("Setting up listener for IHC resources, OpenApiService: " + openAPIService);

        IhcResourceListenerThread ihcResourceListenerThread = new IhcResourceListenerThread(openAPIService,
                getSubscriptionList(), template, lightRepository);
        ihcResourceListenerThread.start();

    }

    public int getKithchenLight() {
        return getIhcOpenApiIntResource(KITCHEN_LIGHT_LEVEL);
    }

    public void turnOnKitchenLight(boolean set, int value) {
        ObjectFactory aOF = new ObjectFactory();
        ArrayOfWSResourceValueEvent rveList = aOF.createArrayOfWSResourceValueEvent();

        if (set && value != 100) {
            logger.info("Kitchen light level adjustment. Setting level to: " + value);
            WSResourceValueEvent lightValueEvent = aOF.createWSResourceValueEvent();
            lightValueEvent.setMResourceID(KITCHEN_LIGHT_LEVEL);
            WSIntegerValue lightValue = aOF.createWSIntegerValue();
            lightValue.setInteger(value);
            rveList.getArrayItem().add(lightValueEvent);
            boolean b = openAPIService.setValues(rveList);
            logger.info("Response: " + b);
            return;
        }


        WSResourceValueEvent valueEvent = aOF.createWSResourceValueEvent();
        valueEvent.setMResourceID(set ? KITCHEN_PUSH_UP_LEFT : KITCHEN_PUSH_UP_RIGHT);
        WSBooleanValue booleanValue = aOF.createWSBooleanValue();
        booleanValue.setValue(true);
        valueEvent.setMValue(booleanValue);
        rveList.getArrayItem().add(valueEvent);

        valueEvent = aOF.createWSResourceValueEvent();
        valueEvent.setMResourceID(set ? KITCHEN_PUSH_UP_LEFT : KITCHEN_PUSH_UP_RIGHT);
        booleanValue = aOF.createWSBooleanValue();
        booleanValue.setValue(false);
        valueEvent.setMValue(booleanValue);
        rveList.getArrayItem().add(valueEvent);


        boolean b = openAPIService.setValues(rveList);
        logger.info("Response: " + b);


    }

    private boolean getIhcOpenApiBooleanResource(int resource) {
        ObjectFactory aOF = new ObjectFactory();

        ArrayOfint aoi = aOF.createArrayOfint();
        aoi.getArrayItem().add(resource);

        ArrayOfWSResourceValue aoWSRV = openAPIService.getValues(aoi);

        logger.debug("ihcService.getValue for resource: {} Returned: {} ", resource, aoWSRV.getArrayItem());

        String value = null;

        if (aoWSRV.getArrayItem() != null && aoWSRV.getArrayItem().size() > 0) {
            WSResourceValue wsRValue = aoWSRV.getArrayItem().get(0);

            if (wsRValue instanceof WSBooleanValue) {
                //value = "[BOOLEAN]->[" + WSBooleanValue.class.cast(wsRValue).isValue() +"]";
                //aLog.debug("Value: {}", value);

                return WSBooleanValue.class.cast(wsRValue).isValue();

            }

        }
        return false;
    }

    private int getIhcOpenApiIntResource(int resource) {
        ObjectFactory aOF = new ObjectFactory();

        ArrayOfint aoi = aOF.createArrayOfint();
        aoi.getArrayItem().add(resource);

        ArrayOfWSResourceValue aoWSRV = openAPIService.getValues(aoi);

        logger.debug("ihcService.getValue for resource: {} Returned: {} ", resource, aoWSRV.getArrayItem());

        String value = null;

        if (aoWSRV.getArrayItem() != null && aoWSRV.getArrayItem().size() > 0) {
            WSResourceValue wsRValue = aoWSRV.getArrayItem().get(0);

            if (wsRValue instanceof WSIntegerValue) {
                //value = "[BOOLEAN]->[" + WSBooleanValue.class.cast(wsRValue).isValue() +"]";
                //aLog.debug("Value: {}", value);

                return WSIntegerValue.class.cast(wsRValue).getInteger();

            }

        }
        return 0;
    }


//    @Scheduled(fixedRate = 5000)
//    public void reportCurrentTime() {
//        broadCast(new DataMessage("Hvad sker der bliver der broadCasted"));
//    }

    public void broadCast(DataMessage message) {
        //template.convertAndSend("/topic/message", new DataMessage(message.getMessage()));
    }


    private ArrayOfint getSubscriptionList() {
        ObjectFactory aOF = new ObjectFactory();
        ArrayOfint subscriptionList = aOF.createArrayOfint();

        //3. button ON
        subscriptionList.getArrayItem().add(ON_BUTTON_RESOURCE);
        //4. button OFF
        subscriptionList.getArrayItem().add(OFF_BUTTON_RESOURCE);

        subscriptionList.getArrayItem().add(KITCHEN_LIGHT_LEVEL);
        subscriptionList.getArrayItem().add(KITCHEN_LIGHT_INDICATOR);

        subscriptionList.getArrayItem().add(KITCHEN_PUSH_UP_LEFT);
        subscriptionList.getArrayItem().add(KITCHEN_PUSH_UP_RIGHT);
        subscriptionList.getArrayItem().add(TEL_SWITCH_OUT);

        subscriptionList.getArrayItem().add(TEL_BUTTON_OFF);
        subscriptionList.getArrayItem().add(TEL_BUTTON_ON);

        return subscriptionList;
    }

}
