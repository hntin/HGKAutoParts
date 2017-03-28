<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tìm kiếm sản phẩm</title>
        <link rel="stylesheet" type="text/css" href="./css/admin/admin-style.css">
        <script type="text/javascript">
            function TaoMoiSP() {
                c = confirm("Thêm mới sản phẩm?");
                if (c == true) {
                    document.getElementsByName("FormTimKiemSP")[0].action = "./GoTaoMoiSanPham";
                    document.getElementsByName("FormTimKiemSP")[0].submit();
                }
            }            
        </script>
    </head>
    <body>
        <div id="website-admin">
            <jsp:include page="../MainMenu.jsp" />

            <!-- Phần Content của trang Admin-->
            <div id="content-admin">
                <div class="page-caption">Tìm kiếm sản phẩm</div>
                <div style="alignment-adjust: central; margin-top: 30px">
                    <s:form action="TimKiemSanPham" method="post" name="FormTimKiemSP" id="FormTimKiemSP" theme="simple">
                        <s:hidden name="selectedPage" value="1"></s:hidden>
                        <table>
                            <tr>
                                <td>Tên sản phẩm</td>
                                <td><s:textfield name="tenSanPham" label="Tên sản phẩm"></s:textfield></td>
                                <td><s:submit value="Tìm kiếm" align="left"></s:submit></td>
                            </tr>
                        </table>
                            <br>
                            <h3><s:property value="msg"></s:property></h3>
                            <table class="table1">
                                <tr>
                                    <th>STT</th>
                                    <th>Loại sản phẩm</th>
                                    <th>Mã sản phẩm</th>
                                    <th>Tên sản phẩm</th>
                                    <th>Mô tả</th>
                                    <th>Hãng sản xuất</th>
                                    <th>Xuất xứ</th>
                                    <th>Giá (VNĐ)</th>
                                    <th>Tình trạng</th>
                                    <th>Hình đại diện</th>
                                </tr>
                            <%int i = 0;%>
                            <s:iterator var="i" step="1" value="dsSanPhamDTO">
                                <tr>
                                    <td><s:a href="GoCapNhatSanPham?maSanPham=%{MaSanPham}"><%=++i%></s:a></td>
                                    <td><s:a href="GoCapNhatSanPham?maSanPham=%{MaSanPham}"><s:property value="tenLoaiSanPham"/></s:a></td>
                                    <td><s:a href="GoCapNhatSanPham?maSanPham=%{MaSanPham}"><s:property value="maSanPham"/></s:a></td>
                                    <td><s:a href="GoCapNhatSanPham?maSanPham=%{MaSanPham}"><s:property value="tenSanPham"/></s:a></td>
                                    <td><s:a href="GoCapNhatSanPham?maSanPham=%{MaSanPham}"><s:property value="moTaSanPham"/></s:a></td>
                                    <td><s:a href="GoCapNhatSanPham?maSanPham=%{MaSanPham}"><s:property value="tenNhaCC"/></s:a></td>
                                    <td><s:a href="GoCapNhatSanPham?maSanPham=%{MaSanPham}"><s:property value="tenNuocSanXuat"/></s:a></td>  
                                    <td><s:a href="GoCapNhatSanPham?maSanPham=%{MaSanPham}"><s:property value="gia"/></s:a></td>  
                                    <td><s:a href="GoCapNhatSanPham?maSanPham=%{MaSanPham}"><s:property value="tenTinhTrang"/></s:a></td>  
                                    <td><s:a href="GoCapNhatSanPham?maSanPham=%{MaSanPham}"><img src='<s:property value="hinhDaiDien"/>' style="width: 20px; height: 20px"></s:a></td>  
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
                                        <s:a action="TimKiemSanPham?selectedPage=%{pageID}"><%=i%></s:a>&nbsp&nbsp
                                    </span>
                                </s:else>
                                <%i++;%>
                            </s:iterator>
                        </span>
                        <br><br>
                        <input type="button" name="ThemSPButton" value="Thêm mới" onclick="TaoMoiSP()">
                    </s:form>
                </div>
            </div>
        </div>
    </body>
</html>
