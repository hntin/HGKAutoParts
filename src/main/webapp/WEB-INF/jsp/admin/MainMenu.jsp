<%@page import="org.apache.struts2.dispatcher.SessionMap"%>
<%
    String logUser = (String) session.getAttribute("maNguoiDungLogged");    
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
        <link rel="stylesheet" type="text/css" href="./css/admin/admin-style.css">
    </head>
    <body>
        <!-- Phần Top trang Admin -->
        <div id="top-admin">
            <div id="top-left-admin">E-Commerce Framework 1.0 - Quản trị hệ thống</div>
            <div id="top-center-admin">@Copyright by HGK-Software 2016</div>
            <div id="top-right-admin">Người dùng:  <text style="color: crimson"><%=logUser%></text> </div>
        </div>

        <!-- Phần Menu trang Admin -->
        <div id="menu">
            <ul>
                <li style="border-left: none"><a href="#">Hệ thống</a>
                    <ul class="menu-sub">
                        <li><a href="#" onclick="alert('Chức năng này chưa cần thiết, Nên chưa làm')">Sao lưu dữ liệu</a></li>
                        <li><a href="#" onclick="alert('Chức năng này chưa cần thiết, Nên chưa làm')">Phục hồi dữ liệu</a></li>
                        <li><a href="./DangXuat">Thoát</a></li>
                    </ul>
                </li>
                <li><a href="#">Người dùng</a>
                    <ul class="menu-sub">
                         <li><a href="./GoTaoMoiNguoiDung">Thêm mới</a></li>
                        <li><a href="./QuanLyNguoiDung">Danh mục</a></li>
                        <li><a href="./TimKiemNguoiDung">Tìm kiếm</a></li>
                        <li><a href="#" onclick="alert('Chức năng này chưa cần thiết, Nên chưa làm')">Phân quyền</a></li>
                    </ul>
                </li>
                <li><a href="#">Sản phẩm</a>
                    <ul class="menu-sub">
                        <li><a href="./GoTaoMoiSanPham">Thêm mới</a></li>
                        <li><a href="./LocSanPham">Danh mục</a></li>
                        <li><a href="./TimKiemSanPham">Tìm kiếm</a></li>                        
                        <li><a href="#" onclick="alert('Chức năng này chưa cần thiết, Nên chưa làm')">Import từ Excel</a></li>
                        <li><a href="#" onclick="alert('Chức năng này chưa cần thiết, Nên chưa làm')">Export ra Excel</a></li>
                    </ul>
                </li>
<!--                <li><a href="#">Loại sản phẩm</a>
                    <ul class="menu-sub">
                        <li><a href="#" onclick="alert('Chức năng này chưa cần thiết, Nên chưa làm')">Thêm mới</a></li>
                        <li><a href="#" onclick="alert('Chức năng này chưa cần thiết, Nên chưa làm')">Danh mục</a></li>
                        <li><a href="#" onclick="alert('Chức năng này chưa cần thiết, Nên chưa làm')">Tìm kiếm</a></li>
                    </ul>
                </li>-->
                <li><a href="#">Nhà cung cấp</a>
                    <ul class="menu-sub">
                        <li><a href="./GoTaoMoiNhaCC">Thêm mới</a></li>
                        <li><a href="./QuanLyNhaCC">Danh mục</a></li>
                        <li><a href="./TimKiemNhaCC">Tìm kiếm</a></li>
                    </ul>
                </li>
                <li><a href="#">Quản lý dòng xe</a>
                    <ul class="menu-sub">
                        <li><a href="./QuanLyHangXe">Hãng xe</a></li>
                        <li><a href="./QuanLyLoaiXe">Loại xe</a></li>
                        <li><a href="./QuanLyDongXe">Dòng xe</a></li>
                    </ul>
                </li>
                <li><a href="#">Áp dụng</a>
                    <ul class="menu-sub">
                        <li><a href="./GoTaoMoiApDung">Thêm mới</a></li>
                        <li><a href="./QuanLyApDung">Danh mục</a></li>
                        <li><a href="./TimKiemApDung">Tìm kiếm</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </body>
</html>
