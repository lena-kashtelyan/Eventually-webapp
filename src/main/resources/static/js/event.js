var eventID, auth, username;

$(document).ready(function() {
	eventID = $("#eventID").text();
	auth = $("#auth").text();
	username = $("#username").text();
});

$(document).ready(function () {
	$('a').on('click', function(e) {
	if ($(this).attr('href') == "#full-description") {
		return true;
	} else {
		e.preventDefault();
	}
	});
});

$(document).ready(function () {
	$("#save-btn").on('click', function(e) {
		// var auth = $("#auth").text();
		// var username = $("#username").text();
		var params = { "auth" : auth, "username" : username, "eventID" : eventID};
		$.post("/save", params);
		//change star color
	});
});

$(document).ready(function () {
	$("#attend-btn").on('click', function(e) {
		// var auth = $("#auth").text();
		// var username = $("#username").text();
		var params = { "auth" : auth, "username" : username, "eventID" : eventID};
		$.post("/attend", params);
		//change to "saved"
	});
});

$(document).ready(function () {
	$("#comment-btn").on('click', function(e) {
		console.log("clicked comment button")
		e.preventDefault();
	//	var auth = $("#auth").text();
	//	var username = $("#username").text();
		console.log(eventID);
		var comment = $("#comment").text();
		console.log(comment);
		var params = { "auth" : auth, "username" : username, "eventID" : eventID, "comment" : comment};
		//$.post("/comment", params);
	});
});

// $.cloudinary.config({ cloud_name: 'df1bylm3l', api_key: '411248546735325'});

// var cl = cloudinary.CloudinaryJQuery.new( { cloud_name: 'df1bylm3l', api_key: '411248546735325'});

