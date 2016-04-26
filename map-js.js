function embedMap(div) {
    var map_object, tileURL;

    var cartocssHeatmap = "#" + div + "{marker-fill:#f60;marker-width:10;marker-allow-overlap:true;}";

    var init = function(mapId) {
        console.log("tesT");
        tileURL = 'http://{s}.basemaps.cartocdn.com/light_all/{z}/{x}/{y}.png';

        var getStartingLocation = function() {
          console.log("getStartingLocation");
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
                  console.log("setupMap");
                    map_object = new L.Map(mapId, options);

                    L.tileLayer(tileURL).addTo(map_object);

                    cartodb.createLayer(map_object, {
                        user_name: 'colehansen',
                        type: 'cartodb',
                        sublayers: [{
                            sql: "SELECT * FROM testa",
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
              getDefaultLocation(setupOptions);
                //getCurrentLocation(setupOptions);
            } else {
                getDefaultLocation(setupOptions);
            }
        }

        getStartingLocation();
    }

    init(div);
}

$(document).ready(function() {
  debugger;
  embedMap("map-container");
});
