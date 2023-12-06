-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 30, 2022 at 06:29 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_kp`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_permintaan_truk`
--

CREATE TABLE `tb_permintaan_truk` (
  `id_premintaan` varchar(11) NOT NULL,
  `no_pol` varchar(11) NOT NULL,
  `jenis_truk` varchar(100) NOT NULL,
  `dari` varchar(100) NOT NULL,
  `sampai` varchar(100) NOT NULL,
  `rute` varchar(100) NOT NULL,
  `keterangan` varchar(100) NOT NULL,
  `tgl_permintaan` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `tb_unit_truk`
--

CREATE TABLE `tb_unit_truk` (
  `no_pol` varchar(11) NOT NULL,
  `jenis_truk` varchar(100) NOT NULL,
  `kapasitas` varchar(100) NOT NULL,
  `keterangan` varchar(100) NOT NULL,
  `dari` varchar(100) NOT NULL,
  `sampai` varchar(100) NOT NULL,
  `rute` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `tb_user`
--

CREATE TABLE `tb_user` (
  `id` varchar(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(8) NOT NULL,
  `level` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_permintaan_truk`
--
ALTER TABLE `tb_permintaan_truk`
  ADD PRIMARY KEY (`id_premintaan`),
  ADD UNIQUE KEY `no_pol` (`no_pol`);

--
-- Indexes for table `tb_unit_truk`
--
ALTER TABLE `tb_unit_truk`
  ADD PRIMARY KEY (`no_pol`);

--
-- Indexes for table `tb_user`
--
ALTER TABLE `tb_user`
  ADD PRIMARY KEY (`id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tb_permintaan_truk`
--
ALTER TABLE `tb_permintaan_truk`
  ADD CONSTRAINT `tb_permintaan_truk_ibfk_1` FOREIGN KEY (`no_pol`) REFERENCES `tb_unit_truk` (`no_pol`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
