Feature: MS Conciliacion- Cuenta
	Background:
		* url 'http://localhost:8080'
		* header Accept = 'application/json'
	Scenario: Guardar una cuenta
		Given path 'mimonte/catalogos/cuentas'
		And header requestUser = 'ismael'
		And request {"numero":"123123123","afiliaciones":[{"id":1,"numero":1}]}
		When method POST
		Then status 200

	Scenario: Actualiza una cuenta
		Given path 'mimonte/catalogos/cuentas'
		And header requestUser = 'ismael'
		And request {"id":0,"estatus":true,"numero":"string","afiliaciones":[{"id":0,"numero":0}]}
		When method PUT
		Then status 200

	Scenario: Regresa una cuenta por numero
		Given path 'mimonte/catalogos/cuentas/1'
		When method GET
		Then status 200

	Scenario: Regresa una lista de cuentas por id de entidad
		Given path 'mimonte/catalogos/cuentas/entidad/1'
		When method GET
		Then status 200

	Scenario: Regresa una lista de todas las cuentas
		Given path 'mimonte/catalogos/cuentas/'
		When method GET
		Then status 200

	Scenario: Actualiza el estatus de una cuenta a false (inactiva)
		Given path 'mimonte/catalogos/cuentas/1'
		And header requestUser = 'ismael'
		When method PUT
		Then status 200
