<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng nhập</title>
        <link rel="stylesheet" type="text/css" href="./css/admin/admin-style.css">
    </head>
    <body>
        <div id="website">
            <div id="top-admin">
                <div id="top-left-admin">E-Commerce Framework 1.0 - Quản trị hệ thống</div>
                <div id="top-center-admin">@Copyright by HGK-Software 2016</div>
                <div id="top-right-admin">Người dùng:</div>
            </div>
            <div id="content-admin">
                <center>
                    <div class="page-caption">Đăng nhập hệ thống</div>
                    <div style="alignment-adjust: central;">
                        <s:form method="post" action="./DangNhap">
                            <h3><s:property value="msg"></s:property></h3>
                            <s:textfield name="maNguoiDung" label="Mã người dùng" errorPosition="top"></s:textfield><br>
                            <s:password name="matKhau" label="Mật khẩu" errorPosition="top"></s:password><br>
                            <s:submit value="Đăng nhập"></s:submit>
                        </s:form>
                    </div>
                </center>
            </div>      
        </div>
    </body>
</html>
