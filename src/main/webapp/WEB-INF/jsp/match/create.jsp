<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Page</title>
</head>
<body>
	<div id="matchLevel">
		<form:form action="/admin/match/add" method="post" commandName="match">

			<table>
				<tr>
					<td>Title</td>
					<td><form:input path="title" /></td>
					<td><form:errors path="t" cssStyle="color: #ff0000;" /></td>
				</tr>
				<tr>
					<td>Level</td>
					<td><form:select path="level" id="level">
							<option value="0">NONE</option>
							<c:forEach items="${levels}" var="level">
								<option value="${level.idLevel}">${level.name}</option>
							</c:forEach>

						</form:select></td>

				</tr>

				<tr>
					<td>Competition</td>
					<td><form:select path="competition" id="competition">
							<option value="0">NONE</option>
							<c:forEach items="${competitions}" var="competition">
								<option value="${competition.idCompetition}">${competition.name}+${competition.begin}</option>

							</c:forEach>

						</form:select></td>
				</tr>

				<tr>
					<td></td>
					<td><input type="submit" value="Create" /></td>
				</tr>
			</table>



		</form:form>

	</div>

	<div>
		<h3>List</h3>
		<c:if test="">
			<table>
				<tr>
					<th>ID</th>
					<th>Title</th>
					<th>Level</th>
					<th>Competition</th>
				</tr>

				<c:forEach items="matchs" var="match">
					<tr>
					</tr>


				</c:forEach>

			</table>




		</c:if>
	</div>
</body>
</html>