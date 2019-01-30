USE compose;

-- INSERCION INICIAL EN CATALOGO estatus_tarjeta_c 
INSERT INTO estatus_tarjeta_c(id, descripcion_corta, descripcion)
	VALUES(1, 'Activa', 'Tarjeta Activa'),
		(2, 'Inactiva', 'Tarjeta Inactiva');

-- INSERCION INICIAL EN CATALOGO estatus_transaccion_c
INSERT INTO estatus_transaccion_c(id, descripcion_corta, descripcion)
	VALUES(1,'PRegistrado','Pago Registrado');

-- INSERCION INICIAL EN CATALOGO tipo_tarjeta_c
INSERT INTO tipo_tarjeta_c(id, descripcion_corta, descripcion)
	VALUES(1, 'Visa', 'Tarjeta Visa'),
		(2, 'MasterCard', 'Tarjeta MasterCard');

-- INSERCION INICIAL EN CATALOGO CAT_CATALOGO
INSERT INTO cat_catalogo(id, descripcion_corta,descripcion, nombre_tabla, activo)
	VALUES(1,'Afiliacion','El catalog de afiliaicones','catalogo_afiliacion',1),
		(2,'Estatus Tarjeta','Catalogo de estatus de tarjeta posibles','estatus_tarjeta_c',1),
		(3,'Estatus Transaccion','Estatus de transacciones posibles','estatus_transaccion_c',1),
		(4,'Tipo Tarjeta','Catalogo de tipos de tarjetas','tipo_tarjeta_c',1);