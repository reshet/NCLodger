<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>NCLodger | Administrator dashboard</title>
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
//            $( "#tabs" ).tabs();
            $("#tabs").tabs({
                create: function(event, ui){
                    $(this).tabs({'select': $(this).find("ul").index($(this).find('a[href="' + window.location.hash + '"]').parent())});
                },
                activate: function(event, ui){
                    window.location.hash = $(ui.newTab[0]).find('a[href^="#tab"]').attr("href");
                }
            });
        });

        function OnMakeBlock(){
            document.adgetalluser.action = "makeblock";
            document.adgetalluser.submit();
            return true;
        }

        function OnMakeUnBlock(){
            document.adgetalluser.action = "makeunblock";
            document.adgetalluser.submit();
            return true;
        }

        function OnMakeSM(){
            document.adgetalluser.action = "grantsm";
            document.adgetalluser.submit();
            return true;
        }

        function OnDismissSM(){
            document.adgetalluser.action = "dismisssm";
            document.adgetalluser.submit();
            return true;
        }

        function OnDeleteUser(){
            document.adgetalluser.action = "deleteuser";
            document.adgetalluser.submit();
            return true;
        }

        function toggle(source) {
            checkboxes = document.getElementsByName('block[]');
            for(var i=0, n=checkboxes.length;i<n;i++) {
                checkboxes[i].checked = source.checked;
            }
        }

    </script>

    <script type="text/javascript">
        function OnSliderChanged (slider) {
            var sliderValue = document.getElementById (slider.id + "Value");
            sliderValue.innerHTML = slider.value;
        }

        function Init () {
            var slider = document.getElementById ("slider1");
            OnSliderChanged (slider);
            var slider = document.getElementById ("slider2");
            OnSliderChanged (slider);
            var slider = document.getElementById ("slider3");
            OnSliderChanged (slider);
        }
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

<body onload="Init ()">

<div id="wrapper">

    <div id="header">
        <div class="greeting" style="float: right; padding-right: 2em; ">
            <c:if test="${sessionScope.username == null}">
                <a href="login.jsp">Log in</a> / <a href="registration.jsp">Register</a>
            </c:if>
            <c:if test="${sessionScope.username != null}">
                Hello, <c:out value="${sessionScope.username}"/>!
                <br><a href="signout">Log out</a>
                <br><a href="ussettings.jsp" class="orangelink"><img src="resources/img/user.gif">User dashboard</a>
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
            <form name="adgetalluser" method="POST" onsubmit="">
                <a href="admingetallusers">All users</a>
                <c:if test="${requestScope.allusers != null}">
                <table cellpadding="0" cellspacing="0" border="0" id="table" class="sortable">
                    <thead>
                    <tr>
                        <th><input type="checkbox" onClick="toggle(this)"/></th>
                        <th><h3>Name</h3></th>
                        <th><h3>Email</h3></th>
                        <th><h3>Block Status</h3></th>
                        <th><h3>Role in the system</h3></th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach items="${requestScope.allusers}" var="user">
                        <tr>
                            <td><input type="checkbox" name = "block[]" c:out value="${user.id}"/></td>
                            <td><c:out value="${user.name}"/></td>
                            <td><c:out value="${user.email}"/></td>
                           <td>
                               <c:choose>
                                <c:when test="${user.is_blocked=='0'}">not blocked</c:when>
                                <c:when test="${user.is_blocked=='1'}">blocked</c:when>
                               </c:choose>
                           </td>
                            <td>
                                <c:choose>
                                    <c:when test="${user.id_ut=='3'}">Admin</c:when>
                                    <c:when test="${user.id_ut=='2'}">Sales Manager</c:when>
                                    <c:when test="${user.id_ut=='1'}">Customer</c:when>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <input type = "submit" name = "Block" value="Block" onclick="OnMakeBlock();">
                <input type="submit" name = "UnBlock" value="UnBlock" onclick="OnMakeUnBlock();">
                <input type="submit" name = "GrantSM" value="Grant SM" onclick="OnMakeSM();">
                <input type="submit" name = "DismissSM" value="Dismiss SM" onclick="OnDismissSM();">
                <input type="submit" name = "CreateUser" value="Create User">
                <input type="submit" name="DeleteUser" value="Delete User" onclick="OnDeleteUser();">
                <div class="controls">
                    <div class="perpage">
                        <select onchange="sorter.size(this.value)">
                            <option value="5">5</option>
                            <option value="10" selected="selected">10</option>
                            <option value="20">20</option>
                            <option value="50">50</option>
                            <option value="100">100</option>
                        </select>
                        <span>Entries Per Page</span>
                    </div>
                    <div class="navigation">
                        <img src="resources/img/first.gif" width="16" height="16" alt="First Page" onclick="sorter.move(-1,true)"/>
                        <img src="resources/img/previous.gif" width="16" height="16" alt="First Page" onclick="sorter.move(-1)"/>
                        <img src="resources/img/next.gif" width="16" height="16" alt="First Page" onclick="sorter.move(1)"/>
                        <img src="resources/img/last.gif" width="16" height="16" alt="Last Page" onclick="sorter.move(1,true)"/>
                    </div>
                    <div class="text">Displaying Page <span id="currentpage"></span> of <span id="pagelimit"></span></div>
                </div>
                <script type="text/javascript">
                    var sorter = new TINY.table.sorter("sorter");
                    sorter.head = "head";
                    sorter.asc = "asc";
                    sorter.desc = "desc";
                    sorter.even = "evenrow";
                    sorter.odd = "oddrow";
                    sorter.evensel = "evenselected";
                    sorter.oddsel = "oddselected";
                    sorter.paginate = true;
                    sorter.currentid = "currentpage";
                    sorter.limitid = "pagelimit";
                    sorter.init("table", 1);
                </script>
                </c:if>
        </div>
        </form>

    <div id="tabs-2"><!-- 'Commission & Discounts' tab-->
        <form name="discountsfrm" method="POST" action="initialdiscounts" onsubmit="">
            <p>Commission:</p>
            <input type="range" name="agency_com" id="slider1" value="" min="3" max="17" onchange="OnSliderChanged (this)"/>
            <span id="slider1Value" class="sliderValue"></span>
            <p>User discount:</p>
            <input type="range" name="user_discount" id="slider2" value="" min="0" max="33" onchange="OnSliderChanged (this)"/>
            <span id="slider2Value" class="sliderValue"></span>
            <p>VIP User discount:</p>
            <input type="range" name="vip_user_discount" id="slider3" value="" min="0" max="33" onchange="OnSliderChanged (this)"/>
            <span id="slider3Value" class="sliderValue"></span>
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