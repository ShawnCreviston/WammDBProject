package org.wamm.DbClient.BusinessObjects;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Shawn Creviston, Eric Stephenson
 * WAMM
 */
public class Patient {

	// Patient ID info
	private int patientId;
	
	// Name info
	private String firstName;
	private String middleName;
	private String lastName;
	
	// Gender
	private String gender;
	
	// Birth and death dates
	// null if unknown
	private Date birthday;
	private Date deceased;
	
	// Weight
	// -1 if unknown
	private double weight;
	
	// Address info
	private Territory curTerritory;
	private String curStreet;
	private Territory prevTerritory;
	private String prevStreet;
	
	// Phone info
	private String phoneNumber1;
	private String phoneNumber2;
	
	// School info for child or adult
	// null if none
	private School school;
	
	// Patients occupation if known
	private String occupation;
	
	// Martial Status
	private String maritalStatus;
	
	private SupportGroup supportGroup;
	
	private ArrayList<Treatment> treatments;
	
	private Plhiv phlivData;
	
	private Date registeredDate;
	private Date lastUpdatedDate;
	
	public Patient() {
		patientId = -1;
		
		firstName = null;
		middleName = null;
		lastName = null;
		
		gender = null;
		
		birthday = null;
		deceased = null;
		
		weight = 0;
		
		curTerritory = null;
		curStreet = null;
		
		phoneNumber1 = null;
		phoneNumber2 = null;
		
		school = null;
		
		occupation = null;
		maritalStatus = null;
		
		supportGroup = null;
		
		registeredDate = null;
		lastUpdatedDate = null;
	}

	/**
	 * @return the patientId
	 */
	public int getPatientId() {
		return patientId;
	}

	/**
	 * @param patientId the patientId to set
	 */
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the deceased
	 */
	public Date getDeceased() {
		return deceased;
	}

	/**
	 * @param deseased the deceased to set
	 */
	public void setDeceased(Date deceased) {
		this.deceased = deceased;
	}

	/**
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * @return the curTerritory
	 */
	public Territory getCurTerritory() {
		return curTerritory;
	}

	/**
	 * @param curTerritory the curTerritory to set
	 */
	public void setCurTerritory(Territory curTerritory) {
		this.curTerritory = curTerritory;
	}

	/**
	 * @return the curStreet
	 */
	public String getCurStreet() {
		return curStreet;
	}

	/**
	 * @param curStreet the curStreet to set
	 */
	public void setCurStreet(String curStreet) {
		this.curStreet = curStreet;
	}

	/**
	 * @return the school
	 */
	public School getSchool() {
		return school;
	}

	/**
	 * @param school the school to set
	 */
	public void setSchool(School school) {
		this.school = school;
	}

	/**
	 * @return the occupation
	 */
	public String getOccupation() {
		return occupation;
	}

	/**
	 * @param occupation the occupation to set
	 */
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	/**
	 * @return the maritalStatus
	 */
	public String getMaritalStatus() {
		return maritalStatus;
	}

	/**
	 * @param maritalStatus the maritalStatus to set
	 */
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	/**
	 * @return the lastUpdatedDate
	 */
	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	/**
	 * @param lastUpdatedDate the lastUpdatedDate to set
	 */
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	/**
	 * @return the prevTerritory
	 */
	public Territory getPrevTerritory() {
		return prevTerritory;
	}

	/**
	 * @param prevTerritory the prevTerritory to set
	 */
	public void setPrevTerritory(Territory prevTerritory) {
		this.prevTerritory = prevTerritory;
	}

	/**
	 * @return the prevStreet
	 */
	public String getPrevStreet() {
		return prevStreet;
	}

	/**
	 * @param prevStreet the prevStreet to set
	 */
	public void setPrevStreet(String prevStreet) {
		this.prevStreet = prevStreet;
	}

//	/**
//	 * @return the supportSystem
//	 */
//	public ArrayList<SupportGroup> getSupportSystem() {
//		return supportSystem;
//	}
//
//	/**
//	 * @param supportSystem the supportSystem to set
//	 */
//	public void setSupportSystem(ArrayList<SupportGroup> supportSystem) {
//		this.supportSystem = supportSystem;
//	}
//	
//	public void addSupportGroup(SupportGroup sg) {
//		this.supportSystem.add(sg);
//	}
//	
//	public SupportGroup getSupportGroup() {
//		if (supportSystem != null && supportSystem.size() > 0) {
//			return supportSystem.get(0);
//		}
//		return null;
//	}

	/**
	 * @return the supportGroup
	 */
	public SupportGroup getSupportGroup() {
		return supportGroup;
	}

	/**
	 * @param supportGroup the supportGroup to set
	 */
	public void setSupportGroup(SupportGroup supportGroup) {
		this.supportGroup = supportGroup;
	}

	/**
	 * @return the treatments
	 */
	public ArrayList<Treatment> getTreatments() {
		return treatments;
	}

	/**
	 * @param treatments the treatments to set
	 */
	public void setTreatments(ArrayList<Treatment> treatments) {
		this.treatments = treatments;
	}
	
	public void addTreatment(Treatment t) {
		if (this.treatments == null) {
			treatments = new ArrayList<>();
		}
		treatments.add(t);
	}

	/**
	 * @return the phlivData
	 */
	public Plhiv getPhlivData() {
		return phlivData;
	}

	/**
	 * @param phlivData the phlivData to set
	 */
	public void setPhlivData(Plhiv phlivData) {
		this.phlivData = phlivData;
	}

	/**
	 * @return the registeredDate
	 */
	public Date getRegisteredDate() {
		return registeredDate;
	}

	/**
	 * @param registeredDate the registeredDate to set
	 */
	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}

	/**
	 * @return the phoneNumber1
	 */
	public String getPhoneNumber1() {
		return phoneNumber1;
	}

	/**
	 * @param phoneNumber1 the phoneNumber1 to set
	 */
	public void setPhoneNumber1(String phoneNumber1) {
		this.phoneNumber1 = phoneNumber1;
	}

	/**
	 * @return the phoneNumber2
	 */
	public String getPhoneNumber2() {
		return phoneNumber2;
	}

	/**
	 * @param phoneNumber2 the phoneNumber2 to set
	 */
	public void setPhoneNumber2(String phoneNumber2) {
		this.phoneNumber2 = phoneNumber2;
	}

}
