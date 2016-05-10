var eventID, auth, username;

$(document).ready(function() {
	var eventID = $("#eventID").text();
	var auth = $("#auth").text();
	var username = $("#username").text();
});

$(document).ready(function () {
	$('a').on('click', function(e) {
		console.log("in href click");
	if ($(this).attr('href') == "#full-description") {
		console.log("full desc");
		return true;
	} else {
		console.log("preventDefault");
		e.preventDefault();
	}
	});
});

$(document).ready(function () {

	$('.save-btn').on('click', function(e) {

		var auth = $("#auth").text();
		var username = $("#username").text();
		var eventID = $(this).attr("name");
		var btn = document.getElementById("save"+eventID);
		var params = { "auth" : auth, "username" : username, "eventID" : eventID};
		console.log(btn.innerHTML);
		if (btn.innerHTML == "save") {
			$.post("/save", params);
			btn.innerHTML = "saved";
		} else if (btn.innerHTML == "saved") {
			$.post("/unsave", params);
			btn.innerHTML = "save";
		}
	});
});

$(document).ready(function () {

	$('.attend-btn').on('click', function(e) {

		var auth = $("#auth").text();
		var username = $("#username").text();
		var eventID = $(this).attr("name");
		var btn = document.getElementById("attend"+eventID);
		var params = { "auth" : auth, "username" : username, "eventID" : eventID};
		console.log(btn.innerHTML);
		if (btn.innerHTML == "attend") {
			$.post("/attend", params);
			btn.innerHTML = "attending";
		} else if (btn.innerHTML == "attending") {
			$.post("/unattend", params);
			btn.innerHTML = "attend";
		}
	});
});

$(document).ready(function () {
	$("#comment-btn").on('click', function(e) {
		e.preventDefault();

		var eventID = $("#eventID").text();
		console.log(eventID);
		var auth = $("#auth").text();
		var username = $("#username").text();
		var comment = $("#comment").val();
		if (comment != "") {
			var timestamp = Date.now()/1000 | 0;
			var params = { "auth" : auth, "username" : username, "eventID" : eventID, "url" : comment, "timestamp" : timestamp, "type" : "comment"};
			$.post("/media", params);
			window.location = "/event?" + $.param({"auth" : auth, "username" : username, "eventID" : eventID});
		}
	});
});

$(document).ready(function() {
	var elem = $(".description");
	var text = elem.html()
	var newtext = text.replace(/\\n/g, "<br />");
	elem.html(newtext);
});

$(document).ready(function () {
	var previewNode = document.querySelector("#template");
    previewNode.id = "";
    var previewTemplate = previewNode.parentNode.innerHTML;
    previewNode.parentNode.removeChild(previewNode);

	var myDropzone = new Dropzone(document.body, {
		uploadMultiple: false,
		acceptedFiles:'.jpg,.png,.jpeg,.gif',
		parallelUploads: 6,
		url: 'https://api.cloudinary.com/v1_1/df1bylm3l/image/upload/',
		thumbnailWidth: 80,
		thumbnailHeight: 80,
		parallelUploads: 20,
		previewTemplate: previewTemplate,
		autoQueue: false, // Make sure the files aren't queued until manually added
		previewsContainer: "#previews", // Define the container to display the previews
		clickable: ".fileinput-button" // Define the element that should be used as click trigger to select files.
	});

	myDropzone.on("addedfile", function(file) {
		file.previewElement.querySelector(".start").onclick = function() { myDropzone.enqueueFile(file); };
	});

	myDropzone.on("totaluploadprogress", function(progress) {
		document.querySelector(".progress-bar").style.width = progress + "%";
	});

	myDropzone.on('sending', function (file, xhr, formData) {
		document.querySelector(".progress-bar").style.opacity = "1";
		file.previewElement.querySelector(".start").setAttribute("disabled", "disabled");
		formData.append('api_key', 411248546735325);
		formData.append('timestamp', Date.now() / 1000 | 0);
		formData.append('upload_preset', 'vwkniwoh');
	});

	myDropzone.on('success', function (file, response) {
		console.log("here");
		console.log('Success! Cloudinary public ID is', response.public_id);
		var eventID = $("#eventID").text();
		console.log(eventID);
		var auth = $("#auth").text();
		var username = $("#username").text();
		var url = response.public_id;
		var timestamp = Date.now() / 1000 | 0;
		var params = { "auth" : auth, "username" : username, "eventID" : eventID, "url" : response.public_id, "timestamp" : timestamp, "type" : "image"};
		$.post("/media", params);
		window.location = "/event?" + $.param({"auth" : auth, "username" : username, "eventID" : eventID});
	});

	myDropzone.on("queuecomplete", function(progress) {
		document.querySelector(".progress-bar").style.opacity = "0";
	});

	document.querySelector(".start").onclick = function() {
		myDropzone.enqueueFiles(myDropzone.getFilesWithStatus(Dropzone.ADDED));
	};
	document.querySelector(".cancel").onclick = function() {
		myDropzone.removeAllFiles(true);
	};
});
