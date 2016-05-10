<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
    <link href='https://fonts.googleapis.com/css?family=Poiret+One' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" type="text/css" href="css/topbar.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="css/account.css">
  </head>

  <body>
    <#include "background.ftl">
    <#include "topbar.ftl">

    <div class="container">
      <div class="col-md-1"></div>
      <div class="col-md-5">
        <ul class="list-group">
          <li class="list-group-item">
            <img src="${profilePhoto}" class="img-responsive" alt="Responsive image">
          </li>
          <li class="list-group-item">
            <div class="panel panel-default">
              <div class="panel-heading">
                <h2>${username}</h2>
              </div>
              <div class="panel-body">
                <h4>${name}</h4>
              </div>
            </div>
          </li>
        </ul>
      </div>

      <div class="col-md-5">
        <ul class="list-group">
          <li class="list-group-item">
            <div class="panel panel-default">
              <div class="panel-heading">
                <h2>change password</h2>
              </div>
              <div class="panel-body">
                <form method="post">
                  <div class="form-group">
                    <label class="control-label requiredField" for="currentPassword">
                      <h4>current password<span class="asteriskField"> *</span></h4>
                    </label>
                    <input class="form-control input-lg" id="current-password" name="currentPassword">
                    <label class="control-label requiredField" for="currentPassword">
                      <h4>new password<span class="asteriskField"> *</span></h4>
                    </label>
                    <div class="input-group">
                      <input class="form-control input-lg" id="new-password" type="password">
                      <span class="input-group-btn">
                        <button class="btn btn-default" id="submitLogin" type="button">>></button>
                      </span>
                    </div>
                  </div>
                </form>
              </div>
            </div>
          </li>
          <li class="list-group-item">
            <div class="panel panel-default">
              <div class="panel-heading">
                <h2>change username</h2>
              </div>
              <div class="panel-body">
                <form method="post">
                  <div class="form-group">
                    <label class="control-label requiredField" for="currentPassword">
                      <h4>new username<span class="asteriskField"> *</span></h4>
                    </label>
                    <div class="input-group">
                      <input class="form-control input-lg" id="new-password" type="password">
                      <span class="input-group-btn">
                        <button class="btn btn-default" id="submitLogin" type="button">>></button>
                      </span>
                    </div>
                  </div>
                </form>
              </div>
            </div>
          </li>
        </ul>
      </div>
      <div class="col-md-1"></div>
    </div>

    <script src="https://code.jquery.com/jquery-2.2.3.min.js" integrity="sha256-a23g1Nt4dtEYOj7bR+vTu7+T8VP13humZFBJNIYoEJo="   crossorigin="anonymous"></script>
    <script type="text/javascript" src="js/jquery.cycle.all.2.74.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="http://libs.cartocdn.com/cartodb.js/v3/3.15/cartodb.js"></script>
    <script src="js/topbar.js"></script>
    <script src="js/background.js"></script>
    <#if auth??><span id="auth" class="noshow">${auth}</span></#if>
    <#if username??><span id="username" class="noshow">${username}</span></#if>
  </body>
</html>
