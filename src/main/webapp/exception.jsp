<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>NCLodger | Exception!</title>
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

        <div class="window" >
            <h1>Exception occurred while processing!</h1>
            <p>
                Message:  <%=
                (String) request.getAttribute("error_message")
                %>

            </p>
        </div>

    </div><!-- #content -->

    <div id="footer">
    </div><!-- #footer -->

</div><!-- #wrapper -->

</body>

</html>