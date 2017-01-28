<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<div class="row">
	<br>
	<br>
	<br>
	<h1>Sorry. But something unexpected happened with your application. We apologize and we will fix it in short time.</h1>
	<p><b>Error type: </b>${exception.getClass()}</p>
	<p><b>Error message: </b>${exception.getMessage()}</p>
	<p><b>Caused by: </b>${exception.getCause()}</p>
	<p><b>Error full name: </b>${exception.toString()}</p>
	<p><a class="btn btn-default"	href="${pageContext.request.contextPath}/">Go to main page</a></p>
	<br>
	<br>
	<br>
</div>