package hgksoft.acquy.admin.actions.nhacc;

import com.opensymphony.xwork2.ActionSupport;
import hgksoft.acquy.bo.NhaCungCapBO;
import hgksoft.acquy.constant.CommonConst;
import hgksoft.acquy.dto.NhaCCDTO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author HNTIN
 */
public class XoaNhaCCActionSupport extends ActionSupport {
    private String maNhaCC;
    private String tenNhaCC;
    private List<NhaCCDTO> dsNhaCCDTO;
    private String selectedPage;
    private int numberOfPages;
    private String CheckedList;
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

    public List<NhaCCDTO> getDsNhaCCDTO() {
        return dsNhaCCDTO;
    }

    public void setDsNhaCCDTO(List<NhaCCDTO> dsNhaCCDTO) {
        this.dsNhaCCDTO = dsNhaCCDTO;
    }

    public String getSelectedPage() {
        return selectedPage;
    }

    public void setSelectedPage(String selectedPage) {
        this.selectedPage = selectedPage;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getCheckedList() {
        return CheckedList;
    }

    public void setCheckedList(String CheckedList) {
        this.CheckedList = CheckedList;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public XoaNhaCCActionSupport() {
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
        
        msg = "Số lượng nhà cung cấp đã xóa: ";
        NhaCungCapBO nccBO = new NhaCungCapBO();
        //<editor-fold defaultstate="collapsed" desc="Xóa Nhà CC">
        int soluongNCCXoa = nccBO.deleteNhaCC(CheckedList);
        if (soluongNCCXoa != -1)
            msg += soluongNCCXoa;
        else 
            msg = "Không thể xóa nhà cung cấp đã chọn, do có ràng buộc dữ liệu !";  
        
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Quay lại trang quản lý nhà cung cấp">
        dsNhaCCDTO = nccBO.getDSTatCaNhaCC();

        //<editor-fold defaultstate="collapsed" desc="Phân trang">
        if (this.dsNhaCCDTO != null) {
            if (this.dsNhaCCDTO.size() % CommonConst.MAX_ROW_PER_PAGE == 0) {
                this.numberOfPages = dsNhaCCDTO.size() / CommonConst.MAX_ROW_PER_PAGE;
            } else {
                this.numberOfPages = dsNhaCCDTO.size() / CommonConst.MAX_ROW_PER_PAGE + 1;
            }

            int beginIdx = (Integer.parseInt(selectedPage) - 1) * CommonConst.MAX_ROW_PER_PAGE;
            int endIdx = beginIdx + CommonConst.MAX_ROW_PER_PAGE;
            if (endIdx > dsNhaCCDTO.size()) {
                endIdx = dsNhaCCDTO.size();
            }
            dsNhaCCDTO = dsNhaCCDTO.subList(beginIdx, endIdx);
            //</editor-fold>
        }
        //</editor-fold>
        
        return SUCCESS;
    }
}
