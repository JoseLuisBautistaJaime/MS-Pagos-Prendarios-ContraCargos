/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

/**
 * @name ResumenConciliacionResponseDTO
 * @description Clase que encapsula el response del endpoint de resumen de
 *              conciliaciones
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 27/01/2020 17:32 hrs.
 * @version 0.1
 */
public class ResumenConciliacionResponseDTO {

	private TotalProcesoDTO totalProceso;
	private TotalDevolucionesLiquidadasDTO totalDevolucionesLiquidadas;
	private TotalConciliacionesDTO totalConciliaciones;

	public ResumenConciliacionResponseDTO() {
		super();
	}

	public ResumenConciliacionResponseDTO(TotalProcesoDTO totalProceso,
			TotalDevolucionesLiquidadasDTO totalDevolucionesLiquidadas, TotalConciliacionesDTO totalConciliaciones) {
		super();
		this.totalProceso = totalProceso;
		this.totalDevolucionesLiquidadas = totalDevolucionesLiquidadas;
		this.totalConciliaciones = totalConciliaciones;
	}

	public TotalProcesoDTO getTotalProceso() {
		return totalProceso;
	}

	public void setTotalProceso(TotalProcesoDTO totalProceso) {
		this.totalProceso = totalProceso;
	}

	public TotalDevolucionesLiquidadasDTO getTotalDevolucionesLiquidadas() {
		return totalDevolucionesLiquidadas;
	}

	public void setTotalDevolucionesLiquidadas(TotalDevolucionesLiquidadasDTO totalDevolucionesLiquidadas) {
		this.totalDevolucionesLiquidadas = totalDevolucionesLiquidadas;
	}

	public TotalConciliacionesDTO getTotalConciliaciones() {
		return totalConciliaciones;
	}

	public void setTotalConciliaciones(TotalConciliacionesDTO totalConciliaciones) {
		this.totalConciliaciones = totalConciliaciones;
	}

	@Override
	public String toString() {
		return "ResumenConciliacionResponseDTO [totalProceso=" + totalProceso + ", totalDevolucionesLiquidadas="
				+ totalDevolucionesLiquidadas + ", totalConciliaciones=" + totalConciliaciones + "]";
	}

}
