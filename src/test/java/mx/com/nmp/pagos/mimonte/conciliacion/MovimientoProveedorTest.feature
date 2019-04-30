Feature: MS Conciliacion- Afiliacion
	Background:
		* url 'http://localhost:8080'
		* header Accept = 'application/json'
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
