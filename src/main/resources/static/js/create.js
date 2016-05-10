function validateForm(e) {
	console.log("validate form, beginning");
	var validation = true;
    $('#event-form').validate({
        rules: {
            eventName: {
                minlength: 2,
                required: true,
            },
            date: {
                required: true,
            },
            time: {
                required: true,
            },
           	location: {
                minlength: 2,
                required: true,
            },
            category: {
                required: true,
            }
        },
        highlight: function (element) {
            $(element).closest('.control-group').removeClass('success').addClass('error');
            console.log("validation returning false");
            validation = false;
        },
        success: function (element) {
        	console.log("ins uccess" + element);
            element.text('OK!').addClass('valid')
                .closest('.control-group').removeClass('error').addClass('success');
        },
        submitHandler: function(form) {
        	e.preventDefault();
        	submitEvent()
        }
    });
    console.log("val" + validation);
    return validation;
}

function submitEvent() {
	console.log("in submitevent");
	var auth = $("#auth").text();
	console.log("auth: " + auth);
	var username = $("#username").text();
	console.log("username: " + username);
	var name = $("#eventName").val();
	console.log(name);
	var description = $("#eventDesc").val();
	console.log(description);
	var date = $("#date").val();
	console.log(date);
	var time = $("#time").val();
	console.log(time);
	var location = $("#location").val();
	console.log(location);
	var e = document.getElementById("category");
	var category = e.options[e.selectedIndex].text;
	console.log(category);
	var facebookAdd = $("#facebookAdd").val();
	console.log(facebookAdd);
	var params = {"auth" : auth, "username" : username, "eventName" : name, "date" : date, "description" : description, "time" : time, "location" : location, "category" : category, "facebookAdd" : facebookAdd};
	$.post("/create", params, function(responseJSON){
		var responseObject = JSON.parse(responseJSON);
		window.location = responseObject.redirect;
	});
}

$(document).ready(function () {
	$("#create-btn").on('click', function(e) {
		var val = validateForm(e);
	});
});

$(document).ready(function(){
    var date_input=$('input[name="date"]');
    var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
    date_input.datepicker({
        format: 'mm/dd/yyyy',
        container: container,
        todayHighlight: true,
        autoclose: true,
    });
});

function initialize() {
	var defaultBounds = new google.maps.LatLngBounds(  //THINK ABOUT BOUNDS AND CHANGING THEM
	new google.maps.LatLng(41, -72),
	new google.maps.LatLng(42, -71));

	var input = document.getElementById('location');
	geocoder = new google.maps.Geocoder();

	var searchBox = new google.maps.places.SearchBox(input, {
	  bounds: defaultBounds
	});
}

$(document).ready(function(){ initialize();});
