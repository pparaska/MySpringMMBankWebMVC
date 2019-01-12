<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<table>
		<tr>
			<th><a href="sortByAccountNumber">Account Number</a></th>
			<th><a href="sortByAccountHolderName">Holder Name</a></th>
			<th><a href="sortByAccountBalance">Account Balance</a></th>
			<th><a href="sortBySalary">Salary</a></th>
			<th>Over Draft Limit</th>
			<th>Type Of Account</th>
		</tr>

		<jstl:forEach var="account" items="${accounts}">
			<tr>
				<td>${account.bankAccount.accountNumber}</td>
				<td>${account.bankAccount.accountHolderName }</td>
				<td>${account.bankAccount.accountBalance}</td>
				<td>${account.salary}</td>
			</tr>
		</jstl:forEach>


	</table>

	<div>
		<jsp:include page="home.jsp"></jsp:include>
	</div>