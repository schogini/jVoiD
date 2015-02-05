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
 package com.jvoid.products.product.model; 

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Product_attribute_values")
public class ProductAttributeValues {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="product_id")
	private int productId;

	@Column(name="attribute_value_id")
	private int attributeValueId;

	@Column(name="default_value")
	private int defaultValue;

	@Column(name="position")
	private int position;

	public ProductAttributeValues() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getAttributeValueId() {
		return attributeValueId;
	}

	public void setAttributeValueId(int attributeValueId) {
		this.attributeValueId = attributeValueId;
	}

	public int getDefault() {
		return defaultValue;
	}

	public void setDefault(int defaultValue) {
		this.defaultValue = defaultValue;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "ProductAttributeValues [ id=" + id + ", productId=" + productId + ", attributeValueId=" + attributeValueId + ", default=" + defaultValue + ", position=" + position + "]";
	}
}