package dk.mhr.ihc.entity;

import java.util.Date;

/**
 * Created by mortenrummelhoff on 01/05/16.
 */
public class Message {

    private int id;
    private Date time;

    private DataMessage message;


    public Message() {
    }

    public Message(Message message) {
        this(message.getMessage());
        id = message.getId();
        time = message.getTime();
    }

    public Message(DataMessage message) {
        this.message = message;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }


    public DataMessage getMessage() {
        return message;
    }

    public void setMessage(DataMessage dataMessage) {
        this.message = dataMessage;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", time=" + time +
                ", message=" + message +
                '}';
    }
}
