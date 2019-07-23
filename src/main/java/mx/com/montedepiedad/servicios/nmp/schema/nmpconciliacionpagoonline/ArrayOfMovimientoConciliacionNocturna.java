
package mx.com.montedepiedad.servicios.nmp.schema.nmpconciliacionpagoonline;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfMovimientoConciliacionNocturna complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfMovimientoConciliacionNocturna">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MovimientoConciliacionNocturna" type="{http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx}MovimientoConciliacionNocturna" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfMovimientoConciliacionNocturna", propOrder = {
    "movimientoConciliacionNocturna"
})
public class ArrayOfMovimientoConciliacionNocturna {

    @XmlElement(name = "MovimientoConciliacionNocturna", nillable = true)
    protected List<MovimientoConciliacionNocturna> movimientoConciliacionNocturna;

    /**
     * Gets the value of the movimientoConciliacionNocturna property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the movimientoConciliacionNocturna property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMovimientoConciliacionNocturna().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MovimientoConciliacionNocturna }
     * 
     * 
     */
    public List<MovimientoConciliacionNocturna> getMovimientoConciliacionNocturna() {
        if (movimientoConciliacionNocturna == null) {
            movimientoConciliacionNocturna = new ArrayList<MovimientoConciliacionNocturna>();
        }
        return this.movimientoConciliacionNocturna;
    }

}
