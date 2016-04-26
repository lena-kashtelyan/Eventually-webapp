  <nav class="navbar navbar-default">
    <div class="container-fluid">
      <div class="navbar-header">
        <!--LOGO-->
        <a href="/">hopper</a>
      </div>

      <ul class="nav navbar-nav navbar-right">
        <li>
          <button class="btn btn-primary btn-sm dropdown-toggle" type="button" id="flip-button" data-toggle="dropdown"><span class="glyphicon glyphicon-refresh"></span></button>
        </li>
        <li>
          <input type="text" placeholder="event, time, place...">
        </li>
        <li>
          <div class="dropdown">
            <button class="btn btn-primary btn-sm dropdown-toggle" type="button" id="menu-button" data-toggle="dropdown"><span class="glyphicon glyphicon-menu-hamburger"></span></button>
            <ul class="dropdown-menu">
              <li><a href="#" id="browse">events</a></li>
              <li><a href="#">now</a></li>
              <li><a href="#" id="create">create</a></li>
              <li><a href="#">account</a></li>
            </ul>
          </div>
        </li>
      </ul>
    </div>
  </nav>

  <#if auth??><span id="auth" class="noshow">${auth}</span></#if>

