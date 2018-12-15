CREATE TABLE `cat_catalogo` (
  `id` smallint(6) NOT NULL,
  `descripcion_corta` varchar(20) DEFAULT NULL,
  `descripcion` varchar(50) DEFAULT NULL,
  `nombre_tabla` varchar(30) NOT NULL,
  `activo` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE `catalogo_afiliacion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=UTF8;


CREATE TABLE `cliente` (
  `idCliente` int(11) NOT NULL,
  `nombreTitular` varchar(100) NOT NULL,
  `fecha_alta` datetime NOT NULL,
  PRIMARY KEY (`idCliente`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;


CREATE TABLE `estatus_tarjeta_c` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion_corta` varchar(45) DEFAULT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=UTF8;


CREATE TABLE `estatus_transaccion_c` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion_corta` varchar(45) DEFAULT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=UTF8;


CREATE TABLE `tipo_tarjeta_c` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion_corta` varchar(45) DEFAULT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=UTF8;


CREATE TABLE `regla_negocio` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `descripcion` varchar(100) NOT NULL,
  `consulta` varchar(500) NOT NULL,
  `id_afiliacion` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  --INDEX (`id_afiliacion`),
  CONSTRAINT `ca_fk` FOREIGN KEY (`id_afiliacion`) REFERENCES `catalogo_afiliacion` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=UTF8;


CREATE TABLE `cliente_regla_negocio` (
  `id_cliente` int(11) NOT NULL,
  `id_regla_negocio` int(11) NOT NULL,
  --INDEX (`id_cliente`),
  --INDEX (`id_regla_negocio`),
  CONSTRAINT `ic_fk` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`idCliente`),
  CONSTRAINT `irn_fk` FOREIGN KEY (`id_regla_negocio`) REFERENCES `regla_negocio` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;


CREATE TABLE `pagos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idcliente` int(11) DEFAULT NULL,
  `fecha_transaccion` datetime DEFAULT NULL,
  `monto` double DEFAULT NULL,
  `autorizacion` varchar(100) DEFAULT NULL,
  `metodo` varchar(45) DEFAULT NULL,
  `tarjeta` varchar(4) DEFAULT NULL,
  `idopenpay` varchar(100) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  `idorder` varchar(100) DEFAULT NULL,
  `estatus_transaccion` int(11) DEFAULT NULL,
  `restresponse` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`id`),
  --INDEX (`estatus_transaccion`),
  --INDEX (`idcliente`),
  CONSTRAINT `cliente_transacion_fk` FOREIGN KEY (`idcliente`) REFERENCES `cliente` (`idCliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `esatus_transaccion_fk` FOREIGN KEY (`estatus_transaccion`) REFERENCES `estatus_transaccion_c` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=UTF8;


CREATE TABLE `tarjetas` (
  `token` varchar(50) NOT NULL,
  `ultimos_digitos` varchar(4) DEFAULT NULL,
  `alias` varchar(100) DEFAULT NULL,
  `fecha_alta` datetime DEFAULT NULL,
  `fecha_modificacion` datetime DEFAULT NULL,
  `idCliente` int(11) NOT NULL,
  `tipo_tarjeta_c_id` int(11) NOT NULL,
  `estatus_tarjeta_c` int(11) NOT NULL,
  PRIMARY KEY (`token`),
  --INDEX (`tipo_tarjeta_c_id`),
  --INDEX (`estatus_tarjeta_c`),
  --INDEX (`idCliente`),
  CONSTRAINT `cliente_tarjeta_fk` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`idCliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `estatus_tarjeta_fk` FOREIGN KEY (`estatus_tarjeta_c`) REFERENCES `estatus_tarjeta_c` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tipo_tarjeta_tarjeta_fk` FOREIGN KEY (`tipo_tarjeta_c_id`) REFERENCES `tipo_tarjeta_c` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;
