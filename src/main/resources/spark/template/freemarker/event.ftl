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
    <div class="container-fluid" id="feed">
      <div class="row">
        <div class="col-md-8" id="event-panel">
          <ul class="list-group">
            <li class="list-group-item">
              <div class="panel panel-default">
                <div class="panel-heading">
                  <div class="row" id="event-name">
                    <h2>
                      <div class="col-md-8">
                        <a id="event-link" href=${event.ID}>${event.name}</a>
                      </div>
                      <div class="col-md-4">
                        <button type="button" data-toggle="tooltip" data-placement="bottom"title="attend event" id="attend-btn" class="btn btn-default pull-right">attend</button>
                        <button type="button" data-toggle="tooltip" data-placement="bottom"title="save event" id="save-btn" class="btn btn-default pull-right">save</button>
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
                        <p>${description[0..880]}<a href="#full-description" data-toggle="collapse">...</a>
                        <div id="full-description" class="collapse">
                            ${description[881..]}
                        </div></p>
                      <#else>
                        ${event.description}
                      </#if>
                      <div class="row">
                        <div class="col-md-10 col-sm-9 col-xs-9" id="venue-name">${event.venueName}</div>
                        <div class="col-md-2 col-sm-3 col-xs-3 pull-right" id="event-popularity">
                        ${event.attendingCount}</div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </li>
          </ul>
        </div>
        <div class="col-md-4" id="stream-top-right">
          <ul class="list-group">
            <li class="list-group-item">
              <div class="panel panel-default">
                <div class="panel-heading">
                  <h2>what do you think of the event?</h2>
                </div>
                <div class="panel-body">
                  <div class="row">
                    <label class="sr-only" for="comment">say something:</label>
                    <textarea class="form-control input-lg" id="comment" name="comment" placeholder="my comment" type="text"></textarea>
                    <button type="button" id="comment-btn" class="btn btn-default">Submit</button>
                  </div>
                  <div class="row">
                    <button type="button" data-toggle="tooltip" data-placement="bottom"title="upload file" id="upload-btn" class="btn btn-default pull-right fileinput-button">upload file</button>
                    <label for="dropzone-area">add photo/video:</label>
                    <div class="table table-striped" class="files" id="previews">
                      <div id="template" class="file-row">
                        <!-- This is used as the file preview template -->
                        <div>
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
                          <button class="btn btn-default start">
                              <i class="glyphicon glyphicon-upload"></i>
                              <span>Start</span>
                          </button>
                          <button data-dz-remove class="btn btn-default cancel">
                              <i class="glyphicon glyphicon-ban-circle"></i>
                              <span>Cancel</span>
                          </button>
                          <button data-dz-remove class="btn btn-default delete">
                            <i class="glyphicon glyphicon-trash"></i>
                            <span>Delete</span>
                          </button>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </li>
          </ul>
        </div>
      </div>
<!--       <div class="row">
        <ul class="list-group">
          <#list stream as entry>
            <li class="list-group-item">
              <div class="panel panel-default">
                <div class="panel-heading">
                  <!--fill-->
                </div>
                <div class="panel-body">
                  
                </div>
              </div>
            </li>
          </#list>
        </ul>
      </div>  -->
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
    <#if eventID??><span id="eventID" class="noshow">${eventID}</span></#if>
  </body>
</html>
