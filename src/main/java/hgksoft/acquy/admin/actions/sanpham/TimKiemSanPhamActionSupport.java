package hgksoft.acquy.admin.actions.sanpham;

import com.opensymphony.xwork2.ActionSupport;
import hgksoft.acquy.bo.SanPhamBO;
import hgksoft.acquy.constant.CommonConst;
import hgksoft.acquy.dto.SanPhamDTO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author HNTIN
 */
public class TimKiemSanPhamActionSupport extends ActionSupport {

    private String maSanPham;
    private String tenSanPham;

    private List<SanPhamDTO> dsSanPhamDTO;
    private String selectedPage;
    private int numberOfPages;
    private String msg;

    //<editor-fold defaultstate="collapsed" desc="Getter & Setter">
    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public List<SanPhamDTO> getDsSanPhamDTO() {
        return dsSanPhamDTO;
    }

    public void setDsSanPhamDTO(List<SanPhamDTO> dsSanPhamDTO) {
        this.dsSanPhamDTO = dsSanPhamDTO;
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    //</editor-fold>

    public TimKiemSanPhamActionSupport() {
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
        
        SanPhamBO sanphamBO = new SanPhamBO();
        if (maSanPham == null) maSanPham = "";
        if (tenSanPham == null) tenSanPham = "";
        
        dsSanPhamDTO = sanphamBO.getDSSanPham(maSanPham, tenSanPham);
        //<editor-fold defaultstate="collapsed" desc="Phân trang">
        if (this.dsSanPhamDTO != null && this.dsSanPhamDTO.size() != 0) {
            if (this.dsSanPhamDTO.size() % CommonConst.MAX_ROW_PER_PAGE == 0) {
                this.numberOfPages = dsSanPhamDTO.size() / CommonConst.MAX_ROW_PER_PAGE;
            } else {
                this.numberOfPages = dsSanPhamDTO.size() / CommonConst.MAX_ROW_PER_PAGE + 1;
            }

            if (selectedPage == null) selectedPage = "1";
            int beginIdx = (Integer.parseInt(selectedPage) - 1) * CommonConst.MAX_ROW_PER_PAGE;
            int endIdx = beginIdx + CommonConst.MAX_ROW_PER_PAGE;
            if (endIdx > dsSanPhamDTO.size()) {
                endIdx = dsSanPhamDTO.size();
            }
            dsSanPhamDTO = dsSanPhamDTO.subList(beginIdx, endIdx);
            //</editor-fold>
        }
        return SUCCESS;
    }
}
