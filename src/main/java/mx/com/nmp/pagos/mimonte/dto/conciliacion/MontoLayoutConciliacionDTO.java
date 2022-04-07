/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;


import java.util.Objects;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 03-Feb-2022 11:33:55 AM
 */
public class MontoLayoutConciliacionDTO implements Comparable<MontoLayoutConciliacionDTO> {

	private Long folioConciliacion;
	private Long acumulado;
	private String tipoLayout;


	public MontoLayoutConciliacionDTO() {
		super();
	}

	public MontoLayoutConciliacionDTO(Long folioConciliacion, Long acumulado, String tipoLayout) {
		super();
		this.folioConciliacion = folioConciliacion;
		this.acumulado = acumulado;
		this.tipoLayout = tipoLayout;
	}

	public Long getFolioConciliacion() {
		return folioConciliacion;
	}

	public void setFolioConciliacion(Long folioConciliacion) {
		this.folioConciliacion = folioConciliacion;
	}

	public Long getAcumulado() {
		return acumulado;
	}

	public void setAcumulado(Long acumulado) {
		this.acumulado = acumulado;
	}

	public String getTipoLayout() {
		return tipoLayout;
	}

	public void setTipoLayout(String tipoLayout) {
		this.tipoLayout = tipoLayout;
	}

	@Override
	public String toString() {
		return "MontoLayoutConciliacionDTO [folioConciliacion=" + folioConciliacion + ", acumulado=" + acumulado +", tipoLayout=" +tipoLayout+ "]";
	}

	@Override
	public int compareTo(MontoLayoutConciliacionDTO o) {
		return 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(folioConciliacion, acumulado, tipoLayout);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof MontoLayoutConciliacionDTO)) {
			return false;
		}
		final MontoLayoutConciliacionDTO other = (MontoLayoutConciliacionDTO) obj;
		return (this.hashCode() == other.hashCode());

	}


}