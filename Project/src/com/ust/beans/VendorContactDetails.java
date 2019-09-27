package com.ust.beans;

public class VendorContactDetails {
	private int vendorId;
	private String vendorName;
	private String address;
	private String location;
	private String service;
	private int pincode;
	private int isActive;
	private int contactId;
	private String name;
	private String department;
	private String email;
	private String phone;
	public int getVendorId() {
		return vendorId;
	}
	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public int getContactId() {
		return contactId;
	}
	public void setContactId(int contactId) {
		this.contactId = contactId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public VendorContactDetails() {
		super();
		
	}
	public VendorContactDetails(int vendorId, String vendorName,
			String address, String location, String service, int pincode,
			int isActive, int contactId, String name, String department,
			String email, String phone) {
		super();
		this.vendorId = vendorId;
		this.vendorName = vendorName;
		this.address = address;
		this.location = location;
		this.service = service;
		this.pincode = pincode;
		this.isActive = isActive;
		this.contactId = contactId;
		this.name = name;
		this.department = department;
		this.email = email;
		this.phone = phone;
	}
	public VendorContactDetails(String vendorName, String address,
			String location, String service, int pincode, int isActive,
			int contactId, String name, String department, String email,
			String phone) {
		super();
		this.vendorName = vendorName;
		this.address = address;
		this.location = location;
		this.service = service;
		this.pincode = pincode;
		this.isActive = isActive;
		this.contactId = contactId;
		this.name = name;
		this.department = department;
		this.email = email;
		this.phone = phone;
	}
	
	
	
	

}
