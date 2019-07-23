Feature: MS Conciliacion- MovimientoMidas
	Background:
		* url 'http://localhost:8080'
		* header Accept = 'application/json'
	Scenario: Da de alta un movimiento de tipo midas
		Given path 'mimonte/movimientos/nocturnos'
		And header requestUser = 'ismael'
		And request {"fechaDesde":"2019-05-06T23:12:54.126Z","fechaHasta":"2019-05-06T23:12:54.126Z","folio":1,"movimientos":[{"capitalActual":10,"comisiones":15,"estadoTransaccion":"en proc","estatus":"activa","fecha":"2019-05-06T23:12:54.127Z","folioPartida":123,"id":10,"idConsumidor":"002","interes":11,"montoOperacion":4526,"numAutorizacion":"33664","operacionAbr":"op abr","operacionDesc":"op desc","sucursal":30,"tipoContratoAbr":"tc abr","tipoContratoDesc":"tc desc","transaccion":112200}]}
		When method POST
		Then status 200

	Scenario: Regresa una lista de movimientos de midas por folio de conciliacion (CORRECTO)
		Given path 'mimonte/movimientos/nocturnos/consulta'
		And request {"folio":1,"pagina":0,"resultados":5, "estatus": "activa"}
		When method POST
		Then status 200

  Scenario: Regresa una lista de movimientos de midas por folio de conciliacion (INCORRECTO CON PARAMETROS NO VALIDOS)
    Given path 'mimonte/movimientos/nocturnos/consulta'
    And request {"folio":0,"pagina":0,"resultados":0, "estatus": "activa"}
    When method POST
    Then status 200

  Scenario: Regresa una lista de movimientos de midas por folio de conciliacion (INCORRECTO CON FOLIO QUE NO EXISTE)
		Given path 'mimonte/movimientos/nocturnos/consulta'
		And request {"folio":1000,"pagina":0,"resultados":5, "estatus": "activa"}
		When method POST
		Then status 200
