<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <jsp:useBean id="calcService" class="com.softserve.edu.CalcService" scope="page" /> --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Exec</h1>
	<br />
		(7+8)/2=  ${calcService.avg(7.0,8.0)}
		<!-- <c:out value="${calcService.avg(7.0,8.0)}" /> -->
	<br/>
	<UL>
		<c:forEach var="i" begin="1" end="10">
			<LI>${i}
		</c:forEach>
	</UL>
</body>
</html>