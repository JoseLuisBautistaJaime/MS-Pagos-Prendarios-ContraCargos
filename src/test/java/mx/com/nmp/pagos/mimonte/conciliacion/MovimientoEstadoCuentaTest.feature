Feature: MS Conciliacion- MovimientoEstadoCuenta
	Background:
		* url 'http://localhost:8080'
		* header Accept = 'application/json'
	Scenario: Consulta movimientos de estado de cuenta por folio de conciliacion y con paginacion
		Given path 'mimonte/movimientos/estadocuenta/consulta'
		And request {"folio": 0, "resultados": 0, "pagina": 0}
		When method POST
		Then status 200

	Scenario: Alta movimientos de estado de cuenta por folio de conciliacion (se crea un objeto reporte en BD)
		Given path 'mimonte/movimientos/estadocuenta'
		And request {"fechaFinal":"2019-05-15T22:49:09.733Z","fechaInicial":"2019-05-15T22:49:09.733Z","folio":1}
		When method POST
		Then status 200
