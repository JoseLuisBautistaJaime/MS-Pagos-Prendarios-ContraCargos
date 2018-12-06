package mx.com.nmp.pagos.mimonte.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import mx.com.nmp.pagos.mimonte.config.Constants;
import mx.com.nmp.pagos.mimonte.dao.ClienteRepository;
import mx.com.nmp.pagos.mimonte.dao.EstatusTarjetaRepository;
import mx.com.nmp.pagos.mimonte.dao.TarjetaRepository;
import mx.com.nmp.pagos.mimonte.dao.TipoTarjetaRepository;
import mx.com.nmp.pagos.mimonte.dto.ClienteDTO;
import mx.com.nmp.pagos.mimonte.dto.EstatusTarjetaDTO;
import mx.com.nmp.pagos.mimonte.dto.TarjeDTO;
import mx.com.nmp.pagos.mimonte.dto.TarjetaDTO;
import mx.com.nmp.pagos.mimonte.dto.TipoTarjetaDTO;
import mx.com.nmp.pagos.mimonte.model.Cliente;
import mx.com.nmp.pagos.mimonte.model.EstatusTarjeta;
import mx.com.nmp.pagos.mimonte.model.Tarjetas;
import mx.com.nmp.pagos.mimonte.model.TipoTarjeta;
import mx.com.nmp.pagos.mimonte.services.TarjetasService;
import mx.com.nmp.pagos.mimonte.util.Response;

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
	 * @return Response
	 */
	public Response getTarjetasIdCliente(Integer idCliente) {

		// Obtiene los registros
		List<Tarjetas> tarjetasCliente = clienteRepository.findByIdCliente(idCliente);

		List<TarjeDTO> tarClie = new ArrayList<TarjeDTO>();
		TarjeDTO tar = new TarjeDTO();
		EstatusTarjetaDTO esta = new EstatusTarjetaDTO();
		TipoTarjetaDTO tip = new TipoTarjetaDTO();
		// Evalua si existen registros
		if (!CollectionUtils.isEmpty(tarjetasCliente)) {

			for (Tarjetas tarjetaClie : tarjetasCliente) {

				esta = new EstatusTarjetaDTO();
				tip = new TipoTarjetaDTO();

				esta.setId(tarjetaClie.getEstatusTarjeta().getId());
				esta.setDescripcion(tarjetaClie.getEstatusTarjeta().getDescripcion());
				esta.setDescripcionCorta(tarjetaClie.getEstatusTarjeta().getDescripcionCorta());

				tip.setId(tarjetaClie.getTipoTarjeta().getId());
				tip.setDescripcion(tarjetaClie.getTipoTarjeta().getDescripcion());
				tip.setDescripcionCorta(tarjetaClie.getTipoTarjeta().getDescripcionCorta());

				tar = new TarjeDTO();
				tar.setToken(tarjetaClie.getToken());
				tar.setDigitos(tarjetaClie.getUltimosDigitos());
				tar.setAlias(tarjetaClie.getAlias());
				tar.setFechaAlta(tarjetaClie.getFechaAlta());
				tar.setFechaModificacion(tarjetaClie.getFechaModificacion());
				tar.setTipo(tip);
				tar.setEstatus(esta);
				tarClie.add(tar);

			}

			return beanFactory.getBean(Response.class, HttpStatus.OK.name(), Constants.MSG_SUCCESS, tarClie);

		} else {

			return beanFactory.getBean(Response.class, HttpStatus.BAD_REQUEST.name(), Constants.MSG_FAILURE, tarClie);

		}

	}

	/**
	 * Metodo que obtiene el registro de la tarjeta.
	 * 
	 * @param token
	 * @return Response
	 */
	@Override
	public Response getTarjetasToken(String token) {

		// Obtiene los registros
		Tarjetas tarjetasCliente = tarjetaRepository.findByToken(token);
		TarjeDTO tar = new TarjeDTO();
		EstatusTarjetaDTO esta = new EstatusTarjetaDTO();
		TipoTarjetaDTO tip = new TipoTarjetaDTO();

		// Evalua si existen registros
		if (tarjetasCliente != null) {

			esta = new EstatusTarjetaDTO();
			tip = new TipoTarjetaDTO();

			esta.setId(tarjetasCliente.getEstatusTarjeta().getId());
			esta.setDescripcion(tarjetasCliente.getEstatusTarjeta().getDescripcion());
			esta.setDescripcionCorta(tarjetasCliente.getEstatusTarjeta().getDescripcionCorta());

			tip.setId(tarjetasCliente.getTipoTarjeta().getId());
			tip.setDescripcion(tarjetasCliente.getTipoTarjeta().getDescripcion());
			tip.setDescripcionCorta(tarjetasCliente.getTipoTarjeta().getDescripcionCorta());

			tar = new TarjeDTO();
			tar.setToken(tarjetasCliente.getToken());
			tar.setDigitos(tarjetasCliente.getUltimosDigitos());
			tar.setAlias(tarjetasCliente.getAlias());
			tar.setFechaAlta(tarjetasCliente.getFechaAlta());
			tar.setFechaModificacion(tarjetasCliente.getFechaModificacion());
			tar.setTipo(tip);
			tar.setEstatus(esta);

			return beanFactory.getBean(Response.class, HttpStatus.OK.name(), Constants.MSG_SUCCESS, tar);

		} else {

			return beanFactory.getBean(Response.class, HttpStatus.BAD_REQUEST.name(), Constants.MSG_FAILURE, tarjetasCliente);

		}

	}

	/**
	 * Metodo que obtiene el registro de la tarjeta.
	 * 
	 * @param token
	 * @param idCliente
	 * @return Response
	 */
	@Override
	public Response getTarjetasTokenIdCliente(Integer idCliente, String token) {

		// Obtiene los registros
		Tarjetas tarjetasCliente = tarjetaRepository.findByIdclienteAndToken(idCliente, token);
		TarjeDTO tar = new TarjeDTO();
		EstatusTarjetaDTO esta = new EstatusTarjetaDTO();
		TipoTarjetaDTO tip = new TipoTarjetaDTO();

		// Evalua si existen registros
		if (tarjetasCliente != null) {

			esta = new EstatusTarjetaDTO();
			tip = new TipoTarjetaDTO();

			esta.setId(tarjetasCliente.getEstatusTarjeta().getId());
			esta.setDescripcion(tarjetasCliente.getEstatusTarjeta().getDescripcion());
			esta.setDescripcionCorta(tarjetasCliente.getEstatusTarjeta().getDescripcionCorta());

			tip.setId(tarjetasCliente.getTipoTarjeta().getId());
			tip.setDescripcion(tarjetasCliente.getTipoTarjeta().getDescripcion());
			tip.setDescripcionCorta(tarjetasCliente.getTipoTarjeta().getDescripcionCorta());

			tar = new TarjeDTO();
			tar.setToken(tarjetasCliente.getToken());
			tar.setDigitos(tarjetasCliente.getUltimosDigitos());
			tar.setAlias(tarjetasCliente.getAlias());
			tar.setFechaAlta(tarjetasCliente.getFechaAlta());
			tar.setFechaModificacion(tarjetasCliente.getFechaModificacion());
			tar.setTipo(tip);
			tar.setEstatus(esta);

			return beanFactory.getBean(Response.class, HttpStatus.OK.name(), Constants.MSG_SUCCESS, tar);

		} else {

			return beanFactory.getBean(Response.class, HttpStatus.BAD_REQUEST.name(), Constants.MSG_FAILURE, tar);

		}

	}

	/**
	 * Método que registra una tarjeta.
	 * 
	 * @param tarjeta.
	 * @return Response.
	 */
	@Override
	public Response addTarjetas(TarjetaDTO tarjeta) {

		Tarjetas tarjetas = new Tarjetas();

		Cliente cliente = addCliente(tarjeta.getCliente());

		TipoTarjeta tipoTarjeta = tipoTarjetaRepository.encontrar(tarjeta.getTipo().getId());
		EstatusTarjeta estatusTarjeta = estatusTarjetaRepository.encontrar(tarjeta.getEstatus().getId());

		if (tarjeta.getEstatus() != null && tarjeta.getEstatus().getId() != null && tarjeta.getTipo() != null
				&& tarjeta.getTipo().getId() != null && tarjeta.getToken() != null) {

			tarjetas.setToken(tarjeta.getToken());
			tarjetas.setUltimosDigitos(tarjeta.getDigitos());
			tarjetas.setAlias(tarjeta.getAlias());
			tarjetas.setFechaAlta(new Date());
			tarjetas.setFechaModificacion(null);
			tarjetas.setIdcliente(cliente.getIdcliente());
			tarjetas.setTipoTarjeta(tipoTarjeta);
			tarjetas.setEstatusTarjeta(estatusTarjeta);

			tarjetas = tarjetaRepository.save(tarjetas);

			return beanFactory.getBean(Response.class, HttpStatus.OK.name(), Constants.MSG_SUCCESS, tarjetas);

		} else {

			return beanFactory.getBean(Response.class, HttpStatus.BAD_REQUEST.name(), Constants.MSG_FAILURE, tarjetas);

		}

	}

	/**
	 * Metodo que actualiza el alias de la tarjeta.
	 * 
	 * @param token.
	 * @param alias.
	 * @return Response.
	 */
	@Override
	public Response updateTarjeta(String token, String alias) {
		// Obtiene los registros
		Tarjetas updateTarjeta = tarjetaRepository.findByToken(token);

		// Evalua si existen registros
		if (updateTarjeta != null) {

			updateTarjeta.setToken(updateTarjeta.getToken());
			updateTarjeta.setUltimosDigitos(updateTarjeta.getUltimosDigitos());
			updateTarjeta.setAlias(alias);
			updateTarjeta.setFechaAlta(updateTarjeta.getFechaAlta());
			updateTarjeta.setFechaModificacion(new Date());
			updateTarjeta.setIdcliente(updateTarjeta.getIdcliente());
			updateTarjeta.setTipoTarjeta(updateTarjeta.getTipoTarjeta());
			updateTarjeta.setEstatusTarjeta(updateTarjeta.getEstatusTarjeta());

			tarjetaRepository.save(updateTarjeta);

			return beanFactory.getBean(Response.class, HttpStatus.OK.name(), Constants.MSG_SUCCESS_UPDATE, updateTarjeta);

		} else {

			return beanFactory.getBean(Response.class, HttpStatus.BAD_REQUEST.name(), Constants.MSG_NO_SUCCESS_UPDATE, updateTarjeta);

		}
	}

	/**
	 * Metodo que elimina el registro de la tarjeta.
	 * 
	 * @param token
	 * @param idCliente
	 * @return Response
	 */
	@Override
	public Response deleteTarjeta(String token) {

		Response response = null;

		// Obtiene los registros
		tarjetaRepository.deleteById(token);

		if (token != null) {

			return beanFactory.getBean(Response.class, HttpStatus.OK.name(), Constants.MSG_SUCCESS_DELETE, response);

		} else {

			return beanFactory.getBean(Response.class, HttpStatus.BAD_REQUEST.name(), Constants.MSG_NO_SUCCESS_DELETE,
					response);

		}
	}

	@Override
	public int countTarjetasByIdCliente(Integer idCliente) {
		// return tarjetaRepository.countByClientes(idCliente);
		return 0;
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
}
