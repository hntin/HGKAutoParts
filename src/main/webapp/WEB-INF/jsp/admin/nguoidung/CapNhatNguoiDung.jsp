<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cập nhật người dùng</title>
        <link rel="stylesheet" type="text/css" href="./css/admin/admin-style.css">
    </head>
    <body>
        <div id="website-admin">
            <jsp:include page="../MainMenu.jsp" />            
            <!-- Phần Content của trang Admin-->
            <div id="content-admin">
                <div class="page-caption">Cập nhật thông tin người dùng</div>
                <div style="alignment-adjust: central; margin-top: 30px">
                    <center>
                        <h3><s:property value="msg"></s:property></h3>
                        <s:form method="post" action="./CapNhatNguoiDung">
                            <s:textfield name="maNguoiDung" label="Mã người dùng" readonly="true" value="%{nguoidungDTO.MaNguoiDung}"></s:textfield>
                            <s:textfield name="tenNguoiDung" label="Tên người dùng" value="%{nguoidungDTO.TenNguoiDung}"></s:textfield>
                            <s:select label="Loại người dùng" 
                                      list="%{loaiNguoiDungHM}" 
                                      name="maLoaiNguoiDung" value="%{nguoidungDTO.MaLoaiNguoiDung}"/>
                            <s:password label="Mật khẩu" name="matKhau" showPassword="true" value="%{nguoidungDTO.MatKhau}" ></s:password>
                            <s:password label="Nhập lại mật khẩu" name="matKhauConfirm" showPassword="true" ></s:password>
                            <s:submit value="Cập nhật"></s:submit>
                        </s:form>
                    </center>
                </div>
            </div>
        </div>
    </body>
</html>
