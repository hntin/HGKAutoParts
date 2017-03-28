package hgksoft.acquy.admin.actions.loaixe;

import com.opensymphony.xwork2.ActionSupport;
import hgksoft.acquy.bo.HangXeBO;
import hgksoft.acquy.bo.LoaiXeBO;
import hgksoft.acquy.dto.HangXeDTO;
import hgksoft.acquy.dto.LoaiXeDTO;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author HNTIN
 */
public class CapNhatLoaiXeActionSupport extends ActionSupport {
    private String selectedHangXe;
    private String maLoaiXe;
    private String tenLoaiXe;
    private LoaiXeDTO loaixeDTO;
    private HashMap<String, String> dsHangXeHM = new HashMap<String, String>();
    private String msg;

    public LoaiXeDTO getLoaixeDTO() {
        return loaixeDTO;
    }

    public void setLoaixeDTO(LoaiXeDTO loaixeDTO) {
        this.loaixeDTO = loaixeDTO;
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

    public HashMap<String, String> getDsHangXeHM() {
        return dsHangXeHM;
    }

    public void setDsHangXeHM(HashMap<String, String> dsHangXeHM) {
        this.dsHangXeHM = dsHangXeHM;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
        
    public CapNhatLoaiXeActionSupport() {
    }

    @Override
    public void validate() {
        try {
            HangXeBO hxBO = new HangXeBO();
            List<HangXeDTO> dsHangXeDTO = hxBO.getDSTatCaHangXe();
            if (dsHangXeDTO != null && dsHangXeDTO.size() > 0) {
                for (int i = 0; i < dsHangXeDTO.size(); i++) {
                    this.dsHangXeHM.put(dsHangXeDTO.get(i).getMaHangXe(), dsHangXeDTO.get(i).getTenHangXe());
                }
            }

            if (maLoaiXe == null || maLoaiXe.equals("")) {
                addFieldError("maLoaiXe", "Nhập mã dòng xe");
            } else {
//                LoaiXeBO loaixeBO = new LoaiXeBO();
//                LoaiXeDTO tempDTO = loaixeBO.getLoaiXeDTO(maLoaiXe);
//                if (tempDTO != null && !tempDTO.getMaLoaiXe().equals("")) {
//                    addFieldError("maLoaiXe", "Mã loại xe đã tồn tại");
//                }
            }

            if (tenLoaiXe == null || tenLoaiXe.equals("")) {
                addFieldError("tenLoaiXe", "Nhập tên loại xe");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String execute() throws Exception {

        LoaiXeBO loaixeBO = new LoaiXeBO();
        LoaiXeDTO lxDTO = new LoaiXeDTO();
        lxDTO.setMaLoaiXe(maLoaiXe);
        lxDTO.setTenLoaiXe(tenLoaiXe);
        lxDTO.setMaHangXe(selectedHangXe);
        
        int result = loaixeBO.updateLoaiXe(lxDTO);
        
        if (result == 1) {
            msg = "Cập nhật thành công";
        } else {
            msg = "Không thể cập nhật";
        }
                
        this.loaixeDTO = lxDTO;
        
        return SUCCESS;
    }
}
