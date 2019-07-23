
package mx.com.montedepiedad.servicios.nmp.services.nmpconciliacionpagoonline;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import mx.com.montedepiedad.servicios.nmp.schema.nmpstandardmessage.MessageError;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.com.montedepiedad.servicios.nmp.services.nmpconciliacionpagoonline package. 
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

    private final static QName _ConciliacionPagoOnlineFaultMessageFaultInfo_QNAME = new QName("http://nmpconciliacionpagoonline.services.nmp.servicios.montedepiedad.com.mx", "faultInfo");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.com.montedepiedad.servicios.nmp.services.nmpconciliacionpagoonline
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ConciliacionPagoOnlineFaultMessage }
     * 
     */
    public ConciliacionPagoOnlineFaultMessage createConciliacionPagoOnlineFaultMessage() {
        return new ConciliacionPagoOnlineFaultMessage();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MessageError }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nmpconciliacionpagoonline.services.nmp.servicios.montedepiedad.com.mx", name = "faultInfo", scope = ConciliacionPagoOnlineFaultMessage.class)
    public JAXBElement<MessageError> createConciliacionPagoOnlineFaultMessageFaultInfo(MessageError value) {
        return new JAXBElement<MessageError>(_ConciliacionPagoOnlineFaultMessageFaultInfo_QNAME, MessageError.class, ConciliacionPagoOnlineFaultMessage.class, value);
    }

}
