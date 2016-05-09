<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link href='https://fonts.googleapis.com/css?family=Poiret+One' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" type="text/css" href="css/bootstrap-social.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="css/topbar.css">
    <link rel="stylesheet" type="text/css" href="css/login.css">
    <title>${title}</title>
  </head>

  <body>
    <#include "background.ftl">
    <#include "topbar-min.ftl">

    <div class="wrapper">
      <div class="jumbotron vertical-center">
        <div id="center-box">
          <div class="container">
            <div class="row">
              <div class="col-md-1 col-sm-1 col-xs-1"></div>
              <div class="col-md-5 col-sm-5">
                <p>explore events?</p>
              </div>
              <div class="form-group">
                <div class="col-md-5 col-sm-5 col-xs-12">
                  <div class="input-group input-group-lg" id="usernameOrEmailContainer">
                    <input id="usernameOrEmail" type="text" class="form-control" placeholder="username">
                  </div>
                  <div class="input-group input-group-lg">
                    <input id="password" type="password" class="form-control" id="exampleInputPassword1" placeholder="password">
                    <span class="input-group-btn">
                      <button class="btn btn-default" id="submitLogin" type="button"> >> </button>
                    </span>
                  </div>
                </div>
                <div class="col-md-1 col-sm-2 col-xs-1"></div>
              </div>
            </div>
          </div>
        <h2></h2>
        <div class="container">
          <div class="row">
            <div class="col-md-1 col-sm-1 col-xs-1"></div>
            <div class="col-md-5 col-sm-5">
              <p>connect with facebook?</p>
            </div>
            <div class="col-md-5 col-sm-5 col-xs-12">
             <a id="facebooklogin" class="btn btn-block btn-lg btn-social btn-facebook">
                <i class="fa fa-facebook-official" aria-hidden="true"></i> sign in with Facebook
             </a>
            </div>
            <div class="col-md-1 col-sm-2 col-xs-1"></div>
          </div>
        </div>
        <h2></h2>
        <div class="container">
          <div class="row">
            <div class="col-md-1 col-sm-1 col-xs-1"></div>
            <div class="col-md-5 col-sm-5">
              <p>first time here?</p>
            </div>
            <div class="col-md-5 col-sm-5 col-xs-12">
              <button id="signup" type="submit" class="btn btn-block btn-default">sign up</button>
            </div>
            <div class="col-md-1 col-sm-2 col-xs-1"></div>
          </div>
        </div>
        <h2></h2>
        <div class="container">
          <div class="row">
            <div class="col-md-1 col-sm-1 col-xs-1"></div>
            <div class="col-md-5 col-sm-5">
              <p>forgot password?</p>
            </div>
            <div class="col-md-5 col-sm-5 col-xs-12">
              <button id="forgot" type="button" class="btn btn-block btn-default">password reminder</button>
            </div>
            <div class="col-md-1 col-sm-2 col-xs-1"></div>
          </div>
        </div>
      </div>
    </div>
            </div>

  <script src="js/jquery-2.1.1.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script type="text/javascript" src="js/jquery.cycle.all.2.74.js"></script>
  <script src="js/login.js"></script>
  <script src="js/topbar-min.js"></script>
  <script src="js/background.js"></script>

  <#if auth??><span id="auth" class="noshow">${auth}</span></#if>
  <#if username??><span id="username" class="noshow">${username}</span></#if>
  </body>
</html>
