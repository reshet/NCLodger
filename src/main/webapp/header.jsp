<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <br><a href="" class="orangelink"><img src="resources/img/user.gif">User dashboard</a>
    <% } %>
    <%
        if((Integer)session.getAttribute("utype") == (Integer)2) {
    %>
    <br><a href="smsettings" class="orangelink"><img src="resources/img/user.gif">Sales manager dashboard</a>
    <% } %>
    <%
        if((Integer)session.getAttribute("utype") == (Integer)3) {
    %>
    <br><a href="adminsettings" class="orangelink"><img src="resources/img/user.gif">Administrator dashboard</a>
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