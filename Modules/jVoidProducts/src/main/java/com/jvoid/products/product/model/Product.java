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
 package com.jvoid.products.product.model; 

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="created_on")
	private String createdOn;

	@Column(name="updated_on")
	private String updatedOn;

	@Column(name="sku")
	private String sku;

	@Column(name="type")
	private String type;

	@Column(name="has_options")
	private int hasOptions;

	@Column(name="required_options")
	private int requiredOptions;

	public Product() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getHasOptions() {
		return hasOptions;
	}

	public void setHasOptions(int hasOptions) {
		this.hasOptions = hasOptions;
	}

	public int getRequiredOptions() {
		return requiredOptions;
	}

	public void setRequiredOptions(int requiredOptions) {
		this.requiredOptions = requiredOptions;
	}

	@Override
	public String toString() {
		return "products [ id=" + id + ", createdOn=" + createdOn + ", updatedOn=" + updatedOn + ", sku=" + sku + ", type=" + type + ", hasOptions=" + hasOptions + ", requiredOptions=" + requiredOptions + "]";
	}
}