package dk.mhr.ihc.entity;

import java.util.Date;

/**
 * Created by mortenrummelhoff on 14/03/16.
 */
public class DataMessage {

    public enum RESOURCE_METHOD {
        GET, SET;
    }

    public enum RESOURCE_TYPE {
        KITCHEN, LIVING_ROOM;
    }

    private RESOURCE_METHOD resource_method;
    private RESOURCE_TYPE resource_type;
    private Object value;


    public DataMessage() {
    }

    public DataMessage(DataMessage dataMessage) {
        this(dataMessage.getResource_method(), dataMessage.getResource_type(), dataMessage.getValue());
    }

    public DataMessage(RESOURCE_METHOD resource_method, RESOURCE_TYPE resource_type) {
        this(resource_method, resource_type, null);
    }

    public DataMessage(RESOURCE_METHOD resource_method, RESOURCE_TYPE resource_type, Object value) {
        this.resource_method = resource_method;
        this.resource_type = resource_type;
        this.value = value;
    }

    public RESOURCE_METHOD getResource_method() {
        return resource_method;
    }

    public void setResource_method(RESOURCE_METHOD resource_method) {
        this.resource_method = resource_method;
    }

    public RESOURCE_TYPE getResource_type() {
        return resource_type;
    }

    public void setResource_type(RESOURCE_TYPE resource_type) {
        this.resource_type = resource_type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return "DataMessage{" +
                "resource_method=" + resource_method +
                ", resource_type=" + resource_type +
                ", value=" + value +
                '}';
    }
}
