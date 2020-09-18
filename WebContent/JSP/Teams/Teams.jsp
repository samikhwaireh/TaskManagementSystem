<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Teams</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<!-- <script>
window.location.hash="no-back-button";
window.location.hash="Again-No-back-button";//again because google chrome don't insert first hash into history
window.location.hash="Again-No-back-button";
window.onhashchange=function(){window.location.hash="#";}
</script> -->
<body>
	<%
      if (session.getAttribute("email") == null) {
			response.sendRedirect("login.jsp");
			} 
      %>
	<f:view>
		<header> <nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: gray">
		<div>
			<a href="#" class="navbar-brand">Task Management System</a>
		</div>
		<ul class="navbar-nav">
			<li><a href="<%=request.getContextPath()%>/display_users"
				class="nav-link">Users</a></li>
		</ul>
		<ul class="navbar-nav">
			<li><a href="<%=request.getContextPath()%>/Teams"
				class="nav-link">Teams</a></li>
		</ul>
		<ul class="navbar-nav navbar-collapse justify-content-end">
			<li><a href="logout" class="nav-link">Logout</a></li>
		</ul>
		</nav> </header>
		<div class="row">
			<div class="container">
				<br /> <a><b>user:</b> ${firstName} ${lastName}</a><br /> <a><b>role:</b>
					${role}</a>
				<hr>
				<tbody>
					<!-- for each team -->
					<c:forEach var="team" items="${teamList}">
						<div class="card">
							<div class="card-body" style="display: inline-block;">
								<tr>
									<th><a> <b>ID: </b> <c:out value="${team.teamId}"></c:out>
									</a> </br> <a> <b></b> <c:out value="${team.teamName}"></c:out>
									</a></th>
									<th>&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp; <a> <b><span
												class="badge badge-warning">TeamLeader</span></b> <c:out
												value="${team.teamLeader}"></c:out>
									</a>
									<th>
									<th>&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp; <a
										href="display_developers?team_id=<c:out value='${team.teamId}' />"
										class="btn btn-info">Developers</a>
									<th>
								</tr>
							</div>
						</div>
						<hr>
					</c:forEach>
				</tbody>
			</div>
		</div>
	</f:view>
</body>
</html>