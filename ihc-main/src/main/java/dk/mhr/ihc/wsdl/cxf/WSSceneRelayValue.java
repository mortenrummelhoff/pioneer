
package dk.mhr.ihc.wsdl.cxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WSSceneRelayValue complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WSSceneRelayValue"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{utcs.values}WSResourceValue"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="delayTime" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="relayValue" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSSceneRelayValue", propOrder = {
    "delayTime",
    "relayValue"
})
public class WSSceneRelayValue
    extends WSResourceValue
{

    protected int delayTime;
    protected boolean relayValue;

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
     * Gets the value of the relayValue property.
     * 
     */
    public boolean isRelayValue() {
        return relayValue;
    }

    /**
     * Sets the value of the relayValue property.
     * 
     */
    public void setRelayValue(boolean value) {
        this.relayValue = value;
    }

}
