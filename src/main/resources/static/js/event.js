$(document).ready(function () {
	$('a').on('click', function(e) {
	if ($(this).attr('href') == "#full-description") {
		return true;
	}
	console.log("here");
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

$(document).ready(function () {
	$("#attend-btn").on('click', function(e) {
		var auth = $("#auth").text();
		var username = $("#username").text();
		var eventID = $(this).attr('href');
		var params = { "auth" : auth, "username" : username, "eventID" : eventID};
		$.post("/attend", params);
		//change star color
	});
});