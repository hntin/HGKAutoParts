package hgksoft.acquy.admin.actions.nguoidung;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.*;
import hgksoft.acquy.bo.NguoiDungBO;
import hgksoft.acquy.dto.NguoiDungDTO;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author HNTIN
 */
public class TaoMoiNguoiDungActionSupport extends ActionSupport {

    private NguoiDungDTO nguoidungDTO = new NguoiDungDTO();
    private String maNguoiDungMoi;
    private String tenNguoiDungMoi;
    private String matKhauMoi;
    private String matKhauMoiConfirm;
    private HashMap<Integer, String> loaiNguoiDungHM = new HashMap<>();
    private String selectedLoaiND;
    private String msg;

    //<editor-fold defaultstate="collapsed" desc="Getter & Setter">
    public NguoiDungDTO getNguoidungDTO() {
        return nguoidungDTO;
    }

    public void setNguoidungDTO(NguoiDungDTO nguoidungDTO) {
        this.nguoidungDTO = nguoidungDTO;
    }

    public String getMaNguoiDungMoi() {
        return maNguoiDungMoi;
    }

    public void setMaNguoiDungMoi(String maNguoiDungMoi) {
        this.maNguoiDungMoi = maNguoiDungMoi;
    }

    public String getTenNguoiDungMoi() {
        return tenNguoiDungMoi;
    }

    public void setTenNguoiDungMoi(String tenNguoiDungMoi) {
        this.tenNguoiDungMoi = tenNguoiDungMoi;
    }

    public String getSelectedLoaiND() {
        return selectedLoaiND;
    }

    public void setSelectedLoaiND(String selectedLoaiND) {
        this.selectedLoaiND = selectedLoaiND;
    }

    public String getMatKhauMoi() {
        return matKhauMoi;
    }

    public void setMatKhauMoi(String matKhauMoi) {
        this.matKhauMoi = matKhauMoi;
    }

    public String getMatKhauMoiConfirm() {
        return matKhauMoiConfirm;
    }

    public void setMatKhauMoiConfirm(String matKhauMoiConfirm) {
        this.matKhauMoiConfirm = matKhauMoiConfirm;
    }

    public HashMap<Integer, String> getLoaiNguoiDungHM() {
        return loaiNguoiDungHM;
    }

    public void setLoaiNguoiDungHM(HashMap<Integer, String> loaiNguoiDungHM) {
        this.loaiNguoiDungHM = loaiNguoiDungHM;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    //</editor-fold>

    public TaoMoiNguoiDungActionSupport() {
    }

    @Override
    public void validate() {
        // \d  	A digit: [0-9]
        // \D  	A non-digit: [^0-9]
        // \s  	A whitespace character: [ \t\n\x0B\f\r]
        // \S  	A non-whitespace character: [^\s]
        // \w  	A word character: [a-zA-Z_0-9]
        // \W   A non-word character: [^\w]
        Pattern pattern = Pattern.compile("\\W");
        if (maNguoiDungMoi == null || maNguoiDungMoi.equals("")) {
            addFieldError("maNguoiDungMoi", "Nhập mã người dùng");
        } 

        if (tenNguoiDungMoi == null || tenNguoiDungMoi.equals("")) {
            addFieldError("tenNguoiDungMoi", "Nhập tên người dùng");
        } 

        if (matKhauMoi == null || matKhauMoi.equals("")) {
            addFieldError("matKhauMoi", "Nhập mật khẩu");
        } else {
            if (!matKhauMoi.equals(matKhauMoiConfirm)) {
                addFieldError("matKhauMoi", "Nhập lại mật khẩu chưa khớp");
            }
        }

        loaiNguoiDungHM.put(0, "Tất cả");
        loaiNguoiDungHM.put(1, "Quản trị");
        loaiNguoiDungHM.put(2, "Nhân viên");
    }

    @Override
    public String execute() throws Exception {
        //<editor-fold defaultstate="collapsed" desc="Kiểm tra trạng thái login trong Session">
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        Boolean logged = (Boolean) session.getAttribute("isLogged");
        if (logged == null || logged == false) {
            return "logout";
        }
        //</editor-fold>
        
        loaiNguoiDungHM.put(0, "Tất cả");
        loaiNguoiDungHM.put(1, "Quản trị");
        loaiNguoiDungHM.put(2, "Nhân viên");

        NguoiDungBO nguoidungBO = new NguoiDungBO();
        NguoiDungDTO nguoidungDTO = nguoidungBO.getNguoiDungDTO(maNguoiDungMoi);
        if (nguoidungDTO != null) {
            if (nguoidungDTO.getMaNguoiDung() != null && !nguoidungDTO.getMaNguoiDung().equals("")) {
                msg = "Mã người dùng đã tồn tại";
            }
            else {
                nguoidungDTO = new NguoiDungDTO();
                nguoidungDTO.setMaNguoiDung(maNguoiDungMoi);
                nguoidungDTO.setTenNguoiDung(tenNguoiDungMoi);
                nguoidungDTO.setMatKhau(matKhauMoi);
                nguoidungDTO.setMaLoaiNguoiDung(Integer.parseInt(selectedLoaiND));
                int result = nguoidungBO.createNguoiDung(nguoidungDTO);
                if (result == 1)
                    msg = "Thêm mới người dùng thành công";
            }
        }
            
        return "success";
    }
}
