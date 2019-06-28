-- ------------------------------------------------------------------------------------------------------------
-- FASE 1 PAGOS -----------------------------------------------------------------------------------------------
-- ------------------------------------------------------------------------------------------------------------

-- INSERCION INICIAL EN CATALOGO tk_estatus_tarjeta
INSERT INTO tk_estatus_tarjeta(id, descripcion_corta, descripcion) VALUES
(1, 'Activa', 'Tarjeta Activa'),
(2, 'Inactiva', 'Tarjeta Inactiva');

-- INSERCION INICIAL EN CATALOGO tk_estatus_transaccion (Estatus Pago)
INSERT INTO tk_estatus_pago(id, descripcion_corta, descripcion) VALUES
(1,'Registrado','Pago Registrado');

-- INSERCION INICIAL EN CATALOGO tk_tipo_tarjeta
INSERT INTO tk_tipo_tarjeta(id, descripcion_corta, descripcion) VALUES
(1, 'Visa', 'Tarjeta Visa'),
(2, 'MasterCard', 'Tarjeta MasterCard');

-- INSERCION INICIAL EN CATALOGO TK_CATALOGO
INSERT INTO tk_catalogo(id, descripcion_corta,descripcion, nombre_tabla, activo) VALUES
(1,'Afiliacion','El catalog de afiliaicones','tc_afiliacion',1),
(2,'Estatus Tarjeta','Catalogo de estatus de tarjeta posibles','tk_estatus_tarjeta',1),
(3,'Estatus Transaccion','Estatus de transacciones posibles','tk_estatus_transaccion',1),
(4,'Tipo Tarjeta','Catalogo de tipos de tarjetas','tk_tipo_tarjeta',1),
(5,'Categoria','Categorias de codigos de estados de cuenta','tk_categoria',1)
-- OJO:: CHECAR LOS SIGUIENTES
/*(3, 'Afiliacion', 'Catalogo de afiliaciones', 'tc_afiliacion', 1),
(4, 'EstatusPago', 'Estatus de las transacciones o pagos', 'tk_estatus_transaccion', 1),
(5, 'ReglaNegocio', 'Reglas de negocio', 'tk_regla_negocio', 1),
(6, 'Variable', 'Variables para la aplicacion', 'tk_variable', 1),
(7, 'Categoria', 'Categorias de los codigos de estado de cuenta', 'tk_categoria', 1)*/;

--- INSERCION INICIAL EN CATALOG DE ESTATUS DE OPERACIONES
INSERT INTO tk_estatus_operacion(id, descripcion_corta, descripcion) VALUES
(1, "Operacion Exitosa", "Operacion realizada de manera exitosa"),
(2, "Operacion Fallida", "Operacion ralizada de manera fallida");

-- ------------------------------------------------------ --
-- ------------------ MODULO DSS ------------------------ --
-- ------------------------------------------------------ --
-- TRUNCATE PARA TABLAS ASOCIADAS A DSS
TRUNCATE TABLE tr_regla_negocio_variable;
TRUNCATE TABLE tr_regla_negocio_tipo_autorizacion;
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE tk_regla_negocio;
TRUNCATE TABLE tk_variable;
SET FOREIGN_KEY_CHECKS = 1;
-- FINALIZA TRUNCATE PARA TABLAS ASOCIADAS A DSS

-- INSERCIONES EN CATALGO DE TIPOS (SE USAN EN MODULO DSS)
INSERT INTO tk_tipo_autorizacion(id, descripcion, descripcion_corta) 
	VALUES(1, 'Ningun tipo de afiliacion', 'Niguna')
    , (2, '3D Secure', '3D Secure');
    
-- VARIABLES
-- Insercion Regla Negocio 1
INSERT INTO tk_variable(id_variable, clave, valor) VALUES(1, '{totalIni}', '10');
INSERT INTO tk_variable(id_variable, clave, valor) VALUES(2, '{totalFin}', '100');
-- Insercion Regla Negocio 2
INSERT INTO tk_variable(id_variable, clave, valor) VALUES(3, '{sumaIni}', '10000');
INSERT INTO tk_variable(id_variable, clave, valor) VALUES(4, '{sumaFin}', '100000');
-- Insercion Regla Negocio 3
INSERT INTO tk_variable(id_variable, clave, valor) VALUES(5, '{idTipoAutorizacion}', '2');
INSERT INTO tk_variable(id_variable, clave, valor) VALUES(6, '{conteo}', '20');

-- REGLAS DE NEGOCIO
-- REGLA DE NEGOCIO 1, 2 Y 3
INSERT INTO tk_regla_negocio(id, nombre, descripcion, consulta) 
	VALUES(1, 'Regla de Negocio 1', 'Evalua suma de montos con un monto total variable', 'SELECT CASE WHEN (SELECT COUNT(DISTINCT p.id_transaccion_midas) AS TOTAL FROM to_pagos p WHERE p.id_cliente = {idCliente}) BETWEEN {totalIni} AND {totalFin} THEN TRUE ELSE FALSE END AS ESTATUS')
		, (2, 'Regla de Negocio 2', 'Evalua cantidad de transacciones con cantidad variable', 'SELECT CASE WHEN (SELECT SUM(p.monto) AS SUMA FROM to_pagos p WHERE p.id_cliente = {idCliente}) BETWEEN {sumaIni} AND {sumaFin} THEN TRUE ELSE FALSE END AS ESTATUS')
        , (3, 'Regla de Negocio 3', 'Evalua cantidad de transacciones con un determinado id de autorizacion variable', 'SELECT CASE WHEN (SELECT COUNT(DISTINCT p.id_transaccion_midas) AS CONTEO FROM to_pagos p WHERE p.id_cliente = {idCliente} AND p.id_tipo_autorizacion = {idTipoAutorizacion}) > {conteo} THEN TRUE ELSE FALSE END AS ESTATUS');

-- INSERCION DE ASOCIACION REGLAS DE NEGOCIO - VARIABLE
INSERT INTO tr_regla_negocio_variable(id_regla_negocio, id_variable) VALUES(1,1), (1,2), (2,3), (2,4), (3,5), (3,6);

-- INSERCION DE ASOCIACION ENTRE REGLAS DE NEGOCIO Y TIPOS DE AUTORIZACION
INSERT INTO tr_regla_negocio_tipo_autorizacion(id_regla_negocio, id_tipo_autorizacion) VALUES(1,2), (2,2), (3,2);




-- ------------------------------------------------------------------------------------------------------------
-- FASE 2 CATALOGOS -------------------------------------------------------------------------------------------
-- ------------------------------------------------------------------------------------------------------------

-- INSERCION INICIAL DE TIPOS DE CONTACTOS --
INSERT INTO `tk_tipo_contacto` (`id`, `estatus`, `description`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`, `short_description`) VALUES
(1, true, 'Contacto Midas', 'Quarksoft', now(), null, null, 'Contacto Midas'),
(2, true, 'Contacto Entidad', 'Quarksoft', now(), null, null, 'Contacto Ent');

-- INSERCION DE TIPOS DE AFILIACION (SE USA EN MODULO DSS)--
-- INSERT INTO `tk_tipo_afiliacion` (`id`, `descripcion_corta`, `descripcion`) VALUES
-- (1, 'Ninguno', 'Ningun tipo de afiliacion'),
-- (2, '3DSecure', '3DSecure');


-- INSERCION DE CATEGORIAS --
DELETE FROM `tk_categoria`;
INSERT INTO `tk_categoria` (`id`, `nombre`, `descripcion`, `estatus`, `created_date`, `last_modified_date`, `created_by`, `last_modified_by`, `descripcion_corta`) VALUES
(1, 'Ventas', 'Categoria Ventas', true, now(), null, 'Quarksoft', null, 'Cat Vtas'),
(2, 'Comisiones', 'Categoria Comisiones', true, now(), null, 'Quarksoft', null, 'Cat Com'),
(3, 'Cargos', 'Categoria Cargos', true, now(), null, 'Quarksoft', null, 'Cat Car'),
(4, 'Abonos', 'Categoria Abono', true, now(), null, 'Quarksoft', null, 'Cat Abo'),
(5, 'Iva Comision', 'Iva Comision', true, now(), null, 'Quarksoft', null, 'Cat IC'),
(6, 'Devolucion', 'Devolucion', true, now(), null, 'Quarksoft', null, 'Cat Dev');


-- Insercion de Entidad de prueba para relacionar codigos ---
DELETE FROM `tc_entidad`;
INSERT INTO `tc_entidad` (`id`, `nombre`, `description`, `estatus`, `created_date`, `last_modified_date`, `created_by`, `last_modified_by`, `short_description`) VALUES
(1, "Banco 1", "Banco 1", true, now(), null, "Quarksoft", null, null);


-- Insercion codigos de estados de cuenta inicial
DELETE FROM `tc_codigo_estado_cuenta`;
INSERT INTO `tc_codigo_estado_cuenta` (`codigo`, `id_categoria`, `id_entidad`, `description`, `estatus`, `created_date`, `last_modified_date`, `created_by`, `last_modified_by`, `short_description`) VALUES
("V01", 1, 1, "VENTAS TARJETAS BANCARIAS", true, now(), null, "Quarksoft", null, null),
("V02", 2, 1, "COMISION TARJETAS", true, now(), null, "Quarksoft", null, null),
("V03", 5, 1, "IVA COMISION TARJETAS", true, now(), null, "Quarksoft", null, null),
("V04", 6, 1, "DEVOLUCION TARJETAS", true, now(), null, "Quarksoft", null, null),
("V05", 2, 1, "COMISION DEVOLUCION", true, now(), null, "Quarksoft", null, null),
("V06", 5, 1, "IVA COMISION DEVOLUCION", true, now(), null, "Quarksoft", null, null),
("V42", 1, 1, "VENTAS DEBITO", true, now(), null, "Quarksoft", null, null),
("V43", 2, 1, "COMISION VENTAS DEBITO", true, now(), null, "Quarksoft", null, null),
("V44", 5, 1, "IVA COM. VENTAS DEBITO", true, now(), null, "Quarksoft", null, null),
("V45", 1, 1, "VENTAS CREDITO", true, now(), null, "Quarksoft", null, null),
("V46", 2, 1, "COMISION VENTAS CREDITO", true, now(), null, "Quarksoft", null, null),
("V47", 5, 1, "IVA COM. VENTAS CREDITO", true, now(), null, "Quarksoft", null, null),
("V50", 2, 1, "COM DEV TARJETA", true, now(), null, "Quarksoft", null, null);




-- ------------------------------------------------------------------------------------------------------------
-- FASE 3 CONCILIACION ----------------------------------------------------------------------------------------
-- ------------------------------------------------------------------------------------------------------------

-- ------------------------------------------------------------ --
-- INSERT PARA CATALOGO NO ADMINISTRABLE tk_estatus_transaccion --
-- ------------------ [2019-06-07 16:51:31] ------------------- --
-- ------------------------------------------------------------ --
-- // VERIFICAR ESTE CATALOGO (EstatusPago = tk_estatus_transaccion)
INSERT INTO `tk_estatus_transaccion` (`id`, `descripcion_corta`, `descripcion`) VALUES
(1, 'Solicitada', 'Solicitada'),
(2, 'En Proceso', 'En Proceso'),
(3, 'Terminada', 'Terminada');

INSERT INTO `tk_estatus_devolucion` (`id`, `nombre`, `descripcion`, `estatus`, `created_date`, `created_by`, `descripcion_corta`)
VALUES ('1', 'devolucion', 'devolucion', true, '2019-05-16 16:46:23', 'NMP', 'devolucion');

-- ------------------------------------------------------------------------ --
-- INSERT PARA CATALOGO NO ADMINISTRABLE tk_estatus_movimientos_en_transito --
-- ------------------------ [2019-06-07 16:51:31] -------------------------- --
-- ------------------------------------------------------------------------- --    
INSERT INTO `tk_estatus_movimientos_en_transito` (`id`, `nombre`, `descripcion`, `estatus`, `created_date`, `last_modified_date`, `created_by`, `last_modified_by`, `descripcion_corta`) VALUES
(1, 'No identificada en MIDAS', 'No identificada en MIDAS', true, NOW(), NULL, 'Quarksoft', NULL, 'No identificada en MIDAS'),
(2, 'Solicitada', 'Solicitada', true, NOW(), NULL, 'Quarksoft', NULL, 'Solicitada');

-- --------------------------------------- --
-- ----- INSERTS INICIALES EN tk_operacion --
-- --------------------------------------- --
INSERT INTO `tk_operacion` (`id`, `tipo`, `abreviatura`, `descripcion`, `inddep`) VALUES
(8, 1, 'RF', 'Cobro Refrendo', 0),
(116, 1, 'APL', 'Abono -PagosLibres', 0),
(148, 1, 'DSO', 'Cobro Desempeño en Linea', 0);
    
-- ------------------------------------------- --
-- ----- INSERTS INICIALES EN tk_tipo_contrato --
-- ------------------------------------------- --
INSERT INTO `tk_tipo_contrato` (`id`, `descripcion`, `baja_logica`, `abreviatura`, `inddep`) VALUES
(146, 'PAGOS LIBRES', 'f', 'PL', 0),
(145, 'CLASICO', 'f', 'CL', 0);    

INSERT INTO `tk_estatus_conciliacion` (`id`, `nombre`, `descripcion`, `estatus`, `created_date`, `created_by`, `descripcion_corta`, `order_number`) VALUES
(1, 'En Proceso', 'En Proceso', true, now(), 'Sistema', 'En Proceso', 1),
(2, 'Finalizada', 'Finalizada', true, now(), 'Sistema', 'Finalizada', 2);

INSERT INTO `tk_sub_estatus_conciliacion` (id, `description`, `estatus`, `created_date`, `created_by`, `order_number`) VALUES (1, 'Creada', true, now(), 'Sistema', 1);
INSERT INTO `tk_sub_estatus_conciliacion` (id, `description`, `estatus`, `created_date`, `created_by`, `order_number`) VALUES (2, 'Consulta Midas', true, now(), 'Sistema', 2);
INSERT INTO `tk_sub_estatus_conciliacion` (id, `description`, `estatus`, `created_date`, `created_by`, `order_number`) VALUES (3, 'Consulta Midas Completada', true, now(), 'Sistema', 3);
INSERT INTO `tk_sub_estatus_conciliacion` (id, `description`, `estatus`, `created_date`, `created_by`, `order_number`) VALUES (4, 'Consulta Midas Error', true, now(), 'Sistema', 4);
INSERT INTO `tk_sub_estatus_conciliacion` (id, `description`, `estatus`, `created_date`, `created_by`, `order_number`) VALUES (5, 'Consulta Open Pay', true, now(), 'Sistema', 5);
INSERT INTO `tk_sub_estatus_conciliacion` (id, `description`, `estatus`, `created_date`, `created_by`, `order_number`) VALUES (6, 'Consulta Open Pay Completada', true, now(), 'Sistema', 6);
INSERT INTO `tk_sub_estatus_conciliacion` (id, `description`, `estatus`, `created_date`, `created_by`, `order_number`) VALUES (7, 'Consulta Open Pay Error', true, now(), 'Sistema', 7);
INSERT INTO `tk_sub_estatus_conciliacion` (id, `description`, `estatus`, `created_date`, `created_by`, `order_number`) VALUES (8, 'Conciliacion', true, now(), 'Sistema', 8);
INSERT INTO `tk_sub_estatus_conciliacion` (id, `description`, `estatus`, `created_date`, `created_by`, `order_number`) VALUES (9, 'Conciliacion Completada', true, now(), 'Sistema', 9);
INSERT INTO `tk_sub_estatus_conciliacion` (id, `description`, `estatus`, `created_date`, `created_by`, `order_number`) VALUES (10, 'Conciliacion Error', true, now(), 'Sistema', 10);
INSERT INTO `tk_sub_estatus_conciliacion` (id, `description`, `estatus`, `created_date`, `created_by`, `order_number`) VALUES (11, 'Consulta Estado de Cuenta', true, now(), 'Sistema', 11);
INSERT INTO `tk_sub_estatus_conciliacion` (id, `description`, `estatus`, `created_date`, `created_by`, `order_number`) VALUES (12, 'Consulta Estado de Cuenta Completada', true, now(), 'Sistema', 12);
INSERT INTO `tk_sub_estatus_conciliacion` (id, `description`, `estatus`, `created_date`, `created_by`, `order_number`) VALUES (13, 'Consulta Estado de Cuenta Error', true, now(), 'Sistema', 13);
INSERT INTO `tk_sub_estatus_conciliacion` (id, `description`, `estatus`, `created_date`, `created_by`, `order_number`) VALUES (14, 'Enviada', true, now(), 'Sistema', 14);
INSERT INTO `tk_sub_estatus_conciliacion` (id, `description`, `estatus`, `created_date`, `created_by`, `order_number`) VALUES (15, 'Enviada Error', true, now(), 'Sistema', 15);
INSERT INTO `tk_sub_estatus_conciliacion` (id, `description`, `estatus`, `created_date`, `created_by`, `order_number`) VALUES (16, 'Finalizada', true, now(), 'Sistema', 16);

-- --------------------------------------------------------------------------------- --
-- INSERCIONES EN TABLA RELACIONAL PARA SABER CUAL ES LA ASOCIAN ESTATUS - SUB ESTATUS
-- --------------------------------------------------------------------------------- --
INSERT INTO tr_estatus_conciliacion_sub_estatus_conciliacion(id_estatus, id_sub_estatus) 
	VALUES
		(1,1),
		(1,2),
		(1,3),
		(1,4),
		(1,5),
		(1,6),
		(1,7),
		(1,8),
		(1,9),
		(1,10),
		(1,11),
		(1,12),
		(1,13),
		(1,14),
		(1,15),
		(2,16);
