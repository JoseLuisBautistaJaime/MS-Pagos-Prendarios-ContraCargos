package mx.com.nmp.pagos.mimonte;

import mx.com.nmp.pagos.mimonte.config.ApplicationProperties;
import mx.com.nmp.pagos.mimonte.config.Constants;
import mx.com.nmp.pagos.mimonte.util.DefaultProfileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;


/**
 * Nombre: MimonteApplication
 * Descripcion: Clase principal de arranque, utiliza las anotaciones pertinentes para realizar la autoconfiguracion

 * @author: Javier Hernandez Barraza jhernandez@quarksoft.net
 * @version: 0.1
 */

@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperties.class})
@EntityScan(basePackageClasses = {MimonteApplication.class, Jsr310JpaConverters.class})
public class MimonteApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(MimonteApplication.class);
    private final Environment env;

    /**
     * Inyección de dependencias por constructor
     *
     * @param env Entorno
     */
    public MimonteApplication(Environment env) {
        this.env = env;
    }

    /**
     * Inicializa la aplicación.
     * <p>
     * Los profiles de Spring pueden ser especificados como argumentos de línea de
     * comandos: --spring.profiles.active=el-profile-activo
     */
    @PostConstruct
    public void initApplication() {
        Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
        if (activeProfiles.contains(Constants.SPRING_PROFILE_DEVELOPMENT) &&
                activeProfiles.contains(Constants.SPRING_PROFILE_BMX)) {
            LOGGER.error("Los profiles 'dev' y 'bmx' no deben ser especificados al mismo tiempo.");
        }
        if (activeProfiles.contains(Constants.SPRING_PROFILE_DEVELOPMENT) &&
                activeProfiles.contains(Constants.SPRING_PROFILE_CLOUD)) {
            LOGGER.error("Los profiles 'dev' y 'cloud' no deben ser especificados al mismo tiempo.");
        }
        LOGGER.debug("DEBUG--------------");
        LOGGER.info("INFO--------------");
    }

    public static void main(String[] args) throws UnknownHostException {

        System.setProperty("spring.security.strategy", "MODE_INHERITABLETHREADLOCAL");
        SpringApplication app = new SpringApplication(MimonteApplication.class);
        DefaultProfileUtil.addDefaultProfile(app);
        Environment env = app.run(args).getEnvironment();
        String protocol = "http";
        if (env.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }
        LOGGER.info("\n----------------------------------------------------------\n\t" +
                        "Aplicacion '{}' en ejecucion. URLs de acceso:\n\t" +
                        "Local: \t\t{}://localhost:{}\n\t" +
                        "Externa: \t{}://{}:{}\n\t" +
                        "Profile(s): \t{}\n----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                protocol,
                env.getProperty("server.port"),
                protocol,
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"),
                env.getActiveProfiles());

        LOGGER.debug("DEBUG--------------");
        LOGGER.info("INFO--------------");
    }
}
