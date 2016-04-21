<!DOCTYPE html>
<html>
  <head>
    <style>
    </style>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.css" />
  <link rel="stylesheet" href="http://libs.cartocdn.com/cartodb.js/v3/3.15/themes/css/cartodb.css" />
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/topbar.css">
    <link rel="stylesheet" type="text/css" href="css/forgot.css">
    <link rel="stylesheet" type="text/css" href="css/login.css">
    <link rel="stylesheet" type="text/css" href="css/signup.css">
    <script src="js/bootstrap.min.js">
    </script>
  </head>

  <body id="body" class="noselect"> <!--onload="loadCanvas()"--> 

      <#include "topbar.ftl">
      
      <div id="user-info">
        <form id="username-form">       <!--WHERE DOES THIS LEAD?-->
          <input type="text" id="username" name="username" placeholder="username"> <br>
        </form>
      </div> 
      <div id="security-questions">
        <form id="questions" method="GET" action="/response">
          <input type="text" id="question-one" name="question-one" placeholder="security question 1" READONLY> <br>
          <input type="text" id="answer-one" name = "answer-one" placeholder="answer to question 1"> <br>
          <input type="text" id="question-two" name = "question-two" placeholder="security question 2" READONLY> <br>
          <input type="text" id="answer-two" name = "answer-two" placeholder="answer to question 2"> <br>
          <button type="submit" class="submit-btn" id="create-btn" value="find me!" action="/response"></button>
        </form>
      </div>
  </body>
 </html> 