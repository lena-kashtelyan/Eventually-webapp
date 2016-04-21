<!DOCTYPE html>
<html>
  <head>
    <style>
    </style>
<!--     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.css" />
  <link rel="stylesheet" href="http://libs.cartocdn.com/cartodb.js/v3/3.15/themes/css/cartodb.css" /> -->
    <link rel="stylesheet" type="text/css" href="css/styling.css">
    <script src="js/bootstrap.min.js">
    </script>
  </head>

  <body id="body" class="noselect"> <!--onload="loadCanvas()"--> 

    <#include "topbar.ftl">
    
    <div id="center-container">
      <div id="response">
        <p> Here is your password: PASSWORD </p>
        <form method="GET" action="/home">       <!--WHERE DOES THIS LEAD?-->
          <button type="submit" class="submit-btn" id="create-btn" value="let me in!"></button>
        </form>
      </div> 
    </div>
  </body>
 </html> 