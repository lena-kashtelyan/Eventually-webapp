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
	console.log("in save --");
	$('.save-btn').on('click', function(e) {
		console.log("in save");
		var auth = $("#auth").text();
		var username = $("#username").text();
		var eventID = $("#event-link").attr('href');
		console.log("in save");
		console.log(eventID);
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