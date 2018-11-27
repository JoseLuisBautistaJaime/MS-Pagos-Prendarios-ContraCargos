package mx.com.nmp.pagos.mimonte.builder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import mx.com.nmp.pagos.mimonte.dto.ClienteDTO;
import mx.com.nmp.pagos.mimonte.dto.PagoDTO;
import mx.com.nmp.pagos.mimonte.dto.TarjetaDTO;
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
@Component
public class ClienteBuilder {

	/**
	 * Metodo que construye una lista de Entitidades Cliente desde un set de objetos ClienteDTO
	 * 
	 * @param List de objetos de tipo clientesDTO
	 * @return List de entidad de tipo Cliente
	 */
	public static List<Cliente> buildListClienteFromSetClienteDTO(List<ClienteDTO> clientesDTO) {
		List<Cliente> clientesSetEntity = new ArrayList<>();
		for(ClienteDTO clienteDTO : clientesDTO) {
			clientesSetEntity.add(buildClienteFromClienteDTO(clienteDTO));
		}
		return clientesSetEntity;
	}
	
	/**
	 * Metodo que construye una lista de objetos de tipo ClienteDTO desde un set de entidades de tipo Cliente 
	 * 
	 * @param Lista de entidades de tipo Cliente clientesEntity
	 * @return Lista de objetos de tipo ClienteDTO
	 */
	public static List<ClienteDTO> buildListClienteDTOFromSetCliente(List<Cliente> clientesEntity) {
		List<ClienteDTO> clientesSetDTO = new ArrayList<>();
		for(Cliente cliente : clientesEntity) {
			clientesSetDTO.add(buildClienteDTOFromCliente(cliente));
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
		Cliente clienteEntity = new Cliente();
		clienteEntity.setFechaAlta(clienteDTO.getFechaAlta());
		clienteEntity.setIdcliente(clienteDTO.getIdCliente());
		clienteEntity.setNombreTitular(clienteDTO.getNombreTitular());
		List<Tarjetas> tarjetas = new ArrayList<>();
		clienteEntity.setTarjetas(tarjetas);
		Set<Pago> pagos = new HashSet<>();
		clienteEntity.setPagos(pagos);
		return clienteEntity;
	}

	/**
	 * Metodo que construye un objeto tipo ClienteDTO desde un entity tipo Cliente
	 * 
	 * @param Entity de tipo Cliente clienteEntity
	 * @return Objeto de tipo ClienteDTO
	 */
	public static ClienteDTO buildClienteDTOFromCliente(Cliente clienteEntity) {
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setFechaAlta(clienteEntity.getFechaAlta());
		clienteDTO.setIdCliente((clienteEntity.getIdcliente()));
		clienteDTO.setNombreTitular(clienteEntity.getNombreTitular());
		List<TarjetaDTO> tarjetas = new ArrayList<>();
		tarjetas.add(TarjetaBuilder.builTarjetaDTOFromTrajeta(((Tarjetas) clienteEntity.getTarjetas().toArray()[0])));
		Set<PagoDTO> pagos = new HashSet<>();
		pagos.add(PagoBuilder
				.buildPagoDTO((((Pago) clienteEntity.getPagos().toArray()[0]))));
		return clienteDTO;
	}

}
