<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cập nhật sản phẩm</title>
        <link rel="stylesheet" type="text/css" href="./css/admin/admin-style.css">
        <script type="text/javascript">
            function OnClickSuaMaSanPham() {
                c = confirm("Sửa mã sản phẩm?");
                if (c == true) {
                    document.getElementById('maSanPhamUpdate').removeAttribute('disabled');
                }
            }

            function OnClickCapNhatSP() {
                document.getElementById('maSanPhamUpdate').removeAttribute('disabled');
                document.getElementsByName("FormCapNhatSanPham")[0].action = "./CapNhatSanPham";
                document.getElementsByName("FormCapNhatSanPham")[0].submit();
            }
        </script>
    </head>
    <body>
        <div id="website-admin">
            <jsp:include page="../MainMenu.jsp" />            
            <!-- Phần Content của trang Admin-->
            <div id="content-admin">
                <div class="page-caption">Cập nhật thông tin sản phẩm</div>
                <div style="alignment-adjust: central; margin-top: 30px">
                    <center>
                        <h3><s:property value="msg"></s:property></h3>
                        <s:form method="post" action="" theme="simple" enctype="multipart/form-data" name="FormCapNhatSanPham" id="FormCapNhatSanPham">
                            <s:hidden name="maSanPham" label="Mã sản phẩm" errorPosition="top" value="%{sanphamDTO.MaSanPham}"></s:hidden>
                                <table border="0">
                                    <tr>
                                        <td>Mã sản phẩm</td>
                                        <td><s:textfield name="maSanPhamUpdate" id="maSanPhamUpdate" errorPosition="top" value="%{sanphamDTO.MaSanPham}" disabled="true"></s:textfield>
                                            <input type="button" name="ButtonSuaMa" value="Sửa mã" onclick="OnClickSuaMaSanPham()">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Tên sản phẩm</td>
                                        <td><s:textfield name="tenSanPham" errorPosition="top" value="%{sanphamDTO.TenSanPham}"></s:textfield></td>
                                    </tr>
                                    <tr>
                                        <td>Mô tả sản phẩm</td>
                                        <td><s:textarea name="moTaSanPham" errorPosition="top" cssStyle="width: 300px;" value="%{sanphamDTO.MoTaSanPham}">
                                        </s:textarea></td>
                                </tr>
                                <tr>
                                    <td>Hãng sản xuất</td>
                                    <td><s:select list="%{hangSanXuatHM}" name="selectedHangSanXuat" value="%{sanphamDTO.MaNhaCC}"/></td>
                                </tr>
                                <tr>
                                    <td>Nơi sản xuất</td>
                                    <td><s:select list="%{noiSanXuatHM}" name="selectedNoiSanXuat" value="%{sanphamDTO.MaNuocSanXuat}"/></td>
                                </tr>
                                <tr>
                                    <td>Loại sản phẩm</td>
                                    <td><s:select label="" list="%{loaiSanPhamHM}" name="selectedLoaiSP" value="%{sanphamDTO.MaLoaiSanPham}"/></td>
                                </tr>
                                <tr>
                                    <td>Tình trạng</td>
                                    <td><s:select label="" list="%{tinhTrangHM}" name="selectedTinhTrangSP" value="%{sanphamDTO.MaTinhTrang}"/></td>
                                </tr>
                                <tr>
                                    <td>Giá (VNĐ)</td>
                                    <td><s:textfield name="gia" label="" errorPosition="top" value="%{sanphamDTO.Gia}"></s:textfield></td>
                                    </tr>
                                    <tr>
                                        <td>Hình đại diện</td>
                                        <td><img src="<s:property value="%{sanphamDTO.HinhDaiDien}"/>" width="50" height="50"></td>
                                </tr>
                                <tr>
                                    <td>Chọn hình đại diện mới</td>
                                    <td><s:file name="uploadImage" label="" size="50"></s:file></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td><input type="button" name="" value="Cập nhật" onclick="OnClickCapNhatSP()"></td>
                                    </tr>
                                </table>                            
                        </s:form>
                    </center>
                </div>
            </div>
        </div>
    </body>
</html>
