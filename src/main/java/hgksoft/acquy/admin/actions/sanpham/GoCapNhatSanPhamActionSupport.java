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
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author HNTIN
 */
public class GoCapNhatSanPhamActionSupport extends ActionSupport {

    private SanPhamDTO sanphamDTO;
    private String maSanPham;
    private String msg;

    private HashMap<String, String> noiSanXuatHM = new HashMap<>();
    private HashMap<String, String> hangSanXuatHM = new HashMap<>();
    private HashMap<Integer, String> loaiSanPhamHM = new HashMap<>();
    private HashMap<String, String> tinhTrangHM = new HashMap<>();

    public SanPhamDTO getSanphamDTO() {
        return sanphamDTO;
    }

    public void setSanphamDTO(SanPhamDTO sanphamDTO) {
        this.sanphamDTO = sanphamDTO;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
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

    public GoCapNhatSanPhamActionSupport() {
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
        
        SanPhamBO sanphamBO = new SanPhamBO();
        this.sanphamDTO = sanphamBO.getSanPhamDTO(maSanPham.trim());
        return SUCCESS;
    }

}
