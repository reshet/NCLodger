<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>NCLodger | User dashboard</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css" />
    <!--[if lt IE 7]>
    <style type="text/css">
        #wrapper { height:100%; }
    </style>
    <![endif]-->
    <script>

    </script>
</head>

<body>

<div id="wrapper">

    <div id="header">
        <jsp:include page="header.jsp"/>
    </div><!-- #header -->

    <div id="content">
    <p>User information: </p>
    <p>Username:<c:out value="${sessionScope.username}"/></p>
    <p>Email:<c:out value="${sessionScope.email}"/></p>
    </br>
    <form name="changepswdfrm" method="POST" action="chnagepswd" onsubmit="">
        <p>Enter old password:</p>
        <input type="text" name="oldpswd" id="oldpassword" maxlength="20" />
        <p>Enter new password:</p>
        <input type="text" name="newpswd1" id="newpswd1" maxlength="20" />
        <p>Confirm new password:</p>
        <input type="text" name="newpswd2" id="newpswd2" maxlength="20" />
        <p class="submit">
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