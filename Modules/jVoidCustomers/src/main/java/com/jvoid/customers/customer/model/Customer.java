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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="customer_group_id")
	private int customerGroupId;

	@Column(name="email")
	private String email;

	@Column(name="created_at")
	private String createdAt;

	@Column(name="updated_at")
	private String updatedAt;

	@Column(name="is_acive")
	private int isAcive;

	public Customer() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerGroupId() {
		return customerGroupId;
	}

	public void setCustomerGroupId(int customerGroupId) {
		this.customerGroupId = customerGroupId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public int getIsAcive() {
		return isAcive;
	}

	public void setIsAcive(int isAcive) {
		this.isAcive = isAcive;
	}

	@Override
	public String toString() {
		return "Customer [ id=" + id + ", customerGroupId=" + customerGroupId + ", email=" + email + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", isAcive=" + isAcive + "]";
	}
}