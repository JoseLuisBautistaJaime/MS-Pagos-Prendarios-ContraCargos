package mx.com.nmp.pagos.mimonte.services;

import java.util.List;

import mx.com.nmp.pagos.mimonte.dto.TarjetaDTO;
import mx.com.nmp.pagos.mimonte.dto.TarjetasDTO;

public interface TarjetasService {
	
	List<TarjetasDTO> getTarjetas(String idTarjeta);

	List<TarjetasDTO> addTarjetas(TarjetasDTO tarjeta);

	TarjetaDTO updateTarjetas(String idTarjeta, String alias);

	TarjetaDTO deleteTarjetas(String idTarjeta);

}
