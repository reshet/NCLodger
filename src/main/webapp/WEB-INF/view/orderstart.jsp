<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>NCLodger | Ordering Details</title>
    <link rel="stylesheet" type="text/css" href="/NCLodger/resources/css/style.css" />
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <%--<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />--%>
    <script type="text/javascript">
        function validateOrderForm(){
            var isValid = true;
            if(((document.orderfrm.promocode.value != "") && !(/^[a-zA-Z0-9-]+$/).test(document.orderfrm.promocode.value))){
                isValid = false;
                document.orderfrm.promocode.style.borderColor = 'red';
            } else {
                document.orderfrm.promocode.style.borderColor = '#777777';
            }
            return isValid;
        }
    </script>
</head>
<body>
    <div class="wrapper">
        <jsp:include page="header.jsp"/>

        <div class="content">
            <div class="window" style="width: 450px;">
               <h1>Ordering Details</h1>
                <p style="float:right;">
                    <c:if test="${hotel.getImage_url() != null}">
                        <img src="${hotel.getImage_url()}" style="float:right; width: 50px; height: 50px;"/>
                    </c:if>
                    <c:if test="${hotel.getImage_url() == null}">
                        <img src="../../resources/img/noimage.gif" style="float:right; width: 50px; height: 50px;"/>
                    </c:if>
                </p>
                <p>Hotel: ${hotel.getName()} </p>
                <p>Address: ${hotel.getAddress()}</p>
                <p>Type: ${hotel.getRoomType()}</p>
                <p>Prices: ${hotel.getPrice()}</p>
                <p>Occupancy: ${hotel.getRoomOccupancy()}</p>
                <p style="color:#0000ff;">Base price to pay: ${hotel.getRoomPrice()}</p><br>
                <form name="orderfrm" method="POST" action="orderfinish" onsubmit="return validateOrderForm();">
                    <p>Enter promo code if you have one:  <input type="text" name="promocode" style="width: 150px;" maxlength="20"></p>
                    <c:if test="${requestScope.isExist==false}">
                        <p style="color: #bc0f0f;">Such promo code doesn't exist.</p>
                    </c:if>
                    <c:if test="${requestScope.isUsed==true}">
                        <p style="color: #bc0f0f;">This promo code has already been used.</p>
                    </c:if>
                    <c:if test="${requestScope.isExpired==true}">
                        <p style="color: #bc0f0f;">This promo code has been expired.</p>
                    </c:if>
                    <p class="submit"><input type="submit" text="Make Payment" value="Make Payment"></p>
                </form>
            </div>
        </div><!-- .content -->
        <jsp:include page="footer.jsp"/>
        <%--<div class="footer">
        </div>--%>
    </div><!-- .wrapper -->
</body>
</html>