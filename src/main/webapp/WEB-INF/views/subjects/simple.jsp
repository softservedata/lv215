<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="com.softserve.edu.schedule.controller.SubjectController"%>
<%@ page import="com.softserve.edu.schedule.controller.UserController"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<form action="/schedule/subjects/simple?${_csrf.parameterName}=${_csrf.token}" method="POST" enctype="multipart/form-data">
  <p>File Upload: <input type="file" name="file" size="60" /></p>  
  <input type="submit" value="Submit" />  
</form>