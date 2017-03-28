package hgksoft.acquy.admin.actions.nguoidung;

import com.opensymphony.xwork2.ActionSupport;
import hgksoft.acquy.bo.NguoiDungBO;
import hgksoft.acquy.dto.NguoiDungDTO;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author HNTIN
 */
public class CapNhatNguoiDungActionSupport extends ActionSupport {

    private NguoiDungDTO nguoidungDTO;
    private HashMap<Integer, String> loaiNguoiDungHM = new HashMap<>();
    private String maNguoiDung;
    private String tenNguoiDung;
    private String matKhau;
    private String matKhauConfirm;
    private int maLoaiNguoiDung;
    private String msg;

    //<editor-fold defaultstate="collapsed" desc="Getter & Setter">
    public HashMap<Integer, String> getLoaiNguoiDungHM() {
        return loaiNguoiDungHM;
    }

    public void setLoaiNguoiDungHM(HashMap<Integer, String> loaiNguoiDungHM) {
        this.loaiNguoiDungHM = loaiNguoiDungHM;
    }

    public String getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(String maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public String getTenNguoiDung() {
        return tenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        this.tenNguoiDung = tenNguoiDung;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getMatKhauConfirm() {
        return matKhauConfirm;
    }

    public void setMatKhauConfirm(String matKhauConfirm) {
        this.matKhauConfirm = matKhauConfirm;
    }

    public int getMaLoaiNguoiDung() {
        return maLoaiNguoiDung;
    }

    public void setMaLoaiNguoiDung(int maLoaiNguoiDung) {
        this.maLoaiNguoiDung = maLoaiNguoiDung;
    }

    public NguoiDungDTO getNguoidungDTO() {
        return nguoidungDTO;
    }

    public void setNguoidungDTO(NguoiDungDTO nguoidungDTO) {
        this.nguoidungDTO = nguoidungDTO;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    //</editor-fold>

    public CapNhatNguoiDungActionSupport() {
    }

    @Override
    public void validate() {
        if (tenNguoiDung == null || tenNguoiDung.equals("")) {
            addFieldError("tenNguoiDung", "Nhập họ tên người dùng");
        }

        if (matKhau == null || matKhau.equals("")) {
            addFieldError("matKhau", "Nhập mật khẩu");
        } else {
            if (!matKhau.equals(matKhauConfirm)) {
                addFieldError("matKhau", "Nhập lại mật khẩu chưa khớp");
            }
        }

        loaiNguoiDungHM.put(1, "Quản trị");
        loaiNguoiDungHM.put(2, "Nhân viên");
        this.nguoidungDTO = new NguoiDungDTO();
        this.nguoidungDTO.setMaNguoiDung(maNguoiDung);
        this.nguoidungDTO.setTenNguoiDung(tenNguoiDung);
        this.nguoidungDTO.setMatKhau(matKhau);
        this.nguoidungDTO.setMaLoaiNguoiDung(maLoaiNguoiDung);
    }

    public String execute() throws Exception {
        //<editor-fold defaultstate="collapsed" desc="Kiểm tra trạng thái login trong Session">
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        Boolean logged = (Boolean) session.getAttribute("isLogged");
        if (logged == null || logged == false) {
            return "logout";
        }
        //</editor-fold>
        
        NguoiDungBO nguoidungBO = new NguoiDungBO();
        NguoiDungDTO nguoidungDTO = new NguoiDungDTO();
        nguoidungDTO.setMaNguoiDung(maNguoiDung);
        nguoidungDTO.setTenNguoiDung(tenNguoiDung);
        nguoidungDTO.setMatKhau(matKhau);
        nguoidungDTO.setMaLoaiNguoiDung(maLoaiNguoiDung);

        int result = nguoidungBO.updateNguoiDung(nguoidungDTO);
        if (result == 1) {
            msg = "Cập nhật thành công";
        } else {
            msg = "Không thể cập nhật";
        }

        return SUCCESS;
    }
}
