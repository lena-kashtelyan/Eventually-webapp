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
          <form class="form-horizontal">
            <div class="form-group">
              <label for="inputEmail3" class="col-sm-2 control-label">email: </label>
              <div class="col-sm-10">
                <input type="email" class="form-control" id="inputEmail3" placeholder="partyhopper@inbox.com">
              </div>
            </div>
            <div class="form-group">
              <label for="inputPassword3" class="col-sm-2 control-label">password:</label>
              <div class="col-sm-10">
                <input type="password" class="form-control" id="inputPassword3" placeholder="password">
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
                <button type="submit" class="btn btn-block btn-default">sign me up</button>
              </div>
            </div>
          </form>
        </div>
        <div class="col-md-3"></div>
      </div>
    </div>
    <script src="js/jquery-2.1.1.js"></script>
    <script src="js/bootstrap.min.js"></script>
  </body>
 </html> 

