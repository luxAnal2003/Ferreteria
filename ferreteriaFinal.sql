CREATE DATABASE IF NOT EXISTS db_ferreteria;
USE db_ferreteria;
select * from producto;

CREATE TABLE rol (
    idRol INT PRIMARY KEY AUTO_INCREMENT,
    tipo VARCHAR(20) NOT NULL,
    estado INT(1) NOT NULL
);

CREATE TABLE Usuario (
    idUsuario INT PRIMARY KEY AUTO_INCREMENT,
    idRol INT NOT NULL,
    nombre VARCHAR(30) NOT NULL,
    apellido VARCHAR(30) NOT NULL,
    usuario VARCHAR(50) NOT NULL,
    contrasenia VARCHAR(50) NOT NULL,
    telefono VARCHAR(15) NOT NULL,
    correo VARCHAR(50) NOT NULL,
    estado INT(1) NOT NULL,
    FOREIGN KEY (idRol) REFERENCES rol(idRol)
);

CREATE TABLE Cliente (
    idCliente INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(30) NOT NULL,
    apellido VARCHAR(30) NOT NULL,
    telefono VARCHAR(15) NOT NULL,
    correo VARCHAR(50) NOT NULL,
    cedula VARCHAR(10) NOT NULL,
    direccion VARCHAR(100) NOT NULL,
    estado INT(1) NOT NULL
);

CREATE TABLE Empleado (
    idEmpleado INT PRIMARY KEY AUTO_INCREMENT,
    idRol INT NOT NULL,
    cedula VARCHAR(10) NOT NULL,
    direccion VARCHAR(100) NOT NULL,
    estado INT(1) NOT NULL,
    idUsuario INT NOT NULL,
    FOREIGN KEY (idRol) REFERENCES rol(idRol),
    FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario)
);


CREATE TABLE Categoria (
    idCategoria INT PRIMARY KEY AUTO_INCREMENT,
    descripcionCategoria VARCHAR(200) NOT NULL,
    estado INT(1) NOT NULL
);


CREATE TABLE Proveedor (
    idProveedor INT PRIMARY KEY AUTO_INCREMENT,
    ruc VARCHAR(13) NOT NULL,
    nombreProveedor VARCHAR(30) NOT NULL,
    telefonoProveedor VARCHAR(15) NOT NULL,
    direccionProveedor VARCHAR(200) NOT NULL,
    correoProveedor VARCHAR(50) NOT NULL,
    estado INT(1) NOT NULL
);

CREATE TABLE Producto (
    idProducto INT PRIMARY KEY AUTO_INCREMENT,
    idProveedor INT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    cantidad INT NOT NULL,
    descripcion VARCHAR(200) NOT NULL,
    precio DOUBLE(10,2) NOT NULL,
    iva INT(2) NOT NULL,
    idCategoria INT NOT NULL,
    estado INT(1) NOT NULL,
    FOREIGN KEY (idProveedor) REFERENCES Proveedor(idProveedor),
    FOREIGN KEY (idCategoria) REFERENCES Categoria(idCategoria)
);

CREATE TABLE CabeceraVenta (
    idCabeceraVenta INT PRIMARY KEY AUTO_INCREMENT,
    idCliente INT NOT NULL,
    idEmpleado INT NOT NULL,
    total DOUBLE(10,2) NOT NULL,
    fechaVenta DATE NOT NULL,
    estado INT(1) NOT NULL,
    FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente),
    FOREIGN KEY (idEmpleado) REFERENCES Empleado(idEmpleado)
);

CREATE TABLE DetalleVenta (
    idDetalleVenta INT PRIMARY KEY AUTO_INCREMENT,
    idCabeceraVenta INT NOT NULL,
    idProducto INT NOT NULL,
    cantidad INT NOT NULL,
    precioUnitario DOUBLE(10,2) NOT NULL,
    subTotal DOUBLE(10,2) NOT NULL,
    descuento DOUBLE(10,2) NOT NULL,
    iva DOUBLE(10,2) NOT NULL,
    totalPagar DOUBLE(10,2) NOT NULL,
    estado INT(1) NOT NULL,
    FOREIGN KEY (idCabeceraVenta) REFERENCES CabeceraVenta(idCabeceraVenta),
    FOREIGN KEY (idProducto) REFERENCES Producto(idProducto)
);

INSERT INTO rol (tipo, estado) VALUES
('Administrador', 1),
('Empleado', 1);
select * from rol;

-- Tabla Usuario
INSERT INTO Usuario (idRol, nombre, apellido, usuario, contrasenia, telefono, correo, estado) VALUES
(2, 'Consumidor final', 'Tools', 'consumidorFinal', 'empFinal', "1234567891", 'consumidor@tool.com', 1),
(2, 'Sebastián', 'Ramírez', 'sebastian.r', 'emp123', '0998765432', 'sebas.r@tool.com', 1),
(1, 'Carlos', 'Perez', 'carlos.p', 'admin123', '0994885736', 'carlos.p@empresa.com', 1),
(2, 'Eliud', 'Robalino', 'ana.g', 'emp123', '0987654321', 'eliud.r@tool.com', 1),
(2, 'Luccy', 'Sánchez', 'luccy.s', 'emp123', '0995015940', 'luccy.s@tool.com', 1),
(2, 'Miguel', 'Espinoza', 'miguel.e', 'emp123', '0925367481', 'miguel.e@tool.com', 1),
(2, 'Bianca', 'Alvarado', 'bianca.a', 'emp123', '0909673256', 'bianca.a@tool.com', 1);
select * from Usuario;

INSERT INTO Empleado ( idRol, cedula, direccion, estado, idUsuario
) VALUES
(2, '0973856193', 'Av. Principal 100', 1, 2),
(2, '0946728355','Calle Secundaria 200', 1, 4),
(2, '0943399477', 'Portete y la 23', 1, 5),
(2, '0909054439', 'Samborondon', 1, 6),
(2, '0351847398', 'Av. Principal 100', 1, 7);
select *  from empleado;


select * from cliente;
INSERT INTO Cliente (nombre, apellido, telefono, correo, cedula, direccion, estado) VALUES
('Consumidor', 'Final', '0999999999', 'consumidor.final@tool.com', '9999999999', 'N/A', 1),
('Walter', 'Caicedo', '0983627461', 'walter.ca@tool.com', '0102030405', 'Av. Central 123', 1),
('Leandro', 'Correa', '0995015940', 'leandro.co@tool.com', '0102030406', 'Calle Falsa 456', 1);

-- Tabla Categoria
select * from categoria;
INSERT INTO Categoria (descripcionCategoria, estado) VALUES
('Herramientas Manuales', 1),
('Material Eléctrico', 1),
('Pinturas', 1);


select * from proveedor;
INSERT INTO Proveedor  (ruc, nombreProveedor, telefonoProveedor, direccionProveedor, correoProveedor, estado) VALUES
('0998765432001','FerreProve S.A.', '0946758293', 'Av. Venezuela 100', 'contacto@ferreprove.com',  1),
('0987654321001', 'Suministros Eléctricos',  '0945648285', 'Calle colombia y la ch 200', 'ventas@suministroselectricos.com', 1);

select * from producto;
INSERT INTO Producto (idProveedor, nombre, cantidad, descripcion, precio, iva, idCategoria, estado) VALUES
(1, 'Martillo', 50, 'Martillo de acero 16oz', 12.50, 12, 1, 1),
(1, 'Destornillador', 80, 'Destornillador plano 6mm', 5.75, 12, 1, 1),
(2, 'Cable eléctrico 10m', 100, 'Cable eléctrico tipo THHN', 20.00, 12, 2, 1);

select * from cabeceraVenta;
INSERT INTO CabeceraVenta (idCliente, idEmpleado, total, fechaVenta, estado) VALUES
(1, 1, 75.00, '2025-06-10', 1),
(2, 2, 45.75, '2025-06-11', 1);

-- Tabla DetalleVenta
select * from detalleVenta;
INSERT INTO DetalleVenta (idCabeceraVenta, idProducto, cantidad, precioUnitario, subTotal, descuento, iva, totalPagar, estado) VALUES
(1, 1, 3, 12.50, 37.50, 0, 4.50, 42.00, 1),
(1, 2, 5, 5.75, 28.75, 0, 3.45, 32.20, 1),
(2, 3, 2, 20.00, 40.00, 0, 4.80, 44.80, 1);
