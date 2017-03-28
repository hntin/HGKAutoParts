package hgksoft.acquy.admin.actions.apdung;

import com.opensymphony.xwork2.ActionSupport;
import hgksoft.acquy.bo.DongXeBO;
import hgksoft.acquy.bo.HangXeBO;
import hgksoft.acquy.bo.SanPhamBO;
import hgksoft.acquy.bo.SanPhamDongXeBO;
import hgksoft.acquy.constant.CommonConst;
import hgksoft.acquy.dto.DongXeDTO;
import hgksoft.acquy.dto.HangXeDTO;
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
public class TaoMoiApDungActionSupport extends ActionSupport {
    private String selectedMaSanPham;
    private SanPhamDTO sanphamDTO;
    private List<DongXeDTO> dsDongXeDTO = new ArrayList<>();
    private HashMap<String, String> dsHangXeHM = new HashMap<>();
    private String selectedHangXe;
    private String selectedPage;
    private int numberOfPages;
    private String apDungCheckBox;
    private String msg;

    //<editor-fold defaultstate="collapsed" desc="Getter & Setter">
    public String getSelectedMaSanPham() {
        return selectedMaSanPham;
    }
    
    public void setSelectedMaSanPham(String selectedMaSanPham) {
        this.selectedMaSanPham = selectedMaSanPham;
    }
    
    public SanPhamDTO getSanphamDTO() {
        return sanphamDTO;
    }
    
    public void setSanphamDTO(SanPhamDTO sanphamDTO) {
        this.sanphamDTO = sanphamDTO;
    }
    
    public List<DongXeDTO> getDsDongXeDTO() {
        return dsDongXeDTO;
    }
    
    public void setDsDongXeDTO(List<DongXeDTO> dsDongXeDTO) {
        this.dsDongXeDTO = dsDongXeDTO;
    }
    
    public HashMap<String, String> getDsHangXeHM() {
        return dsHangXeHM;
    }
    
    public void setDsHangXeHM(HashMap<String, String> dsHangXeHM) {
        this.dsHangXeHM = dsHangXeHM;
    }
    
    public String getSelectedHangXe() {
        return selectedHangXe;
    }
    
    public void setSelectedHangXe(String selectedHangXe) {
        this.selectedHangXe = selectedHangXe;
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
    
    public String getApDungCheckBox() {
        return apDungCheckBox;
    }
    
    public void setApDungCheckBox(String apDungCheckBox) {
        this.apDungCheckBox = apDungCheckBox;
    }
    
    public String getMsg() {
        return msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
//</editor-fold>
    
    public TaoMoiApDungActionSupport() {
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
        
        msg = "Số áp dụng mới đã thêm thành công: ";
        SanPhamDongXeBO spdxBO = new SanPhamDongXeBO();
        int result = spdxBO.createApDungMoi(selectedMaSanPham, apDungCheckBox);
        msg += result;
        
        SanPhamBO spBO = new SanPhamBO();
        this.sanphamDTO = spBO.getSanPhamDTO(selectedMaSanPham);
        
        HangXeBO hxBO = new HangXeBO();
        DongXeBO dxBO = new DongXeBO();
        if (selectedHangXe == null) 
            selectedHangXe = "1"; // default value
            
        this.dsDongXeDTO = spdxBO.getDSDongXeTuongThichSanPham(selectedMaSanPham);
        
        List<HangXeDTO> dsHangXeDTO = hxBO.getDSTatCaHangXe();
        if (dsHangXeDTO != null && dsHangXeDTO.size()>0) {
            for (int i=0; i< dsHangXeDTO.size(); i++) {
                HangXeDTO hxDTO = dsHangXeDTO.get(i);
                dsHangXeHM.put(hxDTO.getMaHangXe(), hxDTO.getTenHangXe());
            }
        }
        
        //<editor-fold defaultstate="collapsed" desc="Phân trang">
        if (this.dsDongXeDTO != null && this.dsDongXeDTO.size() > 0) {
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
        return SUCCESS;
    }
    
}
