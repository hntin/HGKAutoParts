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
public class TaoMoiNhaCCActionSupport extends ActionSupport {
 
    private String maNhaCC;
    private String tenNhaCC;
    private String email;
    private String dienThoai;
    private String diaChi;
    private String ghiChu;            
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

    public TaoMoiNhaCCActionSupport() {
    }
    
    @Override
    public void validate() {
        if (maNhaCC == null || maNhaCC.equals("")) {
            addFieldError("maNhaCC", "Nhập mã nhà cung cấp");
        }
        
        if (tenNhaCC == null || tenNhaCC.equals("")) {
            addFieldError("tenNhaCC", "Nhập tên nhà cung cấp");
        } 
        
        if (email == null || email.equals("")) {
            addFieldError("email", "Nhập email nhà cung cấp");
        }
        
        if (dienThoai == null || dienThoai.equals("")) {
            addFieldError("dienThoai", "Nhập điện thoại nhà cung cấp");
        }
        
        if (diaChi == null || diaChi.equals("")) {
            addFieldError("diaChi", "Nhập địa chỉ nhà cung cấp");
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
        
        NhaCungCapBO nccBO = new NhaCungCapBO();
        NhaCCDTO nccDTO = nccBO.getNhaCCDTO(maNhaCC);
        if (nccDTO != null) {
            if (nccDTO.getMaNhaCC() != null && !nccDTO.getMaNhaCC().equals("")) {
                msg = "Mã nhà cung cấp đã tồn tại";
            }
            else {
                nccDTO = new NhaCCDTO();
                nccDTO.setMaNhaCC(maNhaCC);
                nccDTO.setTenNhaCC(tenNhaCC);                
                nccDTO.setEmail(email);
                nccDTO.setDienThoai(dienThoai);
                nccDTO.setDiaChi(diaChi);
                nccDTO.setGhiChu(ghiChu);

                int result = nccBO.createNhaCC(nccDTO);
                if (result == 1)
                    msg = "Thêm mới nhà cung cấp thành công";
            }
        }
        
        return SUCCESS;
    }    
}
