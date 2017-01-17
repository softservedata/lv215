<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<td><a href="/users/delete/${user.id}"> delete </a></td>
<td><a href="/users/edit/updateUser/${user.id}"> update </a></td>
<td><a href="/users/edit/updateUserPosition/${user.id}"> change
		position </a></td>
<td><a href="/users/edit/changeRole/${user.id}"> change role </a></td>
<td><a href="/users/edit/addToGroup/${user.id}"> add to group </a></td>
<td><a href="/users/edit/banUser/${user.id}"> ban </a></td>
<td><a href="/users/edit/unBanUser/${user.id}"> unban </a></td>
