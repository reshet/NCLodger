<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>NCLodger | Sales Manager dashboard</title>
<link rel="stylesheet" type="text/css" href="resources/css/style.css"/>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<!-- Script and css for the sorting table -->
<script type="text/javascript" src="resources/js/sorttable.js"></script>
<style type="text/css">
  .sortable .head h3 { background: url(resources/img/sort.gif) 7px center no-repeat; cursor: pointer; padding-left: 18px }
  .sortable .desc, .sortable .asc { background: #4b708d } /* unsort 2 arrows */
  .sortable .desc h3 { background: url(resources/img/desc.gif) 7px center no-repeat; cursor: pointer; padding-left: 18px } /* selected header */
  .sortable .asc h3 { background: url(resources/img/asc.gif) 7px center no-repeat; cursor: pointer; padding-left: 18px } /* dsc arrpw */
</style>
   <script>
       $(function() {
           $( "#start_promo" ).datepicker({
               minDate: new Date(),
               /*        defaultDate: "+1w",
                changeMonth: true,
                numberOfMonths: 3,*/
               onClose: function( selectedDate ) {
                   $( "#end_promo" ).datepicker( "option", "minDate", selectedDate );
               }
           });
           $( "#end_promo" ).datepicker({
               onClose: function( selectedDate ) {
                   $( "#start_promo" ).datepicker( "option", "maxDate", selectedDate );
               }
           });
       });

       $(function() {
           $( "#start_date" ).datepicker({
               maxDate: new Date(),

               onClose: function( selectedDate ) {
                   $( "#end_date" ).datepicker( "option", "minDate", selectedDate );
               }
           });
           $( "#end_date" ).datepicker({
               maxDate: new Date,
               onClose: function( selectedDate ) {
                   $( "#start_promo" ).datepicker( "option", "maxDate", selectedDate );
               }
           });
       })
        $(function() {
//            $( "#tabs" ).tabs();
            $("#tabs").tabs({
                create: function(event, ui){
                    $(this).tabs({'select': $(this).find("ul").index($(this).find('a[href="' + window.location.hash + '"]').parent())});
                },
                activate: function(event, ui){
                    window.location.hash = $(ui.newTab[0]).find('a[href^="#tab"]').attr("href");
                }
            });
        });

        function OnMakeVip(){
            var flag = validateVip();
            if(flag==true){
                document.getalluser.action = "makevip";
                document.getalluser.submit();
            }
            return true;
        }
        function OnMakeUnvip(){
            var flag = validateVip();
            if(flag==true){
                document.getalluser.action = "makeunvip";
                document.getalluser.submit();
            }
            return true;
        }

       function toggleChecked(source) {
           var chec = document.getElementsByName('vip[]');
           var r = displayedCheckbox();
           for(var i=0, n=r; i<n; i++) {
               chec[i].checked = source.checked;
           }
       }

       function toggleParentChecked(status) {
           if(status==false)
               $("#check_all").prop("checked",status);
           else
           {
               var isallchecked = true;
               $(".checkbox").each(function() {
                   if(!$(this).prop("checked"))
                       isallchecked = false;
               })

               if(isallchecked)
                   $("#check_all").prop("checked", true);
           }

       }

       function displayedCheckbox(){
           var all = document.getElementById('uniq').rows;
           var result=0;
           for(var i=0; i<all.length; i++){
               if(all[i].style.display !== "none"){
                   result++;
               }
           }
           return result;
       }

        function validatePromoFrm() {
            var isValid = true;
            if(document.promofrm.start_promo.value == "" || document.promofrm.end_promo.value == "") {
                isValid = false;
                alert("In order to generate promo code the start and end dates must be filled.");
            }
            return isValid;
        }

        function validateVip() {
            var inputElements = document.getElementsByName('vip[]');
            var chekSelect = false;
            for (var i = 0; i < inputElements.length; i++) {
                var myElement = inputElements[i];

                if (myElement.type === "checkbox") {
                    if (myElement.checked) {
                        chekSelect = true;
                        break;
                    }
                }
            }
            if(!chekSelect) {
                alert('Please, select at least one user!');
                return false;
            } else {
                return true;
            }
        }

        function OnViewPopHotel(){
               /* document.getallhotel.action = "getallhotel";*/
                document.getallhotel.action = "showmostpopularhotel";
                document.getallhotel.submit();
            return true;
        }

        function OnViewValAcc(){
            /* document.getallhotel.action = "getallhotel";*/
            document.getallacc.action = "showmostvaluableacc";
            document.getallacc.submit();
            return true;
        }

    </script>

    <script type="text/javascript">
        function OnSliderChanged (slider) {
            var sliderValue = document.getElementById (slider.id + "Value");
            sliderValue.innerHTML = slider.value+"%";
        }

        function Init () {
            var slider = document.getElementById ("slider1");
            OnSliderChanged (slider);
            var slider = document.getElementById ("slider2");
            OnSliderChanged (slider);
            var slider = document.getElementById ("slider3");
            OnSliderChanged (slider);
            var slider = document.getElementById ("slider4");
            OnSliderChanged (slider);
        }
    </script>
</head>

<body onload="Init ()">

<div class="wrapper">

    <jsp:include page="header.jsp"/>

    <div class="content">
        <div id="tabs">
            <%--<c:set var="tabs" value="${fn:split('#tabs-1,#tabs-2,#tabs-3,#tabs-4', ',')}" scope="session" />--%>
            <ul>
    <%--            <c:forEach items="${tabs}" var="tabs">
                    <li><a href="${tabs}">${tabs}</a></li>
                </c:forEach>--%>
                <li><a href="#tabs-1"> <a href="smgetallusers">Users</a></a></li>
                <li><a href="#tabs-2">Commission & Discounts</a></li>
                <li><a href="#tabs-3">Promo codes</a></li>
                <li><a href="#tabs-4">Reports</a></li>
            </ul>

            <div id="tabs-1"><!-- 'Users' tab -->
                <form name="getalluser" method="POST" onsubmit="">
                   <c:if test="${requestScope.allusers != null}">
                   <div class="tabcontent">
                       <p class="h">Users</p>
                        <input type = "submit" name = "UNVIP" value="UNVIP" onclick="OnMakeUnvip();">
                        <input type="submit" name = "VIP" value="VIP" onclick="OnMakeVip();">
                   </div>
                   <table cellpadding="0" cellspacing="0" border="0" id="table" class="sortable">
                        <thead>
                        <tr>

           <!--                 <th class="nosort" ><input type="checkbox" onClick="toggle2(this)"/></th>    -->


                            <th class="nosort"><input type="checkbox" id="check_all" onclick="toggleChecked(this)"/></th>

                            <th><h3>Name</h3></th>
                            <th><h3>Email</h3></th>
                            <th><h3>Vip Status</h3></th>
                        </tr>
                        </thead>
                        <tbody id="uniq">
                        <c:forEach items="${requestScope.allusers}" var="user">
                            <tr>

                          <!--      <td><input type="checkbox" name = "vip[]" c:out value="${user.id}"/> </td>     -->
                                <td><input type="checkbox" class="checkboxes" onclick="toggleParentChecked(this.checked)" name = "vip[]" c:out value="${user.id}"/></td>

                                <td><c:out value="${user.name}"/></td>
                                <td><c:out value="${user.email}"/></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${user.vip=='0'}">Not vip</c:when>
                                        <c:when test="${user.vip=='1'}">Vip</c:when>
                                    </c:choose>
                                </td>
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
                </form>
            </div> <!-- #tab1 -->

            <div id="tabs-2"><!-- 'Commission & Discounts' tab-->
                <div class="tabcontent">
                    <p class="h">Current Values<p>
                    <form name="discountsfrm" method="POST" action="smsetdiscounts" onsubmit="">
                        <p>Commission:</p>
                        <input type="range" id="slider1" name="agency_com" value="${curcom}" min="3" max="17" onchange="OnSliderChanged (this)" />
                        <span id="slider1Value" class="sliderValue"></span>
                        <p>User discount:</p>
                        <input type="range" id="slider2" name="user_discount" value="${curdisc}" min="0" max="33" onchange="OnSliderChanged (this)" />
                        <span id="slider2Value" class="sliderValue"></span>
                        <p>VIP User discount:</p>
                        <input type="range" id="slider3" name="vip_user_discount" value="${curvipdisc}" min="0" max="33" onchange="OnSliderChanged (this)" />
                        <span id="slider3Value" class="sliderValue"></span>
                        <p style="text-align: right;"><input type="submit" name="save_changes" value="Save changes"></p>
                    </form>
                </div>
            </div>

            <div id="tabs-3"><!-- 'Promo codes' tab -->
                <div class="tabcontent">
                    <p class="h">New Promo Code<p>
                    <form name="promofrm" method="POST" action="generatepromo" onsubmit="return validatePromoFrm();">
                        <div class="tapcontent">
                            <p>Start date <span class="mandatory">*</span>:
                                <input id="start_promo" name="start_promo" style="width:100px;"/>
                               Expiration date <span class="mandatory">*</span>:
                                <input id="end_promo" name="end_promo" style="width:100px;"/></p>
                            <p>Discount <span class="mandatory">*</span>:</p>
                            <input type="range" id="slider4" name="promo_discount" value="" min="1" max="33" onchange="OnSliderChanged (this)"/>
                            <span id="slider4Value" class="sliderValue"></span>
                            <c:if test="${promo_code != null}">
                                <p>Generated promo code: <span style="color: #0000ff;">${promo_code}</span></p>
                            </c:if>
                            <p style="text-align: right;"><input type="submit" name="generate_promo" value="Generate"></p>
                        </div>
                        <a href="getallpromocodes">All promo codes:</a>
                        <c:if test="${requestScope.allpromocodes != null}">
                            <table cellpadding="0" cellspacing="0" border="0" id="table_pc" class="sortable">
                                <thead>
                                <tr>
                                    <th><h3>Name</h3></th>
                                    <th><h3>Discount</h3></th>
                                    <th><h3>Start date</h3></th>
                                    <th><h3>Expiration date</h3></th>
                                    <th><h3>Status</h3></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${requestScope.allpromocodes}" var="pc">
                                    <tr>
                                        <td><c:out value="${pc.code}"/></td>
                                        <td><c:out value="${(pc.discount * 100)}"/>%</td>
                                        <td><c:out value="${pc.start_date}"/></td>
                                        <td><c:out value="${pc.end_date}"/></td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${pc.status=='0'}">Available</c:when>
                                                <c:when test="${pc.status=='1'}">Used</c:when>
                                                <c:when test="${pc.status=='2'}">Expired</c:when>
                                            </c:choose>
                                        </td>
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
                                <div class="text">Displaying Page <span id="currentpage_pc"></span> of <span id="pagelimit_pc"></span></div>
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
                                sorter.currentid = "currentpage_pc";
                                sorter.limitid = "pagelimit_pc";
                                sorter.init("table_pc", 1);
                            </script>
                        </c:if>
                    </form>
                </div>
            </div>

            <div id="tabs-4"><!-- 'Reports' tab-->
                <%--                <form id ="reportdate" name="reportdate"  method="POST" onsubmit="">
                                <label for="start_date">From: </label>
                                <input id="start_date" name="start_date" style="width:100px;"/>
                                <label for="end_date">To: </label>
                                <input id="end_date" name="end_date" style="width:100px;"/>
                                </form>--%>

                <form name="getallhotel" method="POST" onsubmit="">
                    <%--                    <a href="showmostpopularhotel">Show most popular hotels</a>--%>
                        <div class="tabcontent">
                            <p class="h">Reports</p>
                            <p><label for="start_date">From: </label>
                            <input id="start_date" name="start_date" style="width:100px;"/>
                            <label for="end_date">To: </label>
                            <input id="end_date" name="end_date" style="width:100px;"/></p>
                            <p><input type = "submit" name = "viewpophotel" value="View the most popular hotels" onclick="OnViewPopHotel();">
                            <input type="submit" name = "viewvalacc" value="View the most valuable accommodations" onclick="OnViewValAcc();"></p>
                        </div>
                        <c:if test="${requestScope.mostpophotel != null}">
                            <table cellpadding="0" cellspacing="0" border="0" id="table" class="sortable">
                                <thead>
                                <tr>
                                    <th><h3>Hotel name</h3></th>
                                    <th><h3>City</h3></th>
                                    <th><h3>Country</h3></th>
                                    <th><h3>Total order for period</h3></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${requestScope.mostpophotel}" var="hotel">
                                    <tr>

                                        <td><c:out value="${hotel.hotelname}"/></td>
                                        <td><c:out value="${hotel.city}"/></td>
                                        <td><c:out value="${hotel.country}"/></td>
                                        <td><c:out value="${hotel.totalOrder}"/></td>

                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <a href="saveexcel">Save as Excel</a>
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
                                <div class="text">Displaying Page <span id="currentpage_h"></span> of <span id="pagelimit_h"></span></div>
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

                    <%--</form>--%>


                    <%-- <form name="getallacc" method="POST" onsubmit="">--%>
                    <%--<a href="showmostvaluableacc">View the most valuable accommodations</a>--%>
                    <%--                            <label for="start_date">From: </label>
                                                <input id="start_date" name="start_date" style="width:100px;"/>
                                                <label for="end_date">To: </label>
                                                <input id="end_date" name="end_date" style="width:100px;"/>--%>
                    <c:if test="${requestScope.mostvalacc != null}">
                        <table cellpadding="0" cellspacing="0" border="0" id="table" class="sortable">
                            <thead>
                            <tr>
                                <th><h3>Type </h3></th>
                                <th><h3>Hotel name</h3></th>
                                <th><h3>City</h3></th>
                                <th><h3>Country</h3></th>
                                <th><h3>Total value for period</h3></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${requestScope.mostvalacc}" var="acc">
                                <tr>
                                    <td><c:out value="${acc.type}"/></td>
                                    <td><c:out value="${acc.hotel_name}"/></td>
                                    <td><c:out value="${acc.city}"/></td>
                                    <td><c:out value="${acc.country}"/></td>
                                    <td><c:out value="${acc.totalValue}"/></td>

                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <a href="saveaccexcel">Save as excel file</a>
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
                            <div class="text">Displaying Page <span id="currentpage_acc"></span> of <span id="pagelimit_acc"></span></div>
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
                </form>
            </div>
    </div>


    </div>
    <!-- #content -->

    <div class="footer">
    </div>
    <!-- #footer -->

</div>
<!-- #wrapper -->

</body>

</html>