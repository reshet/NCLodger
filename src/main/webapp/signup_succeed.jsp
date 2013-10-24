<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>NCLodger | Registration succeeded</title>
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
        <div class="nav">
            <ul>
                <li><a href="home.jsp">Home</a></li>
                <li><a href="#">About Us</a></li>
                <li><a href="#">Contacts</a></li>
            </ul>
        </div>
    </div><!-- #header -->

    <div id="content">
        <div class="window">
            <%
                String username = request.getParameter("username");
                String email = request.getParameter("email");
            %>
            <%
                if(email == null || username == null)
                    request.getRequestDispatcher("login.jsp").forward(request,response);
            %>
            <h1>Registration has succeeded!</h1>
            <p> Congratulations, <%=username%>!</p>
            <p> Confirmation link has been sent to your email address (<%=email%>).
                Please confirm this letter in 3 days.
            </p>
        </div>
    </div><!-- #content -->

    <div id="footer">
    </div><!-- #footer -->

</div><!-- #wrapper -->

</body>

</html>