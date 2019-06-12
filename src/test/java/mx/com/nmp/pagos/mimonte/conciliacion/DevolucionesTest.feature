Feature: MS Conciliacion- ComisionesTransacciones
	Background:
		* url 'http://localhost:8080'
		* header Accept = 'application/json'
	Scenario: Realiza la consulta de movimientos de devolución para la conciliacion.
		Given path '/mimonte/conciliacion/devoluciones/consulta/1'
		When method GET
		Then status 200

	Scenario: Realiza la liquidación de los movimientos seleccionados; se debe especificar la fecha de liquidación para cada uno de los movimientos.
		Given path '/mimonte/conciliacion/devoluciones/liquidar'
		And header requestUser = 'SISTEMA'
		And request {"folio":1,"movimientos":[{"fecha":"2019-06-11T15:14:03.890Z","id":1}]}
		When method POST
		Then status 200

	Scenario: El estatus de la transacción de devolución cambiará de Pendiente a Solicitada.
		Given path '/mimonte/conciliacion/devoluciones/solicitar'
		And header requestUser = 'SISTEMA'
		And request {"folio":1}
		When method POST
		Then status 200

	Scenario: Marca las transacciones seleccionadas de movimientos en tránsito a movimientos de devolución para cuando los pagos solicitados no fueron realizados.
		Given path '/mimonte/conciliacion/marcardevoluciones'
		And header requestUser = 'SISTEMA'
		And request {"folio":1,"idMovimientos":[1]}
		When method POST
		Then status 200

	Scenario: Realiza la administración de devoluciones a nivel entidad - Actualización de la fecha y liquidación para las devoluciones.
		Given path '/mimonte/devoluciones/actualizacion'
		And header requestUser = 'SISTEMA'
		And request [{"fecha":"2019-06-11T15:14:04.226Z","idMovimiento":1,"liquidar":true}]
		When method PUT
		Then status 200

	Scenario: Realiza la administración de devoluciones a nivel entidad - Consulta de devoluciones para todas las entidades bancarias.
		Given path '/mimonte/devoluciones/consulta'
		And request {"estatus":1,"fechaDesde":"2019-06-11T15:14:04.235Z","fechaHasta":"2019-06-11T15:14:04.235Z","idEntidad":1,"identificadorCuenta":"1234","sucursal":1}
		When method POST
		Then status 200

	Scenario: Realiza la administración de devoluciones a nivel entidad - Enviar solicitud de devoluciones.
		Given path '/mimonte/devoluciones/solicitar'
		And header requestUser = 'SISTEMA'
		And request {"idsMovimientos":[1]}
		When method POST
		Then status 200