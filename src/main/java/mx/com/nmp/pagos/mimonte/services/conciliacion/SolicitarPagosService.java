/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.conciliacion;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.velocity.VelocityEngineUtils;

import mx.com.nmp.pagos.mimonte.aspects.ActividadGenericMethod;
import mx.com.nmp.pagos.mimonte.builder.conciliacion.MovimientosBuilder;
import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.constans.EstatusMovimientosTransitoEnum;
import mx.com.nmp.pagos.mimonte.constans.MailServiceConstants;
import mx.com.nmp.pagos.mimonte.consumer.rest.BusMailRestService;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestAdjuntoDTO;
import mx.com.nmp.pagos.mimonte.consumer.rest.dto.BusRestMailDTO;
import mx.com.nmp.pagos.mimonte.dao.ContactoRespository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientoConciliacionRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientoTransitoRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientosMidasRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientosPagoRepository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoPagoDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.SolicitarPagosMailDataDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.SolicitarPagosRequestDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.exception.InformationNotFoundException;
import mx.com.nmp.pagos.mimonte.model.Contactos;
import mx.com.nmp.pagos.mimonte.model.EstatusTransito;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoConciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoPago;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoTransito;
import mx.com.nmp.pagos.mimonte.model.conciliacion.SubTipoActividadEnum;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoActividadEnum;
import mx.com.nmp.pagos.mimonte.util.ConciliacionDataValidator;

/**
 * @name SolicitarPagosService
 * @description Clase de capa de servicios que se encarga de realizar
 *              operaciones de logica de negocio relacionadas con las
 *              solicitudes de pagos
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 04/06/2019 17:18 hrs.
 * @version 0.1
 */
@Service("solicitarPagosService")
public class SolicitarPagosService {

	/**
	 * Instancia que registra los eventos en la bitacora
	 */
	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(SolicitarPagosService.class);

	/**
	 * Repository de movimientos nocturnos (midas)
	 */
	@Autowired
	@Qualifier("movimientosMidasRepository")
	private MovimientosMidasRepository movimientosMidasRepository;

	/**
	 * Repository de MovimientoConciliacionRepository
	 */
	@Autowired
	@Qualifier("movimientoConciliacionRepository")
	private MovimientoConciliacionRepository movimientoConciliacionRepository;

	/**
	 * Repository de MovimientosPagoRepository
	 */
	@Autowired
	@Qualifier("movimientosPagoRepository")
	private MovimientosPagoRepository movimientosPagoRepository;

	/**
	 * Repository de MovimientoTransitoRepository
	 */
	@Autowired
	@Qualifier("movimientoTransitoRepository")
	private MovimientoTransitoRepository movimientoTransitoRepository;

	/**
	 * Repository de contactos
	 */
	@Autowired
	private ContactoRespository contactoRespository;

	/**
	 * Registro de actividades
	 */
	@Autowired
	private ActividadGenericMethod actividadGenericMethod;

	/**
	 * Validador de datos relacionados con conciliacion
	 */
	@Autowired
	private ConciliacionDataValidator conciliacionDataValidator;

	/**
	 * Objeto para consumo de servicio Rest para envio de e-mail
	 */
	@Autowired
	@Qualifier("busMailRestService")
	private BusMailRestService busMailRestService;

	/**
	 * Clase de constantes que mapean propiedades relacionadas con el envio de
	 * e-mail por medio del servicio expuesto por BUS
	 */
	@Autowired
	private MailServiceConstants mc;

	/**
	 * Velocity HTML layouts Engine
	 */
	@Autowired
	private VelocityEngine velocityEngine;

	/**
	 * Regresa un objeto de tipo X (por definir) con la infromacion que se debe
	 * enviar por mail para la solicitud de pagos
	 * 
	 * @param folio
	 * @param idsComisiones
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void solicitarPagos(SolicitarPagosRequestDTO requestDTO, final String createdBy) {
		// Declaracion de objetos y variables necesarios
		List<SolicitarPagosMailDataDTO> solicitarPagosMailDataDTOList = null;
		List<MovimientoTransito> movimientoTransitoList = null;
		BusRestMailDTO generalBusMailDTO = null;

		// Valida que la conciliacion exista
		conciliacionDataValidator.validateFolioExists(requestDTO.getFolio());

		// Valida que los ids existan y tengan relacion con el folio de conciliacion
		// ingresado
		conciliacionDataValidator.validateIdsMovimientosConciliacionExists(requestDTO.getFolio(),
				requestDTO.getIdMovimientos());

		// Valida que los ids tengan un estatus 1
		Boolean val = (movimientoConciliacionRepository.validaFolioAndIdsForMovPagos(requestDTO.getFolio(),
				requestDTO.getIdMovimientos(), ConciliacionConstants.ESTATUS_TRANSITO_NO_IDENTIFICADO_OPEN_PAY,
				requestDTO.getIdMovimientos().size())) == BigInteger.ONE;
		if (!val)
			throw new ConciliacionException(ConciliacionConstants.WRONG_MOVIMIENTOS_ESTATUS,
					CodigoError.NMP_PMIMONTE_BUSINESS_093);

		// Consultar datos, actualizacion y generacion de registros en pagos
		try {
			// Consulta
			solicitarPagosMailDataDTOList = consultarMovimientos(solicitarPagosMailDataDTOList, requestDTO.getFolio(),
					requestDTO.getIdMovimientos());
		} catch (Exception ex) {
			throw new ConciliacionException(ConciliacionConstants.ERROR_ON_GET_MOVIMIENTOS_TRANSITO,
					CodigoError.NMP_PMIMONTE_BUSINESS_038);
		}
		try {
			// Actualiza
			actualizarMovimientosTransito(movimientoTransitoList, requestDTO.getFolio(), requestDTO.getIdMovimientos());
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException(ConciliacionConstants.ERROR_ON_UPDATE_MOVIMIENTOS_TRANSITO,
					CodigoError.NMP_PMIMONTE_BUSINESS_039);
		}
		// Construye e-mail
		try {
			generalBusMailDTO = construyeEMailVelocity(solicitarPagosMailDataDTOList);

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException(ConciliacionConstants.ERROR_ON_BUILD_EMAIL,
					CodigoError.NMP_PMIMONTE_BUSINESS_041);
		}
		try {
			// Envia e-mail
			this.busMailRestService.enviaEmail(generalBusMailDTO);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException(ConciliacionConstants.ERROR_ON_SENDING_EMAIL,
					CodigoError.NMP_PMIMONTE_BUSINESS_042);
		}

		// Registro de actividad
		actividadGenericMethod.registroActividad(requestDTO.getFolio(),
				"Se realizo la solicitud de pago de ".concat(String.valueOf(requestDTO.getIdMovimientos().size()))
						.concat(" movimiento(s) de la conciliacion con el folio: "
								.concat(String.valueOf(requestDTO.getFolio())).concat(", por un total de: $ ")
								.concat(String.valueOf(
										getMontoFromSolicitarPagosMailDataDTOList(solicitarPagosMailDataDTOList)))),
				TipoActividadEnum.ACTIVIDAD, SubTipoActividadEnum.SOLICITAR_PAGO);
	}

	/**
	 * Consulta los movimientos en transito solicitados
	 * 
	 * @param solicitarPagosMailDataDTOList
	 * @param folio
	 * @param idsMovimientos
	 * @return
	 */
	public List<SolicitarPagosMailDataDTO> consultarMovimientos(
			List<SolicitarPagosMailDataDTO> solicitarPagosMailDataDTOList, Long folio, List<Integer> idsMovimientos) {
		return movimientosMidasRepository.getDataByFolioAndIdMovimientos(folio, idsMovimientos);
	}

	/**
	 * Actualiza el estatus de los movimientos en transito solicitados
	 * 
	 * @param movimientoTransitoList
	 * @param folio
	 * @param idsMovimientos
	 */
	public void actualizarMovimientosTransito(List<MovimientoTransito> movimientoTransitoList, Long folio,
			List<Integer> idsMovimientos) {
		movimientoTransitoList = movimientoTransitoRepository.findByFolioAndIds(folio, idsMovimientos);
		if (null == movimientoTransitoList || movimientoTransitoList.isEmpty()) {
			throw new InformationNotFoundException(ConciliacionConstants.INFORMATION_NOT_FOUND,
					CodigoError.NMP_PMIMONTE_0009);
		}

		for (MovimientoTransito mt : movimientoTransitoList) {
			mt.setEstatus(new EstatusTransito(ConciliacionConstants.ESTATUS_TRANSITO_SOLICITADA));
		}
		movimientoTransitoRepository.saveAll(movimientoTransitoList);
	}

	/**
	 * Inserta los correspondientes movimientos pago equivalentes para los
	 * movimientos en transito especificados
	 * 
	 * @param movimientoConciliacionList
	 * @param folio
	 * @param idsMovimientos
	 * @param solicitarPagosMailDataDTOList
	 * @param createdBy
	 */
	public void insertaMovimientosPago(List<MovimientoConciliacion> movimientoConciliacionList, Long folio,
			List<Integer> idsMovimientos, List<SolicitarPagosMailDataDTO> solicitarPagosMailDataDTOList,
			String createdBy) {
		movimientoConciliacionList = movimientoConciliacionRepository.findByFolioAndIds(folio, idsMovimientos);
		if (null == movimientoConciliacionList || movimientoConciliacionList.isEmpty())
			throw new InformationNotFoundException(ConciliacionConstants.INFORMATION_NOT_FOUND,
					CodigoError.NMP_PMIMONTE_0009);
		movimientosPagoRepository.saveAll(MovimientosBuilder
				.buildMovimientoPagoListFromMovimientoConciliacionList(movimientoConciliacionList, createdBy));
		if (null == solicitarPagosMailDataDTOList || solicitarPagosMailDataDTOList.isEmpty())
			throw new InformationNotFoundException(ConciliacionConstants.INFORMATION_NOT_FOUND,
					CodigoError.NMP_PMIMONTE_0009);
	}

	public BusRestMailDTO construyeEMailVelocity(List<SolicitarPagosMailDataDTO> solicitarPagosMailDataDTOList) {
		Map<String, Object> model;
		// Se obtienen los contactos de midas
		List<Contactos> contactos = contactoRespository.findByIdTipoContacto(ConciliacionConstants.TIPO_CONTACTO_MIDAS);
		if (null == contactos || contactos.isEmpty())
			throw new InformationNotFoundException(ConciliacionConstants.THERE_IS_NO_CONTACTS_TO_SEND_MAIL,
					CodigoError.NMP_PMIMONTE_BUSINESS_043);
		// Se constrye el cuerpo de correo HTML
		model = buildMailModel(solicitarPagosMailDataDTOList);
		String htmlMail = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, mc.velocityLayout, "UTF-8",
				model);
		// Se construye el DTO de request y se invoca el servicio de correo proveido por
		// BUS
		StringBuilder contacts = new StringBuilder();
		int ct = 0;
		for (Contactos cto : contactos) {
			if (ct > 0)
				contacts.append(",");
			contacts.append(cto.getEmail());
			ct++;
		}
		return new BusRestMailDTO(contacts.toString(), mc.mailFrom, mc.subjectMail, htmlMail,
				new BusRestAdjuntoDTO(new ArrayList<>()));
	}

	/**
	 * Crea el modelo a usar en plantilla HTML de velocity
	 * 
	 * @param solicitarPagosMailDataDTOList
	 * @return
	 */
	public Map<String, Object> buildMailModel(List<SolicitarPagosMailDataDTO> solicitarPagosMailDataDTOList) {
		Map<String, Object> model = new HashMap<>();
		model.put("elements", solicitarPagosMailDataDTOList);
		return model;
	}

	/**
	 * Inserta los movimientos transito que se marcaron como solicitud de pago
	 * 
	 * @param folio
	 * @param requestUser
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void insertaMovimientosPagoFinal(final Long folio, final String requestUser) {
		List<MovimientoTransito> movimientosTransito = null;
		List<MovimientoPago> movimientosPago = null;
		List<MovimientoPagoDTO> movimientosPagoDTO = null;
		if (null != folio) {
			movimientosTransito = movimientoTransitoRepository.findByIdConciliacionPagos(folio, EstatusMovimientosTransitoEnum.SOLICITADA.getNombre());
			if (null != movimientosTransito && !movimientosTransito.isEmpty()) {
				movimientosPagoDTO = MovimientosBuilder
						.buildMovimientoPagoDTOListFromMovimientoTransitoList(movimientosTransito);
				movimientoTransitoRepository.deleteInBatch(movimientosTransito);
				movimientoTransitoRepository.flush();
				if (null != movimientosPagoDTO && !movimientosPagoDTO.isEmpty()) {
					movimientosPago = MovimientosBuilder
							.buildMovimientoPagoListFromMovimientoPagoDTOList(movimientosPagoDTO, folio, requestUser);
					movimientosPagoRepository.saveAll(movimientosPago);
				}
			}
		}
	}

	/**
	 * Regresa un objeto de tipo BigDecimal con la suma de los montos de una lista
	 * de objetos de tipo MovimientoTransito
	 * 
	 * @param movimientoTransitoList
	 * @return
	 */
	private static BigDecimal getMontoFromSolicitarPagosMailDataDTOList(
			List<SolicitarPagosMailDataDTO> solicitarPagosMailDataDTOList) {
		BigDecimal total = null;
		if (null != solicitarPagosMailDataDTOList && !solicitarPagosMailDataDTOList.isEmpty()) {
			total = new BigDecimal(0);
			for (SolicitarPagosMailDataDTO solicitarPagosMailDataDTO : solicitarPagosMailDataDTOList) {
				total = total.add(
						null != solicitarPagosMailDataDTO && null != solicitarPagosMailDataDTO.getMontoTransaccion()
								? solicitarPagosMailDataDTO.getMontoTransaccion()
								: BigDecimal.ZERO);
			}
		}
		return total;
	}

}
