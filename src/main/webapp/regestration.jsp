<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>NCLodger | Home Page</title>
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <script src="js/reg.js" type="text/javascript"></script>

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
                <li><a href="index.jsp">Home</a></li>
                <li><a href="#">About Us</a></li>
                <li><a href="#">Contacts</a></li>
            </ul>
        </div>
    </div><!-- #header -->

    <div id="content">

        <div class="login" >
            <h1>Regestration</h1>
            <form method="post" action="<c:url value = "/register" />">
                <p><input type="text" id="uname" name="username" value="" placeholder="Username" onkeyup="check();" /></p>
                <span id="span_uname" >Username must contain only latin letters and not less than 6 symbols!</span>
                <p><select name="user_type">
                    <option value="customer">Customer</option>
                    <option value="sales manager">Sales manager</option>
                </select></p>
                <p><input type="text" id="email" name="email" value="" placeholder="Email" onkeyup="check();" /></p>
                <span id="span_email" >Wrong email input!</span>
                <p><input type="password" id="pswd1" name="password" value="" placeholder="Password" onkeyup="check();"></p>
                <span id="span_pswd1" >Password must contain only latin letter, numbers and not less than 6 symbols!</span>
                <p><input type="password" id="pswd2" name="password" value="" placeholder="Confirm password" onkeyup="check();"></p>
                <span id="span_pswd2" >Passwords do not match!</span>
                <p class="submit">
                    <input type="submit" name="commit" value="Submit">
                </p>
            </form>
        </div>

    </div><!-- #content -->

    <div id="footer">
    </div><!-- #footer -->

</div><!-- #wrapper -->

</body>

</html>

