/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.impl.conciliacion;

import mx.com.nmp.pagos.mimonte.builder.conciliacion.DiaInhabilBuilder;
import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.DiaInhabilRepository;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.DiaInhabilDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.FiltroDiaInhabilDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.model.conciliacion.CatalogoDiaInhabil;
import mx.com.nmp.pagos.mimonte.services.conciliacion.DiaInhabilService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	/**
	 * Metodo que realiza una busqueda a partir de un objeto de tipo
	 * FiltroDiaInhabilDTO devolviendo como resultado una lista de tipo
	 * DiaInhabilDTO.
	 */
	@Override
	public List<DiaInhabilDTO> consultarByPropiedades(FiltroDiaInhabilDTO filtroDiaInhabilDTO) {

		// Declaracion de objetos necesarios
		List<DiaInhabilDTO> result = null;

		result = diaInhabilRepository.findByPropiedades( filtroDiaInhabilDTO.getFecha(), filtroDiaInhabilDTO.getDescripcion());

		return result;
	}

	/**
	 * Metodo que da de alta un día inhábil regresando el elemento guardado  a partir de un
	 * objeto de tipo DiaInhabilDTO
	 */
	@Override
	@Transactional
	public DiaInhabilDTO saveDiaInhabil(DiaInhabilDTO diaInhabilDTO) {
		// Se valida que el día inhábil no se duplique
		CatalogoDiaInhabil diaInhabilBD = diaInhabilRepository.findByFecha(diaInhabilDTO.getFecha());
		if (diaInhabilBD != null) {
			throw new ConciliacionException(ConciliacionConstants.DIA_INHABIL_CANNOT_BE_DUPLICATE, CodigoError.NMP_PMIMONTE_BUSINESS_149);
		}

		LOG.debug("Creando día inhábil...");

		// Se construye la conciliacion y se guarda
		CatalogoDiaInhabil diaInhabil = DiaInhabilBuilder.buildCatalogoDiaInhabilFromDiaInhabilDTO(diaInhabilDTO);

		diaInhabil = diaInhabilRepository.save(diaInhabil);

		return DiaInhabilBuilder.buildDiaInhabilDTOFromCatalogoDiaInhabil(diaInhabil);


	}

}
