<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>NCLodger | Sales Manager dashboard</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css"/>
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
    <!-- Script and css for the sorting table -->
    <script type="text/javascript" src="resources/js/sorttable.js"></script>
    <style type="text/css">
    .sortable .head h3 { background: url(resources/img/sort.gif) 7px center no-repeat; cursor: pointer; padding-left: 18px }
    .sortable .desc, .sortable .asc { background: #4b708d } /* unsort 2 arrows */
    .sortable .desc h3 { background: url(resources/img/desc.gif) 7px center no-repeat; cursor: pointer; padding-left: 18px } /* selected header */
    .sortable .asc h3 { background: url(resources/img/asc.gif) 7px center no-repeat; cursor: pointer; padding-left: 18px } /* dsc arrpw */
    </style>
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

        function OnMakeVip(){
            document.getalluser.action = "makevip";
            document.getalluser.submit();
            return true;
        }
        function OnMakeUnvip(){
            document.getalluser.action = "makeunvip";
            document.getalluser.submit();
            return true;
        }

        function toggle2(source) {
            checkboxes = document.getElementsByName('vip[]');
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
            var slider = document.getElementById ("slider4");
            OnSliderChanged (slider);
        }
    </script>

    <!--[if lt IE 7]>
    <style type="text/css">
        #wrapper {
            height: 100%;
        }
    </style>
    <![endif]-->
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
    </div>
    <!-- #header -->

    <div id="content">
        <div id="tabs">
            <%--<c:set var="tabs" value="${fn:split('#tabs-1,#tabs-2,#tabs-3,#tabs-4', ',')}" scope="session" />--%>
            <ul>
    <%--            <c:forEach items="${tabs}" var="tabs">
                    <li><a href="${tabs}">${tabs}</a></li>
                </c:forEach>--%>
                <li><a href="#tabs-1">Users</a></li>
                <li><a href="#tabs-2">Commission & Discounts</a></li>
                <li><a href="#tabs-3">Promo codes</a></li>
                <li><a href="#tabs-4">Reports</a></li>
            </ul>
            <div id="tabs-1"><!-- 'Users' tab -->
            <!--    <form name="getalluser" method="POST" action="makevip" onsubmit=""> -->
            <form name="getalluser" method="POST" onsubmit="">
                <a href="smgetallusers">All users</a>
                   <c:if test="${requestScope.allusers != null}">
                   <table cellpadding="0" cellspacing="0" border="0" id="table" class="sortable">
                        <thead>
                        <tr>
                            <th><input type="checkbox" onClick="toggle2(this)"/></th>
                            <th><h3>Name</h3></th>
                            <th><h3>Email</h3></th>
                            <th><h3>Vip Status</h3></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.allusers}" var="user">
                            <tr>
                                <td><input type="checkbox" name = "vip[]" c:out value="${user.id}"/> </td>
                                <td><c:out value="${user.name}"/></td>
                                <td><c:out value="${user.email}"/></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${user.vip=='0'}">Not vip</c:when>
                                        <c:when test="${user.vip=='1'}">Vip</c:when>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <input type = "submit" name = "UNVIP" value="UNVIP" onclick="OnMakeUnvip();">
                    <input type="submit" name = "VIP" value="VIP" onclick="OnMakeVip();">
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
                <form name="discountsfrm" method="POST" action="smsetdiscounts" onsubmit="">
                    <p><b>Commission:</b></p>
                    <input type="range" id="slider1" name="agency_com" value="${curcom}" min="3" max="17" onchange="OnSliderChanged (this)" />
                    <span id="slider1Value" class="sliderValue"></span>
                    <p><b>User discount:</b></p>
                    <input type="range" id="slider2" name="user_discount" value="${curdisc}" min="0" max="33" onchange="OnSliderChanged (this)" />
                    <span id="slider2Value" class="sliderValue"></span>
                    <p><b>VIP User discount:</b></p>
                    <input type="range" id="slider3" name="vip_user_discount" value="${curvipdisc}" min="0" max="33" onchange="OnSliderChanged (this)" />
                    <span id="slider3Value" class="sliderValue"></span>
                    </br>
                    <p><input type="submit" name="save_changes" value="Save changes"></p>
                </form>
            </div>
            <div id="tabs-3"><!-- 'Promo codes' tab -->
                <form name="promofrm" method="POST" action="generatepromo" onsubmit="">
                    <p><b>Start date: *</b></p>
                    <input id="start_promo" name="start_promo" style="width:100px;"/>
                    <p><b>Expiration date: *</b></p>
                    <input id="end_promo" name="end_promo" style="width:100px;"/>
                    <p><b>Discount: *</b></p>
                    <input type="range" id="slider4" name="promo_discount" value="" min="1" max="33" onchange="OnSliderChanged (this)"/>
                    <span id="slider4Value" class="sliderValue"></span>
                    </br>
                    <c:if test="${promo_code != null}">
                        <p><b>Generated promo code:</b> ${promo_code}</p>
                    </c:if>
                    <p><input type="submit" name="generate_promo" value="Generate"></p>
                    <a href="getallpromocodes">All promo codes:</a>
                    </br>
                    <c:if test="${requestScope.allpromocodes != null}">
                        <table cellpadding="0" cellspacing="0" border="0" id="table_pc" class="sortable">
                            <thead>
                            <tr>
                                <th><h3>Name</h3></th>
                                <th><h3>Discount</h3></th>
                                <th><h3>Start date</h3></th>
                                <th><h3>Expiration date</h3></th>
                                <th><h3>Status</h3></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${requestScope.allpromocodes}" var="pc">
                                <tr>
                                    <td><c:out value="${pc.code}"/></td>
                                    <td><c:out value="${(pc.discount * 100)}"/>%</td>
                                    <td><c:out value="${pc.start_date}"/></td>
                                    <td><c:out value="${pc.end_date}"/></td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${pc.status=='0'}">Available</c:when>
                                            <c:when test="${pc.status=='1'}">Used</c:when>
                                            <c:when test="${pc.status=='2'}">Expired</c:when>
                                        </c:choose>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
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
                            <div class="text">Displaying Page <span id="currentpage_pc"></span> of <span id="pagelimit_pc"></span></div>
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
                            sorter.currentid = "currentpage_pc";
                            sorter.limitid = "pagelimit_pc";
                            sorter.init("table_pc", 1);
                        </script>
                    </c:if>
                </form>
            </div>


            <div id="tabs-4"><!-- 'Reports' tab-->
<%--                <form name="mostvaluableacc" method="POST" action="getmostvaluableacc" onsubmit="">
                    <p>Start date:<input id="start_mostvalacc" name="start_mostvalacc" style="width:100px;"/></p>
                    <p>Expiration date:<input id="end_mostvalacc" name="end_mostvalacc" style="width:100px;"/></p>
&lt;%&ndash;                    <input type="submit" name="show_acc" value="Show most valuable accomodations">&ndash;%&gt;
                </form>--%>
                <form name="getallhotel" method="POST" onsubmit="">
                    <a href="showmostpopularhotel">Show most popular hotels</a>
                    <c:if test="${requestScope.mostpophotel != null}">
                    <table cellpadding="0" cellspacing="0" border="0" id="table" class="sortable">
                        <thead>
                        <tr>
                            <th><h3>Hotel name</h3></th>
                            <th><h3>City</h3></th>
                            <th><h3>Country</h3></th>
                            <th><h3>Total order for period</h3></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.mostpophotel}" var="hotel">
                            <tr>

                                <td><c:out value="${hotel.hotelname}"/></td>
                                <td><c:out value="${hotel.city}"/></td>
                                <td><c:out value="${hotel.country}"/></td>
                                <td><c:out value="${hotel.totalOrder}"/></td>

                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <a href="saveexcel">Save as Excel</a>
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
                        <div class="text">Displaying Page <span id="currentpage_h"></span> of <span id="pagelimit_h"></span></div>
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
        </div>

    </div>
    <!-- #content -->

    <div id="footer">
    </div>
    <!-- #footer -->

</div>
<!-- #wrapper -->

</body>

</html>