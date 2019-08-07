/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.util;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.com.nmp.pagos.mimonte.dao.conciliacion.ConciliacionRepository;

/**
 * @name MiniMaquinaEstadosConciliacion
 * @description Clase de utileria que contiene el metodo parainvocar a a mini
 *              maquina de estados de actualizacion de sub-estatus de
 *              conciliacion
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 23/07/2019 18:05 hrs.
 * @version 0.1
 */
@Component
public class MiniMaquinaEstadosConciliacion {

	private MiniMaquinaEstadosConciliacion() {
		super();
	}

	/**
	 * Repository de conciliacion
	 */
	@Autowired
	private ConciliacionRepository conciliacionRepository;

	/**
	 * Regresa un valor booleano indicando si un id de sub-estatus de conciliacion
	 * es valido, tomando en cuenta los parametros recibidos: folio de conciliacion,
	 * nombre del proceso e id de sub-estatus nuevo
	 * 
	 * @param folio
	 * @param proceso
	 * @param idSubestatus
	 * @return
	 */
	public Boolean checkIfSubEstatusIsRightByFolioAnfIdSubEstatus(final Long folio, final Long idSubestatus) {
		Boolean resp = null;
		Object obj = null;
		if (null != folio && null != idSubestatus) {
			obj = conciliacionRepository.checkIfSubEstatusIsRightByFolioAnfIdSubEstatus(folio, idSubestatus);
			if (null != obj)
				resp = ((BigInteger) obj).compareTo(BigInteger.ONE) == 0;
		}

		return resp;
	}

}
