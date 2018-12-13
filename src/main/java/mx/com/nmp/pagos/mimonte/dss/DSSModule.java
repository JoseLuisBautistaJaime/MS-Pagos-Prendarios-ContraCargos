package mx.com.nmp.pagos.mimonte.dss;

import java.util.List;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.com.nmp.pagos.mimonte.builder.ReglaNegocioBuilder;
import mx.com.nmp.pagos.mimonte.dto.PagoRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.ReglaNegocioDTO;
import mx.com.nmp.pagos.mimonte.dto.ReglaNegocioResumenDTO;
import mx.com.nmp.pagos.mimonte.services.DSSService;
import mx.com.nmp.pagos.mimonte.util.validacion.ValidadorObjeto;

/**
 * Nombre: DSSModule
 * Descripcion: Clase DSS que encuentra el numero asigna el
 * numero de afiliaicion de un clinete en base a unas reglas previamnete
 * establecidas
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 12/12/2018 19:43 hrs.
 * @version 0.1
 */
@Component("dssModule")
public class DSSModule {

	@Autowired
	private DSSService dssService;

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
		Integer noAfiliacion = null;
		List<ReglaNegocioDTO> reglasNegocioDTO = null;
		Stack<ReglaNegocioResumenDTO> stack = null;
		validaciones(pagoRequestDTO);
		reglasNegocioDTO = ReglaNegocioBuilder
				.buildReglaNegocioDTOFromReglaNegocioList(dssService.getReglasNegocio(pagoRequestDTO.getIdCliente()));
		stack = new Stack<>();
		for (ReglaNegocioDTO reglaNegocioDTO : reglasNegocioDTO) {
			stack.push(dssService.executeQuery(reglaNegocioDTO.getConsulta()));
		}
		evaluateResultStack(stack, noAfiliacion);
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
	private static void evaluateResultStack(Stack<ReglaNegocioResumenDTO> stack, Integer noAfiliacion) {
		ReglaNegocioResumenDTO reglaNegocioResumenDTO;
		while (!stack.isEmpty()) {
			reglaNegocioResumenDTO = stack.pop();
			if (null == noAfiliacion)
				noAfiliacion = reglaNegocioResumenDTO.getIdAfiliacion();
			else if (reglaNegocioResumenDTO.getValido() && reglaNegocioResumenDTO.getIdAfiliacion() > noAfiliacion) {
				noAfiliacion = reglaNegocioResumenDTO.getIdAfiliacion();
			}
		}
	}

	public static void validaciones(PagoRequestDTO pagoRequestDTO) {
		ValidadorObjeto vo = new ValidadorObjeto();
		vo.noNulo(pagoRequestDTO);
		vo.noNulo(pagoRequestDTO.getIdCliente());
	}

}
