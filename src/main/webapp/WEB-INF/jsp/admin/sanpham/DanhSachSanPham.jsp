<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản lý sản phẩm</title>
        <link rel="stylesheet" type="text/css" href="./css/admin/admin-style.css">
        <script type="text/javascript">
            function TaoMoiSP() {
                c = confirm("Thêm mới sản phẩm?");
                if (c == true) {
                    document.getElementsByName("FormDanhSachSP")[0].action = "./GoTaoMoiSanPham";
                    document.getElementsByName("FormDanhSachSP")[0].submit();
                }

            }
            function XoaSP() {
                var c1 = confirm("Bạn muốn xóa sản phẩm?");
                if (c1 == true) {
                    var checkboxes = document.getElementsByName("SPCheckBox");
                    var checkboxesChecked = [];
                    // loop over them all
                    for (var i = 0; i < checkboxes.length; i++) {
                        // And stick the checked ones onto an array...
                        if (checkboxes[i].checked) {
                            checkboxesChecked.push(checkboxes[i].value);
                        }
                    }
                    var c2 = confirm("Danh sách sản phẩm sẽ xóa: " + checkboxesChecked);
                    if (c2 == true) {
                        document.getElementsByName("FormDanhSachSP")[0].action = "./XoaSanPham?CheckedList=" + checkboxesChecked;
                        document.getElementsByName("FormDanhSachSP")[0].submit();
                    }

                }
            }
            function OnClickProduct(maSanPham) {
                document.getElementsByName("FormDanhSachSP")[0].action = "GoCapNhatSanPham?maSanPham=" + maSanPham;
                document.getElementsByName("FormDanhSachSP")[0].submit();
            }
        </script>
    </head>
    <body>
        <div id="website-admin">
            <jsp:include page="../MainMenu.jsp" />

            <!-- Phần Content của trang Admin-->
            <div id="content-admin">
                <div class="page-caption">Danh sách sản phẩm</div>
                <div style="alignment-adjust: central; margin-top: 30px">
                    <h3><s:property value="msg"></s:property></h3>
                    <s:form action="LocSanPham" method="post" name="FormDanhSachSP" id="FormDanhSachSP" theme="simple">
                        <s:hidden name="selectedPage" value="1"></s:hidden>
                            <table>
                                <tr>
                                    <td>Hãng sản xuất</td>
                                    <td><s:select list="%{hangSanXuatHM}" name="selectedHangSanXuat"/></td>
                                <td>Nơi sản xuất</td>
                                <td><s:select list="%{noiSanXuatHM}" name="selectedNoiSanXuat"/></td>
                                <td>Loại sản phẩm</td>
                                <td><s:select list="%{loaiSanPhamHM}" name="selectedLoaiSP"/></td>
                                <td>Tình trạng</td>
                                <td><s:select list="%{tinhTrangHM}" name="selectedTinhTrangSP"/></td>
                                <td><s:submit value="Lọc sản phẩm" align="left"></s:submit></td>
                                </tr>
                            </table>
                            <br>
                            <table class="table1">
                                <tr>
                                    <th>STT</th>
                                    <th>Loại sản phẩm</th>
                                    <th>Tên sản phẩm</th>
                                    <th>Mô tả</th>
                                    <th>Hãng sản xuất</th>
                                    <th>Xuất xứ</th>
                                    <th>Giá (VNĐ)</th>
                                    <th>Tình trạng</th>
                                    <th>Hình đại diện</th>
                                    <th></th>
                                </tr>
                            <%int i = 0;%>
                            <s:iterator var="i" step="1" value="dsSanPhamDTO">
                                <tr>
                                    <td><s:a href="GoCapNhatSanPham?maSanPham=%{MaSanPham}"><%=++i%></s:a></td>
                                    <td><s:a href="GoCapNhatSanPham?maSanPham=%{MaSanPham}"><s:property value="tenLoaiSanPham"/></s:a></td>
                                    <td><s:a href="GoCapNhatSanPham?maSanPham=%{MaSanPham}"><s:property value="tenSanPham"/></s:a></td>
                                    <td><s:a href="GoCapNhatSanPham?maSanPham=%{MaSanPham}"><s:property value="moTaSanPham"/></s:a></td>
                                    <td><s:a href="GoCapNhatSanPham?maSanPham=%{MaSanPham}"><s:property value="tenNhaCC"/></s:a></td>
                                    <td><s:a href="GoCapNhatSanPham?maSanPham=%{MaSanPham}"><s:property value="tenNuocSanXuat"/></s:a></td>  
                                    <td><s:a href="GoCapNhatSanPham?maSanPham=%{MaSanPham}"><s:property value="gia"/></s:a></td>  
                                    <td><s:a href="GoCapNhatSanPham?maSanPham=%{MaSanPham}"><s:property value="tenTinhTrang"/></s:a></td>  
                                        <td><s:a href="GoCapNhatSanPham?maSanPham=%{MaSanPham}"><img src='<s:property value="hinhDaiDien"/>' style="width: 20px; height: 20px"></s:a></td>  
                                    <td><input type="checkbox" name="SPCheckBox" value='<s:property value="maSanPham"/>'></td>
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
                                        <s:a action="LocSanPham?selectedPage=%{pageID}">
                                            <%=i%>
                                        </s:a>&nbsp&nbsp
                                    </span>
                                </s:else>
                                <%i++;%>
                            </s:iterator>
                        </span>
                        <br><br>
                        <input type="button" name="ThemSPButton" value="Thêm mới" onclick="TaoMoiSP()">
                        <input type="button" name="XoaSPButton" value="Xóa" onclick="XoaSP()">
                    </s:form>
                </div>
            </div>
        </div>
    </body>
</html>
