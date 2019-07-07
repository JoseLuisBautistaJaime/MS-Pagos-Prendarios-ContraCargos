-- ------------------------------------------------------------------------------------------------------------------ --
-- FASE 2 - PAGOS --------------------------------------------------------------------------------------------------- --
-- ------------------------------------------------------------------------------------------------------------------ --


-- ------------------------------------------------------------------------------------------------------------------ --
-- INSERTS INICIALES EN TABLA - tk_estatus_tarjeta
-- ------------------------------------------------------------------------------------------------------------------ --
INSERT INTO tk_estatus_tarjeta (id, descripcion_corta, descripcion) VALUES (1, 'Activa', 'Tarjeta Activa');
INSERT INTO tk_estatus_tarjeta (id, descripcion_corta, descripcion) VALUES (2, 'Inactiva', 'Tarjeta Inactiva');


-- ------------------------------------------------------------------------------------------------------------------ --
-- INSERTS INICIALES EN TABLA - tk_estatus_pago
-- ------------------------------------------------------------------------------------------------------------------ --
INSERT INTO tk_estatus_pago (id, descripcion_corta, descripcion) VALUES (1, 'Registrado', 'Pago Registrado');


-- ------------------------------------------------------------------------------------------------------------------ --
-- INSERTS INICIALES EN TABLA - tk_tipo_tarjeta
-- ------------------------------------------------------------------------------------------------------------------ --
INSERT INTO tk_tipo_tarjeta (id, descripcion_corta, descripcion) VALUES (1, 'Visa', 'Tarjeta Visa');
INSERT INTO tk_tipo_tarjeta (id, descripcion_corta, descripcion) VALUES (2, 'MasterCard', 'Tarjeta MasterCard');


-- ------------------------------------------------------------------------------------------------------------------ --
-- INSERTS INICIALES EN TABLA - tk_catalogo
-- ------------------------------------------------------------------------------------------------------------------ --
INSERT INTO tk_catalogo (id, descripcion_corta, descripcion, nombre_tabla, activo) VALUES
(1, 'Afiliacion', 'Catalogo de afiliaciones', 'tc_afiliacion', 1);
INSERT INTO tk_catalogo (id, descripcion_corta, descripcion, nombre_tabla, activo) VALUES
(2, 'Estatus Tarjeta', 'Catalogo de estatus de tarjetas', 'tk_estatus_tarjeta', 1);
INSERT INTO tk_catalogo (id, descripcion_corta, descripcion, nombre_tabla, activo) VALUES
(3, 'Estatus Transaccion', 'Catalogo de estatus de transacciones', 'tk_estatus_transaccion', 1);
INSERT INTO tk_catalogo (id, descripcion_corta, descripcion, nombre_tabla, activo) VALUES
(4, 'Tipo Tarjeta', 'Catalogo de tipos de tarjetas', 'tk_tipo_tarjeta', 1);
INSERT INTO tk_catalogo (id, descripcion_corta, descripcion, nombre_tabla, activo) VALUES
(5, 'Categoria', 'Catalogo de categorias de codigos de estados de cuenta', 'tk_categoria', 1);
-- // TODO - OJO:: CHECAR LOS SIGUIENTES
/*(3, 'Afiliacion', 'Catalogo de afiliaciones', 'tc_afiliacion', 1),
(4, 'EstatusPago', 'Estatus de las transacciones o pagos', 'tk_estatus_transaccion', 1),
(5, 'ReglaNegocio', 'Reglas de negocio', 'tk_regla_negocio', 1),
(6, 'Variable', 'Variables para la aplicacion', 'tk_variable', 1),
(7, 'Categoria', 'Categorias de los codigos de estado de cuenta', 'tk_categoria', 1);*/


-- ------------------------------------------------------------------------------------------------------------------ --
-- INSERTS INICIALES EN TABLA - tk_estatus_operacion
-- ------------------------------------------------------------------------------------------------------------------ --
INSERT INTO tk_estatus_operacion (id, descripcion_corta, descripcion) VALUES
(1, 'Operacion Exitosa', 'Operacion realizada de manera exitosa');
INSERT INTO tk_estatus_operacion (id, descripcion_corta, descripcion) VALUES
(2, 'Operacion Fallida', 'Operacion realizada de manera fallida');


-- ------------------------------------------------------------------------------------------------------------------ --
-- MODULO DSS
-- ------------------------------------------------------------------------------------------------------------------ --
-- // TODO - Revisar si esta parte no se debe mover al script del schema.sql
-- TRUNCATE PARA TABLAS ASOCIADAS A DSS --
DELETE FROM tr_regla_negocio_variable;
DELETE FROM tr_regla_negocio_tipo_autorizacion;
SET FOREIGN_KEY_CHECKS = 0;
DELETE FROM tk_regla_negocio;
DELETE FROM tk_variable;
SET FOREIGN_KEY_CHECKS = 1;
-- FINALIZA TRUNCATE PARA TABLAS ASOCIADAS A DSS --


-- ------------------------------------------------------------------------------------------------------------------ --
-- INSERTS INICIALES EN TABLA - tk_tipo_autorizacion - SE USAN EN MODULO DSS
-- ------------------------------------------------------------------------------------------------------------------ --
INSERT INTO tk_tipo_autorizacion (id, descripcion, descripcion_corta) VALUES (1, 'Ningun tipo de afiliacion', 'Ninguno');
INSERT INTO tk_tipo_autorizacion (id, descripcion, descripcion_corta) VALUES (2, '3D Secure', '3D Secure');


-- ------------------------------------------------------------------------------------------------------------------ --
-- INSERTS INICIALES EN TABLA - tk_variable
-- ------------------------------------------------------------------------------------------------------------------ --
-- REGLA NEGOCIO - 1
INSERT INTO tk_variable (id_variable, clave, valor) VALUES (1, '{totalIni}', '10');
INSERT INTO tk_variable (id_variable, clave, valor) VALUES (2, '{totalFin}', '100');
-- REGLA NEGOCIO - 2
INSERT INTO tk_variable (id_variable, clave, valor) VALUES (3, '{sumaIni}', '10000');
INSERT INTO tk_variable (id_variable, clave, valor) VALUES (4, '{sumaFin}', '100000');
-- REGLA NEGOCIO - 3
INSERT INTO tk_variable (id_variable, clave, valor) VALUES (5, '{idTipoAutorizacion}', '2');
INSERT INTO tk_variable (id_variable, clave, valor) VALUES (6, '{conteo}', '20');


-- ------------------------------------------------------------------------------------------------------------------ --
-- INSERTS INICIALES EN TABLA - tk_regla_negocio
-- ------------------------------------------------------------------------------------------------------------------ --
INSERT INTO tk_regla_negocio (id, nombre, descripcion, consulta) VALUES
(1, 'Regla de Negocio 1', 'Evalua suma de montos con un monto total variable', 'SELECT CASE WHEN (SELECT COUNT(DISTINCT p.id_transaccion_midas) AS TOTAL FROM to_pagos p WHERE p.id_cliente = {idCliente}) BETWEEN {totalIni} AND {totalFin} THEN TRUE ELSE FALSE END AS ESTATUS');
INSERT INTO tk_regla_negocio (id, nombre, descripcion, consulta) VALUES
(2, 'Regla de Negocio 2', 'Evalua cantidad de transacciones con cantidad variable', 'SELECT CASE WHEN (SELECT SUM(p.monto) AS SUMA FROM to_pagos p WHERE p.id_cliente = {idCliente}) BETWEEN {sumaIni} AND {sumaFin} THEN TRUE ELSE FALSE END AS ESTATUS');
INSERT INTO tk_regla_negocio (id, nombre, descripcion, consulta) VALUES
(3, 'Regla de Negocio 3', 'Evalua cantidad de transacciones con un determinado id de autorizacion variable', 'SELECT CASE WHEN (SELECT COUNT(DISTINCT p.id_transaccion_midas) AS CONTEO FROM to_pagos p WHERE p.id_cliente = {idCliente} AND p.id_tipo_autorizacion = {idTipoAutorizacion}) > {conteo} THEN TRUE ELSE FALSE END AS ESTATUS');


-- ------------------------------------------------------------------------------------------------------------------ --
-- INSERTS INICIALES EN TABLA RELACIONAL - tr_regla_negocio_variable
-- ------------------------------------------------------------------------------------------------------------------ --
INSERT INTO tr_regla_negocio_variable (id_regla_negocio, id_variable) VALUES (1, 1);
INSERT INTO tr_regla_negocio_variable (id_regla_negocio, id_variable) VALUES (1, 2);
INSERT INTO tr_regla_negocio_variable (id_regla_negocio, id_variable) VALUES (2, 3);
INSERT INTO tr_regla_negocio_variable (id_regla_negocio, id_variable) VALUES (2, 4);
INSERT INTO tr_regla_negocio_variable (id_regla_negocio, id_variable) VALUES (3, 5);
INSERT INTO tr_regla_negocio_variable (id_regla_negocio, id_variable) VALUES (3, 6);


-- ------------------------------------------------------------------------------------------------------------------ --
-- INSERTS INICIALES EN TABLA RELACIONAL - tr_regla_negocio_tipo_autorizacion
-- ------------------------------------------------------------------------------------------------------------------ --
INSERT INTO tr_regla_negocio_tipo_autorizacion (id_regla_negocio, id_tipo_autorizacion) VALUES (1, 2);
INSERT INTO tr_regla_negocio_tipo_autorizacion (id_regla_negocio, id_tipo_autorizacion) VALUES (2, 2);
INSERT INTO tr_regla_negocio_tipo_autorizacion (id_regla_negocio, id_tipo_autorizacion) VALUES (3, 2);




-- ------------------------------------------------------------------------------------------------------------------ --
-- FASE 2 - CATALOGOS ----------------------------------------------------------------------------------------------- --
-- ------------------------------------------------------------------------------------------------------------------ --


-- ------------------------------------------------------------------------------------------------------------------ --
-- INSERTS INICIALES EN TABLA - tk_tipo_contacto
-- ------------------------------------------------------------------------------------------------------------------ --
INSERT INTO tk_tipo_contacto (id, estatus, description, created_by, created_date, last_modified_by, last_modified_date, short_description) VALUES
(1, true, 'Contacto MIDAS', 'Sistema', now(), null, null, 'CTO_MIDAS');
INSERT INTO tk_tipo_contacto (id, estatus, description, created_by, created_date, last_modified_by, last_modified_date, short_description) VALUES
(2, true, 'Contacto Entidad', 'Sistema', now(), null, null, 'CTO_ENT');


-- ------------------------------------------------------------------------------------------------------------------ --
-- INSERTS INICIALES EN TABLA - tk_categoria
-- ------------------------------------------------------------------------------------------------------------------ --
DELETE FROM tk_categoria;
INSERT INTO tk_categoria (id, nombre, descripcion, estatus, created_date, last_modified_date, created_by, last_modified_by, descripcion_corta) VALUES
(1, 'Ventas', 'Categoria Ventas', true, now(), null, 'Sistema', null, 'CAT_VTAS');
INSERT INTO tk_categoria (id, nombre, descripcion, estatus, created_date, last_modified_date, created_by, last_modified_by, descripcion_corta) VALUES
(2, 'Comisiones', 'Categoria Comisiones', true, now(), null, 'Sistema', null, 'CAT_COM');
INSERT INTO tk_categoria (id, nombre, descripcion, estatus, created_date, last_modified_date, created_by, last_modified_by, descripcion_corta) VALUES
(3, 'Cargos', 'Categoria Cargos', true, now(), null, 'Sistema', null, 'CAT_CAR');
INSERT INTO tk_categoria (id, nombre, descripcion, estatus, created_date, last_modified_date, created_by, last_modified_by, descripcion_corta) VALUES
(4, 'Abonos', 'Categoria Abonos', true, now(), null, 'Sistema', null, 'CAT_ABO');
INSERT INTO tk_categoria (id, nombre, descripcion, estatus, created_date, last_modified_date, created_by, last_modified_by, descripcion_corta) VALUES
(5, 'IVA Comision', 'IVA Comision', true, now(), null, 'Sistema', null, 'CAT_ICOM');
INSERT INTO tk_categoria (id, nombre, descripcion, estatus, created_date, last_modified_date, created_by, last_modified_by, descripcion_corta) VALUES
(6, 'Devolucion', 'Devolucion', true, now(), null, 'Sistema', null, 'CAT_DEV');


-- ------------------------------------------------------------------------------------------------------------------ --
-- INSERTS INICIALES EN TABLA - tc_entidad - PARA PODER RELACIONAR LOS CODIGOS
-- ------------------------------------------------------------------------------------------------------------------ --
DELETE FROM tc_entidad;
INSERT INTO tc_entidad (id, nombre, description, estatus, created_date, last_modified_date, created_by, last_modified_by, short_description) VALUES
(1, 'Entidad Default', 'Entidad default', true, now(), null, 'Sistema', null, null);


-- ------------------------------------------------------------------------------------------------------------------ --
-- INSERTS INICIALES EN TABLA - tc_codigo_estado_cuenta
-- ------------------------------------------------------------------------------------------------------------------ --
DELETE FROM tc_codigo_estado_cuenta;
INSERT INTO tc_codigo_estado_cuenta (codigo, id_categoria, id_entidad, description, estatus, created_date, last_modified_date, created_by, last_modified_by, short_description) VALUES
('V01', 1, 1, 'VENTAS TARJETAS BANCARIAS', true, now(), null, 'Sistema', null, null);
INSERT INTO tc_codigo_estado_cuenta (codigo, id_categoria, id_entidad, description, estatus, created_date, last_modified_date, created_by, last_modified_by, short_description) VALUES
('V02', 2, 1, 'COMISION TARJETAS', true, now(), null, 'Sistema', null, null);
INSERT INTO tc_codigo_estado_cuenta (codigo, id_categoria, id_entidad, description, estatus, created_date, last_modified_date, created_by, last_modified_by, short_description) VALUES
('V03', 5, 1, 'IVA COMISION TARJETAS', true, now(), null, 'Sistema', null, null);
INSERT INTO tc_codigo_estado_cuenta (codigo, id_categoria, id_entidad, description, estatus, created_date, last_modified_date, created_by, last_modified_by, short_description) VALUES
('V04', 6, 1, 'DEVOLUCION TARJETAS', true, now(), null, 'Sistema', null, null);
INSERT INTO tc_codigo_estado_cuenta (codigo, id_categoria, id_entidad, description, estatus, created_date, last_modified_date, created_by, last_modified_by, short_description) VALUES
('V05', 2, 1, 'COMISION DEVOLUCION', true, now(), null, 'Sistema', null, null);
INSERT INTO tc_codigo_estado_cuenta (codigo, id_categoria, id_entidad, description, estatus, created_date, last_modified_date, created_by, last_modified_by, short_description) VALUES
('V06', 5, 1, 'IVA COMISION DEVOLUCION', true, now(), null, 'Sistema', null, null);
INSERT INTO tc_codigo_estado_cuenta (codigo, id_categoria, id_entidad, description, estatus, created_date, last_modified_date, created_by, last_modified_by, short_description) VALUES
('V42', 1, 1, 'VENTAS DEBITO', true, now(), null, 'Sistema', null, null);
INSERT INTO tc_codigo_estado_cuenta (codigo, id_categoria, id_entidad, description, estatus, created_date, last_modified_date, created_by, last_modified_by, short_description) VALUES
('V43', 2, 1, 'COMISION VENTAS DEBITO', true, now(), null, 'Sistema', null, null);
INSERT INTO tc_codigo_estado_cuenta (codigo, id_categoria, id_entidad, description, estatus, created_date, last_modified_date, created_by, last_modified_by, short_description) VALUES
('V44', 5, 1, 'IVA COM. VENTAS DEBITO', true, now(), null, 'Sistema', null, null);
INSERT INTO tc_codigo_estado_cuenta (codigo, id_categoria, id_entidad, description, estatus, created_date, last_modified_date, created_by, last_modified_by, short_description) VALUES
('V45', 1, 1, 'VENTAS CREDITO', true, now(), null, 'Sistema', null, null);
INSERT INTO tc_codigo_estado_cuenta (codigo, id_categoria, id_entidad, description, estatus, created_date, last_modified_date, created_by, last_modified_by, short_description) VALUES
('V46', 2, 1, 'COMISION VENTAS CREDITO', true, now(), null, 'Sistema', null, null);
INSERT INTO tc_codigo_estado_cuenta (codigo, id_categoria, id_entidad, description, estatus, created_date, last_modified_date, created_by, last_modified_by, short_description) VALUES
('V47', 5, 1, 'IVA COM. VENTAS CREDITO', true, now(), null, 'Sistema', null, null);
INSERT INTO tc_codigo_estado_cuenta (codigo, id_categoria, id_entidad, description, estatus, created_date, last_modified_date, created_by, last_modified_by, short_description) VALUES
('V50', 2, 1, 'COM DEV TARJETA', true, now(), null, 'Sistema', null, null);




-- ------------------------------------------------------------------------------------------------------------------ --
-- FASE 3 - CONCILIACION -------------------------------------------------------------------------------------------- --
-- ------------------------------------------------------------------------------------------------------------------ --


-- ------------------------------------------------------------------------------------------------------------------ --
-- INSERTS INICIALES EN TABLA - tk_estatus_transaccion
-- ------------------------------------------------------------------------------------------------------------------ --
-- // TODO - VERIFICAR ESTE CATALOGO (EstatusPago = tk_estatus_transaccion)
INSERT INTO tk_estatus_transaccion (id, descripcion_corta, descripcion) VALUES (1, 'Solicitada', 'Solicitada');
INSERT INTO tk_estatus_transaccion (id, descripcion_corta, descripcion) VALUES (2, 'En Proceso', 'En Proceso');
INSERT INTO tk_estatus_transaccion (id, descripcion_corta, descripcion) VALUES (3, 'Terminada', 'Terminada');


-- ------------------------------------------------------------------------------------------------------------------ --
-- INSERTS INICIALES EN TABLA - tk_estatus_devolucion
-- ------------------------------------------------------------------------------------------------------------------ --
INSERT INTO tk_estatus_devolucion (id, nombre, descripcion, estatus, created_date, created_by, descripcion_corta) VALUES
('1', 'Pendiente', 'Pendiente', true, now(), 'Sistema', 'PND');
INSERT INTO tk_estatus_devolucion (id, nombre, descripcion, estatus, created_date, created_by, descripcion_corta) VALUES
('2', 'Solicitada', 'Solicitada', true, now(), 'Sistema', 'SOL');
INSERT INTO tk_estatus_devolucion (id, nombre, descripcion, estatus, created_date, created_by, descripcion_corta) VALUES
('3', 'Liquidada', 'Liquidada', true, now(), 'Sistema', 'LIQ');


-- ------------------------------------------------------------------------------------------------------------------ --
-- INSERTS INICIALES EN TABLA - tk_estatus_movimientos_en_transito
-- ------------------------------------------------------------------------------------------------------------------ --
INSERT INTO tk_estatus_movimientos_en_transito (id, nombre, descripcion, estatus, created_date, last_modified_date, created_by, last_modified_by, descripcion_corta) VALUES
(1, 'No identificada en MIDAS', 'No identificada en MIDAS', true, now(), NULL, 'Sistema', NULL, 'NOID');
INSERT INTO tk_estatus_movimientos_en_transito (id, nombre, descripcion, estatus, created_date, last_modified_date, created_by, last_modified_by, descripcion_corta) VALUES
(2, 'Solicitada', 'Solicitada', true, now(), NULL, 'Sistema', NULL, 'SOL');
INSERT INTO tk_estatus_movimientos_en_transito (id, nombre, descripcion, estatus, created_date, last_modified_date, created_by, last_modified_by, descripcion_corta) VALUES
(4, 'Marcada devolucion', 'Marcada para devolucion', true, now(), NULL, 'Sistema', NULL, 'MDEV');


-- ------------------------------------------------------------------------------------------------------------------ --
-- INSERTS INICIALES EN TABLA - tk_operacion
-- ------------------------------------------------------------------------------------------------------------------ --
INSERT INTO tk_operacion (id, tipo, abreviatura, descripcion, inddep) VALUES
(8, 1, 'RF', 'Cobro Refrendo', 0);
INSERT INTO tk_operacion (id, tipo, abreviatura, descripcion, inddep) VALUES
(116, 1, 'APL', 'Abono - Pagos Libres', 0);
INSERT INTO tk_operacion (id, tipo, abreviatura, descripcion, inddep) VALUES
(148, 1, 'DSO', 'Cobro Desempeño en Linea', 0);


-- ------------------------------------------------------------------------------------------------------------------ --
-- INSERTS INICIALES EN TABLA - tk_tipo_contrato
-- ------------------------------------------------------------------------------------------------------------------ --
INSERT INTO tk_tipo_contrato (id, descripcion, baja_logica, abreviatura, inddep) VALUES
(145, 'CLASICO', 'f', 'CL', 0);
INSERT INTO tk_tipo_contrato (id, descripcion, baja_logica, abreviatura, inddep) VALUES
(146, 'PAGOS LIBRES', 'f', 'PL', 0);


-- ------------------------------------------------------------------------------------------------------------------ --
-- INSERTS INICIALES EN TABLA - tk_estatus_conciliacion
-- ------------------------------------------------------------------------------------------------------------------ --
INSERT INTO tk_estatus_conciliacion (id, nombre, descripcion, estatus, created_date, created_by, descripcion_corta, order_number) VALUES
(1, 'En Proceso', 'En Proceso', true, now(), 'Sistema', 'En Proceso', 1);
INSERT INTO tk_estatus_conciliacion (id, nombre, descripcion, estatus, created_date, created_by, descripcion_corta, order_number) VALUES
(2, 'Finalizada', 'Finalizada', true, now(), 'Sistema', 'Finalizada', 2);


-- ------------------------------------------------------------------------------------------------------------------ --
-- INSERTS INICIALES EN TABLA - tk_sub_estatus_conciliacion
-- ------------------------------------------------------------------------------------------------------------------ --
INSERT INTO tk_sub_estatus_conciliacion (id, description, estatus, created_date, created_by, order_number) VALUES
(1, 'Creada', true, now(), 'Sistema', 1);
INSERT INTO tk_sub_estatus_conciliacion (id, description, estatus, created_date, created_by, order_number) VALUES
(2, 'Consulta MIDAS', true, now(), 'Sistema', 2);
INSERT INTO tk_sub_estatus_conciliacion (id, description, estatus, created_date, created_by, order_number) VALUES
(3, 'Consulta MIDAS Completada', true, now(), 'Sistema', 3);
INSERT INTO tk_sub_estatus_conciliacion (id, description, estatus, created_date, created_by, order_number) VALUES
(4, 'Consulta MIDAS Error', true, now(), 'Sistema', 4);
INSERT INTO tk_sub_estatus_conciliacion (id, description, estatus, created_date, created_by, order_number) VALUES
(5, 'Consulta Open Pay', true, now(), 'Sistema', 5);
INSERT INTO tk_sub_estatus_conciliacion (id, description, estatus, created_date, created_by, order_number) VALUES
(6, 'Consulta Open Pay Completada', true, now(), 'Sistema', 6);
INSERT INTO tk_sub_estatus_conciliacion (id, description, estatus, created_date, created_by, order_number) VALUES
(7, 'Consulta Open Pay Error', true, now(), 'Sistema', 7);
INSERT INTO tk_sub_estatus_conciliacion (id, description, estatus, created_date, created_by, order_number) VALUES
(8, 'Conciliacion', true, now(), 'Sistema', 8);
INSERT INTO tk_sub_estatus_conciliacion (id, description, estatus, created_date, created_by, order_number) VALUES
(9, 'Conciliacion Completada', true, now(), 'Sistema', 9);
INSERT INTO tk_sub_estatus_conciliacion (id, description, estatus, created_date, created_by, order_number) VALUES
(10, 'Conciliacion Error', true, now(), 'Sistema', 10);
INSERT INTO tk_sub_estatus_conciliacion (id, description, estatus, created_date, created_by, order_number) VALUES
(11, 'Consulta Estado de Cuenta', true, now(), 'Sistema', 11);
INSERT INTO tk_sub_estatus_conciliacion (id, description, estatus, created_date, created_by, order_number) VALUES
(12, 'Consulta Estado de Cuenta Completada', true, now(), 'Sistema', 12);
INSERT INTO tk_sub_estatus_conciliacion (id, description, estatus, created_date, created_by, order_number) VALUES
(13, 'Consulta Estado de Cuenta Error', true, now(), 'Sistema', 13);
INSERT INTO tk_sub_estatus_conciliacion (id, description, estatus, created_date, created_by, order_number) VALUES
(14, 'Enviada', true, now(), 'Sistema', 14);
INSERT INTO tk_sub_estatus_conciliacion (id, description, estatus, created_date, created_by, order_number) VALUES
(15, 'Enviada Error', true, now(), 'Sistema', 15);
INSERT INTO tk_sub_estatus_conciliacion (id, description, estatus, created_date, created_by, order_number) VALUES
(16, 'Finalizada', true, now(), 'Sistema', 16);


-- ------------------------------------------------------------------------------------------------------------------ --
-- INSERTS INICIALES EN TABLA RELACIONAL - tr_estatus_conciliacion_sub_estatus_conciliacion
-- ------------------------------------------------------------------------------------------------------------------ --
INSERT INTO tr_estatus_conciliacion_sub_estatus_conciliacion (id_estatus, id_sub_estatus) VALUES (1, 1);
INSERT INTO tr_estatus_conciliacion_sub_estatus_conciliacion (id_estatus, id_sub_estatus) VALUES (1, 2);
INSERT INTO tr_estatus_conciliacion_sub_estatus_conciliacion (id_estatus, id_sub_estatus) VALUES (1, 3);
INSERT INTO tr_estatus_conciliacion_sub_estatus_conciliacion (id_estatus, id_sub_estatus) VALUES (1, 4);
INSERT INTO tr_estatus_conciliacion_sub_estatus_conciliacion (id_estatus, id_sub_estatus) VALUES (1, 5);
INSERT INTO tr_estatus_conciliacion_sub_estatus_conciliacion (id_estatus, id_sub_estatus) VALUES (1, 6);
INSERT INTO tr_estatus_conciliacion_sub_estatus_conciliacion (id_estatus, id_sub_estatus) VALUES (1, 7);
INSERT INTO tr_estatus_conciliacion_sub_estatus_conciliacion (id_estatus, id_sub_estatus) VALUES (1, 8);
INSERT INTO tr_estatus_conciliacion_sub_estatus_conciliacion (id_estatus, id_sub_estatus) VALUES (1, 9);
INSERT INTO tr_estatus_conciliacion_sub_estatus_conciliacion (id_estatus, id_sub_estatus) VALUES (1, 10);
INSERT INTO tr_estatus_conciliacion_sub_estatus_conciliacion (id_estatus, id_sub_estatus) VALUES (1, 11);
INSERT INTO tr_estatus_conciliacion_sub_estatus_conciliacion (id_estatus, id_sub_estatus) VALUES (1, 12);
INSERT INTO tr_estatus_conciliacion_sub_estatus_conciliacion (id_estatus, id_sub_estatus) VALUES (1, 13);
INSERT INTO tr_estatus_conciliacion_sub_estatus_conciliacion (id_estatus, id_sub_estatus) VALUES (1, 14);
INSERT INTO tr_estatus_conciliacion_sub_estatus_conciliacion (id_estatus, id_sub_estatus) VALUES (1, 15);
INSERT INTO tr_estatus_conciliacion_sub_estatus_conciliacion (id_estatus, id_sub_estatus) VALUES (2, 16);
