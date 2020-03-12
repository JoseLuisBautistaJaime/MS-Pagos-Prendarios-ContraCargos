

package mx.com.nmp.pagos.mimonte.filter;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Base64;
import java.util.Collections;



/**
 * Nombre: MimonteApplication
 * Descripcion:  Filtra las peticiones a {host}:{puerto}/mimonte/v1 * para procesar el header Authorization y colocando usuario y
 *  * nombre de usuario que realizo la petición* @author: Javier Hernandez Barraza jhernandez@quarksoft.net
 * @version: 0.1
 */

@Component
public class RESTAuthorizationHeaderUsuarioFilter extends GenericFilterBean {
    private static final String CREDENTIALS_CHARSET = "UTF-8";

    /**
     * Procesa el encabezado de Authorization para colocar los datos de usuario en el contexto de seguridad
     *
     * {@inheritDoc}
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            String headerAuthorization = httpServletRequest.getHeader("Authorization");
            String[] tokens = extractAndDecodeHeader(headerAuthorization);
            String usuario = tokens[0];

            if (authenticationIsRequired(usuario)) {
                UsernamePasswordAuthenticationToken userInfo =
                        new UsernamePasswordAuthenticationToken(usuario, null, Collections.emptyList());
                userInfo.setDetails(tokens[1]);
                SecurityContextHolder.getContext().setAuthentication(userInfo);
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }


    /**
     * Procesa el header de Authorization decodificar de base64 y se parar por :
     *
     * @param header Header
     *
     * @return tokens encontrados en el header
     */
    private String[] extractAndDecodeHeader(String header) {
        String[] authorization = new String[]{"SYSTEM", "SYSTEM"};

        if (!StringUtils.hasText(header)) {
            return authorization;
        }

        byte[] decoded;

        try {
            byte[] base64Token = header.substring(6).getBytes(CREDENTIALS_CHARSET);
            decoded = Base64.getDecoder().decode(base64Token);
            String token = new String(decoded, CREDENTIALS_CHARSET);
            int delim = token.indexOf(":");

            if (delim > -1) {
                authorization = new String[] { token.substring(0, delim), token.substring(delim + 1) };
            }
        } catch (Exception ignored) {

        }

        return authorization;
    }

    /**
     * VErifica si el usuario requiere autenticacion
     *
     * @param username Usuario
     *
     * @return Verdadero si requiere autenticacion, falso si no
     */
    private boolean authenticationIsRequired(String username) {
        Authentication existingAuth = SecurityContextHolder.getContext().getAuthentication();

        if (existingAuth == null || !existingAuth.isAuthenticated()) {
            return true;
        }

        return existingAuth instanceof UsernamePasswordAuthenticationToken && !existingAuth.getName().equals(username);

    }
}
