-- ----------------------------------------------------------------------------------------------- --
-- -------------------------- HABILITAR COBRANZA 24/7 - CONCILIACIÓN AUTOMÁTICA ------------------ --
-- ----------------------------------------------------------------------------------------------- --

DROP TABLE IF EXISTS `to_ejecucion_preconciliacion` ;

DROP TABLE IF EXISTS `tk_estatus_ejecucion_preconciliacion` ;

DROP TABLE IF EXISTS `to_trazado_ejecucion_conciliacion` ;

DROP TABLE IF EXISTS `to_ejecucion_conciliacion` ;

DROP TABLE IF EXISTS `tk_estatus_ejecucion_conciliacion` ;

DROP TABLE IF EXISTS `tk_dia_inhabil` ;

DROP TABLE IF EXISTS `tk_proceso` ;

DROP TABLE IF EXISTS `to_calendario_ejecucion_proceso` ;

-- -------------------------------------------------------------- --
-- ------------ TABLA ESTATUS EJECUCIÓN PRE-CONCILIACIÓN --------- --
-- -------------------------------------------------------------- --

 CREATE TABLE `tk_estatus_ejecucion_preconciliacion` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descripcion_corta` varchar(100) DEFAULT NULL,
  `descripcion` varchar(150) DEFAULT NULL,
  `order_number` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;



-- -------------------------------------------------------------- --
-- ------------ TABLA EJECUCIÓN PRE-CONCILIACIÓN --------- --
-- -------------------------------------------------------------- --

CREATE TABLE `to_ejecucion_preconciliacion` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `proveedor` varchar(150) NOT NULL,
  `id_estatus_ejecucion` bigint NOT NULL,
  `estatus_descripcion` varchar(250) DEFAULT NULL,
  `fecha_periodo_inicio` datetime NOT NULL,
  `fecha_periodo_fin` datetime NOT NULL,
  `fecha_ejecucion` datetime NOT NULL,
  `created_date` datetime NOT NULL,
  `created_by` varchar(100) DEFAULT NULL,
  `last_modified_by` varchar(100) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ejecucion_preconc_estatus_fk_idx` (`id_estatus_ejecucion`),
  KEY `ejecucion_preconc_proveedor_fk_idx` (`proveedor`),
  CONSTRAINT `ejecucion_preconc_estatus_fk` FOREIGN KEY (`id_estatus_ejecucion`) REFERENCES `tk_estatus_ejecucion_preconciliacion` (`id`),
  CONSTRAINT `ejecucion_preconc_proveedor_fk` FOREIGN KEY (`proveedor`) REFERENCES `tk_proveedor` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


-- -------------------------------------------------------------- --
-- ------------ TABLA ESTATUS EJECUCIÓN CONCILIACIÓN ------------ --
-- -------------------------------------------------------------- --

 CREATE TABLE `tk_estatus_ejecucion_conciliacion` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descripcion_corta` varchar(100) DEFAULT NULL,
  `descripcion` varchar(150) DEFAULT NULL,
  `order_number` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


-- -------------------------------------------------------------- --
-- ------------ TABLA EJECUCIÓN CONCILIACIÓN -------------------- --
-- -------------------------------------------------------------- --

CREATE TABLE `to_ejecucion_conciliacion` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `id_conciliacion` bigint NOT NULL,
  `proveedor` varchar(150) NOT NULL,
  `id_estatus_ejecucion` bigint NOT NULL,
  `fecha_periodo_inicio` datetime NOT NULL,
  `fecha_periodo_fin` datetime NOT NULL,
  `fecha_ejecucion` datetime NOT NULL,
  `created_date` datetime NOT NULL,
  `created_by` varchar(100) DEFAULT NULL,
  `last_modified_by` varchar(100) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ejecucion_conc_estatus_fk_idx` (`id_estatus_ejecucion`),
  KEY `ejecucion_conc_conciliacion_fk_idx` (`id_conciliacion`),
  KEY `ejecucion_conc_proveedor_fk_idx` (`proveedor`),
  CONSTRAINT `ejecucion_conc_conciliacion_fk` FOREIGN KEY (`id_conciliacion`) REFERENCES `to_conciliacion` (`id`),
  CONSTRAINT `ejecucion_conc_estatus_fk` FOREIGN KEY (`id_estatus_ejecucion`) REFERENCES `tk_estatus_ejecucion_conciliacion` (`id`),
  CONSTRAINT `ejecucion_conc_proveedor_fk` FOREIGN KEY (`proveedor`) REFERENCES `tk_proveedor` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

-- -------------------------------------------------------------- --
-- ------------ TABLA TRAZADO EJECUCIÓN CONCILIACIÓN --------- --
-- -------------------------------------------------------------- --


CREATE TABLE `to_trazado_ejecucion_conciliacion` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `id_estatus_ejecucion` bigint NOT NULL,
  `id_ejecucion_conciliacion` bigint NOT NULL,
  `estatus_descripcion` varchar(250) DEFAULT NULL,
  `fecha_inicio` datetime NOT NULL,
  `fecha_fin` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `trazado_estatus_ejecucion_fk_idx` (`id_estatus_ejecucion`),
  KEY `trazado_ejecucion_conciliacion_fk_idx` (`id_ejecucion_conciliacion`),
  CONSTRAINT `fk_trazado_estatus_ejecucion_01` FOREIGN KEY (`id_estatus_ejecucion`) REFERENCES `tk_estatus_ejecucion_conciliacion` (`id`),
  CONSTRAINT `fk_trazado_ejecucion_conciliacion_01` FOREIGN KEY (`id_ejecucion_conciliacion`) REFERENCES `to_ejecucion_conciliacion` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-----------------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------------

-- -------------------------------------------------------------- --
-- ------------ TABLA CATÁLOGO PROCESOS --------- --
-- -------------------------------------------------------------- --

 CREATE TABLE `tk_proceso` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descripcion_corta` varchar(100) DEFAULT NULL,
  `descripcion` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


-- -------------------------------------------------------------- --
-- ------------ TABLA CATÁLOGO DÍA INHÁBIL --------- --
-- -------------------------------------------------------------- --

 CREATE TABLE `tk_dia_inhabil` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descripcion_corta` varchar(100) DEFAULT NULL,
  `descripcion` varchar(150) DEFAULT NULL,
  `fecha` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;



-- -------------------------------------------------------------- --
-- ------------ TABLA CALENDARIO EJECUCIÓN PROCESO  --------- --
-- -------------------------------------------------------------- --

CREATE TABLE `to_calendario_ejecucion_proceso` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `id_proceso` bigint NOT NULL,
  `proveedor` varchar(150) NOT NULL,
  `configuracion` varchar(250) DEFAULT NULL,
  `reintentos` int NOT NULL DEFAULT '0',
  `rango_dias_cobertura` int NOT NULL DEFAULT '0',
  `activo` tinyint(1) NOT NULL,
  `created_date` datetime NOT NULL,
  `created_by` varchar(100) DEFAULT NULL,
  `last_modified_by` varchar(100) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `calendario_ejecucion_proceso_fk_idx` (`id_proceso`),
  KEY `calendario_ejecucion_proveedor_fk_idx` (`proveedor`),
  CONSTRAINT `calendario_ejecucion_proceso_fk` FOREIGN KEY (`id_proceso`) REFERENCES `tk_proceso` (`id`),
  CONSTRAINT `calendario_ejecucion_proveedor_fk` FOREIGN KEY (`proveedor`) REFERENCES `tk_proveedor` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1