package mx.com.nmp.pagos.mimonte.dss;

import java.util.List;
import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import mx.com.nmp.pagos.mimonte.builder.ReglaNegocioBuilder;
import mx.com.nmp.pagos.mimonte.constans.DSSConstants;
import mx.com.nmp.pagos.mimonte.dto.PagoRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.ReglaNegocioDTO;
import mx.com.nmp.pagos.mimonte.dto.ReglaNegocioResumenDTO;
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
	private String ID_CLIENTE_VAR;

	/**
	 * Propiedad para uso de variable idReglaNegocio en consultas de reglas de
	 * negocio
	 */
	@Value(DSSConstants.ID_REGLA_PROP)
	private String ID_REGLA_VAR;

	/**
	 * Propiedad para uso de variable idAfiliacion en consultas de reglas de negocio
	 */
	@Value(DSSConstants.ID_AFILAICION_REGLA_PROP)
	private String ID_AFILAICION_REGLA_VAR;

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
	 */
	public Integer getNoAfiliacion(PagoRequestDTO pagoRequestDTO) {
		LOG.debug("Ingresando al metodo DSSModule.getNoAfiliacion()");
		Integer noAfiliacion = null;
		List<ReglaNegocioDTO> reglasNegocioDTO = null;
		Stack<ReglaNegocioResumenDTO> stack = null;
		validacionesPrincipales(pagoRequestDTO);
		LOG.debug("Inicia el proceso de obtencion de reglas de negocio");
		reglasNegocioDTO = ReglaNegocioBuilder
				.buildReglaNegocioDTOFromReglaNegocioList(dssService.getReglasNegocio(pagoRequestDTO.getIdCliente()));
		stack = new Stack<>();
		LOG.debug("Se finalizo el proceso de obtencion de reglas de negocio");
		if (null == reglasNegocioDTO || reglasNegocioDTO.isEmpty())
			throw new DSSException(DSSConstants.NO_RULES_FOUND_MESSAGE);
//		else {
//			validacionesSecundarias(pagoRequestDTO);
//		}
		LOG.debug("Inicia reemplazo de variables");
		for (ReglaNegocioDTO reglaNegocioDTO : reglasNegocioDTO) {
			replaceVariablesDB(reglaNegocioDTO);
			replaceLocalVariables(reglaNegocioDTO, pagoRequestDTO.getIdCliente());
			ReglaNegocioResumenDTO regla = null;
			LOG.debug("Inicia ejecucion de query: " + reglaNegocioDTO.getConsulta());
			regla = dssService.execQuery(reglaNegocioDTO.getConsulta());
			stack.push(regla);
		}
		noAfiliacion = evaluateResultStack(stack);
		return noAfiliacion;
	}

	/**
	 * 
	 * Metodo que desaplia los resultados de la evaluacion de las reglas de un
	 * cliente y toma el que tiene prioridad mayor y cuya regla se haya cumplido
	 * satisfactoriamente
	 * 
	 * @param         Stack<ReglaNegocioResumenDTO> stack
	 * @param Integer noAfiliacion
	 */
	private static Integer evaluateResultStack(Stack<ReglaNegocioResumenDTO> stack) {
		LOG.debug("Inicia proceso de evaluacion de reglas de negocio");
		ReglaNegocioResumenDTO reglaNegocioResumenDTO;
		Integer noAfiliacion = null;
		while (!stack.isEmpty()) {
			reglaNegocioResumenDTO = stack.pop();
			if (null == noAfiliacion)
				noAfiliacion = reglaNegocioResumenDTO.getIdAfiliacion();
			else if (reglaNegocioResumenDTO.getValido() && reglaNegocioResumenDTO.getIdAfiliacion() > noAfiliacion) {
				noAfiliacion = reglaNegocioResumenDTO.getIdAfiliacion();
			}
		}
		return noAfiliacion;
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
	 * 
	 * Metodo que reemplaza las variables de la consulta por los valores reales de
	 * el objeto en cuestion
	 * 
	 * @param reglaNegocioDTO
	 */
	private void replaceLocalVariables(ReglaNegocioDTO reglaNegocioDTO, Integer idCliente) {
		LOG.debug("Inicia reemplazo de variables de base de datos para query: "
				+ (null != reglaNegocioDTO ? reglaNegocioDTO.getConsulta() : null));
		String str = null != reglaNegocioDTO ? reglaNegocioDTO.getConsulta() : null;
		if (null != reglaNegocioDTO && null != reglaNegocioDTO.getAfliacion()
				&& null != reglaNegocioDTO.getAfliacion().getId() && null != reglaNegocioDTO.getId()
				&& null != idCliente && idCliente != 0) {
			str = str.replace(ID_REGLA_VAR, String.valueOf(reglaNegocioDTO.getId()));
			str = str.replace(ID_AFILAICION_REGLA_VAR, String.valueOf(reglaNegocioDTO.getAfliacion().getId()));
			str = str.replace(ID_CLIENTE_VAR, String.valueOf(idCliente));
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
		LOG.debug("Inicia reemplazo de variables de base de datos para query: "
				+ (null != reglaNegocioDTO ? reglaNegocioDTO.getConsulta() : null));
		String str = null != reglaNegocioDTO ? reglaNegocioDTO.getConsulta() : null;
		if (null != reglaNegocioDTO && null != reglaNegocioDTO.getVariables()) {
			for (VariableDTO variableDTO : reglaNegocioDTO.getVariables()) {
				str = str.replace(variableDTO.getClave(), variableDTO.getValor());
			}
			reglaNegocioDTO.setConsulta(str);
		}
	}

}
