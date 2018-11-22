-- Inicio juste a la tabla de Cliente
ALTER TABLE `tarjetas` 
DROP FOREIGN KEY `cliente_tarjeta_fk`;
ALTER TABLE `tarjetas` 
DROP INDEX `cliente_tarjeta_fk_idx` ;

ALTER TABLE `transacciones` 
DROP FOREIGN KEY `cliente_transaccion_fk`;
ALTER TABLE `transacciones` 
DROP INDEX `cliente_transaccion_fk_idx` ;

ALTER TABLE `cliente` 
DROP COLUMN `app_materno`,
DROP COLUMN `app_paterno`,
CHANGE COLUMN `idcliente` `idCliente` INT(11) NOT NULL ,
CHANGE COLUMN `nombre` `nombreTitular` VARCHAR(100) NOT NULL ;

ALTER TABLE `tarjetas` 
CHANGE COLUMN `idcliente` `idCliente` INT(11) NOT NULL ;

ALTER TABLE `tarjetas` 
ADD INDEX `cliente_tarjeta_fk_idx` (`idCliente` ASC);
ALTER TABLE `tarjetas` 
ADD CONSTRAINT `cliente_tarjeta_fk`
  FOREIGN KEY (`idCliente`)
  REFERENCES `cliente` (`idCliente`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
ALTER TABLE `transacciones` 
ADD INDEX `cliente_transacion_fk_idx` (`idcliente` ASC);
ALTER TABLE `transacciones` 
ADD CONSTRAINT `cliente_transacion_fk`
  FOREIGN KEY (`idcliente`)
  REFERENCES `cliente` (`idCliente`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
 
-- Fin juste a la tabla de Cliente