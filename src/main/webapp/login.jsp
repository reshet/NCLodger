<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta name='loginza-verification' content='6bcec9f4dc4c4fa821589bd7751da06f' />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>NCLodger | Login</title>
<link rel="stylesheet" type="text/css" href="resources/css/style.css" />
<script src="resources/js/md5-min.js"></script>
<script src="//loginza.ru/js/widget.js" type="text/javascript"></script>


<script>

    function JSTrial(){
        var json = '{"result":true,"count":1}',
                obj = JSON.parse(json);

        alert(obj.count);
    }






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

<div class="wrapper">
    <jsp:include page="header.jsp"/>

    <div class="content">
        <div class="window" >
            <h1>Login</h1>
            <form name="loginfrm" method="POST" action="signin" onsubmit="return validateLoginForm();">
                <span id="span_notfound">User with such email and password was not found!</span>
                <p>Email:<input type="text" name="email" maxlength="50"></p>
                <span id="span_email">Invalid email!</span>
                <c:if test="${requestScope.regConfirm==true}">
                    <p style="color: #bc0f0f;">This email wasn't registered. Please, sign up or confirm your registration.</p>
                </c:if>
                <p>Password:<input type="password" name="password1" maxlength="20"></p>
                <c:if test="${requestScope.wrongPass==true}">
                    <p style="color: #bc0f0f;">Wrong password!</p>
                </c:if>
                <span id="span_pswd">Invalid password!</span><br>
                <a href="registration.jsp">Haven't got account yet?</a></br>

             <!--   <a href="https://loginza.ru/api/widget?token_url=http://localhost:8080/NCLodger/home.jsp&lang=en&providers_set=facebook,twitter,openid,google,yandex" class="loginza"><input type="button" action="openid" value="Use existing account"></a></button> -->

            <%--<a href="">Login with GOOGLE?</a>--%>
                <p class="submit">
                    <input type="hidden" name="password" value=""/>
                    <input type="submit" name="commit" value="Login">
                    <input type="reset" name="reset" value="Reset">
                </p>
            </form>
        </div>
    </div><!-- #content -->

    <jsp:include page="footer.jsp"/>
    <%--<div class="footer">
    </div><!-- #footer -->--%>

</div><!-- #wrapper -->

</body>

</html>