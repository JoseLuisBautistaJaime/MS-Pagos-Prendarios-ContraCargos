DROP FUNCTION IF EXISTS `save_layout_header`;
DELIMITER $$
CREATE FUNCTION `save_layout_header`(

	-- Campos para layout_header

_id INT(11)
, _id_layout INT(11)
, _cabecera VARCHAR(10)
, _unidad_negocio VARCHAR(45)
, _descripcion VARCHAR(150)
, _codigo_origen VARCHAR(45)
, _fecha DATE
, _campo1 VARCHAR(45)
, _campo2 VARCHAR(45)

-- Campos Actualizables de layout header

, _created_date DATETIME
, _created_by VARCHAR(100)
, _last_modified_by VARCHAR(100)
, _last_modified_date DATETIME

)
RETURNS INT(11)
MODIFIES SQL DATA
MAIN: BEGIN
	-- Funcion que inserta un header de layout

	DECLARE _id_layout_header INT(11);

	-- En caso de error se hace rollback
	-- DECLARE EXIT HANDLER FOR SQLEXCEPTION
	-- BEGIN
	-- 	ROLLBACK;
	-- 	RESIGNAL;
	-- END;

	-- START TRANSACTION;

SET _id_layout_header = (SELECT lh.id FROM to_layout_header lh WHERE lh.id = _id);

		-- Inserta/Actualiza el header de layout
		IF (_id_layout_header IS NULL OR _id <= 0) THEN
			INSERT INTO to_layout_header (id, id_layout, cabecera, unidad_negocio, descripcion, codigo_origen, fecha, campo1, campo2, created_date, created_by)
			VALUES(_id, _id_layout, _cabecera, _unidad_negocio, _descripcion, _codigo_origen, _fecha, _campo1, _campo2, _created_date, _created_by);
            
            SET _id_layout_header = (SELECT lh.id FROM to_layout_header lh WHERE lh.id = _id);
		ELSE
			UPDATE to_layout_header 
				SET id = _id, id_layout = _id_layout, cabecera = _cabecera, unidad_negocio = _unidad_negocio, descripcion = _descripcion, codigo_origen = _codigo_origen, fecha = _fecha, campo1 = _campo1, campo2 = _campo2, last_modified_by = _last_modified_by, last_modified_date = _last_modified_date
			WHERE id = _id;
        END IF;

	-- COMMIT;
    
	RETURN _id_layout_header;

END MAIN;
$$
DELIMITER ;