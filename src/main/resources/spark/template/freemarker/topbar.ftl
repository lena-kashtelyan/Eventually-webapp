  <nav class="navbar navbar-inverse">
    <div class="container-fluid">
      <div class="navbar-header">
        <a class="navbar-brand" href="#">EVENTUALLY</a>
        <button type="button" class="btn-lg navbar-toggle collapsed navbar-right" data-toggle="collapse" data-target="#navbar-inner" aria-haspopup="true" aria-expanded="false" id="collapse-btn">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <form class="form collapsed pull-right" role="search" id="searchbar">
          <div class="form-group">
            <input type="text" class="form-control" placeholder="event, time, place...">
          </div>
        </form>
      </div>
      <div class="collapse navbar-collapse navbar-right" id="navbar-inner">
        <ul class="nav navbar-nav navbar-right">
          <li><a href="#" id="now">now</a></li>
          <li><a href="#" id="browse">events</a></li>
          <li><a href="#" id="create">create</a></li>
          <li><a href="#" id="account">account</a></li>
        </ul>
      </div>
    </div>
  </nav>
  <#if auth??><span id="auth" class="noshow">${auth}</span></#if>
  <style> 
  * { 
    border-radius: 0 !important; 
    font-family: 'Poiret One', cursive;
  } 
  #searchbar { 
    margin: 8px 16px -15px 0px; 
  }
  .navbar-brand {
    font-family: 'Poiret One', cursive;
    font-size: 28px;
  }
  .navbar-collapse {
    font-family: 'Poiret One', cursive;
    font-size:20px;
  }
  </style>


