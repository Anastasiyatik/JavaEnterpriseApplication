package ejbholidaybookingapp;

import java.io.Serializable;
import java.util.Date;

public class RequestDTO implements Serializable, Comparable<RequestDTO> {
	private static final long serialVersionUID = 1L;

	private Integer idReq;
	private Date fromDate;
	private Date toDate;
	private Integer reqStatId;
	private String reqStatusName;
	private String descr;
	private String analysis;
	private Integer sortIdx;
	private String empDetails;

	public RequestDTO(Integer idReq, Date fromDate, Date toDate, Integer reqStatId, String reqStatusName, String descr, String analysis, Integer sortIdx, String empDetails) {
		this.idReq = idReq;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.reqStatId = reqStatId;
		this.reqStatusName = reqStatusName;
		this.descr = descr;
		this.analysis = analysis;
		this.sortIdx = sortIdx;
		this.empDetails = empDetails;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public Integer getIdReq() {
		return idReq;
	}

	public void setIdReq(Integer idReq) {
		this.idReq = idReq;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Integer getReqStatId() {
		return reqStatId;
	}

	public void setReqStatId(Integer reqStatId) {
		this.reqStatId = reqStatId;
	}

	public String getReqStatusName() {
		return reqStatusName;
	}

	public void setReqStatusName(String reqStatusName) {
		this.reqStatusName = reqStatusName;
	}

	public Integer getSortIdx() {
		return sortIdx;
	}

	public void setSortIdx(Integer sortIdx) {
		this.sortIdx = sortIdx;
	}

	public String getAnalysis() {
		return analysis;
	}

	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}

	@Override
	public int compareTo(RequestDTO o) {
		// TODO Auto-generated method stub
		return this.sortIdx - o.getSortIdx();
	}

	public String getEmpDetails() {
		return empDetails;
	}

	public void setEmpDetails(String empDetails) {
		this.empDetails = empDetails;
	}
}
