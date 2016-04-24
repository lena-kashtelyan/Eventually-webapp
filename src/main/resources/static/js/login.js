$(document).ready(function() {
	$("#submitLogin").on('click', function(e) {
		e.preventDefault();
		if ($("#username").val() != "" && $("#password").val() != "") {
			var usernameOrEmail = $("#username").val();
			var password = $("#password").val();
			var auth = $("#auth").val();
			var params = { "usernameOrEmail" : usernameOrEmail, "password" : password, "auth" : auth};
			$.post("/login", params, function(responseJSON){
				var object = JSON.parse(responseJSON);
				var redirect = object.redirect;
				if (redirect == "/login") {
					alert("Invalid login. Please try again.");
					args = { "error" : object.error };
					//fill error message and display it
				} else {
					console.log(object.auth);
					window.location = "/map?" + $.param({"auth" : object.auth});
				}
			});
		}
	});
});
