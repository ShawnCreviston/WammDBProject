CREATE DATABASE  IF NOT EXISTS `sl_nethips` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `sl_nethips`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: sl_nethips
-- ------------------------------------------------------
-- Server version	5.6.19-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `chiefdom`
--

DROP TABLE IF EXISTS `chiefdom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chiefdom` (
  `ChiefdomID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Name` varchar(30) NOT NULL,
  `TerritoryID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`ChiefdomID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chiefdom`
--

LOCK TABLES `chiefdom` WRITE;
/*!40000 ALTER TABLE `chiefdom` DISABLE KEYS */;
/*!40000 ALTER TABLE `chiefdom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `maritalstatuslist`
--

DROP TABLE IF EXISTS `maritalstatuslist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `maritalstatuslist` (
  `MaritalStatusID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `MaritalStatus` varchar(15) NOT NULL,
  PRIMARY KEY (`MaritalStatusID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `maritalstatuslist`
--

LOCK TABLES `maritalstatuslist` WRITE;
/*!40000 ALTER TABLE `maritalstatuslist` DISABLE KEYS */;
/*!40000 ALTER TABLE `maritalstatuslist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nethipsusers`
--

DROP TABLE IF EXISTS `nethipsusers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nethipsusers` (
  `Username` varchar(20) NOT NULL,
  `CanEditUsers` bit(1) DEFAULT b'0',
  `CanEditSupportTables` bit(1) DEFAULT b'0',
  `CanSyncDB` bit(1) DEFAULT b'0',
  PRIMARY KEY (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nethipsusers`
--

LOCK TABLES `nethipsusers` WRITE;
/*!40000 ALTER TABLE `nethipsusers` DISABLE KEYS */;
INSERT INTO `nethipsusers` VALUES ('clientTest','','','');
/*!40000 ALTER TABLE `nethipsusers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patientadult`
--

DROP TABLE IF EXISTS `patientadult`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patientadult` (
  `PatientId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `FirstName` varbinary(128) NOT NULL,
  `MiddleName` varbinary(128) DEFAULT NULL,
  `LastName` varbinary(128) NOT NULL,
  `Gender` char(1) DEFAULT NULL,
  `Birthdate` date DEFAULT NULL,
  `Deseased` date DEFAULT NULL,
  `Weight` decimal(5,2) unsigned NOT NULL DEFAULT '0.00',
  `CurTerritoryId` int(10) unsigned NOT NULL,
  `CurStreet` varchar(45) DEFAULT NULL,
  `PrevTerritoryId` int(10) unsigned DEFAULT NULL,
  `PrevStreet` varchar(45) DEFAULT NULL,
  `PhoneNumber1` varchar(15) DEFAULT NULL,
  `PhoneNumber2` varchar(15) DEFAULT NULL,
  `ChiefdomId` int(10) unsigned DEFAULT NULL,
  `SchoolId` int(10) unsigned DEFAULT NULL,
  `Occupation` varchar(45) DEFAULT NULL,
  `MaritalStatus` varchar(15) DEFAULT NULL,
  `RegisteredDate` date DEFAULT NULL,
  `LastUpdatedDate` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`PatientId`)
) ENGINE=InnoDB AUTO_INCREMENT=100000 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patientadult`
--

LOCK TABLES `patientadult` WRITE;
/*!40000 ALTER TABLE `patientadult` DISABLE KEYS */;
/*!40000 ALTER TABLE `patientadult` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patientchild`
--

DROP TABLE IF EXISTS `patientchild`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patientchild` (
  `PatientId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `FirstName` varbinary(128) NOT NULL,
  `MiddleName` varbinary(128) DEFAULT NULL,
  `LastName` varbinary(128) NOT NULL,
  `Gender` char(1) DEFAULT NULL,
  `Birthdate` date DEFAULT NULL,
  `Deseased` date DEFAULT NULL,
  `Weight` decimal(5,2) unsigned NOT NULL DEFAULT '0.00',
  `CurTerritoryId` int(10) unsigned NOT NULL,
  `CurStreet` varchar(45) DEFAULT NULL,
  `PrevTerritoryId` int(10) unsigned DEFAULT NULL,
  `PrevStreet` varchar(45) DEFAULT NULL,
  `PhoneNumber1` varchar(15) DEFAULT NULL,
  `PhoneNumber2` varchar(15) DEFAULT NULL,
  `ChiefdomId` int(10) unsigned DEFAULT NULL,
  `SchoolId` int(10) unsigned DEFAULT NULL,
  `Occupation` varchar(45) DEFAULT NULL,
  `MaritalStatus` varchar(15) DEFAULT NULL,
  `RegisteredDate` date DEFAULT NULL,
  `LastUpdatedDate` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`PatientId`)
) ENGINE=InnoDB AUTO_INCREMENT=100000 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patientchild`
--

LOCK TABLES `patientchild` WRITE;
/*!40000 ALTER TABLE `patientchild` DISABLE KEYS */;
/*!40000 ALTER TABLE `patientchild` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plhiv`
--

DROP TABLE IF EXISTS `plhiv`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `plhiv` (
  `InternalId` varbinary(128) NOT NULL,
  `HIVCode` varchar(45) DEFAULT NULL,
  `HIVName` varchar(45) DEFAULT NULL,
  `Transfer` varchar(45) DEFAULT NULL,
  `CoInfected` varchar(45) DEFAULT NULL,
  `HIVSource` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`InternalId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plhiv`
--

LOCK TABLES `plhiv` WRITE;
/*!40000 ALTER TABLE `plhiv` DISABLE KEYS */;
/*!40000 ALTER TABLE `plhiv` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plhivcodelist`
--

DROP TABLE IF EXISTS `plhivcodelist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `plhivcodelist` (
  `PLHIVCodeID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `PLHIVCode` varchar(45) NOT NULL,
  PRIMARY KEY (`PLHIVCodeID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plhivcodelist`
--

LOCK TABLES `plhivcodelist` WRITE;
/*!40000 ALTER TABLE `plhivcodelist` DISABLE KEYS */;
/*!40000 ALTER TABLE `plhivcodelist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `school`
--

DROP TABLE IF EXISTS `school`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `school` (
  `SchoolID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `TerritoryID` int(10) unsigned NOT NULL,
  `Street` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`SchoolID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `school`
--

LOCK TABLES `school` WRITE;
/*!40000 ALTER TABLE `school` DISABLE KEYS */;
/*!40000 ALTER TABLE `school` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supportgroup`
--

DROP TABLE IF EXISTS `supportgroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supportgroup` (
  `SupportGroupID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Name` varchar(125) NOT NULL,
  `TerritoryID` int(10) NOT NULL DEFAULT '1',
  PRIMARY KEY (`SupportGroupID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supportgroup`
--

LOCK TABLES `supportgroup` WRITE;
/*!40000 ALTER TABLE `supportgroup` DISABLE KEYS */;
INSERT INTO `supportgroup` VALUES (1,'plhiv list',1),(2,'ovc list (bo)',14),(3,'ovc list (port Loko)',12),(4,'ovc list (pujehun)',17),(5,'HCSA',1),(6,'WE TOO CAN DO',1),(7,'UFDA',1),(8,'WANFAMBUL',1),(9,'KDSG',74),(10,'PUSH',82),(12,'HASRA',12);
/*!40000 ALTER TABLE `supportgroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supportsystem`
--

DROP TABLE IF EXISTS `supportsystem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supportsystem` (
  `InternalID` varbinary(128) NOT NULL,
  `SupportGroupID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`InternalID`,`SupportGroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supportsystem`
--

LOCK TABLES `supportsystem` WRITE;
/*!40000 ALTER TABLE `supportsystem` DISABLE KEYS */;
/*!40000 ALTER TABLE `supportsystem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supporttransactions`
--

DROP TABLE IF EXISTS `supporttransactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supporttransactions` (
  `InternalID` varbinary(128) NOT NULL,
  `SchoolID` int(10) unsigned DEFAULT NULL,
  `SupportGroupID` int(10) unsigned DEFAULT NULL,
  `Date` datetime DEFAULT NULL,
  `Amount` double(7,2) DEFAULT NULL,
  PRIMARY KEY (`InternalID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supporttransactions`
--

LOCK TABLES `supporttransactions` WRITE;
/*!40000 ALTER TABLE `supporttransactions` DISABLE KEYS */;
/*!40000 ALTER TABLE `supporttransactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `territory`
--

DROP TABLE IF EXISTS `territory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `territory` (
  `TerritoryID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `TerritoryType` varchar(45) NOT NULL,
  `ParentTerritoryID` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`TerritoryID`)
) ENGINE=InnoDB AUTO_INCREMENT=192 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `territory`
--

LOCK TABLES `territory` WRITE;
/*!40000 ALTER TABLE `territory` DISABLE KEYS */;
INSERT INTO `territory` VALUES (1,'Sierra Leone','Country',0),(2,'Northern','Province',1),(3,'Eastern','Province',1),(4,'Southern','Province',1),(5,'Western Area','Province',1),(6,'Kailahun','District',3),(7,'Kono','District',3),(8,'Kenema','District',3),(9,'Bombali','District',2),(10,'Kambia','District',3),(11,'Kionadugu','District',2),(12,'Port Loko','District',2),(13,'Tonkolili','District',2),(14,'Bo','District',4),(15,'Bonthe','District',4),(16,'Moyamba','District',4),(17,'Pujehun','District',4),(18,'Rural','District',5),(19,'Urban','District',5),(20,'Kailahun','Town',6),(21,'Segbwema','Town',6),(22,'Daru','Town',6),(23,'Pendembu','Town',6),(24,'Kiondu','Town',6),(25,'Kenema','Town',8),(26,'Blama','Town',8),(27,'Tongo','Town',8),(28,'Yomboma','Town',8),(29,'Barma','Town',8),(30,'Boajibu','Town',8),(31,'Giehun','Town',8),(32,'Gorahun','Town',8),(33,'Hangha','Town',8),(34,'Panguma','Town',8),(35,'Pendemu','Town',8),(36,'Tokpombu','Town',8),(37,'Wuima','Town',8),(38,'Motema','Town',7),(39,'Njaiama','Town',7),(40,'Simbakoro','Town',7),(41,'Foindu','Town',7),(42,'Tefeya','Town',7),(43,'Jaiama-Nimokoro','Town',7),(44,'Jaima-Sewafe','Town',7),(45,'Kaima','Town',7),(46,'Sewafe','Town',7),(47,'Ndoyogbo','Town',7),(48,'Nemeseidu','Town',7),(49,'Kangama','Town',7),(50,'Ngiehun','Town',7),(51,'Peyima','Town',7),(52,'Seidu','Town',7),(53,'Njagbwema','Town',7),(54,'Kamiendor','Town',7),(55,'Masongbon','Town',9),(56,'Kagber','Town',9),(57,'Kalangba','Town',9),(58,'Fintonia','Town',9),(59,'Lowoma','Town',9),(60,'Hunduwa','Town',9),(61,'Kamaranka','Town',9),(62,'Batkanu','Town',9),(63,'Mateboi','Town',9),(64,'Gbendembu','Town',9),(65,'Tambiama','Town',9),(66,'Masingbi','Town',9),(67,'Mapaki','Town',9),(68,'Moriba','Town',9),(69,'Rokulan','Town',9),(70,'Kamalo','Town',9),(71,'Sanda','Town',9),(72,'Tonko','Town',9),(73,'Kambia','Town',10),(74,'Kabala','Town',11),(75,'Sinkunia','Town',11),(76,'Falaba','Town',11),(77,'Fadugu','Town',11),(78,'Kurubonla','Town',11),(79,'Lunsar','Town',12),(80,'Port Loko','Town',12),(81,'Rokupr','Town',12),(82,'Masiaka','Town',12),(83,'Lungi','Town',12),(84,'Pepel','Town',12),(85,'Gbinti','Town',12),(86,'Bamundu','Town',12),(87,'Gbinti','Town',12),(88,'Kasseh','Town',12),(89,'Konaridie','Town',12),(90,'Mahera','Town',12),(100,'Marampa','Town',12),(101,'Masiyila','Town',12),(102,'Semanu','Town',12),(103,'Sawkta','Town',12),(104,'Taiama','Town',12),(105,'Mabonto','Town',13),(106,'Bumbuna','Town',13),(107,'Makali','Town',13),(108,'Masingbi','Town',13),(109,'Yele','Town',13),(110,'Bendugu','Town',13),(111,'Bumbuna','Town',13),(112,'Yonibana','Town',13),(113,'Matotoka','Town',13),(114,'Bo','Town',14),(115,'Boama','Town',14),(116,'Serabu','Town',14),(117,'Bumpe','Town',14),(118,'Mongeri','Town',14),(119,'Sumbuya','Town',14),(120,'Baiima','Town',14),(121,'Yele','Town',14),(122,'Tikonko','Town',14),(123,'Bambima','Town',14),(124,'Bandeh','Town',14),(125,'Koribindu','Town',14),(126,'Momboma','Town',14),(127,'Serabu','Town',14),(128,'Sembehun','Town',14),(129,'Telu','Town',14),(130,'Tokoronko','Town',14),(131,'Mange','Town',14),(132,'Mattru Jong','Town',15),(133,'Bonthe','Town',15),(134,'Moyamba','Town',16),(135,'Njala','Town',16),(136,'Rotifunk','Town',16),(137,'Shenge','Town',16),(138,'Pujehun','Town',17),(139,'Gandorhun','Town',17),(140,'Zimmi','Town',17),(141,'Gendema','Town',17),(142,'Masam','Town',17),(143,'Bomi','Town',17),(144,'Potoru','Town',17),(145,'Waterloo','Town',18),(146,'Newton','Town',18),(147,'Leicester','Town',18),(148,'Benguema','Town',18),(149,'Grafton','Town',18),(150,'York','Town',18),(151,'Tombo','Town',18),(152,'Adonkia','Town',18),(153,'Lakka','Town',18),(154,'Tokeh','Town',18),(155,'Kwama','Town',18),(156,'Samuel Town','Town',18),(157,'Kola Town','Town',18),(158,'Joe Town','Town',18),(159,'Sussex','Town',18),(160,'Bathurst','Town',18),(161,'Baw Baw','Town',18),(162,'Dublin','Town',18),(163,'Ricketts','Town',18),(164,'Fogbo','Town',18),(165,'Gloucester','Town',18),(166,'Kent','Town',18),(167,'Tissana','Town',18),(168,'Kossoh Town','Town',18),(169,'Rokel','Town',18),(170,'Rokupa','Town',18),(171,'Russell','Town',18),(172,'Stones Town','Town',18),(173,'Freetown','Town',19),(174,'Wellington','Neighbourhood',173),(175,'Kissy','Neighbourhood',173),(176,'Kortright','Neighbourhood',173),(177,'Upgun','Neighbourhood',173),(178,'Mt Aureol','Neighbourhood',173),(179,'Kissy','Neighbourhood',173),(180,'New England','Neighbourhood',173),(181,'Congo Town','Neighbourhood',173),(182,'Madongo Town','Neighbourhood',173),(183,'Tengbeh','Neighbourhood',173),(184,'Wilberforce','Neighbourhood',173),(185,'Lumley','Neighbourhood',173),(186,'Goderich','Neighbourhood',18),(187,'Funkia','Neighbourhood',18),(188,'Hastings','Town',18),(189,'Allen Town','Town',18),(190,'Calaba Town','Town',18),(191,'Hamilton','Town',18);
/*!40000 ALTER TABLE `territory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `territorytypelist`
--

DROP TABLE IF EXISTS `territorytypelist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `territorytypelist` (
  `TerritoryTypeID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `TerritoryType` varchar(45) NOT NULL,
  PRIMARY KEY (`TerritoryTypeID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `territorytypelist`
--

LOCK TABLES `territorytypelist` WRITE;
/*!40000 ALTER TABLE `territorytypelist` DISABLE KEYS */;
INSERT INTO `territorytypelist` VALUES (1,'Country'),(2,'Province'),(3,'District'),(4,'Town'),(5,'Neighbourhood'),(6,'Village');
/*!40000 ALTER TABLE `territorytypelist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `treatment`
--

DROP TABLE IF EXISTS `treatment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `treatment` (
  `InternalID` varbinary(128) NOT NULL,
  `Treatment` varchar(45) DEFAULT NULL,
  `PLCode` varchar(45) DEFAULT NULL,
  `CurrentStatus` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`InternalID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `treatment`
--

LOCK TABLES `treatment` WRITE;
/*!40000 ALTER TABLE `treatment` DISABLE KEYS */;
/*!40000 ALTER TABLE `treatment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `treatmentlist`
--

DROP TABLE IF EXISTS `treatmentlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `treatmentlist` (
  `TreatmentID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Treatment` varchar(45) NOT NULL,
  PRIMARY KEY (`TreatmentID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `treatmentlist`
--

LOCK TABLES `treatmentlist` WRITE;
/*!40000 ALTER TABLE `treatmentlist` DISABLE KEYS */;
/*!40000 ALTER TABLE `treatmentlist` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-07-28 16:23:03
