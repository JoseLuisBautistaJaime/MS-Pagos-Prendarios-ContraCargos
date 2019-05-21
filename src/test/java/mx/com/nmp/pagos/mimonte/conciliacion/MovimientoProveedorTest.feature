Feature: MS Conciliacion- MovimientoProveedor
	Background:
		* url 'http://localhost:8080'
		* header Accept = 'application/json'
	Scenario: Da de alta un movimiento de tipo proveedor transaccional
		Given path 'mimonte/movimientos/proveedor'
		And header requestUser = 'ismael'
		And request {"fechaDesde":"2019-05-06T23:12:54.145Z","fechaHasta":"2019-05-06T23:12:54.145Z","folio":1,"movimientos":[{"amount":1000,"authorization":"1244770","card":{"address":"direccion","allowsCharges":true,"allowsPayouts":true,"bankCode":"1002","bankName":"banamex","brand":"citi","cardNumber":"144897784544","creationDate":"2019-05-06T23:12:54.145Z","customerId":"1124","expirationMonth":"10","expirationYear":"2025","holderName":"holder","id":"1","type":"master card"},"conciliated":true,"creationDate":"2019-05-06T23:12:54.145Z","currency":"mxn","customerId":"1554","description":"desc","errorCode":"300","errorMessage":"erro 300","id":"1554","method":"card","operationDate":"2019-05-06T23:12:54.145Z","operationType":"payment","orderId":"2330","paymentMethod":{"type":"one","url":"http://url.com"},"status":"activa","transactionType":"type one"}]}
		When method POST
		Then status 200

	Scenario: Regresa una lista de movimientos de proveedor por folio de conciliacion (CORRECTO)
		Given path 'mimonte/movimientos/proveedor/consulta'
		And request {"folio":1,"pagina":0,"resultados":5}
		When method POST
		Then status 200

	Scenario: Regresa una lista de movimientos de proveedor por folio de conciliacion (INCORRECTO CON PARAMETROS NO VALIDOS)
		Given path 'mimonte/movimientos/proveedor/consulta'
		And request {"folio":0,"pagina":0,"resultados":0}
		When method POST
		Then status 200

	Scenario: Regresa una lista de movimientos de proveedor por folio de conciliacion (INCORRECTO CON FOLIO QUE NO EXISTE)
		Given path 'mimonte/movimientos/proveedor/consulta'
		And request {"folio":1000,"pagina":0,"resultados":5}
		When method POST
		Then status 200
