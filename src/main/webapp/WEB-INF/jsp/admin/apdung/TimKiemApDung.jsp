<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tìm kiếm Áp dụng</title>
        <link rel="stylesheet" type="text/css" href="./css/admin/admin-style.css">
        <script type="text/javascript">
            function XoaApDung() {
                var c1 = confirm("Bạn muốn xóa tất cả các áp dụng đã chọn?");
                if (c1 == true) {
                    var checkboxes = document.getElementsByName("ApDungCheckBox");
                    var checkboxesChecked = [];
                    // loop over them all
                    for (var i = 0; i < checkboxes.length; i++) {
                        // And stick the checked ones onto an array...
                        if (checkboxes[i].checked) {
                            checkboxesChecked.push(checkboxes[i].value);
                        }
                    }
                    document.getElementsByName("FormTimKiemApDung")[0].action = "./XoaApDung?CheckedList=" + checkboxesChecked;
                    document.getElementsByName("FormTimKiemApDung")[0].submit();
                }
            }

        </script>
    </head>
    <body>
        <div id="website-admin">
            <jsp:include page="../MainMenu.jsp" />

            <!-- Phần Content của trang Admin-->
            <div id="content-admin">
                <div class="page-caption">Tìm kiếm áp dụng</div>
                <center><h3><s:property value="msg"></s:property></h3></center>
                    <div style="alignment-adjust: central; margin-top: 30px; border-top:2px solid gainsboro">
                    <s:form action="TimKiemApDung" method="post" name="FormTimKiemApDung" id="FormTimKiemApDung" theme="simple">
                        <s:hidden name="selectedPage" value="1"></s:hidden>
                            <table>
                                <tr>
                                    <td>Mã sản phẩm</td>
                                    <td><s:textfield name="maSanPham" label=""></s:textfield></td>
                                    <td>Tên sản phẩm</td>
                                    <td><s:textfield name="tenSanPham" label=""></s:textfield></td>
                                <td><s:submit value="Xem danh sách" align="left"></s:submit></td>
                                </tr>
                            </table>
                            <table class="table1">
                                <tr>
                                    <th>Mã sản phẩm</th>
                                    <th>Tên</th>
                                    <th>Mô tả</th>
                                    <th>Nhà Cung cấp</th>
                                    <th>Xuất xứ</th>
                                    <th>Dòng xe</th>
                                    <th>Loại xe</th>
                                    <th>Hãng xe </th>
                                    <th></th>
                                </tr>
                            <%int i = 0;%>
                            <s:iterator var="i" step="1" value="dsSanPhamDongXeDTO">
                                <tr>
                                    <td><s:property value="MaSanPham"/></td>
                                    <td><s:a href="./XemApDung?&maSanPham=%{maSanPham}&selectedPage=1" cssStyle="font-weight: bold;" ><s:property value="TenSanPham"/></s:a></td>                                    
                                    <td><s:property value="MoTaSanPham"/></td>
                                    <td><s:property value="TenNhaCC"/></td>
                                    <td><s:property value="NuocSanXuat"/></td>
                                    <td><s:property value="TenDongXe"/></td>
                                    <td><s:property value="TenLoaiXe"/></td>
                                    <td><s:property value="TenHangXe"/></td>
                                    <td><input type="checkbox" name="ApDungCheckBox" value="<s:property value='MaSanPham'/>:<s:property value='MaDongXe'/>"></td>
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
                                        <s:a action="TimKiemApDung?selectedPage=%{pageID}&maSanPham=%{maSanPham}&tenSanPham=%{tenSanPham}"><%=i%>
                                        </s:a>&nbsp&nbsp
                                    </span>
                                </s:else>
                                <%i++;%>
                            </s:iterator>
                        </span>
                        <br><br>
                        <input type="button" name="XoaApDungButton" value="Xóa áp dụng đã chọn" onclick="XoaApDung()">
                    </s:form>
                </div>
            </div>
        </div>
    </body>
</html>
