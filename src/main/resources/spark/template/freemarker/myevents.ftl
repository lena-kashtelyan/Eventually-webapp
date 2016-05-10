<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
    <link href='https://fonts.googleapis.com/css?family=Poiret+One' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Cabin' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>${title}</title>
    <style>
      h4 {
        font-family: 'Cabin', sans-serif;
        font-size: 16px;
        line-height: 1.4;
      }
    </style>
  </head>

  <body>
    <#include "background.ftl">
    <#include "topbar.ftl">
    <#assign saves = userSavedEvents>
    <#assign attends = userAttendingEvents>
    <div class="container-fluid" id="everything">
      <div class="row">
        <br>
        <ul class="list-group">
          <li class="list-group-item">
            <div class="panel panel-default" id="top-panel">
              <div class="panel-body" id="top-panel-body">
                <div class="col-md-4">
                  <h2 style="text-align: center; color: white">memories</h2>
                </div>
                <div class="col-md-4">
                  <h2 style="text-align: center; color: white">plans</h2>
                </div>
                <div class="col-md-4">
                  <h2 style="text-align: center; color: white">possibilities</h2>
                </div>
              </div>
            </div>
          </li>
        </ul>
      </div>
      <div class="row">
        <div class="col-md-4" id="past-events">
          <ul class="list-group">
            <#if (past?size == 0) >
            <li class="list-group-item">
              <div class="panel panel-default" id="top-panel">
                <div class="panel-body" id="top-panel-body">
                    <h4 class="description">your history with eventually is a blank slate,
                      <a href="#" style="color: #F88017" id="check-out-browse"> many experiences to fill it with!</a></h4>
                </div>
              </div>
            </li>
            </#if>
            <#list past as event>
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
                          <button type="button" data-placement="bottom" id="attend${event.ID}" name=${event.ID} class="btn btn-default pull-right attend-btn"><#if (attends[id] == true) >attending<#else>attend</#if></button>
                          <button type="button" data-placement="bottom" id="save${event.ID}" name=${event.ID} class="btn btn-default pull-right save-btn"><#if (saves[id] == true)>saved<#else>save</#if></button>
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
                          ${event.attendingCount}
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </li>
            </#list>
          </ul>
        </div>
        <div class="col-md-4" id="past-events">
          <ul class="list-group">
            <#if (upcoming?size == 0) >
            <li class="list-group-item">
              <div class="panel panel-default" id="top-panel">
                <div class="panel-body" id="top-panel-body">
                    <h4 class="description">you have not yet made any plans,
                      <a href="#" style="color: #F88017" id="check-out-map"> check out what is going on around?</a></h4>
                </div>
              </div>
            </li>
            </#if>
            <#list upcoming as event>
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
                          <button type="button" data-placement="bottom" id="attend${event.ID}" name=${event.ID} class="btn btn-default pull-right attend-btn"><#if (attends[id] == true) >attending<#else>attend</#if></button>
                          <button type="button" data-placement="bottom" id="save${event.ID}" name=${event.ID} class="btn btn-default pull-right save-btn"><#if (saves[id] == true)>saved<#else>save</#if></button>
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
                          ${event.attendingCount}
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </li>
            </#list>
          </ul>
        </div>
        <div class="col-md-4" id="past-events">
          <ul class="list-group">
            <#if (suggested?size == 0) >
            <li class="list-group-item">
              <div class="panel panel-default" id="top-panel">
                <div class="panel-body" id="top-panel-body">
                    <h4 class="description">at the moment we do not have enough information to suggest events we think you would enjoy, but stay tuned!</h4>
                </div>
              </div>
            </li>
            </#if>
            <#list suggested as event>
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
                          <button type="button" data-placement="bottom" id="attend${event.ID}" name=${event.ID} class="btn btn-default pull-right attend-btn"><#if (attends[id] == true) >attending<#else>attend</#if></button>
                          <button type="button" data-placement="bottom" id="save${event.ID}" name=${event.ID} class="btn btn-default pull-right save-btn"><#if (saves[id] == true)>saved<#else>save</#if></button>
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
                          ${event.attendingCount}
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </li>
            </#list>
          </ul>
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
