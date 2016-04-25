<!DOCTYPE html>
<html>
  <head>
    <style>
    </style>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
    <title>${title}</title>
  </head>

  <body> 
    <#include "topbar.ftl">
    <div class="container">
      <#list events>
        <ul class="list-group">
          <#items as event>
            <li class="list-group-item">
              <div class="panel panel-default">
                <div class="panel-body">
                  <div class="col-md-4">
                    <img src={$event.imgURL} class="img-responsive" alt="Responsive image">
                  </div>
                  <div class="col-md-8">
                    <div class="row">
                      <p>{$event.title} </p>
                    </div>
                    <div class="row">
                      <p>{$event.desctiption}</p>
                    </div>
                    <div class="row">
                      <p>{$event.popularity}</p>
                    </div>
                    <div class="row">
                      <p>{$event.location}</p>
                    </div>
                  </div>
                </div>
              </div>
            </li>
          </#items>
        </ul>
      </#list>
    </div>
    <script src="https://code.jquery.com/jquery-2.2.3.min.js" integrity="sha256-a23g1Nt4dtEYOj7bR+vTu7+T8VP13humZFBJNIYoEJo="   crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <#if auth??><span class="noshow">${auth}</span></#if>
  </body>
</html>


<!--EVENT STUB START (just in case)
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
  </li>    -->

      