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
      <#list events as event>
        <li class="list-group-item">
          <div class="panel panel-default">
            <div class="panel-heading">
              <div class="row" id="event-name">
                <h2 id="event-link">
                  <div class="col-md-8">
                    <a class="event-link" href="876362925794780">${event.name}</a>
                  </div>
                  <div class="col-md-4">
                    <button type="button" data-toggle="tooltip" data-placement="bottom"title="attend event" id="attend-btn" class="btn btn-default pull-right attent-btn">
                      <!-- <i class="fa fa-check" aria-hidden="true"></i> -->
                      attend
                    </button>
                    <button id="save-btn" name="save-btn" type="button" data-toggle="tooltip" data-placement="bottom" title="save event" id="save-btn" class="btn btn-default pull-right save-btn">
                      <!-- <i class="fa fa-star" aria-hidden="true"></i> -->
                      save
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
                </div>
                <div class="col-md-6" id="description">
                  <#assign description = event.description>
                  <#if (description?length > 881)>
                    <p>${description[0..880]}<a href="#full-description${event.attendingCount}" id="expand-btn" data-toggle="collapse">...</a>
                    <div id="full-description${event.attendingCount}" class="collapse">
                        ${description[881..]}
                    </div></p>
                  <#else>
                    ${event.description}
                  </#if>
                  <div class="row">
                    <div class="col-md-10 col-sm-9 col-xs-9" id="venue-name">Venue</div>
                    <div class="col-md-2 col-sm-3 col-xs-3 pull-right" id="event-popularity">
                    ${event.attendingCount}</div>
                  </div>
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
    <script src="js/browse.js"></script>
    <script src="js/topbar.js"></script>
    <script src="js/background.js"></script>
    <#if auth??><span id="auth" class="noshow">${auth}</span></#if>
    <#if username??><span id="username" class="noshow">${username}</span></#if>
  </body>
</html>

      