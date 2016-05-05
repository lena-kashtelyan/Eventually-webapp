<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.css" />
	  <link rel="stylesheet" href="http://libs.cartocdn.com/cartodb.js/v3/3.15/themes/css/cartodb.css" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
    <link href='https://fonts.googleapis.com/css?family=Poiret+One' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="css/map.css" />
    <link rel="stylesheet" href="css/topbar.css" />
    <script src="http://libs.cartocdn.com/cartodb.js/v3/3.15/cartodb.js"></script>
    <script src="js/map.js"></script>
  </head>

  <body>
    <div>
      <#include "background.ftl">
      <#include "topbar.ftl">
    </div>

    <!--EMBED MAP-->
    <div id="map-container"></div>
    <script src="https://code.jquery.com/jquery-2.2.3.min.js" integrity="sha256-a23g1Nt4dtEYOj7bR+vTu7+T8VP13humZFBJNIYoEJo="   crossorigin="anonymous"></script>
    <script type="text/javascript" src="js/jquery.cycle.all.2.74.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="js/topbar.js"></script>
    <script src="js/background.js"></script>
    <#if auth??><span id="auth" class="noshow">${auth}</span></#if>
    <#if username??><span id="username" class="noshow">${username}</span></#if>
  </body>
</html>
