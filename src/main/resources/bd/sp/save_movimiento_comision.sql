CREATE /*DEFINER=`root`@`127.0.0.1`*/ FUNCTION `save_movimiento_comision`(

	IN _id INT(11),

	-- Campos para to_movimiento_comision
    IN _fecha_operacion DATE,
    IN _fecha_cargo DATE,
    IN _monto DECIMAL(16, 4),
    IN _tipo VARCHAR(50),
    IN _id_movimiento_estado_cuenta BIGINT(20),
    IN _descripcion VARCHAR(150),
    IN _estatus,

	-- Campos para to_movimiento_conciliacion
	IN _id_conciliacion BIGINT(20),
    IN _created_by VARCHAR(100),
    IN _created_date DATETIME,
    IN _last_modify_by VARCHAR(100),
    IN _last_modified_date DATETIME,
    IN _nuevo TINYINT(4),
    IN _id_movimiento_midas INT(11)
)
RETURNS INT(11)
BEGIN
	-- Funcion que inserta un movimiento conciliacion comision
	DECLARE _id_movimiento_conciliacion INT(11);

	-- En caso de error se hace rollback
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		ROLLBACK;
		RESIGNAL;
	END;


	START TRANSACTION;

		-- Inserta el movimiento conciliacion y regresa el id
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

		-- Inserta el movimiento comision
		IF (_id IS NULL) THEN
			INSERT INTO to_movimiento_comision(id, fecha_operacion, fecha_cargo, monto, tipo, id_movimiento_estado_cuenta, descripcion, estatus)
			VALUES(_id_movimiento_conciliacion, _fecha_operacion, _fecha_cargo, _monto, _tipo, _id_movimiento_estado_cuenta, _descripcion, _estatus);
		ELSE
			UPDATE to_movimiento_comision
			SET
				fecha_operacion = _fecha_operacion,
				fecha_cargo = _fecha_cargo,
				monto = _monto,
				tipo = _tipo,
				id_movimiento_estado_cuenta = _id_movimiento_estado_cuenta,
				descripcion = _descripcion,
				estatus = _estatus
			WHERE
				id = _id_movimiento_conciliacion;
		END IF

	COMMIT;
	
	RETURN _id;

END;
