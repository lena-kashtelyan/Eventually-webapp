$(document).ready(function() {
	$("#brand").on('click', function(e) {
		e.preventDefault();
		var auth = $("#auth").text();
		var params = {"auth" : auth};
			window.location = "/map?" + $.param(params);
	});
});