package mx.com.nmp.pagos.mimonte.consumer.rest.dto;


/**
 * @name BusRestGeneracionLayoutDTO
 * @description Clase que encapsula la información de la peticion del servicio que expone BUS
 *  *              para generar y enviar los Layouts
 *
 * @author Omar Rodriguez orodriguez@quarksoft.net
 * @version 1.0
 * @createdDate 03/02/2022 19:23 hrs.
 */
public class BusRestGeneracionLayoutDTO implements BusRestBodyDTO {


    private Long folio;
    private Integer estadoEnvio;


    public BusRestGeneracionLayoutDTO(Long folio, Integer estadoEnvio) {
        super();
        this.folio = folio;
        this.estadoEnvio = estadoEnvio;

    }

    public Long getFolio() {
        return folio;
    }

    public void setFolio(Long folio) {
        this.folio = folio;
    }

    public Integer getEstadoEnvio() {
        return estadoEnvio;
    }

    public void setEstadoEnvio(Integer estadoEnvio) {
        this.estadoEnvio = estadoEnvio;
    }

    @Override
    public String toString() {
        return "BusRestGeneracionLayoutDTO [folio=" + folio + ", estadoEnvio=" + estadoEnvio  +"]";
    }
}
