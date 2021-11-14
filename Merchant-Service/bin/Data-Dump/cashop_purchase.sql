-- MySQL dump 10.13  Distrib 8.0.27, for macos11 (x86_64)
--
-- Host: localhost    Database: cashop
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `purchase`
--

DROP TABLE IF EXISTS `purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchase` (
  `id` int NOT NULL AUTO_INCREMENT,
  `purchasedate` varchar(255) DEFAULT NULL,
  `mid` varchar(255) DEFAULT NULL,
  `musername` varchar(255) DEFAULT NULL,
  `purchaseamt` double DEFAULT NULL,
  `purchasecat` varchar(255) DEFAULT NULL,
  `purchasecustnum` varchar(255) DEFAULT NULL,
  `textmsgstatus` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqbw9tsgtw1nulgfxdi8ayko7n` (`mid`,`musername`),
  CONSTRAINT `FKqbw9tsgtw1nulgfxdi8ayko7n` FOREIGN KEY (`mid`, `musername`) REFERENCES `merchant` (`id`, `username`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase`
--

LOCK TABLES `purchase` WRITE;
/*!40000 ALTER TABLE `purchase` DISABLE KEYS */;
INSERT INTO `purchase` VALUES (1,'2021-05-01','gpQvFi','raghavddps2',1000,'0','8384852943',NULL),(3,'2021-45-01','gpQvFi','raghavddps2',100000,'1','8384852943','1701|918384852943|2f50ddc3-3c53-45ec-bca6-e955cde96723'),(4,'2021-07-01','gpQvFi','raghavddps2',100000,'1','8384852943','Destination/(s) is not White Listed.'),(5,'2021-23-01','zvUBMz','raghavddps23',10000,'1','8384852943','Destination/(s) is not White Listed.'),(6,'2021-23-01','gpQvFi','raghavddps2',1000,'0','8384852943','Destination/(s) is not White Listed.'),(7,'2021-23-01','gpQvFi','raghavddps2',10030,'2','8384852943','Destination/(s) is not White Listed.'),(8,'2021-24-01','gpQvFi','raghavddps2',2345,'3','8384852943','Destination/(s) is not White Listed.'),(9,'2021-24-01','zvUBMz','raghavddps23',235,'3','8384852943','Destination/(s) is not White Listed.'),(10,'2021-24-01','zvUBMz','raghavddps23',2350,'2','8384852943','Destination/(s) is not White Listed.'),(11,'2021-24-01','zvUBMz','raghavddps23',23500,'0','8384852943','Destination/(s) is not White Listed.');
/*!40000 ALTER TABLE `purchase` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-05  4:45:50
