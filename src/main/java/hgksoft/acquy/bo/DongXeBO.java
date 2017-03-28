package hgksoft.acquy.bo;

import hgksoft.acquy.dbaccess.DongXeMapper;
import hgksoft.acquy.dto.DongXeDTO;
import java.util.List;

/**
 *
 * @author HNTIN
 */
public class DongXeBO {
    public DongXeDTO getDongXeDTO(String maDongXe) throws Exception {
        DongXeMapper mapper = new DongXeMapper();
        DongXeDTO dongxeDTO;
        try {
            dongxeDTO = mapper.getDongXeDTO(maDongXe);
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return dongxeDTO;
    }

    public List<DongXeDTO> getDSDongXe(String maDongXe, String tenDongXe) throws Exception {
        List<DongXeDTO> dsDongXeDTO = null;
        DongXeMapper mapper = new DongXeMapper();
        try {
            dsDongXeDTO = mapper.getDSDongXe(maDongXe, tenDongXe);
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return dsDongXeDTO;
    }
    
    public List<DongXeDTO> getDSDongXeTheoLX(String maLoaiXe) throws Exception {
        List<DongXeDTO> dsDongXeDTO = null;
        DongXeMapper mapper = new DongXeMapper();
        try {
            dsDongXeDTO = mapper.getDSDongXeTheoLX(maLoaiXe);
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return dsDongXeDTO;
    }
    
    public List<DongXeDTO> getDSDongXeTheoLX(String maLoaiXe, String maHangXe) throws Exception {
        List<DongXeDTO> dsDongXeDTO = null;
        DongXeMapper mapper = new DongXeMapper();
        try {
            dsDongXeDTO = mapper.getDSDongXeTheoLX(maLoaiXe, maHangXe);
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return dsDongXeDTO;
    }

    public List<DongXeDTO> getDSTatCaDongXe() throws Exception {
        List<DongXeDTO> dsDongXeDTO = null;
        DongXeMapper mapper = new DongXeMapper();
        try {
            dsDongXeDTO = mapper.getDSTatCaDongXe();
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return dsDongXeDTO;
    }

    public int createDongXe(DongXeDTO dongxeDTO) throws Exception {
        DongXeMapper mapper = new DongXeMapper();
        int result = 0;
        try {
            result = mapper.createDongXe(dongxeDTO);
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return result;
    }

    public int deleteDongXe(String dsMaDongXe) throws Exception {
        DongXeMapper mapper = new DongXeMapper();
        int result = 0;
        try {
            if (dsMaDongXe != null && !dsMaDongXe.equals("")) {
                String[] arrStr = dsMaDongXe.split(",");
                for (int i = 0; i < arrStr.length; i++) {
                    if (mapper.deleteDongXe(arrStr[i]) != 0) {
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

    public int updateDongXe(DongXeDTO dongxeDTO) throws Exception {
        DongXeMapper mapper = new DongXeMapper();;
        int result = 0;
        try {
            result = mapper.updateDongXe(dongxeDTO);
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return result;
    }
}
