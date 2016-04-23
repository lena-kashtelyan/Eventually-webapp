<!DOCTYPE html>
<html>
  <head>
    <script src="js/jquery-2.1.1.js"></script>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap-social.css">
    <link rel="stylesheet" type="text/css" href="css/fontawesome.min.css">
    <script src="js/facebooklogin.js"></script>
    <script src="js/login.js"></script>
    <script src="js/bootstrap.min.js"></script>  </head>

  <body>
    <#include "topbar.ftl">

    <div class="container">
            <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-3">
          <input type="text" class="form-control" placeholder="username">
          <input type="password" class="form-control" id="exampleInputPassword1" placeholder="password">
        </div>

        <div class="col-md-3">
          <button type="submit" class="btn btn-block btn-default">okay</button>
          <a class="btn btn-block btn-social btn-facebook">
            <span class="fa fa-facebook"></span> Sign in with Facebook
          </a>
        </div>
        <div class="col-md-3"></div>
      </div>
    </div>
  </body>
</html>