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
package com.jvoid.core.model; 

import java.util.HashMap;

public class ProductsMaster {

	
	private int id;
	private String name;
	private String description;
	private String shortDescription;
	private String sku;
	private float weight;
	private String fromDate;
	private String toDate;
	private String status;
	private String urlKey;
	private int visibility;
	private String country;
	private float price;
	private String image;
	private int qty;
	private int stock;
	private String type;
	private int hasMoreOption;
	private int requiredOption;
	private int categoryId;

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getRequiredOption() {
		return requiredOption;
	}

	public void setRequiredOption(int requiredOption) {
		this.requiredOption = requiredOption;
	}

	public int getHasMoreOption() {
		return hasMoreOption;
	}

	public void setHasMoreOption(int hasMoreOption) {
		this.hasMoreOption = hasMoreOption;
	}

//	public ProductsMaster() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUrlKey() {
		return urlKey;
	}

	public void setUrlKey(String urlKey) {
		this.urlKey = urlKey;
	}

	public int getVisibility() {
		return visibility;
	}

	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "ProductsMaster [name=" + name + ", description=" + description
				+ ", shortDescription=" + shortDescription + ", sku=" + sku
				+ ", weight=" + weight + ", fromDate=" + fromDate + ", toDate="
				+ toDate + ", status=" + status + ", urlKey=" + urlKey
				+ ", visibility=" + visibility + ", country=" + country
				+ ", price=" + price + ", image=" + image + ", qty=" + qty
				+ ", stock=" + stock + ", type=" + type + ", hasMoreOption="
				+ hasMoreOption + ", requiredOption=" + requiredOption + "]";
	}
	
	public HashMap<String, String> toAttributedHashMap(){
		HashMap<String, String> attributedMap = new HashMap<String, String>();
		
		attributedMap.put("name", name);
		attributedMap.put("description", description);
		attributedMap.put("short_description", shortDescription);
		attributedMap.put("sku", sku);
		attributedMap.put("weight", weight+"");
		attributedMap.put("from_date", fromDate);
		attributedMap.put("to_date", toDate);
		attributedMap.put("status", status);
		attributedMap.put("url_key", urlKey);
		attributedMap.put("visibility", visibility+"");
		attributedMap.put("country", country);
		attributedMap.put("price", price+"");
		attributedMap.put("image", image);
		attributedMap.put("qty", qty+"");
		attributedMap.put("stock", stock+"");
		attributedMap.put("type", type);
		attributedMap.put("has_more_option", hasMoreOption+"");
		attributedMap.put("required_option", requiredOption+"");
		
		return attributedMap;
	}
}