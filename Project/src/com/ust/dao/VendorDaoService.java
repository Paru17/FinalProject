package com.ust.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.ust.beans.User;
import com.ust.beans.VendorContactDetails;

public class VendorDaoService {
	
	JdbcTemplate template;
	
	public JdbcTemplate getTemplate() {
		return template;
	}

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	
	public User selectRole(String username, String password) {
		String sql = "select user_id from login where username='"
				+ username
				+ "' and password='" + password + "'";
		return template.queryForObject(sql, new Object[] {},
				new BeanPropertyRowMapper<User>(User.class));
	}


//To get all vendors
	public List<VendorContactDetails> getVendors() {
		return template
				.query("select vendor_id,vendor_name,address,location,service from vendor where isActive=0",
						new RowMapper<VendorContactDetails>() {
							public VendorContactDetails mapRow(ResultSet rs, int row)
									throws SQLException {
								VendorContactDetails vd = new VendorContactDetails();
								vd.setVendorId(rs.getInt(1));
								vd.setVendorName(rs.getString(2));
								vd.setAddress(rs.getString(3));
								vd.setLocation(rs.getString(4));
								vd.setService(rs.getString(5));
								
								return vd;
							}
						});
	}
	//get vendor by name
	
			public List<VendorContactDetails> getVendorByName(String vendorname) {
				return template
						.query("select vendor_id,vendor_name,address,location,service,pincode from vendor where isActive=0 and vendor_name='"+ vendorname + "'",
								new RowMapper<VendorContactDetails>() {
									public VendorContactDetails mapRow(ResultSet rs, int row)
											throws SQLException {
										VendorContactDetails vendorcontact = new VendorContactDetails();
										vendorcontact.setVendorId(rs.getInt(1));
										vendorcontact.setName(rs.getString(2));
										vendorcontact.setAddress(rs.getString(3));
										vendorcontact.setLocation(rs.getString(4));
										vendorcontact.setService(rs.getString(5));
										vendorcontact.setPincode(rs.getInt(6));
										return vendorcontact;
									}
								});
			}
			
			//get vendor details by id
			public VendorContactDetails getVendorById(int vendor_id) {
				return template
						.queryForObject("select vc.vendor_id,vc.vendor_name,vc.address,vc.location,vc.service,vc.pincode,cd.name,cd.department,cd.email,cd.phone,cd.contact_id from vendor vc join contact cd on vc.vendor_id=cd.vendor_id where vc.isactive=0 and vc.vendor_id="+ vendor_id + "",
								new Object [] {}, new BeanPropertyRowMapper<VendorContactDetails>(VendorContactDetails.class) );
			}
//									public VendorContactDetails mapRow(ResultSet rs, int row)
//											throws SQLException {
//										VendorContactDetails vendorcontact = new VendorContactDetails();
//										vendorcontact.setVendorId(rs.getInt(1));
//										vendorcontact.setName(rs.getString(2));
//										vendorcontact.setAddress(rs.getString(3));
//										vendorcontact.setLocation(rs.getString(4));
//										vendorcontact.setService(rs.getString(5));
//										vendorcontact.setPincode(rs.getInt(6));
//										vendorcontact.setName(rs.getString(7));
//										vendorcontact.setDepartment(rs.getString(8));
//										vendorcontact.setEmail(rs.getString(9));
//										vendorcontact.setPhone(rs.getString(10));
//										vendorcontact.setContactId(rs.getInt(11));
//										return vendorcontact;
//									}
//								});
//			}
		
		//Add vendors
		
		public int saveVendor(VendorContactDetails vendorcontact) {

			String sql1 = "insert into vendor(vendor_name,address,location,service,pincode,isActive) values "
					+ "('"
					+ vendorcontact.getVendorName()
					+ "','"
					+ vendorcontact.getAddress()
					+ "','"
					+ vendorcontact.getLocation()
					+ "','"
					+ vendorcontact.getService()
					+ "','"
					+ vendorcontact.getPincode()
					+ "',"
					+ 0
					+ ")";

			 template.update(sql1);
			 
			 Integer maxId = getSequence();
			 String sql2="insert into contact(vendor_id,name,department,email,phone) values ("
					 + maxId
						+ ",'"
						+ vendorcontact.getName()
						+ "','"
						+ vendorcontact.getDepartment()
						+ "','"
						+ vendorcontact.getEmail()
						+ "','" + vendorcontact.getPhone() + "')";
			 return template.update(sql2);

					 
			 
		}
		
		// to get vendor id
		private Integer getSequence() {
			Integer seq;
			String sql = "select MAX(vendor_id)from vendor";
			seq = template.queryForObject(sql, new Object[] {}, Integer.class);
			return seq;
		}
		
		// update vendor
		public int updateVendor(int vendorId, VendorContactDetails vendorcontact) {

			String sql = "update vendor set vendor_name='" + vendorcontact.getVendorName()
					+ "' ,address='" + vendorcontact.getAddress() + "' ,location='"
					+ vendorcontact.getLocation() + "',service='" + vendorcontact.getService() + "',pincode='" + vendorcontact.getPincode() + "',isactive=" + vendorcontact.getIsActive() + " "
					+ "where vendor_id =" + vendorId;
			template.update(sql);

			// Integer maxId = getSequence();

			String sql1 = "update contact set vendor_id=" + vendorId + ",name='"
					+ vendorcontact.getVendorName() + "',department='"
					+ vendorcontact.getDepartment() + "',email='"
					+ vendorcontact.getEmail() + "',phone='" + vendorcontact.getPhone() + "'where contact_id = " + vendorcontact.getContactId();

			return template.update(sql1);
		}
		
		// get contact by id

		public List<VendorContactDetails> getContactDetailsByVId(int vid) {
		return template.query("select contact_id,name,department,vendor_id,email,phone from contact where vendor_id="+vid+"", new RowMapper<VendorContactDetails>() {
			public VendorContactDetails mapRow(ResultSet rs, int row)
					throws SQLException {
				VendorContactDetails vendorcontact = new VendorContactDetails();
				vendorcontact.setContactId(rs.getInt(1));
				vendorcontact.setVendorName(rs.getString(2));
				vendorcontact.setDepartment(rs.getString(3));
				vendorcontact.setVendorId(rs.getInt(4));
				vendorcontact.setEmail(rs.getString(5));
				vendorcontact.setPhone(rs.getString(6));
				return vendorcontact;
			}
		});
		}
		//-to disable a vendor
		public int disableVendor(int vId) {

		String sql = "update vendor set isActive='1' where vendor_id=" + vId
		+ "";

		return template.update(sql);
		}
		


}
