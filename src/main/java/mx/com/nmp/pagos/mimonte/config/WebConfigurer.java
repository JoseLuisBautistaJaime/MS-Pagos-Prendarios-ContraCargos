package mx.com.nmp.pagos.mimonte.config;

import mx.com.nmp.pagos.mimonte.filter.RESTAuthorizationHeaderUsuarioFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.MimeMappings;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.EnumSet;

/**
 * Configuración de la aplicación web con APIs de Servlet 3.0
 *
 * @author Javier Hernandez
 */


@Configuration
public class WebConfigurer  implements WebMvcConfigurer, ServletContextInitializer, WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

    private final Logger log = LoggerFactory.getLogger(WebConfigurer.class);

    private final Environment env;

    private final ApplicationProperties applicationProperties;

    /**
     * Inyección de dependencias por constructor
     *
     * @param env
     */
    public WebConfigurer(Environment env, ApplicationProperties applicationProperties) {
        this.env = env;
        this.applicationProperties = applicationProperties;
    }

    /**
     * Configure the given {@link ServletContext} with any servlets, filters, listeners
     * context-params and attributes necessary for initialization.
     *
     * @param servletContext the {@code ServletContext} to initialize
     * @throws ServletException if any call against the given {@code ServletContext}
     *                          throws a {@code ServletException}
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        if (env.getActiveProfiles().length != 0) {
            log.info("Configuracion de la aplicacion Web, utilizando profiles: {}", (Object[]) env.getActiveProfiles());
        }
        EnumSet<DispatcherType> disps = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.ASYNC);
        log.info("Aplicacion Web configurada");
    }

    /**
     * Customize the specified {@link WebServerFactoryCustomizer}.
     *
     * @param container the container to customize
     */
    @Override
    public void customize(ConfigurableServletWebServerFactory container) {
        MimeMappings mappings = new MimeMappings(MimeMappings.DEFAULT);
        // IE issue, see https://github.com/jhipster/generator-jhipster/pull/711
        mappings.add("html", "text/html;charset=utf-8");
        // CloudFoundry issue, see https://github.com/cloudfoundry/gorouter/issues/64
        mappings.add("json", "text/html;charset=utf-8");
        container.setMimeMappings(mappings);
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = applicationProperties.getCors();
        if (config.getAllowedOrigins() != null && !config.getAllowedOrigins().isEmpty()) {
            log.debug("Registrando filtro CORS...");
            source.registerCorsConfiguration("/mimonte/**", config);
            source.registerCorsConfiguration("/**", config);
            // source.registerCorsConfiguration("/extrafilter/**", config);
            // source.registerCorsConfiguration("/v2/api-docs", config);
        }
        CorsFilter corsFilter = new CorsFilter(source);
        return corsFilter;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();

        registrationBean.setFilter(restAuthorizationHeaderUsuarioFilter());
        registrationBean.setEnabled(false);
        registrationBean.addUrlPatterns("/mimonte/*");
        registrationBean.setOrder(1);
        registrationBean.setDispatcherTypes(DispatcherType.ERROR, DispatcherType.FORWARD, DispatcherType.REQUEST);

        return registrationBean;
    }


    @Bean
    public RESTAuthorizationHeaderUsuarioFilter restAuthorizationHeaderUsuarioFilter() {
        return new RESTAuthorizationHeaderUsuarioFilter();
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        log.debug("Realizando el registro de filtros CORS desde Adapter...");
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("PUT", "DELETE", "POST", "GET")
                .allowedHeaders("*")
                .allowCredentials(false).maxAge(3600);
    }
}
