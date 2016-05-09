<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link href='https://fonts.googleapis.com/css?family=Poiret+One' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" type="text/css" href="css/topbar.css">
    <link rel="stylesheet" type="text/css" href="css/create.css">
    <link rel="stylesheet" type="text/css" href="css/signup.css">
    <title>${title}</title>
  </head>

  <body>
    <#include "background.ftl">
    <#include "topbar-min.ftl">

    <div class="jumbotron vertical-center">
      <div class="container" id="main-container">
        <div class="row">
          <div class="col-md-3"></div>
        <div class="col-md-6">
          <form id="formSignup" class="form-horizontal">
            <div class="form-group">
              <label for="firstName" class="col-sm-2 control-label">first name: </label>
              <div class="col-sm-10">
                <input type="text" class="form-control input-lg" id="firstName" name="firstName" placeholder="Obi-Wan">
              </div>
            </div>
            <div class="form-group">
              <label for="lastName" class="col-sm-2 control-label">last name: </label>
              <div class="col-sm-10">
                <input type="text" class="form-control input-lg" id="lastName" name="lastName" placeholder="Kenobi">
              </div>
            </div>
            <div class="form-group">
              <label for="username" class="col-sm-2 control-label">username: </label>
              <div class="col-sm-10">
                <input type="text" class="form-control input-lg" id="username" name="username" placeholder="jedimaster">
              </div>
            </div>
            <div class="form-group">
              <label for="email" class="col-sm-2 control-label">email: </label>
              <div class="col-sm-10">
                <input type="email" class="form-control input-lg" id="email" name="email" placeholder="socialite@may4.com">
              </div>
            </div>
            <div class="form-group">
              <label for="password" class="col-sm-2 control-label">password:</label>
              <div class="col-sm-10">
                <input type="password" class="form-control input-lg" id="password" placeholder="password">
              </div>
            </div>
            <div class="form-group">
              <div class="col-sm-offset-2 col-sm-10">
                <button id="submit" type="submit" class="btn btn-block btn-default">sign me up</button>
              </div>
            </div>
          </form>
        </div>
        <div class="col-md-3"></div>
        </div>
      </div>
    </div>

  <script src="js/jquery-2.1.1.js"></script>
  <script src="js/jquery.validate.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script type="text/javascript" src="js/jquery.cycle.all.2.74.js"></script>
  <script src="js/signup.js"></script>
  <script src="js/topbar-min.js"></script>
  <script src="js/background.js"></script>

  <#if auth??><span id="auth" class="noshow">${auth}</span></#if>
  <#if username??><span id="username" class="noshow">${username}</span></#if>
  </body>
</html>
