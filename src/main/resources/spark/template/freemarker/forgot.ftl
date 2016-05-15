<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
    <link href='https://fonts.googleapis.com/css?family=Poiret+One' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Josefin+Sans:300,700' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>${title}</title>
  </head>

  <body>
    <#include "topbar-min.ftl">
    <#include "background.ftl">
    <div class="wrapper">
      <div class="jumbotron vertical-center">
        <div class="container">
          <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6" id="center-box">
              <div class="row">
                <div class="col-md-1 col-sm-1 col-xs-1"></div>
                <div class="col-md-5 col-sm-5 col-xs-5">
                  <div class="input-group">
                    <input type="text" class="form-control input-lg input-block" id="email" placeholder="email">
                  </div>
                </div>
                <div class="col-md-5 col-sm-5 col-xs-5">
                  <button id="submit" type="button" class="btn btn-default btn-lg">send me my password</button>
                </div>
              </div>
            </div>
            <div class="col-md-3"></div>
          </div>
        </div>
      </div>
    </div>
    <script src="https://code.jquery.com/jquery-2.2.3.min.js" integrity="sha256-a23g1Nt4dtEYOj7bR+vTu7+T8VP13humZFBJNIYoEJo="   crossorigin="anonymous"></script>
    <script type="text/javascript" src="js/jquery.cycle.all.2.74.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.js"></script>
    <script src="js/forgot.js"></script>
    <script src="js/topbar-min.js"></script>
    <script src="js/background.js"></script>
    <#if auth??><span id="auth" class="noshow">${auth}</span></#if>
    <#if username??><span id="username" class="noshow">${username}</span></#if>
  </body>
 </html>
