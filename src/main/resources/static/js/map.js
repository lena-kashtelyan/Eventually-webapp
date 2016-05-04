window.onload = function() {
  var vizjson = 'https://cs32finalproject.cartodb.com/api/v2/viz/ea773bf6-124e-11e6-942b-0e787de82d45/viz.json';
  cartodb.createVis('map-container', vizjson);
}
