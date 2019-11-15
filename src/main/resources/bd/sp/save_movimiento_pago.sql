CREATE FUNCTION `save_movimiento_pago`(

	IN _id INT(11),

	-- Campos para to_movimiento_conciliacion
	IN _id_conciliacion BIGINT(20),
    IN _created_by VARCHAR(100),
    IN _created_date DATETIME,
    IN _last_modify_by VARCHAR(100),
    IN _last_modified_date DATETIME,
    IN _nuevo TINYINT(4),
    IN _id_movimiento_midas INT(11),

	-- Campos para to_movimiento_pago
    IN _estatus INT(11),
    IN _monto DECIMAL(16, 4)
)
RETURNS INT(11)
BEGIN
	-- Funcion que inserta un movimiento conciliacion pago

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

		-- Inserta/Actualiza el movimiento pago
		IF (_id IS NULL) THEN
			INSERT INTO to_movimiento_pago(id, estatus, monto)
			VALUES(_id_movimiento_conciliacion, _estatus, _monto);
		ELSE
			UPDATE to_movimiento_pago
			SET
				estatus = _estatus,
				monto = _monto
			WHERE
				id = _id_movimiento_conciliacion;
		END IF

	COMMIT;
	
	RETURN _id;

END;
