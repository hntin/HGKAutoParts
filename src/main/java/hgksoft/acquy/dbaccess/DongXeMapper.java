/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hgksoft.acquy.dbaccess;

import hgksoft.acquy.constant.DBTable;
import hgksoft.acquy.constant.Database;
import hgksoft.acquy.dto.DongXeDTO;
import hgksoft.acquy.dto.DongXeDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HNTIN
 */
public class DongXeMapper extends DBMapper {

    public DongXeMapper() throws Exception {
        super();
    }   
    
    public DongXeDTO getDongXeDTO(String maDongXe) throws Exception {
        DongXeDTO dongxeDTO = new DongXeDTO();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + Database.Name + "." + DBTable.DongXe + " dx,");
            sql.append(Database.Name + "." + DBTable.LoaiXe + " lx,");
            sql.append(Database.Name + "." + DBTable.HangXe + " hx");
            sql.append(" WHERE dx.MaLoaiXe = lx.MaLoaiXe");
            sql.append(" AND lx.MaHangXe = hx.MaHangXe");
            sql.append(" AND dx.MaDongXe = ?");
            
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, maDongXe);
            ResultSet rs = stmt.executeQuery();
            if ((rs != null) && (rs.next())) {
                dongxeDTO.setMaDongXe(maDongXe);
                dongxeDTO.setTenDongXe(rs.getString("TenDongXe"));
                dongxeDTO.setSoXyLanh(rs.getInt("SoXyLanh"));
                dongxeDTO.setDongCo(rs.getString("DongCo"));
                dongxeDTO.setHopSo(rs.getString("HopSo"));
                dongxeDTO.setSoCua(rs.getInt("SoCua"));
                dongxeDTO.setNhienLieu(rs.getString("NhienLieu"));
                dongxeDTO.setNamSX(rs.getString("NamSanXuat"));
                dongxeDTO.setMaLoaiXe(rs.getString("MaLoaiXe"));
                dongxeDTO.setTenLoaiXe(rs.getString("TenLoaiXe"));
                dongxeDTO.setMaHangXe(rs.getString("MaHangXe"));
                dongxeDTO.setTenHangXe(rs.getString("TenHangXe"));
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return dongxeDTO;
    }

    public List<DongXeDTO> getDSDongXe(
            String maDongXe,
            String tenDongXe) throws Exception {

        ArrayList<DongXeDTO> dsDongXeDTO = new ArrayList<>();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + Database.Name + "." + DBTable.DongXe + " dx,");
            sql.append(Database.Name + "." + DBTable.LoaiXe + " lx,");
            sql.append(Database.Name + "." + DBTable.HangXe + " hx");
            sql.append(" WHERE dx.MaLoaiXe = lx.MaLoaiXe");
            sql.append(" AND lx.MaHangXe = hx.MaHangXe");
            sql.append(" AND dx.MaDongXe LIKE ?");
            sql.append(" AND dx.TenDongXe LIKE ?");
            sql.append(" ORDER BY dx.MaDongXe");

            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, "%" + maDongXe + "%");
            stmt.setString(2, "%" + tenDongXe + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                DongXeDTO dongxeDTO = new DongXeDTO();
                dongxeDTO.setMaDongXe(rs.getString("MaDongXe"));
                dongxeDTO.setTenDongXe(rs.getString("TenDongXe"));
                dongxeDTO.setSoXyLanh(rs.getInt("SoXyLanh"));
                dongxeDTO.setDongCo(rs.getString("DongCo"));
                dongxeDTO.setHopSo(rs.getString("HopSo"));
                dongxeDTO.setSoCua(rs.getInt("SoCua"));
                dongxeDTO.setNhienLieu(rs.getString("NhienLieu"));
                dongxeDTO.setNamSX(rs.getString("NamSanXuat"));
                dongxeDTO.setMaLoaiXe(rs.getString("MaLoaiXe"));
                dongxeDTO.setTenLoaiXe(rs.getString("TenLoaiXe"));
                dongxeDTO.setMaHangXe(rs.getString("MaHangXe"));
                dongxeDTO.setTenHangXe(rs.getString("TenHangXe"));
                dsDongXeDTO.add(dongxeDTO);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return dsDongXeDTO;
    }
    
    public List<DongXeDTO> getDSDongXeTheoLX(String maLoaiXe) throws Exception {
        ArrayList<DongXeDTO> dsDongXeDTO = new ArrayList<>();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + Database.Name + "." + DBTable.DongXe + " dx,");
            sql.append(Database.Name + "." + DBTable.LoaiXe + " lx,");
            sql.append(Database.Name + "." + DBTable.HangXe + " hx");
            sql.append(" WHERE dx.MaLoaiXe = lx.MaLoaiXe");
            sql.append(" AND lx.MaHangXe = hx.MaHangXe");
            sql.append(" AND dx.MaLoaiXe = ?");
            sql.append(" ORDER BY dx.MaDongXe");

            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, maLoaiXe);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                DongXeDTO dongxeDTO = new DongXeDTO();
                dongxeDTO.setMaDongXe(rs.getString("MaDongXe"));
                dongxeDTO.setTenDongXe(rs.getString("TenDongXe"));
                dongxeDTO.setSoXyLanh(rs.getInt("SoXyLanh"));
                dongxeDTO.setDongCo(rs.getString("DongCo"));
                dongxeDTO.setHopSo(rs.getString("HopSo"));
                dongxeDTO.setSoCua(rs.getInt("SoCua"));
                dongxeDTO.setNhienLieu(rs.getString("NhienLieu"));
                dongxeDTO.setNamSX(rs.getString("NamSanXuat"));
                dongxeDTO.setMaLoaiXe(rs.getString("MaLoaiXe"));
                dongxeDTO.setTenLoaiXe(rs.getString("TenLoaiXe"));
                dongxeDTO.setMaHangXe(rs.getString("MaHangXe"));
                dongxeDTO.setTenHangXe(rs.getString("TenHangXe"));               
                dsDongXeDTO.add(dongxeDTO);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return dsDongXeDTO;
    }
    
    public List<DongXeDTO> getDSDongXeTheoLX(String maLoaiXe, String maHangXe) throws Exception {
        ArrayList<DongXeDTO> dsDongXeDTO = new ArrayList<>();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + Database.Name + "." + DBTable.DongXe + " dx,");
            sql.append(Database.Name + "." + DBTable.LoaiXe + " lx,");
            sql.append(Database.Name + "." + DBTable.HangXe + " hx");
            sql.append(" WHERE dx.MaLoaiXe = lx.MaLoaiXe");
            sql.append(" AND lx.MaHangXe = hx.MaHangXe");
            sql.append(" AND lx.MaLoaiXe = ?");
            sql.append(" AND hx.MaHangXe = ?");
            sql.append(" ORDER BY dx.MaDongXe");

            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, maLoaiXe);
            stmt.setString(2, maHangXe);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                DongXeDTO dongxeDTO = new DongXeDTO();
                dongxeDTO.setMaDongXe(rs.getString("MaDongXe"));
                dongxeDTO.setTenDongXe(rs.getString("TenDongXe"));
                dongxeDTO.setSoXyLanh(rs.getInt("SoXyLanh"));
                dongxeDTO.setDongCo(rs.getString("DongCo"));
                dongxeDTO.setHopSo(rs.getString("HopSo"));
                dongxeDTO.setSoCua(rs.getInt("SoCua"));
                dongxeDTO.setNhienLieu(rs.getString("NhienLieu"));
                dongxeDTO.setNamSX(rs.getString("NamSanXuat"));
                dongxeDTO.setMaLoaiXe(rs.getString("MaLoaiXe"));
                dongxeDTO.setTenLoaiXe(rs.getString("TenLoaiXe"));
                dongxeDTO.setMaHangXe(rs.getString("MaHangXe"));
                dongxeDTO.setTenHangXe(rs.getString("TenHangXe"));               
                dsDongXeDTO.add(dongxeDTO);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return dsDongXeDTO;
    }
    
    public List<DongXeDTO> getDSTatCaDongXe() throws Exception {

        ArrayList<DongXeDTO> dsDongXeDTO = new ArrayList<DongXeDTO>();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + Database.Name + "." + DBTable.DongXe + " dx,");
            
            sql.append(Database.Name + "." + DBTable.LoaiXe + " lx,");
            sql.append(Database.Name + "." + DBTable.HangXe + " hx");
            sql.append(" WHERE dx.MaLoaiXe = lx.MaLoaiXe");
            sql.append(" AND lx.MaHangXe = hx.MaHangXe");
            sql.append(" ORDER BY dx.MaDongXe");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                DongXeDTO dongxeDTO = new DongXeDTO();
                dongxeDTO.setMaDongXe(rs.getString("MaDongXe"));
                dongxeDTO.setTenDongXe(rs.getString("TenDongXe"));
                dongxeDTO.setSoXyLanh(rs.getInt("SoXyLanh"));
                dongxeDTO.setDongCo(rs.getString("DongCo"));
                dongxeDTO.setHopSo(rs.getString("HopSo"));
                dongxeDTO.setSoCua(rs.getInt("SoCua"));
                dongxeDTO.setNhienLieu(rs.getString("NhienLieu"));
                dongxeDTO.setNamSX(rs.getString("NamSanXuat"));
                dongxeDTO.setMaLoaiXe(rs.getString("MaLoaiXe"));
                dongxeDTO.setTenLoaiXe(rs.getString("TenLoaiXe"));
                dongxeDTO.setMaHangXe(rs.getString("MaHangXe"));
                dongxeDTO.setTenHangXe(rs.getString("TenHangXe"));                  
                dsDongXeDTO.add(dongxeDTO);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return dsDongXeDTO;
    }
    
    public int createDongXe(DongXeDTO dongxeDTO) throws Exception {
        int result = 0;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" INSERT INTO " + Database.Name + "." + DBTable.DongXe);
            sql.append(" (MaDongXe, TenDongXe, SoXyLanh, DongCo, HopSo, SoCua, NhienLieu, NamSanXuat, MaLoaiXe) ");
            sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, dongxeDTO.getMaDongXe());
            stmt.setString(2, dongxeDTO.getTenDongXe());      
            stmt.setInt(3, dongxeDTO.getSoXyLanh());
            stmt.setString(4, dongxeDTO.getDongCo());
            stmt.setString(5, dongxeDTO.getHopSo());
            stmt.setInt(6, dongxeDTO.getSoCua());
            stmt.setString(7, dongxeDTO.getNhienLieu());
            stmt.setString(8, dongxeDTO.getNamSX());
            stmt.setString(9, dongxeDTO.getMaLoaiXe());
            
            result = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
            throw e;
        }
        return result;
    }
    
    /**
     * @param maDongXe
     * @return: 0 --> nothing; row count for SQL statement.
     * @throws Exception
     */
    public int deleteDongXe(String maDongXe) throws Exception {
        int result;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" DELETE FROM " + Database.Name + "." + DBTable.DongXe);
            sql.append(" WHERE MaDongXe = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, maDongXe);
            result = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
            throw e;
        }
        return result;
    }

    public int updateDongXe(DongXeDTO dongxeDTO) throws Exception {
        int result = 0;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" UPDATE " + Database.Name + "." + DBTable.DongXe);
            sql.append(" SET TenDongXe = ?, SoXyLanh = ?, DongCo = ?, HopSo = ?, SoCua = ?, "
                    + "NhienLieu = ?, NamSanXuat = ?, MaLoaiXe = ?");
            sql.append(" WHERE MaDongXe = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, dongxeDTO.getTenDongXe());
            stmt.setInt(2, dongxeDTO.getSoXyLanh());
            stmt.setString(3, dongxeDTO.getDongCo());
            stmt.setString(4, dongxeDTO.getHopSo());
            stmt.setInt(5, dongxeDTO.getSoCua());
            stmt.setString(6, dongxeDTO.getNhienLieu());
            stmt.setString(7, dongxeDTO.getNamSX());            
            stmt.setString(8, dongxeDTO.getMaLoaiXe());
            stmt.setString(9, dongxeDTO.getMaDongXe());            
            result = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
            throw e;
        }
        return result;
    }
}
