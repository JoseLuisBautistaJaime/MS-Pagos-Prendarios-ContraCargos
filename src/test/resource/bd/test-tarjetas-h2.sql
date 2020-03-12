--
-- Datos para tarjetas
--

INSERT INTO tipo_tarjeta_c(id, descripcion_corta, descripcion) VALUES(1, 'Tarjeta corta', 'Tarjeta');
INSERT INTO estatus_tarjeta_c(id, descripcion_corta, descripcion) VALUES(1, 'Estatus corta', 'Estatus');

INSERT INTO cliente(idCliente, nombreTitular, fecha_alta) VALUES(11111, 'Titular', '2018-12-14');

INSERT INTO tarjetas(token, ultimos_digitos, alias, fecha_alta, fecha_modificacion, idCliente, tipo_tarjeta_c_id, estatus_tarjeta_c)
VALUES('TOKEN', '1234', 'Alias', '2018-12-14', '2018-12-14', 11111, 1, 1);