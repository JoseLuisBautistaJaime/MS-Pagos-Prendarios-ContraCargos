package mx.com.nmp.pagos.mimonte.builder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import mx.com.nmp.pagos.mimonte.dto.EstatusTarjetaDTO;
import mx.com.nmp.pagos.mimonte.dto.TarjetaDTO;
import mx.com.nmp.pagos.mimonte.dto.TipoTarjetaDTO;
import mx.com.nmp.pagos.mimonte.model.EstatusTarjeta;
import mx.com.nmp.pagos.mimonte.model.Tarjetas;
import mx.com.nmp.pagos.mimonte.model.TipoTarjeta;

/**
 * Nombre: TarjetaBuilder
 * Descripcion: Builder que se encaraga de fabricar objetos entity a partir de objetos DTO
 * y viceversa
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @CreationDate 21/11/2018 18:10 hrs.
 * @version 0.1
 */
@Component
public class TarjetaBuilder {

	/**
	 * Metodo que construye un entity Tarjetas desde un objeto tipo TrajetaDTO
	 * 
	 * @param Objeto de tipo TarjetaDTO tarjetaDTO
	 * @return Entity de tipo Tarjetas
	 */
	public static Tarjetas builTarjetaFromTrajetaDTO(TarjetaDTO tarjetaDTO) {
		Tarjetas tarjetaEntity = new Tarjetas();
		tarjetaEntity.setAlias(tarjetaDTO.getAlias());
		//tarjetaEntity.setClientes(ClienteBuilder.buildListClienteFromSetClienteDTO((tarjetaDTO.getClientes())));
		List<EstatusTarjeta> list = new ArrayList<>();
//		list.add(EstatusTarjetaBuilder.buildEstatusTarjetaFromEstatusTarjetaDTO(tarjetaDTO.getEstatus()));
		tarjetaEntity.setEstatusTarjeta(list);
		tarjetaEntity.setFechaAlta(tarjetaDTO.getFechaAlta());
		tarjetaEntity.setFechaModificacion(tarjetaDTO.getFechaModificacion());
		List<TipoTarjeta> lst = new ArrayList<>();
		//lst.add(TipoTarjetaBuilder.buildTipoTarjetaFromTipoTrajetaDTO((tarjetaDTO.getTipo())));
		tarjetaEntity.setTipoTarjeta(lst);
		tarjetaEntity.setToken(tarjetaDTO.getToken());
		tarjetaEntity.setUltimosDigitos(tarjetaDTO.getDigitos());
		return tarjetaEntity;	
	}
	
	/**
	 * Metodo que construye un objeto de tipo TrajetaDTO desde un entity de tipo Trajetas
	 * 
	 * @param Entity de tipo Tarjetas tarjetaEntity
	 * @return Objeto de tipo TrajetaDTO
	 */
	public static TarjetaDTO builTarjetaDTOFromTrajeta(Tarjetas tarjetaEntity) {
		TarjetaDTO tarjetaDTO = new TarjetaDTO();
		tarjetaDTO.setAlias(tarjetaEntity.getAlias());
		//tarjetaDTO.setCliente( ClienteBuilder.buildListClienteDTOFromSetCliente((tarjetaEntity.getClientes())).get(0));
		tarjetaDTO.setEstatus(new EstatusTarjetaDTO(1,"Activo", "Activo"));
		tarjetaDTO.setFechaAlta(tarjetaEntity.getFechaAlta());
		tarjetaDTO.setFechaModificacion(tarjetaEntity.getFechaModificacion());
		tarjetaDTO.setTipo(new TipoTarjetaDTO(1,"Visa", "T Visa"));
		tarjetaDTO.setToken(tarjetaEntity.getToken());
		tarjetaDTO.setDigitos((tarjetaEntity.getUltimosDigitos()));
		return tarjetaDTO;	
	}
	
}
