package hgksoft.acquy.user.actions;

import com.opensymphony.xwork2.ActionSupport;
import hgksoft.acquy.bo.DongXeBO;
import hgksoft.acquy.bo.HangXeBO;
import hgksoft.acquy.bo.LoaiXeBO;
import hgksoft.acquy.bo.SanPhamBO;
import hgksoft.acquy.bo.SanPhamDongXeBO;
import hgksoft.acquy.dto.DongXeDTO;
import hgksoft.acquy.dto.HangXeDTO;
import hgksoft.acquy.dto.LoaiXeDTO;
import hgksoft.acquy.dto.SanPhamDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author HNTIN
 */
public class XemChiTietSanPhamActionSupport extends ActionSupport {
    //<editor-fold defaultstate="collapsed" desc="Menu bên trái">
    private HashMap<String, String> dsHangXeHM = new HashMap<>();
    private HashMap<String, String> dsLoaiXeHM = new HashMap<>();
    private List<DongXeDTO> dsDongXeDTO = new ArrayList<>();
    private String selectedHangXe;
    private String selectedLoaiXe;
    //</editor-fold>
    
    private String maSanPham;
    private SanPhamDTO sanphamDTO;
    private List<DongXeDTO> dsDongXeTuongThichDTO;

    //<editor-fold defaultstate="collapsed" desc="Getter & Setter">
    public HashMap<String, String> getDsHangXeHM() {
        return dsHangXeHM;
    }
    
    public void setDsHangXeHM(HashMap<String, String> dsHangXeHM) {
        this.dsHangXeHM = dsHangXeHM;
    }
    
    public HashMap<String, String> getDsLoaiXeHM() {
        return dsLoaiXeHM;
    }
    
    public void setDsLoaiXeHM(HashMap<String, String> dsLoaiXeHM) {
        this.dsLoaiXeHM = dsLoaiXeHM;
    }
    
    public List<DongXeDTO> getDsDongXeDTO() {
        return dsDongXeDTO;
    }
    
    public void setDsDongXeDTO(List<DongXeDTO> dsDongXeDTO) {
        this.dsDongXeDTO = dsDongXeDTO;
    }
    
    public String getSelectedHangXe() {
        return selectedHangXe;
    }
    
    public void setSelectedHangXe(String selectedHangXe) {
        this.selectedHangXe = selectedHangXe;
    }
    
    public String getSelectedLoaiXe() {
        return selectedLoaiXe;
    }
    
    public void setSelectedLoaiXe(String selectedLoaiXe) {
        this.selectedLoaiXe = selectedLoaiXe;
    }
    
    public String getMaSanPham() {
        return maSanPham;
    }
    
    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }
    
    public SanPhamDTO getSanphamDTO() {
        return sanphamDTO;
    }
    
    public void setSanphamDTO(SanPhamDTO sanphamDTO) {
        this.sanphamDTO = sanphamDTO;
    }
    
    public List<DongXeDTO> getDsDongXeTuongThichDTO() {
        return dsDongXeTuongThichDTO;
    }
    
    public void setDsDongXeTuongThichDTO(List<DongXeDTO> dsDongXeTuongThichDTO) {
        this.dsDongXeTuongThichDTO = dsDongXeTuongThichDTO;
    }
//</editor-fold>

    public XemChiTietSanPhamActionSupport() {
    }
    
    public String execute() throws Exception {
        //<editor-fold defaultstate="collapsed" desc=" Khởi tạo menu bên trái">
        HangXeBO hxBO = new HangXeBO();
        List<HangXeDTO> dsHangXeDTO = hxBO.getDSTatCaHangXe();
        if (dsHangXeDTO != null && dsHangXeDTO.size() > 0) {
            dsHangXeHM.put("0", "Chọn hãng xe");
            for (int i = 0; i < dsHangXeDTO.size(); i++) {
                HangXeDTO hxDTO = dsHangXeDTO.get(i);
                dsHangXeHM.put(hxDTO.getMaHangXe(), hxDTO.getTenHangXe());
            }
        }

        if (selectedHangXe == null) {
            selectedHangXe = "0"; // Default value
        }
        if (selectedHangXe != null && !selectedHangXe.equalsIgnoreCase("0")) {
            LoaiXeBO lxBO = new LoaiXeBO();
            List<LoaiXeDTO> dsLoaiXeDTO = lxBO.getDSLoaiXe(selectedHangXe);
            if (dsLoaiXeDTO != null && dsLoaiXeDTO.size() > 0) {
                dsLoaiXeHM.put("0", "Chọn loại xe");
                for (int i = 0; i < dsLoaiXeDTO.size(); i++) {
                    LoaiXeDTO lxDTO = dsLoaiXeDTO.get(i);
                    dsLoaiXeHM.put(lxDTO.getMaLoaiXe(), lxDTO.getTenLoaiXe());
                }
            }
        }

        if (selectedLoaiXe != null && !selectedLoaiXe.equalsIgnoreCase("0")) {
            DongXeBO dxBO = new DongXeBO();
            this.dsDongXeDTO = dxBO.getDSDongXeTheoLX(selectedLoaiXe, selectedHangXe);
        }
        //</editor-fold>
        
        SanPhamBO spBO = new SanPhamBO();
        this.sanphamDTO = spBO.getSanPhamDTO(maSanPham);
        SanPhamDongXeBO spdxBO = new SanPhamDongXeBO();
        this.dsDongXeTuongThichDTO = spdxBO.getDSDongXeTuongThichSanPham(maSanPham);
        
        return SUCCESS;
    }
    
}
