<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>NCLodger | Administrator dashboard</title>
<link rel="stylesheet" type="text/css" href="resources/css/style.css" />
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script type="text/javascript" src="resources/js/sorttable.js"></script>

<script>

    $(function() {
        $("#start_promo").datepicker();
        $("#end_promo").datepicker();
    })
    $(function() {
        $("#start_mostvalacc").datepicker();
        $("#end_mostvalacc").datepicker();
    })


    $(function () {
// $( "#tabs" ).tabs();
        $("#tabs").tabs({
            create: function (event, ui) {
                $(this).tabs({'select': $(this).find("ul").index($(this).find('a[href="' + window.location.hash + '"]').parent())});
            },
            activate: function (event, ui) {
                window.location.hash = $(ui.newTab[0]).find('a[href^="#tab"]').attr("href");
            }
        });
    });





     //2 below fuctions also allow to select all checkboxes but they have bug...or it is because of incorrect pagination
   /*  function toggleChecked(status) {
     $(".checkbox").each(function() {
     $(this).attr("checked",status);
     })
     }

    */

      /*
        function toggleChecked(source) {
   //     var r = displayedCheckbox();
        var chec = document.getElementsByName('block[]');
        for(var i=0, n=chec.length; i<n; i++) {
                chec[i].checked = source.checked;
            }
        }

        */


    function toggleChecked(source) {
       var chec = document.getElementsByName('block[]');
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


    function validateHotelFrm() {
        var isValid = true;
        var message = "In order to create new hotel the following fields must be filled:\n";
        if(document.hotelfrm.hotelname.value == "") {
            isValid = false;
            message += "\n- HotelDTO name";
        }
        if(document.hotelfrm.country.value == "") {
            isValid = false;
            message += "\n- Country";
        }
        if(document.hotelfrm.city.value == "") {
            isValid = false;
            message += "\n- City";
        }
        if(!isValid) {
            alert(message);
        }
// else {
// if(document.hotelfrm.category.value == "") {
// document.hotelfrm.category.value = 0;
// }
// if(document.hotelfrm.latitude.value == "") {
// document.hotelfrm.latitude.value = 0;
// }
// if(document.hotelfrm.longitude.value == "") {
// document.hotelfrm.latitude.value = 0;
// }
// }
        return isValid;
    }


    function OnMakeBlock(){
        var flag = validate();
        if(flag==true){
            document.adgetalluser.action = "makeblock";
            document.adgetalluser.submit();
        }

        return true;
    }

    function OnMakeUnBlock(){
        var flag = validate();
        if(flag==true){
            document.adgetalluser.action = "makeunblock";
            document.adgetalluser.submit();
        }

        return true;

    }

    function OnMakeSM(){
        var flag = validate();
        if(flag==true){
            document.adgetalluser.action = "grantsm";
            document.adgetalluser.submit();
        }
        return true;
    }

    function OnDismissSM(){
        var flag = validate();
        if(flag==true){
            document.adgetalluser.action = "dismisssm";
            document.adgetalluser.submit();
        }
        return true;
    }

    function OnDeleteUser(){
        var flag = validate();
        if(flag==true){
            document.adgetalluser.action = "deleteuser";
            document.adgetalluser.submit();
        }
        return true;
    }



    function validate() {
        var inputElements = document.getElementsByName('block[]');
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
            alert('Please, select at least one User!');
            return false;
        } else {
            return true;
        }
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
    }


    $('#opencreate').click(function() {
        window.location='createuser.jsp';
    });


</script>
<style type="text/css">
    .sortable .head h3 { background: url(resources/img/sort.gif) 7px center no-repeat; cursor:pointer; padding-left:18px} /* unsort 2 arrows */
    .sortable .desc, .sortable .asc {background:#4b708d} /* selected header */
    .sortable .desc h3 { background: url(resources/img/desc.gif) 7px center no-repeat; cursor:pointer; padding-left:18px} /* dsc arrpw */
    .sortable .asc h3 { background: url(resources/img/asc.gif) 7px center no-repeat; cursor:pointer; padding-left:18px} /* asc arrow */
</style>
</head>

<body onload="Init ()">

<div class="wrapper">

    <jsp:include page="header.jsp"/>

    <div class="content">
        <div id="tabs">
            <ul>
                <li><a href="#tabs-1" ><a href = "admingetallusers">Users</a></a></li>
                <li><a href="#tabs-2">Commission & Discounts</a></li>
                <!-- <li><a href="#tabs-3">Hotels</a></li> -->
            </ul>
            <div id="tabs-1"><!-- 'Users' tab -->
                <form name="adgetalluser" method="POST">
                    <c:if test="${requestScope.allusers != null}">
                    <div class="tabcontent">
                        <input type = "submit" name = "Block" value="Block" onclick="OnMakeBlock();">
                        <input type="submit" name = "UnBlock" value="UnBlock" onclick="OnMakeUnBlock();">
                        <input type="submit" name = "GrantSM" value="Grant SM" onclick="OnMakeSM();">
                        <input type="submit" name = "DismissSM" value="Dismiss SM" onclick="OnDismissSM();">
                        <a href="createuser.jsp"><input type="button" name = "CreateUser" value="Create User"></a>
                        <input type="submit" name="DeleteUser" value="Delete User" onclick="OnDeleteUser();">
                    </div>
                    <table cellpadding="0" cellspacing="0" border="0" id="table" class="sortable">
                        <thead>
                        <tr>

                            <th class="nosort"><input type="checkbox" id="check_all"  onclick="toggleChecked(this)"/></th>

                   <!--         <th class="nosort"><input type="checkbox" class="check_all" onclick="$('input[type=checkbox][class=checkboxes]').prop('checked',this.checked)"></th> -->
                            <th><h3>Name</h3></th>
                            <th><h3>Email</h3></th>
                            <th><h3>Block Status</h3></th>
                            <th><h3>Role in the system</h3></th>
                        </tr>
                        </thead>
                        <tbody id="uniq">
                        <c:forEach items="${requestScope.allusers}" var="user">
                            <tr>
                                <!--                 <td><input type="checkbox" name = "block[]" class="checkboxes" c:out value="${user.id}" id="slaves"/></td>  -->

                     <!--         <td><input type="checkbox" class="checkboxes" onclick="toggleParentChecked(this.checked)" name = "block[]" c:out value="${user.id}"/></td> -->

                               <td>
                                    <c:choose>
                                        <c:when test="${user.id_ut == '3'}">
                                            <input type="checkbox" class="checkboxes" c:out value="${user.id}" disabled/>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="checkbox" class="checkboxes" onclick="toggleParentChecked(this.checked)" name = "block[]" c:out value="${user.id}" enabled/>

                                        </c:otherwise>
                                    </c:choose>
                                </td>

                                <td><c:out value="${user.name}"/></td>
                                <td><c:out value="${user.email}"/></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${user.is_blocked=='0'}">not blocked</c:when>
                                        <c:when test="${user.is_blocked=='1'}">blocked</c:when>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${user.id_ut=='3'}">Admin</c:when>
                                        <c:when test="${user.id_ut=='2'}">Sales Manager</c:when>
                                        <c:when test="${user.id_ut=='1'}">Customer</c:when>
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
            </div>
            </form>

            <div id="tabs-2"><!-- 'Commission & Discounts' tab-->
                <div class="tabcontent">
                    <p class="h">Current Values<p>
                    <form name="discountsfrm" method="POST" action="initialdiscounts" onsubmit="">
                        <p><b>Commission:</b></p>
                        <input type="range" name="agency_com" id="slider1" value="${defcurcom}" min="3" max="17" onchange="OnSliderChanged (this)"/>
                        <span id="slider1Value" class="sliderValue"></span>
                        <p><b>User discount:</b></p>
                        <input type="range" name="user_discount" id="slider2" value="${defcurdisc}" min="0" max="33" onchange="OnSliderChanged (this)"/>
                        <span id="slider2Value" class="sliderValue"></span>
                        <p><b>VIP User discount:</b></p>
                        <input type="range" name="vip_user_discount" id="slider3" value="${defcurvipdisc}" min="0" max="33" onchange="OnSliderChanged (this)"/>
                        <span id="slider3Value" class="sliderValue"></span>
                        <p style="text-align: right;"><input type="submit" name="save_changes" value="Save changes"></p>
                    </form>
                </div>
            </div>

            <!-- <div id="tabs-3">
                <div class="tabcontent">
                    <p class="h">New HotelDTO</p>
                    <form name="hotelfrm" method="POST" action="addhotel" onsubmit="return validateHotelFrm();">
                        <p>HotelDTO name <span class="mandatory">*</span>:</p>
                        <input type="text" name="hotelname" maxlength="30">
                        <p>Country <span class="mandatory">*</span>:</p>
                        <input type="text" name="country" maxlength="20">
                        <p>City <span class="mandatory">*</span>:</p>
                        <input type="text" name="city" maxlength="30">
                        <p>Category:</p>
                        <input type="text" name="category" value="0">
                        <p>Latitude:</p>
                        <input type="text" name="latitude" value="0">
                        <p>Longitude:</p>
                        <input type="text" name="longitude" value="0">
                        <p style="text-align: right;">
                            <input type="submit" name="commit" value="Submit">
                            <input type="reset" name="reset" value="Reset">
                        </p>
                    </form>
                </div> -->
                
            </div>
        </div>


    </div><!-- #content -->

    <jsp:include page="footer.jsp"/>
    <%--<div class="footer">
    </div><!-- #footer -->--%>

</div><!-- #wrapper -->

</body>
</html>
