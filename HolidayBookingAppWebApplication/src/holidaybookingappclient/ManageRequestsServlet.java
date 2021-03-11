package holidaybookingappclient;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ejbholidaybookingapp.HolidayBookingAppBeanRemote;
import ejbholidaybookingapp.RequestDTO;

/**
 * Servlet implementation class ManageRequestsServlet
 */
@WebServlet("/ManageRequestsServlet")
public class ManageRequestsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private HolidayBookingAppBeanRemote holidayBookingAppBean;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageRequestsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String userName = (String) session.getAttribute("userName");
		if (request.getParameter("approveRequestId") != null) {
			int requestId = Integer.parseInt(request.getParameter("approveRequestId"));
			holidayBookingAppBean.approveRequest(requestId);
		} else if (request.getParameter("rejectRequestId") != null) {
			int requestId = Integer.parseInt(request.getParameter("rejectRequestId"));
			String rejectReason = request.getParameter("rejectReason");
			holidayBookingAppBean.rejectRequest(requestId, rejectReason);
		}
		
		List<RequestDTO> requestsByDepName = holidayBookingAppBean.getRequestsByDepName(userName);
		request.setAttribute("requestsByDepName", requestsByDepName);	
		request.getRequestDispatcher("/managerequests.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
