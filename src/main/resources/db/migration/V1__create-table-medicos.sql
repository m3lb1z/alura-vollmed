CREATE TABLE `medicos` (
   `id` bigint NOT NULL AUTO_INCREMENT,
   `calle` varchar(255),
   `ciudad` varchar(255),
   `complemento` varchar(255),
   `distrito` varchar(255),
   `numero` int,
   `documento` varchar(255) NOT NULL UNIQUE,
   `email` varchar(255) NOT NULL UNIQUE,
   `especialidad` VARCHAR(100) NOT NULL,
   `nombre` varchar(255) NOT NULL,
   PRIMARY KEY (`id`)
)
