
package dk.mhr.ihc.wsdl.cxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WSTimerValue complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WSTimerValue"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{utcs.values}WSResourceValue"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="milliseconds" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSTimerValue", propOrder = {
    "milliseconds"
})
public class WSTimerValue
    extends WSResourceValue
{

    protected long milliseconds;

    /**
     * Gets the value of the milliseconds property.
     * 
     */
    public long getMilliseconds() {
        return milliseconds;
    }

    /**
     * Sets the value of the milliseconds property.
     * 
     */
    public void setMilliseconds(long value) {
        this.milliseconds = value;
    }

}
