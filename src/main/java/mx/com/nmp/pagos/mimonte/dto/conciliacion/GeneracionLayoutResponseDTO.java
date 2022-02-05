package mx.com.nmp.pagos.mimonte.dto.conciliacion;
/**
 * @name GeneracionLayoutResponseDTO
 * @description Clase de tipo DTO
 *
 * @author Omar Rodriguez Benitez orodriguez@quarksoft.net
 * @version 1.0
 * @createdDate 03/02/2022 15:51 hrs.
 */

public class GeneracionLayoutResponseDTO {
    private String codigo;
    private String descripcion;
    private String message;
    private Boolean respuestaCorrecta;

    public GeneracionLayoutResponseDTO(String codigo, String descripcion, String message, Boolean respuestaCorrecta) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.message = message;
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public GeneracionLayoutResponseDTO(){

    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getRespuestaCorrecta() {return respuestaCorrecta; }

    public void setRespuestaCorrecta(Boolean respuestaCorrecta) { this.respuestaCorrecta = respuestaCorrecta; }

    @Override
    public String toString() {
        return "GeneracionLayoutResponseDTO [codigo=" + codigo + ", descripcion=" + descripcion + ", message=" + message + ", respuestaCorrecta=" + respuestaCorrecta + "]";
    }
}
