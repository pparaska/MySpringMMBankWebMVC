<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<center>
		<h1>Fund Transfer</h1>
		<form action="transferFunds">
			<table>
				<tr>
					<td>Sender's Account Number:</td>
					<td><input type="text" name="sendersAccId"></td>
				</tr>

				<tr>
					<td>Receiver's Account Number:</td>
					<td><input type="text" name="receiversAccId"></td>
				</tr>

				<tr>
					<td>Amount:</td>
					<td><input type="text" name="amount"></td>
				</tr>

				<tr>
					<td><input type="submit" name="submit" value="Submit"></td>
				</tr>
				</form>
			</table>
	</center>
	<div>
		<jsp:include page="home.jsp"></jsp:include>
	</div>
</body>
</html>