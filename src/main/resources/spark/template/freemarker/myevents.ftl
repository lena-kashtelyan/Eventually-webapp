<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
    <link href='https://fonts.googleapis.com/css?family=Poiret+One' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Cabin' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>${title}</title>
  </head>

  <body>
    <#include "background.ftl">
    <#include "topbar.ftl">
    <#assign saves = userSavedEvents>
    <#assign attends = userAttendingEvents>
    <div class="container-fluid" id="everything">
        <ul class="list-group">
          <li class="list-group-item">
            <div class="panel panel-default" id="everything">
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
      <div class="row">
        <div class="col-md-4" id="past-events">
          <ul class="list-group">
            <#if (past?size == 0) >
            <li class="list-group-item">
              <div class="panel panel-default" id="top-panel">
                <div class="panel-body" id="top-panel-body">
                    <h4 class="description">your history with eventually is a blank slate,
                      <a class"check-out-browse" href=browse?auth=${auth}&username=${username} style="color: #F88017" id="check-out-browse"> many experiences to fill it with!</a></h4>
                </div>
              </div>
            </li>
            </#if>
            <#list past as event>
              <li class="list-group-item">
                <div class="panel panel-default">
                  <div class="panel-heading">
                    <div class="row" id="event-name">
                      <div id="event-link">
                        <div class="col-md-7">
                          <#assign id = event.ID>
                          <a class="event-link" href=${event.ID}>${event.name}</a>
                        </div>
                        <div class="col-md-7">
                          <button type="button" data-placement="bottom" id="attend${event.ID}" name=${event.ID} class="btn btn-sm btn-default pull-right attend-btn"><#if attends[id]>attending<#else>attend</#if></button>
                          <button type="button" data-placement="bottom" id="save${event.ID}" name=${event.ID} class="btn btn-sm btn-default pull-right save-btn"><#if saves[id]>saved<#else>save</#if></button>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="panel-body">
                    <div class="row">
                      <div class="col-md-12">
                        <div>
                          <img src=${event.eventphoto} class="img-responsive" alt="Responsive image">
                        </div>
                        <br>
                        <div class="row">
                        <#assign date = "date">
                        <#assign time = "time">
                        <#assign temp = "temp">
                        <#list event.getStartDate()?split("T") as dt>
                            <#assign date = temp>
                            <#assign time = dt>
                            <#assign temp = dt>
                        </#list>
                          <div class="col-md-8 col-sm-8 col-xs-8" id="venue-name">${date},  ${time?substring(0,5)}<br>${event.venueName}</div>
                          <div class="col-md-4 col-sm-4 col-xs-4 pull-right" id="event-popularity">~ ${event.attendingCount} going</div>
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
                      <a href=map?auth=${auth}&username=${username} style="color: #F88017" id="check-out-map"> check out what is going on around?</a></h4>
                </div>
              </div>
            </li>
            </#if>
            <#list upcoming as event>
              <li class="list-group-item">
                <div class="panel panel-default">
                  <div class="panel-heading">
                    <div class="row" id="event-name">
                      <div id="event-link">
                        <div class="col-md-7">
                          <#assign id = event.ID>
                          <a class="event-link" href=${event.ID}>${event.name}</a>
                        </div>
                        <div class="col-md-5">
                          <button type="button" data-placement="bottom" id="attend${event.ID}" name=${event.ID} class="btn btn-sm btn-default pull-right attend-btn"><#if attends[id]>attending<#else>attend</#if></button>
                          <button type="button" data-placement="bottom" id="save${event.ID}" name=${event.ID} class="btn btn-sm btn-default pull-right save-btn"><#if saves[id]>saved<#else>save</#if></button>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="panel-body">
                    <div class="row">
                      <div class="col-md-12">
                        <div>
                          <img src=${event.eventphoto} class="img-responsive" alt="Responsive image">
                        </div>
                        <br>
                        <div class="row">
                        <#assign date = "date">
                        <#assign time = "time">
                        <#assign temp = "temp">
                        <#list event.getStartDate()?split("T") as dt>
                            <#assign date = temp>
                            <#assign time = dt>
                            <#assign temp = dt>
                        </#list>
                          <div class="col-md-8 col-sm-8 col-xs-8" id="venue-name">${date},  ${time?substring(0,5)}<br>${event.venueName}</div>
                          <div class="col-md-4 col-sm-4 col-xs-4 pull-right" id="event-popularity">~ ${event.attendingCount} going</div>
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
                      <div id="event-link">
                        <div class="col-md-7">
                          <#assign id = event.ID>
                          <a class="event-link" href=${event.ID}>${event.name}</a>
                        </div>
                        <div class="col-md-5">
                          <button type="button" data-placement="bottom" id="attend${event.ID}" name=${event.ID} class="btn btn-sm btn-default pull-right attend-btn"><#if attends[id]>attending<#else>attend</#if></button>
                          <button type="button" data-placement="bottom" id="save${event.ID}" name=${event.ID} class="btn btn-sm btn-default pull-right save-btn"><#if saves[id]>saved<#else>save</#if></button>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="panel-body">
                    <div class="row">
                      <div class="col-md-12">
                        <div>
                          <img src=${event.eventphoto} class="img-responsive" alt="Responsive image">
                        </div>
                        <br>
                        <div class="row">
                        <#assign date = "date">
                        <#assign time = "time">
                        <#assign temp = "temp">
                        <#list event.getStartDate()?split("T") as dt>
                            <#assign date = temp>
                            <#assign time = dt>
                            <#assign temp = dt>
                        </#list>
                          <div class="col-md-8 col-sm-8 col-xs-8" id="venue-name">${date},  ${time?substring(0,5)}<br>${event.venueName}</div>
                          <div class="col-md-4 col-sm-4 col-xs-4 pull-right" id="event-popularity">~ ${event.attendingCount} going</div>
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
    <script src="js/myevents.js"></script>
    <#if auth??><span id="auth" class="noshow">${auth}</span></#if>
    <#if username??><span id="username" class="noshow">${username}</span></#if>
  </body>
</html>
