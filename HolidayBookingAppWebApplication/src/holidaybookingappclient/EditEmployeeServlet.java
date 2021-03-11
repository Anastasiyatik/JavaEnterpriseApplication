package holidaybookingappclient;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejbholidaybookingapp.EmployeeDTO;
import ejbholidaybookingapp.HolidayBookingAppBeanRemote;

/**
 * Servlet implementation class EditEmployeeServlet
 */
@WebServlet("/EditEmployeeServlet")
public class EditEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditEmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    private Date parseDate(String dt) {
		try {
			return new SimpleDateFormat("dd/MM/yyyy").parse(dt);
		} catch (Exception e) {
			return null;
		}
	}
    
    @EJB
	private HolidayBookingAppBeanRemote holidayBookingAppBean;
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		EmployeeDTO employeeDTO = holidayBookingAppBean.getEmployeeById(id);

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String phoneNumber = request.getParameter("phoneNumber");
		String homeAddress = request.getParameter("homeAddress");
		Date hireDate = parseDate(request.getParameter("hireDate"));
		Integer salary = Integer.parseInt(request.getParameter("salary"));
		String password = request.getParameter("password");

		String depName = request.getParameter("departmentName");
		String roleName = request.getParameter("roleName");

		employeeDTO.setId(id);
		employeeDTO.setFirstName(firstName);
		employeeDTO.setLastName(lastName);
		employeeDTO.setEmail(email);
		employeeDTO.setPhoneNumber(phoneNumber);
		employeeDTO.setHomeAddress(homeAddress);
		employeeDTO.setHireDate(hireDate);
		employeeDTO.setSalary(salary);
		employeeDTO.setPassword(password);
		employeeDTO.setDepName(depName);
		employeeDTO.setRoleName(roleName);

		holidayBookingAppBean.editEmployee(employeeDTO);
		response.sendRedirect("EmployeesServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
