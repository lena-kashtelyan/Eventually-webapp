<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" type="text/css" href="css/topbar.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css">
    <link rel="stylesheet" href="https://formden.com/static/cdn/bootstrap-iso.css">
    <link href='https://fonts.googleapis.com/css?family=Poiret+One' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" type="text/css" href="css/topbar.css">
    <link rel="stylesheet" type="text/css" href="css/create.css">
    <title>${title}</title>
  </head>

  <body>
    <#include "background.ftl">
    <#include "topbar.ftl">

    <div class="jumbotron vertical-center">
      <div class="container">
        <div class="col-md-3 col-sm-1 col-xs-0"></div>
        <div class="col-md-6 col-sm-10 col-xs-12">
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
                <!-- <select class="select form-control form-control-inner input-lg" id="category" placeholder="type of the event" name="category">
                  <option value="social gathering">social gathering</option>
                  <option value="performance">performance</option>
                  <option value="academic">academic event</option>
                </select> -->

                <select class="selectpicker input-lg show-tick form-control" title="type of the event?">
                  <option value="social gathering">social gathering</option>
                  <option value="performance">performance</option>
                  <option value="academic">academic event</option>
                </select>


              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-md-12">
              <div class="form-group ">
                <label class="control-label sr-only  requiredField" for="eventDesc"> Event Description
                  <span class="asteriskField">*</span>
                </label>
                <textarea class="form-control form-control-inner input-lg" id="eventDesc" name="eventDesc" placeholder="description" type="text"></textarea>
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
                <input class="form-control form-control-inner input-lg" id="location" name="location" placeholder="location"  type="text"/>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-md-12">
              <div class="input-group input-group-lg">
                <span class="input-group-addon">
                  <input type="checkbox" aria-label="add to facebook">
                </span>
                <input type="text" class="form-control form-control-black" aria-label="add to facebook" value="add to facebook       " readonly>
              </div>
            </div>
          </div>
          <h2></h2>
          <div class="row">
            <div class="col-md-12">
              <button class="btn btn-block btn-default" id="create-btn" name="submit" type="submit">create</button>
            </div>
          </div>
        </div>
      </div>
    </div>

    <script src="js/jquery-2.1.1.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
    <script type="text/javascript" src="js/jquery.cycle.all.2.74.js"></script>
    <script src="js/facebooklogin.js"></script>
    <script src="js/topbar.js"></script>
    <script src="js/facebooklogin.js"></script>
    <script src="js/background.js"></script>
    <script src="js/create.js"></script>
    <#if auth??><span id="auth" class="noshow">${auth}</span></#if>
  </body>
</html>
