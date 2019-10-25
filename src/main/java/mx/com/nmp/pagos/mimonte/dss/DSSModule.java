/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dss;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import mx.com.nmp.pagos.mimonte.builder.ReglaNegocioBuilder;
import mx.com.nmp.pagos.mimonte.constans.DSSConstants;
import mx.com.nmp.pagos.mimonte.dto.PagoRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.ReglaNegocioDTO;
import mx.com.nmp.pagos.mimonte.dto.TipoAutorizacionDTO;
import mx.com.nmp.pagos.mimonte.dto.VariableDTO;
import mx.com.nmp.pagos.mimonte.exception.DSSException;
import mx.com.nmp.pagos.mimonte.services.DSSService;
import mx.com.nmp.pagos.mimonte.util.validacion.ValidadorObjeto;

/**
 * Nombre: DSSModule Descripcion: Clase DSS que encuentra el numero asigna el
 * numero de afiliaicion de un clinete en base a unas reglas previamnete
 * establecidas
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 12/12/2018 19:43 hrs.
 * @version 0.1
 */
@Component("dssModule")
public class DSSModule {

	/**
	 * Servicio DSS para procesar peticiones referentes a modulo DSS
	 */
	@Autowired
	private DSSService dssService;

	/**
	 * Propiedad para uso de variable idCliente en consultas de reglas de negocio
	 */
	@Value(DSSConstants.ID_CLIENTE_PROP)
	private String idClienteVar;

	/**
	 * Logger para el registro de actividad en la bitacora
	 */
	private static final Logger LOG = LoggerFactory.getLogger(DSSModule.class);

	public DSSModule() {
		super();
	}

	/**
	 * 
	 * Metodo que entrege un numero de afiliacion en base a las reglas de negocio
	 * que se aplican a un clinete en especifico
	 * 
	 * @param PagoRequestDTO pagoRequestDTO
	 * @return Integer
	 * @throws SQLException
	 * @throws SQLDataException
	 * @throws DataIntegrityViolationException
	 */
	public TipoAutorizacionDTO getNoAfiliacion(PagoRequestDTO pagoRequestDTO)
			throws DataIntegrityViolationException, SQLDataException, SQLException {
		LOG.debug("Ingresando al metodo DSSModule.getNoAfiliacion()");
		List<ReglaNegocioDTO> reglasNegocioDTO = null;
		Set<TipoAutorizacionDTO> stack = null;
		TipoAutorizacionDTO tipoAutorizacionDTO = null;
		TipoAutorizacionDTO tipoAutorizacionDTOFinal = null;
		validacionesPrincipales(pagoRequestDTO);
		LOG.debug("Inicia el proceso de obtencion de reglas de negocio");
		reglasNegocioDTO = ReglaNegocioBuilder.buildReglaNegocioDTOFromReglaNegocioList(dssService.getReglasNegocio());
		stack = new TreeSet<>();
		LOG.debug("Se finalizo el proceso de obtencion de reglas de negocio");
		if (null == reglasNegocioDTO || reglasNegocioDTO.isEmpty())
			throw new DSSException(DSSConstants.NO_RULES_FOUND_MESSAGE);
		LOG.debug("Inicia reemplazo de variables");
		for (ReglaNegocioDTO reglaNegocioDTO : reglasNegocioDTO) {
			replaceVariablesDB(reglaNegocioDTO);
			replaceLocalVariables(reglaNegocioDTO, pagoRequestDTO.getIdCliente());
			Boolean estatus = null;
			LOG.debug("Inicia ejecucion de query: {}",
					(null != reglaNegocioDTO.getConsulta() ? reglaNegocioDTO.getConsulta() : null));
			estatus = (Boolean) dssService.execQuery(reglaNegocioDTO.getConsulta());
			if (estatus) {
				tipoAutorizacionDTO = evaluateGreater(reglaNegocioDTO.getTipoAutorizacionSet());
				if (null != tipoAutorizacionDTO)
					stack.add(tipoAutorizacionDTO);
			}
		}
		tipoAutorizacionDTOFinal = evaluateGreater(stack);
		// Si el tipo de autorizacion es nulo se asigna por default el 1: Ningun tipo de
		// autorizacion
		if (null == tipoAutorizacionDTOFinal)
			tipoAutorizacionDTOFinal = dssService.getTipoAutorizacionById(1);

		LOG.debug("<< getNoAfiliacion - " + tipoAutorizacionDTOFinal.toString());
		return tipoAutorizacionDTOFinal;
	}

	public static TipoAutorizacionDTO evaluateGreater(Set<TipoAutorizacionDTO> tipoAutorizacionDTOSet) {
		int max = 0;
		TipoAutorizacionDTO tDTO = null;
		if (null != tipoAutorizacionDTOSet) {
			for (TipoAutorizacionDTO tipoAutorizacionDTO : tipoAutorizacionDTOSet) {
				if (tipoAutorizacionDTO.getId() > max) {
					max = tipoAutorizacionDTO.getId();
					tDTO = tipoAutorizacionDTO;
				}
			}
		}
		return tDTO;
	}

	/**
	 * 
	 * Metodo que valida que un objeto PagoRequestDTO y su objeto interno Idcliente
	 * no sean nulos
	 * 
	 * @param pagoRequestDTO
	 */
	private static void validacionesPrincipales(PagoRequestDTO pagoRequestDTO) {
		LOG.debug("Se realizan validaciones principales");
		ValidadorObjeto vo = new ValidadorObjeto();
		vo.noNulo(pagoRequestDTO);
		vo.noNulo(pagoRequestDTO.getIdCliente());
	}

	/**
	 * Metodo que reemplaza las variables de la consulta por los valores reales de
	 * el objeto en cuestion
	 * 
	 * @param reglaNegocioDTO
	 * @param idCliente
	 */
	private void replaceLocalVariables(ReglaNegocioDTO reglaNegocioDTO, Long idCliente) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Inicia reemplazo de variables de base de datos para query: {}",
					(null != reglaNegocioDTO ? reglaNegocioDTO.getConsulta() : null));
		}
		String str = null != reglaNegocioDTO ? reglaNegocioDTO.getConsulta() : null;
		if (null != reglaNegocioDTO && null != reglaNegocioDTO.getId() && null != idCliente && idCliente != 0) {
			str = str.replace(idClienteVar, String.valueOf(idCliente));
			reglaNegocioDTO.setConsulta(str);
		}
	}

	/**
	 * 
	 * Metodo que reemplaza las variables de la consulta por valores de base de
	 * datos
	 * 
	 * @param reglaNegocioDTO
	 */
	private void replaceVariablesDB(ReglaNegocioDTO reglaNegocioDTO) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Inicia reemplazo de variables de base de datos para query: {}",
					(null != reglaNegocioDTO && null != reglaNegocioDTO.getConsulta() ? reglaNegocioDTO.getConsulta()
							: null));
		}
		String str = null != reglaNegocioDTO ? reglaNegocioDTO.getConsulta() : null;
		if (null != reglaNegocioDTO && null != reglaNegocioDTO.getVariables()) {
			for (VariableDTO variableDTO : reglaNegocioDTO.getVariables()) {
				str = str.replace(variableDTO.getClave(), variableDTO.getValor());
			}
			reglaNegocioDTO.setConsulta(str);
		}
	}

}
