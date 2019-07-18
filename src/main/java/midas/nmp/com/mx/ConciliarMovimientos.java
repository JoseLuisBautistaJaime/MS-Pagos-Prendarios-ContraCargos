
package midas.nmp.com.mx;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import mx.com.montedepiedad.servicios.nmp.schema.nmpconciliacionpagoonline.ArrayOfMovimientoProveedorTransaccional;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fechaInicio" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="fechaFin" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="movimientos" type="{http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx}ArrayOfMovimientoProveedorTransaccional"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "fechaInicio",
    "fechaFin",
    "movimientos"
})
@XmlRootElement(name = "conciliarMovimientos")
public class ConciliarMovimientos {

    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaInicio;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaFin;
    @XmlElement(required = true, nillable = true)
    protected ArrayOfMovimientoProveedorTransaccional movimientos;

    /**
     * Gets the value of the fechaInicio property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Sets the value of the fechaInicio property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaInicio(XMLGregorianCalendar value) {
        this.fechaInicio = value;
    }

    /**
     * Gets the value of the fechaFin property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaFin() {
        return fechaFin;
    }

    /**
     * Sets the value of the fechaFin property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaFin(XMLGregorianCalendar value) {
        this.fechaFin = value;
    }

    /**
     * Gets the value of the movimientos property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfMovimientoProveedorTransaccional }
     *     
     */
    public ArrayOfMovimientoProveedorTransaccional getMovimientos() {
        return movimientos;
    }

    /**
     * Sets the value of the movimientos property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfMovimientoProveedorTransaccional }
     *     
     */
    public void setMovimientos(ArrayOfMovimientoProveedorTransaccional value) {
        this.movimientos = value;
    }

}
