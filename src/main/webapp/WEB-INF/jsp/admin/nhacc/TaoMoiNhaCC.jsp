<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tạo mới nhà cung cấp</title>
        <link rel="stylesheet" type="text/css" href="./css/admin/admin-style.css">
    </head>
    <body>
        <div id="website-admin">
            <jsp:include page="../MainMenu.jsp" />

            <!-- Phần Content của trang Admin-->
            <div id="content-admin">
                <div class="page-caption">Tạo mới nhà cung cấp</div>
                <div style="alignment-adjust: central; margin-top: 30px">
                    <center>
                        <h3><s:property value="msg"></s:property></h3>
                        <s:form method="post" action="./TaoMoiNhaCC" name="FormTaoMoiNhaCC">
                            <s:textfield name="maNhaCC" label="Mã nhà cung cấp" errorPosition="top"></s:textfield>
                            <s:textfield name="tenNhaCC" label="Tên nhà cung cấp" errorPosition="top"></s:textfield>
                            <s:textfield name="email" label="Thư điện tử (Email)" errorPosition="top"></s:textfield>
                            <s:textfield name="dienThoai" label="Điện thoại" errorPosition="top"></s:textfield>
                            <s:textarea name="diaChi" label="Địa chỉ" cssStyle="width: 400px" errorPosition="top"></s:textarea>
                            <s:textarea name="ghiChu" label="Thông tin khác" cssStyle="width: 400px" errorPosition="top"></s:textarea>
                            <s:submit value="Tạo mới"></s:submit>
                        </s:form>
                    </center>
                </div>
            </div>
        </div>
    </body>
</html>
