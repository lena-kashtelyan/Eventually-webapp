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

$(document).ready(function() {
	var elems = $(".description");
	elems.each(function(index, elem) {
		var text = $(elem).html();
		var newtext = text.replace(/\\n/g, "<br />");
		$(elem).html(newtext);
	});
});

function initialize() {
	var defaultBounds = new google.maps.LatLngBounds(  //THINK ABOUT BOUNDS AND CHANGING THEM
	new google.maps.LatLng(41, -72),
	new google.maps.LatLng(42, -71));

	var input = document.getElementById('search-location');
	geocoder = new google.maps.Geocoder();

	var searchBox = new google.maps.places.SearchBox(input, {
	  bounds: defaultBounds
	});
}

var radiusSlider;

$(document).ready(function(){
	$(".form_datetime").datetimepicker({
        format: "dd MM yyyy - HH:ii P",
        showMeridian: true,
        autoclose: true,
    });
	$(".form_datetime").datetimepicker({
        format: "dd MM yyyy - HH:ii P",
        showMeridian: true,
        autoclose: true,
    });
	initialize();
	radiusSlider = $("#radius").slider();
});

$(document).ready(function(){
	$('#search-refine-btn').on('click', function(e) {
		e.preventDefault();
		var auth = $("#auth").text();
		var username = $("#username").text();
		var sliderValue = radiusSlider.slider('getValue');
		var location = $("#search-location").val();
		var floorTime = $("#floorDT").val();
		var ceilingTime = $("#ceilingDT").val();
		var prox = document.getElementById("prox").checked;
		console.log(prox);
		var pop = document.getElementById("pop").checked;
		console.log(pop);
		var params = { "auth" : auth, "username" : username, "location" : location, "radius" : sliderValue, "floorTime" : floorTime, "ceilingTime" : ceilingTime, "byProximity" : prox, "byPopularity" : pop};
		window.location = "/browse?" + $.param(params);
	});
});

$(document).ready(function(){
	$('#search-clear-btn').on('click', function(e) {
		e.preventDefault();
		//COMPLETE THIS!!!
	});
});
