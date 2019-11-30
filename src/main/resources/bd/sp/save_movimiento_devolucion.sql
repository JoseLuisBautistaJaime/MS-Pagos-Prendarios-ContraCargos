DROP PROCEDURE IF EXISTS `save_movimiento_devolucion`;
DELIMITER $$
CREATE PROCEDURE `save_movimiento_devolucion`(

	-- Campos para to_movimiento_devolucion
    _estatus INT(11),
    _fecha DATE,
    _monto DECIMAL(16, 4),
    _esquema_tarjeta VARCHAR(45),
    _identificador_cuenta VARCHAR(45),
    _titular VARCHAR(255),
    _codigo_autorizacion VARCHAR(45),
    _sucursal INT(11),
    _fecha_liquidacion DATE,

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

	-- Funcion que inserta un movimiento conciliacion devolucion

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

		-- Inserta/Actualiza el movimiento devolucion
		IF (_id IS NULL OR _id <= 0) THEN
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
		END IF;

	-- COMMIT;
	
	-- RETURN _id_movimiento_conciliacion;

END MAIN;
$$
DELIMITER ;
