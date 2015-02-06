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
@Table(name = "checkout_order_item")
public class CheckoutOrderItem {

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

	@Column(name="product_id")
	private int productId;

	@Column(name="attribute_id")
	private int attributeId;

	@Column(name="store_id")
	private int storeId;

	@Column(name="parent_id")
	private int parentId;

	@Column(name="is_virtual")
	private int isVirtual;

	@Column(name="sku")
	private String sku;

	@Column(name="name")
	private String name;

	@Column(name="description")
	private String description;

	@Column(name="free_shipping")
	private int freeShipping;

	@Column(name="weight")
	private double weight;

	@Column(name="quantity")
	private double quantity;

	@Column(name="price")
	private double price;

	@Column(name="base_price")
	private double basePrice;

	@Column(name="row_total")
	private double rowTotal;

	@Column(name="base_row_total")
	private double baseRowTotal;

	@Column(name="row_weight")
	private double rowWeight;

	@Column(name="product_type")
	private String productType;

	public CheckoutOrderItem() {}

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

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getAttributeId() {
		return attributeId;
	}

	public void setAttributeId(int attributeId) {
		this.attributeId = attributeId;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getIsVirtual() {
		return isVirtual;
	}

	public void setIsVirtual(int isVirtual) {
		this.isVirtual = isVirtual;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getFreeShipping() {
		return freeShipping;
	}

	public void setFreeShipping(int freeShipping) {
		this.freeShipping = freeShipping;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public double getRowTotal() {
		return rowTotal;
	}

	public void setRowTotal(double rowTotal) {
		this.rowTotal = rowTotal;
	}

	public double getBaseRowTotal() {
		return baseRowTotal;
	}

	public void setBaseRowTotal(double baseRowTotal) {
		this.baseRowTotal = baseRowTotal;
	}

	public double getRowWeight() {
		return rowWeight;
	}

	public void setRowWeight(double rowWeight) {
		this.rowWeight = rowWeight;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	@Override
	public String toString() {
		return "CheckoutOrderItem [ id=" + id + ", orderId=" + orderId + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", productId=" + productId + ", attributeId=" + attributeId + ", storeId=" + storeId + ", parentId=" + parentId + ", isVirtual=" + isVirtual + ", sku=" + sku + ", name=" + name + ", description=" + description + ", freeShipping=" + freeShipping + ", weight=" + weight + ", quantity=" + quantity + ", price=" + price + ", basePrice=" + basePrice + ", rowTotal=" + rowTotal + ", baseRowTotal=" + baseRowTotal + ", rowWeight=" + rowWeight + ", productType=" + productType + "]";
	}
}