package mx.com.nmp.pagos.mimonte.config;


import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;

import java.util.TimeZone;

/**
 * Propiedades de configuración de la aplicación
 *
 * @author Javier Hernandez
 */
@Configuration
@ConfigurationProperties(prefix = "", ignoreUnknownFields = true)
public class ApplicationProperties {

    private final CorsConfiguration cors = new CorsConfiguration();
    private final Swagger swagger = new Swagger();

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonObjectMapperCustomization() {
        return jacksonObjectMapperBuilder ->
                jacksonObjectMapperBuilder.timeZone(TimeZone.getDefault());
    }

    public CorsConfiguration getCors() {
        return cors;
    }

    public Swagger getSwagger() {
        return swagger;
    }

    public static class Swagger {

        private String title = "API Mi Monte";

        private String description = "Documentacion API REST Micro Servicios Mi Monte Pagos";

        private String version = "0.0.1";

        private String termsOfServiceUrl;

        private String contactName;

        private String contactUrl;

        private String contactEmail;

        private String license;

        private String licenseUrl;

        private String defaultIncludePattern = "/.*";

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getTermsOfServiceUrl() {
            return termsOfServiceUrl;
        }

        public void setTermsOfServiceUrl(String termsOfServiceUrl) {
            this.termsOfServiceUrl = termsOfServiceUrl;
        }

        public String getContactName() {
            return contactName;
        }

        public void setContactName(String contactName) {
            this.contactName = contactName;
        }

        public String getContactUrl() {
            return contactUrl;
        }

        public void setContactUrl(String contactUrl) {
            this.contactUrl = contactUrl;
        }

        public String getContactEmail() {
            return contactEmail;
        }

        public void setContactEmail(String contactEmail) {
            this.contactEmail = contactEmail;
        }

        public String getLicense() {
            return license;
        }

        public void setLicense(String license) {
            this.license = license;
        }

        public String getLicenseUrl() {
            return licenseUrl;
        }

        public void setLicenseUrl(String licenseUrl) {
            this.licenseUrl = licenseUrl;
        }

        public String getDefaultIncludePattern() {
            return defaultIncludePattern;
        }

        public void setDefaultIncludePattern(String defaultIncludePattern) {
            this.defaultIncludePattern = defaultIncludePattern;
        }
    }


    private MiMonte mimonte;

	public MiMonte getMimonte() {
		return mimonte;
	}

	public void setMimonte(MiMonte mimonte) {
		this.mimonte = mimonte;
	}

    public static class MiMonte {
  
    	private Variables variables;
  
		public Variables getVariables() {
			return variables;
		}

		public void setVariables(Variables variables) {
			this.variables = variables;
		}

    	public static class Variables {
    		
    		private Mail mail;
    		private RestTemplate restTemplate;
    		private ConsultaEstadoCuenta consultaEstadoCuenta;
    		
			public Mail getMail() {
				return mail;
			}

			public void setMail(Mail mail) {
				this.mail = mail;
			}

			public RestTemplate getRestTemplate() {
				return restTemplate;
			}

			public void setRestTemplate(RestTemplate restTemplate) {
				this.restTemplate = restTemplate;
			}

			public ConsultaEstadoCuenta getConsultaEstadoCuenta() {
				return consultaEstadoCuenta;
			}

			public void setConsultaEstadoCuenta(ConsultaEstadoCuenta consultaEstadoCuenta) {
				this.consultaEstadoCuenta = consultaEstadoCuenta;
			}

			public static class Mail {
				
				private String from;
				private SolicitudDevolucion solicitudDevolucion;

				public String getFrom() {
					return from;
				}

				public void setFrom(String from) {
					this.from = from;
				}

				public SolicitudDevolucion getSolicitudDevolucion() {
					return solicitudDevolucion;
				}

				public void setSolicitudDevolucion(SolicitudDevolucion solicitudDevolucion) {
					this.solicitudDevolucion = solicitudDevolucion;
				}

	    		public static class SolicitudDevolucion {

	    			private String titulo;
	    			private String velocityTemplate;
	    			private String bodyText1;
	    			private String bodyText2;

	    			public String getTitulo() {
						return titulo;
					}
					public void setTitulo(String titulo) {
						this.titulo = titulo;
					}
					public String getVelocityTemplate() {
						return velocityTemplate;
					}
					public void setVelocityTemplate(String velocityTemplate) {
						this.velocityTemplate = velocityTemplate;
					}
					public String getBodyText1() {
						return bodyText1;
					}
					public void setBodyText1(String bodyText1) {
						this.bodyText1 = bodyText1;
					}
					public String getBodyText2() {
						return bodyText2;
					}
					public void setBodyText2(String bodyText2) {
						this.bodyText2 = bodyText2;
					}
	    		}

			}
			
			public static class RestTemplate {
				private int maxAttempt;
				private int retryTimeInterval;

				public int getMaxAttempt() {
					return maxAttempt;
				}
				public void setMaxAttempt(int maxAttempt) {
					this.maxAttempt = maxAttempt;
				}
				public int getRetryTimeInterval() {
					return retryTimeInterval;
				}
				public void setRetryTimeInterval(int retryTimeInterval) {
					this.retryTimeInterval = retryTimeInterval;
				}
			}
			
			public static class ConsultaEstadoCuenta {
				private String url;
				private ConsultaEstadoCuentaAuth auth;
				private ConsultaEstadoCuentaHeader header;

				public String getUrl() {
					return url;
				}

				public void setUrl(String url) {
					this.url = url;
				}

				public ConsultaEstadoCuentaHeader getHeader() {
					return header;
				}

				public void setHeader(ConsultaEstadoCuentaHeader header) {
					this.header = header;
				}

				public ConsultaEstadoCuentaAuth getAuth() {
					return auth;
				}

				public void setAuth(ConsultaEstadoCuentaAuth auth) {
					this.auth = auth;
				}

				public static class ConsultaEstadoCuentaHeader {
					private String usuario;
					private String idConsumidor;
					private String idDestino;
					public String getUsuario() {
						return usuario;
					}
					public void setUsuario(String usuario) {
						this.usuario = usuario;
					}
					public String getIdConsumidor() {
						return idConsumidor;
					}
					public void setIdConsumidor(String idConsumidor) {
						this.idConsumidor = idConsumidor;
					}
					public String getIdDestino() {
						return idDestino;
					}
					public void setIdDestino(String idDestino) {
						this.idDestino = idDestino;
					}
				}

				public static class ConsultaEstadoCuentaAuth {
					private String usuario;
					private String password;
					public String getUsuario() {
						return usuario;
					}
					public void setUsuario(String usuario) {
						this.usuario = usuario;
					}
					public String getPassword() {
						return password;
					}
					public void setPassword(String password) {
						this.password = password;
					}
				}
			}
    	}

    }

}
