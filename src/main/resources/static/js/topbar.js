$(document).ready(function() {
	$("#create").on('click', function(e) {
		console.log("in create click response in topbar.js");
		e.preventDefault();
			var auth = $("#auth").val();
			console.log("auth: " + auth);
			var params = {"auth" : auth};
			$.get("/create", params, function(responseJSON){
				console.log("in post request in topbar.js");
				window.location = "/create?" + auth;
			});
	});
});
