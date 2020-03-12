
package mx.com.montedepiedad.servicios.nmp.schema.nmpconciliacionpagoonline;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.com.montedepiedad.servicios.nmp.schema.nmpconciliacionpagoonline package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _MovimientoProveedorTransaccionalOrderId_QNAME = new QName("http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", "orderId");
    private final static QName _MovimientoProveedorTransaccionalCurrency_QNAME = new QName("http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", "currency");
    private final static QName _MovimientoProveedorTransaccionalMethod_QNAME = new QName("http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", "method");
    private final static QName _MovimientoProveedorTransaccionalAuthorization_QNAME = new QName("http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", "authorization");
    private final static QName _MovimientoProveedorTransaccionalCard_QNAME = new QName("http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", "card");
    private final static QName _MovimientoProveedorTransaccionalStatus_QNAME = new QName("http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", "status");
    private final static QName _MovimientoProveedorTransaccionalErrorMessage_QNAME = new QName("http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", "errorMessage");
    private final static QName _MovimientoProveedorTransaccionalAmount_QNAME = new QName("http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", "amount");
    private final static QName _MovimientoProveedorTransaccionalTransactionType_QNAME = new QName("http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", "transactionType");
    private final static QName _MovimientoProveedorTransaccionalIdMovimiento_QNAME = new QName("http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", "idMovimiento");
    private final static QName _MovimientoConciliacionNocturnaTarjeta_QNAME = new QName("http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", "tarjeta");
    private final static QName _MovimientoConciliacionNocturnaMarcaTarjeta_QNAME = new QName("http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", "marcaTarjeta");
    private final static QName _MovimientoConciliacionNocturnaImporteTransaccion_QNAME = new QName("http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", "importeTransaccion");
    private final static QName _MovimientoConciliacionNocturnaFolioPartida_QNAME = new QName("http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", "folioPartida");
    private final static QName _MovimientoConciliacionNocturnaFecha_QNAME = new QName("http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", "fecha");
    private final static QName _MovimientoConciliacionNocturnaTipoContratoAbr_QNAME = new QName("http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", "tipoContratoAbr");
    private final static QName _MovimientoConciliacionNocturnaMensajeError_QNAME = new QName("http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", "mensajeError");
    private final static QName _MovimientoConciliacionNocturnaInteres_QNAME = new QName("http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", "interes");
    private final static QName _MovimientoConciliacionNocturnaCapitalActual_QNAME = new QName("http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", "capitalActual");
    private final static QName _MovimientoConciliacionNocturnaMontoOperacion_QNAME = new QName("http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", "montoOperacion");
    private final static QName _MovimientoConciliacionNocturnaIdTipoContrato_QNAME = new QName("http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", "idTipoContrato");
    private final static QName _MovimientoConciliacionNocturnaIdTarjeta_QNAME = new QName("http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", "idTarjeta");
    private final static QName _MovimientoConciliacionNocturnaComisiones_QNAME = new QName("http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", "comisiones");
    private final static QName _MovimientoConciliacionNocturnaIdOperacion_QNAME = new QName("http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", "idOperacion");
    private final static QName _MovimientoConciliacionNocturnaOperacionDesc_QNAME = new QName("http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", "operacionDesc");
    private final static QName _MovimientoConciliacionNocturnaTransaccion_QNAME = new QName("http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", "transaccion");
    private final static QName _MovimientoConciliacionNocturnaTipoContratoDesc_QNAME = new QName("http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", "tipoContratoDesc");
    private final static QName _MovimientoConciliacionNocturnaSucursal_QNAME = new QName("http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", "sucursal");
    private final static QName _MovimientoConciliacionNocturnaCodigoError_QNAME = new QName("http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", "codigoError");
    private final static QName _MovimientoConciliacionNocturnaEstadoTransaccion_QNAME = new QName("http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", "estadoTransaccion");
    private final static QName _MovimientoConciliacionNocturnaIdConsumidor_QNAME = new QName("http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", "idConsumidor");
    private final static QName _MovimientoConciliacionNocturnaOperacionAbr_QNAME = new QName("http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", "operacionAbr");
    private final static QName _MovimientoConciliacionNocturnaTipoTarjeta_QNAME = new QName("http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", "tipoTarjeta");
    private final static QName _MovimientoConciliacionNocturnaNumAutorizacion_QNAME = new QName("http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", "numAutorizacion");
    private final static QName _MovimientoConciliacionNocturnaMonedaPago_QNAME = new QName("http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", "monedaPago");
    private final static QName _TarjetaPagoMovimientoProveedorTransaccionalCardNumber_QNAME = new QName("http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", "cardNumber");
    private final static QName _TarjetaPagoMovimientoProveedorTransaccionalType_QNAME = new QName("http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", "type");
    private final static QName _TarjetaPagoMovimientoProveedorTransaccionalBrand_QNAME = new QName("http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", "brand");
    private final static QName _TarjetaPagoMovimientoProveedorTransaccionalId_QNAME = new QName("http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", "id");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.com.montedepiedad.servicios.nmp.schema.nmpconciliacionpagoonline
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link MovimientoConciliacionNocturna }
     * 
     */
    public MovimientoConciliacionNocturna createMovimientoConciliacionNocturna() {
        return new MovimientoConciliacionNocturna();
    }

    /**
     * Create an instance of {@link ArrayOfMovimientoProveedorTransaccional }
     * 
     */
    public ArrayOfMovimientoProveedorTransaccional createArrayOfMovimientoProveedorTransaccional() {
        return new ArrayOfMovimientoProveedorTransaccional();
    }

    /**
     * Create an instance of {@link MovimientoProveedorTransaccional }
     * 
     */
    public MovimientoProveedorTransaccional createMovimientoProveedorTransaccional() {
        return new MovimientoProveedorTransaccional();
    }

    /**
     * Create an instance of {@link ArrayOfMovimientoConciliacionNocturna }
     * 
     */
    public ArrayOfMovimientoConciliacionNocturna createArrayOfMovimientoConciliacionNocturna() {
        return new ArrayOfMovimientoConciliacionNocturna();
    }

    /**
     * Create an instance of {@link TarjetaPagoMovimientoProveedorTransaccional }
     * 
     */
    public TarjetaPagoMovimientoProveedorTransaccional createTarjetaPagoMovimientoProveedorTransaccional() {
        return new TarjetaPagoMovimientoProveedorTransaccional();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", name = "orderId", scope = MovimientoProveedorTransaccional.class)
    public JAXBElement<String> createMovimientoProveedorTransaccionalOrderId(String value) {
        return new JAXBElement<String>(_MovimientoProveedorTransaccionalOrderId_QNAME, String.class, MovimientoProveedorTransaccional.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", name = "currency", scope = MovimientoProveedorTransaccional.class)
    public JAXBElement<String> createMovimientoProveedorTransaccionalCurrency(String value) {
        return new JAXBElement<String>(_MovimientoProveedorTransaccionalCurrency_QNAME, String.class, MovimientoProveedorTransaccional.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", name = "method", scope = MovimientoProveedorTransaccional.class)
    public JAXBElement<String> createMovimientoProveedorTransaccionalMethod(String value) {
        return new JAXBElement<String>(_MovimientoProveedorTransaccionalMethod_QNAME, String.class, MovimientoProveedorTransaccional.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", name = "authorization", scope = MovimientoProveedorTransaccional.class)
    public JAXBElement<String> createMovimientoProveedorTransaccionalAuthorization(String value) {
        return new JAXBElement<String>(_MovimientoProveedorTransaccionalAuthorization_QNAME, String.class, MovimientoProveedorTransaccional.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TarjetaPagoMovimientoProveedorTransaccional }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", name = "card", scope = MovimientoProveedorTransaccional.class)
    public JAXBElement<TarjetaPagoMovimientoProveedorTransaccional> createMovimientoProveedorTransaccionalCard(TarjetaPagoMovimientoProveedorTransaccional value) {
        return new JAXBElement<TarjetaPagoMovimientoProveedorTransaccional>(_MovimientoProveedorTransaccionalCard_QNAME, TarjetaPagoMovimientoProveedorTransaccional.class, MovimientoProveedorTransaccional.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", name = "status", scope = MovimientoProveedorTransaccional.class)
    public JAXBElement<String> createMovimientoProveedorTransaccionalStatus(String value) {
        return new JAXBElement<String>(_MovimientoProveedorTransaccionalStatus_QNAME, String.class, MovimientoProveedorTransaccional.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", name = "errorMessage", scope = MovimientoProveedorTransaccional.class)
    public JAXBElement<String> createMovimientoProveedorTransaccionalErrorMessage(String value) {
        return new JAXBElement<String>(_MovimientoProveedorTransaccionalErrorMessage_QNAME, String.class, MovimientoProveedorTransaccional.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", name = "amount", scope = MovimientoProveedorTransaccional.class)
    public JAXBElement<BigDecimal> createMovimientoProveedorTransaccionalAmount(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_MovimientoProveedorTransaccionalAmount_QNAME, BigDecimal.class, MovimientoProveedorTransaccional.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", name = "transactionType", scope = MovimientoProveedorTransaccional.class)
    public JAXBElement<String> createMovimientoProveedorTransaccionalTransactionType(String value) {
        return new JAXBElement<String>(_MovimientoProveedorTransaccionalTransactionType_QNAME, String.class, MovimientoProveedorTransaccional.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", name = "idMovimiento", scope = MovimientoProveedorTransaccional.class)
    public JAXBElement<String> createMovimientoProveedorTransaccionalIdMovimiento(String value) {
        return new JAXBElement<String>(_MovimientoProveedorTransaccionalIdMovimiento_QNAME, String.class, MovimientoProveedorTransaccional.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", name = "tarjeta", scope = MovimientoConciliacionNocturna.class)
    public JAXBElement<String> createMovimientoConciliacionNocturnaTarjeta(String value) {
        return new JAXBElement<String>(_MovimientoConciliacionNocturnaTarjeta_QNAME, String.class, MovimientoConciliacionNocturna.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", name = "marcaTarjeta", scope = MovimientoConciliacionNocturna.class)
    public JAXBElement<String> createMovimientoConciliacionNocturnaMarcaTarjeta(String value) {
        return new JAXBElement<String>(_MovimientoConciliacionNocturnaMarcaTarjeta_QNAME, String.class, MovimientoConciliacionNocturna.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", name = "importeTransaccion", scope = MovimientoConciliacionNocturna.class)
    public JAXBElement<BigDecimal> createMovimientoConciliacionNocturnaImporteTransaccion(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_MovimientoConciliacionNocturnaImporteTransaccion_QNAME, BigDecimal.class, MovimientoConciliacionNocturna.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", name = "folioPartida", scope = MovimientoConciliacionNocturna.class)
    public JAXBElement<Long> createMovimientoConciliacionNocturnaFolioPartida(Long value) {
        return new JAXBElement<Long>(_MovimientoConciliacionNocturnaFolioPartida_QNAME, Long.class, MovimientoConciliacionNocturna.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", name = "fecha", scope = MovimientoConciliacionNocturna.class)
    public JAXBElement<XMLGregorianCalendar> createMovimientoConciliacionNocturnaFecha(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_MovimientoConciliacionNocturnaFecha_QNAME, XMLGregorianCalendar.class, MovimientoConciliacionNocturna.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", name = "tipoContratoAbr", scope = MovimientoConciliacionNocturna.class)
    public JAXBElement<String> createMovimientoConciliacionNocturnaTipoContratoAbr(String value) {
        return new JAXBElement<String>(_MovimientoConciliacionNocturnaTipoContratoAbr_QNAME, String.class, MovimientoConciliacionNocturna.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", name = "mensajeError", scope = MovimientoConciliacionNocturna.class)
    public JAXBElement<String> createMovimientoConciliacionNocturnaMensajeError(String value) {
        return new JAXBElement<String>(_MovimientoConciliacionNocturnaMensajeError_QNAME, String.class, MovimientoConciliacionNocturna.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", name = "interes", scope = MovimientoConciliacionNocturna.class)
    public JAXBElement<BigDecimal> createMovimientoConciliacionNocturnaInteres(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_MovimientoConciliacionNocturnaInteres_QNAME, BigDecimal.class, MovimientoConciliacionNocturna.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", name = "capitalActual", scope = MovimientoConciliacionNocturna.class)
    public JAXBElement<BigDecimal> createMovimientoConciliacionNocturnaCapitalActual(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_MovimientoConciliacionNocturnaCapitalActual_QNAME, BigDecimal.class, MovimientoConciliacionNocturna.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", name = "montoOperacion", scope = MovimientoConciliacionNocturna.class)
    public JAXBElement<BigDecimal> createMovimientoConciliacionNocturnaMontoOperacion(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_MovimientoConciliacionNocturnaMontoOperacion_QNAME, BigDecimal.class, MovimientoConciliacionNocturna.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", name = "idTipoContrato", scope = MovimientoConciliacionNocturna.class)
    public JAXBElement<Integer> createMovimientoConciliacionNocturnaIdTipoContrato(Integer value) {
        return new JAXBElement<Integer>(_MovimientoConciliacionNocturnaIdTipoContrato_QNAME, Integer.class, MovimientoConciliacionNocturna.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", name = "idTarjeta", scope = MovimientoConciliacionNocturna.class)
    public JAXBElement<String> createMovimientoConciliacionNocturnaIdTarjeta(String value) {
        return new JAXBElement<String>(_MovimientoConciliacionNocturnaIdTarjeta_QNAME, String.class, MovimientoConciliacionNocturna.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", name = "comisiones", scope = MovimientoConciliacionNocturna.class)
    public JAXBElement<BigDecimal> createMovimientoConciliacionNocturnaComisiones(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_MovimientoConciliacionNocturnaComisiones_QNAME, BigDecimal.class, MovimientoConciliacionNocturna.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", name = "idOperacion", scope = MovimientoConciliacionNocturna.class)
    public JAXBElement<Integer> createMovimientoConciliacionNocturnaIdOperacion(Integer value) {
        return new JAXBElement<Integer>(_MovimientoConciliacionNocturnaIdOperacion_QNAME, Integer.class, MovimientoConciliacionNocturna.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", name = "operacionDesc", scope = MovimientoConciliacionNocturna.class)
    public JAXBElement<String> createMovimientoConciliacionNocturnaOperacionDesc(String value) {
        return new JAXBElement<String>(_MovimientoConciliacionNocturnaOperacionDesc_QNAME, String.class, MovimientoConciliacionNocturna.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", name = "transaccion", scope = MovimientoConciliacionNocturna.class)
    public JAXBElement<Long> createMovimientoConciliacionNocturnaTransaccion(Long value) {
        return new JAXBElement<Long>(_MovimientoConciliacionNocturnaTransaccion_QNAME, Long.class, MovimientoConciliacionNocturna.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", name = "tipoContratoDesc", scope = MovimientoConciliacionNocturna.class)
    public JAXBElement<String> createMovimientoConciliacionNocturnaTipoContratoDesc(String value) {
        return new JAXBElement<String>(_MovimientoConciliacionNocturnaTipoContratoDesc_QNAME, String.class, MovimientoConciliacionNocturna.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", name = "sucursal", scope = MovimientoConciliacionNocturna.class)
    public JAXBElement<Integer> createMovimientoConciliacionNocturnaSucursal(Integer value) {
        return new JAXBElement<Integer>(_MovimientoConciliacionNocturnaSucursal_QNAME, Integer.class, MovimientoConciliacionNocturna.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", name = "codigoError", scope = MovimientoConciliacionNocturna.class)
    public JAXBElement<String> createMovimientoConciliacionNocturnaCodigoError(String value) {
        return new JAXBElement<String>(_MovimientoConciliacionNocturnaCodigoError_QNAME, String.class, MovimientoConciliacionNocturna.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", name = "estadoTransaccion", scope = MovimientoConciliacionNocturna.class)
    public JAXBElement<String> createMovimientoConciliacionNocturnaEstadoTransaccion(String value) {
        return new JAXBElement<String>(_MovimientoConciliacionNocturnaEstadoTransaccion_QNAME, String.class, MovimientoConciliacionNocturna.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", name = "idConsumidor", scope = MovimientoConciliacionNocturna.class)
    public JAXBElement<Integer> createMovimientoConciliacionNocturnaIdConsumidor(Integer value) {
        return new JAXBElement<Integer>(_MovimientoConciliacionNocturnaIdConsumidor_QNAME, Integer.class, MovimientoConciliacionNocturna.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", name = "operacionAbr", scope = MovimientoConciliacionNocturna.class)
    public JAXBElement<String> createMovimientoConciliacionNocturnaOperacionAbr(String value) {
        return new JAXBElement<String>(_MovimientoConciliacionNocturnaOperacionAbr_QNAME, String.class, MovimientoConciliacionNocturna.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", name = "tipoTarjeta", scope = MovimientoConciliacionNocturna.class)
    public JAXBElement<String> createMovimientoConciliacionNocturnaTipoTarjeta(String value) {
        return new JAXBElement<String>(_MovimientoConciliacionNocturnaTipoTarjeta_QNAME, String.class, MovimientoConciliacionNocturna.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", name = "numAutorizacion", scope = MovimientoConciliacionNocturna.class)
    public JAXBElement<String> createMovimientoConciliacionNocturnaNumAutorizacion(String value) {
        return new JAXBElement<String>(_MovimientoConciliacionNocturnaNumAutorizacion_QNAME, String.class, MovimientoConciliacionNocturna.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", name = "monedaPago", scope = MovimientoConciliacionNocturna.class)
    public JAXBElement<String> createMovimientoConciliacionNocturnaMonedaPago(String value) {
        return new JAXBElement<String>(_MovimientoConciliacionNocturnaMonedaPago_QNAME, String.class, MovimientoConciliacionNocturna.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", name = "cardNumber", scope = TarjetaPagoMovimientoProveedorTransaccional.class)
    public JAXBElement<String> createTarjetaPagoMovimientoProveedorTransaccionalCardNumber(String value) {
        return new JAXBElement<String>(_TarjetaPagoMovimientoProveedorTransaccionalCardNumber_QNAME, String.class, TarjetaPagoMovimientoProveedorTransaccional.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", name = "type", scope = TarjetaPagoMovimientoProveedorTransaccional.class)
    public JAXBElement<String> createTarjetaPagoMovimientoProveedorTransaccionalType(String value) {
        return new JAXBElement<String>(_TarjetaPagoMovimientoProveedorTransaccionalType_QNAME, String.class, TarjetaPagoMovimientoProveedorTransaccional.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", name = "brand", scope = TarjetaPagoMovimientoProveedorTransaccional.class)
    public JAXBElement<String> createTarjetaPagoMovimientoProveedorTransaccionalBrand(String value) {
        return new JAXBElement<String>(_TarjetaPagoMovimientoProveedorTransaccionalBrand_QNAME, String.class, TarjetaPagoMovimientoProveedorTransaccional.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nmpconciliacionpagoonline.schema.nmp.servicios.montedepiedad.com.mx", name = "id", scope = TarjetaPagoMovimientoProveedorTransaccional.class)
    public JAXBElement<String> createTarjetaPagoMovimientoProveedorTransaccionalId(String value) {
        return new JAXBElement<String>(_TarjetaPagoMovimientoProveedorTransaccionalId_QNAME, String.class, TarjetaPagoMovimientoProveedorTransaccional.class, value);
    }

}
