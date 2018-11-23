package mx.com.nmp.pagos.mimonte.dto;

import java.util.Date;

public class PagoDummyDTO {

	private String concepto;
	private Boolean guardaTarjeta;
	private Integer montoTotal;
	private Operaciones operaciones;
	
	
}
class Operaciones{
	private String folioContrato;
	private Integer idOperacion;
	private Double monto;
	private String nombreOperacion;
}

class Tarjeta{
	private String alias;
	private String digitos;
	private Date fechaAlta;
	private Date fechaModificacion;
	private String token;

}

class Cliente{
	private Integer idCliente;
	private String nombreTitular;
	private Date fechaAlta;
	
}


class Estatus{
	private Integer id;
	private String descripcion;
	private String descripcionCorta;
}


class Tipo{
	private Integer id;
	private String descripcion;
	private String descripcionCorta;
}



