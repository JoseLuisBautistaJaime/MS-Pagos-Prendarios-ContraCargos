package mx.com.nmp.pagos.mimonte.builder;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import mx.com.nmp.pagos.mimonte.dto.ClienteDTO;
import mx.com.nmp.pagos.mimonte.dto.TarjetaDTO;
import mx.com.nmp.pagos.mimonte.dto.TransaccionDTO;
import mx.com.nmp.pagos.mimonte.model.Cliente;
import mx.com.nmp.pagos.mimonte.model.Tarjetas;
import mx.com.nmp.pagos.mimonte.model.Transaccion;

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
	 * Metodo que construye un entity Cliente desde un Objeto tipo ClienteDTO
	 * 
	 * @param Objeto tipo ClienteDTO clienteDTO
	 * @return Entity tipo Cliente
	 */
	public static Cliente buildClienteFromClienteDTO(ClienteDTO clienteDTO) {
		Cliente clienteEntity = new Cliente();
		clienteEntity.setFechaAlta(clienteDTO.getFechaAlta());
		clienteEntity.setIdCliente(clienteDTO.getIdCliente());
		clienteEntity.setNombreTitular(clienteDTO.getNombreTitular());
		Set<Tarjetas> tarjetas = new HashSet<>();
		tarjetas.add(TarjetaBuilder.builTarjetaFromTrajetaDTO((TarjetaDTO) clienteDTO.getTarjetas().toArray()[0]));
		clienteEntity.setTarjetas(tarjetas);
		Set<Transaccion> transacciones = new HashSet<>();
		transacciones.add(TransaccionBuilder
				.buildTransaccionEntity(((TransaccionDTO) clienteDTO.getTransacciones().toArray()[0])));
		clienteEntity.setTransacciones(transacciones);
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
		clienteDTO.setIdCliente(clienteEntity.getIdCliente());
		clienteDTO.setNombreTitular(clienteEntity.getNombreTitular());
		Set<TarjetaDTO> tarjetas = new HashSet<>();
		tarjetas.add(TarjetaBuilder.builTarjetaDTOFromTrajeta(((Tarjetas) clienteEntity.getTarjetas().toArray()[0])));
		clienteDTO.setTarjetas(tarjetas);
		Set<TransaccionDTO> transacciones = new HashSet<>();
		transacciones.add(TransaccionBuilder
				.buildTransaccionDTO((((Transaccion) clienteEntity.getTransacciones().toArray()[0]))));
		return clienteDTO;
	}

}
