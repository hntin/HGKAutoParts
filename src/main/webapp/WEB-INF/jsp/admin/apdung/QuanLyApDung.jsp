<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản lý Áp dụng</title>
        <link rel="stylesheet" type="text/css" href="./css/admin/admin-style.css">
        <script type="text/javascript">
            function OnChangeHienThiSanPham() {
                document.getElementsByName("FormQuanLyApDung")[0].action = "HienThiSanPham";
                document.getElementsByName("FormQuanLyApDung")[0].submit();
            }
            
            function OnChangeXemApDung() {
                document.getElementsByName("FormQuanLyApDung")[0].action = "QuanLyApDung";
                document.getElementsByName("FormQuanLyApDung")[0].submit();
            }

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
                    document.getElementsByName("FormQuanLyApDung")[0].action = "./XoaApDung?CheckedList=" + checkboxesChecked;
                    document.getElementsByName("FormQuanLyApDung")[0].submit();
                }
            }

        </script>
    </head>
    <body>
        <div id="website-admin">
            <jsp:include page="../MainMenu.jsp" />

            <!-- Phần Content của trang Admin-->
            <div id="content-admin">
                <div class="page-caption">Danh sách áp dụng</div>
                <center><h3><s:property value="msg"></s:property></h3></center>
                    <div style="alignment-adjust: central; margin-top: 30px; border-top:2px solid gainsboro">
                    <s:form action="QuanLyApDung" method="post" name="FormQuanLyApDung" id="FormQuanLyApDung" theme="simple">
                        <s:hidden name="selectedPage" value="1"></s:hidden>
                            <table>
                                <tr>
                                    <td>Loại sản phẩm</td>
                                    <td><s:select list="%{loaiSanPhamHM}" name="selectedLoaiSP" onchange="OnChangeHienThiSanPham()"/></td>
                                <td>Hãng sản xuất</td>
                                <td><s:select list="%{hangSanXuatHM}" name="selectedHangSanXuat" onchange="OnChangeHienThiSanPham()"/></td>
                                <td>Nơi sản xuất</td>
                                <td><s:select list="%{noiSanXuatHM}" name="selectedNoiSanXuat" onchange="OnChangeHienThiSanPham()"/></td>
                                <td>Sản phẩm</td>
                                <td><s:select list="%{dsSanPhamHM}" name="selectedMaSanPham" id="selectedMaSanPham" onchange="OnChangeXemApDung()"/></td>
                                <td><s:submit value="Xem áp dụng" align="left"></s:submit></td>
                                </tr>
                            </table>
                            <br>
                            <label style="font-weight: bold; color: brown; margin-left: 10px; margin-top: 30px;">
                                Danh sách áp dụng tương ứng: 
                            </label>
                            <table class="table1">
                                <tr>
                                    <th>Sản phẩm</th>
                                    <th>Nhà Cung cấp</th>
                                    <th>Xuất xứ</th>
                                    <th>Hãng xe</th>
                                    <th>Loại xe</th>
                                    <th>Dòng xe</th>
                                    <th>Số cửa</th>
                                    <th>Số xy-lanh</th>
                                    <th>Động cơ</th>
                                    <th>Hộp số</th>
                                    <th>Nhiên liệu</th>
                                    <th>Năm sản xuất</th>
                                    <th></th>
                                </tr>
                            <%int i = 0;%>
                            <s:iterator var="i" step="1" value="dsSanPhamDongXeDTO">
                                <tr>
                                    <td><s:a href="./XemApDung?&maSanPham=%{maSanPham}&selectedPage=1" cssStyle="font-weight: bold;" ><s:property value="TenSanPham"/></s:a></td>                                    
                                    <td><s:property value="TenNhaCC"/></td>
                                    <td><s:property value="NuocSanXuat"/></td>
                                    <td><s:property value="TenHangXe"/></td>
                                    <td><s:property value="TenLoaiXe"/></td>
                                    <td><s:property value="TenDongXe"/></td>
                                    <td><s:property value="SoCua"/></td>
                                    <td><s:property value="SoXyLanh"/></td>
                                    <td><s:property value="DongCo"/></td>
                                    <td><s:property value="HopSo"/></td>
                                    <td><s:property value="NhienLieu"/></td>
                                    <td><s:property value="NamSX"/></td>
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
                                        <s:a action="QuanLyApDung?selectedPage=%{pageID}&maSanPham=%{maSanPham}&tenSanPham=%{tenSanPham}"><%=i%>
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
