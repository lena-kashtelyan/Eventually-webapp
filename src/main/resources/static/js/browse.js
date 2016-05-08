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
		console.log("clicking save");
		var auth = $("#auth").text();
		var username = $("#username").text();
		var eventID = $(this).attr("id");
		console.log("(un)saving event by ID: " + eventID);
		var params = { "auth" : auth, "username" : username, "eventID" : eventID};
		if ($(this).text() == "save") {
			$.post("/save", params);
			$(".save-btn #eventID").attr("name", "saved");
			console.log($(".save-btn #eventID").attr("name"));
		} else if ($(this).text() == "saved") {
			$.post("/ussave", params);
			$(this).innerHTML="save";
		}
	});
});

$(document).ready(function () {
	$('.attend-btn').on('click', function(e) {
		console.log("clicking attend");
		var auth = $("#auth").text();
		var username = $("#username").text();
		var eventID = $(this).attr("id");
		console.log("(un)attending event by ID: " + eventID);
		var params = { "auth" : auth, "username" : username, "eventID" : eventID};
		if ($(this).text() == "attend") {
			$.post("/attend", params);
			$(this).innerHTML="attending";
		} else if ($(this).text() == "attending") {
			$.post("/unattend", params);
			$(this).innerHTML="attend";
		}
	});
});