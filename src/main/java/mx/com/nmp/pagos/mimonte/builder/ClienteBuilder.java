package mx.com.nmp.pagos.mimonte.builder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import mx.com.nmp.pagos.mimonte.dto.ClienteDTO;
import mx.com.nmp.pagos.mimonte.model.Cliente;
import mx.com.nmp.pagos.mimonte.model.Pago;
import mx.com.nmp.pagos.mimonte.model.Tarjetas;

/**
 * Nombre: ClienteBuilder
 * Descripcion: Builder que se encaraga de fabricar
 * objetos entity a partir de objetos DTO y viceversa
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @CreationDate 21/11/2018 18:07 hrs.
 * @version 0.1
 */
public class ClienteBuilder {

	private ClienteBuilder() {
		/**
		 * hidden constructor
		 */
	}

	/**
	 * Metodo que construye una lista de Entitidades Cliente desde un set de objetos
	 * ClienteDTO
	 * 
	 * @param List de objetos de tipo clientesDTO
	 * @return List de entidad de tipo Cliente
	 */
	public static List<Cliente> buildListClienteFromSetClienteDTO(List<ClienteDTO> clientesDTOList) {
		List<Cliente> clientesSetEntity = null;
		if (null != clientesDTOList && !clientesDTOList.isEmpty()) {
			clientesSetEntity = new ArrayList<>();
			for (ClienteDTO clienteDTO : clientesDTOList) {
				clientesSetEntity.add(buildClienteFromClienteDTO(clienteDTO));
			}
		}
		return clientesSetEntity;
	}

	/**
	 * Metodo que construye una lista de objetos de tipo ClienteDTO desde un set de
	 * entidades de tipo Cliente
	 * 
	 * @param Lista de entidades de tipo Cliente clientesEntity
	 * @return Lista de objetos de tipo ClienteDTO
	 */
	public static List<ClienteDTO> buildListClienteDTOFromSetCliente(List<Cliente> clientesEntityList) {
		List<ClienteDTO> clientesSetDTO = null;
		if (null != clientesEntityList && !clientesEntityList.isEmpty()) {
			clientesSetDTO = new ArrayList<>();
			for (Cliente cliente : clientesEntityList) {
				clientesSetDTO.add(buildClienteDTOFromCliente(cliente));
			}
		}
		return clientesSetDTO;
	}

	/**
	 * Metodo que construye un entity Cliente desde un Objeto tipo ClienteDTO
	 * 
	 * @param Objeto tipo ClienteDTO clienteDTO
	 * @return Entity tipo Cliente
	 */
	public static Cliente buildClienteFromClienteDTO(ClienteDTO clienteDTO) {
		Cliente clienteEntity = null;
		if (null != clienteDTO) {
			clienteEntity = new Cliente();
			clienteEntity.setFechaAlta(clienteDTO.getFechaAlta());
			clienteEntity.setIdcliente(clienteDTO.getIdCliente());
			clienteEntity.setNombreTitular(clienteDTO.getNombreTitular());
			List<Tarjetas> tarjetas = new ArrayList<>();
			clienteEntity.setTarjetas(tarjetas);
			Set<Pago> pagos = new HashSet<>();
			clienteEntity.setPagos(pagos);
		}
		return clienteEntity;
	}

	/**
	 * Metodo que construye un objeto tipo ClienteDTO desde un entity tipo Cliente
	 * 
	 * @param Entity de tipo Cliente clienteEntity
	 * @return Objeto de tipo ClienteDTO
	 */
	public static ClienteDTO buildClienteDTOFromCliente(Cliente clienteEntity) {
		ClienteDTO clienteDTO = null;
		if (null != clienteEntity) {
			clienteDTO = new ClienteDTO();
			clienteDTO.setFechaAlta(clienteEntity.getFechaAlta());
			clienteDTO.setIdCliente((clienteEntity.getIdcliente()));
			clienteDTO.setNombreTitular(clienteEntity.getNombreTitular());
		}
		return clienteDTO;
	}

	/**
	 * 
	 * Metodo que construye un Set de objetos de tipo ClienteDTO desde un Set de
	 * entitys tipo Cliente
	 * 
	 * @param clienteEntitySet
	 * @return
	 */
	public static Set<ClienteDTO> buildClienteDTOFromClienteSet(Set<Cliente> clienteEntitySet) {
		Set<ClienteDTO> clienteDTOSet = null;
		if (null != clienteEntitySet && !clienteEntitySet.isEmpty()) {
			clienteDTOSet = new TreeSet<ClienteDTO>();
			Iterator<Cliente> it = clienteEntitySet.iterator();
			while (it.hasNext()) {
				clienteDTOSet.add(buildClienteDTOFromCliente(it.next()));
			}
		}
		return clienteDTOSet;
	}

}
