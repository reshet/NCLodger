<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>NCLodger | User dashboard</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css"/>
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<%--    <link rel="stylesheet" type="text/css" href="resources/css/style.css" />--%>
    <script src="resources/js/md5-min.js"></script>
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
    <!--[if lt IE 7]>
    <style type="text/css">
        #wrapper { height:100%; }
    </style>
    <style type="text/css">
        .sortable .head h3 { background: url(resources/img/sort.gif) 7px center no-repeat; cursor: pointer; padding-left: 18px }
        .sortable .desc, .sortable .asc { background: #4b708d } /* unsort 2 arrows */
        .sortable .desc h3 { background: url(resources/img/desc.gif) 7px center no-repeat; cursor: pointer; padding-left: 18px } /* selected header */
        .sortable .asc h3 { background: url(resources/img/asc.gif) 7px center no-repeat; cursor: pointer; padding-left: 18px } /* dsc arrpw */
    </style>
    <![endif]-->
    <script>

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
            }

            return isValid;
        }
    </script>
</head>

<body>

<div id="wrapper">

    <div id="header">
        <jsp:include page="header.jsp"/>
    </div><!-- #header -->

    <div id="content">

        <div id="tabs">
            <%--<c:set var="tabs" value="${fn:split('#tabs-1,#tabs-2,#tabs-3,#tabs-4', ',')}" scope="session" />--%>
            <ul>
                <%--            <c:forEach items="${tabs}" var="tabs">
                                <li><a href="${tabs}">${tabs}</a></li>
                            </c:forEach>--%>
                <li><a href="#tabs-1">Change Password</a></li>
                <li><a href="#tabs-2">My Orders</a></li>

            </ul>
                <div id="tabs-1"><!-- 'Users' tab -->
                    <div class="window" style="width: 500px">
                        <h1>User information</h1>
                        <p>Username:<c:out value="${sessionScope.username}"/></p>
                        <p>Email:<c:out value="${sessionScope.email}"/></p>
                        </br>
                        <form name="changepswdfrm" method="POST" action="changepswd" onsubmit="return validateChangePswdForm();">
                            <p>Enter old password:</p>
                            <input type="password" name="oldpswd" maxlength="20" />
                            <span id="span_oldpswd"><p>Invalid password!</p></span>
                            <c:if test="${requestScope.notify_wrongpswd==true}">
                                <p style="color: #bc0f0f;"> You've entered wrong password, please enter your password.</p>
                            </c:if>

                            <p>Enter new password:</p>
                            <input type="password" name="newpswd1" maxlength="20" />
                            <span id="span_newpswd1"><p>Invalid password!</p></span>
                            <p>Confirm new password:</p>
                            <input type="password" name="newpswd2" maxlength="20" />
                            <span id="span_newpswd2"><p>Passwords don't match!</p></span>
                            <p class="submit">
                                <input type="hidden" name="password" value=""/>
                                <input type="reset" name="reset" value="Reset">
                                <input type="submit" name="confirm" value="Confirm">
                            </p>
                        </form>
                        <c:if test="${requestScope.notify_changepswd != null}">
                            <p><c:out value="${requestScope.notify_changepswd}"/></p>
                        </c:if>

                    </div>
                </div><!-- #tab1 -->

                <div id="tabs-2"><!-- 'View My orders' tab-->

                </div> <!-- #tab2 -->
            <%-- <a href="ordershistory">Show my orders history</a>--%>
        </div> <!-- #tabs -->
    </div><!-- #content -->

    <div id="footer">
    </div><!-- #footer -->

</div><!-- #wrapper -->

</body>

</html>