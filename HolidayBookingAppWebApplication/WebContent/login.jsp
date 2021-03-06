<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Holiday Booking Application</title>
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/css.css">
</head>
<body>
<section class="home">
<nav class="nav">
        <div class="container">
          <div class="logo">
                <a href="#">Holiday Booking Application</a>
            </div>
            
	<%
		String errMsg = (String) request.getAttribute("errorLoginMessage");
	%>
	<%
		if (errMsg != null) {
			out.print("<h5>" + errMsg + "</h5>");
		}
	%>
	<%
		String isLogin = (String) session.getAttribute("userName");
		if (isLogin != null) {
			response.sendRedirect("EmployeesServlet");
		}
	%>
	<div class="col-sm-3 first_cont">
		<form class="form-signin" method="POST"
			action="HolidayBookingAppServlet">
			<input type="email" class="form-control" name="email"
				placeholder="Email address" required>
			<p></p>
			<input type="password" class="form-control" name="password"
				placeholder="Password" required>
			<p></p>
			<button class="btn-lg btn-primary btn-block" type="submit"
				value="Login">Sign in</button>
		</form>
	</div>
	</div>
</nav>
</section>	
<!-- Jquery needed -->
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
		

    </script>
</body>
</html>