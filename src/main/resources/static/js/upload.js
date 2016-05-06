var myDropzone = new Dropzone(document.getElementById('dropzone-area'), {
  uploadMultiple: false, 
  acceptedFiles:'.jpg,.png,.jpeg,.gif',
  parallelUploads: 6,
  url: 'https://api.cloudinary.com/v1_1/df1bylm3l/image/upload'
});

myDropzone.on('sending', function (file, xhr, formData) {
  formData.append('api_key', 411248546735325);
  formData.append('timestamp', Date.now() / 1000 | 0);
  formData.append('upload_preset', 'vwkniwoh');
});

myDropzone.on('success', function (file, response) {
  console.log("here");
  console.log('Success! Cloudinary public ID is', response.public_id);
  var url = "http://res.cloudinary.com/df1bylm3l/image/upload/"
  var timestamp = getTime();
  url = url.concat(response.public_id);
  params = {"url": url, "timestamp", timestamp};
  $.post("/create", params, function(responseJSON){

  }); 
});

function getTime(){
  var a = Date.now();
  var months = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
  var year = a.getFullYear();
  var month = months[a.getMonth()];
  var date = a.getDate();
  var hour = a.getHours();
  var min = a.getMinutes();
  var sec = a.getSeconds();
  var time = date + ' ' + month + ' ' + year + ' ' + hour + ':' + min + ':' + sec ;
  return time;
}