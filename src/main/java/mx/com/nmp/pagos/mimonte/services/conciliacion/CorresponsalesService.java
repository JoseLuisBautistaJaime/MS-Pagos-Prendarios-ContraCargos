/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.conciliacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import mx.com.nmp.pagos.mimonte.builder.CorresponsalBuilder;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.CorresponsalRepository;
import mx.com.nmp.pagos.mimonte.dto.CorresponsalDTO;

/**
 * @name CorresponsalesService
 * @description Clase de capa de servicio para el catalogo de corresponsales (tk_proveedor)
 *              que sirve para realizar operaciones de logica de negocios para el catalogo.
 *
 * @author Daniel Hernandez danhernandez@quarksoft.net
 * @creationDate 06/11/2020 20:58 hrs.
 * @version 0.1
 */
@Service("corresponsalesService")
public class CorresponsalesService {

	/**
	 * Repository para Corresponsales
	 */
	@Autowired
	@Qualifier("corresponsalRepository")
	private CorresponsalRepository corresponsalRepository;

	/**
	 * Regresa todas los Corresponsales registrados en el Catalogo de tk_proveedor
	 */
	public List<CorresponsalDTO> findAll() {
		return CorresponsalBuilder.buildCorresponsalDTOListFromProveedorList(corresponsalRepository.findAll());
	}

}
