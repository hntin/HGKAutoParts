<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cập nhật hãng xe</title>
        <link rel="stylesheet" type="text/css" href="./css/admin/admin-style.css">
    </head>
    <body>
        <div id="website-admin">
            <jsp:include page="../MainMenu.jsp" />            
            <!-- Phần Content của trang Admin-->
            <div id="content-admin">
                <div class="page-caption">Cập nhật thông tin hãng xe</div>
                <div style="alignment-adjust: central; margin-top: 30px">
                    <center>
                        <h3><s:property value="msg"></s:property></h3>
                        <s:form method="post" action="./CapNhatHangXe">
                            <s:textfield name="maHangXe" label="Mã hãng xe" errorPosition="top" value="%{hxDTO.MaHangXe}" readonly="true"></s:textfield>
                            <s:textfield name="tenHangXe" label="Tên hãng xe" errorPosition="top" value="%{hxDTO.TenHangXe}"></s:textfield>
                            <s:textarea name="ghiChu" label="Thông tin khác" errorPosition="top" cssStyle="width:300px;" value="%{hxDTO.GhiChu}"></s:textarea>
                            <s:submit value="Cập nhật"></s:submit>
                        </s:form>
                    </center>
                </div>
            </div>
        </div>
    </body>
</html>
