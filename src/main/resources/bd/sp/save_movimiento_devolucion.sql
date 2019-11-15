CREATE FUNCTION `save_movimiento_devolucion`(

	IN _id INT(11),

	-- Campos para to_movimiento_conciliacion
	IN _id_conciliacion BIGINT(20),
    IN _created_by VARCHAR(100),
    IN _created_date DATETIME,
    IN _last_modify_by VARCHAR(100),
    IN _last_modified_date DATETIME,
    IN _nuevo TINYINT(4),
    IN _id_movimiento_midas INT(11),

	-- Campos para to_movimiento_devolucion
    IN _estatus INT(11),
    IN _fecha DATE,
    IN _monto DECIMAL(16, 4),
    IN _esquema_tarjeta VARCHAR(45),
    IN _identificador_cuenta VARCHAR(45),
    IN _titular VARCHAR(255),
    IN _codigo_autorizacion VARCHAR(45),
    IN _sucursal INT(11),
    IN _fecha_liquidacion DATE
)
RETURNS INT(11)
BEGIN
	-- Funcion que inserta un movimiento conciliacion devolucion

	DECLARE _id_movimiento_conciliacion INT(11);

	-- En caso de error se hace rollback
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		ROLLBACK;
		RESIGNAL;
	END;

	START TRANSACTION;
	
		-- Inserta/actualiza el movimiento conciliacion y regresa el id
		SET _id_movimiento_conciliacion = save_movimiento_conciliacion(
			_id,
			_id_conciliacion,
	    	_created_by,
	    	_created_date,
	    	_last_modify_by,
	    	_last_modified_date,
	    	_nuevo,
	    	_id_movimiento_midas
		);

		-- Inserta/Actualiza el movimiento devolucion
		IF (_id IS NULL) THEN
			INSERT INTO to_movimiento_devolucion(id, estatus, fecha, monto, esquema_tarjeta, identificador_cuenta, titular, codigo_autorizacion, sucursal, fecha_liquidacion)
			VALUES(_id_movimiento_conciliacion, _estatus, _fecha, _monto, _esquema_tarjeta, _identificador_cuenta, _titular, _codigo_autorizacion, _sucursal, fecha_liquidacion);
		ELSE
			UPDATE to_movimiento_devolucion
			SET
				estatus = _estatus,
				fecha = _fecha,
				monto = _monto,
				esquema_tarjeta = _esquema_tarjeta,
				identificador_cuenta = _identificador_cuenta,
				titular = _titular,
				codigo_autorizacion = _codigo_autorizacion,
				sucursal = _sucursal,
				fecha_liquidacion = _fecha_liquidacion
			WHERE
				id = _id_movimiento_conciliacion;
		END IF

	COMMIT;
	
	RETURN _id;

END;
