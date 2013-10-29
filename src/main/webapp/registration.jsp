<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>NCLodger | Registration</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<!--[if lt IE 7]>
<style type="text/css">
     #wrapper { height:100%; }
</style>
<![endif]-->
<script>
    function validateRegForm() {
        var isValid = true;

        if(document.regfrm.username.value.length < 4 || document.regfrm.username.value.length > 20
                || (!(/^[a-zA-Z]+$/).test(document.regfrm.username.value))) {
            document.getElementById("span_username").style.display = 'inline';
            isValid = false;
        }
        else { document.getElementById("span_username").style.display = 'none'; }

        if(document.regfrm.password1.value.length < 6 || document.regfrm.password1.value.length > 20
                || (!(/^[a-zA-Z0-9]+$/).test(document.regfrm.password1.value))) {
            document.getElementById("span_pswd1").style.display = 'inline';
            isValid = false;
        }
        else { document.getElementById("span_pswd1").style.display = 'none'; }

        if(document.regfrm.password1.value == document.regfrm.password2.value) {
            document.getElementById("span_pswd2").style.display = 'none';
        }
        else {
            document.getElementById("span_pswd2").style.display = 'inline';
            isValid = false;
        }

        if (!(/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/).test(document.regfrm.email.value)) {
            document.getElementById("span_email").style.display = 'inline';
            isValid = false;
        }
        else { document.getElementById("span_email").style.display = 'none'; }

        return isValid;
    }
</script>
</head>

<body>

<div id="wrapper">

    <div id="header">
        <div class="nav">
            <ul>
                <li><a href="home.jsp">Home</a></li>
                <li><a href="#">About Us</a></li>
                <li><a href="#">Contacts</a></li>
            </ul>
        </div>
    </div><!-- #header -->

    <div id="content">
        <div class="window" >
            <h1>Registration</h1>
            <form name="regfrm" method="POST" action="signup" onsubmit="return validateRegForm();">
                <p>Username:<input type="text" name="username" maxlength="20"/></p>
                <span id="span_username">Username must contain only latin letters (4 to 20 symbols)!</span>
                <p>Email:<input type="text" name="email" maxlength="50"/></p>
                <span id="span_email">Invalid email!</span>

                <p>Password:<input type="password" name="password1" maxlength="20"></p>
                <span id="span_pswd1">Password must contain only latin letters and numbers (6 to 20 symbols)!</span>
                <p>Confirm password:<input type="password" name="password2" maxlength="20"></p>
                <span id="span_pswd2">Passwords do not match!</span>
                <p class="submit">
                    <input type="reset" name="reset" value="Reset">
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

