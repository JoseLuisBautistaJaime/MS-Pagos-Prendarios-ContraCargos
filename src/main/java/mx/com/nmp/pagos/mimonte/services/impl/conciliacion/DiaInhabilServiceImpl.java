/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.impl.conciliacion;

import mx.com.nmp.pagos.mimonte.dao.conciliacion.DiaInhabilRepository;
import mx.com.nmp.pagos.mimonte.model.conciliacion.CatalogoDiaInhabil;
import mx.com.nmp.pagos.mimonte.services.conciliacion.DiaInhabilService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * @name DiaInhabilServiceImpl
 * @description Clase de capa de servicio para el catálogo de días  inhábiles que sirve para
 *              realizar operaciones de lógica de negocios
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 30/11/2021 10:48 hrs.
 * @version 0.1
 */
@Service("diaInhabilServiceImpl")
public class DiaInhabilServiceImpl implements DiaInhabilService {

	/**
	 * Instancia para impresion de LOG's
	 */
	private static final Logger LOG = LoggerFactory.getLogger(DiaInhabilServiceImpl.class);


	@Autowired
	private DiaInhabilRepository diaInhabilRepository;

	/**
	 * Metodo que realiza una busqueda a partir de la fecha de calendarización del día inhábil
	 * devolviendo como resultado un objeto de tipo CalendarioEjecucionProcesoDTO.
	 */
	@Override
	public CatalogoDiaInhabil buscarByFecha(Date fecha){

		// Declaracion de objetos necesarios
		CatalogoDiaInhabil result = null;

		result = diaInhabilRepository.findByFecha( fecha );

		return result;

	}

}
