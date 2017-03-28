package hgksoft.acquy.admin.actions.nhacc;

import com.opensymphony.xwork2.ActionSupport;
import hgksoft.acquy.bo.NhaCungCapBO;
import hgksoft.acquy.dto.NhaCCDTO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author HNTIN
 */
public class GoCapNhatNhaCCActionSupport extends ActionSupport {
    private String maNhaCC;
    private NhaCCDTO nccDTO;

    public NhaCCDTO getNccDTO() {
        return nccDTO;
    }

    public void setNccDTO(NhaCCDTO nccDTO) {
        this.nccDTO = nccDTO;
    }
    
    public String getMaNhaCC() {
        return maNhaCC;
    }

    public void setMaNhaCC(String maNhaCC) {
        this.maNhaCC = maNhaCC;
    }
    
    public GoCapNhatNhaCCActionSupport() {
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
        
        NhaCungCapBO nccBO = new NhaCungCapBO();
        nccDTO = nccBO.getNhaCCDTO(maNhaCC.trim());
        return SUCCESS;
    }
    
}
