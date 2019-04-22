USE compose;

-- INSERCION INICIAL DE TIPOS DE CONTACTOS --

INSERT INTO `compose`.`tk_tipo_contacto`
(`id`,
`estatus`,
`description`,
`created_by`,
`created_date`,
`last_modified_by`,
`last_modified_date`,
`short_description`)
VALUES
(1,
true,
'Contacto Midas',
'Ismael',
now(),
null,
null,
'Contacto Midas');

INSERT INTO `compose`.`tk_tipo_contacto`
(`id`,
`estatus`,
`description`,
`created_by`,
`created_date`,
`last_modified_by`,
`last_modified_date`,
`short_description`)
VALUES
(2,
true,
'Contacto Entidad',
'Ismael',
now(),
null,
null,
'Contacto Ent');

-- INSERCION DE TIPOS DE AFILIACION --
INSERT INTO `compose`.`tk_tipo_afiliacion`
(`id`,
`descripcion_corta`,
`descripcion`)
VALUES
(1,
'Ninguno',
'Ningun tipo de afiliacion'),
(2,
'3DSecure',
'3DSecure');

-- INSERCION DE CATEGORIAS DUMMIES --

INSERT INTO `compose`.`tk_categoria`
(`id`,
`nombre`,
`descripcion`,
`estatus`,
`created_date`,
`last_modified_date`,
`created_by`,
`last_modified_by`,
`descripcion_corta`)
VALUES
(1,
'Ventas',
'Categoria Ventas',
true,
now(),
null,
'Quarksoft',
null,
'Cat Vtas'),
(2,
'Comisiones',
'Categoria Comisiones',
true,
now(),
null,
'Quarksoft',
null,
'Cat Com'),
(3,
'Otra',
'Otra Categoria',
true,
now(),
null,
'Quarksoft',
null,
'Otra Cat');