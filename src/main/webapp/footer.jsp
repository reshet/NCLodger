<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- TOP MENU -->
<div class="footer">
    <div class="footermenu">
        <ul>
            <li><a href="home.jsp">Home</a></li>
            <c:if test="${sessionScope.email != null}">
                <li><a href="myorders.jsp">My Orders</a></li>
                <c:if test="${sessionScope.utype == 2}">
                    <li><a href="smsettings">Sales Manager</a></li>
                </c:if>
                <c:if test="${sessionScope.utype == 3}">
                    <li><a href="adsettings">Administrator</a></li>
                </c:if>
            </c:if>
            <li><a href="aboutus.jsp">About Us</a></li>
            <li><a href="contacts.jsp">Contacts</a></li>
        </ul>
        <ul>
            <li><p><b>NCLodger - Group A © 2013</b></p></li>
        </ul>
    </div>
</div>