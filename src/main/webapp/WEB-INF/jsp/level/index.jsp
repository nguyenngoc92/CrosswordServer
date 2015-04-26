<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Level manager</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){

		$("#add").click(function(){
			$.post("/admin/manager/level/add",
		
					)

			})

		})


</script>
	
</head>
<body>


	<h3>List of Level</h3>
	
	<div >
	
	
	</div>
	<button id="add">Add level</button>
	<c:if test="${not empty levelList}">
		<div id="table_level">
			<table id="">
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Match</th>
				</tr>
				<c:forEach items="${levelList}" var="l">
					<tr>
						<td>${l.idLevel}</td>
						<td>${l.name}</td>
						<td><a href="/admin/manager/level/${l.idLevel}/match">View
								Match</a></td>
					</tr>

				</c:forEach>


			</table>

		</div>
	</c:if>
</body>
</html>