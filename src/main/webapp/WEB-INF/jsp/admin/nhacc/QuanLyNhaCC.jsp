<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản lý nhà cung cấp</title>
        <link rel="stylesheet" type="text/css" href="./css/admin/admin-style.css">
        <script type="text/javascript">
            function TaoMoiNhaCC() {
                c = confirm("Thêm mới nhà cung cấp?");
                if (c == true) {
                    document.getElementsByName("FormQuanLyNhaCC")[0].action = "./GoTaoMoiNhaCC";
                    document.getElementsByName("FormQuanLyNhaCC")[0].submit();
                }

            }
            function XoaNhaCC() {
                var c1 = confirm("Bạn muốn xóa nhà cung cấp?");
                if (c1 == true) {
                    var checkboxes = document.getElementsByName("NhaCCCheckBox");
                    var checkboxesChecked = [];
                    // loop over them all
                    for (var i = 0; i < checkboxes.length; i++) {
                        // And stick the checked ones onto an array...
                        if (checkboxes[i].checked) {
                            checkboxesChecked.push(checkboxes[i].value);
                        }
                    }
                    var c2 = confirm("Danh sách nhà cung cấp sẽ xóa: " + checkboxesChecked);
                    if (c2 == true) {
                        document.getElementsByName("FormQuanLyNhaCC")[0].action = "./XoaNhaCC?CheckedList=" + checkboxesChecked;
                        document.getElementsByName("FormQuanLyNhaCC")[0].submit();
                    }

                }
            }
        </script>
    </head>
    <body>
        <div id="website-admin">
            <jsp:include page="../MainMenu.jsp" />

            <!-- Phần Content của trang Admin-->
            <div id="content-admin">
                <div class="page-caption">Danh sách nhà cung cấp</div>
                <div style="alignment-adjust: central; margin-top: 30px">
                    <s:form action="QuanLyNhaCC" method="post" name="FormQuanLyNhaCC" theme="simple">
                        <s:hidden name="selectedPage" value="1"></s:hidden>
                            <table>
                                <tr>
                                <td><s:submit value="Xem danh sách" align="left"></s:submit></td>
                                </tr>
                            </table>
                        <s:set name="var1" value="selectedPage"/>
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
                                    <th></th>
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
                                    <td><input type="checkbox" name="NhaCCCheckBox" value='<s:property value="MaNhaCC"/>'></td>
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
                                        <s:a action="QuanLyNhaCC?selectedPage=%{pageID}"><%=i%>
                                        </s:a>&nbsp&nbsp
                                    </span>
                                </s:else>
                                <%i++;%>
                            </s:iterator>
                        </span>
                        <br><br>
                        <input type="button" name="ThemNhaCCButton" value="Thêm mới" onclick="TaoMoiNhaCC()">
                        <input type="button" name="XoaNhaCCButton" value="Xóa" onclick="XoaNhaCC()">
                    </s:form>
                </div>
            </div>
        </div>
    </body>
</html>
