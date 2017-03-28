package hgksoft.acquy.bo;

import hgksoft.acquy.dbaccess.LoaiXeMapper;
import hgksoft.acquy.dto.LoaiXeDTO;
import java.util.List;

/**
 *
 * @author HNTIN
 */
public class LoaiXeBO {

    public LoaiXeDTO getLoaiXeDTO(String maLoaiXe) throws Exception {
        LoaiXeMapper mapper = new LoaiXeMapper();
        LoaiXeDTO hangxeDTO;
        try {
            hangxeDTO = mapper.getLoaiXeDTO(maLoaiXe);
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return hangxeDTO;
    }

    public List<LoaiXeDTO> getDSLoaiXe(String maLoaiXe, String tenLoaiXe) throws Exception {
        List<LoaiXeDTO> dsLoaiXeDTO = null;
        LoaiXeMapper mapper = new LoaiXeMapper();
        try {
            dsLoaiXeDTO = mapper.getDSLoaiXe(maLoaiXe, tenLoaiXe);
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return dsLoaiXeDTO;
    }
    
    public List<LoaiXeDTO> getDSLoaiXe(String maHangXe) throws Exception {
        List<LoaiXeDTO> dsLoaiXeDTO = null;
        LoaiXeMapper mapper = new LoaiXeMapper();
        try {
            dsLoaiXeDTO = mapper.getDSLoaiXe(maHangXe);
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return dsLoaiXeDTO;
    }

    public List<LoaiXeDTO> getDSTatCaLoaiXe() throws Exception {
        List<LoaiXeDTO> dsLoaiXeDTO = null;
        LoaiXeMapper mapper = new LoaiXeMapper();
        try {
            dsLoaiXeDTO = mapper.getDSTatCaLoaiXe();
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return dsLoaiXeDTO;
    }

    public int createLoaiXe(LoaiXeDTO hangxeDTO) throws Exception {
        LoaiXeMapper mapper = new LoaiXeMapper();
        int result = 0;
        try {
            result = mapper.createLoaiXe(hangxeDTO);
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return result;
    }

    public int deleteLoaiXe(String dsMaLoaiXe) throws Exception {
        LoaiXeMapper mapper = new LoaiXeMapper();
        int result = 0;
        try {
            if (dsMaLoaiXe != null && !dsMaLoaiXe.equals("")) {
                String[] arrStr = dsMaLoaiXe.split(",");
                for (int i = 0; i < arrStr.length; i++) {
                    if (mapper.deleteLoaiXe(arrStr[i]) != 0) {
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

    public int updateLoaiXe(LoaiXeDTO loaixeDTO) throws Exception {
        LoaiXeMapper mapper = new LoaiXeMapper();;
        int result = 0;
        try {
            result = mapper.updateLoaiXe(loaixeDTO);
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return result;
    }
}
