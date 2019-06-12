Feature: MS Conciliacion- ComisionesTransacciones
	Background:
		* url 'http://localhost:8080'
		* header Accept = 'application/json'
	Scenario: para dar de alta una nueva conciliacion para entidad y cuenta seleccionados
		Given path 'mimonte/conciliacion'
		And header requestUser = 'SISTEMA'
		And request {"idCuenta": 1,"idEntidad": 1}
		When method POST
		Then status 200

	Scenario: Se encarga de guardar los cambios realizados en la conciliacion para las secciones de movimientos en transito.
		Given path '/mimonte/conciliacion'
		And header requestUser = 'SISTEMA'
		And request {"comisiones":[{"descripcion":"prueba","estatus":true,"fecha":"2019-06-05T19:00:38.642Z","id":1,"monto":123}],"folio":1,"movimientosTransito":[{"id":1,"tipo":"prueba"}]}
		When method PUT
		Then status 200

	Scenario: Realiza la consulta del log de las últimas actividades realizadas en el sistema.
		Given path '/mimonte/conciliacion/actividades'
		And request {"fechaDesde":"2019-06-05T19:00:38.660Z","fechaHasta":"2019-06-05T19:00:38.660Z","folio":1}
		When method POST
		Then status 200

	Scenario: Servicio callback que será usado para actualizar el id del registro de las plantillas que será devuelto por PeopleSoft
		Given path '/mimonte/conciliacion/actualizarPS'
		And header requestUser = 'SISTEMA'
		And request {"folio":1,"idEstatusConciliacion":1,"idPeopleSoft":1}
		When method PUT
		Then status 200

	Scenario: Servicio que será usado para actualizar el sub estatus del proceso de conciliación para conocer el estatus de la consulta de los reportes
		Given path '/mimonte/conciliacion/actualizarSubEstatus'
		And header requestUser = 'SISTEMA'
		And request {"descripcion":"PRUEBA","folio":1,"idSubEstatus":1}
		When method PUT
		Then status 200

	Scenario: Realiza la consulta de las conciliaciones dadas de alta en el sistema
		Given path '/mimonte/conciliacion/consulta'
		And request {"fechaDesde":"2019-06-05T19:00:38.702Z","fechaHasta":"2019-06-05T19:00:38.702Z","folio":1,"idEntidad":1,"idEstatus":1}
		When method POST
		Then status 200

	Scenario: Realiza la consulta de la conciliación desde la pantalla de consulta de conciliaciones.
		Given path '/mimonte/conciliacion/consulta/1'
		When method GET
		Then status 200

	Scenario: Al confirmar que la información es correcta, el usuario solicitará el cierre de la conciliación, y tendrá la posibilidad de visualizar y editar los layout antes de enviarlos.
		Given path '/mimonte/conciliacion/enviar/1'
		And header requestUser = 'SISTEMA'
		When method POST
		Then status 200

	Scenario: Servicio que permite generar la conciliación usando los movimientos de procesos nocturnos, del proveedor transaccional (open pay) y de estado de cuenta de acuerdo a su disponibilidad.
		Given path '/mimonte/conciliacion/generar/1'
		And header requestUser = 'SISTEMA'
		When method POST
		Then status 200

	Scenario: Servicio que consulta el resumen de conciliaciones realizadas.
		Given path '/mimonte/conciliacion/resumen'
		And request {"fechaFinal":"2019-06-11T15:14:04.023Z","fechaInicial":"2018-06-11T15:14:04.023Z"}
		When method POST
		Then status 200

	Scenario: Realiza la consulta de los movimientos en transito de la conciliacion (con error)
		Given path '/mimonte/conciliacion/transito/consulta/1'
		When method POST
		Then status 200
