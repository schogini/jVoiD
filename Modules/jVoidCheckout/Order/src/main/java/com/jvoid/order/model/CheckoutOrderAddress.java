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
package com.jvoid.order.model; 

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Checkout_order_address")
public class CheckoutOrderAddress {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="order_id")
	private int orderId;

	@Column(name="created_at")
	private String createdAt;

	@Column(name="updated_at")
	private String updatedAt;

	@Column(name="customer_address_id")
	private int customerAddressId;

	@Column(name="quote_address_id")
	private int quoteAddressId;

	@Column(name="region_id")
	private int regionId;

	@Column(name="customer_id")
	private int customerId;

	@Column(name="fax")
	private String fax;

	@Column(name="region")
	private String region;

	@Column(name="postcode")
	private String postcode;

	@Column(name="lastname")
	private String lastname;

	@Column(name="street")
	private String street;

	@Column(name="city")
	private String city;

	@Column(name="email")
	private String email;

	@Column(name="telephone")
	private String telephone;

	@Column(name="country_id")
	private String countryId;

	@Column(name="firstname")
	private String firstname;

	@Column(name="address_type")
	private String addressType;

	@Column(name="prefix")
	private String prefix;

	@Column(name="middlename")
	private String middlename;

	@Column(name="suffix")
	private String suffix;

	@Column(name="company")
	private String company;

	public CheckoutOrderAddress() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public int getCustomerAddressId() {
		return customerAddressId;
	}

	public void setCustomerAddressId(int customerAddressId) {
		this.customerAddressId = customerAddressId;
	}

	public int getQuoteAddressId() {
		return quoteAddressId;
	}

	public void setQuoteAddressId(int quoteAddressId) {
		this.quoteAddressId = quoteAddressId;
	}

	public int getRegionId() {
		return regionId;
	}

	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "CheckoutOrderAddress [ id=" + id + ", orderId=" + orderId + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", customerAddressId=" + customerAddressId + ", quoteAddressId=" + quoteAddressId + ", regionId=" + regionId + ", customerId=" + customerId + ", fax=" + fax + ", region=" + region + ", postcode=" + postcode + ", lastname=" + lastname + ", street=" + street + ", city=" + city + ", email=" + email + ", telephone=" + telephone + ", countryId=" + countryId + ", firstname=" + firstname + ", addressType=" + addressType + ", prefix=" + prefix + ", middlename=" + middlename + ", suffix=" + suffix + ", company=" + company + "]";
	}
}