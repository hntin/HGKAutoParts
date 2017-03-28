package hgksoft.acquy.dto;

import java.util.List;
import javax.json.Json;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author HNTIN
 */
public class LoaiSanPhamDTO {

    private int maLSP;
    private String tenLSP;
    private int maLoaiSPCha;
    List<LoaiSanPhamDTO> dsLSPCon;

    public int getMaLSP() {
        return maLSP;
    }

    public void setMaLSP(int maLSP) {
        this.maLSP = maLSP;
    }

    public String getTenLSP() {
        return tenLSP;
    }

    public void setTenLSP(String tenLSP) {
        this.tenLSP = tenLSP;
    }

    public int getMaLoaiSPCha() {
        return maLoaiSPCha;
    }

    public void setMaLoaiSPCha(int maLoaiSPCha) {
        this.maLoaiSPCha = maLoaiSPCha;
    }

    public List<LoaiSanPhamDTO> getDsLSPCon() {
        return dsLSPCon;
    }

    public void setDsLSPCon(List<LoaiSanPhamDTO> dsLSPCon) {
        this.dsLSPCon = dsLSPCon;
    }

    public JSONArray retrieveData(LoaiSanPhamDTO node) {
        JSONArray tree = new JSONArray();
        JSONObject root = createNode(node);
        JSONArray arr = new JSONArray();
        List<LoaiSanPhamDTO> dsNodeCon = node.getDsLSPCon();
        if (dsNodeCon != null) {
            for (LoaiSanPhamDTO i : node.getDsLSPCon()) {
                JSONObject child = createNode(i);
                arr.add(child);
                child.put("items", retrieveData(i));
            }
            root.put("items", arr);
        }
        tree.add(root);
        return tree;
    }

    private JSONObject createNode(LoaiSanPhamDTO dto) {
        JSONObject node = new JSONObject();
        node.put("id", new Integer(dto.getMaLSP()));
        node.put("text", dto.tenLSP);
        node.put("open", new Integer(1));
        return node;
    }

    public JSONArray toJsonObject() {
        return retrieveData(this);
    }

}
