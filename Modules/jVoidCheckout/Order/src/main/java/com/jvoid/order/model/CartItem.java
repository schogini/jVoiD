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

public class CartItem {

	private int id;

	private int quoteId;

	private String createdAt;

	private String updatedAt;

	private int productId;

	private int attributeId;

	private int storeId;

	private int parentId;

	private int isVirtual;

	private String sku;

	private String name;

	private String description;

	private int freeShipping;

	private double weight;

	private double quantity;

	private double price;

	private double basePrice;

	private double rowTotal;

	private double baseRowTotal;

	private double rowWeight;

	private String productType;

	public CartItem() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuoteId() {
		return quoteId;
	}

	public void setQuoteId(int quoteId) {
		this.quoteId = quoteId;
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
		return "CheckoutQuoteItem [ id=" + id + ", quoteId=" + quoteId + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", productId=" + productId + ", attributeId=" + attributeId + ", storeId=" + storeId + ", parentId=" + parentId + ", isVirtual=" + isVirtual + ", sku=" + sku + ", name=" + name + ", description=" + description + ", freeShipping=" + freeShipping + ", weight=" + weight + ", quantity=" + quantity + ", price=" + price + ", basePrice=" + basePrice + ", rowTotal=" + rowTotal + ", baseRowTotal=" + baseRowTotal + ", rowWeight=" + rowWeight + ", productType=" + productType + "]";
	}
}