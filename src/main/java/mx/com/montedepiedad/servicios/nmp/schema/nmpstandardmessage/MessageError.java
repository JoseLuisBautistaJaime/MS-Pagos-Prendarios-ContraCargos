
package mx.com.montedepiedad.servicios.nmp.schema.nmpstandardmessage;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MessageError complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MessageError">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codigoError" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descripcionError" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="severidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoError" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MessageError", propOrder = {
    "codigoError",
    "descripcionError",
    "severidad",
    "tipoError"
})
public class MessageError {

    @XmlElementRef(name = "codigoError", namespace = "http://nmpstandardmessage.schema.nmp.servicios.montedepiedad.com.mx", type = JAXBElement.class, required = false)
    protected JAXBElement<String> codigoError;
    @XmlElementRef(name = "descripcionError", namespace = "http://nmpstandardmessage.schema.nmp.servicios.montedepiedad.com.mx", type = JAXBElement.class, required = false)
    protected JAXBElement<String> descripcionError;
    @XmlElementRef(name = "severidad", namespace = "http://nmpstandardmessage.schema.nmp.servicios.montedepiedad.com.mx", type = JAXBElement.class, required = false)
    protected JAXBElement<String> severidad;
    @XmlElementRef(name = "tipoError", namespace = "http://nmpstandardmessage.schema.nmp.servicios.montedepiedad.com.mx", type = JAXBElement.class, required = false)
    protected JAXBElement<String> tipoError;

    /**
     * Gets the value of the codigoError property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoError() {
        return codigoError;
    }

    /**
     * Sets the value of the codigoError property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoError(JAXBElement<String> value) {
        this.codigoError = value;
    }

    /**
     * Gets the value of the descripcionError property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDescripcionError() {
        return descripcionError;
    }

    /**
     * Sets the value of the descripcionError property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDescripcionError(JAXBElement<String> value) {
        this.descripcionError = value;
    }

    /**
     * Gets the value of the severidad property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSeveridad() {
        return severidad;
    }

    /**
     * Sets the value of the severidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSeveridad(JAXBElement<String> value) {
        this.severidad = value;
    }

    /**
     * Gets the value of the tipoError property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoError() {
        return tipoError;
    }

    /**
     * Sets the value of the tipoError property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoError(JAXBElement<String> value) {
        this.tipoError = value;
    }

}
