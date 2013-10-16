<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>NCLodger | Home Page</title>
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <script type="text/javascript">

        function check() {
            var uname = document.getElementById('uname').value;
            var pswd1 = document.getElementById('pswd1').value;
            var pswd2 = document.getElementById('pswd2').value;
            var email = document.getElementById('email').value;
            var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;

            // Check email validity
            if (!filter.test(email)) {
                document.getElementById("span_email").style.display = 'inline';
            }
            else {
                document.getElementById("span_email").style.display = 'none';
            }

            // Check the length of each the inpute
            if (uname.length < 6) {
                document.getElementById("span_uname").style.display = 'inline';
            }
            else {
                document.getElementById("span_uname").style.display = 'none';
            }

            if (pswd1.length < 6) {
                document.getElementById("span_pswd1").style.display = 'inline';
            }
            else {
                document.getElementById("span_pswd1").style.display = 'none';
            }

            // Check that passwords are match
            if (pswd2 != pswd1) {
                document.getElementById("span_pswd2").style.display = 'inline';
            }
            else {
                document.getElementById("span_pswd2").style.display = 'none';
            }

        }

    </script>

    <style type="text/css">
    html,
    body {
        margin:0;
        padding:0;
        height:100%;
    }
    #wrapper {
        min-height:100%;
        position:relative;
        background:#fff;
    }
    #header {
        background:#fa8736;
        padding:10px;
        -moz-box-shadow:    0px 1px 2px rgba(0,0,0,0.55);
        -webkit-box-shadow: 0px 1px 2px rgba(0,0,0,0.55);
        -khtml-box-shadow:  0px 1px 2px rgba(0,0,0,0.55);
        box-shadow:         0px 1px 2px rgba(0,0,0,0.55);
    }
    #content {
        padding-bottom:100px; /* Height of the footer element */
    }
    #footer {
        background:#9ec7dd;
        width:100%;
        height:100px;
        position:absolute;
        bottom:0;
        left:0;
        border-top:1px solid #577486;
    }

        /*                      */

        /*	NAVIGATION BAR	*/
    .nav{
        list-style:none;
        text-align:center;
    }
    .nav li{
        display:inline;
    }
    .nav a{
        display:inline-block;
        color: #fff;
        padding: 0 30px;
        text-decoration: none;
        font-weight: bold;
    }
    .nav a:hover { color: #719450;}




        /*                       */

        /*	LOGIN FORM	*/
    .login {
        margin: 30px auto;
        padding: 20px 20px 20px;
        width: 310px;
        height: 100%;
        background: white;
        border-radius: 3px;
        -webkit-box-shadow: 0 0 200px rgba(255, 255, 255, 0.5), 0 1px 2px rgba(0, 0, 0, 0.3);
        box-shadow: 0 0 200px rgba(255, 255, 255, 0.5), 0 1px 2px rgba(0, 0, 0, 0.3);
    }
    .login:before {
        content: '';
        position: absolute;
        top: -8px;
        right: -8px;
        bottom: -8px;
        left: -8px;
        z-index: -1;
        /*background: rgba(0, 0, 0, 0.08);*/
        border-radius: 4px;
    }
    .login h1 {
        margin: -20px -20px 21px;
        line-height: 40px;
        font-size: 15px;
        font-weight: bold;
        color: #555;
        text-align: center;
        text-shadow: 0 1px white;
        background: #f3f3f3;
        border-bottom: 1px solid #cfcfcf;
        border-radius: 3px 3px 0 0;
        background-image: -webkit-linear-gradient(top, whiteffd, #eef2f5);
        background-image: -moz-linear-gradient(top, whiteffd, #eef2f5);
        background-image: -o-linear-gradient(top, whiteffd, #eef2f5);
        background-image: linear-gradient(to bottom, whiteffd, #eef2f5);
        -webkit-box-shadow: 0 1px whitesmoke;
        box-shadow: 0 1px whitesmoke;
    }
    .login p {
        margin: 30px 0 0;
    }
    .login p:first-child {
        margin-top: 0;
    }
    .login input[type=text], .login input[type=password] {
        width: 278px;
    }
    .login p.create_acc {
        float: left;
        line-height: 31px;
    }
    .login a {
        color: #94a4b0;
        text-decoration: underline;
    }
    .login a:hover {
        text-decoration: none;
    }

    .login p.submit {
        text-align: right;
    }

    .login span {
        color: #bc0f0f;
        display: none;
    }

    .login-help {
        margin: 20px 0;
        font-size: 12px;
        color: #577486;
        text-align: center;
    }
    .login-help a {
        color: #9ec7dd;
        text-decoration: none;
    }
    .login-help a:hover {
        text-decoration: underline;
    }

    :-moz-placeholder {
        color: #c9c9c9 !important;
        font-size: 13px;
    }

    ::-webkit-input-placeholder {
        color: #ccc;
        font-size: 13px;
    }

    input {
        font-family: 'Lucida Grande', Tahoma, Verdana, sans-serif;
        font-size: 14px;
    }

    input[type=text], input[type=password] , select {
        margin: 5px;
        padding: 0 10px;
        width: 300px;
        height: 34px;
        color: #404040;
        background: white;
        border: 1px solid;
        border-color: #c4c4c4 #d1d1d1 #d4d4d4;
        border-radius: 2px;
        outline: 5px solid #eff4f7;
        -moz-outline-radius: 3px;
        -webkit-box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.12);
        box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.12);
    }
    input[type=text]:focus, input[type=password]:focus, select:focus{
        border-color: #7dc9e2;
        outline-color: #dceefc;
        outline-offset: 0;
    }

    input[type=submit] {
        padding: 0 18px;
        height: 29px;
        font-size: 12px;
        font-weight: bold;
        color: #527881;
        text-shadow: 0 1px #e3f1f1;
        background: #cde5ef;
        border: 1px solid;
        border-color: #b4ccce #b3c0c8 #9eb9c2;
        border-radius: 16px;
        outline: 0;
        -webkit-box-sizing: content-box;
        -moz-box-sizing: content-box;
        box-sizing: content-box;
        background-image: -webkit-linear-gradient(top, #edf5f8, #cde5ef);
        background-image: -moz-linear-gradient(top, #edf5f8, #cde5ef);
        background-image: -o-linear-gradient(top, #edf5f8, #cde5ef);
        background-image: linear-gradient(to bottom, #edf5f8, #cde5ef);
        -webkit-box-shadow: inset 0 1px white, 0 1px 2px rgba(0, 0, 0, 0.15);
        box-shadow: inset 0 1px white, 0 1px 2px rgba(0, 0, 0, 0.15);
    }
    input[type=submit]:active {
        background: #cde5ef;
        border-color: #9eb9c2 #b3c0c8 #b4ccce;
        -webkit-box-shadow: inset 0 0 3px rgba(0, 0, 0, 0.2);
        box-shadow: inset 0 0 3px rgba(0, 0, 0, 0.2);
    }

    .lt-ie9 input[type=text], .lt-ie9 input[type=password] {
        line-height: 34px;
    }
        /*                                  */
    </style>

</head>

<body>

<div id="wrapper">

    <div id="header">
        <div class="nav">
            <!--<img src="img/header.png">-->
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="#">About Us</a></li>
                <li><a href="#">Contacts</a></li>
            </ul>
        </div>
    </div><!-- #header -->

    <div id="content">

        <div class="login" >
            <h1>Registration</h1>
            <form method="post" action="<c:url value = "/register" />">
                <p><input type="text" id="uname" name="username" value="" placeholder="Username" onkeyup="check();" /></p>
                <span id="span_uname" >Username must contain only latin letters and not less than 6 symbols!</span>
                <p><select name="user_type">
                    <option value="customer">Customer</option>
                    <option value="sales manager">Sales manager</option>
                </select></p>
                <p><input type="text" id="email" name="email" value="" placeholder="Email" onkeyup="check();" /></p>
                <span id="span_email" >Wrong email input!</span>
                <p><input type="password" id="pswd1" name="password" value="" placeholder="Password" onkeyup="check();"></p>
                <span id="span_pswd1" >Password must contain only latin letter, numbers and not less than 6 symbols!</span>
                <p><input type="password" id="pswd2" name="password" value="" placeholder="Confirm password" onkeyup="check();"></p>
                <span id="span_pswd2" >Passwords do not match!</span>
                <p class="submit">
                    <input type="submit" name="commit" value="Submit">
                </p>
            </form>
        </div>

    </div><!-- #content -->

    <div id="footer">
    </div><!-- #footer -->

</div><!-- #wrapper -->

</body>

</html>

