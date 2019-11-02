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


-- ------------------------------------------------------ --
-- AJUSTES CON NUEVOS ESTATUS Y MODIFICACION DE ALGUNOS - --
-- -------------- [2019-11-01 14:23:35] ----------------- --
-- ------------------------------------------------------ --

-- ------------------------------------------------------ --
-- ----- ACTUALIZACION DE NOMBRES DE PROCESOS EN ME ----- --
-- ------------------------------------------------------ --
UPDATE tk_maquina_estados_subestatus_conciliacion me
	SET me.nombre_proceso = 'GENERACION_LAYOUTS'
	WHERE me.id_sub_estatus_inicial IN(9,12) AND me.id_sub_estatus_posible = 14;


-- ------------------------------------------------------ --
-- ------------------ NUEVOS ESTATUS -------------------- --
-- ------------------------------------------------------ --
UPDATE tk_sub_estatus_conciliacion sc 
	SET sc.description = 'Generacion Layouts' 
    WHERE sc.id = 14;
    
UPDATE tk_sub_estatus_conciliacion sc 
	SET sc.description = 'Generacion Layouts Completada' 
    WHERE sc.id = 15;

UPDATE tk_sub_estatus_conciliacion sc 
	SET sc.description = 'Generacion Layouts Error' 
	WHERE sc.id = 16;
    

-- ------------------------------------------------------ --
-- -- ACTUALIZACION DE ANTIGUOS ESTATUS CON NUEVOS ID'S - --
-- ------------------------------------------------------ --
INSERT INTO tk_sub_estatus_conciliacion
	(id, description, short_description, estatus, created_date, created_by, last_modified_date, last_modified_by, order_number)
		VALUES
			(17, 'Enviada', NULL, TRUE, NOW(), 'Sistema', NULL, NULL, 17),
			(18, 'Enviada Error', NULL, TRUE, NOW(), 'Sistema', NULL, NULL, 18),
			(19, 'Finalizada', NULL, TRUE, NOW(), 'Sistema', NULL, NULL, 19);


-- ------------------------------------------------------ --
-- -------- ACTUALIZACION DE MAQUINA DE ESTADOS --------- --
-- ------------------------------------------------------ --
UPDATE tk_maquina_estados_subestatus_conciliacion me 
	SET me.id_sub_estatus_posible = 17 
    WHERE me.id_sub_estatus_posible = 14 AND me.id_sub_estatus_inicial = 15;
    
UPDATE tk_maquina_estados_subestatus_conciliacion me 
	SET me.id_sub_estatus_posible = 18 
    WHERE me.id_sub_estatus_posible = 15;

UPDATE tk_maquina_estados_subestatus_conciliacion me 
	SET me.id_sub_estatus_posible = 19 
	WHERE me.id_sub_estatus_posible = 16;
    

-- ------------------------------------------------------ --
-- -- ACTUALIZACION DE ANTIGUOS ESTATUS CON NUEVOS ID'S - --
-- ------------------------------------------------------ --
INSERT INTO tk_maquina_estados_subestatus_conciliacion 
	(id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible)
		VALUES
            (25, 'GENERACION_LAYOUTS', 16, 14);
