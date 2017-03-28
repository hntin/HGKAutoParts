package hgksoft.acquy.dbaccess;

import hgksoft.acquy.constant.DBTable;
import hgksoft.acquy.constant.Database;
import hgksoft.acquy.dto.NguoiDungDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HNTIN
 */
public class NguoiDungMapper extends DBMapper {

    public NguoiDungMapper() throws Exception {
        super();
    }

    public boolean isExistNguoiDung(String maNguoiDung, String matKhau) throws Exception {
        boolean isExist = false;

        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + Database.Name + "." + DBTable.NguoiDung + " nd");
            sql.append(" WHERE nd.MaNguoiDung = ?");
            sql.append(" AND nd.MatKhau = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, maNguoiDung);
            stmt.setString(2, matKhau);
            ResultSet rs = stmt.executeQuery();
            if ((rs != null) && (rs.next())) {
                isExist = true;
            }
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
            throw e;
        }
        return isExist;
    }

    public NguoiDungDTO getNguoiDungDTO(String maNguoiDung) throws Exception {
        NguoiDungDTO nguoidungDTO = new NguoiDungDTO();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + Database.Name + "." + DBTable.NguoiDung + " nd,");
            sql.append(Database.Name + "." + DBTable.LoaiND + " lnd");
            sql.append(" WHERE nd.MaLoaiNguoiDung = lnd.MaLoaiNguoiDung");
            sql.append(" AND nd.MaNguoiDung = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, maNguoiDung);
            ResultSet rs = stmt.executeQuery();
            if ((rs != null) && (rs.next())) {
                nguoidungDTO.setMaNguoiDung(maNguoiDung);
                nguoidungDTO.setTenNguoiDung(rs.getString("TenNguoiDung"));
                nguoidungDTO.setMatKhau(rs.getString("MatKhau"));
                nguoidungDTO.setMaLoaiNguoiDung(rs.getInt("MaLoaiNguoiDung"));
                nguoidungDTO.setTenLoaiNguoiDung(rs.getString("TenLoaiNguoiDung"));
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return nguoidungDTO;
    }

    public NguoiDungDTO getNguoiDungDTO(String maNguoiDung, String matKhau) throws Exception {
        NguoiDungDTO nguoidungDTO = new NguoiDungDTO();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + Database.Name + "." + DBTable.NguoiDung + " nd,");
            sql.append(Database.Name + "." + DBTable.LoaiND + " lnd");
            sql.append(" WHERE nd.MaLoaiNguoiDung = lnd.MaLoaiNguoiDung");
            sql.append(" AND nd.MaNguoiDung = ?");
            sql.append(" AND nd.MatKhau = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, maNguoiDung);
            stmt.setString(2, matKhau);
            ResultSet rs = stmt.executeQuery();
            if ((rs != null) && (rs.next())) {
                nguoidungDTO.setMaNguoiDung(maNguoiDung);
                nguoidungDTO.setTenNguoiDung(rs.getString("TenNguoiDung"));
                nguoidungDTO.setMatKhau(rs.getString("MatKhau"));
                nguoidungDTO.setMaLoaiNguoiDung(rs.getInt("MaLoaiNguoiDung"));
                nguoidungDTO.setTenLoaiNguoiDung(rs.getString("TenLoaiNguoiDung"));
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return nguoidungDTO;
    }

    /**
     * @param maNguoiDung
     * @return: 0 --> nothing; row count for SQL statement.
     * @throws Exception
     */
    public int deleteNguoiDung(String maNguoiDung) throws Exception {
        int result;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" DELETE FROM " + Database.Name + "." + DBTable.NguoiDung);
            sql.append(" WHERE MaNguoiDung = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, maNguoiDung);
            result = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
            throw e;
        }
        return result;
    }

    public List<NguoiDungDTO> getDSNguoiDung(
            String maNguoiDung,
            String tenNguoiDung) throws Exception {

        ArrayList<NguoiDungDTO> dsNguoiDungDTO = new ArrayList<NguoiDungDTO>();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + Database.Name + "." + DBTable.NguoiDung + " nd,");
            sql.append(Database.Name + "." + DBTable.LoaiND + " lnd");
            sql.append(" WHERE nd.MaLoaiNguoiDung = lnd.MaLoaiNguoiDung");
            sql.append(" AND nd.MaNguoiDung LIKE ?");
            sql.append(" AND nd.TenNguoiDung LIKE ?");
            sql.append(" ORDER BY nd.MaNguoiDung");

            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, "%" + maNguoiDung + "%");
            stmt.setString(2, "%" + tenNguoiDung + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                NguoiDungDTO nguoidungDTO = new NguoiDungDTO();
                nguoidungDTO.setMaNguoiDung(rs.getString("MaNguoiDung"));
                nguoidungDTO.setTenNguoiDung(rs.getString("TenNguoiDung"));
                nguoidungDTO.setMatKhau(rs.getString("MatKhau"));
                nguoidungDTO.setMaLoaiNguoiDung(rs.getInt("MaLoaiNguoiDung"));
                nguoidungDTO.setTenLoaiNguoiDung(rs.getString("TenLoaiNguoiDung"));
                dsNguoiDungDTO.add(nguoidungDTO);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return dsNguoiDungDTO;
    }

    public List<NguoiDungDTO> getDSNguoiDung(
            String maNguoiDung,
            String tenNguoiDung,
            String maLoaiND) throws Exception {

        ArrayList<NguoiDungDTO> dsNguoiDungDTO = new ArrayList<NguoiDungDTO>();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + Database.Name + "." + DBTable.NguoiDung + " nd,");
            sql.append(Database.Name + "." + DBTable.LoaiND + " lnd");
            sql.append(" WHERE nd.MaLoaiNguoiDung = lnd.MaLoaiNguoiDung");
            sql.append(" AND nd.MaNguoiDung LIKE ?");
            sql.append(" AND nd.TenNguoiDung LIKE ?");
            sql.append(" AND nd.MaLoaiNguoiDung = ?");
            sql.append(" ORDER BY nd.MaNguoiDung");

            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, "%" + maNguoiDung + "%");
            stmt.setString(2, "%" + tenNguoiDung + "%");
            stmt.setString(3, maLoaiND);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                NguoiDungDTO nguoidungDTO = new NguoiDungDTO();
                nguoidungDTO.setMaNguoiDung(rs.getString("MaNguoiDung"));
                nguoidungDTO.setTenNguoiDung(rs.getString("TenNguoiDung"));
                nguoidungDTO.setMatKhau(rs.getString("MatKhau"));
                nguoidungDTO.setMaLoaiNguoiDung(rs.getInt("MaLoaiNguoiDung"));
                nguoidungDTO.setTenLoaiNguoiDung(rs.getString("TenLoaiNguoiDung"));
                dsNguoiDungDTO.add(nguoidungDTO);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return dsNguoiDungDTO;
    }

    public int createNguoiDung(NguoiDungDTO nguoidungDTO) throws Exception {
        int result = 0;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" INSERT INTO " + Database.Name + "." + DBTable.NguoiDung);
            sql.append(" (MaNguoiDung, TenNguoiDung, MatKhau, MaLoaiNguoiDung) ");
            sql.append(" VALUES (?, ?, ?, ?)");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, nguoidungDTO.getMaNguoiDung());
            stmt.setString(2, nguoidungDTO.getTenNguoiDung());
            stmt.setString(3, nguoidungDTO.getMatKhau());
            stmt.setInt(4, nguoidungDTO.getMaLoaiNguoiDung());
            result = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
            throw e;
        }
        return result;
    }

    public int updateNguoiDung(NguoiDungDTO nguoidungDTO) throws Exception {
        int result = 0;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" UPDATE " + Database.Name + "." + DBTable.NguoiDung);
            sql.append(" SET TenNguoiDung = ?, MatKhau = ?, MaLoaiNguoiDung = ?");
            sql.append(" WHERE MaNguoiDung = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, nguoidungDTO.getTenNguoiDung());
            stmt.setString(2, nguoidungDTO.getMatKhau());
            stmt.setInt(3, nguoidungDTO.getMaLoaiNguoiDung());
            stmt.setString(4, nguoidungDTO.getMaNguoiDung());
            result = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
            throw e;
        }
        return result;
    }
}
