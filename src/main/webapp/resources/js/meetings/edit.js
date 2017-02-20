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