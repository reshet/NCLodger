<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>NCLodger | My orders</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css"/>
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
    <script type="text/javascript" src="resources/js/sorttable.js"></script>
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
    <style type="text/css">
        .sortable .head h3 { background: url(resources/img/sort.gif) 7px center no-repeat; cursor: pointer; padding-left: 18px }
        .sortable .desc, .sortable .asc { background: #4b708d } /* unsort 2 arrows */
        .sortable .desc h3 { background: url(resources/img/desc.gif) 7px center no-repeat; cursor: pointer; padding-left: 18px } /* selected header */
        .sortable .asc h3 { background: url(resources/img/asc.gif) 7px center no-repeat; cursor: pointer; padding-left: 18px } /* dsc arrpw */
    </style>
    <script>
        $(function() {
            $("#tabs").tabs({
                create: function(event, ui){
                    $(this).tabs({'select': $(this).find("ul").index($(this).find('a[href="' + window.location.hash + '"]').parent())});
                },
                activate: function(event, ui){
                    window.location.hash = $(ui.newTab[0]).find('a[href^="#tab"]').attr("href");
                }
            });
        });
    </script>
</head>

<body>

<div id="wrapper">
    <jsp:include page="header.jsp"/>
    <div id="content">
        <div id="tabs">
            <ul>
                <li><a href="#tabs-1">My Orders</a></li>
            </ul>
            <div id="tabs-1"><!-- 'View My orders' tab-->
                <c:if test="${requestScope.bookinglist != null}">
                    <table cellpadding="0" cellspacing="0" border="0" id="table" class="sortable">
                        <thead>
                        <tr>
                            <th><h3>Type</h3></th>
                            <th><h3>Hotel Name</h3></th>
                            <th><h3>City</h3></th>
                            <th><h3>Order's date</h3></th>
                            <th><h3>Check in date</h3></th>
                            <th><h3>Check out date</h3></th>
                            <th><h3>Price</h3></th>
                        </tr>
                        </thead>
                        <tbody id="uniq">
                        <c:forEach items="${requestScope.bookinglist}" var="b">
                            <tr>
                                <td><c:out value="${b.type}"/></td>
                                <td><c:out value="${b.hotelName}"/></td>
                                <td><c:out value="${b.hotelCity}"/></td>
                                <td><c:out value="${b.dateOrder}"/></td>
                                <td><c:out value="${b.startOrder}"/></td>
                                <td><c:out value="${b.endOrder}"/></td>
                                <td><c:out value="${b.price}"/></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="controls">
                        <div class="perpage">
                            <select onchange="sorter.size(this.value)">
                                <option value="5">5</option>
                                <option value="10" selected="selected">10</option>
                                <option value="20">20</option>
                                <option value="50">50</option>
                                <option value="100">100</option>
                            </select>
                            <span>Entries Per Page</span>
                        </div>
                        <div class="navigation">
                            <img src="resources/img/first.gif" width="16" height="16" alt="First Page" onclick="sorter.move(-1,true)"/>
                            <img src="resources/img/previous.gif" width="16" height="16" alt="First Page" onclick="sorter.move(-1)"/>
                            <img src="resources/img/next.gif" width="16" height="16" alt="First Page" onclick="sorter.move(1)"/>
                            <img src="resources/img/last.gif" width="16" height="16" alt="Last Page" onclick="sorter.move(1,true)"/>
                        </div>
                        <div class="text">Displaying Page <span id="currentpage"></span> of <span id="pagelimit"></span></div>
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
                </c:if>
            </div> <!-- end 'View My orders' tab -->
        </div> <!-- #tabs -->
    </div><!-- .content -->
    <jsp:include page="footer.jsp"/>

</div><!-- .wrapper -->

</body>

</html>
