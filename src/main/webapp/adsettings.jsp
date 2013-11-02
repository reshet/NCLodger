<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>NCLodger | Settings: administrator</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<!--[if lt IE 7]>
<style type="text/css">
     #wrapper { height:100%; }
</style>
<![endif]-->
</head>

<body>

<div id="wrapper">

    <div id="header">
        <div class="greeting" style="float: right; padding-right: 2em; ">
            <%
                if(session.getAttribute("username") == null){
            %>
                    <a href="login.jsp">Log in</a> / <a href="registration.jsp">Register</a>
            <%
                }
                else {
            %>
                Hello, <%=session.getAttribute("username")%>!
                <br><a href="signout">Log out</a>
            <%
                }
            %>
            <%
                if((Integer)session.getAttribute("utype") == (Integer)3) {
            %>
            <br><a href="adsettings" class="orangelink"><img src="img/user.gif">Sales manager actions</a>
            <% } %>
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
        <div class="search">

        </div>
    </div><!-- #content -->

    <div id="footer">
    </div><!-- #footer -->

</div><!-- #wrapper -->

</body>

</html>