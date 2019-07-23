/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder;

import mx.com.nmp.pagos.mimonte.dto.ClienteDTO;
import mx.com.nmp.pagos.mimonte.dto.TarjetaDTO;
import mx.com.nmp.pagos.mimonte.dto.TarjetaPagoDTO;
import mx.com.nmp.pagos.mimonte.model.Tarjetas;

/**
 * @name TarjetaBuilder
 * @description Builder que se encaraga de fabricar objetos entity a partir de
 *              objetos DTO y viceversa
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
	 * @param tarjetaDTO
	 * @return
	 */
	public static Tarjetas builTarjetaFromTrajetaDTO(TarjetaDTO tarjetaDTO) {
		Tarjetas tarjetaEntity = new Tarjetas();
		tarjetaEntity.setAlias(tarjetaDTO.getAlias());
		tarjetaEntity.setFechaAlta(tarjetaDTO.getFechaAlta());
		tarjetaEntity.setFechaModificacion(tarjetaDTO.getFechaModificacion());
		tarjetaEntity.setToken(tarjetaDTO.getToken());
		tarjetaEntity.setUltimosDigitos(tarjetaDTO.getDigitos());
		return tarjetaEntity;
	}

	/**
	 * Cosntruye un entity de tipo Tarjetas a partir del DTO TarjetaPagoDTO
	 * 
	 * @param tarjetaPagoDTO
	 * @param clienteDTO
	 * @return
	 */
	public static Tarjetas buildTarjetaFromTarjetaPagoDTO(TarjetaPagoDTO tarjetaPagoDTO) {
		Tarjetas tarjeta = new Tarjetas();
		tarjeta.setAlias(tarjetaPagoDTO.getAlias());
		tarjeta.setEstatusTarjeta(
				EstatusTarjetaBuilder.buildEstatusTarjetaFromEstatusTarjetaDTO(tarjetaPagoDTO.getEstatus()));
		tarjeta.setFechaAlta(tarjetaPagoDTO.getFechaAlta());
		tarjeta.setFechaModificacion(tarjetaPagoDTO.getFechaModificacion());
		tarjeta.setToken(tarjetaPagoDTO.getToken());
		tarjeta.setUltimosDigitos(tarjetaPagoDTO.getDigitos());
		return tarjeta;
	}

	/**
	 * Metodo que construye un objeto de tipo TarjetaDTO desde un objeto
	 * TarjetaPagoDTO y un objeto de tipo ClienteDTO
	 * 
	 * @param tarjetaPagoDTO
	 * @param cliente
	 * @return
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
		tarjetaDTO.setId_openpay(tarjetaPagoDTO.getId_openpay());
		return tarjetaDTO;
	}

}
