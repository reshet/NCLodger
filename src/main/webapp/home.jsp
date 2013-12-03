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
<script>
    $(function() {
        $( "#checkindate" ).datepicker({
            minDate: new Date(),
            onClose: function( selectedDate ) {
             var d = new Date(selectedDate);
                d.setHours(d.getHours()+24);
                $( "#checkoutdate" ).datepicker( "option", "minDate", d );
            }
        });
        $( "#checkoutdate" ).datepicker({
            onClose: function( selectedDate ) {
                var d = new Date(selectedDate);
                d.setHours(d.getHours()-24);
                $( "#checkindate" ).datepicker( "option", "maxDate", d);
            }
        });
    });

    function valideSearchForm() {
        var isValid = true;
        if(document.searchfrm.min_price.value != "") {
            if(document.searchfrm.min_price.value < 0 || (!(/^[0-9]+$/).test(document.searchfrm.min_price.value))) {
                isValid = false;
                document.searchfrm.min_price.style.borderColor = 'red';
                document.searchfrm.max_price.style.borderColor = 'red';
            } else {
                document.searchfrm.min_price.style.borderColor = '#777777';
                document.searchfrm.max_price.style.borderColor = '#777777';
            }
        }
        if(document.searchfrm.max_price.value != "") {
            if(document.searchfrm.max_price.value < 0 || (!(/^[0-9]+$/).test(document.searchfrm.max_price.value))
                    || document.searchfrm.max_price.value < document.searchfrm.min_price.value) {
                isValid = false;
                document.searchfrm.min_price.style.borderColor = 'red';
                document.searchfrm.max_price.style.borderColor = 'red';
            } else {
                document.searchfrm.min_price.style.borderColor = '#777777';
                document.searchfrm.max_price.style.borderColor = '#777777';
            }
        }
        if(document.searchfrm.checkindate.value == "") {
            isValid = false;
            document.searchfrm.checkindate.style.borderColor = 'red';
        } else {
            document.searchfrm.checkindate.style.borderColor = '#777777';
        }
        if(document.searchfrm.checkoutdate.value == "") {
            isValid = false;
            document.searchfrm.checkoutdate.style.borderColor = 'red';
        } else {
            document.searchfrm.checkoutdate.style.borderColor = '#777777';
        }
        if(document.searchfrm.country.value == ""){
            isValid = false;
            document.searchfrm.country.style.borderColor = 'red';
        } else {
            document.searchfrm.country.style.borderColor = '#777777';
        }
        if(document.searchfrm.city.value == "") {
            isValid = false;
            document.searchfrm.city.style.borderColor = 'red';
        } else {
            document.searchfrm.city.style.borderColor = '#777777';
        }
        return isValid;
    }
</script>
</head>

<body>
<div class="wrapper">

    <jsp:include page="header.jsp"/>

    <div class="content">
        <div class="search">
            <form name="searchfrm" method="POST" action="search" onsubmit="return valideSearchForm();">
                <ul>
                    <li> <!--    CHECK IN    -->
                        <b>Check in date <span class="mandatory">*</span>:</b>&nbsp&nbsp
                        <input id="checkindate" name="checkindate" style="width:100px;" value="<%=session.getAttribute("checkindate")!=null?session.getAttribute("checkindate"):"" %>"/>
                    </li>
                    <c:set var="countries" value="${fn:split('UA,USA', ',')}" scope="session" />
                    <c:set var="cities" value="${fn:split('Lviv,Kiev', ',')}" scope="session" />
                    <c:set var="adults" value="${fn:split('1,2,3,4,5,6,7,8', ',')}" scope="session" />
                    <c:set var="children" value="${fn:split('0,1,2,3,4,5,6,7,8', ',')}" scope="session" />
                    <c:set var="currencies" value="${fn:split('USD,EUR,UAH', ',')}" scope="session" />
                    <li>
                        <b>Country <span class="mandatory">*</span>:</b>
                        <select id="country" name="country" style="width: 180px;">
                            <option value=""></option>
                            <c:forEach items="${countries}" var="country">
                                <option value="${country}" ${sessionScope.country == country ? 'selected' : ''}>${country}</option>
                            </c:forEach>
                        </select>
                    </li>
                    <li>
                        <b>City <span class="mandatory">*</span>:</b>
                        <select id="city" name="city" style="width: 180px;">
                                <option value=""></option>
                                <c:forEach items="${cities}" var="city">
                                    <option value="${city}" ${sessionScope.city == city ? 'selected' : ''}>${city}</option>
                                </c:forEach>
                        </select>
                    </li>
                </ul>
                <ul>
                    <li>  <!--    CHECK OUT   -->
                        <b>Check out date <span class="mandatory">*</span>:</b>
                        <input id="checkoutdate" name="checkoutdate" style="width:100px;" value="<%=session.getAttribute("checkoutdate")!=null?session.getAttribute("checkoutdate"):"" %>"/>
                    </li>
                    <li>
                        <b>Price:</b>
                        <input type="text" id="min_price" name="min_price" maxlength="9" value="<%=session.getAttribute("min_price")!=null?session.getAttribute("min_price"):"" %>" style="width: 100px;"/> <b>to</b>
                        <input type="text" id="max_price" name="max_price" maxlength="9" value="<%=session.getAttribute("max_price")!=null?session.getAttribute("max_price"):"" %>" style="width: 100px;"/>
                    </li>
                    <li>
                        <select id="currency" name="currency" style="width: 100px">
                            <c:forEach items="${currencies}" var="currency">
                                <option value="${currency}" ${sessionScope.currency == currency ? 'selected' : ''}>${currency}</option>
                            </c:forEach>
                        </select>
                    </li>
                </ul>
                <ul>
                    <li>
                        <b>Adults(18+):</b>
                        <select id="guests_adults" name="guests_adults" style="width: 80px">
                            <c:forEach items="${adults}" var="adult">
                                <option value="${adult}" ${sessionScope.guests_adults == adult ? 'selected' : ''}>${adult}</option>
                            </c:forEach>
                        </select>
                    </li>
                    <li>
                        <b>Children:</b>
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
        </div><!-- Search form -->

        <c:if test="${requestScope.hotels != null}">
        <div class="hotel_info_wrapper">
            <c:forEach items="${requestScope.hotels}" var="hotel">
                <div class="hotel_info">
                    <p>
                        Hotel: <span>${hotel.getImage_url()}</span>
                        <c:if test="${hotel.getImage_url() != null}">
                            <img src="${hotel.getImage_url()}" style=""/>
                        </c:if>
                        <c:if test="${hotel.getImage_url() == null}">
                            <img src="resources/img/noimage.gif"/>
                        </c:if>
                    </p>
                    <p>Address: <span>${hotel.getAddress()}</span></p>
                    <p>Type: <span>${hotel.getRoomType()}</span></p>
                    <p>Prices: <span>${hotel.getPrice()}</span></p>
                    <p>Occupancy: <span>${hotel.getRoomOccupancy()}</span></p>
                    <p class="submit">
                        <a href="acdetails?idhotel=${hotel.id}" style="color:#0000ff;">Details &rarr;</a>
                    </p>
                </div>
            </c:forEach>
        </div>
        </c:if>

<%--
        <!-- EXAMPLE WITH STATIC VALUES -->
        <div class="hotel_info_wrapper">
            <div class="hotel_info">
            <p><img src="resources/img/noimage.gif"/> <span><b>Name going to be here </b></span></p>
            <p>Address: <span>Adress is going to be </span></p>
            <p>Type:   <span>Type is going to be here </span></p>
            <p>Prices:   <span>price is going to be here</span></p>
            <p>Occupancy:   <span>occupancy</span></p>
            <p class="submit">
                <a href="acdetails?idhotel=${hotel.id}" style="color:#0000ff;">Details &rarr;</a>
            </p>
        </div>
            <div class="hotel_info">
                <p><img src="resources/img/noimage.gif"/></p>
                <p>Address: <span>Adress is going to be here </span></p>
                <p>Type:   <span>Type is going to be here </span></p>
                <p>Prices:   <span>price is going to be here</span></p>
                <p>Occupancy:   <span>occupancy</span></p>
                <p class="submit">
                    <a href="acdetails?idhotel=${hotel.id}" style="color:#0000ff;">Details &rarr;</a>
                </p>
            </div>
            <div class="hotel_info">
                <p><img src="resources/img/noimage.gif"/></p>
                <p>Address: <span>Adress is going to be here </span></p>
                <p>Type:   <span>Type is going to be here </span></p>
                <p>Prices:   <span>price is going to be here</span></p>
                <p>Occupancy:   <span>occupancy</span></p>
                <p class="submit">
                    <a href="acdetails?idhotel=${hotel.id}" style="color:#0000ff;">Details &rarr;</a>
                </p>
            </div>
            <br style="clear:both;"/>
        </div>--%>

    </div><!-- #content -->

    <div class="footer">
    </div><!-- #footer -->

</div><!-- #wrapper -->

</body>

</html>
