-- ----------------------------------------------------------------------------------------------- --
-- -------------------------- ACTUALIZACION CONCILIACION OXXO ------------------------------------- --
-- ----------------------------------------------------------------------------------------------- --

-- -------------------------------------------------------------- --
-- ------------ TABLA PROVEEDOR / CORRESPONSAL ------------------ --
-- -------------------------------------------------------------- --
CREATE TABLE `tk_proveedor` (
`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
`nombre` VARCHAR(150),
`descripcion` VARCHAR(250),
PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARACTER SET = latin1;

-- -------------------------------------------------------------- --
-- ------------ RELACION PROVEEDOR - CONCILIACION --------------- --
-- -------------------------------------------------------------- -- 
ALTER TABLE `to_conciliacion` ADD COLUMN id_proveedor BIGINT(20) NOT NULL DEFAULT 1;
ALTER TABLE `to_conciliacion` ADD INDEX `proveedor_con_fk_idx` (`id_proveedor` ASC);
ALTER TABLE `to_conciliacion` ADD CONSTRAINT `proveedor_con_fk` FOREIGN KEY (`id_proveedor`) REFERENCES `tk_proveedor` (`id`);

-- -------------------------------------------------------------- --
-- ---------- DATOS OBLIGATORIOS MOVIMIENTOS PROVEEDOR ---------- --
-- -------------------------------------------------------------- -- 
ALTER TABLE `to_movimiento_proveedor` CHANGE COLUMN `description` `description` VARCHAR(50) NULL;
ALTER TABLE `to_movimiento_proveedor` CHANGE COLUMN `currency` `currency` VARCHAR(50) NULL;
ALTER TABLE `to_movimiento_proveedor` CHANGE COLUMN `order_id` `order_id` VARCHAR(50) NOT NULL DEFAULT '';
ALTER TABLE `to_movimiento_proveedor` CHANGE COLUMN `amount` `amount` DECIMAL(16,4) NOT NULL DEFAULT 0.0;



-- ------------------------------------------------------------------------ --
-- ---------- ASOCIA UN ESTADO DE CUENTA A VARIAS CONCILIACIONES ---------- --
-- ------------------------------------------------------------------------ -- 
CREATE TABLE `tr_conciliacion_estado_cuenta` (
	`id_conciliacion` BIGINT(20) NOT NULL,
	`id_reporte` BIGINT(20) NOT NULL,
	PRIMARY KEY (`id_conciliacion`, `id_reporte`),
	CONSTRAINT "fk_tr_conciliacion_estado_cuenta_conciliacion" FOREIGN KEY(id_conciliacion) REFERENCES to_conciliacion(id),
	CONSTRAINT "fk_tr_conciliacion_estado_cuenta_report" FOREIGN KEY(id_reporte) REFERENCES to_reporte(id)
) ENGINE = InnoDB;


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
ALTER TABLE `to_movimiento_conciliacion` CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL,
			CHANGE COLUMN `id_movimiento_midas` `id_movimiento_midas` BIGINT(20) NULL DEFAULT NULL;
ALTER TABLE `to_movimiento_comision` CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL;
ALTER TABLE `to_movimiento_devolucion` CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL;
ALTER TABLE `to_movimiento_pago` CHANGE COLUMN `id` `id` BIGINT(11) NOT NULL;
ALTER TABLE `to_movimiento_transito` CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL;
ALTER TABLE `to_estado_cuenta` CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL;
ALTER TABLE `to_reporte` CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL;
ALTER TABLE `to_movimiento_estado_cuenta` CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL,
			CHANGE COLUMN `estado_cuenta` `estado_cuenta` BIGINT(20) NULL DEFAULT NULL;
ALTER TABLE `to_estado_cuenta` CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL,
			CHANGE COLUMN `reporte` `reporte` BIGINT(20) NULL DEFAULT NULL;
ALTER TABLE `to_movimiento_midas` CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL,
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
