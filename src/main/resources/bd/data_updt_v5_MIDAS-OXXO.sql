--
-- Proyecto:        NMP - MIDAS - OXXO.
-- Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
--



-- ------------------------------------------------------------------------------------------------------------------ --
-- INSERTS INICIALES EN TABLA - tk_operacion
-- ------------------------------------------------------------------------------------------------------------------ --
INSERT INTO tk_operacion (id, abreviatura, descripcion) VALUES
(198, 'RFE', 'Cobro Refrendo Extemporaneo');


-- ------------------------------------------------------------------------------------------------------------------ --
-- ------------------------------------------------------------------------------------------------------------------ --

-- Se crea la secuencia para OPENPAY usando el ultimo folio
INSERT INTO seq_conciliacion(seq_name, seq_value) VALUES('OPENPAY', (SELECT MAX(id) FROM to_conciliacion));
-- Se crea la secuencia para OXXO iniciando en 0
INSERT INTO seq_conciliacion(seq_name, seq_value) VALUES('OXXO', 0);


-- ---------------------------------------------------------------------------------- --
-- --------------- INSERT DE LAS COMISIONES PROVEEDORES / CORRESPONSALES ------------------------ --
-- ---------------------------------------------------------------------------------- --
INSERT INTO `tc_comision_proveedor` (`id`, `corresponsal`, `comision`, `iva`) 
	VALUES ('1', 'OXXO', '15.52', '2.48');


-- ---------------------------------------------------------------------------------- --
-- --------------- INSERT DE LAS COMISIONES PROVEEDORES / CORRESPONSALES ------------------------ --
-- ---------------------------------------------------------------------------------- --
INSERT INTO `tk_estatus_bonificacion` (`id`, `nombre`, `descripcion`, `descripcion_corta`) 
	VALUES (1, 'Pendiente', 'Pendiente', 'Pendiente');


-- ---------------------------------------------------------------------------------- --
-- ---------- INSERT DE ESTADOS EN MAQUINA DE ESTADOS PARA CORRESPONSAL OXXO -------- --
-- ---------------------------------------------------------------------------------- --
INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (30, 'ALTA_MOVIMIENTOS_PROCESOS_NOCTURNOS', 1, 2, 'OXXO');

INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (31, 'ALTA_MOVIMIENTOS_PROCESOS_NOCTURNOS', 2, 3, 'OXXO');
INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (32, 'ALTA_MOVIMIENTOS_PROCESOS_NOCTURNOS', 2, 4, 'OXXO');

INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (33, 'ALTA_MOVIMIENTOS_PROVEEDOR_TRANSACCIONAL', 3, 5, 'OXXO');

INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (34, 'ALTA_MOVIMIENTOS_PROCESOS_NOCTURNOS', 4, 2, 'OXXO');

INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (35, 'ALTA_MOVIMIENTOS_PROVEEDOR_TRANSACCIONAL', 5, 6, 'OXXO');
INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (36, 'ALTA_MOVIMIENTOS_PROVEEDOR_TRANSACCIONAL', 5, 7, 'OXXO');

INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (37, 'CONCILIACION_MERGE', 6, 8, 'OXXO');
INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (38, 'ALTA_MOVIMIENTOS_ESTADO_CUENTA', 6, 11, 'OXXO');

INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (39, 'ALTA_MOVIMIENTOS_PROVEEDOR_TRANSACCIONAL', 7, 5, 'OXXO');

INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (40, 'CONCILIACION_MERGE', 8, 9, 'OXXO');
INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (41, 'CONCILIACION_MERGE', 8, 10, 'OXXO');

INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (42, 'CONCILIACION_MERGE', 9, 8, 'OXXO');
INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (43, 'ALTA_MOVIMIENTOS_ESTADO_CUENTA', 9, 11, 'OXXO');
INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (44, 'GENERACION_LAYOUTS', 9, 14, 'OXXO');

INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (45, 'CONCILIACION_MERGE', 10, 8, 'OXXO');

INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (46, 'ALTA_MOVIMIENTOS_ESTADO_CUENTA', 11, 12, 'OXXO');
INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (47, 'ALTA_MOVIMIENTOS_ESTADO_CUENTA', 11, 13, 'OXXO');

INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (148, 'CONCILIACION_MERGE', 12, 8, 'OXXO');
INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (49, 'GENERACION_LAYOUTS', 12, 14, 'OXXO');

INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (50, 'ALTA_MOVIMIENTOS_ESTADO_CUENTA', 13, 11, 'OXXO');

INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (51, 'GENERACION_LAYOUTS', 14, 15, 'OXXO');
INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (52, 'GENERACION_LAYOUTS', 14, 16, 'OXXO');

INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (53, 'ENVIO_CONCILIACION', 15, 17, 'OXXO');

INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (54, 'GENERACION_LAYOUTS', 16, 14, 'OXXO');

INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (55, 'ENVIO_CONCILIACION', 17, 18, 'OXXO');
INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (56, 'ENVIO_CONCILIACION', 17, 19, 'OXXO');

INSERT INTO tk_maquina_estados_subestatus_conciliacion (id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES (57, 'ENVIO_CONCILIACION', 18, 17, 'OXXO');

INSERT INTO tk_maquina_estados_subestatus_conciliacion(id, nombre_proceso, id_sub_estatus_inicial, id_sub_estatus_posible, corresponsal)
VALUES( 58, 'CONCILIACION_MERGE', 8, 12, 'OXXO');