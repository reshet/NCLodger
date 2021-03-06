<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>NCLodger | Exception</title>
<link rel="stylesheet" type="text/css" href="resources/css/style.css" />
</head>

<body>

<div class="wrapper">
    <jsp:include page="header.jsp"/>
    <div class="content">
        <div class="window" >
            <h1>Exception occurred while processing!</h1>
            <p>
                Message:
                <c:out value="${requestScope.error_message}"/>
            </p>
        </div>
    </div><!-- .content -->
    <jsp:include page="footer.jsp"/>
</div><!-- .wrapper -->

</body>

</html>