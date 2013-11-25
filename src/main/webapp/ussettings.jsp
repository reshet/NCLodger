<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>NCLodger | User dashboard</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css" />
    <script src="resources/js/md5-min.js"></script>
    <!--[if lt IE 7]>
    <style type="text/css">
        #wrapper { height:100%; }
    </style>
    <![endif]-->
    <script>
        function validateChangePswdForm() {

            var isValid = true;

/*
            if(document.changepswdfrm.oldpswd.value.length < 6 || document.changepswdfrm.oldpswd.value.length > 20
                    || (!(/^[a-zA-Z0-9]+$/).test(document.changepswdfrm.oldpswd.value))) {
                document.getElementById("span_oldpswd").style.display = 'none';
                isValid = false;
            }
            else { document.getElementById("span_oldpswd").style.display = 'inline'; }

            if(document.changepswdfrm.newpswd1.value.length < 6 || document.changepswdfrm.newpswd1.value.length > 20
                    || (!(/^[a-zA-Z0-9]+$/).test(document.changepswdfrm.newpswd1.value))) {
                document.getElementById("span_newpswd1").style.display = 'none';
                isValid = false;
            }
            else { document.getElementById("span_newpswd1").style.display = 'inline'; }


            if(document.changepswdfrm.newpswd2.value == document.changepswdfrm.newpswd2.value) {
                document.getElementById("span_newpswd2").style.display = 'none';
            }
            else {
                document.getElementById("span_newpswd2").style.display = 'inline';
                isValid = false;
            }
*/

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
    <p>User information </p>
    <p>Username:<c:out value="${sessionScope.username}"/></p>
    <p>Email:<c:out value="${sessionScope.email}"/></p>
    </br>
    <form name="changepswdfrm" method="POST" action="changepswd" onsubmit="return validateChangePswdForm();">
        <p>Enter old password:</p>
        <input type="password" name="oldpswd" maxlength="20" />
       <%-- <span id="span_oldpswd"><p>Invalid password!</p></span>--%>
        <p>Enter new password:</p>
        <input type="password" name="newpswd1" maxlength="20" />
        <%--<span id="span_newpswd1"><p>Invalid password!</p></span>--%>
        <p>Confirm new password:</p>
        <input type="password" name="newpswd2" maxlength="20" />
        <%--<span id="span_newpswd2"><p>Passwords don't match!</p></span>--%>
        <p class="submit">
            <input type="hidden" name="password" value=""/>
            <p><input type="reset" name="reset" value="Cancel">
            <input type="submit" name="confirm" value="Confirm"></p>
        </p>
    </form>
    </div><!-- #content -->

    <div id="footer">
    </div><!-- #footer -->

</div><!-- #wrapper -->

</body>

</html>