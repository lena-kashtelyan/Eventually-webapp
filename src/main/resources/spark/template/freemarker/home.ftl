<!DOCTYPE html>
<html>
  <head>
    <style>
    </style>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.css" />
	<link rel="stylesheet" href="http://libs.cartocdn.com/cartodb.js/v3/3.15/themes/css/cartodb.css" />
    <link rel="stylesheet" type="text/css" href="css/styling.css">
    <script src="js/bootstrap.min.js">
    </script>
  </head>

  <body id="body" class="noselect"> <!--onload="loadCanvas()"--> 

    <#include "topbar.ftl">

    <div>
    	<div id="empty-div" style="visibility: hidden; height:50px;">.</div>
    	<div id="map-container" onload="embedMap("map-container")></div>
    </div>
    
    <div id="button-container"></div>
    
    <script src="js/map.js"></script>
    <script src="https://code.jquery.com/jquery-2.2.3.min.js" integrity="sha256-a23g1Nt4dtEYOj7bR+vTu7+T8VP13humZFBJNIYoEJo="   crossorigin="anonymous"></script>
	<script src="http://libs.cartocdn.com/cartodb.js/v3/3.15/cartodb.js"></script>

  </body>
</html>