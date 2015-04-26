<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


	<form  action="/login" method="post">
		<div>
			<label for="email">Email address</label> <input type="email"
				name="email" id="email" required autofocus />
		</div>
		<div>
			<label for="password">Password</label> <input type="password"
				name="password" id="password" required />
		</div>
		<div>
			<label for="remember-me">Remember me</label> <input type="checkbox"
				name="remember-me" id="remember-me" />
		</div>
		<button type="submit">Sign in</button>
	</form>
</body>
</html>