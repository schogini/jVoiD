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
 package com.jvoid.products.product.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.jvoid.products.product.model.Attribute;
import com.jvoid.products.product.model.AttributeGroup;
import com.jvoid.products.product.model.AttributeSet;
import com.jvoid.products.product.model.AttributeValues;
import com.jvoid.products.product.model.Categories;
import com.jvoid.products.product.model.CategoryProducts;
import com.jvoid.products.product.model.Entities;
import com.jvoid.products.product.model.Product;
import com.jvoid.products.product.model.ProductAttributeValues;
import com.jvoid.products.product.model.ProductEntityValues;
import com.jvoid.products.product.model.ProductsMaster;
import com.jvoid.products.utils.Utilities;

/**
 * All Product related operations such as add, edit, delete and product attribute functions etc.
 * 
 * @author Shajir K, Rajeev Pillai 
 * @version 1.0
 */

public class ProductsMasterService {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductEntityValuesService productEntityValuesService;
	
	@Autowired
	private EntitiesService entitiesService;
	
	@Autowired
	private CategoryProductsService categoryProductsService;
	
	@Autowired
	private AttributeService attributeService;
	
	@Autowired
	private AttributeGroupService attributeGroupService;
	
	@Autowired
	private AttributeSetService attributeSetService;
	
	@Autowired
	private AttributeValuesService attributeValuesService;
	
	@Autowired
	private ProductAttributeValuesService productAttributeValuesService;
	
	@Autowired
	private CategoriesService categoriesService;
	
	public void setCategoriesService(CategoriesService categoriesService) {
		this.categoriesService = categoriesService;
	}
	
	public void setProductAttributeValuesService(ProductAttributeValuesService productAttributeValuesService) {
		this.productAttributeValuesService = productAttributeValuesService;
	}
	
	public void setAttributeValuesService(AttributeValuesService attributeValuesService){
		this.attributeValuesService = attributeValuesService;
	}
	
	public void setAttributeSetService(AttributeSetService attributeSetService) {
		this.attributeSetService = attributeSetService;
	}
	
	public void setAttributeGroupService(AttributeGroupService attributeGroupService) {
		this.attributeGroupService = attributeGroupService;
	}
	
	public void setAttributeService(AttributeService attributeService) {
		this.attributeService = attributeService;
	}
	
	public void setCategoryProductsService(CategoryProductsService categoryProductsService) {
		this.categoryProductsService = categoryProductsService;
	}
	
	public void setProductService(ProductService ps) {
		this.productService = ps;
	}
	
	public void setProductEntityValuesService(ProductEntityValuesService productEntityValuesService) {
		this.productEntityValuesService = productEntityValuesService;
	}
	
	public void setEntitiesService(EntitiesService entitiesService) {
		this.entitiesService = entitiesService;
	}
	
	public int deleteProductAttributeValue(int id) {
		int status = 0;
		ProductAttributeValues productAttributeValue = this.productAttributeValuesService.getProductAttributeValuesById(id);
		if ( null != productAttributeValue ) {
			this.productAttributeValuesService.removeProductAttributeValues(id);
			status = 1;
		}
		return status;
	}
	
	public int updateProductAttributeValue(JSONObject jsonObject) {
		
		int id = 0;
		int productId = 0;
		int attributeValueId = 0;
		int defaultValue = 0;
		int position = 0;		
		
		try {
			id = jsonObject.getInt("id");
			productId = jsonObject.getInt("product_id");
			attributeValueId = jsonObject.getInt("attribute_value_id");
			defaultValue = jsonObject.getInt("default");
			position = jsonObject.getInt("position");
			if ( id > 0 ) {
				ProductAttributeValues productAttributeValues = this.productAttributeValuesService.getProductAttributeValuesById(id);
				if ( null != productAttributeValues ) {
					productAttributeValues.setId(id);
					productAttributeValues.setAttributeValueId(attributeValueId);
					productAttributeValues.setDefault(defaultValue);
					productAttributeValues.setProductId(productId);
					productAttributeValues.setPosition(position);
					this.productAttributeValuesService.updateProductAttributeValues(productAttributeValues);
				}else{
					id = 0;
				}
			}else{
				id = 0;
			}
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		return id;
	}
	
	public int addProductAttributeValue(JSONObject jsonObject) {
		int productAttributeValueId = 0;
		int productId = 0;
		int attributeValueId = 0;
		int defaultValue = 0;
		int position = 0;
		
		ProductAttributeValues productAttributeValues = new ProductAttributeValues();
		try {
			productId = jsonObject.getInt("product_id");
			attributeValueId = jsonObject.getInt("attribute_value_id");
			defaultValue = jsonObject.getInt("default");
			position = jsonObject.getInt("position");
			
			productAttributeValues.setId(productAttributeValueId);
			productAttributeValues.setAttributeValueId(attributeValueId);
			productAttributeValues.setDefault(defaultValue);
			productAttributeValues.setProductId(productId);
			productAttributeValues.setPosition(position);
			this.productAttributeValuesService.addProductAttributeValues(productAttributeValues);
			productAttributeValueId = productAttributeValues.getId();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return productAttributeValueId;
	}
	
	public int addAttributeValue(JSONObject jsonObject) {
		int status = 1;
		JSONObject jsonObj = jsonObject;
		try {
			JSONArray jArray = jsonObj.getJSONArray("attributeValues");
			for(int i=0; i<jArray.length(); i++) {
				JSONObject obj = jArray.getJSONObject(i);
				System.out.println(obj.toString());
				
				int attributeId = obj.getInt("attribute_id");
				int position = obj.getInt("position");
				String language = obj.getString("language");
				String value = obj.getString("value");
				int id = insertAttributeValue(attributeId, position, language, value);
				if ( id == 0 ){
					status = 0; 
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return status;
	}
	
	 public int insertAttributeValue(int attributeId, int position, String language, String value){
		System.out.println("AID:"+attributeId+",POS:"+position+",Lang:"+language+",Value:"+value);
		int attributeValueId = 0;
		AttributeValues attributeValue = new AttributeValues();
		attributeValue.setAttributeId(attributeId);
		attributeValue.setLanguage(language);
		attributeValue.setPosition(position);
		attributeValue.setValue(value);
		this.attributeValuesService.addAttributeValues(attributeValue);
		attributeValueId = attributeValue.getId();
		return attributeValueId;
	}
	  
	
	public int deleteAttribute(int attributeId){
		int status = 0;
		Attribute attribute = this.attributeService.getAttributeById(attributeId);
		if( null != attribute ) {
			this.attributeService.removeAttribute(attributeId);
			status = 1;
		}
		
		return status;
	}
	
	public int updateAttribute(JSONObject jsonObject){
		
		int attributeGroupId = 0;
		int id = 0;
		String name = null;
		try {
			id = jsonObject.getInt("id");
			name = jsonObject.getString("name");
			attributeGroupId = jsonObject.getInt("attribute_group_id");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		Attribute attribute = new Attribute();
		attribute.setAttributeGroupId(attributeGroupId);
		attribute.setId(id);
		attribute.setName(name);
		
		this.attributeService.updateAttribute(attribute);
		return id;
	}
	
	public int addAttribute(int attributeGroupId, String attributeName){
		int attributeId = 0;
		if( null == attributeName ) 
			return attributeId;
		Attribute attribute= new Attribute();
		attribute.setAttributeGroupId(attributeGroupId);
		attribute.setName(attributeName);
		this.attributeService.addAttribute(attribute);
		attributeId = attribute.getId();
		return attributeGroupId;
	}
	
	public void deleteAttributeValue(int attributeValueId) {
		this.attributeValuesService.removeAttributeValues(attributeValueId);
	}
	
	public int updateAttributeValue(JSONObject jsonObject) {
		int status = 1;
		JSONObject jsonObj = jsonObject;
		try {
			JSONArray jArray = jsonObj.getJSONArray("attributeValues");
			for(int i=0; i<jArray.length(); i++) {
				JSONObject obj = jArray.getJSONObject(i);
				System.out.println(obj.toString());
				
				int id = obj.getInt("id");
				int attributeId = obj.getInt("attribute_id");
				int position = obj.getInt("position");
				String language = obj.getString("language");
				String value = obj.getString("value");
				AttributeValues attributeValue = this.attributeValuesService.getAttributeValuesById(id);
				if ( null != attributeValue ) {
					attributeValue.setId(id);
					attributeValue.setAttributeId(attributeId);
					attributeValue.setLanguage(language);
					attributeValue.setPosition(position);
					attributeValue.setValue(value);
					if ( id > 0) {
						this.attributeValuesService.updateAttributeValues(attributeValue);
					}else{
						status = 0;
					}
				}else{
					status = 0;
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return status;
	}
		
	public int deleteAttributeGroup(int attributeGroupId){
		int status = 0;
		AttributeGroup attributeGroup = this.attributeGroupService.getAttributeGroupById(attributeGroupId);
		if( null != attributeGroup ) {
			this.attributeGroupService.removeAttributeGroup(attributeGroupId);
			status = 1;
		}
		return status;
	}
	
	public int updateAttributeGroup(JSONObject jsonObject){
		
		String attributeGroupName = null;
		int attributSetId = 0;
		int id = 0;
		
		try {
			id = jsonObject.getInt("id");
			attributeGroupName = jsonObject.getString("name");
			attributSetId = jsonObject.getInt("attribute_set_id");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		if ( id > 0 ) {
			AttributeGroup attributeGroup = this.attributeGroupService.getAttributeGroupById(id);
			
			if ( null !=  attributeGroup ) {
				attributeGroup.setId(id);
				attributeGroup.setAttributeSetId(attributSetId);
				attributeGroup.setName(attributeGroupName);
				this.attributeGroupService.updateAttributeGroup(attributeGroup);
			}else{
				id = 0;
			}
		}else{
			id = 0;
		}
		return id;
	}
	
	public int addAttributeGroup(int attributeSetId, String attributeGroupName){
		int attributeGroupId = 0;
		if( null == attributeGroupName ) 
			return attributeSetId;
		AttributeGroup attributeGroup = new AttributeGroup();
		attributeGroup.setAttributeSetId(attributeSetId);
		attributeGroup.setName(attributeGroupName);
		this.attributeGroupService.addAttributeGroup(attributeGroup);
		attributeGroupId = attributeGroup.getId();
		return attributeGroupId;
	}
	
	public int deleteAttributeSet(int attributeSetId) {
		int status = 0;
		AttributeSet attributeSet = this.attributeSetService.getAttributeSetById(attributeSetId);
		if ( null != attributeSet ) {
			this.attributeSetService.removeAttributeSet(attributeSetId);
			status = 1;
		}
		return status;
	}
	
	public int updateAttributeSet(JSONObject jsonObject) {
		int attributeSetId = 0;
		String attributeSetName = null;
		try {
			attributeSetId = jsonObject.getInt("id");
			attributeSetName = jsonObject.getString("name");
		} catch (JSONException e) {
			e.printStackTrace();
		}
						
		AttributeSet attributeSet = this.attributeSetService.getAttributeSetById(attributeSetId);
		if ( null != attributeSet ) {
			attributeSet.setId(attributeSetId);
			attributeSet.setName(attributeSetName);
			this.attributeSetService.updateAttributeSet(attributeSet);	
		}else{
			attributeSetId = 0;
		}
		return attributeSetId;
	}
	
	public int addAttributeSet(String name){
		int attributeSetId = 0;
		if( null == name ) 
			return attributeSetId;
		AttributeSet attributeSet = new AttributeSet();
		attributeSet.setName(name);
		this.attributeSetService.addAttributeSet(attributeSet);
		attributeSetId = attributeSet.getId();
		return attributeSetId;
	}
	
	public int updateProduct(ProductsMaster productsMaster) {
		
		int status = 1;
		
		int  productId = productsMaster.getId();
		Product product = new Product();
		product.setId(productId);
		product.setSku(productsMaster.getSku());
		product.setHasOptions(productsMaster.getHasMoreOption());
		product.setRequiredOptions(productsMaster.getRequiredOption());
		product.setUpdatedOn(Utilities.getCurrentDateTime());
		productService.updateproduct(product);
		
		List<Entities> listAttributes = this.entitiesService.listAttributes();
		HashMap<Integer, String> attrs = getAttributesList(listAttributes, "Product");
		System.out.println("LIST ATTR:"+listAttributes.toString());
		
		for(Entry<Integer, String> entry : attrs.entrySet()) {
		    Integer attrId = entry.getKey();
		    String attrValue = entry.getValue();
		    
		    ProductEntityValues productAttributeValues = this.productEntityValuesService.getProductAttributeValuesByProductIdAndAttributeId(productId, attrId);
		    
		    if ( null != productAttributeValues ) {
			    System.out.println("PRODUCT ATTR VALUES:"+productAttributeValues.toString());
			    
			    ProductEntityValues prodAttrValue = new ProductEntityValues();
			    prodAttrValue.setProductId(productId);
			    prodAttrValue.setAttributeId(attrId);
			    prodAttrValue.setLanguage("enUS");
			    prodAttrValue.setId(productAttributeValues.getId());
			    prodAttrValue.setValue(productsMaster.toAttributedHashMap().get(attrValue));
			    productEntityValuesService.updateProductAttributeValues(prodAttrValue);
			    //System.out.println("custAttrValue-->"+prodAttrValue.toString());
		    }
		}
		return status;
	}

	public void deleteProductFromCategory(int categoryId, int productId) {
		CategoryProducts categoryProduct = this.categoryProductsService.getCategoryProductByCategoryIdAndProductId(categoryId, productId);
		this.categoryProductsService.removeCategoryProducts(categoryProduct.getId());
	}
	
	public int addProductToCategory(int categoryId, int productId) {
		CategoryProducts categoryProduct = new CategoryProducts();
		categoryProduct.setProductId(productId);
		categoryProduct.setCategoryId(categoryId);
		this.categoryProductsService.addCategoryProducts(categoryProduct);
		int categoryProductId = categoryProduct.getId();
		return categoryProductId;
	}
	
	public Integer addProduct(ProductsMaster productsMaster) {
		int pid = 0;
		
		Product product = new Product();
		product.setCreatedOn(Utilities.getCurrentDateTime());
		product.setUpdatedOn(Utilities.getCurrentDateTime());
		product.setSku(productsMaster.getSku());
		product.setHasOptions(productsMaster.getHasMoreOption());
		product.setRequiredOptions(productsMaster.getRequiredOption());
		product.setType(productsMaster.getType());
	
		productService.addproduct(product);
		pid = product.getId();
		System.out.println("customer==>"+product.toString());
		
		//add CategoryProduct entry
		int categoryId = productsMaster.getCategoryId();
		int categoryProductId = 0;
		if ( categoryId > 0 ){
			categoryProductId = addProductToCategory(categoryId, pid);
		}
		
		/*//rest call
		final String SERVER_URI = "http://localhost:8080/jvoidattributes/listProductAttriburtes";
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
				
				ProductAttributeValues prodAttrValue = new ProductAttributeValues();
			    prodAttrValue.setProductId(product.getId());
			    prodAttrValue.setLanguage("enUS");
			    prodAttrValue.setAttributeId(attrId);
			    prodAttrValue.setValue(productsMaster.toAttributedHashMap().get(attrValue));
			    productAttributeValuesService.addProductAttributeValues(prodAttrValue);
			    System.out.println("custAttrValue-->"+prodAttrValue.toString());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		*/
		
		List<Entities> listAttributes = this.entitiesService.listAttributes();
		HashMap<Integer, String> attrs = getAttributesList(listAttributes, "Product");
		System.out.println("attrs==>"+attrs.toString());
		
		for(Entry<Integer, String> entry : attrs.entrySet()) {
		    Integer attrId = entry.getKey();
		    String attrValue = entry.getValue();
		    
		    ProductEntityValues prodAttrValue = new ProductEntityValues();
		    prodAttrValue.setProductId(product.getId());
		    prodAttrValue.setLanguage("enUS");
		    prodAttrValue.setAttributeId(attrId);
		    prodAttrValue.setValue(productsMaster.toAttributedHashMap().get(attrValue));
		    productEntityValuesService.addProductAttributeValues(prodAttrValue);
		    System.out.println("custAttrValue-->"+prodAttrValue.toString());
		}
		return pid;  
	}
	
	public String deleteProduct(int id) {
		int status = removeProductAttributes(id);
		JSONObject jsonObj = new JSONObject();
		try {
			jsonObj.put("status", status);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		String message = "Deletion failed";
		if ( status == 1 ){ 
			this.productService.removeproduct(id);
			//System.out.println("PID:"+id);
			message = "Successfully deleted.";
		}
		try {
			jsonObj.put("message", message);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObj.toString();
	}
	
	public int removeProductAttributes(int id) {
		int status = 1;
		List<ProductEntityValues> attrs = this.productEntityValuesService.listProductAttributeValues();
		List<Integer> deleteIds = new ArrayList<>();
		for(int i=0; i<attrs.size(); i++) {
			if ( attrs.get(i).getProductId() == id ){
				deleteIds.add(attrs.get(i).getId());
				//System.out.println(attrs.get(i).getId());
			}
		}
		
		if( deleteIds.size() > 0 ) {
			for(int i=0; i<deleteIds.size(); i++){
				this.productEntityValuesService.removeProductAttributeValues(deleteIds.get(i).intValue());
			}
		}else{
			status = 0;
		}
		return status;
	}
	
	public List<ProductsMaster> getProductMasterListUsingChildCategory(int categoryId) {
		List<ProductsMaster> productsMasterList = new ArrayList<ProductsMaster>();
		
		List<CategoryProducts> categoryProductList = this.categoryProductsService.getCategoryProductsByCategoryId(categoryId);
		for(int i=0; i<categoryProductList.size(); i++) {
			
			int productId = categoryProductList.get(i).getProductId();
			Product product = this.productService.getproductById(productId);
			
			ProductsMaster productMaster = new ProductsMaster();
			productMaster.setCategoryId(categoryId);
			productMaster.setId(productId);
			productMaster.setSku(product.getSku());
			productMaster.setType(product.getType());
			productMaster.setHasMoreOption(product.getHasOptions());
			productMaster.setRequiredOption(product.getRequiredOptions());
			
						
			List<ProductEntityValues> productAttributeValuesList = productEntityValuesService.listProductAttributeValuesByProductId(productId);
			
			
			for (ProductEntityValues productAttributeValues : productAttributeValuesList) {
				switch (productAttributeValues.getAttributeId()) {
				case 1:
					productMaster.setName(productAttributeValues.getValue());
					break;
				case 2:
					productMaster.setDescription(productAttributeValues.getValue());
					break;
				case 3:
					productMaster.setShortDescription(productAttributeValues.getValue());
					break;
				case 4:
					productMaster.setSku(productAttributeValues.getValue());
					break;
				case 5:
					productMaster.setWeight(Float.parseFloat(productAttributeValues.getValue()));
					break;
				case 6:
					productMaster.setFromDate(productAttributeValues.getValue());
					break;
				case 7:
					productMaster.setToDate(productAttributeValues.getValue());
					break;
				case 8:
					productMaster.setStatus(productAttributeValues.getValue());
					break;
				case 9:
					productMaster.setUrlKey(productAttributeValues.getValue());
					break;
				case 10:
					productMaster.setVisibility(Integer.parseInt(productAttributeValues.getValue()));
					break;
				case 11:
					productMaster.setCountry(productAttributeValues.getValue());
					break;
				case 12:
					productMaster.setPrice(Float.parseFloat(productAttributeValues.getValue()));
					break;
				case 13:
					productMaster.setImage(productAttributeValues.getValue());
					break;
				case 14:
					productMaster.setQty(Integer.parseInt(productAttributeValues.getValue()));
					break;
				case 15:
					productMaster.setStock(Integer.parseInt(productAttributeValues.getValue()));
					break;
				case 16:
					productMaster.setType(productAttributeValues.getValue());
					break;
				case 17:
					productMaster.setHasMoreOption(Integer.parseInt(productAttributeValues.getValue()));
					break;
				case 18:
					productMaster.setRequiredOption(Integer.parseInt(productAttributeValues.getValue()));
					break;
				default:
					break;
				}
			}
			productsMasterList.add(productMaster);
		}
		
		return productsMasterList;
	}
	
	/*public List<Integer> getAllChildrenCategoryIdsOld(int parentCategoryId) {
		
		List<Integer> childrenCategoryIdsList = new ArrayList<Integer>();
		List<Categories> categoryList = this.categoriesService.getAllChildCategories(parentCategoryId);
		childrenCategoryIdsList.add(parentCategoryId);
		for(int i=0; i<categoryList.size(); i++) {
			childrenCategoryIdsList.add(categoryList.get(i).getId());
		}
		
		return childrenCategoryIdsList;
	}*/
	
	public List<Integer> getAllChildrenCategoryIds(int parentCategoryId) {
		
		List<Integer> childrenCategoryIdsList = new ArrayList<Integer>();
		List<Categories> categoryList = this.categoriesService.getAllChildCategories(parentCategoryId);
		childrenCategoryIdsList.add(parentCategoryId);
		for(int i=0; i<categoryList.size(); i++) {
			childrenCategoryIdsList.add(categoryList.get(i).getId());
			List<Integer> childrenList = getAllChildrenCategoryIds(categoryList.get(i).getId());
			System.out.println("CHILDREN:"+childrenList.toString());
			childrenCategoryIdsList.addAll(childrenList);
		}
		Collections.sort(childrenCategoryIdsList);
		
		List<Integer> UniqueCategoryIdsList = new ArrayList<Integer>();
		for(int i=0; i<childrenCategoryIdsList.size(); i++) {
			
			if ( !UniqueCategoryIdsList.contains(childrenCategoryIdsList.get(i).intValue())){
				UniqueCategoryIdsList.add(childrenCategoryIdsList.get(i).intValue());
			}
		}
		return UniqueCategoryIdsList;
	}
	
	public List<ProductsMaster> getProductMasterListUsingParentCategory(int categoryId) {
		
		//get children ids
		List<Integer> childrenCategoryIdsList = getAllChildrenCategoryIds(categoryId);
		
		List<ProductsMaster> productsMasterList = new ArrayList<ProductsMaster>();
		
		for (int i=0; i<childrenCategoryIdsList.size(); i++) {
			
			int tmpCategoryId = childrenCategoryIdsList.get(i).intValue();
			
			List<CategoryProducts> categoryProductList = this.categoryProductsService.getCategoryProductsByCategoryId(tmpCategoryId);
			
			for(int j=0; j<categoryProductList.size(); j++) {
				
				int productId = categoryProductList.get(j).getProductId();
				Product product = this.productService.getproductById(productId);
				ProductsMaster productMaster = new ProductsMaster();
				productMaster.setCategoryId(categoryId);
				productMaster.setId(productId);
				productMaster.setSku(product.getSku());
				productMaster.setType(product.getType());
				productMaster.setHasMoreOption(product.getHasOptions());
				productMaster.setRequiredOption(product.getRequiredOptions());
							
				List<ProductEntityValues> productAttributeValuesList = productEntityValuesService.listProductAttributeValuesByProductId(productId);
				
				for (ProductEntityValues productAttributeValues : productAttributeValuesList) {
					switch (productAttributeValues.getAttributeId()) {
					case 1:
						productMaster.setName(productAttributeValues.getValue());
						break;
					case 2:
						productMaster.setDescription(productAttributeValues.getValue());
						break;
					case 3:
						productMaster.setShortDescription(productAttributeValues.getValue());
						break;
					case 4:
						productMaster.setSku(productAttributeValues.getValue());
						break;
					case 5:
						productMaster.setWeight(Float.parseFloat(productAttributeValues.getValue()));
						break;
					case 6:
						productMaster.setFromDate(productAttributeValues.getValue());
						break;
					case 7:
						productMaster.setToDate(productAttributeValues.getValue());
						break;
					case 8:
						productMaster.setStatus(productAttributeValues.getValue());
						break;
					case 9:
						productMaster.setUrlKey(productAttributeValues.getValue());
						break;
					case 10:
						productMaster.setVisibility(Integer.parseInt(productAttributeValues.getValue()));
						break;
					case 11:
						productMaster.setCountry(productAttributeValues.getValue());
						break;
					case 12:
						productMaster.setPrice(Float.parseFloat(productAttributeValues.getValue()));
						break;
					case 13:
						productMaster.setImage(productAttributeValues.getValue());
						break;
					case 14:
						productMaster.setQty(Integer.parseInt(productAttributeValues.getValue()));
						break;
					case 15:
						productMaster.setStock(Integer.parseInt(productAttributeValues.getValue()));
						break;
					case 16:
						productMaster.setType(productAttributeValues.getValue());
						break;
					case 17:
						productMaster.setHasMoreOption(Integer.parseInt(productAttributeValues.getValue()));
						break;
					case 18:
						productMaster.setRequiredOption(Integer.parseInt(productAttributeValues.getValue()));
						break;
					default:
						break;
					}
				}
				productsMasterList.add(productMaster);
			}
			
		}
		
		return productsMasterList;
	}
	
	public List<ProductsMaster> getProductByCategory(int categoryId) {
		
		List<ProductsMaster> productsMasterList = new ArrayList<ProductsMaster>();
		//check whether the category has children
		Categories category = this.categoriesService.getCategoriesById(categoryId);
		int parentId = category.getParentId();
		productsMasterList = getProductMasterListUsingParentCategory(categoryId);
		/*if ( parentId == 0 ) {
			productsMasterList = getProductMasterListUsingParentCategory(categoryId);
		}else{
			productsMasterList = getProductMasterListUsingChildCategory(categoryId);
		}*/
		
		return productsMasterList;
	}
	
	public ProductsMaster getProductById(int id) {
		ProductsMaster productsMaster = new ProductsMaster();
		
		List<Product> products = productService.listproducts();
		for (Product product : products) {
			if( product.getId() == id ) {
			
			List<ProductEntityValues> productAttributeValuesList = productEntityValuesService.listProductAttributeValuesByProductId(product.getId());
			ProductsMaster pm = new ProductsMaster();
			pm.setId(product.getId());
			for (ProductEntityValues productAttributeValues : productAttributeValuesList) {
				switch (productAttributeValues.getAttributeId()) {
				case 1:
					pm.setName(productAttributeValues.getValue());
					break;
				case 2:
					pm.setDescription(productAttributeValues.getValue());
					break;
				case 3:
					pm.setShortDescription(productAttributeValues.getValue());
					break;
				case 4:
					pm.setSku(productAttributeValues.getValue());
					break;
				case 5:
					pm.setWeight(Float.parseFloat(productAttributeValues.getValue()));
					break;
				case 6:
					pm.setFromDate(productAttributeValues.getValue());
					break;
				case 7:
					pm.setToDate(productAttributeValues.getValue());
					break;
				case 8:
					pm.setStatus(productAttributeValues.getValue());
					break;
				case 9:
					pm.setUrlKey(productAttributeValues.getValue());
					break;
				case 10:
					pm.setVisibility(Integer.parseInt(productAttributeValues.getValue()));
					break;
				case 11:
					pm.setCountry(productAttributeValues.getValue());
					break;
				case 12:
					pm.setPrice(Float.parseFloat(productAttributeValues.getValue()));
					break;
				case 13:
					pm.setImage(productAttributeValues.getValue());
					break;
				case 14:
					pm.setQty(Integer.parseInt(productAttributeValues.getValue()));
					break;
				case 15:
					pm.setStock(Integer.parseInt(productAttributeValues.getValue()));
					break;
				case 16:
					pm.setType(productAttributeValues.getValue());
					break;
				case 17:
					pm.setHasMoreOption(Integer.parseInt(productAttributeValues.getValue()));
					break;
				case 18:
					pm.setRequiredOption(Integer.parseInt(productAttributeValues.getValue()));
					break;
				default:
					break;
				}
			}
			productsMaster = pm;
			break;
			}else{
				continue;
			}
			
		}
		
		return productsMaster;
	}
	public List<ProductsMaster> getAllProducts(){
		List<ProductsMaster> listOfProducts = new ArrayList<ProductsMaster>();
		List<Product> products = productService.listproducts();
		for (Product product : products) {
			List<ProductEntityValues> productAttributeValuesList = productEntityValuesService.listProductAttributeValuesByProductId(product.getId());
			ProductsMaster pm = new ProductsMaster();
			pm.setId(product.getId());
			for (ProductEntityValues productAttributeValues : productAttributeValuesList) {
				switch (productAttributeValues.getAttributeId()) {
				case 1:
					pm.setName(productAttributeValues.getValue());
					break;
				case 2:
					pm.setDescription(productAttributeValues.getValue());
					break;
				case 3:
					pm.setShortDescription(productAttributeValues.getValue());
					break;
				case 4:
					pm.setSku(productAttributeValues.getValue());
					break;
				case 5:
					pm.setWeight(Float.parseFloat(productAttributeValues.getValue()));
					break;
				case 6:
					pm.setFromDate(productAttributeValues.getValue());
					break;
				case 7:
					pm.setToDate(productAttributeValues.getValue());
					break;
				case 8:
					pm.setStatus(productAttributeValues.getValue());
					break;
				case 9:
					pm.setUrlKey(productAttributeValues.getValue());
					break;
				case 10:
					pm.setVisibility(Integer.parseInt(productAttributeValues.getValue()));
					break;
				case 11:
					pm.setCountry(productAttributeValues.getValue());
					break;
				case 12:
					pm.setPrice(Float.parseFloat(productAttributeValues.getValue()));
					break;
				case 13:
					pm.setImage(productAttributeValues.getValue());
					break;
				case 14:
					pm.setQty(Integer.parseInt(productAttributeValues.getValue()));
					break;
				case 15:
					pm.setStock(Integer.parseInt(productAttributeValues.getValue()));
					break;
				case 16:
					pm.setType(productAttributeValues.getValue());
					break;
				case 17:
					pm.setHasMoreOption(Integer.parseInt(productAttributeValues.getValue()));
					break;
				case 18:
					pm.setRequiredOption(Integer.parseInt(productAttributeValues.getValue()));
					break;
				default:
					break;
				}
			}
			listOfProducts.add(pm);
		}
		return listOfProducts;
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
