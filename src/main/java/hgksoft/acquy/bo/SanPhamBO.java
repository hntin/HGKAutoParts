package hgksoft.acquy.bo;

import hgksoft.acquy.constant.CommonConst;
import hgksoft.acquy.dbaccess.SanPhamMapper;
import hgksoft.acquy.dto.SanPhamDTO;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import javax.servlet.ServletContext;
import org.apache.commons.io.FileUtils;
import static org.apache.struts2.ServletActionContext.getServletContext;

/**
 *
 * @author HNTIN
 */
public class SanPhamBO {

    public String uploadHinhAnhSanPham(String maSP, File uploadImage, String uploadImageFileName) throws Exception {
        ServletContext servletContext = getServletContext();
        String contextPath = servletContext.getRealPath(File.separator);
        //String imageFilePath = contextPath + CommonConst.PATH_UPLOAD_PRODUCTIMAGES;
        String imageFilePath = contextPath + CommonConst.PATH_UPLOAD_PRODUCTIMAGES + "\\" + maSP;

        if (Files.isDirectory(Paths.get(imageFilePath))) {
            // Thư mục Upload đã tồn tại --> Xóa hình đại diện cũ
            File file = new File(imageFilePath);
            File[] fileList = file.listFiles();
            for (int i=0; i<fileList.length; i++) {
                if (fileList[i].isFile())
                    fileList[i].delete();
            }            
        } else {
            // Tạo thư mục Upload hình cho sản phẩm
            File imageDir = new File(imageFilePath);
            imageDir.mkdir();
        }

        File destFile = new File(imageFilePath, uploadImageFileName);
        FileUtils.copyFile(uploadImage, destFile);

        //return CommonConst.PATH_UPLOAD_PRODUCTIMAGES + "/" + uploadImageFileName;
        return CommonConst.PATH_UPLOAD_PRODUCTIMAGES + "/" + maSP + "/" + uploadImageFileName;
    }

    public int createSanPham(SanPhamDTO sanphamDTO, File uploadImage, String uploadImageFileName) throws Exception {
        SanPhamMapper mapper = null;
        String uploadFileStr = "";
        int result = 0;
        try {
            mapper = new SanPhamMapper();
            if (uploadImage != null) {
                uploadFileStr = uploadHinhAnhSanPham(sanphamDTO.getMaSanPham(), uploadImage, uploadImageFileName);
            }
            sanphamDTO.setHinhDaiDien(uploadFileStr);
            result = mapper.createSanPham(sanphamDTO);
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return result;
    }

    public int updateSanPham(String maSP, SanPhamDTO updatedSPDTO, File uploadImage, String uploadImageFileName) throws Exception {
        SanPhamMapper mapper = null;
        String uploadFileStr = "";
        int result = 0;
        try {
            mapper = new SanPhamMapper();
            // Cập nhật & upload file hình đại diện mới
            if (uploadImage != null) {
                uploadFileStr = uploadHinhAnhSanPham(updatedSPDTO.getMaSanPham(), uploadImage, uploadImageFileName);
                if (uploadFileStr != null) {
                    updatedSPDTO.setHinhDaiDien(uploadFileStr);
                }
            } else {
                SanPhamDTO oldSPDTO = mapper.getSanPhamDTO(maSP);
                updatedSPDTO.setHinhDaiDien(oldSPDTO.getHinhDaiDien());
            }

            result = mapper.updateSanPham(updatedSPDTO);
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return result;
    }

    public int deleteSanPham(String dsMaSanPham) throws Exception {
        SanPhamMapper mapper = null;
        int result = 0;
        try {
            mapper = new SanPhamMapper();
            if (dsMaSanPham != null && !dsMaSanPham.equals("")) {
                String[] arrStr = dsMaSanPham.split(",");
                for (int i = 0; i < arrStr.length; i++) {
                    if (mapper.deleteSanPham(arrStr[i]) != 0) {
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

    public SanPhamDTO getSanPhamDTO(String maSanPham) throws Exception {
        SanPhamMapper mapper = null;
        SanPhamDTO nguoiDungDTO;
        try {
            mapper = new SanPhamMapper();
            nguoiDungDTO = mapper.getSanPhamDTO(maSanPham);
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return nguoiDungDTO;
    }

    public List<SanPhamDTO> getDSSanPham(
            String maSanPham, String tenSanPham, String moTaSanPham,
            String hangSX, String noiSX, String loaiSP, String tinhTrangSP) throws Exception {
        List<SanPhamDTO> dsSanPhamDTO = null;
        SanPhamMapper mapper = null;
        try {
            mapper = new SanPhamMapper();
            dsSanPhamDTO = mapper.getDSSanPham(maSanPham, tenSanPham, moTaSanPham, hangSX, noiSX, loaiSP, tinhTrangSP);
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return dsSanPhamDTO;
    }

    public List<SanPhamDTO> getDSSanPham(String maSanPham, String tenSanPham) throws Exception {
        List<SanPhamDTO> dsSanPhamDTO = null;
        SanPhamMapper mapper = null;
        try {
            mapper = new SanPhamMapper();
            dsSanPhamDTO = mapper.getDSSanPham(maSanPham, tenSanPham);
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return dsSanPhamDTO;
    }

    public List<SanPhamDTO> getDSSanPham(int maLoaiSanPham) throws Exception {
        List<SanPhamDTO> dsSanPhamDTO = null;
        SanPhamMapper mapper = null;
        try {
            mapper = new SanPhamMapper();
            dsSanPhamDTO = mapper.getDSSanPham(maLoaiSanPham);
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return dsSanPhamDTO;
    }

    public List<SanPhamDTO> getDSTatCaSanPham() throws Exception {
        List<SanPhamDTO> dsSanPhamDTO = null;
        SanPhamMapper mapper = null;
        try {
            mapper = new SanPhamMapper();
            dsSanPhamDTO = mapper.getDSTatCaSanPham();
        } catch (Exception e) {
            throw e;
        } finally {
            mapper.closeConnection();
        }
        return dsSanPhamDTO;
    }

}
