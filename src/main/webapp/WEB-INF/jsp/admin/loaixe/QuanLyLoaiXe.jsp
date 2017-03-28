<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản lý loại xe</title>
        <link rel="stylesheet" type="text/css" href="./css/admin/admin-style.css">
        <script type="text/javascript">
            function TaoMoiLoaiXe() {
                c = confirm("Thêm mới loại xe ?");
                if (c == true) {
                    document.getElementsByName("FormQuanLyLoaiXe")[0].action = "./GoTaoMoiLoaiXe";
                    document.getElementsByName("FormQuanLyLoaiXe")[0].submit();
                }

            }
            function XoaLoaiXe() {
                var c1 = confirm("Bạn muốn xóa loại xe?");
                if (c1 == true) {
                    var checkboxes = document.getElementsByName("LoaiXeCheckBox");
                    var checkboxesChecked = [];
                    // loop over them all
                    for (var i = 0; i < checkboxes.length; i++) {
                        // And stick the checked ones onto an array...
                        if (checkboxes[i].checked) {
                            checkboxesChecked.push(checkboxes[i].value);
                        }
                    }
                    var c2 = confirm("Danh sách loại xe sẽ xóa: " + checkboxesChecked);
                    if (c2 == true) {
                        document.getElementsByName("FormQuanLyLoaiXe")[0].action = "./XoaLoaiXe?CheckedList=" + checkboxesChecked;
                        document.getElementsByName("FormQuanLyLoaiXe")[0].submit();
                    }

                }
            }

            function OnChangeHangXe() {
                document.getElementsByName("FormQuanLyLoaiXe")[0].action = "QuanLyLoaiXe";
                document.getElementsByName("FormQuanLyLoaiXe")[0].submit();
            }
        </script>
    </head>
    <body>
        <div id="website-admin">
            <jsp:include page="../MainMenu.jsp" />

            <!-- Phần Content của trang Admin-->
            <div id="content-admin">
                <div class="page-caption">Danh sách loại xe</div>
                <div style="alignment-adjust: central; margin-top: 30px">
                    <s:form action="QuanLyLoaiXe" method="post" name="FormQuanLyLoaiXe" id="FormQuanLyLoaiXe" theme="simple">
                        <s:hidden name="selectedPage" value="1"></s:hidden>
                        <s:set name="var1" value="selectedPage"/>

                        <table>
                                <tr>
                                    <td>Chọn hãng xe</td>
                                    <td>
                                    <s:select list="%{dsHangXeHM}" name="selectedHangXe" onchange="OnChangeHangXe()"/>
                                    <s:set name="var2" value="selectedHangXe"/>
                                </td>                                                                
                                <td><input type="submit" value="Lọc"></td>                                
                            </tr>    
                        </table>                        

                        <br>
                        <h3><s:property value="msg"></s:property></h3>
                            <table class="table1">
                                <tr>
                                    <th>STT</th>
                                    <th>Hãng xe </th>  
                                    <th>Mã loại xe</th>
                                    <th>Tên loại xe</th>

                                    <th></th>
                                </tr>
                            <%int i = 0;%>
                            <s:iterator var="i" step="1" value="dsLoaiXeDTO">
                                <tr>
                                    <td><%=++i%></td>
                                    <td><s:a href="./GoCapNhatLoaiXe?maLoaiXe=%{MaLoaiXe}"><s:property value="tenHangXe"/></s:a></td>
                                    <td><s:a href="./GoCapNhatLoaiXe?maLoaiXe=%{MaLoaiXe}">
                                            <s:property value="MaLoaiXe"/></s:a></td>
                                    <td><s:a href="./GoCapNhatLoaiXe?maLoaiXe=%{MaLoaiXe}">
                                            <s:property value="TenLoaiXe"/></s:a></td>
                                        <td><input type="checkbox" name="LoaiXeCheckBox" 
                                                   value="<s:property value="MaLoaiXe"/>"></td>
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
                                        <s:a action="QuanLyLoaiXe?selectedPage=%{pageID}&selectedHangXe=%{var2}"><%=i%>
                                        </s:a>&nbsp&nbsp
                                    </span>
                                </s:else>
                                <%i++;%>
                            </s:iterator>
                        </span>
                        <br><br>
                        <input type="button" name="ThemLoaiXeButton" value="Thêm mới" onclick="TaoMoiLoaiXe()">
                        <input type="button" name="XoaLoaiXeButton" value="Xóa" onclick="XoaLoaiXe()">
                    </s:form>
                </div>
            </div>
        </div>
    </body>
</html>
