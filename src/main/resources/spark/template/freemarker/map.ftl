<!DOCTYPE html>
<html>
  <head>
    <!-- NEED TO ADD THIS FIRST LINE TO ALL FTLs -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- END -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.css" />
	  <link rel="stylesheet" href="http://libs.cartocdn.com/cartodb.js/v3/3.15/themes/css/cartodb.css" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
    <link href='https://fonts.googleapis.com/css?family=Poiret+One' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="css/map.css" />
    <link rel="stylesheet" href="css/topbar.css" />
    <script src="http://libs.cartocdn.com/cartodb.js/v3/3.15/cartodb.js"></script>
    <script src="js/map.js"></script>
    <style id="jlkasjdlfjsalfd">

    </style>
  </head>

  <body>
    <#include "background.ftl">
    <#include "topbar.ftl">

    <!--EMBED MAP-->
    <div id="map-supercontainer">
      <div id="map-container"></div>
    </div>
    <script src="https://code.jquery.com/jquery-2.2.3.min.js" integrity="sha256-a23g1Nt4dtEYOj7bR+vTu7+T8VP13humZFBJNIYoEJo="   crossorigin="anonymous"></script>
    <script type="text/javascript" src="js/jquery.cycle.all.2.74.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="js/topbar.js"></script>
    <script src="js/background.js"></script>
    <script type="infowindow/html" id="infowindow_template">
    <div class="cartodb-popup">
      <a href="#close" class="cartodb-popup-close-button close">x</a>

      <div class="cartodb-popup-content-wrapper">
        <div class="cartodb-popup-content">
          <p style="color:black;font-size:1.2rem">{{name}}</p>
          <p>{{eventid}}</p>
          <a href="/event?auth=${auth}&username=${username}&eventID={{eventid}}"><img src={{eventphoto}}></a>
        </div>
      </div>
      <div class="cartodb-popup-tip-container"></div>
    </div>
    </script>
    <#if auth??><span id="auth" class="noshow">${auth}</span></#if>
    <#if username??><span id="username" class="noshow">${username}</span></#if>
  </body>
</html>
