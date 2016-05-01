  <style> 
    * { 
      border-radius: 0 !important; 
    } 
    .form-control:focus {
      border-color: #FF0000;
      box-shadow: inset 0 1px 1px #FBB917, 0 0 8px #E8A317;
    }
    .form-control-topbar::-webkit-input-placeholder { font-family: 'Poiret One', cursive; color: gray; text-align: left; }
    .form-control-topbar:-moz-placeholder { font-family: 'Poiret One', cursive; color: gray; text-align: left; }
    .form-control-topbar::-moz-placeholder { font-family: 'Poiret One', cursive; color: gray; text-align: left; }
    .form-control-topbar:-ms-input-placeholder { font-family: 'Poiret One', cursive; color: gray; text-align: left; }
    #searchbar { 
      margin: 12px 16px -15px 0px; 
    }
    .form-control-topbar {
      padding-left: 8px;
    }
    .navbar-brand {
      font-family: 'Poiret One', cursive;
      font-size: 36px;
      margin-bottom: 4px;
      margin-top: -4px;
    }
    .navbar-collapse {
      font-family: 'Poiret One', cursive;
      font-size:20px;
    }
  </style>

  <nav class="navbar navbar-inverse">
    <div class="container-fluid">
      <div class="navbar-header">
        <a class="navbar-brand" id="brand" href="#"><span style="color:#F88017">event</span>ually</a>
        <button type="button" class="btn-lg navbar-toggle collapsed navbar-right" data-toggle="collapse" data-target="#navbar-inner" aria-haspopup="true" aria-expanded="false" id="collapse-btn">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <form class="form collapsed pull-right" role="search" id="searchbar">
          <div class="form-group">
            <input type="text" class="form-control-topbar" placeholder="event, time, place...">
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



