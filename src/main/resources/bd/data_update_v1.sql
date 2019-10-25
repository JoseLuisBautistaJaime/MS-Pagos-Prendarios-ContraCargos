-- ------------------------------------------------------------------------------------------------------------
-- SCRIPT ACTUALIZACION - [2019-09-12] ------------------------------------------------------------------------
-- ------------------------------------------------------------------------------------------------------------


-- ------------------------------------------------------------------------------------------------------------------ --
-- INSERTS INICIALES EN TABLA - tk_catalogo
-- ------------------------------------------------------------------------------------------------------------------ --
INSERT INTO tk_catalogo (id, descripcion_corta, descripcion, nombre_tabla, activo) VALUES
(7, 'Estatus Devolucion', 'Catalogo de estatus de devoluciones', 'tk_estatus_devolucion', 1);


-- ------------------------------------------------------------------------------------------------------------------ --
-- INSERTS INICIALES EN TABLA - tk_maquina_estados_subestatus_conciliacion (actualización)
-- ------------------------------------------------------------------------------------------------------------------ --
UPDATE tk_maquina_estados_subestatus_conciliacion SET id = 24 WHERE id = 23;
UPDATE tk_maquina_estados_subestatus_conciliacion SET id = 23 WHERE id = 22;
UPDATE tk_maquina_estados_subestatus_conciliacion SET id = 22 WHERE id = 21;
UPDATE tk_maquina_estados_subestatus_conciliacion SET id = 21 WHERE id = 20;
UPDATE tk_maquina_estados_subestatus_conciliacion SET id = 20 WHERE id = 19;
UPDATE tk_maquina_estados_subestatus_conciliacion SET id = 19 WHERE id = 18;
UPDATE tk_maquina_estados_subestatus_conciliacion SET id = 18 WHERE id = 17;
UPDATE tk_maquina_estados_subestatus_conciliacion SET id = 17 WHERE id = 16;
UPDATE tk_maquina_estados_subestatus_conciliacion SET id = 16 WHERE id = 15;
UPDATE tk_maquina_estados_subestatus_conciliacion SET id = 15 WHERE id = 14;
UPDATE tk_maquina_estados_subestatus_conciliacion SET id = 14 WHERE id = 13;
INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible)
VALUES (13, 'CONCILIACION_MERGE', 9, 8);