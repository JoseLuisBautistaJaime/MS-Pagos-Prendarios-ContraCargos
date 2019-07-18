
package mx.com.montedepiedad.servicios.nmp.schema.nmpconciliacionpagoonline;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for MovimientoConciliacionNocturna complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MovimientoConciliacionNocturna">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="capitalActual" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="codigoError" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="comisiones" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="estadoTransaccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="estatus" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="fecha" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="folioPartida" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="idConsumidor" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="idOperacion" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="idTarjeta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idTipoContrato" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="importeTransaccion" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="interes" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="marcaTarjeta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mensajeError" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="monedaPago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="montoOperacion" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="numAutorizacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operacionAbr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operacionDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sucursal" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="tarjeta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoContratoAbr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoContratoDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoTarjeta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transaccion" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MovimientoConciliacionNocturna", propOrder = {
    "capitalActual",
    "codigoError",
    "comisiones",
    "estadoTransaccion",
    "estatus",
    "fecha",
    "folioPartida",
    "idConsumidor",
    "idOperacion",
    "idTarjeta",
    "idTipoContrato",
    "importeTransaccion",
    "interes",
    "marcaTarjeta",
    "mensajeError",
    "monedaPago",
    "montoOperacion",
    "numAutorizacion",
    "operacionAbr",
    "operacionDesc",
    "sucursal",
    "tarjeta",
    "tipoContratoAbr",
    "tipoContratoDesc",
    "tipoTarjeta",
    "transaccion"
})
public class MovimientoConciliacionNocturna {

    @XmlElementRef(name = "capitalActual", namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> capitalActual;
    @XmlElementRef(name = "codigoError", namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", type = JAXBElement.class, required = false)
    protected JAXBElement<String> codigoError;
    @XmlElementRef(name = "comisiones", namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> comisiones;
    @XmlElementRef(name = "estadoTransaccion", namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", type = JAXBElement.class, required = false)
    protected JAXBElement<String> estadoTransaccion;
    protected Boolean estatus;
    @XmlElementRef(name = "fecha", namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> fecha;
    @XmlElementRef(name = "folioPartida", namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", type = JAXBElement.class, required = false)
    protected JAXBElement<Long> folioPartida;
    @XmlElementRef(name = "idConsumidor", namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> idConsumidor;
    @XmlElementRef(name = "idOperacion", namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> idOperacion;
    @XmlElementRef(name = "idTarjeta", namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", type = JAXBElement.class, required = false)
    protected JAXBElement<String> idTarjeta;
    @XmlElementRef(name = "idTipoContrato", namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> idTipoContrato;
    @XmlElementRef(name = "importeTransaccion", namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> importeTransaccion;
    @XmlElementRef(name = "interes", namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> interes;
    @XmlElementRef(name = "marcaTarjeta", namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", type = JAXBElement.class, required = false)
    protected JAXBElement<String> marcaTarjeta;
    @XmlElementRef(name = "mensajeError", namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", type = JAXBElement.class, required = false)
    protected JAXBElement<String> mensajeError;
    @XmlElementRef(name = "monedaPago", namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", type = JAXBElement.class, required = false)
    protected JAXBElement<String> monedaPago;
    @XmlElementRef(name = "montoOperacion", namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> montoOperacion;
    @XmlElementRef(name = "numAutorizacion", namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", type = JAXBElement.class, required = false)
    protected JAXBElement<String> numAutorizacion;
    @XmlElementRef(name = "operacionAbr", namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", type = JAXBElement.class, required = false)
    protected JAXBElement<String> operacionAbr;
    @XmlElementRef(name = "operacionDesc", namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", type = JAXBElement.class, required = false)
    protected JAXBElement<String> operacionDesc;
    @XmlElementRef(name = "sucursal", namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> sucursal;
    @XmlElementRef(name = "tarjeta", namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", type = JAXBElement.class, required = false)
    protected JAXBElement<String> tarjeta;
    @XmlElementRef(name = "tipoContratoAbr", namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", type = JAXBElement.class, required = false)
    protected JAXBElement<String> tipoContratoAbr;
    @XmlElementRef(name = "tipoContratoDesc", namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", type = JAXBElement.class, required = false)
    protected JAXBElement<String> tipoContratoDesc;
    @XmlElementRef(name = "tipoTarjeta", namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", type = JAXBElement.class, required = false)
    protected JAXBElement<String> tipoTarjeta;
    @XmlElementRef(name = "transaccion", namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", type = JAXBElement.class, required = false)
    protected JAXBElement<Long> transaccion;

    /**
     * Gets the value of the capitalActual property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getCapitalActual() {
        return capitalActual;
    }

    /**
     * Sets the value of the capitalActual property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setCapitalActual(JAXBElement<BigDecimal> value) {
        this.capitalActual = value;
    }

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
     * Gets the value of the comisiones property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getComisiones() {
        return comisiones;
    }

    /**
     * Sets the value of the comisiones property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setComisiones(JAXBElement<BigDecimal> value) {
        this.comisiones = value;
    }

    /**
     * Gets the value of the estadoTransaccion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEstadoTransaccion() {
        return estadoTransaccion;
    }

    /**
     * Sets the value of the estadoTransaccion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEstadoTransaccion(JAXBElement<String> value) {
        this.estadoTransaccion = value;
    }

    /**
     * Gets the value of the estatus property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEstatus() {
        return estatus;
    }

    /**
     * Sets the value of the estatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEstatus(Boolean value) {
        this.estatus = value;
    }

    /**
     * Gets the value of the fecha property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getFecha() {
        return fecha;
    }

    /**
     * Sets the value of the fecha property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setFecha(JAXBElement<XMLGregorianCalendar> value) {
        this.fecha = value;
    }

    /**
     * Gets the value of the folioPartida property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public JAXBElement<Long> getFolioPartida() {
        return folioPartida;
    }

    /**
     * Sets the value of the folioPartida property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public void setFolioPartida(JAXBElement<Long> value) {
        this.folioPartida = value;
    }

    /**
     * Gets the value of the idConsumidor property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getIdConsumidor() {
        return idConsumidor;
    }

    /**
     * Sets the value of the idConsumidor property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setIdConsumidor(JAXBElement<Integer> value) {
        this.idConsumidor = value;
    }

    /**
     * Gets the value of the idOperacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getIdOperacion() {
        return idOperacion;
    }

    /**
     * Sets the value of the idOperacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setIdOperacion(JAXBElement<Integer> value) {
        this.idOperacion = value;
    }

    /**
     * Gets the value of the idTarjeta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdTarjeta() {
        return idTarjeta;
    }

    /**
     * Sets the value of the idTarjeta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdTarjeta(JAXBElement<String> value) {
        this.idTarjeta = value;
    }

    /**
     * Gets the value of the idTipoContrato property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getIdTipoContrato() {
        return idTipoContrato;
    }

    /**
     * Sets the value of the idTipoContrato property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setIdTipoContrato(JAXBElement<Integer> value) {
        this.idTipoContrato = value;
    }

    /**
     * Gets the value of the importeTransaccion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getImporteTransaccion() {
        return importeTransaccion;
    }

    /**
     * Sets the value of the importeTransaccion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setImporteTransaccion(JAXBElement<BigDecimal> value) {
        this.importeTransaccion = value;
    }

    /**
     * Gets the value of the interes property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getInteres() {
        return interes;
    }

    /**
     * Sets the value of the interes property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setInteres(JAXBElement<BigDecimal> value) {
        this.interes = value;
    }

    /**
     * Gets the value of the marcaTarjeta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMarcaTarjeta() {
        return marcaTarjeta;
    }

    /**
     * Sets the value of the marcaTarjeta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMarcaTarjeta(JAXBElement<String> value) {
        this.marcaTarjeta = value;
    }

    /**
     * Gets the value of the mensajeError property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMensajeError() {
        return mensajeError;
    }

    /**
     * Sets the value of the mensajeError property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMensajeError(JAXBElement<String> value) {
        this.mensajeError = value;
    }

    /**
     * Gets the value of the monedaPago property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMonedaPago() {
        return monedaPago;
    }

    /**
     * Sets the value of the monedaPago property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMonedaPago(JAXBElement<String> value) {
        this.monedaPago = value;
    }

    /**
     * Gets the value of the montoOperacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getMontoOperacion() {
        return montoOperacion;
    }

    /**
     * Sets the value of the montoOperacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setMontoOperacion(JAXBElement<BigDecimal> value) {
        this.montoOperacion = value;
    }

    /**
     * Gets the value of the numAutorizacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNumAutorizacion() {
        return numAutorizacion;
    }

    /**
     * Sets the value of the numAutorizacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNumAutorizacion(JAXBElement<String> value) {
        this.numAutorizacion = value;
    }

    /**
     * Gets the value of the operacionAbr property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOperacionAbr() {
        return operacionAbr;
    }

    /**
     * Sets the value of the operacionAbr property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOperacionAbr(JAXBElement<String> value) {
        this.operacionAbr = value;
    }

    /**
     * Gets the value of the operacionDesc property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOperacionDesc() {
        return operacionDesc;
    }

    /**
     * Sets the value of the operacionDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOperacionDesc(JAXBElement<String> value) {
        this.operacionDesc = value;
    }

    /**
     * Gets the value of the sucursal property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getSucursal() {
        return sucursal;
    }

    /**
     * Sets the value of the sucursal property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setSucursal(JAXBElement<Integer> value) {
        this.sucursal = value;
    }

    /**
     * Gets the value of the tarjeta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTarjeta() {
        return tarjeta;
    }

    /**
     * Sets the value of the tarjeta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTarjeta(JAXBElement<String> value) {
        this.tarjeta = value;
    }

    /**
     * Gets the value of the tipoContratoAbr property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoContratoAbr() {
        return tipoContratoAbr;
    }

    /**
     * Sets the value of the tipoContratoAbr property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoContratoAbr(JAXBElement<String> value) {
        this.tipoContratoAbr = value;
    }

    /**
     * Gets the value of the tipoContratoDesc property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoContratoDesc() {
        return tipoContratoDesc;
    }

    /**
     * Sets the value of the tipoContratoDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoContratoDesc(JAXBElement<String> value) {
        this.tipoContratoDesc = value;
    }

    /**
     * Gets the value of the tipoTarjeta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoTarjeta() {
        return tipoTarjeta;
    }

    /**
     * Sets the value of the tipoTarjeta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoTarjeta(JAXBElement<String> value) {
        this.tipoTarjeta = value;
    }

    /**
     * Gets the value of the transaccion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public JAXBElement<Long> getTransaccion() {
        return transaccion;
    }

    /**
     * Sets the value of the transaccion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public void setTransaccion(JAXBElement<Long> value) {
        this.transaccion = value;
    }

}
