<!DOCTYPE html>
<html>
  <head>
    <style>
    </style>
    <script src="js/jquery-2.1.1.js"></script>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/topbar.css">
    <link rel="stylesheet" type="text/css" href="css/login.css">
    <link rel="stylesheet" type="text/css" href="css/signup.css">
    <script src="js/bootstrap.min.js">
    </script>
  </head>

  <body id="body" class="noselect"> <!--onload="loadCanvas()"--> 

      <#include "topbar.ftl">

      <div id="user-info">
        <form action="">       <!--WHERE DOES THIS LEAD?-->
          <input type="text" id="username" placeholder="username"> <br>
          <input type="text" id="name" placeholder="name"> <br>
          <input type="password" id="password" placeholder="password"> <br>
          <input type="text" id="question-one" placeholder="security question 1"> <br>
          <input type="text" id="answer-one" placeholder="answer to question 1"> <br>
          <input type="text" id="question-two" placeholder="security question 2"> <br>
          <input type="text" id="answer-two" placeholder="answer to question 2"> <br>
          <input type="submit" class="submit-btn" id="create-btn" value="create account">
        </form>
      </div>

  </body>
</html>