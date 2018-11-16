package mx.com.nmp.pagos.mimonte.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;


/**
 * Configuración de aspectos relacionados con base de datos en ambiente cloud
 *
 * @author Javier Hernandez
 */

@Configuration
@Profile(Constants.SPRING_PROFILE_CLOUD)
public class CloudDatabaseConfiguration extends AbstractCloudConfig {

    private final Logger LOGGER = LoggerFactory.getLogger(CloudDatabaseConfiguration.class);

    @Bean
    public DataSource dataSource() {
        LOGGER.info("Configurando datasource JDBC desde proveedor cloud...");
        return connectionFactory().dataSource();
    }

}