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



