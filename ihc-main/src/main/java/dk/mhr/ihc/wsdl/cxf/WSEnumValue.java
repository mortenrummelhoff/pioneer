
package dk.mhr.ihc.wsdl.cxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WSEnumValue complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WSEnumValue"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{utcs.values}WSResourceValue"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="definitionTypeID" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="enumValueID" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="enumName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSEnumValue", propOrder = {
    "definitionTypeID",
    "enumValueID",
    "enumName"
})
public class WSEnumValue
    extends WSResourceValue
{

    protected int definitionTypeID;
    protected int enumValueID;
    @XmlElement(required = true)
    protected String enumName;

    /**
     * Gets the value of the definitionTypeID property.
     * 
     */
    public int getDefinitionTypeID() {
        return definitionTypeID;
    }

    /**
     * Sets the value of the definitionTypeID property.
     * 
     */
    public void setDefinitionTypeID(int value) {
        this.definitionTypeID = value;
    }

    /**
     * Gets the value of the enumValueID property.
     * 
     */
    public int getEnumValueID() {
        return enumValueID;
    }

    /**
     * Sets the value of the enumValueID property.
     * 
     */
    public void setEnumValueID(int value) {
        this.enumValueID = value;
    }

    /**
     * Gets the value of the enumName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnumName() {
        return enumName;
    }

    /**
     * Sets the value of the enumName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnumName(String value) {
        this.enumName = value;
    }

}
