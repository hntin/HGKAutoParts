<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tạo mới dòng xe</title>
        <link rel="stylesheet" type="text/css" href="./css/admin/admin-style.css">
        <script type="text/javascript">
            function OnChangeHangXe() {
                document.getElementsByName("FormTaoMoiDongXe")[0].action = "./GoTaoMoiDongXe";
                document.getElementsByName("FormTaoMoiDongXe")[0].submit();
            }
        </script>
    </head>
    <body>
        <div id="website-admin">
            <jsp:include page="../MainMenu.jsp" />

            <!-- Phần Content của trang Admin-->
            <div id="content-admin">
                <div class="page-caption">Tạo mới dòng xe</div>
                <div style="alignment-adjust: central; margin-top: 30px">
                    <center>
                        <h3><s:property value="msg"></s:property></h3>
                        <s:form method="post" action="./TaoMoiDongXe" name="FormTaoMoiDongXe">
                            <s:textfield name="maDongXe" label="Mã dòng xe" errorPosition="top" value="%{maDongXe}"></s:textfield>
                            <s:textfield name="tenDongXe" label="Tên dòng xe" errorPosition="top" value="%{tenDongXe}"></s:textfield>
                            <s:select list="%{soXyLanhList}" label="Số Xy-lanh" name="soXyLanh" value="%{soXyLanh}"></s:select>                            
                            <s:textfield name="dongCo" label="Dung tích động cơ" errorPosition="top" value="%{dongCo}"></s:textfield>
                            <s:select list="%{hopSoList}" label="Hộp số" name="hopSo" value="%{hopSo}"></s:select>
                            <s:select list="%{soCuaList}" label="Số cửa" name="soCua" value="%{soCua}"></s:select>
                            <s:select list="%{nhienLieuList}" label="Nhiên liệu" name="nhienLieu" value="%{nhienLieu}"></s:select>
                            <s:textfield name="namSX" label="Năm sản xuất" value="%{namSX}"></s:textfield>
                            <s:select list="%{dsHangXeHM}" label="Hãng xe" name="selectedHangXe" value="%{selectedHangXe}" onchange="OnChangeHangXe()"/>
                            <s:select list="%{dsLoaiXeHM}" label="Loại xe" name="selectedLoaiXe"/>
                            <s:submit value="Tạo mới"></s:submit>
                        </s:form>
                    </center>
                </div>
            </div>
        </div>
    </body>
</html>
