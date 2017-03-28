package hgksoft.acquy.admin.actions.hangxe;

import com.opensymphony.xwork2.ActionSupport;
import hgksoft.acquy.bo.HangXeBO;
import hgksoft.acquy.constant.CommonConst;
import hgksoft.acquy.dto.HangXeDTO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author HNTIN
 */
public class XoaHangXeActionSupport extends ActionSupport {
    private String maHangXe;
    private String tenHangXe;
    private List<HangXeDTO> dsHangXeDTO;
    private String selectedPage;
    private int numberOfPages;
    private String CheckedList;
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

    public List<HangXeDTO> getDsHangXeDTO() {
        return dsHangXeDTO;
    }

    public void setDsHangXeDTO(List<HangXeDTO> dsHangXeDTO) {
        this.dsHangXeDTO = dsHangXeDTO;
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
    
    public XoaHangXeActionSupport() {
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
        
        msg = "Số lượng hãng xe đã xóa: ";
        HangXeBO hangxeBO = new HangXeBO();
        //<editor-fold defaultstate="collapsed" desc="Xóa hãng xe ">
        int soluongHXXoa = hangxeBO.deleteHangXe(CheckedList);
        if (soluongHXXoa != -1)
            msg += soluongHXXoa;
        else 
            msg = "Không thể xóa hãng xe đã chọn, do có ràng buộc dữ liệu !"; 
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Quay lại trang quản lý hãng xe">
        dsHangXeDTO = hangxeBO.getDSTatCaHangXe();

        //<editor-fold defaultstate="collapsed" desc="Phân trang">
        if (this.dsHangXeDTO != null) {
            if (this.dsHangXeDTO.size() % CommonConst.MAX_ROW_PER_PAGE == 0) {
                this.numberOfPages = dsHangXeDTO.size() / CommonConst.MAX_ROW_PER_PAGE;
            } else {
                this.numberOfPages = dsHangXeDTO.size() / CommonConst.MAX_ROW_PER_PAGE + 1;
            }

            int beginIdx = (Integer.parseInt(selectedPage) - 1) * CommonConst.MAX_ROW_PER_PAGE;
            int endIdx = beginIdx + CommonConst.MAX_ROW_PER_PAGE;
            if (endIdx > dsHangXeDTO.size()) {
                endIdx = dsHangXeDTO.size();
            }
            dsHangXeDTO = dsHangXeDTO.subList(beginIdx, endIdx);
            //</editor-fold>
        }
        //</editor-fold>
        return SUCCESS;
    }
    
}
