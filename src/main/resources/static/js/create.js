$(document).ready(function() {
	$("#create-btn").on('click', function(e) {
		console.log("in create click response in create.js");
		e.preventDefault();
			var auth = $("#auth").text();
			var name = $("#eventName").val();
			var description = $("#eventDesc").val();
			var date = $("#date").val();
			console.log(date);
			var time = $("#time").val();
			console.log(time);
			var location = $("#location").val();
			var category = $("category").val();
			var facebookAdd = $("#facebookYes").val();
			console.log("auth: " + auth);
			var params = {"auth" : auth, "username" : auth, "eventName" : name, "date" : date, "description" : description, "time" : time, "location" : location, "category" : category, "facebookAdd" : facebookAdd};
<<<<<<< HEAD
				
=======
>>>>>>> ae8e231339b34e9489d86247fd8a4afe5f4827e6
			$.post("/create", params, function(responseJSON){
			});
	});
});


