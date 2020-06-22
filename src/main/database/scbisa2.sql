-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 22, 2020 at 04:39 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `scbisa2`
--

-- --------------------------------------------------------

--
-- Table structure for table `arsip`
--

CREATE TABLE `arsip` (
  `id` int(11) NOT NULL,
  `nama_penulis` varchar(100) NOT NULL,
  `kategori` varchar(33) NOT NULL,
  `tanggal` timestamp NOT NULL DEFAULT current_timestamp(),
  `path` varchar(100) NOT NULL,
  `verifikasi` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `arsip`
--

INSERT INTO `arsip` (`id`, `nama_penulis`, `kategori`, `tanggal`, `path`, `verifikasi`) VALUES
(16, 'Soetomo Priyadi', 'Laporan', '2020-06-22 08:23:59', 'C:UsersUSERDownloads182410102080_UAS_MKSI E.pdf', 0),
(18, 'Soetomo Priyadi', 'Proposal', '2020-06-22 08:25:38', 'C:UsersUSERDownloads182410102080_UAS_MKSI E.pdf', 0),
(19, 'Soetomo Priyadi', 'Laporan', '2020-06-22 08:30:50', 'C:UsersUSERDownloadsUAS Algoritma dan Pemrograman 2.pdf', 0),
(20, 'Soetomo Priyadi', 'Proposal', '2020-06-22 08:33:04', 'C:UsersUSERDownloadsUAS Algoritma dan Pemrograman 2.pdf', 0),
(21, 'Soetomo Priyadi', 'Proposal', '2020-06-22 08:36:31', 'C:UsersUSERDownloadsUAS Algoritma dan Pemrograman 2.pdf', 0);

-- --------------------------------------------------------

--
-- Table structure for table `detail_nilai`
--

CREATE TABLE `detail_nilai` (
  `id` bigint(33) NOT NULL,
  `id_siswa` bigint(33) NOT NULL,
  `nama_siswa` varchar(100) NOT NULL,
  `semester` varchar(33) NOT NULL,
  `mapel1` int(11) NOT NULL DEFAULT 0,
  `mapel2` int(11) NOT NULL DEFAULT 0,
  `mapel3` int(11) NOT NULL DEFAULT 0,
  `mapel4` int(11) NOT NULL DEFAULT 0,
  `mapel5` int(11) NOT NULL DEFAULT 0,
  `mapel6` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `detail_nilai`
--

INSERT INTO `detail_nilai` (`id`, `id_siswa`, `nama_siswa`, `semester`, `mapel1`, `mapel2`, `mapel3`, `mapel4`, `mapel5`, `mapel6`) VALUES
(16, 101020201001, 'Gabriela Browne A', '1', 2, 1, 99, 3, 2, 1),
(19, 101020201001, 'Gabriela Browne A', '2', 1, 3, 3, 99, 2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `guru`
--

CREATE TABLE `guru` (
  `id` bigint(33) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `jabatan` varchar(100) NOT NULL,
  `gelar` varchar(100) NOT NULL,
  `id_Mapel` varchar(33) NOT NULL,
  `Alamat` text NOT NULL,
  `password` varchar(33) NOT NULL DEFAULT '123'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `guru`
--

INSERT INTO `guru` (`id`, `nama`, `jabatan`, `gelar`, `id_Mapel`, `Alamat`, `password`) VALUES
(1810300900, 'Soetomo Priyadi', 'Kepala Sekolah', 'M.Kom. S.Kom.', 'BHS INDO', 'bwi', '123');

-- --------------------------------------------------------

--
-- Table structure for table `jabatan`
--

CREATE TABLE `jabatan` (
  `id` bigint(33) NOT NULL,
  `jabatan` varchar(33) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `jabatan`
--

INSERT INTO `jabatan` (`id`, `jabatan`) VALUES
(1, 'Guru'),
(2, 'Kepala Sekolah'),
(3, 'TU');

-- --------------------------------------------------------

--
-- Table structure for table `kategori`
--

CREATE TABLE `kategori` (
  `id` int(11) NOT NULL,
  `kategori` varchar(33) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `kategori`
--

INSERT INTO `kategori` (`id`, `kategori`) VALUES
(1, 'Laporan'),
(2, 'Proposal');

-- --------------------------------------------------------

--
-- Table structure for table `kelas`
--

CREATE TABLE `kelas` (
  `id` bigint(33) NOT NULL,
  `detail` varchar(33) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `kelas`
--

INSERT INTO `kelas` (`id`, `detail`) VALUES
(1, 'I A'),
(2, 'I B'),
(3, 'I C'),
(4, 'I D'),
(5, 'I E'),
(6, 'I F'),
(7, 'I G'),
(8, 'II A'),
(9, 'II B'),
(10, 'II C'),
(11, 'II D'),
(12, 'II E'),
(13, 'II F'),
(14, 'II G'),
(15, 'III A'),
(16, 'III B'),
(17, 'III C'),
(18, 'III D'),
(19, 'III E'),
(20, 'III F'),
(21, 'III G');

-- --------------------------------------------------------

--
-- Table structure for table `mapel`
--

CREATE TABLE `mapel` (
  `id` varchar(33) NOT NULL,
  `detail` varchar(33) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `mapel`
--

INSERT INTO `mapel` (`id`, `detail`) VALUES
('BHS INDO', 'Pelajaran Bahasa Indonesia'),
('Matematika', 'Pelajaran Matematika');

-- --------------------------------------------------------

--
-- Table structure for table `semester`
--

CREATE TABLE `semester` (
  `id` bigint(33) NOT NULL,
  `semester` varchar(33) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `semester`
--

INSERT INTO `semester` (`id`, `semester`) VALUES
(1, '1'),
(2, '2'),
(3, '3'),
(4, '4'),
(5, '5'),
(6, '6');

-- --------------------------------------------------------

--
-- Table structure for table `siswa`
--

CREATE TABLE `siswa` (
  `id` bigint(33) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `kelas_id` varchar(33) NOT NULL,
  `tp_lahir` varchar(33) NOT NULL,
  `tl_lahir` date NOT NULL,
  `alamat` varchar(33) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `siswa`
--

INSERT INTO `siswa` (`id`, `nama`, `kelas_id`, `tp_lahir`, `tl_lahir`, `alamat`) VALUES
(101020201001, 'Gabriela Browne A', 'I A', 'Rennes', '2002-09-18', 'Rennes');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `arsip`
--
ALTER TABLE `arsip`
  ADD PRIMARY KEY (`id`),
  ADD KEY `nama_guru` (`nama_penulis`),
  ADD KEY `kategori` (`kategori`);

--
-- Indexes for table `detail_nilai`
--
ALTER TABLE `detail_nilai`
  ADD PRIMARY KEY (`id`),
  ADD KEY `semester` (`semester`),
  ADD KEY `id_siswa` (`id_siswa`),
  ADD KEY `nama_siswa` (`nama_siswa`);

--
-- Indexes for table `guru`
--
ALTER TABLE `guru`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_Mapel` (`id_Mapel`),
  ADD KEY `nama` (`nama`),
  ADD KEY `jabatan` (`jabatan`);

--
-- Indexes for table `jabatan`
--
ALTER TABLE `jabatan`
  ADD PRIMARY KEY (`id`),
  ADD KEY `jabatan` (`jabatan`);

--
-- Indexes for table `kategori`
--
ALTER TABLE `kategori`
  ADD PRIMARY KEY (`id`),
  ADD KEY `kategori` (`kategori`);

--
-- Indexes for table `kelas`
--
ALTER TABLE `kelas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `detail` (`detail`);

--
-- Indexes for table `mapel`
--
ALTER TABLE `mapel`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `semester`
--
ALTER TABLE `semester`
  ADD PRIMARY KEY (`id`),
  ADD KEY `semester` (`semester`);

--
-- Indexes for table `siswa`
--
ALTER TABLE `siswa`
  ADD PRIMARY KEY (`id`),
  ADD KEY `kelas` (`kelas_id`),
  ADD KEY `nama` (`nama`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `arsip`
--
ALTER TABLE `arsip`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `detail_nilai`
--
ALTER TABLE `detail_nilai`
  MODIFY `id` bigint(33) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `jabatan`
--
ALTER TABLE `jabatan`
  MODIFY `id` bigint(33) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `kategori`
--
ALTER TABLE `kategori`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `kelas`
--
ALTER TABLE `kelas`
  MODIFY `id` bigint(33) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `semester`
--
ALTER TABLE `semester`
  MODIFY `id` bigint(33) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `arsip`
--
ALTER TABLE `arsip`
  ADD CONSTRAINT `arsip_ibfk_1` FOREIGN KEY (`nama_penulis`) REFERENCES `guru` (`nama`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `arsip_ibfk_2` FOREIGN KEY (`kategori`) REFERENCES `kategori` (`kategori`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `detail_nilai`
--
ALTER TABLE `detail_nilai`
  ADD CONSTRAINT `detail_nilai_ibfk_2` FOREIGN KEY (`semester`) REFERENCES `semester` (`semester`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detail_nilai_ibfk_3` FOREIGN KEY (`id_siswa`) REFERENCES `siswa` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detail_nilai_ibfk_4` FOREIGN KEY (`nama_siswa`) REFERENCES `siswa` (`nama`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `guru`
--
ALTER TABLE `guru`
  ADD CONSTRAINT `guru_ibfk_1` FOREIGN KEY (`id_Mapel`) REFERENCES `mapel` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `guru_ibfk_2` FOREIGN KEY (`jabatan`) REFERENCES `jabatan` (`jabatan`);

--
-- Constraints for table `siswa`
--
ALTER TABLE `siswa`
  ADD CONSTRAINT `siswa_ibfk_3` FOREIGN KEY (`kelas_id`) REFERENCES `kelas` (`detail`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
