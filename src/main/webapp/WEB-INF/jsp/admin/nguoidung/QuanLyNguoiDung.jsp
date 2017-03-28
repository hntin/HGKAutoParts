<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="hgksoft.acquy.dto.NguoiDungDTO"%>
<%@page import="hgksoft.acquy.constant.CommonConst" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản lý người dùng</title>
        <link rel="stylesheet" type="text/css" href="./css/admin/admin-style.css">
        <script type="text/javascript">
            function TaoMoiND() {
                c = confirm("Thêm mới người dùng?");
                if (c == true) {
                    document.getElementsByName("FormQuanLyND")[0].action = "./GoTaoMoiNguoiDung";
                    document.getElementsByName("FormQuanLyND")[0].submit();
                }

            }
            function XoaND() {
                var c1 = confirm("Bạn muốn xóa người dùng?");
                if (c1 == true) {
                    var checkboxes = document.getElementsByName("NguoiDungCheckBox");
                    var checkboxesChecked = [];
                    // loop over them all
                    for (var i = 0; i < checkboxes.length; i++) {
                        // And stick the checked ones onto an array...
                        if (checkboxes[i].checked) {
                            checkboxesChecked.push(checkboxes[i].value);
                        }
                    }
                    var c2 = confirm("Danh sách người dùng sẽ xóa: " + checkboxesChecked);
                    if (c2 == true) {
                        document.getElementsByName("FormQuanLyND")[0].action = "./XoaNguoiDung?CheckedList=" + checkboxesChecked;
                        document.getElementsByName("FormQuanLyND")[0].submit();
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
                <div class="page-caption">Danh sách người dùng</div>
                <div style="alignment-adjust: central; margin-top: 30px">
                    <s:form action="QuanLyNguoiDung" method="post" name="FormQuanLyND" id="FormQuanLyND" theme="simple">
                        <s:hidden name="selectedPage" value="1"></s:hidden>
                            <table>
                                <tr>
                                    <td>Loại người dùng</td>
                                    <td><s:select label="Loại người dùng" list="%{loaiNguoiDungHM}" name="selectedLoaiND"/></td>
                                <td><s:submit value="Xem" align="left"></s:submit></td>
                                </tr>
                            </table>
                        <s:set name="var1" value="selectedPage"/>
                        <s:set name="var2" value="selectedLoaiND"/>
                            <br>
                            <h3><s:property value="msg"></s:property></h3>
                            <table class="table1">
                                <tr>
                                    <th>STT</th>
                                    <th>Mã người dùng</th>
                                    <th>Họ và tên</th>
                                    <th>Mật khẩu</th>
                                    <th>Loại người dùng</th>
                                    <th></th>
                                </tr>
                            <%int i = 0;%>

                            <s:iterator var="i" step="1" value="dsNguoiDungDTO">
                                <tr>
                                    <td><s:a href="GoCapNhatNguoiDung?maNguoiDung=%{MaNguoiDung}"><%=++i%></s:a></td>
                                    <td><s:a href="GoCapNhatNguoiDung?maNguoiDung=%{MaNguoiDung}"><s:property value="maNguoiDung"/></s:a></td>
                                    <td><s:a href="GoCapNhatNguoiDung?maNguoiDung=%{MaNguoiDung}"><s:property value="tenNguoiDung"/></s:a></td>
                                    <td><s:a href="GoCapNhatNguoiDung?maNguoiDung=%{MaNguoiDung}"><s:property value="matKhau"/></s:a></td>
                                    <td><s:a href="GoCapNhatNguoiDung?maNguoiDung=%{MaNguoiDung}"><s:property value="maLoaiNguoiDung"/></s:a></td>
                                    <td><input type="checkbox" name="NguoiDungCheckBox" value='<s:property value="maNguoiDung"/>'></td>
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
                                        <s:a action="QuanLyNguoiDung?selectedPage=%{pageID}&selectedLoaiND=%{var2}"><%=i%>
                                        </s:a>&nbsp&nbsp
                                    </span>
                                </s:else>
                                <%i++;%>
                            </s:iterator>
                        </span>
                        <br><br>
                        <input type="button" name="ThemNguoiDungButton" value="Thêm mới" onclick="TaoMoiND()">
                        <input type="button" name="XoaNguoiDungButton" value="Xóa" onclick="XoaND()">
                    </s:form>
                </div>
            </div>
        </div>
    </body>
</html>
