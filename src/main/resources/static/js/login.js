$(document).ready(function() {
	$("#login-form").on('submit', function(e) {
		console.log("handling the okay button click");
		e.preventDefault();
		console.log("handling the okay button click");
		var usernameOrEmail = $("#username").val();
		var password = $("#password").val();

		var params = { "usernameOrEmail" : usernameOrEmail, "password" : password };
		$.post("/login", params, function(responseJSON){
			var object = JSON.parse(responseJSON);
			var redirect = object.redirect;
			if (redirect == "/login") {
				args = { "error" : object.error };
				//fill error message and display it
			} else {
				console.log(object.auth);
				window.location = "/map?" + $.param({"auth" : object.auth});
			}
		});
	});
});
