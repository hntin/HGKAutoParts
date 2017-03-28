package hgksoft.acquy.dbaccess;

import hgksoft.acquy.constant.DBTable;
import hgksoft.acquy.constant.Database;
import hgksoft.acquy.dto.NhaCCDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HNTIN
 */
public class NhaCungCapMapper extends DBMapper {

    public NhaCungCapMapper() throws Exception {
        super();
    }
    
    public NhaCCDTO getNhaCCDTO(String maNhaCC) throws Exception {
        NhaCCDTO nccDTO = new NhaCCDTO();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + Database.Name + "." + DBTable.NhaCC + " ncc");
            sql.append(" WHERE ncc.MaNhaCC = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, maNhaCC);
            ResultSet rs = stmt.executeQuery();
            if ((rs != null) && (rs.next())) {
                nccDTO.setMaNhaCC(maNhaCC);
                nccDTO.setTenNhaCC(rs.getString("TenNhaCC"));
                nccDTO.setEmail(rs.getString("Email"));
                nccDTO.setDienThoai(rs.getString("DienThoai"));
                nccDTO.setDiaChi(rs.getString("DiaChi"));
                nccDTO.setGhiChu(rs.getString("GhiChu"));
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return nccDTO;
    }

    /**
     * @param maNhaCC
     * @return: 0 --> nothing; row count for SQL statement.
     * @throws Exception
     */
    public int deleteNhaCC(String maNhaCC) throws Exception {
        int result;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" DELETE FROM " + Database.Name + "." + DBTable.NhaCC);
            sql.append(" WHERE MaNhaCC = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, maNhaCC);
            result = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
            throw e;
        }
        return result;
    }

    public List<NhaCCDTO> getDSNhaCC(
            String maNhaCC,
            String tenNhaCC) throws Exception {

        ArrayList<NhaCCDTO> dsNhaCCDTO = new ArrayList<NhaCCDTO>();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + Database.Name + "." + DBTable.NhaCC + " ncc");
            sql.append(" WHERE ncc.MaNhaCC LIKE ?");
            sql.append(" AND ncc.TenNhaCC LIKE ?");
            sql.append(" ORDER BY ncc.MaNhaCC");

            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, "%" + maNhaCC + "%");
            stmt.setString(2, "%" + tenNhaCC + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                NhaCCDTO nccDTO = new NhaCCDTO();
                nccDTO.setMaNhaCC(rs.getString("MaNhaCC"));
                nccDTO.setTenNhaCC(rs.getString("TenNhaCC"));
                nccDTO.setEmail(rs.getString("Email"));
                nccDTO.setDienThoai(rs.getString("DienThoai"));
                nccDTO.setDiaChi(rs.getString("DiaChi"));
                nccDTO.setGhiChu(rs.getString("GhiChu"));
                dsNhaCCDTO.add(nccDTO);
            }
            
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return dsNhaCCDTO;
    }
    
    public List<NhaCCDTO> getTatCaNhaCC() throws Exception {

        ArrayList<NhaCCDTO> dsNhaCCDTO = new ArrayList<NhaCCDTO>();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + Database.Name + "." + DBTable.NhaCC + " ncc");
            sql.append(" ORDER BY ncc.MaNhaCC");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                NhaCCDTO nccDTO = new NhaCCDTO();
                nccDTO.setMaNhaCC(rs.getString("MaNhaCC"));
                nccDTO.setTenNhaCC(rs.getString("TenNhaCC"));
                nccDTO.setEmail(rs.getString("Email"));
                nccDTO.setDienThoai(rs.getString("DienThoai"));
                nccDTO.setDiaChi(rs.getString("DiaChi"));
                nccDTO.setGhiChu(rs.getString("GhiChu"));
                dsNhaCCDTO.add(nccDTO);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return dsNhaCCDTO;
    }
    
    public int createNhaCC(NhaCCDTO nccDTO) throws Exception {
        int result = 0;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" INSERT INTO " + Database.Name + "." + DBTable.NhaCC);
            sql.append(" (MaNhaCC, TenNhaCC, Email, DienThoai, DiaChi, GhiChu) ");
            sql.append(" VALUES (?, ?, ?, ?, ?, ?)");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, nccDTO.getMaNhaCC());
            stmt.setString(2, nccDTO.getTenNhaCC());
            stmt.setString(3, nccDTO.getEmail());
            stmt.setString(4, nccDTO.getDienThoai());
            stmt.setString(5, nccDTO.getDiaChi());            
            stmt.setString(6, nccDTO.getGhiChu());
            result = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
            throw e;
        }
        return result;
    }

    public int updateNhaCC(NhaCCDTO nccDTO) throws Exception {
        int result = 0;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" UPDATE " + Database.Name + "." + DBTable.NhaCC);
            sql.append(" SET TenNhaCC = ?, Email = ?, DienThoai = ?, DiaChi = ?, GhiChu = ?");
            sql.append(" WHERE MaNhaCC = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, nccDTO.getTenNhaCC());
            stmt.setString(2, nccDTO.getEmail());
            stmt.setString(3, nccDTO.getDienThoai());
            stmt.setString(4, nccDTO.getDiaChi());            
            stmt.setString(5, nccDTO.getGhiChu());
            stmt.setString(6, nccDTO.getMaNhaCC());
            
            result = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
            throw e;
        }
        return result;
    }
}
