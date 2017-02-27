$(document).ready(function() {
	$('#calendar').fullCalendar({
		defaultView : 'agendaWeek',
		locale : $("#language").text(),
		header : {
			left : 'prev,next,today',
			center : 'title',
			right : 'month,agendaWeek,agendaDay,listWeek'
		},
		nowIndicator : true,
		navLinks : true,
		events : {
			url : $("#userRestURL").text(),
			type : 'GET',
			data : {
				userId : $("#userId").text()
			}
		}

	})

});

google.charts.load('current', {
	packages : [ 'corechart' ]
});
google.charts.setOnLoadCallback(drawChartWeek);
google.charts.setOnLoadCallback(drawChartMonth);
var date = new Date();
var firstDay = new Date(date.getFullYear(), date.getMonth(), 2).toISOString()
		.substring(0, 10);
var lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 1)
		.toISOString().substring(0, 10);
var date2 = new Date();
var first = date2.getDate() - date2.getDay() + 1;
var last = first + 6;
var firstDay2 = new Date(date2.setDate(first)).toISOString().substring(0, 10);
var lastDay2 = new Date(date2.setDate(last)).toISOString().substring(0, 10);
var jsonData = $.ajax({
	url : $("#userRestChart").text(),
	data : {
		start : firstDay,
		end : lastDay,
		userId : $("#userId").text()
	},
	dataType : 'json',
	async : false
}).responseText;
var jsonData2 = $.ajax({
	url : $("#userRestChart").text(),
	data : {
		start : firstDay2,
		end : lastDay2,
		userId : $("#userId").text()
	},
	dataType : 'json',
	async : false
}).responseText;

function drawChartWeek() {
	var data = new google.visualization.DataTable();
	data.addColumn('string', 'Subject');
	data.addColumn('number', 'Time');
	data.addRows(Object.keys(JSON.parse(jsonData2)).length);
	var iterator = 0;
	$.each(JSON.parse(jsonData2), function(key, value) {
		data.setValue(iterator, 0, key);
		data.setValue(iterator, 1, value);
		iterator++;
	});
	var options = {
		'title' : "My meetings of current week\n(" + firstDay2 + " - "
				+ lastDay2 + ")",
		'titleFontSize' : 12,
		'width' : 300,
		'height' : 300,
		'legend' : {
			'position' : 'bottom',
			'alignment' : 'left'
		},
		'pieHole' : 0.4
	};
	var chart = new google.visualization.PieChart(document
			.getElementById('myPieChartWeek'));
	chart.draw(data, options);
}

function drawChartMonth() {
	var data = new google.visualization.DataTable();
	data.addColumn('string', 'Subject');
	data.addColumn('number', 'Time');
	data.addRows(Object.keys(JSON.parse(jsonData)).length);
	var iterator = 0;
	$.each(JSON.parse(jsonData), function(key, value) {
		data.setValue(iterator, 0, key);
		data.setValue(iterator, 1, value);
		iterator++;
	});
	var options = {
		'title' : "My meetings of current month\n(" + firstDay + " - "
				+ lastDay + ")",
		'titleFontSize' : 12,
		'width' : 300,
		'height' : 300,
		'legend' : {
			'position' : 'bottom',
			'alignment' : 'left'
		},
		'pieHole' : 0.4
	};
	var chart = new google.visualization.PieChart(document
			.getElementById('myPieChartMonth'));
	chart.draw(data, options);
}