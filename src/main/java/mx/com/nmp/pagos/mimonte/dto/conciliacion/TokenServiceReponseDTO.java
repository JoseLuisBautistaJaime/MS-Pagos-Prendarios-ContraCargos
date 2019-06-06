/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

/**
 * @name TokenServiceReponseDTO
 * @description Clase de tipo DTO que mapea el response de el servicio para
 *              obtener un token para envio de e-mail
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 05/06/2019 13:57 hrs.
 * @version 0.1
 */
public class TokenServiceReponseDTO {

	private String expires_in;
	private String token_type;
	private String refresh_token;
	private String access_token;

	public TokenServiceReponseDTO() {
		super();
	}

	public TokenServiceReponseDTO(String expires_in, String token_type, String refresh_token, String access_token) {
		super();
		this.expires_in = expires_in;
		this.token_type = token_type;
		this.refresh_token = refresh_token;
		this.access_token = access_token;
	}

	public String getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}

	public String getToken_type() {
		return token_type;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	@Override
	public String toString() {
		return "TokenServiceReponseDTO [expires_in=" + expires_in + ", token_type=" + token_type + ", refresh_token="
				+ refresh_token + ", access_token=" + access_token + "]";
	}

}
