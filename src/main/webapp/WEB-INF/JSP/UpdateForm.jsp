<%@ page isELIgnored="false" language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="updateResult">
		<center>
			<h1>UPDATE AN ACCOUNT</h1>
			<label>Account Number : <br>
			<input type="text" name="accountNumber" readonly="readonly"
				value="${account.bankAccount.accountNumber}"></label><br>
			<br>
			</label> <label>Name :<br>
			<input type="text" name="accountHolderName"
				value="${account.bankAccount.accountHolderName}" /></label><br>
			<br> <label>AccountBalance :<br>
			<input type="text" name="accountBalance" readonly="readonly"
				value="${account.bankAccount.accountBalance}"></label><br>
			<br> <label>Salaried :</label> <label><input
				type="radio" name="salary" value="yes"
				${account.salary==true?"checked":""}>YES</label> <label><input
				type="radio" name="salary" value="no"
				${account.salary==true?"":"checked"}>NO</label><br>
			<br> <label><input type="submit" name="submit"
				value="Submit"></label> <label><input type="reset"
				name="reset" value="Reset"></label>
	</form>
</body>
</html>