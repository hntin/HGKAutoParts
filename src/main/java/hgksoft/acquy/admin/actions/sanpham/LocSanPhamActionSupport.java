package hgksoft.acquy.admin.actions.sanpham;

import com.opensymphony.xwork2.ActionSupport;
import hgksoft.acquy.bo.LoaiSanPhamBO;
import hgksoft.acquy.bo.NhaCungCapBO;
import hgksoft.acquy.bo.QuocGiaBO;
import hgksoft.acquy.bo.SanPhamBO;
import hgksoft.acquy.bo.TinhTrangBO;
import hgksoft.acquy.constant.CommonConst;
import hgksoft.acquy.dto.LoaiSanPhamDTO;
import hgksoft.acquy.dto.SanPhamDTO;
import hgksoft.acquy.dto.NhaCCDTO;
import hgksoft.acquy.dto.QuocGiaDTO;
import hgksoft.acquy.dto.TinhTrangDTO;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author HNTIN
 */
public class LocSanPhamActionSupport extends ActionSupport {

    private String selectedLoaiSP;
    private String selectedHangSanXuat;
    private String selectedNoiSanXuat;
    private String selectedTinhTrangSP;

    private HashMap<String, String> noiSanXuatHM = new HashMap<>();
    private HashMap<String, String> hangSanXuatHM = new HashMap<>();
    private HashMap<Integer, String> loaiSanPhamHM = new HashMap<>();
    private HashMap<String, String> tinhTrangHM = new HashMap<>();

    private List<SanPhamDTO> dsSanPhamDTO;
    private String selectedPage;
    private int numberOfPages;
    private String msg;

    //<editor-fold defaultstate="collapsed" desc="Getter & Setter">
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

    public String getSelectedTinhTrangSP() {
        return selectedTinhTrangSP;
    }

    public void setSelectedTinhTrangSP(String selectedTinhTrangSP) {
        this.selectedTinhTrangSP = selectedTinhTrangSP;
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

    public HashMap<Integer, String> getLoaiSanPhamHM() {
        return loaiSanPhamHM;
    }

    public void setLoaiSanPhamHM(HashMap<Integer, String> loaiSanPhamHM) {
        this.loaiSanPhamHM = loaiSanPhamHM;
    }

    public HashMap<String, String> getTinhTrangHM() {
        return tinhTrangHM;
    }

    public void setTinhTrangHM(HashMap<String, String> tinhTrangHM) {
        this.tinhTrangHM = tinhTrangHM;
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

    public LocSanPhamActionSupport() {
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
        LoaiSanPhamBO loaiSPBO = new LoaiSanPhamBO();
        QuocGiaBO quocgiaBO = new QuocGiaBO();
        NhaCungCapBO nccBO = new NhaCungCapBO();
        TinhTrangBO ttBO = new TinhTrangBO();

        List<NhaCCDTO> dsNhaCCDTO = nccBO.getDSTatCaNhaCC();
        List<QuocGiaDTO> dsQuocGiaDTO = quocgiaBO.getDSTatCaQuocGia();
        List<LoaiSanPhamDTO> dsLoaiSPDTO = loaiSPBO.getDSTatCaLoaiSanPham();
        List<TinhTrangDTO> dsTinhTrangDTO = ttBO.getDSTatCaTinhTrang();

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

        for (int i = 0; i < dsTinhTrangDTO.size(); i++) {
            TinhTrangDTO tinhTrangSPDTO = dsTinhTrangDTO.get(i);
            tinhTrangHM.put("0", "Tất cả");
            tinhTrangHM.put(tinhTrangSPDTO.getMaTinhTrang(), tinhTrangSPDTO.getTenTinhTrang());
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
        if (selectedTinhTrangSP == null || selectedTinhTrangSP.equals("0")) {
            selectedTinhTrangSP = "";
        }

        dsSanPhamDTO = sanphamBO.getDSSanPham("", "", "",
                selectedHangSanXuat, selectedNoiSanXuat, selectedLoaiSP, selectedTinhTrangSP);

        //<editor-fold defaultstate="collapsed" desc="Phân trang">
        if (this.dsSanPhamDTO != null && this.dsSanPhamDTO.size() != 0) {
            if (this.dsSanPhamDTO.size() % CommonConst.MAX_ROW_PER_PAGE == 0) {
                this.numberOfPages = dsSanPhamDTO.size() / CommonConst.MAX_ROW_PER_PAGE;
            } else {
                this.numberOfPages = dsSanPhamDTO.size() / CommonConst.MAX_ROW_PER_PAGE + 1;
            }

            if (selectedPage == null) {
                selectedPage = "1";
            }
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
