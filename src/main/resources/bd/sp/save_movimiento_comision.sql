DROP PROCEDURE IF EXISTS `save_movimiento_comision`;
DELIMITER $$
CREATE PROCEDURE `save_movimiento_comision`(

	-- Campos para to_movimiento_comision
    _fecha_operacion DATE,
    _fecha_cargo DATE,
    _monto DECIMAL(16, 4),
    _descripcion VARCHAR(150),
    _estatus TINYINT(4),
    _tipo VARCHAR(50),
    _id_movimiento_estado_cuenta BIGINT(20),

	-- Campos para to_movimiento_conciliacion
	_id INT(11),
	_id_conciliacion BIGINT(20),
    _nuevo TINYINT(4),
    _id_movimiento_midas INT(11),

    -- Updatable
    _created_date DATETIME,
    _last_modified_date DATETIME,
    _created_by VARCHAR(100),
    _last_modify_by VARCHAR(100)
)
MODIFIES SQL DATA
MAIN: BEGIN

	-- Funcion que inserta un movimiento conciliacion comision
	DECLARE _id_movimiento_conciliacion INT(11);

	-- En caso de error se hace rollback
	-- DECLARE EXIT HANDLER FOR SQLEXCEPTION
	-- BEGIN
	--	ROLLBACK;
	--	RESIGNAL;
	-- END;


	-- START TRANSACTION;

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

		-- Inserta el movimiento comision
		IF (_id IS NULL OR _id <= 0) THEN
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
		END IF;

	-- COMMIT;

	-- RETURN _id_movimiento_conciliacion;

END MAIN;
$$
DELIMITER ;
