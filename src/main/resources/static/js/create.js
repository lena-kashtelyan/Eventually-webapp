$(document).ready(function() {
	$("#create-btn").on('click', function(e) {
		console.log("in create click response in create.js");
		e.preventDefault();
			var auth = $("#auth").text();
			var name = $("#eventName").text();
			var description = $("#eventDesc").text();
			var date = $("#date").text();
			var time = $("#time").text();
			var location = $("#location").text();
			var category = $("category").text();
			var facebookAdd = $("#facebookYes").val();
			console.log("auth: " + auth);
			var params = {"auth" : auth, "username" : auth, "eventName" : name, "date" : date, "description" : description, "time" : time, "location" : location, "category" : category, "facebookAdd" : facebookAdd};
				window.location = "/browse?" + $.param(params);
			$.post("/create", params, function(responseJSON){
			});
	});
});


