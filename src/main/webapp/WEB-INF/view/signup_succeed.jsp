<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>NCLodger | Registration Succeeded!</title>
<link rel="stylesheet" type="text/css" href="resources/css/style.css" />
</head>

<body>
<div class="wrapper">
    <jsp:include page="header.jsp"/>
    <div class="content">
        <div class="window">
            <h1>Registration has succeeded!</h1>
            <p> Congratulations, <c:out value="${requestScope.username}"/>!</p>
            <p> Confirmation link has been sent to your email address (<span style="color:#0000ff; display:inline;"><c:out value="${requestScope.email}"/></span>).
                Please confirm this letter in 3 days.
            </p>
        </div>
    </div><!-- #content -->
    <jsp:include page="footer.jsp"/>
</div><!-- #wrapper -->

</body>

</html>