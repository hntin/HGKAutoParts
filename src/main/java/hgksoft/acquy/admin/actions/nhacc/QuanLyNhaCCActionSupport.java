package hgksoft.acquy.admin.actions.nhacc;

import com.opensymphony.xwork2.ActionSupport;
import hgksoft.acquy.bo.NhaCungCapBO;
import hgksoft.acquy.constant.CommonConst;
import hgksoft.acquy.dto.NhaCCDTO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author HNTIN
 */
public class QuanLyNhaCCActionSupport extends ActionSupport {

    private List<NhaCCDTO> dsNhaCCDTO;
    private String selectedPage;
    private int numberOfPages;
    private String msg;

    public List<NhaCCDTO> getDsNhaCCDTO() {
        return dsNhaCCDTO;
    }

    public void setDsNhaCCDTO(List<NhaCCDTO> dsNhaCCDTO) {
        this.dsNhaCCDTO = dsNhaCCDTO;
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

    public QuanLyNhaCCActionSupport() {
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

        NhaCungCapBO nccBO = new NhaCungCapBO();
        dsNhaCCDTO = nccBO.getDSNhaCC("", "");

        //<editor-fold defaultstate="collapsed" desc="Phân trang">
        if (this.dsNhaCCDTO != null && this.dsNhaCCDTO.size() != 0) {
            if (this.dsNhaCCDTO.size() % CommonConst.MAX_ROW_PER_PAGE == 0) {
                this.numberOfPages = dsNhaCCDTO.size() / CommonConst.MAX_ROW_PER_PAGE;
            } else {
                this.numberOfPages = dsNhaCCDTO.size() / CommonConst.MAX_ROW_PER_PAGE + 1;
            }

            if (selectedPage != null) {
                int beginIdx = (Integer.parseInt(selectedPage) - 1) * CommonConst.MAX_ROW_PER_PAGE;
                int endIdx = beginIdx + CommonConst.MAX_ROW_PER_PAGE;
                if (endIdx > dsNhaCCDTO.size()) {
                    endIdx = dsNhaCCDTO.size();
                }
                dsNhaCCDTO = dsNhaCCDTO.subList(beginIdx, endIdx);
            }
            //</editor-fold>
        }
        return SUCCESS;
    }
}
