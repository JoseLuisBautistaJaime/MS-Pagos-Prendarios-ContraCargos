package mx.com.nmp.pagos.mimonte.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Configuración de aspectos relacionados con base de datos
 *
 * @author Javier Hernandez
 */


@Configuration
@EnableJpaRepositories("mx.com.nmp.pagos.mimonte.dao")
@EnableTransactionManagement
public class DatabaseConfiguration {
}


