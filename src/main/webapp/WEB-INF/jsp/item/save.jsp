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
		<div id="save-form">
			<form:form action="/admin/manager/level/match/item/save"  method="post" commandName="item">
				<fieldset>
					<input type="hidden" name="idmatch"  value="${id}"/>
					<form:hidden path="id"/>
					<table>
						<tr>
							<td><label for="question">Question</label></td>
							<td><form:input path="question" /></td>
							<td><form:errors path="question"></form:errors></td>
						</tr>

						<tr>
							<td><label for="suggest">Suggest</label></td>
							<td><form:input path="suggest" /></td>
							<td><form:errors path="suggest"></form:errors></td>
						</tr>

						<tr>
							<td><label for="answer">Answer</label></td>
							<td><form:input path="answer" /></td>
							<td><form:errors path="answer"></form:errors></td>
							
						</tr>
						<tr >
							<td colspan="2" ><input type="reset" value="Reset" />&nbsp;&nbsp;&nbsp;<input
								type="submit" value="Save" /></td>

						</tr>
					</table>

				</fieldset>
			</form:form>

		</div>





</body>
</html>