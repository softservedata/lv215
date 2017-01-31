<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script type="text/javascript">
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
</script>
<div class="container">
	<div class="row">
		<div
			class="col-lg-4 col-lg-offset-4 col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 panel panel-default">
			<h3 class="text-center"><spring:message code="lbl.meeting.editstatus" /></h3>
			<form:form role="form" method="post" modelAttribute="meetingForm">
				<form:input path="id" type="hidden" />

				<div class="form-group">
					<label for="status"><spring:message code="lbl.meeting.changestatus" /></label>
					<form:select class="form-control" path="status" id="status">
						<c:forEach items="${meetingStatuses}" var="status">
							<c:choose>
								<c:when test="${meetingForm.status eq status}">
									<option value="${status}" selected="selected">${status}</option>
								</c:when>
								<c:otherwise>
									<option value="${status}">${status}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</form:select>
					<form:errors path="status" class="text-danger" />
				</div>
				
				<div class="form-group text-center">
					<input type="submit" class="btn btn-default"
						value="<spring:message code="lbl.form.save"/>"> <a
						class="btn btn-default"
						href="/schedule/meetings/editStatus/${meetingForm.id}"><spring:message
							code="lbl.form.reset" /></a> <a class="btn btn-default"
						href="${pageContext.request.contextPath}/meetings"><spring:message
							code="lbl.form.cancel" /></a>
				</div>
			</form:form>
		</div>
	</div>
</div>