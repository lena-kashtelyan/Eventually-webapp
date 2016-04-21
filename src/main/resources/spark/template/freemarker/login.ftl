<!DOCTYPE html>
<html>
  <head>
    <style>
    </style>
    <script src="js/jquery-2.1.1.js"></script>
    <link rel="stylesheet" type="text/css" href="css/styling.css">
    <script src="js/facebooklogin.js"></script>
    <script src="js/bootstrap.min.js">
    </script>
  </head>

  <body id="body" class="noselect"> <!--onload="loadCanvas()"--> 

    <#include "topbar.ftl">

   	<div id="center-container">
  		<div id="facebook-btn">
        <div id="fb-root"></div>
  			<div id="fb-button" class="fb-login-button" data-max-rows="1" data-size="xlarge" data-show-faces="false" data-auto-logout-link="true" scope="public_profile,email" onlogin="checkLoginState();"></div>
  		</div>
    	<div id="login-info">
    		<form action="/home">       
					<input type="text" id="username" placeholder="username"> <br>
					<input type="password" id="password" placeholder="password"> <br>
          <!-- <div class="submit-btn-wrapper"> -->
            <input type="submit" class="submit-btn" id="okay-btn" value="okay">
          <!-- </div> -->
				</form>
	    </div>
    </div>

    <div id="bottom-container">
      <a href="/forgot" id="forgot-link">forgot password?</a>
      <form action="/signup">
        <input type="submit" class="submit-btn" id="signup-btn" value="sign up">
      </form>
    </div>
  </body>
</html>