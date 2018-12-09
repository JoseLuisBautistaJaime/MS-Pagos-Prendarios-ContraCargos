package mx.com.nmp.pagos.mimonte.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import mx.com.nmp.pagos.mimonte.builder.ClienteBuilder;
import mx.com.nmp.pagos.mimonte.constans.TarjetaConstants;
import mx.com.nmp.pagos.mimonte.dao.ClienteRepository;
import mx.com.nmp.pagos.mimonte.dao.EstatusTarjetaRepository;
import mx.com.nmp.pagos.mimonte.dao.TarjetaRepository;
import mx.com.nmp.pagos.mimonte.dao.TipoTarjetaRepository;
import mx.com.nmp.pagos.mimonte.dto.ClienteDTO;
import mx.com.nmp.pagos.mimonte.dto.EstatusTarjetaDTO;
import mx.com.nmp.pagos.mimonte.dto.TarjeDTO;
import mx.com.nmp.pagos.mimonte.dto.TarjetaDTO;
import mx.com.nmp.pagos.mimonte.dto.TipoTarjetaDTO;
import mx.com.nmp.pagos.mimonte.exception.TarjetaException;
import mx.com.nmp.pagos.mimonte.model.Cliente;
import mx.com.nmp.pagos.mimonte.model.EstatusTarjeta;
import mx.com.nmp.pagos.mimonte.model.Tarjetas;
import mx.com.nmp.pagos.mimonte.model.TipoTarjeta;
import mx.com.nmp.pagos.mimonte.services.TarjetasService;

/**
 * Capa de servicios en la cual esta la logica del manejo de las tarjetas.
 * 
 * @author Quarksoft
 *
 */
@Service
public class TarjetasServiceImpl implements TarjetasService {

	/**
	 * Logger para el registro de actividad en la bitacora
	 */
	private final Logger log = LoggerFactory.getLogger(TarjetasServiceImpl.class);

	@Autowired
	BeanFactory beanFactory;

	@Autowired
	private TarjetaRepository tarjetaRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private TipoTarjetaRepository tipoTarjetaRepository;

	@Autowired
	private EstatusTarjetaRepository estatusTarjetaRepository;

	/**
	 * Metodo que obtiene el registro de la tarjeta.
	 * 
	 * @param idCliente
	 * @return List<TarjeDTO>
	 */
	public List<TarjeDTO> getTarjetasIdCliente(Integer idCliente) {

		// Obtiene los registros
		List<Tarjetas> tarjetasCliente = clienteRepository.findByIdCliente(idCliente);

		List<TarjeDTO> tarjetaCliente = new ArrayList<TarjeDTO>();

		TarjeDTO tarjeDto = null;

		try {

			log.debug("Validando parametro idCliente...");
			if (idCliente != null) {

				if (!CollectionUtils.isEmpty(tarjetasCliente)) {

					for (Tarjetas tarjetaClie : tarjetasCliente) {

						tarjeDto = getTarjetas(tarjetaClie);

						tarjetaCliente.add(tarjeDto);

					}

				} else {

					throw new TarjetaException(TarjetaConstants.MSG_FAILURE);

				}

			} else {

				throw new TarjetaException(TarjetaConstants.MSG_FAIL_PARAMETER);

			}

		} catch (DataAccessException dae) {

			throw new TarjetaException(TarjetaConstants.MSG_FAILURE + dae.getMessage());

		}

		return tarjetaCliente;

	}

	/**
	 * Metodo que obtiene el registro de la tarjeta.
	 * 
	 * @param token
	 * @return TarjeDTO.
	 */
	@Override
	public TarjeDTO getTarjetasToken(String token) {

		// Obtiene los registros
		Tarjetas tarjetasCliente = tarjetaRepository.findByToken(token);

		TarjeDTO tarjeDto = null;

		try {

			if (tarjetasCliente != null) {

				tarjeDto = getTarjetas(tarjetasCliente);

			} else {

				throw new TarjetaException(TarjetaConstants.MSG_FAILURE);

			}

		} catch (DataAccessException dae) {

			throw new TarjetaException(TarjetaConstants.MSG_FAILURE + dae.getMessage());

		}

		return tarjeDto;

	}

	/**
	 * Método que obtiene el registro de la tarjeta.
	 * 
	 * @param token.
	 * @param idCliente.
	 * @return TarjeDTO.
	 */
	@Override
	public TarjeDTO getTarjetasTokenIdCliente(Integer idCliente, String token) {

		// Obtiene los registros
		Tarjetas tarjetasCliente = tarjetaRepository.findByIdclienteAndToken(idCliente, token);

		TarjeDTO tarjeDto = null;

		try {

			if (idCliente != null && !token.isEmpty()) {

				if (tarjetasCliente != null) {

					tarjeDto = getTarjetas(tarjetasCliente);

				} else {

					throw new TarjetaException(TarjetaConstants.MSG_FAILURE);

				}

			} else {

				throw new TarjetaException(TarjetaConstants.MSG_FAIL_PARAMETERS);

			}

		} catch (DataAccessException dae) {

			throw new TarjetaException(TarjetaConstants.MSG_FAILURE + dae.getMessage());

		}

		return tarjeDto;

	}

	/**
	 * Método que registra una tarjeta.
	 * 
	 * @param tarjeta.
	 * @return Tarjetas.
	 */
	@Override
	public Tarjetas addTarjetas(TarjetaDTO tarjeta) {

		Tarjetas tarjetas = new Tarjetas();

		Cliente cliente = addCliente(tarjeta.getCliente());

		TipoTarjeta tipoTarjeta = tipoTarjetaRepository.encontrar(tarjeta.getTipo().getId());
		EstatusTarjeta estatusTarjeta = estatusTarjetaRepository.encontrar(tarjeta.getEstatus().getId());
		Cliente clientes = clienteRepository.encuentraIdCliente(cliente.getIdcliente());
		

		Tarjetas token = tarjetaRepository.findByToken(tarjeta.getToken());

		if (token.getToken() != null) {

			throw new TarjetaException(TarjetaConstants.MSG_FAIL_TOKEN_ALREADY_EXISTS);

		} else {

			if (tarjeta.getEstatus() != null && tarjeta.getEstatus().getId() != null && tarjeta.getTipo() != null
					&& tarjeta.getTipo().getId() != null && tarjeta.getToken() != null) {

				tarjetas.setToken(tarjeta.getToken());
				tarjetas.setUltimosDigitos(tarjeta.getDigitos());
				tarjetas.setAlias(tarjeta.getAlias());
				tarjetas.setFechaAlta(new Date());
				tarjetas.setFechaModificacion(null);
				tarjetas.setClientes(clientes);
//				tarjetas.setClientes(ClienteBuilder.buildClienteFromClienteDTO((tarjeta.getCliente())));
				tarjetas.setTipoTarjeta(tipoTarjeta);
				tarjetas.setEstatusTarjeta(estatusTarjeta);

				tarjetas = tarjetaRepository.save(tarjetas);

			}

			return tarjetas;

		}

	}

	/**
	 * Metodo que actualiza el alias de la tarjeta.
	 * 
	 * @param token.
	 * @param alias.
	 * @return Tarjetas.
	 */
	public Tarjetas updateTarjeta(String token, String alias) {
		// Obtiene los registros
		Tarjetas updateTarjeta = tarjetaRepository.findByToken(token);

		// Evalua si existen registros

		try {

			if (updateTarjeta.getEstatusTarjeta() != null && updateTarjeta.getEstatusTarjeta().getId() != null
					&& updateTarjeta.getTipoTarjeta() != null && updateTarjeta.getTipoTarjeta().getId() != null
					&& updateTarjeta.getToken() != null) {

				updateTarjeta.setToken(updateTarjeta.getToken());
				updateTarjeta.setUltimosDigitos(updateTarjeta.getUltimosDigitos());
				updateTarjeta.setAlias(alias);
				updateTarjeta.setFechaAlta(updateTarjeta.getFechaAlta());
				updateTarjeta.setFechaModificacion(new Date());
				updateTarjeta.setClientes(updateTarjeta.getClientes());
				updateTarjeta.setTipoTarjeta(updateTarjeta.getTipoTarjeta());
				updateTarjeta.setEstatusTarjeta(updateTarjeta.getEstatusTarjeta());

				tarjetaRepository.save(updateTarjeta);

			} else {

				throw new TarjetaException(TarjetaConstants.MSG_NO_SUCCESS_UPDATE_NULL);

			}

		} catch (DataAccessException dae) {

			throw new TarjetaException(TarjetaConstants.MSG_NO_SUCCESS_UPDATE_NULL);

		}

		return updateTarjeta;

	}

	/**
	 * Metodo que elimina el registro de la tarjeta.
	 * 
	 * @param token
	 * @param idCliente
	 * @return void.
	 */
	@Override
	public Tarjetas deleteTarjeta(String token) {

		// Obtiene los registros
		Tarjetas deleteTarjeta = tarjetaRepository.findByToken(token);

		try {

			if (deleteTarjeta.getEstatusTarjeta() != null && deleteTarjeta.getEstatusTarjeta().getId() != null
					&& deleteTarjeta.getTipoTarjeta() != null && deleteTarjeta.getTipoTarjeta().getId() != null
					&& deleteTarjeta.getToken() != null) {

				tarjetaRepository.deleteById(token);

			}

		} catch (DataAccessException dae) {

			throw new TarjetaException(TarjetaConstants.MSG_FAIL_TOKEN);

		}

		return deleteTarjeta;
	}

	/**
	 * Metodo que cuenta la cantidad de tarjetas que tiene un cliente especifico
	 */
	@Override
	public Integer countTarjetasByIdcliente(Integer idCliente) {
		return tarjetaRepository.countTarjetasByIdcliente(idCliente);
	}

	/**
	 * 
	 * Método que recupera el objeto cliente si no existe crea uno nuevo.
	 * 
	 * @param dto
	 * @return cliente
	 */
	public Cliente addCliente(ClienteDTO dto) {

		if (dto == null)
			return null;

		Cliente cliente = clienteRepository.findByIdcliente(dto.getIdCliente());

		List<Tarjetas> tarjetaCliente = tarjetaRepository.encuentraidCliente(dto.getIdCliente());

		if (tarjetaCliente.size() > 3) {

			throw new TarjetaException(TarjetaConstants.MSG_NO_SUCCESS_ADD_MAX_CARDS);

		}

		if (cliente != null) {

			return cliente;

		} else {

			cliente = new Cliente();
			cliente.setIdcliente(dto.getIdCliente() == null ? dto.getIdCliente() : dto.getIdCliente());
			cliente.setNombreTitular(dto.getNombreTitular() == null ? dto.getNombreTitular() : dto.getNombreTitular());
			cliente.setFechaAlta(new Date() == null ? new Date() : new Date());

			cliente = clienteRepository.save(cliente);

		}

		return cliente;

	}

	/**
	 * Método que setea las propiedades de la tarjeta.
	 * 
	 * 
	 * @param tarjetasCliente.
	 * @return TarjeDTO.
	 */
	public TarjeDTO getTarjetas(Tarjetas tarjetasCliente) {

		TarjeDTO tarjeDto = new TarjeDTO();
		EstatusTarjetaDTO estatusTarjeta = new EstatusTarjetaDTO();
		TipoTarjetaDTO tipoTarjeta = new TipoTarjetaDTO();

		estatusTarjeta.setId(tarjetasCliente.getEstatusTarjeta().getId());
		estatusTarjeta.setDescripcion(tarjetasCliente.getEstatusTarjeta().getDescripcion());
		estatusTarjeta.setDescripcionCorta(tarjetasCliente.getEstatusTarjeta().getDescripcionCorta());

		tipoTarjeta.setId(tarjetasCliente.getTipoTarjeta().getId());
		tipoTarjeta.setDescripcion(tarjetasCliente.getTipoTarjeta().getDescripcion());
		tipoTarjeta.setDescripcionCorta(tarjetasCliente.getTipoTarjeta().getDescripcionCorta());

		tarjeDto.setToken(tarjetasCliente.getToken());
		tarjeDto.setDigitos(tarjetasCliente.getUltimosDigitos());
		tarjeDto.setAlias(tarjetasCliente.getAlias());
		tarjeDto.setFechaAlta(tarjetasCliente.getFechaAlta());
		tarjeDto.setFechaModificacion(tarjetasCliente.getFechaModificacion());

		tarjeDto.setTipo(tipoTarjeta);
		tarjeDto.setEstatus(estatusTarjeta);

		return tarjeDto;

	}

}
