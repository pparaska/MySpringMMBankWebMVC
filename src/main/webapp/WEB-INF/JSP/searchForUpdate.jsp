<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<h1>Search Account For Update</h1>
</head>
<form action="updateForm">
	<body>
		<label>Account Number : <input type="number"
			name="accountNumber"></label>
		<br>
		<br>
		<label><input type="submit" name="submit" value="Submit"></label>
	</body>
	<div>
		<jsp:include page="home.jsp"></jsp:include>
	</div>
</form>
</html>