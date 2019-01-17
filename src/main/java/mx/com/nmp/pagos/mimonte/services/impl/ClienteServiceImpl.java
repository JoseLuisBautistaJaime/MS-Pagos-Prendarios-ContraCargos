package mx.com.nmp.pagos.mimonte.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.nmp.pagos.mimonte.builder.ClienteBuilder;
import mx.com.nmp.pagos.mimonte.dao.ClienteRepository;
import mx.com.nmp.pagos.mimonte.dto.ClienteDTO;
import mx.com.nmp.pagos.mimonte.model.Cliente;
import mx.com.nmp.pagos.mimonte.services.ClienteService;

/**
 * Nombre: ClienteServiceImpl
 * Descripcion: Implementacion que define las
 * operaciones encargadas de realizar consultas sobre la entidad Cliente
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 27/11/2018 11:08 hrs.
 * @version 0.1
 */
@Service("clienteServiceImpl")
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	ClienteRepository clienteRepository;

	/**
	 * Metodo que regresa un objeto ClienteDTO en base a un id de cliente recibido
	 * como parametro
	 * 
	 */
	@Override
	public ClienteDTO getClienteById(Integer idcliente){
		Cliente cliente = clienteRepository.findByIdcliente(idcliente);
		ClienteDTO clienteDTO = ClienteBuilder.buildClienteDTOFromCliente(cliente);
		return clienteDTO;
	}

	@Override
	public ClienteDTO saveCliente(ClienteDTO clienteDTO) {
		Cliente cliente = ClienteBuilder.buildClienteFromClienteDTO(clienteDTO);
		ClienteDTO clienteDTORet = ClienteBuilder.buildClienteDTOFromCliente(clienteRepository.save(cliente));
		return clienteDTORet;
	}

}
