
package dk.mhr.ihc.wsdl.cxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WSSceneShutterSimpleValue complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WSSceneShutterSimpleValue"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{utcs.values}WSResourceValue"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="delayTime" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="shutterPositionIsUp" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSSceneShutterSimpleValue", propOrder = {
    "delayTime",
    "shutterPositionIsUp"
})
public class WSSceneShutterSimpleValue
    extends WSResourceValue
{

    protected int delayTime;
    protected boolean shutterPositionIsUp;

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
     * Gets the value of the shutterPositionIsUp property.
     * 
     */
    public boolean isShutterPositionIsUp() {
        return shutterPositionIsUp;
    }

    /**
     * Sets the value of the shutterPositionIsUp property.
     * 
     */
    public void setShutterPositionIsUp(boolean value) {
        this.shutterPositionIsUp = value;
    }

}
