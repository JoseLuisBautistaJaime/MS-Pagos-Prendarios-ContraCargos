-- -------------------------------------------------------------------------------------- --
-- -----	SCRIPT DE ACTUALIZACION DE data.sql VERSION 1 [2019-07-23 19:39:54]	----- --
-- -------------------------------------------------------------------------------------- --
INSERT INTO `tk_maquina_estados_subestatus_conciliacion`
       (`nombre_proceso`, `id_sub_estatus_inicial`, `id_sub_estatus_posible`)
VALUES
       ('ALTA_REPORTES', 1, 2), ('ALTA_REPORTES', 1, 3), ('ALTA_REPORTES', 1, 4)
       , ('ALTA_REPORTES', 2, 3), ('ALTA_REPORTES', 2, 4)
       , ('ALTA_REPORTES', 3, 5)
       , ('ALTA_REPORTES', 4, 2)
       , ('ALTA_REPORTES', 5, 6), ('ALTA_REPORTES', 5, 7)
       , ('ALTA_REPORTES', 6, 8)
       , ('ALTA_REPORTES', 7, 5)
       , ('ALTA_REPORTES', 8, 9), ('ALTA_REPORTES', 8, 10)
       , ('ALTA_REPORTES', 9, 11)
       , ('ALTA_REPORTES', 10, 8)
       , ('ALTA_REPORTES', 11, 12), ('ALTA_REPORTES', 11, 13)
       , ('ALTA_REPORTES', 12, 14)
       , ('ALTA_REPORTES', 13, 11)
       , ('ALTA_REPORTES', 14, 15), ('ALTA_REPORTES', 14, 16)
       , ('ALTA_REPORTES', 15, 14)
;