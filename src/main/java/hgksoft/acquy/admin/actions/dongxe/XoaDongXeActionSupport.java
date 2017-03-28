package hgksoft.acquy.admin.actions.dongxe;

import com.opensymphony.xwork2.ActionSupport;
import hgksoft.acquy.bo.DongXeBO;
import hgksoft.acquy.bo.HangXeBO;
import hgksoft.acquy.constant.CommonConst;
import hgksoft.acquy.dto.DongXeDTO;
import hgksoft.acquy.dto.HangXeDTO;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author HNTIN
 */
public class XoaDongXeActionSupport extends ActionSupport {

    private List<DongXeDTO> dsDongXeDTO;
    private String selectedHangXe;
    private String selectedLoaiXe;
    private HashMap<String, String> dsHangXeHM = new HashMap<String, String>();
    private HashMap<String, String> dsLoaiXeHM = new HashMap<String, String>();
    private String selectedPage;
    private int numberOfPages;
    private String CheckedList;
    private String msg;

    public String getSelectedHangXe() {
        return selectedHangXe;
    }

    public void setSelectedHangXe(String selectedHangXe) {
        this.selectedHangXe = selectedHangXe;
    }

    public HashMap<String, String> getDsHangXeHM() {
        return dsHangXeHM;
    }

    public void setDsHangXeHM(HashMap<String, String> dsHangXeHM) {
        this.dsHangXeHM = dsHangXeHM;
    }

    public List<DongXeDTO> getDsDongXeDTO() {
        return dsDongXeDTO;
    }

    public void setDsDongXeDTO(List<DongXeDTO> dsDongXeDTO) {
        this.dsDongXeDTO = dsDongXeDTO;
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

    public String getSelectedLoaiXe() {
        return selectedLoaiXe;
    }

    public void setSelectedLoaiXe(String selectedLoaiXe) {
        this.selectedLoaiXe = selectedLoaiXe;
    }

    public HashMap<String, String> getDsLoaiXeHM() {
        return dsLoaiXeHM;
    }

    public void setDsLoaiXeHM(HashMap<String, String> dsLoaiXeHM) {
        this.dsLoaiXeHM = dsLoaiXeHM;
    }

    public XoaDongXeActionSupport() {
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

        msg = "Số lượng dòng xe đã xóa: ";
        DongXeBO dongxeBO = new DongXeBO();
        //<editor-fold defaultstate="collapsed" desc="Xóa dòng xe">
        int soluongDongXeXoa = dongxeBO.deleteDongXe(CheckedList);
        if (soluongDongXeXoa != -1) {
            msg += soluongDongXeXoa;
        } else {
            msg = "Không thể xóa dòng xe đã chọn, do có ràng buộc dữ liệu !"; 
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Quay lại trang quản lý dòng xe">
        HangXeBO hxBO = new HangXeBO();
        List<HangXeDTO> dsHangXeDTO = hxBO.getDSTatCaHangXe();
        if (dsHangXeDTO != null && dsHangXeDTO.size() > 0) {
            for (int i = 0; i < dsHangXeDTO.size(); i++) {
                this.dsHangXeHM.put(dsHangXeDTO.get(i).getMaHangXe(), dsHangXeDTO.get(i).getTenHangXe());
            }
        }
        if (selectedHangXe != null) {
            dsDongXeDTO = dongxeBO.getDSDongXeTheoLX(selectedLoaiXe, selectedLoaiXe);
        }

        //<editor-fold defaultstate="collapsed" desc="Phân trang">
        if (this.dsDongXeDTO != null) {
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
        //</editor-fold>

        return SUCCESS;
    }
}
