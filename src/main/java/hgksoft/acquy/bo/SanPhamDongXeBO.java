package hgksoft.acquy.bo;

import hgksoft.acquy.dbaccess.DongXeMapper;
import hgksoft.acquy.dbaccess.SanPhamDongXeMapper;
import hgksoft.acquy.dto.DongXeDTO;
import hgksoft.acquy.dto.SanPhamDTO;
import hgksoft.acquy.dto.SanPhamDongXeDTO;
import java.util.List;

/**
 *
 * @author HNTIN
 */
public class SanPhamDongXeBO {

    public List<SanPhamDTO> getDSSanPhamTuongThichDongXe(String maDongXe) throws Exception {
        SanPhamDongXeMapper mapper = new SanPhamDongXeMapper();
        List<SanPhamDTO> dsSanPhamDTO = null;
        try {
            dsSanPhamDTO = mapper.getDSSanPhamTuongThichDongXe(maDongXe);
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return dsSanPhamDTO;
    }
    
    public List<DongXeDTO> getDSDongXeChuaApDung(String maSanPham, String maHangXe) throws Exception {
        SanPhamDongXeMapper mapper = new SanPhamDongXeMapper();
        List<DongXeDTO> dsDongXeDTO = null;
        try {
            dsDongXeDTO = mapper.getDSDongXeChuaApDung(maSanPham, maHangXe);
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return dsDongXeDTO;
    }

    public List<DongXeDTO> getDSDongXeTuongThichSanPham(String maSanPham) throws Exception {
        SanPhamDongXeMapper mapper = new SanPhamDongXeMapper();
        List<DongXeDTO> dsDongXeDTO = null;
        try {
            dsDongXeDTO = mapper.getDSDongXeTuongThichSanPham(maSanPham);
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return dsDongXeDTO;
    }
    
    public List<SanPhamDongXeDTO> getDSSanPhamDongXeTuongThich(String maSanPham) throws Exception {
        SanPhamDongXeMapper mapper = new SanPhamDongXeMapper();
        List<SanPhamDongXeDTO> dsSanPhamDongXeDTO = null;
        try {
            dsSanPhamDongXeDTO = mapper.getDSSanPhamDongXeTuongThich(maSanPham);
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return dsSanPhamDongXeDTO;
    }

    public List<SanPhamDongXeDTO> searchDSSanPhamDongXeTuongThich(String maSanPham, String tenSanPham) throws Exception {
        SanPhamDongXeMapper mapper = new SanPhamDongXeMapper();
        List<SanPhamDongXeDTO> dsSanPhamDongXeDTO = null;
        try {
            dsSanPhamDongXeDTO = mapper.searchDSSanPhamDongXeTuongThich(maSanPham, tenSanPham);
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return dsSanPhamDongXeDTO;
    }

    public int deleteApDung(String dsApDungSeXoa) throws Exception {
        SanPhamDongXeMapper mapper = new SanPhamDongXeMapper();
        int result = 0;
        try {
            if (dsApDungSeXoa != null && !dsApDungSeXoa.equals("")) {
                String[] arrStr1 = dsApDungSeXoa.split(",");
                for (int i = 0; i < arrStr1.length; i++) {
                    String[] arrStr2 = arrStr1[i].split(":");
                    if (mapper.deleteApDung(arrStr2[0].trim(), arrStr2[1].trim()) != 0) {
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
    
    public int deleteApDung(String maSanPham, String dsApDungSeXoa) throws Exception {
        SanPhamDongXeMapper mapper = new SanPhamDongXeMapper();
        int result = 0;
        try {
            if (dsApDungSeXoa != null && !dsApDungSeXoa.equals("")) {
                String[] arrStr1 = dsApDungSeXoa.split(",");
                for (int i = 0; i < arrStr1.length; i++) {
                    if (mapper.deleteApDung(maSanPham, arrStr1[i].trim()) != 0) {
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
    
    public int createApDungMoi(String maSanPham, String dsApDungMoi) throws Exception {
        SanPhamDongXeMapper mapper = new SanPhamDongXeMapper();
        int result = 0;
        try {
            if (dsApDungMoi != null && !dsApDungMoi.equals("")) {
                String[] arrStr1 = dsApDungMoi.split(",");
                for (int i = 0; i < arrStr1.length; i++) {
                    if (mapper.createApDungMoi(maSanPham, arrStr1[i].trim()) != 0) {
                        result += 1;
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return result;
    }
}
