/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hgksoft.acquy.bo;

import hgksoft.acquy.dbaccess.NguoiDungMapper;
import hgksoft.acquy.dto.NguoiDungDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HNTIN
 */
public class NguoiDungBO {

    public boolean isExistNguoiDung(String maNguoiDung, String matKhau) throws Exception {
        NguoiDungMapper mapper = null;
        boolean isExist = false;
        try {
            mapper = new NguoiDungMapper();
            isExist = mapper.isExistNguoiDung(maNguoiDung, matKhau);
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return isExist;
    }

    public int deleteNguoiDung(String dsMaNguoiDung) throws Exception {
        NguoiDungMapper mapper = null;
        int result = 0;
        try {
            mapper = new NguoiDungMapper();
            if (dsMaNguoiDung != null && !dsMaNguoiDung.equals("")) {
                String[] arrStr = dsMaNguoiDung.split(",");
                for (int i = 0; i < arrStr.length; i++) {
                    if (mapper.deleteNguoiDung(arrStr[i]) != 0) {
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

    public NguoiDungDTO getNguoiDungDTO(String maNguoiDung) throws Exception {
        NguoiDungMapper mapper = null;
        NguoiDungDTO nguoiDungDTO;
        try {
            mapper = new NguoiDungMapper();
            nguoiDungDTO = mapper.getNguoiDungDTO(maNguoiDung);
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return nguoiDungDTO;
    }

    public NguoiDungDTO getNguoiDungDTO(String maNguoiDung, String matKhau) throws Exception {
        NguoiDungMapper mapper = null;
        NguoiDungDTO nguoiDungDTO;
        try {
            mapper = new NguoiDungMapper();
            nguoiDungDTO = mapper.getNguoiDungDTO(maNguoiDung, matKhau);
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return nguoiDungDTO;
    }

    public List<NguoiDungDTO> getDSNguoiDung(String maNguoiDung, String tenNguoiDung) throws Exception {
        List<NguoiDungDTO> dsNguoiDungDTO = null;
        NguoiDungMapper mapper = null;
        try {
            mapper = new NguoiDungMapper();
            dsNguoiDungDTO = mapper.getDSNguoiDung(maNguoiDung, tenNguoiDung);
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return dsNguoiDungDTO;
    }

    public List<NguoiDungDTO> getDSNguoiDung(String maNguoiDung, String tenNguoiDung, String maLoaiND) throws Exception {
        List<NguoiDungDTO> dsNguoiDungDTO = null;
        NguoiDungMapper mapper = null;
        try {
            mapper = new NguoiDungMapper();
            dsNguoiDungDTO = mapper.getDSNguoiDung(maNguoiDung, tenNguoiDung, maLoaiND);
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return dsNguoiDungDTO;
    }

    public int createNguoiDung(NguoiDungDTO nguoidungDTO) throws Exception {
        NguoiDungMapper mapper = null;
        int result = 0;
        try {
            mapper = new NguoiDungMapper();
            result = mapper.createNguoiDung(nguoidungDTO);
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return result;
    }
    
    public int updateNguoiDung(NguoiDungDTO nguoidungDTO) throws Exception {
        NguoiDungMapper mapper = null;
        int result = 0;
        try {
            mapper = new NguoiDungMapper();
            result = mapper.updateNguoiDung(nguoidungDTO);
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return result;
    }
}
