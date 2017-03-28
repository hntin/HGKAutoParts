<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Ắc Quy Gia Khang</title>
        <meta charset="UTF-8">
        <meta charset="UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="./css/user/style.css"/>
        <link rel="stylesheet" href="./css/user/flexslider.css" type="text/css" media="screen" />

    </head>
    <body>
        <div id="website">
            <jsp:include page="KhungBenTren.jsp" /> 
            <jsp:include page="KhungQuangCao.jsp"/>
            <!-- Phần Main -->
            <div id="main">
                <jsp:include page="KhungBenTrai.jsp" /> 
                <div id="main-right">
                    <h3 class="page-caption">Chuyên Mục Ắc Quy</h3>
                    <div id="products">
                        <ul>
                            <s:iterator var="spDTO" step="1" value="dsSanPhamDTO">
                                <li>
                                    <div class="content">
                                        <a href="./XemChiTietSanPham?maSanPham=<s:property value='MaSanPham'/>">
                                            <img src="<s:property value="hinhDaiDien"/>" width="210" height="250"/>
                                            <p class="title"><s:property value="moTaSanPham"/></p>
                                        </a>
                                        <s:set var="gia" value="#spDTO.Gia"></s:set>
                                        <s:if test="#gia == 0">
                                            <p class="sanpham_gia"><span>Giá: Liên hệ</span></p>
                                        </s:if>
                                        <s:else>
                                            <p class="sanpham_gia"><span>Giá: <s:property value="#spDTO.Gia"/> (VNĐ)</span></p>
                                        </s:else>
                                    </div>
                                </li>  
                            </s:iterator>                                               
                        </ul>
                    </div>     
                    <div class="clr"></div>
                    <br>
                </div>
                <div class="clr"></div>
            </div>
            <jsp:include page="KhungBenDuoi.jsp" />
        </div>
    </body>
</html>