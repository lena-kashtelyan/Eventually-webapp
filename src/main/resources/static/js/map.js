$(document).ready(function() {
  console.log("pront");
  var map = new L.Map('map-container', {
    center: [0,0],
    zoom: 2
  });

  cartodb.createLayer(map, 'http://documentation.cartodb.com/api/v2/viz/2b13c956-e7c1-11e2-806b-5404a6a683d5/viz.json')
    .addTo(map)
    .on('done', function(layer) {
      console.log("test");
    })
    .on('error', function(err) {
      alert("some error occurred: " + err);
    });
});
