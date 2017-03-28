<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tạo mới người dùng</title>
        <link rel="stylesheet" type="text/css" href="./css/admin/admin-style.css">
    </head>
    <body>
        <div id="website-admin">
            <jsp:include page="../MainMenu.jsp" />

            <!-- Phần Content của trang Admin-->
            <div id="content-admin">
                <div class="page-caption">Tạo mới người dùng</div>
                <div style="alignment-adjust: central; margin-top: 30px">
                    <center>
                        <h3><s:property value="msg"></s:property></h3>
                        <s:form method="post" action="./TaoMoiNguoiDung">
                            <s:textfield name="maNguoiDungMoi" label="Mã người dùng" errorPosition="top"/>
                            <s:textfield name="tenNguoiDungMoi" label="Tên người dùng" errorPosition="top"/>
                            <s:select label="Loại người dùng" 
                                      list="%{loaiNguoiDungHM}" 
                                      name="selectedLoaiND"/>
                            <s:password label="Mật khẩu" name="matKhauMoi" showPassword="false"></s:password>
                            <s:password label="Nhập lại mật khẩu" name="matKhauMoiConfirm" errorPosition="top" showPassword="false"></s:password>
                            <s:submit value="Tạo mới"></s:submit>
                        </s:form>
                    </center>
                </div>
            </div>
        </div>
    </body>
</html>
