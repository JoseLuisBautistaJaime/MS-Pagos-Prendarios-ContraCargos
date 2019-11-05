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
	public void generarConciliacion(Long folio, String requestUser) throws ConciliacionException {
		Boolean procesoCorrecto = false;
		String descripcionError = null;
		try {
			conciliacionServiceImpl.generarConciliacion(folio, requestUser);
			procesoCorrecto = true;
		} catch (ConciliacionException cex) {
			procesoCorrecto = false;
			descripcionError = cex.getCodigoError().getDescripcion();
			LOG.error(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE, cex);
			// TODO: Eliminar comentario
			//			throw cex;
		} catch (Exception eex) {
			procesoCorrecto = false;
			descripcionError = CodigoError.NMP_PMIMONTE_BUSINESS_132.getDescripcion();
			LOG.error(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE, eex);
			// TODO: Eliminar comentario
//			throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_132.getDescripcion(),
//					CodigoError.NMP_PMIMONTE_BUSINESS_132);
		} finally {
			try {
				// Se actualiza el sub estatus de la conciliacion en base al resultado
				LOG.info(">>> INICIA ACTUALIZACION DE SUB ESTATUS DE CONCILIACION, DESPUES DE HABER HECHO LA CONCILIACION FOLIO: {}", folio);
				conciliacionServiceImpl.actualizaSubEstatusConciliacion(new ActualizarSubEstatusRequestDTO(folio,
						procesoCorrecto ? ConciliacionConstants.SUBESTATUS_CONCILIACION_CONCILIACION_COMPLETADA
								: ConciliacionConstants.SUBESTATUS_CONCILIACION_CONCILIACION_ERROR,
						descripcionError), requestUser);
				LOG.info(">>> FINALIZA EXITOSAMENTE ACTUALIZACION DE SUB ESTATUS DE CONCILIACION, DESPUES DE HABER HECHO LA CONCILIACION FOLIO: {}", folio);
			} catch (Exception ex) {
				LOG.error(ConciliacionConstants.GENERIC_EXCEPTION_INITIAL_MESSAGE, ex);
				// TODO: Eliminar esta excepcion lanzada
//				throw new ConciliacionException(CodigoError.NMP_PMIMONTE_BUSINESS_030.getDescripcion(),
//						CodigoError.NMP_PMIMONTE_BUSINESS_030);
			}
		}

	}

}
