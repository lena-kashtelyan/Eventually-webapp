<!DOCTYPE html>
<html>
  <head>
    <style>
    </style>
    <script src="js/jquery-2.1.1.js"></script>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/topbar.css">
    <link rel="stylesheet" type="text/css" href="css/login.css">
    <script src="js/bootstrap.min.js">
    </script>
  </head>

  <body id="body" class="noselect"> <!--onload="loadCanvas()"--> 

    <#include "topbar.ftl">

   	<div id="fb-root"></div>
	<script src="js/facebooklogin.js"></script>


    <div id="background-container">

      <!--<img id="pic" src="http://ahamot.org/wp-content/uploads/top.jpg"/>-->

      <div id="under-topbar-container">
      	<div id="buttons-container">
      		<div class="facebook-btn">
      			<div id="fb-button" class="fb-login-button" data-max-rows="1" data-size="xlarge" data-show-faces="false" data-auto-logout-link="true"></div>
      		</div>
	      	<div id="login-info">
	      		<form action="">
					<input type="text" id="username" placeholder="username"> <br>
					<input type="password" id="password" placeholder="password">
				</form>
			</div>
		</div>
      </div>

    </div>

  </body>
</html>