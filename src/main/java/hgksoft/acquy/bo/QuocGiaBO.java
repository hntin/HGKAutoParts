/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hgksoft.acquy.bo;

import hgksoft.acquy.dbaccess.QuocGiaMapper;
import hgksoft.acquy.dto.QuocGiaDTO;
import java.util.List;

/**
 *
 * @author HNTIN
 */
public class QuocGiaBO {

    public QuocGiaDTO getQuocGiaDTO(String maQuocGia) throws Exception {
        QuocGiaMapper mapper = null;
        QuocGiaDTO quocgiaDTO;
        try {
            mapper = new QuocGiaMapper();
            quocgiaDTO = mapper.getQuocGiaDTO(maQuocGia);
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return quocgiaDTO;
    }

    public List<QuocGiaDTO> getDSQuocGia(String maQuocGia, String tenQuocGia) throws Exception {
        List<QuocGiaDTO> dsQuocGiaDTO = null;
        QuocGiaMapper mapper = null;
        try {
            mapper = new QuocGiaMapper();
            dsQuocGiaDTO = mapper.getDSQuocGia(maQuocGia, tenQuocGia);
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return dsQuocGiaDTO;
    }

    public List<QuocGiaDTO> getDSTatCaQuocGia() throws Exception {
        List<QuocGiaDTO> dsQuocGiaDTO = null;
        QuocGiaMapper mapper = null;
        try {
            mapper = new QuocGiaMapper();
            dsQuocGiaDTO = mapper.getDSTatCaQuocGia();
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return dsQuocGiaDTO;
    }

}
