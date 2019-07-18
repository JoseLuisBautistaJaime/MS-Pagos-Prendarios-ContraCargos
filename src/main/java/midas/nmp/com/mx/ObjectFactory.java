
package midas.nmp.com.mx;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import mx.com.montedepiedad.servicios.nmp.services.nmpconciliacionpagoonline.ConciliacionPagoOnlineFaultMessage;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the midas.nmp.com.mx package. 
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

    private final static QName _ConciliacionPagoOnlineFaultMessage_QNAME = new QName("urn:mx.com.nmp.midas", "ConciliacionPagoOnlineFaultMessage");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: midas.nmp.com.mx
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ConciliarMovimientosResponse }
     * 
     */
    public ConciliarMovimientosResponse createConciliarMovimientosResponse() {
        return new ConciliarMovimientosResponse();
    }

    /**
     * Create an instance of {@link ObtenerMovimientosConciliacionNocturna }
     * 
     */
    public ObtenerMovimientosConciliacionNocturna createObtenerMovimientosConciliacionNocturna() {
        return new ObtenerMovimientosConciliacionNocturna();
    }

    /**
     * Create an instance of {@link ObtenerMovimientosConciliacionNocturnaResponse }
     * 
     */
    public ObtenerMovimientosConciliacionNocturnaResponse createObtenerMovimientosConciliacionNocturnaResponse() {
        return new ObtenerMovimientosConciliacionNocturnaResponse();
    }

    /**
     * Create an instance of {@link IsDiaHabilConciliacionResponse }
     * 
     */
    public IsDiaHabilConciliacionResponse createIsDiaHabilConciliacionResponse() {
        return new IsDiaHabilConciliacionResponse();
    }

    /**
     * Create an instance of {@link ConciliarMovimientos }
     * 
     */
    public ConciliarMovimientos createConciliarMovimientos() {
        return new ConciliarMovimientos();
    }

    /**
     * Create an instance of {@link IsDiaHabilConciliacion }
     * 
     */
    public IsDiaHabilConciliacion createIsDiaHabilConciliacion() {
        return new IsDiaHabilConciliacion();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConciliacionPagoOnlineFaultMessage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:mx.com.nmp.midas", name = "ConciliacionPagoOnlineFaultMessage")
    public JAXBElement<ConciliacionPagoOnlineFaultMessage> createConciliacionPagoOnlineFaultMessage(ConciliacionPagoOnlineFaultMessage value) {
        return new JAXBElement<ConciliacionPagoOnlineFaultMessage>(_ConciliacionPagoOnlineFaultMessage_QNAME, ConciliacionPagoOnlineFaultMessage.class, null, value);
    }

}
