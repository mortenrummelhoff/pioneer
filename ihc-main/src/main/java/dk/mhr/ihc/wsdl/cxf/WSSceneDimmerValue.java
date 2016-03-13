
package dk.mhr.ihc.wsdl.cxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WSSceneDimmerValue complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WSSceneDimmerValue"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{utcs.values}WSResourceValue"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="delayTime" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="dimmerPercentage" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="rampTime" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSSceneDimmerValue", propOrder = {
    "delayTime",
    "dimmerPercentage",
    "rampTime"
})
public class WSSceneDimmerValue
    extends WSResourceValue
{

    protected int delayTime;
    protected int dimmerPercentage;
    protected int rampTime;

    /**
     * Gets the value of the delayTime property.
     * 
     */
    public int getDelayTime() {
        return delayTime;
    }

    /**
     * Sets the value of the delayTime property.
     * 
     */
    public void setDelayTime(int value) {
        this.delayTime = value;
    }

    /**
     * Gets the value of the dimmerPercentage property.
     * 
     */
    public int getDimmerPercentage() {
        return dimmerPercentage;
    }

    /**
     * Sets the value of the dimmerPercentage property.
     * 
     */
    public void setDimmerPercentage(int value) {
        this.dimmerPercentage = value;
    }

    /**
     * Gets the value of the rampTime property.
     * 
     */
    public int getRampTime() {
        return rampTime;
    }

    /**
     * Sets the value of the rampTime property.
     * 
     */
    public void setRampTime(int value) {
        this.rampTime = value;
    }

}
