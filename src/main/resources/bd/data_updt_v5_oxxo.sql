-- ------------------------------------------------------------------------------------------ --
-- -------------------- INSERTS INICIALES PARA FASE OXXO ------------------------------------ --
-- ------------------------------------------------------------------------------------------ --

-- Se asigna el campo folio usando el id de la conciliacion para los registros existentes de openpay
UPDATE to_conciliacion SET folio = id WHERE id_proveedor = 1;


-- ---------------------------------------------------------------------------------- --
-- --------------- INSERT DE LAS COMISIONES PROVEEDORES / CORRESPONSALES ------------------------ --
-- ---------------------------------------------------------------------------------- --
INSERT INTO `conciliacion`.`tc_comision_proveedor` (`id`, `corresponsal`, `comision`, `iva`) 
	VALUES ('1', 'OXXO', '15.52', '2.48');