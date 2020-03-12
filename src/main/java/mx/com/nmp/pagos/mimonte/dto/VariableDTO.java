package mx.com.nmp.pagos.mimonte.dto;

/**
 * Nombre: VariableDTO
 * Descripcion: Objeto que representa una variable de base
 * de datos dentro del sistema.
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 17/12/2018 15:00 hrs.
 * @version 0.1
 */
public class VariableDTO implements Comparable<VariableDTO> {

	private Integer idVariable;
	private String clave;
	private String valor;

	public VariableDTO() {
		super();
	}

	public VariableDTO(Integer idVariable, String clave, String valor) {
		super();
		this.idVariable = idVariable;
		this.clave = clave;
		this.valor = valor;
	}

	public Integer getIdVariable() {
		return idVariable;
	}

	public void setIdVariable(Integer idVariable) {
		this.idVariable = idVariable;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "VariableDTO [idVariable=" + idVariable + ", clave=" + clave + ", valor=" + valor + "]";
	}

	@Override
	public int compareTo(VariableDTO o) {
		int val = 0;
		if(this.idVariable > o.getIdVariable())
			val = 1;
		else
			val = -1;
		return val;
	}

}
