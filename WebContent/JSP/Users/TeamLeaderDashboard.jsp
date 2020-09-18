<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Task Management System</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script type="text/javascript">
	$(document).ready(function() {
		$('[data-toggle="offcanvas"]').click(function() {
			$("#navigation").toggleClass("hidden-xs");
		});
	});
</script>
</head>
<!-- prevent back and forward buttons -->
<script>
	$(document).ready(function() {
		function disableBack() {
			window.history.forward()
		}

		window.onload = disableBack();
		window.onpageshow = function(evt) {
			if (evt.persisted)
				disableBack()
		}
	});
</script>
<!-- <script>
	window.location.hash = "no-back-button";
	window.location.hash = "Again-No-back-button";//again because google chrome don't insert first hash into history
	window.location.hash = "Again-No-back-button";
	window.onhashchange = function() {
		window.location.hash = "#";
	}
</script> -->
<body>
	<%
		if (session.getAttribute("email") == null || session.getAttribute("role").equals("Developer")) {
		response.sendRedirect("login.jsp");
	}
	%>
	<!-- header -->
	<jsp:include page="/JSP/Resources/Header.jsp" />
	<c:if test="${role eq 'TeamLeader' }">
		<div class="container text-left">
			<center>
				<a href="display_tasks?user_id=<c:out value='${userId}' /> "
					class="btn btn-dark">MyTasks</a> <a href="unApproved_task"
					value='TeamLeader' class="btn btn-secondary">unApproved tasks</a>
			</center>
		</div>
		<hr>
	</c:if>
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>ID</th>
				<th>first name</th>
				<th>last name</th>
				<th>userName</th>
				<th>email</th>
				<th>gender</th>
				<th>role</th>
				<th>teamName</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<!--   for (User user : users) {  -->
			<c:forEach var="user" items="${userList}">
				<tr>
					<td><c:out value="${user.userId}" /></td>
					<td><c:out value="${user.firstName}" /></td>
					<td><c:out value="${user.lastName}" /></td>
					<td><c:out value="${user.username}" /></td>
					<td><c:out value="${user.email}" /></td>
					<td><c:out value="${user.gender}" /></td>
					<td><c:out value="${user.role}" /></td>
					<td><c:out value="${user.teamName}" /></td>
					<td><c:if test="${user.role ne 'Manager' }">
							<a href="display_tasks?user_id=<c:out value='${user.userId}' />"
								class="btn btn-info">viewTask</a>
							<a href="task_form?user_id=<c:out value='${user.userId}' />"
								class="btn btn-primary">addTasks</a>
						</c:if></td>
				</tr>
			</c:forEach>
			<!-- } -->
		</tbody>
	</table>
	</div>
	</div>
</body>
</html>