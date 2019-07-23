
package mx.com.montedepiedad.servicios.nmp.schema.nmpconciliacionpagoonline;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfMovimientoProveedorTransaccional complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfMovimientoProveedorTransaccional">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MovimientoProveedorTransaccional" type="{http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx}MovimientoProveedorTransaccional" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfMovimientoProveedorTransaccional", propOrder = {
    "movimientoProveedorTransaccional"
})
public class ArrayOfMovimientoProveedorTransaccional {

    @XmlElement(name = "MovimientoProveedorTransaccional", nillable = true)
    protected List<MovimientoProveedorTransaccional> movimientoProveedorTransaccional;

    /**
     * Gets the value of the movimientoProveedorTransaccional property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the movimientoProveedorTransaccional property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMovimientoProveedorTransaccional().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MovimientoProveedorTransaccional }
     * 
     * 
     */
    public List<MovimientoProveedorTransaccional> getMovimientoProveedorTransaccional() {
        if (movimientoProveedorTransaccional == null) {
            movimientoProveedorTransaccional = new ArrayList<MovimientoProveedorTransaccional>();
        }
        return this.movimientoProveedorTransaccional;
    }

}
