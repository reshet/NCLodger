<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>NCLodger | Settings: administrator</title>
<link rel="stylesheet" type="text/css" href="resources/css/style.css" />
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script type="text/javascript" src="resources/js/sorttable.js"></script>

    <script>
        $(function() {
            $("#start_promo").datepicker();
            $("#end_promo").datepicker();
        })
        $(function() {
            $("#start_mostvalacc").datepicker();
            $("#end_mostvalacc").datepicker();
        })
        $(function() {
            $( "#tabs" ).tabs();
        });
    </script>

<!--[if lt IE 7]>
<style type="text/css">
     #wrapper { height:100%; }
</style>
<![endif]-->
<style type="text/css">
    .sortable .head h3 { background: url(resources/img/sort.gif) 7px center no-repeat; cursor:pointer; padding-left:18px} /* unsort 2 arrows */
    .sortable .desc, .sortable .asc {background:#4b708d}                                                        /* selected header */
    .sortable .desc h3 { background: url(resources/img/desc.gif) 7px center no-repeat; cursor:pointer; padding-left:18px} /* dsc arrpw */
    .sortable .asc h3 { background: url(resources/img/asc.gif) 7px center no-repeat; cursor:pointer; padding-left:18px}   /* asc arrow */
</style>
</head>

<body>

<div id="wrapper">

    <div id="header">
        <div class="greeting" style="float: right; padding-right: 2em; ">
            <c:if test="${sessionScope.username == null}">
                <a href="login.jsp">Log in</a> / <a href="registration.jsp">Register</a>
            </c:if>
            <c:if test="${sessionScope.username != null}">
                Hello, <c:out value="${sessionScope.username}"/>!
                <br><a href="signout">Log out</a>
                <br><a href="" class="orangelink"><img src="resources/img/user.gif">User dashboard</a>
            </c:if>
        </div>

        <div class="nav">
            <ul>
                <li><a href="home.jsp"><h1>NCLodger</h1></a></li>
                <li><a href="home.jsp">Home</a></li>
                <li><a href="#">About Us</a></li>
                <li><a href="#">Contacts</a></li>
            </ul>
        </div>
    </div><!-- #header -->

    <div id="content">

    <div id="tabs">
        <ul>
            <li><a href="#tabs-1">Users</a></li>
            <li><a href="#tabs-2">Commission & Discounts</a></li>
            <li><a href="#tabs-3">Hotels</a></li>
        </ul>
        <div id="tabs-1"><!-- 'Users' tab -->

        </div>

    <div id="tabs-2"><!-- 'Commission & Discounts' tab-->
        <form name="discountsfrm" method="POST" action="initialdiscounts" onsubmit="">
            <%--<div id="agency_com">
                Agency comission: <span id="agency_com_currentval">0</span>%
            </div>
            <div id="defaultslide"></div>
            <script type="text/javascript">
                $(function(){
                    $('#agency_com').slider({
                        max: 17,
                        min: 3,
                        value: 0,
                        slide: function(e,ui) {
                            $('#agency_com_currentval').html(ui.value);
                        }
                    });
                });
            </script>--%>
            <p>Commission:</p>
            <input type="range" name="agency_com" id="agency_com" value="" min="0" max="33" />
            <p>User discount:</p>
            <input type="range" name="user_discount" id="user_discount" value="" min="0" max="33" />
            <p>VIP User discount:</p>
            <input type="range" name="vip_user_discount" id="vip_user_discount" value="" min="0" max="33" />
            <p><input type="submit" name="save_changes" value="Save changes"></p>
        </form>
    </div>
    <div id="tabs-3"><!-- 'Promo codes' tab -->
    </div>
    </div>


    </div><!-- #content -->

    <div id="footer">
    </div><!-- #footer -->

</div><!-- #wrapper -->

</body>
</html>