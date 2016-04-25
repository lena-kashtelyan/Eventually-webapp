$(document).ready(function() {
	$("#create").on('click', function(e) {
		console.log("in create click response in topbar.js");
		e.preventDefault();
			var auth = $("#auth").text();
			console.log("auth: " + auth);
			var params = {"auth" : auth};
				window.location = "/create?" + $.param(params);
	});
});

