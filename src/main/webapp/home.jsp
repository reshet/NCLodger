<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>NCLodger | Home</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<!--[if lt IE 7]>
<style type="text/css">
     #wrapper { height:100%; }
</style>
<![endif]-->
<script>
    function valideSearchForm() {
        var isValid = true;
        var message = "Invalid parameters input: ";
        if(document.searchfrm.min_price.value != ""){
            if(document.searchfrm.min_price.value < 0 || (!(/^[0-9]+$/).test(document.searchfrm.min_price.value))) {
                message += "min price; "
                isValid = false;
            }
        }
        if(document.searchfrm.max_price.value != ""){
            if(document.searchfrm.max_price.value < 0 || (!(/^[0-9]+$/).test(document.searchfrm.max_price.value))
                    || document.searchfrm.max_price.value < document.searchfrm.min_price.value) {
                message += "max price; "
                isValid = false;
            }
        }
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
        <div class="greeting" style="float: right; padding-right: 2em; ">
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
            <%}%>
        </div>

        <div class="nav">
            <ul>
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
                        Check in:
                        <select id="checkin_month" name="checkin_month">
                            <option> - Month - </option>
                            <option value="January">January</option>
                            <option value="Febuary">Febuary</option>
                            <option value="March">March</option>
                            <option value="April">April</option>
                            <option value="May">May</option>
                            <option value="June">June</option>
                            <option value="July">July</option>
                            <option value="August">August</option>
                            <option value="September">September</option>
                            <option value="October">October</option>
                            <option value="November">November</option>
                            <option value="December">December</option>
                        </select> /
                        <select id="checkin_day" name="checkin_day">
                            <option> - Day - </option>
                            <script>
                                for(var i = 1; i <= 31; i++){
                                    document.write('<option value="'+i+'">'+i+'</option>');
                                }
                            </script>
                        </select> /
                        <select id="checkin_year" name="checkin_year">
                            <option> - Year - </option>
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
                        Country:
                        <select id="country" name="country" style="width: 200px;">
                            <option> - Choose - </option>
                        </select>
                    </li>
                </ul>
                <ul>
                    <li>  <!--    CHECK OUT   -->
                        Check out:
                        <select id="checkout_month" name="checkout_month">
                            <option> - Month - </option>
                            <option value="January">January</option>
                            <option value="Febuary">Febuary</option>
                            <option value="March">March</option>
                            <option value="April">April</option>
                            <option value="May">May</option>
                            <option value="June">June</option>
                            <option value="July">July</option>
                            <option value="August">August</option>
                            <option value="September">September</option>
                            <option value="October">October</option>
                            <option value="November">November</option>
                            <option value="December">December</option>
                        </select> /
                        <select id="checkout_day" name="checkout_day">
                            <option> - Day - </option>
                            <script>
                                for(var i = 1; i <= 31; i++){
                                    document.write('<option value="'+i+'">'+i+'</option>');
                                }
                            </script>
                        </select> /
                        <select id="checkout_year" name="checkout_year">
                            <option> - Year - </option>
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
                        <select id="currency" name="currency" style="width: 80px">
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
                    <li>
                        Promo code:
                        <input type="text" id="promo_code" name="promo_code" value="" style="width:200px;" maxlength="20"/>
                    </li>
                    <li>
                        <input type="reset" name="reset" value="Reset">
                        <input type="submit" name="search" value="Search">
                    </li>
                </ul>
            </form>
        </div>
    </div><!-- #content -->

    <div id="footer">
    </div><!-- #footer -->

</div><!-- #wrapper -->

</body>

</html>