Feature: MS Conciliacion - Catalogo contactos
  Background:
    * url 'http://localhost:8080'
    * header Accept = 'application/json'
  Scenario: Registrar una contacto
    Given path 'mimonte/catalogos/contactos'
    And request {"createdBy": "Jose", "descripcion": "Contacto", "email": "email@email.com", "nombre": "Jose", "tipoContacto": 1}
    When method POST
    Then status 200

  Scenario: Actualizar contacto
    Given path 'mimonte/catalogos/contactos'
    And request { "descripcion": "contacto", "email": "email@email.com", "estatus": true, "id": 1, "lastModifiedBy": "Jose", "nombre": "Jose", "tipoContactoReqDTO": { "id": 1 }}
    When method PUT
    Then status 200

  Scenario: Elimina un contacto por id
    Given path 'mimonte/catalogos/contactos/1'
    When method DELETE
    Then status 200

  Scenario: Consultar una contacto por id
    Given path 'mimonte/catalogos/contactos/1'
    When method GET
    Then status 200

  Scenario: Consultar contactos por tipo de contacto
    Given path 'mimonte/catalogos/contactos/tipocontacto/1'
    When method GET
    Then status 200
    
  Scenario: Consultar contactos por tipo de contacto
    Given path 'mimonte/catalogos/contactos/tipocontacto/1'
    When method GET
    Then status 200
    