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
<title>user form</title>
</head>
<!-- <script>
window.location.hash="no-back-button";
window.location.hash="Again-No-back-button";//again because google chrome don't insert first hash into history
window.location.hash="Again-No-back-button";
window.onhashchange=function(){window.location.hash="#";}
</script> -->
<body>
	<jsp:include page="/JSP/Resources/Header.jsp" />
	<%
      if (session.getAttribute("email") == null) {
			response.sendRedirect("login.jsp");
			} 
      %>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${user != null}">
					<form action="update_user" method="post">
				</c:if>
				<c:if test="${user == null}">
					<form action="submit_new_user" method="post">
				</c:if>

				<c:if test="${user != null}">
					<input type="hidden" name="userID"
						value="<c:out value='${user.userId}' />" />
				</c:if>

				<caption>
					<h2>
						<c:if test="${user ne null}">Update User</c:if>
						<c:if test="${user eq null}">New User</c:if>
					</h2>
				</caption>

				<fieldset class="form-group">
					<label>First Name</label> <input type="text"
						value="<c:out value='${user.firstName}' />" class="form-control"
						name="first_name" required="required" minlength="5">
				</fieldset>

				<fieldset class="form-group">
					<label>Last Name</label> <input type="text"
						value="<c:out value='${user.lastName}' />" class="form-control"
						name="last_name" required="required" minlength="5">
				</fieldset>

				<fieldset class="form-group">
					<label>UserName</label> <input type="text"
						value="<c:out value='${user.username}' />" class="form-control"
						name="user_name" required="required" minlength="5">
				</fieldset>

				<fieldset class="form-group">
					<label>Email</label> <input type="text"
						value="<c:out value='${user.email}' />" class="form-control"
						name="email" required="required" minlength="5">
				</fieldset>

				<fieldset class="form-group">
					<label>Gender</label> <select class="form-control" name="gender">
						<option value="M" ${user.gender eq 'M' ? 'selected':''}>Male</option>
						<option value="F" ${user.gender eq 'F' ? 'selected':''}>Female</option>
					</select>
				</fieldset>

				<fieldset class="form-group">
					<label>Role</label> <select class="form-control" name="role">
						<option value="Manager" ${user.role eq 'Manager' ? 'selected':''}>Manager</option>
						<option value="TeamLeader"
							${user.role eq 'TeamLeader' ? 'selected':''}>TeamLeader</option>
						<option value="Developer"
							${user.role eq 'Developer' ? 'selected':''}>Developer</option>
					</select>
				</fieldset>

				<fieldset class="form-group">
					<label>Team</label> <select class="form-control" name="team">
						<option value="1" ${user.teamId eq '1' ? 'selected':''}>Java
							Team</option>
						<option value="2" ${user.teamId eq '2' ? 'selected':''}>Mobile
							Team</option>

						<!-- <option value="3">add new team here</option> -->
					</select>
				</fieldset>
				<center>
					<button type="submit" class="btn btn-success">Submit</button>
				</center>
				</form>
			</div>
		</div>
	</div>
</body>
</html>