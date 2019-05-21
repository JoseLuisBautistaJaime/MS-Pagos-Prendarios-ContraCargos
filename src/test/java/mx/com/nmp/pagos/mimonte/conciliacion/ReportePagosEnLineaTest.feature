Feature: MS Conciliacion- ReportePagosEnLinea
	Background:
		* url 'http://localhost:8080'
		* header Accept = 'application/json'
	Scenario: Consulta el reporte de pagos en linea de los procesos nocturnos de midas
		Given path 'mimonte/conciliacion/reportes/pagosenlinea'
		And header requestUser = 'ismael'
		And request {"fechaDesde":"2019-05-01T18:34:31.264Z","fechaHasta":"2019-05-21T18:34:31.264Z","operacion":8,"partida":123,"producto":145,"sucursales":[10,44,222]}
		When method POST
		Then status 200
