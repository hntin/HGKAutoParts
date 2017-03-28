<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cập nhật loại xe</title>
        <link rel="stylesheet" type="text/css" href="./css/admin/admin-style.css">
    </head>
    <body>
        <div id="website-admin">
            <jsp:include page="../MainMenu.jsp" />            
            <!-- Phần Content của trang Admin-->
            <div id="content-admin">
                <div class="page-caption">Cập nhật thông tin loại xe</div>
                <div style="alignment-adjust: central; margin-top: 30px">
                    <center>
                        <h3><s:property value="msg"></s:property></h3>
                        <s:form method="post" action="./CapNhatLoaiXe">
                            <s:select label="Hãng Xe" list="%{dsHangXeHM}" name="selectedHangXe"/>
                            <s:textfield name="maLoaiXe" label="Mã loại xe" errorPosition="top" value="%{loaixeDTO.MaLoaiXe}" readonly="true"></s:textfield>
                            <s:textfield name="tenLoaiXe" label="Tên loại xe" errorPosition="top" value="%{loaixeDTO.TenLoaiXe}"></s:textfield>
                            <s:submit value="Cập nhật"></s:submit>
                        </s:form>
                    </center>
                </div>
            </div>
        </div>
    </body>
</html>
