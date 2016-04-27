<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
    <link href='https://fonts.googleapis.com/css?family=Poiret+One' rel='stylesheet' type='text/css'>
    <title>${title}</title>
  </head>

  <body> 
    <#include "topbar.ftl">
    <div class="container">
      <ul class="list-group">
      <#list events as event>
        <li class="list-group-item">
          <div class="panel panel-default">
            <div class="panel-body">
              <div class="col-md-4">
                <!--<img src={$event.imgURL} class="img-responsive" alt="Responsive image">-->
              </div>
              <div class="col-md-8">
                <div class="row">
                  <p>${event.name} </p>
                </div>
                <div class="row">
                  <p>${event.description}</p>
                </div>
                <div class="row">
                  <p>${event.attendingCount}</p>
                </div>
                <div class="row">
                  <p>${event.venueName}</p>
                </div>
              </div>
            </div>
          </div>
        </li>
      </#list>
      </ul>
    </div>
    <script src="https://code.jquery.com/jquery-2.2.3.min.js" integrity="sha256-a23g1Nt4dtEYOj7bR+vTu7+T8VP13humZFBJNIYoEJo="   crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="js/topbar.js"></script>
    <#if auth??><span id="auth" class="noshow">${auth}</span></#if>
  </body>
</html>

      