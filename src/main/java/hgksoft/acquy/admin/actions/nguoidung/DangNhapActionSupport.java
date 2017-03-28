package hgksoft.acquy.admin.actions.nguoidung;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import hgksoft.acquy.bo.NguoiDungBO;
import hgksoft.acquy.constant.CommonConst;
import hgksoft.acquy.dto.NguoiDungDTO;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author HNTIN
 */
public class DangNhapActionSupport extends ActionSupport {

    private String maNguoiDung;
    private String matKhau;
    private String msg;
    private Map<String, Object> sessionMap;

    @RequiredStringValidator(message = "Mã người dùng không được rỗng")
    public String getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(String maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    @RequiredStringValidator(message = "Mật khẩu không được rỗng")
    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getSessionMap() {
        return sessionMap;
    }

    public void setSessionMap(Map<String, Object> sessionMap) {
        this.sessionMap = sessionMap;
    }

    public DangNhapActionSupport() {
    }

    public String execute() throws Exception {
        String page = null;
        NguoiDungBO nguoidungBO = new NguoiDungBO();
        NguoiDungDTO nguoidungDTO = nguoidungBO.getNguoiDungDTO(maNguoiDung, matKhau);
        if (nguoidungDTO != null) {
            if (nguoidungDTO.getMaNguoiDung() == null || nguoidungDTO.getMaNguoiDung().equals("")) {
                page = "fail";
                msg = "Mã người dùng và mật khẩu không hợp lệ!";
            } else { // Đăng nhập thành công
                if (nguoidungDTO.getMaLoaiNguoiDung() == CommonConst.MALOAINGUOIDUNG_ADMIN) {
                    page = "quantri";
                    sessionMap = ActionContext.getContext().getSession();
                    sessionMap.put("isLogged", true);
                    sessionMap.put("maNguoiDungLogged", maNguoiDung);
                    sessionMap.put("loggedTime", new Date());
                }
            }
        } else {
            page = "fail";
            msg = "Lỗi đăng nhập";
        }

        return page;
    }
}
