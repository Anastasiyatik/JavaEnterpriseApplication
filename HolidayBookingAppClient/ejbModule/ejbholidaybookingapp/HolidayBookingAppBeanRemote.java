package ejbholidaybookingapp;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

@Remote
public interface HolidayBookingAppBeanRemote {
	String login(String email, String password);
	List<EmployeeDTO> getAllEmployees();
	EmployeeDTO getEmployeeById(int employeeId);
	boolean addNewEmployee(EmployeeDTO newEmp);
	List<DepartmentDTO> getAllDepartments();
	List<RoleDTO> getAllRoles();
	boolean editEmployee(EmployeeDTO editEmp);
	boolean deleteEmployee(int employeeId);
	void newRequest(String userName, Date fromDate, Date toDate);
	List<RequestDTO> getAllRequestsByUser(String userName);
	List<RequestDTO> getRequestsByDepName(String userName);
	void approveRequest(int requestId);
	void rejectRequest(int requestId, String rejectReason);
	EmployeeDTO getEmployeeByEmail(String userName);
	List<EmployeeDTO> getApproversByDep(int idDep);
	void addNewRequestAlert(String creator, String addressee, String emailBody);
}
