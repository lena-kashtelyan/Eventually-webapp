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
    <style type="cartocss/text" id="simpleOrange">
      #events{
        marker-fill-opacity: 0.9;
        marker-line-color: #FFF;
        marker-line-width: 1.5;
        marker-line-opacity: 1;
        marker-placement: point;
        marker-type: ellipse;
        marker-width: 10;
        marker-fill: #FF6600;
        marker-allow-overlap: true;
      }
    </style>
    <style type="cartocss/text" id="bubble">
    /** bubble visualization */
      #events{
        marker-fill-opacity: 0.9;
        marker-line-color: #FFF;
        marker-line-width: 1;
        marker-line-opacity: 1;
        marker-placement: point;
        marker-multi-policy: largest;
        marker-type: ellipse;
        marker-fill: #FF5C00;
        marker-allow-overlap: true;
        marker-clip: false;
      }
      #events [ attendingcount <= 14166] {
         marker-width: 35.0;
      }
      #events [ attendingcount <= 820] {
         marker-width: 32.8;
      }
      #events [ attendingcount <= 407] {
         marker-width: 30.6;
      }
      #events [ attendingcount <= 296] {
         marker-width: 28.3;
      }
      #events [ attendingcount <= 219] {
         marker-width: 26.1;
      }
      #events [ attendingcount <= 162] {
         marker-width: 23.9;
      }
      #events [ attendingcount <= 125] {
         marker-width: 21.7;
      }
      #events [ attendingcount <= 92] {
         marker-width: 19.4;
      }
      #events [ attendingcount <= 61] {
         marker-width: 17.2;
      }
      #events [ attendingcount <= 30] {
         marker-width: 15.0;
      }
    </style>
    <style type="cartocss/text" id="color">
      /** category visualization */

      #events {
         marker-fill-opacity: 0.9;
         marker-line-color: #FFF;
         marker-line-width: 1;
         marker-line-opacity: 1;
         marker-placement: point;
         marker-type: ellipse;
         marker-width: 10;
         marker-allow-overlap: true;
      }

      #events[category="category"] {
         marker-fill: #A6CEE3;
      }
      #events[category="food fest"] {
         marker-fill: #1F78B4;
      }
      #events[category="movies and art"] {
         marker-fill: #B2DF8A;
      }
      #events[category="nightlife"] {
         marker-fill: #33A02C;
      }
      #events[category="other"] {
         marker-fill: #FB9A99;
      }
      #events[category="public lecture"] {
         marker-fill: #E31A1C;
      }
      #events[category="religious and cultural celebration"] {
         marker-fill: #FDBF6F;
      }
      #events[category="sports"] {
         marker-fill: #FF7F00;
      }
      #events[category="theater and performance"] {
         marker-fill: #CAB2D6;
      }
      #events[category="workshop"] {
         marker-fill: #6A3D9A;
      }
    </style>
  </head>

  <body>
    <#include "background.ftl">
    <#include "topbar.ftl">

    <!--EMBED MAP-->
    <div id="map-supercontainer">
      <div id="map-container"></div>
    </div>
    <div id="display-options" class>
      <button id="search-icon" type="button" class="btn btn-default btn-circle btn-lg"><span class="glyphicon glyphicon-search search-icon"></span></button>
      <button id="remove-circle-icon" type="button" class="btn btn-default btn-circle btn-lg noshow"><span class="remove-circle-icon"><b>X</b></span></button>
    </div>
    <div id="cartocss" class="layer_selector floating-menu container noshow">
      <div>
        <ul>
          <div class="row">
            <button class="btn btn-default col-xs-12"><li data="color" data-type="cartocss">Color based on Category</li></button>
          </div>
          <div class="row">
            <button class="btn btn-default col-xs-12"><li data="bubble" data-type="cartocss">Size based on Attending Count</li></button>
          </div>
          <div class="row">
            <button class="btn btn-default col-xs-12"><li data="simpleOrange" data-type="cartocss">Default</li></button>
          </div>
        </ul>
      </div>
      <div id="color-legend" class='cartodb-legend category noshow'>
      <ul>
        <li>
        <div class="bullet" style="background: #A6CEE3"></div> category
        </li>
        <li>
        <div class="bullet" style="background: #1F78B4"></div> food fest
        </li>
        <li>
        <div class="bullet" style="background: #B2DF8A"></div> movies and art
        </li>
        <li>
        <div class="bullet" style="background: #33A02C"></div> nightlife
        </li>
        <li>
        <div class="bullet" style="background: #FB9A99"></div> other
        </li>
        <li>
        <div class="bullet" style="background: #E31A1C"></div> public lecture
        </li>
        <li>
        <div class="bullet" style="background: #FDBF6F"></div> religious and cultural
        </li>
        <li>
        <div class="bullet" style="background: #FF7F00"></div> sports
        </li>
        <li>
        <div class="bullet" style="background: #CAB2D6"></div> theater and performance
        </li>
        <li>
        <div class="bullet" style="background: #6A3D9A"></div> workshop
        </li>
      </ul>
      </div>
    </div>
    <script src="https://code.jquery.com/jquery-2.2.3.min.js" integrity="sha256-a23g1Nt4dtEYOj7bR+vTu7+T8VP13humZFBJNIYoEJo="   crossorigin="anonymous"></script>
    <script type="text/javascript" src="js/jquery.cycle.all.2.74.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="js/topbar.js"></script>
    <script src="js/background.js"></script>
    <script type="infowindow/html" id="infowindow_template">
      <div class="cartodb-popup v2">
        <a href="#close" class="cartodb-popup-close-button close">x</a>
        <div class="cartodb-popup-content-wrapper">
          <div class="cartodb-popup-content">
            <p style="color:black;font-size:1.2rem">{{name}}</p>
            <p>{{description}}</p>
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
