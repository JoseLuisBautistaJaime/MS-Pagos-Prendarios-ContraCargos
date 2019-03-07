Feature: MS Conciliacion- Codigos de estados de cuenta
	Background:
		* url 'http://localhost:8080'
		* header Accept = 'application/json'
	Scenario: Guardar un codigo de estado de cuenta
		Given path 'mimonte/catalogos/codigos'
		And request {"categoria": {"id": 1},"entidad": {"id": 1},"leyenda": "legendOfZelda"}
		When method POST
		Then status 200

	Scenario: Actualiza un codigo de estado de cuenta
		Given path 'mimonte/catalogos/codigos'
		And request {"categoria": {"id": 1},"entidad": {"id": 1},"id": 1,"leyenda": "legendOfZelda","status": true}
		When method PUT
		Then status 200

	Scenario: Regresa un codigo de estado de cuenta por id
		Given path 'mimonte/catalogos/codigos/1'
		When method GET
		Then status 200

	Scenario: Regresa una lista de codigos de estado de cuenta por id de entidad
		Given path 'mimonte/catalogos/codigos/entidad/1'
		When method GET
		Then status 200
