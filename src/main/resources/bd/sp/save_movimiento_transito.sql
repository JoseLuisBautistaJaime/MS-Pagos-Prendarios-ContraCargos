CREATE FUNCTION `save_movimiento_transito`(

	IN _id INT(11),

	-- Campos para to_movimiento_conciliacion
	IN _id_conciliacion BIGINT(20),
    IN _created_by VARCHAR(100),
    IN _created_date DATETIME,
    IN _last_modify_by VARCHAR(100),
    IN _last_modified_date DATETIME,
    IN _nuevo TINYINT(4),
    IN _id_movimiento_midas INT(11),

	-- Campos para to_movimiento_transito
    IN _estatus INT(11),
    IN _folio INT(11),
    IN _sucursal INT(11),
    IN _fecha DATE,
    IN _operacion_desc VARCHAR(45),
    IN _monto DECIMAL(16, 4)
    IN _tipo_contrato_desc VARCHAR(45),
    IN _esquema_tarjeta VARCHAR(45),
    IN _cuenta VARCHAR(45),
    IN _titular VARCHAR(255),
    IN _num_autorizacion(45)
)
RETURNS INT(11)
BEGIN
	-- Funcion que inserta un movimiento conciliacion transito

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

		-- Inserta/Actualiza el movimiento transito
		IF (_id IS NULL) THEN
			INSERT INTO to_movimiento_transito(id, estatus, folio, sucursal, fecha, operacion_desc, monto, tipo_contrato_desc, esquema_tarjeta, cuenta, titular, num_autorizacion)
			VALUES(_id_movimiento_conciliacion, _estatus, _folio, _sucursal, _fecha, _operacion_desc, _monto, _tipo_contrato_desc, _esquema_tarjeta, cuenta, _titular, _num_autorizacion);
		ELSE
			UPDATE to_movimiento_transito
			SET
				estatus = _estatus,
				folio = _folio,
				sucursal = _sucursal,
				fecha = _fecha,
				operacion_desc = _operacion_desc,
				monto = _monto,
				tipo_contrato_desc = _tipo_contrato_desc,
				esquema_tarjeta = _esquema_tarjeta,
				cuenta = _cuenta,
				titular = _titular,
				num_autorizacion = _num_autorizacion
			WHERE
				id = _id_movimiento_conciliacion;
		END IF

	COMMIT;
	
	RETURN _id;

END;
