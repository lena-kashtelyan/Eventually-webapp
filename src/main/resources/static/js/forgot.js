$(document).ready(function() {
	$("#submit").on('click', function(e) {
		e.preventDefault();
    console.log($("#email").val())
		if ($("#email").val() != "") {
      var email = $("#email").val();
			var params = { "email" : email };
			$.post("/forgot", params, function(responseJSON){
				var object = JSON.parse(responseJSON);
				var redirect = object.redirect;
				if (redirect == "/forgot") {
					alert(object.error);
					args = { "error" : object.error };
					//fill error message and display it
				} else {
					window.location = "/login";
				}
			});
		} else {
      alert("All fields must be non-empty!");
    }
	});
});
