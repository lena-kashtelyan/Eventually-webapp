<!DOCTYPE html>
<html>
  <head>
    <style>
    </style>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
  </head>

  <body id="body" class="noselect"> <!--onload="loadCanvas()"--> 

  <#include "topbar.ftl">

    <div class="container">
      <ul class="list-group">

        <!--EVENT STUB START (replace w/variables) -->
        <li class="list-group-item">
          <div class="panel panel-default">
            <div class="panel-body">
              <div class="col-md-4">
                <img src="https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcSmtYA3Ph_Ah0uwmO42azt0ZAz-Ey2FtECbCzGvwml04HOuPiyYEmCbDe0w" class="img-responsive" alt="Responsive image">
              </div>
              <div class="col-md-8">
                <div class="row">
                  <p> event title </p>
                </div>
                <div class="row">
                  <p> event description.... </p>
                </div>
                <div class="row">
                  <p> EVENT POPULARITY STATUS </p>
                </div>
                <div class="row">
                  <p> location </p>
                </div>
              </div>
            </div>
          </div>
        </li>
        <!--EVENT STUB END-->

        <!--EVENT STUB START-->
        <li class="list-group-item">
          <div class="panel panel-default">
            <div class="panel-body">
              <div class="col-md-4">
                <img src="https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcSmtYA3Ph_Ah0uwmO42azt0ZAz-Ey2FtECbCzGvwml04HOuPiyYEmCbDe0w" class="img-responsive" alt="Responsive image">
              </div>
              <div class="col-md-8">
                <div class="row">
                  <p> event title </p>
                </div>
                <div class="row">
                  <p> event description.... </p>
                </div>
                <div class="row">
                  <p> EVENT POPULARITY STATUS </p>
                </div>
                <div class="row">
                  <p> location </p>
                </div>
              </div>
            </div>
          </div>
        </li>
        <!--EVENT STUB END-->

        <!--EVENT STUB START-->
        <li class="list-group-item">
          <div class="panel panel-default">
            <div class="panel-body">
              <div class="col-md-4">
                <img src="https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcSmtYA3Ph_Ah0uwmO42azt0ZAz-Ey2FtECbCzGvwml04HOuPiyYEmCbDe0w" class="img-responsive" alt="Responsive image">
              </div>
              <div class="col-md-8">
                <div class="row">
                  <p> event title </p>
                </div>
                <div class="row">
                  <p> event description.... </p>
                </div>
                <div class="row">
                  <p> EVENT POPULARITY STATUS </p>
                </div>
                <div class="row">
                  <p> location </p>
                </div>
              </div>
            </div>
          </div>
        </li>
        <!--EVENT STUB END-->

        <!--EVENT STUB START-->
        <li class="list-group-item">
          <div class="panel panel-default">
            <div class="panel-body">
              <div class="col-md-4">
                <img src="https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcSmtYA3Ph_Ah0uwmO42azt0ZAz-Ey2FtECbCzGvwml04HOuPiyYEmCbDe0w" class="img-responsive" alt="Responsive image">
              </div>
              <div class="col-md-8">
                <div class="row">
                  <p> event title </p>
                </div>
                <div class="row">
                  <p> event description.... </p>
                </div>
                <div class="row">
                  <p> EVENT POPULARITY STATUS </p>
                </div>
                <div class="row">
                  <p> location </p>
                </div>
              </div>
            </div>
          </div>
        </li>
        <!--EVENT STUB END-->
      </ul>
    </div>
    <script src="https://code.jquery.com/jquery-2.2.3.min.js" integrity="sha256-a23g1Nt4dtEYOj7bR+vTu7+T8VP13humZFBJNIYoEJo="   crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <#if auth??><span class="noshow">${auth}</span></#if>
  </body>
</html>