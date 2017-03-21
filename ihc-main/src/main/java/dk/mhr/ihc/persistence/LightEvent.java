package dk.mhr.ihc.persistence;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by mortenrummelhoff on 14/03/2017.
 */
@Entity
public class LightEvent implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private boolean state;

    @Column
    private Date date;

    @Column
    private int value;

    public LightEvent() {
    }

    public LightEvent(String name, boolean state, Date date, int value) {
        this.name = name;
        this.state = state;
        this.date = date;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "LightEvent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", state=" + state +
                ", date=" + date +
                ", value=" + value +
                '}';
    }
}
