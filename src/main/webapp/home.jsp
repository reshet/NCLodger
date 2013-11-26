<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>NCLodger | Home</title>
<link rel="stylesheet" type="text/css" href="resources/css/style.css" />
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<!--[if lt IE 7]>
<style type="text/css">
     #wrapper { height:100%; }
</style>
<![endif]-->
<script>
    $(function() {
        $("#checkindate").datepicker();
        $("#checkoutdate").datepicker();
    })

    function valideSearchForm() {
        var isValid = true;
        var myDate = new Date();
        var curYear = myDate.getFullYear();
        var curMonth = myDate.getMonth();
        var curDay = myDate.getDay();
        var message = "Dear user, you might have entered invalid parameters:\n";
        if(document.searchfrm.min_price.value != "") {
            if(document.searchfrm.min_price.value < 0 || (!(/^[0-9]+$/).test(document.searchfrm.min_price.value))) {
                message += "\n- Minimum price"
                isValid = false;
            }
        }
        if(document.searchfrm.max_price.value != "") {
            if(document.searchfrm.max_price.value < 0 || (!(/^[0-9]+$/).test(document.searchfrm.max_price.value))
                    || document.searchfrm.max_price.value < document.searchfrm.min_price.value) {
                message += "\n- Maximum price"
                isValid = false;
            }
        }
        if(document.searchfrm.checkindate.value == "") {
                message += "\n- Check in date wasn't selected"
                isValid = false;
        }
        if(document.searchfrm.checkoutdate.value == "") {
            message += "\n- Checkout date wasn't selected"
            isValid = false;
        }
        if(document.searchfrm.country.value == ""){
            message += "\n- Country wasn't selected"
            isValid = false;
        }
        if(document.searchfrm.city.value == "") {
            message += "\n- City wasn't selected"
            isValid = false;
        }
        /*if( (curYear < document.searchfrm.checkin_year.value)
            || (curYear == document.searchfrm.checkin_year.value && curMonth < document.searchfrm.checkin_month.value)
            || (curYear == document.searchfrm.checkin_year.value && curMonth == document.searchfrm.checkin_month.value && curDay <= document.searchfrm.checkin_day.value)
           ) {
            if(document.searchfrm.checkin_year.value == document.searchfrm.checkout_year.value){
                if(document.searchfrm.checkin_month.value == document.searchfrm.checkout_month.value){
                    if(document.searchfrm.checkout_day.value <= document.searchfrm.checkin_day.value){
                        message += "\nCheckout day;"
                        isValid = false;
                    }
                }
                if (document.searchfrm.checkout_month.value < document.searchfrm.checkin_month.value){
                    message += "\nCheckout month;"
                    isValid = false;
                }
            }
            if (document.searchfrm.checkout_year.value < document.searchfrm.checkin_year.value){
                message += "\nCheckout year;"
                isValid = false;
            }
        }
        else {
            message += "\nCheckin/checkout date is old;"
            isValid = false;
        }*/
        if(!isValid) {
            alert(message);
        }
        return isValid;
    }
</script>
</head>

<body>
<div id="wrapper">

    <div id="header">
        <jsp:include page="header.jsp"/>
    </div><!-- #header -->

    <div id="content">
        <div class="search">
            <form name="searchfrm" method="POST" action="search" onsubmit="return valideSearchForm();">
                <ul>
                    <li> <!--    CHECK IN    -->
                        Check in date: *&nbsp&nbsp
                        <input id="checkindate" name="checkindate" style="width:100px;" value="<%=session.getAttribute("checkindate")!=null?session.getAttribute("checkindate"):"" %>"/>
                    </li>
                    <c:set var="countries" value="${fn:split('UA,USA', ',')}" scope="session" />
                    <c:set var="cities" value="${fn:split('Lviv,Kiev', ',')}" scope="session" />
                    <c:set var="adults" value="${fn:split('1,2,3,4,5,6,7,8', ',')}" scope="session" />
                    <c:set var="children" value="${fn:split('0,1,2,3,4,5,6,7,8', ',')}" scope="session" />
                    <c:set var="currencies" value="${fn:split('USD,EUR,UAH', ',')}" scope="session" />
                    <li>
                        Country: *
                        <select id="country" name="country" style="width: 180px;">
                            <option value=""> none </option>
                            <c:forEach items="${countries}" var="country">
                                <option value="${country}" ${sessionScope.country == country ? 'selected' : ''}>${country}</option>
                            </c:forEach>
                        </select>
                    </li>
                    <li>
                        City: *
                        <select id="city" name="city" style="width: 180px;">
                                <option value=""> none </option>
                                <c:forEach items="${cities}" var="city">
                                    <option value="${city}" ${sessionScope.city == city ? 'selected' : ''}>${city}</option>
                                </c:forEach>
                        </select>
                    </li>
                </ul>
                <ul>
                    <li>  <!--    CHECK OUT   -->
                        Check out date: *
                        <input id="checkoutdate" name="checkoutdate" style="width:100px;" value="<%=session.getAttribute("checkoutdate")!=null?session.getAttribute("checkoutdate"):"" %>"/>
                    </li>
                    <li>
                        Price:
                        <input type="text" id="min_price" name="min_price" maxlength="9" value="<%=session.getAttribute("min_price")!=null?session.getAttribute("min_price"):"" %>" style="width: 100px;"/> to
                        <input type="text" id="max_price" name="max_price" maxlength="9" value="<%=session.getAttribute("max_price")!=null?session.getAttribute("max_price"):"" %>" style="width: 100px;"/>
                        <select id="currency" name="currency" style="width: 100px">
                            <c:forEach items="${currencies}" var="currency">
                                <option value="${currency}" ${sessionScope.currency == currency ? 'selected' : ''}>${currency}</option>
                            </c:forEach>

                        </select>
                    </li>
                </ul>
                <ul>
                    <li>
                        Adults(18+):
                        <select id="guests_adults" name="guests_adults" style="width: 80px">
                            <c:forEach items="${adults}" var="adult">
                                <option value="${adult}" ${sessionScope.guests_adults == adult ? 'selected' : ''}>${adult}</option>
                            </c:forEach>
                        </select>
                    </li>
                    <li>
                        Children:
                        <select id="guests_children" name="guests_children" style="width: 80px" selected="<%=session.getAttribute("guests_children")!=null?session.getAttribute("guests_children"):"" %>">
                            <c:forEach items="${children}" var="children">
                                <option value="${children}" ${sessionScope.guests_children == children ? 'selected' : ''}>${children}</option>
                            </c:forEach>
                        </select>
                    </li>
                    <li class="submit">
                        <input type="reset" name="reset" value="Reset">
                        <input type="submit" name="search" value="Search">
                    </li>
                </ul>
            </form>
        </div>
        <c:forEach items="${requestScope.hotels}" var="hotel">
            <div class="window" style="width: 700px; background: #F0F0F0;">
                <h2>${hotel.getName()}</h2>
                <p style="padding-left: 10px;">
                   <c:if test="${hotel.getImage_url() != null}">
                    <img src="${hotel.getImage_url()}" style=""/>
                   </c:if>
                </p>
                <p><b>Address:</b>    ${hotel.getAddress()}</p>
                <p><b>Type:</b>   ${hotel.getRoomType()}</p>
                <p><b>Prices:</b>   ${hotel.getPrice()}</p>
                <p><b>Occupancy:</b>   ${hotel.getRoomOccupancy()}</p>
                <p><b><a href="acdetails?idhotel=${hotel.id}" style="color:#fa8736;">Details &rarr;</a></b></p>

            </div>
        </c:forEach>

    </div><!-- #content -->

    <div id="footer">
    </div><!-- #footer -->

</div><!-- #wrapper -->

</body>

</html>
