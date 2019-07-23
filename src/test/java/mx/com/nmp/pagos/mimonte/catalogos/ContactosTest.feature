Feature: MS Conciliacion - Catalogo contactos
  Background:
    * url 'http://localhost:8080'
    * header Accept = 'application/json'
  Scenario: Registrar una contacto
    Given path 'mimonte/catalogos/contacto'
    And request {"createdBy": "Jose", "descripcion": "Contacto", "email": "email@email.com", "nombre": "Jose", "tipoContacto": 1}
    When method POST
    Then status 200

  Scenario: Actualizar contacto
    Given path 'mimonte/catalogos/contactos'
    And request { "descripcion": "contacto", "email": "email@email.com", "estatus": true, "id": 1, "lastModifiedBy": "Jose", "nombre": "Jose", "tipoContactoReqDTO": { "id": 1 }}
    When method PUT
    Then status 200

  Scenario: Actualizar estatus del contacto
    Given path 'mimonte/catalogos/estatus'
    And request { "estatus": true, "id": 0, "lastModifiedBy": "string" }
    When method PUT
    Then status 200

  Scenario: Consultar una contacto por id
    Given path 'mimonte/catalogos/1'
    When method GET
    Then status 200

  Scenario: Consultar contactos por tipo de contacto
    Given path 'mimonte/catalogo/1'
    When method GET
    Then status 200