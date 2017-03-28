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
public class GoCapNhatNguoiDungActionSupport extends ActionSupport {
    private NguoiDungDTO nguoidungDTO;
    private String maNguoiDung;
    private int maLoaiNguoiDung;
    private HashMap<Integer, String> loaiNguoiDungHM = new HashMap<>();

    //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
    public NguoiDungDTO getNguoidungDTO() {
        return nguoidungDTO;
    }

    public void setNguoidungDTO(NguoiDungDTO nguoidungDTO) {
        this.nguoidungDTO = nguoidungDTO;
    }

    public int getMaLoaiNguoiDung() {
        return maLoaiNguoiDung;
    }

    public void setMaLoaiNguoiDung(int maLoaiNguoiDung) {
        this.maLoaiNguoiDung = maLoaiNguoiDung;
    }

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
    
    //</editor-fold>

    public GoCapNhatNguoiDungActionSupport() {
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
        loaiNguoiDungHM.put(1, "Quản trị");
        loaiNguoiDungHM.put(2, "Nhân viên");
        NguoiDungBO nguoidungBO = new NguoiDungBO();
        
        this.nguoidungDTO = nguoidungBO.getNguoiDungDTO(maNguoiDung.trim());
        
        return SUCCESS;
    }

}
