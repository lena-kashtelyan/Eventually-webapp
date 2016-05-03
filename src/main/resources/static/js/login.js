$(document).ready(function() {
	$("#submitLogin").on('click', function(e) {
		e.preventDefault();
		console.log($("#usernameOrEmail").val());
		if ($("#usernameOrEmail").val() != "" && $("#password").val() != "") {
			var usernameOrEmail = $("#usernameOrEmail").val();
			var password = $("#password").val();
			var auth = $("#auth").text();
			var username = $("#username").text();
			console.log(auth);
			var params = { "usernameOrEmail" : usernameOrEmail, "password" : password, "auth" : auth, "username" : username};
			$.post("/login", params, function(responseJSON){
				var object = JSON.parse(responseJSON);
				var redirect = object.redirect;
				if (redirect == "/login") {
					alert("Invalid login. Please try again.");
					args = { "error" : object.error };
					//fill error message and display it
				} else {
					console.log(object.auth);
					window.location = "/map?" + $.param({"auth" : object.auth, "username" : object.username});
				}
			});
		} else {
			alert("Username and password fields must not be empty!");
		}
	});

	$("#signup").on("click", function(e) {
		e.preventDefault();
		window.location = "/signup";
	});

	$("#forgot").on("click", function(e) {
		e.preventDefault();
		window.location = "/forgot";
	});

	$("#facebooklogin").on("click", function(e) {
		e.preventDefault();
		window.location = "/facebooklogin";
	});
});
