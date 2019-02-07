package mx.com.nmp.pagos.mimonte.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import mx.com.nmp.pagos.mimonte.constans.PagoConstants;
import mx.com.nmp.pagos.mimonte.constans.TarjetaConstants;
import mx.com.nmp.pagos.mimonte.controllers.TarjetasController;
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
import mx.com.nmp.pagos.mimonte.exception.TarjetaIdentifierException;
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

	@Value(PagoConstants.MAXIMUM_AMOUNT_OF_CARDS_PROPERTY)
	private Integer MAXIMUM_AMOUNT_OF_CARDS_PER_CLIENT;

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
	public List<TarjeDTO> getTarjetasIdCliente(Long idCliente) {

		if (idCliente == null || idCliente < 1)
			throw new TarjetaException(TarjetaConstants.MSG_FAIL_PARAMETER);

		// Obtiene los registros
		List<Tarjetas> tarjetasCliente = clienteRepository.findByIdCliente(idCliente);

		if (CollectionUtils.isEmpty(tarjetasCliente))
			throw new TarjetaException(TarjetaConstants.MSG_FAILURE);

		List<TarjeDTO> tarjetaCliente = new ArrayList<TarjeDTO>();

		TarjeDTO tarjeDto = null;

		for (Tarjetas tarjetaClie : tarjetasCliente) {

			tarjeDto = getTarjetas(tarjetaClie);

			tarjetaCliente.add(tarjeDto);

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
	public TarjeDTO getTarjetasToken(String id_openpay) {

		if (id_openpay == null || id_openpay.equals(""))
			throw new TarjetaException(TarjetaConstants.MSG_FAILURE);

		// Obtiene los registros
		Tarjetas tarjetasCliente = tarjetaRepository.findByIdOpenPay(id_openpay);

		if (tarjetasCliente == null)
			throw new TarjetaException(TarjetaConstants.MSG_FAIL_TOKEN);

		TarjeDTO tarjeDto = null;

		if (tarjetasCliente != null) {

			tarjeDto = getTarjetas(tarjetasCliente);

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
	public TarjeDTO getTarjetasTokenIdCliente(Long idCliente, String id_openpay) {

		if (idCliente == null || idCliente < 1)
			throw new TarjetaException(TarjetaConstants.MSG_FAIL_PARAMETER_IDCLIENTE);

		if (id_openpay == null || id_openpay.isEmpty())
			throw new TarjetaException(TarjetaConstants.MSG_FAILURE_TOKEN);

		// Obtiene los registros
		Tarjetas tarjetasCliente = tarjetaRepository.findByIdclienteAndIdOpenPay(idCliente, id_openpay);

		if (tarjetasCliente == null)
			throw new TarjetaException(TarjetaConstants.MSG_FAILURES);

		TarjeDTO tarjeDto = null;

		if (tarjetasCliente != null) {

			tarjeDto = getTarjetas(tarjetasCliente);

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
	public Tarjetas addTarjetas(TarjetaDTO tarjeta) throws TarjetaException{

		if (tarjeta.getAlias() == null || tarjeta.getAlias().isEmpty())
			throw new TarjetaException(TarjetaConstants.MSG_FAIL_PARAMETERS_ALIAS_SHOULD_NOT_BE_NULL_OR_VOID);
		
		if(tarjeta.getAlias().length() > 10)
			throw new TarjetaException(TarjetaConstants.MSG_FAILURE_ALIAS_EXCEED_DIGITS);
		
		if(tarjeta.getAlias().matches(TarjetaConstants.SYMBOL) == false)
			throw new TarjetaException(TarjetaConstants.MSG_WITHOUT_SYMBOLS);

		if (tarjeta.getId_openpay() == null || tarjeta.getId_openpay().isEmpty())
			throw new TarjetaException(TarjetaConstants.MSG_FAIL_TOKEN_NULL_OR_VOID);
		
		if(tarjeta.getDigitos() == null || tarjeta.getDigitos().length() < 4 || tarjeta.getDigitos().length() > 4)
			throw new TarjetaException(TarjetaConstants.MSG_FAIL_DIGITS_NULL_OR_VOID);		

		if (tarjeta.getCliente() == null)
			throw new TarjetaException(TarjetaConstants.MSG_FAIL_CLIENT_NULL_OR_VOID);

		if (tarjeta.getCliente().getIdCliente() == null || tarjeta.getCliente().getIdCliente() < 1)
			throw new TarjetaException(TarjetaConstants.MSG_FAIL_ID_CLIENT_SHOULD_NOT_BE_NULL_OR_VOID);

		if (tarjeta.getEstatus() == null)
			throw new TarjetaException(TarjetaConstants.MSG_FAIL_STATUS_SHOULD_NOT_BE_NULL_OR_VOID);

		if (tarjeta.getEstatus().getId() == null || tarjeta.getEstatus().getId() < 1)
			throw new TarjetaException(TarjetaConstants.MSG_FAIL_ID_STATUS_SHOULD_NOT_BE_NULL_OR_VOID);

		if (tarjeta.getTipo() == null)
			throw new TarjetaException(TarjetaConstants.MSG_FAIL_TIPO_SHOULD_NOT_BE_NULL_OR_VOID);

		if (tarjeta.getTipo().getId() == null || tarjeta.getTipo().getId() < 1)
			throw new TarjetaException(TarjetaConstants.MSG_FAIL_ID_TIPO_SHOULD_NOT_BE_NULL_OR_VOID);
		
		doTypeValidations(tarjeta);

		Tarjetas tarjetas = new Tarjetas();

		Cliente cliente = addCliente(tarjeta.getCliente());

		TipoTarjeta tipoTarjeta = tipoTarjetaRepository.encontrar(tarjeta.getTipo().getId());
		EstatusTarjeta estatusTarjeta = estatusTarjetaRepository.encontrar(tarjeta.getEstatus().getId());
		
		if (tipoTarjeta == null || tipoTarjeta.getId() == null)
			throw new TarjetaException(TarjetaConstants.MSG_FAIL_CAN_NOT_FIND_THE_CARD_TYPE_RECORD);
		
		if (estatusTarjeta == null || estatusTarjeta.getId() == null)
			throw new TarjetaException(TarjetaConstants.MSG_FAIL_CAN_NOT_FIND_THE_CARD_STATUS_RECORD);

		Tarjetas idOpen = tarjetaRepository.findByIdOpenPay(tarjeta.getId_openpay());

		if(idOpen != null)
			throw new TarjetaIdentifierException(TarjetaConstants.MSG_FAIL_TOKENS);
		
		List<Tarjetas> tarjetasList = tarjetaRepository.findByIdcliente(tarjeta.getCliente().getIdCliente());
		
		if(null != tarjetasList && !tarjetasList.isEmpty()) {
			for(Tarjetas tarjetaVal : tarjetasList) {
				
				String aliasBd = tarjetaVal.getAlias().toUpperCase().trim();
				
				String aliasUsu = tarjeta.getAlias().toUpperCase().trim();
				
				if(null != tarjetaVal && null != aliasBd && aliasBd.equals(aliasUsu)) {
					throw new TarjetaException(TarjetaConstants.ALIAS_ALREADY_EXIST_FOR_CURRENT_CLIENT);
				}
			}	
		}
		
		tarjetas.setToken(tarjeta.getToken());
		tarjetas.setUltimosDigitos(tarjeta.getDigitos());
		tarjetas.setAlias(tarjeta.getAlias().trim());
		tarjetas.setFechaAlta(new Date());
		tarjetas.setClientes(cliente);
		tarjetas.setTipoTarjeta(tipoTarjeta);
		tarjetas.setEstatusTarjeta(estatusTarjeta);
		tarjetas.setId_openpay(tarjeta.getId_openpay());
		tarjetaRepository.save(tarjetas);
				
		return tarjetas;
	}

	/**
	 * Metodo que actualiza el alias de la tarjeta.
	 * 
	 * @param token.
	 * @param alias.
	 * @return Tarjetas.
	 */
	public Tarjetas updateTarjeta(String id_openpay, String alias) {

		if (id_openpay == null || id_openpay.isEmpty())
			throw new TarjetaException(TarjetaConstants.MSG_FAILURE_TOKEN);

		if (alias == null || alias.isEmpty())
			throw new TarjetaException(TarjetaConstants.MSG_FAILURE_ALIAS);

		if (alias.length() > 10)
			throw new TarjetaException(TarjetaConstants.MSG_FAILURE_ALIAS_EXCEED_DIGITS);

		if (alias.matches(TarjetaConstants.SYMBOL) == false)
			throw new TarjetaException(TarjetaConstants.MSG_WITHOUT_SYMBOLS);

		// Obtiene los registros
		Tarjetas updateTarjeta = tarjetaRepository.findByIdOpenPay(id_openpay);

		// Evalua si existen registros
		if (updateTarjeta == null)
			throw new TarjetaException(TarjetaConstants.MSG_NO_SUCCESS_UPDATE_NULL);

		List<Tarjetas> tarjetasList = tarjetaRepository.findByIdcliente(updateTarjeta.getClientes().getIdcliente());

		if (null != tarjetasList && !tarjetasList.isEmpty()) {

			for (Tarjetas tarjetaVal : tarjetasList) {

				String aliasBd = tarjetaVal.getAlias().toUpperCase().trim();

				String aliasUsu = alias.toUpperCase().trim();

				// Evalua si ya existe el mismo alias en la tarjeta
				if (null != tarjetaVal && null != aliasBd && aliasBd.equals(aliasUsu))
					throw new TarjetaException(TarjetaConstants.ALIAS_ALREADY_EXIST_FOR_CURRENT_CLIENT);

			}

		}

		updateTarjeta.setAlias(alias.trim());
		updateTarjeta.setFechaModificacion(new Date());

		tarjetaRepository.save(updateTarjeta);

		return updateTarjeta;

	}
	
	/**
	 * Metodo que actualiza el alias de la tarjeta.
	 * 
	 * @param token.
	 * @param alias.
	 * @return Tarjetas.
	 */
	public Tarjetas updateToken(String id_openpay, String token) {

		if (id_openpay == null || id_openpay.isEmpty()) {

			throw new TarjetaException(TarjetaConstants.MSG_FAILURE_TOKEN);

		}

		if (token == null || token.isEmpty()) {

			throw new TarjetaException(TarjetaConstants.MSG_FAILURE_UPTOKEN);

		}

		// Obtiene los registros
		Tarjetas updateTarjeta = tarjetaRepository.findByIdOpenPay(id_openpay);

		// Evalua si existen registros
		if (updateTarjeta == null)
			throw new TarjetaException(TarjetaConstants.MSG_NO_SUCCESS_UPDATE_NULL);

		updateTarjeta.setToken(token);
		updateTarjeta.setFechaModificacion(new Date());

		tarjetaRepository.save(updateTarjeta);

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
	public Tarjetas deleteTarjeta(String id_openpay) {

		if (id_openpay == null || id_openpay.isEmpty()) {

			throw new TarjetaException(TarjetaConstants.MSG_FAILURE_TOKEN);

		}

		// Obtiene los registros
		Tarjetas deleteTarjeta = tarjetaRepository.findByIdOpenPay(id_openpay);

		if (deleteTarjeta == null)
			throw new TarjetaException(TarjetaConstants.MSG_NO_SUCCESS_DELETE);

		tarjetaRepository.eliminarTarjeta(id_openpay);

		return deleteTarjeta;
	}

	/**
	 * Metodo que cuenta la cantidad de tarjetas que tiene un cliente especifico
	 */
	@Override
	public Integer countTarjetasByIdcliente(Long idCliente) {
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

		if (tarjetaCliente.size() >= MAXIMUM_AMOUNT_OF_CARDS_PER_CLIENT) {

			throw new TarjetaException(TarjetaConstants.MSG_NO_SUCCESS_ADD_MAX_CARDS);

		}

		if (cliente != null) {

			return cliente;

		} else {

			cliente = new Cliente();
			cliente.setIdcliente(dto.getIdCliente() == null ? dto.getIdCliente() : dto.getIdCliente());
			cliente.setNombreTitular(dto.getNombreTitular() == null ? dto.getNombreTitular() : dto.getNombreTitular());
			cliente.setFechaAlta(new Date());

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
		tarjeDto.setId_openpay(tarjetasCliente.getId_openpay());
		tarjeDto.setTipo(tipoTarjeta);
		tarjeDto.setEstatus(estatusTarjeta);

		return tarjeDto;

	}
	
	/**
	 * 
	 * Metodo que realiza validaciones de tipos de datos en parametros
	 * 
	 * @throws TarjetaException
	 */
	public static void doTypeValidations(TarjetaDTO tarjeta)throws TarjetaException{
		try {
			Integer.parseInt(tarjeta.getDigitos());
		}
		catch(NumberFormatException nex) {
			throw new TarjetaException(TarjetaConstants.MSG_ONLY_NUMBERS);
		}
		if(null != tarjeta.getCliente() && null != tarjeta.getCliente().getNombreTitular()) {
			char[] clArray= tarjeta.getCliente().getNombreTitular().toUpperCase().toCharArray();
			for(int i = 0; i < clArray.length; i ++) {
				if(!TarjetaConstants.LETTER_VALUES.contains(String.valueOf(clArray[i]))) {
					throw new TarjetaException(TarjetaConstants.MSG_ONLY_LETTERS);
				}
			}
		}		
	}

}
