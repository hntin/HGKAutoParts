<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản lý hãng xe</title>
        <link rel="stylesheet" type="text/css" href="./css/admin/admin-style.css">
        <script type="text/javascript">
            function TaoMoiHangXe() {
                c = confirm("Thêm mới hãng xe ?");
                if (c == true) {
                    document.getElementsByName("FormQuanLyHangXe")[0].action = "./GoTaoMoiHangXe";
                    document.getElementsByName("FormQuanLyHangXe")[0].submit();
                }

            }
            function XoaHangXe() {
                var c1 = confirm("Bạn muốn xóa hãng xe?");
                if (c1 == true) {
                    var checkboxes = document.getElementsByName("HangXeCheckBox");
                    var checkboxesChecked = [];
                    // loop over them all
                    for (var i = 0; i < checkboxes.length; i++) {
                        // And stick the checked ones onto an array...
                        if (checkboxes[i].checked) {
                            checkboxesChecked.push(checkboxes[i].value);
                        }
                    }
                    var c2 = confirm("Danh sách hãng xe sẽ xóa: " + checkboxesChecked);
                    if (c2 == true) {
                        document.getElementsByName("FormQuanLyHangXe")[0].action = "./XoaHangXe?CheckedList=" + checkboxesChecked;
                        document.getElementsByName("FormQuanLyHangXe")[0].submit();
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
                <div class="page-caption">Danh sách hãng xe</div>
                <div style="alignment-adjust: central; margin-top: 30px">
                    <s:form action="QuanLyHangXe" method="post" name="FormQuanLyHangXe" id="FormQuanLyHangXe" theme="simple">
                        <s:hidden name="selectedPage" value="1"></s:hidden>
                        <table>
                            <tr>
                                <td>Mã hãng xe</td>
                                <td><s:textfield name="maHangXe" label=""></s:textfield></td>
                                <td>Tên hãng xe</td>
                                <td><s:textfield name="tenHangXe" label=""></s:textfield></td>
                                <td><s:submit value="Tìm kiếm" align="left"></s:submit></td>
                            </tr>
                        </table>
                        <s:set name="var1" value="selectedPage"/>
                        <s:set name="var2" value="maHangXe"/>
                        <s:set name="var3" value="tenHangXe"/>
                            <br>
                            <h3><s:property value="msg"></s:property></h3>
                            <table class="table1">
                                <tr>
                                    <th>STT</th>
                                    <th>Mã hãng xe</th>
                                    <th>Tên hãng xe</th>
                                    <th>Thông tin khác</th>                                    
                                    <th></th>
                                </tr>
                            <%int i = 0;%>
                            <s:iterator var="i" step="1" value="dsHangXeDTO">
                                <tr>
                                    <td><%=++i%></td>
                                    <td><s:a href="./GoCapNhatHangXe?maHangXe=%{MaHangXe}">
                                            <s:property value="MaHangXe"/></s:a></td>
                                    <td><s:a href="./GoCapNhatHangXe?maHangXe=%{MaHangXe}">
                                            <s:property value="TenHangXe"/></s:a></td>
                                    <td><s:a href="./GoCapNhatHangXe?maHangXe=%{MaHangXe}"><s:property value="ghiChu"/></s:a></td>                                    
                                    <td><input type="checkbox" name="HangXeCheckBox" value='<s:property value="maHangXe"/>'></td>
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
                                        <s:a action="QuanLyHangXe?selectedPage=%{pageID}&maHangXe=%{var2}&tenHangXe=%{var3}"><%=i%>
                                        </s:a>&nbsp&nbsp
                                    </span>
                                </s:else>
                                <%i++;%>
                            </s:iterator>
                        </span>
                        <br><br>
                        <input type="button" name="ThemHangXeButton" value="Thêm mới" onclick="TaoMoiHangXe()">
                        <input type="button" name="XoaHangXeButton" value="Xóa" onclick="XoaHangXe()">
                    </s:form>
                </div>
            </div>
        </div>
    </body>
</html>
