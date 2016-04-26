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

$(document).ready(function() {
	$("#browse").on('click', function(e) {
		console.log("in browse click response in topbar.js");
		e.preventDefault();
			var auth = $("#auth").text();
			console.log("auth: " + auth);
			var params = {"auth" : auth};
				window.location = "/browse?" + $.param(params);
	});
});

$(document).ready(function() {
	$("#now").on('click', function(e) {
		console.log("in browse click response in topbar.js");
		e.preventDefault();
			var auth = $("#auth").text();
			console.log("auth: " + auth);
			var params = {"auth" : auth};
				window.location = "/map?" + $.param(params);
	});
});

$(document).ready(function() {
	$("#account").on('click', function(e) {
		console.log("in browse click response in topbar.js");
		e.preventDefault();
			var auth = $("#auth").text();
			console.log("auth: " + auth);
			var params = {"auth" : auth};
				window.location = "/account?" + $.param(params);
	});
});




