<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cập nhật dòng xe</title>
        <link rel="stylesheet" type="text/css" href="./css/admin/admin-style.css">
        <script type="text/javascript">
            function OnChangeHangXe() {
                document.getElementsByName("FormCapNhatDongXe")[0].action = "./GoCapNhatDongXe";
                document.getElementsByName("FormCapNhatDongXe")[0].submit();
            }
        </script>
    </head>
    <body>
        <div id="website-admin">
            <jsp:include page="../MainMenu.jsp" />            
            <!-- Phần Content của trang Admin-->
            <div id="content-admin">
                <div class="page-caption">Cập nhật thông tin dòng xe</div>
                <div style="alignment-adjust: central; margin-top: 30px">
                    <center>
                        <h3><s:property value="msg"></s:property></h3>
                        <s:form method="post" action="./CapNhatDongXe" name="FormCapNhatDongXe">                            
                            <s:textfield name="maDongXe" label="Mã dòng xe" errorPosition="top" value="%{dongxeDTO.MaDongXe}" readonly="true"></s:textfield>
                            <s:textfield name="tenDongXe" label="Tên dòng xe" errorPosition="top" value="%{dongxeDTO.TenDongXe}"></s:textfield>
                            <s:select list="%{soXyLanhList}" label="Số Xy-lanh" name="soXyLanh" value="%{dongxeDTO.SoXyLanh}"></s:select>                            
                            <s:textfield name="dongCo" label="Dung tích động cơ" errorPosition="top" value="%{dongxeDTO.DongCo}"></s:textfield>
                            <s:select list="%{hopSoList}" label="Hộp số" name="hopSo" value="%{dongxeDTO.HopSo}"></s:select>
                            <s:select list="%{soCuaList}" label="Số cửa" name="soCua" value="%{dongxeDTO.SoCua}"></s:select>
                            <s:select list="%{nhienLieuList}" label="Nhiên liệu" name="nhienLieu" value="%{dongxeDTO.NhienLieu}"></s:select>
                            <s:textfield name="namSX" label="Năm sản xuất" value="%{dongxeDTO.NamSX}"></s:textfield>
                            <s:select list="%{dsHangXeHM}" label="Hãng xe" name="selectedHangXe" value="%{dongxeDTO.MaHangXe}" onchange="OnChangeHangXe()"/>
                            <s:select list="%{dsLoaiXeHM}" label="Loại xe" name="selectedLoaiXe" value="%{dongxeDTO.MaLoaiXe}" />
                            <s:submit value="Cập nhật"></s:submit>
                        </s:form>
                    </center>
                </div>
            </div>
        </div>
    </body>
</html>
