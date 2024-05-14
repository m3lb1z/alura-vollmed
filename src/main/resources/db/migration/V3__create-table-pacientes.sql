CREATE TABLE `pacientes` (
   `id` bigint NOT NULL AUTO_INCREMENT,
   `nombre` varchar(100) NOT NULL,
   `email` varchar(100) NOT NULL UNIQUE,
   `documento` varchar(14) NOT NULL UNIQUE,
   `telefono` varchar(20),
   `urbanizacion` varchar(100),
   `distrito` varchar(100),
   `codigoPostal` varchar(9),
   `complemento` varchar(100),
   `numero` int,
   `provincia` varchar(100),
   `ciudad` varchar(100),
   PRIMARY KEY (`id`)
)