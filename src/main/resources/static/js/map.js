// window.onload = function() {
//   var vizjson = 'https://cs32finalproject.cartodb.com/api/v2/viz/ea773bf6-124e-11e6-942b-0e787de82d45/viz.json';
//   cartodb.createVis('map-container', vizjson);
// }

var currentUserLat, currentUserLng, currentUserZoom;
var map_object;

function embedMap(div) {
    var tileURL;

    var cartocssHeatmap = "#" + div + "{marker-fill:#f60;marker-width:10;marker-allow-overlap:true;}";

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
                            alert("User denied the request for Geolocation.");
                            break;
                        case error.POSITION_UNAVAILABLE:
                            alert("Location information is unavailable.");
                            break;
                        case error.TIMEOUT:
                            alert("The request to get user location timed out.");
                            break;
                        case error.UNKNOWN_ERROR:
                            alert("An unknown error occurred.");
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
                    map_object = new L.Map(mapId, options);

                    L.tileLayer(tileURL).addTo(map_object);

                    cartodb.createLayer(map_object, {
                        user_name: 'cs32finalproject',
                        type: 'cartodb',
                        sublayers: [{
                            sql: "SELECT * FROM events",
                            cartocss: cartocssHeatmap
                        }]
                    }).addTo(map_object);
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

window.onload = function() {
  embedMap("map-container");

  var setLocation = function(position) {

  }

  var reloadLocation = function() {
    // console.log("this");
    // try {
    //   console.log(map_object.getCenter());
    //   var position = map_object.getCenter();
    //   var zoom = map_object.getZoom();
    //   currentUserLat = position.lat;
    //   currentUserLng = position.lng;
    //   currentUserZoom = zoom;
    //   var params = {
    //     "latitude" : currentUserLat,
    //     "longitude" : currentUserLng,
    //     "zoom" : currentUserZoom
    //   }
    //   $.post("/update-events-database", params, function() {
    //     //setInterval(reloadLocation, 15000);
    //   });
    // } catch (e) {
    //   //setInterval(reloadLocation, 15000);
    // }
    //navigator.geolocation.getCurrentPosition(setLocation);
  }

  reloadLocation();
}
