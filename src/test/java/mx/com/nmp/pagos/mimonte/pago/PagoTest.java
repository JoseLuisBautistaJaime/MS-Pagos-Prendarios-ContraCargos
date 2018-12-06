package mx.com.nmp.pagos.mimonte.pago;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ibm.icu.impl.Assert;

import mx.com.nmp.pagos.mimonte.dto.EstatusTarjetaDTO;
import mx.com.nmp.pagos.mimonte.dto.OperacionDTO;
import mx.com.nmp.pagos.mimonte.dto.PagoRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.TarjetaPagoDTO;
import mx.com.nmp.pagos.mimonte.dto.TipoTarjetaDTO;
import mx.com.nmp.pagos.mimonte.services.PagoService;

/**
 * Clase de pruebas para el servcicio WSPago
 * 
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 28/11/2018 15:55 hrs.
 * @version 0.1
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PagoTest {

	@Autowired
	PagoService pagoService;
	
	/**
	 * 
	 * Guarda un pago
	 * 
	 */
	@Test
	public void savePago() {
		List<OperacionDTO> operacionesDTO;
		PagoRequestDTO pagoRequestDTO;
		pagoRequestDTO = new PagoRequestDTO();
		pagoRequestDTO.setConcepto("Test Concepto");
		pagoRequestDTO.setMontoTotal(10000);
		pagoRequestDTO.setGuardaTarjeta(false);
		pagoRequestDTO.setIdCliente(1);
		pagoRequestDTO.setTarjeta(new TarjetaPagoDTO("TOK3N12345","9876","TestCard",new Date(),new Date(),new TipoTarjetaDTO(1,"Tarjeta Visa","Visa"),new EstatusTarjetaDTO(1,"Registrada","Tarjeta Registrada")));
		operacionesDTO = new ArrayList<>();
		operacionesDTO.add(new OperacionDTO(1,"Pago de partida","CP123",5000D));
		operacionesDTO.add(new OperacionDTO(2,"Pago de partida 2","CP456",5000D));
		pagoRequestDTO.setOperaciones(operacionesDTO);
		try {
			pagoService.savePago(pagoRequestDTO);
			Assert.assrt("Test correcto", true);
		}
		catch(Exception ex) {
			Assert.fail(ex);
		}
	}
	
}
