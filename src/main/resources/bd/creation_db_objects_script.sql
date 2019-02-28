-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema compose
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `compose` ;

-- -----------------------------------------------------
-- Schema compose
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `compose` DEFAULT CHARACTER SET latin1 ;
USE `compose` ;

-- -----------------------------------------------------
-- Table `tk_catalogo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tk_catalogo` ;

CREATE TABLE IF NOT EXISTS `tk_catalogo` (
  `id` SMALLINT(6) NOT NULL,
  `descripcion_corta` VARCHAR(20) NULL DEFAULT NULL,
  `descripcion` VARCHAR(50) NULL DEFAULT NULL,
  `nombre_tabla` VARCHAR(30) NOT NULL,
  `activo` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `tk_afiliacion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tk_afiliacion` ;

CREATE TABLE IF NOT EXISTS `tk_afiliacion` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(100) NOT NULL,
  `tipo` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `ca_fk_idx` (`id` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `tk_cliente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tk_cliente` ;

CREATE TABLE IF NOT EXISTS `tk_cliente` (
  `id_cliente` BIGINT(20) UNSIGNED NOT NULL,
  `nombre_titular` VARCHAR(100) NULL DEFAULT NULL,
  `fecha_alta` DATETIME NOT NULL,
  PRIMARY KEY (`id_cliente`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `tk_regla_negocio`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tk_regla_negocio` ;

CREATE TABLE IF NOT EXISTS `tk_regla_negocio` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) NOT NULL,
  `descripcion` VARCHAR(100) NOT NULL,
  `consulta` VARCHAR(500) NOT NULL,
  `id_afiliacion` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `rn_fk_idx` (`id` ASC),
  INDEX `ca_fk` (`id_afiliacion` ASC),
  CONSTRAINT `ca_fk`
    FOREIGN KEY (`id_afiliacion`)
    REFERENCES `tk_afiliacion` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `tr_cliente_regla_negocio`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tr_cliente_regla_negocio` ;

CREATE TABLE IF NOT EXISTS `tr_cliente_regla_negocio` (
  `id_cliente` BIGINT(20) UNSIGNED NOT NULL,
  `id_regla_negocio` INT(11) NOT NULL,
  INDEX `ic_fk` (`id_cliente` ASC),
  INDEX `irn_fk` (`id_regla_negocio` ASC),
  CONSTRAINT `ic_fk`
    FOREIGN KEY (`id_cliente`)
    REFERENCES `cliente` (`id_cliente`),
  CONSTRAINT `irn_fk`
    FOREIGN KEY (`id_regla_negocio`)
    REFERENCES `regla_negocio` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `tk_estatus_operacion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tk_estatus_operacion` ;

CREATE TABLE IF NOT EXISTS `tk_estatus_operacion` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `descripcion_corta` VARCHAR(45) NULL DEFAULT NULL,
  `descripcion` VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `tk_estatus_tarjeta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tk_estatus_tarjeta` ;

CREATE TABLE IF NOT EXISTS `tk_estatus_tarjeta` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `descripcion_corta` VARCHAR(45) NULL DEFAULT NULL,
  `descripcion` VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `tk_estatus_transaccion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tk_estatus_transaccion` ;

CREATE TABLE IF NOT EXISTS `tk_estatus_transaccion` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `descripcion_corta` VARCHAR(45) NULL DEFAULT NULL,
  `descripcion` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `to_pagos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `to_pagos` ;

CREATE TABLE IF NOT EXISTS `to_pagos` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idcliente` BIGINT(20) UNSIGNED NOT NULL,
  `fecha_transaccion` DATETIME NULL DEFAULT NULL,
  `monto` DOUBLE NULL DEFAULT NULL,
  `autorizacion` VARCHAR(100) NULL DEFAULT NULL,
  `metodo` VARCHAR(45) NULL DEFAULT NULL,
  `tarjeta` VARCHAR(4) NULL DEFAULT NULL,
  `idopenpay` VARCHAR(100) NULL DEFAULT NULL,
  `fecha_creacion` DATETIME NULL DEFAULT NULL,
  `descripcion` VARCHAR(200) NULL DEFAULT NULL,
  `idorder` VARCHAR(100) NULL DEFAULT NULL,
  `estatus_transaccion` INT(11) NULL DEFAULT NULL,
  `restresponse` VARCHAR(400) NULL DEFAULT NULL,
  `id_transaccion_midas` BIGINT(20) UNSIGNED NULL DEFAULT NULL,
  `folio_partida` BIGINT(20) UNSIGNED NULL DEFAULT NULL,
  `id_operacion` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `esatus_transaccion_fk_idx` (`estatus_transaccion` ASC),
  INDEX `cliente_transacion_fk_idx` (`idcliente` ASC),
  INDEX `idx_id_transaccion_midas` (`id_transaccion_midas` ASC),
  INDEX `idx_folio_partida` (`folio_partida` ASC),
  INDEX `idx_id_operacion` (`id_operacion` ASC),
  CONSTRAINT `esatus_transaccion_fk`
    FOREIGN KEY (`estatus_transaccion`)
    REFERENCES `tk_estatus_transaccion` (`id`),
  CONSTRAINT `fk_cliente_id`
    FOREIGN KEY (`idcliente`)
    REFERENCES `tk_cliente` (`id_cliente`))
ENGINE = InnoDB
AUTO_INCREMENT = 60
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `tk_variable`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tk_variable` ;

CREATE TABLE IF NOT EXISTS `tk_variable` (
  `id_variable` INT(11) NOT NULL,
  `clave` VARCHAR(100) NOT NULL,
  `valor` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id_variable`),
  INDEX `va_fk_idx` (`id_variable` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `tr_regla_negocio_variable`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tr_regla_negocio_variable` ;

CREATE TABLE IF NOT EXISTS `tr_regla_negocio_variable` (
  `id_regla_negocio` INT(11) NOT NULL,
  `id_variable` INT(11) NOT NULL,
  INDEX `id_fk` (`id_regla_negocio` ASC),
  INDEX `idv_fk` (`id_variable` ASC),
  CONSTRAINT `id_fk`
    FOREIGN KEY (`id_regla_negocio`)
    REFERENCES `tk_regla_negocio` (`id`),
  CONSTRAINT `idv_fk`
    FOREIGN KEY (`id_variable`)
    REFERENCES `tk_variable` (`id_variable`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `tk_tipo_tarjeta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tk_tipo_tarjeta` ;

CREATE TABLE IF NOT EXISTS `tk_tipo_tarjeta` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `descripcion_corta` VARCHAR(45) NULL DEFAULT NULL,
  `descripcion` VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `tc_tarjetas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tc_tarjetas` ;

CREATE TABLE IF NOT EXISTS `tc_tarjetas` (
  `id_openpay` VARCHAR(40) NOT NULL,
  `ultimos_digitos` VARCHAR(4) NULL DEFAULT NULL,
  `alias` VARCHAR(100) NULL DEFAULT NULL,
  `fecha_alta` DATETIME NULL DEFAULT NULL,
  `fecha_modificacion` DATETIME NULL DEFAULT NULL,
  `id_cliente` BIGINT(20) UNSIGNED NOT NULL,
  `tipo_tarjeta_c_id` INT(11) NOT NULL,
  `estatus_tarjeta_c` INT(11) NOT NULL,
  `token` VARCHAR(40) NULL DEFAULT NULL,
  PRIMARY KEY (`id_openpay`),
  INDEX `tipo_tarjeta_tarjeta_fk_idx` (`tipo_tarjeta_c_id` ASC),
  INDEX `estatus_tarjeta_fk_idx` (`estatus_tarjeta_c` ASC),
  INDEX `cliente_tarjeta_fk` (`id_cliente` ASC),
  CONSTRAINT `cliente_tarjeta_fk`
    FOREIGN KEY (`id_cliente`)
    REFERENCES `tk_cliente` (`id_cliente`),
  CONSTRAINT `estatus_tarjeta_fk`
    FOREIGN KEY (`estatus_tarjeta_c`)
    REFERENCES `tk_estatus_tarjeta` (`id`),
  CONSTRAINT `tipo_tarjeta_tarjeta_fk`
    FOREIGN KEY (`tipo_tarjeta_c_id`)
    REFERENCES `tk_tipo_tarjeta` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `tk_tipo_afiliacion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tk_tipo_afiliacion` ;

CREATE TABLE IF NOT EXISTS `tk_tipo_afiliacion` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `descripcion_corta` VARCHAR(45) NULL DEFAULT NULL,
  `descripcion` VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
