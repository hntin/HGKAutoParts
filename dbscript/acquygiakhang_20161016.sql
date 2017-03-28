CREATE DATABASE  IF NOT EXISTS `acquygiakhangdb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `acquygiakhangdb`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: acquygiakhangdb
-- ------------------------------------------------------
-- Server version	5.7.14-log

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
-- Table structure for table `dongxe`
--

DROP TABLE IF EXISTS `dongxe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dongxe` (
  `MaDongXe` varchar(45) CHARACTER SET utf8 NOT NULL,
  `TenDongXe` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `SoXyLanh` int(11) DEFAULT NULL,
  `DongCo` varchar(10) CHARACTER SET utf8 DEFAULT NULL,
  `HopSo` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `SoCua` int(11) DEFAULT NULL,
  `NhienLieu` varchar(45) COLLATE utf8_general_mysql500_ci DEFAULT NULL,
  `NamSanXuat` varchar(45) COLLATE utf8_general_mysql500_ci DEFAULT NULL,
  `MaLoaiXe` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`MaDongXe`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dongxe`
--

LOCK TABLES `dongxe` WRITE;
/*!40000 ALTER TABLE `dongxe` DISABLE KEYS */;
INSERT INTO `dongxe` VALUES ('1','Vios 2013-2016',4,'1.6','Tự động',4,'Xăng','2013-2016','2'),('2','Camry 2.4',4,'2.4','Tự động',4,'Xăng','2016','1'),('3','Camry 3.0',4,'3.0','Tự động',4,'Xăng','2016','1');
/*!40000 ALTER TABLE `dongxe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hangxe`
--

DROP TABLE IF EXISTS `hangxe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hangxe` (
  `MaHangXe` varchar(45) CHARACTER SET utf8 NOT NULL,
  `TenHangXe` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `GhiChu` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`MaHangXe`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hangxe`
--

LOCK TABLES `hangxe` WRITE;
/*!40000 ALTER TABLE `hangxe` DISABLE KEYS */;
INSERT INTO `hangxe` VALUES ('1','Toyota','Toyota'),('2','Honda','Honda');
/*!40000 ALTER TABLE `hangxe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hinhanh`
--

DROP TABLE IF EXISTS `hinhanh`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hinhanh` (
  `MaHinhAnh` int(11) NOT NULL AUTO_INCREMENT,
  `TenHinhAnh` varchar(100) DEFAULT NULL,
  `DuongDanHinhAnh` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`MaHinhAnh`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hinhanh`
--

LOCK TABLES `hinhanh` WRITE;
/*!40000 ALTER TABLE `hinhanh` DISABLE KEYS */;
/*!40000 ALTER TABLE `hinhanh` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loainguoidung`
--

DROP TABLE IF EXISTS `loainguoidung`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `loainguoidung` (
  `MaLoaiNguoiDung` int(11) NOT NULL,
  `TenLoaiNguoiDung` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `GhiChu` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`MaLoaiNguoiDung`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loainguoidung`
--

LOCK TABLES `loainguoidung` WRITE;
/*!40000 ALTER TABLE `loainguoidung` DISABLE KEYS */;
INSERT INTO `loainguoidung` VALUES (1,'Quản trị','Quản trị'),(2,'Nhân viên','Nhân viên');
/*!40000 ALTER TABLE `loainguoidung` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loaisanpham`
--

DROP TABLE IF EXISTS `loaisanpham`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `loaisanpham` (
  `MaLoaiSanPham` int(11) NOT NULL AUTO_INCREMENT,
  `TenLoaiSanPham` varchar(45) DEFAULT NULL,
  `MaLoaiCha` int(11) DEFAULT NULL,
  `MucLoaiSanPham` int(11) DEFAULT NULL,
  PRIMARY KEY (`MaLoaiSanPham`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loaisanpham`
--

LOCK TABLES `loaisanpham` WRITE;
/*!40000 ALTER TABLE `loaisanpham` DISABLE KEYS */;
INSERT INTO `loaisanpham` VALUES (1,'Loại sản phẩm',-1,NULL),(2,'Ắc Quy',1,NULL),(3,'Bóng đèn',1,NULL),(4,'Bugi',1,NULL),(5,'Còi Xe',1,NULL),(6,'Gạt mưa',1,NULL),(7,'Thiết bị',1,NULL),(8,'Phụ kiện',1,NULL),(9,'Bơm xăng',1,NULL),(10,'Ắc quy Ôtô',2,NULL),(11,'Ắc quy DataCenter',2,NULL),(12,'Ắc quy máy phát',2,NULL),(13,'Bóng đèn Xenon',3,NULL),(14,'Bóng đèn tăng sáng',3,NULL),(15,'Ánh sáng trắng',3,NULL),(16,'Máy nạp ắc quy',7,NULL),(17,'Máy phun rửa áp lực',NULL,NULL);
/*!40000 ALTER TABLE `loaisanpham` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loaixe`
--

DROP TABLE IF EXISTS `loaixe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `loaixe` (
  `MaLoaiXe` varchar(45) NOT NULL,
  `TenLoaiXe` varchar(45) DEFAULT NULL,
  `MaHangXe` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`MaLoaiXe`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loaixe`
--

LOCK TABLES `loaixe` WRITE;
/*!40000 ALTER TABLE `loaixe` DISABLE KEYS */;
INSERT INTO `loaixe` VALUES ('1','Camry','1'),('11','Civic','2'),('14','Accord','2'),('2','Vios','1'),('3','Innova','1'),('4','Yaris','1'),('7','Honda City','2'),('9','CRV','2');
/*!40000 ALTER TABLE `loaixe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nguoidung`
--

DROP TABLE IF EXISTS `nguoidung`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nguoidung` (
  `MaNguoiDung` varchar(45) CHARACTER SET utf8 NOT NULL,
  `TenNguoiDung` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `MatKhau` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `MaLoaiNguoiDung` int(11) DEFAULT NULL,
  PRIMARY KEY (`MaNguoiDung`),
  KEY `MaLoaiNguoiDung_idx` (`MaLoaiNguoiDung`),
  CONSTRAINT `MaLoaiNguoiDung` FOREIGN KEY (`MaLoaiNguoiDung`) REFERENCES `loainguoidung` (`MaLoaiNguoiDung`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nguoidung`
--

LOCK TABLES `nguoidung` WRITE;
/*!40000 ALTER TABLE `nguoidung` DISABLE KEYS */;
INSERT INTO `nguoidung` VALUES ('hnthanh','hnthanh','84264',1),('hntin','hntin','hntin',1),('nhung.tran','Trần Ngọc Nhung','nhung.tran',2),('oneadmin','oneadmin','oneadmin',1);
/*!40000 ALTER TABLE `nguoidung` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhacungcap`
--

DROP TABLE IF EXISTS `nhacungcap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nhacungcap` (
  `MaNhaCC` varchar(45) CHARACTER SET utf8 NOT NULL,
  `TenNhaCC` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `Email` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `DienThoai` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `DiaChi` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `GhiChu` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`MaNhaCC`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhacungcap`
--

LOCK TABLES `nhacungcap` WRITE;
/*!40000 ALTER TABLE `nhacungcap` DISABLE KEYS */;
INSERT INTO `nhacungcap` VALUES ('1','BOSCH','bosch@gmail.com','123456789','Đường Điện Biên Phủ, Quận Bình Thạnh, TPHCM','BOSCH'),('2','OSRAM','osram@gmail.com','123456','Quận 1, TPHCM','OSRAM'),('GS','GS','gs@gmail.com','1234567','Biên Hòa, Đồng Nai','Biên Hòa, Đồng Nai');
/*!40000 ALTER TABLE `nhacungcap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quocgia`
--

DROP TABLE IF EXISTS `quocgia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quocgia` (
  `MaQuocGia` varchar(45) CHARACTER SET utf8 NOT NULL,
  `TenQuocGia` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `GhiChu` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`MaQuocGia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quocgia`
--

LOCK TABLES `quocgia` WRITE;
/*!40000 ALTER TABLE `quocgia` DISABLE KEYS */;
INSERT INTO `quocgia` VALUES ('1','Ấn độ','India'),('2','Anh','England'),('3','Đức','Germany'),('4','Mỹ','USA'),('5','Nhật','JP'),('6','Singapore','Singapore'),('7','Thổ Nhĩ Kỳ','Turkey'),('8','Trung Quốc','China'),('9','Vietnam','VN');
/*!40000 ALTER TABLE `quocgia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sanpham`
--

DROP TABLE IF EXISTS `sanpham`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sanpham` (
  `MaSanPham` varchar(45) CHARACTER SET utf8 NOT NULL,
  `TenSanPham` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `MoTa` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `Gia` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `MaTinhTrang` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `HinhDaiDien` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `MaNhaCC` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `MaQuocGia` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `MaLoaiSanPham` int(11) DEFAULT NULL,
  PRIMARY KEY (`MaSanPham`),
  KEY `MaNhaCC_idx` (`MaNhaCC`),
  KEY `MaQuocGia_idx` (`MaQuocGia`),
  KEY `MaLoaiSanPham_idx` (`MaLoaiSanPham`),
  CONSTRAINT `MaLoaiSanPham` FOREIGN KEY (`MaLoaiSanPham`) REFERENCES `loaisanpham` (`MaLoaiSanPham`),
  CONSTRAINT `MaNhaCC` FOREIGN KEY (`MaNhaCC`) REFERENCES `nhacungcap` (`MaNhaCC`),
  CONSTRAINT `MaQuocGia` FOREIGN KEY (`MaQuocGia`) REFERENCES `quocgia` (`MaQuocGia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sanpham`
--

LOCK TABLES `sanpham` WRITE;
/*!40000 ALTER TABLE `sanpham` DISABLE KEYS */;
INSERT INTO `sanpham` VALUES ('1','Ắc quy 01','Ắc quy 01','1','1','ProductImages/acquy01.jpg','1','3',1),('123','123','123','1','1','ProductImages/acquy01.jpg','1','3',1);
/*!40000 ALTER TABLE `sanpham` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sanphamdongxe`
--

DROP TABLE IF EXISTS `sanphamdongxe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sanphamdongxe` (
  `MaSanPham` varchar(45) CHARACTER SET utf8 NOT NULL,
  `MaDongXe` varchar(45) CHARACTER SET utf8 NOT NULL,
  `MoTa` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`MaSanPham`,`MaDongXe`),
  KEY `MaDongXe_idx` (`MaDongXe`),
  CONSTRAINT `MaDongXe` FOREIGN KEY (`MaDongXe`) REFERENCES `dongxe` (`MaDongXe`),
  CONSTRAINT `MaSanPham` FOREIGN KEY (`MaSanPham`) REFERENCES `sanpham` (`MaSanPham`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sanphamdongxe`
--

LOCK TABLES `sanphamdongxe` WRITE;
/*!40000 ALTER TABLE `sanphamdongxe` DISABLE KEYS */;
INSERT INTO `sanphamdongxe` VALUES ('1','1','1#1'),('1','2','1#2'),('1','3','1#3'),('123','1','123#1'),('123','2','123#2'),('123','3','123#3');
/*!40000 ALTER TABLE `sanphamdongxe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sanphamhinhanh`
--

DROP TABLE IF EXISTS `sanphamhinhanh`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sanphamhinhanh` (
  `MaSanPham` varchar(45) NOT NULL,
  `TenHinhAnh` varchar(100) NOT NULL,
  `DuongDanHinhAnh` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`MaSanPham`,`TenHinhAnh`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sanphamhinhanh`
--

LOCK TABLES `sanphamhinhanh` WRITE;
/*!40000 ALTER TABLE `sanphamhinhanh` DISABLE KEYS */;
/*!40000 ALTER TABLE `sanphamhinhanh` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tinhtrang`
--

DROP TABLE IF EXISTS `tinhtrang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tinhtrang` (
  `MaTinhTrang` varchar(45) NOT NULL,
  `TenTinhTrang` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`MaTinhTrang`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tinhtrang`
--

LOCK TABLES `tinhtrang` WRITE;
/*!40000 ALTER TABLE `tinhtrang` DISABLE KEYS */;
INSERT INTO `tinhtrang` VALUES ('1','Còn hàng'),('2','Hết hàng');
/*!40000 ALTER TABLE `tinhtrang` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-10-16 15:35:43
