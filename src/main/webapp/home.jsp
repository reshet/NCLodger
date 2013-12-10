<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>NCLodger | Home</title>
<link rel="stylesheet" type="text/css" href="/NCLodger/resources/css/style.css" />
<%--New Year version--%>
<script src="/NCLodger/resources/js/snowstorm.js"></script>
<%--<link rel="stylesheet" media="screen" href="resources/css/christmaslights.css" />
<script type="text/javascript" src="resources/js/soundmanager2-nodebug-jsmin.js"></script>
<script type="text/javascript" src="http://yui.yahooapis.com/combo?2.6.0/build/yahoo-dom-event/yahoo-dom-event.js&2.6.0/build/animation/animation-min.js"></script>
<script type="text/javascript" src="resources/js/christmaslights.js"></script>
<script type="text/javascript">
    var urlBase = './';
    soundManager.url = './';
</script>--%>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?libraries=places&language=eng&sensor=false"></script>
<script>
    var countries = {
        "Andorra": "AD",
        "Arab Emirates": "AE",
        "Afghanistan": "AF",
        "Antigua and Barbuda": "AG",
        "Anguilla": "AI",
        "Albania": "AL",
        "Armenia": "AM",
        "Angola": "AO",
        "Antarctica": "AQ",
        "Argentina": "AR",
        "American Samoa": "AS",
        "Austria": "AT",
        "Australia": "AU",
        "Aruba": "AW",
        "Åland": "AX",
        "Azerbaijan": "AZ",
        "Bosnia and Herzegovina": "BA",
        "Barbados": "BB",
        "Bangladesh": "BD",
        "Belgium": "BE",
        "Burkina Faso": "BF",
        "Bulgaria": "BG",
        "Bahrain": "BH",
        "Burundi": "BI",
        "Benin": "BJ",
        "Saint Barthélemy": "BL",
        "Bermuda": "BM",
        "Brunei": "BN",
        "Bolivia": "BO",
        "Bonaire": "BQ",
        "Brazil": "BR",
        "Bahamas": "BS",
        "Bhutan": "BT",
        "Bouvet Island": "BV",
        "Botswana": "BW",
        "Belarus": "BY",
        "Belize": "BZ",
        "Canada": "CA",
        "Cocos [Keeling] Islands": "CC",
        "Democratic Republic of the Congo": "CD",
        "Central African Republic": "CF",
        "Republic of the Congo": "CG",
        "Switzerland": "CH",
        "Ivory Coast": "CI",
        "Cook Islands": "CK",
        "Chile": "CL",
        "Cameroon": "CM",
        "China": "CN",
        "Colombia": "CO",
        "Costa Rica": "CR",
        "Cuba": "CU",
        "Cape Verde": "CV",
        "Curacao": "CW",
        "Christmas Island": "CX",
        "Cyprus": "CY",
        "Czechia": "CZ",
        "Germany": "DE",
        "Djibouti": "DJ",
        "Denmark": "DK",
        "Dominica": "DM",
        "Dominican Republic": "DO",
        "Algeria": "DZ",
        "Ecuador": "EC",
        "Estonia": "EE",
        "Egypt": "EG",
        "Western Sahara": "EH",
        "Eritrea": "ER",
        "Spain": "ES",
        "Ethiopia": "ET",
        "Finland": "FI",
        "Fiji": "FJ",
        "Falkland Islands": "FK",
        "Micronesia": "FM",
        "Faroe Islands": "FO",
        "France": "FR",
        "Gabon": "GA",
        "United Kingdom": "GB",
        "Grenada": "GD",
        "Georgia": "GE",
        "French Guiana": "GF",
        "Guernsey": "GG",
        "Ghana": "GH",
        "Gibraltar": "GI",
        "Greenland": "GL",
        "Gambia": "GM",
        "Guinea": "GN",
        "Guadeloupe": "GP",
        "Equatorial Guinea": "GQ",
        "Greece": "GR",
        "South Georgia and the South Sandwich Islands": "GS",
        "Guatemala": "GT",
        "Guam": "GU",
        "Guinea-Bissau": "GW",
        "Guyana": "GY",
        "Hong Kong": "HK",
        "Heard Island and McDonald Islands": "HM",
        "Honduras": "HN",
        "Croatia": "HR",
        "Haiti": "HT",
        "Hungary": "HU",
        "Indonesia": "ID",
        "Ireland": "IE",
        "Israel": "IL",
        "Isle of Man": "IM",
        "India": "IN",
        "British Indian Ocean Territory": "IO",
        "Iraq": "IQ",
        "Iran": "IR",
        "Iceland": "IS",
        "Italy": "IT",
        "Jersey": "JE",
        "Jamaica": "JM",
        "Jordan": "JO",
        "Japan": "JP",
        "Kenya": "KE",
        "Kyrgyzstan": "KG",
        "Cambodia": "KH",
        "Kiribati": "KI",
        "Comoros": "KM",
        "Saint Kitts and Nevis": "KN",
        "North Korea": "KP",
        "South Korea": "KR",
        "Kuwait": "KW",
        "Cayman Islands": "KY",
        "Kazakhstan": "KZ",
        "Laos": "LA",
        "Lebanon": "LB",
        "Saint Lucia": "LC",
        "Liechtenstein": "LI",
        "Sri Lanka": "LK",
        "Liberia": "LR",
        "Lesotho": "LS",
        "Lithuania": "LT",
        "Luxembourg": "LU",
        "Latvia": "LV",
        "Libya": "LY",
        "Morocco": "MA",
        "Monaco": "MC",
        "Moldova": "MD",
        "Montenegro": "ME",
        "Saint Martin": "MF",
        "Madagascar": "MG",
        "Marshall Islands": "MH",
        "Macedonia": "MK",
        "Mali": "ML",
        "Myanmar [Burma]": "MM",
        "Mongolia": "MN",
        "Macao": "MO",
        "Northern Mariana Islands": "MP",
        "Martinique": "MQ",
        "Mauritania": "MR",
        "Montserrat": "MS",
        "Malta": "MT",
        "Mauritius": "MU",
        "Maldives": "MV",
        "Malawi": "MW",
        "Mexico": "MX",
        "Malaysia": "MY",
        "Mozambique": "MZ",
        "Namibia": "NA",
        "New Caledonia": "NC",
        "Niger": "NE",
        "Norfolk Island": "NF",
        "Nigeria": "NG",
        "Nicaragua": "NI",
        "Netherlands": "NL",
        "Norway": "NO",
        "Nepal": "NP",
        "Nauru": "NR",
        "Niue": "NU",
        "New Zealand": "NZ",
        "Oman": "OM",
        "Panama": "PA",
        "Peru": "PE",
        "French Polynesia": "PF",
        "Papua New Guinea": "PG",
        "Philippines": "PH",
        "Pakistan": "PK",
        "Poland": "PL",
        "Saint Pierre and Miquelon": "PM",
        "Pitcairn Islands": "PN",
        "Puerto Rico": "PR",
        "Palestine": "PS",
        "Portugal": "PT",
        "Palau": "PW",
        "Paraguay": "PY",
        "Qatar": "QA",
        "Réunion": "RE",
        "Romania": "RO",
        "Serbia": "RS",
        "Russia": "RU",
        "Rwanda": "RW",
        "Saudi Arabia": "SA",
        "Solomon Islands": "SB",
        "Seychelles": "SC",
        "Sudan": "SD",
        "Sweden": "SE",
        "Singapore": "SG",
        "Saint Helena": "SH",
        "Slovenia": "SI",
        "Svalbard and Jan Mayen": "SJ",
        "Slovakia": "SK",
        "Sierra Leone": "SL",
        "San Marino": "SM",
        "Senegal": "SN",
        "Somalia": "SO",
        "Suriname": "SR",
        "South Sudan": "SS",
        "São Tomé and Príncipe": "ST",
        "El Salvador": "SV",
        "Sint Maarten": "SX",
        "Syria": "SY",
        "Swaziland": "SZ",
        "Turks and Caicos Islands": "TC",
        "Chad": "TD",
        "French Southern Territories": "TF",
        "Togo": "TG",
        "Thailand": "TH",
        "Tajikistan": "TJ",
        "Tokelau": "TK",
        "East Timor": "TL",
        "Turkmenistan": "TM",
        "Tunisia": "TN",
        "Tonga": "TO",
        "Turkey": "TR",
        "Trinidad and Tobago": "TT",
        "Tuvalu": "TV",
        "Taiwan": "TW",
        "Tanzania": "TZ",
        "Ukraine": "UA",
        "Uganda": "UG",
        "U.S. Minor Outlying Islands": "UM",
        "United States": "US",
        "Uruguay": "UY",
        "Uzbekistan": "UZ",
        "Vatican City": "VA",
        "Saint Vincent and the Grenadines": "VC",
        "Venezuela": "VE",
        "British Virgin Islands": "VG",
        "U.S. Virgin Islands": "VI",
        "Vietnam": "VN",
        "Vanuatu": "VU",
        "Wallis and Futuna": "WF",
        "Samoa": "WS",
        "Kosovo": "XK",
        "Yemen": "YE",
        "Mayotte": "YT",
        "South Africa": "ZA",
        "Zambia": "ZM",
        "Zimbabwe": "ZW"
    }


    $(function() {
        $( "#checkindate" ).datepicker({
            minDate: new Date(),
            onClose: function( selectedDate ) {
             var d = new Date(selectedDate);
                d.setHours(d.getHours()+24);
                $( "#checkoutdate" ).datepicker( "option", "minDate", d );
            }
        });
        $( "#checkoutdate" ).datepicker({
            onClose: function( selectedDate ) {
                var d = new Date(selectedDate);
                d.setHours(d.getHours()-24);
                $( "#checkindate" ).datepicker( "option", "maxDate", d);
            }
        });
    });

    $(document).ready(function(){

        for(var co in countries){
            var code = countries[co];
            $("#country").append("<option value="+code+">"+co+"</option>");
        }

        autocomplete = new google.maps.places.Autocomplete(
                /** @type {HTMLInputElement} */(document.getElementById('city')),
                {
                    types: ['(cities)'],
                    componentRestrictions: { 'country': "ua" }
                });
        $("#country").change(function() {
            var co = $(this).val();
            //var code = countries[co];
            console.log(co);
            //console.log(code);

            autocomplete.setComponentRestrictions({ 'country': co });
        });
        //autocomplete.setComponentRestrictions({ 'country': country });

        // прячем кнопку #back-top
        $("#back-top").hide();

        // появление/затухание кнопки #back-top
        $(function (){
            $(window).scroll(function (){
                if ($(this).scrollTop() > 100){
                    $('#back-top').fadeIn();
                } else{
                    $('#back-top').fadeOut();
                }
            });

            // при клике на ссылку плавно поднимаемся вверх
            $('#back-top a').click(function (){
                $('body,html').animate({
                    scrollTop:0
                }, 800);
                return false;
            });
        });
    });

    function valideSearchForm() {
        var isValid = true;
        if(document.searchfrm.min_price.value != "") {
            if(document.searchfrm.min_price.value < 0 || (!(/^[0-9]+$/).test(document.searchfrm.min_price.value))) {
                isValid = false;
                document.searchfrm.min_price.style.borderColor = 'red';
                document.searchfrm.max_price.style.borderColor = 'red';
            } else {
                document.searchfrm.min_price.style.borderColor = '#777777';
                document.searchfrm.max_price.style.borderColor = '#777777';
            }
        }
        if(document.searchfrm.max_price.value != "") {
            if(document.searchfrm.max_price.value < 0 || (!(/^[0-9]+$/).test(document.searchfrm.max_price.value))
                    || document.searchfrm.max_price.value < document.searchfrm.min_price.value) {
                isValid = false;
                document.searchfrm.min_price.style.borderColor = 'red';
                document.searchfrm.max_price.style.borderColor = 'red';
            } else {
                document.searchfrm.min_price.style.borderColor = '#777777';
                document.searchfrm.max_price.style.borderColor = '#777777';
            }
        }
        if(document.searchfrm.checkindate.value == "") {
            isValid = false;
            document.searchfrm.checkindate.style.borderColor = 'red';
        } else {
            document.searchfrm.checkindate.style.borderColor = '#777777';
        }
        if(document.searchfrm.checkoutdate.value == "") {
            isValid = false;
            document.searchfrm.checkoutdate.style.borderColor = 'red';
        } else {
            document.searchfrm.checkoutdate.style.borderColor = '#777777';
        }
        if(document.searchfrm.country.value == ""){
            isValid = false;
            document.searchfrm.country.style.borderColor = 'red';
        } else {
            document.searchfrm.country.style.borderColor = '#777777';
        }
        if(document.searchfrm.city.value == "") {
            isValid = false;
            document.searchfrm.city.style.borderColor = 'red';
        } else {
            document.searchfrm.city.style.borderColor = '#777777';
        }
        return isValid;
    }
</script>
</head>

<body>
<div class="wrapper">

    <div id="lights">
           <!-- lights go here -->
    </div>

    <jsp:include page="/WEB-INF/view/header.jsp"/>

    <div class="content">
        <div class="search">
            <form name="searchfrm" method="POST" action="search" onsubmit="return valideSearchForm();">
                <ul>
                    <li> <!--    CHECK IN    -->
                        <b>Check in date <span class="mandatory">*</span>:</b>&nbsp&nbsp
                        <input id="checkindate" name="checkindate" style="width:100px;" value="<%=session.getAttribute("checkindate")!=null?session.getAttribute("checkindate"):"" %>"/>
                    </li>
                    <c:set var="countries" value="${fn:split('UA,USA', ',')}" scope="session" />
                    <c:set var="cities" value="${fn:split('Lviv,Kiev', ',')}" scope="session" />
                    <c:set var="adults" value="${fn:split('1,2,3,4,5,6,7,8', ',')}" scope="session" />
                    <c:set var="children" value="${fn:split('0,1,2,3,4,5,6,7,8', ',')}" scope="session" />
                    <c:set var="currencies" value="${fn:split('USD,EUR,UAH', ',')}" scope="session" />
                    <li>
                        <b>Country <span class="mandatory">*</span>:</b>
                        <select id="country" name="country" style="width: 180px;">
                            <option value=""></option>
                           <%-- <c:forEach items="${countries}" var="country">
                                <option value="${country}" ${sessionScope.country == country ? 'selected' : ''}>${country}</option>
                            </c:forEach>--%>
                        </select>
                    </li>
                    <li>
                        <b>City <span class="mandatory">*</span>:</b>
                        <input id="city" name="city" style="width: 180px;">

                        </input>
                       <%-- <select id="city" name="city" style="width: 180px;">
                                <option value=""></option>
                                <c:forEach items="${cities}" var="city">
                                    <option value="${city}" ${sessionScope.city == city ? 'selected' : ''}>${city}</option>
                                </c:forEach>
                        </select>--%>
                    </li>
                    <%--<li>
                        <b>City Google:</b>
                        <input id="city" name="city" style="width: 180px;">

                        </input>
                    </li>--%>
                </ul>
                <ul>
                    <li>  <!--    CHECK OUT   -->
                        <b>Check out date <span class="mandatory">*</span>:</b>
                        <input id="checkoutdate" name="checkoutdate" style="width:100px;" value="<%=session.getAttribute("checkoutdate")!=null?session.getAttribute("checkoutdate"):"" %>"/>
                    </li>
                    <li>
                        <b>Price:</b>
                        <input type="text" id="min_price" name="min_price" maxlength="9" value="<%=session.getAttribute("min_price")!=null?session.getAttribute("min_price"):"" %>" style="width: 100px;"/> <b>to</b>
                        <input type="text" id="max_price" name="max_price" maxlength="9" value="<%=session.getAttribute("max_price")!=null?session.getAttribute("max_price"):"" %>" style="width: 100px;"/>
                    </li>
                    <li>
                        <select id="currency" name="currency" style="width: 100px">
                            <c:forEach items="${currencies}" var="currency">
                                <option value="${currency}" ${sessionScope.currency == currency ? 'selected' : ''}>${currency}</option>
                            </c:forEach>
                        </select>
                    </li>
                </ul>
                <ul>
                    <li>
                        <b>Adults(18+):</b>
                        <select id="guests_adults" name="guests_adults" style="width: 80px">
                            <c:forEach items="${adults}" var="adult">
                                <option value="${adult}" ${sessionScope.guests_adults == adult ? 'selected' : ''}>${adult}</option>
                            </c:forEach>
                        </select>
                    </li>
                    <li>
                        <b>Children:</b>
                        <select id="guests_children" name="guests_children" style="width: 80px" selected="<%=session.getAttribute("guests_children")!=null?session.getAttribute("guests_children"):"" %>">
                            <c:forEach items="${children}" var="children">
                                <option value="${children}" ${sessionScope.guests_children == children ? 'selected' : ''}>${children}</option>
                            </c:forEach>
                        </select>
                    </li>
                    <li class="submit">
                        <input type="reset" name="reset" value="Reset">
                        <input type="submit" name="search" value="Search">
                    </li>
                </ul>
            </form>
        </div><!-- Search form -->

        <c:if test="${requestScope.hotelDTOs != null}">
        <div class="hotel_info_wrapper">
            <c:forEach items="${requestScope.hotelDTOs}" var="hotel">
                <div class="hotel_info">
                    <p>
                        Hotel: <span>${hotel.getName()}</span>
                        <c:if test="${hotel.getImage_url() != null}">
                            <img src="${hotel.getImage_url()}" style=""/>
                        </c:if>
                        <c:if test="${hotel.getImage_url() == null}">
                            <img src="resources/img/noimage.gif"/>
                        </c:if>
                    </p>
                    <p>Address: <span>${hotel.getAddress()}</span></p>
                    <p>Type: <span>${hotel.getRoomType()}</span></p>
                    <p>Prices: <span>${hotel.getPrice()}</span></p>
                    <p>Occupancy: <span>${hotel.getRoomOccupancy()}</span></p>
                    <p class="submit">
                        <a href="acdetails?idhotel=${hotel.id}" style="color:#0000ff;">Details &rarr;</a>
                    </p>
                </div>
            </c:forEach>
            <br style="clear:both;"/>
        </div>
        </c:if>
        <p id="back-top">
            <a href="#top"><span style="background:#ddd url(resources/img/up-arrow.png) no-repeat center center;"></span>Top</a>
        </p>

    </div><!-- #content -->

    <jsp:include page="/WEB-INF/view/footer.jsp"/>
    <%--<div class="footer">
    </div><!-- #footer -->--%>

</div><!-- #wrapper -->

</body>

</html>
