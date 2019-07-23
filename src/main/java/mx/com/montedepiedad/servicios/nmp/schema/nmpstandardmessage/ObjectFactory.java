
package mx.com.montedepiedad.servicios.nmp.schema.nmpstandardmessage;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.com.montedepiedad.servicios.nmp.schema.nmpstandardmessage package. 
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

    private final static QName _MessageErrorDescripcionError_QNAME = new QName("http://nmpstandardmessage.schema.nmp.servicios.montedepiedad.com.mx", "descripcionError");
    private final static QName _MessageErrorCodigoError_QNAME = new QName("http://nmpstandardmessage.schema.nmp.servicios.montedepiedad.com.mx", "codigoError");
    private final static QName _MessageErrorSeveridad_QNAME = new QName("http://nmpstandardmessage.schema.nmp.servicios.montedepiedad.com.mx", "severidad");
    private final static QName _MessageErrorTipoError_QNAME = new QName("http://nmpstandardmessage.schema.nmp.servicios.montedepiedad.com.mx", "tipoError");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.com.montedepiedad.servicios.nmp.schema.nmpstandardmessage
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link MessageError }
     * 
     */
    public MessageError createMessageError() {
        return new MessageError();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nmpstandardmessage.schema.nmp.servicios.montedepiedad.com.mx", name = "descripcionError", scope = MessageError.class)
    public JAXBElement<String> createMessageErrorDescripcionError(String value) {
        return new JAXBElement<String>(_MessageErrorDescripcionError_QNAME, String.class, MessageError.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nmpstandardmessage.schema.nmp.servicios.montedepiedad.com.mx", name = "codigoError", scope = MessageError.class)
    public JAXBElement<String> createMessageErrorCodigoError(String value) {
        return new JAXBElement<String>(_MessageErrorCodigoError_QNAME, String.class, MessageError.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nmpstandardmessage.schema.nmp.servicios.montedepiedad.com.mx", name = "severidad", scope = MessageError.class)
    public JAXBElement<String> createMessageErrorSeveridad(String value) {
        return new JAXBElement<String>(_MessageErrorSeveridad_QNAME, String.class, MessageError.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nmpstandardmessage.schema.nmp.servicios.montedepiedad.com.mx", name = "tipoError", scope = MessageError.class)
    public JAXBElement<String> createMessageErrorTipoError(String value) {
        return new JAXBElement<String>(_MessageErrorTipoError_QNAME, String.class, MessageError.class, value);
    }

}
