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
			console.log("returnint true");
			return true;
		}
	});
});

$(document).ready(function () {
	$('.save-btn').on('click', function(e) {
		var auth = $("#auth").text();
		var username = $("#username").text();
		var eventID = $('.event-link').attr('href');
		console.log("in save");
		console.log(eventID);
		var params = { "auth" : auth, "username" : username, "eventID" : eventID};
		$.post("/save", params);
		//change star color
	});
});

$(document).ready(function () {
	$('.attend-btn').on('click', function(e) {
		var auth = $("#auth").text();
		var username = $("#username").text();
		var eventID = $('.event-link').attr('href');
		var params = { "auth" : auth, "username" : username, "eventID" : eventID};
		$.post("/attend", params);
		//change star color
	});
});