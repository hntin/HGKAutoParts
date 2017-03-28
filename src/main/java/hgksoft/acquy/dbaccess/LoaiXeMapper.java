package hgksoft.acquy.dbaccess;

import hgksoft.acquy.constant.DBTable;
import hgksoft.acquy.constant.Database;
import hgksoft.acquy.dto.LoaiXeDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HNTIN
 */
public class LoaiXeMapper extends DBMapper {

    public LoaiXeMapper() throws Exception {
        super();
    }
   
    public LoaiXeDTO getLoaiXeDTO(String maLoaiXe) throws Exception {
        LoaiXeDTO loaixeDTO = new LoaiXeDTO();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + Database.Name + "." + DBTable.LoaiXe + " lx,");
            sql.append(Database.Name + "." + DBTable.HangXe + " hx");
            sql.append(" WHERE lx.MaHangXe = hx.MaHangXe");
            sql.append(" AND lx.MaLoaiXe = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, maLoaiXe);
            ResultSet rs = stmt.executeQuery();
            if ((rs != null) && (rs.next())) {
                loaixeDTO.setMaLoaiXe(maLoaiXe);
                loaixeDTO.setTenLoaiXe(rs.getString("TenLoaiXe"));
                loaixeDTO.setMaHangXe(rs.getString("MaHangXe"));
                loaixeDTO.setTenHangXe(rs.getString("TenHangXe"));
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return loaixeDTO;
    }

    public List<LoaiXeDTO> getDSLoaiXe(
            String maLoaiXe,
            String tenLoaiXe) throws Exception {

        ArrayList<LoaiXeDTO> dsLoaiXeDTO = new ArrayList<LoaiXeDTO>();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + Database.Name + "." + DBTable.LoaiXe + " lx,");
            sql.append(Database.Name + "." + DBTable.HangXe + " hx");
            sql.append(" WHERE lx.MaHangXe = hx.MaHangXe");
            sql.append(" AND lx.MaLoaiXe LIKE ?");
            sql.append(" AND lx.TenLoaiXe LIKE ?");
            sql.append(" ORDER BY lx.MaLoaiXe");

            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, "%" + maLoaiXe + "%");
            stmt.setString(2, "%" + tenLoaiXe + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                LoaiXeDTO loaixeDTO = new LoaiXeDTO();
                loaixeDTO.setMaLoaiXe(rs.getString("MaLoaiXe"));
                loaixeDTO.setTenLoaiXe(rs.getString("TenLoaiXe"));
                loaixeDTO.setMaHangXe(rs.getString("MaHangXe"));
                loaixeDTO.setTenHangXe(rs.getString("TenHangXe"));
                dsLoaiXeDTO.add(loaixeDTO);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return dsLoaiXeDTO;
    }
    
    public List<LoaiXeDTO> getDSLoaiXe(
            String maHangXe) throws Exception {

        ArrayList<LoaiXeDTO> dsLoaiXeDTO = new ArrayList<LoaiXeDTO>();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + Database.Name + "." + DBTable.LoaiXe + " lx,");
            sql.append(Database.Name + "." + DBTable.HangXe + " hx");
            sql.append(" WHERE lx.MaHangXe = hx.MaHangXe");
            sql.append(" AND lx.MaHangXe = ?");
            sql.append(" ORDER BY lx.MaLoaiXe");

            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, maHangXe);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                LoaiXeDTO loaixeDTO = new LoaiXeDTO();
                loaixeDTO.setMaLoaiXe(rs.getString("MaLoaiXe"));
                loaixeDTO.setTenLoaiXe(rs.getString("TenLoaiXe"));
                loaixeDTO.setMaHangXe(rs.getString("MaHangXe"));
                loaixeDTO.setTenHangXe(rs.getString("TenHangXe"));
                dsLoaiXeDTO.add(loaixeDTO);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return dsLoaiXeDTO;
    }
    
    public List<LoaiXeDTO> getDSTatCaLoaiXe() throws Exception {

        ArrayList<LoaiXeDTO> dsLoaiXeDTO = new ArrayList<>();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + Database.Name + "." + DBTable.LoaiXe + " lx,");           
            sql.append(Database.Name + "." + DBTable.HangXe + " hx");
            sql.append(" WHERE lx.MaHangXe = hx.MaHangXe");
            sql.append(" ORDER BY lx.MaHangXe, lx.MaLoaiXe");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                LoaiXeDTO loaixeDTO = new LoaiXeDTO();
                loaixeDTO.setMaLoaiXe(rs.getString("MaLoaiXe"));
                loaixeDTO.setTenLoaiXe(rs.getString("TenLoaiXe"));
                loaixeDTO.setMaHangXe(rs.getString("MaHangXe"));
                loaixeDTO.setTenHangXe(rs.getString("TenHangXe"));
                dsLoaiXeDTO.add(loaixeDTO);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return dsLoaiXeDTO;
    }
    
    public int createLoaiXe(LoaiXeDTO loaixeDTO) throws Exception {
        int result = 0;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" INSERT INTO " + Database.Name + "." + DBTable.LoaiXe);
            sql.append(" (MaLoaiXe, TenLoaiXe, MaHangXe) ");
            sql.append(" VALUES (?, ?, ?)");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, loaixeDTO.getMaLoaiXe());
            stmt.setString(2, loaixeDTO.getTenLoaiXe());      
            stmt.setString(3, loaixeDTO.getMaHangXe());  
            result = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
            throw e;
        }
        return result;
    }
    
    /**
     * @param maLoaiXe
     * @return: 0 --> nothing; row count for SQL statement.
     * @throws Exception
     */
    public int deleteLoaiXe(String maLoaiXe) throws Exception {
        int result;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" DELETE FROM " + Database.Name + "." + DBTable.LoaiXe);
            sql.append(" WHERE MaLoaiXe = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, maLoaiXe);
            result = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
            throw e;
        }
        return result;
    }

    public int updateLoaiXe(LoaiXeDTO loaixeDTO) throws Exception {
        int result = 0;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" UPDATE " + Database.Name + "." + DBTable.LoaiXe);
            sql.append(" SET TenLoaiXe = ?, MaHangXe = ?");
            sql.append(" WHERE MaLoaiXe = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, loaixeDTO.getTenLoaiXe());
            stmt.setString(2, loaixeDTO.getMaHangXe());
            stmt.setString(3, loaixeDTO.getMaLoaiXe());            
            result = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
            throw e;
        }
        return result;
    }
}
