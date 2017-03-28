package hgksoft.acquy.user.actions;

import com.opensymphony.xwork2.ActionSupport;
import hgksoft.acquy.bo.DongXeBO;
import hgksoft.acquy.dto.DongXeDTO;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author HNTIN
 */
public class LietKeDongXeActionSupport extends ActionSupport {

    private String selectedLoaiXe;

    public String getSelectedLoaiXe() {
        return selectedLoaiXe;
    }

    public void setSelectedLoaiXe(String selectedLoaiXe) {
        this.selectedLoaiXe = selectedLoaiXe;
    }

    public LietKeDongXeActionSupport() {
    }

    public String execute() throws Exception {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("html/text;charset=utf-8");
        response.setHeader("cache-control", "no-cache");
        PrintWriter out = response.getWriter();
        out.println("<table class='table1'>");
        out.println("<tr>");
        out.println("<th>Hãng xe</th>");
        out.println("<th>Loại xe</th>");
        out.println("<th>Dòng xe</th>");
        out.println("<th>Xy-Lanh</th>");
        out.println("<th>Động cơ</th>");
        out.println("<th>Hộp số</th>");
        out.println("<th>Số cửa</th>");
        out.println("<th>Nhiên liệu</th>");
        out.println("<th>Năm</th>");
        out.println("<th></th>");
        out.println("</tr>");

        DongXeBO dxBO = new DongXeBO();
        if (selectedLoaiXe != null && !selectedLoaiXe.equals("")) {
            List<DongXeDTO> dsDongXeDTO = dxBO.getDSDongXeTheoLX(selectedLoaiXe);
            for (int i = 0; i < dsDongXeDTO.size(); i++) {
                DongXeDTO dxDTO = dsDongXeDTO.get(i);
                out.println("<tr>");
                out.println("<td>" + dxDTO.getTenHangXe() + "</td>");
                out.println("<td>" + dxDTO.getTenLoaiXe() + "</td>");
                out.println("<td>" + dxDTO.getTenDongXe() + "</td>");
                out.println("<td>" + dxDTO.getSoXyLanh() + "</td>");
                out.println("<td>" + dxDTO.getDongCo() + "</td>");
                out.println("<td>" + dxDTO.getHopSo() + "</td>");
                out.println("<td>" + dxDTO.getSoCua() + "</td>");
                out.println("<td>" + dxDTO.getNhienLieu() + "</td>");
                out.println("<td>" + dxDTO.getNamSX() + "</td>");
                out.println("<td><input type='button' value='Chọn' onclick=\"OnSelectDongXe(" + dxDTO.getMaDongXe() + ")\"></td>");
                out.println("</tr>");
            }
        }

        out.println("</table>");
        out.flush();
        return "success";
    }

}
