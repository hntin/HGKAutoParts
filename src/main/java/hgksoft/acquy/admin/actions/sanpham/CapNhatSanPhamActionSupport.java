package hgksoft.acquy.admin.actions.sanpham;

import com.opensymphony.xwork2.ActionSupport;
import hgksoft.acquy.bo.LoaiSanPhamBO;
import hgksoft.acquy.bo.NhaCungCapBO;
import hgksoft.acquy.bo.QuocGiaBO;
import hgksoft.acquy.bo.SanPhamBO;
import hgksoft.acquy.bo.TinhTrangBO;
import hgksoft.acquy.constant.CommonConst;
import hgksoft.acquy.dto.LoaiSanPhamDTO;
import hgksoft.acquy.dto.NhaCCDTO;
import hgksoft.acquy.dto.QuocGiaDTO;
import hgksoft.acquy.dto.SanPhamDTO;
import hgksoft.acquy.dto.TinhTrangDTO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import static org.apache.struts2.ServletActionContext.getServletContext;

/**
 *
 * @author HNTIN
 */
public class CapNhatSanPhamActionSupport extends ActionSupport {

    private SanPhamDTO sanphamDTO;
    private String maSanPhamUpdate;
    private String maSanPham;
    private String tenSanPham;
    private String moTaSanPham;
    private String gia;
    private int selectedLoaiSP;
    private String selectedNoiSanXuat;
    private String selectedHangSanXuat;
    private File uploadImage;
    private String uploadImageFileName;
    private String selectedTinhTrangSP;
    private String msg = "";

    private HashMap<String, String> noiSanXuatHM = new HashMap<>();
    private HashMap<String, String> hangSanXuatHM = new HashMap<>();
    private HashMap<Integer, String> loaiSanPhamHM = new HashMap<>();
    private HashMap<String, String> tinhTrangHM = new HashMap<>();

    //<editor-fold defaultstate="collapsed" desc="Getter & Setter">
    public SanPhamDTO getSanphamDTO() {
        return sanphamDTO;
    }
    
    public void setSanphamDTO(SanPhamDTO sanphamDTO) {
        this.sanphamDTO = sanphamDTO;
    }
    
    public String getMaSanPhamUpdate() {
        return maSanPhamUpdate;
    }
    
    public void setMaSanPhamUpdate(String maSanPhamUpdate) {
        this.maSanPhamUpdate = maSanPhamUpdate;
    }
    
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

    @Override
    public void validate() {
        SanPhamBO sanphamBO = new SanPhamBO();
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

            this.sanphamDTO = initSanPhamDTOFromView();
            this.sanphamDTO.setHinhDaiDien(sanphamBO.getSanPhamDTO(maSanPham).getHinhDaiDien());

            // Có sự thay đổi mã sản phẩm
            if (!maSanPhamUpdate.equalsIgnoreCase(maSanPham)) {
                SanPhamDTO spDTO = sanphamBO.getSanPhamDTO(maSanPhamUpdate);
                if (spDTO.getMaSanPham() != null && !spDTO.getMaSanPham().equals("")) {
                    this.sanphamDTO.setMaSanPham(maSanPham);
                    addFieldError("maSanPham", "");
                    this.msg += "Mã sản phẩm mới đã tồn tại. Chọn mã khác; ";
                }
            }

            Pattern pattern = Pattern.compile("\\W");
            if (maSanPhamUpdate == null || maSanPhamUpdate.equals("")) {
                addFieldError("maSanPham", "");
                this.msg += "Nhập mã sản phẩm; ";
            } 

            if (tenSanPham == null || tenSanPham.equals("")) {
                addFieldError("tenSanPham", "");
                this.msg += "Nhập tên sản phẩm; ";
            }

            if (moTaSanPham == null || moTaSanPham.equals("")) {
                addFieldError("moTaSanPham", "");
                this.msg += "Nhập mô tả sản phẩm; ";
            }

            if (gia == null || gia.equals("")) {
                addFieldError("gia", "");
                this.msg += "Nhập giá sản phẩm; ";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public CapNhatSanPhamActionSupport() {
    }

    @Override
    public String execute() throws Exception {        
        SanPhamBO sanphamBO = new SanPhamBO();
        SanPhamDTO updatedSPDTO = initSanPhamDTOFromView();

        // Có thay đổi mã
        if (!maSanPhamUpdate.equalsIgnoreCase(maSanPham)) {
            // Xóa dòng cũ, thêm dòng mới
            sanphamBO.deleteSanPham(maSanPham);
            sanphamBO.createSanPham(updatedSPDTO, uploadImage, uploadImageFileName);
            msg = "Cập nhật thành công";
        } else { 
            // Không thay đổi mã --> Cập nhật thông tin SP đang xét
            int result = sanphamBO.updateSanPham(maSanPham, updatedSPDTO, uploadImage, uploadImageFileName);
            if (result == 1) {
                msg = "Cập nhật thành công";
            } else {
                msg = "Không thể cập nhật";
            }
        }

        this.sanphamDTO = updatedSPDTO;
        return SUCCESS;
    }

    private SanPhamDTO initSanPhamDTOFromView() {
        SanPhamDTO spDTO = new SanPhamDTO();
        spDTO.setMaSanPham(maSanPhamUpdate.trim());
        spDTO.setTenSanPham(tenSanPham.trim());
        spDTO.setMoTaSanPham(moTaSanPham.trim());
        spDTO.setGia(gia.trim());
        spDTO.setMaNhaCC(selectedHangSanXuat);
        spDTO.setMaNuocSanXuat(selectedNoiSanXuat);
        spDTO.setMaLoaiSanPham(selectedLoaiSP);
        spDTO.setMaTinhTrang(selectedTinhTrangSP);
        return spDTO;
    }
}
