
DROP TABLE IF EXISTS to_movimiento_comision;
DROP TABLE IF EXISTS to_movimiento_pago;
DROP TABLE IF EXISTS to_movimiento_devolucion;
DROP TABLE IF EXISTS to_movimiento_transito;
DROP TABLE IF EXISTS to_movimiento_conciliacion;
DROP TABLE IF EXISTS to_movimiento_estado_cuenta;
DROP TABLE IF EXISTS to_movimiento_midas;
DROP TABLE IF EXISTS to_movimiento_proveedor;
DROP TABLE IF EXISTS to_reporte;
DROP TABLE IF EXISTS to_layout_linea;
DROP TABLE IF EXISTS to_layout_header;
DROP TABLE IF EXISTS to_layout;
DROP TABLE IF EXISTS to_global;
DROP TABLE IF EXISTS to_conciliacion;


CREATE TABLE to_conciliacion (
	id INTEGER NOT NULL,
	estatus INTEGER NOT NULL,
	entidad INTEGER NOT NULL,
	cuenta INTEGER NOT NULL,
	peoplesoft_id VARCHAR(100),
	create_date DATETIME,
	created_by VARCHAR(100),
	last_modified_by VARCHAR(100),
	last_modified_date DATETIME,
	PRIMARY KEY (id)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE to_global (
	id INTEGER NOT NULL,
	conciliacion INTEGER NOT NULL,
	fecha DATE NOT NULL,
	movimientos INTEGER,
	partidas INTEGER,
	monto DECIMAL(16,4),
	importe_midas DECIMAL(16,4),
	importe_proveedor DECIMAL(16,4),
	importe_banco DECIMAL(16,4),
	devoluciones INTEGER,
	diferencia_proveedor_midas DECIMAL(16,4),
	diferencia_proveedor_banco DECIMAL(16,4),
	PRIMARY KEY (id),
	UNIQUE UQ_to_global_conciliacion(conciliacion),
	CONSTRAINT FK_to_global_to_conciliacion 
		FOREIGN KEY (conciliacion) REFERENCES to_conciliacion (id)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE to_movimiento_conciliacion (
	id INTEGER NOT NULL,
	conciliacion INTEGER NOT NULL,
	created_by VARCHAR(100),
	created_date DATETIME,
	last_modified_by VARCHAR(100),
	last_modified_date DATETIME,
	nuevo TINYINT,
	PRIMARY KEY (id),
	CONSTRAINT FK_to_movimiento_conciliacion_to_conciliacion 
	FOREIGN KEY (conciliacion) REFERENCES to_conciliacion (id)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE to_movimiento_comision (
	id INTEGER NOT NULL,
	fecha_operacion DATE,
	fecha_cargo DATE,
	monto DECIMAL(16,4) NOT NULL,
	descripcion VARCHAR(150) NOT NULL,
	PRIMARY KEY (id),
    CONSTRAINT FK_comisiones_to_movimiento_conciliacion 
	FOREIGN KEY (id) REFERENCES to_movimiento_conciliacion (id)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE to_movimiento_pago (
	id INTEGER NOT NULL,
	estatus INTEGER NOT NULL,
	PRIMARY KEY (id),
    CONSTRAINT FK_to_movimiento_pago_to_movimiento_conciliacion 
		FOREIGN KEY (id) REFERENCES to_movimiento_conciliacion (id)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE to_movimiento_devolucion (
	id INTEGER NOT NULL,
	estatus INTEGER NOT NULL,
	fecha DATE NOT NULL,
	monto DECIMAL(16,4) NOT NULL,
	esquema_tarjeta VARCHAR(45),
	identificador_cuenta VARCHAR(45),
	titular VARCHAR(255),
	codigo_autorizacion VARCHAR(45),
	sucursal INTEGER,
	PRIMARY KEY (id),
    CONSTRAINT FK_to_movimiento_devolucion_to_movimiento_conciliacion 
		FOREIGN KEY (id) REFERENCES to_movimiento_conciliacion (id)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE to_movimiento_transito (
	id INTEGER NOT NULL,
	estatus INTEGER NOT NULL,
	folio INTEGER,
	sucursal INTEGER,
	fecha DATE,
	operacion_desc VARCHAR(45),
	monto DECIMAL(16,4),
	tipo_contrato_desc VARCHAR(45),
	esquema_tarjeta VARCHAR(45),
	cuenta VARCHAR(45),
	titular VARCHAR(255),
	PRIMARY KEY (id),
    CONSTRAINT FK_to_movimiento_transito_to_movimiento_conciliacion 
		FOREIGN KEY (id) REFERENCES to_movimiento_conciliacion (id)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1;



CREATE TABLE to_reporte (
	id INTEGER NOT NULL,
	conciliacion INTEGER NOT NULL,
	tipo VARCHAR(45) NOT NULL,
	disponible TINYINT NOT NULL DEFAULT 0,
	fecha_desde DATE NOT NULL,
	fecha_hasta DATE NOT NULL,
	create_date DATETIME,
	created_by VARCHAR(100),
	last_modified_by VARCHAR(100),
	last_modified_date DATETIME,
	PRIMARY KEY (id),
	CONSTRAINT FK_to_carga_reporte_to_conciliacion 
		FOREIGN KEY (conciliacion) REFERENCES to_conciliacion (id)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE to_movimiento_estado_cuenta (
	id INTEGER NOT NULL,
	reporte INTEGER NOT NULL,
	fecha DATE NOT NULL,
	descripcion VARCHAR(150),
	depositos DECIMAL(16,4),
	retiros DECIMAL(16,4),
	saldo DECIMAL(16,4),
	PRIMARY KEY (id),
	CONSTRAINT FK_to_movimiento_estado_cuenta_to_reporte 
		FOREIGN KEY (reporte) REFERENCES to_reporte (id)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE to_movimiento_midas (
	id INTEGER NOT NULL,
	reporte INTEGER NOT NULL,
	folio INTEGER,
	sucursal INTEGER,
	operacion_abr VARCHAR(10),
	operacion_desc VARCHAR(45),
	monto DECIMAL(16,4),
	tipo_contrato_abr VARCHAR(10),
	tipo_contrato_desc VARCHAR(45),
	num_autorizacion VARCHAR(45),
	capital DECIMAL(16,4),
	comisiones DECIMAL(16,4),
	interes DECIMAL(16,4),
	estatus VARCHAR(10),
	PRIMARY KEY (id),
	CONSTRAINT FK_to_movimiento_midas_to_reporte 
		FOREIGN KEY (reporte) REFERENCES to_reporte (id)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE to_movimiento_proveedor (
	id INTEGER NOT NULL,
	reporte INTEGER NOT NULL,
	id_comerciante VARCHAR(45),
	id_pedido VARCHAR(45),
	referencia_pedido VARCHAR(45),
	id_transaccion VARCHAR(45),
	referencia_transaccion VARCHAR(45),
	entidad_gestora VARCHAR(45),
	fecha DATETIME,
	metodo_pago VARCHAR(45),
	tipo_transaccion VARCHAR(45),
	monto DECIMAL(16,4),
	moneda VARCHAR(10),
	resutado VARCHAR(10),
	codigo_puerta_enlace VARCHAR(45),
	esquema_tarjeta VARCHAR(10),
	identificador_cuenta VARCHAR(45),
	identificador_banco VARCHAR(45),
	titular_cuenta VARCHAR(150),
	codigo_autorizacion VARCHAR(45),
	codigo_respuesta VARCHAR(45),
	numero_lote_pago VARCHAR(45),
	origen_transaccion VARCHAR(45),
	recomendacion_riesgo VARCHAR(45),
	resultado_revision_riesgo VARCHAR(45),
	respuesta_avs VARCHAR(45),
	respuesta_csc VARCHAR(45),
	respuesta_3ds VARCHAR(45),
	3dseci VARCHAR(45),
	recibo_transaccion VARCHAR(45),
	PRIMARY KEY (id)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- Layouts

CREATE TABLE to_layout (
	id INTEGER NOT NULL,
	conciliacion INTEGER NOT NULL,
	tipo VARCHAR(20) NOT NULL,
	PRIMARY KEY (id),
	CONSTRAINT FK_to_layout_to_conciliacion 
		FOREIGN KEY (conciliacion) REFERENCES to_conciliacion (id)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE to_layout_linea (
	id INTEGER NOT NULL,
	layout INTEGER NOT NULL,
	linea VARCHAR(10) NOT NULL,
	cuenta VARCHAR(45),
	dep_id VARCHAR(45),
	unidad_operativa VARCHAR(10),
	negocio VARCHAR(45),
	proyecto_nmp VARCHAR(45),
	monto DECIMAL(16,4) NOT NULL,
	create_date DATETIME,
	created_by VARCHAR(100),
	last_modified_by VARCHAR(100),
	last_modified_date DATETIME,
	PRIMARY KEY (id),
	CONSTRAINT FK_to_layout_linea_to_layout 
		FOREIGN KEY (layout) REFERENCES to_layout (id)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE to_layout_header (
	id INTEGER NOT NULL,
	layout INTEGER NOT NULL,
	cabecera VARCHAR(10) NOT NULL,
	unidad_negocio VARCHAR(45),
	descripcion VARCHAR(150),
	codigo_origen VARCHAR(45),
	fecha DATE,
	campo1 VARCHAR(45),
	campo2 VARCHAR(45),
	create_date DATETIME,
	created_by VARCHAR(100),
	last_modified_by VARCHAR(100),
	last_modified_date DATETIME,
	PRIMARY KEY (id),
	CONSTRAINT FK_to_layout_header_to_layout 
		FOREIGN KEY (layout) REFERENCES to_layout (id)
)  ENGINE=InnoDB DEFAULT CHARSET=latin1;

