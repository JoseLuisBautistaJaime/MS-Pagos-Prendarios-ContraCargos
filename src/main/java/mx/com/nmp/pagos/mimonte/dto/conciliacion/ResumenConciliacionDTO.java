/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

/**
 * @name ResumenConciliacionDTO
 * @description Clase que encapsula el request de ResumenConciliacionDTO para la
 *              conciliación.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 15/05/2019 21:38 hrs.
 * @version 0.1
 */
// TODO: Eliminar esta clase si solo se usa en el response del endpoint de resumen de conciliacion dentro del controlador de conciliacion si se detecta que no se usa
public class ResumenConciliacionDTO implements Comparable<ResumenConciliacionDTO>{
	
    private Long totalProceso;
    private Long totalDevolucionesLiquidadas;
    private Long totalConciliaciones;

	public ResumenConciliacionDTO() {
		super();
	}

	public ResumenConciliacionDTO(Long totalProceso, Long totalDevolucionesLiquidadas, Long totalConciliaciones) {
		super();
		this.totalProceso = totalProceso;
		this.totalDevolucionesLiquidadas = totalDevolucionesLiquidadas;
		this.totalConciliaciones = totalConciliaciones;
	}

	public Long getTotalProceso() {
		return totalProceso;
	}

	public void setTotalProceso(Long totalProceso) {
		this.totalProceso = totalProceso;
	}

	public Long getTotalDevolucionesLiquidadas() {
		return totalDevolucionesLiquidadas;
	}

	public void setTotalDevolucionesLiquidadas(Long totalDevolucionesLiquidadas) {
		this.totalDevolucionesLiquidadas = totalDevolucionesLiquidadas;
	}

	public Long getTotalConciliaciones() {
		return totalConciliaciones;
	}

	public void setTotalConciliaciones(Long totalConciliaciones) {
		this.totalConciliaciones = totalConciliaciones;
	}

	@Override
	public String toString() {
		return "ResumenConciliacionDTO [totalProceso=" + totalProceso + ", totalDevolucionesLiquidadas="
				+ totalDevolucionesLiquidadas + ", totalConciliaciones=" + totalConciliaciones + "]";
	}

	@Override
	public int compareTo(ResumenConciliacionDTO o) {
		return 0;
	}

}
