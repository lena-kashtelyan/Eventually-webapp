<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.css" />
	  <link rel="stylesheet" href="http://libs.cartocdn.com/cartodb.js/v3/3.15/themes/css/cartodb.css" />
    <link rel="stylesheet" type="text/css" href="css/topbar.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
  </head>

  <body>
    
    <#include "topbar.ftl">

    <div class="container">
      <div class="col-md-6">
        <img src="http://weknowyourdreamz.com/images/party-wallpaper/party-wallpaper-08.jpg" class="img-responsive" alt="Responsive image">
        <div class="container-fluid">
          <p> event title </p>
        </div>
        <div class="container-fluid">
          <p> event description.... </p>
        </div>
        <div class="container-fluid">
          <p> EVENT POPULARITY STATUS </p>
        </div>
        <div class="container-fluid">
          <p> location </p>
        </div>
      </div>

      <div class="col-md-6">
        <ul class="list-group">
          <!-- EVENT STORYSTREAM ENTRY STUB STARTS -->
          <li class="list-group-item">
            <div class="panel panel-default">
              <div class="panel-heading">
                @jjannotti took a picture:
              </div>
              <div class="panel-body">
                <img src="https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcR5XZQV93jqbJoPH0CNSdJ1qO0Y6cMu_ZtbwLF8vrBTjghS8lRckH8pDnnG" class="img-responsive" alt="Responsive image">
              </div>
            </div>
          </li>
          <!-- EVENT STORYSTREAM ENTRY STUB ENDS -->

          <!-- EVENT STORYSTREAM ENTRY STUB STARTS -->
          <li class="list-group-item">
            <div class="panel panel-default">
              <div class="panel-heading">
                @jjannotti commented:
              </div>
              <div class="panel-body">
                <p>Awesome gathering, you do not want to miss this!</p>
              </div>
            </div>
          </li>
          <!-- EVENT STORYSTREAM ENTRY STUB ENDS -->
        </ul>
      </div>
    </div>


    <script src="https://code.jquery.com/jquery-2.2.3.min.js" integrity="sha256-a23g1Nt4dtEYOj7bR+vTu7+T8VP13humZFBJNIYoEJo="   crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  </body>
</html>
