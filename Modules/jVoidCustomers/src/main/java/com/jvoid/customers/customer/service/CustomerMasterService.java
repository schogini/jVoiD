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
 package com.jvoid.customers.customer.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Map.Entry;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.jvoid.customers.customer.model.Entities;
import com.jvoid.customers.customer.model.Customer;
import com.jvoid.customers.customer.model.CustomerEntityValues;
import com.jvoid.customers.customer.model.CustomerMaster;
import com.jvoid.customers.utils.Utilities;
import com.sun.mail.iap.Protocol;

/**
 * All Customer related operations such as add, edit, delete and validation functions
 * change/reset customer password etc.
 * 
 * @author Shajir K, Rajeev Pillai 
 * @version 1.0
 */

public class CustomerMasterService {
	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private CustomerEntityValuesService customerEntityValuesService;
	@Autowired
	private EntitiesService entitiesService;
	@Autowired
	private CustomerGroupService customerGroupService;
	
	public void setCustomerGroupService(CustomerGroupService customerGroupService) {
		this.customerGroupService = customerGroupService;
	}
	
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	public void setCustomerEntityValuesService(CustomerEntityValuesService customerEntityValuesService) {
		this.customerEntityValuesService = customerEntityValuesService;
	}
	
	public void setEntitiesService(EntitiesService entitiesService){
		this.entitiesService = entitiesService;
	}
	
	public int deleteCustomer(int id) {
		int status = 0;
		
		Customer customer = this.customerService.getcustomerById(id);
		if ( null != customer ) {
			this.customerService.removecustomer(id);
			status = removeCustomerEntities(id);
		}else{
			status = 0;
		}
		
		return status;
	}
	
	public int removeCustomerEntities(int id) {
		int status = 1;
		List<CustomerEntityValues> customerEntityValuesList = this.customerEntityValuesService
				.getCustomerEntityValuesByCustomerId(id);
		
		List<Integer> deleteIds = new ArrayList<>();
		for(int i=0; i<customerEntityValuesList.size(); i++) {
			if ( customerEntityValuesList.get(i).getCustomerId() == id ){
				deleteIds.add(customerEntityValuesList.get(i).getId());
			}
		}
		
		if( deleteIds.size() > 0 ) {
			for(int i=0; i<deleteIds.size(); i++){
				this.customerEntityValuesService.removeCustomerAttributeValues(deleteIds.get(i).intValue());
			}
		}else{
			status = 0;
		}
		return status;
	}
	
	public String randomPassword(){
		String pwd = null;
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 20; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		pwd = sb.toString();
		
		return pwd;
	}
	
	
	public void sendEmail(String email, String password){
		
		Properties properties = System.getProperties();
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.starttls.enable", "true");
	    properties.put("mail.smtp.auth", false);
	    properties.put("mail.smtp.host", "smtp.gmail.com");
	    properties.put("mail.smtp.port", 587);
	    Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("test@example.com", "test123");
			}
	    });
	    
	    try {
	    	 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("toaddress@example.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			message.setSubject("Reset Password");
			String content = "Your new password is " + password;
			message.setText(content);
 			Transport.send(message);
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int changePassword(String email, String oldPwd, String newPwd){
		int status = 0;
		Customer customer = this.customerService.getCustomerByEmailId(email);
		int attributeId = 28;// password attribute
		if ( null != customer ) {
			
			CustomerEntityValues custAttrValue = this.customerEntityValuesService
					.getCustomerEntityValuesByCustomerIdAndAttributeId(customer.getId(), attributeId);
			
			if ( oldPwd.equals(custAttrValue.getValue())){
				
				custAttrValue.setCustomerId(customer.getId());
			    custAttrValue.setLanguage(custAttrValue.getLanguage());
			    custAttrValue.setAttributeId(attributeId);
			    String password = randomPassword();
		    	custAttrValue.setValue(newPwd);
		    	custAttrValue.setId(custAttrValue.getId());
		    	customerEntityValuesService.updateCustomerAttributeValues(custAttrValue);
		    	status = 1;
			}
		}
		
		return status;
	}
	
	public int resetPassword(String email) {
		int status = 0;
		Customer customer = this.customerService.getCustomerByEmailId(email);
		int attributeId = 28;// password attribute
		if ( null != customer ) {
			CustomerEntityValues custAttrValue = this.customerEntityValuesService
					.getCustomerEntityValuesByCustomerIdAndAttributeId(customer.getId(), attributeId);
			
		    custAttrValue.setCustomerId(customer.getId());
		    custAttrValue.setLanguage(custAttrValue.getLanguage());
		    custAttrValue.setAttributeId(attributeId);
		    String password = randomPassword();
	    	custAttrValue.setValue(password);
	    	custAttrValue.setId(custAttrValue.getId());
	    	customerEntityValuesService.updateCustomerAttributeValues(custAttrValue);
	    	sendEmail(email, password);
	    	status = 1;
		}
		
		return status;
	}
	
	public boolean isEmailAlreadyExists(String email) {
		boolean status = false;
		Customer customer = this.customerService.getCustomerByEmailId(email);
		if ( customer  != null ) {
			status = true;
		}
		return status;
	}
	
	public CustomerMaster getCustomerByEmail(String email) {
		CustomerMaster customerMaster = new CustomerMaster();
		Customer customer = this.customerService.getCustomerByEmailId(email);
		int customerId = customer.getId();
		
		if ( null != customer ) {
			customerMaster.setId(customerId);
			List<CustomerEntityValues> customerEntityValuesList = this.customerEntityValuesService.getCustomerEntityValuesByCustomerId(customer.getId());
			
			for (CustomerEntityValues customerEntityValues : customerEntityValuesList) {
				System.out.println("CustEntityValues:"+customerEntityValuesList.toString());
				switch (customerEntityValues.getAttributeId()) {
				case 19:
					customerMaster.setCustomerGroup(customer.getCustomerGroupId());
					break;
				case 20:
					customerMaster.setPrefix(customerEntityValues.getValue()); 
					break;
				case 21:
					customerMaster.setFirstName(customerEntityValues.getValue());
					break;
				case 22:
					customerMaster.setMiddleName(customerEntityValues.getValue());
					break;
				case 23:
					customerMaster.setLastName(customerEntityValues.getValue());
					break;
				case 24:
					customerMaster.setEmail(customerEntityValues.getValue());
					break;
				case 25:
					customerMaster.setDateOfBirth(customerEntityValues.getValue());
					break;
				case 26:
					customerMaster.setTaxNumber(customerEntityValues.getValue());
					break;
				case 27:
					customerMaster.setGender(customerEntityValues.getValue());
					break;
				case 28:
					customerMaster.setPassword(customerEntityValues.getValue());
					break;
				case 29:
					customerMaster.setCompany(customerEntityValues.getValue());
					break;
				case 30:
					customerMaster.setStreetAddress1(customerEntityValues.getValue());
					break;
				case 31:
					customerMaster.setStreetAddress2(customerEntityValues.getValue());
					break;
				case 32:
					customerMaster.setStreetAddress3(customerEntityValues.getValue());
					break;
				case 33:
					customerMaster.setCity(customerEntityValues.getValue());
					break;
				case 34:
					customerMaster.setCountry(customerEntityValues.getValue());
					break;
				case 35:
					customerMaster.setState(customerEntityValues.getValue());
					break;
				case 36:
					customerMaster.setPostalCode(customerEntityValues.getValue());
					break;
				case 37:
					customerMaster.setPhone(customerEntityValues.getValue());
					break;
				case 38:
					customerMaster.setFax(customerEntityValues.getValue());
					break;
				default:
					break;
				}
			}
		}
		
		return customerMaster;
	}
	
	public CustomerMaster getCustomer(int customerId) {
		CustomerMaster customerMaster = new CustomerMaster();
		Customer customer = this.customerService.getcustomerById(customerId);
		
		if ( null != customer ) {
		
			List<CustomerEntityValues> customerEntityValuesList = this.customerEntityValuesService.getCustomerEntityValuesByCustomerId(customer.getId());
		
			for (CustomerEntityValues customerEntityValues : customerEntityValuesList) {
				
				switch (customerEntityValues.getAttributeId()) {
				case 19:
					customerMaster.setCustomerGroup(customer.getCustomerGroupId());
					break;
				case 20:
					customerMaster.setPrefix(customerEntityValues.getValue()); 
					break;
				case 21:
					customerMaster.setFirstName(customerEntityValues.getValue());
					break;
				case 22:
					customerMaster.setMiddleName(customerEntityValues.getValue());
					break;
				case 23:
					customerMaster.setLastName(customerEntityValues.getValue());
					break;
				case 24:
					customerMaster.setEmail(customerEntityValues.getValue());
					break;
				case 25:
					customerMaster.setDateOfBirth(customerEntityValues.getValue());
					break;
				case 26:
					customerMaster.setTaxNumber(customerEntityValues.getValue());
					break;
				case 27:
					customerMaster.setGender(customerEntityValues.getValue());
					break;
				case 28:
					customerMaster.setPassword(customerEntityValues.getValue());
					break;
				case 29:
					customerMaster.setCompany(customerEntityValues.getValue());
					break;
				case 30:
					customerMaster.setStreetAddress1(customerEntityValues.getValue());
					break;
				case 31:
					customerMaster.setStreetAddress2(customerEntityValues.getValue());
					break;
				case 32:
					customerMaster.setStreetAddress3(customerEntityValues.getValue());
					break;
				case 33:
					customerMaster.setCity(customerEntityValues.getValue());
					break;
				case 34:
					customerMaster.setCountry(customerEntityValues.getValue());
					break;
				case 35:
					customerMaster.setState(customerEntityValues.getValue());
					break;
				case 36:
					customerMaster.setPostalCode(customerEntityValues.getValue());
					break;
				case 37:
					customerMaster.setPhone(customerEntityValues.getValue());
					break;
				case 38:
					customerMaster.setFax(customerEntityValues.getValue());
					break;
				default:
					break;
				}
			}
		
		}
		return customerMaster;
	}
	
	public List<CustomerMaster> getAllCustomers() {
		
		List<CustomerMaster> customerMasterList = new ArrayList<>();
		List<Customer> customerList = this.customerService.listcustomers();
		for (Customer customer : customerList) {
			List<CustomerEntityValues> customerEntityValuesList = this.customerEntityValuesService.getCustomerEntityValuesByCustomerId(customer.getId());
			CustomerMaster customerMaster = new CustomerMaster();
			customerMaster.setId(customer.getId());
			customerMaster.setCustomerGroup(customer.getCustomerGroupId());
			customerMaster.setEmail(customer.getEmail());
			
			for (CustomerEntityValues customerEntityValues : customerEntityValuesList) {
				
				switch (customerEntityValues.getAttributeId()) {
				case 19:
					customerMaster.setCustomerGroup(customer.getCustomerGroupId());
					break;
				case 20:
					customerMaster.setPrefix(customerEntityValues.getValue()); 
					break;
				case 21:
					customerMaster.setFirstName(customerEntityValues.getValue());
					break;
				case 22:
					customerMaster.setMiddleName(customerEntityValues.getValue());
					break;
				case 23:
					customerMaster.setLastName(customerEntityValues.getValue());
					break;
				case 24:
					customerMaster.setEmail(customerEntityValues.getValue());
					break;
				case 25:
					customerMaster.setDateOfBirth(customerEntityValues.getValue());
					break;
				case 26:
					customerMaster.setTaxNumber(customerEntityValues.getValue());
					break;
				case 27:
					customerMaster.setGender(customerEntityValues.getValue());
					break;
				case 28:
					customerMaster.setPassword(customerEntityValues.getValue());
					break;
				case 29:
					customerMaster.setCompany(customerEntityValues.getValue());
					break;
				case 30:
					customerMaster.setStreetAddress1(customerEntityValues.getValue());
					break;
				case 31:
					customerMaster.setStreetAddress2(customerEntityValues.getValue());
					break;
				case 32:
					customerMaster.setStreetAddress3(customerEntityValues.getValue());
					break;
				case 33:
					customerMaster.setCity(customerEntityValues.getValue());
					break;
				case 34:
					customerMaster.setCountry(customerEntityValues.getValue());
					break;
				case 35:
					customerMaster.setState(customerEntityValues.getValue());
					break;
				case 36:
					customerMaster.setPostalCode(customerEntityValues.getValue());
					break;
				case 37:
					customerMaster.setPhone(customerEntityValues.getValue());
					break;
				case 38:
					customerMaster.setFax(customerEntityValues.getValue());
					break;
				default:
					break;
				}
			}
			customerMasterList.add(customerMaster);
		}
		
		return customerMasterList;
	}
	
	public int updateCustomer(JSONObject jObj) {
		int status = 0;
		Customer customer = null;
		int customerId = 0;
		try {
			customerId = jObj.getInt("id");
			customer = this.customerService.getcustomerById(customerId);
		} catch (JSONException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		if ( null != customer ) {
			
			System.out.println("Date:"+Utilities.getCurrentDateTime());
			System.out.println("CO:"+customer.toString());
			customer.setUpdatedAt(Utilities.getCurrentDateTime());
			customer.setCreatedAt(customer.getCreatedAt());
			try {
				customer.setEmail(jObj.getString("email"));
				customer.setCustomerGroupId(jObj.getInt("customerGroup"));
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			this.customerService.updatecustomer(customer);
			
			List<Entities> listAttributes = this.entitiesService.listAttributes();
			HashMap<Integer, String> attrs = getAttributesList(listAttributes, "Customer");
			System.out.println("attrs==>"+attrs.toString());
		
			for(Entry<Integer, String> entry : attrs.entrySet()) {
			    Integer attrId = entry.getKey();
			    String attrName = entry.getValue();
			    
			    CustomerEntityValues customerEntityValue = this.customerEntityValuesService.getCustomerEntityValuesByCustomerIdAndAttributeId(customerId, attrId);
			    
			    String attrValue;
				try {
					attrValue = jObj.getString(attrName);
					 if ( null != attrValue ) {
					    	
					    	CustomerEntityValues custAttrValue = new CustomerEntityValues();
						    custAttrValue.setCustomerId(customerId);
						    custAttrValue.setLanguage(customerEntityValue.getLanguage());
						    custAttrValue.setAttributeId(attrId);
					    	custAttrValue.setValue(attrValue);
					    	custAttrValue.setId(customerEntityValue.getId());
					    	customerEntityValuesService.updateCustomerAttributeValues(custAttrValue);
						    System.out.println("custAttrValue-->"+custAttrValue.toString());
					    }
				} catch (JSONException e) {
					e.printStackTrace();
				}
			   
			}
			status = 1;
		}else{
			status = 0;
		}
		return status;
	}
	

	
	public int addCustomer(JSONObject jObj) {
		int cid = 0;
		String emailId = null;
		Customer customer = new Customer();
		customer.setCreatedAt(Utilities.getCurrentDateTime());
		customer.setUpdatedAt(Utilities.getCurrentDateTime());
		try {
			customer.setCustomerGroupId(jObj.getInt("customerGroup"));
			emailId = jObj.getString("email");
			System.out.println("EMAIL ID:"+emailId);
			customer.setEmail(emailId);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if ( null == emailId ) {
			return cid;
		}else if ( isEmailAlreadyExists(emailId) ){
			return cid;
		}
		customerService.addcustomer(customer);
		cid = customer.getId();
		System.out.println("customer==>"+customer.toString());
		
		List<Entities> listAttributes = this.entitiesService.listAttributes();
		HashMap<Integer, String> attrs = getAttributesList(listAttributes, "Customer");
		System.out.println("attrs==>"+attrs.toString());
		
		for(Entry<Integer, String> entry : attrs.entrySet()) {
		    Integer attrId = entry.getKey();
		    String attrName = entry.getValue();
		    
		    String attrValue = null;
			try {
				attrValue = jObj.getString(attrName);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    if ( null != attrValue ) {
		    	
		    	CustomerEntityValues custAttrValue = new CustomerEntityValues();
			    custAttrValue.setCustomerId(cid);
			    custAttrValue.setLanguage("enUS");
			    custAttrValue.setAttributeId(attrId);
		    	custAttrValue.setValue(attrValue);
		    	customerEntityValuesService.addCustomerAttributeValues(custAttrValue);
			    System.out.println("custAttrValue-->"+custAttrValue.toString());
		    }
		    
		}
		
		return cid;
		
		/* //Rest call
		final String SERVER_URI = "http://localhost:8080/jvoidattributes/listCustomerAttriburtes";
		RestTemplate restTemplate = new RestTemplate();

		String returnString = restTemplate.getForObject(SERVER_URI, String.class);        
        JSONObject returnJsonObj = null;
		try {
			returnJsonObj = new JSONObject(returnString);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        System.out.println("returnJsonObj=>"+returnJsonObj);
        
        JSONArray arr = null;
        try {
			arr = returnJsonObj.getJSONArray("Attributes");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        for (int i=0; i<arr.length(); i++) {
		    try {
				String attrValue = arr.getJSONObject(i).getString("code");
				Integer attrId = Integer.parseInt(arr.getJSONObject(i).getString("id"));
				System.out.println("id = "+attrId+"   code = "+attrValue);
				
				CustomerAttributeValues custAttrValue = new CustomerAttributeValues();
			    custAttrValue.setCustomerId(customer.getId());//get it from customer table
			    custAttrValue.setLanguage("enUS");
			    custAttrValue.setAttributeId(attrId);
			    custAttrValue.setValue(customerMaster.toAttributedHashMap().get(attrValue));
			    customerAttributeValuesService.addCustomerAttributeValues(custAttrValue);
			    System.out.println("custAttrValue-->"+custAttrValue.toString());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		*/
		
		
	}
	
	public HashMap<Integer, String> getAttributesList(List<Entities> listAttributes, String type){
		HashMap<Integer, String> attrMap = new HashMap<Integer, String>();
		for(int i=0; i<listAttributes.size(); i++) {			
			if ( listAttributes.get(i).getType().equalsIgnoreCase(type) ){
				attrMap.put(listAttributes.get(i).getId(), listAttributes.get(i).getCode());
			}
		}
		return attrMap;
	}
}
