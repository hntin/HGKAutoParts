<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản lý dòng xe</title>
        <link rel="stylesheet" type="text/css" href="./css/admin/admin-style.css">
        <script type="text/javascript">
            function TaoMoiDongXe() {
                c = confirm("Thêm mới dòng xe?");
                if (c == true) {
                    document.getElementsByName("FormQuanLyDongXe")[0].action = "./GoTaoMoiDongXe";
                    document.getElementsByName("FormQuanLyDongXe")[0].submit();
                }

            }
            function XoaDongXe() {
                var c1 = confirm("Bạn muốn xóa dòng xe?");
                if (c1 == true) {
                    var checkboxes = document.getElementsByName("DongXeCheckBox");
                    var checkboxesChecked = [];
                    // loop over them all
                    for (var i = 0; i < checkboxes.length; i++) {
                        // And stick the checked ones onto an array...
                        if (checkboxes[i].checked) {
                            checkboxesChecked.push(checkboxes[i].value);
                        }
                    }
                    var c2 = confirm("Danh sách dòng xe sẽ xóa: " + checkboxesChecked);
                    if (c2 == true) {
                        document.getElementsByName("FormQuanLyDongXe")[0].action = "./XoaDongXe?CheckedList=" + checkboxesChecked;
                        document.getElementsByName("FormQuanLyDongXe")[0].submit();
                    }

                }
            }

            function OnChangeHangXe() {
                document.getElementsByName("FormQuanLyDongXe")[0].action = "QuanLyDongXe";
                document.getElementsByName("FormQuanLyDongXe")[0].submit();
            }

            function OnChangeLoaiXe() {
                document.getElementsByName("FormQuanLyDongXe")[0].action = "QuanLyDongXe";
                document.getElementsByName("FormQuanLyDongXe")[0].submit();
            }
        </script>
    </head>
    <body>
        <div id="website-admin">
            <jsp:include page="../MainMenu.jsp" />

            <!-- Phần Content của trang Admin-->
            <div id="content-admin">
                <div class="page-caption">Danh sách dòng xe</div>
                <div style="alignment-adjust: central; margin-top: 30px">
                    <s:form action="QuanLyDongXe" method="post" name="FormQuanLyDongXe" id="FormQuanLyDongXe" theme="simple">
                        <s:hidden name="selectedPage" value="1"></s:hidden>
                            <table>
                                <tr>
                                    <td>Chọn hãng xe</td>
                                    <td>
                                    <s:select list="%{dsHangXeHM}" name="selectedHangXe" onchange="OnChangeHangXe()"/>
                                    <s:set name="var1" value="selectedHangXe"/>
                                </td>
                                <td>Chọn loại xe</td>
                                <td>
                                    <s:if test="#var1 == 0">
                                        <s:select list="%{dsLoaiXeHM}" name="selectedLoaiXe" 
                                                  onchange="OnChangeLoaiXe()" disabled="true"/>
                                    </s:if>
                                    <s:else>
                                        <s:select list="%{dsLoaiXeHM}" name="selectedLoaiXe" 
                                                  onchange="OnChangeLoaiXe()" disabled="false"/>
                                    </s:else>
                                </td>
                                <td><input type="submit" value="Lọc"></td>                                
                            </tr>    
                        </table>

                        <s:set name="var1" value="selectedPage"/>
                        <s:set name="var2" value="selectedHangXe"/>
                        <br>
                        <h3><s:property value="msg"></s:property></h3>
                            <table class="table1">
                                <tr>
                                    <th>STT</th>
                                    <th>Hãng xe</th>
                                    <th>Mã dòng xe</th>
                                    <th>Dòng xe</th>
                                    <th>Số Xy-lanh</th>
                                    <th>Dung tích</th>
                                    <th>Hộp số</th>
                                    <th>Số cửa</th>
                                    <th>Nhiên liệu</th>
                                    <th>Năm SX</th>
                                    <th></th>
                                </tr>
                            <%int i = 0;%>
                            <s:iterator var="i" step="1" value="dsDongXeDTO">
                                <tr>
                                    <td><s:a href="./GoCapNhatDongXe?maDongXe=%{MaDongXe}"><%=++i%></s:a></td>
                                    <td><s:a href="./GoCapNhatDongXe?maDongXe=%{MaDongXe}"><s:property value="TenHangXe"/></s:a></td>
                                    <td><s:a href="./GoCapNhatDongXe?maDongXe=%{MaDongXe}"><s:property value="MaDongXe"/></s:a></td>
                                    <td><s:a href="./GoCapNhatDongXe?maDongXe=%{MaDongXe}"><s:property value="TenDongXe"/></s:a></td>
                                    <td><s:a href="./GoCapNhatDongXe?maDongXe=%{MaDongXe}"><s:property value="SoXyLanh"/></s:a></td>
                                    <td><s:a href="./GoCapNhatDongXe?maDongXe=%{MaDongXe}"><s:property value="DongCo"/></s:a></td>
                                    <td><s:a href="./GoCapNhatDongXe?maDongXe=%{MaDongXe}"><s:property value="HopSo"/></s:a></td>
                                    <td><s:a href="./GoCapNhatDongXe?maDongXe=%{MaDongXe}"><s:property value="SoCua"/></s:a></td>
                                    <td><s:a href="./GoCapNhatDongXe?maDongXe=%{MaDongXe}"><s:property value="NhienLieu"/></s:a></td>
                                    <td><s:a href="./GoCapNhatDongXe?maDongXe=%{MaDongXe}"><s:property value="NamSX"/></s:a></td>
                                    <td><input type="checkbox" name="DongXeCheckBox" value='<s:property value="MaDongXe"/>'></td>
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
                                        <s:a action="QuanLyDongXe?selectedPage=%{pageID}&selectedHangXe=%{var2}"><%=i%>
                                        </s:a>&nbsp&nbsp
                                    </span>
                                </s:else>
                                <%i++;%>
                            </s:iterator>
                        </span>
                        <br><br>
                        <input type="button" name="ThemDongXeButton" value="Thêm mới dòng xe" onclick="TaoMoiDongXe()">
                        <input type="button" name="XoaDongXeButton" value="Xóa dòng xe" onclick="XoaDongXe()">
                    </s:form>
                </div>
            </div>
        </div>
    </body>
</html>
