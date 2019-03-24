Feature: MS Conciliacion- Catalogos
	Background:
		* url 'http://localhost:8080'
		* header Accept = 'application/json'
	Scenario: Regresa la informacion de un catalogo registrado en la base de datos
		Given path 'mimonte/v1/catalogo/Estatus%20Tarjeta'
		When method GET
		Then status 200
		
	Scenario: Regresa la informacion de un catalogo registrado en la base de datos
		Given path 'mimonte/v1/catalogo/Estatus%20Transaccion'
		When method GET
		Then status 200
		
	Scenario: Regresa la informacion de un catalogo registrado en la base de datos
		Given path 'mimonte/v1/catalogo/Tipo%20Tarjeta'
		When method GET
		Then status 200
		
	Scenario: Regresa la informacion de un catalogo registrado en la base de datos
		Given path 'mimonte/v1/catalogo/Categoria'
		When method GET
		Then status 200
		
	Scenario: Regresa la informacion de un catalogo registrado en la base de datos
		Given path 'mimonte/v1/catalogo/Conciliacion'
		When method GET
		Then status 200
		
	Scenario: Regresa la informacion de un catalogo registrado en la base de datos
		Given path 'mimonte/v1/catalogo/Movimiento%20en%20Transito'
		When method GET
		Then status 200
		
	Scenario: Regresa la informacion de un catalogo registrado en la base de datos
		Given path 'mimonte/v1/catalogo/Devoluciones'
		When method GET
		Then status 200
		
	Scenario: Regresa la informacion de un catalogo registrado en la base de datos
		Given path 'mimonte/v1/catalogo/Estatus%20Operacion'
		When method GET
		Then status 200
		
	Scenario: Regresa la informacion de un catalogo registrado en la base de datos
		Given path 'mimonte/v1/catalogo/Tipo%20Afiliacion'
		When method GET
		Then status 200
		
	Scenario: Regresa la informacion de un catalogo registrado en la base de datos
		Given path 'mimonte/v1/catalogo/Tipo%20Contacto'
		When method GET
		Then status 200

	Scenario: Regresa la lista de los nombres de extrafilter registrados en el sistema para su consulta
		Given path 'mimonte/v1/catalogos'
		When method GET
		Then status 200