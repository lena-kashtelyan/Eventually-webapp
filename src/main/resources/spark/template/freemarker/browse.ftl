<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
    <link href='https://fonts.googleapis.com/css?family=Poiret+One' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Josefin+Sans:300,700' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-slider/7.0.2/css/bootstrap-slider.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-slider/7.0.2/css/bootstrap-slider.min.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap-datetimepicker.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>${title}</title>
  </head>

  <body>
    <#include "background.ftl">
    <#include "topbar.ftl">
    <#assign saves = userSavedEvents>
    <#assign attends = userAttendingEvents>
    <div class="container-fluid" id="feed">
      <ul class="list-group">
        <br>
        <li class="list-group-item">
          <div class="panel panel-default" id="refine-panel">
            <div class="panel-body" id="refine-block">
              <div class="row">
                <div class="col-md-3 col-sm-12 col-xs-12">
                  <div class="row">
                    <div class="col-md-12 col-sm-2 col-xs-2">
                      <label class="control-label requiredField" for="search-location"><h2>near</h2></label>
                    </div>
                    <div class="col-md-12 col-sm-9 col-xs-9">
                      <br>
                      <input class="form-control form-control-inner input-lg" id="search-location" name="search-location" placeholder="location" type="text">
                    </div>
                  </div>
                </div>
                <div class="col-md-3 col-sm-12 col-xs-12">
                  <div class="row">
                    <div class="col-md-12 col-sm-2 col-xs-2">
                      <label class="control-label requiredField" for="radius"><h2>within</h2></label>
                    </div>
                    <div class="col-md-12 col-sm-9 col-xs-9">
                      <br>
                      <span style="float: left">1mi</span>
                      <span style="margin-left: 146px">50mi</span>
                      <br>
                      <span style="width:100%">
                      <input id="radius" data-slider-id='radiusSlider' type="text" data-slider-min="1" data-slider-max="50" data-slider-step="1" data-slider-value="9" data-placement="bottom"/>
                    </span>
                    </div>
                  </div>
                </div>
                <div class="col-md-2 col-sm-6 col-xs-12">
                  <div class="row">
                    <div class="col-md-12 col-sm-4 col-xs-2">
                      <label class="control-label requiredField" for="floor-time"><h2>from</h2></label>
                    </div>
                    <div class="col-md-12 col-sm-8 col-xs-10">
                      <br>
                      <div class="input-append date form_datetime" id="floor" data-date="2016-05-10T15:25:00Z">
                        <input class="input input-lg input-block" style="color:black; font-family: montserrat; font-weight: 200; font-size: 14px; padding: 5px" type="text" id="floorDT">
                        <span class="add-on"><i class="icon-remove"></i></span>
                        <span class="add-on"><i class="icon-th"></i></span>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="col-md-2 col-sm-6 col-xs-12">
                  <div class="row">
                    <div class="col-md-12 col-sm-4 col-xs-2">
                      <label class="control-label requiredField" for="ceiling-time"><h2>until</h2></label>
                    </div>
                    <div class="col-md-12 col-sm-8 col-xs-10">
                      <br>
                      <div class="input-append date form_datetime" id="ceiling" data-date="2016-05-10T15:25:00Z">
                        <input class="input input-lg input-block" style="color:black; font-family: raleway; font-weight: 400; font-size: 14px; padding: 5px" type="text" id="ceilingDT">
                        <span class="add-on"><i class="icon-remove"></i></span>
                        <span class="add-on"><i class="icon-th"></i></span>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="col-md-2 col-sm-12 col-xs-12">
                  <div class="row">
                    <div class="col-md-0 col-sm-7 col-xs-0"></div>
                    <h4><div class="col-md-12 col-sm-4 col-xs-2"></div>
                    <div class="col-md-12 col-sm-2 col-xs-3">
                      <div class="radio">
                        <label>
                          <input type="radio" name="bp" value="byProximity" id="prox" checked> by proximity
                        </label>
                      </div>
                    </div>
                    <div class="col-md-12 col-sm-2 col-xs-3">
                      <div class="radio">
                        <label>
                          <input type="radio" name="bp" value="byPopularity" id="pop"> by popularity
                        </label>
                      </div>
                    </div></h4>
                    <div class="col-md-12 col-sm-3 col-xs-3">
                      <button type="button" id="search-refine-btn" class="btn btn-default btn-lg btn-block">search</button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </li>
      <#list events as event>
        <li class="list-group-item">
          <div class="panel panel-default">
            <div class="panel-heading">
              <div class="row" id="event-name">
                <div class="col-md-8">
                  <#assign id = event.ID>
                  <h2><a class="event-link" href=${event.ID}>${event.name}</a></h2>
                </div>
                <div class="col-md-4">
                  <br>
                  <button type="button" data-placement="bottom" id="attend${event.ID}" name=${event.ID} class="btn btn-default btn-lg pull-right attend-btn"><#if (attends[id] == true)>attending<#else>attend</#if></button>
                  <button type="button" data-placement="bottom" id="save${event.ID}" name=${event.ID} class="btn btn-default btn-lg pull-right save-btn"><#if (saves[id] == true)>saved<#else>save</#if></button>
                </div>
              </div>
            </div>
            <div class="panel-body">
              <div class="row">
                <div class="col-md-4">
                  <div class="row" id="event-image">
                    <div class="col-md-12">
                      <img src=${event.eventphoto} class="img-responsive" alt="Responsive image">
                    </div>
                  </div>
                </div>
                <div class="col-md-8 description">
                  <#assign description = event.description>
                  <#if (description?length > 881)>
                    <p>${description[0..880]}<a href="#full-description${event.ID}" id="expand-btn" data-toggle="collapse">...</a>
                    <div id="full-description${event.ID}" class="collapse">
                        ${description[881..]}
                    </div></p>
                  <#else>
                    ${event.description}
                  </#if>
                  <div class="row">
                    <br>
                    <div class="col-md-10 col-sm-10 col-xs-9" id="venue-name">${event.venueName}</div>
                    <div class="col-md-2 col-sm-2 col-xs-3 pull-right" id="event-popularity">
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
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.js"></script>
    <script src="js/bootstrap-datetimepicker.js"></script>
    <script src="js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyABBUM2bl_qcqOiw6AWn_AZxob2YQ0g4AQ&libraries=places"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-slider/7.0.2/bootstrap-slider.min.js"></script>
    <script src="js/browse.js"></script>
    <script src="js/topbar.js"></script>
    <script src="js/background.js"></script>
    <#if auth??><span id="auth" class="noshow">${auth}</span></#if>
    <#if username??><span id="username" class="noshow">${username}</span></#if>
  </body>
</html>
