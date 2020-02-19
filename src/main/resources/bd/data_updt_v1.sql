-- ------------------------------------------------------------------------------ --
-- --------------- Se ajusta maquina de sub-estatus de conciliaicon ------------- --
-- --------------------------- [2020-02-18 13:53:06] ---------------------------- --
-- ------------------------------------------------------------------------------ --
DELETE msc FROM tk_maquina_estados_subestatus_conciliacion msc WHERE msc.id_sub_estatus_inicial = 12 AND msc.id_sub_estatus_posible = 14;
DELETE msc FROM tk_maquina_estados_subestatus_conciliacion msc WHERE msc.id_sub_estatus_inicial = 18 AND msc.id_sub_estatus_posible = 17;
INSERT INTO tk_maquina_estados_subestatus_conciliacion(id_sub_estatus_inicial, id_sub_estatus_posible) VALUES (15, 18);