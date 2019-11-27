DROP FUNCTION IF EXISTS `save_layout`;
DELIMITER $$
CREATE FUNCTION `save_layout`(
	-- Campos para layout

_id INT(11)
, _id_conciliacion BIGINT(20)
, _tipo VARCHAR(50)

)
RETURNS INT(11)
MODIFIES SQL DATA
MAIN: BEGIN
	-- Funcion que inserta un layout

	DECLARE _id_layout INT(11);

	-- En caso de error se hace rollback
	-- DECLARE EXIT HANDLER FOR SQLEXCEPTION
	-- BEGIN
	-- 	ROLLBACK;
	-- 	RESIGNAL;
	-- END;

	-- START TRANSACTION;

SET _id_layout = (SELECT l.id FROM to_layout l WHERE l.id_conciliacion = _id_conciliacion AND l.tipo = _tipo);

		-- Inserta/Actualiza el movimiento transito
		IF (_id_layout IS NULL OR _id <= 0) THEN
			INSERT INTO to_layout(id_conciliacion, tipo)
			VALUES(_id_conciliacion, _tipo);
            SET _id_layout = (SELECT l.id FROM to_layout l WHERE l.id_conciliacion = _id_conciliacion AND l.tipo = _tipo);
		END IF;

	-- COMMIT;
    
	RETURN _id_layout;

END MAIN;
$$
DELIMITER ;