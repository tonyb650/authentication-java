<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %><!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Login and Registration</title>
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>
	<div class="container">
		<h1>Welcome!</h1>
		<h4>Join our growing community.</h4>
		<div class="row">
			<div class="col mx-5">
				<h2>Register</h2>
				<form:form action="/register" modelAttribute="newUser" method="post">
					<form:label path="userName" for="userName" class="form-label">User Name</form:label>
					<form:errors path="userName" class="text-danger"/>
					<form:input path="userName" type="text" id="userName" class="form-control" />
					<form:label path="email" for="email" class="form-label">Email</form:label>
					<form:errors path="email" class="text-danger"/>
					<form:input path="email" type="text" id="email"  class="form-control"/>
					<form:label path="password" for="password" class="form-label">Password</form:label>
					<form:errors path="password" class="text-danger"/>
					<form:input path="password" type="text" id="password" class="form-control" />
					<form:label path="confirm" for="confirm" class="form-label">Confirm PW</form:label>
					<form:errors path="confirm" class="text-danger"/>
					<form:input path="confirm" type="text" id="confirm"  class="form-control"/>
					<input type="submit" value="submit" class="btn btn-secondary"/>
				</form:form>
			</div>
			<div class="col mx-5">
				<h2>Log in</h2>
				<form:form action="/login" modelAttribute="newLogin" method="post">
					<form:label path="email" for="email" class="form-label">Email</form:label>
					<form:errors path="email" class="text-danger"/>
					<form:input path="email" type="text" id="email"  class="form-control"/>
					<form:label path="password" for="password" class="form-label">Password</form:label>
					<form:errors path="password" class="text-danger"/>
					<form:input path="password" type="text" id="password"  class="form-control"/>
					<input type="submit" value="submit" class="btn btn-secondary"/>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>