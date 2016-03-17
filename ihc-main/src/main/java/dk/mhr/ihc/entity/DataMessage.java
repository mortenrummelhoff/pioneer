package dk.mhr.ihc.entity;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by mortenrummelhoff on 14/03/16.
 */
public class DataMessage {

    private String message;
    private Date time;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataMessage() {
        time = new Date();
    }

    public DataMessage(String message) {
        time = new Date();
        this.message = message;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "DataMessage{" +
                "message='" + message + '\'' +
                ", time=" + time +
                '}';
    }
}
