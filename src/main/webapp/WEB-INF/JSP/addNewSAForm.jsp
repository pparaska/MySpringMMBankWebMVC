<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="createAccount">
		<center>
			<label>Enter Account Holder Name: </label> <input type="text"
				name="txtAccHN" /> <br /> <label>Enter Account Balance: </label> <input
				type="number" name="txtBalance" /> <br /> <label>Salaried?:
			</label> <input type="radio" name="rdSalary" value="Yes" /> Yes <input
				type="radio" name="rdSalary" value="No" /> No <br /> <input
				type="submit" value="Submit" /> <input type="reset" value="Clear" />
		</center>
	</form>
	<div>
		<jsp:include page="home.jsp"></jsp:include>
	</div>
</body>
</html>










