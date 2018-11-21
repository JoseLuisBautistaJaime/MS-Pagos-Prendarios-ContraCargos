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

	public List<TarjetaDTO> getTarjetas(String idTarjeta) {

		List<TipoTarjetaDTO> tipoTarjeta = new ArrayList<TipoTarjetaDTO>();
		List<EstatusTarjetaDTO> estatusTarjeta = new ArrayList<EstatusTarjetaDTO>();
		List<TarjetaDTO> tarjetaDTO = new ArrayList<TarjetaDTO>();
		

		TipoTarjetaDTO tipoTarjetaDto = new TipoTarjetaDTO("Visa", "Tarjeta tipo Visa", "T Visa");
		tipoTarjeta.add(tipoTarjetaDto);

		EstatusTarjetaDTO estatusTarjetaDto = new EstatusTarjetaDTO();
		estatusTarjetaDto.setId(1L);
		estatusTarjetaDto.setDescripcionCorta("Activa");
		estatusTarjetaDto.setDescripcion("Activa");
		estatusTarjeta.add(estatusTarjetaDto);

		TarjetaDTO tarjetaDto = new TarjetaDTO(tipoTarjetaDto, estatusTarjetaDto, "1", "T001002003004", "MyBSmart",	2345);
		tarjetaDTO.add(tarjetaDto);

		

		return tarjetaDTO;

	}

	@Override
	public List<TarjetaDTO> addTarjetas(TarjetaDTO tarjeta) {

		List<TipoTarjetaDTO> tipoTarjeta = new ArrayList<TipoTarjetaDTO>();
		List<EstatusTarjetaDTO> estatusTarjeta = new ArrayList<EstatusTarjetaDTO>();
		List<TarjetaDTO> tarjetaDTO = new ArrayList<TarjetaDTO>();
		

		TipoTarjetaDTO tipoTarjetaDto = new TipoTarjetaDTO("Visa", "Tarjeta tipo Visa", "T Visa");
		tipoTarjeta.add(tipoTarjetaDto);

		EstatusTarjetaDTO estatusTarjetaDto = new EstatusTarjetaDTO();
		estatusTarjetaDto.setId(1L);
		estatusTarjetaDto.setDescripcionCorta("Activa");
		estatusTarjetaDto.setDescripcion("Activa");
		estatusTarjeta.add(estatusTarjetaDto);

		TarjetaDTO tarjetaDto = new TarjetaDTO(tipoTarjetaDto, estatusTarjetaDto, "1", "T001002003004", "MyBSmart",	2345);
		tarjetaDTO.add(tarjetaDto);

		

		return tarjetaDTO;
	}

	@Override
	public List<TarjetaDTO> updateTarjetas(String idTarjeta) {

		List<TipoTarjetaDTO> tipoTarjeta = new ArrayList<TipoTarjetaDTO>();
		List<EstatusTarjetaDTO> estatusTarjeta = new ArrayList<EstatusTarjetaDTO>();
		List<TarjetaDTO> tarjetaDTO = new ArrayList<TarjetaDTO>();
		

		TipoTarjetaDTO tipoTarjetaDto = new TipoTarjetaDTO("Visa", "Tarjeta tipo Visa", "T Visa");
		tipoTarjeta.add(tipoTarjetaDto);

		EstatusTarjetaDTO estatusTarjetaDto = new EstatusTarjetaDTO();
		estatusTarjetaDto.setId(1L);
		estatusTarjetaDto.setDescripcionCorta("Activa");
		estatusTarjetaDto.setDescripcion("Activa");
		estatusTarjeta.add(estatusTarjetaDto);

		TarjetaDTO tarjetaDto = new TarjetaDTO(tipoTarjetaDto, estatusTarjetaDto, "1", "T001002003004", "MyBSmart",	2345);
		tarjetaDTO.add(tarjetaDto);

		

		return tarjetaDTO;
	}

	@Override
	public TarjetaDTO deleteTarjetas(String idTarjeta) {

		TarjetaDTO tarjetaDto = null;

		return tarjetaDto;
	}

}
