<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>NCLodger | Order finished</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css" />
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
</head>
<body>
    <div class="wrapper">
        <jsp:include page="header.jsp"/>
        <div class="content">
            <div class="window" style="width:450px;">
                <h1>Your booking succeeded!</h1>
                <p style="padding-left: 10px;">
                    <c:if test="${hotel.getImage_url() != null}">
                        <img src="${hotel.getImage_url()}" class="hotel_image"/>
                    </c:if>
                    <c:if test="${hotel.getImage_url() == null}">
                        <img src="resources/img/noimage.gif"  class="hotel_image"/>
                    </c:if>
                </p>
                <p>Hotel: ${hotel.getName()} </p>
                <p>Address: ${hotel.getAddress()} </p>
                <p>Type: ${hotel.getRoomType()} </p>
                <p>Price before discount: ${hotel.getPrice()} </p>
                <p>Occupancy: ${hotel.getRoomOccupancy()} </p>
                <c:if test="${sessionScope.checkindate != null && sessionScope.checkoutdate != null}">
                    <p>
                        Check in date: <c:out value="${sessionScope.checkindate}"/>
                    </p>
                    <p>
                        Check out date: <c:out value="${sessionScope.checkoutdate}"/>
                    </p>
                </c:if>
                <c:if test="${requestScope.promocode != null}">
                    <p>Your promo entered: ${requestScope.promocode} </p>
                </c:if>
                <p><h3>You were billed for: ${requestScope.finalprice} ${sessionScope.currency}</h3></p>
            </div>
        </div>
        <jsp:include page="footer.jsp"/>
    </div><!-- .wrapper -->

</body>
</html>
