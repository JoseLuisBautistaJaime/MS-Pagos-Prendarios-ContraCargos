package mx.com.nmp.pagos.mimonte.dto;

/**
 * 
 * @author Quarksoft
 *
 */
public class TarjetasDTO {

	private TarjetaDTO tarjeta;

	public TarjetasDTO() {
		super();
	}

	public TarjetasDTO(TarjetaDTO tarjeta) {
		this.tarjeta = tarjeta;
	}

	public TarjetaDTO getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(TarjetaDTO tarjeta) {
		this.tarjeta = tarjeta;
	}

	@Override
	public String toString() {
		return "TarjetasDTO [tarjeta=" + tarjeta + "]";
	}

}
