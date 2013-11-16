<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>NCLodger | Login</title>
<link rel="stylesheet" type="text/css" href="resources/css/style.css" />
    <script src="resources/js/md5-min.js"></script>

    <!--[if lt IE 7]>
<style type="text/css">
     #wrapper { height:100%; }
</style>
<![endif]-->
<script>
    function validateLoginForm() {
        var isValid = true;
        if (!(/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/).test(document.loginfrm.email.value)) {
            document.getElementById("span_email").style.display = 'inline';
            isValid = false;
        }
        else { document.getElementById("span_email").style.display = 'none'; }

        if(document.loginfrm.password1.value.length < 6 || document.loginfrm.password1.value.length > 20
                || (!(/^[a-zA-Z0-9]+$/).test(document.loginfrm.password1.value))) {
            document.getElementById("span_pswd").style.display = 'inline';
            isValid = false;
        }
        else { document.getElementById("span_pswd").style.display = 'none'; }
        if(isValid){
            document.loginfrm.password.value = hex_md5(document.loginfrm.password1.value);
        }

        return isValid;
    }
</script>
</head>

<body>

<div id="wrapper">

    <div id="header">
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

        <div class="window" >
            <h1>Login</h1>
            <form name="loginfrm" method="POST" action="signin" onsubmit="return validateLoginForm();">
                <span id="span_notfound">User with such email and password was not found!</span>
                <p>Email:<input type="text" name="email" maxlength="50"></p>
                <span id="span_email">Invalid email!</span>
                <p>Password:<input type="password" name="password1" maxlength="20"></p>
                <span id="span_pswd"><p>Invalid password!</p></span>
                <a href="registration.jsp">Haven't got account yet?</a></br>
                <a href="">Login with GOOGLE?</a>

                <p class="submit">
                    <input type="hidden" name="password" value=""/>
                    <input type="submit" name="commit" value="Login">
                    <input type="submit" name="commit" value="Login as guest">
                </p>
            </form>
        </div>

        <div class="window-help">
            <p>Forgot your password? <a href="index.html">Click here to reset it</a>.</p>
        </div>

    </div><!-- #content -->

    <div id="footer">
    </div><!-- #footer -->

</div><!-- #wrapper -->

</body>

</html>