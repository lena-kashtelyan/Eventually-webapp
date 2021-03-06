<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
    <link href='https://fonts.googleapis.com/css?family=Poiret+One' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Josefin+Sans:300,700' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="css/event.css">
    <title>${title}</title>
    <style>
    </style>
  </head>

  <body>
    <#include "background.ftl">
    <#include "topbar.ftl">
    <br>
    <div class="container-fluid" id="feed">
      <div class="row">
        <div class="col-md-6" id="event-panel">
          <ul class="list-group">
            <li class="list-group-item">
              <div class="panel panel-default">
                <div class="panel-heading">
                  <div class="row" id="event-name">
                    <div class="col-md-8 col-sm-9 col-xs-9" style="color:white;">
                      <h3><a id="event-link" href=${event.ID}>${event.name}</a></h3>
                    </div>
                    <div class="col-md-4 col-sm-3 col-xs-3">
                      <br>
                      <button type="button" data-placement="bottom" id="attend${event.ID}" name=${event.ID} class="btn btn-default btn-lg pull-right attend-btn"><#if (attending == true)>attending<#else>attend</#if></button>
                      <button type="button" data-placement="bottom" id="save${event.ID}" name=${event.ID} class="btn btn-default btn-lg pull-right save-btn"><#if (saved == true)>saved<#else>save</#if></button>
                    </div>
                  </div>
                </div>
                <div class="panel-body">
                  <div class="row" id="event-image">
                    <div class="col-md-12">
                      <img src=${event.eventphoto} class="img-responsive" alt="Responsive image">
                    </div>
                  </div>
                  <h4></h4>
                  <div class="row">
                    <div class="col-md-12">
                      <div class="description">
                        <#assign description = event.description>
                        <#if (description?length > 881)>
                          <p>${description[0..880]}<a href="#full-description" data-toggle="collapse">...</a>
                          <div id="full-description" class="collapse">
                              ${description[881..]}
                          </div></p>
                        <#else>
                          <p>${event.description}</p>
                        </#if>
                      </div>
                    </div>
                  </div>
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
                      <div class="col-md-4 col-sm-4 col-xs-4 pull-right" id="event-popularity"><br>~ ${event.attendingCount} going</div>
                  </div>
                </div>
              </div>
            </li>
          </ul>
        </div>
        <div class="col-md-6" id="stream-top-right">
          <ul class="list-group">
            <li class="list-group-item">
              <div class="panel panel-default">
                <div class="panel-heading">
                  <h2>event feed</h2>
                </div>
                <div class="panel-body">
                  <div class="row">
                    <div class="col-md-9 col-sm-9 col-xs-8">
                      <label class="sr-only" for="comment">say something:</label>
                      <textarea class="form-control input-lg" id="comment" name="comment" placeholder="say something?" type="text"></textarea>
                      <span class="input-group-addon btn btn-default" id="comment-btn">post</span>
                    </div>
                    <div class="col-md-3 col-sm-3 col-xs-4">
                      <button type="button" data-toggle="tooltip" data-placement="bottom"title="upload file" id="upload-btn" class="btn btn-default pull-right btn-block fileinput-button">upload file</button>
                    </div>
                    <label class="sr-only" for="dropzone-area">add photo/video:</label>
                    <div class="col-md-3 col-sm-3 col-xs-4">
                      <div class="table table-striped" class="files" id="previews">
                        <div id="template" class="file-row">
                          <div>
                            <br>
                              <span class="preview"><img data-dz-thumbnail /></span>
                          </div>
                          <div>
                              <p class="name" data-dz-name></p>
                              <strong class="error text-danger" data-dz-errormessage></strong>
                          </div>
                          <div>
                              <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0">
                                <div class="progress-bar progress-bar-success" style="width:0%;" data-dz-uploadprogress></div>
                              </div>
                          </div>
                          <div>
                            <button class="btn btn-default btn-block start">
                                <i class="glyphicon glyphicon-upload"></i>
                                <span>upload</span>
                            </button>
                            <button data-dz-remove class="btn btn-default btn-block cancel">
                                <i class="glyphicon glyphicon-ban-circle"></i>
                                <span>cancel</span>
                            </button>
                            <button data-dz-remove class="btn btn-default btn-block delete">
                              <i class="glyphicon glyphicon-trash"></i>
                              <span>delete</span>
                            </button>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  <br>
                  <div class="col-md-12 col-sm-12 col-xs-12">
                    <#list stream as entry>
                      <div class="row comment">
                        <#if (entry.type == "comment")>
                          <h4><b>${entry.getUserName()}</b> : ${entry.path}</h4>
                        </#if>
                      </div>
                    </#list>
                  </div>
                </div>
              </div>
            </div>
          </li>
        </ul>
      </div>

      </div>
      <div id="wrapper">
        <div id="columns">
          <#list stream as entry>
            <!-- <div class="col-md-4 col-sm-3"></div> -->
            <#if (entry.type == "image")>
            <div class="pin">
              <div class="panel panel-default storystream-panel">
              <div class="panel-heading">
              <h4><b>${entry.getUserName()}</b> posted:</h4>
              </div>
              <div class="panel-body">
              <img src=${entry.path} class="img-responsive" alt="Responsive image">
              </div>
              </div>
            </div>
            </#if>
            <!-- <div class="col-md-4 col-sm-3"></div> -->
          </#list>
        </div>
      </div>


    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/dropzone/4.0.1/dropzone.js"></script>
    <script src="https://code.jquery.com/jquery-2.2.3.min.js" integrity="sha256-a23g1Nt4dtEYOj7bR+vTu7+T8VP13humZFBJNIYoEJo="   crossorigin="anonymous"></script>
    <script type="text/javascript" src="js/jquery.cycle.all.2.74.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="js/topbar.js"></script>
    <script src="js/background.js"></script>
    <script src="js/event.js"></script>
    <#if auth??><span id="auth" class="noshow">${auth}</span></#if>
    <#if username??><span id="username" class="noshow">${username}</span></#if>
    <#if eventID??><span id="eventID" class="noshow">${eventID}</span></#if>
  </body>
</html>
