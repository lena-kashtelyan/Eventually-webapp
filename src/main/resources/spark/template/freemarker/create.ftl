<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" type="text/css" href="css/topbar.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>
    <link rel="stylesheet" href="https://formden.com/static/cdn/bootstrap-iso.css" />
    <link href='https://fonts.googleapis.com/css?family=Poiret+One' rel='stylesheet' type='text/css'>
    <style>
    body, .jumbotron, .vertical-center, .bootstrap-iso {
      background-color: transparent !important;
    }
    .bootstrap-iso, .formden_header h2, .bootstrap-iso .formden_header p, .bootstrap-iso form{
      font-family: 'Poiret One', cursive;
      color: white;
      font-size: 20px;
    }
    .btn {
      background-color: black !important;
      border-color: white !important;
      font-size: 20px !important;
    }
    .form-control {
      text-align: center;
    }
    .form-control-inner::-webkit-input-placeholder { font-size: 110%; text-align: center; color:black; }
    .form-control-inner:-moz-placeholder { font-size: 110%; text-align: center; color:black;! }
    .form-control-inner::-moz-placeholder { font-size: 110%; text-align: center; color:black; }
    .form-control-inner:-ms-input-placeholder { font-size: 110%; text-align: center; color:black; }
    }
    .bootstrap-iso form button, .bootstrap-iso form button:hover{
      color: #ffffff !important;
    }
    .bootstrap-iso .form-control:focus {
      border-color: #bfbfbf;
      -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px rgba(191, 191, 191, 0.6);
      box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px rgba(191, 191, 191, 0.6);
    }
    .asteriskField{
      color: red;
    }
    html { 
      background: url("http://www.beautifulfractals.com/wp-content/uploads/00033-2560x1600.jpg") no-repeat center center fixed; 
      -webkit-background-size: cover;
      -moz-background-size: cover;
      -o-background-size: cover;
      background-size: cover;
    }
    </style>
  </head>

  <body>

    <#include "topbar.ftl">

    <div class="bootstrap-iso">
     <div class="jumbotron vertical-center">
      <div class="col-md-2 col-sm-1 col xs-0"></div>
      <div class="col-md-8 col-sm-10 col xs-12">
      <div class="row">
        <div class="col-md-3 col-sm-1 col-xs-0"></div>
        <div class="col-md-6 col-sm-10 col-xs-12">
          <p></p>
          <form class="form-horizontal" method="post">
           <div class="form-group">
            <label class="control-label sr-only requiredField" for="eventName">
             Event Name
             <span class="asteriskField">
              *
             </span>
            </label>
             <input class="form-control form-control-inner input-lg" id="eventName" name="eventName" placeholder="name of the event" type="text"/>
           </div>
           <div class="form-group ">
            <label class="control-label sr-only  requiredField" for="eventDesc">
             Event Description
             <span class="asteriskField">
              *
             </span>
            </label>
             <textarea class="form-control form-control-inner input-lg" id="eventDesc" name="eventDesc" placeholder="description" type="text"></textarea>
           </div>

           <div class="row">
            <div class="col-md-6 col-sm-6 col-xs-6">
             <div class="form-group">
              <label class="control-label sr-only  requiredField" for="date">
               Date
               <span class="asteriskField">
                *
               </span>
              </label>
               <input class="form-control form-control-inner input-lg" id="date" name="date" placeholder="MM/DD/YYYY" type="text"/>
             </div>
           </div>
          <div class="col-md-6 col-sm-6 col-xs-6">
            <div class="form-group">
              <label class="control-label sr-only  requiredField" for="time">
               Time
               <span class="asteriskField">
                *
               </span>
              </label>
              <input class="form-control form-control-inner input-lg" id="time" name="time" placeholder="time" type="time"/>
            </div>
           </div>
          </div>

           <div class="form-group ">
            <label class="control-label sr-only  requiredField" for="location">
             Location
             <span class="asteriskField">
              *
             </span>
            </label>
             <input class="form-control form-control-inner input-lg" id="location" name="location" placeholder="location"  type="text"/>
           </div>
           <div class="form-group ">
            <label class="control-label sr-only  requiredField" for="category">
             category
             <span class="asteriskField">
              *
             </span>
            </label>
             <select class="select form-control form-control-inner input-lg" id="category" placeholder="type of the event" name="category">
              <option value="Social Gathering">
               social gathering
              </option>
              <option value="Performance">
               performance
              </option>
              <option value="Academic Event">
               academic Event
              </option>
             </select>
           </div>
           <div class="form-group" id="div_facebookAdd">
            <label class="control-label col-sm-8 col-xs-10" for="facebookAdd">
             add to Facebook?
            </label>
            <div class="col-sm-4 col-xs-2">
             <label class="checkbox-inline">
              <input name="facebookAdd" id="facebookYes" type="radio" value="Yes"/>
             </label>
            </div>
           </div>
           <div class="form-group">
            <p></p> 
            <button class="btn btn-block btn-primary" id="create-btn" name="submit" type="submit">
              create
            </button>
           </div>
          </form>
         </div>
        </div>
      </div>
     </div>
    </div>

    <script src="js/jquery-2.1.1.js"></script>
    <script src="js/facebooklogin.js"></script>
    <script src="js/topbar.js"></script>
    <script src="js/create.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
    <#if auth??><span id="auth" class="noshow">${auth}</span></#if>
    <script>
    $(document).ready(function(){
        var date_input=$('input[name="date"]');
        var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
        date_input.datepicker({
            format: 'mm/dd/yyyy',
            container: container,
            todayHighlight: true,
            autoclose: true,
        })
    })
    </script>
  </body>
</html>
