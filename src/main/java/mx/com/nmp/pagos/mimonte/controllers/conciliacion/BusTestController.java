/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.controllers.conciliacion;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.soap.MessageFactory;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import midas.nmp.com.mx.ObtenerMovimientosConciliacionNocturna;
import midas.nmp.com.mx.ObtenerMovimientosConciliacionNocturnaResponse;
import mx.com.montedepiedad.servicios.nmp.schema.nmpconciliacionpagoonline.MovimientoConciliacionNocturna;
import mx.com.nmp.pagos.mimonte.constans.CatalogConstants;
import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaMidasProveedorRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoMidasRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoProcesosNocturnosListResponseDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoProveedorDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoTransaccionalListRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.TarjetaMovimientosProveedorDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.util.Response;
import mx.openpay.client.Charge;
import mx.openpay.client.core.OpenpayAPI;
import mx.openpay.client.core.operations.ChargeOperations;
import mx.openpay.client.utils.SearchParams;

/**
 * Emula las operaciones de alta de Open Pay y Midas desde el BUS
 * @version 0.1
 */
@RestController
@RequestMapping(value = "/mimonte")
@Api(value = "Servicio para emular BUS", description = "REST API para emular BUS", produces = MediaType.APPLICATION_JSON_VALUE, protocols = "http", tags = {
		"BUS" })
public class BusTestController {

	/**
	 * Fabrica de beans
	 */
	@Autowired
	private BeanFactory beanFactory;

	/**
	 * Movimientos Controller
	 */
	@Autowired
	private MovimientosController movimientosController;



	/**
	 * Da de alta el reporte open pay
	 * @param userRequest
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/openPay", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Alta del reporte open desde el bus", tags = {
			"OpenPay" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Alta exitosa") })
	public Response openPay(@RequestBody ConsultaMidasProveedorRequestDTO request, @RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String userRequest) {
		
		OpenpayAPI api = new OpenpayAPI("https://sandbox-api.openpay.mx", "sk_418e1d30425c4254a77b79e029bbb7e4", "mbcgnlbecmpj2ww2sm9y");
		ChargeOperations chargeOperations = api.charges();


		// Query Open Pay
		List<Charge> charges = null;
		try {
			SearchParams params = new SearchParams();
			params.creationLte(request.getFechaHasta());
			params.creationGte(request.getFechaDesde());
			charges = chargeOperations.list(params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ConciliacionException("Error al consultar Open Pay", CodigoError.NMP_PMIMONTE_BUSINESS_069);
		}

		if (charges == null || charges.size() <= 0) {
			throw new ConciliacionException("No existen movimientos Open Pay", CodigoError.NMP_PMIMONTE_BUSINESS_069);
		}


		// Mapeo //////////////////////
		List<MovimientoProveedorDTO> movimientos = new ArrayList<MovimientoProveedorDTO>();
		for (Charge charge : charges) {
			MovimientoProveedorDTO movimiento = new MovimientoProveedorDTO();
			movimiento.setAmount(charge.getAmount());
			movimiento.setAuthorization(charge.getAuthorization());
			if (charge.getCard() != null) {
				movimiento.setCard(new TarjetaMovimientosProveedorDTO());
				if (charge.getCard().getAddress() != null) {
					movimiento.getCard().setAddress(charge.getCard().getAddress().getLine1()); // TODO: Address is a object
				}
				movimiento.getCard().setAllowsCharges(charge.getCard().getAllowsCharges());
				movimiento.getCard().setAllowsPayouts(charge.getCard().getAllowsPayouts());
				movimiento.getCard().setBankCode(charge.getCard().getBankCode());
				movimiento.getCard().setBankName(charge.getCard().getBankName());
				movimiento.getCard().setBrand(charge.getCard().getBrand());
				movimiento.getCard().setCardNumber(charge.getCard().getCardNumber());
				movimiento.getCard().setCreationDate(charge.getCard().getCreationDate());
				movimiento.getCard().setCustomerId(charge.getCard().getDeviceSessionId()); // TODO: CVV2, PointsType, TokenId 
				movimiento.getCard().setExpirationMonth(charge.getCard().getExpirationMonth());
				movimiento.getCard().setExpirationYear(charge.getCard().getExpirationYear());
				movimiento.getCard().setHolderName(charge.getCard().getHolderName());
				movimiento.getCard().setId(charge.getCard().getId());
				movimiento.getCard().setType(charge.getCard().getType());
			}
			movimiento.setConciliated(movimiento.getConciliated());
			movimiento.setCreationDate(charge.getCreationDate());
			movimiento.setCurrency(charge.getCurrency());
			movimiento.setCustomerId(charge.getCustomerId());
			movimiento.setDescription(charge.getDescription());
			movimiento.setErrorCode(charge.getErrorMessage());
			movimiento.setIdMovimiento(charge.getId());
			movimiento.setMethod(charge.getMethod());
			movimiento.setOperationDate(charge.getOperationDate());
			movimiento.setOperationType(charge.getOperationType());
			movimiento.setOrderId(charge.getOrderId());
			// movimiento.setPaymentMethod(charge.getPaymentMethod()); TODO: Payment method object
			movimiento.setStatus(charge.getStatus());
			movimiento.setTransactionType(charge.getTransactionType());
			movimientos.add(movimiento);
		}

		MovimientoTransaccionalListRequestDTO movsTransaccion = new MovimientoTransaccionalListRequestDTO();
		movsTransaccion.setFechaDesde(request.getFechaDesde());
		movsTransaccion.setFechaHasta(request.getFechaHasta());
		movsTransaccion.setFolio(request.getFolio() != null ? request.getFolio().intValue() : 0);
		movsTransaccion.setMovimientos(movimientos);
		
		return movimientosController.saveMovimientosProvedor(movsTransaccion, userRequest);
	}


	/**
	 * Da de alta el reporte open pay
	 * @param userRequest
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/midas", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Alta del reporte procesos nocturnos desde el bus", tags = {
			"Midas" })
	@ApiResponses({ @ApiResponse(code = 200, response = Response.class, message = "Alta exitosa") })
	public Response midas(@RequestBody ConsultaMidasProveedorRequestDTO request, @RequestHeader(CatalogConstants.REQUEST_USER_HEADER) String userRequest) {
		
		
		List<MovimientoConciliacionNocturna> movsMidas = null;
		try {
			SaajSoapMessageFactory messageFactory = new SaajSoapMessageFactory(MessageFactory.newInstance());
		    messageFactory.afterPropertiesSet();
	
		    WebServiceTemplate webServiceTemplate = new WebServiceTemplate(messageFactory);

		    Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		    marshaller.setContextPath("midas.nmp.com.mx");
		    marshaller.afterPropertiesSet();
		    webServiceTemplate.setMarshaller(marshaller);

		    Jaxb2Marshaller unmarshaller = new Jaxb2Marshaller();
		    unmarshaller.setContextPath("midas.nmp.com.mx");
		    unmarshaller.afterPropertiesSet();
		    webServiceTemplate.setUnmarshaller(unmarshaller);
		    
		    webServiceTemplate.afterPropertiesSet();

		    ObtenerMovimientosConciliacionNocturna requestMidas = new ObtenerMovimientosConciliacionNocturna();
		    requestMidas.setFechaInicio(toXMLGregorianCalendar(request.getFechaDesde()));
		    requestMidas.setFechaFin(toXMLGregorianCalendar(request.getFechaHasta()));

		    ObtenerMovimientosConciliacionNocturnaResponse response = (ObtenerMovimientosConciliacionNocturnaResponse) webServiceTemplate.marshalSendAndReceive(
	    		"http://localhost:9010/midas-web/services/ConciliacionPagoOnlineService",
                requestMidas
            );
		    
		    if (response.getMovimientos() != null) {
		    	movsMidas = response.getMovimientos().getMovimientoConciliacionNocturna();
		    }
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException("Error al consultar Midas", CodigoError.NMP_PMIMONTE_BUSINESS_069);
		}
		
		
		List<MovimientoMidasRequestDTO> movimientos = new ArrayList<MovimientoMidasRequestDTO>();
		if (CollectionUtils.isNotEmpty(movsMidas)) {
			for (MovimientoConciliacionNocturna movMidas : movsMidas) {
				MovimientoMidasRequestDTO movMidasDTO = new MovimientoMidasRequestDTO();
				movMidasDTO.setCapitalActual(movMidas.getCapitalActual().getValue());
				movMidasDTO.setCodigoError(movMidas.getCodigoError().getValue());
				movMidasDTO.setComisiones(movMidas.getComisiones().getValue());
				movMidasDTO.setConsumidor(movMidas.getIdConsumidor().getValue() != null ? movMidas.getIdConsumidor().getValue().toString() : null);
				movMidasDTO.setEstadoTransaccion(movMidas.getEstadoTransaccion().getValue());
				//movMidasDTO.setEstatus(movMidas.getE); // TODO: Status
				movMidasDTO.setFecha(movMidas.getFecha().getValue().toGregorianCalendar().getTime());
				movMidasDTO.setFolioPartida(movMidas.getFolioPartida().getValue());
				movMidasDTO.setIdOperacion(movMidas.getIdOperacion().getValue());
				movMidasDTO.setIdTarjeta(movMidas.getIdTarjeta().getValue());
				movMidasDTO.setIdTipoContrato(movMidas.getIdTipoContrato().getValue());
				movMidasDTO.setImporteTransaccion(movMidas.getImporteTransaccion().getValue());
				movMidasDTO.setInteres(movMidas.getInteres().getValue());
				movMidasDTO.setMarcaTarjeta(movMidas.getMarcaTarjeta().getValue());
				movMidasDTO.setMensajeError(movMidas.getMensajeError().getValue());
				movMidasDTO.setMonedaPago(movMidas.getMonedaPago().getValue());
				movMidasDTO.setMontoOperacion(movMidas.getMontoOperacion().getValue());
				movMidasDTO.setNumAutorizacion(movMidas.getNumAutorizacion().getValue());
				movMidasDTO.setOperacionAbr(movMidas.getOperacionAbr().getValue());
				movMidasDTO.setOperacionDesc(movMidas.getOperacionDesc().getValue());
				movMidasDTO.setSucursal(movMidas.getSucursal().getValue());
				movMidasDTO.setTarjeta(movMidas.getTarjeta().getValue());
				movMidasDTO.setTipoContratoAbr(movMidas.getTipoContratoAbr().getValue());
				movMidasDTO.setTipoContratoDesc(movMidas.getTipoContratoDesc().getValue());
				movMidasDTO.setTipoTarjeta(movMidas.getTipoTarjeta().getValue());
				movMidasDTO.setTransaccion(movMidas.getTransaccion().getValue());
				movimientos.add(movMidasDTO);
			}
		}

		MovimientoProcesosNocturnosListResponseDTO movsDTO = new MovimientoProcesosNocturnosListResponseDTO();
		movsDTO.setFechaDesde(request.getFechaDesde());
		movsDTO.setFechaHasta(request.getFechaHasta());
		movsDTO.setFolio(request.getFolio() != null ? request.getFolio().intValue() : 0);
		movsDTO.setMovimientos(movimientos);
		
		
		return this.movimientosController.saveMovimientosNocturnos(movsDTO, userRequest);
	}


	private XMLGregorianCalendar toXMLGregorianCalendar(Date date) throws ConciliacionException {
		
		XMLGregorianCalendar xmlGregorian = null;
		try {
			GregorianCalendar c = new GregorianCalendar();
			c.setTime(date);
			xmlGregorian = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		}
		catch (Exception ex) {
			throw new ConciliacionException("Error al crear request para consulta movimientos nocturnos");
		}
		return xmlGregorian;
	}

}
