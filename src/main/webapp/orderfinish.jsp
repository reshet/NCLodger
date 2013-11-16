<%--
  Created by IntelliJ IDEA.
  User: reshet
  Date: 11/16/13
  Time: 4:53 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.List" %>
<%@ page import="com.nclodger.webservices.Hotel" %>
<%@ page import="com.nclodger.webservices.ExpediaSearcher" %>
<%@ page import="org.json.JSONObject" %>
<%@ page import="org.json.JSONException" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Arrays" %>
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
    <!--[if lt IE 7]>
    <style type="text/css">
        #wrapper { height:100%; }
    </style>
    <![endif]-->
</head>
<body>

<div id="header">
    <jsp:include page="header.jsp"/>
</div>
<h2>Your booking succeded!</h2>
<div id="acdetails" style="padding-left: 30px;">
    <p style="padding-left: 10px;">
        <c:if test="${hotel.getImage_url() != null}">
            <img src="${hotel.getImage_url()}"/>
        </c:if>
        ${hotel.getName()}</p>
    <p>
        Address:   ${hotel.getAddress()}
    </p>
    <p>
        Type:  ${hotel.getRoomType()}
    </p>

    <p>
        Prices:   ${hotel.getPrice()}
    </p>

    <p>
        Occupancy:    ${hotel.getRoomOccupancy()}
    </p>
    <p>
        Your promo entered: ${requestScope.promocode}
    </p>
    <p>
        <h3>You was billed for: ${requestScope.finalprice}</h3>
    </p>

</div>
</body>
</html>