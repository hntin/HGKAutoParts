package hgksoft.acquy.admin.actions.sanpham;

import com.opensymphony.xwork2.ActionSupport;
import hgksoft.acquy.bo.LoaiSanPhamBO;
import hgksoft.acquy.bo.NhaCungCapBO;
import hgksoft.acquy.bo.QuocGiaBO;
import hgksoft.acquy.bo.SanPhamBO;
import hgksoft.acquy.bo.TinhTrangBO;
import hgksoft.acquy.dto.LoaiSanPhamDTO;
import hgksoft.acquy.dto.NhaCCDTO;
import hgksoft.acquy.dto.QuocGiaDTO;
import hgksoft.acquy.dto.SanPhamDTO;
import hgksoft.acquy.dto.TinhTrangDTO;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author HNTIN
 */
public class TaoMoiSanPhamActionSupport extends ActionSupport {

    private String maSP;
    private String tenSP;
    private String moTaSP;
    private String gia;
    private int selectedLoaiSP;
    private String selectedNoiSanXuat;
    private String selectedHangSanXuat;
    private File uploadImage;
    private String uploadImageFileName;
    private String selectedTinhTrangSP;
    private String msg;

    private HashMap<String, String> noiSanXuatHM = new HashMap<>();
    private HashMap<String, String> hangSanXuatHM = new HashMap<>();
    private HashMap<Integer, String> loaiSanPhamHM = new HashMap<>();
    private HashMap<String, String> tinhTrangHM = new HashMap<>();

    //<editor-fold defaultstate="collapsed" desc="Getter & Setter">
    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getMoTaSP() {
        return moTaSP;
    }

    public void setMoTaSP(String moTaSP) {
        this.moTaSP = moTaSP;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public int getSelectedLoaiSP() {
        return selectedLoaiSP;
    }

    public void setSelectedLoaiSP(int selectedLoaiSP) {
        this.selectedLoaiSP = selectedLoaiSP;
    }

    public String getSelectedNoiSanXuat() {
        return selectedNoiSanXuat;
    }

    public void setSelectedNoiSanXuat(String selectedNoiSanXuat) {
        this.selectedNoiSanXuat = selectedNoiSanXuat;
    }

    public String getSelectedHangSanXuat() {
        return selectedHangSanXuat;
    }

    public void setSelectedHangSanXuat(String selectedHangSanXuat) {
        this.selectedHangSanXuat = selectedHangSanXuat;
    }

    public File getUploadImage() {
        return uploadImage;
    }

    public void setUploadImage(File uploadImage) {
        this.uploadImage = uploadImage;
    }

    public String getUploadImageFileName() {
        return uploadImageFileName;
    }

    public void setUploadImageFileName(String uploadImageFileName) {
        this.uploadImageFileName = uploadImageFileName;
    }

    public String getSelectedTinhTrangSP() {
        return selectedTinhTrangSP;
    }

    public void setSelectedTinhTrangSP(String selectedTinhTrangSP) {
        this.selectedTinhTrangSP = selectedTinhTrangSP;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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
//</editor-fold>

    public TaoMoiSanPhamActionSupport() {
    }

    @Override
    public void validate() {
        Pattern pattern = Pattern.compile("\\W");
        if (maSP == null || maSP.equals("")) {
            addFieldError("maSP", "Nhập mã sản phẩm");
        }

        if (tenSP == null || tenSP.equals("")) {
            addFieldError("tenSP", "Nhập tên sản phẩm");
        }

        if (moTaSP == null || moTaSP.equals("")) {
            addFieldError("moTaSP", "Nhập mô tả sản phẩm");
        }

        if (gia == null || gia.equals("")) {
            addFieldError("gia", "Nhập giá sản phẩm");
        }

        LoaiSanPhamBO loaiSPBO = new LoaiSanPhamBO();
        QuocGiaBO quocgiaBO = new QuocGiaBO();
        NhaCungCapBO nccBO = new NhaCungCapBO();
        TinhTrangBO ttBO = new TinhTrangBO();

        try {
            List<NhaCCDTO> dsNhaCCDTO = nccBO.getDSTatCaNhaCC();
            List<QuocGiaDTO> dsQuocGiaDTO = quocgiaBO.getDSTatCaQuocGia();
            List<LoaiSanPhamDTO> dsLoaiSPDTO = loaiSPBO.getDSTatCaLoaiSanPham();
            List<TinhTrangDTO> dsTinhTrangDTO = ttBO.getDSTatCaTinhTrang();

            for (int i = 0; i < dsNhaCCDTO.size(); i++) {
                NhaCCDTO nccDTO = dsNhaCCDTO.get(i);
                hangSanXuatHM.put(nccDTO.getMaNhaCC(), nccDTO.getTenNhaCC());
            }

            for (int i = 0; i < dsQuocGiaDTO.size(); i++) {
                QuocGiaDTO quocgiaDTO = dsQuocGiaDTO.get(i);
                noiSanXuatHM.put(quocgiaDTO.getMaQuocGia(), quocgiaDTO.getTenQuocGia());
            }

            for (int i = 0; i < dsLoaiSPDTO.size(); i++) {
                LoaiSanPhamDTO loaiSPDTO = dsLoaiSPDTO.get(i);
                loaiSanPhamHM.put(loaiSPDTO.getMaLSP(), loaiSPDTO.getTenLSP());
            }

            for (int i = 0; i < dsTinhTrangDTO.size(); i++) {
                TinhTrangDTO tinhTrangSPDTO = dsTinhTrangDTO.get(i);
                tinhTrangHM.put(tinhTrangSPDTO.getMaTinhTrang(), tinhTrangSPDTO.getTenTinhTrang());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
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
        SanPhamDTO sanphamDTO = sanphamBO.getSanPhamDTO(maSP);
        if (sanphamDTO != null) {
            if (sanphamDTO.getMaSanPham() != null && !sanphamDTO.getMaSanPham().equals("")) {
                addFieldError("maSP", "Mã sản phẩm đã tồn tại. Vui lòng nhập mã khác!");;
            } else {
                sanphamDTO = initSanPhamDTOFromView();
                int result = sanphamBO.createSanPham(sanphamDTO, uploadImage, uploadImageFileName);
                if (result == 1) {
                    msg = "Thêm mới sản phẩm thành công";
                }
            }
        }

        return SUCCESS;
    }

    private SanPhamDTO initSanPhamDTOFromView() {
        SanPhamDTO spDTO = new SanPhamDTO();
        spDTO.setMaSanPham(maSP.trim());
        spDTO.setTenSanPham(tenSP.trim());
        spDTO.setMoTaSanPham(moTaSP.trim());
        spDTO.setGia(gia.trim());
        spDTO.setMaTinhTrang(selectedTinhTrangSP);
        spDTO.setMaNhaCC(selectedHangSanXuat);
        spDTO.setMaNuocSanXuat(selectedNoiSanXuat);
        spDTO.setMaLoaiSanPham(selectedLoaiSP);
        return spDTO;
    }
}
