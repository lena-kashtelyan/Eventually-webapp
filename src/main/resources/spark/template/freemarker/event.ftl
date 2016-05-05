<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
    <link href='https://fonts.googleapis.com/css?family=Poiret+One' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="css/topbar.css">
    <link rel="stylesheet" type="text/css" href="css/browse.css">
    <title>${title}</title>
  </head>

  <body>
    
    <#include "topbar.ftl">

    <div class="container">
      <div class="col-md-6">
        <img src=${event.eventphoto} class="img-responsive" alt="Responsive image">
        <div class="container-fluid">
          <p>${event.name}</p>
        </div>
        <div class="container-fluid">
          <p>${event.description}</p>
        </div>
        <div class="container-fluid">
          <p>${event.attendingCount}</p>
        </div>
        <div class="container-fluid">
          <p>Venue</p>
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
    <script type="text/javascript" src="js/jquery.cycle.all.2.74.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="js/topbar.js"></script>
    <script src="js/background.js"></script>
    <#if auth??><span id="auth" class="noshow">${auth}</span></#if>
    <#if username??><span id="username" class="noshow">${username}</span></#if>
  </body>
</html>
