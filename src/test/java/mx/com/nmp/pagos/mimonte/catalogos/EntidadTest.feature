Feature: MS Conciliacion - Catalogo entidad
  Background:
    * url 'http://localhost:8080'
    * header Accept = 'application/json'
  Scenario: Registrar una entidad
    Given path 'mimonte/v1/conciliacion/entidades'
    And request {"nombre": "E1","descripcion": "descripcion 1","contactos": [{id: 1,"nombre": "contact 1","email": "contact1@gmail.com","status": true}],"cuentas": [{"id": 1,"numero": "55608767","status": true,"afiliaciones": [{"id": 1,"numero": "3322344","estatus": true}, {"id": 2,"numero": "4343254","estatus": true}]}]}
    When method POST
    Then status 200

  Scenario: Actualizar una entidad
    Given path 'mimonte/v1/conciliacion/entidades'
    And request {"id": 1, "nombre": "E1","descripcion": "descripcion 1","contactos": [{id: 1,"nombre": "contact 1","email": "contact1@gmail.com","status": true}],"cuentas": [{"id": 1,"numero": "55608767","status": true,"afiliaciones": [{"id": 1,"numero": "3322344","estatus": true}, {"id": 2,"numero": "4343254","estatus": true}]}]}
    When method PUT
    Then status 200

  Scenario: Consultar una entidad por id
    Given path 'mimonte/v1/conciliacion/entidades/{identidad}'
    When method GET
    Then status 200

  Scenario: Consultar una entidad por nombre y estatus
    Given path 'mimonte/v1/conciliacion/entidades/{nombre}/{estatus}'
    When method GET
    Then status 200
