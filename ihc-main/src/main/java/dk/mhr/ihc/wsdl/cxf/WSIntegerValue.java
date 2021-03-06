
package dk.mhr.ihc.wsdl.cxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WSIntegerValue complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WSIntegerValue"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{utcs.values}WSResourceValue"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="integer" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="maximumValue" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="minimumValue" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSIntegerValue", propOrder = {
    "integer",
    "maximumValue",
    "minimumValue"
})
public class WSIntegerValue
    extends WSResourceValue
{

    protected int integer;
    protected int maximumValue;
    protected int minimumValue;

    /**
     * Gets the value of the integer property.
     * 
     */
    public int getInteger() {
        return integer;
    }

    /**
     * Sets the value of the integer property.
     * 
     */
    public void setInteger(int value) {
        this.integer = value;
    }

    /**
     * Gets the value of the maximumValue property.
     * 
     */
    public int getMaximumValue() {
        return maximumValue;
    }

    /**
     * Sets the value of the maximumValue property.
     * 
     */
    public void setMaximumValue(int value) {
        this.maximumValue = value;
    }

    /**
     * Gets the value of the minimumValue property.
     * 
     */
    public int getMinimumValue() {
        return minimumValue;
    }

    /**
     * Sets the value of the minimumValue property.
     * 
     */
    public void setMinimumValue(int value) {
        this.minimumValue = value;
    }

}
