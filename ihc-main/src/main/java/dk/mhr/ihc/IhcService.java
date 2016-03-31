package dk.mhr.ihc;

import dk.mhr.ihc.entity.DataMessage;
import dk.mhr.ihc.entity.IhcData;
import dk.mhr.ihc.wsdl.cxf.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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


    public Integer getApiVersion() {
        return openAPIService.getAPIVersion();
    }

    @PostConstruct
    private void initListenForResourceEvents() {
        logger.debug("Setting up listener for IHC resources, OpenApiService: " + openAPIService);

        IhcResourceListenerThread ihcResourceListenerThread = new IhcResourceListenerThread(openAPIService, getSubscriptionList());
        ihcResourceListenerThread.start();

    }

    public int getKithchenLight() {
        return getIhcOpenApiIntResource(KITCHEN_DIMMER);
    }

    public void turnOnKitchenLight(boolean set) {
        ObjectFactory aOF = new ObjectFactory();

        //set both buttons off
        ArrayOfWSResourceValueEvent rveList = aOF.createArrayOfWSResourceValueEvent();

        WSResourceValueEvent rveLeft = aOF.createWSResourceValueEvent();
        rveLeft.setMResourceID(KITCHEN_PUSH_UP_LEFT);

        WSResourceValueEvent rveRight = aOF.createWSResourceValueEvent();
        rveRight.setMResourceID(KITCHEN_PUSH_UP_RIGHT);

        WSBooleanValue bFalseValue = aOF.createWSBooleanValue();
        bFalseValue.setValue(false);
        rveLeft.setMValue(bFalseValue);
        rveRight.setMValue(bFalseValue);

        rveList.getArrayItem().add(rveLeft);
        rveList.getArrayItem().add(rveRight);

        Boolean result = openAPIService.setValues(rveList);

        //set Poweron resource for 1 sec
        WSBooleanValue bTrueValue = aOF.createWSBooleanValue();
        bTrueValue.setValue(true);
        if (set) {
            rveLeft.setMValue(bTrueValue);
        }
        else {
            rveRight.setMValue(bTrueValue);
        }

        openAPIService.setValues(rveList);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //Set both buttons to false again
        rveLeft.setMValue(bFalseValue);
        rveRight.setMValue(bFalseValue);

        openAPIService.setValues(rveList);


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


    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        broadCast(new DataMessage("Hvad sker der bliver der broadCasted"));
    }

    public void broadCast(DataMessage message) {
        template.convertAndSend("/topic/message", new DataMessage(message.getMessage()));
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
