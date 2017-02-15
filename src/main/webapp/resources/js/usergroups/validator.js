	function validateForm() {
		document.getElementById("nameErrorMsg").innerHTML = "";
		document.getElementById("descriptionErrorMsg").innerHTML = "";

		var isValid = true;

		var description = document.getElementById("description");
		var name = document.getElementById("name");

		if (name.value.length < minName) {
			document.getElementById("nameErrorMsg").innerHTML = document.getElementById("shortName").value;
			isValid = false;
		}
		if (name.value.length > maxName) {
			document.getElementById("nameErrorMsg").innerHTML = document.getElementById("longName").value;
			isValid = false;
		}
		if (description.value.length < minDescription) {
			document.getElementById("descriptionErrorMsg").innerHTML = document.getElementById("shortDescription").value;;
			isValid = false;
		} else if (description.value.length > maxDescription) {
			document.getElementById("descriptionErrorMsg").innerHTML = document.getElementById("longDescription").value;
			isValid = false;
		}
		return isValid;
	}