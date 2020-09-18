<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Task From</title>
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

				<c:if test="${task ne null}">
					<form action="update_task" method="post">
						<input type="hidden" name="taskCreaterId"
							value="<c:out value='${task.taskCreaterId}'/>" />
				</c:if>

				<c:if test="${task eq null}">
					<form action="add_task" method="post">
				</c:if>

				<!-- get tasked user id -->
				<!-- don't remove this line -->
				<c:choose>
					<c:when test="${role eq 'Developer'}">
						<input type="hidden" name="userID"
							value="<c:out value='${userId}'></c:out>">
					</c:when>
					<c:otherwise>
						<c:if test="${task eq null}">
							<input type="hidden" name="userID"
								value="<c:out value='${user.userId}'/>" />
						</c:if>
						<c:if test="${task ne null}">
							<input type="hidden" name="userID"
								value="<c:out value='${task.forUserId}'/>" />
						</c:if>
					</c:otherwise>
				</c:choose>
				<input type="hidden" name="taskID"
					value="<c:out value='${task.taskId}' />" />

				<caption>
					<h2>
						<c:if test="${task != null}">Update Task</c:if>
						<c:if test="${task == null}">New Task</c:if>
					</h2>
				</caption>
				<div class="form-group">
					<label for="exampleFormControlTextarea1">task Description</label>
					<textarea class="form-control" id="exampleFormControlTextarea1"
						value="${task.taskDescription}" name="task_description">${task.taskDescription }</textarea>
				</div>

				<fieldset class="form-group">
					<label>Expected Time (Day)</label> <input type="text"
						value="<c:out value='${task.expectedTime}' />"
						class="form-control" name="expected_time" required="required">
				</fieldset>

				<c:if test="${task.isApproved eq 'true'}">
					<input type="hidden" name="is_approved" value="1" />
				</c:if>

				<center>
					<button type="submit" class="btn btn-success">Submit</button>
				</center>
				</form>
			</div>
		</div>
	</div>
</body>
</html>