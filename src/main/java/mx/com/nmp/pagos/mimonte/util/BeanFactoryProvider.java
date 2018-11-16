
package mx.com.nmp.pagos.mimonte.util;


import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


 /**
 * Nombre: BeanFactoryProvider
 * Descripcion: Utilería que permite el acceso al {@link BeanFactory} a objetos que no pertenecen al contexto spring.
 * @author: Javier Hernandez Barraza jhernandez@quarksoft.net
 * @version: 0.1
 */
@Component
public class BeanFactoryProvider {
    private static BeanFactory beanFactory;

    /**
     * Constructor.
     */
    public BeanFactoryProvider() {
        super();
    }

    /**
     * Establece el valor de {@code beanFactory}
     *
     * @param beanFactory Valor de {@code beanFactory}
     */
    @Autowired
    public void setBeanFactory(BeanFactory beanFactory) {
        BeanFactoryProvider.set(beanFactory);
    }

    public static BeanFactory get() {
        return beanFactory;
    }

    private static synchronized  void set(BeanFactory beanFactory) {
        BeanFactoryProvider.beanFactory = beanFactory;
    }
}
