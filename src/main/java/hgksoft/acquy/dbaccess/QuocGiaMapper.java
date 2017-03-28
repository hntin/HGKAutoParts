/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hgksoft.acquy.dbaccess;

import hgksoft.acquy.constant.DBTable;
import hgksoft.acquy.constant.Database;
import hgksoft.acquy.dto.QuocGiaDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HNTIN
 */
public class QuocGiaMapper extends DBMapper{

    public QuocGiaMapper() throws Exception {
        super();
    }
   
    public QuocGiaDTO getQuocGiaDTO(String maQuocGia) throws Exception {
        QuocGiaDTO quocgiaDTO = new QuocGiaDTO();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + Database.Name + "." + DBTable.QuocGia + " qg");
            sql.append(" WHERE qg.MaQuocGia = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, maQuocGia);
            ResultSet rs = stmt.executeQuery();
            if ((rs != null) && (rs.next())) {
                quocgiaDTO.setMaQuocGia(maQuocGia);
                quocgiaDTO.setTenQuocGia(rs.getString("TenQuocGia"));
                quocgiaDTO.setGhiChu(rs.getString("GhiChu"));
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return quocgiaDTO;
    }

    /**
     * @param maQuocGia
     * @return: 0 --> nothing; row count for SQL statement.
     * @throws Exception
     */
    public int deleteQuocGia(String maQuocGia) throws Exception {
        int result;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" DELETE FROM " + Database.Name + "." + DBTable.QuocGia);
            sql.append(" WHERE MaQuocGia = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, maQuocGia);
            result = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
            throw e;
        }
        return result;
    }

    public List<QuocGiaDTO> getDSQuocGia(
            String maQuocGia,
            String tenQuocGia) throws Exception {

        ArrayList<QuocGiaDTO> dsQuocGiaDTO = new ArrayList<QuocGiaDTO>();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + Database.Name + "." + DBTable.QuocGia + " qg");
            sql.append(" WHERE qg.MaQuocGia LIKE ?");
            sql.append(" AND qg.TenQuocGia LIKE ?");
            sql.append(" ORDER BY qg.MaQuocGia");

            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, "%" + maQuocGia + "%");
            stmt.setString(2, "%" + tenQuocGia + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                QuocGiaDTO quocgiaDTO = new QuocGiaDTO();
                quocgiaDTO.setMaQuocGia(rs.getString("MaQuocGia"));
                quocgiaDTO.setTenQuocGia(rs.getString("TenQuocGia"));
                quocgiaDTO.setGhiChu(rs.getString("GhiChu"));
                dsQuocGiaDTO.add(quocgiaDTO);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return dsQuocGiaDTO;
    }
    
    public List<QuocGiaDTO> getDSTatCaQuocGia() throws Exception {

        ArrayList<QuocGiaDTO> dsQuocGiaDTO = new ArrayList<QuocGiaDTO>();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + Database.Name + "." + DBTable.QuocGia + " qg");
            sql.append(" ORDER BY qg.MaQuocGia");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                QuocGiaDTO quocgiaDTO = new QuocGiaDTO();
                quocgiaDTO.setMaQuocGia(rs.getString("MaQuocGia"));
                quocgiaDTO.setTenQuocGia(rs.getString("TenQuocGia"));
                quocgiaDTO.setGhiChu(rs.getString("GhiChu"));
                dsQuocGiaDTO.add(quocgiaDTO);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return dsQuocGiaDTO;
    }

}
