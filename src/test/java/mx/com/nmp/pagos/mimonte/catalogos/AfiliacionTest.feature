Feature: MS Conciliacion- Afiliacion
	Background:
		* url 'http://localhost:8080'
		* header Accept = 'application/json'
	Scenario: Guardar una afiliacion
		Given path 'mimonte/catalogos/afiliaciones'
		And header requestUser = 'ismael'
		And request {"numero":12123123}
		When method POST
		Then status 200

	Scenario: Actualiza una afiliacion
		Given path 'mimonte/catalogos/afiliaciones'
		And header requestUser = 'ismael'
		And request {"estatus":true,"id":1,"numero":12123123}
		When method PUT
		Then status 200

	Scenario: Regresa una afiliacion por numero
		Given path 'mimonte/catalogos/afiliaciones/12123123'
		When method GET
		Then status 200

	Scenario: Regresa una lista de afiliaciones por id de cuenta
		Given path 'mimonte/catalogos/afiliaciones/cuenta/1'
		When method GET
		Then status 200

	Scenario: Regresa una lista de todas las afiliaciones
		Given path 'mimonte/catalogos/afiliaciones/'
		When method GET
		Then status 200

	Scenario: Actualiza el estatus de una afiliacion a false (inactiva)
		Given path 'mimonte/catalogos/afiliaciones/1'
		And header requestUser = 'ismael'
		When method PUT
		Then status 200
