Feature: MS Conciliacion- MovimientoMidas
	Background:
		* url 'http://localhost:8080'
		* header Accept = 'application/json'
	Scenario: Da de alta un movimiento de tipo midas
		Given path 'mimonte/conciliacion/solicitarpagos'
		And header requestUser = 'ismael'
		And request {"folio":1,"idMovimientos":[1,2,3]}
		When method POST
		Then status 200
