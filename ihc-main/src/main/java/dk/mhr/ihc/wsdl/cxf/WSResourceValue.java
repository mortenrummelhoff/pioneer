
package dk.mhr.ihc.wsdl.cxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WSResourceValue complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WSResourceValue"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSResourceValue")
@XmlSeeAlso({
    WSWeekdayValue.class,
    WSTimeValue.class,
    WSTimerValue.class,
    WSSceneShutterSimpleValue.class,
    WSSceneRelayValue.class,
    WSSceneDimmerValue.class,
    WSPhoneNumberValue.class,
    WSIntegerValue.class,
    WSFloatingPointValue.class,
    WSEnumValue.class,
    WSDateValue.class,
    WSBooleanValue.class
})
public class WSResourceValue {


}
