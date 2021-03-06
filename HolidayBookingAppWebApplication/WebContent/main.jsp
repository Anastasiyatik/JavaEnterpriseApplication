<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Main</title>
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/css.css">
</head>

<body>

<section class="home">
<nav class="nav">
        <div class="container">
            <div id="mainListDiv" class="main_list content">
                <ul>
    			<%
				int isAdmin = Integer.parseInt((String) session.getAttribute("isAdmin"));
				if (isAdmin == 1) {
					out.print(
							"<li><a href=\"/HolidayBookingAppWebApplication/EmployeesServlet\">Manage employees</a></li><li><a href=\"/HolidayBookingAppWebApplication/ManageRequestsServlet\">Manage holiday requests</a></li>");
				}
			%>
			<li><a 
				href="/HolidayBookingAppWebApplication/RequestsServlet">My requests/Submit new</a></li>
			
		</ul>
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
		
}		
    </script>
    </body>
</html>