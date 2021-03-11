package holidaybookingappclient;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejbholidaybookingapp.DepartmentDTO;
import ejbholidaybookingapp.EmployeeDTO;
import ejbholidaybookingapp.HolidayBookingAppBeanRemote;
import ejbholidaybookingapp.RoleDTO;

/**
 * Servlet implementation class NewEmployeeServlet
 */
@WebServlet("/NewEmployeeServlet")
public class NewEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewEmployeeServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	private Date parseDate(String dt) {
		try {
			return new SimpleDateFormat("dd/MM/yyyy").parse(dt);
		} catch (Exception e) {
			return null;
		}
	}

	@EJB
	private HolidayBookingAppBeanRemote holidayBookingAppBean;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("addNewEmployee") != null) {
			List<DepartmentDTO> allDepartments = holidayBookingAppBean.getAllDepartments();
			request.setAttribute("allDepartments", allDepartments);			
			List<RoleDTO> allRoles = holidayBookingAppBean.getAllRoles();
			request.setAttribute("allRoles", allRoles);
			request.getRequestDispatcher("/newemployee.jsp").forward(request, response);
		} else {
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");
			String phoneNumber = request.getParameter("phoneNumber");
			String homeAddress = request.getParameter("homeAddress");
			Date hireDate = parseDate(request.getParameter("hireDate"));
			Integer salary = Integer.parseInt(request.getParameter("salary"));
			String password = request.getParameter("password");
			String departmentName = request.getParameter("departmentName");
			String roleName = request.getParameter("roleName");
			
			EmployeeDTO newEmp = new EmployeeDTO(0, null, departmentName, email, password, firstName, lastName, phoneNumber,
					hireDate, salary, homeAddress, null, roleName);

			holidayBookingAppBean.addNewEmployee(newEmp);
			response.sendRedirect("EmployeesServlet");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
