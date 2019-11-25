DROP FUNCTION IF EXISTS `save_movimiento_transito`;
DELIMITER $$
CREATE FUNCTION `save_movimiento_transito`(

	-- Campos para to_movimiento_transito
    _estatus INT(11),
    _folio INT(11),
    _sucursal INT(11),
    _fecha DATE,
    _operacion_desc VARCHAR(45),
    _monto DECIMAL(16, 4),
    _tipo_contrato_desc VARCHAR(45),
    _esquema_tarjeta VARCHAR(45),
    _cuenta VARCHAR(45),
    _titular VARCHAR(255),
    _num_autorizacion VARCHAR(45),

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
	-- Funcion que inserta un movimiento conciliacion transito

	DECLARE _id_movimiento_conciliacion INT(11);

	-- En caso de error se hace rollback
	-- DECLARE EXIT HANDLER FOR SQLEXCEPTION
	-- BEGIN
	-- 	ROLLBACK;
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
		END IF;

	-- COMMIT;
	
	RETURN _id_movimiento_conciliacion;

END MAIN;
$$
DELIMITER ;