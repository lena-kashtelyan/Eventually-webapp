$(document).ready(function () {
	$('a').on('click', function(e) {
		e.preventDefault();
		var auth = $("#auth").text();
		var username = $("#username").text();
		var eventID = $(this).attr('href');
		window.location = "/event?" + $.param({"auth" : auth, "username" : username, "eventID" : eventID});
	});
});

$(document).ready(function () {
	$("#save-btn").on('click', function(e) {
		var auth = $("#auth").text();
		var username = $("#username").text();
		var eventID = $(this).attr('href');
		var params = { "auth" : auth, "username" : username, "eventID" : eventID};
		$.post("/save", params);
		//change star color
	});
});