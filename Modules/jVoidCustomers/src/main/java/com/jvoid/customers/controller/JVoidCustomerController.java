/*
 * #%L
 * jVoiD Open Platform
 * %%
 * Copyright (C) 2014 - 2015 jVoiD
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.jvoid.customers.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jvoid.customers.customer.model.CustomerMaster;
import com.jvoid.customers.customer.service.CustomerGroupService;
import com.jvoid.customers.customer.service.CustomerMasterService;

/**
 * controller for Customer
 * 
 * @author Shajir K, Rajeev Pillai
 * @version 1.0
 */

@Controller
@RequestMapping("/")
public class JVoidCustomerController {
	
	@Autowired
	private CustomerGroupService customerGroupService;
	@Autowired
	private CustomerMasterService customerMasterService;
	
	public void setCustomerGroupService(CustomerGroupService customerGroupService){
		this.customerGroupService  = customerGroupService;
	}
	public void setCustomerMasterService(CustomerMasterService cms){
		this.customerMasterService = cms;
	}
	
	
	@RequestMapping
    public @ResponseBody String list(Model model) {
		return "Welcome to Jvoid Customers";
	}
	
	@RequestMapping(value="customer/getbyemail")
    public @ResponseBody String getByEmail(@RequestParam(required = false, value = "callback") String callback,
			@RequestParam(required = false, value = "params") JSONObject jsonParams, HttpServletResponse response) {
		
		String email = "";
		String json  = "";
		CustomerMaster customerMaster = null;
		try {
			email = jsonParams.getString("email");
			customerMaster = this.customerMasterService.getCustomerByEmail(email);
			System.out.println(customerMaster.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (customerMaster != null) {
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			try {
				json = ow.writeValueAsString(customerMaster);
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return json.toString();
	}
	
	@RequestMapping(value="customer/resetpassword")
	public @ResponseBody String resetCustomerPassword(@RequestParam(required = false, value = "callback") String callback,
			@RequestParam(required = false, value = "params") JSONObject jsonParams, HttpServletResponse response){
	
		JSONObject test = new JSONObject();
		try {
			test.put("email", "test@oracle.com");
		} catch (JSONException e2) {
			e2.printStackTrace();
		}
		
		String email = null;
		try {
			email = test.getString("email");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		int status = this.customerMasterService.resetPassword(email);
		
		JSONObject jsonObject = new JSONObject();
		if ( status > 0 ) {
			try {
				jsonObject.put("status", 1);
				jsonObject.put("message", "Password sent successfully.");	
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}else{
			try {
				jsonObject.put("status", 0);
				jsonObject.put("message", "Failed to send password.");	
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return jsonObject.toString();
	} 
		
	@RequestMapping(value="customer/changepassword")
	public @ResponseBody String changeCustomerPassword(@RequestParam(required = false, value = "callback") String callback,
			@RequestParam(required = false, value = "params") JSONObject jsonParams, HttpServletResponse response){
	
		JSONObject test = new JSONObject();
		try {
			test.put("email", "test@oracle.com");
			test.put("oldPassword", "test123");
			test.put("newPassword", "test12345");
		} catch (JSONException e2) {
			e2.printStackTrace();
		}
		
		String email = null;
		String oldPassword = null;
		String newPassword = null;
		try {
			email = test.getString("email");
			oldPassword = test.getString("oldPassword");
			newPassword = test.getString("newPassword");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		int status = this.customerMasterService.changePassword(email, oldPassword, newPassword);
		JSONObject jsonObject = new JSONObject();
		if ( status > 0 ) {
			try {
				jsonObject.put("status", 1);
				jsonObject.put("message", "Password changed successfully.");	
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}else{
			try {
				jsonObject.put("status", 0);
				jsonObject.put("message", "Failed to change password.");	
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return jsonObject.toString();
	} 
	
	
	@RequestMapping(value="customer/list", method = RequestMethod.GET)
	public @ResponseBody String listCustomer(@RequestParam(required = false, value = "callback") String callback,
			@RequestParam(required = false, value = "params") JSONObject jsonParams, HttpServletResponse response){
	
		JSONObject test = new JSONObject();
		try {
			test.put("id", -1);
		} catch (JSONException e2) {
			e2.printStackTrace();
		}
		
		JSONObject jparams = jsonParams;
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		String json = "";
		//getAllCustomers
		
		int cid = -1;
		try {
			cid = jparams.getInt("id");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		List<CustomerMaster> customersList = null;
		if ( cid > 0 ) {
			//getCustomer;
			CustomerMaster customerMaster = this.customerMasterService.getCustomer(cid);
			List<CustomerMaster> cmList = new ArrayList<>();
			cmList.add(customerMaster);
			customersList = cmList;
		}else{
			customersList = this.customerMasterService.getAllCustomers();
		}
		JSONObject customerListObject = new JSONObject();
		JSONArray customers = new JSONArray();
		ObjectMapper mapper = new ObjectMapper();
		for(int i=0; i<customersList.size(); i++) {
			
			try {
				String strCustomerMasterObj = mapper.writeValueAsString(customersList.get(i));
				JSONObject jsonObj = new JSONObject(strCustomerMasterObj);
				customers.put(jsonObj);
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			
		}
		try {
			customerListObject.put("customers", customers);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		json = customerListObject.toString();
		return json;
	}
	
	@RequestMapping(value="customer/delete", method = RequestMethod.GET)
	public @ResponseBody String deleteCustomer(@RequestParam(required = false, value = "callback") String callback,
			@RequestParam(required = false, value = "params") JSONObject jsonParams, HttpServletResponse response){
	
		JSONObject jparams = jsonParams;
		int customerId = 0;
		try {
			customerId = jparams.getInt("id");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		JSONObject jsonObject = new JSONObject();
		if( customerId > 0 ) {
			int status = this.customerMasterService.deleteCustomer(customerId);
			try {
				if ( status == 1) {
					jsonObject.put("status", 1);
					jsonObject.put("message", "Customer deleted successfully.");
				}else{
					jsonObject.put("status", 0);
					jsonObject.put("message", "Failed to delete customer.");
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}else{
			try {
				jsonObject.put("status", 0);
				jsonObject.put("message", "Failed to delete customer.");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		return jsonObject.toString();
	}
	
	@RequestMapping(value="customer/update", method = RequestMethod.GET)
	public @ResponseBody String updateCustomer(@RequestParam(required = false, value = "callback") String callback,
			@RequestParam(required = false, value = "params") JSONObject jsonParams, HttpServletResponse response){
	
//		CustomerMaster customerMaster = new CustomerMaster();
//		customerMaster.setId(1);
//		customerMaster.setCity("Cochin-1");
//		customerMaster.setCompany("ORA");
//		customerMaster.setCountry("IN");
//		customerMaster.setCustomerGroup(1);
//		customerMaster.setDateOfBirth("22-10-1985");
//		customerMaster.setEmail("test@oracle.ora");
//		customerMaster.setFax("59999999555");
//		customerMaster.setFirstName("Mathew");
//		customerMaster.setGender("Male");
//		customerMaster.setLastName("Thomas");
//		customerMaster.setPassword("pwd123");
//		customerMaster.setPhone("4433222222");
//		customerMaster.setPostalCode("695038");
//		customerMaster.setPrefix("Mr.");
//		customerMaster.setState("Kerala");
//		customerMaster.setStreetAddress1("ORA-123");
//		customerMaster.setStreetAddress2("Oracle Avenue");
//		customerMaster.setType("Customer");
//		
//		JSONObject jObj = new JSONObject();
//		try {
//			jObj.put("id", 13);
//			jObj.put("city", "Cochin");
//			jObj.put("company", "ORA");
//			jObj.put("customer_group", "1");
//			jObj.put("date_of_birth", "22-10-1985");
//			jObj.put("email", "test@oracle.ora");
//			jObj.put("fax", "59999999555");
//			jObj.put("first_name", "Mathew");
//			jObj.put("middle_name", "");
//			jObj.put("last_name", "Thomas");
//			jObj.put("password", "pwd123");
//			jObj.put("phone", "4433222222");
//			jObj.put("tax_number", "ATPR1293");
//			jObj.put("postal_code", "695038");
//			jObj.put("prefix", "Mr.");
//			jObj.put("State", "Kerala");
//			jObj.put("country", "IN");
//			jObj.put("street_address1", "ORA-123");
//			jObj.put("street_address2", "Oracle Avenue");
//			jObj.put("street_address3", "TVM");
//			jObj.put("type", "Customer");
//		} catch (JSONException e1) {
//			e1.printStackTrace();
//		}
		
		
		int status = this.customerMasterService.updateCustomer(jsonParams);

		JSONObject jsonObject = new JSONObject();
		if ( status > 0 ) {
			try {
				jsonObject.put("status", 1);
				jsonObject.put("message", "Customer updated successfully.");	
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}else{
			try {
				jsonObject.put("status", 0);
				jsonObject.put("message", "Failed to update customer.");	
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return jsonObject.toString();
		
	}
	
	@RequestMapping(value="customer/add", method = RequestMethod.GET)
	public @ResponseBody String addCustomer(@RequestParam(required = false, value = "callback") String callback,
			@RequestParam(required = false, value = "params") JSONObject jsonParams, HttpServletResponse response){		
		int id = this.customerMasterService.addCustomer(jsonParams);
		
		JSONObject jsonObject = new JSONObject();
		if ( id > 0 ) {
			try {
				jsonObject.put("status", 1);
				jsonObject.put("id", id);
				jsonObject.put("message", "Customer added successfully.");	
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}else{
			try {
				jsonObject.put("status", 0);
				jsonObject.put("message", "Failed to add customer.");	
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return jsonObject.toString();
	}
	
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getAddNewCustomerForm(@ModelAttribute("newCustomer") CustomerMaster newCustomer, Model model) {
		model.addAttribute("customerGroup", this.customerGroupService.listCustomerGroups());
		return "addJvoidCustomer";
	}
	
		   
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAddNewCustomerForm(@ModelAttribute("newCustomer") CustomerMaster customerToBeAdded) {
		customerToBeAdded.setType("Customer");
		//this.customerMasterService.addCustomer(customerToBeAdded);
		return "redirect:/jvoid-customer";
	}  
	
}
