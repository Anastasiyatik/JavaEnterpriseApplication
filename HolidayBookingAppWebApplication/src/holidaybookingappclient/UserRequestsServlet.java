package holidaybookingappclient;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ejbholidaybookingapp.DepartmentDTO;
import ejbholidaybookingapp.EmployeeDTO;
import ejbholidaybookingapp.HolidayBookingAppBeanRemote;
import ejbholidaybookingapp.RequestDTO;
import ejbholidaybookingapp.RoleDTO;

/**
 * Servlet implementation class RequestsServlet
 */
@WebServlet("/RequestsServlet")
public class UserRequestsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	@EJB
	private HolidayBookingAppBeanRemote holidayBookingAppBean;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRequestsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @throws ParseException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	public Date parseStringToDate(String dt) {
		try {
			return new SimpleDateFormat("dd/MM/yyyy").parse(dt);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String userName = (String) session.getAttribute("userName");
		if (request.getParameter("newRequest") != null) {
			Date fromDate = parseStringToDate((String) request.getParameter("fromDate"));
			Date toDate = parseStringToDate((String) request.getParameter("toDate"));
			holidayBookingAppBean.newRequest(userName, fromDate, toDate);
		}
		
		List<RequestDTO> allRequestsByUser = holidayBookingAppBean.getAllRequestsByUser(userName);
		request.setAttribute("allRequestsByUser", allRequestsByUser);
		request.getRequestDispatcher("/request.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
