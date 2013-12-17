<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>NCLodger | User dashboard</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css" />
    <script src="resources/js/md5-min.js"></script>
    <script>
        function validateChangePswdForm() {
            var isValid = true;

            if(document.changepswdfrm.oldpswd.value.length < 6 || document.changepswdfrm.oldpswd.value.length > 20
                    || (!(/^[a-zA-Z0-9]+$/).test(document.changepswdfrm.oldpswd.value))) {
                document.getElementById("span_oldpswd").style.display = 'inline';
                isValid = false;
            }
            else { document.getElementById("span_oldpswd").style.display = 'none'; }

            if(document.changepswdfrm.newpswd1.value.length < 6 || document.changepswdfrm.newpswd1.value.length > 20
                    || (!(/^[a-zA-Z0-9]+$/).test(document.changepswdfrm.newpswd1.value))) {
                document.getElementById("span_newpswd1").style.display = 'inline';
                isValid = false;
            }
            else { document.getElementById("span_newpswd1").style.display = 'none'; }

            if(document.changepswdfrm.newpswd1.value == document.changepswdfrm.newpswd2.value) {
                document.getElementById("span_newpswd2").style.display = 'none';
            }
            else {
                document.getElementById("span_newpswd2").style.display = 'inline';
                isValid = false;
            }

            if(isValid){
                document.changepswdfrm.password.value = hex_md5(document.changepswdfrm.newpswd1.value);
                document.changepswdfrm.oldpswd_hidden.value = hex_md5(document.changepswdfrm.oldpswd.value);
            }

            return isValid;
        }
    </script>
</head>

<body>

<div class="wrapper">
    <jsp:include page="header.jsp"/>
    <div class="content">
        <div class="window" style="width: 350px">
            <h1>User information</h1>
            <p>
                Username:
                <span class="blackspan"><c:out value="${sessionScope.username}"/></span>
            </p>
            <p>
                Email:
                <span class="blackspan"><c:out value="${sessionScope.email}"/></span>
            </p>
            <p>
                User type:
                <c:if test="${sessionScope.utype == 1}">
                    <span class="bluespan">Customer</span>
                </c:if>
                <c:if test="${sessionScope.utype == 2}">
                    <span class="bluespan">Sales Manager</span>
                </c:if>
                <c:if test="${sessionScope.utype == 3}">
                    <span class="bluespan">Administrator</span>
                </c:if>
            </p>
            <c:if test="${sessionScope.utype == 1}">
            <p>
                Customer status:
                <span class="greenspan">VIP</span>
            </p>
            </c:if>
            <p>
                Bonus balance:
                <c:if test="${sessionScope.bonus != null}">
                    <span class="bluespan"><c:out value="${sessionScope.bonus}"/>$</span>
                </c:if>
                <c:if test="${sessionScope.bonus == null}">
                   <span class="blackspan">Please reload this page</span>
                </c:if>
            </p>
            <p>
                Blocked status:
                <c:if test="${sessionScope.userfull.getIs_blocked() == 0}">
                    <span class="bluespan">not blocked</span>
                </c:if>
                <c:if test="${sessionScope.userfull.getIs_blocked() == 1}">
                    <span class="redpan">blocked</span>
                </c:if>
            </p>
            <br><hr>
            <p><a class="bluespan">Password change:</a></p><br>
            <c:if test="${requestScope.notify_changepswd != null}">
                <p style="color: #00FF00;"><c:out value="${requestScope.notify_changepswd}"/></p><br>
            </c:if>
            <div>
                <form name="changepswdfrm" method="POST" action="changepswd" onsubmit="return validateChangePswdForm();">
                    <p>Enter old password <span class="mandatory">*</span>:</p>
                    <input type="password" name="oldpswd" maxlength="20" />
                    <span id="span_oldpswd"><p>Invalid password!</p></span>
                    <c:if test="${requestScope.notify_wrongpswd==true}">
                        <p style="color: #bc0f0f;"> You've entered wrong password, please enter your password.</p>
                    </c:if>
                    <p>Enter new password <span class="mandatory">*</span>:</p>
                    <input type="password" name="newpswd1" maxlength="20" />
                    <span id="span_newpswd1"><p>Invalid password!</p></span>
                    <p>Confirm new password <span class="mandatory">*</span>:</p>
                    <input type="password" name="newpswd2" maxlength="20" />
                    <span id="span_newpswd2"><p>Passwords don't match!</p></span>
                    <br>
                    <p class="submit">
                        <input type="hidden" name="oldpswd_hidden" value=""/>
                        <input type="hidden" name="password" value=""/>
                        <input type="reset" name="reset" value="Reset">
                        <input type="submit" name="confirm" value="Confirm">
                    </p>
                </form>
            </div>
        </div>
    </div><!-- .content -->
    <jsp:include page="footer.jsp"/>
</div><!-- .wrapper -->

</body>

</html>