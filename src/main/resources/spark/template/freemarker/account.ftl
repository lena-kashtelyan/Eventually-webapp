<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.css" />
	  <link rel="stylesheet" href="http://libs.cartocdn.com/cartodb.js/v3/3.15/themes/css/cartodb.css" />
    <link rel="stylesheet" type="text/css" href="css/topbar.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
  </head>

  <body>
    
    <#include "topbar.ftl">

    <div class="container">
      <div class="col-md-1"></div>
      <div class="col-md-5">
        <div class="container-fluid">
          <img src="https://lh4.googleusercontent.com/-Dr4TCurbw-Q/AAAAAAAAAAI/AAAAAAAAC9U/t_1ZEww4REQ/photo.jpg" class="img-responsive" alt="Responsive image">
        </div>
        <div class="container">
          <p>John Janotti</p>
        </div>
        <div class="containter">
          <p>@jjannotti</p>
        </div>
      </div>

      <div class="col-md-5">
        <div class="panel panel-default">
          <div class="panel-heading">
            Change password
          </div>
          <div class="panel-body">
            <form method="post">
             <div class="form-group ">
              <label class="control-label requiredField" for="currentPassword">
               Current Password
               <span class="asteriskField">
                *
               </span>
              </label>
              <input class="form-control" id="currentPassword" name="currentPassword" placeholder="password" type="text"/>
             </div>
             <div class="form-group ">
              <label class="control-label requiredField" for="newPassword">
               New Password
               <span class="asteriskField">
                *
               </span>
              </label>
              <input class="form-control" id="newPassword" name="newPassword" placeholder="new password" type="text"/>
             </div>
             <div class="form-group">
              <div>
               <button class="btn btn-primary" name="new-password-submit" type="submit">
                Change
               </button>
              </div>
             </div>
            </form>
          </div>
        </div>
        <div class="panel panel-default">
          <div class="panel-heading">
            Change username
          </div>
          <div class="panel-body">
            <form method="post">
             <div class="form-group">
              <label class="control-label requiredField" for="newUsername">
               New username
               <span class="asteriskField">
                *
               </span>
              </label>
              <input class="form-control" id="newUsername" name="newUsername" placeholder="new username" type="text"/>
             </div>
             <div class="form-group">
              <div>
               <button class="btn btn-primary " name="new-username-submit" type="submit">
                Create
               </button>
              </div>
             </div>
            </form>
          </div>
        </div>
      </div>
      <div class="col-md-1"></div>
    </div>

    <script src="https://code.jquery.com/jquery-2.2.3.min.js" integrity="sha256-a23g1Nt4dtEYOj7bR+vTu7+T8VP13humZFBJNIYoEJo="   crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  </body>
</html>