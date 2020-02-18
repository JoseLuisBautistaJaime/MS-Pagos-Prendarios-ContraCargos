/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ActualizarSubEstatusRequestDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.services.impl.conciliacion.ConciliacionServiceImpl;

/**
 * @name AsyncLayer
 * @description Clase que sirve de capa intermedia para ejecutar metodos de
 *              manera asincrona
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 31/10/2019 13:19 hrs.
 * @version 0.1
 */
@Component
public class AsyncLayer {

	@Autowired
	@Qualifier("conciliacionServiceImpl")
	private ConciliacionServiceImpl conciliacionServiceImpl;

	/**
	 * Instancia para impresion de LOG's
	 */
	private static final Logger LOG = LoggerFactory.getLogger(AsyncLayer.class);

	@Async("conciliacionAsyncExecutor")
	public void generarConciliacion(Long folio, String requestUser, Long subEstatusInicial) throws ConciliacionException {
		Boolean procesoCorrecto = false;
		String descripcionError = null;
		try {
			conciliacionServiceImpl.generarConciliacion(folio, requestUser);
			procesoCorrecto = true;
		} catch (ConciliacionException cex) {
			procesoCorrecto = false;
			descripcionError = cex.getCodigoError().getDescripcion();
			LOG.error(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE, cex);
		} catch (Exception eex) {
			procesoCorrecto = false;
			descripcionError = CodigoError.NMP_PMIMONTE_BUSINESS_132.getDescripcion();
			LOG.error(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE, eex);
		} finally {
			try {
				// Si la conciliacion se realizo despues de completar el alta de movs. proveedor transaccional
				if(ConciliacionConstants.SUBESTATUS_CONCILIACION_CONSULTA_OPEN_PAY_COMPLETADA.equals(subEstatusInicial)) {
					// Se actualiza el sub estatus de la conciliacion en base al resultado
					LOG.info(">>> INICIA ACTUALIZACION DE SUB ESTATUS DE CONCILIACION, DESPUES DE HABER HECHO LA CONCILIACION FOLIO: {}", folio);
					conciliacionServiceImpl.actualizaSubEstatusConciliacion(new ActualizarSubEstatusRequestDTO(folio,
							procesoCorrecto ? ConciliacionConstants.SUBESTATUS_CONCILIACION_CONCILIACION_COMPLETADA
									: ConciliacionConstants.SUBESTATUS_CONCILIACION_CONCILIACION_ERROR,
							descripcionError), requestUser);
					LOG.info(">>> FINALIZA EXITOSAMENTE ACTUALIZACION DE SUB ESTATUS DE CONCILIACION, DESPUES DE HABER HECHO LA CONCILIACION FOLIO: {}", folio);
				}
			// Si la conciliacion se realizo despues de completar el alta de movs. de edo. cta.
			else if(ConciliacionConstants.SUBESTATUS_CONCILIACION_CONSULTA_ESTADO_DE_CUENTA_COMPLETADA.equals(subEstatusInicial)) {
				// Se actualiza el sub estatus de la conciliacion en base al resultado
				LOG.info(">>> INICIA ACTUALIZACION DE SUB ESTATUS DE CONCILIACION, DESPUES DE HABER HECHO LA CONCILIACION FOLIO: {}", folio);
				conciliacionServiceImpl.actualizaSubEstatusConciliacion(new ActualizarSubEstatusRequestDTO(folio,
						procesoCorrecto ? ConciliacionConstants.SUBESTATUS_CONCILIACION_CONSULTA_ESTADO_DE_CUENTA_COMPLETADA
								: ConciliacionConstants.SUBESTATUS_CONCILIACION_CONSULTA_ESTADO_DE_CUENTA_COMPLETADA,
						descripcionError), requestUser);
				LOG.info(">>> FINALIZA EXITOSAMENTE ACTUALIZACION DE SUB ESTATUS DE CONCILIACION, DESPUES DE HABER HECHO LA CONCILIACION FOLIO: {}", folio);
				}
			} catch (Exception ex) {
				LOG.error(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE, ex);
			}
		}

	}

}
