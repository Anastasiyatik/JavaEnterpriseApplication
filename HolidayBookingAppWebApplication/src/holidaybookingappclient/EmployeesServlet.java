package holidaybookingappclient;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
 * Servlet implementation class Employees
 */
@WebServlet("/EmployeesServlet")
public class EmployeesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeesServlet() {
		super();
	}

	@EJB
	private HolidayBookingAppBeanRemote holidayBookingAppBean;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("editEmployeeId") != null) {
			int employeeId = Integer.parseInt(request.getParameter("editEmployeeId"));
			EmployeeDTO employee = holidayBookingAppBean.getEmployeeById(employeeId);
			request.setAttribute("editEmployee", employee);
			List<DepartmentDTO> allDepartments = holidayBookingAppBean.getAllDepartments();
			request.setAttribute("allDepartments", allDepartments);
			List<RoleDTO> allRoles = holidayBookingAppBean.getAllRoles();
			request.setAttribute("allRoles", allRoles);
			request.getRequestDispatcher("/editemployee.jsp").forward(request, response);
		} else {
			List<EmployeeDTO> allEmployees = holidayBookingAppBean.getAllEmployees();
			request.setAttribute("allEmployees", allEmployees);
			request.getRequestDispatcher("/employee.jsp").forward(request, response);
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
