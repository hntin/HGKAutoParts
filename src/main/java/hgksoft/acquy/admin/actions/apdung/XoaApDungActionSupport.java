package hgksoft.acquy.admin.actions.apdung;

import com.opensymphony.xwork2.ActionSupport;
import hgksoft.acquy.bo.LoaiSanPhamBO;
import hgksoft.acquy.bo.NhaCungCapBO;
import hgksoft.acquy.bo.QuocGiaBO;
import hgksoft.acquy.bo.SanPhamBO;
import hgksoft.acquy.dto.SanPhamDongXeDTO;
import hgksoft.acquy.bo.SanPhamDongXeBO;
import hgksoft.acquy.constant.CommonConst;
import hgksoft.acquy.dto.DongXeDTO;
import hgksoft.acquy.dto.LoaiSanPhamDTO;
import hgksoft.acquy.dto.NhaCCDTO;
import hgksoft.acquy.dto.QuocGiaDTO;
import hgksoft.acquy.dto.SanPhamDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author HNTIN
 */
public class XoaApDungActionSupport extends ActionSupport {

    private SanPhamDTO sanphamDTO;
    private List<SanPhamDTO> dsSanPhamDTO;
    private List<DongXeDTO> dsDongXeDTO = new ArrayList<>();
    private List<SanPhamDongXeDTO> dsSanPhamDongXeDTO;
    private String selectedPage;
    private int numberOfPages;
    private String CheckedList;
    private String msg;

    private String selectedLoaiSP;
    private String selectedHangSanXuat;
    private String selectedNoiSanXuat;
    private String selectedMaSanPham;
    private String selectedHangXe;

    private HashMap<Integer, String> loaiSanPhamHM = new HashMap<>();
    private HashMap<String, String> hangSanXuatHM = new HashMap<>();
    private HashMap<String, String> noiSanXuatHM = new HashMap<>();
    private HashMap<String, String> dsSanPhamHM = new HashMap<>();

    public SanPhamDTO getSanphamDTO() {
        return sanphamDTO;
    }

    public void setSanphamDTO(SanPhamDTO sanphamDTO) {
        this.sanphamDTO = sanphamDTO;
    }

    public List<SanPhamDTO> getDsSanPhamDTO() {
        return dsSanPhamDTO;
    }

    public void setDsSanPhamDTO(List<SanPhamDTO> dsSanPhamDTO) {
        this.dsSanPhamDTO = dsSanPhamDTO;
    }

    public List<DongXeDTO> getDsDongXeDTO() {
        return dsDongXeDTO;
    }

    public void setDsDongXeDTO(List<DongXeDTO> dsDongXeDTO) {
        this.dsDongXeDTO = dsDongXeDTO;
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

    public String getSelectedLoaiSP() {
        return selectedLoaiSP;
    }

    public void setSelectedLoaiSP(String selectedLoaiSP) {
        this.selectedLoaiSP = selectedLoaiSP;
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

    public String getSelectedMaSanPham() {
        return selectedMaSanPham;
    }

    public void setSelectedMaSanPham(String selectedMaSanPham) {
        this.selectedMaSanPham = selectedMaSanPham;
    }

    public String getSelectedHangXe() {
        return selectedHangXe;
    }

    public void setSelectedHangXe(String selectedHangXe) {
        this.selectedHangXe = selectedHangXe;
    }

    public HashMap<Integer, String> getLoaiSanPhamHM() {
        return loaiSanPhamHM;
    }

    public void setLoaiSanPhamHM(HashMap<Integer, String> loaiSanPhamHM) {
        this.loaiSanPhamHM = loaiSanPhamHM;
    }

    public HashMap<String, String> getHangSanXuatHM() {
        return hangSanXuatHM;
    }

    public void setHangSanXuatHM(HashMap<String, String> hangSanXuatHM) {
        this.hangSanXuatHM = hangSanXuatHM;
    }

    public HashMap<String, String> getNoiSanXuatHM() {
        return noiSanXuatHM;
    }

    public void setNoiSanXuatHM(HashMap<String, String> noiSanXuatHM) {
        this.noiSanXuatHM = noiSanXuatHM;
    }

    public HashMap<String, String> getDsSanPhamHM() {
        return dsSanPhamHM;
    }

    public void setDsSanPhamHM(HashMap<String, String> dsSanPhamHM) {
        this.dsSanPhamHM = dsSanPhamHM;
    }

    //</editor-fold>
    public XoaApDungActionSupport() {
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
        //<editor-fold defaultstate="collapsed" desc="Khởi tạo các ComboBox">
        SanPhamBO sanphamBO = new SanPhamBO();
        LoaiSanPhamBO loaiSPBO = new LoaiSanPhamBO();
        QuocGiaBO quocgiaBO = new QuocGiaBO();
        NhaCungCapBO nccBO = new NhaCungCapBO();

        List<NhaCCDTO> dsNhaCCDTO = nccBO.getDSTatCaNhaCC();
        List<QuocGiaDTO> dsQuocGiaDTO = quocgiaBO.getDSTatCaQuocGia();
        List<LoaiSanPhamDTO> dsLoaiSPDTO = loaiSPBO.getDSLoaiSanPham("1");

        for (int i = 0; i < dsNhaCCDTO.size(); i++) {
            NhaCCDTO nccDTO = dsNhaCCDTO.get(i);
            hangSanXuatHM.put("0", "Tất cả");
            hangSanXuatHM.put(nccDTO.getMaNhaCC(), nccDTO.getTenNhaCC());
        }

        for (int i = 0; i < dsQuocGiaDTO.size(); i++) {
            QuocGiaDTO quocgiaDTO = dsQuocGiaDTO.get(i);
            noiSanXuatHM.put("0", "Tất cả");
            noiSanXuatHM.put(quocgiaDTO.getMaQuocGia(), quocgiaDTO.getTenQuocGia());
        }

        for (int i = 0; i < dsLoaiSPDTO.size(); i++) {
            LoaiSanPhamDTO loaiSPDTO = dsLoaiSPDTO.get(i);
            loaiSanPhamHM.put(0, "Tất cả");
            loaiSanPhamHM.put(loaiSPDTO.getMaLSP(), loaiSPDTO.getTenLSP());
        }

        if (selectedHangSanXuat == null || selectedHangSanXuat.equals("0")) {
            selectedHangSanXuat = "";
        }
        if (selectedNoiSanXuat == null || selectedNoiSanXuat.equals("0")) {
            selectedNoiSanXuat = "";
        }
        if (selectedLoaiSP == null || selectedLoaiSP.equals("0")) {
            selectedLoaiSP = "";
        }

        dsSanPhamDTO = sanphamBO.getDSSanPham("", "", "",
                selectedHangSanXuat, selectedNoiSanXuat, selectedLoaiSP, "");
        if (dsSanPhamDTO != null && dsSanPhamDTO.size() > 0) {
            dsSanPhamHM.put("0", "Chọn sản phẩm");
            for (int i = 0; i < dsSanPhamDTO.size(); i++) {
                dsSanPhamHM.put(dsSanPhamDTO.get(i).getMaSanPham(), dsSanPhamDTO.get(i).getTenSanPham());
            }
        }
        //</editor-fold>

        msg = "Số lượng áp dụng đã xóa: ";
        SanPhamDongXeBO spdxBO = new SanPhamDongXeBO();

        //<editor-fold defaultstate="collapsed" desc="Xóa áp dụng">
        int soluongApDungXoa = spdxBO.deleteApDung(CheckedList);
        if (soluongApDungXoa != -1) {
            msg += soluongApDungXoa;
        } else {
            msg = "Không thể xóa áp dụng đã chọn, do có ràng buộc dữ liệu !";
        }

        //</editor-fold>
        dsSanPhamDongXeDTO = spdxBO.searchDSSanPhamDongXeTuongThich(selectedMaSanPham, "");
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
