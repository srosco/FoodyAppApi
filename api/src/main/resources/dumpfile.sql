CREATE DATABASE  IF NOT EXISTS `foody_api` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `foody_api`;
-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: foody_api
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `creation_date` date NOT NULL,
  `name` varchar(255) NOT NULL,
  `total_proteins` double DEFAULT NULL,
  `total_carbohydrates` double DEFAULT NULL,
  `total_fibers` double DEFAULT NULL,
  `total_calories` double DEFAULT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (1,'2025-03-17','Test Cart',119.392,466.08799999999997,94.136,2766.68,5),(2,'2025-03-17','Test Cart 2',36.088,140.882,28.454,836.27,1),(4,'2025-03-17','Test Cart',18.2,71.05,14.35,421.75,1),(5,'2025-03-17','Test Cart',18.2,71.05,14.35,421.75,2),(7,'2025-03-17','Test Cart',18.2,71.05,14.35,421.75,1),(8,'2025-03-17','Test Cart',18.2,71.05,14.35,421.75,1),(10,'2025-03-17','Test Cart 2',18.148,70.847,14.308999999999997,420.545,2),(11,'2025-03-17','Test Cart 1',39,152.25,30.749999999999996,903.75,2),(12,'2025-03-17','Test Cart 2',31.2,121.8,24.6,723,3),(13,'2025-03-17','Test Cart 2',31.2,121.8,24.6,723,3),(14,'2025-03-17','Test Cart 2',31.2,121.8,24.6,723,3),(15,'2025-03-17','Test Cart 2',31.2,121.8,24.6,723,3),(16,'2025-03-17','Test Cart 2',31.2,121.8,24.6,723,3),(17,'2025-03-17','Test Cart 2',0,0,0,0,3),(18,'2025-03-17','Test Cart 2',31.2,121.8,24.6,723,3),(19,'2025-03-17','Test Cart 2',0,0,0,0,3),(20,'2025-03-17','Test Cart 11',31.2,121.8,24.6,723,1),(21,'2025-03-17','Test Cart 11',0,0,0,0,1),(22,'2025-04-28','cart 32222',13,50.75,10.25,301.25,1),(23,'2025-04-28','cart 31111',0,0,0,0,2),(24,'2025-05-12','Roland\'s cart',204.18,94.39500000000001,19.064999999999998,4880.325,9);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_products`
--

DROP TABLE IF EXISTS `cart_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_products` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cart_id` bigint NOT NULL,
  `product_id` bigint NOT NULL,
  `quantity_in_grams` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `cart_id` (`cart_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `cart_products_ibfk_1` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`),
  CONSTRAINT `cart_products_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_products`
--

LOCK TABLES `cart_products` WRITE;
/*!40000 ALTER TABLE `cart_products` DISABLE KEYS */;
INSERT INTO `cart_products` VALUES (1,1,9,205),(4,2,9,123),(5,2,10,321),(8,4,9,200),(9,4,10,150),(10,5,9,200),(11,5,10,150),(14,7,9,200),(15,7,10,150),(16,8,9,200),(17,8,10,150),(22,10,9,299),(23,10,10,50),(24,11,9,350),(25,11,10,400),(26,12,7,100),(27,12,5,500),(28,13,7,100),(29,13,5,500),(34,14,7,100),(35,14,5,500),(36,15,7,100),(37,15,5,500),(38,16,7,100),(39,16,5,500),(56,1,2,300),(57,1,10,500),(58,1,3,300),(64,1,6,300),(67,2,2,50),(68,2,3,36),(69,2,4,164),(71,1,5,501),(72,1,7,65),(73,1,4,125),(74,20,7,100),(75,20,5,500),(78,21,7,0),(79,21,5,0),(80,22,4,250),(81,22,2,0),(82,22,1,0),(83,23,1,0),(84,24,1,400),(85,24,2,65);
/*!40000 ALTER TABLE `cart_products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `category` varchar(255) DEFAULT NULL,
  `proteins` double DEFAULT NULL,
  `fibers` double DEFAULT NULL,
  `calories` double DEFAULT NULL,
  `carbohydrates` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `unique_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Orange','Fruit',50.2,4.1,1200.5,20.3),(2,'Healthy Salad','Salad',5.2,4.1,120.5,20.3),(3,'Carrot','Veggetable',5.2,4.1,120.5,20.3),(4,'Beans','Veggetable',5.2,4.1,120.5,20.3),(5,'Banana','Fruit',5.2,4.1,120.5,20.3),(6,'Apple','Fruit',5.2,4.1,120.5,20.3),(7,'Kiwi','Fruit',5.2,4.1,120.5,20.3),(8,'Kaki','Fruit',5.2,4.1,120.5,20.3),(9,'Strawberry','Fruit',5.2,4.1,120.5,20.3),(10,'Blueberry','Fruit',5.2,4.1,120.5,20.3);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `current_maccros` double DEFAULT NULL,
  `aimed_maccros` double DEFAULT NULL,
  `cart_id` bigint DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `salt` varchar(255) NOT NULL,
  `aimed_carbohydrates` double DEFAULT '0',
  `aimed_fibers` double DEFAULT '0',
  `aimed_proteins` double DEFAULT '0',
  `aimed_calories` double DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Johnya45','Doey','johny.doeyssa3@example.com',1750,2000,0,'sKgt9OTvcbhy6oUgxMVIWQIzm44PPPHIDcNBCRPBCqM=','ak8cNfWA+CY+vJHETLIcbw==',350,20,90,2500),(2,'Johnya4','Doeya4','johny.doeyssa1@example.com',1750,2000,0,'LrqM3sTdl22/2E/Box17UyxzoD43D6HmMbUWYkUYL4w=','I2ei8PW3m5rXVEHQbv8ygw==',0,0,0,0),(3,'Johny55','Doeya4','johny.doe1@example.com',1750,2000,0,'PGc2w+aULOb9brxmx13ao3zvReJ6RZnxmmqgxOlZhVY=','SrlT8W4MdGvaYhmETpx6+w==',0,0,0,0),(4,'Johnya4','Doey','johny.doe2@example.com',1750,2000,0,'P9YzghpUiV11EtXFWxMhs34HTOOTLLFROXjhIwMIQ6U=','CvKs1W/K+84dEEqdHzqQNw==',0,0,0,0),(5,'Test 1','Doeya4','johny.doe3@example.com',1750,2500,0,'iqRCHKJiFXsMJKXZzRtOy9EgpTkL241kU2SITLgw+fI=','itisjB1Tw0LjPD5+HOft3A==',400,18,95,3000),(6,'Johnya4','Doeya4','johny.doe9@example.com',1750,2000,0,'kyazzkIS3jeUcwI4sV4zyMNJZFClSsyiKjDnkfVuP4g=','DpktyVUiASy9XFME++iMcA==',0,0,0,0),(8,'Johnya4','Doeya4','johny.doe15@example.com',0,2000,0,'lq+C3EEDJSPK/7ZpMJ1a1riwCa29v4M3PuYFkoa3MVQ=','uBSGYlstFBhsBjuyYGtyWw==',0,0,0,0),(9,'Roland','Niel','username@test.com',0,0,0,'myHgbCgBlTkdtImHX2t1xR3p5grU3QO0aaSJHaK69kw=','E1K9ET/KNY/Kq9skBnjc8w==',325,30,130,2600);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-14 17:14:02
