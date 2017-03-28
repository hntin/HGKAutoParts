package hgksoft.acquy.admin.actions.apdung;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import hgksoft.acquy.bo.SanPhamDongXeBO;
import hgksoft.acquy.constant.CommonConst;
import hgksoft.acquy.dto.SanPhamDongXeDTO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author HNTIN
 */
public class TimKiemApDungActionSupport extends ActionSupport {
    private String maSanPham;
    private String tenSanPham;
    private List<SanPhamDongXeDTO> dsSanPhamDongXeDTO;
    private String selectedPage;
    private int numberOfPages;
    private String msg;

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

    public List<SanPhamDongXeDTO> getDsSanPhamDongXeDTO() {
        return dsSanPhamDongXeDTO;
    }

    public void setDsSanPhamDongXeDTO(List<SanPhamDongXeDTO> dsSanPhamDongXeDTO) {
        this.dsSanPhamDongXeDTO = dsSanPhamDongXeDTO;
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

    
    public TimKiemApDungActionSupport() {
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
        
        SanPhamDongXeBO spdxBO = new SanPhamDongXeBO();
        dsSanPhamDongXeDTO = spdxBO.searchDSSanPhamDongXeTuongThich(maSanPham, tenSanPham);        
        //<editor-fold defaultstate="collapsed" desc="Phân trang">
        if (this.dsSanPhamDongXeDTO != null && this.dsSanPhamDongXeDTO.size() > 0) {
            if (this.dsSanPhamDongXeDTO.size() % CommonConst.MAX_ROW_PER_PAGE == 0) {
                this.numberOfPages = dsSanPhamDongXeDTO.size() / CommonConst.MAX_ROW_PER_PAGE;
            } else {
                this.numberOfPages = dsSanPhamDongXeDTO.size() / CommonConst.MAX_ROW_PER_PAGE + 1;
            }

            int beginIdx = (Integer.parseInt(selectedPage) - 1) * CommonConst.MAX_ROW_PER_PAGE;
            int endIdx = beginIdx + CommonConst.MAX_ROW_PER_PAGE;
            if (endIdx > dsSanPhamDongXeDTO.size()) {
                endIdx = dsSanPhamDongXeDTO.size();
            }
            dsSanPhamDongXeDTO = dsSanPhamDongXeDTO.subList(beginIdx, endIdx);
            //</editor-fold>
        }
        
        return SUCCESS;
    }

}
