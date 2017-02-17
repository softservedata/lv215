var geo = {lviv : "49.839683,24.029717"};
function initMap() {
	var x = document.getElementById('coordinates').value;
	var z = 16;
	if (x == "") {
		x = geo.lviv;
		z = 5;
		document.getElementById('coordinates').value = x;
	}

	var coord = x.split(",");
	var uluru = {
		lat : Number(coord[0]),
		lng : Number(coord[1])
	};
	var map = new google.maps.Map(document.getElementById('map'), {
		zoom : z,
		center : uluru
	});
	var marker = new google.maps.Marker({
		position : uluru,
		map : map
	});
}

$('.close').click(function() {
	$('#geo-error').hide();
});

$('#showmap')
		.click(
				function initialize() {
					var geocoder;
					var map;
					var address = $('#address').val();
					geocoder = new google.maps.Geocoder();
					var latlng = new google.maps.LatLng(49.839683, 24.029717);
					var myOptions = {
						zoom : 16,
						center : latlng,
					};
					map = new google.maps.Map(document.getElementById("map"),
							myOptions);
					if (geocoder) {
						geocoder
								.geocode(
										{
											'address' : address
										},
										function(results, status) {
											if (status == google.maps.GeocoderStatus.OK) {
												if (status != google.maps.GeocoderStatus.ZERO_RESULTS) {
													map
															.setCenter(results[0].geometry.location);
													var str = results[0].geometry.location
															.lat()
															+ ","
															+ results[0].geometry.location
																	.lng();
													document
															.getElementById('coordinates').value = str;
													var infowindow = new google.maps.InfoWindow(
															{
																content : '<b>'
																		+ address
																		+ '</b>',
																size : new google.maps.Size(
																		150, 50)
															});

													var marker = new google.maps.Marker(
															{
																position : results[0].geometry.location,
																map : map,
																title : address
															});
													google.maps.event
															.addListener(
																	marker,
																	'click',
																	function() {
																		infowindow
																				.open(
																						map,
																						marker);
																	});

												} else {
													/*
													 * alert("No results
													 * found");
													 */
													$('#geo-error').show();
													document
															.getElementById('coordinates').value = geo.lviv;
												}
											} else {
												/*
												 * alert("Geocode was not
												 * successful for the following
												 * reason: " + status);
												 */
												$('#geo-error').show();
												document
														.getElementById('coordinates').value = geo.lviv;
											}
										});
					}
				});
