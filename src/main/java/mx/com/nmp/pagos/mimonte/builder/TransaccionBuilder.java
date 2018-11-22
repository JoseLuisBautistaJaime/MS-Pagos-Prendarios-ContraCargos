package mx.com.nmp.pagos.mimonte.builder;

import org.springframework.stereotype.Component;

import mx.com.nmp.pagos.mimonte.dto.PagoDTO;
import mx.com.nmp.pagos.mimonte.dto.TransaccionDTO;
import mx.com.nmp.pagos.mimonte.model.Catalogo;
import mx.com.nmp.pagos.mimonte.model.Transaccion;

/**
 * Nombre: TransaccionBuilder
 * Descripcion: Builder que se encaraga de fabricar objetos entity a partir de objetos DTO
 * y viceversa
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @CreationDate 21/11/2018 17:30 hrs.
 * @version 0.1
 */
@Component
public class TransaccionBuilder {

	/**
	 * Metodo que cosntruye un Entity de tipo Transaccion desde un objeto de tipo PagoDTO
	 * 
	 * @param Objeto de tipo PagoDTO pagoDTO
	 * @return Entity de tipo Transaccion
	 */
	public static Transaccion buildTransaccionEntityFromPagoDTO(PagoDTO pagoDTO) {
		return null;
	}
	
	public static TransaccionDTO buildTransaccionDTOFromPagoDTO(PagoDTO pagoDTO) {
		return null;
	}
	
	/**
	 * Metodo que construye un objeto de tipo TransaccionDTO a un Entity Transaccion
	 * 
	 * @param Objeto de tipo transaccionDTO
	 * @return Entity de tipo Transaccion
	 */
	public static Transaccion buildTransaccionEntity(TransaccionDTO transaccionDTO) {
		Transaccion transaccionEntity = new Transaccion();
		transaccionEntity.setAutorizacion(transaccionDTO.getAutorizacion());
//		transaccionEntity.setCliente(transaccionDTO.getCliente());
		transaccionEntity.setDescripcion(transaccionDTO.getDescripcion());
//		transaccionEntity.setEstatusTransaccion(transaccionDTO.getEstatusTransaccion());
		transaccionEntity.setFechaCreacion(transaccionDTO.getFechaCreacion());
		transaccionEntity.setFechaTarnsaccion(transaccionDTO.getFechaTarnsaccion());
		transaccionEntity.setId(transaccionDTO.getId());
		transaccionEntity.setIdOpenPay(transaccionDTO.getIdOpenPay());
		transaccionEntity.setIdOrder(transaccionDTO.getIdOrder());
		transaccionEntity.setMetodo(transaccionDTO.getMetodo());
		transaccionEntity.setMonto(transaccionDTO.getMonto());
		transaccionEntity.setRestResponse(transaccionDTO.getRestResponse());
//		transaccionEntity.setTarjeta(transaccionDTO.getTarjeta());
		return transaccionEntity;
	}

	/**
	 * Metodo que construye un objeto de tipo TransaccionDTO desde un Entity tipo Transaccion
	 * 
	 * @param Entity de tipo Transaccion transaccionEntity
	 * @return Objeto de tipo TransaccionDTO
	 */
	public static TransaccionDTO buildTransaccionDTO(Transaccion transaccionEntity) {
		TransaccionDTO transaccionDTO = new TransaccionDTO();
		transaccionDTO.setAutorizacion(transaccionEntity.getAutorizacion());
//		transaccionDTO.setCliente(transaccionEntity.getCliente());
		transaccionDTO.setDescripcion(transaccionEntity.getDescripcion());
//		transaccionDTO.setEstatusTransaccion(transaccionEntity.getEstatusTransaccion());
		transaccionDTO.setFechaCreacion(transaccionEntity.getFechaCreacion());
		transaccionDTO.setFechaTarnsaccion(transaccionEntity.getFechaTarnsaccion());
		transaccionDTO.setId(transaccionEntity.getId());
		transaccionDTO.setIdOpenPay(transaccionEntity.getIdOpenPay());
		transaccionDTO.setIdOrder(transaccionEntity.getIdOrder());
		transaccionDTO.setMetodo(transaccionEntity.getMetodo());
		transaccionDTO.setMonto(transaccionEntity.getMonto());
		transaccionDTO.setRestResponse(transaccionEntity.getRestResponse());
//		transaccionDTO.setTarjeta(transaccionEntity.getTarjeta());
		return transaccionDTO;
	}
	
}
