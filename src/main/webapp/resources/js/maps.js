function initMap() {
	var x = document.getElementById('coordinates').value;
	var coord = x.split(",");
	var uluru = {
		lat : Number(coord[0]),
		lng : Number(coord[1])
	};
	var map = new google.maps.Map(document.getElementById('map'), {
		zoom : 17,
		center : uluru
	});
	var marker = new google.maps.Marker({
		position : uluru,
		map : map,
	});
}