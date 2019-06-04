Feature: MS Conciliacion- ComisionesTransacciones
	Background:
		* url 'http://localhost:8080'
		* header Accept = 'application/json'
	Scenario: Da de alta una comision (CORRECTO)
		Given path 'mimonte/comisiones'
		And header requestUser = 'ismael'
		And request {"folio":1,"id":1,"fechaOperacion":"2019/01/01","fechaCargo":"2019/01/01","monto":1500.5,"descripcion":"Descripcion 1"}
		When method POST
		Then status 200

	Scenario: Da de alta una comision con fechas posteriores a la actual(INCORRECTO)
		Given path 'mimonte/comisiones'
		And header requestUser = 'ismael'
		And request {"folio":1,"id":1,"fechaOperacion":"2050/01/01","fechaCargo":"2050/01/01","monto":1500.5,"descripcion":"Descripcion 1"}
		When method POST
		Then status 200

	Scenario: Da de alta una comision con folio e id menor a 0(INCORRECTO)
		Given path 'mimonte/comisiones'
		And header requestUser = 'ismael'
		And request {"folio":-1,"id":-11,"fechaOperacion":"2019/01/01","fechaCargo":"2019/29/01","monto":1500.5,"descripcion":"Descripcion 1"}
		When method POST
		Then status 200

	Scenario: Da de alta una comision con folio e id igual a 0(INCORRECTO)
		Given path 'mimonte/comisiones'
		And header requestUser = 'ismael'
		And request {"folio":0,"id":0,"fechaOperacion":"2019/01/01","fechaCargo":"2019/29/01","monto":1500.5,"descripcion":"Descripcion 1"}
		When method POST
		Then status 200

	Scenario: Da de alta una comision con monto igual a 0(INCORRECTO)
		Given path 'mimonte/comisiones'
		And header requestUser = 'ismael'
		And request {"folio":0,"id":0,"fechaOperacion":"2019/01/01","fechaCargo":"2019/29/01","monto":0.0,"descripcion":"Descripcion 1"}
		When method POST
		Then status 200

	Scenario: Elimina una comision (CORRECTO)
		Given path 'mimonte/comisiones'
		And request {"folio":1,"idComisiones":[1,2,3,4,5,6,7]}
		When method DELETE
		Then status 200

	Scenario: Elimina una comision lista de ids comisiones vacia (INCORRECTO)
		Given path 'mimonte/comisiones'
		And request {"folio":1,"idComisiones":[]}
		When method DELETE
		Then status 200

	Scenario: Elimina una comision folio igual a 0 (INCORRECTO)
		Given path 'mimonte/comisiones'
		And request {"folio":0,"idComisiones":[1,2,3,4,5,6]}
		When method DELETE
		Then status 200

	Scenario: Regresa una lista de comisiones por folio de conciliacion (CORRECTO)
		Given path 'mimonte/comisiones/consulta/1'
		When method GET
		Then status 200

	Scenario: Regresa una lista de comisiones por folio de conciliacion con folio igual 0 (INCORRECTO)
		Given path 'mimonte/comisiones/consulta/0'
		When method GET
		Then status 200

	Scenario: Regresa una proyeccion de movimientos de tipo Pagos y Devoluciones ademas el total de comisiones (CORRECTO)
		Given path 'mimonte/comisiones/consulta/transacciones'
		And request {"fechaDesde":"2019/01/01","fechaHasta":"2019/12/01","comision":1.2}
		When method POST
		Then status 200

	Scenario: Regresa una proyeccion de movimientos de tipo Pagos y Devoluciones ademas el total de comisiones fechas posiblemente posteriores a hoy (INCORRECTO)
		Given path 'mimonte/comisiones/consulta/transacciones'
		And request {"fechaDesde":"2050/01/01","fechaHasta":"2050/12/01","comision":1.2}
		When method POST
		Then status 200

	Scenario: Regresa una proyeccion de movimientos de tipo Pagos y Devoluciones ademas el total de comisiones fecha desde posterior a fecha hasta (INCORRECTO)
		Given path 'mimonte/comisiones/consulta/transacciones'
		And request {"fechaDesde":"2019/02/01","fechaHasta":"2019/01/01","comision":1.2}
		When method POST
		Then status 200

	Scenario: Regresa una proyeccion de movimientos de tipo Pagos y Devoluciones ademas el total de comisiones comision menor a 0 (INCORRECTO)
		Given path 'mimonte/comisiones/consulta/transacciones'
		And request {"fechaDesde":"2019/02/01","fechaHasta":"2019/01/01","comision":-1.2}
		When method POST
		Then status 200
