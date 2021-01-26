/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.controllers.conciliacion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import mx.com.nmp.pagos.mimonte.constans.CatalogConstants;
import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaMidasProveedorRequestDTO;
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
		movsTransaccion.setFolio(request.getFolio() != null ? request.getFolio() : 0);
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
		return null;
	}

}
