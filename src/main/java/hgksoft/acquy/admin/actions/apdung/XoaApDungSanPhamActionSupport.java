package hgksoft.acquy.admin.actions.apdung;

import com.opensymphony.xwork2.ActionSupport;
import hgksoft.acquy.bo.SanPhamBO;
import hgksoft.acquy.bo.SanPhamDongXeBO;
import hgksoft.acquy.constant.CommonConst;
import hgksoft.acquy.dto.DongXeDTO;
import hgksoft.acquy.dto.SanPhamDTO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author HNTIN
 */
public class XoaApDungSanPhamActionSupport extends ActionSupport {

    private String maSanPham;
    private SanPhamDTO sanphamDTO;
    private List<DongXeDTO> dsDongXeDTO;
    private String apDungCheckBox;
    private String selectedPage;
    private int numberOfPages;
    private String msg;

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public SanPhamDTO getSanphamDTO() {
        return sanphamDTO;
    }

    public void setSanphamDTO(SanPhamDTO sanphamDTO) {
        this.sanphamDTO = sanphamDTO;
    }

    public List<DongXeDTO> getDsDongXeDTO() {
        return dsDongXeDTO;
    }

    public void setDsDongXeDTO(List<DongXeDTO> dsDongXeDTO) {
        this.dsDongXeDTO = dsDongXeDTO;
    }

    public String getApDungCheckBox() {
        return apDungCheckBox;
    }

    public void setApDungCheckBox(String apDungCheckBox) {
        this.apDungCheckBox = apDungCheckBox;
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

    public XoaApDungSanPhamActionSupport() {
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

        // Xóa danh sách áp dụng đã chọn
        SanPhamDongXeBO spdxBO = new SanPhamDongXeBO();
        int soluongApDungXoa = spdxBO.deleteApDung(maSanPham, apDungCheckBox);
        msg = "Số áp dụng đã xóa: ";
        if (soluongApDungXoa != -1) {
            msg += soluongApDungXoa;
        } else {
            msg = "Không thể xóa áp dụng đã chọn, do có ràng buộc dữ liệu !";
        }

        // Lấy danh sách áp dụng liên quan sản phẩm
        SanPhamBO spBO = new SanPhamBO();
        this.sanphamDTO = spBO.getSanPhamDTO(maSanPham);
        dsDongXeDTO = spdxBO.getDSDongXeTuongThichSanPham(maSanPham);

        //<editor-fold defaultstate="collapsed" desc="Phân trang">
        if (this.dsDongXeDTO != null && this.dsDongXeDTO.size() > 0) {
            if (this.dsDongXeDTO.size() % CommonConst.MAX_ROW_PER_PAGE == 0) {
                this.numberOfPages = dsDongXeDTO.size() / CommonConst.MAX_ROW_PER_PAGE;
            } else {
                this.numberOfPages = dsDongXeDTO.size() / CommonConst.MAX_ROW_PER_PAGE + 1;
            }

            int beginIdx = (Integer.parseInt(selectedPage) - 1) * CommonConst.MAX_ROW_PER_PAGE;
            int endIdx = beginIdx + CommonConst.MAX_ROW_PER_PAGE;
            if (endIdx > dsDongXeDTO.size()) {
                endIdx = dsDongXeDTO.size();
            }
            dsDongXeDTO = dsDongXeDTO.subList(beginIdx, endIdx);
            //</editor-fold>
        }

        return SUCCESS;
    }

}
