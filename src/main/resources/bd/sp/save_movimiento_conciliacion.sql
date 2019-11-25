DROP FUNCTION IF EXISTS `save_movimiento_conciliacion`;
DELIMITER $$
CREATE /*DEFINER=`root`@`127.0.0.1`*/ FUNCTION `save_movimiento_conciliacion`(
	_id INT(11),
	_id_conciliacion BIGINT(20),
    _created_by VARCHAR(100),
    _created_date DATETIME,
    _last_modified_by VARCHAR(100),
    _last_modified_date DATETIME,
    _nuevo TINYINT(4),
    _id_movimiento_midas INT(11)
)
RETURNS INT(11)
MODIFIES SQL DATA
MAIN: BEGIN

	DECLARE _id_movimiento_conciliacion INT(11);
	-- Funcion que inserta un movimiento conciliacion y regresa el id generado

	-- En caso de error se hace rollback
	-- DECLARE EXIT HANDLER FOR SQLEXCEPTION
	-- BEGIN
	--	ROLLBACK;
	--	RESIGNAL;
	-- END;

	-- Inserta el movimiento conciliacion y regresa el id
	IF (_id IS NULL) THEN
		INSERT INTO to_movimiento_conciliacion(id_conciliacion, created_by, created_date, last_modified_by, last_modified_date, nuevo, id_movimiento_midas)
		VALUES(_id_conciliacion, _created_by, _created_date, _last_modified_by, _last_modified_date, _nuevo, _id_movimiento_midas);
		
		SET _id_movimiento_conciliacion = LAST_INSERT_ID();
	ELSE
		UPDATE to_movimiento_conciliacion
		SET
			id_concilacion = _id_concilacion,
			created_by = _created_by,
			created_date = _created_date,
			last_modified_by = _last_modified_by,
			last_modified_date = _last_modified_date,
			nuevo = _nuevo,
			id_movimiento_midas = _id_movimiento_midas
		WHERE
			id = _id;
		SET _id_movimiento_conciliacion = _id;
	END IF;

	RETURN _id_movimiento_conciliacion;

END MAIN;
$$
DELIMITER ;
