<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- TOP MENU -->
<div class="header">
    <c:if test="${sessionScope.email == null}">
        <ul class="navlist">
            <li><a href="loginpage">Log in</a> / <a href="registrationpage">Register</a></li>
        </ul>
    </c:if>
    <c:if test="${sessionScope.email != null}">
        <div class="navlist">
            <span>Welcome, <c:out value="${sessionScope.username}"/> !</span><a href="signout">(Log out)</a>
            <span> / </span><a href="ussettingspage">Account</a>
        </div>
    </c:if>
</div>

<!-- MAIN MENU -->
<div class="mainmenu">
    <ul>
        <li><a class="logo" href="home.jsp">NCLodger</a></li>
        <li><a href="home.jsp">Home</a></li>
        <c:if test="${sessionScope.email != null}">
            <li><a href="myorderspage">My Orders</a></li>
            <c:if test="${sessionScope.utype != 1}">
                <li><a href="smsettings">Sales Manager</a></li>
            </c:if>
            <c:if test="${sessionScope.utype == 3}">
                <li><a href="adsettings">Administrator</a></li>
            </c:if>
        </c:if>
        <li><a href="aboutus">About Us</a></li>
        <li><a href="contacts">Contacts</a></li>
    </ul>
</div>