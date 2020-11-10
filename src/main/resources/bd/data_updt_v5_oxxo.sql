-- ------------------------------------------------------------------------------------------ --
-- -------------------- INSERTS INICIALES PARA FASE OXXO ------------------------------------ --
-- ------------------------------------------------------------------------------------------ --

-- Se crea la secuencia para OPENPAY usando el ultimo folio
INSERT INTO seq_conciliacion(seq_name, seq_value) VALUES('OPENPAY', (SELECT MAX(id) FROM to_conciliacion));
-- Se crea la secuencia para OXXO iniciando en 0
INSERT INTO seq_conciliacion(seq_name, seq_value) VALUES('OXXO', 0);


-- ---------------------------------------------------------------------------------- --
-- --------------- INSERT DE LAS COMISIONES PROVEEDORES / CORRESPONSALES ------------------------ --
-- ---------------------------------------------------------------------------------- --
INSERT INTO `conciliacion`.`tc_comision_proveedor` (`id`, `corresponsal`, `comision`, `iva`) 
	VALUES ('1', 'OXXO', '15.52', '2.48');