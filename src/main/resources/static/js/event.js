var eventID, auth, username;

$(document).ready(function() {
	eventID = $("#eventID").text();
	auth = $("#auth").text();
	username = $("#username").text();
});

$(document).ready(function () {
	$('a').on('click', function(e) {
		console.log("in href click");
	if ($(this).attr('href') == "#full-description") {
		console.log("full desc");
		return true;
	} else {
		console.log("preventDefault");
		e.preventDefault();
	}
	});
});

$(document).ready(function () {
	$("#save-btn").on('click', function(e) {
		if ($(this).text() == "save") {
			var params = { "auth" : auth, "username" : username, "eventID" : eventID};
			$.post("/save", params);
			document.getElementById("save-btn").innerHTML="saved";
		} else if ($(this).text() == "saved") {
			var params = { "auth" : auth, "username" : username, "eventID" : eventID};
			$.post("/ussave", params);
			document.getElementById("save-btn").innerHTML="save";
		}
	});
});

$(document).ready(function () {
	$("#attend-btn").on('click', function(e) {
		if ($(this).text() == "attend") {
			var params = { "auth" : auth, "username" : username, "eventID" : eventID};
			$.post("/attend", params);
			document.getElementById("attend-btn").innerHTML="attending";
		} else if ($(this).text() == "attending") {
			var params = { "auth" : auth, "username" : username, "eventID" : eventID};
			$.post("/unattend", params);
			document.getElementById("attend-btn").innerHTML="attend";
		}
	});
});

$(document).ready(function () {
	$("#comment-btn").on('click', function(e) {
		e.preventDefault();
		var comment = $("#comment").val();
		var params = { "auth" : auth, "username" : username, "eventID" : eventID, "comment" : comment};
		$.post("/comment", params);
		location.reload(true);
	});
});

// $.cloudinary.config({ cloud_name: 'df1bylm3l', api_key: '411248546735325'});

// var cl = cloudinary.CloudinaryJQuery.new( { cloud_name: 'df1bylm3l', api_key: '411248546735325'});

