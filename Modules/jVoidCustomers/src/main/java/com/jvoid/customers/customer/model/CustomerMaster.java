/*
 * #%L
 * jVoiD Open Platform
 * %%
 * Copyright 2014-2015 Schogini Systems Pvt Ltd (http://www.schogini.com)
 * Project Website: http://www.jvoid.com
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
package com.jvoid.customers.customer.model; 

import java.util.HashMap;


public class CustomerMaster {

	
	
	//test
	//test111
	
	private int id;

	private int customerGroup;

	private String prefix;

	private String firstName;

	private String middleName;

	private String lastName;

	private String email;

	private String dateOfBirth;

	private String taxNumber;

	private String gender;

	private String password;

	private String company;

	private String streetAddress1;

	private String streetAddress2;

	private String streetAddress3;

	private String city;

	private String country;

	private String state;

	private String postalCode;

	private String phone;

	private String fax;
	
	private String type;


	public CustomerMaster() {}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerGroup() {
		return customerGroup;
	}

	public void setCustomerGroup(int customerGroup) {
		this.customerGroup = customerGroup;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getTaxNumber() {
		return taxNumber;
	}

	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getStreetAddress1() {
		return streetAddress1;
	}

	public void setStreetAddress1(String streetAddress1) {
		this.streetAddress1 = streetAddress1;
	}

	public String getStreetAddress2() {
		return streetAddress2;
	}

	public void setStreetAddress2(String streetAddress2) {
		this.streetAddress2 = streetAddress2;
	}

	public String getStreetAddress3() {
		return streetAddress3;
	}

	public void setStreetAddress3(String streetAddress3) {
		this.streetAddress3 = streetAddress3;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	@Override
	public String toString() {
		return "CustomerMaster [customerGroup=" + customerGroup + ", prefix="
				+ prefix + ", firstName=" + firstName + ", middleName="
				+ middleName + ", lastName=" + lastName + ", email=" + email
				+ ", dateOfBirth=" + dateOfBirth + ", taxNumber=" + taxNumber
				+ ", gender=" + gender + ", password=" + password
				+ ", company=" + company + ", streetAddress1=" + streetAddress1
				+ ", streetAddress2=" + streetAddress2 + ", streetAddress3="
				+ streetAddress3 + ", city=" + city + ", country=" + country
				+ ", state=" + state + ", postalCode=" + postalCode
				+ ", phone=" + phone + ", fax=" + fax + ", type=" + type + "]";
	}
	
	public HashMap<String, String> toAttributedHashMap(){
		HashMap<String, String> attributedMap = new HashMap<String, String>();
		
		attributedMap.put("customer_group", customerGroup+"");
		attributedMap.put("prefix", prefix);
		attributedMap.put("first_name", firstName);
		attributedMap.put("middle_name", middleName);
		attributedMap.put("last_name", lastName);
		attributedMap.put("email", email);
		attributedMap.put("date_of_birth", dateOfBirth);
		attributedMap.put("tax_number", taxNumber);
		attributedMap.put("gender", gender);
		attributedMap.put("password", password);
		attributedMap.put("company", company);
		attributedMap.put("street_address1", streetAddress1);
		attributedMap.put("street_address2", streetAddress2);
		attributedMap.put("street_address3", streetAddress3);
		attributedMap.put("city", city);
		attributedMap.put("country", country);
		attributedMap.put("state", state);
		attributedMap.put("postal_code", postalCode);
		attributedMap.put("phone", phone);
		attributedMap.put("fax", fax);
		
		return attributedMap;
	}
}