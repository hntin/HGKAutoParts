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
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
        <script>window.jQuery || document.write('<script src="js/libs/jquery-1.7.min.js">\x3C/script>')</script>
    </head>
    <body>
        <div id="website">
            <jsp:include page="KhungBenTren.jsp" /> 
            <!-- Phần Main -->
            <div id="main">
                <jsp:include page="KhungBenTrai.jsp" />
                <div id="main-right">
                    <div id="product-image">
                        <a href="./XemChiTietSanPham">
                            <img src="<s:property value="%{sanphamDTO.HinhDaiDien}"/>" width="275" height="310"/>
                            <p class="title"><s:property value="%{sanphamDTO.TenSanPham}"/></p>
                            <s:set var="gia" value="%{sanphamDTO.Gia}"></s:set>
                            <s:if test="#gia == 0">
                                <p class="sanpham_gia"><span style="margin-left: 80px;">Giá: Liên hệ</span></p>
                            </s:if>
                            <s:else>
                                <p class="sanpham_gia"><span style="margin-left: 80px;">Giá: <s:property value="%{sanphamDTO.Gia}"/> (VNĐ)</span></p>
                            </s:else>
                        </a>
                    </div>
                    <div id="product-detail">
                        <div id="product-detail-upper">
                            <div style="text-align: left; padding-left: 20px; padding-top: 15px;">
                                <b>Mã sản phẩm:</b> 
                                <label style="font-weight: bold; color: red;">
                                    <s:property value="%{sanphamDTO.MaSanPham}"/></label>
                            </div>
                            <div style="text-align: left; padding-left: 20px; padding-top: 15px;">
                                <b>Tên Sản phẩm:</b> 
                                <label style="font-weight: bold; color: red;">
                                    <s:property value="%{sanphamDTO.TenSanPham}"/></label>
                            </div>
                            <div style="text-align: left; padding-left: 20px; padding-top: 15px;">
                                <b>Mô tả:</b> 
                                <label style="font-weight: bold; color: red;">
                                    <s:property value="%{sanphamDTO.MoTaSanPham}"/></label>
                            </div>
                            <div style="text-align: left; padding-left: 20px; padding-top: 10px;">
                                <b>Hãng sản xuất:</b> 
                                <label style="font-weight: bold; color: red;">
                                    <s:property value="%{sanphamDTO.TenNhaCC}"/></label>
                            </div>
                            <div style="text-align: left; padding-left: 20px; padding-top: 10px;">
                                <b>Xuất xứ:</b> 
                                <label style="font-weight: bold; color: red;">
                                    <s:property value="%{sanphamDTO.TenNuocSanXuat}"/></label>
                            </div>
                            <div style="text-align: left; padding-left: 20px; padding-top: 10px;">
                                <b>Giá:</b> 
                                <s:if test="#gia == 0">
                                    <label style="font-weight: bold; color: red;">Liên hệ</label>
                                </s:if>
                                <s:else>
                                    <label style="font-weight: bold; color: red;"><s:property value="%{sanphamDTO.Gia}"/> (VNĐ) </label>
                                </s:else>
                            </div>
                            <div style="text-align: left; padding-left: 20px; padding-top: 10px;">
                                <b>Tình trạng:</b> 
                                <label style="font-weight: bold; color: red;">
                                    <s:property value="%{sanphamDTO.TenTinhTrang}"/></label>
                            </div>
                        </div>
                        <div id="product-detail-under">
                            <h3>Hỗ trợ bán hàng</h3>
                            <div style="float: left;">
                                <div style="text-align: left; padding-left: 20px; padding-top: 15px;">
                                    <b>Tư vấn bán hàng 1:</b> <label style="font-weight: bold; color: red;">01213 33.55.88</label>
                                </div>
                                <div style="text-align: left; padding-left: 20px; padding-top: 10px;">
                                    <b>Tư vấn bán hàng 2:</b> <label style="font-weight: bold; color: red;">01238 33.55.88</label>
                                </div>
                                <div style="text-align: left; padding-left: 20px; padding-top: 10px;">
                                    <b>Tư vấn bán hàng 3:</b> <label style="font-weight: bold; color: red;">01698 33.55.88</label>
                                </div>
                                <div style="text-align: left; padding-left: 20px; padding-top: 10px;">
                                    <b>Bảo hành:</b> <label style="font-weight: bold; color: red;">(08) 62967700</label>
                                </div>
                            </div>
                            <div style="float: right; margin-right: 15px; text-align: center;">
                                <p style="font-size: 13px; font-weight: bolder; margin-top: 10px">Yahoo:</p>
                                <a href="ymsgr:sendIM?giakhangautoparts">
                                    <img src="http://opi.yahoo.com/online?u=giakhangautoparts&m=g&t=2"/>
                                </a>
                                <div class="clr"></div>
                                <p style="font-size: 13px; font-weight: bolder; margin-top: 10px">Skype:</p>
                                <a title="" href="skype:giakhang.autoparts?chat">
                                    <img src="./images/user/icons/skype-48.png" alt="Nick Skype status">
                                </a>
                                <a title="" href="skype:haophanhgk?chat">
                                    <img src="./images/user/icons/skype-48.png" alt="Nick Skype status">
                                </a>
                                <a href="skype:haophanhgk?chat" nickname="haophanhgk" type="skype">
                                    <img src="./images/user/icons/skype-48.png">
                                </a>
                            </div>

                        </div>
                    </div>
                    <div class="clr"></div>
                    <h3 style="margin-top: 5px; background: crimson; color: white; font-family: sans-serif; font-size: 13px; font-weight: bolder; height: 35px; line-height: 30px; 
                        margin-top: -5px; padding-left: 10px; text-align: left; border-radius: 3px;">Những dòng xe có thể dùng:</h3>
                    <div id="product-application">
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
                            </tr>
                            <s:iterator var="i" step="1" value="dsDongXeTuongThichDTO">
                                <tr>
                                    <td><s:property value="%{TenHangXe}"/></td>
                                    <td><s:property value="%{TenLoaiXe}"/></td>
                                    <td><s:property value="%{TenDongXe}"/></td>
                                    <td><s:property value="%{SoXyLanh}"/></td>
                                    <td><s:property value="%{DongCo}"/></td>
                                    <td><s:property value="%{HopSo}"/></td>
                                    <td><s:property value="%{SoCua}"/></td>
                                    <td><s:property value="%{NhienLieu}"/></td>
                                    <td><s:property value="%{NamSX}"/></td>
                                </tr>
                            </s:iterator>
                        </table>
                    </div>

                </div>
            </div>
            <jsp:include page="KhungBenDuoi.jsp" />
        </div>
    </body>
</html>