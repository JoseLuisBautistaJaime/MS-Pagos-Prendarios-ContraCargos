package mx.com.nmp.pagos.mimonte.builder;

import java.util.Date;

import org.springframework.stereotype.Component;

import mx.com.nmp.pagos.mimonte.constans.EstatusPago;
import mx.com.nmp.pagos.mimonte.dto.ClienteDTO;
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

	public static Pago buildPagoFromObject(PagoRequestDTO pagoRequestDTO, ClienteDTO clienteDTO, Integer index) {
		Pago pago = new Pago();
		pago.setCliente(ClienteBuilder.buildClienteFromClienteDTO(clienteDTO));
		pago.setDescripcion(pagoRequestDTO.getConcepto());
		pago.setEstatusPago(
				new mx.com.nmp.pagos.mimonte.model.EstatusPago(EstatusPago.REGISTERED_PAYMENT_STATUS.getId(),
						EstatusPago.REGISTERED_PAYMENT_STATUS.getDescripcionCorta(),
						EstatusPago.REGISTERED_PAYMENT_STATUS.getDescripcion()));
		pago.setFechaCreacion(new Date());
		pago.setFechaTarnsaccion(new Date());
		pago.setAutorizacion(null);
		pago.setIdOpenPay(null);
		pago.setIdOrder(null);
		pago.setMetodo(null);
		pago.setRestResponse(null);
		pago.setMonto(pagoRequestDTO.getOperaciones().get(index).getMonto());
		pago.setTarjeta(pagoRequestDTO.getTarjeta().getDigitos());
		return pago;
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
