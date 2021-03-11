<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="ejbholidaybookingapp.DepartmentDTO"%>
<%@ page import="ejbholidaybookingapp.RoleDTO"%>
<%@ page import="ejbholidaybookingapp.EmployeeDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Employee</title>
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
                    <li><a
						href="/HolidayBookingAppWebApplication/EmployeesServlet">Back</a></li>
                    
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
	<div class="row">
		<div class="col-md-6">
			<h3>Employee edit</h3>
		</div>
	</div>
	<form method="POST" action="EditEmployeeServlet">
		<%
			EmployeeDTO editEmployee = (EmployeeDTO) request.getAttribute("editEmployee");
		%>
		<input type="hidden" name="id" value=<%=editEmployee.getId()%>>
		<div class="form-group">
			<label>First name</label> <input class="form-control"
				name="firstName" value=<%=editEmployee.getFirstName()%>
				placeholder="First name" />
		</div>
		<div class="form-group">
			<label>Last name</label> <input class="form-control" name="lastName"
				value=<%=editEmployee.getLastName()%> placeholder="Last name" />
		</div>
		<div class="form-group">
			<label>Department</label> 
			<select class="form-control"
				name="departmentName">
				<%
					List<DepartmentDTO> allDepartments = (List) request.getAttribute("allDepartments");
					for (DepartmentDTO e : allDepartments) {
				%>
				<option class="form-control"
					<%if (e.getDepName().equalsIgnoreCase(editEmployee.getDepName()))
					out.print(" selected");%>>
					<%=e.getDepName()%>
					<%
						}
					%>
				</option>
			</select>
		</div>
		<div class="form-group">
			<label>Role</label> <select class="form-control" name="roleName">
				<%
					List<RoleDTO> allRoles = (List) request.getAttribute("allRoles");
					for (RoleDTO e : allRoles) {
				%>
				<option class="form-control"
					<%if (e.getRoleName().equalsIgnoreCase(editEmployee.getRoleName()))
					out.print(" selected");%>>
					<%=e.getRoleName()%>
					<%
						}
					%>
				</option>
			</select>
		</div>
		<div>
			<label>Email</label> <input type="email" class="form-control"
				value=<%=editEmployee.getEmail()%> name="email" placeholder="email"
				required>
		</div>
		<div class="form-group">
			<label>Phone number</label> <input class="form-control"
				value=<%=editEmployee.getPhoneNumber()%> name="phoneNumber"
				placeholder="Phone number" />
		</div>
		<div class="form-group">
			<label>Home address</label> <input class="form-control"
				value=<%=editEmployee.getHomeAddress()%> name="homeAddress"
				placeholder="Home address" />
		</div>
		<div class="form-group">
			<label>Hire date</label> <input class="form-control" name="hireDate"
				value=<%=editEmployee.getHireDateAsString()%>
				placeholder="Hire date" />
		</div>
		<div class="form-group">
			<label>Salary</label> <input class="form-control" name="salary"
				value=<%=editEmployee.getSalary()%> placeholder="Salary" />
		</div>
		<div class="form-group">
			<label>Password</label> <input class="form-control" name="password"
				value=<%=editEmployee.getPassword()%> placeholder="password" />
		</div>
		<button type="submit" class="btn-lg btn-primary btn-block">Submit</button>
		
	</form>
	 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
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
	$('.navTrigger').click(function () {
    $(this).toggleClass('active');
    console.log("Clicked menu");
    $("#mainListDiv").toggleClass("show_list");
    $("#mainListDiv").fadeIn();

});

    </script>
</body>
</html>