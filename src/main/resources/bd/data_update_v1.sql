-- -------------------------------------------------------------------------------------- --
-- -----	SCRIPT DE ACTUALIZACION DE data.sql VERSION 1 [2019-07-23 19:39:54]	----- --
-- -------------------------------------------------------------------------------------- --
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

INSERT INTO tk_estatus_movimientos_en_transito (id, nombre, descripcion, estatus, created_date, last_modified_date, created_by, last_modified_by, descripcion_corta) VALUES
(5, 'No Identificada en Open Pay', 'No Identificada en Open Pay', true, now(), null, 'Sistema', null, 'NIOP');
