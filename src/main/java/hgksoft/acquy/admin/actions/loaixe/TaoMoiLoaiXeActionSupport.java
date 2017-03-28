package hgksoft.acquy.admin.actions.loaixe;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import hgksoft.acquy.bo.HangXeBO;
import hgksoft.acquy.bo.LoaiXeBO;
import hgksoft.acquy.dto.HangXeDTO;
import hgksoft.acquy.dto.LoaiXeDTO;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author HNTIN
 */
public class TaoMoiLoaiXeActionSupport extends ActionSupport {

    private HashMap<String, String> dsHangXeHM = new HashMap<>();
    private String maLoaiXe;
    private String tenLoaiXe;
    private String selectedHangXe;
    private String msg;

    public HashMap<String, String> getDsHangXeHM() {
        return dsHangXeHM;
    }

    public String getSelectedHangXe() {
        return selectedHangXe;
    }

    public void setSelectedHangXe(String selectedHangXe) {
        this.selectedHangXe = selectedHangXe;
    }

    public String getMaLoaiXe() {
        return maLoaiXe;
    }

    public void setMaLoaiXe(String maLoaiXe) {
        this.maLoaiXe = maLoaiXe;
    }

    public String getTenLoaiXe() {
        return tenLoaiXe;
    }

    public void setTenLoaiXe(String tenLoaiXe) {
        this.tenLoaiXe = tenLoaiXe;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public TaoMoiLoaiXeActionSupport() {
    }

    @Override
    public void validate() {
        if (maLoaiXe == null || maLoaiXe.equals("")) {
            addFieldError("maLoaiXe", "Nhập mã loại xe");
        }
        
        if (tenLoaiXe == null || tenLoaiXe.equals("")) {
            addFieldError("tenLoaiXe", "Nhập tên loại xe");
        }
        
        try {
            HangXeBO hxBO = new HangXeBO();
            List<HangXeDTO> dsHangXeDTO = hxBO.getDSTatCaHangXe();
            if (dsHangXeDTO != null) {
                for (HangXeDTO hangXeDTO : dsHangXeDTO) {
                    dsHangXeHM.put(hangXeDTO.getMaHangXe(), hangXeDTO.getTenHangXe());
                }
            }
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

        LoaiXeBO loaixeBO = new LoaiXeBO();
        LoaiXeDTO loaixeDTO = loaixeBO.getLoaiXeDTO(maLoaiXe);
        if (loaixeDTO != null) {
            if (loaixeDTO.getMaLoaiXe() != null && !loaixeDTO.getMaLoaiXe().equals("")) {
                msg = "Mã loại xe đã tồn tại";
            } else {
                loaixeDTO = new LoaiXeDTO();
                loaixeDTO.setMaLoaiXe(maLoaiXe);
                loaixeDTO.setTenLoaiXe(tenLoaiXe);
                loaixeDTO.setMaHangXe(selectedHangXe);

                int result = loaixeBO.createLoaiXe(loaixeDTO);
                if (result == 1) {
                    msg = "Thêm mới loại xe thành công";
                }
            }
        }

        return SUCCESS;
    }

}
