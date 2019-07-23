/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto;

/**
 * Nombre: TipoContactoReqDTO Descripcion: Clase que encapsula la informacion perteneciente a un contacto.
 *
 * @author José Rodriguez jgrodriguez@quarksoft.net Fecha: 06/03/2019 21:11 hrs.
 * @version 0.1
 */
public class TipoContactoReqDTO {
	
	private Long id;
	
	public TipoContactoReqDTO() {
		super();
	}

	public TipoContactoReqDTO(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "TipoContactoReqDTO [id=" + id + "]";
	}
}
