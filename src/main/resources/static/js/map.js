// window.onload = function() {
//   var vizjson = 'https://cs32finalproject.cartodb.com/api/v2/viz/ea773bf6-124e-11e6-942b-0e787de82d45/viz.json';
//   cartodb.createVis('map-container', vizjson);
// }

var currentUserLat, currentUserLng, currentUserZoom;
var map_object;
var sublayers = [];

function embedMap(div, afterMapLoadCallback) {
    var tileURL;

    //var simpleOrange = "#" + div + "{marker-fill:#f60;marker-width:10;marker-allow-overlap:true;}";

    var init = function(mapId) {
        tileURL = 'http://{s}.basemaps.cartocdn.com/light_all/{z}/{x}/{y}.png';

        var getStartingLocation = function() {
            var getCurrentLocation = function(callback) {

                var getCurrentLocationSuccess = function(position) {
                    callback(position);
                }

                var getCurrentLocationError = function(error) {
                    switch(error.code) {
                        case error.PERMISSION_DENIED:
                            //alert("User denied the request for Geolocation.");
                            break;
                        case error.POSITION_UNAVAILABLE:
                            //alert("Location information is unavailable.");
                            break;
                        case error.TIMEOUT:
                            //alert("The request to get user location timed out.");
                            break;
                        case error.UNKNOWN_ERROR:
                            //alert("An unknown error occurred.");
                            break;
                    }
                    getDefaultLocation(callback);
                }
                navigator.geolocation.getCurrentPosition(getCurrentLocationSuccess, getCurrentLocationError);
            }

            var getDefaultLocation = function(callback) {
                var position = {
                    html5GeoLocation : false,
                    coords : {
                        latitude : 41.83,
                        longitude : -71.40
                    }
                }
                callback(position);
            }

            var setupOptions = function(position) {
                var setupMap = function(options) {
                    var createSelector = function(layer) {
                      var cartocss = "";
                      var $options = $(".layer_selector").find("button > li");
                      $options.click(function(e) {
                        var $li = $(e.target);
                        var selected = $li.attr('data');
                        if (selected == "color") {
                          $("#color-legend").removeClass("noshow");
                          //$(".floating-menu").css("bottom", "100px");
                        } else {
                          $("#color-legend").addClass("noshow");
                          //$(".floating-menu").css("bottom", "100px");
                        }
                        $options.removeClass('cartocss_selected');
                        $li.addClass('cartocss_selected');
                        cartocss = $('#'+selected).text();
                        layer.setCartoCSS(cartocss);
                      });
                    }

                    map_object = new L.Map(mapId, options);

                    L.tileLayer(tileURL).addTo(map_object);

                    cartodb.createLayer(map_object, {
                        user_name: 'cs32finalproject',
                        type: 'cartodb',
                        refreshTime: 2500,
                        sublayers: [{
                            sql: "SELECT * FROM events",
                            cartocss: $("#simpleOrange").text()
                        }],
                        infowindow: true
                    }).addTo(map_object)
                    .done(function(layer) {
                      // Create layer selector
                      // for (var i = 0; i < layer.getSubLayerCount(); i++) {
                      //   sublayers[i] = layer.getSubLayer(i);
                      //   alert("Congrats, you added sublayer #" + i + "!");
                      // }
                      sublayer = layer.getSubLayer(0);
                      createSelector(sublayer);
                      cartodb.vis.Vis.addInfowindow(map_object, layer, ['cartodb_id', 'name', 'eventid', 'eventphoto', 'description', 'attendingcount'],{
                        infowindowTemplate: $('#infowindow_template').html(),
                        templateType: 'mustache'
                      });
                      afterMapLoadCallback();
                    })
                }

                if (!position.html5GeoLocation) {
                    var options = {
                        center : [
                            position.coords.latitude,
                            position.coords.longitude
                        ],
                        zoom : 14
                    }
                    setupMap(options);
                } else {
                    var options = {
                        center : [
                            position.coords.latitude,
                            position.coords.longitude
                        ],
                        zoom : position.coords.accuracy / 3
                    }
                    setupMap(options);
                }
            }

            if (navigator.geolocation) {
              //getDefaultLocation(setupOptions);
              getCurrentLocation(setupOptions);
            } else {
              getDefaultLocation(setupOptions);
            }
        }

        getStartingLocation();
    }

    init(div);
}

var sublayer;

window.onload = function() {
  embedMap("map-container", reloadLocation);
  $("#display-options > button#search-icon").on("click", function(e) {
    e.preventDefault();
    $("#display-options > button#search-icon").toggleClass("noshow");
    $("#display-options > button#remove-circle-icon").toggleClass("noshow");
    $("#cartocss").toggleClass("noshow");
    $("#cartocss").css("bottom", "75px");
    return false;
  });
  $("#display-options > button#remove-circle-icon").on("click", function(e) {
    e.preventDefault();
    $("#display-options > button#search-icon").toggleClass("noshow");
    $("#display-options > button#remove-circle-icon").toggleClass("noshow");
    $("#cartocss").toggleClass("noshow");
    $("#cartocss").css("bottom", "25px");
    return false;
  });
}

var reloadLocation = function() {
    console.log("this");
    try {
      console.log(map_object.getCenter());
      var position = map_object.getCenter();
      var zoom = map_object.getZoom();
      currentUserLat = position.lat;
      currentUserLng = position.lng;
      currentUserZoom = zoom;
      var params = {
        "latitude" : currentUserLat,
        "longitude" : currentUserLng,
        "zoom" : currentUserZoom
      }
      fetchFbEvents(params);
    } catch (e) {
        console.log("map not loaded");
    }
  }

var fetchFbEvents = function(params) {
  $.post("/update-events-database", params).done(function(res) {
    console.log(res);
    reloadLocation();
    // setInterval(reloadLocation, 25000);
  });
}
