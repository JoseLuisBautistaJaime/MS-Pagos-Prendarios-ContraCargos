package mx.com.nmp.pagos.mimonte.dss;

import java.util.List;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import mx.com.nmp.pagos.mimonte.builder.ReglaNegocioBuilder;
import mx.com.nmp.pagos.mimonte.constans.DSSConstants;
import mx.com.nmp.pagos.mimonte.dto.PagoRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.ReglaNegocioDTO;
import mx.com.nmp.pagos.mimonte.dto.ReglaNegocioResumenDTO;
import mx.com.nmp.pagos.mimonte.exception.DSSException;
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

	@Value(DSSConstants.DataObjectNames.ClienteData.ID_CLIENTE)
	private String idClienteProp;
	@Value(DSSConstants.DataObjectNames.PagoData.MONTO_TOTAL)
	private String montoTotalProp;
	@Value(DSSConstants.DataObjectNames.TarjetaData.ALIAS_TARJETA)
	private String aliasTarjetaProp;
	@Value(DSSConstants.DataObjectNames.TarjetaData.DIGITOS_TARJETA)
	private String digitosTarjetaProp;
	@Value(DSSConstants.DataObjectNames.TarjetaData.TOKEN_TARJETA)
	private String tokenTarjetaProp;
	@Value(DSSConstants.DataObjectNames.TarjetaData.TIPO_TARJETA_ID)
	private String tipoTarjetaIdProp;

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
		validacionesPrincipales(pagoRequestDTO);
		reglasNegocioDTO = ReglaNegocioBuilder
				.buildReglaNegocioDTOFromReglaNegocioList(dssService.getReglasNegocio(pagoRequestDTO.getIdCliente()));
		stack = new Stack<>();
		if (null == reglasNegocioDTO || reglasNegocioDTO.isEmpty())
			throw new DSSException(DSSConstants.NO_RULES_FOUND_MESSAGE);
		else {
			validacionesSecundarias(pagoRequestDTO);
		}
		for (ReglaNegocioDTO reglaNegocioDTO : reglasNegocioDTO) {
			replaceVariables(reglaNegocioDTO, pagoRequestDTO);
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

	/**
	 * 
	 * Metodo que valida que un objeto PagoRequestDTO y su objeto interno Idcliente
	 * no sean nulos
	 * 
	 * @param pagoRequestDTO
	 */
	private static void validacionesPrincipales(PagoRequestDTO pagoRequestDTO) {
		ValidadorObjeto vo = new ValidadorObjeto();
		vo.noNulo(pagoRequestDTO);
		vo.noNulo(pagoRequestDTO.getIdCliente());
	}

	/**
	 * 
	 * Metodo que valida que un objeto PagoRequestDTO y su objeto interno Tarjeta no
	 * sean nulos
	 * 
	 * @param pagoRequestDTO
	 */
	private static void validacionesSecundarias(PagoRequestDTO pagoRequestDTO) {
		ValidadorObjeto vo = new ValidadorObjeto();
		vo.noNulo(pagoRequestDTO);
		vo.noNulo(pagoRequestDTO.getTarjeta());
		vo.noNulo(pagoRequestDTO.getTarjeta().getAlias());
		vo.noNulo(pagoRequestDTO.getTarjeta().getDigitos());
		vo.noNulo(pagoRequestDTO.getTarjeta().getToken());
		vo.noNulo(pagoRequestDTO.getTarjeta().getTipo());
		vo.noNulo(pagoRequestDTO.getTarjeta().getTipo().getId());
	}

	/**
	 * 
	 * Metodo que reemplaza las variables de un query por los valores del objeto en cuestion (pago, cliente, tarjeta)
	 * 
	 * @param reglaNegocioDTO
	 * @param pagoRequestDTO
	 */
	private void replaceVariables(ReglaNegocioDTO reglaNegocioDTO, PagoRequestDTO pagoRequestDTO) {
		reglaNegocioDTO.setConsulta(
				reglaNegocioDTO.getConsulta().replace(idClienteProp, String.valueOf(pagoRequestDTO.getIdCliente()))
						.replace(montoTotalProp, String.valueOf(pagoRequestDTO.getMontoTotal()))
						.replace(aliasTarjetaProp, pagoRequestDTO.getTarjeta().getAlias())
						.replace(digitosTarjetaProp, pagoRequestDTO.getTarjeta().getDigitos())
						.replace(tokenTarjetaProp, pagoRequestDTO.getTarjeta().getToken())
						.replace(tipoTarjetaIdProp, String.valueOf(pagoRequestDTO.getTarjeta().getTipo().getId())));
	}

}
