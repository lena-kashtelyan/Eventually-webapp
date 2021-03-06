<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="css/topbar.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css">
    <link rel="stylesheet" href="https://formden.com/static/cdn/bootstrap-iso.css">
    <link href='https://fonts.googleapis.com/css?family=Poiret+One' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>${title}</title>
  </head>

  <body>
    <#include "background.ftl">
    <#include "topbar.ftl">
    <div class="wrapper">
      <div class="jumbotron vertical-center">
        <div class="container">
          <div class="col-md-3 col-sm-1 col-xs-0"></div>
          <div class="col-md-6 col-sm-10 col-xs-12" id="center-box">
            <form id="event-form" action="">
              <div class="row">
                <div class="col-md-6"> 
                  <div class="form-group">
                    <label class="control-label sr-only requiredField" for="eventName">Event Name
                      <span class="asteriskField">*</span>
                    </label>
                    <input class="form-control form-control-inner input-lg" id="eventName" name="eventName" placeholder="name of the event" type="text"/>
                  </div>
                </div>
                <div class="col-md-6"> 
                  <div class="form-group">
                    <label class="control-label sr-only  requiredField" for="category">category
                      <span class="asteriskField">*</span>
                    </label>
                    <select class="selectpicker input-lg show-tick form-control" id="category" title="type of the event?">
                      <option selected disabled>category</option>
                      <option value="social gathering">nightlife</option>
                      <option value="performance">public lecture</option>
                      <option value="academic">workshop</option>
                      <option value="academic">sports</option>
                      <option value="academic">food fest</option>
                      <option value="academic">movies & art</option>
                      <option value="academic">theater & performance</option>
                      <option value="academic">religious & cultural celebration</option>
                      <option value="academic">sports</option>
                      <option value="academic">other</option>
                    </select>
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="col-md-12">
                  <div class="form-group ">
                    <label class="control-label sr-only requiredField" for="eventDesc"> Event Description
                      <span class="asteriskField">*</span>
                    </label>
                    <textarea class="form-control input-lg" id="eventDesc" name="eventDesc" placeholder="description" type="text"></textarea>
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="col-md-6">
                  <div class="form-group">
                    <label class="control-label sr-only  requiredField" for="date">Date
                      <span class="asteriskField">*</span>
                    </label>
                    <input class="form-control form-control-inner input-lg" id="date" name="date" placeholder="MM/DD/YYYY" type="text"/>
                  </div>
                </div>
                <div class="col-md-6">
                  <div class="form-group">
                    <label class="control-label sr-only requiredField" for="time">Time 
                      <span class="asteriskField">*</span>
                    </label>
                    <input class="form-control form-control-inner input-lg" id="time" name="time" placeholder="time" type="time"/>
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="col-md-12">
                  <div class="form-group">
                    <label class="control-label sr-only  requiredField" for="location">Location
                      <span class="asteriskField">*</span>
                    </label>
                    <input class="form-control form-control-inner input-lg" id="location" name="location" placeholder="location" type="text">
                  </div>
                </div>
              </div>
              <h2></h2>
              <div class="row">
                <div class="col-md-12">
                  <button class="btn btn-block btn-default" id="create-btn">create</button>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>  
    <script type="text/javascript" src="js/jquery-2.1.1.js"></script>
    <script src="js/jquery.validate.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyABBUM2bl_qcqOiw6AWn_AZxob2YQ0g4AQ&libraries=places"></script>
    <script type="text/javascript" src="js/jquery.cycle.all.2.74.js"></script>
    <script type="text/javascript" src="js/topbar.js"></script>
    <script type="text/javascript" src="js/facebooklogin.js"></script>
    <script type="text/javascript" src="js/background.js"></script>
    <script type="text/javascript" src="js/create.js"></script>
    <#if auth??><span id="auth" class="noshow">${auth}</span></#if>
    <#if username??><span id="username" class="noshow">${username}</span></#if>
  </body>
</html>
