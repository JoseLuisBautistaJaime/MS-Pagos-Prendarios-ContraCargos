
-- Setea negocio y proyecto a COMISIONES SUCURSAL
UPDATE tc_layout_linea SET negocio = 'PRENDA', proyecto_nmp = 'SUCS_NB' WHERE id = 3;

-- Comisiones
INSERT INTO tc_layout_linea (id, linea, cuenta, dep_id, unidad_operativa, negocio, proyecto_nmp, tipo, grupo, created_date, last_modified_date, created_by, last_modified_by) VALUES
(7, 'L', '1219001003', '', '13%03d', 'PRENDA', 'SUCS_NB', 'COMISIONES_GENERALES', 'SUCURSALES', now(), null, 'Sistema', null); -- Sucursales
INSERT INTO tc_layout_linea (id, linea, cuenta, dep_id, unidad_operativa, negocio, proyecto_nmp, tipo, grupo, created_date, last_modified_date, created_by, last_modified_by) VALUES
(8, 'L', '1011001063', '15000', '', '', '', 'COMISIONES_MOV', 'BANCOS', now(), null, 'Sistema', null); -- Bancos

-- Comisiones Iva
INSERT INTO tc_layout_linea (id, linea, cuenta, dep_id, unidad_operativa, negocio, proyecto_nmp, tipo, grupo, created_date, last_modified_date, created_by, last_modified_by) VALUES
(9, 'L', '1219001003', '', '13%03d', 'PRENDA', 'SUCS_NB', 'COMISIONES_IVA', 'SUCURSALES', now(), null, 'Sistema', null); -- Sucursales
INSERT INTO tc_layout_linea (id, linea, cuenta, dep_id, unidad_operativa, negocio, proyecto_nmp, tipo, grupo, created_date, last_modified_date, created_by, last_modified_by) VALUES
(10, 'L', '1011001063', '15000', '', '', '', 'COMISIONES_IVA', 'BANCOS', now(), null, 'Sistema', null); -- Bancos
