CREATE DATABASE  IF NOT EXISTS `cineshaven` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `cineshaven`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: cineshaven
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `asiento`
--

DROP TABLE IF EXISTS `asiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `asiento` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fila` char(1) COLLATE utf8mb4_spanish_ci NOT NULL,
  `numero` int NOT NULL,
  `id_sala` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Asiento_Sala1_idx` (`id_sala`),
  CONSTRAINT `fk_Asiento_Sala1` FOREIGN KEY (`id_sala`) REFERENCES `sala` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=361 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asiento`
--

LOCK TABLES `asiento` WRITE;
/*!40000 ALTER TABLE `asiento` DISABLE KEYS */;
INSERT INTO `asiento` VALUES (1,'A',1,1),(2,'A',2,1),(3,'A',3,1),(4,'A',4,1),(5,'A',5,1),(6,'A',6,1),(7,'A',7,1),(8,'A',8,1),(9,'A',9,1),(10,'A',10,1),(11,'A',11,1),(12,'A',12,1),(13,'B',1,1),(14,'B',2,1),(15,'B',3,1),(16,'B',4,1),(17,'B',5,1),(18,'B',6,1),(19,'B',7,1),(20,'B',8,1),(21,'B',9,1),(22,'B',10,1),(23,'B',11,1),(24,'B',12,1),(25,'C',1,1),(26,'C',2,1),(27,'C',3,1),(28,'C',4,1),(29,'C',5,1),(30,'C',6,1),(31,'C',7,1),(32,'C',8,1),(33,'C',9,1),(34,'C',10,1),(35,'C',11,1),(36,'C',12,1),(37,'D',1,1),(38,'D',2,1),(39,'D',3,1),(40,'D',4,1),(41,'D',5,1),(42,'D',6,1),(43,'D',7,1),(44,'D',8,1),(45,'D',9,1),(46,'D',10,1),(47,'D',11,1),(48,'D',12,1),(49,'E',1,1),(50,'E',2,1),(51,'E',3,1),(52,'E',4,1),(53,'E',5,1),(54,'E',6,1),(55,'E',7,1),(56,'E',8,1),(57,'E',9,1),(58,'E',10,1),(59,'E',11,1),(60,'E',12,1),(61,'A',1,2),(62,'A',2,2),(63,'A',3,2),(64,'A',4,2),(65,'A',5,2),(66,'A',6,2),(67,'A',7,2),(68,'A',8,2),(69,'A',9,2),(70,'A',10,2),(71,'A',11,2),(72,'A',12,2),(73,'B',1,2),(74,'B',2,2),(75,'B',3,2),(76,'B',4,2),(77,'B',5,2),(78,'B',6,2),(79,'B',7,2),(80,'B',8,2),(81,'B',9,2),(82,'B',10,2),(83,'B',11,2),(84,'B',12,2),(85,'C',1,2),(86,'C',2,2),(87,'C',3,2),(88,'C',4,2),(89,'C',5,2),(90,'C',6,2),(91,'C',7,2),(92,'C',8,2),(93,'C',9,2),(94,'C',10,2),(95,'C',11,2),(96,'C',12,2),(97,'D',1,2),(98,'D',2,2),(99,'D',3,2),(100,'D',4,2),(101,'D',5,2),(102,'D',6,2),(103,'D',7,2),(104,'D',8,2),(105,'D',9,2),(106,'D',10,2),(107,'D',11,2),(108,'D',12,2),(109,'E',1,2),(110,'E',2,2),(111,'E',3,2),(112,'E',4,2),(113,'E',5,2),(114,'E',6,2),(115,'E',7,2),(116,'E',8,2),(117,'E',9,2),(118,'E',10,2),(119,'E',11,2),(120,'E',12,2),(121,'A',1,3),(122,'A',2,3),(123,'A',3,3),(124,'A',4,3),(125,'A',5,3),(126,'A',6,3),(127,'A',7,3),(128,'A',8,3),(129,'A',9,3),(130,'A',10,3),(131,'A',11,3),(132,'A',12,3),(133,'B',1,3),(134,'B',2,3),(135,'B',3,3),(136,'B',4,3),(137,'B',5,3),(138,'B',6,3),(139,'B',7,3),(140,'B',8,3),(141,'B',9,3),(142,'B',10,3),(143,'B',11,3),(144,'B',12,3),(145,'C',1,3),(146,'C',2,3),(147,'C',3,3),(148,'C',4,3),(149,'C',5,3),(150,'C',6,3),(151,'C',7,3),(152,'C',8,3),(153,'C',9,3),(154,'C',10,3),(155,'C',11,3),(156,'C',12,3),(157,'D',1,3),(158,'D',2,3),(159,'D',3,3),(160,'D',4,3),(161,'D',5,3),(162,'D',6,3),(163,'D',7,3),(164,'D',8,3),(165,'D',9,3),(166,'D',10,3),(167,'D',11,3),(168,'D',12,3),(169,'E',1,3),(170,'E',2,3),(171,'E',3,3),(172,'E',4,3),(173,'E',5,3),(174,'E',6,3),(175,'E',7,3),(176,'E',8,3),(177,'E',9,3),(178,'E',10,3),(179,'E',11,3),(180,'E',12,3);
/*!40000 ALTER TABLE `asiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comentario`
--

DROP TABLE IF EXISTS `comentario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comentario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_usuario` int NOT NULL,
  `id_pelicula` int NOT NULL,
  `texto` varchar(750) COLLATE utf8mb4_spanish_ci NOT NULL,
  `fecha` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Usuario_has_Pelicula_Pelicula1_idx` (`id_pelicula`),
  KEY `fk_Usuario_has_Pelicula_Usuario_idx` (`id_usuario`),
  CONSTRAINT `fk_Usuario_has_Pelicula_Pelicula1` FOREIGN KEY (`id_pelicula`) REFERENCES `pelicula` (`id`),
  CONSTRAINT `fk_Usuario_has_Pelicula_Usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comentario`
--

LOCK TABLES `comentario` WRITE;
/*!40000 ALTER TABLE `comentario` DISABLE KEYS */;
INSERT INTO `comentario` VALUES (1,1,2,'Lorem Ipsum','2023-05-09 21:00:28');
/*!40000 ALTER TABLE `comentario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pelicula`
--

DROP TABLE IF EXISTS `pelicula`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pelicula` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(200) COLLATE utf8mb4_spanish_ci NOT NULL,
  `director` varchar(100) COLLATE utf8mb4_spanish_ci NOT NULL,
  `duracion` int NOT NULL,
  `trailer` varchar(50) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `poster` varchar(255) COLLATE utf8mb4_spanish_ci NOT NULL,
  `captura` varchar(255) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `sinopsis` varchar(750) COLLATE utf8mb4_spanish_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pelicula`
--

LOCK TABLES `pelicula` WRITE;
/*!40000 ALTER TABLE `pelicula` DISABLE KEYS */;
INSERT INTO `pelicula` VALUES (1,'Pericles on 31st Street','Athene Rodrigo',1,'https://businesswire.com','http://dummyimage.com/222x100.png/ff4444/ffffff','http://dummyimage.com/138x100.png/5fa2dd/ffffff','vel augue vestibulum ante ipsum primis in faucibus orci luctus et ultrices'),(2,'Wild Bill','Edna Pantone',2,'https://mozilla.com','http://dummyimage.com/205x100.png/ff4444/ffffff','http://dummyimage.com/167x100.png/dddddd/000000','lectus in quam fringilla rhoncus mauris enim leo rhoncus sed'),(3,'Silver City','Silvain Orans',3,'https://free.fr','http://dummyimage.com/246x100.png/cc0000/ffffff','http://dummyimage.com/192x100.png/ff4444/ffffff','blandit non interdum in ante vestibulum ante ipsum primis in'),(4,'Enemy','Josias De Ruggiero',4,'http://biblegateway.com','http://dummyimage.com/190x100.png/dddddd/000000','http://dummyimage.com/245x100.png/cc0000/ffffff','mauris viverra diam vitae quam suspendisse potenti nullam porttitor lacus at turpis'),(5,'Woman in the Window, The','Briana Burgiss',5,'https://cbsnews.com','http://dummyimage.com/145x100.png/dddddd/000000','http://dummyimage.com/166x100.png/dddddd/000000','ridiculus mus etiam vel augue vestibulum rutrum rutrum neque aenean'),(6,'Lawrence of Arabia','Kelwin Mynett',6,'http://hostgator.com','http://dummyimage.com/138x100.png/cc0000/ffffff','http://dummyimage.com/240x100.png/5fa2dd/ffffff','sit amet cursus id turpis integer aliquet massa id lobortis convallis tortor risus dapibus augue vel'),(7,'Dante\'s Inferno','Yorgos Vigne',7,'https://spotify.com','http://dummyimage.com/105x100.png/cc0000/ffffff','http://dummyimage.com/149x100.png/cc0000/ffffff','ac neque duis bibendum morbi non quam nec dui luctus rutrum nulla tellus in sagittis'),(8,'My Lady Margarine (Die Austernprinzessin) (Oyster Princess, The)','Liuka Radin',8,'https://un.org','http://dummyimage.com/184x100.png/ff4444/ffffff','http://dummyimage.com/122x100.png/dddddd/000000','augue a suscipit nulla elit ac nulla sed vel enim'),(9,'Actress, the Dollars and the Transylvanians, The (Artista, dolarii si Ardelenii)','Janis Dybald',9,'https://i2i.jp','http://dummyimage.com/246x100.png/dddddd/000000','http://dummyimage.com/215x100.png/cc0000/ffffff','augue aliquam erat volutpat in congue etiam justo etiam pretium iaculis justo in'),(10,'Five Minarets in New York (Act of Vengeance) (Terrorist, The)','Gerti Lain',10,'http://linkedin.com','http://dummyimage.com/228x100.png/5fa2dd/ffffff','http://dummyimage.com/219x100.png/cc0000/ffffff','ultrices vel augue vestibulum ante ipsum primis in faucibus orci luctus et'),(11,'Beowulf','Hyacinthe Hiorn',11,'http://latimes.com','http://dummyimage.com/137x100.png/ff4444/ffffff','http://dummyimage.com/124x100.png/cc0000/ffffff','sapien quis libero nullam sit amet turpis elementum ligula vehicula consequat morbi a ipsum integer a nibh'),(12,'Bad Teacher','Rory Deerness',12,'https://storify.com','http://dummyimage.com/130x100.png/5fa2dd/ffffff','http://dummyimage.com/242x100.png/5fa2dd/ffffff','phasellus in felis donec semper sapien a libero nam dui proin leo odio porttitor id consequat in consequat ut nulla'),(13,'The Way He Looks','Elianore Duddell',13,'https://comsenz.com','http://dummyimage.com/147x100.png/cc0000/ffffff','http://dummyimage.com/223x100.png/5fa2dd/ffffff','vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae'),(14,'Great Flamarion, The','Sven Antonias',14,'http://bbb.org','http://dummyimage.com/189x100.png/ff4444/ffffff','http://dummyimage.com/137x100.png/ff4444/ffffff','platea dictumst etiam faucibus cursus urna ut tellus nulla ut erat id mauris vulputate'),(15,'Merlusse','Arabella Dunridge',15,'https://typepad.com','http://dummyimage.com/167x100.png/dddddd/000000','http://dummyimage.com/239x100.png/cc0000/ffffff','tellus nisi eu orci mauris lacinia sapien quis libero nullam sit amet turpis elementum ligula vehicula consequat morbi a ipsum'),(16,'Czech Dream (Ceský sen)','Orelee Stanluck',16,'https://flickr.com','http://dummyimage.com/132x100.png/cc0000/ffffff','http://dummyimage.com/125x100.png/5fa2dd/ffffff','consequat ut nulla sed accumsan felis ut at dolor quis odio consequat varius integer ac'),(17,'Happy Feet','Wang Fardon',17,'https://prlog.org','http://dummyimage.com/250x100.png/cc0000/ffffff','http://dummyimage.com/108x100.png/cc0000/ffffff','vivamus vestibulum sagittis sapien cum sociis natoque penatibus et magnis dis parturient montes nascetur ridiculus mus etiam vel augue'),(18,'Border, The','Tremain Heggadon',18,'https://mac.com','http://dummyimage.com/238x100.png/ff4444/ffffff','http://dummyimage.com/134x100.png/ff4444/ffffff','aliquam augue quam sollicitudin vitae consectetuer eget rutrum at lorem integer tincidunt'),(19,'Incredible Hulk Returns, The','Kin Durling',19,'https://zimbio.com','http://dummyimage.com/172x100.png/cc0000/ffffff','http://dummyimage.com/144x100.png/dddddd/000000','sed tincidunt eu felis fusce posuere felis sed lacus morbi sem mauris laoreet ut'),(20,'Sweet Bird of Youth','Wilbert Bevington',20,'http://google.es','http://dummyimage.com/123x100.png/dddddd/000000','http://dummyimage.com/201x100.png/ff4444/ffffff','nulla eget eros elementum pellentesque quisque porta volutpat erat quisque erat'),(21,'Oldboy','Park Chan-Wook',90,'www.google.com','http://localhost:8080/files/1683744087066_posterEjemplo.png','http://localhost:8080/files/1683744087092_capturaEjemplo.png','Busca venganza');
/*!40000 ALTER TABLE `pelicula` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserva`
--

DROP TABLE IF EXISTS `reserva`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reserva` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `id_usuario` int NOT NULL,
  `id_sesion` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Reserva_Usuario1_idx` (`id_usuario`),
  KEY `fk_Reserva_Sesion1_idx` (`id_sesion`),
  CONSTRAINT `fk_Reserva_Sesion1` FOREIGN KEY (`id_sesion`) REFERENCES `sesion` (`id`),
  CONSTRAINT `fk_Reserva_Usuario1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserva`
--

LOCK TABLES `reserva` WRITE;
/*!40000 ALTER TABLE `reserva` DISABLE KEYS */;
INSERT INTO `reserva` VALUES (1,'2023-05-09',1,1),(2,'2023-05-09',1,1),(3,'2023-05-10',1,1);
/*!40000 ALTER TABLE `reserva` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserva_tiene_asiento`
--

DROP TABLE IF EXISTS `reserva_tiene_asiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reserva_tiene_asiento` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_reserva` int NOT NULL,
  `id_asiento` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Reserva_has_Asiento_Asiento1_idx` (`id_asiento`),
  KEY `fk_Reserva_has_Asiento_Reserva1_idx` (`id_reserva`),
  CONSTRAINT `fk_Reserva_has_Asiento_Asiento1` FOREIGN KEY (`id_asiento`) REFERENCES `asiento` (`id`),
  CONSTRAINT `fk_Reserva_has_Asiento_Reserva1` FOREIGN KEY (`id_reserva`) REFERENCES `reserva` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserva_tiene_asiento`
--

LOCK TABLES `reserva_tiene_asiento` WRITE;
/*!40000 ALTER TABLE `reserva_tiene_asiento` DISABLE KEYS */;
INSERT INTO `reserva_tiene_asiento` VALUES (1,1,1),(2,1,2),(3,1,3),(4,2,1),(5,2,2),(6,2,3),(7,3,1),(8,3,2),(9,3,3);
/*!40000 ALTER TABLE `reserva_tiene_asiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sala`
--

DROP TABLE IF EXISTS `sala`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sala` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) COLLATE utf8mb4_spanish_ci NOT NULL,
  `num_asientos` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sala`
--

LOCK TABLES `sala` WRITE;
/*!40000 ALTER TABLE `sala` DISABLE KEYS */;
INSERT INTO `sala` VALUES (1,'Sala 1',390),(2,'Sala 2',390),(3,'Sala3',390);
/*!40000 ALTER TABLE `sala` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sesion`
--

DROP TABLE IF EXISTS `sesion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sesion` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_sala` int NOT NULL,
  `id_pelicula` int NOT NULL,
  `fecha` date NOT NULL,
  `horas` varchar(150) COLLATE utf8mb4_spanish_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Sala_has_Pelicula_Pelicula1_idx` (`id_pelicula`),
  KEY `fk_Sala_has_Pelicula_Sala1_idx` (`id_sala`),
  CONSTRAINT `fk_Sala_has_Pelicula_Pelicula1` FOREIGN KEY (`id_pelicula`) REFERENCES `pelicula` (`id`),
  CONSTRAINT `fk_Sala_has_Pelicula_Sala1` FOREIGN KEY (`id_sala`) REFERENCES `sala` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sesion`
--

LOCK TABLES `sesion` WRITE;
/*!40000 ALTER TABLE `sesion` DISABLE KEYS */;
INSERT INTO `sesion` VALUES (1,1,2,'2023-06-06','19:00,20:30');
/*!40000 ALTER TABLE `sesion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nick` varchar(30) COLLATE utf8mb4_spanish_ci NOT NULL,
  `email` varchar(85) COLLATE utf8mb4_spanish_ci NOT NULL,
  `clave` varchar(255) COLLATE utf8mb4_spanish_ci NOT NULL,
  `avatar` varchar(255) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `rol` enum('USER','ADMIN') COLLATE utf8mb4_spanish_ci NOT NULL DEFAULT 'USER',
  PRIMARY KEY (`id`),
  UNIQUE KEY `nick_UNIQUE` (`nick`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'root','admin@gmail.com','pestillo123',NULL,'ADMIN'),(2,'Prueba','prueba@prueba.com','pestillo123',NULL,'USER');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_ha_visto_pelicula`
--

DROP TABLE IF EXISTS `usuario_ha_visto_pelicula`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_ha_visto_pelicula` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_usuario` int NOT NULL,
  `id_pelicula` int NOT NULL,
  `fecha` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Usuario_has_Pelicula_Pelicula2_idx` (`id_pelicula`),
  KEY `fk_Usuario_has_Pelicula_Usuario1_idx` (`id_usuario`),
  CONSTRAINT `fk_Usuario_has_Pelicula_Pelicula2` FOREIGN KEY (`id_pelicula`) REFERENCES `pelicula` (`id`),
  CONSTRAINT `fk_Usuario_has_Pelicula_Usuario1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_ha_visto_pelicula`
--

LOCK TABLES `usuario_ha_visto_pelicula` WRITE;
/*!40000 ALTER TABLE `usuario_ha_visto_pelicula` DISABLE KEYS */;
INSERT INTO `usuario_ha_visto_pelicula` VALUES (1,1,2,'2023-05-09'),(2,1,2,'2023-05-10');
/*!40000 ALTER TABLE `usuario_ha_visto_pelicula` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-11 21:58:37
