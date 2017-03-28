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
            <jsp:include page="KhungQuangCao.jsp"/>
            <!-- Phần Main -->
            <div id="main">
                <jsp:include page="KhungBenTrai.jsp" /> 
                <div id="main-right">
                    <div class="page-caption">
                        CÔNG TY TNHH MTV TMDV HUỲNH GIA KHANG
                    </div>
                    <div style="padding-left: 20px; margin-top: 20px;">Tiền thân là đơn vị chuyên cung cấp phụ tùng - phụ kiện chính hãng cho dòng ô tô nhập khẩu từ Mỹ và Đài Loan, như:                            
                    </div>
                    <div style="padding-left: 20px; margin-top: 20px;"> 
                        - Toyota Venza, Avalon, Sienna, Camry, Prius, Yaris, RAV4, Highlander
                    </div>
                    <div style="padding-left: 20px; margin-top: 20px;"> 
                        - Lexus LS, GS, RX, GX
                    </div>
                    <div style="padding-left: 20px; margin-top: 20px;"> 
                        - Honda Odyssey, Accord, CR-V, Civic, Pilot, Fit
                    </div>
                    <div style="padding-left: 20px; margin-top: 20px;"> 
                        - Acura MDX, ZDX, RDX
                    </div>
                    <div style="padding-left: 20px; margin-top: 20px;"> 
                        và các dòng xe khác Nissan, Infiniti, Cadillac, BMW, Mercedes, Audi, Porsche, Chrysler, Jeep, Dodge, Ford, GM, Hummer, Land Rover ...
                    </div>
                    
                    <div style="padding-left: 20px; margin-top: 20px; font-weight: bolder;"> 
                        Hiện nay, công ty chúng tôi còn là nhà phân phối chính thức phụ tùng ô tô & ắc quy BOSCH tại Việt Nam.
                    </div>
                    
                    <div style="padding-left: 20px; margin-top: 20px; text-align: justify;"> 
                        Với những hiểu biết về chuyên môn và kinh nghiệm trong lĩnh vực phụ tùng, cùng với đội ngũ nhân viên thân thiện, nhiệt tình, chúng tôi cam kết sẽ mang lại cho Quý khách sự hài lòng với những phụ tùng thay thế có chất lượng và dịch vụ bán hàng tốt nhất.
                    </div>
                    
                    <div style="padding-left: 20px; margin-top: 20px;"> 
                        TRÂN TRỌNG KÍNH CHÀO !
                    </div>
                    <div style="padding-left: 20px; margin-top: 20px; text-align: center; color: red; font-weight: bold;"> 
                        XIN CẢM ƠN SỰ QUAN TÂM VÀ ỦNG HỘ CỦA QUÝ KHÁCH HÀNG !
                    </div>                    
                </div>
            </div>
        </div>
        <jsp:include page="KhungBenDuoi.jsp" />
    </div>
</body>
</html>







