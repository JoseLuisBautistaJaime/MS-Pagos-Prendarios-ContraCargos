-- -------------------------------------------------------------------------------------- --
-- -----	SCRIPT DE ACTUALIZACION DE schema.sql VERSION 1 [2019-07-23 19:39:54]	----- --
-- -------------------------------------------------------------------------------------- --
ALTER TABLE `to_layout` AUTO_INCREMENT = 1;
ALTER TABLE `to_layout_header` AUTO_INCREMENT = 1;
ALTER TABLE `to_layout_linea` AUTO_INCREMENT = 1;

ALTER TABLE to_movimiento_transito ADD num_autorizacion VARCHAR(45);

ALTER TABLE `to_layout_linea` ADD (
	`nuevo` TINYINT(1) NULL DEFAULT 0
);

ALTER TABLE `to_layout` 
ADD UNIQUE INDEX `to_layout_UNIQUE` (`id_conciliacion`, tipo);