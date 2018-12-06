package mx.com.nmp.pagos.mimonte.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mx.com.nmp.pagos.mimonte.dto.ClienteDTO;
import mx.com.nmp.pagos.mimonte.dto.EstatusTarjetaDTO;
import mx.com.nmp.pagos.mimonte.dto.TarjetaDTO;
import mx.com.nmp.pagos.mimonte.dto.TarjetaPagoDTO;
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
public class TarjetaBuilder {

	private TarjetaBuilder() {
		/**
		 * hidden constructor
		 */
	}
	
	/**
	 * Metodo que construye un entity Tarjetas desde un objeto tipo TrajetaDTO
	 * 
	 * @param Objeto de tipo TarjetaDTO tarjetaDTO
	 * @return Entity de tipo Tarjetas
	 */
	public static Tarjetas builTarjetaFromTrajetaDTO(TarjetaDTO tarjetaDTO) {
		Tarjetas tarjetaEntity = new Tarjetas();
		tarjetaEntity.setAlias(tarjetaDTO.getAlias());
		List<EstatusTarjeta> list = new ArrayList<>();
	//	tarjetaEntity.setEstatusTarjeta(list);
		tarjetaEntity.setFechaAlta(tarjetaDTO.getFechaAlta());
		tarjetaEntity.setFechaModificacion(tarjetaDTO.getFechaModificacion());
		List<TipoTarjeta> lst = new ArrayList<>();
	//	tarjetaEntity.setTipoTarjeta(lst);
		tarjetaEntity.setToken(tarjetaDTO.getToken());
		tarjetaEntity.setUltimosDigitos(tarjetaDTO.getDigitos());
		return tarjetaEntity;	
	}
	
	public static Tarjetas buildTarjetaFromTarjetaPagoDTO(TarjetaPagoDTO tarjetaPagoDTO, ClienteDTO clienteDTO) {
		Tarjetas tarjeta = new Tarjetas();
		tarjeta.setAlias(tarjetaPagoDTO.getAlias());



//		tarjeta.setClientes(ClienteBuilder.buildClienteFromClienteDTO((clienteDTO)));
		tarjeta.setEstatusTarjeta( EstatusTarjetaBuilder.buildEstatusTarjetaFromEstatusTarjetaDTO(tarjetaPagoDTO.getEstatus()));

		tarjeta.setFechaAlta(tarjetaPagoDTO.getFechaAlta());
		tarjeta.setFechaModificacion(tarjetaPagoDTO.getFechaModificacion());
	//	tarjeta.setTipoTarjeta(Arrays.asList(TipoTarjetaBuilder.buildTipoTarjetaFromTipoTrajetaDTO(tarjetaPagoDTO.getTipo())));
		tarjeta.setToken(tarjetaPagoDTO.getToken());
		tarjeta.setUltimosDigitos(tarjetaPagoDTO.getDigitos());
		return tarjeta;
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
		tarjetaDTO.setEstatus(new EstatusTarjetaDTO(1,"Activo", "Activo"));
		tarjetaDTO.setFechaAlta(tarjetaEntity.getFechaAlta());
		tarjetaDTO.setFechaModificacion(tarjetaEntity.getFechaModificacion());
		tarjetaDTO.setTipo(new TipoTarjetaDTO(1,"Visa", "T Visa"));
		tarjetaDTO.setToken(tarjetaEntity.getToken());
		tarjetaDTO.setDigitos((tarjetaEntity.getUltimosDigitos()));
		return tarjetaDTO;	
	}
	
	/**
	 * Metodo que construye un objeto de tipo TarjetaDTO desde un objeto TarjetaPagoDTO y un objeto de tipo ClienteDTO
	 * 
	 * @param Objeto de tipo TarjetaPagoDTO tarjetaPagoDTO
	 * @param Objeto de tipo ClienteDTO cliente
	 * @return Objeto de tipo TarjetaDTO
	 */
	public static TarjetaDTO buildTarjetaDTOFromTarjetaPagoDTO(TarjetaPagoDTO tarjetaPagoDTO, ClienteDTO cliente) {
		TarjetaDTO tarjetaDTO = new TarjetaDTO();
		tarjetaDTO.setAlias(tarjetaPagoDTO.getAlias());
		tarjetaDTO.setCliente(cliente);
		tarjetaDTO.setDigitos(tarjetaPagoDTO.getDigitos());
		tarjetaDTO.setEstatus(tarjetaPagoDTO.getEstatus());
		tarjetaDTO.setFechaAlta(tarjetaPagoDTO.getFechaAlta());
		tarjetaDTO.setFechaModificacion(tarjetaPagoDTO.getFechaModificacion());
		tarjetaDTO.setTipo(tarjetaPagoDTO.getTipo());
		tarjetaDTO.setToken(tarjetaPagoDTO.getToken());
		return tarjetaDTO;
	}
	
}
