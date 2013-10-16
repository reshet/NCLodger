<%--
  Created by IntelliJ IDEA.
  User: Iaro
  Date: 14.10.13
  Time: 12:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>NCLodger | Regestration</title>
    <%--<base href="${pageContext.request.contextPath}">--%>
    <link rel="stylesheet" type="text/css" href="<c:url value = "/static/css/style.css" />" />

    <!--[if lt IE 7]>
    <style type="text/css">
        #wrapper { height:100%; }
    </style>
    <![endif]-->

</head>

<body>

<div id="wrapper">

    <div id="header">
        <div class="nav">
            <!--<img src="img/header.png">-->
            <ul>
                <li><a href="#">Home</a></li>
                <li><a href="#">About Us</a></li>
                <li><a href="#">Contacts</a></li>
                <li><a href="<c:url value = "/regestration.jsp" />">Register</a></li>
            </ul>
        </div>
    </div><!-- #header -->

    <div id="content">

        <div class="login" >
            <h1>Login</h1>
            <form method="post" action="<c:url value = "/index.jsp" />">
                <p><input type="text" name="email" value="" placeholder="Email"></p>
                <p><input type="password" name="password" value="" placeholder="Password"></p>
                <a href="<c:url value = "/regestration.jsp" />">Haven't got account yet?</a></br>
                <a href="">Login with GOOGLE?</a>
                <p class="submit">
                    <input type="submit" name="commit" value="Login">
                    <input type="submit" name="commit" value="Login as guest">
                </p>
            </form>
        </div>

        <div class="login-help">
            <p>Forgot your password? <a href="<c:url value = "/index.jsp" />">Click here to reset it</a>.</p>
        </div>

    </div><!-- #content -->

    <div id="footer">
    </div><!-- #footer -->

</div><!-- #wrapper -->

</body>

</html>