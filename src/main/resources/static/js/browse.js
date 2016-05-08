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

