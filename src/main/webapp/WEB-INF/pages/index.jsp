<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>NCLodger | Hotel booking  </title>
        <link href="css/style.css" rel="stylesheet" type="text/css"> 
    </head>

    <body>	
        <div class="top">
            <a href="">Home</a>    	
            <a href="">Log in</a>  
            <a href="reg.jsp">Sign up</a>
        </div> 

        <div class="wrapper">    

            <!-- SIGN IN FORM -->
            <form class="form_signin" name="signin" action="" method="post">

                <div class="formtitle">Sign in</div>

                <div class="input">            
                    <div class="inputtext">Email: </div>                
                    <div class="inputcontent"> 
                        <input type="text" name="email" id="email" />                   
                    </div>
                </div>

                <div class="input nobottomborder">
                    <div class="inputtext">Password: </div>
                    <div class="inputcontent">
                        <input type="password" name="pass" id="pass" />
                        <a href="#">Forgot?</a>
                    </div>
                </div>

                <div class="buttons">
                    <input class="orangebutton" type="submit" value="Sign in" />
                    <input class="greybutton" type="button" value="Cancel" />
                    <input class="openidbutton" type="button" value="Sign in with Google" />
                </div>

                <!-- SIGN IN FORM END -->    
            </form>                

        </div> <!-- wrapper div end -->

    </body>
</html>

