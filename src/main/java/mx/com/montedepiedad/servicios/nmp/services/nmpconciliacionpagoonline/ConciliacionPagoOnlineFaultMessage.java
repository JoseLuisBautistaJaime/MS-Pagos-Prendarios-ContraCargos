
package mx.com.montedepiedad.servicios.nmp.services.nmpconciliacionpagoonline;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import mx.com.montedepiedad.servicios.nmp.schema.nmpstandardmessage.MessageError;


/**
 * <p>Java class for ConciliacionPagoOnlineFaultMessage complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConciliacionPagoOnlineFaultMessage">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="faultInfo" type="{http://nmpstandardmessage.schema.nmp.servicios.montedepiedad.com.mx}MessageError" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConciliacionPagoOnlineFaultMessage", propOrder = {
    "faultInfo"
})
public class ConciliacionPagoOnlineFaultMessage {

    @XmlElementRef(name = "faultInfo", namespace = "http://nmpconciliacionpagoonline.services.nmp.servicios.montedepiedad.com.mx", type = JAXBElement.class, required = false)
    protected JAXBElement<MessageError> faultInfo;

    /**
     * Gets the value of the faultInfo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link MessageError }{@code >}
     *     
     */
    public JAXBElement<MessageError> getFaultInfo() {
        return faultInfo;
    }

    /**
     * Sets the value of the faultInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link MessageError }{@code >}
     *     
     */
    public void setFaultInfo(JAXBElement<MessageError> value) {
        this.faultInfo = value;
    }

}
