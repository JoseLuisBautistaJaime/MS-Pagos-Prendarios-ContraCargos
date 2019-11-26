DROP FUNCTION IF EXISTS `save_movimiento_pago`;
DELIMITER $$
CREATE FUNCTION `save_movimiento_pago`(

	-- Campos para to_movimiento_pago
    _estatus INT(11),
    _monto DECIMAL(16, 4),

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
RETURNS INT(11)
MODIFIES SQL DATA
MAIN: BEGIN

	-- Funcion que inserta un movimiento conciliacion pago

	DECLARE _id_movimiento_conciliacion INT(11);

	-- En caso de error se hace rollback
	-- DECLARE EXIT HANDLER FOR SQLEXCEPTION
	-- BEGIN
	--	ROLLBACK;
	-- 	RESIGNAL;
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

		-- Inserta/Actualiza el movimiento pago
		IF (_id IS NULL OR _id <= 0) THEN
			INSERT INTO to_movimiento_pago(id, estatus, monto)
			VALUES(_id_movimiento_conciliacion, _estatus, _monto);
		ELSE
			UPDATE to_movimiento_pago
			SET
				estatus = _estatus,
				monto = _monto
			WHERE
				id = _id_movimiento_conciliacion;
		END IF;

	-- COMMIT;
	
	RETURN _id_movimiento_conciliacion;

END MAIN;
$$
DELIMITER ;
