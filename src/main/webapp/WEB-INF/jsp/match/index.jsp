<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Match manager</title>
</head>
<body>
	<c:if test="${not empty level}">
		<div>
			<h3>${level.name}</h3>
		</div>

		<c:if test="${not empty matchList}">

			<div id="">
				<table id="">
					<tr>
						<th>ID</th>
						<th>Title</th>
						<th>Question</th>
						<th>Edit</th>
						<th>Delete</th>

					</tr>

					<c:forEach items="${matchList}" var="m">
						<tr>
						<td>${m.idMatch}</td>
						<td>${m.title}</td>
						<td><a href="/admin/manager/level/match/${m.idMatch}/item">View question</a></td>
						<td><a href="">Edit</a></td>
						<td><a href="">Delete</a></td>
						</tr>
					</c:forEach>

				</table>

			</div>


		</c:if>


	</c:if>

</body>
</html>