CREATE TABLE `tipodocumento` (
  `idtipoDocumento` int NOT NULL,
  `codigo` varchar(10) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`idtipoDocumento`)
);

CREATE TABLE `cliente` (
  `idCliente` int NOT NULL AUTO_INCREMENT,
  `primerNombre` varchar(20) NOT NULL,
  `segundoNombre` varchar(20) DEFAULT NULL,
  `primerApellido` varchar(20) NOT NULL,
  `segundoApellido` varchar(20) DEFAULT NULL,
  `documento` varchar(20) NOT NULL,
  `tipoDocumento` int NOT NULL,
  `correo` varchar(45) NOT NULL,
  `celular` varchar(20) NOT NULL,
  `direccion` varchar(45) NOT NULL,
  PRIMARY KEY (`idCliente`),
  KEY `idIndex` (`idCliente`),
  KEY `tipoDocumentoFk_idx` (`tipoDocumento`),
  CONSTRAINT `tipoDocumentoFk` FOREIGN KEY (`tipoDocumento`) REFERENCES `tipodocumento` (`idtipoDocumento`)
);

CREATE TABLE `tienda` (
  `idtienda` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `tipoDocumento` int NOT NULL,
  `documento` varchar(45) NOT NULL,
  PRIMARY KEY (`idtienda`),
  KEY `tipoDocFK_idx` (`tipoDocumento`),
  CONSTRAINT `tipoDocFK` FOREIGN KEY (`tipoDocumento`) REFERENCES `tipodocumento` (`idtipoDocumento`)
);

CREATE TABLE `empleado` (
  `idempleado` int NOT NULL AUTO_INCREMENT,
  `primerNombre` varchar(45) NOT NULL,
  `segundoNombre` varchar(45) DEFAULT NULL,
  `primerApellido` varchar(45) NOT NULL,
  `segundoApellido` varchar(45) DEFAULT NULL,
  `tipoDocumento` int NOT NULL,
  `documento` varchar(45) NOT NULL,
  `correo` varchar(45) NOT NULL,
  `celular` varchar(45) NOT NULL,
  `direccion` varchar(45) NOT NULL,
  `idTienda` int DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idempleado`),
  KEY `idTiendaFk_idx` (`idTienda`),
  KEY `tipoDocFk_idx` (`tipoDocumento`),
  CONSTRAINT `idTiendaFk` FOREIGN KEY (`idTienda`) REFERENCES `tienda` (`idtienda`),
  CONSTRAINT `idTipoDocFk` FOREIGN KEY (`tipoDocumento`) REFERENCES `tipodocumento` (`idtipoDocumento`)
);

CREATE TABLE `articulo` (
  `idArticulos` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `codigo` varchar(45) NOT NULL,
  `cantidad` varchar(45) NOT NULL,
  `precioUnitario` decimal(20,2) NOT NULL,
  `idTienda` int NOT NULL,
  `tipoArticulo` varchar(45) NOT NULL,
  PRIMARY KEY (`idArticulos`),
  KEY `idTiendaFk_idx` (`idTienda`),
  CONSTRAINT `tiendaIdFk` FOREIGN KEY (`idTienda`) REFERENCES `tienda` (`idtienda`)
);

CREATE TABLE `mantenimiento` (
  `idMantenimiento` int NOT NULL AUTO_INCREMENT,
  `descripcion` longtext NOT NULL,
  `estado` varchar(20) NOT NULL,
  `idCliente` int NOT NULL,
  `idMecanico` int NOT NULL,
  `estado2` varchar(20) DEFAULT NULL,
  `limitePresupuesto` int DEFAULT NULL,
  `fechaInicio` datetime DEFAULT NULL,
  `fechaFin` datetime DEFAULT NULL,
  PRIMARY KEY (`idMantenimiento`),
  KEY `idClienteFk_idx` (`idCliente`),
  KEY `idMecanicoFk_idx` (`idMecanico`),
  CONSTRAINT `idClienteFk` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`idCliente`),
  CONSTRAINT `idMecanicoFk` FOREIGN KEY (`idMecanico`) REFERENCES `empleado` (`idempleado`)
);

CREATE TABLE `detallemantenimiento` (
  `iddetalleMantenimiento` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) DEFAULT NULL,
  `idArticulo` int NOT NULL,
  `cantidad` int NOT NULL,
  `idMantenimiento` int NOT NULL,
  PRIMARY KEY (`iddetalleMantenimiento`),
  KEY `articuloIdFk_idx` (`idArticulo`),
  KEY `mantenimientoIdFk_idx` (`idMantenimiento`),
  CONSTRAINT `articuloIdFk` FOREIGN KEY (`idArticulo`) REFERENCES `articulo` (`idArticulos`),
  CONSTRAINT `mantenimientoIdFk` FOREIGN KEY (`idMantenimiento`) REFERENCES `mantenimiento` (`idMantenimiento`)
);