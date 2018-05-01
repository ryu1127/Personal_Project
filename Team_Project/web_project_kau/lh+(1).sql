-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3307
-- Generation Time: Dec 22, 2017 at 04:18 AM
-- Server version: 5.7.20
-- PHP Version: 7.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `lh`
--

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE `comment` (
  `no` int(11) NOT NULL,
  `id` varchar(100) NOT NULL,
  `date` datetime(6) NOT NULL,
  `title` varchar(100) NOT NULL,
  `comment` varchar(300) NOT NULL,
  `view` int(11) NOT NULL,
  `image` longblob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`no`, `id`, `date`, `title`, `comment`, `view`, `image`) VALUES
(1, 'asdasd', '2017-12-21 20:44:30.000000', 'asdasdasdasd', 'asdasdasdads', 0, ''),
(2, '22222222222222222', '2017-12-21 20:45:01.000000', 'adsdasdasd', 'asfasfasff', 0, ''),
(3, '111111111', '2017-12-21 21:04:27.000000', 'qwweqfwegqewg', 'asdgvgewqgqer', 0, '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`no`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
  MODIFY `no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
