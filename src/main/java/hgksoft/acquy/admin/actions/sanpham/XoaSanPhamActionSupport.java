package hgksoft.acquy.admin.actions.sanpham;

import com.opensymphony.xwork2.ActionSupport;
import hgksoft.acquy.bo.SanPhamBO;
import hgksoft.acquy.constant.CommonConst;
import hgksoft.acquy.dto.SanPhamDTO;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author HNTIN
 */
public class XoaSanPhamActionSupport extends ActionSupport {
    private String maSanPham;
    private String tenSanPham;
    private String moTaSanPham;
    private List<SanPhamDTO> dsSanPhamDTO;
    private HashMap<String, String> noiSanXuatHM = new HashMap<>();
    private HashMap<String, String> hangSanXuatHM = new HashMap<>();
    private String selectedHangSanXuat;
    private String selectedNoiSanXuat;
    private String selectedPage;
    private int numberOfPages;
    private String CheckedList;
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

    public String getMoTaSanPham() {
        return moTaSanPham;
    }

    public void setMoTaSanPham(String moTaSanPham) {
        this.moTaSanPham = moTaSanPham;
    }

    public List<SanPhamDTO> getDsSanPhamDTO() {
        return dsSanPhamDTO;
    }

    public void setDsSanPhamDTO(List<SanPhamDTO> dsSanPhamDTO) {
        this.dsSanPhamDTO = dsSanPhamDTO;
    }

    public HashMap<String, String> getNoiSanXuatHM() {
        return noiSanXuatHM;
    }

    public void setNoiSanXuatHM(HashMap<String, String> noiSanXuatHM) {
        this.noiSanXuatHM = noiSanXuatHM;
    }

    public HashMap<String, String> getHangSanXuatHM() {
        return hangSanXuatHM;
    }

    public void setHangSanXuatHM(HashMap<String, String> hangSanXuatHM) {
        this.hangSanXuatHM = hangSanXuatHM;
    }

    public String getSelectedHangSanXuat() {
        return selectedHangSanXuat;
    }

    public void setSelectedHangSanXuat(String selectedHangSanXuat) {
        this.selectedHangSanXuat = selectedHangSanXuat;
    }

    public String getSelectedNoiSanXuat() {
        return selectedNoiSanXuat;
    }

    public void setSelectedNoiSanXuat(String selectedNoiSanXuat) {
        this.selectedNoiSanXuat = selectedNoiSanXuat;
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

    public XoaSanPhamActionSupport() {
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
        
        msg = "Số lượng sản phẩm đã xóa: ";        
        SanPhamBO sanphamBO = new SanPhamBO();
        //<editor-fold defaultstate="collapsed" desc="Xóa sản phẩm">
        int soluongSPXoa = sanphamBO.deleteSanPham(CheckedList);
        if (soluongSPXoa != -1)
            msg += soluongSPXoa;
        else 
            msg = "Không thể xóa sản phẩm đã chọn, do có ràng buộc dữ liệu !";        
        //</editor-fold>

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
        }
        //</editor-fold>

        return SUCCESS;
    }

}
