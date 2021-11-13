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
-- Table structure for table `campaign`
--

DROP TABLE IF EXISTS `campaign`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `campaign` (
  `id` int NOT NULL AUTO_INCREMENT,
  `campbannerurl` varchar(255) DEFAULT NULL,
  `campcreatedat` varchar(255) DEFAULT NULL,
  `campmsg` varchar(255) DEFAULT NULL,
  `campname` varchar(255) DEFAULT NULL,
  `mid` varchar(255) DEFAULT NULL,
  `musername` varchar(255) DEFAULT NULL,
  `targetcategory` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKf21vkiuur26ycskspkm07639u` (`mid`,`musername`),
  CONSTRAINT `FKf21vkiuur26ycskspkm07639u` FOREIGN KEY (`mid`, `musername`) REFERENCES `merchant` (`id`, `username`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `campaign`
--

LOCK TABLES `campaign` WRITE;
/*!40000 ALTER TABLE `campaign` DISABLE KEYS */;
INSERT INTO `campaign` VALUES (1,'https://res.cloudinary.com/practicaldev/image/fetch/s--ZcoG81Bn--/c_imagga_scale,f_auto,fl_progressive,h_420,q_auto,w_1000/https://thepracticaldev.s3.amazonaws.com/i/m1f4s9lm21thr7cpwzwq.png','2021-43-02','Come to our shop and get 50% off using the diwali dhamaka sale','Super Diwali Dhamaka Sale','gpQvFi','raghavddps2',1),(2,'https://res.cloudinary.com/practicaldev/image/fetch/s--ZcoG81Bn--/c_imagga_scale,f_auto,fl_progressive,h_420,q_auto,w_1000/https://thepracticaldev.s3.amazonaws.com/i/m1f4s9lm21thr7cpwzwq.png','2021-45-02','Come to our shop and get 50% off using the diwali dhamaka sale','Super Diwali Dhamaka Sale','gpQvFi','raghavddps2',1),(3,'https://res.cloudinary.com/practicaldev/image/fetch/s--ZcoG81Bn--/c_imagga_scale,f_auto,fl_progressive,h_420,q_auto,w_1000/https://thepracticaldev.s3.amazonaws.com/i/m1f4s9lm21thr7cpwzwq.png','2021-53-02','Come to our shop and get 50% off using the diwali dhamaka sale','Super Diwali Dhamaka Sale','gpQvFi','raghavddps2',1),(4,'https://res.cloudinary.com/practicaldev/image/fetch/s--ZcoG81Bn--/c_imagga_scale,f_auto,fl_progressive,h_420,q_auto,w_1000/https://thepracticaldev.s3.amazonaws.com/i/m1f4s9lm21thr7cpwzwq.png','2021-54-02','Come to our shop and get 50% off using the diwali dhamaka sale','Super Diwali Dhamaka Sale','gpQvFi','raghavddps2',1),(5,'https://res.cloudinary.com/practicaldev/image/fetch/s--ZcoG81Bn--/c_imagga_scale,f_auto,fl_progressive,h_420,q_auto,w_1000/https://thepracticaldev.s3.amazonaws.com/i/m1f4s9lm21thr7cpwzwq.png','2021-57-02','Come to our shop and get 50% off using the diwali dhamaka sale','Super Diwali Dhamaka Sale','gpQvFi','raghavddps2',1),(6,'https://res.cloudinary.com/practicaldev/image/fetch/s--ZcoG81Bn--/c_imagga_scale,f_auto,fl_progressive,h_420,q_auto,w_1000/https://thepracticaldev.s3.amazonaws.com/i/m1f4s9lm21thr7cpwzwq.png','2021-59-02','Come to our shop and get 50% off using the diwali dhamaka sale','Super Diwali Dhamaka Sale','gpQvFi','raghavddps2',1),(7,'https://res.cloudinary.com/practicaldev/image/fetch/s--ZcoG81Bn--/c_imagga_scale,f_auto,fl_progressive,h_420,q_auto,w_1000/https://thepracticaldev.s3.amazonaws.com/i/m1f4s9lm21thr7cpwzwq.png','2021-03-02','Come to our shop and get 50% off using the diwali dhamaka sale','Super Diwali Dhamaka Sale','gpQvFi','raghavddps2',1);
/*!40000 ALTER TABLE `campaign` ENABLE KEYS */;
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
