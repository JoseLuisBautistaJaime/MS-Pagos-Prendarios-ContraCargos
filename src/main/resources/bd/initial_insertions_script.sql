USE compose;

-- INSERCION INICIAL EN CATALOGO tk_estatus_tarjeta
INSERT INTO tk_estatus_tarjeta(id, descripcion_corta, descripcion)
	VALUES(1, 'Activa', 'Tarjeta Activa'),
		(2, 'Inactiva', 'Tarjeta Inactiva');

-- INSERCION INICIAL EN CATALOGO tk_estatus_transaccion
INSERT INTO tk_estatus_transaccion(id, descripcion_corta, descripcion)
	VALUES(1,'PRegistrado','Pago Registrado');

-- INSERCION INICIAL EN CATALOGO tk_tipo_tarjeta
INSERT INTO tk_tipo_tarjeta(id, descripcion_corta, descripcion)
	VALUES(1, 'Visa', 'Tarjeta Visa'),
		(2, 'MasterCard', 'Tarjeta MasterCard');

-- INSERCION INICIAL EN CATALOGO TK_CATALOGO
INSERT INTO tk_catalogo(id, descripcion_corta,descripcion, nombre_tabla, activo)
	VALUES(1,'Afiliacion','El catalog de afiliaicones','catalogo_afiliacion',1),
		(2,'Estatus Tarjeta','Catalogo de estatus de tarjeta posibles','tk_estatus_tarjeta',1),
		(3,'Estatus Transaccion','Estatus de transacciones posibles','tk_estatus_transaccion',1),
		(4,'Tipo Tarjeta','Catalogo de tipos de tarjetas','tk_tipo_tarjeta',1);

--- INSERCION INICIAL EN CATALOG DE ESTATUS DE OPERACIONES
INSERT INTO tk_estatus_operacion(id, descripcion_corta, descripcion)
	VALUES (1, "Operacion Exitosa", "Operacion realizada de manera exitosa"),
		(2, "Operacion Fallida", "Operacion ralizada de manera fallida");

-- INSERCION DE METODOS DE AFILIACION (3DSECURE INCLUIDO (1))
INSERT INTO tk_tipo_afiliacion(id, descripcion_corta, descripcion) VALUES(0, 'Ningun tipo', 'Ningun tipo'), (0, '3DSecure', 'Metodo de autorizacion 3DSecure');
