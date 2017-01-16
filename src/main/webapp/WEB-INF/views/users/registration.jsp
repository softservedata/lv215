<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Registration</title>
</head>
<body>
	<form:form action="/shedule/registration" method="post" commandName="userFormCreate">

		<h3>First Name</h3><form:input type="text" placeholder="first name" path="firstName"
			id="name" />
		<br>
		<h3>Last Name</h3><form:input type="text" placeholder="second name" path="lastName"
			id="name" />
		<br>
		<h3>Email</h3><form:input type="email" placeholder="email" path="mail" id="name" />
		<br>
		<h3>Phone number</h3><form:input type="phone" placeholder="phone" path="phone" id="name" />
		<br>
		<h3>Position</h3><form:input type="text" placeholder="position" path="position" id="name" />
		<br>
		<h3>Password</h3><form:input type="password" placeholder="password" path="password"
			id="name" />
		<br>
		<button>Registration</button>

	</form:form>



<%-- 	<form:form action="/shedule/registration" method="post"
        commandName="userForm">
        <table>
            <tr>
                <td colspan="2" align="center"><h2>User Form Demo -
                        Registration</h2></td>
            </tr>
            <form:hidden path="id" />
            <tr>
                <td>User first name:</td>
                <td><form:input path="firstName" /></td>
            </tr>
            <tr>
                <td>User Last name:</td>
                <td><form:input path="lastName" /></td>
            </tr>
            <tr>
                <td>User email:</td>
                <td><form:input path="mail" /></td>
            </tr>
            <tr>
                <td>User phone number:</td>
                <td><form:input path="phone" /></td>
            </tr>
            <tr>
                <td>User position:</td>
                <td><form:input path="position" /></td>
            </tr>
            <tr>
                <td>password:</td>
                <td><form:input path="password" /></td>
            </tr>
  
            <tr>
                <td colspan="2" align="center"><input type="submit"
                    value="Register" /></td>
            </tr>
        </table>
    </form:form> --%>
</body>
</html>