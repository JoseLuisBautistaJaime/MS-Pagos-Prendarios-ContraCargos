--
-- Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
-- Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
--

-- --------------------------------------------------------------------------------------------- --
-- ------- INSERCIONES DE CATÁLOGO DE ESTATUS EJECUCIÓN PRE-CONCILIACIÓN ----------------------- --
-- --------------------------------------------------------------------------------------------- --

INSERT INTO tk_estatus_ejecucion_preconciliacion (id, descripcion_corta, descripcion, order_number) VALUES (1, 'SOLICITUD', 'SOLICITUD', 1);
INSERT INTO tk_estatus_ejecucion_preconciliacion (id, descripcion_corta, descripcion, order_number) VALUES (2, 'DESCARGA_CORRECTA', 'DESCARGA CORRECTA', 2);
INSERT INTO tk_estatus_ejecucion_preconciliacion (id, descripcion_corta, descripcion, order_number) VALUES (3, 'DESCARGA_INCORRECTA', 'DESCARGA INCORRECTA', 3);



-- --------------------------------------------------------------------------------------------- --
-- ------- INSERCIONES DE CATÁLOGO DE ESTATUS EJECUCIÓN DEL PROCESO DE CONCILIACIÓN ------------ --
-- --------------------------------------------------------------------------------------------- --

INSERT INTO tk_estatus_ejecucion_conciliacion (id, descripcion_corta, descripcion, order_number) VALUES (1, 'CREADA', 'CREADA', 1);
INSERT INTO tk_estatus_ejecucion_conciliacion (id, descripcion_corta, descripcion, order_number) VALUES (2, 'CARGA_MOVIMIENTOS_MIDAS', 'CARGA MOVIMIENTOS MIDAS', 2);
INSERT INTO tk_estatus_ejecucion_conciliacion (id, descripcion_corta, descripcion, order_number) VALUES (3, 'CARGA_MOVIMIENTOS_PROVEEDOR', 'CARGA MOVIMIENTOS PROVEEDOR', 3);
INSERT INTO tk_estatus_ejecucion_conciliacion (id, descripcion_corta, descripcion, order_number) VALUES (4, 'CONCILIACION_MIDAS_PROVEEDOR', 'CONCILIACION MOVIMIENTOS MIDAS Y PROVEEDOR', 4);
INSERT INTO tk_estatus_ejecucion_conciliacion (id, descripcion_corta, descripcion, order_number) VALUES (5, 'CARGA_MOVIMIENTOS_ESTADO_CUENTA', 'CARGA MOVIMIENTOS ESTADO DE CUENTA', 5);
INSERT INTO tk_estatus_ejecucion_conciliacion (id, descripcion_corta, descripcion, order_number) VALUES (6, 'CONCILIACION_MIDAS_PROVEEDOR_ESTADOCUENTA', 'CONCILIACION MOVIMIENTOS MIDAS, PROVEEDOR Y ESTADO DE CUENTA', 6);
INSERT INTO tk_estatus_ejecucion_conciliacion (id, descripcion_corta, descripcion, order_number) VALUES (7, 'GENERAR_LAYOUTS', 'GENERAR LAYAOUTS', 7);
INSERT INTO tk_estatus_ejecucion_conciliacion (id, descripcion_corta, descripcion, order_number) VALUES (8, 'ENVIAR_LAYOUTS', 'ENVIAR LAYAOUTS', 8);


-- --------------------------------------------------------------------------------------------- --
-- ------------------------- INSERCIONES DE CATÁLOGO DE PROCESOS ------------------------------- --
-- --------------------------------------------------------------------------------------------- --

INSERT INTO tk_proceso (id, descripcion_corta, descripcion) VALUES (1, 'Preconciliacion', 'Proceso automatizado de preconciliacion');
INSERT INTO tk_proceso (id, descripcion_corta, descripcion) VALUES (2, 'Conciliacion Etapa 1  (Carga de Movimientos MIDAS / Proveedor)', 'Proceso automatizado de conciliacion etapa 1');
INSERT INTO tk_proceso (id, descripcion_corta, descripcion) VALUES (3, 'Conciliacion Etapa 2  (Carga de Movimientos Estado de Cuenta)', 'Proceso automatizado de conciliacion etapa 2');
INSERT INTO tk_proceso (id, descripcion_corta, descripcion) VALUES (4, 'Conciliacion Etapa 3  (Envio de Layouts)', 'Proceso automatizado de conciliacion etapa 3');


-- --------------------------------------------------------------------------------------------- --
-- ------------------ INSERCIONES DE CATÁLOGO DE CALENDARIO EJECUCIÓN PROCESO ------------------ --
-- --------------------------------------------------------------------------------------------- --

INSERT INTO to_calendario_ejecucion_proceso (id, id_proceso, proveedor, configuracion, reintentos, rango_dias_cobertura_min, rango_dias_cobertura_max, activo, created_date, created_by, last_modified_by, last_modified_date) VALUES (1, 1, 'OPENPAY', '0 30 0 ? * *', 2, 1, 1, true, now(), 'Sistema', null, null);
INSERT INTO to_calendario_ejecucion_proceso (id, id_proceso, proveedor, configuracion, reintentos, rango_dias_cobertura_min, rango_dias_cobertura_max, activo, created_date, created_by, last_modified_by, last_modified_date) VALUES (2, 2, 'OPENPAY', '0 0 9 ? * TUE,WED,THU,FRI', 0, 1, 1, true, now(), 'Sistema', null, null);
INSERT INTO to_calendario_ejecucion_proceso (id, id_proceso, proveedor, configuracion, reintentos, rango_dias_cobertura_min, rango_dias_cobertura_max, activo, created_date, created_by, last_modified_by, last_modified_date) VALUES (3, 2, 'OPENPAY', '0 0 9 ? * MON', 0, 3, 1, true, now(), 'Sistema', null, null);
INSERT INTO to_calendario_ejecucion_proceso (id, id_proceso, proveedor, configuracion, reintentos, rango_dias_cobertura_min, rango_dias_cobertura_max, activo, created_date, created_by, last_modified_by, last_modified_date) VALUES (4, 3, 'OPENPAY', '0 0 10 ? * WED,THU,FRI,SAT', 0, 2, 2, true, now(), 'Sistema', null, null);
INSERT INTO to_calendario_ejecucion_proceso (id, id_proceso, proveedor, configuracion, reintentos, rango_dias_cobertura_min, rango_dias_cobertura_max, activo, created_date, created_by, last_modified_by, last_modified_date) VALUES (5, 3, 'OPENPAY', '0 0 10 ? * TUE', 0, 4, 2, true, now(), 'Sistema', null, null);
INSERT INTO to_calendario_ejecucion_proceso (id, id_proceso, proveedor, configuracion, reintentos, rango_dias_cobertura_min, rango_dias_cobertura_max, activo, created_date, created_by, last_modified_by, last_modified_date) VALUES (6, 4, 'OPENPAY', '0 0 11 ? * TUE,WED,THU,FRI,SAT', 0, 2, 1, true, now(), 'Sistema', null, null);
