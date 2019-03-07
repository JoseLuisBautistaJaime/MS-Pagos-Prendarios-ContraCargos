Feature: MS Conciliacion- Catalogos
	Background:
		* url 'http://localhost:8080'
		* header Accept = 'application/json'
	Scenario: Regresa la informacion de un catalogo registrado en la base de datos
		Given path 'mimonte/v1/catalogo/Afiliacion'
		When method GET
		Then status 200

	Scenario: Regresa la lista de los nombres de extrafilter registrados en el sistema para su consulta
		Given path 'mimonte/v1/catalogos'
		When method GET
		Then status 200