<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<header> <nav class="navbar navbar-expand-md navbar-dark"
		style="background-color: gray">
	<div>
		<c:choose>
			<c:when test="${role eq 'Manager'}">
				<a href="<%=request.getContextPath()%>/display_users"
					class="navbar-brand">Task Management System</a>
			</c:when>

			<c:when test="${role eq 'TeamLeader'}">
				<a href="display_developers?team_id=<c:out value='${teamId}'/>"
					class="navbar-brand">Task Management System</a>
			</c:when>

			<c:when test="${role eq 'Developer'}">
				<a href="display_tasks?user_id=<c:out value='${userId}'/>"
					class="navbar-brand">Task Management System</a>
			</c:when>
		</c:choose>

	</div>
	<!-- manager page header --> <c:if test="${role eq 'Manager'}">
		<ul class="navbar-nav">
			<li><a href="<%=request.getContextPath()%>/display_users"
				class="nav-link">Users</a></li>
		</ul>
		<ul class="navbar-nav">
			<li><a href="<%=request.getContextPath()%>/Teams"
				class="nav-link">Teams</a></li>
		</ul>
	</c:if> <!-- Team Leader page header --> <c:if test="${role eq 'TeamLeader' }">
		<ul class="navbar-nav">
			<li><a
				href="display_developers?team_id=<c:out value='${teamId}'/> "
				class="nav-link">MyTeam</a></li>
		</ul>
		<ul class="navbar-nav">
			<li><a href="display_tasks?user_id=<c:out value='${userId}' />"
				class="nav-link">MyTasks</a></li>
		</ul>
	</c:if> <!-- Developer page header --> <c:if test="${role eq 'Developer' }">
		<ul class="navbar-nav">
			<li><a href="display_tasks?user_id=<c:out value='${userId}' />"
				class="nav-link">MyTasks</a></li>
		</ul>
	</c:if>
	<ul class="navbar-nav navbar-collapse justify-content-end">
		<li>
			<form action="logout" method="post">
				<input type="submit" class="fadeIn fourth" value="logout">
			</form>
		</li>
	</ul>
	</nav> </header>
	<div class="row">
		<div class="container">
			<br /> <a><b>user:</b> ${firstName} ${lastName}</a><br /> <a><b>role:</b>
				${role}</a>
			<hr>
</body>
</html>