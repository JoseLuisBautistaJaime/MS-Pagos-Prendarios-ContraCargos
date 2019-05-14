Feature: MS Conciliacion- MovimientoEstadoCuenta
	Background:
		* url 'http://localhost:8080'
		* header Accept = 'application/json'
	Scenario: Consulta movimientos de estado de cuenta por dolio de conciliacion y con paginacion
		Given path 'mimonte/movimientos/estadocuenta/consulta'
		And request {"folio": 0, "resultados": 0, "pagina": 0}
		When method POST
		Then status 200
