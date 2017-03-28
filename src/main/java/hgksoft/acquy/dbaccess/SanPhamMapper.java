package hgksoft.acquy.dbaccess;

import hgksoft.acquy.constant.DBTable;
import hgksoft.acquy.constant.Database;
import hgksoft.acquy.dto.SanPhamDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HNTIN
 */
public class SanPhamMapper extends DBMapper {

    public SanPhamMapper() throws Exception {
        super();
    }

    public int createSanPham(SanPhamDTO sanphamDTO) throws Exception {
        int result = 0;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" INSERT INTO " + Database.Name + "." + DBTable.SP);
            sql.append(" (MaSanPham, TenSanPham, MoTa, Gia, MaTinhTrang, HinhDaiDien, MaNhaCC, MaQuocGia, MaLoaiSanPham) ");
            sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, sanphamDTO.getMaSanPham());
            stmt.setString(2, sanphamDTO.getTenSanPham());
            stmt.setString(3, sanphamDTO.getMoTaSanPham());
            stmt.setString(4, sanphamDTO.getGia());
            stmt.setString(5, sanphamDTO.getMaTinhTrang());
            stmt.setString(6, sanphamDTO.getHinhDaiDien());
            stmt.setString(7, sanphamDTO.getMaNhaCC());
            stmt.setString(8, sanphamDTO.getMaNuocSanXuat());
            stmt.setInt(9, sanphamDTO.getMaLoaiSanPham());
            result = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
            throw e;
        }
        return result;
    }

    public int updateSanPham(SanPhamDTO sanphamDTO) throws Exception {
        int result = 0;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" UPDATE " + Database.Name + "." + DBTable.SP);
            sql.append(" SET TenSanPham = ?, MoTa = ?, Gia = ?, MaTinhTrang = ?, HinhDaiDien = ?, "
                    + "MaNhaCC = ?, MaQuocGia = ?, MaLoaiSanPham = ?");
            sql.append(" WHERE MaSanPham = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, sanphamDTO.getTenSanPham());
            stmt.setString(2, sanphamDTO.getMoTaSanPham());
            stmt.setString(3, sanphamDTO.getGia());
            stmt.setString(4, sanphamDTO.getMaTinhTrang());
            stmt.setString(5, sanphamDTO.getHinhDaiDien());
            stmt.setString(6, sanphamDTO.getMaNhaCC());
            stmt.setString(7, sanphamDTO.getMaNuocSanXuat());
            stmt.setInt(8, sanphamDTO.getMaLoaiSanPham());
            stmt.setString(9, sanphamDTO.getMaSanPham());
            result = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw e;
        }
        return result;
    }
    
    /**
     * @param maSanPham
     * @return: 0 --> nothing; row count for SQL statement.
     * @throws Exception
     */
    public int deleteSanPham(String maSanPham) throws Exception {
        int result;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" DELETE FROM " + Database.Name + "." + DBTable.SP);
            sql.append(" WHERE MaSanPham = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, maSanPham);
            result = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
            throw e;
        }
        return result;
    }
    
    public SanPhamDTO getSanPhamDTO(String maSanPham) throws Exception {
        SanPhamDTO sanphamDTO = new SanPhamDTO();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + Database.Name + "." + DBTable.SP + " sp,");
            sql.append(Database.Name + "." + DBTable.LOAISP + " lsp,");
            sql.append(Database.Name + "." + DBTable.NhaCC + " ncc,");
            sql.append(Database.Name + "." + DBTable.QuocGia + " qg,");
            sql.append(Database.Name + "." + DBTable.TINHTRANG + " tt");
            sql.append(" WHERE sp.MaNhaCC = ncc.MaNhaCC AND sp.MaQuocGia = qg.MaQuocGia");
            sql.append(" AND sp.MaLoaiSanPham = lsp.MaLoaiSanPham");
            sql.append(" AND sp.MaTinhTrang = tt.MaTinhTrang");
            sql.append(" AND sp.MaSanPham = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, maSanPham);
            ResultSet rs = stmt.executeQuery();
            if ((rs != null) && (rs.next())) {
                sanphamDTO.setMaSanPham(rs.getString("MaSanPham"));
                sanphamDTO.setTenSanPham(rs.getString("TenSanPham"));
                sanphamDTO.setMoTaSanPham(rs.getString("MoTa"));
                sanphamDTO.setGia(rs.getString("Gia"));
                sanphamDTO.setMaTinhTrang(rs.getString("MaTinhTrang"));
                sanphamDTO.setTenTinhTrang(rs.getString("TenTinhTrang"));
                sanphamDTO.setHinhDaiDien(rs.getString("HinhDaiDien"));
                sanphamDTO.setMaNhaCC(rs.getString("MaNhaCC"));
                sanphamDTO.setTenNhaCC(rs.getString("TenNhaCC"));
                sanphamDTO.setMaNuocSanXuat(rs.getString("MaQuocGia"));
                sanphamDTO.setTenNuocSanXuat(rs.getString("TenQuocGia"));
                sanphamDTO.setMaLoaiSanPham(rs.getInt("MaLoaiSanPham"));
                sanphamDTO.setTenLoaiSanPham(rs.getString("TenLoaiSanPham"));
            }
            stmt.close();
        } catch (SQLException e) {
            throw e;
        }
        return sanphamDTO;
    }
    
    public List<SanPhamDTO> getDSSanPham(int maLoaiSanPham) throws Exception {

        ArrayList<SanPhamDTO> dsSanPhamDTO = new ArrayList<SanPhamDTO>();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + Database.Name + "." + DBTable.SP + " sp,");
            sql.append(Database.Name + "." + DBTable.LOAISP + " lsp,");
            sql.append(Database.Name + "." + DBTable.NhaCC + " ncc,");
            sql.append(Database.Name + "." + DBTable.QuocGia + " qg,");
            sql.append(Database.Name + "." + DBTable.TINHTRANG + " tt");
            sql.append(" WHERE sp.MaNhaCC = ncc.MaNhaCC AND sp.MaQuocGia = qg.MaQuocGia");
            sql.append(" AND sp.MaLoaiSanPham = lsp.MaLoaiSanPham");
            sql.append(" AND sp.MaTinhTrang = tt.MaTinhTrang");
            sql.append(" AND sp.MaLoaiSanPham = ?");
            sql.append(" ORDER BY sp.MaSanPham");

            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setInt(1, maLoaiSanPham);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                SanPhamDTO sanphamDTO = new SanPhamDTO();
                sanphamDTO.setMaSanPham(rs.getString("MaSanPham"));
                sanphamDTO.setTenSanPham(rs.getString("TenSanPham"));
                sanphamDTO.setMoTaSanPham(rs.getString("MoTa"));
                sanphamDTO.setGia(rs.getString("Gia"));
                sanphamDTO.setMaTinhTrang(rs.getString("MaTinhTrang"));
                sanphamDTO.setTenTinhTrang(rs.getString("TenTinhTrang"));
                sanphamDTO.setHinhDaiDien(rs.getString("HinhDaiDien"));
                sanphamDTO.setMaNhaCC(rs.getString("MaNhaCC"));
                sanphamDTO.setTenNhaCC(rs.getString("TenNhaCC"));
                sanphamDTO.setMaNuocSanXuat(rs.getString("MaQuocGia"));
                sanphamDTO.setTenNuocSanXuat(rs.getString("TenQuocGia"));
                sanphamDTO.setMaLoaiSanPham(rs.getInt("MaLoaiSanPham"));
                sanphamDTO.setTenLoaiSanPham(rs.getString("TenLoaiSanPham"));
                dsSanPhamDTO.add(sanphamDTO);
            }
            stmt.close();
        } catch (SQLException e) {
            throw e;
        }
        return dsSanPhamDTO;
    }

    public List<SanPhamDTO> getDSSanPham(
            String maSanPham,
            String tenSanPham) throws Exception {

        ArrayList<SanPhamDTO> dsSanPhamDTO = new ArrayList<SanPhamDTO>();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + Database.Name + "." + DBTable.SP + " sp,");
            sql.append(Database.Name + "." + DBTable.LOAISP + " lsp,");
            sql.append(Database.Name + "." + DBTable.NhaCC + " ncc,");
            sql.append(Database.Name + "." + DBTable.QuocGia + " qg,");
            sql.append(Database.Name + "." + DBTable.TINHTRANG + " tt");
            sql.append(" WHERE sp.MaNhaCC = ncc.MaNhaCC AND sp.MaQuocGia = qg.MaQuocGia");
            sql.append(" AND sp.MaLoaiSanPham = lsp.MaLoaiSanPham");
            sql.append(" AND sp.MaTinhTrang = tt.MaTinhTrang");
            sql.append(" AND sp.MaSanPham LIKE ?");
            sql.append(" AND sp.TenSanPham LIKE ?");
            sql.append(" ORDER BY sp.MaSanPham");

            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, "%" + maSanPham + "%");
            stmt.setString(2, "%" + tenSanPham + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                SanPhamDTO sanphamDTO = new SanPhamDTO();
                sanphamDTO.setMaSanPham(rs.getString("MaSanPham"));
                sanphamDTO.setTenSanPham(rs.getString("TenSanPham"));
                sanphamDTO.setMoTaSanPham(rs.getString("MoTa"));
                sanphamDTO.setGia(rs.getString("Gia"));
                sanphamDTO.setMaTinhTrang(rs.getString("MaTinhTrang"));
                sanphamDTO.setTenTinhTrang(rs.getString("TenTinhTrang"));
                sanphamDTO.setHinhDaiDien(rs.getString("HinhDaiDien"));
                sanphamDTO.setMaNhaCC(rs.getString("MaNhaCC"));
                sanphamDTO.setTenNhaCC(rs.getString("TenNhaCC"));
                sanphamDTO.setMaNuocSanXuat(rs.getString("MaQuocGia"));
                sanphamDTO.setTenNuocSanXuat(rs.getString("TenQuocGia"));
                sanphamDTO.setMaLoaiSanPham(rs.getInt("MaLoaiSanPham"));
                sanphamDTO.setTenLoaiSanPham(rs.getString("TenLoaiSanPham"));
                dsSanPhamDTO.add(sanphamDTO);
            }
            stmt.close();
        } catch (SQLException e) {
            throw e;
        }
        return dsSanPhamDTO;
    }

    public List<SanPhamDTO> getDSSanPham(
            String maSanPham, String tenSanPham, String moTaSanPham,
            String hangSX, String noiSX, String loaiSP, String tinhTrangSP) throws Exception {

        ArrayList<SanPhamDTO> dsSanPhamDTO = new ArrayList<SanPhamDTO>();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + Database.Name + "." + DBTable.SP + " sp,");
            sql.append(Database.Name + "." + DBTable.LOAISP + " lsp,");
            sql.append(Database.Name + "." + DBTable.NhaCC + " ncc,");
            sql.append(Database.Name + "." + DBTable.QuocGia + " qg,");
            sql.append(Database.Name + "." + DBTable.TINHTRANG + " tt");
            sql.append(" WHERE sp.MaNhaCC = ncc.MaNhaCC AND sp.MaQuocGia = qg.MaQuocGia");
            sql.append(" AND sp.MaLoaiSanPham = lsp.MaLoaiSanPham");
            sql.append(" AND sp.MaTinhTrang = tt.MaTinhTrang");
            
            sql.append(" AND sp.MaSanPham LIKE ?"); // ColumnIndex = 1
            sql.append(" AND sp.TenSanPham LIKE ?"); // ColumnIndex = 2
            sql.append(" AND sp.MoTa LIKE ?"); // ColumnIndex = 3

            int nextColumnIndex = 4;
            int columnIndexHangSX = 0;
            if (hangSX != null && !hangSX.equals("")) {
                columnIndexHangSX = nextColumnIndex;
                nextColumnIndex += 1;
                sql.append(" AND sp.MaNhaCC = ?");
            }

            int columnIndexNoiSX = 0;
            if (noiSX != null && !noiSX.equals("")) {
                columnIndexNoiSX = nextColumnIndex;
                nextColumnIndex += 1;
                sql.append(" AND sp.MaQuocGia = ?");
            }

            int columnIndexLoaiSP = 0;
            if (loaiSP != null && !loaiSP.equals("")) {
                columnIndexLoaiSP = nextColumnIndex;
                nextColumnIndex += 1;
                sql.append(" AND sp.MaLoaiSanPham = ?");
            }

            int columnIndexTinhTrangSP = 0;
            if (tinhTrangSP != null && !tinhTrangSP.equals("")) {
                columnIndexTinhTrangSP = nextColumnIndex;
                nextColumnIndex += 1;
                sql.append(" AND sp.MaTinhTrang = ?");
            }
            sql.append(" ORDER BY sp.MaSanPham");

            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, "%" + maSanPham + "%");
            stmt.setString(2, "%" + tenSanPham + "%");
            stmt.setString(3, "%" + moTaSanPham + "%");

            if (hangSX != null && !hangSX.equals("")) {
                stmt.setString(columnIndexHangSX, hangSX);
            }

            if (noiSX != null && !noiSX.equals("")) {
                stmt.setString(columnIndexNoiSX, noiSX);
            }

            if (loaiSP != null && !loaiSP.equals("")) {
                stmt.setString(columnIndexLoaiSP, loaiSP);
            }

            if (tinhTrangSP != null && !tinhTrangSP.equals("")) {
                stmt.setString(columnIndexTinhTrangSP, tinhTrangSP);
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                SanPhamDTO sanphamDTO = new SanPhamDTO();
                sanphamDTO.setMaSanPham(rs.getString("MaSanPham"));
                sanphamDTO.setTenSanPham(rs.getString("TenSanPham"));
                sanphamDTO.setMoTaSanPham(rs.getString("MoTa"));
                sanphamDTO.setGia(rs.getString("Gia"));
                sanphamDTO.setMaTinhTrang(rs.getString("MaTinhTrang"));
                sanphamDTO.setTenTinhTrang(rs.getString("TenTinhTrang"));
                sanphamDTO.setHinhDaiDien(rs.getString("HinhDaiDien"));
                sanphamDTO.setMaNhaCC(rs.getString("MaNhaCC"));
                sanphamDTO.setTenNhaCC(rs.getString("TenNhaCC"));
                sanphamDTO.setMaNuocSanXuat(rs.getString("MaQuocGia"));
                sanphamDTO.setTenNuocSanXuat(rs.getString("TenQuocGia"));
                sanphamDTO.setMaLoaiSanPham(rs.getInt("MaLoaiSanPham"));
                sanphamDTO.setTenLoaiSanPham(rs.getString("TenLoaiSanPham"));
                dsSanPhamDTO.add(sanphamDTO);
            }
            stmt.close();
        } catch (SQLException e) {
            throw e;
        }
        return dsSanPhamDTO;
    }

    public List<SanPhamDTO> getDSTatCaSanPham() throws Exception {

        ArrayList<SanPhamDTO> dsSanPhamDTO = new ArrayList<SanPhamDTO>();
        try {           
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM " + Database.Name + "." + DBTable.SP + " sp,");
            sql.append(Database.Name + "." + DBTable.LOAISP + " lsp,");
            sql.append(Database.Name + "." + DBTable.NhaCC + " ncc,");
            sql.append(Database.Name + "." + DBTable.QuocGia + " qg,");
            sql.append(Database.Name + "." + DBTable.TINHTRANG + " tt");
            sql.append(" WHERE sp.MaNhaCC = ncc.MaNhaCC AND sp.MaQuocGia = qg.MaQuocGia");
            sql.append(" AND sp.MaLoaiSanPham = lsp.MaLoaiSanPham");
            sql.append(" AND sp.MaTinhTrang = tt.MaTinhTrang");
            sql.append(" ORDER BY sp.MaSanPham");

            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                SanPhamDTO sanphamDTO = new SanPhamDTO();
                sanphamDTO.setMaSanPham(rs.getString("MaSanPham"));
                sanphamDTO.setTenSanPham(rs.getString("TenSanPham"));
                sanphamDTO.setMoTaSanPham(rs.getString("MoTa"));
                sanphamDTO.setGia(rs.getString("Gia"));
                sanphamDTO.setMaTinhTrang(rs.getString("MaTinhTrang"));
                sanphamDTO.setTenTinhTrang(rs.getString("TenTinhTrang"));
                sanphamDTO.setHinhDaiDien(rs.getString("HinhDaiDien"));
                sanphamDTO.setMaNhaCC(rs.getString("MaNhaCC"));
                sanphamDTO.setTenNhaCC(rs.getString("TenNhaCC"));
                sanphamDTO.setMaNuocSanXuat(rs.getString("MaQuocGia"));
                sanphamDTO.setTenNuocSanXuat(rs.getString("TenQuocGia"));
                sanphamDTO.setMaLoaiSanPham(rs.getInt("MaLoaiSanPham"));
                sanphamDTO.setTenLoaiSanPham(rs.getString("TenLoaiSanPham"));
                dsSanPhamDTO.add(sanphamDTO);
            }
            stmt.close();
        } catch (SQLException e) {
            throw e;
        }
        return dsSanPhamDTO;
    }
}
