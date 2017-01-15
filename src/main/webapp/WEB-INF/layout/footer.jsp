<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<button class="btn btn-success" onclick="goBack()">Go Back</button>
<br>
<p>(С) 2017 SoftServe</p>

<script>
	function goBack() {
		window.history.back();
	}
</script>
