--
-- Proyecto:        NMP - MIDAS - OXXO.
-- Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
--



-- ------------------------------------------------------------------------------------------------------------------ --
-- BORRADO DE TABLA - tk_operacion
-- ------------------------------------------------------------------------------------------------------------------ --
DELETE FROM tk_operacion WHERE id = 198;

-- ------------------------------------------------------------------------------------------------------------------ --
-- -------------- Borrado de sequancias de corresponsales ---------------
-- ------------------------------------------------------------------------------------------------------------------ --
DELETE FROM seq_conciliacion WHERE seq_name = 'OPENPAY';
DELETE FROM seq_conciliacion WHERE seq_name = 'OXXO';

-- ------------------------------------------------------------------------------------------------------------------ --
-- -------------- Borrado de comision proveedor 1 ---------------
-- ------------------------------------------------------------------------------------------------------------------ --
DELETE FROM tc_comision_proveedor WHERE id = '1';

-- ------------------------------------------------------------------------------------------------------------------ --
-- -------------- Borrado de bonificacion proveedor 1 ---------------
-- ------------------------------------------------------------------------------------------------------------------ --
DELETE FROM tk_estatus_bonificacion WHERE id>0;


-- DELETE NECESARIO EN ESTE ARCHIVO POR INTEGRIDAD REFERENCIAL EN FUTUROS INDICES
DELETE FROM tk_maquina_estados_subestatus_conciliacion WHERE ID BETWEEN 30 AND 58;

-- ------------------- SE AGREGA LA NUEVA COLUMNA DE CORRESPONSAL ------------ --
ALTER TABLE tk_maquina_estados_subestatus_conciliacion DROP COLUMN corresponsal;

DROP TABLE to_movimiento_bonificacion_referencia;
DROP TABLE to_movimiento_bonificacion;

DROP TABLE tk_estatus_bonificacion;

DROP TABLE tc_comision_proveedor;

ALTER TABLE to_movimiento_transito DROP COLUMN transaccion;

DELETE FROM tc_layout_linea WHERE corresponsal = "OXXO";
ALTER TABLE tc_layout_linea DROP COLUMN corresponsal; 
ALTER TABLE tc_layout_linea DROP COLUMN operacion;

ALTER TABLE tc_layout_header DROP COLUMN corresponsal;

DROP TABLE tr_conciliacion_estado_cuenta;

ALTER TABLE to_movimiento_transito DROP FOREIGN KEY FK_to_movimiento_transito_to_movimiento_conciliacion;

ALTER TABLE to_movimiento_pago DROP FOREIGN KEY FK_to_movimiento_pago_to_movimiento_conciliacion;

ALTER TABLE to_movimiento_devolucion DROP FOREIGN KEY FK_to_movimiento_devolucion_to_movimiento_conciliacion;

ALTER TABLE to_movimiento_conciliacion DROP FOREIGN KEY FK_to_movimiento_conciliacion_to_conciliacion;

ALTER TABLE to_movimiento_conciliacion DROP FOREIGN KEY to_movimiento_conciliacion_ibfk_1;

ALTER TABLE to_movimiento_comision DROP FOREIGN KEY FK_comisiones_to_movimiento_conciliacion;

ALTER TABLE to_movimiento_estado_cuenta DROP FOREIGN KEY FK_to_movimiento_estado_cuenta_to_estado_cuenta;

ALTER TABLE to_estado_cuenta DROP FOREIGN KEY FK_to_estado_cuenta_to_reporte;

ALTER TABLE to_movimiento_proveedor DROP INDEX fk_to_movimiento_midas_to_reporte1;

ALTER TABLE to_movimiento_proveedor DROP FOREIGN KEY fk_to_movimiento_midas_to_reporte1;

ALTER TABLE to_movimiento_midas DROP INDEX FK_to_movimiento_midas_to_reporte;

ALTER TABLE to_movimiento_midas DROP FOREIGN KEY FK_to_movimiento_midas_to_reporte;

ALTER TABLE to_reporte DROP INDEX FK_to_carga_reporte_to_conciliacion_idx;

ALTER TABLE to_reporte DROP FOREIGN KEY FK_to_carga_reporte_to_conciliacion;

ALTER TABLE to_movimiento_proveedor CHANGE COLUMN id_reporte id_reporte INT(11) NOT NULL;

ALTER TABLE to_movimiento_midas CHANGE COLUMN id id INT(11) NOT NULL AUTO_INCREMENT,
			CHANGE COLUMN id_reporte id_reporte INT(11) NOT NULL;

ALTER TABLE to_estado_cuenta CHANGE COLUMN id id INT(11) NOT NULL AUTO_INCREMENT,
			CHANGE COLUMN reporte reporte INT(11) NULL DEFAULT NULL;

ALTER TABLE to_movimiento_estado_cuenta CHANGE COLUMN id id INT(11) NOT NULL AUTO_INCREMENT,
			CHANGE COLUMN estado_cuenta estado_cuenta INT(11) NULL DEFAULT NULL;

ALTER TABLE to_reporte CHANGE COLUMN id id INT(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `to_reporte` CHANGE COLUMN `id_conciliacion` `id_conciliacion` INT(11) NOT NULL;

ALTER TABLE to_estado_cuenta CHANGE COLUMN id id INT(11) NOT NULL;

ALTER TABLE to_movimiento_transito CHANGE COLUMN id id INT(11) NOT NULL;

ALTER TABLE to_movimiento_pago CHANGE COLUMN id id INT(11) NOT NULL;

ALTER TABLE to_movimiento_devolucion CHANGE COLUMN id id INT(11) NOT NULL;

ALTER TABLE to_movimiento_comision CHANGE COLUMN id id INT(11) NOT NULL;

ALTER TABLE to_movimiento_conciliacion CHANGE COLUMN id id INT(11) NOT NULL AUTO_INCREMENT,
			CHANGE COLUMN id_movimiento_midas id_movimiento_midas INT(11) NULL DEFAULT NULL;

ALTER TABLE to_reporte ADD KEY FK_to_carga_reporte_to_conciliacion_idx (id_conciliacion);

ALTER TABLE to_reporte ADD CONSTRAINT FK_to_carga_reporte_to_conciliacion FOREIGN KEY (id_conciliacion) REFERENCES to_conciliacion (id);

ALTER TABLE to_estado_cuenta ADD KEY FK_to_estado_cuenta_to_reporte (reporte);

ALTER TABLE to_estado_cuenta ADD CONSTRAINT FK_to_estado_cuenta_to_reporte FOREIGN KEY (reporte) REFERENCES to_reporte (id);

ALTER TABLE to_movimiento_estado_cuenta ADD KEY estado_cuenta (estado_cuenta);

ALTER TABLE to_movimiento_estado_cuenta ADD CONSTRAINT FK_to_movimiento_estado_cuenta_to_estado_cuenta FOREIGN KEY (estado_cuenta) REFERENCES to_estado_cuenta (id);

ALTER TABLE to_movimiento_proveedor ADD KEY fk_to_movimiento_midas_to_reporte1 (id_reporte);

ALTER TABLE to_movimiento_proveedor ADD CONSTRAINT fk_to_movimiento_midas_to_reporte1 FOREIGN KEY (id_reporte) REFERENCES to_reporte (id);

ALTER TABLE to_movimiento_midas ADD KEY FK_to_movimiento_midas_to_reporte (id_reporte);

ALTER TABLE to_movimiento_midas ADD CONSTRAINT FK_to_movimiento_midas_to_reporte FOREIGN KEY (id_reporte) REFERENCES to_reporte (id);

ALTER TABLE to_movimiento_pago ADD CONSTRAINT FK_to_movimiento_pago_to_movimiento_conciliacion FOREIGN KEY (id) REFERENCES to_movimiento_conciliacion (id);

ALTER TABLE to_movimiento_devolucion ADD CONSTRAINT FK_to_movimiento_devolucion_to_movimiento_conciliacion FOREIGN KEY (id) REFERENCES to_movimiento_conciliacion (id);

ALTER TABLE to_movimiento_comision ADD CONSTRAINT FK_comisiones_to_movimiento_conciliacion FOREIGN KEY (id) REFERENCES to_movimiento_conciliacion (id);

ALTER TABLE to_movimiento_transito ADD CONSTRAINT FK_to_movimiento_transito_to_movimiento_conciliacion FOREIGN KEY (id) REFERENCES to_movimiento_conciliacion (id);

ALTER TABLE to_movimiento_conciliacion ADD KEY FK_to_movimiento_conciliacion_to_conciliacion_idx (id_conciliacion);

ALTER TABLE to_movimiento_conciliacion ADD CONSTRAINT FK_to_movimiento_conciliacion_to_conciliacion FOREIGN KEY (id_conciliacion) REFERENCES to_conciliacion (id);

ALTER TABLE to_movimiento_conciliacion ADD KEY id_movimiento_midas (id_movimiento_midas);

ALTER TABLE to_movimiento_conciliacion ADD CONSTRAINT to_movimiento_conciliacion_ibfk_1 FOREIGN KEY (id_movimiento_midas) REFERENCES to_movimiento_midas (id);

ALTER TABLE to_movimiento_proveedor CHANGE COLUMN amount amount DECIMAL(16,4) NOT NULL;

ALTER TABLE to_movimiento_proveedor CHANGE COLUMN order_id order_id VARCHAR(50) NULL;

ALTER TABLE to_movimiento_proveedor CHANGE COLUMN currency currency VARCHAR(50) NOT NULL;

ALTER TABLE to_movimiento_proveedor CHANGE COLUMN description description VARCHAR(50) NOT NULL;

ALTER TABLE to_conciliacion DROP INDEX to_conciliacion_folio_proveedor_unq;

-- DUDA CON ESTE PASO
-- UPDATE to_conciliacion SET folio = id WHERE proveedor = "OPENPAY"; ???

ALTER TABLE to_conciliacion DROP INDEX to_conciliacion_folio_idx;

ALTER TABLE to_conciliacion DROP COLUMN folio;

ALTER TABLE to_conciliacion DROP FOREIGN KEY proveedor_con_fk;

ALTER TABLE to_conciliacion DROP INDEX proveedor_con_fk_idx;

ALTER TABLE to_conciliacion DROP COLUMN proveedor;

-- Se agrega campo importe bonificaciones
ALTER TABLE `to_global` DROP COLUMN `importe_bonificaciones`;


-- DELETE NECESARIO EN ESTE ARCHIVO POR INTEGRIDAD REFERENCIAL EN FUTUROS INDICES
DELETE FROM tk_proveedor WHERE nombre IN ('OPENPAY', 'OXXO');

DROP TABLE tk_proveedor;



-- --------------------------------------------------------------------------------------------- --
-- ----------- ACTUALIZACION SUB-ESTATUS-CONCILIACION ------------------------------------------ --
-- --------------------------------------------------------------------------------------------- --
UPDATE tk_sub_estatus_conciliacion SET description = 'Consulta Open Pay' WHERE id = 5;
UPDATE tk_sub_estatus_conciliacion SET description = 'Consulta Open Pay Completada' WHERE id = 6;
UPDATE tk_sub_estatus_conciliacion SET description = 'Consulta Open Pay Error' WHERE id = 7;

UPDATE `tk_estatus_movimientos_en_transito` SET `nombre` = 'No identificada en Open Pay', `descripcion` = 'No identificada en Open Pay' WHERE (`id` = '4');


-- ----------------------------------------------------------------------------------------------------- --
-- -------------------------------------- STORE PROCEDURES ---------------------------------------------- --
-- ----------------------------------------------------------------------------------------------------- --

-- ---------------------------------------------- --
-- ------- PROC: save_movimiento_transito ------ --
-- ---------------------------------------------- --
CREATE PROCEDURE `save_movimiento_transito`(
 
 	-- Campos para to_movimiento_transito
     _estatus INT(11),
     _folio INT(11),
     _sucursal INT(11),
     _fecha DATETIME,
     _operacion_desc VARCHAR(45),
     _monto DECIMAL(16, 4),
     _tipo_contrato_desc VARCHAR(45),
     _esquema_tarjeta VARCHAR(45),
     _cuenta VARCHAR(45),
     _titular VARCHAR(255),
     _num_autorizacion VARCHAR(45),
 
 	-- Campos para to_movimiento_conciliacion
 	_id INT(11),
 	_id_conciliacion BIGINT(20),
     _nuevo TINYINT(4),
     _id_movimiento_midas INT(11),
 
     -- Updatable
     _created_date DATETIME,
     _last_modified_date DATETIME,
     _created_by VARCHAR(100),
     _last_modified_by VARCHAR(100)
 )
     MODIFIES SQL DATA
 MAIN: BEGIN
 	-- Funcion que inserta un movimiento conciliacion transito
 
 	DECLARE _id_movimiento_conciliacion INT(11);
 
 	-- En caso de error se hace rollback
 	DECLARE EXIT HANDLER FOR SQLEXCEPTION
 	BEGIN
 		ROLLBACK;
 		RESIGNAL;
 	END;
 
 	START TRANSACTION;
 
 		-- Inserta/actualiza el movimiento conciliacion y regresa el id
 		IF (_id IS NULL) THEN
 			INSERT INTO to_movimiento_conciliacion(id_conciliacion, created_by, created_date, last_modified_by, last_modified_date, nuevo, id_movimiento_midas)
 			VALUES(_id_conciliacion, _created_by, _created_date, _last_modified_by, _last_modified_date, _nuevo, _id_movimiento_midas);
 			
 			SET _id_movimiento_conciliacion = LAST_INSERT_ID();
 		ELSE
 			UPDATE to_movimiento_conciliacion
 			SET
 				id_conciliacion = _id_conciliacion,
 				created_by = _created_by,
 				created_date = _created_date,
 				last_modified_by = _last_modified_by,
 				last_modified_date = _last_modified_date,
 				nuevo = _nuevo,
 				id_movimiento_midas = _id_movimiento_midas
 			WHERE
 				id = _id;
 			SET _id_movimiento_conciliacion = _id;
 		END IF;
 
 		-- Inserta/Actualiza el movimiento transito
 		IF (_id IS NULL OR _id <= 0) THEN
 			INSERT INTO to_movimiento_transito(id, estatus, folio, sucursal, fecha, operacion_desc, monto, tipo_contrato_desc, esquema_tarjeta, cuenta, titular, num_autorizacion)
 			VALUES(_id_movimiento_conciliacion, _estatus, _folio, _sucursal, _fecha, _operacion_desc, _monto, _tipo_contrato_desc, _esquema_tarjeta, _cuenta, _titular, _num_autorizacion);
 		ELSE
 			UPDATE to_movimiento_transito
 			SET
 				estatus = _estatus,
 				folio = _folio,
 				sucursal = _sucursal,
 				fecha = _fecha,
 				operacion_desc = _operacion_desc,
 				monto = _monto,
 				tipo_contrato_desc = _tipo_contrato_desc,
 				esquema_tarjeta = _esquema_tarjeta,
 				cuenta = _cuenta,
 				titular = _titular,
 				num_autorizacion = _num_autorizacion
 			WHERE
 				id = _id_movimiento_conciliacion;
 		END IF;
 
 	COMMIT;
 
 	-- RETURN _id_movimiento_conciliacion;
 
 END MAIN
 
$$
DELIMITER;
 
-- ---------------------------------------------- --
-- ------- PROC: save_movimiento_devolucion ------ --
-- ---------------------------------------------- --
 CREATE PROCEDURE `save_movimiento_devolucion`(
 
 	-- Campos para to_movimiento_devolucion
     _estatus INT(11),
     _fecha DATE,
     _monto DECIMAL(16, 4),
     _esquema_tarjeta VARCHAR(45),
     _identificador_cuenta VARCHAR(45),
     _titular VARCHAR(255),
     _codigo_autorizacion VARCHAR(45),
     _sucursal INT(11),
     _fecha_liquidacion DATE,
 
 	-- Campos para to_movimiento_conciliacion
 	_id INT(11),
 	_id_conciliacion BIGINT(20),
     _nuevo TINYINT(4),
     _id_movimiento_midas INT(11),
 
     -- Updatable
     _created_date DATETIME,
     _last_modified_date DATETIME,
     _created_by VARCHAR(100),
     _last_modified_by VARCHAR(100)
 )
     MODIFIES SQL DATA
 MAIN: BEGIN
 
 	-- Funcion que inserta un movimiento conciliacion devolucion
 
 	DECLARE _id_movimiento_conciliacion INT(11);
 
 	-- En caso de error se hace rollback
 	DECLARE EXIT HANDLER FOR SQLEXCEPTION
 	BEGIN
 		ROLLBACK;
 		RESIGNAL;
 	END;
 
 	START TRANSACTION;
 	
 		-- Inserta/actualiza el movimiento conciliacion y regresa el id
 		IF (_id IS NULL) THEN
 			INSERT INTO to_movimiento_conciliacion(id_conciliacion, created_by, created_date, last_modified_by, last_modified_date, nuevo, id_movimiento_midas)
 			VALUES(_id_conciliacion, _created_by, _created_date, _last_modified_by, _last_modified_date, _nuevo, _id_movimiento_midas);
 			
 			SET _id_movimiento_conciliacion = LAST_INSERT_ID();
 		ELSE
 			UPDATE to_movimiento_conciliacion
 			SET
 				id_conciliacion = _id_conciliacion,
 				created_by = _created_by,
 				created_date = _created_date,
 				last_modified_by = _last_modified_by,
 				last_modified_date = _last_modified_date,
 				nuevo = _nuevo,
 				id_movimiento_midas = _id_movimiento_midas
 			WHERE
 				id = _id;
 			SET _id_movimiento_conciliacion = _id;
 		END IF;
 
 		-- Inserta/Actualiza el movimiento devolucion
 		IF (_id IS NULL OR _id <= 0) THEN
 			INSERT INTO to_movimiento_devolucion(id, estatus, fecha, monto, esquema_tarjeta, identificador_cuenta, titular, codigo_autorizacion, sucursal, fecha_liquidacion)
 			VALUES(_id_movimiento_conciliacion, _estatus, _fecha, _monto, _esquema_tarjeta, _identificador_cuenta, _titular, _codigo_autorizacion, _sucursal, _fecha_liquidacion);
 		ELSE
 			UPDATE to_movimiento_devolucion
 			SET
 				estatus = _estatus,
 				fecha = _fecha,
 				monto = _monto,
 				esquema_tarjeta = _esquema_tarjeta,
 				identificador_cuenta = _identificador_cuenta,
 				titular = _titular,
 				codigo_autorizacion = _codigo_autorizacion,
 				sucursal = _sucursal,
 				fecha_liquidacion = _fecha_liquidacion
 			WHERE
 				id = _id_movimiento_conciliacion;
 		END IF;
 
 	COMMIT;
 	
 	-- RETURN _id_movimiento_conciliacion;
 
 END MAIN
 
$$
DELIMITER;
 
-- ---------------------------------------------- --
-- ------- PROC: save_movimiento_comision ------ --
-- ---------------------------------------------- -- 
 CREATE PROCEDURE `save_movimiento_comision`(
 
 	-- Campos para to_movimiento_comision
     _fecha_operacion DATE,
     _fecha_cargo DATE,
     _monto DECIMAL(16, 4),
     _descripcion VARCHAR(150),
     _estatus TINYINT(4),
     _tipo VARCHAR(50),
     _id_movimiento_estado_cuenta BIGINT(20),
 
 	-- Campos para to_movimiento_conciliacion
 	_id INT(11),
 	_id_conciliacion BIGINT(20),
     _nuevo TINYINT(4),
     _id_movimiento_midas INT(11),
 
     -- Updatable
     _created_date DATETIME,
     _last_modified_date DATETIME,
     _created_by VARCHAR(100),
     _last_modified_by VARCHAR(100)
 )
     MODIFIES SQL DATA
 MAIN: BEGIN
 
 	-- Funcion que inserta un movimiento conciliacion comision
 	DECLARE _id_movimiento_conciliacion INT(11);
 
 	-- En caso de error se hace rollback
 	DECLARE EXIT HANDLER FOR SQLEXCEPTION
 	 BEGIN
 		ROLLBACK;
 		RESIGNAL;
 	 END;
 
 
 	START TRANSACTION;
 
 		-- Inserta/actualiza el movimiento conciliacion y regresa el id
 		IF (_id IS NULL) THEN
 			INSERT INTO to_movimiento_conciliacion(id_conciliacion, created_by, created_date, last_modified_by, last_modified_date, nuevo, id_movimiento_midas)
 			VALUES(_id_conciliacion, _created_by, _created_date, _last_modified_by, _last_modified_date, _nuevo, _id_movimiento_midas);
 			
 			SET _id_movimiento_conciliacion = LAST_INSERT_ID();
 		ELSE
 			UPDATE to_movimiento_conciliacion
 			SET
 				id_conciliacion = _id_conciliacion,
 				created_by = _created_by,
 				created_date = _created_date,
 				last_modified_by = _last_modified_by,
 				last_modified_date = _last_modified_date,
 				nuevo = _nuevo,
 				id_movimiento_midas = _id_movimiento_midas
 			WHERE
 				id = _id;
 			SET _id_movimiento_conciliacion = _id;
 		END IF;
 
 		-- Inserta el movimiento comision
 		IF (_id IS NULL OR _id <= 0) THEN
 			INSERT INTO to_movimiento_comision(id, fecha_operacion, fecha_cargo, monto, tipo, id_movimiento_estado_cuenta, descripcion, estatus)
 			VALUES(_id_movimiento_conciliacion, _fecha_operacion, _fecha_cargo, _monto, _tipo, _id_movimiento_estado_cuenta, _descripcion, _estatus);
 		ELSE
 			UPDATE to_movimiento_comision
 			SET
 				fecha_operacion = _fecha_operacion,
 				fecha_cargo = _fecha_cargo,
 				monto = _monto,
 				tipo = _tipo,
 				id_movimiento_estado_cuenta = _id_movimiento_estado_cuenta,
 				descripcion = _descripcion,
 				estatus = _estatus
 			WHERE
 				id = _id_movimiento_conciliacion;
 		END IF;
 
 	COMMIT;
 
 	-- RETURN _id_movimiento_conciliacion;
 
 END MAIN
 
$$
DELIMITER;