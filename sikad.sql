-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.24-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.1.0.6537
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for sikad
CREATE DATABASE IF NOT EXISTS `sikad` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `sikad`;

-- Dumping structure for table sikad.akun_mhs
CREATE TABLE IF NOT EXISTS `akun_mhs` (
  `nim` int(11) NOT NULL,
  `username` varchar(50) NOT NULL DEFAULT '0',
  `password` varchar(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`nim`),
  CONSTRAINT `FK_akun_mhs_mahasiswa` FOREIGN KEY (`nim`) REFERENCES `mahasiswa` (`nim`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table sikad.akun_mhs: ~2 rows (approximately)
INSERT INTO `akun_mhs` (`nim`, `username`, `password`) VALUES
	(152021169, 'Tegar', '123'),
	(152021170, 'Faishal', '1234');

-- Dumping structure for table sikad.keuangan_mhs
CREATE TABLE IF NOT EXISTS `keuangan_mhs` (
  `nim` int(11) NOT NULL,
  `dpp_wajib` int(11) DEFAULT NULL,
  `ukt` int(11) DEFAULT NULL,
  `biayaSks` int(11) DEFAULT NULL,
  `total` int(11) DEFAULT NULL,
  `ukv` int(11) DEFAULT NULL,
  `tanggal_jatuh_tempo_pembayaran` date DEFAULT NULL,
  `tanggal_jatuh_tempo_perwalian` date DEFAULT NULL,
  `lunas` enum('1','0') DEFAULT NULL,
  `denda` double DEFAULT NULL,
  PRIMARY KEY (`nim`),
  KEY `nim` (`nim`) USING BTREE,
  CONSTRAINT `keuangan` FOREIGN KEY (`nim`) REFERENCES `mahasiswa` (`nim`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table sikad.keuangan_mhs: ~1 rows (approximately)
INSERT INTO `keuangan_mhs` (`nim`, `dpp_wajib`, `ukt`, `biayaSks`, `total`, `ukv`, `tanggal_jatuh_tempo_pembayaran`, `tanggal_jatuh_tempo_perwalian`, `lunas`, `denda`) VALUES
	(152021169, 0, 2160000, 240000, 5544000, 2880000, '2023-05-28', '2023-05-28', '0', 0.05);

-- Dumping structure for table sikad.mahasiswa
CREATE TABLE IF NOT EXISTS `mahasiswa` (
  `nim` int(11) NOT NULL,
  `nama` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `dosen_wali` varchar(50) DEFAULT NULL,
  `semester_aktif` varchar(50) DEFAULT NULL,
  `batas_studi` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `nomor` varchar(50) DEFAULT NULL,
  `prodi` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`nim`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table sikad.mahasiswa: ~1 rows (approximately)
INSERT INTO `mahasiswa` (`nim`, `nama`, `status`, `dosen_wali`, `semester_aktif`, `batas_studi`, `email`, `nomor`, `prodi`) VALUES
	(152021169, 'Tegar Subagdjaa', 'Aktif', 'Jasman Pardede', '2023/2', '2030', 'kingtegar1510@gmail.com', '081546517020', 'Informatika'),
	(152021170, 'Faishal', 'Aktif', 'Jasman Pardede', '2023/2', '2030', 'kingtegar1510@gmail.com', '081546517020', 'Informatika');

-- Dumping structure for table sikad.matakuliah
CREATE TABLE IF NOT EXISTS `matakuliah` (
  `kode` varchar(50) NOT NULL DEFAULT 'AUTO_INCREMENT',
  `matakuliah` varchar(50) DEFAULT NULL,
  `sks` int(11) DEFAULT NULL,
  `kelas` varchar(10) DEFAULT NULL,
  `jenis` varchar(20) DEFAULT NULL,
  `sisa` int(11) DEFAULT NULL,
  `status_awal` varchar(50) DEFAULT NULL,
  `bdatm` varchar(20) DEFAULT 'Batal',
  `approve_wali` varchar(20) DEFAULT NULL,
  `hari` varchar(20) DEFAULT NULL,
  `jam` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`kode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table sikad.matakuliah: ~23 rows (approximately)
INSERT INTO `matakuliah` (`kode`, `matakuliah`, `sks`, `kelas`, `jenis`, `sisa`, `status_awal`, `bdatm`, `approve_wali`, `hari`, `jam`) VALUES
	('IFA-2010', 'PEMROGRAMAN DASAR', 3, 'AA', 'Mata Kuliah A', 239, 'Batal', 'Batal', NULL, 'Selasa', '10:00-12:00'),
	('IFA-202', 'PEMROGRAMAN LANJUT', 3, 'AA', 'Mata Kuliah A', 240, 'Batal', 'BDATM', 'Approve', 'Selasa', '10:00-12:00'),
	('IFA-203', 'PEMROGRAMAN WEB LANJUT', 3, 'CC', 'Mata Kuliah B', 240, 'Batal', 'BDATM', 'Approve', 'Rabu', '13:00-15:00'),
	('IFA-204', 'SISTEM OPERASI', 3, 'AA', 'Mata Kuliah B', 239, 'Ambil', 'BDATM', 'Approve', 'Kamis', '15:00-17:00'),
	('IFA-205', 'KECERDASAN BUATAN', 3, 'AA', 'Mata Kuliah A', 240, 'Batal', 'BDATM', 'Approve', 'Jumat', '08:00-10:00'),
	('IFA-206', 'PEMROGRAMAN MOBILE', 3, 'AA', 'Mata Kuliah B', 240, 'Batal', 'BDATM', 'Approve', 'Senin', '13:00-15:00'),
	('IFA-301', 'BASIS DATA LANJUT', 3, 'CC', 'Mata Kuliah A', 240, 'Batal', 'BDATM', 'Approve', 'Selasa', '10:00-12:00'),
	('IFA-302', 'JARINGAN KOMPUTER', 3, 'DD', 'Mata Kuliah B', 240, 'Batal', 'BDATM', 'Approve', 'Rabu', '15:00-17:00'),
	('IFA-303', 'ANALISIS DAN DESAIN SISTEM', 3, 'BB', 'Mata Kuliah A', 239, 'Ambil', 'BDATM', 'Approve', 'Kamis', '08:00-10:00'),
	('IFA-304', 'PEMROGRAMAN PARALEL', 3, 'BB', 'Mata Kuliah B', 240, 'Batal', 'BDATM', 'Approve', 'Jumat', '13:00-15:00'),
	('IFA-305', 'KEAMANAN KOMPUTER', 3, 'BB', 'Mata Kuliah A', 240, 'Batal', 'BDATM', 'Approve', 'Senin', '10:00-12:00'),
	('IFA-306', 'PENGENALAN BIG DATA', 3, 'BB', 'Mata Kuliah B', 240, 'Batal', 'BDATM', 'Approve', 'Selasa', '15:00-17:00'),
	('IFA-401', 'SISTEM INFORMASI', 3, 'BB', 'Mata Kuliah A', 240, 'Batal', 'BDATM', 'Approve', 'Rabu', '08:00-10:00'),
	('IFA-402', 'PEMROGRAMAN GAME', 3, 'CC', 'Mata Kuliah B', 240, 'Batal', 'BDATM', 'Approve', 'Kamis', '13:00-15:00'),
	('IFA-403', 'DATA MINING', 3, 'CC', 'Mata Kuliah A', 240, 'Batal', 'BDATM', 'Approve', 'Jumat', '10:00-12:00'),
	('IFA-404', 'SISTEM CERDAS', 3, 'AA', 'Mata Kuliah B', 240, 'Batal', 'BDATM', 'Approve', 'Senin', '15:00-17:00'),
	('IFA-405', 'PROYEK AKHIR', 6, 'AA', 'Mata Kuliah A', 240, 'Batal', 'BDATM', 'Approve', 'Selasa', '08:00-12:00'),
	('IFA-501', 'PEMROGRAMAN WEB DASAR', 3, 'AA', 'Mata Kuliah A', 240, 'Batal', 'BDATM', 'Approve', 'Rabu', '10:00-12:00'),
	('IFA-502', 'PEMROGRAMAN DASAR', 3, 'EE', 'Mata Kuliah B', 239, 'Ambil', 'BDATM', 'Approve', 'Kamis', '13:00-15:00'),
	('IFA-503', 'STATISTIKA', 3, 'EE', 'Mata Kuliah A', 240, 'Batal', 'BDATM', 'Approve', 'Jumat', '08:00-10:00'),
	('IFA-504', 'ALGORITMA DAN STRUKTUR DATA', 3, 'EE', 'Mata Kuliah B', 240, 'Batal', 'BDATM', 'Approve', 'Senin', '15:00-17:00'),
	('IFA-505', 'PEMROGRAMAN VISUAL', 3, 'BB', 'Mata Kuliah A', 240, 'Batal', 'BDATM', 'Approve', 'Selasa', '10:00-12:00');

-- Dumping structure for table sikad.nilai_mhs
CREATE TABLE IF NOT EXISTS `nilai_mhs` (
  `nim` int(11) NOT NULL,
  `kode` varchar(50) NOT NULL,
  `matakuliah` varchar(50) DEFAULT NULL,
  `sks` varchar(50) DEFAULT NULL,
  `semester` varchar(50) DEFAULT NULL,
  `nilai` varchar(50) DEFAULT NULL,
  `bobot` varchar(50) DEFAULT NULL,
  `nk` varchar(50) DEFAULT NULL,
  KEY `nim` (`nim`),
  CONSTRAINT `nilai` FOREIGN KEY (`nim`) REFERENCES `mahasiswa` (`nim`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table sikad.nilai_mhs: ~17 rows (approximately)
INSERT INTO `nilai_mhs` (`nim`, `kode`, `matakuliah`, `sks`, `semester`, `nilai`, `bobot`, `nk`) VALUES
	(152021169, 'IFA-201', 'ALJABAR LINEAR', '3', '2021/1', 'A', '4.00', '12.00'),
	(152021169, 'IFA-202', 'PEMROGRAMAN LANJUT', '3', '2021/1', 'AB', '3.50', '10.50'),
	(152021169, 'IFA-203', 'PEMROGRAMAN WEB LANJUT', '3', '2021/1', 'B', '3.00', '9.00'),
	(152021169, 'IFA-204', 'SISTEM OPERASI', '3', '2021/2', 'A', '4.00', '12.00'),
	(152021169, 'IFA-205', 'KECERDASAN BUATAN', '3', '2021/2', 'B', '3.00', '9.00'),
	(152021169, 'IFA-206', 'PEMROGRAMAN MOBILE', '3', '2021/2', 'A', '4.00', '12.00'),
	(152021169, 'IFA-301', 'BASIS DATA LANJUT', '3', '2022/1', 'A', '4.00', '12.00'),
	(152021169, 'IFA-302', 'JARINGAN KOMPUTER', '3', '2022/1', 'B', '3.00', '9.00'),
	(152021169, 'IFA-303', 'ANALISIS DAN DESAIN SISTEM', '3', '2022/1', 'AB', '3.50', '10.50'),
	(152021169, 'IFA-304', 'PEMROGRAMAN PARALEL', '3', '2022/2', 'B', '3.00', '9.00'),
	(152021169, 'IFA-305', 'KEAMANAN KOMPUTER', '3', '2022/2', 'A', '4.00', '12.00'),
	(152021169, 'IFA-306', 'PENGENALAN BIG DATA', '3', '2022/2', 'AB', '3.50', '10.50'),
	(152021169, 'IFA-401', 'SISTEM INFORMASI', '3', '2022/2', 'B', '3.00', '9.00'),
	(152021169, 'IFA-402', 'PEMROGRAMAN GAME', '3', '2023/1', 'A', '4.00', '12.00'),
	(152021169, 'IFA-403', 'DATA MINING', '3', '2023/1', 'AB', '3.50', '10.50'),
	(152021169, 'IFA-404', 'SISTEM CERDAS', '3', '2023/1', 'B', '3.00', '9.00'),
	(152021169, 'IFA-405', 'PROYEK AKHIR', '6', '2023/2', 'A', '4.00', '24.00');

-- Dumping structure for table sikad.perwalian_mhs
CREATE TABLE IF NOT EXISTS `perwalian_mhs` (
  `nim` int(11) NOT NULL,
  `sks` int(11) DEFAULT NULL,
  `mk` int(11) DEFAULT NULL,
  `tanggal_update` date DEFAULT NULL,
  PRIMARY KEY (`nim`),
  CONSTRAINT `perwalian` FOREIGN KEY (`nim`) REFERENCES `mahasiswa` (`nim`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table sikad.perwalian_mhs: ~1 rows (approximately)
INSERT INTO `perwalian_mhs` (`nim`, `sks`, `mk`, `tanggal_update`) VALUES
	(152021169, 12, 4, '2023-06-03');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
