package hgksoft.acquy.admin.actions.dongxe;

import com.opensymphony.xwork2.ActionSupport;
import hgksoft.acquy.bo.DongXeBO;
import hgksoft.acquy.bo.HangXeBO;
import hgksoft.acquy.bo.LoaiXeBO;
import hgksoft.acquy.dto.DongXeDTO;
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
public class GoCapNhatDongXeActionSupport extends ActionSupport {

    private String maDongXe;
    private DongXeDTO dongxeDTO;
    private List<String> soXyLanhList = new ArrayList();
    private List<String> hopSoList = new ArrayList();
    private List<String> soCuaList = new ArrayList();
    private List<String> nhienLieuList = new ArrayList();
    private HashMap<String, String> dsHangXeHM = new HashMap<String, String>();
    private HashMap<String, String> dsLoaiXeHM = new HashMap<String, String>();
    private String selectedHangXe;
    private String selectdLoaiXe;

    public String getSelectdLoaiXe() {
        return selectdLoaiXe;
    }

    public void setSelectdLoaiXe(String selectdLoaiXe) {
        this.selectdLoaiXe = selectdLoaiXe;
    }

    public String getSelectedHangXe() {
        return selectedHangXe;
    }

    public void setSelectedHangXe(String selectedHangXe) {
        this.selectedHangXe = selectedHangXe;
    }

    public String getMaDongXe() {
        return maDongXe;
    }

    public void setMaDongXe(String maDongXe) {
        this.maDongXe = maDongXe;
    }

    public DongXeDTO getDongxeDTO() {
        return dongxeDTO;
    }

    public void setDongxeDTO(DongXeDTO dongxeDTO) {
        this.dongxeDTO = dongxeDTO;
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

    public GoCapNhatDongXeActionSupport() {
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
        //</editor-fold>

        DongXeBO dxBO = new DongXeBO();
        DongXeDTO dxDTO = dxBO.getDongXeDTO(maDongXe);

        HangXeBO hxBO = new HangXeBO();
        List<HangXeDTO> dsHangXeDTO = hxBO.getDSTatCaHangXe();
        if (dsHangXeDTO != null && dsHangXeDTO.size() > 0) {
            for (int i = 0; i < dsHangXeDTO.size(); i++) {
                this.dsHangXeHM.put(dsHangXeDTO.get(i).getMaHangXe(), dsHangXeDTO.get(i).getTenHangXe());
            }
        }

        LoaiXeBO lxBO = new LoaiXeBO();
        if (selectedHangXe != null) { // Chọn thay đổi hãng xe cho dòng xe tương ứng
            List<LoaiXeDTO> dsLoaiXeDTO = lxBO.getDSLoaiXe(selectedHangXe);
            if (dsLoaiXeDTO != null && dsLoaiXeDTO.size() > 0) {
                for (int i = 0; i < dsLoaiXeDTO.size(); i++) {
                    this.dsLoaiXeHM.put(dsLoaiXeDTO.get(i).getMaLoaiXe(), dsLoaiXeDTO.get(i).getTenLoaiXe());
                }
            }
            dxDTO.setMaHangXe(selectedHangXe);
            
        } else { // Hiển thị hãng xe hiện tại của dòng xe tương ứng
            List<LoaiXeDTO> dsLoaiXeDTO = lxBO.getDSLoaiXe(dxDTO.getMaHangXe());
            if (dsLoaiXeDTO != null && dsLoaiXeDTO.size() > 0) {
                for (int i = 0; i < dsLoaiXeDTO.size(); i++) {
                    this.dsLoaiXeHM.put(dsLoaiXeDTO.get(i).getMaLoaiXe(), dsLoaiXeDTO.get(i).getTenLoaiXe());
                }
            }
            this.selectdLoaiXe = dxDTO.getMaLoaiXe();
        }

        this.dongxeDTO = dxDTO;
        return SUCCESS;
    }

}
