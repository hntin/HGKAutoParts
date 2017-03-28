package hgksoft.acquy.dbaccess;

import hgksoft.acquy.constant.DBTable;
import hgksoft.acquy.constant.Database;
import hgksoft.acquy.dto.HangXeDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HNTIN
 */
public class HangXeMapper extends DBMapper {

    public HangXeMapper() throws Exception {
        super();
    }
   
    public HangXeDTO getHangXeDTO(String maHangXe) throws Exception {
        HangXeDTO hangxeDTO = new HangXeDTO();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + Database.Name + "." + DBTable.HangXe + " hx");
            sql.append(" WHERE hx.MaHangXe = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, maHangXe);
            ResultSet rs = stmt.executeQuery();
            if ((rs != null) && (rs.next())) {
                hangxeDTO.setMaHangXe(maHangXe);
                hangxeDTO.setTenHangXe(rs.getString("TenHangXe"));
                hangxeDTO.setGhiChu(rs.getString("GhiChu"));
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return hangxeDTO;
    }

    public List<HangXeDTO> getDSHangXe(
            String maHangXe,
            String tenHangXe) throws Exception {

        ArrayList<HangXeDTO> dsHangXeDTO = new ArrayList<HangXeDTO>();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + Database.Name + "." + DBTable.HangXe + " hx");
            sql.append(" WHERE hx.MaHangXe LIKE ?");
            sql.append(" AND hx.TenHangXe LIKE ?");
            sql.append(" ORDER BY hx.MaHangXe");

            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, "%" + maHangXe + "%");
            stmt.setString(2, "%" + tenHangXe + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                HangXeDTO hangxeDTO = new HangXeDTO();
                hangxeDTO.setMaHangXe(rs.getString("MaHangXe"));
                hangxeDTO.setTenHangXe(rs.getString("TenHangXe"));
                hangxeDTO.setGhiChu(rs.getString("GhiChu"));
                dsHangXeDTO.add(hangxeDTO);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return dsHangXeDTO;
    }
    
    public List<HangXeDTO> getDSTatCaHangXe() throws Exception {

        ArrayList<HangXeDTO> dsHangXeDTO = new ArrayList<HangXeDTO>();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + Database.Name + "." + DBTable.HangXe + " hx");
            sql.append(" ORDER BY hx.MaHangXe");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                HangXeDTO hangxeDTO = new HangXeDTO();
                hangxeDTO.setMaHangXe(rs.getString("MaHangXe"));
                hangxeDTO.setTenHangXe(rs.getString("TenHangXe"));
                hangxeDTO.setGhiChu(rs.getString("GhiChu"));
                dsHangXeDTO.add(hangxeDTO);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return dsHangXeDTO;
    }
    
    public int createHangXe(HangXeDTO hangxeDTO) throws Exception {
        int result = 0;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" INSERT INTO " + Database.Name + "." + DBTable.HangXe);
            sql.append(" (MaHangXe, TenHangXe, GhiChu) ");
            sql.append(" VALUES (?, ?, ?)");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, hangxeDTO.getMaHangXe());
            stmt.setString(2, hangxeDTO.getTenHangXe());      
            stmt.setString(3, hangxeDTO.getGhiChu());
            result = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
            throw e;
        }
        return result;
    }
    
    /**
     * @param maHangXe
     * @return: 0 --> nothing; row count for SQL statement.
     * @throws Exception
     */
    public int deleteHangXe(String maHangXe) throws Exception {
        int result;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" DELETE FROM " + Database.Name + "." + DBTable.HangXe);
            sql.append(" WHERE MaHangXe = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, maHangXe);
            result = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
            throw e;
        }
        return result;
    }

    public int updateHangXe(HangXeDTO hangxeDTO) throws Exception {
        int result = 0;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" UPDATE " + Database.Name + "." + DBTable.HangXe);
            sql.append(" SET TenHangXe = ?, GhiChu = ?");
            sql.append(" WHERE MaHangXe = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, hangxeDTO.getTenHangXe());
            stmt.setString(2, hangxeDTO.getGhiChu());
            stmt.setString(3, hangxeDTO.getMaHangXe());            
            result = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
            throw e;
        }
        return result;
    }
}
