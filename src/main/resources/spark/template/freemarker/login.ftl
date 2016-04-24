<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="css/bootstrap-social.css">
    <link rel="stylesheet" type="text/css" href="css/fontawesome.min.css">
    <title>${title}</title>
  </head>

  <body>

    <#include "topbar-min.ftl">

    <div class="container"> <!-- CENTER CONTAINER -->

        <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-3">
          <input type="text" class="form-control" placeholder="username">
        </div>

        <div class="col-md-3">
          <button type="submit" class="btn btn-block btn-default">okay</button>
        </div>
        <div class="col-md-3"></div>
      </div>

      <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-3">
          <input type="password" class="form-control" id="exampleInputPassword1" placeholder="password">
        </div>

        <div class="col-md-3">
          <a class="btn btn-block btn-social btn-facebook">
            <span class="fa fa-facebook"></span> Sign in with Facebook
          </a>
        </div>
        <div class="col-md-3"></div>
      </div>

    </div>

    <div class="container"> <!-- BOTTOM CONTAINER -->

      <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-3">
          <button type="submit" class="btn btn-block btn-default">sign up</button>
        </div>
        <div class="col-md-3">
          <button type="button" class="btn btn-block btn-default">password reminder</button>
        </div>
        <div class="col-md-3"></div>
      </div>

    </div>
    <script src="js/jquery-2.1.1.js"></script>
    <script src="js/facebooklogin.js"></script>
    <script src="js/login.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <#if auth??><span id="auth" class="noshow">${auth}</span></#if>
  </body>
</html>
