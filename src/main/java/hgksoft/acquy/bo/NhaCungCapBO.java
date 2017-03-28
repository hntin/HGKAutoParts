package hgksoft.acquy.bo;

import hgksoft.acquy.dbaccess.NhaCungCapMapper;
import hgksoft.acquy.dbaccess.SanPhamMapper;
import hgksoft.acquy.dto.NhaCCDTO;
import java.util.List;

/**
 *
 * @author HNTIN
 */
public class NhaCungCapBO {
    
    public int createNhaCC(NhaCCDTO nccDTO) throws Exception {
        NhaCungCapMapper mapper = null;
        int result = 0;
        try {
            mapper = new NhaCungCapMapper();
            result = mapper.createNhaCC(nccDTO);
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return result;
    }
    
    public int deleteNhaCC(String dsMaNhaCC) throws Exception {
        NhaCungCapMapper mapper = null;
        int result = 0;
        try {
            mapper = new NhaCungCapMapper();
            if (dsMaNhaCC != null && !dsMaNhaCC.equals("")) {
                String[] arrStr = dsMaNhaCC.split(",");
                for (int i = 0; i < arrStr.length; i++) {
                    if (mapper.deleteNhaCC(arrStr[i]) != 0) {
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

    
    public int updateNhaCC(NhaCCDTO nccDTO) throws Exception {
        NhaCungCapMapper mapper = null;
        int result = 0;
        try {
            mapper = new NhaCungCapMapper();
            result = mapper.updateNhaCC(nccDTO);
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return result;
    }
    
    public NhaCCDTO getNhaCCDTO(String maNhaCC) throws Exception {
        NhaCungCapMapper mapper = null;
        NhaCCDTO nccDTO;
        try {
            mapper = new NhaCungCapMapper();
            nccDTO = mapper.getNhaCCDTO(maNhaCC);
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return nccDTO;
    }

    public List<NhaCCDTO> getDSNhaCC(String maNhaCC, String tenNhaCC) throws Exception {
        List<NhaCCDTO> dsNhaCungCapDTO = null;
        NhaCungCapMapper mapper = null;
        try {
            mapper = new NhaCungCapMapper();
            dsNhaCungCapDTO = mapper.getDSNhaCC(maNhaCC, tenNhaCC);
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return dsNhaCungCapDTO;
    }

    public List<NhaCCDTO> getDSTatCaNhaCC() throws Exception {
        List<NhaCCDTO> dsNhaCungCapDTO = null;
        NhaCungCapMapper mapper = null;
        try {
            mapper = new NhaCungCapMapper();
            dsNhaCungCapDTO = mapper.getTatCaNhaCC();
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return dsNhaCungCapDTO;
    }
}
