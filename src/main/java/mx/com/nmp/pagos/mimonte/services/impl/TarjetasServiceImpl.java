package mx.com.nmp.pagos.mimonte.services.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
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
import mx.com.nmp.pagos.mimonte.dto.EstatusTarjetaDTO;
import mx.com.nmp.pagos.mimonte.dto.TarjeDTO;
import mx.com.nmp.pagos.mimonte.dto.TarjetaDTO;
import mx.com.nmp.pagos.mimonte.dto.TipoTarjetaDTO;
import mx.com.nmp.pagos.mimonte.model.Tarjetas;
import mx.com.nmp.pagos.mimonte.services.TarjetasService;
import mx.com.nmp.pagos.mimonte.util.Response;

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

		List<Tarjetas> tarClie = new ArrayList<Tarjetas>();

		// Evalua si existen registros
		if (!CollectionUtils.isEmpty(tarjetasCliente)) {

			for (Tarjetas tarjetaClie : tarjetasCliente) {

				tarjetaClie.setToken(tarjetaClie.getToken());
				tarjetaClie.setUltimosDigitos(tarjetaClie.getUltimosDigitos());
				tarjetaClie.setAlias(tarjetaClie.getAlias());
				tarjetaClie.setFechaAlta(tarjetaClie.getFechaAlta());
				tarjetaClie.setFechaModificacion(tarjetaClie.getFechaModificacion());
				tarjetaClie.setTipoTarjeta(tarjetaClie.getTipoTarjeta());
				tarjetaClie.setEstatusTarjeta(tarjetaClie.getEstatusTarjeta());
				tarClie.add(tarjetaClie);

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

		// Evalua si existen registros
		if (tarjetasCliente != null) {

			return beanFactory.getBean(Response.class, HttpStatus.OK.name(), Constants.MSG_SUCCESS, tarjetasCliente);

		} else {

			return beanFactory.getBean(Response.class, HttpStatus.BAD_REQUEST.name(), Constants.MSG_FAILURE,
					tarjetasCliente);

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
		Tarjetas tarjetasCliente = tarjetaRepository.findByIdClienteAndToken(idCliente, token);

		// Evalua si existen registros
		if (tarjetasCliente != null) {

			return beanFactory.getBean(Response.class, HttpStatus.OK.name(), Constants.MSG_SUCCESS, tarjetasCliente);

		} else {

			return beanFactory.getBean(Response.class, HttpStatus.BAD_REQUEST.name(), Constants.MSG_FAILURE,
					tarjetasCliente);

		}

	}

	
	/**
	 * Metodo que registra una tarjeta.
	 * 
	 * @param tarjeta.
	 * @return Response.
	 */
	@Override
	public Response addTarjetas(TarjetaDTO tarjeta) {

		Tarjetas guarda = new Tarjetas();

		if (tarjeta.getCliente() != null && (tarjeta.getTipo() != null && tarjeta.getTipo().getId() != null)
				&& (tarjeta.getEstatus() != null && tarjeta.getEstatus().getId() != null)
				&& (tarjeta.getCliente() != null && tarjeta.getCliente().getIdCliente() != null
						&& tarjeta.getCliente().getIdCliente() > 0)
				&& (tarjeta.getTipo() != null && tarjeta.getTipo().getId() != null && tarjeta.getTipo().getId() > 0)
				&& tarjeta.getEstatus() != null && tarjeta.getEstatus().getId() != null
				&& tarjeta.getEstatus().getId() > 0) {

			try {
				BeanUtils.copyProperties(guarda, tarjeta);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			 tipoTarjetaRepository.findAll();
			 estatusTarjetaRepository.findAll();
			
//			guarda.setIdCliente(null);
//			guarda.setIdCliente(tarjeta.getCliente().getIdCliente());
//			guarda.setTipoTarjeta(tipoTarjeta.get(0).getId());
//			guarda.setEstatusTarjeta(tipoTarjeta.get(0).getId());
			guarda = tarjetaRepository.save(guarda);

			return beanFactory.getBean(Response.class, HttpStatus.OK.name(), Constants.MSG_SUCCESS, guarda);

		} else {

			return beanFactory.getBean(Response.class, HttpStatus.BAD_REQUEST.name(), Constants.MSG_FAILURE, guarda);

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

		Response response = null;

		// Obtiene los registros
		Tarjetas updateTarjeta = tarjetaRepository.findByToken(token);
		
		

		if (updateTarjeta != null) {
			
//			updateTarjeta.set(0, alias);
//
//			tarjetaRepository.save(updateTarjeta.get(0).getAlias());

			return beanFactory.getBean(Response.class, HttpStatus.OK.name(), Constants.MSG_SUCCESS_UPDATE, response);

		} else {

			return beanFactory.getBean(Response.class, HttpStatus.BAD_REQUEST.name(), Constants.MSG_NO_SUCCESS_UPDATE, response);

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
		Tarjetas deleteTarjeta = tarjetaRepository.findByToken(token);
		
		List<Tarjetas> tarjeta = new ArrayList<Tarjetas>();

		if (deleteTarjeta != null) {
			
			tarjetaRepository.deleteById(token);

			return beanFactory.getBean(Response.class, HttpStatus.OK.name(), Constants.MSG_SUCCESS_DELETE, response);

		} else {

			return beanFactory.getBean(Response.class, HttpStatus.BAD_REQUEST.name(), Constants.MSG_NO_SUCCESS_DELETE, response);

		}
	}

	@Override
	public int countTarjetasByIdCliente(Integer idCliente) {
		//return tarjetaRepository.countByClientes(idCliente);
		return 0;
	}
}
