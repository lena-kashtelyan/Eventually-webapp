$(document).ready(function() {
	$("#create").on('click', function(e) {
		e.preventDefault();
		var auth = $("#auth").text();
		var username = $("#username").text();
		var params = {"auth" : auth, "username" : username};
			window.location = "/create?" + $.param(params);
	});
});

$(document).ready(function() {
	$("#browse").on('click', function(e) {
		e.preventDefault();
		var auth = $("#auth").text();
		var username = $("#username").text();
		var params = {"auth" : auth, "username" : username};
			window.location = "/browse?" + $.param(params);
	});
});

$(document).ready(function() {
	$("#now").on('click', function(e) {
		e.preventDefault();
		var auth = $("#auth").text();
		var username = $("#username").text();
		var params = {"auth" : auth, "username" : username};
			window.location = "/map?" + $.param(params);
	});
});

$(document).ready(function() {
	$("#account").on('click', function(e) {
		e.preventDefault();
		var auth = $("#auth").text();
		var username = $("#username").text();
		var params = {"auth" : auth, "username" : username};
			window.location = "/account?" + $.param(params);
	});
});

$(document).ready(function() {
	$("#brand").on('click', function(e) {
		e.preventDefault();
		var auth = $("#auth").text();
		var username = $("#username").text();
		var params = {"auth" : auth, "username" : username};
			window.location = "/map?" + $.param(params);
	});
});
