$(document).ready(function() {
	$("#create-btn").on('click', function(e) {
		console.log("in create click response in create.js");
		e.preventDefault();
			var auth = $("#auth").text();
			var name = $("#eventName").val();
			var description = $("#eventDesc").val();
			var date = $("#date").val();
			console.log(date);
			var time = $("#time").val();
			console.log(time);
			var location = $("#location").val();
			var category = $("category").val();
			var facebookAdd = $("#facebookYes").val();
			console.log("auth: " + auth);
			var params = {"auth" : auth, "username" : auth, "eventName" : name, "date" : date, "description" : description, "time" : time, "location" : location, "category" : category, "facebookAdd" : facebookAdd};

			$.post("/create", params, function(responseJSON){
			});
	});
});

$(document).ready(function(){
    var date_input=$('input[name="date"]');
    var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
    date_input.datepicker({
        format: 'mm/dd/yyyy',
        container: container,
        todayHighlight: true,
        autoclose: true,
    });
});

function initialize() {
	var defaultBounds = new google.maps.LatLngBounds(  //THINK ABOUT BOUNDS AND CHANGING THEM
	new google.maps.LatLng(-33.8902, 151.1759),
	new google.maps.LatLng(-33.8474, 151.2631));

	var input = document.getElementById('location');

	var searchBox = new google.maps.places.SearchBox(input, {
	  bounds: defaultBounds
	});

	searchBox.addListener('places_changed', function() {
	  var places = searchBox.getPlaces();

	  if (places.length == 0) {
	    return;
	  }

	  // // Clear out the old markers.
	  // markers.forEach(function(marker) {
	  //   marker.setMap(null);
	  // });
	  // markers = [];

	  // For each place, get the icon, name and location.
	  var bounds = new google.maps.LatLngBounds();
	  places.forEach(function(place) {
	    var icon = {
	      url: place.icon,
	      size: new google.maps.Size(71, 71),
	      origin: new google.maps.Point(0, 0),
	      anchor: new google.maps.Point(17, 34),
	      scaledSize: new google.maps.Size(25, 25)
	    };

	    // // Create a marker for each place.
	    // markers.push(new google.maps.Marker({
	    //   map: map,
	    //   icon: icon,
	    //   title: place.name,
	    //   position: place.geometry.location
	    // }));

	    if (place.geometry.viewport) {
	      // Only geocodes have viewport.
	      bounds.union(place.geometry.viewport);
	    } else {
	      bounds.extend(place.geometry.location);
	    }
	  });
	  // map.fitBounds(bounds);
	});
}
initialize();




