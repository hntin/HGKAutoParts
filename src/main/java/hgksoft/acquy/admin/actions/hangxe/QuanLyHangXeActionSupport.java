package hgksoft.acquy.admin.actions.hangxe;

import com.opensymphony.xwork2.ActionSupport;
import hgksoft.acquy.bo.HangXeBO;
import hgksoft.acquy.constant.CommonConst;
import hgksoft.acquy.dto.HangXeDTO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author HNTIN
 */
public class QuanLyHangXeActionSupport extends ActionSupport {
    private String maHangXe;
    private String tenHangXe;
    private List<HangXeDTO> dsHangXeDTO;
    private String selectedPage;
    private int numberOfPages;
    private String msg;

    public String getMaHangXe() {
        return maHangXe;
    }

    public void setMaHangXe(String maHangXe) {
        this.maHangXe = maHangXe;
    }

    public String getTenHangXe() {
        return tenHangXe;
    }

    public void setTenHangXe(String tenHangXe) {
        this.tenHangXe = tenHangXe;
    }

    public List<HangXeDTO> getDsHangXeDTO() {
        return dsHangXeDTO;
    }

    public void setDsHangXeDTO(List<HangXeDTO> dsHangXeDTO) {
        this.dsHangXeDTO = dsHangXeDTO;
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public QuanLyHangXeActionSupport() {
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
        
        HangXeBO hangxeBO =  new HangXeBO();                
        if (maHangXe != null && tenHangXe != null) {
            maHangXe.trim();
            tenHangXe.trim();
            dsHangXeDTO = hangxeBO.getDSHangXe(maHangXe, tenHangXe);
        }

        //<editor-fold defaultstate="collapsed" desc="Phân trang">
        if (this.dsHangXeDTO != null && this.dsHangXeDTO.size() != 0) {
            if (this.dsHangXeDTO.size() % CommonConst.MAX_ROW_PER_PAGE == 0) {
                this.numberOfPages = dsHangXeDTO.size() / CommonConst.MAX_ROW_PER_PAGE;
            } else {
                this.numberOfPages = dsHangXeDTO.size() / CommonConst.MAX_ROW_PER_PAGE + 1;
            }

            int beginIdx = (Integer.parseInt(selectedPage) - 1) * CommonConst.MAX_ROW_PER_PAGE;
            int endIdx = beginIdx + CommonConst.MAX_ROW_PER_PAGE;
            if (endIdx > dsHangXeDTO.size()) {
                endIdx = dsHangXeDTO.size();
            }
            dsHangXeDTO = dsHangXeDTO.subList(beginIdx, endIdx);
            //</editor-fold>
        }
        return SUCCESS;
    }
    
}
