DROP FUNCTION IF EXISTS `save_layout_linea`;
DELIMITER $$
CREATE FUNCTION `save_layout_linea`(

	-- Campos para layout_linea

_id INT(11)
, _cuenta VARCHAR(45)
, _dep_id VARCHAR(45)
, _linea VARCHAR(10)
, _monto DECIMAL(16,4)
, _negocio VARCHAR(45)
, _proyecto_nmp VARCHAR(45)
, _unidad_operativa VARCHAR(50)
, _id_layout INT(11)
, _nuevo TINYINT(1)

	-- Campos Actualizables de layout linea

, _created_date DATETIME
, _created_by VARCHAR(100)
, _last_modified_by VARCHAR(100)
, _last_modified_date DATETIME

)
RETURNS INT(11)
MODIFIES SQL DATA
MAIN: BEGIN
	-- Funcion que inserta una linea de layout

	DECLARE _id_layout_linea INT(11);

	-- En caso de error se hace rollback
	-- DECLARE EXIT HANDLER FOR SQLEXCEPTION
	-- BEGIN
	-- 	ROLLBACK;
	-- 	RESIGNAL;
	-- END;

	-- START TRANSACTION;

SET _id_layout_linea = (SELECT ll.id FROM to_layout_linea ll WHERE ll.id = _id);

		-- Inserta/Actualiza la linea de layout
		IF (_id_layout_linea IS NULL OR _id <= 0) THEN
			INSERT INTO to_layout_linea (id_layout, linea, cuenta, dep_id, unidad_operativa, negocio, proyecto_nmp, monto, nuevo, created_date, created_by)
			VALUES(_id_layout, _linea, _cuenta, _dep_id, _unidad_operativa, _negocio, _proyecto_nmp, _monto, _nuevo, _created_date, _created_by);
            
            SET _id_layout_linea = (SELECT ll.id FROM to_layout_linea ll WHERE ll.id = _id);
		ELSE
			UPDATE to_layout_linea SET id_layout = _id_layout, linea = _linea, cuenta = _cuenta, dep_id = _dep_id, unidad_operativa = _unidad_operativa, negocio = _negocio, proyecto_nmp = _proyecto_nmp, monto = _monto, nuevo = _nuevo, last_modified_by = _last_modified_by, last_modified_date = _last_modified_date
			WHERE id = _id;
        END IF;

	-- COMMIT;
    
	RETURN _id_layout_linea;

END MAIN;
$$
DELIMITER ;