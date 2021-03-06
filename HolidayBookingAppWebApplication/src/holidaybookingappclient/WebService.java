package holidaybookingappclient;

import org.json.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.ws.rs.*;

import ejbholidaybookingapp.EmployeeDTO;
import ejbholidaybookingapp.HolidayBookingAppBeanRemote;
import model.TEmployee;

@Path("/")
@Produces({ "application/json" })
@Consumes({ "application/json" })
@Stateless
public class WebService {
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

	@EJB
	private HolidayBookingAppBeanRemote holidayBookingAppBean;

	@POST
	@Path("/login")
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	public String login(String request) {
		String invalidLogonResp = "Invalid login/password! Please try again.";
		try {
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(request);
			String email = (String) jsonObject.get("email");
			String password = (String) jsonObject.get("password");
			String result = holidayBookingAppBean.login(email, password);
			String res = result == null ? invalidLogonResp : result;
			return "{\"result\":\"" + res + "\"}";
		} catch (Exception e) {
			e.printStackTrace();
			return invalidLogonResp;
		}
	}

	@POST
	@Path("/submitnewrequest")
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	public String submitNewRequest(String request) {
		try {
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(request);
			String userName = (String) jsonObject.get("userName");
			String fromDate = (String) jsonObject.get("fromDate");
			Date fromDateDt = simpleDateFormat.parse(fromDate);
			String toDate = (String) jsonObject.get("toDate");
			Date toDateDt = simpleDateFormat.parse(toDate);
			holidayBookingAppBean.newRequest(userName, fromDateDt, toDateDt);
			putMessageInQueue(userName, fromDate, toDate);
			return "{\"result\":\"Request successfully submitted\"}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"result\":\"Request failed\"}";
		}
	}

	private void putMessageInQueue(String userName, String fromDate, String toDate) {
		try {
			System.out.println("putMessageToQueue: START");
			Context jndiContext = new InitialContext();
			ConnectionFactory factory = (ConnectionFactory) jndiContext.lookup("java:/ConnectionFactory");
			Queue requestQueue = (Queue) jndiContext.lookup("java:/jms/HolidayRequest");

			Connection connect = factory.createConnection();
			Session session = connect.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer sender = session.createProducer(requestQueue);

			MapMessage message = session.createMapMessage();

			EmployeeDTO emp = holidayBookingAppBean.getEmployeeByEmail(userName);
			List<EmployeeDTO> allApprovers = holidayBookingAppBean.getApproversByDep(emp.getDepId());
			String addresseeEmails = allApprovers.stream().map(a -> a.getEmail()).collect(Collectors.joining(","));
			String emailBody = "New holiday request has been submitted" + "\r\n" + "		Request details: creator - "
					+ userName + ", from date - " + fromDate + ", to date - " + toDate;

			message.setString("addresseeEmails", addresseeEmails);
			message.setString("emailBody", emailBody);

			System.out.println("Sending message");
			sender.send(message);

			holidayBookingAppBean.addNewRequestAlert(userName, addresseeEmails, emailBody);

			connect.close();
			System.out.println("putMessageToQueue: END");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
