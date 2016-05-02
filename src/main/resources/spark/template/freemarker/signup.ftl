<!DOCTYPE html>
<html>
  <head>
<!--     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.css" /> -->
<!--     <link rel="stylesheet" href="http://libs.cartocdn.com/cartodb.js/v3/3.15/themes/css/cartodb.css" /> -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
  </head>

  <body>

    <#include "topbar-min.ftl">

    <div class="container">
      <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">
          <form id="formSignup" class="form-horizontal">
            <div class="form-group">
              <label for="firstName" class="col-sm-2 control-label">first name: </label>
              <div class="col-sm-10">
                <input type="text" class="form-control" id="firstName" placeholder="Joe">
              </div>
            </div>
            <div class="form-group">
              <label for="lastName" class="col-sm-2 control-label">last name: </label>
              <div class="col-sm-10">
                <input type="text" class="form-control" id="lastName" placeholder="Smith">
              </div>
            </div>
            <div class="form-group">
              <label for="username" class="col-sm-2 control-label">username: </label>
              <div class="col-sm-10">
                <input type="text" class="form-control" id="username" placeholder="jsmith123">
              </div>
            </div>
            <div class="form-group">
              <label for="email" class="col-sm-2 control-label">email: </label>
              <div class="col-sm-10">
                <input type="email" class="form-control" id="email" placeholder="partyhopper@inbox.com">
              </div>
            </div>
            <div class="form-group">
              <label for="password" class="col-sm-2 control-label">password:</label>
              <div class="col-sm-10">
                <input type="password" class="form-control" id="password" placeholder="password">
              </div>
            </div>
            <div class="form-group">
              <div class="col-sm-offset-2 col-sm-10">
                <div class="checkbox">
                  <label>
                    <input type="checkbox"> Remember me
                  </label>
                </div>
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
    <script src="js/jquery-2.1.1.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/signup.js"></script>
    <#if auth??><span id="auth" class="noshow">${auth}</span></#if>
    <#if username??><span id="username" class="noshow">${username}</span></#if>
  </body>
 </html>
