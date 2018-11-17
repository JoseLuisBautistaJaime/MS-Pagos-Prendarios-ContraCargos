package mx.com.nmp.pagos.mimonte.services;

import java.util.List;

import mx.com.nmp.pagos.mimonte.dto.TarjetaDTO;

public interface TarjetasService {
	
	List<TarjetaDTO> getTarjetas(String idTarjeta);

	TarjetaDTO postTarjetas(TarjetaDTO tarjeta);

	TarjetaDTO updateTarjetas(String idTarjeta, String alias);

	TarjetaDTO deleteTarjetas(String idTarjeta);

}
