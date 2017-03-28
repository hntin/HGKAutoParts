package hgksoft.acquy.dto;

import com.opensymphony.xwork2.validator.annotations.*;

/**
 *
 * @author HNTIN
 */
public class NguoiDungDTO {

    private String maNguoiDung;
    private String tenNguoiDung;
    private String matKhau;
    private int maLoaiNguoiDung;
    private String tenLoaiNguoiDung;

    @RequiredStringValidator(message = "Mã người dùng không được rỗng")
     public String getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(String maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }
    
    @RequiredStringValidator(message = "Tên người dùng không được rỗng")
    public String getTenNguoiDung() {
        return tenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        this.tenNguoiDung = tenNguoiDung;
    }

    @RequiredStringValidator(message = "Mật khẩu không được rỗng")
    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    @RequiredStringValidator(message = "Loại người dùng không được rỗng")
    public int getMaLoaiNguoiDung() {
        return maLoaiNguoiDung;
    }

    public void setMaLoaiNguoiDung(int maLoaiNguoiDung) {
        this.maLoaiNguoiDung = maLoaiNguoiDung;
    }

    public String getTenLoaiNguoiDung() {
        return tenLoaiNguoiDung;
    }

    public void setTenLoaiNguoiDung(String tenLoaiNguoiDung) {
        this.tenLoaiNguoiDung = tenLoaiNguoiDung;
    }
}
