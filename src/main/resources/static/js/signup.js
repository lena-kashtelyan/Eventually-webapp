function validateForm(e) {
    $('#formSignup').validate({
        rules: {
            firstName: {
                // minlength: 1,
                required: true,
            },
            lastName: {
                // minlength: 1,
                required: true,
            },
            username: {
            	// minlength: 2,
                required: true,
            },
            email: {
            	email: true,
                required: true,
            },
           	password: {
                // minlength: 6,
                required: true,
            }
        },
        highlight: function (element) {
            $(element).closest('.control-group').removeClass('success').addClass('error');
        },
        success: function (element) {
        	console.log("ins uccess" + element);
            element.text('OK!').addClass('valid')
                .closest('.control-group').removeClass('error').addClass('success');
        },
        submitHandler: function(form) {
        	submitSignup(e);
        }
    });
}

function submitSignup(e) {
	console.log("in submitsignup");
	e.preventDefault();
	if ($("#firstName").val() != "" && $("#lastName").val() != "" && $("#username").val() != "" && $("#email") && $("#password").val() != "") {
		var firstName = $("#firstName").val();
		var lastName = $("#lastName").val();
      	var username = $("#username").val();
      	var email = $("#email").val();
      	var password = $("#password").val();
		var auth = $("#auth").val();
		var params = { "firstName" : firstName, "lastName" : lastName, "username" : username, "email" : email, "password" : password, "auth" : auth};
		$.post("/signup", params, function(responseJSON){
			var object = JSON.parse(responseJSON);
			var redirect = object.redirect;
			if (redirect == "/signup") {
				alert(object.error);
				args = { "error" : object.error };
				//fill error message and display it
			} else {
				window.location = "/map?" + $.param({"auth" : object.auth, "username" : object.username});
			}
		});
	}
}

$(document).ready(function() {
	$("#submit").on('click', function(e) {
		validateForm(e);
	});
});
