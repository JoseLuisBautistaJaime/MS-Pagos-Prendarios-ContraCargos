package mx.com.nmp.pagos.mimonte.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.nmp.pagos.mimonte.dao.TarjetaRepository;
import mx.com.nmp.pagos.mimonte.dto.EstatusTarjetaDTO;
import mx.com.nmp.pagos.mimonte.dto.TarjetaDTO;
import mx.com.nmp.pagos.mimonte.dto.TarjetasDTO;
import mx.com.nmp.pagos.mimonte.dto.TipoTarjetaDTO;
import mx.com.nmp.pagos.mimonte.services.TarjetasService;

@Service
public class TarjetasServiceImpl implements TarjetasService {

	/**
	 * Logger para el registro de actividad en la bitacora
	 */
	private final Logger log = LoggerFactory.getLogger(TarjetasServiceImpl.class);

	@Autowired
	private TarjetaRepository tarjetaRepository;

	public List<TarjetasDTO> getTarjetas(String idTarjeta) {

		List<TipoTarjetaDTO> tipoTarjeta = new ArrayList<TipoTarjetaDTO>();
		List<EstatusTarjetaDTO> estatusTarjeta = new ArrayList<EstatusTarjetaDTO>();
		List<TarjetaDTO> tarjetaDTO = new ArrayList<TarjetaDTO>();
		List<TarjetasDTO> tarjetasDTO = new ArrayList<TarjetasDTO>();

		TipoTarjetaDTO tipoTarjetaDto = new TipoTarjetaDTO("Visa", "Tarjeta tipo Visa", "T Visa");
		tipoTarjeta.add(tipoTarjetaDto);

		EstatusTarjetaDTO estatusTarjetaDto = new EstatusTarjetaDTO("Activa", "Activa");
		estatusTarjeta.add(estatusTarjetaDto);

		TarjetaDTO tarjetaDto = new TarjetaDTO(tipoTarjetaDto, estatusTarjetaDto, "1", "T001002003004", "MyBSmart",	2345);
		tarjetaDTO.add(tarjetaDto);

		TarjetasDTO tarjetasDto = new TarjetasDTO(tarjetaDto);
		tarjetasDTO.add(tarjetasDto);
		tarjetasDTO.add(tarjetasDto);

		return tarjetasDTO;

	}

	@Override
	public List<TarjetasDTO> addTarjetas(TarjetasDTO tarjeta) {

		List<TipoTarjetaDTO> tipoTarjeta = new ArrayList<TipoTarjetaDTO>();
		List<EstatusTarjetaDTO> estatusTarjeta = new ArrayList<EstatusTarjetaDTO>();
		List<TarjetaDTO> tarjetaDTO = new ArrayList<TarjetaDTO>();
		List<TarjetasDTO> tarjetasDTO = new ArrayList<TarjetasDTO>();

		TipoTarjetaDTO tipoTarjetaDto = new TipoTarjetaDTO("Visa", "Tarjeta tipo Visa", "T Visa");
		tipoTarjeta.add(tipoTarjetaDto);

		EstatusTarjetaDTO estatusTarjetaDto = new EstatusTarjetaDTO("Activa", "Activa");
		estatusTarjeta.add(estatusTarjetaDto);

		TarjetaDTO tarjetaDto = new TarjetaDTO(tipoTarjetaDto, estatusTarjetaDto, "1", "T001002003004", "MyBSmart",	2345);
		tarjetaDTO.add(tarjetaDto);

		TarjetasDTO tarjetasDto = new TarjetasDTO(tarjetaDto);
		tarjetasDTO.add(tarjetasDto);

		return tarjetasDTO;
	}

	@Override
	public TarjetaDTO updateTarjetas(String idTarjeta, String alias) {

		TarjetaDTO tarjetaDto = new TarjetaDTO();

		tarjetaDto.setAlias(alias);
		tarjetaDto.setIdTarjeta(idTarjeta);

		return tarjetaDto;
	}

	@Override
	public TarjetaDTO deleteTarjetas(String idTarjeta) {

		TarjetaDTO tarjetaDto = null;

		return tarjetaDto;
	}

}
