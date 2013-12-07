<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>NCLodger | AC Details</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css" />
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
</head>
<body>

    <div class="wrapper">
        <jsp:include page="header.jsp"/>
        <div class="content">
            <div class="window" style="width:450px;">
                <h1>Accomodation Details</h1>
                <p style="float:right;">
                    <c:if test="${hotel.getImage_url() != null}">
                        <img src="${hotel.getImage_url()}" style="float:right; width: 50px; height: 50px;"/>
                    </c:if>
                    <c:if test="${hotel.getImage_url() == null}">
                        <img src="resources/img/noimage.gif" style="float:right; width: 50px; height: 50px;"/>
                    </c:if>
                </p>
                <c:if test="${requestScope.isOccupied == false}">
                    <p><a href="occupyhotel">Occupy this hotel</a></p>
                </c:if>
                <c:if test="${requestScope.isOccupied == true}">
                    <p style="color:#00FF00;">You occupied this hotel <a href="disposehotel" style="color:#000000">( Dispose )</a>)</p>
                </c:if>
                <p>Hotel: ${hotel.getName()} </p>
                <p>Address: ${hotel.getAddress()}</p>
                <p>Type: ${hotel.getRoomType()}</p>
                <p>Prices: ${hotel.getPrice()}</p>
                <p>Occupancy: ${hotel.getRoomOccupancy()}</p><br>
                <p class="submit">
                    <a href="orderstart">Book this accomodation&rarr;</a>
                </p>
            </div><!-- .window -->
        </div><!-- content -->
        <jsp:include page="footer.jsp"/>
    </div><!-- .wrapper -->
</body>
</html>