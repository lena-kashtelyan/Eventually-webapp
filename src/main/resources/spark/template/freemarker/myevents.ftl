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
    <style>
      #description {
        font-family: 'Oxygen', sans-serif;
        font-size: 14px;
      }
    </style>
  </head>

  <body>
    <#include "background.ftl">
    <#include "topbar.ftl">
    <div class="container-fluid" id="everything">
      <div class="row">
          <div class="panel panel-default" id="top-panel">
            <div class="panel-body" id="top-panel-body">
              <div class="col-md-4">
                <h2>memories</h2>
                <#if(savedEvents.length() == 0)
                  <p>seems like you have not yet attended any events!</p>
                </#if>
              </div> 
              <div class="col-md-4">
                <h2>plans</h2>
                <#if(savedEvents.length() == 0)
                  <p>seems like you have not yet attended any events!</p>
                </#if>
              </div> 
              <div class="col-md-4">
                <h2>possibilities</h2>
                <#if(savedEvents.length() == 0)
                  <p>seems like you have not yet attended any events!</p>
                </#if>
              </div>  
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-4" id="past-events">
            <ul class="list-group">
              <#list savedEvents as event>
                <li class="list-group-item">
                  <div class="panel panel-default">
                    <div class="panel-heading">
                      <div class="row" id="event-name">
                        <h2 id="event-link">
                          <div class="col-md-8">
                            <#assign id = event.ID>
                            <a class="event-link" href=${event.ID}>${event.name}</a>
                          </div>
                          <div class="col-md-4">
                            <button type="button" data-placement="bottom" id="attend${event.ID}" name=${event.ID} class="btn btn-default pull-right attend-btn"><#if(attends[id] == true)>attending<#else>attend</#if></button>
                            <button type="button" data-placement="bottom" id="save${event.ID}" name=${event.ID} class="btn btn-default pull-right save-btn"><#if(saves[id] == true)>saved<#else>save</#if></button>
                          </div>
                        </h2>
                      </div>
                    </div>
                    <div class="panel-body">
                      <div class="row">
                        <div class="col-md-12">
                          <div class="wrapper">
                            <img src=${event.eventphoto} class="img-responsive" alt="Responsive image">
                          </div>
                          <div class="col-md-2 col-sm-3 col-xs-3 pull-right" id="event-popularity">
                            ${event.attendingCount}</div>
                        </div>
                        <div class="row">
                          <div class="col-md-10 col-sm-9 col-xs-9" id="venue-name">${event.venueName}</div>
                          <div class="col-md-2 col-sm-3 col-xs-3 pull-right" id="event-popularity">
                          ${event.attendingCount}</div>
                        </div>
                      </div>
                    </div>
                  </div>
                </li>
              </#list>
            </ul>
          </div>
        </div>
      </div> 
    </div>              

    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/dropzone/4.0.1/dropzone.js"></script>
    <script src="https://code.jquery.com/jquery-2.2.3.min.js" integrity="sha256-a23g1Nt4dtEYOj7bR+vTu7+T8VP13humZFBJNIYoEJo="   crossorigin="anonymous"></script>
    <script type="text/javascript" src="js/jquery.cycle.all.2.74.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="js/topbar.js"></script>
    <script src="js/background.js"></script>
    <script src="js/event.js"></script>
<!--<script src='jquery.min.js' type='text/javascript'></script>
    <script src='jquery.ui.widget.js' type='text/javascript'></script>
    <script src='jquery.iframe-transport.js' type='text/javascript'></script>
    <script src='jquery.fileupload.js' type='text/javascript'></script>
    <script src='jquery.cloudinary.js' type='text/javascript'></script> -->
    <#if auth??><span id="auth" class="noshow">${auth}</span></#if>
    <#if username??><span id="username" class="noshow">${username}</span></#if>
  </body>
</html>
