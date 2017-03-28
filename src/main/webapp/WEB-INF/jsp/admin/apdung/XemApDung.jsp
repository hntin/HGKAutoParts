<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cập nhật Áp dụng</title>
        <link rel="stylesheet" type="text/css" href="./css/admin/admin-style.css">
        <script type="text/javascript">
            function BoSungApDung(currentMaSanPham) {
                if (confirm("Bổ sung áp dụng cho sản phẩm đang xem ?") == true) {
                    document.getElementsByName("FormXemApDung")[0].action = "./GoTaoMoiApDung?selectedMaSanPham=" + currentMaSanPham;
                    document.getElementsByName("FormXemApDung")[0].submit();
                }
            }

            function XoaApDung() {
                if (confirm("Bạn có chắc sẽ xóa các áp dụng đã chọn không ?") == true) {
                    document.getElementsByName("FormXemApDung")[0].action = "./XoaApDungSanPham";
                    document.getElementsByName("FormXemApDung")[0].submit();
                }
            }
        </script>
    </head>
    <body>
        <div id="website-admin">
            <jsp:include page="../MainMenu.jsp" />

            <!-- Phần Content của trang Admin-->
            <div id="content-admin">
                <div class="page-caption">Danh sách áp dụng tương ứng</div>
                <center><h3><s:property value="msg"></s:property></h3><br></center><br>
                <label style="font-weight: bold; color: brown; margin-left: 10px; margin-top: 30px;">Mã sản phẩm:</label>
                <label style="font-weight: bold; color: blue"><s:property value='sanphamDTO.MaSanPham'/></label>
                <label style="font-weight: bold; color: brown; margin-left: 10px; margin-top: 30px;">Tên sản phẩm: </label>
                <label style="font-weight: bold; color: blue"><s:property value='sanphamDTO.TenSanPham'/></label>
                <label style="font-weight: bold; color: brown; margin-left: 10px; margin-top: 30px;">Mô tả sản phẩm: </label>
                <label style="font-weight: bold; color: blue"><s:property value='sanphamDTO.MoTaSanPham'/></label>
                <div style="alignment-adjust: central; margin-top: 15px; border-top:2px solid gainsboro"><br>
                <label style="font-weight: bold; color: brown; margin-left: 10px; margin-top: 30px;">Danh sách dòng xe có thể áp dụng</label>
                    <s:form action="" method="post" name="FormXemApDung" id="FormXemApDung">
                        <s:hidden name="maSanPham" value="%{sanphamDTO.MaSanPham}"></s:hidden>
                        <s:hidden name="selectedPage" value="1"></s:hidden>
                            <table class="table1">
                                <tr>
                                    <th>Hãng xe</th>
                                    <th>Mã dòng xe</th>
                                    <th>Tên dòng xe</th>
                                    <th>Số Xy-Lanh</th>
                                    <th>Dung tích động cơ</th>
                                    <th>Hộp số</th>
                                    <th>Số cửa</th>
                                    <th>Nhiên liệu</th>
                                    <th>Năm sản xuất</th>
                                    <th></th>
                                </tr>
                            <%int i = 0;%>
                            <s:iterator var="i" step="1" value="dsDongXeDTO">
                                <tr>
                                    <td><s:property value="TenHangXe"/></td>
                                    <td><s:property value="MaDongXe"/></td>
                                    <td><s:property value="TenDongXe"/></td>                                    
                                    <td><s:property value="SoXyLanh"/></td>
                                    <td><s:property value="DongCo"/></td>
                                    <td><s:property value="HopSo"/></td>
                                    <td><s:property value="SoCua"/></td>
                                    <td><s:property value="NhienLieu"/></td>
                                    <td><s:property value="NamSX"/></td>
                                    <td><input type="checkbox" name="apDungCheckBox" value="<s:property value='MaDongXe'/>"></td>
                                </tr>
                            </s:iterator>
                        </table>
                        <br>
                        <%i = 1;%>
                        <span>
                            Trang: 
                            <s:iterator var="pageID" begin="1" end="numberOfPages">
                                <s:if test="#pageID == #var1">
                                    <span><%=i%>&nbsp&nbsp</span>
                                </s:if>
                                <s:else>
                                    <span>
                                        <s:a action="XemApDung?selectedPage=%{pageID}&maSanPham=%{sanphamDTO.MaSanPham}"><%=i%>
                                        </s:a>&nbsp&nbsp
                                    </span>
                                </s:else>
                                <%i++;%>
                            </s:iterator>
                        </span>
                        <br><br>
                        <input type="button" name="ThemNguoiDungButton" value="Bổ sung áp dụng mới" onclick="BoSungApDung('<s:property value="sanphamDTO.MaSanPham"/>')">
                        <input type="button" name="XoaNguoiDungButton" value="Xóa áp dụng đã chọn" onclick="XoaApDung()">
                    </s:form>
                </div>
            </div>
        </div>
    </body>
</html>
