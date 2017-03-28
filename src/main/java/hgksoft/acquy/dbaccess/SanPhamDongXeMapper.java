package hgksoft.acquy.dbaccess;

import hgksoft.acquy.constant.DBTable;
import hgksoft.acquy.constant.Database;
import hgksoft.acquy.dto.DongXeDTO;
import hgksoft.acquy.dto.SanPhamDTO;
import hgksoft.acquy.dto.SanPhamDongXeDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HNTIN
 */
public class SanPhamDongXeMapper extends DBMapper {

    public SanPhamDongXeMapper() throws Exception {
        super();
    }
    
    public List<SanPhamDTO> getDSSanPhamTuongThichDongXe(String maDongXe) throws Exception {
        ArrayList<SanPhamDTO> dsSanPhamDTO = new ArrayList<>();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT sp.MaSanPham, sp.TenSanPham, sp.MoTa, sp.Gia, sp.HinhDaiDien, ");
            sql.append(" tt.MaTinhTrang, tt.TenTinhTrang, ncc.MaNhaCC, ncc.TenNhaCC, qg.MaQuocGia, qg.TenQuocGia, spdx.MaDongXe ");

            sql.append(" FROM " + Database.Name + "." + DBTable.SP + " sp, ");
            sql.append(Database.Name + "." + DBTable.TINHTRANG + " tt, ");
            sql.append(Database.Name + "." + DBTable.NhaCC + " ncc, ");
            sql.append(Database.Name + "." + DBTable.QuocGia + " qg, ");
            sql.append(Database.Name + "." + DBTable.SP_DongXe + " spdx");

            sql.append(" WHERE sp.MaNhaCC = ncc.MaNhaCC AND sp.MaQuocGia = qg.MaQuocGia AND "
                    + " sp.MaTinhTrang = tt.MaTinhTrang AND sp.MaSanPham = spdx.MaSanPham");
            sql.append(" AND spdx.MaDongXe = ?");
            sql.append(" ORDER BY sp.MaSanPham");

            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, maDongXe);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                SanPhamDTO spDTO = new SanPhamDTO();
                spDTO.setMaSanPham(rs.getString("MaSanPham"));
                spDTO.setTenSanPham(rs.getString("TenSanPham"));
                spDTO.setMoTaSanPham(rs.getString("MoTa"));
                spDTO.setGia(rs.getString("Gia"));
                spDTO.setHinhDaiDien(rs.getString("HinhDaiDien"));
                spDTO.setMaNhaCC(rs.getString("MaNhaCC"));
                spDTO.setTenNhaCC(rs.getString("TenNhaCC"));
                spDTO.setMaNuocSanXuat(rs.getString("MaQuocGia"));
                spDTO.setTenNuocSanXuat(rs.getString("TenQuocGia"));
                spDTO.setMaTinhTrang(rs.getString("MaTinhTrang"));
                spDTO.setTenTinhTrang(rs.getString("TenTinhTrang"));              
                dsSanPhamDTO.add(spDTO);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return dsSanPhamDTO;
    }
    
    public List<DongXeDTO> getDSDongXeChuaApDung(String maSanPham, String maHangXe) throws Exception {
        ArrayList<DongXeDTO> dsDongXeDTO = new ArrayList<>();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT dx.MaDongXe, dx.TenDongXe, dx.SoXyLanh, dx.DongCo, dx.HopSo, dx.SoCua, "
                    + "dx.NhienLieu, dx.NamSanXuat, lx.MaLoaiXe, lx.TenLoaiXe, hx.MaHangXe, hx.TenHangXe");
            sql.append(" FROM " + Database.Name + "." + DBTable.DongXe + " dx, ");
            sql.append(Database.Name + "." + DBTable.LoaiXe + " lx, ");
            sql.append(Database.Name + "." + DBTable.HangXe + " hx ");
            sql.append(" WHERE dx.MaLoaiXe = lx.MaLoaiXe ");
            sql.append(" AND lx.MaHangXe = hx.MaHangXe ");
            sql.append(" AND hx.MaHangXe = ?");
            sql.append(" AND dx.MaDongXe NOT IN (");
            sql.append(" SELECT spdx.MaDongXe FROM " + Database.Name + "." + DBTable.SP_DongXe + " spdx" );
            sql.append(" WHERE spdx.MaSanPham = ?");
            sql.append(" )");
            sql.append(" ORDER BY dx.MaLoaiXe");

            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, maHangXe);
            stmt.setString(2, maSanPham);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                DongXeDTO dxDTO = new DongXeDTO();
                dxDTO.setMaDongXe(rs.getString("MaDongXe"));
                dxDTO.setTenDongXe(rs.getString("TenDongXe"));
                dxDTO.setSoXyLanh(rs.getInt("SoXyLanh"));
                dxDTO.setDongCo(rs.getString("DongCo"));
                dxDTO.setHopSo(rs.getString("HopSo"));
                dxDTO.setSoCua(rs.getInt("SoCua"));
                dxDTO.setNhienLieu(rs.getString("NhienLieu"));
                dxDTO.setNamSX(rs.getString("NamSanXuat"));
                dxDTO.setMaLoaiXe(rs.getString("MaLoaiXe"));
                dxDTO.setTenLoaiXe(rs.getString("TenLoaiXe"));
                dxDTO.setMaHangXe(rs.getString("MaHangXe"));
                dxDTO.setTenHangXe(rs.getString("TenHangXe"));
                dsDongXeDTO.add(dxDTO);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return dsDongXeDTO;
    }

    public List<DongXeDTO> getDSDongXeTuongThichSanPham(String maSanPham) throws Exception {
        ArrayList<DongXeDTO> dsDongXeDTO = new ArrayList<>();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT dx.MaDongXe, dx.TenDongXe, dx.SoXyLanh, dx.DongCo, dx.HopSo, dx.SoCua, "
                    + "dx.NhienLieu, dx.NamSanXuat, lx.MaLoaiXe, lx.TenLoaiXe, hx.MaHangXe, hx.TenHangXe");
            sql.append(" FROM " + Database.Name + "." + DBTable.SP_DongXe + " spdx, ");
            sql.append(Database.Name + "." + DBTable.DongXe + " dx, ");
            sql.append(Database.Name + "." + DBTable.LoaiXe + " lx, ");
            sql.append(Database.Name + "." + DBTable.HangXe + " hx ");

            sql.append(" WHERE spdx.MaDongXe = dx.MaDongXe AND dx.MaLoaiXe = lx.MaLoaiXe ");
            sql.append(" AND lx.MaHangXe = hx.MaHangXe");
            sql.append(" AND spdx.MaSanPham = ?");
            sql.append(" ORDER BY spdx.MaDongXe");

            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, maSanPham);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                DongXeDTO dxDTO = new DongXeDTO();
                dxDTO.setMaDongXe(rs.getString("MaDongXe"));
                dxDTO.setTenDongXe(rs.getString("TenDongXe"));
                dxDTO.setSoXyLanh(rs.getInt("SoXyLanh"));
                dxDTO.setDongCo(rs.getString("DongCo"));
                dxDTO.setHopSo(rs.getString("HopSo"));
                dxDTO.setSoCua(rs.getInt("SoCua"));
                dxDTO.setNhienLieu(rs.getString("NhienLieu"));
                dxDTO.setNamSX(rs.getString("NamSanXuat"));
                dxDTO.setMaLoaiXe(rs.getString("MaLoaiXe"));
                dxDTO.setTenLoaiXe(rs.getString("TenLoaiXe"));
                dxDTO.setMaHangXe(rs.getString("MaHangXe"));
                dxDTO.setTenHangXe(rs.getString("TenHangXe"));
                dsDongXeDTO.add(dxDTO);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return dsDongXeDTO;
    }
    
    public List<SanPhamDongXeDTO> getDSSanPhamDongXeTuongThich(String maSanPham) throws Exception {
        ArrayList<SanPhamDongXeDTO> dsSanPhamDongXeDTO = new ArrayList<>();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT sp.MaSanPham, sp.TenSanPham, sp.MoTa, ");
            sql.append(" ncc.TenNhaCC, qg.TenQuocGia, spdx.MaDongXe, ");
            sql.append(" dx.TenDongXe, dx.SoXyLanh, dx.DongCo, dx.HopSo, dx.SoCua, dx.NhienLieu, "
                    + "dx.NamSanXuat, lx.MaLoaiXe, lx.TenLoaiXe, hx.MaHangXe, hx.TenHangXe");

            sql.append(" FROM " + Database.Name + "." + DBTable.SP + " sp, ");
            sql.append(Database.Name + "." + DBTable.NhaCC + " ncc, ");
            sql.append(Database.Name + "." + DBTable.QuocGia + " qg, ");
            sql.append(Database.Name + "." + DBTable.SP_DongXe + " spdx, ");
            sql.append(Database.Name + "." + DBTable.DongXe + " dx, ");
            sql.append(Database.Name + "." + DBTable.LoaiXe + " lx, ");
            sql.append(Database.Name + "." + DBTable.HangXe + " hx ");

            sql.append(" WHERE sp.MaNhaCC = ncc.MaNhaCC AND sp.MaQuocGia = qg.MaQuocGia AND "
                    + " sp.MaSanPham = spdx.MaSanPham AND spdx.MaDongXe = dx.MaDongXe "
                    + " AND dx.MaLoaiXe = lx.MaLoaiXe AND lx.MaHangXe = hx.MaHangXe ");
            sql.append(" AND spdx.MaSanPham = ?");
            sql.append(" ORDER BY sp.MaSanPham, hx.MaHangXe");

            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, maSanPham);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                SanPhamDongXeDTO spdxDTO = new SanPhamDongXeDTO();
                spdxDTO.setMaSanPham(rs.getString("MaSanPham"));
                spdxDTO.setTenSanPham(rs.getString("TenSanPham"));
                spdxDTO.setMoTaSanPham(rs.getString("MoTa"));
                spdxDTO.setTenNhaCC(rs.getString("TenNhaCC"));
                spdxDTO.setNuocSanXuat(rs.getString("TenQuocGia"));

                spdxDTO.setMaDongXe(rs.getString("MaDongXe"));
                spdxDTO.setTenDongXe(rs.getString("TenDongXe"));
                spdxDTO.setSoXyLanh(rs.getInt("SoXyLanh"));
                spdxDTO.setDongCo(rs.getString("DongCo"));
                spdxDTO.setHopSo(rs.getString("HopSo"));
                spdxDTO.setSoCua(rs.getInt("SoCua"));
                spdxDTO.setNhienLieu(rs.getString("NhienLieu"));
                spdxDTO.setNamSX(rs.getString("NamSanXuat"));                
                spdxDTO.setMaLoaiXe(rs.getString("MaLoaiXe"));
                spdxDTO.setTenLoaiXe(rs.getString("TenLoaiXe"));
                spdxDTO.setMaHangXe(rs.getString("MaHangXe"));
                spdxDTO.setTenHangXe(rs.getString("TenHangXe"));                
                dsSanPhamDongXeDTO.add(spdxDTO);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return dsSanPhamDongXeDTO;
    }

    public List<SanPhamDongXeDTO> searchDSSanPhamDongXeTuongThich(String maSanPham, String tenSanPham) throws Exception {
        ArrayList<SanPhamDongXeDTO> dsSanPhamDongXeDTO = new ArrayList<>();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT sp.MaSanPham, sp.TenSanPham, sp.MoTa, ");
            sql.append(" ncc.TenNhaCC, qg.TenQuocGia, spdx.MaDongXe, ");
            sql.append(" dx.TenDongXe, dx.SoXyLanh, dx.DongCo, dx.HopSo, dx.SoCua, dx.NhienLieu, "
                    + "dx.NamSanXuat, lx.MaLoaiXe, lx.TenLoaiXe, hx.MaHangXe, hx.TenHangXe");

            sql.append(" FROM " + Database.Name + "." + DBTable.SP + " sp, ");
            sql.append(Database.Name + "." + DBTable.NhaCC + " ncc, ");
            sql.append(Database.Name + "." + DBTable.QuocGia + " qg, ");
            sql.append(Database.Name + "." + DBTable.SP_DongXe + " spdx, ");
            sql.append(Database.Name + "." + DBTable.DongXe + " dx, ");
            sql.append(Database.Name + "." + DBTable.LoaiXe + " lx, ");
            sql.append(Database.Name + "." + DBTable.HangXe + " hx ");

            sql.append(" WHERE sp.MaNhaCC = ncc.MaNhaCC AND sp.MaQuocGia = qg.MaQuocGia AND "
                    + " sp.MaSanPham = spdx.MaSanPham AND spdx.MaDongXe = dx.MaDongXe "
                    + " AND dx.MaLoaiXe = lx.MaLoaiXe AND lx.MaHangXe = hx.MaHangXe ");
            sql.append(" AND spdx.MaSanPham LIKE ?");
            sql.append(" AND sp.TenSanPham LIKE ?");
            sql.append(" ORDER BY sp.MaSanPham, hx.MaHangXe");

            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, "%" + maSanPham + "%");
            stmt.setString(2, "%" + tenSanPham + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                SanPhamDongXeDTO spdxDTO = new SanPhamDongXeDTO();
                spdxDTO.setMaSanPham(rs.getString("MaSanPham"));
                spdxDTO.setTenSanPham(rs.getString("TenSanPham"));
                spdxDTO.setMoTaSanPham(rs.getString("MoTa"));
                spdxDTO.setTenNhaCC(rs.getString("TenNhaCC"));
                spdxDTO.setNuocSanXuat(rs.getString("TenQuocGia"));

                spdxDTO.setMaDongXe(rs.getString("MaDongXe"));
                spdxDTO.setTenDongXe(rs.getString("TenDongXe"));
                spdxDTO.setSoXyLanh(rs.getInt("SoXyLanh"));
                spdxDTO.setDongCo(rs.getString("DongCo"));
                spdxDTO.setHopSo(rs.getString("HopSo"));
                spdxDTO.setSoCua(rs.getInt("SoCua"));
                spdxDTO.setNhienLieu(rs.getString("NhienLieu"));
                spdxDTO.setNamSX(rs.getString("NamSanXuat"));                
                spdxDTO.setMaLoaiXe(rs.getString("MaLoaiXe"));
                spdxDTO.setTenLoaiXe(rs.getString("TenLoaiXe"));
                spdxDTO.setMaHangXe(rs.getString("MaHangXe"));
                spdxDTO.setTenHangXe(rs.getString("TenHangXe"));                
                dsSanPhamDongXeDTO.add(spdxDTO);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return dsSanPhamDongXeDTO;
    }

    public int deleteApDung(String maSanPham, String maDongXe) throws Exception {
        int result;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" DELETE FROM " + Database.Name + "." + DBTable.SP_DongXe);
            sql.append(" WHERE MaSanPham = ? AND MaDongXe = ?");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, maSanPham);
            stmt.setString(2, maDongXe);
            result = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
            throw e;
        }
        return result;
    }
    
    public int createApDungMoi(String maSanPham, String maDongXe) throws Exception {
        int result = 0;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" INSERT INTO " + Database.Name + "." + DBTable.SP_DongXe);
            sql.append(" (MaSanPham, MaDongXe, MoTa) ");
            sql.append(" VALUES (?, ?, ?)");
            PreparedStatement stmt = getConnection().prepareStatement(sql.toString());
            stmt.setString(1, maSanPham);
            stmt.setString(2, maDongXe);
            stmt.setString(3, maSanPham + "#" + maDongXe);
            result = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
            throw e;
        }
        return result;
    }
    

}
