package hgksoft.acquy.admin.actions.loaixe;

import com.opensymphony.xwork2.ActionSupport;
import hgksoft.acquy.bo.HangXeBO;
import hgksoft.acquy.bo.LoaiXeBO;
import hgksoft.acquy.constant.CommonConst;
import hgksoft.acquy.dto.HangXeDTO;
import hgksoft.acquy.dto.LoaiXeDTO;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author HNTIN
 */
public class XoaLoaiXeActionSupport extends ActionSupport {

    private List<LoaiXeDTO> dsLoaiXeDTO;
    private String selectedHangXe;
    private HashMap<String, String> dsHangXeHM = new HashMap<String, String>();
    private String selectedPage;
    private int numberOfPages;
    private String CheckedList;
    private String msg;

    public List<LoaiXeDTO> getDsLoaiXeDTO() {
        return dsLoaiXeDTO;
    }

    public void setDsLoaiXeDTO(List<LoaiXeDTO> dsLoaiXeDTO) {
        this.dsLoaiXeDTO = dsLoaiXeDTO;
    }

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

    public XoaLoaiXeActionSupport() {
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
        LoaiXeBO loaixeBO = new LoaiXeBO();
        //<editor-fold defaultstate="collapsed" desc="Xóa dòng xe">
        int soluongLoaiXeXoa = loaixeBO.deleteLoaiXe(CheckedList);
        if (soluongLoaiXeXoa != -1) {
            msg += soluongLoaiXeXoa;
        } else {
            msg = "Không thể xóa nhà cung cấp đã chọn, do có ràng buộc dữ liệu !";
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Quay lại trang quản lý loại xe">
        HangXeBO hxBO = new HangXeBO();
        List<HangXeDTO> dsHangXeDTO = hxBO.getDSTatCaHangXe();
        if (dsHangXeDTO != null && dsHangXeDTO.size() > 0) {
            this.dsHangXeHM.put("0", "Chọn hãng xe");
            for (int i = 0; i < dsHangXeDTO.size(); i++) {
                this.dsHangXeHM.put(dsHangXeDTO.get(i).getMaHangXe(), dsHangXeDTO.get(i).getTenHangXe());
            }
        }

        if (selectedHangXe != null) {
            LoaiXeBO lxBO = new LoaiXeBO();
            this.dsLoaiXeDTO = lxBO.getDSLoaiXe(selectedHangXe);
        }

        //<editor-fold defaultstate="collapsed" desc="Phân trang">
        if (this.dsLoaiXeDTO != null) {
            if (this.dsLoaiXeDTO.size() % CommonConst.MAX_ROW_PER_PAGE == 0) {
                this.numberOfPages = dsLoaiXeDTO.size() / CommonConst.MAX_ROW_PER_PAGE;
            } else {
                this.numberOfPages = dsLoaiXeDTO.size() / CommonConst.MAX_ROW_PER_PAGE + 1;
            }

            int beginIdx = (Integer.parseInt(selectedPage) - 1) * CommonConst.MAX_ROW_PER_PAGE;
            int endIdx = beginIdx + CommonConst.MAX_ROW_PER_PAGE;
            if (endIdx > dsLoaiXeDTO.size()) {
                endIdx = dsLoaiXeDTO.size();
            }
            dsLoaiXeDTO = dsLoaiXeDTO.subList(beginIdx, endIdx);
        }
        //</editor-fold>

        return SUCCESS;
    }

}
