<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>NCLodger | Accommodation Details</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css" />
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
</head>
<body>

    <div class="wrapper">

        <jsp:include page="header.jsp"/>

        <div class="content">
            <div class="window" style="width:450px;">
                <h1>Your booking succeded!</h1>
                <form>
                <p style="padding-left: 10px;">
                    <c:if test="${hotel.getImage_url() != null}">
                        <img src="${hotel.getImage_url()}" style="float:right; width: 50px; height: 50px;"/>
                    </c:if>
                    <c:if test="${hotel.getImage_url() == null}">
                        <img src="resources/img/noimage.gif" style="float:right; width: 50px; height: 50px;"/>
                    </c:if>
                </p>
                <p>Hotel: ${hotel.getName()} </p>
                <p>Address: ${hotel.getAddress()} </p>
                <p>Type: ${hotel.getRoomType()} </p>
                <p>Prices: ${hotel.getPrice()} </p>
                <p>Occupancy: ${hotel.getRoomOccupancy()} </p>
                <p>Your promo entered: ${requestScope.promocode} </p>
                <p><h3>You were billed for: ${requestScope.finalprice}</h3></p>
                </form>
            </div>
        </div>

        <div class="footer">
        </div>

    </div><!-- .wrapper -->

</body>
</html>
