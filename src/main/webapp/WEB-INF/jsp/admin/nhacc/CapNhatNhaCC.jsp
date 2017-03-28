<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cập nhật nhà cung cấp</title>
        <link rel="stylesheet" type="text/css" href="./css/admin/admin-style.css">
    </head>
    <body>
        <div id="website-admin">
            <jsp:include page="../MainMenu.jsp" />            
            <!-- Phần Content của trang Admin-->
            <div id="content-admin">
                <div class="page-caption">Cập nhật thông tin nhà cung cấp</div>
                <div style="alignment-adjust: central; margin-top: 30px">
                    <center>
                        <h3><s:property value="msg"></s:property></h3>
                        <s:form method="post" action="./CapNhatNhaCC">
                            <s:textfield name="maNhaCC" label="Mã nhà cung cấp" errorPosition="top" readonly="true" value="%{nccDTO.MaNhaCC}"></s:textfield>
                            <s:textfield name="tenNhaCC" label="Tên nhà cung cấp" errorPosition="top" value="%{nccDTO.TenNhaCC}"></s:textfield>
                            <s:textfield name="email" label="Thư điện tử (Email)" errorPosition="top" value="%{nccDTO.Email}"></s:textfield>
                            <s:textfield name="dienThoai" label="Điện thoại" errorPosition="top" value="%{nccDTO.DienThoai}"></s:textfield>
                            <s:textarea name="diaChi" label="Địa chỉ" errorPosition="top" cssStyle="width: 400px" value="%{nccDTO.DiaChi}"></s:textarea>
                            <s:textarea name="ghiChu" label="Thông tin khác" errorPosition="top" cssStyle="width: 400px" value="%{nccDTO.GhiChu}"></s:textarea>
                            <s:submit value="Cập nhật"></s:submit>
                        </s:form>
                    </center>
                </div>
            </div>
        </div>
    </body>
</html>
