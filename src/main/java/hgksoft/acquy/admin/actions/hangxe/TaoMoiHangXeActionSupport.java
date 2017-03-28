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
public class TaoMoiHangXeActionSupport extends ActionSupport {
    private String maHangXe;
    private String tenHangXe;
    private String ghiChu;
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public TaoMoiHangXeActionSupport() {
    }

    @Override
    public void validate() {
        if (maHangXe == null || maHangXe.equals("")) {
            addFieldError("maHangXe", "Nhập mã hãng xe");
        }
        
        if (tenHangXe == null || tenHangXe.equals("")) {
            addFieldError("tenHangXe", "Nhập tên hãng xe");
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
        
        HangXeBO hangxeBO = new HangXeBO();
        HangXeDTO hangxeDTO = hangxeBO.getHangXeDTO(maHangXe);
        if (hangxeDTO != null) {
            if (hangxeDTO.getMaHangXe() != null && !hangxeDTO.getMaHangXe().equals("")) {
                msg = "Mã hãng xe đã tồn tại";
            } else {
                hangxeDTO = new HangXeDTO();
                hangxeDTO.setMaHangXe(maHangXe);
                hangxeDTO.setTenHangXe(tenHangXe);
                hangxeDTO.setGhiChu(ghiChu);

                int result = hangxeBO.createHangXe(hangxeDTO);
                if (result == 1) {
                    msg = "Thêm mới hãng xe thành công";
                }
            }
        }
        return SUCCESS;
    }

}
