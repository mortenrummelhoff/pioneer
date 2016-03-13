
package dk.mhr.ihc.wsdl.cxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WSDateValue complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WSDateValue"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{utcs.values}WSResourceValue"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="day" type="{http://www.w3.org/2001/XMLSchema}byte"/&gt;
 *         &lt;element name="month" type="{http://www.w3.org/2001/XMLSchema}byte"/&gt;
 *         &lt;element name="year" type="{http://www.w3.org/2001/XMLSchema}short"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSDateValue", propOrder = {
    "day",
    "month",
    "year"
})
public class WSDateValue
    extends WSResourceValue
{

    protected byte day;
    protected byte month;
    protected short year;

    /**
     * Gets the value of the day property.
     * 
     */
    public byte getDay() {
        return day;
    }

    /**
     * Sets the value of the day property.
     * 
     */
    public void setDay(byte value) {
        this.day = value;
    }

    /**
     * Gets the value of the month property.
     * 
     */
    public byte getMonth() {
        return month;
    }

    /**
     * Sets the value of the month property.
     * 
     */
    public void setMonth(byte value) {
        this.month = value;
    }

    /**
     * Gets the value of the year property.
     * 
     */
    public short getYear() {
        return year;
    }

    /**
     * Sets the value of the year property.
     * 
     */
    public void setYear(short value) {
        this.year = value;
    }

}
