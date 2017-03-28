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
public class CapNhatNhaCCActionSupport extends ActionSupport {
    private String maNhaCC;
    private String tenNhaCC;
    private String email;
    private String dienThoai;
    private String diaChi;
    private String ghiChu;
    
    private NhaCCDTO nccDTO;

    public NhaCCDTO getNccDTO() {
        return nccDTO;
    }

    public void setNccDTO(NhaCCDTO nccDTO) {
        this.nccDTO = nccDTO;
    }
    
    private String msg;

    public String getMaNhaCC() {
        return maNhaCC;
    }

    public void setMaNhaCC(String maNhaCC) {
        this.maNhaCC = maNhaCC;
    }

    public String getTenNhaCC() {
        return tenNhaCC;
    }

    public void setTenNhaCC(String tenNhaCC) {
        this.tenNhaCC = tenNhaCC;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
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

    public CapNhatNhaCCActionSupport() {
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
        
        NhaCCDTO nccDTO = new NhaCCDTO();
        nccDTO.setMaNhaCC(maNhaCC);
        nccDTO.setTenNhaCC(tenNhaCC);
        nccDTO.setEmail(email);
        nccDTO.setDienThoai(dienThoai);
        nccDTO.setDiaChi(diaChi);
        nccDTO.setGhiChu(ghiChu);
        
        NhaCungCapBO nccBO = new NhaCungCapBO();
        int result = nccBO.updateNhaCC(nccDTO);
        if (result == 1) {
            msg = "Cập nhật thành công";
        }
        else {
            msg = "Không thể cập nhật";
        }
        
        this.nccDTO = nccDTO;
        return SUCCESS;
    }
}
