package mx.com.nmp.pagos.mimonte.builder;

import org.springframework.stereotype.Component;

import mx.com.nmp.pagos.mimonte.dto.PagoDTO;
import mx.com.nmp.pagos.mimonte.dto.PagoRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.TarjetaDTO;
import mx.com.nmp.pagos.mimonte.model.Pago;

/**
 * Nombre: PagoBuilder
 * Descripcion: Builder que se encaraga de fabricar objetos
 * entity a partir de objetos DTO y viceversa
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 21/11/2018 17:30 hrs.
 * @version 0.1
 */
@Component
public class PagoBuilder {

	/**
	 * Metodo que cosntruye un Entity de tipo Pago desde un objeto de tipo
	 * PagoRequestDTO
	 * 
	 * @param Objeto de tipo PagoRequestDTO pagoDTO
	 * @return Entity de tipo Pago
	 */
	public static Pago buildPagoEntityFromPagoDTO(PagoRequestDTO pagoDTO) {
		return null;
	}

	/**
	 * Metodo que construye un objeto tipo PagoDTO desde un objeto tipo
	 * PagoRequestDTO
	 * 
	 * @param Objeto tipo PagoRequestDTO pagoDTO
	 * @return Objeto de tipo PagoDTO
	 */
	public static PagoDTO buildPagoDTOFromPagoDTO(PagoRequestDTO pagoDTO) {
		return null;
	}

	/**
	 * Metodo que construye un objeto de tipo PagoDTO a un Entity Pago
	 * 
	 * @param Objeto de tipo pagoDTO
	 * @return Entity de tipo Pago
	 */
	public static Pago buildPagoEntity(PagoDTO pagoDTO) {
		Pago pagoEntity = new Pago();
		pagoEntity.setAutorizacion(pagoDTO.getAutorizacion());
		pagoEntity.setCliente(ClienteBuilder.buildClienteFromClienteDTO(pagoDTO.getCliente()));
		pagoEntity.setDescripcion(pagoDTO.getDescripcion());
		pagoEntity.setFechaCreacion(pagoDTO.getFechaCreacion());
		pagoEntity.setFechaTarnsaccion(pagoDTO.getFechaTarnsaccion());
		pagoEntity.setId(pagoDTO.getId());
		pagoEntity.setIdOpenPay(pagoDTO.getIdOpenPay());
		pagoEntity.setIdOrder(pagoDTO.getIdOrder());
		pagoEntity.setMetodo(pagoDTO.getMetodo());
		pagoEntity.setMonto(pagoDTO.getMonto());
		pagoEntity.setRestResponse(pagoDTO.getRestResponse());
		pagoEntity.setTarjeta(pagoDTO.getTarjeta().getDigitos());
		return pagoEntity;
	}

	/**
	 * Metodo que construye un objeto de tipo PagoDTO desde un Entity tipo Pago
	 * 
	 * @param Entity de tipo Pago pagoEntity
	 * @return Objeto de tipo PagoDTO
	 */
	public static PagoDTO buildPagoDTO(Pago pagoEntity) {
		PagoDTO pagoDTO = new PagoDTO();
		pagoDTO.setAutorizacion(pagoEntity.getAutorizacion());
		pagoDTO.setCliente(ClienteBuilder.buildClienteDTOFromCliente(pagoEntity.getCliente()));
		pagoDTO.setDescripcion(pagoEntity.getDescripcion());
		pagoDTO.setFechaCreacion(pagoEntity.getFechaCreacion());
		pagoDTO.setFechaTarnsaccion(pagoEntity.getFechaTarnsaccion());
		pagoDTO.setId(pagoEntity.getId());
		pagoDTO.setIdOpenPay(pagoEntity.getIdOpenPay());
		pagoDTO.setIdOrder(pagoEntity.getIdOrder());
		pagoDTO.setMetodo(pagoEntity.getMetodo());
		pagoDTO.setMonto(pagoEntity.getMonto());
		pagoDTO.setRestResponse(pagoEntity.getRestResponse());
		// No es un Foreign Key por eso solo requiere los ultimos digitos de la tarjeta
		TarjetaDTO tarjeta = new TarjetaDTO();
		tarjeta.setDigitos(pagoEntity.getTarjeta());
		pagoDTO.setTarjeta(tarjeta);
		return pagoDTO;
	}

}
