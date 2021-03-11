package ejbholidaybookingapp;

import java.io.Serializable;

public class DepartmentDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int depId;
	private String depName;
	private Integer maxNPeople;
	private Integer nRequiredPeople;

	public DepartmentDTO(int depId, String depName, Integer maxNPeople, Integer nRequiredPeople) {
		this.depId = depId;
		this.depName = depName;
		this.maxNPeople = maxNPeople;
		this.nRequiredPeople = nRequiredPeople;
	}

	public int getDepId() {
		return depId;
	}

	public void setDepId(int depId) {
		this.depId = depId;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public Integer getMaxNPeople() {
		return maxNPeople;
	}

	public void setMaxNPeople(Integer maxNPeople) {
		this.maxNPeople = maxNPeople;
	}

	public Integer getnRequiredPeople() {
		return nRequiredPeople;
	}

	public void setnRequiredPeople(Integer nRequiredPeople) {
		this.nRequiredPeople = nRequiredPeople;
	}
}
