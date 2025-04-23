-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Apr 23, 2025 at 01:16 PM
-- Server version: 8.3.0
-- PHP Version: 8.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sis`
--

-- --------------------------------------------------------

--
-- Table structure for table `osoba`
--

DROP TABLE IF EXISTS `osoba`;
CREATE TABLE IF NOT EXISTS `osoba` (
  `ID` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `jmeno` varchar(100) COLLATE utf8mb4_czech_ci NOT NULL,
  `prijmeni` varchar(100) COLLATE utf8mb4_czech_ci NOT NULL,
  `mail` varchar(320) COLLATE utf8mb4_czech_ci NOT NULL,
  `Uzivatel_ID` int UNSIGNED NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_Osoba_Uzivatel1_idx` (`Uzivatel_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_czech_ci;

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `ID` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `nazev` varchar(45) COLLATE utf8mb4_czech_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_czech_ci;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`ID`, `nazev`) VALUES
(1, 'admin'),
(2, 'vedeni'),
(3, 'rodic'),
(4, 'zak'),
(5, 'ucitel'),
(6, 'hospodarka');

-- --------------------------------------------------------

--
-- Table structure for table `uzivatel`
--

DROP TABLE IF EXISTS `uzivatel`;
CREATE TABLE IF NOT EXISTS `uzivatel` (
  `ID` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(64) COLLATE utf8mb4_czech_ci NOT NULL,
  `heslo` varchar(255) COLLATE utf8mb4_czech_ci NOT NULL,
  `lastLogin` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_czech_ci;

--
-- Dumping data for table `uzivatel`
--

INSERT INTO `uzivatel` (`ID`, `username`, `heslo`, `lastLogin`) VALUES
(1, 'admin', '$2a$12$Fxrtg1LS13A.06rxusUY5.wOTclvf66qfOcHdseX0642fDhiYgIY2', NULL),
(2, 'marekparek', 'asdf', NULL),
(3, 'Pepa', 'pepik123', '2025-01-22 09:10:51'),
(4, 'PatrikBigipi', 'patrik123', '2025-01-22 09:18:01');

-- --------------------------------------------------------

--
-- Table structure for table `uzivatel_has_role`
--

DROP TABLE IF EXISTS `uzivatel_has_role`;
CREATE TABLE IF NOT EXISTS `uzivatel_has_role` (
  `Uzivatel_ID` int UNSIGNED NOT NULL,
  `Role_ID` int UNSIGNED NOT NULL,
  PRIMARY KEY (`Uzivatel_ID`,`Role_ID`),
  KEY `fk_Uzivatel_has_Role_Role1_idx` (`Role_ID`),
  KEY `fk_Uzivatel_has_Role_Uzivatel_idx` (`Uzivatel_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_czech_ci;

--
-- Dumping data for table `uzivatel_has_role`
--

INSERT INTO `uzivatel_has_role` (`Uzivatel_ID`, `Role_ID`) VALUES
(1, 1);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `osoba`
--
ALTER TABLE `osoba`
  ADD CONSTRAINT `fk_Osoba_Uzivatel1` FOREIGN KEY (`Uzivatel_ID`) REFERENCES `uzivatel` (`ID`);

--
-- Constraints for table `uzivatel_has_role`
--
ALTER TABLE `uzivatel_has_role`
  ADD CONSTRAINT `fk_Uzivatel_has_Role_Role1` FOREIGN KEY (`Role_ID`) REFERENCES `role` (`ID`),
  ADD CONSTRAINT `fk_Uzivatel_has_Role_Uzivatel` FOREIGN KEY (`Uzivatel_ID`) REFERENCES `uzivatel` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
