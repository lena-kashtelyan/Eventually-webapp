<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
    <link href='https://fonts.googleapis.com/css?family=Poiret+One' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" type="text/css" href="css/topbar.css">
    <link rel="stylesheet" type="text/css" href="css/browse.css">
    <title>${title}</title>
  </head>

  <body> 
    <#include "background.ftl">
    <#include "topbar.ftl">
    <div class="container" id="feed">
      <ul class="list-group">
      <#list events as event>
        <li class="list-group-item">
          <div class="panel panel-default">
            <div class="panel-body">
              <div class="col-md-4">
                <img src=${event.eventphoto} class="img-responsive img-thumbnail" alt="Responsive image">
              </div>
              <div class="col-md-8">
                <div class="row" id="event-name">${event.name}</div>
                <div class="row" id="event-description">${event.description}</div>
                <div class="row" id="event-popularity">
                  <div class="col-md-2 col-sm-3 col-xs-3" id="event-popularity">${event.attendingCount}</div>
                  <div class="col-md-10 col-sm-9 col-xs-9" id="venue-name">${event.venueName}</div>
                </div>
              </div>
            </div>
          </div>
        </li>
      </#list>
      </ul>
    </div>
    <script src="https://code.jquery.com/jquery-2.2.3.min.js" integrity="sha256-a23g1Nt4dtEYOj7bR+vTu7+T8VP13humZFBJNIYoEJo="   crossorigin="anonymous"></script>
    <script type="text/javascript" src="js/jquery.cycle.all.2.74.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="js/topbar.js"></script>
    <script src="js/background.js"></script>
    <script src="js/browse.js"></script>
    <#if auth??><span id="auth" class="noshow">${auth}</span></#if>
    <#if username??><span id="username" class="noshow">${username}</span></#if>
  </body>
</html>

      