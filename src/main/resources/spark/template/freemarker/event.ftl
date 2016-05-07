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
                    <button type="button" data-toggle="tooltip" data-placement="bottom" title="attend event" id="attend-btn" class="btn btn-default pull-right">
                      <!-- <i class="fa fa-check" aria-hidden="true"></i> -->
                      attend
                    </button>
                    <button type="button" data-toggle="tooltip" data-placement="bottom" title="save event" id="save-btn" class="btn btn-default pull-right">
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
                    <p>${description[0..880]}<a href="#full-description" data-toggle="collapse">...</a>
                    <div id="full-description" class="collapse">
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
        <li class="list-group-item">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h2>what do you think of the event?</h2>
            </div>
            <div class="panel-body">
              <div class="col-md-6">
                <label class="sr-only" for="comment">say something:</label>
                <textarea class="form-control input-lg" id="comment" name="comment" placeholder="my comment" type="text"></textarea>
                <button type="button" id="comment-btn" class="btn btn-default">Submit</button>
              </div>
              <div class="col-md-6">
                <label for="dropzone-area">add photo/video:</label>
                <div id="dropzone-area" style="width:100%;height:200px;display:block;margin:2px auto;border:1px solid black;"></div>
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
              <img src="http://res.cloudinary.com/df1bylm3l/image/upload/v1462571854/ur5uxso9g7ww28sg8q7x.jpg" class="img-responsive" alt="Responsive image">
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

    <div id="dropzone-area" style="width:200px;height:200px;display:block;margin:2px auto;border:1px solid red;"></div>

    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/dropzone/4.0.1/dropzone.js"></script>
    <script type="text/javascript">
      var myDropzone = new Dropzone(document.getElementById('dropzone-area'), {
        uploadMultiple: false, 
        acceptedFiles:'.jpg,.png,.jpeg,.gif',
        parallelUploads: 6,
        url: 'https://api.cloudinary.com/v1_1/df1bylm3l/image/upload'
      });
      myDropzone.on('sending', function (file, xhr, formData) {
        formData.append('api_key', 411248546735325);
        formData.append('timestamp', Date.now() / 1000 | 0);
        formData.append('upload_preset', 'vwkniwoh');
      });

      myDropzone.on('success', function (file, response) {
        console.log("here");
        console.log('Success! Cloudinary public ID is', response.public_id);
      });
    </script>
   


    <script src="https://code.jquery.com/jquery-2.2.3.min.js" integrity="sha256-a23g1Nt4dtEYOj7bR+vTu7+T8VP13humZFBJNIYoEJo="   crossorigin="anonymous"></script>
    <script type="text/javascript" src="js/jquery.cycle.all.2.74.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="js/topbar.js"></script>
    <script src="js/background.js"></script>
<!--<script src='jquery.min.js' type='text/javascript'></script>
    <script src='jquery.ui.widget.js' type='text/javascript'></script>
    <script src='jquery.iframe-transport.js' type='text/javascript'></script>
    <script src='jquery.fileupload.js' type='text/javascript'></script>
    <script src='jquery.cloudinary.js' type='text/javascript'></script> -->
    <#if auth??><span id="auth" class="noshow">${auth}</span></#if>
    <#if username??><span id="username" class="noshow">${username}</span></#if>
  </body>
</html>
