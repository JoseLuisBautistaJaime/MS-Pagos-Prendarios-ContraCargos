-- ------------------------------------------------------------------------------------------------------------
-- SCRIPT ACTUALIZACION - [2019-09-12] ------------------------------------------------------------------------
-- ------------------------------------------------------------------------------------------------------------


-- -----------------------------------------------------
-- Table `to_movimiento_proveedor`
-- -----------------------------------------------------
ALTER TABLE `to_movimiento_proveedor` MODIFY `customer_id` VARCHAR(50) DEFAULT NULL;

-- Secuencia para los folios de conciliacion -------

CREATE TABLE `seq_conciliacion` (
  `seq_name` VARCHAR(45) NOT NULL,
  `seq_value` BIGINT(20) DEFAULT '0',
  PRIMARY KEY (`seq_name`)
) ENGINE=InnoDB DEFAULT CHARACTER SET = latin1;
