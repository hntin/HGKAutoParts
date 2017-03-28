package hgksoft.acquy.dbaccess;

import hgksoft.acquy.constant.DBTable;
import hgksoft.acquy.constant.Database;
import hgksoft.acquy.dto.TinhTrangDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HNTIN
 */
public class TinhTrangMapper extends DBMapper {
    public TinhTrangMapper() throws Exception {
        super();
    }
    
    public int createTinhTrang(TinhTrangDTO ttDTO) throws Exception {
        int result = 0;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" INSERT INTO " + Database.Name + "." + DBTable.TINHTRANG);
            sql.append(" (MaTinhTrang, TenTinhTrang, Email, DienThoai, DiaChi, GhiChu) ");
            sql.append(" VALUES (?, ?, ?, ?, ?, ?)");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, ttDTO.getMaTinhTrang());
            stmt.setString(2, ttDTO.getTenTinhTrang());
            result = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
            throw e;
        }
        return result;
    }

    public int updateTinhTrang(TinhTrangDTO ttDTO) throws Exception {
        int result = 0;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" UPDATE " + Database.Name + "." + DBTable.TINHTRANG);
            sql.append(" SET TenTinhTrang = ?, Email = ?, DienThoai = ?, DiaChi = ?, GhiChu = ?");
            sql.append(" WHERE MaTinhTrang = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, ttDTO.getTenTinhTrang());
            stmt.setString(6, ttDTO.getMaTinhTrang());
            
            result = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
            throw e;
        }
        return result;
    }
    
    public int deleteTinhTrang(String maTinhTrang) throws Exception {
        int result;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" DELETE FROM " + Database.Name + "." + DBTable.TINHTRANG);
            sql.append(" WHERE MaTinhTrang = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, maTinhTrang);
            result = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
            throw e;
        }
        return result;
    }
    
    public TinhTrangDTO getTinhTrangDTO(String maTinhTrang) throws Exception {
        TinhTrangDTO ttDTO = new TinhTrangDTO();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + Database.Name + "." + DBTable.TINHTRANG + " tt");
            sql.append(" WHERE tt.MaTinhTrang = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, maTinhTrang);
            ResultSet rs = stmt.executeQuery();
            if ((rs != null) && (rs.next())) {
                ttDTO.setMaTinhTrang(maTinhTrang);
                ttDTO.setTenTinhTrang(rs.getString("TenTinhTrang"));
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return ttDTO;
    }

    public List<TinhTrangDTO> getDSTinhTrang(
            String maTinhTrang,
            String tenTinhTrang) throws Exception {

        ArrayList<TinhTrangDTO> dsTinhTrangDTO = new ArrayList<TinhTrangDTO>();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + Database.Name + "." + DBTable.TINHTRANG + " tt");
            sql.append(" WHERE tt.MaTinhTrang LIKE ?");
            sql.append(" AND tt.TenTinhTrang LIKE ?");
            sql.append(" ORDER BY tt.MaTinhTrang");

            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, "%" + maTinhTrang + "%");
            stmt.setString(2, "%" + tenTinhTrang + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TinhTrangDTO ttDTO = new TinhTrangDTO();
                ttDTO.setMaTinhTrang(rs.getString("MaTinhTrang"));
                ttDTO.setTenTinhTrang(rs.getString("TenTinhTrang"));
                dsTinhTrangDTO.add(ttDTO);
            }
            
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return dsTinhTrangDTO;
    }
    
    public List<TinhTrangDTO> getTatCaTinhTrang() throws Exception {

        ArrayList<TinhTrangDTO> dsTinhTrangDTO = new ArrayList<TinhTrangDTO>();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + Database.Name + "." + DBTable.TINHTRANG + " tt");
            sql.append(" ORDER BY tt.MaTinhTrang");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TinhTrangDTO ttDTO = new TinhTrangDTO();
                ttDTO.setMaTinhTrang(rs.getString("MaTinhTrang"));
                ttDTO.setTenTinhTrang(rs.getString("TenTinhTrang"));
                dsTinhTrangDTO.add(ttDTO);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return dsTinhTrangDTO;
    }
    
    
}
