Feature: MS Conciliacion - Catalogo entidad
  Background:
    * url 'http://localhost:8080'
    * header Accept = 'application/json'
  Scenario: Registrar una entidad
    Given path 'mimonte/catalogos/entidades'
    And request {"contactos": [{"email": "ismael@gmail.com","estatus": true,"id": 1,"nombre": "Ismael"}],"createdBy": "Ismael","cuentas": [{"afiliaciones": [{"estatus": true,"id": 1,"numero": 123123123}],"estatus": true,"id": 1,"numeroCuenta": 11621836374}],"descripcion": "Banco Banamex","nombre": "Banamex"}
    And header requestUser = 'Quarksoft'
    When method POST
    Then status 200

  Scenario: Actualizar una entidad
    Given path 'mimonte/catalogos/entidades'
    And request {"contactos": [{"email": "ismael@gmail.com","estatus": true,"id": 1,"nombre": "Ismael"}],"createdBy": "Ismael","cuentas": [{"afiliaciones": [{"estatus": true,"id": 1,"numero": 123123}],"estatus": true,"id": 1}],"descripcion": "Banco Banamex","estatus": true,"id": 1,"lastModifiedBy": "Otro chaval","lastModifiedDate": "2019-03-07T21:02:16.308Z","nombre": "Banamex"}
    And header requestUser = 'Quarksoft'
    When method PUT
    Then status 200
    
  Scenario: Elimina una entidad de manera logica
  	Given path 'mimonte/catalogos/entidades/1'
  	And header requestUser = 'Quarksoft'
  	When method PUT
  	Then status 200

  Scenario: Consultar una entidad por id
    Given path 'mimonte/catalogos/entidades/1'
    When method GET
    Then status 200

  Scenario: Consultar una entidad por nombre y estatus
    Given path 'mimonte/catalogos/entidades/consultas/Banamex/true'
    When method GET
    Then status 200
