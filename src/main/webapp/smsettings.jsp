<%@ page import="java.util.List" %>
<%@ page import="com.nclodger.dao.Users" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>


<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>NCLodger | Settings: sales manager</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css"/>
    <script type="text/javascript" src="js/sorttable.js"></script>
    <!--[if lt IE 7]>





    <style type="text/css">
        #wrapper {
            height: 100%;
        }
    </style>
    <![endif]-->
    <style type="text/css">
        .sortable .head h3 {
            background: url(resources/img/sort.gif) 7px center no-repeat;
            cursor: pointer;
            padding-left: 18px
        }

            /* unsort 2 arrows */
        .sortable .desc, .sortable .asc {
            background: #4b708d
        }

            /* selected header */
        .sortable .desc h3 {
            background: url(resources/img/desc.gif) 7px center no-repeat;
            cursor: pointer;
            padding-left: 18px
        }

            /* dsc arrpw */
        .sortable .asc h3 {
            background: url(resources/img/asc.gif) 7px center no-repeat;
            cursor: pointer;
            padding-left: 18px
        }

            /* asc arrow */
    </style>
</head>

<body>

<div id="wrapper">

    <div id="header">
        <div class="greeting" style="float: right; padding-right: 2em; ">
            <%
                if (session.getAttribute("username") == null) {
            %>
            <a href="login.jsp">Log in</a> / <a href="registration.jsp">Register</a>
            <%
            } else {
            %>
            Hello, <%=session.getAttribute("username")%>!
            <br><a href="signout">Log out</a>
            <%
                }
            %>
            <%
                if ((Integer) session.getAttribute("utype") == (Integer) 2) {
            %>
            <br><a href="smsettings" class="orangelink"><img src="img/user.gif">Sales manager actions</a>
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
    </div>
    <!-- #header -->

    <div id="content">
        <div class="search">
            <a href="smgetallusers">View all users</a>
        </div>


        <%--<% if(request.getAttribute("allusers") != null) { %>--%>
        <c:if test="${requestScope.allusers != null}">

            <div class="actionres">
                All users:
            </div>
            <table cellpadding="0" cellspacing="0" border="0" id="table" class="sortable">
                <thead>
                <tr>
                    <th><h3>Name</h3></th>
                    <th><h3>Email</h3></th>
                </tr>
                </thead>
                <tbody>
                     <%--<script>--%>
                         <%--document.write('<tr>');--%>
                         <%--document.write('<td>Iaroslav Dmytruk</td>');--%>
                         <%--document.write('<td><a>Iaroslav@example.org</a></td>');--%>
                         <%--document.write('</tr>');--%>

                         <%--document.write('<tr>');--%>
                         <%--document.write('<td>John Terry</td>');--%>
                         <%--document.write('<td><a>John@example.org</a></td>');--%>
                         <%--document.write('</tr>');--%>

                     <%--</script>--%>

                    <%--<%--%>
                    <%--List<Users> allusers = (List) request.getAttribute("allusers");--%>

                    <%--for(Users user : allusers) {--%>
                    <%--out.println("<tr>");--%>
                    <%--out.print("<td>"+user.getName()+"</td>");--%>
                    <%--out.print("<td>"+user.getEmail()+"</td>");--%>
                    <%--out.println("</tr>");--%>

                    <%--}--%>
                    <%--%>--%>

                <c:forEach items="${requestScope.allusers}" var="user">
                    <tr>
                        <td><c:out value="${user.name}"/></td>
                        <td><c:out value="${user.email}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div id="controls">
                <div id="perpage">
                    <select onchange="sorter.size(this.value)">
                        <option value="5">5</option>
                        <option value="10" selected="selected">10</option>
                        <option value="20">20</option>
                        <option value="50">50</option>
                        <option value="100">100</option>
                    </select>
                    <span>Entries Per Page</span>
                </div>
                <div id="navigation">
                    <img src="img/first.gif" width="16" height="16" alt="First Page" onclick="sorter.move(-1,true)"/>
                    <img src="img/previous.gif" width="16" height="16" alt="First Page" onclick="sorter.move(-1)"/>
                    <img src="img/next.gif" width="16" height="16" alt="First Page" onclick="sorter.move(1)"/>
                    <img src="img/last.gif" width="16" height="16" alt="Last Page" onclick="sorter.move(1,true)"/>
                </div>
                <div id="text">Displaying Page <span id="currentpage"></span> of <span id="pagelimit"></span></div>
            </div>
            <script type="text/javascript">
                var sorter = new TINY.table.sorter("sorter");
                sorter.head = "head";
                sorter.asc = "asc";
                sorter.desc = "desc";
                sorter.even = "evenrow";
                sorter.odd = "oddrow";
                sorter.evensel = "evenselected";
                sorter.oddsel = "oddselected";
                sorter.paginate = true;
                sorter.currentid = "currentpage";
                sorter.limitid = "pagelimit";
                sorter.init("table", 1);

            </script>
            <%--<%}%>--%>
        </c:if>
    </div>
    <!-- #content -->

    <div id="footer">
    </div>
    <!-- #footer -->

</div>
<!-- #wrapper -->

</body>

</html>