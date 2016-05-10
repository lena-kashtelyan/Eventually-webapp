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
  	} else {
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
    window.location.reload();
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
    window.location.reload();
	});
});