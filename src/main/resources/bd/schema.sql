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


-- SE MODIFICA TIPO DE DATO DE NUMERO DE AFILIACION DE NUMERICO A ALFANUMERICO --
ALTER TABLE tc_afiliacion CHANGE numero numero VARCHAR(100);

-- ELIMINACION DE TABLA TC_ENTIDA_CUENTA POR CAMBIO DE LOGICA DE FUNCIONAMIENTO DE CATALOGO ENTIDAD --
TRUNCATE TABLE tr_entidad_cuenta;
DROP TABLE tr_entidad_cuenta;

-- CREACION DE NUEVA TABLA DE ASOCIACION ENTIDAD-CUENTA-AFILIACION --

-- -----------------------------------------------------
-- Table`tc_entidad_cuenta_afiliacion`
-- -----------------------------------------------------

DROP TABLE IF EXISTS`tc_entidad_cuenta_afiliacion` ;

CREATE TABLE IF NOT EXISTS`tc_entidad_cuenta_afiliacion` (
  `id_entidad` BIGINT(20) NOT NULL,
  `id_cuenta` BIGINT(20) NOT NULL,
  `id_afiliacion` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id_entidad`, `id_cuenta`, `id_afiliacion`),
  INDEX `idx_eca1_entidad` (`id_entidad` ASC),
  INDEX `idx_eca1_cuenta` (`id_cuenta` ASC),
  INDEX `idx_eca1_afiliacion` (`id_afiliacion` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = latin1;


-- INSERCION INICIAL DE TIPOS DE CONTACTOS --

INSERT INTO `compose`.`tk_tipo_contacto`
(`id`,
`estatus`,
`description`,
`created_by`,
`created_date`,
`last_modified_by`,
`last_modified_date`,
`short_description`)
VALUES
(1,
true,
'Contacto Midas',
'Ismael',
now(),
null,
null,
'Contacto Midas');

INSERT INTO `compose`.`tk_tipo_contacto`
(`id`,
`estatus`,
`description`,
`created_by`,
`created_date`,
`last_modified_by`,
`last_modified_date`,
`short_description`)
VALUES
(2,
true,
'Contacto Entidad',
'Ismael',
now(),
null,
null,
'Contacto Ent');

-- RENOMBRADO DE TABLA tc_entidad_cuenta_afiliacion --
ALTER TABLE tc_entidad_cuenta_afiliacion RENAME TO tr_entidad_cuenta_afiliacion;

-- INSERCION DE TIPOS DE AFILIACION --
INSERT INTO `compose`.`tk_tipo_afiliacion`
(`id`,
`descripcion_corta`,
`descripcion`)
VALUES
(1,
'Ninguno',
'Ningun tipo de afiliacion'),
(2,
'3DSecure',
'3DSecure');

-- INSERCION DE CATEGORIAS DUMMIES --

INSERT INTO `compose`.`tk_categoria`
(`id`,
`nombre`,
`descripcion`,
`estatus`,
`created_date`,
`last_modified_date`,
`created_by`,
`last_modified_by`,
`descripcion_corta`)
VALUES
(1,
'Ventas',
'Categoria Ventas',
true,
now(),
null,
'Quarksoft',
null,
'Cat Vtas'),
(2,
'Comisiones',
'Categoria Comisiones',
true,
now(),
null,
'Quarksoft',
null,
'Cat Com'),
(3,
'Otra',
'Otra Categoria',
true,
now(),
null,
'Quarksoft',
null,
'Otra Cat');

-- INICIA CREACION DE OBJETOS PARA MODULO CONCILIACION -
-- [2019-04-04 09:58:20] --

DROP TABLE IF EXISTS to_movimiento_comision;
DROP TABLE IF EXISTS to_movimiento_pago;
DROP TABLE IF EXISTS to_movimiento_devolucion;
DROP TABLE IF EXISTS to_movimiento_transito;
DROP TABLE IF EXISTS to_movimiento_conciliacion;
DROP TABLE IF EXISTS to_movimiento_estado_cuenta;
DROP TABLE IF EXISTS to_movimiento_midas;
DROP TABLE IF EXISTS to_movimiento_proveedor;
DROP TABLE IF EXISTS to_reporte;
DROP TABLE IF EXISTS to_layout_linea;
DROP TABLE IF EXISTS to_layout_header;
DROP TABLE IF EXISTS to_layout;
DROP TABLE IF EXISTS to_global;
DROP TABLE IF EXISTS to_conciliacion;


CREATE TABLE to_conciliacion (
	id INTEGER NOT NULL,
	estatus INTEGER NOT NULL,
	entidad INTEGER NOT NULL,
	cuenta INTEGER NOT NULL,
	peoplesoft_id VARCHAR(100),
	created_date DATETIME,
	created_by VARCHAR(100),
	last_modified_by VARCHAR(100),
	last_modified_date DATETIME,
	PRIMARY KEY (id)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE to_global (
	id INTEGER NOT NULL,
	id_conciliacion INTEGER NOT NULL,
	fecha DATE NOT NULL,
	movimientos INTEGER,
	partidas INTEGER,
	monto DECIMAL(16,4),
	importe_midas DECIMAL(16,4),
	importe_proveedor DECIMAL(16,4),
	importe_banco DECIMAL(16,4),
	devoluciones INTEGER,
	diferencia_proveedor_midas DECIMAL(16,4),
	diferencia_proveedor_banco DECIMAL(16,4),
	PRIMARY KEY (id),
	UNIQUE UQ_to_global_conciliacion(conciliacion),
	CONSTRAINT FK_to_global_to_conciliacion 
		FOREIGN KEY (conciliacion) REFERENCES to_conciliacion (id)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE to_movimiento_conciliacion (
	id INTEGER NOT NULL,
	conciliacion INTEGER NOT NULL,
	created_by VARCHAR(100),
	created_date DATETIME,
	last_modified_by VARCHAR(100),
	last_modified_date DATETIME,
	nuevo TINYINT,
	PRIMARY KEY (id),
	CONSTRAINT FK_to_movimiento_conciliacion_to_conciliacion 
	FOREIGN KEY (conciliacion) REFERENCES to_conciliacion (id)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE to_movimiento_comision (
	id INTEGER NOT NULL,
	fecha_operacion DATE,
	fecha_cargo DATE,
	monto DECIMAL(16,4) NOT NULL,
	descripcion VARCHAR(150) NOT NULL,
	PRIMARY KEY (id),
    CONSTRAINT FK_comisiones_to_movimiento_conciliacion 
	FOREIGN KEY (id) REFERENCES to_movimiento_conciliacion (id)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE to_movimiento_pago (
	id INTEGER NOT NULL,
	estatus INTEGER NOT NULL,
	PRIMARY KEY (id),
    CONSTRAINT FK_to_movimiento_pago_to_movimiento_conciliacion 
		FOREIGN KEY (id) REFERENCES to_movimiento_conciliacion (id)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE to_movimiento_devolucion (
	id INTEGER NOT NULL,
	estatus INTEGER NOT NULL,
	fecha DATE NOT NULL,
	monto DECIMAL(16,4) NOT NULL,
	esquema_tarjeta VARCHAR(45),
	identificador_cuenta VARCHAR(45),
	titular VARCHAR(255),
	codigo_autorizacion VARCHAR(45),
	sucursal INTEGER,
	PRIMARY KEY (id),
    CONSTRAINT FK_to_movimiento_devolucion_to_movimiento_conciliacion 
		FOREIGN KEY (id) REFERENCES to_movimiento_conciliacion (id)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE to_movimiento_transito (
	id INTEGER NOT NULL,
	estatus INTEGER NOT NULL,
	folio INTEGER,
	sucursal INTEGER,
	fecha DATE,
	operacion_desc VARCHAR(45),
	monto DECIMAL(16,4),
	tipo_contrato_desc VARCHAR(45),
	esquema_tarjeta VARCHAR(45),
	cuenta VARCHAR(45),
	titular VARCHAR(255),
	PRIMARY KEY (id),
    CONSTRAINT FK_to_movimiento_transito_to_movimiento_conciliacion 
		FOREIGN KEY (id) REFERENCES to_movimiento_conciliacion (id)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1;



CREATE TABLE to_reporte (
	id INTEGER NOT NULL AUTO_INCREMENT,
	conciliacion INTEGER NOT NULL,
	tipo VARCHAR(45) NOT NULL,
	disponible TINYINT NOT NULL DEFAULT 0,
	fecha_desde DATE NOT NULL,
	fecha_hasta DATE NOT NULL,
	created_date DATETIME,
	created_by VARCHAR(100),
	last_modified_by VARCHAR(100),
	last_modified_date DATETIME,
	PRIMARY KEY (id),
	CONSTRAINT FK_to_carga_reporte_to_conciliacion 
		FOREIGN KEY (conciliacion) REFERENCES to_conciliacion (id)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE to_movimiento_estado_cuenta (
	id INTEGER NOT NULL AUTO_INCREMENT,
	id_reporte INTEGER NOT NULL,
	fecha DATE NOT NULL,
	descripcion VARCHAR(150),
	depositos DECIMAL(16,4),
	retiros DECIMAL(16,4),
	saldo DECIMAL(16,4),
	PRIMARY KEY (id),
	CONSTRAINT FK_to_movimiento_estado_cuenta_to_reporte 
		FOREIGN KEY (reporte) REFERENCES to_reporte (id)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE to_movimiento_midas (
	id INTEGER NOT NULL AUTO_INCREMENT,
	id_reporte INTEGER NOT NULL,
	folio INTEGER,
	sucursal INTEGER,
	operacion_abr VARCHAR(10),
	operacion_desc VARCHAR(45),
	monto DECIMAL(16,4),
	tipo_contrato_abr VARCHAR(10),
	tipo_contrato_desc VARCHAR(45),
	num_autorizacion VARCHAR(45),
	capital DECIMAL(16,4),
	comisiones DECIMAL(16,4),
	interes DECIMAL(16,4),
    transaccion BIGINT(20),
    fecha DATETIME,
    estado_transaccion VARCHAR(100) NULL DEFAULT NULL,
    consumidor VARCHAR(50) NULL DEFAULT NULL,
    codigo_error VARCHAR(50) NULL DEFAULT NULL,
	mensaje_error VARCHAR(50) NULL DEFAULT NULL,
	id_tarjeta VARCHAR(50) NULL DEFAULT NULL,
	marca_tarjeta VARCHAR(50) NULL DEFAULT NULL,
	tipo_tarjeta VARCHAR(50) NULL DEFAULT NULL,
	tarjeta VARCHAR(50) NULL DEFAULT NULL,
	moneda_pago VARCHAR(50) NULL DEFAULT NULL,
    importe_transaccion DECIMAL (16,4) NULL DEFAULT NULL,
	estatus BIT DEFAULT TRUE,
	PRIMARY KEY (id),
	CONSTRAINT FK_to_movimiento_midas_to_reporte 
		FOREIGN KEY (reporte) REFERENCES to_reporte (id)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `to_movimiento_proveedor` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `id_movimiento` VARCHAR(100) NULL DEFAULT NULL,
  `id_reporte` int(11) NOT NULL,
  `authorization` VARCHAR(50) NOT NULL,
  `operation_type` VARCHAR(50) NOT NULL,
  `method` VARCHAR(50) NOT NULL,
  `transaction_type` VARCHAR(50) NOT NULL,
  `status` VARCHAR(50) NOT NULL,
  `conciliated` BIT NOT NULL DEFAULT FALSE,
  `creation_date` DATETIME NOT NULL,
  `operation_date` DATETIME NOT NULL,
  `description` VARCHAR(50) NOT NULL,
  `error_message` VARCHAR(50) NOT NULL,
  `order_id` VARCHAR(50) NOT NULL,
  `customer_id` VARCHAR(50) NOT NULL,
  `error_code` VARCHAR(50) NOT NULL,
  `currency` VARCHAR(50) NOT NULL,
  `amount` DECIMAL(16,4) NOT NULL,
  `payment_method_type` VARCHAR(50) NOT NULL,
  `payment_method_url` VARCHAR(50) NOT NULL,
  `card_id` VARCHAR(50) NOT NULL,
  `card_type` VARCHAR(50) NOT NULL,
  `card_brand` VARCHAR(50) NOT NULL,
  `card_address` VARCHAR(50) NOT NULL,
  `card_number` VARCHAR(50) NOT NULL,
  `card_holder_name` VARCHAR(50) NOT NULL,
  `card_expiration_year` VARCHAR(50) NOT NULL,
  `card_expiration_month` VARCHAR(50) NOT NULL,
  `card_allows_charges` BIT NOT NULL,
  `card_allows_payouts` BIT NOT NULL,
  `card_creation_date` DATETIME NOT NULL,
  `card_bank_name` VARCHAR(50) NOT NULL,
  `card_bank_code` VARCHAR(50) NOT NULL,
  `card_customer_id` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_to_movimiento_midas_to_reporte1` (`id_reporte`),
  CONSTRAINT `fk_to_movimiento_midas_to_reporte1` FOREIGN KEY (`id_reporte`) REFERENCES `to_reporte` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;


-- Layouts

CREATE TABLE to_layout (
	id INTEGER NOT NULL,
	id_conciliacion INTEGER NOT NULL,
	tipo VARCHAR(20) NOT NULL,
	PRIMARY KEY (id),
	CONSTRAINT FK_to_layout_to_conciliacion 
		FOREIGN KEY (conciliacion) REFERENCES to_conciliacion (id)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE to_layout_linea (
	id INTEGER NOT NULL,
	id_layout INTEGER NOT NULL,
	linea VARCHAR(10) NOT NULL,
	cuenta VARCHAR(45),
	dep_id VARCHAR(45),
	unidad_operativa VARCHAR(10),
	negocio VARCHAR(45),
	proyecto_nmp VARCHAR(45),
	monto DECIMAL(16,4) NOT NULL,
	create_date DATETIME,
	created_by VARCHAR(100),
	last_modified_by VARCHAR(100),
	last_modified_date DATETIME,
	PRIMARY KEY (id),
	CONSTRAINT FK_to_layout_linea_to_layout 
		FOREIGN KEY (layout) REFERENCES to_layout (id)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE to_layout_header (
	id INTEGER NOT NULL,
	id_layout INTEGER NOT NULL,
	cabecera VARCHAR(10) NOT NULL,
	unidad_negocio VARCHAR(45),
	descripcion VARCHAR(150),
	codigo_origen VARCHAR(45),
	fecha DATE,
	campo1 VARCHAR(45),
	campo2 VARCHAR(45),
	create_date DATETIME,
	created_by VARCHAR(100),
	last_modified_by VARCHAR(100),
	last_modified_date DATETIME,
	PRIMARY KEY (id),
	CONSTRAINT FK_to_layout_header_to_layout 
		FOREIGN KEY (layout) REFERENCES to_layout (id)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- FINALIZA CREACION DE OBJETOS PARA MODULO CONCILIACION --

-- INICIAN AJUSTES PARA SEGUIR ESTANDAR DE NOMBRADO EN FOREIGN KEYS --

-- ALTER TABLE to_movimiento_estado_cuenta CHANGE reporte id_reporte INT(11) NOT NULL;
-- ALTER TABLE to_movimiento_midas CHANGE reporte id_reporte INT(11) NOT NULL;
-- ALTER TABLE to_reporte CHANGE conciliacion id_conciliacion INT(11) NOT NULL;
-- ALTER TABLE to_layout_linea CHANGE layout id_layout INT(11) NOT NULL;
-- ALTER TABLE to_layout_header CHANGE layout id_layout INT(11) NOT NULL;
-- ALTER TABLE to_layout CHANGE conciliacion id_conciliacion INT(11) NOT NULL;
-- ALTER TABLE to_global CHANGE conciliacion id_conciliacion INT(11) NOT NULL;

-- FINALIZAN AJUSTES PARA SEGUIR ESTANDAR DE NOMBRADO EN FOREIGN KEYS --


-- SE AGREGAN COLUMNAS DE TRANSACCION Y FECHA FALTANTES A TABLA DE MOVIMIENTOS MIDAS --
-- [2019-04-29 17:57:24] --
-- ALTER TABLE to_movimiento_midas ADD COLUMN transaccion BIGINT(20);
-- ALTER TABLE to_movimiento_midas ADD COLUMN fecha DATETIME;

-- SE REALIZA RENOMBRADO DE COLUMNA reporte EN TABLA to_movimiento_proveedor para ajustar a estandar de noombrado --
-- y se agrega clave foranea que apunte a el id de la tabla to_reporte --
-- [2019-04-30 11:45:19] --
-- ALTER TABLE to_movimiento_proveedor change reporte id_reporte INT(11);
-- ALTER TABLE to_movimiento_proveedor ADD CONSTRAINT `fk_tmp_tr_1` FOREIGN KEY (`id_reporte`) REFERENCES `to_reporte` (`id`);

-- SE REALIZA CORRECCION DE NOMBRADO EN CAMPO created_date DE TABLA to_reporte y to_conciliacion --
-- [2019-04-30 12:24:57] --
-- ALTER TABLE to_reporte CHANGE create_date created_date DATETIME NULL DEFAULT NULL;
-- ALTER TABLE to_conciliacion CHANGE create_date created_date DATETIME NULL DEFAULT NULL;

-- SE CAMBIAN IDENTIFICADORES DE TABLAS: to_movimiento_midas y to_movimiento_proveedor PARA QUE SEAN AUTOINCREMENTALES --
-- [2019-05-02 10:14:14] --
-- ALTER TABLE to_movimiento_midas CHANGE id id INT(11) NOT NULL AUTO_INCREMENT;
-- ALTER TABLE to_movimiento_proveedor CHANGE id id INT(11) NOT NULL AUTO_INCREMENT;


-- SE AGREGAN COLUMNAS estado_transaccion y canal a tabla to_movimiento_midas --
-- [2019-05-02 17:37:10] --
-- ALTER TABLE to_movimiento_midas ADD COLUMN estado_transaccion VARCHAR(100) NULL DEFAULT NULL;
-- ALTER TABLE to_movimiento_midas ADD COLUMN canal VARCHAR(50) NULL DEFAULT NULL;

-- SE CAMBIA LA ESTRUCTURA DE LA TABLA to_movimiento proveedor --
-- [2019-05-06 13:38:45] --
-- TRUNCATE to_movimiento_proveedor;
-- DROP TABLE IF EXISTS to_movimiento_proveedor;

  -- CREATE TABLE `to_movimiento_proveedor` (
  -- `id` INT(11) NOT NULL AUTO_INCREMENT,
  -- `id_movimiento` VARCHAR(100) NULL DEFAULT NULL,
  -- `id_reporte` int(11) NOT NULL,
  -- `authorization` VARCHAR(50) NOT NULL,
  -- `operation_type` VARCHAR(50) NOT NULL,
  -- `method` VARCHAR(50) NOT NULL,
  -- `transaction_type` VARCHAR(50) NOT NULL,
  -- `status` VARCHAR(50) NOT NULL,
  -- `conciliated` BIT NOT NULL DEFAULT FALSE,
  -- `creation_date` DATETIME NOT NULL,
  -- `operation_date` DATETIME NOT NULL,
  -- `description` VARCHAR(50) NOT NULL,
  -- `error_message` VARCHAR(50) NOT NULL,
  -- `order_id` VARCHAR(50) NOT NULL,
  -- `customer_id` VARCHAR(50) NOT NULL,
  -- `error_code` VARCHAR(50) NOT NULL,
  -- `currency` VARCHAR(50) NOT NULL,
  -- `amount` DECIMAL(16,4) NOT NULL,
  -- `payment_method_type` VARCHAR(50) NOT NULL,
  -- `payment_method_url` VARCHAR(50) NOT NULL,
  -- `card_id` VARCHAR(50) NOT NULL,
  -- `card_type` VARCHAR(50) NOT NULL,
  -- `card_brand` VARCHAR(50) NOT NULL,
--   `card_address` VARCHAR(50) NOT NULL,
  -- `card_number` VARCHAR(50) NOT NULL,
  -- `card_holder_name` VARCHAR(50) NOT NULL,
  -- `card_expiration_year` VARCHAR(50) NOT NULL,
  -- `card_expiration_month` VARCHAR(50) NOT NULL,
  -- `card_allows_charges` BIT NOT NULL,
--   `card_allows_payouts` BIT NOT NULL,
  -- `card_creation_date` DATETIME NOT NULL,
--   `card_bank_name` VARCHAR(50) NOT NULL,
--   `card_bank_code` VARCHAR(50) NOT NULL,
  -- `card_customer_id` VARCHAR(50) NOT NULL,
  -- PRIMARY KEY (`id`),
  -- KEY `fk_to_movimiento_midas_to_reporte1` (`id_reporte`),
  -- CONSTRAINT `fk_to_movimiento_midas_to_reporte1` FOREIGN KEY (`id_reporte`) REFERENCES `to_reporte` (`id`)
  -- ) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;


-- SE ACTUALIZA ID DE TABLA to_reporte A AUTO_INCREMENTAL
-- [2019-05-06 14:47:11] --
-- SET FOREIGN_KEY_CHECKS=0;
-- ALTER TABLE to_reporte CHANGE id id INT(11) NOT NULL AUTO_INCREMENT;
-- SET FOREIGN_KEY_CHECKS=1;

-- RENOMBRADO DE COLUMNA CANAL EN to_movimiento_midas --
-- [2019-05-06 19:25:19] --
-- ALTER TABLE to_movimiento_midas CHANGE canal id_consumidor VARCHAR(50) NULL;

-- SE AGREGAN CAMPOS PARA REGISTRO DE MOVIMIENTOS ERRONEOS EN TABLA to_movimiento_midas --
-- [2019-05-07 18:24:37] --
-- ALTER TABLE to_movimiento_midas 
-- ADD COLUMN codigo_error VARCHAR(50) NULL DEFAULT NULL,
-- ADD COLUMN mensaje_error VARCHAR(50) NULL DEFAULT NULL,
-- ADD COLUMN id_tarjeta VARCHAR(50) NULL DEFAULT NULL,
-- ADD COLUMN marca_tarjeta VARCHAR(50) NULL DEFAULT NULL,
-- ADD COLUMN tipo_tarjeta VARCHAR(50) NULL DEFAULT NULL,
-- ADD COLUMN tarjeta VARCHAR(50) NULL DEFAULT NULL,
-- ADD COLUMN moneda_pago VARCHAR(50) NULL DEFAULT NULL;


-- SE MODIFICA COLUMNA id_consumidor por consumidor en tabla to_movimiento_midas --
-- SE AGREGA NUEVA COLUMNA importe_transaccion a tabla to_movimiento_midas --
-- SE CAMBIA TIPO DE DATO EN CAMPO estatus DE TABLA to_movimiento_midas DE VARCHAR(50) A BIT--
-- [2019-05-08 11:55:35] --
-- ALTER TABLE to_movimiento_midas CHANGE id_consumidor consumidor VARCHAR(50) NULL DEFAULT NULL;
-- ALTER TABLE to_movimiento_midas ADD COLUMN importe_transaccion DECIMAL (16,4) NULL DEFAULT NULL;
-- TRUNCATE TABLE to_movimiento_midas;
-- ALTER TABLE to_movimiento_midas CHANGE estatus estatus BIT DEFAULT TRUE;

-- SE MODIFICA COLUMNA ID PARA QUE SEA INCREMENTAL Y DE TIPO INT Y SE AGREGA NUEVA COLUMNA PARA MAPEAR ID DE PETICION DE BUS --
-- [2019-05-08 14:23:15] --
-- ALTER TABLE to_movimiento_proveedor CHANGE id id INT(11) NOT NULL AUTO_INCREMENT;
-- ALTER TABLE to_movimiento_proveedor ADD COLUMN id_movimiento VARCHAR(100) NULL DEFAULT NULL;

-- SE CREAN TABLAS PARA MOVIMIENTOS ESTADO CUENTA --
-- [2019-05-10 11:49:39] --

DROP TABLE IF EXISTS to_estado_cuenta;
DROP TABLE IF EXISTS to_estado_cuenta_totales_adicional;
DROP TABLE IF EXISTS to_estado_cuenta_totales;
DROP TABLE IF EXISTS to_estado_cuenta_cabecera;
DROP TABLE IF EXISTS to_movimiento_estado_cuenta;

CREATE TABLE to_estado_cuenta
(
	id INTEGER NOT NULL AUTO_INCREMENT,
	reporte INTEGER,
	cabecera INTEGER,
	total_movimientos INTEGER,
	totales INTEGER,
	totales_adicional INTEGER,
	fecha_carga DATE,
	PRIMARY KEY (id),
	KEY (cabecera),
	KEY (totales),
	KEY (totales_adicional)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE to_estado_cuenta_totales_adicional
(
	id INTEGER NOT NULL AUTO_INCREMENT,
	clave_pais VARCHAR(4),
	sucursal VARCHAR(4),
	cuenta VARCHAR(10),
	no_cargos VARCHAR(5),
	importe_total_cargos DECIMAL(16,2),
	no_abonos INTEGER,
	importe_total_abonos DECIMAL(16,2),
	tipo_saldo TINYINT,
	saldo_final DECIMAL(16,2),
	moneda_alfabetica VARCHAR(3),
	PRIMARY KEY (id)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE to_estado_cuenta_totales
(
	id INTEGER NOT NULL AUTO_INCREMENT,
	clave_pais VARCHAR(3),
	subcodigo_registro VARCHAR(2),
	informacion1 VARCHAR(35),
	informacion2 VARCHAR(35),
	PRIMARY KEY (id)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE to_estado_cuenta_cabecera
(
	id INTEGER NOT NULL AUTO_INCREMENT,
	clave_pais VARCHAR(4),
	sucursal VARCHAR(4),
	cuenta VARCHAR(10),
	tipo_saldo TINYINT,
	saldo_inicial DECIMAL(16,2),
	moneda_alfabetica VARCHAR(3),
	digito_cuenta_clabe CHAR(1),
	titular_cuenta VARCHAR(23),
	plaza_cuenta_clabe VARCHAR(3),
	libre VARCHAR(3),
	PRIMARY KEY (id)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE to_movimiento_estado_cuenta
(
	id INTEGER NOT NULL AUTO_INCREMENT,
	estado_cuenta INTEGER,
	clave_pais VARCHAR(4),
	sucursal VARCHAR(4),
	fecha_operacion DATE,
	fecha_valor DATE,
	libre VARCHAR(2),
	clave_leyenda VARCHAR(3),
	tipo_movimiento TINYINT,
	importe DECIMAL(16,2),
	dato VARCHAR(10),
	concepto VARCHAR(28),
	codigo_dato VARCHAR(2),
	referencia_ampliada VARCHAR(38),
	referencia VARCHAR(38),
	PRIMARY KEY (id),
	KEY (estado_cuenta)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE to_estado_cuenta ADD CONSTRAINT FK_to_estado_cuenta_to_movimiento_estado_cuenta_cabecera 
	FOREIGN KEY (cabecera) REFERENCES to_estado_cuenta_cabecera (id);

ALTER TABLE to_estado_cuenta ADD CONSTRAINT FK_to_estado_cuenta_to_movimiento_estado_cuenta_totales 
	FOREIGN KEY (totales) REFERENCES to_estado_cuenta_totales (id);

ALTER TABLE to_estado_cuenta ADD CONSTRAINT FK_to_estado_cuenta_to_movimiento_edo_cta_tot_adicional 
	FOREIGN KEY (totales_adicional) REFERENCES to_estado_cuenta_totales_adicional (id);

ALTER TABLE to_estado_cuenta ADD CONSTRAINT FK_to_estado_cuenta_to_reporte 
	FOREIGN KEY (id) REFERENCES to_reporte (id);

ALTER TABLE to_movimiento_estado_cuenta ADD CONSTRAINT FK_to_movimiento_estado_cuenta_to_estado_cuenta 
	FOREIGN KEY (estado_cuenta) REFERENCES to_estado_cuenta (id);

-- ----------------------------------------------------- --
-- INICIAN CAMBIOS PARA MODULO REPORTE DE PAGOS EN LINEA --
-- -------------- [2019-05-17 14:19:53] ---------------- --
-- ----------------------------------------------------- --
-- -------------------------------------------- --
-- ---------- TABLE tk_operacion--------------- --
-- -------------------------------------------- --
DROP TABLE IF EXISTS tk_operacion;

CREATE TABLE `tk_operacion` (
  `id` INT(11) NOT NULL,
  `tipo` INT(11) NOT NULL,
  `abreviatura` VARCHAR(10) NOT NULL,
  `descripcion` VARCHAR(100) NOT NULL,
  `inddep` INT(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- -------------------------------------------- --
-- ---------- TABLE tk_tipo_contrato--------------- --
-- -------------------------------------------- --
DROP TABLE IF EXISTS tk_tipo_contrato;
CREATE TABLE `tk_tipo_contrato` (
  `id` INT(11) NOT NULL,
  `descripcion` VARCHAR(100) NOT NULL,
  `baja_logica` VARCHAR(1) NOT NULL,
  `abreviatura` VARCHAR(10) NOT NULL,
  `inddep` INT(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------- --
-- ----- INSERTS INICIALES EN tk_operacion --
-- --------------------------------------- --
INSERT INTO `tk_operacion`
	(`id`, `tipo`, `abreviatura`, `descripcion`, `inddep`)
VALUES
	(8, 1, 'RF', 'Cobro Refrendo', 0),
	(116, 1, 'APL', 'Abono -PagosLibres', 0),
	(148, 1, 'DSO', 'Cobro Desempeño en Linea', 0);
    
-- ------------------------------------------- --
-- ----- INSERTS INICIALES EN tk_tipo_contrato --
-- ------------------------------------------- --
INSERT INTO `compose`.`tk_tipo_contrato`
	(`id`, `descripcion`, `baja_logica`, `abreviatura`, `inddep`)
VALUES
	(146, 'PAGOS LIBRES', 'f', 'PL', 0),
    (145, 'CLASICO', 'f', 'CL', 0);


-- ---------- SE AGREGAN COLUMNAS NUEVAS A TABLA to_movimiento_midas ---- --
-- ------------------------ [2019-05-20 18:18:47] ------------------------ --
ALTER TABLE to_movimiento_midas ADD COLUMN id_operacion INT(11) DEFAULT NULL;
ALTER TABLE to_movimiento_midas ADD COLUMN id_tipo_contrato INT(11) DEFAULT NULL;

-- ------------------------------------------------------------------ --
-- ----------- SE CREAN INDICES PARA TABLA to_movimiento_midas ------ --
-- ------------------------[2019-05-22 17:49:45] -------------------- --
-- ------------------------------------------------------------------ --
ALTER TABLE to_movimiento_midas ADD INDEX idx_id (id);
ALTER TABLE to_movimiento_midas ADD INDEX idx_fecha (fecha);
ALTER TABLE to_movimiento_midas ADD INDEX idx_folio (folio);
ALTER TABLE to_movimiento_midas ADD INDEX idx_id_operacion (id_operacion);
ALTER TABLE to_movimiento_midas ADD INDEX idx_id_tipo_contrato (id_tipo_contrato);
ALTER TABLE to_movimiento_midas ADD INDEX idx_sucursal (sucursal);

-- ------------------------------------------------------------------------------------------ --
-- --------------- SE REALIZAN AJUSTES PARA ARTEFACTO TRANSACCIONESCOMISIONESBD ------------- --
-- -------------------------------- [2019-05-23 11:14:28] ----------------------------------- --
-- ------------------------------------------------------------------------------------------ --
ALTER TABLE to_movimiento_pago ADD FOREIGN KEY (estatus) REFERENCES tk_estatus_transaccion(id);
