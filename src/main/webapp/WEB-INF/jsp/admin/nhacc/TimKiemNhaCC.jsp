<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tìm kiếm nhà cung cấp</title>
        <link rel="stylesheet" type="text/css" href="./css/admin/admin-style.css">
        <script type="text/javascript">
            function TaoMoiNhaCC() {
                c = confirm("Thêm mới nhà cung cấp?");
                if (c == true) {
                    document.getElementsByName("FormTimKiemNhaCC")[0].action = "./GoTaoMoiNhaCC";
                    document.getElementsByName("FormTimKiemNhaCC")[0].submit();
                }

            }
        </script>
    </head>
    <body>
        <div id="website-admin">
            <jsp:include page="../MainMenu.jsp" />

            <!-- Phần Content của trang Admin-->
            <div id="content-admin">
                <div class="page-caption">Tìm kiếm nhà cung cấp</div>
                <div style="alignment-adjust: central; margin-top: 30px">
                    <s:form action="TimKiemNhaCC" method="post" name="FormTimKiemNhaCC" theme="simple">
                        <s:hidden name="selectedPage" value="1"></s:hidden>
                            <table>
                                <tr>
                                    <td>Mã nhà cung cấp</td>
                                    <td><s:textfield name="searchMaNhaCC" label=""></s:textfield></td>
                                    <td>Tên nhà cung cấp</td>
                                    <td><s:textfield name="searchTenNhaCC" label=""></s:textfield></td>
                                <td><s:submit value="Tìm kiếm" align="left"></s:submit></td>
                                </tr>
                            </table>
                        <s:set name="var1" value="selectedPage"/>
                        <s:set name="var2" value="searchMaNhaCC"/>
                        <s:set name="var3" value="searchTenNhaCC"/>
                        <br>
                        <h3><s:property value="msg"></s:property></h3>
                            <table class="table1">
                                <tr>
                                    <th>STT</th>
                                    <th>Mã Nhà CC</th>
                                    <th>Tên Nhà CC</th>
                                    <th>Email</th>
                                    <th>Điện thoại</th>
                                    <th>Địa chỉ</th>
                                    <th>Thông tin khác</th>
                                </tr>
                            <%int i = 0;%>

                            <s:iterator var="i" step="1" value="dsNhaCCDTO">
                                <tr>
                                    <td><s:a href="./GoCapNhatNhaCC?maNhaCC=%{MaNhaCC}"><%=++i%></s:a></td>
                                    <td><s:a href="./GoCapNhatNhaCC?maNhaCC=%{MaNhaCC}"><s:property value="MaNhaCC"/></s:a></td>
                                    <td><s:a href="./GoCapNhatNhaCC?maNhaCC=%{MaNhaCC}"><s:property value="TenNhaCC"/></s:a></td>
                                    <td><s:a href="./GoCapNhatNhaCC?maNhaCC=%{MaNhaCC}"><s:property value="Email"/></s:a></td>
                                    <td><s:a href="./GoCapNhatNhaCC?maNhaCC=%{MaNhaCC}"><s:property value="DienThoai"/></s:a></td>
                                    <td><s:a href="./GoCapNhatNhaCC?maNhaCC=%{MaNhaCC}"><s:property value="DiaChi"/></s:a></td>
                                    <td><s:a href="./GoCapNhatNhaCC?maNhaCC=%{MaNhaCC}"><s:property value="GhiChu"/></s:a></td>
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
                                        <s:a action="TimKiemNhaCC?selectedPage=%{pageID}&searchMaNhaCC=%{var2}&searchTenNhaCC=%{var3}"><%=i%>
                                        </s:a>&nbsp&nbsp
                                    </span>
                                </s:else>
                                <%i++;%>
                            </s:iterator>
                        </span>
                        <br><br>
                        <input type="button" name="ThemNhaCCButton" value="Thêm mới" onclick="TaoMoiNhaCC()">
                    </s:form>
                </div>
            </div>
        </div>
    </body>
</html>
