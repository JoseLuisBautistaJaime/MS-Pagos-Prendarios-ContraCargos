-- ------------------------------------------------------------------------------------------------------------
-- SCRIPT ACTUALIZACION - [2020-01-07] ------------------------------------------------------------------------
-- ------------------------------------------------------------------------------------------------------------

-- ----------------------------------------------- UPDATES ------------------------------------- --
SET SQL_SAFE_UPDATES = 0;

-- ---------------------------------------------------------------------------------------------------------------- --
-- ---- UPDATE DE DATO DE CUENTA PARA EL CATALOGO DE LINEAS DE LAYOUTS PARA EL TIPO DEVOLCUONES Y EL GRUPO BANCOS -- --
-- ---------------------------------------------------------------------------------------------------------------- --
UPDATE tc_layout_linea cta SET cta.cuenta = '1011001080' WHERE cta.tipo = 'DEVOLUCIONES' AND cta.grupo = 'BANCOS';

-- --------------------------------------------------------------------------------------------------- --
-- ---- UPDATE DE DATO DE CUENTA PARA EL CATALOGO DE LINEAS DE LAYOUTS  DE PAGOS EN EL GRUPO BANCOS -- --
-- --------------------------------------------------------------------------------------------------- --
UPDATE tc_layout_linea cta SET cta.cuenta = '1011001080' WHERE cta.tipo = 'PAGOS' AND cta.grupo = 'BANCOS';

SET SQL_SAFE_UPDATES = 0;
-- ----------------------------------------------- UPDATES ------------------------------------- --

-- --------------------------------------------------------------------------------------------------- --
-- ------------- INTERCAMBIA LAS DESCRIPCIONES DE LA REGLA 1 Y 2 PARA QUE SEAN LAS CORRECTAS ---------- --
-- --------------------------------------------------------------------------------------------------- --
UPDATE tk_regla_negocio rn SET rn.descripcion = 'Evalua cantidad de transacciones con cantidad variable' WHERE rn.id = 2;
UPDATE tk_regla_negocio rn SET rn.descripcion = 'Evalua suma de montos con un monto total variable' WHERE rn.id = 1;