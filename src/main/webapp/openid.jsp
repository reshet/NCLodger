<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>NCLodger | Authentication </title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css" />
    <script src="resources/js/md5-min.js"></script>
    <script src="//loginza.ru/js/widget.js" type="text/javascript"></script>


</head>

<body>

<div class="wrapper">
    <jsp:include page="header.jsp"/>

    <div class="content">
        <div class="window" >
            <h1>Enter with openID</h1>
            <form name="openfrm" method="POST">
             <!--
		                <label for="openid_url">Your OpenID URL: </label>
		                <input type="url" name="openid:url" id="openid_url" value=""/>
                                                                                      -->
                <a href="https://loginza.ru/api/widget?token_url=http://openid-provider.appspot.com/nclodger&lang=en" class="loginza">Enter with OpenID</a>
                <p class="submit">
                    <input type="submit" name="submit" value="Submit">
                    <input type="reset" name="reset" value="Reset">
                </p>

            </form>

        </div>
    </div>
    <jsp:include page="footer.jsp"/>


</div>

</body>

</html>