<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="com.digit.javaTraining.mvcApp.model.*,  java.util.ArrayList"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="ISO-8859-1">

<title>Insert title here</title>

<style type="text/css">
table {
	border-collapse: collapse;
	width: 100%;
}

th, td {
	border: 1px solid black;
	padding: 8px;
	text-align: left;
	padding: 8px;
}

tr.green {
	background-color: #c8e6c9;
}

tr.red {
	background-color: #ffcdd2;
}
</style>

</head>

<body>



	<%
	session = request.getSession();

	if (session == null) {

		response.sendRedirect("/BankApplication/Welcome.html");

		return;

	}

	BankApp curBankUser = (BankApp) session.getAttribute("bankapp");

	if (curBankUser == null) {

		response.sendRedirect("/BankApplication/Welcome.html");

		return;

	}

	String isListGenerated = (String) session.getAttribute("isListGenerated");

	if (isListGenerated == null) {

		response.sendRedirect("/BankApplication/TransectionDetail.jsp]");

	} else {

		session.removeAttribute("isListGenerated");

	}

	ArrayList<transferstates> arrayList = (ArrayList<transferstates>) session.getAttribute("ALL_TRANSACTIONS");

	if (arrayList == null) {

		return;

	}
	%>



	<%
	if (arrayList.size() == 0) {
	%>

	<h1 align="center">No Transactions Made Yet!</h1>

	<%
	return;

	}
	%>



	<h1 align="center">All Transactions</h1>



	<table>



		<tr>

			<th>Customer ID</th>

			<th>Sender Bank Name</th>

			<th>Sender Bank IFSC Code</th>

			<th>Sender Account Number</th>

			<th>Receiver IFSC</th>

			<th>Receiver Account Number</th>

			<th>Amount of Transfer</th>

			<th>Transaction ID</th>

		</tr>



		<%
		for (transferstates curTransaction : arrayList) {

			String trTypeClass = curTransaction.getSaccno() == curBankUser.getAccno() ? "red" : "green";
		%>

		<tr class="<%=trTypeClass%>">

			<td><%=curTransaction.getCustid()%></td>

			<td><%=curTransaction.getBank_name()%>
			<td><%=curTransaction.getSifsc()%></td>

			<td><%=curTransaction.getSaccno()%></td>

			<td><%=curTransaction.getRifsc()%></td>

			<td><%=curTransaction.getRaccno()%></td>

			<td><%=curTransaction.getAmount()%></td>

			<td><%=curTransaction.getTransection_id()%></td>

		</tr>



		<%
		}
		%>



	</table>



	</ul>



</body>

</html>