<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tạo mới sản phẩm</title>
        <link rel="stylesheet" type="text/css" href="./css/admin/admin-style.css">
        <script type="text/javascript">
        </script>
    </head>
    <body>
        <div id="website-admin">
            <jsp:include page="../MainMenu.jsp" />

            <!-- Phần Content của trang Admin-->
            <div id="content-admin">
                <div class="page-caption">Thêm mới sản phẩm</div>
                <center><h3><s:property value="msg"></s:property></h3></center>
                <div style="alignment-adjust: central; margin-top: 30px">
                    <center>
                        <s:form method="post" action="./TaoMoiSanPham" enctype="multipart/form-data" name="FormTaoMoiSanPham">
                            <s:textfield name="maSP" label="Mã sản phẩm" errorPosition="top"></s:textfield>
                            <s:textfield name="tenSP" label="Tên sản phẩm" errorPosition="top"></s:textfield>
                            <s:textarea name="moTaSP" label="Mô tả sản phẩm" errorPosition="top" cssStyle="width: 300px;"></s:textarea>

                            <s:select label="Hãng sản xuất" 
                                      list="%{hangSanXuatHM}" 
                                      name="selectedHangSanXuat"/>

                            <s:select label="Nơi sản xuất" 
                                      list="%{noiSanXuatHM}" 
                                      name="selectedNoiSanXuat"/>

                            <s:select label="Loại sản phẩm" 
                                      list="%{loaiSanPhamHM}" 
                                      name="selectedLoaiSP"/>

                            <s:select label="Tình trạng" 
                                      list="%{tinhTrangHM}" 
                                      name="selectedTinhTrangSP"/>

                            <s:textfield name="gia" label="Giá (VNĐ)" errorPosition="top"></s:textfield>
                            <s:file name="uploadImage" label="Hình đại diện" size="50"></s:file>
                            <s:submit value="Tạo mới"></s:submit>
                        </s:form>
                    </center>
                </div>
            </div>
        </div>
    </body>
</html>
