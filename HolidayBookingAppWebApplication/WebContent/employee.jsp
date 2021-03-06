<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="ejbholidaybookingapp.EmployeeDTO"%>
<!DOCTYPE html>
<html>
<head>
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
			<p class="text-center">Employees</p>
		</h3>

		<form method="POST" action="NewEmployeeServlet">
			<button class="btn-lg btn-primary btn-block" name="addNewEmployee"
				type="submit" value="New_employee">Add New Employee</button>
		</form>
		<div class="table-responsive">
			<table class="table table-bordered table-striper table-hover">
				<tr>
					<th>First name</th>
					<th>Last name</th>
					<th>Email</th>
					<th>Department</th>
					<th>Role</th>
					<th class="text-center" colspan="3">Actions</th>
				</tr>
				<%
					List<EmployeeDTO> allEmployees = (List) request.getAttribute("allEmployees");
					int cnt = 0;
					for (EmployeeDTO e : allEmployees) {
				%>
				<tr>
					<td><%=e.getFirstName()%></td>
					<td><%=e.getLastName()%></td>
					<td><%=e.getEmail()%></td>
					<td><%=e.getDepName()%></td>
					<td><%=e.getRoleName()%></td>
					<td>
						<form method="POST" action="EmployeesServlet">
							<button class="btn-lg btn-primary btn-block"
								name="editEmployeeId" value=<%=e.getId()%> type="submit">Edit</button>
						</form>
					</td>
					<td>
						<form method="POST" action="DeleteEmployeeServlet">
							<button class="btn-lg btn-primary btn-block"
								name="deleteEmployeeId" value=<%=e.getId()%> type="submit">Delete</button>
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