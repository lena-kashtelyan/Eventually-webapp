<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" type="text/css" href="css/topbar.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>
    <link rel="stylesheet" href="https://formden.com/static/cdn/bootstrap-iso.css" />

    <style>
    .bootstrap-iso .formden_header h2, .bootstrap-iso .formden_header p, .bootstrap-iso form{
        font-family: Arial, Helvetica, sans-serif;
        color: black
    }
    .bootstrap-iso form button, .bootstrap-iso form button:hover{color: #ffffff !important;
    }
    .bootstrap-iso .form-control:focus {
      border-color: #bfbfbf;
      -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px rgba(191, 191, 191, 0.6);
      box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px rgba(191, 191, 191, 0.6);
    }
    .asteriskField{
      color: red;
    }
    </style>
  </head>

  <body>

    <#include "topbar.ftl">

    <div class="bootstrap-iso">
     <div class="container">
      <div class="row">
       <div class="col-md-6 col-sm-6 col-xs-12">
        <div class="formden_header">
         <h2>
          Create Event
         </h2>
         <p>
         </p>
        </div>
        <form class="form-horizontal" method="post">
         <div class="form-group ">
          <label class="control-label col-sm-2 requiredField" for="eventName">
           Event Name
           <span class="asteriskField">
            *
           </span>
          </label>
          <div class="col-sm-10">
           <input class="form-control" id="eventName" name="eventName" type="text"/>
          </div>
         </div>
         <div class="form-group ">
          <label class="control-label col-sm-2 requiredField" for="date">
           Date
           <span class="asteriskField">
            *
           </span>
          </label>
          <div class="col-sm-10">
           <input class="form-control" id="date" name="date" placeholder="MM/DD/YYYY" type="text"/>
          </div>
         </div>
         <div class="form-group ">
          <label class="control-label col-sm-2 requiredField" for="time">
           Time
           <span class="asteriskField">
            *
           </span>
          </label>
          <div class="col-sm-10">
           <input class="form-control" id="time" name="time" placeholder="__ : __" type="time"/>
          </div>
         </div>
         <div class="form-group ">
          <label class="control-label col-sm-2 requiredField" for="location">
           Location
           <span class="asteriskField">
            *
           </span>
          </label>
          <div class="col-sm-10">
           <input class="form-control" id="location" name="location" type="text"/>
          </div>
         </div>
         <div class="form-group ">
          <label class="control-label col-sm-2 requiredField" for="category">
           Category
           <span class="asteriskField">
            *
           </span>
          </label>
          <div class="col-sm-10">
           <select class="select form-control" id="category" name="category">
            <option value="Social Gathering">
             Social Gathering
            </option>
            <option value="Performance">
             Performance
            </option>
            <option value="Academic Event">
             Academic Event
            </option>
           </select>
          </div>
         </div>
         <div class="form-group" id="div_facebookAdd">
          <label class="control-label col-sm-2" for="facebookAdd">
           Add to Facebook?
          </label>
          <div class="col-sm-10 ">
           <label class="checkbox-inline">
            <input name="facebookAdd" type="radio" value="Yes"/>
            Yes
           </label>
           <label class="checkbox-inline">
            <input name="facebookAdd" type="radio" value="No"/>
            No
           </label>
          </div>
         </div>
         <div class="form-group">
          <div class="col-sm-10 col-sm-offset-2">
           <button class="btn btn-primary " name="submit" type="submit">
            Create
           </button>
          </div>
         </div>
        </form>
       </div>
      </div>
     </div>
    </div>

    <script src="js/jquery-2.1.1.js"></script>
    <script src="js/facebooklogin.js"></script>
    <script src="js/topbar.js"></script>
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
