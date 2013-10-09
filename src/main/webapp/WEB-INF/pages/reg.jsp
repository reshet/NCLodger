<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>NCLodger | Registration</title>
        <link href="css/style.css" rel="stylesheet" type="text/css">
            <script src="js/reg.js" type="text/javascript"></script>
    </head>

    <body>
        <div class="top">	
            <a href="index.jsp">Home</a>
        </div> 
        <div class="wrapper">   

            <!-- SIGN UP FORM -->
            <form class="form_signup" name="signup" action="" method="post" onsubmit="return check();">

                <div class="formtitle">Sign up</div>

                <div class="input">
                    <div class="inputtext">Full name: </div>
                    <div class="inputcontent">                
                        <input type="text" name="fname" id="fnmae" onkeyup="check();" />
                        <img src="img/invalid.png" id="fname_valid" style="display: none;"  />                   
                    </div>
                </div>          

                <div class="input">
                    <div class="inputtext">User type: </div>
                    <div class="inputcontent">
                        <select name="user_type[]">
                            <option value="customer">Customer</option>
                            <option value="sales manager">Sales manager</option>
                        </select> 
                    </div>
                </div>

                <div class="input">            
                    <div class="inputtext">Email:</div>
                    <div class="inputcontent">
                        <input type="text" name="email" id="email" onkeyup="check();" />  
                        <img src="img/invalid.png" id="email_valid" style="display: none;"  />                  
                    </div>
                </div>                                   

                <div class="input nobottomborder">
                    <div class="inputtext">Password: </div>
                    <div class="inputcontent">
                        <input type="password" name="pass" id="pass" onkeyup="check();" />
                        <img src="img/invalid.png" id="pass_valid" style="display: none;"  />
                    </div>
                </div>

                <div class="input nobottomborder">
                    <div class="inputtext">Confirm password: </div>
                    <div class="inputcontent">
                        <input type="password" name="pass_confirm" id="pass_confirm" onkeyup="check();" />
                        <img src="img/invalid.png" id="pass_valid2" style="display: none;"  />
                    </div>
                </div>            

                <div class="buttons">
                    <input class="orangebutton" type="submit" value="Submit" />
                    <input class="greybutton" type="button" value="Reset" onclick="this.form.reset();" />               
                </div>

            </form>

        </div> <!-- wrapper div end -->

    </body>
</html>
