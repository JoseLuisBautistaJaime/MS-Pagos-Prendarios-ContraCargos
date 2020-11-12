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
  `comision` DECIMAL(5,2) NOT NULL,
  `iva` DECIMAL(5,2) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `cp_fk_idx` (`id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


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

END MAIN;
$$
DELIMITER ;
