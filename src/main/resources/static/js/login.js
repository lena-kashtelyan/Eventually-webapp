console.log("here");


$("#okay-btn").on('click', function(e) {
	console.log("handling the okay button click");
	e.preventDefault();
	console.log("handling the okay button click");
	var usernameOrEmail = $("#username").val();
	var password = $("#password").val();
	var redirect = "default";

	params = { "usernameOrEmail" : usernameOrEmail, "password" : password };
	$.post("/login", params, function(responseJSON){
		var object = JSON.parse(responseJSON);
		redirect = object.redirect;
		if (redirect == "/login") {
			args = { "error" : object.error };
			//fill error message and display it
		} else {
			console.log(auth);
			$("html").load("/map", {"auth" : object.auth});
		}
	});
});