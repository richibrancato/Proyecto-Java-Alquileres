-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: todo_alquileres
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `alquileres`
--

DROP TABLE IF EXISTS `alquileres`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alquileres` (
  `idAlquileres` int NOT NULL,
  `idPersona` int NOT NULL,
  `idTipoCategoria` int NOT NULL,
  `idCosaAlquilada` int NOT NULL,
  `fechaInicio` date DEFAULT NULL,
  `fechaFin` date DEFAULT NULL,
  `estado` tinyint DEFAULT NULL,
  PRIMARY KEY (`idAlquileres`),
  KEY `idPersonas_idx` (`idPersona`),
  KEY `idTipoCategoria_idx` (`idTipoCategoria`),
  CONSTRAINT `FK_Alquileres_Categorias_idTipoCategoria` FOREIGN KEY (`idTipoCategoria`) REFERENCES `categorias` (`idTipoCategoria`),
  CONSTRAINT `FK_Alquileres_Personas_idPersonas` FOREIGN KEY (`idPersona`) REFERENCES `personas` (`idpersonas`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `autos`
--

DROP TABLE IF EXISTS `autos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `autos` (
  `idAuto` int NOT NULL AUTO_INCREMENT,
  `idVehiculos` int NOT NULL COMMENT 'clave externa',
  `padron` int NOT NULL,
  `patente` varchar(10) NOT NULL,
  `marca` varchar(20) DEFAULT NULL,
  `modelo` varchar(20) DEFAULT NULL,
  `kilometros` int DEFAULT NULL,
  `cantidadAsientos` smallint DEFAULT NULL,
  PRIMARY KEY (`idAuto`),
  UNIQUE KEY `patente_UNIQUE` (`patente`),
  KEY `TipoCategoria_idx` (`idVehiculos`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `casas`
--

DROP TABLE IF EXISTS `casas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `casas` (
  `idCasas` int NOT NULL AUTO_INCREMENT,
  `idTipoCategoria` int NOT NULL,
  `padron` int NOT NULL,
  `direccion` varchar(60) DEFAULT NULL,
  `parrillero` tinyint DEFAULT NULL,
  `piscina` tinyint DEFAULT NULL,
  `wifi` tinyint DEFAULT NULL,
  `aireAcondicionado` tinyint DEFAULT NULL,
  `cantidadHabitaciones` smallint DEFAULT NULL,
  PRIMARY KEY (`idCasas`),
  UNIQUE KEY `padron_UNIQUE` (`padron`),
  KEY `idTipoCategoria_idx` (`idTipoCategoria`),
  CONSTRAINT `FK_Casas_Categorias_idTipoCategoria` FOREIGN KEY (`idTipoCategoria`) REFERENCES `categorias` (`idTipoCategoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorias` (
  `idTipoCategoria` int NOT NULL,
  `descripcion` varchar(45) NOT NULL,
  `localizacion` varchar(100) DEFAULT NULL,
  `contacto` varchar(100) DEFAULT NULL,
  `precio` decimal(10,0) DEFAULT NULL,
  `disponibilidad` tinyint DEFAULT NULL,
  PRIMARY KEY (`idTipoCategoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `parlantes`
--

DROP TABLE IF EXISTS `parlantes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parlantes` (
  `idparlantes` int NOT NULL AUTO_INCREMENT,
  `idTipoCategoria` int NOT NULL,
  `potenciaRms` int DEFAULT NULL,
  `marca` varchar(60) DEFAULT NULL,
  `bluetooth` tinyint DEFAULT NULL,
  PRIMARY KEY (`idparlantes`),
  KEY `idTipoCategoria_idx` (`idTipoCategoria`),
  CONSTRAINT `FK_Parlantes_Categorias_idTipoCategoria` FOREIGN KEY (`idTipoCategoria`) REFERENCES `categorias` (`idTipoCategoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `personas`
--

DROP TABLE IF EXISTS `personas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personas` (
  `idpersonas` int NOT NULL AUTO_INCREMENT,
  `tipodocumento` varchar(20) NOT NULL,
  `nrodocumento` varchar(20) NOT NULL,
  `apellido1` varchar(45) DEFAULT NULL,
  `apellido2` varchar(45) DEFAULT NULL,
  `nombre1` varchar(45) DEFAULT NULL,
  `nombre2` varchar(45) DEFAULT NULL,
  `calle` varchar(60) DEFAULT NULL,
  `nropuerta` varchar(60) DEFAULT NULL,
  `apto` varchar(60) DEFAULT NULL,
  `departamento` varchar(60) DEFAULT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `celular` varchar(15) DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL,
  `nomusuario` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idpersonas`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `idUsuario` int NOT NULL AUTO_INCREMENT,
  `nomUsuario` varchar(15) NOT NULL,
  `clave` varchar(30) NOT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `vehiculos`
--

DROP TABLE IF EXISTS `vehiculos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehiculos` (
  `idVehiculos` int NOT NULL AUTO_INCREMENT,
  `idTipoCategoria` int DEFAULT NULL COMMENT 'clave externa',
  `padron` varchar(10) NOT NULL,
  `patente` varchar(10) NOT NULL,
  `marca` varchar(20) DEFAULT NULL,
  `modelo` varchar(20) DEFAULT NULL,
  `kilometros` varchar(10) DEFAULT NULL,
  `cantidadAsientos` varchar(10) DEFAULT NULL,
  `precio` varchar(10) DEFAULT NULL,
  `disponibilidad` varchar(15) DEFAULT NULL,
  `fecha` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idVehiculos`),
  UNIQUE KEY `patente_UNIQUE` (`patente`),
  KEY `TipoCategoria_idx` (`idTipoCategoria`),
  CONSTRAINT `FK_Autos_Categorias_idTipoCategoria` FOREIGN KEY (`idTipoCategoria`) REFERENCES `categorias` (`idTipoCategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'todo_alquileres'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-24 12:31:02
