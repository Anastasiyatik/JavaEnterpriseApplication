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
	<h3>
		<p class="text-center">Manage requests</p>
	</h3>
		<div class="container">
	
	<div class="table-responsive">
		<table class="table table-bordered table-striper table-hover">
			<tr>
				<th>Employee details</th>
				<th>From Date</th>
				<th>To Date</th>
				<th class="text-center">Status</th>
				<th>Validation</th>
				<th class="text-center" colspan="2">Actions</th>
			</tr>
			<%
				List<RequestDTO> allRequestsByDepName = (List) request.getAttribute("requestsByDepName");
				int cnt = 0;
				for (RequestDTO e : allRequestsByDepName) {
			%>
			<tr>
				<td><%=e.getEmpDetails()%></td>
				<td><%=e.getFromDate()%></td>
				<td><%=e.getToDate()%></td>
				<td><%=e.getReqStatusName()%></td>
				<td style="white-space: pre-wrap; word-wrap: break-word"><%=e.getAnalysis()%></td>
				<td>

					<form method="POST" action="ManageRequestsServlet">
						<button class="btn-lg btn-primary btn-block"
							<%-- 			<%if (e.getAnalysis() != null && !e.getAnalysis().trim().isEmpty())
					out.print("disabled");%> --%>
							name="approveRequestId"
							value=<%=e.getIdReq()%> type="submit">Approve</button>
					</form>
				</td>
				<td>
					<form method="POST" action="ManageRequestsServlet">
						<input class="form-control" name="rejectReason"
							placeholder="reason" />
						<button class="btn-lg btn-primary btn-block"
							name="rejectRequestId" value=<%=e.getIdReq()%> type="submit">Reject</button>
					</form>
				</td>
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