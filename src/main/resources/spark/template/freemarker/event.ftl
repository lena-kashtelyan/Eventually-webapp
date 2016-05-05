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
    <#include "background.ftl">
    <#include "topbar.ftl">
    <div class="container" id="feed">
      <ul class="list-group">
        <li class="list-group-item">
          <div class="panel panel-default">
            <div class="panel-heading">
              <div class="row" id="event-name">
                <h2>
                  <div class="col-md-8">
                    <a id="event-link" href="876362925794780">${event.name}</a>
                  </div>
                  <div class="col-md-4">
                    <button type="button" data-toggle="tooltip" data-placement="bottom"title="attend event" id="attend-btn" class="btn btn-default pull-right">
                      <i class="fa fa-check" aria-hidden="true"></i>
                    </button>
                    <button type="button" data-toggle="tooltip" data-placement="bottom"title="save event" id="save-btn" class="btn btn-default pull-right">
                      <i class="fa fa-star" aria-hidden="true"></i>
                    </button>
                  </div>
                </h2>
              </div>
            </div>
            <div class="panel-body">
              <div class="row">
                <div class="col-md-6">
                  <div class="row" id="event-image">
                    <div class="col-md-12">
                      <img src=${event.eventphoto} class="img-responsive" alt="Responsive image">
                    </div>
                  </div>
                  <div class="row" id="event-popularity">
                    <div class="col-md-2 col-sm-3 col-xs-3" id="event-popularity">${event.attendingCount}</div>
                    <div class="col-md-10 col-sm-9 col-xs-9" id="venue-name">Venue</div>
                  </div>
                </div>
                <div class="col-md-6">
                  ${event.description}
                </div>
              </div>
            </div>
          </div>
        </li>
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
   


    <script src="https://code.jquery.com/jquery-2.2.3.min.js" integrity="sha256-a23g1Nt4dtEYOj7bR+vTu7+T8VP13humZFBJNIYoEJo="   crossorigin="anonymous"></script>
    <script type="text/javascript" src="js/jquery.cycle.all.2.74.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="js/topbar.js"></script>
    <script src="js/background.js"></script>
    <#if auth??><span id="auth" class="noshow">${auth}</span></#if>
    <#if username??><span id="username" class="noshow">${username}</span></#if>
  </body>
</html>
