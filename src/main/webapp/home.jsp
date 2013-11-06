<%@ page import="java.util.List" %>
<%@ page import="com.nclodger.webservices.Hotel" %>
<%@ page import="com.nclodger.webservices.ExpediaSearcher" %>
<%@ page import="org.json.JSONObject" %>
<%@ page import="org.json.JSONException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>NCLodger | Home</title>
<link rel="stylesheet" type="text/css" href="resources/css/style.css" />
<!--[if lt IE 7]>
<style type="text/css">
     #wrapper { height:100%; }
</style>
<![endif]-->
<script>
    function valideSearchForm() {
        var isValid = true;
        var myDate = new Date();
        var curYear = myDate.getFullYear();
        var curMonth = myDate.getMonth();
        var curDay = myDate.getDay();
        var message = "Dear user, you might have entered invalid parameters:\n";
        /*alert("PASSED:"+curMonth+"-"+curDay+"-"+curYear);*/
        if(document.searchfrm.min_price.value != ""){
            if(document.searchfrm.min_price.value < 0 || (!(/^[0-9]+$/).test(document.searchfrm.min_price.value))) {
                message += "\nMinimum price; "
                isValid = false;
            }
        }
        if(document.searchfrm.max_price.value != ""){
            if(document.searchfrm.max_price.value < 0 || (!(/^[0-9]+$/).test(document.searchfrm.max_price.value))
                    || document.searchfrm.max_price.value < document.searchfrm.min_price.value) {
                message += "\nMaximum price; "
                isValid = false;
            }
        }
        if(document.searchfrm.checkin_month.value == ""
                || document.searchfrm.checkin_day.value == "" || document.searchfrm.checkin_year.value == ""){
                message += "\nCheckin date wasn't selected;"
                isValid = false;
        }
        if(document.searchfrm.checkout_month.value == ""
                || document.searchfrm.checkout_day.value == "" || document.searchfrm.checkout_year.value == ""){
            message += "\nCheckout date wasn't selected;"
            isValid = false;
        }
        if(document.searchfrm.country.value == ""){
            message += "\nCountry wasn't selected;"
            isValid = false;
        }
        if(document.searchfrm.city.value == ""){
            message += "\nCity wasn't selected;"
            isValid = false;
        }
        if( (curYear < document.searchfrm.checkin_year.value)
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
        }
        if(!isValid) {
            alert(message);
        }
        return isValid;
    }
</script>
</head>
    <%----%>
<body>

<div id="wrapper">

    <div id="header">
        <div class="greeting">
            <%
                if(session.getAttribute("username") == null){
            %>
                    <a href="login.jsp">Log in</a> / <a href="registration.jsp">Register</a>
            <%
                }
                else {
            %>
                    Hello, <%=session.getAttribute("username")%>!
                    <br><a href="signout">Log out</a>
                    <br><a href="" class="orangelink"><img src="img/user.gif">User dashboard</a>
            <% } %>
            <%
                if((Integer)session.getAttribute("utype") == (Integer)2) {
            %>
                    <br><a href="smsettings" class="orangelink"><img src="img/user.gif">Sales manager dashboard</a>
            <% } %>
            <%
                if((Integer)session.getAttribute("utype") == (Integer)3) {
            %>
                    <br><a href="adminsettings" class="orangelink"><img src="img/user.gif">Administrator dashboard</a>
            <% } %>
        </div>
        <div class="nav">
            <ul>
                <li><a href="home.jsp"><h1>NCLodger</h1></a></li>
                <li><a href="home.jsp">Home</a></li>
                <li><a href="#">About Us</a></li>
                <li><a href="#">Contacts</a></li>
            </ul>
        </div>
    </div><!-- #header -->

    <div id="content">
        <div class="search">
            <form name="searchfrm" method="POST" action="search" onsubmit="return valideSearchForm();">
                <ul>
                    <li> <!--    CHECK IN    -->
                        Check in: *&nbsp&nbsp
                        <select id="checkin_month" name="checkin_month">
                            <option value=""> - Month - </option>
                            <option value="1">January</option>
                            <option value="2">Febuary</option>
                            <option value="3">March</option>
                            <option value="4">April</option>
                            <option value="5">May</option>
                            <option value="6">June</option>
                            <option value="7">July</option>
                            <option value="8">August</option>
                            <option value="9">September</option>
                            <option value="10">October</option>
                            <option value="11">November</option>
                            <option value="12">December</option>
                        </select> /
                        <select id="checkin_day" name="checkin_day">
                            <option value=""> - Day - </option>
                            <script>
                                for(var i = 1; i <= 31; i++){
                                    document.write('<option value="'+i+'">'+i+'</option>');
                                }
                            </script>
                        </select> /
                        <select id="checkin_year" name="checkin_year">
                            <option value=""> - Year - </option>
                            <script>
                                var myDate = new Date();
                                var year = myDate.getFullYear();
                                for(var i = year; i <= year+1; i++){
                                    document.write('<option value="'+i+'">'+i+'</option>');
                                }
                            </script>
                        </select>
                    </li>
                    <li>
                        Country: *
                        <select id="country" name="country" style="width: 130px;">
                            <option value=""> - Choose - </option>
                            <option value="UA">Ukraine</option>
                        </select>
                    </li>
                    <li>
                        City: *
                        <select id="city" name="city" style="width: 130px;">
                            <option value=""> - Choose - </option>
                            <option value="Kiev">Kiev</option>
                            <option value="Lviv">Lviv</option>
                        </select>
                    </li>
                </ul>
                <ul>
                    <li>  <!--    CHECK OUT   -->
                        Check out: *
                        <select id="checkout_month" name="checkout_month">
                            <option value=""> - Month - </option>
                            <option value="1">January</option>
                            <option value="2">Febuary</option>
                            <option value="3">March</option>
                            <option value="4">April</option>
                            <option value="5">May</option>
                            <option value="6">June</option>
                            <option value="7">July</option>
                            <option value="8">August</option>
                            <option value="9">September</option>
                            <option value="10">October</option>
                            <option value="11">November</option>
                            <option value="12">December</option>
                        </select> /
                        <select id="checkout_day" name="checkout_day">
                            <option value=""> - Day - </option>
                            <script>
                                for(var i = 1; i <= 31; i++){
                                    document.write('<option value="'+i+'">'+i+'</option>');
                                }
                            </script>
                        </select> /
                        <select id="checkout_year" name="checkout_year">
                            <option value=""> - Year - </option>
                            <script>
                                var myDate = new Date();
                                var year = myDate.getFullYear();
                                for(var i = year; i <= year+1; i++){
                                    document.write('<option value="'+i+'">'+i+'</option>');
                                }
                            </script>
                        </select>
                    </li>
                    <li>
                        Price:
                        <input type="text" id="min_price" name="min_price" /> to
                        <input type="text" id="max_price" name="max_price" />
                        <select id="currency" name="currency" style="width: 100px">
                            <option value="$">$</option>
                            <option value="$">€</option>
                            <option value="UAH">UAH</option>
                        </select>
                    </li>
                </ul>
                <ul>
                    <li>
                        Adults(18+):
                        <select id="guests_adults" name="guests_adults" style="width: 60px">
                            <option value="">-</option>
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                            <option value="6">6</option>
                            <option value="7">7</option>
                            <option value="8">8</option>
                        </select>
                    </li>
                    <li>
                        Children:
                        <select id="guests_children" name="guests_children" style="width: 60px">
                            <option value="">-</option>
                            <option value="0">0</option>
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                            <option value="6">6</option>
                            <option value="7">7</option>
                            <option value="8">8</option>
                        </select>
                    </li>
                    <li class="submit">
                            <input type="reset" name="reset" value="Reset">
                            <input type="submit" name="search" value="Search">
                    </li>
                </ul>
            </form>
        </div>

            <%
                if(request.getAttribute("servlet_value") != null) {
                    List<Hotel> hotels = (List) request.getAttribute("servlet_value");

                    for(Hotel hotel : hotels) {
                        out.println("<div class=\"search\">");
                        out.print(hotel.getName()+"<br/>");
                        out.print("Location: "+hotel.getLoc_lat()+", "+hotel.getLoc_lng()+"<br/><br/>");
                        out.println("</div>");
                    }
                }
            %>

    </div><!-- #content -->

    <div id="footer">
    </div><!-- #footer -->

</div><!-- #wrapper -->

</body>

</html>
