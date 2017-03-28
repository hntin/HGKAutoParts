package hgksoft.acquy.admin.actions.nguoidung;

import com.opensymphony.xwork2.ActionSupport;
import hgksoft.acquy.bo.NguoiDungBO;
import hgksoft.acquy.constant.CommonConst;
import hgksoft.acquy.dto.NguoiDungDTO;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author HNTIN
 */
public class XoaNguoiDungActionSupport extends ActionSupport {

    private String maNguoiDung;
    private String tenNguoiDung;
    private List<NguoiDungDTO> dsNguoiDungDTO;
    private HashMap<Integer, String> loaiNguoiDungHM = new HashMap<>();
    private String selectedPage;
    private int numberOfPages;
    private String selectedLoaiND;
    private String CheckedList;
    private String msg;

    //<editor-fold defaultstate="collapsed" desc="Getter & Setter">
    public String getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(String maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public String getTenNguoiDung() {
        return tenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        this.tenNguoiDung = tenNguoiDung;
    }

    public HashMap<Integer, String> getLoaiNguoiDungHM() {
        return loaiNguoiDungHM;
    }

    public void setLoaiNguoiDungHM(HashMap<Integer, String> loaiNguoiDungHM) {
        this.loaiNguoiDungHM = loaiNguoiDungHM;
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

    public String getSelectedLoaiND() {
        return selectedLoaiND;
    }

    public void setSelectedLoaiND(String selectedLoaiND) {
        this.selectedLoaiND = selectedLoaiND;
    }

    public List<NguoiDungDTO> getDsNguoiDungDTO() {
        return dsNguoiDungDTO;
    }

    public void setDsNguoiDungDTO(List<NguoiDungDTO> dsNguoiDungDTO) {
        this.dsNguoiDungDTO = dsNguoiDungDTO;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCheckedList() {
        return CheckedList;
    }

    public void setCheckedList(String CheckedList) {
        this.CheckedList = CheckedList;
    }    
    //</editor-fold>

    public XoaNguoiDungActionSupport() {
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
        
        msg = "Số lượng người dùng đã xóa: ";
        NguoiDungBO nguoidungBO = new NguoiDungBO();
        
        //<editor-fold defaultstate="collapsed" desc="Xóa người dùng">
        int soluongNDXoa = nguoidungBO.deleteNguoiDung(CheckedList);
        msg += soluongNDXoa;
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Quay lại trang quản lý người dùng">
        loaiNguoiDungHM.put(0, "Tất cả");
        loaiNguoiDungHM.put(1, "Quản trị");
        loaiNguoiDungHM.put(2, "Nhân viên");
        if (selectedLoaiND != null) {
            if (selectedLoaiND.equals("0")) {
                dsNguoiDungDTO = nguoidungBO.getDSNguoiDung(maNguoiDung, tenNguoiDung);
            } else {
                dsNguoiDungDTO = nguoidungBO.getDSNguoiDung(maNguoiDung, tenNguoiDung, selectedLoaiND);
            }
        }
        
        //<editor-fold defaultstate="collapsed" desc="Phân trang">
        if (this.dsNguoiDungDTO != null) {
            if (this.dsNguoiDungDTO.size() % CommonConst.MAX_ROW_PER_PAGE == 0) {
                this.numberOfPages = dsNguoiDungDTO.size() / CommonConst.MAX_ROW_PER_PAGE;
            } else {
                this.numberOfPages = dsNguoiDungDTO.size() / CommonConst.MAX_ROW_PER_PAGE + 1;
            }
            
            int beginIdx = (Integer.parseInt(selectedPage) - 1) * CommonConst.MAX_ROW_PER_PAGE;
            int endIdx = beginIdx + CommonConst.MAX_ROW_PER_PAGE;
            if (endIdx > dsNguoiDungDTO.size()) {
                endIdx = dsNguoiDungDTO.size();
            }
            dsNguoiDungDTO = dsNguoiDungDTO.subList(beginIdx, endIdx);
            //</editor-fold>
        }
        //</editor-fold>

        return "success";
    }

}
