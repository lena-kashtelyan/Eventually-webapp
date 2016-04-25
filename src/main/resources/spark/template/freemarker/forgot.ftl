<!DOCTYPE html>
<html>
  <head>
<!--     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.css" /> -->
<!--     <link rel="stylesheet" href="http://libs.cartocdn.com/cartodb.js/v3/3.15/themes/css/cartodb.css" /> -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
    <title>${title}</title>
  </head>

  <body>

    <#include "topbar-min.ftl">

    <div class="container">
      <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">
          <form class="form-inline">
            <div class="form-group">
              <label class="sr-only" for="userEmail">email</label>
              <div class="input-group">
                <input type="text" class="form-control" id="email" placeholder="email">
              </div>
            </div>
            <button id="submit" type="submit" class="btn btn-default">send me my password</button>
          </form>
        </div>
        <div class="col-md-3"></div>
      </div>
    </div>
    <script src="js/jquery-2.1.1.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/forgot.js"></script>
    <#if auth??><span class="noshow">${auth}</span></#if>
  </body>
 </html>

<!-- In case we still want sequrity questions for some reason
 -->
 <!-- <form id="questions" method="GET" action="/response">
  <input type="text" id="question-one" name="question-one" placeholder="security question 1" READONLY> <br>
  <input type="text" id="answer-one" name = "answer-one" placeholder="answer to question 1"> <br>
  <input type="text" id="question-two" name = "question-two" placeholder="security question 2" READONLY> <br>
  <input type="text" id="answer-two" name = "answer-two" placeholder="answer to question 2"> <br>
  <button type="submit" class="submit-btn" id="create-btn" value="find me!" action="/response"></button>
</form> -->
