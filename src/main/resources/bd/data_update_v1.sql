-- ------------------------------------------------------------------------------------------------------------------ --
-- INSERTS INICIALES EN TABLA - tk_maquina_estados_subestatus_conciliacion
-- ------------------------------------------------------------------------------------------------------------------ --
INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible)
VALUES (1, 'ALTA_MOVIMIENTOS_PROCESOS_NOCTURNOS', 1, 2);

INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible)
VALUES (2, 'ALTA_MOVIMIENTOS_PROCESOS_NOCTURNOS', 2, 3);
INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible)
VALUES (3, 'ALTA_MOVIMIENTOS_PROCESOS_NOCTURNOS', 2, 4);

INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible)
VALUES (4, 'ALTA_MOVIMIENTOS_PROVEEDOR_TRANSACCIONAL', 3, 5);

INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible)
VALUES (5, 'ALTA_MOVIMIENTOS_PROCESOS_NOCTURNOS', 4, 2);

INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible)
VALUES (6, 'ALTA_MOVIMIENTOS_PROVEEDOR_TRANSACCIONAL', 5, 6);
INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible)
VALUES (7, 'ALTA_MOVIMIENTOS_PROVEEDOR_TRANSACCIONAL', 5, 7);

INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible)
VALUES (8, 'CONCILIACION_MERGE', 6, 8);
INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible)
VALUES (9, 'ALTA_MOVIMIENTOS_ESTADO_CUENTA', 6, 11);

INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible)
VALUES (10, 'ALTA_MOVIMIENTOS_PROVEEDOR_TRANSACCIONAL', 7, 5);

INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible)
VALUES (11, 'CONCILIACION_MERGE', 8, 9);
INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible)
VALUES (12, 'CONCILIACION_MERGE', 8, 10);

INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible)
VALUES (13, 'ALTA_MOVIMIENTOS_ESTADO_CUENTA', 9, 11);
INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible)
VALUES (14, 'ENVIO_CONCILIACION', 9, 14);

INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible)
VALUES (15, 'CONCILIACION_MERGE', 10, 8);

INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible)
VALUES (16, 'ALTA_MOVIMIENTOS_ESTADO_CUENTA', 11, 12);
INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible)
VALUES (17, 'ALTA_MOVIMIENTOS_ESTADO_CUENTA', 11, 13);

INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible)
VALUES (18, 'CONCILIACION_MERGE', 12, 8);
INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible)
VALUES (19, 'ENVIO_CONCILIACION', 12, 14);

INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible)
VALUES (20, 'ALTA_MOVIMIENTOS_ESTADO_CUENTA', 13, 11);

INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible)
VALUES (21, 'ENVIO_CONCILIACION', 14, 15);
INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible)
VALUES (22, 'ENVIO_CONCILIACION', 14, 16);

INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible)
VALUES (23, 'ENVIO_CONCILIACION', 15, 14);


-- ------------------------------------------------------------------------------------------------------------------ --
-- INSERTS INICIALES EN TABLA - tk_estatus_movimientos_en_transito
-- ------------------------------------------------------------------------------------------------------------------ --
INSERT INTO tk_estatus_movimientos_en_transito (id, nombre, descripcion, estatus, created_date, last_modified_date, created_by, last_modified_by, descripcion_corta) VALUES
(4, 'No identificada en Open Pay', 'No identificada en Open Pay', true, now(), null, 'Sistema', null, 'NIOP');


-- ------------------------------------------------------------------------------------------------------------------ --
-- INSERTS INICIALES EN TABLA - tc_layout_header
-- ------------------------------------------------------------------------------------------------------------------ --
DELETE FROM tc_layout_header WHERE id > 0;

INSERT INTO tc_layout_header (id, cabecera, unidad_negocio, descripcion, codigo_origen, tipo, created_date, last_modified_date, created_by, last_modified_by) VALUES
('1', 'H', 'NMP01', 'COB RF Y DS EN L {0}', 'B', 'PAGOS', now(), null, 'Sistema', null);
INSERT INTO tc_layout_header (id, cabecera, unidad_negocio, descripcion, codigo_origen, tipo, created_date, last_modified_date, created_by, last_modified_by) VALUES
('2', 'H', 'NMP01', 'COMISIÓN {0}', 'B', 'COMISIONES_MOV', now(), null, 'Sistema', null);
INSERT INTO tc_layout_header (id, cabecera, unidad_negocio, descripcion, codigo_origen, tipo, created_date, last_modified_date, created_by, last_modified_by) VALUES
('3', 'H', 'NMP01', 'COMISIÓN {0}', 'B', 'COMISIONES_GENERALES', now(), null, 'Sistema', null);
INSERT INTO tc_layout_header (id, cabecera, unidad_negocio, descripcion, codigo_origen, tipo, created_date, last_modified_date, created_by, last_modified_by) VALUES
('4', 'H', 'NMP01', 'DEV RF Y DS EN LINEA {0}', 'B', 'DEVOLUCIONES', now(), null, 'Sistema', null);


-- ------------------------------------------------------------------------------------------------------------------ --
-- INSERTS INICIALES EN TABLA - tc_layout_linea
-- ------------------------------------------------------------------------------------------------------------------ --
DELETE FROM tc_layout_linea where id > 0;

-- Pagos
INSERT INTO tc_layout_linea (id, linea, cuenta, dep_id, unidad_operativa, negocio, proyecto_nmp, tipo, grupo, created_date, last_modified_date, created_by, last_modified_by) VALUES
(1, 'L', '1220001013', '', '13%03d', '', '', 'PAGOS', 'SUCURSALES', now(), null, 'Sistema', null); -- Movimientos/Sucursales
INSERT INTO tc_layout_linea (id, linea, cuenta, dep_id, unidad_operativa, negocio, proyecto_nmp, tipo, grupo, created_date, last_modified_date, created_by, last_modified_by) VALUES
(2, 'L', '1011001063', '15000', '', '', '', 'PAGOS', 'BANCOS', now(), null, 'Sistema', null); -- Banco

-- Comisiones
INSERT INTO tc_layout_linea (id, linea, cuenta, dep_id, unidad_operativa, negocio, proyecto_nmp, tipo, grupo, created_date, last_modified_date, created_by, last_modified_by) VALUES
(3, 'L', '1219001003', '', '13%03d', '', '', 'COMISIONES_MOV', 'SUCURSALES', now(), null, 'Sistema', null); -- Sucursales
INSERT INTO tc_layout_linea (id, linea, cuenta, dep_id, unidad_operativa, negocio, proyecto_nmp, tipo, grupo, created_date, last_modified_date, created_by, last_modified_by) VALUES
(4, 'L', '1011001063', '15000', '', '', '', 'COMISIONES_GENERALES', 'BANCOS', now(), null, 'Sistema', null); -- Bancos

-- Devoluciones
INSERT INTO tc_layout_linea (id, linea, cuenta, dep_id, unidad_operativa, negocio, proyecto_nmp, tipo, grupo, created_date, last_modified_date, created_by, last_modified_by) VALUES
(5, 'L', '1220001013', '', '13%03d', '', '', 'DEVOLUCIONES', 'SUCURSALES', now(), null, 'Sistema', null); -- Sucursales
INSERT INTO tc_layout_linea (id, linea, cuenta, dep_id, unidad_operativa, negocio, proyecto_nmp, tipo, grupo, created_date, last_modified_date, created_by, last_modified_by) VALUES
(6, 'L', '1011001063 ', '15000', '', '', '', 'DEVOLUCIONES', 'BANCOS', now(), null, 'Sistema', null); -- Bancos
