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
</head>
<!-- <script>
	window.location.hash = "no-back-button";
	window.location.hash = "#";//again because google chrome don't insert first hash into history
	window.location.hash = "Again-No-back-button";
	window.onhashchange = function() {
		window.location.hash = "#";
	}

	/* history.pushState(null, null, 'no-back-button');
	window.addEventListener('popstate', function(event) {
	  history.pushState(null, null, 'no-back-button');
	}); */
</script> -->
<body>
	<%
		/* response.setHeader("Cache-Control","no-cache");
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragma","no-cache");
	//response.setHeader("Cache-Control","must-sevalidate");
	response.setDateHeader ("Expires", 0); */

	if (session.getAttribute("email") == null || !session.getAttribute("role").equals("Manager")) {
		response.sendRedirect("login.jsp");
	}
	%>
	<!-- HEADER -->
	<jsp:include page="/JSP/Resources/Header.jsp" />
	<%--       <jsp:forward page="/display_users"/> 
         --%>
	<!-- Body -->
	<div class="container text-left">
		<center>
			<a href="<%=request.getContextPath()%>/new_user_form"
				class="btn btn-dark">New User</a> <a href="unApproved_task"
				value='TeamLeader' class="btn btn-secondary">unApproved tasks</a>
		</center>
	</div>
	<hr>
	<!-- search  -->
	<form action="search_user">
		<div class="input-group mb-3">
			<input type="text" name="search" class="form-control"
				placeholder="Type to search..." aria-label="Recipient's username"
				aria-describedby="basic-addon2">
			<div class="input-group-append">
				<a class="input-group-text" id="basic-addon2">search</a>
			</div>
		</div>
	</form>
	<!-- TABLE -->
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
				<th>teamId</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<!-- for loop -->
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
					<td><a
						href="update_user_form?user_id=<c:out value='${user.userId}'/>"
						class="btn btn-warning">Update</a> <c:if
							test="${user.role ne 'Manager' }">
							<a href="remove_user?user_id=<c:out value='${user.userId}' />"
								class="btn btn-danger">Remove</a>
							<a href="display_tasks?user_id=<c:out value='${user.userId}'/>"
								class="btn btn-info">Tasks</a>
							<br />
							<br />
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