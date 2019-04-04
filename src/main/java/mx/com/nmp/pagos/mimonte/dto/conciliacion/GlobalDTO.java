/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @name GlobalDTO
 * @description Clase que encapsula el resumen global de la conciliación.
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net
 * @creationDate 02/04/2019 16:50 hrs.
 * @version 0.1
 */
public class GlobalDTO implements Comparable<GlobalDTO> {

	private Long id;
	private Date fechaOperacion;
	private Long totalMovimientos;
	private Long totalPartidas;
	private BigDecimal importeMidas;
	private BigDecimal importeProveedor;
	private BigDecimal importeBanco;
	private BigDecimal importeDevoluciones;
	private BigDecimal diferenciasProveedorVsMidas;
	private BigDecimal diferenciasProveedorVsBanco;

	public GlobalDTO() {
		super();
	}

	public GlobalDTO(Long id, Date fechaOperacion, Long totalMovimientos, Long totalPartidas, BigDecimal importeMidas,
			BigDecimal importeProveedor, BigDecimal importeBanco, BigDecimal importeDevoluciones,
			BigDecimal diferenciasProveedorVsMidas, BigDecimal diferenciasProveedorVsBanco) {
		super();
		this.id = id;
		this.fechaOperacion = fechaOperacion;
		this.totalMovimientos = totalMovimientos;
		this.totalPartidas = totalPartidas;
		this.importeMidas = importeMidas;
		this.importeProveedor = importeProveedor;
		this.importeBanco = importeBanco;
		this.importeDevoluciones = importeDevoluciones;
		this.diferenciasProveedorVsMidas = diferenciasProveedorVsMidas;
		this.diferenciasProveedorVsBanco = diferenciasProveedorVsBanco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaOperacion() {
		return fechaOperacion;
	}

	public void setFechaOperacion(Date fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}

	public Long getTotalMovimientos() {
		return totalMovimientos;
	}

	public void setTotalMovimientos(Long totalMovimientos) {
		this.totalMovimientos = totalMovimientos;
	}

	public Long getTotalPartidas() {
		return totalPartidas;
	}

	public void setTotalPartidas(Long totalPartidas) {
		this.totalPartidas = totalPartidas;
	}

	public BigDecimal getImporteMidas() {
		return importeMidas;
	}

	public void setImporteMidas(BigDecimal importeMidas) {
		this.importeMidas = importeMidas;
	}

	public BigDecimal getImporteProveedor() {
		return importeProveedor;
	}

	public void setImporteProveedor(BigDecimal importeProveedor) {
		this.importeProveedor = importeProveedor;
	}

	public BigDecimal getImporteBanco() {
		return importeBanco;
	}

	public void setImporteBanco(BigDecimal importeBanco) {
		this.importeBanco = importeBanco;
	}

	public BigDecimal getImporteDevoluciones() {
		return importeDevoluciones;
	}

	public void setImporteDevoluciones(BigDecimal importeDevoluciones) {
		this.importeDevoluciones = importeDevoluciones;
	}

	public BigDecimal getDiferenciasProveedorVsMidas() {
		return diferenciasProveedorVsMidas;
	}

	public void setDiferenciasProveedorVsMidas(BigDecimal diferenciasProveedorVsMidas) {
		this.diferenciasProveedorVsMidas = diferenciasProveedorVsMidas;
	}

	public BigDecimal getDiferenciasProveedorVsBanco() {
		return diferenciasProveedorVsBanco;
	}

	public void setDiferenciasProveedorVsBanco(BigDecimal diferenciasProveedorVsBanco) {
		this.diferenciasProveedorVsBanco = diferenciasProveedorVsBanco;
	}

	@Override
	public String toString() {
		return "GlobalDTO [id=" + id + ", fechaOperacion=" + fechaOperacion + ", totalMovimientos=" + totalMovimientos
				+ ", totalPartidas=" + totalPartidas + ", importeMidas=" + importeMidas + ", importeProveedor="
				+ importeProveedor + ", importeBanco=" + importeBanco + ", importeDevoluciones=" + importeDevoluciones
				+ ", diferenciasProveedorVsMidas=" + diferenciasProveedorVsMidas + ", diferenciasProveedorVsBanco="
				+ diferenciasProveedorVsBanco + "]";
	}

	@Override
	public int compareTo(GlobalDTO o) {
		return 0;
	}

}
