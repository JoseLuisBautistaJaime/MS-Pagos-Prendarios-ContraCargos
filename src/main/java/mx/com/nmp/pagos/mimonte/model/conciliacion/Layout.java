package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the to_layout database table.
 * 
 */
@Entity
@Table(name="to_layout")
public class Layout implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="id_conciliacion")
	private Long idConciliacion;

	@Column(name="tipo")
	private String tipo;

	@OneToOne(mappedBy="layout")
	private LayoutHeader layoutHeader;

	@OneToMany(mappedBy="layout", cascade = {CascadeType.MERGE})
	private List<LayoutLinea> layoutLineas;

	public Layout() {
		super();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdConciliacion() {
		return this.idConciliacion;
	}

	public void setIdConciliacion(Long idConciliacion) {
		this.idConciliacion = idConciliacion;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public LayoutHeader getLayoutHeader() {
		return this.layoutHeader;
	}

	public void setLayoutHeader(LayoutHeader layoutHeader) {
		this.layoutHeader = layoutHeader;
	}

	public List<LayoutLinea> getLayoutLineas() {
		return this.layoutLineas;
	}

	public void setLayoutLineas(List<LayoutLinea> layoutLineas) {
		this.layoutLineas = layoutLineas;
	}

	public LayoutLinea addLayoutLinea(LayoutLinea layoutLinea) {
		getLayoutLineas().add(layoutLinea);
		layoutLinea.setLayout(this);

		return layoutLinea;
	}

	public LayoutLinea removeLayoutLinea(LayoutLinea layoutLinea) {
		getLayoutLineas().remove(layoutLinea);
		layoutLinea.setLayout(null);

		return layoutLinea;
	}

	@Override
	public String toString() {
		return "Layout{" +
				"id=" + id +
				", idConciliacion=" + idConciliacion +
				", tipo='" + tipo + '\'' +
				", layoutHeader=" + layoutHeader +
				", layoutLineas=" + layoutLineas.size() +
				'}';
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idConciliacion == null) ? 0 : idConciliacion.hashCode());
		result = prime * result + ((layoutHeader == null) ? 0 : layoutHeader.hashCode());
		result = prime * result + ((layoutLineas == null) ? 0 : layoutLineas.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}
	
}