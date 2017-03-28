package hgksoft.acquy.bo;

import hgksoft.acquy.dbaccess.LoaiSanPhamMapper;
import hgksoft.acquy.dto.LoaiSanPhamDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author HNTIN
 */
public class LoaiSanPhamBO {

    public JSONArray getJsonTree() throws Exception {
        LoaiSanPhamMapper mapper = null;
        LoaiSanPhamDTO rootLSPDTO = null;
        try {
            mapper = new LoaiSanPhamMapper();
            List<LoaiSanPhamDTO> lookupTable = mapper.getDSTatCaLoaiSanPham();
            // Lấy node gốc và ắt đầu tạo cây
            rootLSPDTO = mapper.getRootLSP();
            createTree(rootLSPDTO, lookupTable);
        } catch (Exception ex) {
            Logger.getLogger(LoaiSanPhamBO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (mapper != null) {
                mapper.closeConnection();
            }
        }
        return rootLSPDTO.toJsonObject();
    }

    public String getTreeInJavaScript() throws Exception {
        LoaiSanPhamMapper mapper = null;
        String javaScriptCode = "";
        try {
            mapper = new LoaiSanPhamMapper();
            List<LoaiSanPhamDTO> lookupTable = mapper.getDSTatCaLoaiSanPham();
            List<LoaiSanPhamDTO> loaiSanPhamLevel2 = mapper.getDSLoaiSanPhamMucDuoi(1);
            for (LoaiSanPhamDTO lsp2 : loaiSanPhamLevel2) {
                List<LoaiSanPhamDTO> loaiSanPhamLevel3
                        = mapper.getDSLoaiSanPhamMucDuoi(lsp2.getMaLSP());

                JSONArray data = new JSONArray();

                for (LoaiSanPhamDTO lspl3 : loaiSanPhamLevel3) {
                    createTree(lspl3, lookupTable);
                    data.addAll(lspl3.retrieveData(lspl3));
                }

                javaScriptCode += "var myTreeView"
                        + lsp2.getMaLSP()
                        + " = khungAccodion.cells(\""
                        + +lsp2.getMaLSP() + "\").attachTreeView({items: " + data.toJSONString() + "}); \n"
                        + "myTreeView" + lsp2.getMaLSP() + ".attachEvent(\"onSelect\", onSelectItemTreeView);\n";
            }
        } catch (Exception ex) {
            Logger.getLogger(LoaiSanPhamBO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (mapper != null) {
                mapper.closeConnection();
            }
        }
        return javaScriptCode;
    }

    public JSONArray getJsonAccordion() throws Exception {
        LoaiSanPhamMapper mapper = null;
        JSONArray accrdion = new JSONArray();
        try {
            mapper = new LoaiSanPhamMapper();
            List<LoaiSanPhamDTO> loaiSanPhamLevel2 = mapper.getDSLoaiSanPhamMucDuoi(1);
            accrdion = new JSONArray();
            for (LoaiSanPhamDTO lsp : loaiSanPhamLevel2) {
                JSONObject obj = new JSONObject();
                obj.put("id", lsp.getMaLSP());
                obj.put("text", lsp.getTenLSP());
                accrdion.add(obj);
            }
        } catch (Exception ex) {
            Logger.getLogger(LoaiSanPhamBO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (mapper != null) {
                mapper.closeConnection();
            }
        }
        return accrdion;
    }

    private LoaiSanPhamDTO createTree(LoaiSanPhamDTO root, List<LoaiSanPhamDTO> lookupTable) {
        List<LoaiSanPhamDTO> dsNodeCon = getDsNodeCon(root, lookupTable);
        if (dsNodeCon != null && dsNodeCon.size() > 0) {
            root.setDsLSPCon(dsNodeCon);
            for (LoaiSanPhamDTO i : dsNodeCon) {
                createTree(i, lookupTable);
            }
        }
        return root;
    }

    private List<LoaiSanPhamDTO> getDsNodeCon(LoaiSanPhamDTO node, List<LoaiSanPhamDTO> lookupTable) {
        List<LoaiSanPhamDTO> dsNodeCon = new ArrayList<LoaiSanPhamDTO>();
        for (LoaiSanPhamDTO i : lookupTable) {
            if (i.getMaLoaiSPCha() == node.getMaLSP()) {
                dsNodeCon.add(i);
            }
        }
        return dsNodeCon;
    }

    public int createLoaiSanPham(LoaiSanPhamDTO loaiSPDTO) throws Exception {
        LoaiSanPhamMapper mapper = null;
        int result = 0;
        try {
            mapper = new LoaiSanPhamMapper();
            result = mapper.createLoaiSanPham(loaiSPDTO);
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return result;
    }

    public int updateLoaiSanPham(LoaiSanPhamDTO loaiSPDTO) throws Exception {
        LoaiSanPhamMapper mapper = null;
        int result = 0;
        try {
            mapper = new LoaiSanPhamMapper();
            result = mapper.updateLoaiSanPham(loaiSPDTO);
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return result;
    }

    public int deleteLoaiSanPham(String dsMaLoaiSanPham) throws Exception {
        LoaiSanPhamMapper mapper = null;
        int result = 0;
        try {
            mapper = new LoaiSanPhamMapper();
            if (dsMaLoaiSanPham != null && !dsMaLoaiSanPham.equals("")) {
                String[] arrStr = dsMaLoaiSanPham.split(",");
                for (int i = 0; i < arrStr.length; i++) {
                    if (mapper.deleteLoaiSanPham(arrStr[i]) != 0) {
                        result += 1;
                    }
                }
            }
        } catch (Exception e) {
            return -1;
        } finally {
            mapper.closeConnection();
        }
        return result;
    }

    public LoaiSanPhamDTO getLoaiSanPhamDTO(int maLoaiSanPham) throws Exception {
        LoaiSanPhamMapper mapper = null;
        LoaiSanPhamDTO lspDTO;
        try {
            mapper = new LoaiSanPhamMapper();
            lspDTO = mapper.getLoaiSanPhamDTO(maLoaiSanPham);
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return lspDTO;
    }

    public List<LoaiSanPhamDTO> getDSTatCaLoaiSanPham() throws Exception {
        List<LoaiSanPhamDTO> dsLoaiSanPhamDTO = null;
        LoaiSanPhamMapper mapper = null;
        try {
            mapper = new LoaiSanPhamMapper();
            dsLoaiSanPhamDTO = mapper.getDSTatCaLoaiSanPham();
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return dsLoaiSanPhamDTO;
    }

    public List<LoaiSanPhamDTO> getDSLoaiSanPham(String mucLoaiSanPham) throws Exception {
        List<LoaiSanPhamDTO> dsLoaiSanPhamDTO = null;
        LoaiSanPhamMapper mapper = null;
        try {
            mapper = new LoaiSanPhamMapper();
            dsLoaiSanPhamDTO = mapper.getDSLoaiSanPham(mucLoaiSanPham);
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return dsLoaiSanPhamDTO;
    }

    public List<LoaiSanPhamDTO> getDSLoaiSanPhamMucDuoi(int maLoaiCha) throws Exception {
        List<LoaiSanPhamDTO> dsLoaiSanPhamDTO = null;
        LoaiSanPhamMapper mapper = null;
        try {
            mapper = new LoaiSanPhamMapper();
            dsLoaiSanPhamDTO = mapper.getDSLoaiSanPhamMucDuoi(maLoaiCha);
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return dsLoaiSanPhamDTO;
    }
}
