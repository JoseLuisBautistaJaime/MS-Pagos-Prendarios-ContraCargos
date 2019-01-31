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
	VALUES(3, 'Afiliacion', 'Catalogo de afiliaciones', 'catalogo_afiliacion', 1),
    (4, 'EstatusPago', 'Estatus de las transacciones o pagos', 'estatus_transaccion_c', 1),
    (5, 'ReglaNegocio', 'Reglas de negocio', 'regla_negocio', 1),
    (6, 'Variable', 'Variables para la aplicacion', 'variable', 1);
-- NEW CATALOGS ENDS

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
