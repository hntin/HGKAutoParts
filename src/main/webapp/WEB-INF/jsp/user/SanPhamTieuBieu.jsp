<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="./css/user/style.css"/>
<link rel="stylesheet" href="./css/user/flexslider.css" type="text/css" media="screen" />

<div id="main-right">

    <s:iterator value="dsLoaiSP_SanPham_HM" var="hm_LSP_SP">
        <s:set var="lspKey" value="#hm_LSP_SP.key"/>
        <h3><s:property value="#hm_LSP_SP.key"/></h3>
        <div id="products">
            <ul>
                <s:iterator var="spDTO" value="#hm_LSP_SP.value">
                    <li>
                        <div class="content">
                            <a href="./XemChiTietSanPham?maSanPham=<s:property value="#spDTO.MaSanPham"/>">
                                <img src="<s:property value="#spDTO.HinhDaiDien"/>" width="210" height="250"/>
                                <p class="title"><s:property value="#spDTO.TenSanPham"/></p>  
                                <s:set var="gia" value="#spDTO.Gia"></s:set>
                                <s:if test="#gia == 0">
                                    <p class="sanpham_gia"><span>Liên hệ</span></p>
                                </s:if>
                                <s:else>
                                    <p class="sanpham_gia"><span><s:property value="#spDTO.Gia"/> (VNĐ)</span></p>
                                </s:else>
                        </div>
                    </li>
                </s:iterator>
            </ul>
        </div>    
        <div class="clr"></div>
        <br>
    </s:iterator>
    <div class="clr"></div>
    <br>
</div>
<div class="clr"></div>

