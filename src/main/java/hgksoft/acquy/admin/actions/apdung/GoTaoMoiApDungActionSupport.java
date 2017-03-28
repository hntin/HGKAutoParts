package hgksoft.acquy.admin.actions.apdung;

import com.opensymphony.xwork2.ActionSupport;
import hgksoft.acquy.bo.HangXeBO;
import hgksoft.acquy.bo.LoaiSanPhamBO;
import hgksoft.acquy.bo.NhaCungCapBO;
import hgksoft.acquy.bo.QuocGiaBO;
import hgksoft.acquy.bo.SanPhamBO;
import hgksoft.acquy.bo.SanPhamDongXeBO;
import hgksoft.acquy.constant.CommonConst;
import hgksoft.acquy.dto.DongXeDTO;
import hgksoft.acquy.dto.HangXeDTO;
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
public class GoTaoMoiApDungActionSupport extends ActionSupport {

    private SanPhamDTO sanphamDTO;
    private List<SanPhamDTO> dsSanPhamDTO;
    private List<DongXeDTO> dsDongXeDTO = new ArrayList<>();
    private String selectedPage;
    private int numberOfPages;
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
    private HashMap<String, String> dsHangXeHM = new HashMap<>();

    //<editor-fold defaultstate="collapsed" desc="Getter & Setter">
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

    public HashMap<String, String> getDsHangXeHM() {
        return dsHangXeHM;
    }

    public void setDsHangXeHM(HashMap<String, String> dsHangXeHM) {
        this.dsHangXeHM = dsHangXeHM;
    }
//</editor-fold>

    public GoTaoMoiApDungActionSupport() {
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

        if (selectedMaSanPham != null && !selectedMaSanPham.equalsIgnoreCase("")) {
            this.sanphamDTO = sanphamBO.getSanPhamDTO(selectedMaSanPham);
        }

        HangXeBO hxBO = new HangXeBO();
        List<HangXeDTO> dsHangXeDTO = hxBO.getDSTatCaHangXe();
        if (dsHangXeDTO != null && dsHangXeDTO.size() > 0) {
            for (int i = 0; i < dsHangXeDTO.size(); i++) {
                HangXeDTO hxDTO = dsHangXeDTO.get(i);
                dsHangXeHM.put(hxDTO.getMaHangXe(), hxDTO.getTenHangXe());
            }
        }

        SanPhamDongXeBO spdxBO = new SanPhamDongXeBO();
        if (selectedHangXe == null) {
            selectedHangXe = "1"; // default value
        }

        this.dsDongXeDTO = spdxBO.getDSDongXeChuaApDung(selectedMaSanPham, selectedHangXe);
        //<editor-fold defaultstate="collapsed" desc="Phân trang">
        if (this.dsDongXeDTO != null && this.dsDongXeDTO.size() > 0) {
            if (this.dsDongXeDTO.size() % CommonConst.MAX_ROW_PER_PAGE == 0) {
                this.numberOfPages = dsDongXeDTO.size() / CommonConst.MAX_ROW_PER_PAGE;
            } else {
                this.numberOfPages = dsDongXeDTO.size() / CommonConst.MAX_ROW_PER_PAGE + 1;
            }

            if (selectedPage == null) {
                selectedPage = "1"; // default is the first page
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
