-- DROP DATABASE Gamers_Planet;

CREATE DATABASE Gamers_Planet;
USE Gamers_Planet;
CREATE TABLE usuario(
idUsuario int auto_increment not null PRIMARY KEY,
nombre varchar(50) not null,
apellidos varchar(50) not null,
telefono varchar(50) not null,
correo varchar(35) not null,
contrasenia varchar(40) not null,
direccion varchar(100) not null,
statusRol tinyint not null,/* usuario - administrador*/
statusUsr bit(1) not null, /*alta - baja*/
idUsrAlta int not null,
fechaAlta timestamp DEFAULT current_timestamp, /*http://cambrico.net/mysql/como-insertar-automaticamente-la-fecha-y-hora-en-mysql*/
idUsrMod int not null,
fechaMod timestamp DEFAULT current_timestamp
);

CREATE TABLE cliente(/*no puse idUsuario, checar*/
idCliente int auto_increment not null PRIMARY KEY,
nombre varchar(50) not null,
apellidos varchar(50) not null,
telefono varchar(50) not null,
correo varchar(35) not null,
contrasenia varchar(40) not null,
direccion varchar(100) not null,
statusCliente bit(1) not null, /*alta - baja*/
idMovimiento int not null,
idUsrAlta int not null,
fechaAlta timestamp DEFAULT current_timestamp,
idUsrMod int not null,
fechaMod timestamp DEFAULT current_timestamp
);

CREATE TABLE producto(
idProducto int auto_increment not null PRIMARY KEY,
nombre varchar(50) not null,
descripcion varchar(200) not null,
unidad varchar(30) not null,
existencia int not null,
idArea int not null,
statusProd bit(1) not null,
idUsrAlta int not null,
fechaAlta timestamp DEFAULT current_timestamp,
idUsrMod int not null,
fechaMod timestamp DEFAULT current_timestamp,
precio float(7,2)
);

CREATE TABLE movimientoProducto(
idMovimientoProducto int auto_increment not null PRIMARY KEY,
idProducto int not null,
idMovimiento int not null,
unidades int not null
);

CREATE TABLE movimientoP(
idMovimientoProducto int auto_increment not null PRIMARY KEY,
idUsuario int not null,
tipoMovimiento varchar(100) not null,
fechaMov timestamp DEFAULT current_timestamp
);
drop  table movimientoP;

CREATE TABLE movimiento(
idMovimiento int auto_increment not null PRIMARY KEY,
idCliente int not null,
tipoMovimiento varchar(30) not null,
fechaMov timestamp DEFAULT current_timestamp
);

CREATE TABLE detalleMovimiento(
idDetMov int auto_increment not null PRIMARY KEY,
idMovimiento int not null,
origen varchar(75) not null,
destino varchar(75) not null
);

CREATE TABLE areaAlmacen(
idArea int auto_increment not null PRIMARY KEY,
nombre varchar (100) not null,
idUsrAlta int not null,
fechaAlta timestamp DEFAULT current_timestamp,
idUsrMod int not null,
fechaMod timestamp DEFAULT current_timestamp
);

/********************LLAVES FORÁNEAS**********************/
/*Tabla usuarios
ALTER TABLE usuario
ADD FOREIGN KEY (idUsrAlta) References usuario(idUsuario)
ON DELETE CASCADE ON UPDATE CASCADE; 

ALTER TABLE usuario
ADD FOREIGN KEY (idUsrMod) References usuario(idUsuario)
ON DELETE CASCADE ON UPDATE CASCADE;

/*Tabla clientes*/
ALTER TABLE cliente
ADD FOREIGN KEY (idUsrAlta) References usuario(idUsuario)
ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE cliente
ADD FOREIGN KEY (idUsrMod) References usuario(idUsuario)
ON DELETE CASCADE ON UPDATE CASCADE;

/*ALTER TABLE cliente
ADD FOREIGN KEY (idMovimiento) REFERENCES movimiento(idMovimiento)
ON DELETE CASCADE ON UPDATE CASCADE;/*tal vez está mal esta proque es  1 a n y solo va en movimientos*/
/*Tabla movimientos*/
ALTER TABLE movimiento
ADD FOREIGN KEY (idCliente) REFERENCES cliente(idCliente)
ON DELETE CASCADE ON UPDATE CASCADE;
/*Tabla detalle movimiento*/
ALTER TABLE detallemovimiento
ADD FOREIGN KEY (idMovimiento) References movimiento(idMovimiento)
ON DELETE CASCADE ON UPDATE CASCADE;

/*Tabla movimiento producto*/
ALTER TABLE movimientoProducto
ADD FOREIGN KEY (idMovimiento) REFERENCES movimiento(idMovimiento)
ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE movimientoProducto
ADD FOREIGN KEY (idProducto) REFERENCES producto(idProducto)
ON DELETE CASCADE ON UPDATE CASCADE;

/*Tabla producto*/
ALTER TABLE producto
ADD FOREIGN KEY (idArea) REFERENCES areaAlmacen(idArea)
ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE producto
ADD FOREIGN KEY (idUsrAlta) References usuario(idUsuario)
ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE producto
ADD FOREIGN KEY (idUsrMod) References usuario(idUsuario)
ON DELETE CASCADE ON UPDATE CASCADE;
/*Tabla area almacen*/
ALTER TABLE areaAlmacen
ADD FOREIGN KEY (idUsrAlta) References usuario(idUsuario)
ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE areaAlmacen
ADD FOREIGN KEY (idUsrMod) References usuario(idUsuario)
ON DELETE CASCADE ON UPDATE CASCADE;
/*Insert to users*/
INSERT INTO usuario VALUES (1, 'Administrador', 'Prueba', '4774091956', 'administrador@hotmail.com', 'administrador', 'Direccion prueba',0 /*Rol*/,1 /*Estatus*/, 0, NOW(),0,NOW());
INSERT INTO usuario VALUES (2, 'Empleado', 'Prueba', '4771234567', 'empleado@hotmail.com', 'empleado', 'Direccion prueba',1 /*Rol*/,1 /*Estatus*/, 1, NOW(),0,NOW());
INSERT INTO usuario VALUES (3, 'Cliente', 'Prueba', '4771234567', 'cliente@hotmail.com', 'cliente', 'Direccion prueba',2 /*Rol*/,1 /*Estatus*/, 1, NOW(),0,NOW());

/*Insert ti storage area*/
INSERT INTO areaAlmacen (nombre,idUsrAlta,fechaAlta,idUsrMod,fechaMod)VALUES('Electronica',1,NOW(),1,NOW());

/*Insert to products*/
INSERT INTO producto(nombre,descripcion,unidad,existencia,idArea,statusProd,idUsrAlta,fechaAlta,idUsrMod,fechaMod,precio)
VALUES ('XBOX 360','XBOX 360 con disco duro de 1 TB con 2 juegos ademas de dos palancas','1 pz',10,1,1,1,NOW(),1,NOW(),3000);

INSERT INTO producto(nombre,descripcion,unidad,existencia,idArea,statusProd,idUsrAlta,fechaAlta,idUsrMod,fechaMod,precio)
VALUES ('XBOX ONE','XBOX ONE con disco duro de 1 TB con 2 juegos ademas de dos palancas','1 pz',10,1,1,1,NOW(),1,NOW(),7000);

INSERT INTO producto(nombre,descripcion,unidad,existencia,idArea,statusProd,idUsrAlta,fechaAlta,idUsrMod,fechaMod,precio)
VALUES ('PLAY STATION 4','PLAY STATION con disco duro de 1 TB con 2 juegos ademas de dos palancas','1 pz',10,1,1,1,NOW(),1,NOW(),7000);

INSERT INTO producto(nombre,descripcion,unidad,existencia,idArea,statusProd,idUsrAlta,fechaAlta,idUsrMod,fechaMod,precio)
VALUES ('Audifonos BEAT','Audifonos BEAT BY DOCTOR DREE hard sound','1 pz',10,1,1,1,NOW(),1,NOW(),1000);

SELECT * FROM usuario;
select * from producto;
select * from areaAlmacen;

select * from movimientoP