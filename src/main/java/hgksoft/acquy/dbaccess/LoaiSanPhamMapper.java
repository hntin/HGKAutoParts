package hgksoft.acquy.dbaccess;

import hgksoft.acquy.constant.DBTable;
import hgksoft.acquy.constant.Database;
import hgksoft.acquy.dto.LoaiSanPhamDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HNTIN
 */
public class LoaiSanPhamMapper extends DBMapper {

    public LoaiSanPhamMapper() throws Exception {
        super();
    }

    public LoaiSanPhamDTO getRootLSP() throws Exception {
        LoaiSanPhamDTO loaiSPDTO = new LoaiSanPhamDTO();
        PreparedStatement stmt_1 = null;

        try {
            StringBuffer sql_1 = new StringBuffer();
            sql_1.append(" SELECT * FROM " + Database.Name + "." + DBTable.LOAISP + " lsp");
            sql_1.append(" WHERE lsp.MaLoaiCha = ?");
            stmt_1 = getConnection().prepareStatement(sql_1.toString());
            stmt_1.setInt(1, -1);
            ResultSet rs = stmt_1.executeQuery();
            if ((rs != null) && (rs.next())) {
                loaiSPDTO.setMaLSP(rs.getInt("MaLoaiSanPham"));
                loaiSPDTO.setTenLSP(rs.getString("TenLoaiSanPham"));
                loaiSPDTO.setMaLoaiSPCha(rs.getInt("MaLoaiCha"));
            }
            if (stmt_1 != null) {
                stmt_1.close();
            }

        } catch (Exception e) {
            throw e;
        }
        return loaiSPDTO;
    }

    public LoaiSanPhamDTO getLoaiSanPhamDTO(int maLoaiSanPham) throws Exception {
        LoaiSanPhamDTO loaiSPDTO = new LoaiSanPhamDTO();
        PreparedStatement stmt = null;

        try {
            StringBuffer sql_1 = new StringBuffer();
            sql_1.append(" SELECT * FROM " + Database.Name + "." + DBTable.LOAISP + " lsp");
            sql_1.append(" WHERE lsp.MaLoaiSanPham = ?");
            stmt = getConnection().prepareStatement(sql_1.toString());
            stmt.setInt(1, maLoaiSanPham);
            ResultSet rs = stmt.executeQuery();
            if ((rs != null) && (rs.next())) {
                loaiSPDTO.setMaLSP(rs.getInt("MaLoaiSanPham"));
                loaiSPDTO.setTenLSP(rs.getString("TenLoaiSanPham"));
                loaiSPDTO.setMaLoaiSPCha(rs.getInt("MaLoaiCha"));
            }

            stmt.close();
        } catch (Exception e) {
            throw e;
        }
        return loaiSPDTO;
    }

    public int createLoaiSanPham(LoaiSanPhamDTO loaiSPDTO) throws Exception {
        int result = 0;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" INSERT INTO " + Database.Name + "." + DBTable.SP);
            sql.append(" (MaLoaiSanPham, TenLoaiSanPham, MaLoaiCha) ");
            sql.append(" VALUES (?, ?, ?)");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setInt(1, loaiSPDTO.getMaLSP());
            stmt.setString(2, loaiSPDTO.getTenLSP());
            stmt.setInt(3, loaiSPDTO.getMaLoaiSPCha());
            result = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
            throw e;
        }
        return result;
    }

    public int updateLoaiSanPham(LoaiSanPhamDTO loaiSPDTO) throws Exception {
        int result = 0;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" UPDATE " + Database.Name + "." + DBTable.LOAISP);
            sql.append(" SET TenLoaiSanPham = ?, MaLoaiCha = ?");
            sql.append(" WHERE MaLoaiSanPham = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, loaiSPDTO.getTenLSP());
            stmt.setInt(2, loaiSPDTO.getMaLoaiSPCha());
            stmt.setInt(3, loaiSPDTO.getMaLSP());
            result = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
            throw e;
        }
        return result;
    }

    public int deleteLoaiSanPham(String maLoaiSanPham) throws Exception {
        int result;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" DELETE FROM " + Database.Name + "." + DBTable.LOAISP + " lsp");
            sql.append(" WHERE lsp.MaLoaiSanPham = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, maLoaiSanPham);
            result = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
            throw e;
        }
        return result;
    }

    public List<LoaiSanPhamDTO> getDSTatCaLoaiSanPham() throws Exception {

        ArrayList<LoaiSanPhamDTO> dsLoaiSanPhamDTO = new ArrayList<LoaiSanPhamDTO>();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + Database.Name + "." + DBTable.LOAISP + " lsp");
            sql.append(" ORDER BY lsp.MaLoaiSanPham");

            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if (rs.getInt("MaLoaiCha") != -1) { // Bỏ qua nút gốc
                    LoaiSanPhamDTO loaiSPDTO = new LoaiSanPhamDTO();
                    loaiSPDTO.setMaLSP(rs.getInt("MaLoaiSanPham"));
                    loaiSPDTO.setTenLSP(rs.getString("TenLoaiSanPham"));
                    loaiSPDTO.setMaLoaiSPCha(rs.getInt("MaLoaiCha"));
                    dsLoaiSanPhamDTO.add(loaiSPDTO);
                }
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return dsLoaiSanPhamDTO;
    }

    public List<LoaiSanPhamDTO> getDSLoaiSanPhamMucDuoi(int maLoaiCha) throws Exception {

        ArrayList<LoaiSanPhamDTO> dsLoaiSanPhamDTO = new ArrayList<LoaiSanPhamDTO>();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + Database.Name + "." + DBTable.LOAISP + " lsp");
            sql.append(" WHERE lsp.MaLoaiCha = ?");
            sql.append(" ORDER BY lsp.MaLoaiSanPham");

            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setInt(1, maLoaiCha);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                LoaiSanPhamDTO loaiSPDTO = new LoaiSanPhamDTO();
                loaiSPDTO.setMaLSP(rs.getInt("MaLoaiSanPham"));
                loaiSPDTO.setTenLSP(rs.getString("TenLoaiSanPham"));
                loaiSPDTO.setMaLoaiSPCha(rs.getInt("MaLoaiCha"));
                dsLoaiSanPhamDTO.add(loaiSPDTO);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return dsLoaiSanPhamDTO;
    }

    public List<LoaiSanPhamDTO> getDSLoaiSanPham(String mucLoaiSanPham) throws Exception {

        ArrayList<LoaiSanPhamDTO> dsLoaiSanPhamDTO = new ArrayList<LoaiSanPhamDTO>();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + Database.Name + "." + DBTable.LOAISP + " lsp");
            sql.append(" WHERE lsp.MucLoaiSanPham = ?");
            sql.append(" ORDER BY lsp.MaLoaiSanPham");

            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, mucLoaiSanPham);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                LoaiSanPhamDTO loaiSPDTO = new LoaiSanPhamDTO();
                loaiSPDTO.setMaLSP(rs.getInt("MaLoaiSanPham"));
                loaiSPDTO.setTenLSP(rs.getString("TenLoaiSanPham"));
                loaiSPDTO.setMaLoaiSPCha(rs.getInt("MaLoaiCha"));
                dsLoaiSanPhamDTO.add(loaiSPDTO);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return dsLoaiSanPhamDTO;
    }

}
