CREATE DATABASE  IF NOT EXISTS `hotel_alura` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `hotel_alura`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: hotel_alura
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `acceso_empleados`
--

DROP TABLE IF EXISTS `acceso_empleados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `acceso_empleados` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre_de_usuario` varchar(50) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `rol` varchar(50) NOT NULL,
  `contrasena` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre_de_usuario` (`nombre_de_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acceso_empleados`
--

LOCK TABLES `acceso_empleados` WRITE;
/*!40000 ALTER TABLE `acceso_empleados` DISABLE KEYS */;
INSERT INTO `acceso_empleados` VALUES (1,'DMASS','DANIEL','MASS DOMINGUEZ','ADMINISTRADOR','Dm21elas!'),(2,'FARGUELLO','FERNANDO','ARGUELLO AGUEDA','RECEPCIONISTA','BigMike69');
/*!40000 ALTER TABLE `acceso_empleados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `huespedes`
--

DROP TABLE IF EXISTS `huespedes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `huespedes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `fecha_de_nacimiento` date DEFAULT NULL,
  `nacionalidad` varchar(50) DEFAULT NULL,
  `telefono` varchar(50) DEFAULT NULL,
  `reserva_actual` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `reserva_actual` (`reserva_actual`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `huespedes`
--

LOCK TABLES `huespedes` WRITE;
/*!40000 ALTER TABLE `huespedes` DISABLE KEYS */;
INSERT INTO `huespedes` VALUES (1,'Leopoldo','Bala Gordillo','1998-05-01','mexicano-mexicana','9611491283',1),(2,'Porfirio','Bajo Lopez','1998-11-14','mexicano-mexicana','9617083156',2),(3,'Rodrigo','Figueroa Arguello','1997-11-20','mexicano-mexicana','5617420822',7),(7,'Sergio','Mass Domínguez','1995-11-18','mexicano-mexicana','9611491283',7),(8,'Jose Luis','Tapia Melendez','1973-09-29','mexicano-mexicana','9632276349',9),(11,'Carlos Rogelio','Aguirre Muños','1996-06-04','japonés-japonesa','9636666666',10),(12,'Pedro','Perez Perez','2003-09-19','chileno-chilena','91456238',11),(13,'Liu','Kang','0001-01-25','japonés-japonesa','123123213123',11),(14,'Jhonny','Cage','1995-09-21','estadounidense-estadounidense','na',12),(15,'Dante','Sparda','1980-09-07','estadounidense-estadounidense','na',13),(16,'Vergil','Sparda','1980-09-08','estadounidense-estadounidense','na',14),(17,'Prueba1','Pruebita1','2023-09-19','afganes','9631111428',15),(18,'OtraPrueba','OtraPrueba1','2023-09-22','australiano-australiana','na',16);
/*!40000 ALTER TABLE `huespedes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `precios_reservas`
--

DROP TABLE IF EXISTS `precios_reservas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `precios_reservas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tipo_de_habitacion` varchar(255) DEFAULT NULL,
  `precio` decimal(10,2) DEFAULT NULL,
  `fecha_de_modificacion` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `precios_reservas`
--

LOCK TABLES `precios_reservas` WRITE;
/*!40000 ALTER TABLE `precios_reservas` DISABLE KEYS */;
INSERT INTO `precios_reservas` VALUES (1,'HABITACION ESTANDAR - 1 CAMA MATRIMONIAL',2500.00,'2023-09-19 02:06:53'),(2,'HABITACION ESTANDAR - 2 CAMAS INDIVIDUALES',2500.00,'2023-09-19 02:07:56');
/*!40000 ALTER TABLE `precios_reservas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservas`
--

DROP TABLE IF EXISTS `reservas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fecha_entrada` date NOT NULL,
  `fecha_salida` date NOT NULL,
  `valor` decimal(10,2) NOT NULL,
  `formato_de_pago` varchar(50) NOT NULL,
  `id_huesped` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_huesped` (`id_huesped`),
  CONSTRAINT `reservas_ibfk_1` FOREIGN KEY (`id_huesped`) REFERENCES `huespedes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservas`
--

LOCK TABLES `reservas` WRITE;
/*!40000 ALTER TABLE `reservas` DISABLE KEYS */;
INSERT INTO `reservas` VALUES (1,'2023-08-23','2023-08-29',5000.00,'Dinero en efectivo',1),(2,'2023-09-10','2023-09-15',11000.00,'Tarjeta de Débito',2),(4,'2023-09-19','2023-09-28',15000.00,'Dinero en efectivo',1),(7,'2023-10-10','2023-10-18',15000.00,'Dinero en efectivo',3),(8,'2023-09-20','2023-09-26',14875.00,'Tarjeta de Débito',7),(9,'2023-10-10','2023-10-30',44625.00,'Tarjeta de Crédito',8),(10,'2023-12-20','2023-12-31',25500.00,'Tarjeta de Crédito',11),(11,'2023-09-21','2023-09-27',14875.00,'Tarjeta de Crédito',12),(12,'2023-09-21','2023-11-24',138125.00,'Dinero en efectivo',14),(13,'2024-09-28','2024-09-30',7500.00,'Dinero en efectivo',15),(14,'2023-09-21','2023-12-26',206125.00,'Tarjeta de Débito',16),(15,'2023-09-14','2023-09-28',31875.00,'Dinero en efectivo',17);
/*!40000 ALTER TABLE `reservas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'hotel_alura'
--

--
-- Dumping routines for database 'hotel_alura'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-22 15:41:16
