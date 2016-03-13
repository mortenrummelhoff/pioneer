
package dk.mhr.ihc.wsdl.cxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WSVersionInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WSVersionInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="majorVersion" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="minorVersion" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="buildVersion" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSVersionInfo", namespace = "utcs", propOrder = {
    "majorVersion",
    "minorVersion",
    "buildVersion"
})
public class WSVersionInfo {

    protected int majorVersion;
    protected int minorVersion;
    protected int buildVersion;

    /**
     * Gets the value of the majorVersion property.
     * 
     */
    public int getMajorVersion() {
        return majorVersion;
    }

    /**
     * Sets the value of the majorVersion property.
     * 
     */
    public void setMajorVersion(int value) {
        this.majorVersion = value;
    }

    /**
     * Gets the value of the minorVersion property.
     * 
     */
    public int getMinorVersion() {
        return minorVersion;
    }

    /**
     * Sets the value of the minorVersion property.
     * 
     */
    public void setMinorVersion(int value) {
        this.minorVersion = value;
    }

    /**
     * Gets the value of the buildVersion property.
     * 
     */
    public int getBuildVersion() {
        return buildVersion;
    }

    /**
     * Sets the value of the buildVersion property.
     * 
     */
    public void setBuildVersion(int value) {
        this.buildVersion = value;
    }

}
