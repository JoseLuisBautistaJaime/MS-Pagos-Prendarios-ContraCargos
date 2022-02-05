package mx.com.nmp.pagos.mimonte.consumer.rest.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BusRestGeneracionLayoutDTO {

    private static final SimpleDateFormat sf;

    private Long folio;
    private String estadoEnvio;

    static {
        sf = new SimpleDateFormat("yyyy-MM-dd");
    }

    public BusRestGeneracionLayoutDTO(Long folio, String estadoEnvio) {
        super();
        this.folio = folio;
        this.estadoEnvio = estadoEnvio;

    }

    public static SimpleDateFormat getSf() {
        return sf;
    }

    public Long getFolio() {
        return folio;
    }

    public void setFolio(Long folio) {
        this.folio = folio;
    }

    public String getEstadoEnvio() {
        return estadoEnvio;
    }

    public void setEstadoEnvio(String estadoEnvio) {
        this.estadoEnvio = estadoEnvio;
    }

    @Override
    public String toString() {
        return "BusRestGeneracionLayoutDTO [folio=" + folio + ", estadoEnvio=" + estadoEnvio  +"]";
    }
}
