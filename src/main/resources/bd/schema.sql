-- Inicio juste a la tabla de Cliente
ALTER TABLE `tarjetas`
DROP FOREIGN KEY `cliente_tarjeta_fk`;
ALTER TABLE `tarjetas`
DROP INDEX `cliente_tarjeta_fk_idx` ;

ALTER TABLE `transacciones`
DROP FOREIGN KEY `cliente_transaccion_fk`;
ALTER TABLE `transacciones`
DROP INDEX `cliente_transaccion_fk_idx` ;

ALTER TABLE `cliente`
DROP COLUMN `app_materno`,
DROP COLUMN `app_paterno`,
CHANGE COLUMN `idcliente` `id_cliente` INT(11) NOT NULL ,
CHANGE COLUMN `nombre` `nombre_titular` VARCHAR(100) NOT NULL ;

ALTER TABLE `tarjetas`
CHANGE COLUMN `idcliente` `id_cliente` INT(11) NOT NULL ;

ALTER TABLE `tarjetas`
ADD INDEX `cliente_tarjeta_fk_idx` (`id_cliente` ASC);
ALTER TABLE `tarjetas`
ADD CONSTRAINT `cliente_tarjeta_fk`
  FOREIGN KEY (`id_liente`)
  REFERENCES `cliente` (`id_cliente`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `transacciones`
ADD INDEX `cliente_transacion_fk_idx` (`id_cliente` ASC);
ALTER TABLE `transacciones`
ADD CONSTRAINT `cliente_transacion_fk`
  FOREIGN KEY (`id_cliente`)
  REFERENCES `cliente` (`id_cliente`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

-- Fin juste a la tabla de Cliente

-- DSS MODULE CREATION BEGINS
USE compose;

CREATE TABLE `catalogo_afiliacion` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ca_fk_idx` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

CREATE TABLE `regla_negocio` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50)NOT NULL,
  `descripcion` VARCHAR(100) NOT NULL,
  `consulta` VARCHAR(500) NOT NULL,
  `id_afiliacion` INT(11) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `rn_fk_idx` (`id`),
    CONSTRAINT `ca_fk` FOREIGN KEY (`id_afiliacion`) REFERENCES `catalogo_afiliacion` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

CREATE TABLE `cliente_regla_negocio` (
  `id_cliente` INT(11) NOT NULL,
  `id_regla_negocio` INT(11) NOT NULL,
	CONSTRAINT `ic_fk` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`),
	CONSTRAINT `irn_fk` FOREIGN KEY (`id_regla_negocio`) REFERENCES `regla_negocio` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

CREATE TABLE `variable` (
  `id_variable` INT(11) NOT NULL,
  `clave` VARCHAR(100) NOT NULL,
  `valor` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id_variable`),
  KEY `va_fk_idx` (`id_variable`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

CREATE TABLE `regla_negocio_variable` (
  `id_regla_negocio` INT(11) NOT NULL,
  `id_variable` INT(11) NOT NULL,
  CONSTRAINT `id_fk` FOREIGN KEY (`id_regla_negocio`) REFERENCES `regla_negocio` (`id`),
  CONSTRAINT `idv_fk` FOREIGN KEY (`id_variable`) REFERENCES `variable` (`id_variable`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

-- DSS MODULE CREATION ENDS

-- ADD OPENPAY ID

ALTER TABLE `compose`.`tarjetas`
ADD COLUMN `id_openpay` VARCHAR(40) NULL AFTER `estatus_tarjeta_c`;

-- END OPENPAY ID

-- NEW CATALOGS [catalogo_afiliacion, estatus_transaccion_c, regla_negocio, variable]
INSERT INTO cat_catalogo(id, descripcion_corta, descripcion, nombre_tabla, activo)
	VALUES(3, 'Afiliacion', 'Catalogo de afiliaciones', 'tc_afiliacion', 1),
    (4, 'EstatusPago', 'Estatus de las transacciones o pagos', 'tk_estatus_transaccion', 1),
    (5, 'ReglaNegocio', 'Reglas de negocio', 'tk_regla_negocio', 1),
    (6, 'Variable', 'Variables para la aplicacion', 'tk_variable', 1),
    (7, 'Categoria', 'Categorias de los codigos de estado de cuenta', 'tk_categoria', 1)
    ;
-- NEW CATALOGS ENDS

select len('Categorias de los codigos de estado de cuenta');

-- INSERT DEFAULT VALUE IN STATUS TRANSACTION CATALOG
INSERT INTO estatus_transaccion_c(id, descripcion_corta, descripcion) VALUES(1,'PRegistrado','Pago Registrado');
-- INSERT INTO STATUS TRANSACTION ENDS

-- NEW COLUMNS FOR VALIDATION IDEMPOTENT REQUEST BEGINS
ALTER TABLE pagos ADD COLUMN id_transaccion_midas INT NULL;
ALTER TABLE pagos ADD COLUMN folio_partida INT NULL;
ALTER TABLE pagos ADD COLUMN id_operacion INT NULL;
-- NEW COLUMNS FOR IDEMPOTENT REQUEST ENDS
-- INDEXES FOR NEW COLUMNS BEGINS
CREATE INDEX idx_id_transaccion_midas ON pagos(id_transaccion_midas);
CREATE INDEX idx_folio_partida ON pagos(folio_partida);
CREATE INDEX idx_id_operacion ON pagos(id_operacion);
-- INDEXES FOR NEW COLUMNS ENDS

-- ALTER CAMBIO DE DATO TOKEN Y OPENPAY
ALTER TABLE `compose`.`tarjetas`
CHANGE COLUMN `token` `id_openpay` VARCHAR(40) NOT NULL ,
CHANGE COLUMN `id_openpay` `token` VARCHAR(40) NULL DEFAULT NULL ;
-- FIN CAMBIO TOKEN Y OPENPAY

-- PENDIENTES A SUBIR A LA FECHA [01/01/2019]
-- MODIFICACION DE ID DE CLIENTE POR BIGINT PARA SOPORTAR IDS MAS GRANDES
SET foreign_key_checks = 0;
ALTER TABLE cliente MODIFY id_cliente BIGINT UNSIGNED NOT NULL;
ALTER TABLE tarjetas MODIFY id_cliente BIGINT UNSIGNED NOT NULL;
ALTER TABLE pagos MODIFY idcliente BIGINT UNSIGNED NOT NULL;
ALTER TABLE cliente_regla_negocio MODIFY id_cliente BIGINT UNSIGNED NOT NULL;
SET foreign_key_checks = 1;
-- TERMINA MODIFICAICION DE ID DE CLIENTE PARA SOPORTAR IDS MAS GTRANDES

-- SE AGREGA COLUMNA DE VERIFICACION DE 3D SECURE A CATALOGO DE AFILIACION
ALTER TABLE catalogo_afiliacion ADD COLUMN tipo INT(1) NULL;

-- SE MODIFICA ID DE de tabla pagos para soportar numero mas grandes
ALTER TABLE pagos MODIFY id BIGINT UNSIGNED NOT NULL;

-- CREACION DE CATALOG DE ESTATUS DE OPERACION
-- -----------------------------------------------------
-- Table `compose`.`estatus_operacion_c`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `compose`.`estatus_operacion_c` ;

CREATE TABLE IF NOT EXISTS `compose`.`estatus_operacion_c` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `descripcion_corta` VARCHAR(45) NULL DEFAULT NULL,
  `descripcion` VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = latin1;

-- -----------------------------------------------------
-- Table `compose`.`tipo_afiliacion_c`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `compose`.`tipo_afiliacion_c` ;

CREATE TABLE IF NOT EXISTS `compose`.`tipo_afiliacion_c` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `descripcion_corta` VARCHAR(45) NULL DEFAULT NULL,
  `descripcion` VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = latin1;

-- ALTER DE COLUMNA TIPO EN TABLA catalogo_afiliacion PARA CONVERTIRLO EN FOREING KEY
ALTER TABLE catalogo_afiliacion MODIFY tipo INT(11) NOT NULL;

-- CREACION DE DE FOREIGN KEY A TABLA catalogo_afiliaicon DE ID TIPO AFILIACION
SET foreign_key_checks = 0;
ALTER TABLE catalogo_afiliacion ADD CONSTRAINT fk_id_tipo_afiliacion FOREIGN KEY (tipo) REFERENCES tipo_afiliacion_c(id);
SET foreign_key_checks = 1;

-- ALTER pagos MODIFY id_transaccion_midas AND folio_partida FROM INT TO BIGINT UNSIGNED[06-02-2019 : 12:41 hrs.]
ALTER TABLE pagos MODIFY id_transaccion_midas BIGINT UNSIGNED NULL DEFAULT NULL;
ALTER TABLE pagos MODIFY folio_partida BIGINT UNSIGNED NULL DEFAULT NULL;

-- RENOMBRADO DE TABLAS PARA SEGUIR ESTANDAR DE NOMBRADO DE MIDAS --
RENAME TABLE cat_catalogo TO tk_catalogo;
RENAME TABLE catalogo_afiliacion TO tk_afiliacion;
RENAME TABLE cliente TO tk_cliente;
RENAME TABLE cliente_regla_negocio TO tr_cliente_regla_negocio;
RENAME TABLE estatus_operacion_c TO tk_estatus_operacion;
RENAME TABLE estatus_tarjeta_c TO tk_estatus_tarjeta;
RENAME TABLE estatus_transaccion_c TO tk_estatus_transaccion;
RENAME TABLE pagos TO to_pagos;
RENAME TABLE regla_negocio TO tk_regla_negocio;
RENAME TABLE regla_negocio_variable TO tr_regla_negocio_variable;
RENAME TABLE tarjetas TO tc_tarjetas;
RENAME TABLE tipo_afiliacion_c TO tk_tipo_afiliacion;
RENAME TABLE tipo_tarjeta_c TO tk_tipo_tarjeta;
RENAME TABLE variable TO tk_variable;

ALTER TABLE tc_tarjetas CHANGE tipo_tarjeta_c_id id_tipo_tarjeta INT(11) NOT NULL;
ALTER TABLE tc_tarjetas CHANGE estatus_tarjeta_c id_estatus_tarjeta INT(11) NOT NULL;

ALTER TABLE to_pagos CHANGE idcliente id_cliente BIGINT(20) UNSIGNED NOT NULL;
ALTER TABLE to_pagos CHANGE idopenpay id_openpay VARCHAR(100) NULL;
ALTER TABLE to_pagos CHANGE idorder id_order VARCHAR(100) NULL;
ALTER TABLE to_pagos CHANGE estatus_transaccion id_estatus_transaccion INT(11) NOT NULL;
ALTER TABLE to_pagos CHANGE restresponse rest_response VARCHAR(400) NULL;


-- INICIAN MODIFICACIONES EN MODULO CATALOGOS --

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE tk_afiliacion;
-- -----------------------------------------------------
-- Table `tc_afiliacion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tc_afiliacion` ;

CREATE TABLE IF NOT EXISTS `tc_afiliacion` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(150) NULL DEFAULT NULL,
  `tipo` INT(11) NOT NULL,
  `estatus` BIT(1) NOT NULL DEFAULT b'1',
  `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_date` DATETIME NULL DEFAULT NULL,
  `created_by` VARCHAR(100) NULL DEFAULT NULL,
  `last_modified_by` VARCHAR(100) NULL DEFAULT NULL,
  `short_description` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `ca_fk_idx` (`id` ASC),
  INDEX `´ctr_tipo_afiliacion_afi1´` (`tipo` ASC),
  CONSTRAINT `´ctr_tipo_afiliacion_afi1´`
    FOREIGN KEY (`tipo`)
    REFERENCES `tk_tipo_afiliacion` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = latin1;

-- -----------------------------------------------------
-- Table `tk_categoria`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tk_categoria` ;

CREATE TABLE IF NOT EXISTS `tk_categoria` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
  `description` VARCHAR(150) NULL DEFAULT NULL,
  `estatus` BIT(1) NOT NULL DEFAULT b'1',
  `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_date` DATETIME NULL DEFAULT NULL,
  `created_by` VARCHAR(100) NULL DEFAULT NULL,
  `last_modified_by` VARCHAR(100) NULL DEFAULT NULL,
  `short_description` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `idx_categoria` (`id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

-- -----------------------------------------------------
-- Table `tc_codigo_estado_cuenta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tc_codigo_estado_cuenta` ;

CREATE TABLE IF NOT EXISTS `tc_codigo_estado_cuenta` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `estatus` BIT(1) NOT NULL DEFAULT b'1',
  `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_date` DATETIME NULL DEFAULT NULL,
  `created_by` VARCHAR(100) NULL DEFAULT NULL,
  `last_modified_by` VARCHAR(100) NULL DEFAULT NULL,
  `id_categoria` BIGINT(20) NOT NULL,
  `codigo` VARCHAR(20) NULL DEFAULT NULL,
  `description` VARCHAR(150) NULL DEFAULT NULL,
  `short_description` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `idx_codigo_estado_cuenta` (`id` ASC),
  INDEX `idx_categoria` (`id_categoria` ASC),
  CONSTRAINT `ctr_fk_categoria`
    FOREIGN KEY (`id_categoria`)
    REFERENCES `tk_categoria` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

-- -----------------------------------------------------
-- Table `tk_tipo_contacto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tk_tipo_contacto` ;

CREATE TABLE IF NOT EXISTS `tk_tipo_contacto` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `estatus` BIT(1) NOT NULL,
  `description` VARCHAR(150) NULL DEFAULT NULL,
  `created_by` VARCHAR(100) NOT NULL,
  `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` VARCHAR(100) NULL DEFAULT NULL,
  `last_modified_date` DATETIME NULL DEFAULT NULL,
  `short_description` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `tc_contactos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tc_contactos` ;

CREATE TABLE IF NOT EXISTS `tc_contactos` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `estatus` BIT(1) NOT NULL,
  `nombre` VARCHAR(100) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `description` VARCHAR(150) NULL DEFAULT NULL,
  `id_tipo_contacto` BIGINT(20) NULL DEFAULT NULL,
  `created_by` VARCHAR(100) NOT NULL,
  `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` VARCHAR(100) NULL DEFAULT NULL,
  `last_modified_date` DATETIME NULL DEFAULT NULL,
  `short_description` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `id_tipo_contacto_fk_idx` (`id_tipo_contacto` ASC),
  CONSTRAINT `id_tipo_contacto_fk`
    FOREIGN KEY (`id_tipo_contacto`)
    REFERENCES `tk_tipo_contacto` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `tc_cuenta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tc_cuenta` ;

CREATE TABLE IF NOT EXISTS `tc_cuenta` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `estatus` BIT(1) NOT NULL DEFAULT b'1',
  `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_date` DATETIME NULL DEFAULT NULL,
  `created_by` VARCHAR(100) NULL DEFAULT NULL,
  `last_modified_by` VARCHAR(100) NULL DEFAULT NULL,
  `numero_cuenta` VARCHAR(50) NULL DEFAULT NULL,
  `short_description` VARCHAR(100) NULL DEFAULT NULL,
  `description` VARCHAR(150) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `idx_cuenta` (`id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

-- -----------------------------------------------------
-- Table `tc_entidad`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tc_entidad` ;

CREATE TABLE IF NOT EXISTS `tc_entidad` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `estatus` BIT(1) NOT NULL DEFAULT b'1',
  `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_date` DATETIME NULL DEFAULT NULL,
  `created_by` VARCHAR(100) NULL DEFAULT NULL,
  `last_modified_by` VARCHAR(100) NULL DEFAULT NULL,
  `nombre` VARCHAR(100) NOT NULL,
  `description` VARCHAR(150) NULL DEFAULT NULL,
  `short_description` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `idx_entidad` (`id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

-- -----------------------------------------------------
-- Table `tr_codigo_estado_cuenta_entidad`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tr_codigo_estado_cuenta_entidad` ;

CREATE TABLE IF NOT EXISTS `tr_codigo_estado_cuenta_entidad` (
  `id_codigo_estado_cuenta` BIGINT(20) NOT NULL,
  `id_entidad` BIGINT(20) NOT NULL,
  INDEX `idx_codigo_estado_cuenta` (`id_codigo_estado_cuenta` ASC),
  INDEX `idx_entidad` (`id_entidad` ASC),
  CONSTRAINT `ctr_fk_codigo_estado_cuent_tr1`
    FOREIGN KEY (`id_codigo_estado_cuenta`)
    REFERENCES `tc_codigo_estado_cuenta` (`id`),
  CONSTRAINT `ctr_fk_entidad_tr3`
    FOREIGN KEY (`id_entidad`)
    REFERENCES `tc_entidad` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

-- -----------------------------------------------------
-- Table `tr_cuenta_afiliacion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tr_cuenta_afiliacion` ;

CREATE TABLE IF NOT EXISTS `tr_cuenta_afiliacion` (
  `id_cuenta` BIGINT(20) NOT NULL,
  `id_afiliacion` BIGINT(20) NOT NULL,
  INDEX `idx_cuenta` (`id_cuenta` ASC),
  INDEX `idx_afiliacion` (`id_afiliacion` ASC),
  CONSTRAINT `ctr_fk_afiliacion_tr1`
    FOREIGN KEY (`id_afiliacion`)
    REFERENCES `tc_afiliacion` (`id`),
  CONSTRAINT `ctr_fk_cuenta_tr1`
    FOREIGN KEY (`id_cuenta`)
    REFERENCES `tc_cuenta` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

-- -----------------------------------------------------
-- Table `tr_entidad_contactos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tr_entidad_contactos` ;

CREATE TABLE IF NOT EXISTS `tr_entidad_contactos` (
  `id_entidad` BIGINT(20) NOT NULL,
  `id_contacto` BIGINT(20) NOT NULL,
  INDEX `idx_entidad` (`id_entidad` ASC),
  INDEX `idx_contacto` (`id_contacto` ASC),
  CONSTRAINT `ctr_fk_contacto_tr1`
    FOREIGN KEY (`id_contacto`)
    REFERENCES `tc_contactos` (`id`),
  CONSTRAINT `ctr_fk_entidad_tr2`
    FOREIGN KEY (`id_entidad`)
    REFERENCES `tc_entidad` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

-- -----------------------------------------------------
-- Table `tr_entidad_cuenta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tr_entidad_cuenta` ;

CREATE TABLE IF NOT EXISTS `tr_entidad_cuenta` (
  `id_entidad` BIGINT(20) NOT NULL,
  `id_cuenta` BIGINT(20) NOT NULL,
  INDEX `idx_entidad` (`id_entidad` ASC),
  INDEX `idx_cuenta` (`id_cuenta` ASC),
  CONSTRAINT `ctr_fk_cuenta_tr2`
    FOREIGN KEY (`id_cuenta`)
    REFERENCES `tc_cuenta` (`id`),
  CONSTRAINT `ctr_fk_entidad_tr1`
    FOREIGN KEY (`id_entidad`)
    REFERENCES `tc_entidad` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

ALTER TABLE tc_tarjetas DROP FOREIGN KEY estatus_tarjeta_fk;
ALTER TABLE tc_tarjetas DROP FOREIGN KEY tipo_tarjeta_tarjeta_fk;

ALTER TABLE tk_regla_negocio DROP FOREIGN KEY ca_fk;

ALTER TABLE to_pagos DROP FOREIGN KEY esatus_transaccion_fk;

ALTER TABLE tr_cliente_regla_negocio DROP FOREIGN KEY ic_fk;
ALTER TABLE tr_cliente_regla_negocio DROP FOREIGN KEY irn_fk;

ALTER TABLE tk_cliente MODIFY COLUMN fecha_alta DATETIME NOT NULL;

desc tc_tarjetas;

ALTER TABLE tc_tarjetas ADD FOREIGN KEY estatus_tarjeta_fk (id_estatus_tarjeta) REFERENCES tk_estatus_tarjeta (id);
ALTER TABLE tc_tarjetas ADD FOREIGN KEY tipo_tarjeta_tarjeta_fk (id_tipo_tarjeta) REFERENCES tk_tipo_tarjeta (id);

ALTER TABLE tk_regla_negocio CHANGE id_afiliacion id_afiliacion BIGINT(20) NOT NULL;
ALTER TABLE tk_regla_negocio ADD FOREIGN KEY ctr_afiliacion_fk1 (id_afiliacion) REFERENCES tc_afiliacion(id);

ALTER TABLE to_pagos CHANGE id_estatus_transaccion id_estatus_transaccion INT(11) NOT NULL;

ALTER TABLE tr_cliente_regla_negocio ADD FOREIGN KEY ic_fk_2(id_cliente) REFERENCES tk_cliente(id_cliente);
ALTER TABLE tr_cliente_regla_negocio ADD FOREIGN KEY irn_fk(id_regla_negocio) REFERENCES tk_regla_negocio(id);

ALTER TABLE tc_afiliacion ADD COLUMN numero BIGINT(20) NOT NULL;

SET FOREIGN_KEY_CHECKS=1;


-- INICIA MODIFICACION DE RELACION ENTRE CATALOGO CODIGO ESTADO CUENTA Y ENTIDAD --
-- [22/03/2019 | 15:47:00 hrs]
SET FOREIGN_KEY_CHECKS=0;
TRUNCATE tr_codigo_estado_cuenta_entidad;
DROP TABLE tr_codigo_estado_cuenta_entidad;
ALTER TABLE tc_codigo_estado_cuenta ADD COLUMN id_entidad BIGINT(20) NOT NULL;
ALTER TABLE tc_codigo_estado_cuenta ADD FOREIGN KEY ctr_fk_id_entidad5 (id_entidad) REFERENCES tc_entidad (id);
SET FOREIGN_KEY_CHECKS=1;
-- FINALIZA MODIFICACION DE RELACION ENTRE CATALOGO CODIGO ESTADO CUENTA Y ENTIDAD --


-- SE CREAN PRIMARY KEYS DE LAS TABLAS RELACIONALES PARA EVITAR ERROR DE MYSQL EN BASE DE DATOS DEV --
-- [2019-03-28 10:27:14] --
ALTER TABLE tr_cuenta_afiliacion ADD PRIMARY KEY (id_cuenta, id_afiliacion);
ALTER TABLE tr_entidad_contactos ADD PRIMARY KEY (id_entidad, id_contacto);
ALTER TABLE tr_entidad_cuenta ADD PRIMARY KEY (id_entidad, id_cuenta);
-- FINALIZA CREACION DE PRIMARY KEYS PARA TABLAS RELACIONALES --

