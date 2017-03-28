package hgksoft.acquy.admin.actions.hangxe;

import com.opensymphony.xwork2.ActionSupport;
import hgksoft.acquy.bo.HangXeBO;
import hgksoft.acquy.dto.HangXeDTO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author HNTIN
 */
public class GoCapNhatHangXeActionSupport extends ActionSupport {
    private String maHangXe;
    private HangXeDTO hxDTO;

    public String getMaHangXe() {
        return maHangXe;
    }

    public void setMaHangXe(String maHangXe) {
        this.maHangXe = maHangXe;
    }

    public HangXeDTO getHxDTO() {
        return hxDTO;
    }

    public void setHxDTO(HangXeDTO hxDTO) {
        this.hxDTO = hxDTO;
    }
    
    public GoCapNhatHangXeActionSupport() {
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
        
        HangXeBO hangxeBO = new HangXeBO();
        hxDTO = hangxeBO.getHangXeDTO(maHangXe);
        return SUCCESS;        
    }    
}
