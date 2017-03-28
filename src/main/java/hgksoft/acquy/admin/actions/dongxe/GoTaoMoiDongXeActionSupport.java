package hgksoft.acquy.admin.actions.dongxe;

import com.opensymphony.xwork2.ActionSupport;
import hgksoft.acquy.bo.HangXeBO;
import hgksoft.acquy.bo.LoaiXeBO;
import hgksoft.acquy.dto.HangXeDTO;
import hgksoft.acquy.dto.LoaiXeDTO;
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
public class GoTaoMoiDongXeActionSupport extends ActionSupport {

    private String maDongXe;
    private String tenDongXe;
    private List<String> soXyLanhList = new ArrayList();
    private List<String> hopSoList = new ArrayList();
    private List<String> soCuaList = new ArrayList();
    private List<String> nhienLieuList = new ArrayList();
    private String dongCo;
    private String namSX;
    private HashMap<String, String> dsHangXeHM = new HashMap<>();
    private HashMap<String, String> dsLoaiXeHM = new HashMap<>();
    private String selectedHangXe;

    public String getMaDongXe() {
        return maDongXe;
    }

    public void setMaDongXe(String maDongXe) {
        this.maDongXe = maDongXe;
    }

    public String getTenDongXe() {
        return tenDongXe;
    }

    public void setTenDongXe(String tenDongXe) {
        this.tenDongXe = tenDongXe;
    }

    public List<String> getSoXyLanhList() {
        return soXyLanhList;
    }

    public void setSoXyLanhList(List<String> soXyLanhList) {
        this.soXyLanhList = soXyLanhList;
    }

    public List<String> getHopSoList() {
        return hopSoList;
    }

    public void setHopSoList(List<String> hopSoList) {
        this.hopSoList = hopSoList;
    }

    public List<String> getSoCuaList() {
        return soCuaList;
    }

    public void setSoCuaList(List<String> soCuaList) {
        this.soCuaList = soCuaList;
    }

    public List<String> getNhienLieuList() {
        return nhienLieuList;
    }

    public void setNhienLieuList(List<String> nhienLieuList) {
        this.nhienLieuList = nhienLieuList;
    }

    public String getDongCo() {
        return dongCo;
    }

    public void setDongCo(String dongCo) {
        this.dongCo = dongCo;
    }

    public String getNamSX() {
        return namSX;
    }

    public void setNamSX(String namSX) {
        this.namSX = namSX;
    }

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

    public String getSelectedHangXe() {
        return selectedHangXe;
    }

    public void setSelectedHangXe(String selectedHangXe) {
        this.selectedHangXe = selectedHangXe;
    }
    
    public GoTaoMoiDongXeActionSupport() {
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

        //<editor-fold defaultstate="collapsed" desc="Khởi tạo các combo box">
        soXyLanhList.add("3");
        soXyLanhList.add("4");
        soXyLanhList.add("6");
        soXyLanhList.add("8");

        hopSoList.add("Tự động");
        hopSoList.add("Sàn");

        soCuaList.add("2");
        soCuaList.add("4");
        soCuaList.add("5");

        nhienLieuList.add("Xăng");
        nhienLieuList.add("Dầu Diesel");

        HangXeBO hxBO = new HangXeBO();
        List<HangXeDTO> dsHangXeDTO = hxBO.getDSTatCaHangXe();
        if (dsHangXeDTO != null && dsHangXeDTO.size() > 0) {
            this.dsHangXeHM.put("0", "Chọn hãng xe");
            for (int i = 0; i < dsHangXeDTO.size(); i++) {
                this.dsHangXeHM.put(dsHangXeDTO.get(i).getMaHangXe(), dsHangXeDTO.get(i).getTenHangXe());
            }
        }

        if (selectedHangXe != null && !selectedHangXe.equals("")) {
            LoaiXeBO lxBO = new LoaiXeBO();
            List<LoaiXeDTO> dsLoaiXeDTO = lxBO.getDSLoaiXe(selectedHangXe);
            if (dsLoaiXeDTO != null && dsLoaiXeDTO.size() > 0) {
                this.dsLoaiXeHM.put("0", "Chọn loại xe");
                for (int i = 0; i < dsLoaiXeDTO.size(); i++) {
                    this.dsLoaiXeHM.put(dsLoaiXeDTO.get(i).getMaLoaiXe(), dsLoaiXeDTO.get(i).getTenLoaiXe());
                }
            }
        }

        //</editor-fold>
        return SUCCESS;
    }

}
