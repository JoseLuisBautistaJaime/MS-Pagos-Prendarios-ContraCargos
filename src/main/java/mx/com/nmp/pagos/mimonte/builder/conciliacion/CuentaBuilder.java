/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder.conciliacion;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.CuentaDTO;
import mx.com.nmp.pagos.mimonte.model.Cuenta;

/**
 * Nombre: CuentaBuilder Descripcion: Clase de capa de builder que se encarga de
 * convertir difrentes tipos de objetos y entidades relacionadas con la Cuenta
 *
 * @author José Rodríguez jgrodriguez@qaurksoft.net
 * @creationDate 07/05/2019 14:01 hrs.
 * @version 0.1
 */
public class CuentaBuilder {

	public CuentaBuilder() {
		super();
	}

	/**
	 * Construye un objeto de tipo CuentaDTO a partir de una entidad de tipo Cuenta.
	 * 
	 * @param cuenta
	 * @return cuentaDTO
	 */
	public static CuentaDTO buildCuentaDTOFromCuenta(Cuenta cuenta) {
		CuentaDTO cuentaDTO = null;
		if (cuenta != null) {
			cuentaDTO = new CuentaDTO();
			cuentaDTO.setId(cuenta.getId());
			cuentaDTO.setNumero(cuenta.getNumeroCuenta());
			cuentaDTO.setEstatus(cuenta.getEstatus());
		}
		return cuentaDTO;
	}

	/**
	 * Construye una entidad de tipo Cuenta a partir de un objeto de tipo CuentaDTO.
	 * 
	 * @param cuentaDTO
	 * @return
	 */
	public static Cuenta buildCuentaFromCuentaDTO(CuentaDTO cuentaDTO) {
		Cuenta cuenta = null;
		if (cuentaDTO != null) {
			cuenta = new Cuenta();
			cuenta.setId(cuentaDTO.getId());
			cuenta.setNumeroCuenta(cuentaDTO.getNumero());
			cuenta.setEstatus(cuentaDTO.getEstatus());
		}
		return cuenta;
	}

}
