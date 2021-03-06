<%@ page import="java.util.List"%>
<%@ page import="ejbholidaybookingapp.RequestDTO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/css.css">
</head>
<body>
	<section class="employee">
		<nav class="nav">
			<div class="container">
				<div class="logo">
					<a href="#">Holiday booking</a>
				</div>
				<div id="mainListDiv" class="main_list">
					<ul class="navlinks">
						<li><a href="/HolidayBookingAppWebApplication/main.jsp">Back</a></li>
					</ul>
				</div>
			</div>
		</nav>
	</section>

	<%
		String isLogin = (String) session.getAttribute("userName");
		if (isLogin == null) {
			response.sendRedirect("HolidayBookingAppServlet");
		}
	%>
	<div class="container">
		<h3>
			<p class="text-center">My requests/Submit new</p>
		</h3>
				<form method="POST" action="RequestsServlet">
				<div class="form-row">
			    <div class="form-group col-md-6">
				      <input class="form-control" type="date" name="fromDate" placeholder="From">
				    </div>
			    <div class="form-group col-md-6">
				      <input class="form-control" type="date" name="toDate" placeholder="To">
				    </div>
				  </div>
				<button type="submit" class="btn-lg btn-primary btn-block" name="newRequest">submit</button>
				</form>
		<div class="table-responsive">
			<table class="table-striped table-bordered table">
				<tr>
					<th>From Date</th>
					<th>To Date</th>
					<th class="text-center">Status</th>
					<th>Description</th>
				</tr>
				<%
					List<RequestDTO> allRequestsByUser = (List) request.getAttribute("allRequestsByUser");
					int cnt = 0;
					for (RequestDTO e : allRequestsByUser) {
				%>
				<tr>
					<td><%=e.getFromDate()%></td>
					<td><%=e.getToDate()%></td>
					<td><%=e.getReqStatusName()%></td>
					<td><%=e.getDescr()%></td>
				</tr>
				<%
					}
				%>
			</table>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script src="js/scripts.js"></script>

	<!-- Function used to shrink nav bar removing paddings and adding black background -->
	<script>
		$(window).scroll(function() {
			if ($(document).scrollTop() > 50) {
				$('.nav').addClass('affix');
				console.log("OK");
			} else {
				$('.nav').removeClass('affix');
			}
		});
		$('.navTrigger').click(function() {
			$(this).toggleClass('active');
			console.log("Clicked menu");
			$("#mainListDiv").toggleClass("show_list");
			$("#mainListDiv").fadeIn();

		});
	</script>
</body>
</html>