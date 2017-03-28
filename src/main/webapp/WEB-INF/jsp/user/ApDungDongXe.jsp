<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Web E-Commerce</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="./css/user/style.css"/>
        <link rel="stylesheet" href="./css/user/flexslider.css" type="text/css" media="screen" />
    </head>
    <body>
        <div id="website">
            <jsp:include page="KhungBenTren.jsp" /> 
            <jsp:include page="KhungQuangCao.jsp" />

            <!-- Phần Main -->
            <div id="main">
                <jsp:include page="KhungBenTrai.jsp" /> 
                <div id="main-right">
                    <div id="car-info">
                        <table class="table1">
                            <tr>
                                <th>Hãng xe</th>
                                <th>Loại xe</th>
                                <th>Dòng xe</th>
                                <th>Số Xy-Lanh</th>
                                <th>Động cơ</th>
                                <th>Hộp số</th>
                                <th>Số cửa</th>
                                <th>Nhiên liệu</th>
                                <th>Đời</th>
                            </tr>
                            <tr>
                                <td><s:property value="%{dxDTO.TenHangXe}"/></td>
                                <td><s:property value="%{dxDTO.TenLoaiXe}"/></td>
                                <td><s:property value="%{dxDTO.TenDongXe}"/></td>
                                <td><s:property value="%{dxDTO.SoXyLanh}"/></td>
                                <td><s:property value="%{dxDTO.DongCo}"/></td>
                                <td><s:property value="%{dxDTO.HopSo}"/></td>
                                <td><s:property value="%{dxDTO.SoCua}"/></td>
                                <td><s:property value="%{dxDTO.NhienLieu}"/></td>
                                <td><s:property value="%{dxDTO.NamSX}"/></td>
                            </tr>                            
                        </table>
                    </div>
                    <h3 class="page-caption">Danh mục sản phẩm tương thích</h3>
                    <div id="products">
                        <ul>
                            <s:iterator var="sanphamDTO" step="1" value="dsSanPhamTuongThichDTO">
                                <s:set var="gia" value="%{Gia}"></s:set>
                                    <li>
                                        <div class="content">
                                            <a href="./XemChiTietSanPham?maSanPham=<s:property value='MaSanPham'/>">
                                                <img src="<s:property value="%{HinhDaiDien}"/>" width="210" height="250"/>
                                            <p class="title"><s:property value="%{TenSanPham}"/></p>
                                        </a>
                                        <s:if test="#gia == 0">
                                            <p class="sanpham_gia"><span>Giá: Liên hệ</span></p>
                                        </s:if>
                                        <s:else>
                                            <p class="sanpham_gia"><span>Giá: <s:property value="%{Gia}"/> (VNĐ)</span></p>
                                        </s:else>
                                    </div>
                                </li>                            
                            </s:iterator>
                        </ul>
                    </div>
                </div>
            </div>
            <jsp:include page="KhungBenDuoi.jsp" />
        </div>
        <div class="clr"></div>
    </body>
</html>
