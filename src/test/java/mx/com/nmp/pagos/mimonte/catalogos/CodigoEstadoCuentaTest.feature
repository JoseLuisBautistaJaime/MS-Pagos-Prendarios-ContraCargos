Feature: MS Conciliacion- Codigos de estados de cuenta
	Background:
		* url 'http://localhost:8080'
		* header Accept = 'application/json'
	Scenario: Guardar un codigo de estado de cuenta
		Given path 'mimonte/v1/conciliacion/codigos'
		And request {"leyenda": "legend","entidad": {"id": 1},"categoria": {"id": 1}}
		When method POST
		Then status 200

	Scenario: Actualiza un codigo de estado de cuenta
		Given path 'mimonte/v1/conciliacion/codigos'
		And request {"id": 1, estatus: true, "leyenda": "legend","entidad": {"id": 1},"categoria": {"id": 1}}
		When method PUT
		Then status 200

	Scenario: Regresa un codigo de estado de cuenta por id
		Given path 'mimonte/v1/conciliacion/codigos/{idcodigo}'
		When method GET
		Then status 200

	Scenario: Regresa una lista de codigos de estado de cuenta por id de entidad
		Given path 'mimonte/v1/conciliacion/codigos/entidad({identidad}'
		When method GET
		Then status 200
