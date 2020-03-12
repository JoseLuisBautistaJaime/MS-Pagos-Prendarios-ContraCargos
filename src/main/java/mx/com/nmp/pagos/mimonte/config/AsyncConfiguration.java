/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @name AsyncConfiguration
 * @description Clase de configuracion de spring donde se crea un bean Executor
 *              para asincronicidad de metodos
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 31/10/2019 13:19 hrs.
 * @version 0.1
 */
@Configuration
@EnableAsync
public class AsyncConfiguration {

	@Bean(name = "conciliacionAsyncExecutor")
	public Executor asyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(2);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("Async-");
		executor.initialize();
		return executor;
	}

}
