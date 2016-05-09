$(document).ready(function () {
	$('.event-link').on('click', function(e) {
	if ($(this).attr('href') != "#full-description") {
		console.log("here");
		e.preventDefault();
		var auth = $("#auth").text();
		var username = $("#username").text();
		var eventID = $(this).attr('href');
		console.log(eventID);
		window.location = "/event?" + $.param({"auth" : auth, "username" : username, "eventID" : eventID});
		}
		else {
			console.log("returning true");
			return true;
		}
	});
});

$(document).ready(function () {
	$('.save-btn').on('click', function(e) {
		var auth = $("#auth").text();
		var username = $("#username").text();
		var eventID = $(this).attr("name");
		var btn = document.getElementById("save"+eventID);
		var params = { "auth" : auth, "username" : username, "eventID" : eventID};
		console.log(btn.innerHTML);
		if (btn.innerHTML == "save") {
			$.post("/save", params);
			btn.innerHTML = "saved";
		} else if (btn.innerHTML == "saved") {
			$.post("/unsave", params);
			btn.innerHTML = "save";
		}
	});
});

$(document).ready(function () {
	$('.attend-btn').on('click', function(e) {
		var auth = $("#auth").text();
		var username = $("#username").text();
		var eventID = $(this).attr("name");
		var btn = document.getElementById("attend"+eventID);
		var params = { "auth" : auth, "username" : username, "eventID" : eventID};
		console.log(btn.innerHTML);
		if (btn.innerHTML == "attend") {
			$.post("/attend", params);
			btn.innerHTML = "attending";
		} else if (btn.innerHTML == "attending") {
			$.post("/unattend", params);
			btn.innerHTML = "attend";
		}
	});
});

function initialize() {
	var defaultBounds = new google.maps.LatLngBounds(  //THINK ABOUT BOUNDS AND CHANGING THEM
	new google.maps.LatLng(-33.8902, 151.1759),
	new google.maps.LatLng(-33.8474, 151.2631));

	var input = document.getElementById('search-location');
	geocoder = new google.maps.Geocoder();

	var searchBox = new google.maps.places.SearchBox(input, {
	  bounds: defaultBounds
	});
}

var radiusSlider;

$(document).ready(function(){ 
	initialize();
	radiusSlider = $("#radius").slider();
});

$(document).ready(function(){ 
	$('#search-refine-btn').on('click', function(e) {
		e.preventDefault();
		var sliderValue = radiusSlider.slider('getValue');
		console.log(sliderValue);
		var location = $("#search-location").val();
		console.log(location); 
		var floorTime = $("#floor-time").val();
		console.log(floorTime);
		var ceilingTime = $("#ceiling-time").val();
		console.log(ceilingTime);
		var params = { "auth" : auth, "username" : username, "location" : location, "radius" : sliderValue, "floorTime" : floorTime, "ceilingTime" : ceilingTime};
		$.post("/search", params);
	});
});

$(document).ready(function(){ 
	$('#search-clear-btn').on('click', function(e) {
		e.preventDefault();
		//COMPLETE THIS!!!
	});
});

