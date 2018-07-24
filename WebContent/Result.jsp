<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="controller.Controller"%>
<%@ page import="model.Order"%>
<%@ page import="java.util.ArrayList"%>

<%
	if ((request.getAttribute("LinkstoPayOptions") == null)) {
		System.out.println("Forwarding to error because null  ");
		out.println("<script type=\"text/javascript\">");
		out.println("alert(' Please login first with the right credentials !!');");
		out.println("</script>");
		out.println("<script>");
		out.println(" var timer = setTimeout(function() {");
		out.println("window.location='Order.jsp'");
		out.print("window.location='Order.jsp'},100)");
		out.println("</script>");
		return;
	} else {

	}
%>




<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Result</title>
<meta charset="utf-8">

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<style>
/* .btn-primary {
	color: #fff;
	background-color: #337ab7;
	border-color: #2e6da4;
	margin-left: 50px;
} */
.container {
	width: 600px;
}
</style>
<body>

	<%
		System.out.println("about to see order object"); // Debug information 

		Order jspOrderObject = (Order) request.getAttribute("LinkstoPayOptions"); // Receiving a passed object to jsp page 

		System.out.println(jspOrderObject.getMerchantEmail());

		ArrayList<Object> payoptionsFromOrderClass = jspOrderObject.getPayoptions();
		ArrayList<Object> payoptionsNamesFromOrderClass = jspOrderObject.getPayoptionsNames();
		
		String MerchantEmailforInvoice = jspOrderObject.getMerchantEmail();
		String OrderName = jspOrderObject.getOrderName();
		int costForInvoice = jspOrderObject.getCost();
		int quantityForInvoice = jspOrderObject.getQuantity();
		String productForInvoice = jspOrderObject.getItems();

		request.setAttribute("OrderObject", jspOrderObject);
	%>

	<section class="content content_content"
		style="width: 70%; margin: auto;"> <section class="invoice">
	<!-- title row -->
	<div class="row">
		<div class="col-xs-12">
			<h2 class="page-header">
				<i class="fa fa-globe"></i>  Dream Oval Mentor Programme Practical  Aptitude Test <small
					class="pull-right">Date: 2017/01/09</small>
			</h2>
		</div>
		<!-- /.col -->
	</div>
	<!-- info row -->
	<div class="row invoice-info">
		<div class="col-sm-4 invoice-col">
			From
			<address>
				<strong> </strong>
			</address>
		</div>
		<!-- /.col -->
		<div class="col-sm-4 invoice-col">
			To
			<address>
				<%= MerchantEmailforInvoice %> 
			</address>
		</div>
		<!-- /.col -->
		<div class="col-sm-4 invoice-col">
			<b>Invoice #007612</b><br> <br> <b>Order ID:</b> 4F3S8J<br>
			<b>Payment Due:</b> 2/22/2014<br> <b>Account:</b> 968-34567
		</div>
		<!-- /.col -->
	</div>
	<!-- /.row --> <!-- Table row -->
	<div class="row">
		<div class="col-xs-12 table-responsive">
			<table class="table table-striped">
				<thead>
					<tr>
						<th> Quantity  </th>
						<th> Product</th>
						<th> Cost</th>
						<th>Sub Total</th>
					</tr>
				</thead>
				<tbody>


					<tr>
						<td><%= quantityForInvoice %></td>
						<td><%= productForInvoice %></td>
						<td> <%= costForInvoice %></td>
						<td> <%= costForInvoice * quantityForInvoice %></td>
					</tr>
				</tbody>
			</table>
		</div>
		<!-- /.col -->
	</div>
	<!-- /.row -->

	<div class="row">
		<!-- accepted payments column -->
		<div class="col-md-12">
			<p class="lead">Amount Due 2/22/2014</p>
			<div class="table-responsive">
				<table class="table">
					<tbody>


						<tr>
							<th>Total:</th>
							<td> Ghc <%= costForInvoice * quantityForInvoice %></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<!-- /.col -->
	</div>
	<!-- /.row --> </section> </section>

	<div class="container">
		<h2 style="color: Green;">Select Your Payment Option</h2>

		<div class="panel-group">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" href="#collapse1"> Your Payment
							Options</a>
					</h4>
				</div>
				<div id="collapse1" class="panel-collapse collapse">
					<ul class="list-group">

						<%
							for (Integer i = 0; i < payoptionsFromOrderClass.size(); i++) {

								out.println("<li class='list-group-item'>");

								out.println("<form action ='Controller' method='post'>");

								String name = (String) payoptionsNamesFromOrderClass.get(i);

								out.println("<img src='" + payoptionsFromOrderClass.get(i) + "'+ alt=\"'"
										+ payoptionsNamesFromOrderClass.get(i) + "'\"+/>");
								out.println("<input type='hidden' name='action' value='PayOption'> ");
								out.println("<input class='btn btn-primary' type='submit' value='Go'>");

								out.println("<input type='hidden' name='PayOptionSlctd' value='" + payoptionsNamesFromOrderClass.get(i)
										+ "'> ");
								out.println("</form>");

								out.println("</li>");

								out.println("<br>");

							}
						%>


					</ul>
				</div>
			</div>
		</div>
	</div>

</body>
</html>