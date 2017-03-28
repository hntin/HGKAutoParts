<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cập nhật Áp dụng</title>
        <link rel="stylesheet" type="text/css" href="./css/admin/admin-style.css">
        <script type="text/javascript">
            function OnSelectHangXe() {
                document.getElementsByName("FormThemMoiApDung")[0].action = "GoTaoMoiApDung";
                document.getElementsByName("FormThemMoiApDung")[0].submit();
            }

            function OnChangeGoTaoMoiApDung() {
                document.getElementsByName("FormThemMoiApDung")[0].action = "GoTaoMoiApDung";
                document.getElementsByName("FormThemMoiApDung")[0].submit();
            }

            function OnThemMoiApDung() {
                var spComboBox = document.getElementById("selectedMaSanPham");
                if (spComboBox.value == 0) {
                    alert("Hãy chọn sản phẩm để thêm áp dụng");
                }
                else {
                    document.getElementsByName("FormThemMoiApDung")[0].action = "TaoMoiApDung";
                    document.getElementsByName("FormThemMoiApDung")[0].submit();
                }
            }
        </script>
    </head>
    <body>
        <div id="website-admin">
            <jsp:include page="../MainMenu.jsp" />

            <!-- Phần Content của trang Admin-->
            <div id="content-admin">
                <div class="page-caption">Thêm mới áp dụng (Chọn sản phẩm và dòng xe tương ứng)</div><br><br>
                <center><h3><s:property value="msg"></s:property></h3><br></center>
                <label style="font-weight: bold; color: brown; margin-left: 10px; margin-top: 30px;">Mã sản phẩm: </label><label style="color: blue; font-weight: bold"><s:property value='sanphamDTO.MaSanPham'/></label>
                <label style="font-weight: bold; color: brown; margin-left: 10px; margin-top: 30px;">Tên sản phẩm: </label><label style="color: blue; font-weight: bold"><s:property value='sanphamDTO.TenSanPham'/></label>
                <label style="font-weight: bold; color: brown; margin-left: 10px; margin-top: 30px;">Mô tả sản phẩm: </label><label style="color: blue; font-weight: bold"><s:property value='sanphamDTO.MoTaSanPham'/></label>
                <br>
                <div style="alignment-adjust: central; margin-top: 30px; border-top:2px solid gainsboro"><br>
                    <s:form action="TaoMoiApDung" method="post" name="FormThemMoiApDung" id="FormThemMoiApDung" theme="simple">
                        <s:hidden name="selectedPage" value="1"></s:hidden>
                            <table>
                                <tr>
                                    <td>Loại sản phẩm</td>
                                    <td><s:select list="%{loaiSanPhamHM}" name="selectedLoaiSP" onchange="OnChangeGoTaoMoiApDung()"/></td>
                                <td>Hãng sản xuất</td>
                                <td><s:select list="%{hangSanXuatHM}" name="selectedHangSanXuat" onchange="OnChangeGoTaoMoiApDung()"/></td>
                                <td>Nơi sản xuất</td>
                                <td><s:select list="%{noiSanXuatHM}" name="selectedNoiSanXuat" onchange="OnChangeGoTaoMoiApDung()"/></td>
                                <td>Sản phẩm</td>
                                <td><s:select list="%{dsSanPhamHM}" name="selectedMaSanPham" id="selectedMaSanPham" onchange="OnChangeGoTaoMoiApDung()"/></td>
                            </tr>
                        </table>
                        <br>
                        <label style="font-weight: bold; color: brown; margin-left: 10px; margin-top: 30px;">Danh sách dòng xe chưa áp dụng. Chọn để thêm mới </label>
                        <s:select label="Chọn hãng xe" list="%{dsHangXeHM}" name="selectedHangXe" id="selectedHangXe" onchange="OnSelectHangXe()" ></s:select>
                            <table class="table1">
                                <tr>
                                    <th>Hãng xe</th>
                                    <th>Loại xe</th>
                                    <th>Dòng xe</th>
                                    <th>Số Xy-Lanh</th>
                                    <th>Dung tích động cơ</th>
                                    <th>Hộp số</th>
                                    <th>Số cửa</th>
                                    <th>Nhiên liệu</th>
                                    <th>Năm sản xuất</th>
                                    <th></th>
                                </tr>
                            <%int i = 0;%>
                            <s:iterator var="i" step="1" value="dsDongXeDTO">
                                <tr>
                                    <td><s:property value="TenHangXe"/></td>
                                    <td><s:property value="TenLoaiXe"/></td>
                                    <td><s:property value="TenDongXe"/></td>                                    
                                    <td><s:property value="SoXyLanh"/></td>
                                    <td><s:property value="DongCo"/></td>
                                    <td><s:property value="HopSo"/></td>
                                    <td><s:property value="SoCua"/></td>
                                    <td><s:property value="NhienLieu"/></td>
                                    <td><s:property value="NamSX"/></td>
                                    <td><input type="checkbox" name="apDungCheckBox" value="<s:property value='MaDongXe'/>"></td>
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
                                        <s:a action="GoTaoMoiApDung?selectedPage=%{pageID}&maSanPham=%{sanphamDTO.MaSanPham}&selectedHangXe=%{selectedHangXe}"><%=i%>
                                        </s:a>&nbsp&nbsp
                                    </span>
                                </s:else>
                                <%i++;%>
                            </s:iterator>
                        </span>
                        <br><br>
                        <input type="button" value="Thêm mới áp dụng đã chọn" onclick="OnThemMoiApDung()">
                    </s:form>
                </div>
            </div>
        </div>
    </body>
</html>
