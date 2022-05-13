<%@page import="com.Payment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment </title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/payment.js"></script>
</head>

<body>

<nav class="navbar navbar-dark bg-dark">




<a class="navbar-brand" href="#">ElectroGrid</a>








</nav><br>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Payment </h1>

				<form id="formPayment" name="formPayment" method="post" action="Payment.jsp">


					Card Type: <input id="cardName" name="cardName" type="text"
						class="form-control form-control-sm"> 
						
						<br>Card Number: <input id="cardNo" name="cardNo" type="text"
						class="form-control form-control-sm"> 
						
						<br> Expire Date: <input id="expDate" name="expDate" type="text"
						class="form-control form-control-sm"> 
						
						<br> CVV Number: <input id="cvv" name="cvv" type="text"
						class="form-control form-control-sm"> 
						
						<br> Customer ID: <input id="cusID" name="cusID" type="text"
						class="form-control form-control-sm"> 
						
						<br> <input
						id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary"> <input type="hidden"
						id="hidPaymentIDSave" name="hidPaymentIDSave" value="">
				</form>

				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>

				<br>
				<div id="divPaymentGrid">
					<%
						Payment paymentObj = new Payment();
						out.print(paymentObj.readPayment());
					%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
