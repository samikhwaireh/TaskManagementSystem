<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Task Management System</title>
<script type="text/javascript">
         $(document).ready(function(){
         	   $('[data-toggle="offcanvas"]').click(function(){
         	       $("#navigation").toggleClass("hidden-xs");
         	   });
         	});
      </script>
</head>
<!-- prevent back and forward buttons -->
<script>
      $(document).ready(function() {
          function disableBack() { window.history.forward() }
      
          window.onload = disableBack();
          window.onpageshow = function(evt) { if (evt.persisted) disableBack() }
      });
      
   </script>
<!-- <script>
window.location.hash="no-back-button";
window.location.hash="Again-No-back-button";//again because google chrome don't insert first hash into history
window.location.hash="Again-No-back-button";
window.onhashchange=function(){window.location.hash="#";}
</script> -->
<%
      response.setHeader("Cache-Control","no-cache");
      response.setHeader("Cache-Control","no-store");
      response.setHeader("Cache-Control","must-sevalidate");
      response.setHeader("Pragma","no-cache");
      response.setDateHeader ("Expires", 0);
      %>
<body>
	<%
      if (session.getAttribute("email") == null) {
			response.sendRedirect("login.jsp");
			} 
      %>
	<jsp:include page="/JSP/Resources/Header.jsp" />
	<!-- developer Body -->
	<c:if test="${role eq 'Developer'}">
		<div class="container text-left">
			<center>
				<a href="task_form?user_id=<c:out value='${userId}' />"
					class="btn btn-light">New Task</a>
			</center>
		</div>
		<hr>
	</c:if>
	<!-- TABLE -->
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>ID</th>
				<th>Date</th>
				<th>Expected Time</th>
				<th>Created by</th>
				<th>Description</th>
				<th>isApproved</th>
				<th>State</th>
				<th>Tasked User</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="task" items="${taskList}">
				<tr>
					<td><c:out value="${task.taskId}" /></td>
					<td><c:out value="${task.taskDate}" /></td>
					<td><c:out value="${task.expectedTime}" /></td>
					<td><c:out value="${task.taskCreaterId} ${task.craeterName}" />
					</td>
					<td><c:out value="${task.taskDescription}" /></td>
					<td><c:out value="${task.isApproved}" /></td>
					<td><c:choose>
							<c:when test="${task.taskState eq '1'}">Done</c:when>
							<c:when test="${task.taskState eq '0'}">in process</c:when>
						</c:choose></td>
					<td><c:out value="${task.forUserId} ${task.assignedFor}" /></td>
					<td><c:if test="${role ne 'Developer'}">
							<a
								href="remove_task?task_id=<c:out value='${task.taskId}' />&user_id=<c:out value="${task.forUserId}"/>"
								class="btn btn-danger">Remove</a>
							<a
								href="update_task_form?task_id=<c:out value='${task.taskId}' />"
								class="btn btn-warning">Update</a>
							<c:if test="${task.isApproved eq 'false'}">
								<a href="approve_task?task_id=<c:out value='${task.taskId}' />"
									class="btn btn-success">Approve</a>
							</c:if>
						</c:if> <c:if test="${role eq 'Developer'}">
							<c:if test="${task.taskState eq '0'}">
								<c:if test="${task.isApproved eq 'true'}">
									<a href="make_it_done?task_id=<c:out value='${task.taskId}' />">Done</a>
								</c:if>
							</c:if>
						</c:if></td>
				</tr>
			</c:forEach>
			<!-- } -->
		</tbody>
	</table>
</body>
</html>