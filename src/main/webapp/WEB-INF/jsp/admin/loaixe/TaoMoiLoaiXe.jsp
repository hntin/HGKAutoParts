<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tạo mới loại xe</title>
        <link rel="stylesheet" type="text/css" href="./css/admin/admin-style.css">
    </head>
    <body>
        <div id="website-admin">
            <jsp:include page="../MainMenu.jsp" />

            <!-- Phần Content của trang Admin-->
            <div id="content-admin">
                <div class="page-caption">Tạo mới loại xe</div>
                <div style="alignment-adjust: central; margin-top: 30px">
                    <center>
                        <h3><s:property value="msg"></s:property></h3>
                        <s:form method="post" action="./TaoMoiLoaiXe">
                            <s:select label="Hãng Xe" list="%{dsHangXeHM}" name="selectedHangXe"/>
                            <s:textfield name="maLoaiXe" label="Mã loại xe" errorPosition="top"></s:textfield>
                            <s:textfield name="tenLoaiXe" label="Tên loại xe" errorPosition="top"></s:textfield>
                            <s:submit value="Tạo mới"></s:submit>
                        </s:form>
                    </center>
                </div>
            </div>
        </div>
    </body>
</html>
