<%@page import="org.json.simple.JSONArray"%>
<%@page import="hgksoft.acquy.bo.LoaiSanPhamBO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link rel="stylesheet" type="text/css" href="./css/user/style.css"/>
<link rel="stylesheet" href="./css/user/flexslider.css" type="text/css" media="screen" />
<link rel="stylesheet" type="text/css"
      href="codebase/fonts/font_roboto/roboto.css" />
<link rel="stylesheet" type="text/css" href="codebase/dhtmlx.css" />
<script src="codebase/dhtmlx.js"></script>
<style>    
    html {
    overflow-y:scroll;
}
</style>
<script type="text/javascript">
    function OnChangHangXe() {
        var strURL = window.location.href;
        if (strURL.indexOf("GoIndex") >= 0) {
            document.getElementsByName("FormTimKiemApDungUser")[0].action = "./GoIndex";
            document.getElementsByName("FormTimKiemApDungUser")[0].submit();
        }

        if (strURL.indexOf("XemChiTietSanPham") >= 0) {
            document.getElementsByName("FormTimKiemApDungUser")[0].action = "./XemChiTietSanPham";
            document.getElementsByName("FormTimKiemApDungUser")[0].submit();
        }

        if (strURL.indexOf("XemApDungDongXe") >= 0) {
            document.getElementsByName("FormTimKiemApDungUser")[0].action = "./XemApDungDongXe";
            document.getElementsByName("FormTimKiemApDungUser")[0].submit();
        }

        if (strURL.indexOf("XemDanhMucSanPham") >= 0) {
            document.getElementsByName("FormTimKiemApDungUser")[0].action = "./XemDanhMucSanPham";
            document.getElementsByName("FormTimKiemApDungUser")[0].submit();
        }

        if (strURL.indexOf("GoGioiThieu") >= 0) {
            document.getElementsByName("FormTimKiemApDungUser")[0].action = "./GoGioiThieu";
            document.getElementsByName("FormTimKiemApDungUser")[0].submit();
        }

        if (strURL.indexOf("LietKeSanPham") >= 0) {
            document.getElementsByName("FormTimKiemApDungUser")[0].action = "./LietKeSanPham";
            document.getElementsByName("FormTimKiemApDungUser")[0].submit();
        }

    }

    function OnChangeLoaiXe() {
        if (document.getElementsByName("selectedLoaiXe")[0].value !== "0") {
            // Get the modal
            var popup = document.getElementById('myModal');
            // Get the <span> element that closes the modal
            var span = document.getElementsByClassName("close")[0];

            // Load dữ liệu vào dòng xe vào Popup
            // Lấy dòng xe được chọn
            var selectedLoaiXe = document.getElementsByName("selectedLoaiXe")[0].value;
            $.ajax({
                type: "GET",
                url: "./LietKeDongXe",
                data: "selectedLoaiXe=" + selectedLoaiXe,
                async: false,
                success: function (response) {
                    // we have the response
                    $('#modal-body').html(response);
                    popup.style.display = "block";
                    // When the user clicks on <span> (x), close the modal
                    span.onclick = function () {
                        popup.style.display = "none";
                    };

                },
                error: function (e) {
                    alert('Error: ' + e);
                }
            });
        }

        // When the user clicks anywhere outside of the modal, close it
        window.onclick = function (event) {
            if (event.target == popup) {
                popup.style.display = "none";
            }
        };
    }

    function OnSelectDongXe(maDongXe) {
        document.getElementsByName("FormTimKiemApDungUser")[0].action = "./XemApDungDongXe?maDongXe=" + maDongXe;
        document.getElementsByName("FormTimKiemApDungUser")[0].submit();
    }
</script>

<%
    LoaiSanPhamBO lspBO = new LoaiSanPhamBO();
    JSONArray jsonTree = lspBO.getJsonTree();
    JSONArray accodion = lspBO.getJsonAccordion();

%>

<script>
    
    window.onload = function doOnLoad() {
        var khungAccodion = new dhtmlXAccordion({           
            parent: "menu-left",
            items: <%=accodion.toJSONString()%>
        });

        khungAccodion.attachEvent("onActive", function (id, state) {
            onSelectItemTreeView(id);
        });
        
        <%=lspBO.getTreeInJavaScript()%>
        khungAccodion.cells(getCookie("last_id")).open();
    }
  

    function setCookie(cname, cvalue, exdays) {
        var d = new Date();
        d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
        var expires = "expires=" + d.toUTCString();
        document.cookie = cname + "=" + cvalue + "; " + expires;
    }

    function getCookie(cname) {
        var name = cname + "=";
        var ca = document.cookie.split(';');
        for (var i = 0; i < ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0) == ' ') {
                c = c.substring(1);
            }
            if (c.indexOf(name) == 0) {
                return c.substring(name.length, c.length);
            }
        }
        return "";
    }

    function onSelectItemTreeView(id) {
        setCookie("last_id", id, 1);
        document.getElementsByName("FormTimKiemApDungUser")[0].action = "./LietKeSanPham?maLoaiSanPham=" + id;
        document.getElementsByName("FormTimKiemApDungUser")[0].submit();
        return true;
    }


//    var myTreeView;
//    window.onload = function doOnLoad() {
//        myTreeView = new dhtmlXTreeView({
//            parent: "menu-left",
//            items:<%=jsonTree.toJSONString()%>
//        });
//
//        myTreeView.attachEvent("onSelect", onSelectItemTreeView);
//
//////        myTreeView.attachEvent("onSelect", function (id, mode) {
//////            var xhttp = new XMLHttpRequest();
//////            xhttp.onreadystatechange = function () {
//////                if (xhttp.readyState == 4 && xhttp.status == 200) {
//////                    document.getElementById("demo").innerHTML += xhttp.responseText + "<br>";
//////                }
//////            };
//////            xhttp.open("POST", "./LietKeSanPham", true);
//////            xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
//////            xhttp.send("select_id=" + id + "&select_mode=" + mode);
//////        });
//    }

//    function onSelectItemTreeView(id) {
//        //setCookie("last_id", id, 1);
//        document.getElementsByName("FormTimKiemApDungUser")[0].action = "./LietKeSanPham?maLoaiSanPham=" + id;
//        document.getElementsByName("FormTimKiemApDungUser")[0].submit();
//        return true;
//    }

</script>

<div id="main-left">
    <div id="search-application">
        <h3>Bạn đang dùng xe gì?</h3>
        <div class="clr"></div>
        <s:form action="./GoIndex" method="post" name="FormTimKiemApDungUser" 
                id="FormTimKiemApDungUser" theme="simple">
            <table style="background-color: white;">
                <tr>
                    <td><s:select list="%{dsHangXeHM}" name="selectedHangXe" id="selectedHangXe"
                              cssStyle="width:210px; height:25px; border: 1px solid whitesmoke" 
                              onchange="OnChangHangXe()"/>
                        <s:set name="var1" value="selectedHangXe"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div>
                            <s:if test="#var1 == 0">
                                <s:select list="%{dsLoaiXeHM}" name="selectedLoaiXe" id="selectedLoaiXe" disabled="true"
                                          cssStyle="width:210px; height:25px; border: 1px solid whitesmoke"
                                          onchange="OnChangeLoaiXe()"/>
                            </s:if>
                            <s:else>
                                <s:select list="%{dsLoaiXeHM}" name="selectedLoaiXe" id="selectedLoaiXe" disabled="false"
                                          cssStyle="width:210px; height:25px; border: 1px solid whitesmoke"
                                          onchange="OnChangeLoaiXe()"/>
                            </s:else>

                        </div>

                    </td>
                </tr>
            </table>
        </s:form>
    </div>
    <div id="menu-left"></div>
    <script src="javascript/user/jquery.js" type="text/javascript"></script>
</div>

<!-- The Modal/Popup windows -->
<div id="myModal" class="modal">
    <!-- Modal content -->
    <div class="modal-content">
        <div class="modal-header">
            <span class="close">×</span>
            <div style="font-weight: bolder; font-size: 14px; margin-top: 7px">
                <label>Bạn đang sử dụng dòng xe gì?</label>
            </div>
        </div>
        <div class="modal-body" id="modal-body">
        </div>
        <div class="modal-footer">
            <h3></h3>
        </div>
    </div>
</div>
