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
            <jsp:include page="KhungBenTrai.jsp" />
            <!-- Phần Main -->
            <div id="main">
                <jsp:include page="ApDungDongXe.jsp" /> 
                <div id="main-right">
                    <h3>Danh mục sản phẩm</h3>
                    <div id="products">
                        <ul>
                            <li>
                                <div class="content">
                                    <a href="./XemChiTietSanPham">
                                        <img src="images/user/products/timthumb.jpg" width="210" height="250"/>
                                        <p class="title">Ắc quy khô 12V-62Ah 56225</p>
                                    </a>
                                    <p class="price">Giá: liên hệ</p>
                                </div>
                            </li>
                            <li>
                                <div class="content">
                                    <a href="./XemChiTietSanPham">
                                        <img src="images/user/products/timthumb.jpg" width="210" height="250"/>
                                        <p class="title">Ắc quy khô 12V-62Ah 56225</p>
                                    </a>
                                    <p class="price">Giá: liên hệ</p>
                                </div>
                            </li>
                            <li>
                                <div class="content">
                                    <a href="./XemChiTietSanPham">
                                        <img src="images/user/products/timthumb.jpg" width="210" height="250"/>
                                        <p class="title">Ắc quy khô 12V-62Ah 56225</p>
                                    </a>
                                    <p class="price">Giá: liên hệ</p>
                                </div>
                            </li>
                            <li>
                                <div class="content">
                                    <a href="./XemChiTietSanPham">
                                        <img src="images/user/products/timthumb.jpg" width="210" height="250"/>
                                        <p class="title">Ắc quy khô 12V-62Ah 56225</p>
                                    </a>
                                    <p class="price">Giá: liên hệ</p>
                                </div>
                            </li>
                            <li>
                                <div class="content">
                                    <a href="./XemChiTietSanPham">
                                        <img src="images/user/products/timthumb.jpg" width="210" height="250"/>
                                        <p class="title">Ắc quy khô 12V-62Ah 56225</p>
                                    </a>
                                    <p class="price">Giá: liên hệ</p>
                                </div>
                            </li>
                            <li>
                                <div class="content">
                                    <a href="./XemChiTietSanPham">
                                        <img src="images/user/products/timthumb.jpg" width="210" height="250"/>
                                        <p class="title">Ắc quy khô 12V-62Ah 56225</p>
                                    </a>
                                    <p class="price">Giá: liên hệ</p>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <jsp:include page="KhungBenDuoi.jsp" />
        </div>
        <div class="clr"></div>
    </body>
</html>
