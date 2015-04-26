<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Item manager</title>
</head>

<body>

	<c:if test="${not empty match}">
		<h3>ID : ${match.idMatch}&nbsp;&nbsp;&nbsp;&nbsp;Title :
			${match.title}</h3>
		<div id="">
			<a href="/admin/manager/level/match/${match.idMatch}/item/save">Add question</a>
		</div>
		<div id="">
			<c:if test="${not empty itemList}">
				<table id="">
					<tr>
						<th>ID</th>
						<th>Question</th>
						<th>Suggest</th>
						<th>Answer</th>
						<th>Count</th>
						<th>Edit</th>
						<th>Delete</th>

					</tr>
					<c:forEach items="${itemList}" var="item">
						<tr>
							<td>${item.idItem}</td>
							<td>${item.question}</td>
							<td>${item.suggest}</td>
							<td>${item.answer}</td>
							<td>${item.count}</td>
							<td><a href="">Edit</a></td>
							<td><a href="">Delete</a></td>

						</tr>

					</c:forEach>
				</table>

			</c:if>

		</div>
	</c:if>
</body>
</html>