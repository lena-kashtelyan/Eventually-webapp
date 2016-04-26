  <nav class="navbar navbar-default">
    <div class="container-fluid">
      <div class="navbar-header">
        <!--LOGO-->
        <a href="/">hopper</a>
      </div>

      <ul class="nav navbar-nav navbar-right">
        <li>
          <div class="dropdown">
            <button class="btn btn-primary navbar-btn btn-sm dropdown-toggle" type="button" id="menu-button" data-toggle="dropdown"><span class="glyphicon glyphicon-menu-hamburger"></span></button>
            <ul class="dropdown-menu">
              <li><a href="#" id="browse">events</a></li>
              <li><a href="#" id="now">now</a></li>
              <li><a href="#" id="create">create</a></li>
              <li><a href="#" id="account">account</a></li>
            </ul>
          </div>
        </li>
        <li>
          <button class="btn btn-primary navbar-btn btn-sm dropdown-toggle" type="button" id="flip-button" data-toggle="dropdown"><span class="glyphicon glyphicon-refresh"></span></button>
        </li>
        <li>
          <form class="navbar-form" role="search">
            <div class="form-group">
              <input type="text" class="form-control" placeholder="event, time, place...">
            </div>
          </form>
        </li>
      </ul>
    </div>
  </nav>

  <#if auth??><span id="auth" class="noshow">${auth}</span></#if>

