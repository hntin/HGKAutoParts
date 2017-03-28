package hgksoft.acquy.bo;

import hgksoft.acquy.dbaccess.TinhTrangMapper;
import hgksoft.acquy.dto.TinhTrangDTO;
import java.util.List;

/**
 *
 * @author HNTIN
 */
public class TinhTrangBO {
    public int createTinhTrang(TinhTrangDTO ttDTO) throws Exception {
        TinhTrangMapper mapper = null;
        int result = 0;
        try {
            mapper = new TinhTrangMapper();
            result = mapper.createTinhTrang(ttDTO);
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return result;
    }
    
    public int deleteTinhTrang(String dsMaTinhTrang) throws Exception {
        TinhTrangMapper mapper = null;
        int result = 0;
        try {
            mapper = new TinhTrangMapper();
            if (dsMaTinhTrang != null && !dsMaTinhTrang.equals("")) {
                String[] arrStr = dsMaTinhTrang.split(",");
                for (int i = 0; i < arrStr.length; i++) {
                    if (mapper.deleteTinhTrang(arrStr[i]) != 0) {
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

    
    public int updateTinhTrang(TinhTrangDTO ttDTO) throws Exception {
        TinhTrangMapper mapper = null;
        int result = 0;
        try {
            mapper = new TinhTrangMapper();
            result = mapper.updateTinhTrang(ttDTO);
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return result;
    }
    
    public TinhTrangDTO getTinhTrangDTO(String maTinhTrang) throws Exception {
        TinhTrangMapper mapper = null;
        TinhTrangDTO ttDTO;
        try {
            mapper = new TinhTrangMapper();
            ttDTO = mapper.getTinhTrangDTO(maTinhTrang);
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return ttDTO;
    }

    public List<TinhTrangDTO> getDSTinhTrang(String maTinhTrang, String tenTinhTrang) throws Exception {
        List<TinhTrangDTO> dsTinhTrangDTO = null;
        TinhTrangMapper mapper = null;
        try {
            mapper = new TinhTrangMapper();
            dsTinhTrangDTO = mapper.getDSTinhTrang(maTinhTrang, tenTinhTrang);
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return dsTinhTrangDTO;
    }

    public List<TinhTrangDTO> getDSTatCaTinhTrang() throws Exception {
        List<TinhTrangDTO> dsTinhTrangDTO = null;
        TinhTrangMapper mapper = null;
        try {
            mapper = new TinhTrangMapper();
            dsTinhTrangDTO = mapper.getTatCaTinhTrang();
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return dsTinhTrangDTO;
    }
}
