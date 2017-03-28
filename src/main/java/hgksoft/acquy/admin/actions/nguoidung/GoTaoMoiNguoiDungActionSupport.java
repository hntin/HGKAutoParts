package hgksoft.acquy.admin.actions.nguoidung;

import com.opensymphony.xwork2.ActionSupport;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author HNTIN
 */
public class GoTaoMoiNguoiDungActionSupport extends ActionSupport {
    private HashMap<Integer, String> loaiNguoiDungHM = new HashMap<>();

    public HashMap<Integer, String> getLoaiNguoiDungHM() {
        return loaiNguoiDungHM;
    }

    public void setLoaiNguoiDungHM(HashMap<Integer, String> loaiNguoiDungHM) {
        this.loaiNguoiDungHM = loaiNguoiDungHM;
    }
    
    public GoTaoMoiNguoiDungActionSupport() {
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
        loaiNguoiDungHM.put(1, "Quản trị");
        loaiNguoiDungHM.put(2, "Nhân viên");        
        return SUCCESS;
    }    
}
