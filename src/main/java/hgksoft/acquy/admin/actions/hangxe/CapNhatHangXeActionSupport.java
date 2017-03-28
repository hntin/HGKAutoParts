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
public class CapNhatHangXeActionSupport extends ActionSupport {

    private String maHangXe;
    private String tenHangXe;
    private String ghiChu;
    private HangXeDTO hxDTO;
    private String msg;

    public String getMaHangXe() {
        return maHangXe;
    }

    public void setMaHangXe(String maHangXe) {
        this.maHangXe = maHangXe;
    }

    public String getTenHangXe() {
        return tenHangXe;
    }

    public void setTenHangXe(String tenHangXe) {
        this.tenHangXe = tenHangXe;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public HangXeDTO getHxDTO() {
        return hxDTO;
    }

    public void setHxDTO(HangXeDTO hxDTO) {
        this.hxDTO = hxDTO;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public CapNhatHangXeActionSupport() {
    }

    @Override
    public void validate() {
        try {
            HangXeBO hxBO = new HangXeBO();
            this.hxDTO = hxBO.getHangXeDTO(maHangXe);
            
            if (maHangXe == null || maHangXe.equals("")) {
                addFieldError("maHangXe", "Nhập mã hãng xe");
                this.hxDTO.setMaHangXe("");
            }
            if (tenHangXe == null || tenHangXe.equals("")) {
                addFieldError("tenHangXe", "Nhập tên hãng xe");
                this.hxDTO.setTenHangXe("");
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

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
        
        HangXeDTO hangxeDTO = new HangXeDTO();
        hangxeDTO.setMaHangXe(maHangXe);
        hangxeDTO.setTenHangXe(tenHangXe);
        hangxeDTO.setGhiChu(ghiChu);

        HangXeBO hxBO = new HangXeBO();
        int result = hxBO.updateHangXe(hangxeDTO);
        if (result == 1) {
            msg = "Cập nhật thành công";
        } else {
            msg = "Không thể cập nhật";
        }

        this.hxDTO = hangxeDTO;
        return SUCCESS;
    }
}
