--
-- Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
-- Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
--

-- --------------------------------------------------------------------------------------------- --
-- BORRADO DE TABLA - tk_estatus_ejecucion_preconciliacion
-- --------------------------------------------------------------------------------------------- --

DELETE FROM tk_estatus_ejecucion_preconciliacion WHERE id = 1;
DELETE FROM tk_estatus_ejecucion_preconciliacion WHERE id = 2;
DELETE FROM tk_estatus_ejecucion_preconciliacion WHERE id = 3;


-- --------------------------------------------------------------------------------------------- --
-- BORRADO DE TABLA - tk_estatus_ejecucion_conciliacion
-- --------------------------------------------------------------------------------------------- --

DELETE FROM tk_estatus_ejecucion_conciliacion WHERE id = 1;
DELETE FROM tk_estatus_ejecucion_conciliacion WHERE id = 2;
DELETE FROM tk_estatus_ejecucion_conciliacion WHERE id = 3;
DELETE FROM tk_estatus_ejecucion_conciliacion WHERE id = 4;
DELETE FROM tk_estatus_ejecucion_conciliacion WHERE id = 5;
DELETE FROM tk_estatus_ejecucion_conciliacion WHERE id = 6;
DELETE FROM tk_estatus_ejecucion_conciliacion WHERE id = 7;
DELETE FROM tk_estatus_ejecucion_conciliacion WHERE id = 8;


-- --------------------------------------------------------------------------------------------- --
-- BORRADO DE TABLA - tk_proceso
-- --------------------------------------------------------------------------------------------- --

DELETE FROM tk_proceso WHERE id = 1;
DELETE FROM tk_proceso WHERE id = 2;
DELETE FROM tk_proceso WHERE id = 3;
DELETE FROM tk_proceso WHERE id = 4;


-- --------------------------------------------------------------------------------------------- --
-- BORRADO DE TABLA - tk_estatus_ejecucion_conciliacion
-- --------------------------------------------------------------------------------------------- --

DELETE FROM to_calendario_ejecucion_proceso WHERE id = 1;
DELETE FROM to_calendario_ejecucion_proceso WHERE id = 2;
DELETE FROM to_calendario_ejecucion_proceso WHERE id = 3;
DELETE FROM to_calendario_ejecucion_proceso WHERE id = 4;
DELETE FROM to_calendario_ejecucion_proceso WHERE id = 5;
DELETE FROM to_calendario_ejecucion_proceso WHERE id = 6;
