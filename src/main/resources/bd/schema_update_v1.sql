-- -------------------------------------------------------------------------------------- --
-- -----	SCRIPT DE ACTUALIZACION DE schema.sql VERSION 1 [2019-07-23 19:39:54]	----- --
-- -------------------------------------------------------------------------------------- --
ALTER TABLE `to_layout` AUTO_INCREMENT = 1;
ALTER TABLE `to_layout_header` AUTO_INCREMENT = 1;
ALTER TABLE `to_layout_linea` AUTO_INCREMENT = 1;

ALTER TABLE to_movimiento_transito ADD num_autorizacion VARCHAR(45);

ALTER TABLE `to_layout_linea` ADD (`nuevo` TINYINT(1) NULL DEFAULT 0);

ALTER TABLE `to_layout` ADD UNIQUE INDEX `to_layout_UNIQUE` (`id_conciliacion`, tipo);

ALTER TABLE `tc_layout_header` DROP COLUMN `fecha`;
ALTER TABLE `tc_layout_header` DROP COLUMN `id_layout`;
ALTER TABLE `tc_layout_header` ADD COLUMN `tipo` VARCHAR(50) NULL AFTER `campo2`;

ALTER TABLE `tc_layout_linea` DROP COLUMN `id_layout`;
ALTER TABLE `tc_layout_linea` ADD COLUMN `tipo` VARCHAR(45) NULL AFTER `monto`;
ALTER TABLE `tc_layout_linea` ADD COLUMN `grupo` VARCHAR(50) NULL AFTER `tipo`;
ALTER TABLE `tc_layout_linea` DROP COLUMN `monto`;
