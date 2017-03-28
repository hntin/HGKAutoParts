package hgksoft.acquy.bo;

import hgksoft.acquy.dbaccess.HangXeMapper;
import hgksoft.acquy.dto.HangXeDTO;
import java.util.List;

/**
 *
 * @author HNTIN
 */
public class HangXeBO {

    public HangXeDTO getHangXeDTO(String maHangXe) throws Exception {
        HangXeMapper mapper = new HangXeMapper();
        HangXeDTO hangxeDTO;
        try {
            hangxeDTO = mapper.getHangXeDTO(maHangXe);
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return hangxeDTO;
    }

    public List<HangXeDTO> getDSHangXe(String maHangXe, String tenHangXe) throws Exception {
        List<HangXeDTO> dsHangXeDTO = null;
        HangXeMapper mapper = new HangXeMapper();
        try {
            dsHangXeDTO = mapper.getDSHangXe(maHangXe, tenHangXe);
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return dsHangXeDTO;
    }

    public List<HangXeDTO> getDSTatCaHangXe() throws Exception {
        List<HangXeDTO> dsHangXeDTO = null;
        HangXeMapper mapper = new HangXeMapper();
        try {
            dsHangXeDTO = mapper.getDSTatCaHangXe();
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return dsHangXeDTO;
    }

    public int createHangXe(HangXeDTO hangxeDTO) throws Exception {
        HangXeMapper mapper = new HangXeMapper();
        int result = 0;
        try {
            result = mapper.createHangXe(hangxeDTO);
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return result;
    }

    public int deleteHangXe(String dsMaHangXe) throws Exception {
        HangXeMapper mapper = new HangXeMapper();
        int result = 0;
        try {
            if (dsMaHangXe != null && !dsMaHangXe.equals("")) {
                String[] arrStr = dsMaHangXe.split(",");
                for (int i = 0; i < arrStr.length; i++) {
                    if (mapper.deleteHangXe(arrStr[i]) != 0) {
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

    public int updateHangXe(HangXeDTO hangxeDTO) throws Exception {
        HangXeMapper mapper = new HangXeMapper();;
        int result = 0;
        try {
            result = mapper.updateHangXe(hangxeDTO);
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return result;
    }
}
