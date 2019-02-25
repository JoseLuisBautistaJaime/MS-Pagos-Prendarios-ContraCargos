
package mx.com.nmp.pagos.mimonte.filter;


import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.CACHE_CONTROL;
import static org.springframework.http.HttpHeaders.EXPIRES;
import static org.springframework.http.HttpHeaders.PRAGMA;

/**
 * Nombre: MimonteApplication
 * Descripcion:  Filtra las peticiones a {host}:{puerto}/mimonte/v1/* para procesar las peticiones con el header X-Requested-With
 *  * y colocar los headers de control de cache en la respuesta* @author: Javier Hernandez Barraza jhernandez@quarksoft.net
 * @version: 0.1
 */

@Component
public class XmlHttpRequestFilter extends GenericFilterBean {

    private static final String X_REQUESTED_WITH = "X-Requested-With";
    private static final String XML_HTTP_REQUEST = "XmlHttpRequest";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            String xmlHttpRequest = httpServletRequest.getHeader(X_REQUESTED_WITH);

            if (XML_HTTP_REQUEST.equalsIgnoreCase(xmlHttpRequest) && response instanceof HttpServletResponse) {
                HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                httpServletResponse.setHeader(CACHE_CONTROL, "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader(PRAGMA, "no-cache");
                httpServletResponse.setHeader(EXPIRES, "-1");
            }
        }

        chain.doFilter(request, response);
    }
}
