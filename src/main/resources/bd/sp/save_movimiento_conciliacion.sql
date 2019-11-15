CREATE /*DEFINER=`root`@`127.0.0.1`*/ FUNCTION `save_movimiento_conciliacion`(
	IN _id INT(11),
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

	-- Funcion que inserta un movimiento conciliacion y regresa el id generado

	-- En caso de error se hace rollback
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		ROLLBACK;
		RESIGNAL;
	END;

	-- Inserta el movimiento conciliacion y regresa el id
	IF (_id IS NULL) THEN
		INSERT INTO to_movimiento_conciliacion(id_concilacion, created_by, created_date, last_modified_by, last_modified_date, nuevo, id_movimiento_midas)
		VALUES(_id_conciliacion, _created_by, _created_date, _last_modified_by, _last_modified_date, _nuevo, _id_movimiento_midas);
		
		SET _id = LAST_INSERT_ID();
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
	END IF

	RETURN _id;

END;
