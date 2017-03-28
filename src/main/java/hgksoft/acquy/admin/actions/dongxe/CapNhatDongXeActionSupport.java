package hgksoft.acquy.admin.actions.dongxe;

import com.opensymphony.xwork2.ActionSupport;
import hgksoft.acquy.bo.DongXeBO;
import hgksoft.acquy.bo.HangXeBO;
import hgksoft.acquy.bo.LoaiXeBO;
import hgksoft.acquy.constant.CommonConst;
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
public class CapNhatDongXeActionSupport extends ActionSupport {

    private String maDongXe;
    private String tenDongXe;
    private int soXyLanh;
    private String dongCo;
    private String hopSo;
    private int soCua;
    private String nhienLieu;
    private String namSX;
    private String selectedHangXe;
    private String selectedLoaiXe;
    private HashMap<String, String> dsHangXeHM = new HashMap<String, String>();
    private HashMap<String, String> dsLoaiXeHM = new HashMap<String, String>();
    private List<String> soXyLanhList = new ArrayList();
    private List<String> hopSoList = new ArrayList();
    private List<String> soCuaList = new ArrayList();
    private List<String> nhienLieuList = new ArrayList();
    private List<String> namSXList = new ArrayList();
    private DongXeDTO dongxeDTO;
    private String msg;

    public HashMap<String, String> getDsLoaiXeHM() {
        return dsLoaiXeHM;
    }

    public void setDsLoaiXeHM(HashMap<String, String> dsLoaiXeHM) {
        this.dsLoaiXeHM = dsLoaiXeHM;
    }

    public String getSelectedLoaiXe() {
        return selectedLoaiXe;
    }

    public void setSelectedLoaiXe(String selectedLoaiXe) {
        this.selectedLoaiXe = selectedLoaiXe;
    }

    public String getNhienLieu() {
        return nhienLieu;
    }

    public void setNhienLieu(String nhienLieu) {
        this.nhienLieu = nhienLieu;
    }

    public String getNamSX() {
        return namSX;
    }

    public void setNamSX(String namSX) {
        this.namSX = namSX;
    }

    public List<String> getNhienLieuList() {
        return nhienLieuList;
    }

    public void setNhienLieuList(List<String> nhienLieuList) {
        this.nhienLieuList = nhienLieuList;
    }

    public List<String> getNamSXList() {
        return namSXList;
    }

    public void setNamSXList(List<String> namSXList) {
        this.namSXList = namSXList;
    }

    public String getSelectedHangXe() {
        return selectedHangXe;
    }

    public void setSelectedHangXe(String selectedHangXe) {
        this.selectedHangXe = selectedHangXe;
    }

    public HashMap<String, String> getDsHangXeHM() {
        return dsHangXeHM;
    }

    public void setDsHangXeHM(HashMap<String, String> dsHangXeHM) {
        this.dsHangXeHM = dsHangXeHM;
    }

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

    public int getSoXyLanh() {
        return soXyLanh;
    }

    public void setSoXyLanh(int soXyLanh) {
        this.soXyLanh = soXyLanh;
    }

    public String getDongCo() {
        return dongCo;
    }

    public void setDongCo(String dongCo) {
        this.dongCo = dongCo;
    }

    public String getHopSo() {
        return hopSo;
    }

    public void setHopSo(String hopSo) {
        this.hopSo = hopSo;
    }

    public int getSoCua() {
        return soCua;
    }

    public void setSoCua(int soCua) {
        this.soCua = soCua;
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

    public DongXeDTO getDongxeDTO() {
        return dongxeDTO;
    }

    public void setDongxeDTO(DongXeDTO dongxeDTO) {
        this.dongxeDTO = dongxeDTO;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public CapNhatDongXeActionSupport() {
    }

    @Override
    public void validate() {
        try {
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
            HangXeBO hxBO = new HangXeBO();
            List<HangXeDTO> dsHangXeDTO = hxBO.getDSTatCaHangXe();
            if (dsHangXeDTO != null && dsHangXeDTO.size() > 0) {
                for (int i = 0; i < dsHangXeDTO.size(); i++) {
                    this.dsHangXeHM.put(dsHangXeDTO.get(i).getMaHangXe(), dsHangXeDTO.get(i).getTenHangXe());
                }
            }
            DongXeBO dxBO = new DongXeBO();
            DongXeDTO dxDTO = dxBO.getDongXeDTO(maDongXe);
            this.dongxeDTO = dxDTO;

            if (maDongXe == null || maDongXe.equals("")) {
                addFieldError("maDongXe", "Nhập mã dòng xe");
                this.dongxeDTO.setMaDongXe("");
            }
            if (tenDongXe == null || tenDongXe.equals("")) {
                addFieldError("tenDongXe", "Nhập tên dòng xe");
                this.dongxeDTO.setTenDongXe("");
            }
            if (dongCo == null || dongCo.equals("")) {
                addFieldError("dongCo", "Nhập dung tích động cơ");
                this.dongxeDTO.setDongCo("");
            }

            //</editor-fold>
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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

        for (int i = CommonConst.MIN_YEAR_MODEL_CAR; i < CommonConst.MAX_YEAR_MODEL_CAR; i++) {
            namSXList.add(String.valueOf(i));
        }

        HangXeBO hxBO = new HangXeBO();
        List<HangXeDTO> dsHangXeDTO = hxBO.getDSTatCaHangXe();
        if (dsHangXeDTO != null && dsHangXeDTO.size() > 0) {
            for (int i = 0; i < dsHangXeDTO.size(); i++) {
                this.dsHangXeHM.put(dsHangXeDTO.get(i).getMaHangXe(), dsHangXeDTO.get(i).getTenHangXe());
            }
        }

        if (selectedHangXe != null && !selectedHangXe.equals("")) {
            LoaiXeBO lxBO = new LoaiXeBO();
            List<LoaiXeDTO> dsLoaiXeDTO = lxBO.getDSLoaiXe(selectedHangXe);
            if (dsLoaiXeDTO != null && dsLoaiXeDTO.size() > 0) {
                for (int i = 0; i < dsLoaiXeDTO.size(); i++) {
                    this.dsLoaiXeHM.put(dsLoaiXeDTO.get(i).getMaLoaiXe(), dsLoaiXeDTO.get(i).getTenLoaiXe());
                }
            }
        }

        //</editor-fold>
        DongXeDTO dongxeDTO = new DongXeDTO();
        dongxeDTO.setMaDongXe(maDongXe);
        dongxeDTO.setTenDongXe(tenDongXe);
        dongxeDTO.setSoXyLanh(soXyLanh);
        dongxeDTO.setDongCo(dongCo);
        dongxeDTO.setHopSo(hopSo);
        dongxeDTO.setSoCua(soCua);
        dongxeDTO.setNhienLieu(nhienLieu);
        dongxeDTO.setNamSX(namSX);
        dongxeDTO.setMaHangXe(selectedHangXe);
        dongxeDTO.setMaLoaiXe(selectedLoaiXe);

        DongXeBO dongxeBO = new DongXeBO();
        int result = dongxeBO.updateDongXe(dongxeDTO);
        if (result == 1) {
            msg = "Cập nhật thành công";
        } else {
            msg = "Không thể cập nhật";
        }

        this.dongxeDTO = dongxeDTO;
        return SUCCESS;
    }

}
