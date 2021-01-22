--
-- Proyecto:        NMP - MIDAS - OXXO.
-- Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
--

-- ----------------------------------------------------------------------------------------------- --
-- -------------------------- ACTUALIZACION CONCILIACION OXXO ------------------------------------- --
-- ----------------------------------------------------------------------------------------------- --

-- -------------------------------------------------------------- --
-- ------------ TABLA PROVEEDOR / CORRESPONSAL ------------------ --
-- -------------------------------------------------------------- --
CREATE TABLE `tk_proveedor` (
`nombre` VARCHAR(150) PRIMARY KEY NOT NULL,
`descripcion` VARCHAR(250),
UNIQUE(nombre)
) ENGINE=InnoDB DEFAULT CHARACTER SET = latin1;


-- Se requiere insertar los corresponsables para poder crear la foreign key
INSERT INTO tk_proveedor (nombre, descripcion) 
	VALUES
		('OPENPAY', 'Proveedor transaccional')
        , ('OXXO', 'Proveedor oxxo');

-- -------------------------------------------------------------- --
-- ------------ RELACION PROVEEDOR - CONCILIACION --------------- --
-- -------------------------------------------------------------- -- 
ALTER TABLE `to_conciliacion` ADD COLUMN proveedor VARCHAR(150) NOT NULL DEFAULT 'OPENPAY';
ALTER TABLE `to_conciliacion` ADD INDEX `proveedor_con_fk_idx` (`proveedor` ASC);
ALTER TABLE `to_conciliacion` ADD CONSTRAINT `proveedor_con_fk` FOREIGN KEY (`proveedor`) REFERENCES `tk_proveedor` (`nombre`);

-- Se agrega campo folio, indice y se agrega llave unica folio-proveedor
ALTER TABLE `to_conciliacion` ADD COLUMN folio BIGINT(20) NOT NULL DEFAULT 1;
ALTER TABLE `to_conciliacion` ADD INDEX `to_conciliacion_folio_idx` (`folio`);
-- Se asigna el campo folio usando el id de la conciliacion para los registros existentes de openpay
UPDATE to_conciliacion SET folio = id WHERE proveedor = "OPENPAY";
ALTER TABLE `to_conciliacion` ADD UNIQUE `to_conciliacion_folio_proveedor_unq` (`folio`, proveedor);


-- -------------------------------------------------------------- --
-- ---------- DATOS OBLIGATORIOS MOVIMIENTOS PROVEEDOR ---------- --
-- -------------------------------------------------------------- -- 
ALTER TABLE `to_movimiento_proveedor` CHANGE COLUMN `description` `description` VARCHAR(50) NULL;
ALTER TABLE `to_movimiento_proveedor` CHANGE COLUMN `currency` `currency` VARCHAR(50) NULL;
ALTER TABLE `to_movimiento_proveedor` CHANGE COLUMN `order_id` `order_id` VARCHAR(50) NOT NULL DEFAULT '';
ALTER TABLE `to_movimiento_proveedor` CHANGE COLUMN `amount` `amount` DECIMAL(16,4) NOT NULL DEFAULT 0.0;

-- ------------------------------------------------------------------------ --
-- ----------------- SE ACTUALIZA TIPO DATO INT A BIGINT ------------------ --
-- ------------------------------------------------------------------------ -- 

-- Se eliminan foreign keys
ALTER TABLE `to_movimiento_conciliacion` DROP FOREIGN KEY `to_movimiento_conciliacion_ibfk_1`;
ALTER TABLE `to_movimiento_conciliacion` DROP INDEX `id_movimiento_midas`;
ALTER TABLE `to_movimiento_conciliacion` DROP FOREIGN KEY `FK_to_movimiento_conciliacion_to_conciliacion`;
ALTER TABLE `to_movimiento_conciliacion` DROP INDEX `FK_to_movimiento_conciliacion_to_conciliacion_idx`;
ALTER TABLE `to_movimiento_transito` DROP FOREIGN KEY `FK_to_movimiento_transito_to_movimiento_conciliacion`;
ALTER TABLE `to_movimiento_comision` DROP FOREIGN KEY `FK_comisiones_to_movimiento_conciliacion`;
ALTER TABLE `to_movimiento_devolucion` DROP FOREIGN KEY `FK_to_movimiento_devolucion_to_movimiento_conciliacion`;
ALTER TABLE `to_movimiento_pago` DROP FOREIGN KEY `FK_to_movimiento_pago_to_movimiento_conciliacion`;
ALTER TABLE `to_movimiento_midas` DROP FOREIGN KEY `FK_to_movimiento_midas_to_reporte`;
ALTER TABLE `to_movimiento_midas` DROP INDEX `FK_to_movimiento_midas_to_reporte`;
ALTER TABLE `to_movimiento_proveedor` DROP FOREIGN KEY `fk_to_movimiento_midas_to_reporte1`;
ALTER TABLE `to_movimiento_proveedor` DROP INDEX `fk_to_movimiento_midas_to_reporte1`;
ALTER TABLE `to_movimiento_estado_cuenta` DROP FOREIGN KEY `FK_to_movimiento_estado_cuenta_to_estado_cuenta`;
ALTER TABLE `to_movimiento_estado_cuenta` DROP INDEX `estado_cuenta`;
ALTER TABLE `to_estado_cuenta` DROP FOREIGN KEY `FK_to_estado_cuenta_to_reporte`;
ALTER TABLE `to_estado_cuenta` DROP INDEX `FK_to_estado_cuenta_to_reporte`;
ALTER TABLE `to_reporte` DROP FOREIGN KEY `FK_to_carga_reporte_to_conciliacion`;
ALTER TABLE `to_reporte` DROP INDEX `FK_to_carga_reporte_to_conciliacion_idx`;

-- Se cambian tipo de dato INT(11) a BIGINT(20)
ALTER TABLE `to_movimiento_conciliacion` CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
			CHANGE COLUMN `id_movimiento_midas` `id_movimiento_midas` BIGINT(20) NULL DEFAULT NULL;
ALTER TABLE `to_movimiento_comision` CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL;
ALTER TABLE `to_movimiento_devolucion` CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL;
ALTER TABLE `to_movimiento_pago` CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL;
ALTER TABLE `to_movimiento_transito` CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL;
ALTER TABLE `to_estado_cuenta` CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL;
ALTER TABLE `to_reporte` CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL AUTO_INCREMENT;
ALTER TABLE `to_reporte` CHANGE COLUMN `id_conciliacion` `id_conciliacion` BIGINT(20) NULL;
ALTER TABLE `to_movimiento_estado_cuenta` CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
			CHANGE COLUMN `estado_cuenta` `estado_cuenta` BIGINT(20) NULL DEFAULT NULL;
ALTER TABLE `to_estado_cuenta` CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
			CHANGE COLUMN `reporte` `reporte` BIGINT(20) NULL DEFAULT NULL;
ALTER TABLE `to_movimiento_midas` CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
			CHANGE COLUMN `id_reporte` `id_reporte` BIGINT(20) NOT NULL;
ALTER TABLE `to_movimiento_proveedor` CHANGE COLUMN `id_reporte` `id_reporte` BIGINT(20) NOT NULL;

-- Se agregan foreign keys
ALTER TABLE `to_reporte` ADD (
  INDEX `FK_to_carga_reporte_to_conciliacion_idx` (`id_conciliacion` ASC),
  CONSTRAINT `FK_to_carga_reporte_to_conciliacion`
    FOREIGN KEY (`id_conciliacion`)
    REFERENCES `to_conciliacion` (`id`)
);
ALTER TABLE `to_movimiento_midas` ADD (
  INDEX `FK_to_movimiento_midas_to_reporte` (`id_reporte` ASC),
  CONSTRAINT `FK_to_movimiento_midas_to_reporte`
    FOREIGN KEY (`id_reporte`)
    REFERENCES `to_reporte` (`id`)
);
ALTER TABLE `to_movimiento_proveedor` ADD (
  INDEX `fk_to_movimiento_midas_to_reporte1` (`id_reporte` ASC),
  CONSTRAINT `fk_to_movimiento_midas_to_reporte1`
    FOREIGN KEY (`id_reporte`)
    REFERENCES `to_reporte` (`id`)
);
ALTER TABLE to_estado_cuenta ADD (
  CONSTRAINT FK_to_estado_cuenta_to_reporte
    FOREIGN KEY (reporte) REFERENCES to_reporte (id)
);
ALTER TABLE to_movimiento_estado_cuenta ADD (
  CONSTRAINT FK_to_movimiento_estado_cuenta_to_estado_cuenta
    FOREIGN KEY (estado_cuenta) REFERENCES to_estado_cuenta (id)
);
ALTER TABLE `to_movimiento_comision` ADD (
  CONSTRAINT `FK_comisiones_to_movimiento_conciliacion`
    FOREIGN KEY (`id`)
    REFERENCES `to_movimiento_conciliacion` (`id`)
);
ALTER TABLE `to_movimiento_conciliacion` ADD (
  CONSTRAINT `FK_to_movimiento_conciliacion_to_conciliacion`
    FOREIGN KEY (`id_conciliacion`)
    REFERENCES `to_conciliacion` (`id`),
  CONSTRAINT `to_movimiento_conciliacion_ibfk_1`
    FOREIGN KEY (`id_movimiento_midas`)
    REFERENCES `to_movimiento_midas` (`id`)
);
ALTER TABLE `to_movimiento_devolucion` ADD (
  CONSTRAINT `FK_to_movimiento_devolucion_to_movimiento_conciliacion`
    FOREIGN KEY (`id`)
    REFERENCES `to_movimiento_conciliacion` (`id`)
);
ALTER TABLE `to_movimiento_pago` ADD (
  CONSTRAINT `FK_to_movimiento_pago_to_movimiento_conciliacion`
    FOREIGN KEY (`id`)
    REFERENCES `to_movimiento_conciliacion` (`id`)
);
ALTER TABLE `to_movimiento_transito` ADD (
  CONSTRAINT `FK_to_movimiento_transito_to_movimiento_conciliacion`
    FOREIGN KEY (`id`)
    REFERENCES `to_movimiento_conciliacion` (`id`)
);

-- ------------------------------------------------------------------------ --
-- ---------- ASOCIA UN ESTADO DE CUENTA A VARIAS CONCILIACIONES ---------- --
-- ------------------------------------------------------------------------ -- 
CREATE TABLE `tr_conciliacion_estado_cuenta` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`id_conciliacion` BIGINT(20) NOT NULL,
	`id_reporte` BIGINT(20) NOT NULL,
	PRIMARY KEY (`id`, `id_conciliacion`, `id_reporte`),
	CONSTRAINT `fk_tr_conciliacion_estado_cuenta_conciliacion` FOREIGN KEY(id_conciliacion) REFERENCES to_conciliacion(id),
	CONSTRAINT `fk_tr_conciliacion_estado_cuenta_report` FOREIGN KEY(id_reporte) REFERENCES to_reporte(id)
) ENGINE = InnoDB;

-- -------------------------------------------------------------- --
-- ------------------ TABLA TC_LAYOUT_HEADER -------------------- --
-- -------------------------------------------------------------- -- 
ALTER TABLE `tc_layout_header` ADD COLUMN corresponsal VARCHAR(50) NOT NULL DEFAULT 'OPENPAY';


-- -------------------------------------------------------------- --
-- ------------------ TABLA TC_LAYOUT_LINEA --------------------- --
-- -------------------------------------------------------------- --
ALTER TABLE `tc_layout_linea` ADD COLUMN corresponsal VARCHAR(50) NOT NULL DEFAULT 'OPENPAY'; 
ALTER TABLE `tc_layout_linea` ADD COLUMN operacion VARCHAR(50) NOT NULL DEFAULT 'DEPOSITOS'; 


-- -------------------------------------------------------------- --
-- ------------------ TABLA Transito --------------------------- --
-- -------------------------------------------------------------- --
ALTER TABLE `to_movimiento_transito` ADD COLUMN `transaccion` VARCHAR(50) NULL DEFAULT '';


-- -----------------------------------------------------
-- ---------- TABLA tc_comision_proveedor ----------- --
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tc_comision_proveedor` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `corresponsal` VARCHAR(50) NOT NULL DEFAULT 'OPENPAY',
  `comision` DECIMAL(5,4) NOT NULL,
  `iva` DECIMAL(5,4) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `cp_fk_idx` (`id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- ----------------------------------------------------
-- ------------- TABLA Bonificaciones -----------------
-- ----------------------------------------------------

CREATE TABLE IF NOT EXISTS `tk_estatus_bonificacion` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
  `descripcion` VARCHAR(150) NULL DEFAULT NULL,
  `estatus` BIT(1) NOT NULL DEFAULT b'1',
  `descripcion_corta` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


CREATE TABLE IF NOT EXISTS `to_movimiento_bonificacion` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `asignacion` VARCHAR(50),
  `num_doc` VARCHAR(50),
  `fecha_doc` DATE DEFAULT NULL,
  `tienda` VARCHAR(50),
  `plaza` VARCHAR(50),
  `importe_ml` DECIMAL(16,4) NOT NULL,
  `folio_bonificacion` VARCHAR(50),
  `sucursal` INT(11),
  `id_estatus_bonificacion` INT(11) NOT NULL,
  `id_conciliacion` BIGINT(20) NOT NULL,
  `created_by` VARCHAR(100) NOT NULL,
  `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` VARCHAR(100) NULL DEFAULT NULL,
  `last_modified_date` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `estatus_fk_idx` (`id_estatus_bonificacion`),
  CONSTRAINT `estatus_fk`
    FOREIGN KEY (`id_estatus_bonificacion`)
    REFERENCES `tk_estatus_bonificacion` (`id`),
  INDEX `bonificacion_id_conciliacion_fk_idx` (`id_conciliacion`),
  CONSTRAINT `bonificacion_id_conciliacion_fk`
    FOREIGN KEY (`id_conciliacion`)
    REFERENCES `to_conciliacion` (`id`)
) ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

CREATE TABLE IF NOT EXISTS `to_movimiento_bonificacion_referencia` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `referencia` VARCHAR(50),
  `monto` DECIMAL(16,4) NOT NULL,
  `sucursal` INT(11),
  `id_movimiento_bonificacion` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `movimiento_bonificacion_fk_idx` (`id_movimiento_bonificacion`),
  CONSTRAINT `movimiento_bonificacion_fk`
    FOREIGN KEY (`id_movimiento_bonificacion`)
    REFERENCES `to_movimiento_bonificacion` (`id`)
)
DEFAULT CHARACTER SET = latin1;

-- --------------------------- CAMBIOS NUEVOS EN MAQUINA DE ESTADOS INICIO ----------------- --
-- ------------------- SE DUPLICAN LOS ACTUALES ESTADOS DE OPENPAY PARA OXXO ------------ --
-- INSERT INTO tk_maquina_estados_subestatus_conciliacion (nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible)
-- 	SELECT me.nombre_proceso, me.id_sub_estatus_inicial, me.id_sub_estatus_posible FROM tk_maquina_estados_subestatus_conciliacion me;

-- ------------------- SE AGREGA LA NUEVA COLUMNA DE CORRESPONSAL ------------ --
ALTER TABLE tk_maquina_estados_subestatus_conciliacion ADD COLUMN corresponsal VARCHAR(150) NOT NULL DEFAULT 'OPENPAY';

-- ------------------- SE ACTUALIZA EL CORRESPONSAL PARA LOS ESTADOS DE OXXO ------------ --
-- UPDATE tk_maquina_estados_subestatus_conciliacion SET corresponsal = 'OXXO' WHERE ID >= 30;

-- --------------------------- CAMBIOS NUEVOS EN MAQUINA DE ESTADOS FIN ----------------- --

-- ------------------------------------------------------------------------------------------------- --
-- --------------------------------------- CREACION DE SP's ---------------------------------------- --
-- ------------------------------------------------------------------------------------------------- --

-- --------------------------------------------------------------------------------- --
-- ----------------------- save_movimiento_comision -------------------------------- --
-- --------------------------------------------------------------------------------- --
DROP PROCEDURE IF EXISTS `save_movimiento_comision`;
DELIMITER $$
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
	_id BIGINT(20),
	_id_conciliacion BIGINT(20),
    _nuevo TINYINT(4),
    _id_movimiento_midas BIGINT(20),

    -- Updatable
    _created_date DATETIME,
    _last_modified_date DATETIME,
    _created_by VARCHAR(100),
    _last_modified_by VARCHAR(100)
)
MODIFIES SQL DATA
MAIN: BEGIN

	-- Funcion que inserta un movimiento conciliacion comision
	DECLARE _id_movimiento_conciliacion BIGINT(20);

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

END MAIN;
$$
DELIMITER ;


-- --------------------------------------------------------------------------------- --
-- ----------------------- save_movimiento_devolucion -------------------------------- --
-- --------------------------------------------------------------------------------- --
DROP PROCEDURE IF EXISTS `save_movimiento_devolucion`;
DELIMITER $$
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
	_id BIGINT(20),
	_id_conciliacion BIGINT(20),
    _nuevo TINYINT(4),
    _id_movimiento_midas BIGINT(20),

    -- Updatable
    _created_date DATETIME,
    _last_modified_date DATETIME,
    _created_by VARCHAR(100),
    _last_modified_by VARCHAR(100)
)
MODIFIES SQL DATA
MAIN: BEGIN

	-- Funcion que inserta un movimiento conciliacion devolucion

	DECLARE _id_movimiento_conciliacion BIGINT(20);

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

END MAIN;
$$
DELIMITER ;


-- --------------------------------------------------------------------------------- --
-- ----------------------- save_movimiento_transito -------------------------------- --
-- --------------------------------------------------------------------------------- --
DROP PROCEDURE IF EXISTS `save_movimiento_transito`;
DELIMITER $$
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
    -- nuevo campo
     _transaccion VARCHAR(50),
    
	-- Campos para to_movimiento_conciliacion
	_id BIGINT(20),
	_id_conciliacion BIGINT(20),
    _nuevo TINYINT(4),
    _id_movimiento_midas BIGINT(20),

    -- Updatable
    _created_date DATETIME,
    _last_modified_date DATETIME,
    _created_by VARCHAR(100),
    _last_modified_by VARCHAR(100)
)
MODIFIES SQL DATA
MAIN: BEGIN
	-- Funcion que inserta un movimiento conciliacion transito

	DECLARE _id_movimiento_conciliacion BIGINT(20);

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
			INSERT INTO to_movimiento_transito(id, estatus, folio, sucursal, fecha, operacion_desc, monto, tipo_contrato_desc, esquema_tarjeta, cuenta, titular, num_autorizacion, transaccion)
			VALUES(_id_movimiento_conciliacion, _estatus, _folio, _sucursal, _fecha, _operacion_desc, _monto, _tipo_contrato_desc, _esquema_tarjeta, _cuenta, _titular, _num_autorizacion, _transaccion);
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
                , transaccion = _transaccion
			WHERE
				id = _id_movimiento_conciliacion;
		END IF;

	COMMIT;

	-- RETURN _id_movimiento_conciliacion;

END MAIN;
$$
DELIMITER ;


-- ------------------------------------------------------------------------------------------ --
-- -------------------- INSERTS INICIALES PARA FASE OXXO ------------------------------------ --
-- ------------------------------------------------------------------------------------------ --

-- ------------------------------------------------------------------------------------------------------------------ --
-- INSERTS INICIALES EN TABLA - tk_operacion
-- ------------------------------------------------------------------------------------------------------------------ --
INSERT INTO tk_operacion (id, abreviatura, descripcion) VALUES
(198, 'RFE', 'Cobro Refrendo Extemporaneo');


-- ------------------------------------------------------------------------------------------------------------------ --
-- ------------------------------------------------------------------------------------------------------------------ --

-- Se crea la secuencia para OPENPAY usando el ultimo folio
INSERT INTO seq_conciliacion(seq_name, seq_value) VALUES('OPENPAY', (SELECT MAX(id) FROM to_conciliacion));
-- Se crea la secuencia para OXXO iniciando en 0
INSERT INTO seq_conciliacion(seq_name, seq_value) VALUES('OXXO', 0);


-- ---------------------------------------------------------------------------------- --
-- --------------- INSERT DE LAS COMISIONES PROVEEDORES / CORRESPONSALES ------------------------ --
-- ---------------------------------------------------------------------------------- --
INSERT INTO `tc_comision_proveedor` (`id`, `corresponsal`, `comision`, `iva`) 
	VALUES ('1', 'OXXO', '1.552', '0.248');


-- ---------------------------------------------------------------------------------- --
-- --------------- INSERT DE LAS COMISIONES PROVEEDORES / CORRESPONSALES ------------------------ --
-- ---------------------------------------------------------------------------------- --
INSERT INTO `tk_estatus_bonificacion` (`id`, `nombre`, `descripcion`, `descripcion_corta`) 
	VALUES (1, 'Pendiente', 'Pendiente', 'PE');
INSERT INTO `tk_estatus_bonificacion` (`id`, `nombre`, `descripcion`, `descripcion_corta`) 
	VALUES (2, 'Reversa', 'Reversa', 'RV');
INSERT INTO `tk_estatus_bonificacion` (`id`, `nombre`, `descripcion`, `descripcion_corta`) 
	VALUES (3, 'Conciliada', 'Conciliada', 'CON');


-- ---------------------------------------------------------------------------------- --
-- ---------- INSERT DE ESTADOS EN MAQUINA DE ESTADOS PARA CORRESPONSAL OXXO -------- --
-- ---------------------------------------------------------------------------------- --
INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (30, 'ALTA_MOVIMIENTOS_PROCESOS_NOCTURNOS', 1, 2, 'OXXO');

INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (31, 'ALTA_MOVIMIENTOS_PROCESOS_NOCTURNOS', 2, 3, 'OXXO');
INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (32, 'ALTA_MOVIMIENTOS_PROCESOS_NOCTURNOS', 2, 4, 'OXXO');

INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (33, 'ALTA_MOVIMIENTOS_PROVEEDOR_TRANSACCIONAL', 3, 5, 'OXXO');

INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (34, 'ALTA_MOVIMIENTOS_PROCESOS_NOCTURNOS', 4, 2, 'OXXO');

INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (35, 'ALTA_MOVIMIENTOS_PROVEEDOR_TRANSACCIONAL', 5, 6, 'OXXO');
INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (36, 'ALTA_MOVIMIENTOS_PROVEEDOR_TRANSACCIONAL', 5, 7, 'OXXO');

INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (37, 'CONCILIACION_MERGE', 6, 8, 'OXXO');
INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (38, 'ALTA_MOVIMIENTOS_ESTADO_CUENTA', 6, 11, 'OXXO');

INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (39, 'ALTA_MOVIMIENTOS_PROVEEDOR_TRANSACCIONAL', 7, 5, 'OXXO');

INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (40, 'CONCILIACION_MERGE', 8, 9, 'OXXO');
INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (41, 'CONCILIACION_MERGE', 8, 10, 'OXXO');

INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (42, 'CONCILIACION_MERGE', 9, 8, 'OXXO');
INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (43, 'ALTA_MOVIMIENTOS_ESTADO_CUENTA', 9, 11, 'OXXO');
INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (44, 'GENERACION_LAYOUTS', 9, 14, 'OXXO');

INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (45, 'CONCILIACION_MERGE', 10, 8, 'OXXO');

INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (46, 'ALTA_MOVIMIENTOS_ESTADO_CUENTA', 11, 12, 'OXXO');
INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (47, 'ALTA_MOVIMIENTOS_ESTADO_CUENTA', 11, 13, 'OXXO');

INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (48, 'CONCILIACION_MERGE', 12, 8, 'OXXO');
INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (49, 'GENERACION_LAYOUTS', 12, 14, 'OXXO');

INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (50, 'ALTA_MOVIMIENTOS_ESTADO_CUENTA', 13, 11, 'OXXO');

INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (51, 'GENERACION_LAYOUTS', 14, 15, 'OXXO');
INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (52, 'GENERACION_LAYOUTS', 14, 16, 'OXXO');

INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (53, 'ENVIO_CONCILIACION', 15, 17, 'OXXO');

INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (54, 'GENERACION_LAYOUTS', 16, 14, 'OXXO');

INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (55, 'ENVIO_CONCILIACION', 17, 18, 'OXXO');
INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (56, 'ENVIO_CONCILIACION', 17, 19, 'OXXO');

INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (57, 'ENVIO_CONCILIACION', 18, 17, 'OXXO');

INSERT INTO tk_maquina_estados_subestatus_conciliacion(id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES( 58, 'CONCILIACION_MERGE', 8, 12, 'OXXO');

INSERT INTO tk_maquina_estados_subestatus_conciliacion(id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES( 59, 'ALTA_MOVIMIENTO_ESTADO_CUENTA', 15, 11, 'OXXO');

-- ------------------------------------------------------------------------------------------------------------------ --
-- LINEAS DE LAYOUTS OXXO
-- ------------------------------------------------------------------------------------------------------------------ --

-- PAGOS - DEPOSITOS
INSERT INTO tc_layout_linea (id, linea, cuenta, dep_id, unidad_operativa, negocio, proyecto_nmp, tipo, grupo, corresponsal, operacion, created_date, last_modified_date, created_by, last_modified_by) VALUES
(11, 'L', '1220001014', '', '13%03d', 'PRENDA', 'SUCS_NB', 'PAGOS', 'SUCURSALES', 'OXXO', 'DEPOSITOS', now(), null, 'Sistema', null); -- Sucursales
INSERT INTO tc_layout_linea (id, linea, cuenta, dep_id, unidad_operativa, negocio, proyecto_nmp, tipo, grupo, corresponsal, operacion, created_date, last_modified_date, created_by, last_modified_by) VALUES
(12, 'L', '1011001', '15000', '', '', '', 'PAGOS', 'BANCOS', 'OXXO', 'DEPOSITOS', now(), null, 'Sistema', null); -- Bancos

-- Comisiones OXXO (MOV) - DEPOSITOS
INSERT INTO tc_layout_linea (id, linea, cuenta, dep_id, unidad_operativa, negocio, proyecto_nmp, tipo, grupo, corresponsal, operacion, created_date, last_modified_date, created_by, last_modified_by) VALUES
(13, 'L', '6402001005', '', '13%03d', 'PRENDA', 'SUCS_NB', 'COMISIONES_MOV', 'SUCURSALES', 'OXXO', 'DEPOSITOS', now(), null, 'Sistema', null); -- Sucursales
INSERT INTO tc_layout_linea (id, linea, cuenta, dep_id, unidad_operativa, negocio, proyecto_nmp, tipo, grupo, corresponsal, operacion, created_date, last_modified_date, created_by, last_modified_by) VALUES
(14, 'L', '1011001', '15000', '', '', '', 'COMISIONES_MOV', 'BANCOS', 'OXXO', 'DEPOSITOS', now(), null, 'Sistema', null); -- Bancos

-- Comisiones Iva - DEPOSITOS
INSERT INTO tc_layout_linea (id, linea, cuenta, dep_id, unidad_operativa, negocio, proyecto_nmp, tipo, grupo, corresponsal, operacion, created_date, last_modified_date, created_by, last_modified_by) VALUES
(15, 'L', '1219001003', '', '13%03d', 'PRENDA', 'SUCS_NB', 'COMISIONES_IVA', 'SUCURSALES', 'OXXO', 'DEPOSITOS', now(), null, 'Sistema', null); -- Sucursales
INSERT INTO tc_layout_linea (id, linea, cuenta, dep_id, unidad_operativa, negocio, proyecto_nmp, tipo, grupo, corresponsal, operacion, created_date, last_modified_date, created_by, last_modified_by) VALUES
(16, 'L', '1011001', '15000', '', '', '', 'COMISIONES_IVA', 'BANCOS', 'OXXO', 'DEPOSITOS', now(), null, 'Sistema', null); -- Bancos

-- PAGOS - BONIFICACIONES
INSERT INTO tc_layout_linea (id, linea, cuenta, dep_id, unidad_operativa, negocio, proyecto_nmp, tipo, grupo, corresponsal, operacion, created_date, last_modified_date, created_by, last_modified_by) VALUES
(17, 'L', '1220001014', '', '13%03d', 'PRENDA', 'SUCS_NB', 'BONIFICACIONES', 'SUCURSALES', 'OXXO', 'DEPOSITOS', now(), null, 'Sistema', null); -- Sucursales
INSERT INTO tc_layout_linea (id, linea, cuenta, dep_id, unidad_operativa, negocio, proyecto_nmp, tipo, grupo, corresponsal, operacion, created_date, last_modified_date, created_by, last_modified_by) VALUES
(18, 'L', '1011001', '15000', '', '', '', 'BONIFICACIONES', 'BANCOS', 'OXXO', 'DEPOSITOS', now(), null, 'Sistema', null); -- Bancos

-- Comisiones OXXO (MOV) - BONIFICACIONES
INSERT INTO tc_layout_linea (id, linea, cuenta, dep_id, unidad_operativa, negocio, proyecto_nmp, tipo, grupo, corresponsal, operacion, created_date, last_modified_date, created_by, last_modified_by) VALUES
(19, 'L', '6402001005', '', '13%03d', 'PRENDA', 'SUCS_NB', 'COMISIONES_MOV', 'SUCURSALES', 'OXXO', 'BONIFICACIONES', now(), null, 'Sistema', null); -- Sucursales
INSERT INTO tc_layout_linea (id, linea, cuenta, dep_id, unidad_operativa, negocio, proyecto_nmp, tipo, grupo, corresponsal, operacion, created_date, last_modified_date, created_by, last_modified_by) VALUES
(20, 'L', '1011001', '15000', '', '', '', 'COMISIONES_MOV', 'BANCOS', 'OXXO', 'BONIFICACIONES', now(), null, 'Sistema', null); -- Bancos

-- Comisiones Iva - BONIFICACIONES
INSERT INTO tc_layout_linea (id, linea, cuenta, dep_id, unidad_operativa, negocio, proyecto_nmp, tipo, grupo, corresponsal, operacion, created_date, last_modified_date, created_by, last_modified_by) VALUES
(21, 'L', '1219001003', '', '13%03d', 'PRENDA', 'SUCS_NB', 'COMISIONES_IVA', 'SUCURSALES', 'OXXO', 'BONIFICACIONES', now(), null, 'Sistema', null); -- Sucursales
INSERT INTO tc_layout_linea (id, linea, cuenta, dep_id, unidad_operativa, negocio, proyecto_nmp, tipo, grupo, corresponsal, operacion, created_date, last_modified_date, created_by, last_modified_by) VALUES
(22, 'L', '1011001', '15000', '', '', '', 'COMISIONES_IVA', 'BANCOS', 'OXXO', 'BONIFICACIONES', now(), null, 'Sistema', null); -- Bancos



-- --------------------------------------------------------------------------------------------- --
-- ----------- INSERCIONES DE CATALOGO DE HEADERS DE LAYOUTS PARA CORRESPONSAL OXXO ------------- --
-- --------------------------------------------------------------------------------------------- --
INSERT INTO tc_layout_header (id, cabecera, unidad_negocio, descripcion, codigo_origen, tipo, corresponsal, created_date, last_modified_date, created_by, last_modified_by) VALUES
('5', 'H', 'NMP01', 'COB RF Y DS EN L {0}', 'B', 'PAGOS', 'OXXO', now(), null, 'Sistema', null);
INSERT INTO tc_layout_header (id, cabecera, unidad_negocio, descripcion, codigo_origen, tipo, corresponsal, created_date, last_modified_date, created_by, last_modified_by) VALUES
('6', 'H', 'NMP01', 'COMISIÓN {0}', 'B', 'COMISIONES_MOV', 'OXXO', now(), null, 'Sistema', null);
INSERT INTO tc_layout_header (id, cabecera, unidad_negocio, descripcion, codigo_origen, tipo, corresponsal, created_date, last_modified_date, created_by, last_modified_by) VALUES
('7', 'H', 'NMP01', 'COMISIÓN {0}', 'B', 'COMISIONES_GENERALES', 'OXXO', now(), null, 'Sistema', null);
INSERT INTO tc_layout_header (id, cabecera, unidad_negocio, descripcion, codigo_origen, tipo, corresponsal, created_date, last_modified_date, created_by, last_modified_by) VALUES
('8', 'H', 'NMP01', 'DEV RF Y DS EN LINEA {0}', 'B', 'DEVOLUCIONES', 'OXXO', now(), null, 'Sistema', null);
INSERT INTO tc_layout_header (id, cabecera, unidad_negocio, descripcion, codigo_origen, tipo, corresponsal, created_date, last_modified_date, created_by, last_modified_by) VALUES
('9', 'H', 'NMP01', 'BONIFICACIONES {0}', 'B', 'BONIFICACIONES', 'OXXO', now(), null, 'Sistema', null);



-- --------------------------------------------------------------------------------------------- --
-- ----------- ACTUALIZACION SUB-ESTATUS-CONCILIACION ------------------------------------------ --
-- --------------------------------------------------------------------------------------------- --
UPDATE tk_sub_estatus_conciliacion SET description = 'Consulta Proveedor' WHERE id = 5;
UPDATE tk_sub_estatus_conciliacion SET description = 'Consulta Proveedor Completada' WHERE id = 6;
UPDATE tk_sub_estatus_conciliacion SET description = 'Consulta Proveedor Error' WHERE id = 7;

UPDATE `tk_estatus_movimientos_en_transito` SET `nombre` = 'No identificada en Proveedor', `descripcion` = 'No identificada en Proveedor' WHERE (`id` = '4');
