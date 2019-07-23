
package midas.nmp.com.mx;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import mx.com.montedepiedad.servicios.nmp.schema.nmpconciliacionpagoonline.ArrayOfMovimientoConciliacionNocturna;


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
 *         &lt;element name="movimientos" type="{http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx}ArrayOfMovimientoConciliacionNocturna"/>
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
    "movimientos"
})
@XmlRootElement(name = "obtenerMovimientosConciliacionNocturnaResponse")
public class ObtenerMovimientosConciliacionNocturnaResponse {

    @XmlElement(required = true, nillable = true)
    protected ArrayOfMovimientoConciliacionNocturna movimientos;

    /**
     * Gets the value of the movimientos property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfMovimientoConciliacionNocturna }
     *     
     */
    public ArrayOfMovimientoConciliacionNocturna getMovimientos() {
        return movimientos;
    }

    /**
     * Sets the value of the movimientos property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfMovimientoConciliacionNocturna }
     *     
     */
    public void setMovimientos(ArrayOfMovimientoConciliacionNocturna value) {
        this.movimientos = value;
    }

}
