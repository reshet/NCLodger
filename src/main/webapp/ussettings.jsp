<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>NCLodger | User dashboard</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css" />
    <script src="resources/js/md5-min.js"></script>
<%--    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>--%>
<%--    <!--[if lt IE 7]>
    <style type="text/css">
        #wrapper { height:100%; }
    </style>
    <style type="text/css">
        .sortable .head h3 { background: url(resources/img/sort.gif) 7px center no-repeat; cursor: pointer; padding-left: 18px }
        .sortable .desc, .sortable .asc { background: #4b708d } /* unsort 2 arrows */
        .sortable .desc h3 { background: url(resources/img/desc.gif) 7px center no-repeat; cursor: pointer; padding-left: 18px } /* selected header */
        .sortable .asc h3 { background: url(resources/img/asc.gif) 7px center no-repeat; cursor: pointer; padding-left: 18px } /* dsc arrpw */
    </style>
    <![endif]-->--%>
    <script>
        /*$(function() {
//            $( "#tabs" ).tabs();
            $("#tabs").tabs({
                create: function(event, ui){
                    $(this).tabs({'select': $(this).find("ul").index($(this).find('a[href="' + window.location.hash + '"]').parent())});
                },
                activate: function(event, ui){
                    window.location.hash = $(ui.newTab[0]).find('a[href^="#tab"]').attr("href");
                }
            });
        });*/

        function validateChangePswdForm() {

            var isValid = true;

            if(document.changepswdfrm.oldpswd.value.length < 6 || document.changepswdfrm.oldpswd.value.length > 20
                    || (!(/^[a-zA-Z0-9]+$/).test(document.changepswdfrm.oldpswd.value))) {
                document.getElementById("span_oldpswd").style.display = 'inline';
                isValid = false;
            }
            else { document.getElementById("span_oldpswd").style.display = 'none'; }

            if(document.changepswdfrm.newpswd1.value.length < 6 || document.changepswdfrm.newpswd1.value.length > 20
                    || (!(/^[a-zA-Z0-9]+$/).test(document.changepswdfrm.newpswd1.value))) {
                document.getElementById("span_newpswd1").style.display = 'inline';
                isValid = false;
            }
            else { document.getElementById("span_newpswd1").style.display = 'none'; }

            if(document.changepswdfrm.newpswd1.value == document.changepswdfrm.newpswd2.value) {
                document.getElementById("span_newpswd2").style.display = 'none';
            }
            else {
                document.getElementById("span_newpswd2").style.display = 'inline';
                isValid = false;
            }

            if(isValid){
                document.changepswdfrm.password.value = hex_md5(document.changepswdfrm.newpswd1.value);
                document.changepswdfrm.oldpswd_hidden.value = hex_md5(document.changepswdfrm.oldpswd.value);
            }

            return isValid;
        }

        function hideshow() {
            document.getElementById("hidden_div").style.display = 'inline';
        }

    </script>
</head>

<body>

<div class="wrapper">

    <jsp:include page="header.jsp"/>

    <div class="content">
        <div class="window" style="width: 350px">
            <h1>User information</h1>
            <p>
                Username <span class="mandatory">*</span>:
                <span class="bluespan"><c:out value="${sessionScope.username}"/></span>
            </p>
            <p>
                Email <span class="mandatory">*</span>:
                <span class="bluespan"><c:out value="${sessionScope.email}"/></span>
            </p>
            <br><hr><br>
            <p><a class="bluespan"<%-- onclick="hideshow()"--%>>Password change:</a></p><br>
            <c:if test="${requestScope.notify_changepswd != null}">
                <p style="color: #00FF00;"><c:out value="${requestScope.notify_changepswd}"/></p>
            </c:if>
            <div <%--id="hidden_div"--%>>
                <form name="changepswdfrm" method="POST" action="changepswd" onsubmit="return validateChangePswdForm();">
                    <p>Enter old password <span class="mandatory">*</span>:</p>
                    <input type="password" name="oldpswd" maxlength="20" />
                    <span id="span_oldpswd"><p>Invalid password!</p></span>
                    <c:if test="${requestScope.notify_wrongpswd==true}">
                        <p style="color: #bc0f0f;"> You've entered wrong password, please enter your password.</p>
                    </c:if>
                    <p>Enter new password <span class="mandatory">*</span>:</p>
                    <input type="password" name="newpswd1" maxlength="20" />
                    <span id="span_newpswd1"><p>Invalid password!</p></span>
                    <p>Confirm new password <span class="mandatory">*</span>:</p>
                    <input type="password" name="newpswd2" maxlength="20" />
                    <span id="span_newpswd2"><p>Passwords don't match!</p></span>
                    <br>
                    <p class="submit">
                        <input type="hidden" name="oldpswd_hidden" value=""/>
                        <input type="hidden" name="password" value=""/>
                        <input type="reset" name="reset" value="Reset">
                        <input type="submit" name="confirm" value="Confirm">
                    </p>
                </form>
            </div>
        </div>
<%--        <div id="tabs">
            &lt;%&ndash;<c:set var="tabs" value="${fn:split('#tabs-1,#tabs-2,#tabs-3,#tabs-4', ',')}" scope="session" />&ndash;%&gt;
            <ul>
                &lt;%&ndash;            <c:forEach items="${tabs}" var="tabs">
                                <li><a href="${tabs}">${tabs}</a></li>
                            </c:forEach>&ndash;%&gt;
                <li><a href="#tabs-1">Change Password</a></li>
                <li><a href="#tabs-2">My Orders</a></li>

            </ul>
                <div id="tabs-1"><!-- 'Users' tab -->--%>

                <%--</div><!-- #tab1 -->

                <div id="tabs-2"><!-- 'View My orders' tab-->
                    <a href="viewpastbooking">View my booking</a>
                    <form name="viewpastbook" method="POST" onsubmit="">
                        <c:if test="${requestScope.bookinglist!=null}">

                            <table cellpadding="0" cellspacing="0" border="0" id="table" class="sortable">
                                <thead>
                                <tr>

                                    <th><h3>Type</h3></th>
                                    <th><h3>Hotel Name</h3></th>
                                    <th><h3>Hotel City</h3></th>
                                    <th><h3>Order's date</h3></th>
                                    <th><h3>Start date</h3></th>
                                    <th><h3>End date</h3></th>
                                    <th><h3>Price</h3></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${requestScope.bookinglist}" var="b">
                                    <tr>

                                        <td><c:out value="${b.type}"/></td>
                                        <td><c:out value="${b.hotelName}"/></td>
                                        <td><c:out value="${b.hotelCity}"/></td>
                                        <td><c:out value="${b.dateOrder}"/></td>
                                        <td><c:out value="${b.startOrder}"/></td>
                                        <td><c:out value="${b.endOrder}"/></td>
                                        <td><c:out value="${b.price}"/></td>

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
                    </form>

                </div> <!-- #tab2 -->
            &lt;%&ndash; <a href="ordershistory">Show my orders history</a>&ndash;%&gt;
        </div> <!-- #tabs -->--%>
    </div><!-- #content -->

    <div class="footer">
    </div><!-- #footer -->

</div><!-- #wrapper -->

</body>

</html>