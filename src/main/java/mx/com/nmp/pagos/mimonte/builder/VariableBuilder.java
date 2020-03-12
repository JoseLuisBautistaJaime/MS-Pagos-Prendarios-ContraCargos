package mx.com.nmp.pagos.mimonte.builder;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import mx.com.nmp.pagos.mimonte.dto.VariableDTO;
import mx.com.nmp.pagos.mimonte.model.Variable;

/**
 * Nombre: VariableBuilder
 * Descripcion: Builder que se encaraga de fabricar
 * objetos entity a partir de objetos DTO y viceversa
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @CreationDate 17/12/2018 15:17 hrs.
 * @version 0.1
 */
public class VariableBuilder {

	private VariableBuilder() {
		/**
		 * hidden constructor
		 */
	}

	/**
	 * 
	 * Metodo que construye un objeto de tipo VariableDTO desde un objeto de tipo
	 * Variable
	 * 
	 * @param variable
	 * @return
	 */
	public static VariableDTO buildVariableDTOFromVariable(Variable variable) {
		VariableDTO variableDTO = null;
		if (null != variable) {
			variableDTO = new VariableDTO();
			variableDTO.setClave(variable.getClave());
			variableDTO.setIdVariable(variable.getIdVariable());
			variableDTO.setValor(variable.getValor());
		}
		return variableDTO;
	}

	/**
	 * 
	 * Metodo que construye un Set de objetos tipo VariableDTO desde un Set de
	 * entitys tipo Variable
	 * 
	 * @param variable
	 * @return
	 */
	public static Set<VariableDTO> buildVariableDTOFromVariableSet(Set<Variable> variableSet) {
		Set<VariableDTO> variableDTOSet = null;
		if (null != variableSet && !variableSet.isEmpty()) {
			variableDTOSet = new TreeSet<VariableDTO>();
			Iterator<Variable> it = variableSet.iterator();
			while (it.hasNext()) {
				variableDTOSet.add(buildVariableDTOFromVariable(it.next()));
			}
		}
		return variableDTOSet;
	}

}
