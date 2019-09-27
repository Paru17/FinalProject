package com.ust.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ust.beans.User;
import com.ust.beans.VendorContactDetails;
import com.ust.dao.VendorDaoService;

@RestController
public class VendorController {
	@Autowired
	VendorDaoService dao;
	
	//user validation
	@RequestMapping(value = "/api/user/{username}/{password}", method = RequestMethod.GET)
	@ResponseBody
	public User selectUser(@PathVariable("username") String username,
			@PathVariable("password") String password) {
		return dao.selectRole(username,password);
	}
	
	// view staff list
		@RequestMapping(value = "/api/vendors/{vendorName}", method = RequestMethod.GET)
		@ResponseBody
		public List getVendors(Model m, @PathVariable("vendorName") String vendorName ) {
			List list = null;
			if (vendorName.equals("null")) {
				list = dao.getVendors();
			} 
//				else {
//				list = dao.getStaffByName(vendorName);
//			}

			return list;
		}
		// view vendor list by id
				@RequestMapping(value = "/api/vendor/{vendor_id}", method = RequestMethod.GET)
				@ResponseBody
				public VendorContactDetails getvendors(Model m, @PathVariable("vendor_id") int vendor_id) {
				
					return dao.getVendorById(vendor_id);
					
				}

				// Add Vendor
				@ResponseBody
				@RequestMapping(value = "/api/savevendor", method = RequestMethod.POST)
				public void insertVendor(@RequestBody VendorContactDetails vendercontact)  {
					dao.saveVendor(vendercontact);
				}
				
				// update Vendor
				@ResponseBody
				@RequestMapping(value = "/api/updatevendor", method = RequestMethod.PUT)
				public void updateDoctor(@RequestBody VendorContactDetails vendorcontact)  {
					int vendorId = vendorcontact.getVendorId();
					int contactId = vendorcontact.getContactId();
					dao.updateVendor(vendorId, vendorcontact);
				}
				
				// to disable a vendor

				@RequestMapping(value = "/api/disablevendor/{vId}", method = RequestMethod.PUT)
				@ResponseBody
				public void disableStaff(@PathVariable("vId") int vendorid) {
				dao.disableVendor(vendorid);
				}
				
				// view contactdetails by vendor id

				@RequestMapping(value = "/api/contactDetails/{vId}", method = RequestMethod.GET)
				@ResponseBody
				public List getContactDetails(Model m,@PathVariable("vId") int vendor_id) {
					List list;
				list=dao.getContactDetailsByVId(vendor_id);
				return list;

				}


	

}
