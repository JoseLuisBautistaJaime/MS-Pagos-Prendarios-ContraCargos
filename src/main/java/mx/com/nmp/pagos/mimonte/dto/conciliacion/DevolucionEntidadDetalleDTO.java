package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.math.BigDecimal;
import java.util.Date;

import mx.com.nmp.pagos.mimonte.dto.BaseEntidadDTO;

public class DevolucionEntidadDetalleDTO {
	
	private Integer id;
	private Long idEntidad;
	private String nombreEntidad;
	private String descripcionEntidad;
	private Date fecha;
	private Integer idEstatus;
	private String descripcionEstatus;
	private Boolean estatus;
	private Integer sucursal;
	private String identificadorCuenta;
	private BigDecimal monto;
	private String esquemaTarjeta;
	private String titular;
	private String codigoAutorizacion;
	private Date fechaLiquidacion;
	
	public DevolucionEntidadDetalleDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DevolucionEntidadDetalleDTO(Integer id, Long idEntidad, String nombreEntidad, String descripcionEntidad,
			Date fecha, Integer idEstatus, String descripcionEstatus, Boolean estatus, Integer sucursal,
			String identificadorCuenta, BigDecimal monto, String esquemaTarjeta, String titular,
			String codigoAutorizacion, Date fechaLiquidacion) {
		super();
		this.id = id;
		this.idEntidad = idEntidad;
		this.nombreEntidad = nombreEntidad;
		this.descripcionEntidad = descripcionEntidad;
		this.fecha = fecha;
		this.idEstatus = idEstatus;
		this.descripcionEstatus = descripcionEstatus;
		this.estatus = estatus;
		this.sucursal = sucursal;
		this.identificadorCuenta = identificadorCuenta;
		this.monto = monto;
		this.esquemaTarjeta = esquemaTarjeta;
		this.titular = titular;
		this.codigoAutorizacion = codigoAutorizacion;
		this.fechaLiquidacion = fechaLiquidacion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getIdEntidad() {
		return idEntidad;
	}

	public void setIdEntidad(Long idEntidad) {
		this.idEntidad = idEntidad;
	}

	public String getNombreEntidad() {
		return nombreEntidad;
	}

	public void setNombreEntidad(String nombreEntidad) {
		this.nombreEntidad = nombreEntidad;
	}

	public String getDescripcionEntidad() {
		return descripcionEntidad;
	}

	public void setDescripcionEntidad(String descripcionEntidad) {
		this.descripcionEntidad = descripcionEntidad;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getIdEstatus() {
		return idEstatus;
	}

	public void setIdEstatus(Integer idEstatus) {
		this.idEstatus = idEstatus;
	}

	public String getDescripcionEstatus() {
		return descripcionEstatus;
	}

	public void setDescripcionEstatus(String descripcionEstatus) {
		this.descripcionEstatus = descripcionEstatus;
	}

	public Boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public Integer getSucursal() {
		return sucursal;
	}

	public void setSucursal(Integer sucursal) {
		this.sucursal = sucursal;
	}

	public String getIdentificadorCuenta() {
		return identificadorCuenta;
	}

	public void setIdentificadorCuenta(String identificadorCuenta) {
		this.identificadorCuenta = identificadorCuenta;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public String getEsquemaTarjeta() {
		return esquemaTarjeta;
	}

	public void setEsquemaTarjeta(String esquemaTarjeta) {
		this.esquemaTarjeta = esquemaTarjeta;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public String getCodigoAutorizacion() {
		return codigoAutorizacion;
	}

	public void setCodigoAutorizacion(String codigoAutorizacion) {
		this.codigoAutorizacion = codigoAutorizacion;
	}

	public Date getFechaLiquidacion() {
		return fechaLiquidacion;
	}

	public void setFechaLiquidacion(Date fechaLiquidacion) {
		this.fechaLiquidacion = fechaLiquidacion;
	}

	@Override
	public String toString() {
		return "DevolucionEntidadDetalleDTO [id=" + id + ", idEntidad=" + idEntidad + ", nombreEntidad=" + nombreEntidad
				+ ", descripcionEntidad=" + descripcionEntidad + ", fecha=" + fecha + ", idEstatus=" + idEstatus
				+ ", descripcionEstatus=" + descripcionEstatus + ", estatus=" + estatus + ", sucursal=" + sucursal
				+ ", identificadorCuenta=" + identificadorCuenta + ", monto=" + monto + ", esquemaTarjeta="
				+ esquemaTarjeta + ", titular=" + titular + ", codigoAutorizacion=" + codigoAutorizacion
				+ ", fechaLiquidacion=" + fechaLiquidacion + "]";
	}

}
