Feature: MS Conciliacion- MovimientoMidas
	Background:
		* url 'http://localhost:8080'
		* header Accept = 'application/json'
	Scenario: Consulta el resumen de movimientos de Conciliacion
		Given path 'mimonte/conciliacion/resumen'
		And request {"fechaInicial":"2019-01-01","fechaFinal":"2019-12-12"}
		When method POST
		Then status 200
		
	Background:
		* url 'http://localhost:8080'
		* header Accept = 'application/json'
	Scenario: Actualiza el suebestatus y posiblemente el estatus de una conciliacion por folio
		Given path 'mimonte/conciliacion/actualizarSubEstatus'
		And header requestUser = 'ISMAEL'
		And request {"folio":1,"idSubEstatus":1,"descripcion":"Descripcion 1"}
		When method PUT
		Then status 200
		
	Background:
		* url 'http://localhost:8080'
		* header Accept = 'application/json'
	Scenario: Consulta las actividades de la aplicacion por folio y/o rango de fechas, deir nulos dichos parametros consulta los ultimos 10 movimientos por default
		Given path 'mimonte/conciliacion/actividades'
		And request {"folio":1,"fechaDesde":"2019-01-01","fechaHasta":"2019-12-12"}
		When method POST
		Then status 200