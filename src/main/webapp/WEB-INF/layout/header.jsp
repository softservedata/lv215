<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="row">
	<div class="col-md-6">
		<h1>
			<a href="/schedule/"><b>Schedule</b></a>
		</h1>
		<p>
			<b><i>Manage your time.</i></b> 
		</p>
	</div>
	<div class="col-md-6">
		<br>
		<a class="btn btn-success" href="${pageContext.request.contextPath}/registration">Registration</a>
	</div>
	<%-- <div class="col-md-6">
		<p>
			<sec:authorize access="isAuthenticated()">
				<div class="row">
					<div class="col-md-3">
						<a class="text-primary lead" href="/userDetails"><sec:authentication property="principal.fullName"/></a>
					</div>
					<div class="col-md-3">
						<form:form action="/logout" method="post">
							<input class="btn btn-danger" type="submit" value="Logout">
						</form:form>
					</div>
					<div class="col-md-3">
						<a class="btn btn-success" href="/cart">Кошик</a>
					</div>
					<div class="col-md-3">
						<a class="btn btn-success" href="/myOrders">Мої замовлення</a>
					</div>
				</div>
			</sec:authorize>
			<sec:authorize access="!isAuthenticated()">
			<p> </p><br>
			<a class="btn btn-success" href="/login">Авторизація</a>
			<a class="btn btn-success" href="/register">Зареєструватися</a>
			</sec:authorize>
		</p>
	</div> --%>
</div>
