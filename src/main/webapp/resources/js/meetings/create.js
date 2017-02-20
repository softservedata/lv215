function checkform() {
	var dateString = document.purchase.date.value;
	var myDate = new Date(dateString);
	var today = new Date();
	if (document.purchase.txndt.value == "") {
		//something is wrong
		alert('REQUIRED FIELD ERROR: Please enter date in field!')
		return false;
	} else if (myDate < today) {
		//something else is wrong
		alert('You cannot enter a date in the past!');
		return false;
	}
	// if script gets this far through all of your fields
	// without problems, it's ok and you can submit the form
	alert('Alles gut!');
	return true;
}

	//Expect input as d/m/y
	function isValidDate(s) {
		var bits = s.split('/');
		var d = new Date(bits[2], bits[1] - 1, bits[0]);
		return d && (d.getMonth() + 1) == bits[1];
	}

	function isValidForm() {
		var startTimeMeeting = document.getElementById("startTime").value;
		var endTimeMeeting = document.getElementById("endTime").value;
		if (startTimeMeeting > endTimeMeeting) {
			document.getElementById("timevalidator").innerHTML = "Invalid time. The end of the meeting should be after the start meeting.";
			return false;
		}
		
		return true;
	}

	$(function() {
		$("select[name=subject]").chosen({
			width : "100%"
		});
		$("select[name=owner]").chosen({
			width : "100%"
		});
		$("select[name=room]").chosen({
			width : "100%"
		});
		$("select[name=groups]").chosen({
			width : "100%"
		});
		$("select[name=status]").chosen({
			width : "100%"
		});
	})

	//new

	