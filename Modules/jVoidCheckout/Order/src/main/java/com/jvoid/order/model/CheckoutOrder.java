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
package com.jvoid.order.model; 

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "checkout_order")
public class CheckoutOrder {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="store_id")
	private int storeId;

	@Column(name="created_at")
	private String createdAt;

	@Column(name="updated_at")
	private String updatedAt;

	@Column(name="is_active")
	private int isActive;

	@Column(name="is_virtual")
	private int isVirtual;

	@Column(name="is_multi_shipping")
	private int isMultiShipping;

	@Column(name="status")
	private String status;

	@Column(name="items_count")
	private int itemsCount;

	@Column(name="items_quantity")
	private double itemsQuantity;

	@Column(name="grand_total")
	private double grandTotal;

	@Column(name="base_grand_total")
	private double baseGrandTotal;

	@Column(name="checkout_method")
	private String checkoutMethod;
	
	@Column(name="checkout_comment")
	private String checkoutComment;

	@Column(name="customer_id")
	private int customerId;

	@Column(name="customer_group_id")
	private int customerGroupId;

	@Column(name="customer_email")
	private String customerEmail;

	@Column(name="customer_prefix")
	private String customerPrefix;

	@Column(name="customer_firstname")
	private String customerFirstname;

	@Column(name="customer_middlename")
	private String customerMiddlename;

	@Column(name="customer_lastname")
	private String customerLastname;

	@Column(name="customer_suffix")
	private String customerSuffix;

	@Column(name="customer_dob")
	private String customerDob;

	@Column(name="customer_is_guest")
	private int customerIsGuest;

	@Column(name="remote_ip")
	private String remoteIp;

	@Column(name="customer_gender")
	private String customerGender;

	@Column(name="subtotal")
	private double subtotal;

	@Column(name="base_subtotal")
	private double baseSubtotal;

	@Column(name="is_changed")
	private int isChanged;

	public CheckoutOrder() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
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

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public int getIsVirtual() {
		return isVirtual;
	}

	public void setIsVirtual(int isVirtual) {
		this.isVirtual = isVirtual;
	}

	public int getIsMultiShipping() {
		return isMultiShipping;
	}

	public void setIsMultiShipping(int isMultiShipping) {
		this.isMultiShipping = isMultiShipping;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getItemsCount() {
		return itemsCount;
	}

	public void setItemsCount(int itemsCount) {
		this.itemsCount = itemsCount;
	}

	public double getItemsQuantity() {
		return itemsQuantity;
	}

	public void setItemsQuantity(double itemsQuantity) {
		this.itemsQuantity = itemsQuantity;
	}

	public double getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}

	public double getBaseGrandTotal() {
		return baseGrandTotal;
	}

	public void setBaseGrandTotal(double baseGrandTotal) {
		this.baseGrandTotal = baseGrandTotal;
	}

	public String getCheckoutMethod() {
		return checkoutMethod;
	}

	public void setCheckoutMethod(String checkoutMethod) {
		this.checkoutMethod = checkoutMethod;
	}

	public String getCheckoutComment() {
		return checkoutComment;
	}

	public void setCheckoutComment(String checkoutComment) {
		this.checkoutComment = checkoutComment;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getCustomerGroupId() {
		return customerGroupId;
	}

	public void setCustomerGroupId(int customerGroupId) {
		this.customerGroupId = customerGroupId;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerPrefix() {
		return customerPrefix;
	}

	public void setCustomerPrefix(String customerPrefix) {
		this.customerPrefix = customerPrefix;
	}

	public String getCustomerFirstname() {
		return customerFirstname;
	}

	public void setCustomerFirstname(String customerFirstname) {
		this.customerFirstname = customerFirstname;
	}

	public String getCustomerMiddlename() {
		return customerMiddlename;
	}

	public void setCustomerMiddlename(String customerMiddlename) {
		this.customerMiddlename = customerMiddlename;
	}

	public String getCustomerLastname() {
		return customerLastname;
	}

	public void setCustomerLastname(String customerLastname) {
		this.customerLastname = customerLastname;
	}

	public String getCustomerSuffix() {
		return customerSuffix;
	}

	public void setCustomerSuffix(String customerSuffix) {
		this.customerSuffix = customerSuffix;
	}

	public String getCustomerDob() {
		return customerDob;
	}

	public void setCustomerDob(String customerDob) {
		this.customerDob = customerDob;
	}

	public int getCustomerIsGuest() {
		return customerIsGuest;
	}

	public void setCustomerIsGuest(int customerIsGuest) {
		this.customerIsGuest = customerIsGuest;
	}

	public String getRemoteIp() {
		return remoteIp;
	}

	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}

	public String getCustomerGender() {
		return customerGender;
	}

	public void setCustomerGender(String customerGender) {
		this.customerGender = customerGender;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getBaseSubtotal() {
		return baseSubtotal;
	}

	public void setBaseSubtotal(double baseSubtotal) {
		this.baseSubtotal = baseSubtotal;
	}

	public int getIsChanged() {
		return isChanged;
	}

	public void setIsChanged(int isChanged) {
		this.isChanged = isChanged;
	}

	@Override
	public String toString() {
		return "CheckoutOrder [ id=" + id + ", storeId=" + storeId + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", isActive=" + isActive + ", isVirtual=" + isVirtual + ", isMultiShipping=" + isMultiShipping + ", status=" + status + ", itemsCount=" + itemsCount + ", itemsQuantity=" + itemsQuantity + ", grandTotal=" + grandTotal + ", baseGrandTotal=" + baseGrandTotal + ", checkoutMethod=" + checkoutMethod + ", checkoutComment=" + checkoutComment + ", customerId=" + customerId + ", customerGroupId=" + customerGroupId + ", customerEmail=" + customerEmail + ", customerPrefix=" + customerPrefix + ", customerFirstname=" + customerFirstname + ", customerMiddlename=" + customerMiddlename + ", customerLastname=" + customerLastname + ", customerSuffix=" + customerSuffix + ", customerDob=" + customerDob + ", customerIsGuest=" + customerIsGuest + ", remoteIp=" + remoteIp + ", customerGender=" + customerGender + ", subtotal=" + subtotal + ", baseSubtotal=" + baseSubtotal + ", isChanged=" + isChanged + "]";
	}
}