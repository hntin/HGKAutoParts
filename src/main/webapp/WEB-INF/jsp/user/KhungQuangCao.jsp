<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- Phần Quảng cáo -->
<div id="ads">
    <div id="ads-left">
        <section class="slider">
            <div class="flexslider">
                <ul class="slides">
                    <li>
                        <img src="images/user/slider/Osram01.jpg" style="width: 645px; height: 344px"/>
                    </li>
                    <li>
                        <img src="images/user/slider/Battery01.jpg" style="width: 645px; height: 344px"/>
                    </li>
                    <li>
                        <img src="images/user/slider/Battery02.jpg" style="width: 645px; height: 344px"/>
                    </li>
                    <li>
                        <img src="images/user/slider/Horn01.jpg" style="width: 645px; height: 344px"/>
                    </li>
                </ul>
            </div>
        </section>
    </div>
    <!-- jQuery -->
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="js/libs/jquery-1.7.min.js">\x3C/script>')</script>

    <!-- FlexSlider -->
    <script defer src="javascript/user/jquery.flexslider.js"></script>

    <script type="text/javascript">
        $(function () {
            SyntaxHighlighter.all();
        });
        $(window).load(function () {
            $('.flexslider').flexslider({
                animation: "slide",
                controlNav: false,
            });
        });
    </script>
    <div id="ads-right">
        <div id="online-chat">
            <h3>Hỗ trợ trực tuyến</h3>
            <p style="font-size: 13px; font-weight: bolder; margin-top: 10px">Yahoo:</p>
            <a href="ymsgr:sendIM?giakhangautoparts">
                <img src="http://opi.yahoo.com/online?u=giakhangautoparts&m=g&t=2"/>
            </a>
            <div class="clr"></div>
            <p style="font-size: 13px; font-weight: bolder; margin-top: 10px">Skype:</p>
            <a href="skype:haophanhgk?chat" nickname="haophanhgk" type="skype">
                <img src="./images/user/icons/skype-48.png">
            </a>                    
            <a title="" href="skype:nhung.hgk?chat">
                <img src="./images/user/icons/skype-48.png" alt="Nick Skype status">
            </a>
            <a title="" href="skype:facebook:hnthanh.hgk?chat">
                <img src="./images/user/icons/skype-48.png" alt="Nick Skype status">
            </a>
        </div>
        <div id="online-chat">
            <h3>Đường dây nóng</h3>
            <div style="text-align: left; padding-left: 30px; padding-top: 7px;">
                <b>Tư vấn bán hàng 1:</b> <label style="font-weight: bold; color: red;">01213 33.55.88</label>
            </div>
            <div style="text-align: left; padding-left: 30px; padding-top: 7px;">
                <b>Tư vấn bán hàng 2:</b> <label style="font-weight: bold; color: red;">01238 33.55.88</label>
            </div>
            <div style="text-align: left; padding-left: 30px; padding-top: 7px;">
                <b>Tư vấn bán hàng 3:</b> <label style="font-weight: bold; color: red;">01698 33.55.88</label>
            </div>
            <div style="text-align: left; padding-left: 30px; padding-top: 7px;">
                <b>Bảo hành:</b> <label style="font-weight: bold; color: red;">(08) 62967700</label>
            </div>
        </div>
    </div>
</div>
<div class="clr"></div>
