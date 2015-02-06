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
 package com.jvoid.products.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jvoid.products.product.model.CategoryMaster;
import com.jvoid.products.product.model.ProductsMaster;
import com.jvoid.products.product.service.CategoriesService;
import com.jvoid.products.product.service.CategoryProductsMasterService;
import com.jvoid.products.product.service.CategoryProductsService;
import com.jvoid.products.product.service.ProductsMasterService;

/**
 * Controller for Product
 * 
 * @author Shajir K, Rajeev Pillai
 * @version 1.0
 */

@Controller
@RequestMapping("/")
public class JVoidProductController {	
	@Autowired
	private ProductsMasterService productsMasterService;
	
	@Autowired
	private CategoryProductsService categoryProductsService;
	
	@Autowired
	private CategoriesService categoriesService;
	
	@Autowired
	private CategoryProductsMasterService categoryProductsMasterService;
	
	public void setProductsMasterService(ProductsMasterService productsMasterService){
		this.productsMasterService = productsMasterService;
	}
	
	public void setCategoryProductsMasterService(CategoryProductsMasterService categoryProductsMasterService) {
		this.categoryProductsMasterService = categoryProductsMasterService;
	}
	
	public void setCategoriesService(CategoriesService categoriesService){
		this.categoriesService = categoriesService;
	}
	
	public void setCategoryProductsService(CategoryProductsService categoryProductsService){
		this.categoryProductsService = categoryProductsService;
	}
	
	
	@RequestMapping
    public @ResponseBody String list() {
		//this.categoryProductsMasterService.updateAllChildrenCountAndPath();
		
		/*List<Integer> ids = this.productsMasterService.getAllChildrenCategoryIdsNew(1);
		System.out.println("IDs:"+ids.toString());*/
		return "Welcome to Jvoid Products";
	}
	
	
	
	@RequestMapping(value = "productattributevalue/delete", method = RequestMethod.GET)
	public @ResponseBody String deleteProductAttributeValue(@RequestParam(required = false, value = "callback") String callback,
			@RequestParam(required = false, value = "params") JSONObject jsonParams, HttpServletResponse response){
		
		JSONObject jparams = jsonParams;
		int productAttributeValueId = 0;
		try {
			productAttributeValueId = jparams.getInt("id");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		JSONObject jsonObject = new JSONObject();
		if( productAttributeValueId > 0 ) {
			int status = this.productsMasterService.deleteProductAttributeValue(productAttributeValueId);
			if( status == 1 ) {
				try {
					jsonObject.put("status", 1);
					jsonObject.put("message", "ProductAttributeValue deleted successfully.");
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}else{
				try {
					jsonObject.put("status", 0);
					jsonObject.put("message", "Failed to delete ProductAttributeValue.");
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}else{
			try {
				jsonObject.put("status", 0);
				jsonObject.put("message", "Failed to delete ProductAttributeValue.");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		return jsonObject.toString();
	}
	@RequestMapping(value = "productattributevalue/update", method = RequestMethod.GET)
	public @ResponseBody String updateProductAttributeValue(@RequestParam(required = false, value = "callback") String callback,
			@RequestParam(required = false, value = "params") JSONObject jsonParams, HttpServletResponse response){
		
		JSONObject jparams = jsonParams;
		JSONObject mainObj = new JSONObject();
		
		int id = 100;
		int productId = 1;
		int attributeValueId = 1;
		int defaultValue = 0;
		int position = 1;
		
		try {
			mainObj.put("id", id);
			mainObj.put("product_id", productId);
			mainObj.put("attribute_value_id", attributeValueId);
			mainObj.put("default", defaultValue);
			mainObj.put("position", position);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		System.out.println(mainObj.toString());
		
		int productAttributeValueId = this.productsMasterService.updateProductAttributeValue(mainObj);
		
		JSONObject jsonObject = new JSONObject();
		if ( productAttributeValueId > 0 ) {
			try {
				jsonObject.put("status", 1);
				jsonObject.put("id", productAttributeValueId);
				jsonObject.put("message", "ProductAttributeValue updated successfully.");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}else{
			try {
				jsonObject.put("status", 0);
				jsonObject.put("message", "Failed to update ProductAttributeValue.");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return jsonObject.toString();
		
	}
	
	@RequestMapping(value = "productattributevalue/add", method = RequestMethod.GET)
	public @ResponseBody String addProductAttributeValue(@RequestParam(required = false, value = "callback") String callback,
			@RequestParam(required = false, value = "params") JSONObject jsonParams, HttpServletResponse response){
		
		JSONObject jparams = jsonParams;
		JSONObject mainObj = new JSONObject();
		
		int id = 0;
		int productId = 1;
		int attributeValueId = 1;
		int defaultValue = 1;
		int position = 1;
		
		try {
			mainObj.put("product_id", productId);
			mainObj.put("attribute_value_id", attributeValueId);
			mainObj.put("default", defaultValue);
			mainObj.put("position", position);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		System.out.println(mainObj.toString());
		
		int productAttributeValueId = this.productsMasterService.addProductAttributeValue(mainObj);
		
		JSONObject jsonObject = new JSONObject();
		if ( productAttributeValueId > 0 ) {
			try {
				jsonObject.put("status", 1);
				jsonObject.put("id", productAttributeValueId);
				jsonObject.put("message", "ProductAttributeValue inserted successfully.");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}else{
			try {
				jsonObject.put("status", 0);
				jsonObject.put("message", "Failed to insert ProductAttributeValue.");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return jsonObject.toString();
		
	}
	
	@RequestMapping(value = "attributevalue/delete", method = RequestMethod.GET)
	public @ResponseBody String deleteAttributeValue(@RequestParam(required = false, value = "callback") String callback,
			@RequestParam(required = false, value = "params") JSONObject jsonParams, HttpServletResponse response){
		
		JSONObject jparams = jsonParams;
		int attributeValueId = 0;
		try {
			attributeValueId = jparams.getInt("id");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		JSONObject jsonObject = new JSONObject();
		if( attributeValueId > 0 ) {
			this.productsMasterService.deleteAttributeValue(attributeValueId);
			try {
				jsonObject.put("status", 1);
				jsonObject.put("message", "AttributeValue deleted successfully.");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}else{
			try {
				jsonObject.put("status", 0);
				jsonObject.put("message", "Failed to delete AttributeValue.");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		return jsonObject.toString();
	}
	
	@RequestMapping(value = "attributevalue/update", method = RequestMethod.GET)
	public @ResponseBody String updateAttributeValue(@RequestParam(required = false, value = "callback") String callback,
			@RequestParam(required = false, value = "params") JSONObject jsonParams, HttpServletResponse response){
		
		String strJson="{\"attributeValues\":[{\"id\":1,\"value\":\"6\",\"language\":\"enUS\",\"attribute_id\":1,\"position\":1},{\"id\":2,\"value\":\"7\",\"language\":\"enUS\",\"attribute_id\":1,\"position\":2},{\"id\":3,\"value\":\"8\",\"language\":\"enUS\",\"attribute_id\":1,\"position\":3}]}";
		JSONObject mainJObj = new JSONObject();
		JSONArray jArray = new JSONArray();
		try {
			for(int i=0; i<3; i++) {
				JSONObject jObj = new JSONObject();
				jObj.put("attribute_id", 1);
				int id = i+1;
				int value = 6 + i;
				String strValue = String.valueOf(value)+"test"; 
				jObj.put("id", (i+1));
				jObj.put("value", strValue);
				jObj.put("position", (i+1));
				jObj.put("language", "enUS");
				jArray.put(jObj);
			}
			mainJObj.put("attributeValues", jArray);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		System.out.println(mainJObj.toString());
		
		int status = this.productsMasterService.updateAttributeValue(mainJObj);
		
		JSONObject jsonObject = new JSONObject();
		if ( status == 1 ) {
			try {
				jsonObject.put("status", 1);
				jsonObject.put("message", "AttributeValues updated successfully.");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}else{
			try {
				jsonObject.put("status", 0);
				jsonObject.put("message", "Failed to update AttributeValues.");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return jsonObject.toString();
		
	}
	
	@RequestMapping(value = "attributevalue/add", method = RequestMethod.GET)
	public @ResponseBody String addAttributeValue(@RequestParam(required = false, value = "callback") String callback,
			@RequestParam(required = false, value = "params") JSONObject jsonParams, HttpServletResponse response){
		
		String strJson="{\"attributeValues\":[{\"value\":\"6\",\"language\":\"enUS\",\"attribute_id\":1,\"position\":1},{\"value\":\"7\",\"language\":\"enUS\",\"attribute_id\":1,\"position\":2},{\"value\":\"8\",\"language\":\"enUS\",\"attribute_id\":1,\"position\":3}]}";
		JSONObject mainJObj = new JSONObject();
		JSONArray jArray = new JSONArray();
		try {
			for(int i=0; i<3; i++) {
				JSONObject jObj = new JSONObject();
				jObj.put("attribute_id", 1);
				int value = 6 + i;
				String strValue = String.valueOf(value); 
				jObj.put("value", strValue);
				jObj.put("position", (i+1));
				jObj.put("language", "enUS");
				jArray.put(jObj);
			}
			mainJObj.put("attributeValues", jArray);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		System.out.println(mainJObj.toString());
		
		int status = this.productsMasterService.addAttributeValue(mainJObj);
		
		JSONObject jsonObject = new JSONObject();
		if ( status == 1 ) {
			try {
				jsonObject.put("status", 1);
				jsonObject.put("message", "AttributeValues inserted successfully.");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}else{
			try {
				jsonObject.put("status", 0);
				jsonObject.put("message", "Failed to insert AttributeValues.");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return jsonObject.toString();
		
	}
		
	@RequestMapping(value = "attribute/delete", method = RequestMethod.GET)
	public @ResponseBody String deleteAttribute(@RequestParam(required = false, value = "callback") String callback,
			@RequestParam(required = false, value = "params") JSONObject jsonParams, HttpServletResponse response){
		
		JSONObject jparams = jsonParams;
		int attributeId = 0;
		try {
			attributeId = jparams.getInt("id");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		JSONObject jsonObject = new JSONObject();
		if( attributeId > 0 ) {
			int status = this.productsMasterService.deleteAttribute(attributeId);
			
			if ( status == 1 ) {
				try {
					jsonObject.put("status", 1);
					jsonObject.put("message", "Attribute deleted successfully.");
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}else{
				try {
					jsonObject.put("status", 0);
					jsonObject.put("message", "Failed to delete Attribute.");
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}else{
			try {
				jsonObject.put("status", 0);
				jsonObject.put("message", "Failed to delete Attribute.");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		return jsonObject.toString();
	}
	
	@RequestMapping(value = "attribute/update", method = RequestMethod.GET)
	public @ResponseBody String updateAttribute(@RequestParam(required = false, value = "callback") String callback,
			@RequestParam(required = false, value = "params") JSONObject jsonParams, HttpServletResponse response){
		
		JSONObject obj = new JSONObject();
		try {
			obj.put("id", 1);
			obj.put("name", "Shape");
			obj.put("attribute_group_id", 1);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		
		JSONObject jparams = jsonParams;
		int id = this.productsMasterService.updateAttribute(obj);
		
		JSONObject jsonObject = new JSONObject();
		if ( id > 0 ) {
			try {
				jsonObject.put("status", 1);
				jsonObject.put("message", "Attribute updated successfully.");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
		}else{
			try {
				jsonObject.put("status", 0);
				jsonObject.put("message", "Failed to updated Attribute.");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return jsonObject.toString();
	}
	
	@RequestMapping(value = "attribute/add", method = RequestMethod.GET)
	public @ResponseBody String addAttribute(@RequestParam(required = false, value = "callback") String callback,
			@RequestParam(required = false, value = "params") JSONObject jsonParams, HttpServletResponse response){
		
		
		JSONObject jparams = jsonParams;
		int attributeGroupId = 0;
		String attributeName = null;
		try {
			attributeName = jparams.getString("name");
			attributeGroupId = jparams.getInt("id");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		int id = this.productsMasterService.addAttribute(attributeGroupId, attributeName);
		
		JSONObject jsonObject = new JSONObject();
		if ( id > 0 ) {
			try {
				jsonObject.put("status", 1);
				jsonObject.put("id", id);
				jsonObject.put("message", "Attribute created successfully.");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}else{
			try {
				jsonObject.put("status", 0);
				jsonObject.put("message", "Failed to create Attribute.");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return jsonObject.toString();
	}
	
	@RequestMapping(value = "attributegroup/delete", method = RequestMethod.GET)
	public @ResponseBody String deleteAttributeGroup(@RequestParam(required = false, value = "callback") String callback,
			@RequestParam(required = false, value = "params") JSONObject jsonParams, HttpServletResponse response){
		
		JSONObject jparams = jsonParams;
		int attributeGroupId = 0;
		try {
			attributeGroupId = jparams.getInt("id");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		JSONObject jsonObject = new JSONObject();
		if( attributeGroupId > 0 ) {
			int status = this.productsMasterService.deleteAttributeGroup(attributeGroupId);
			if( status == 1 ) {
				try {
					jsonObject.put("status", 1);
					jsonObject.put("message", "AttributeGroup deleted successfully.");
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}else{
				try {
					jsonObject.put("status", 0);
					jsonObject.put("message", "Failed to delete AttributeGroup.");
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}else{
			try {
				jsonObject.put("status", 0);
				jsonObject.put("message", "Failed to delete AttributeGroup.");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		return jsonObject.toString();
	}
	
	@RequestMapping(value = "attributegroup/update", method = RequestMethod.GET)
	public @ResponseBody String updateAttributeGroup(@RequestParam(required = false, value = "callback") String callback,
			@RequestParam(required = false, value = "params") JSONObject jsonParams, HttpServletResponse response){
		
		JSONObject obj = new JSONObject();
		try {
			obj.put("id", 1);
			obj.put("name", "MyGroup123");
			obj.put("attribute_set_id", 1);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		
		JSONObject jparams = jsonParams;
		/*String attributeGroupName = null;
		int attributSetId = 0;
		int attrGroupId = 0;
		
		try {
			attrGroupId = jparams.getInt("id");
			attributeGroupName = jparams.getString("name");
			attributSetId = jparams.getInt("attribute_set_id");
		} catch (JSONException e) {
			e.printStackTrace();
		}*/
		
		int id = this.productsMasterService.updateAttributeGroup(obj);
		
		JSONObject jsonObject = new JSONObject();
		if ( id > 0 ) {
			try {
				jsonObject.put("status", 1);
				jsonObject.put("message", "AttributeGroup updated successfully.");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
		}else{
			try {
				jsonObject.put("status", 0);
				jsonObject.put("message", "Failed to updated AttributeGroup.");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return jsonObject.toString();
	}
	
	@RequestMapping(value = "attributegroup/add", method = RequestMethod.GET)
	public @ResponseBody String addAttributeGroup(@RequestParam(required = false, value = "callback") String callback,
			@RequestParam(required = false, value = "params") JSONObject jsonParams, HttpServletResponse response){
		
		JSONObject jparams = jsonParams;
		String attributeGroupName = null;
		int attributSetId = 0;
		try {
			attributeGroupName = jparams.getString("name");
			attributSetId = jparams.getInt("id");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		int id = this.productsMasterService.addAttributeGroup(attributSetId, attributeGroupName);
		JSONObject jsonObject = new JSONObject();
		if ( id > 0 ) {
			try {
				jsonObject.put("status", 1);
				jsonObject.put("id", id);
				jsonObject.put("message", "AttributeGroup created successfully.");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}else{
			try {
				jsonObject.put("status", 0);
				jsonObject.put("message", "Failed to create AttributeGroup.");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return jsonObject.toString();
	}
	
	@RequestMapping(value = "attributeset/update", method = RequestMethod.GET)
	public @ResponseBody String updateAttributeSet(@RequestParam(required = false, value = "callback") String callback,
			@RequestParam(required = false, value = "params") JSONObject jsonParams, HttpServletResponse response){
		
		JSONObject jObj = new JSONObject();
		try {
			jObj.put("id", 100);
			jObj.put("name", "Test123");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		System.out.println(jObj);
		int id = this.productsMasterService.updateAttributeSet(jObj);
		
		JSONObject jsonObject = new JSONObject();
		if ( id > 0 ) {
			try {
				jsonObject.put("status", 1);
				jsonObject.put("message", "AttributeSet updated successfully.");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
		}else{
			try {
				jsonObject.put("status", 0);
				jsonObject.put("message", "Failed to updated AttributeSet.");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return jsonObject.toString();
		
	}
	
	@RequestMapping(value = "attributeset/delete", method = RequestMethod.GET)
	public @ResponseBody String deleteAttributeSet(@RequestParam(required = false, value = "callback") String callback,
			@RequestParam(required = false, value = "params") JSONObject jsonParams, HttpServletResponse response){
		
		JSONObject jparams = jsonParams;
		int attrSetId = 0;
		try {
			attrSetId = jparams.getInt("id");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		JSONObject jsonObject = new JSONObject();
		if( attrSetId > 0 ) {
			int status = this.productsMasterService.deleteAttributeSet(attrSetId);
			if ( status == 1 ) {
				try {
					jsonObject.put("status", 1);
					jsonObject.put("message", "AttributeSet deleted successfully.");
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}else{
				try {
					jsonObject.put("status", 0);
					jsonObject.put("message", "Failed to delete AttributeSet.");
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}else{
			try {
				jsonObject.put("status", 0);
				jsonObject.put("message", "Failed to delete AttributeSet.");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		return jsonObject.toString();
	}
	
	@RequestMapping(value = "attributeset/add", method = RequestMethod.GET)
	public @ResponseBody String addAttributeSet(@RequestParam(required = false, value = "callback") String callback,
			@RequestParam(required = false, value = "params") JSONObject jsonParams, HttpServletResponse response){
		
		JSONObject jparams = jsonParams;
		String attributeSetName = null;
		try {
			attributeSetName = jparams.getString("name");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		int id = this.productsMasterService.addAttributeSet(attributeSetName);
		JSONObject jsonObject = new JSONObject();
		if ( id > 0 ) {
			try {
				jsonObject.put("status", 1);
				jsonObject.put("id", id);
				jsonObject.put("message", "AttributeSet created successfully.");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
		}else{
			try {
				jsonObject.put("status", 0);
				jsonObject.put("message", "Failed to create AttributeSet.");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return jsonObject.toString();
	}
	
	@RequestMapping(value = "catalog/category/delete", method = RequestMethod.GET)
	public @ResponseBody String removeCategory(@RequestParam(required = false, value = "callback") String callback,
			@RequestParam(required = false, value = "params") JSONObject jsonParams, HttpServletResponse response){
		
		JSONObject test = new JSONObject();
		try {
			test.put("id", 1);
		} catch (JSONException e2) {
			e2.printStackTrace();
		}
		
		JSONObject jparams = jsonParams;
				
		int categoryId = 0;
		try {
			categoryId = jparams.getInt("id");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		int status = this.categoryProductsMasterService.removeCategory(categoryId);
		JSONObject jsonObject = new JSONObject();
		if ( status > 0 ) {
			try {
				jsonObject.put("status", 1);
				jsonObject.put("message", "Category deleted successfully.");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
		}else{
			try {
				jsonObject.put("status", 0);
				jsonObject.put("message", "Failed to delete category.");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return jsonObject.toString();
	}
	
	@RequestMapping(value = "catalog/category/update", method = RequestMethod.GET)
	public @ResponseBody String updateCategory(@RequestParam(required = false, value = "callback") String callback,
			@RequestParam(required = false, value = "params") JSONObject jsonParams, HttpServletResponse response){
		
		String jstr = "{\"id\":100,\"categoryName\":\"Non-fiction\",\"level\":1, \"position\":2, \"parentId\":1}";
		JSONObject test = jsonParams;
		
		ObjectMapper mapper = new ObjectMapper();
		CategoryMaster categoryMaster = new CategoryMaster();
		try {
			categoryMaster = mapper.readValue(jstr, CategoryMaster.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(categoryMaster.toString());
		int id = this.categoryProductsMasterService.updateCategory(categoryMaster);
		
		JSONObject jsonObject = new JSONObject();
		if ( id > 0 ) {
			try {
				jsonObject.put("status", 1);
				jsonObject.put("message", "Category updated successfully.");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
		}else{
			try {
				jsonObject.put("status", 0);
				jsonObject.put("message", "Failed to updated category.");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return jsonObject.toString();
	}
	
	@RequestMapping(value = "catalog/category/add", method = RequestMethod.GET)
	public @ResponseBody String addCategory(@RequestParam(required = false, value = "callback") String callback,
			@RequestParam(required = false, value = "params") JSONObject jsonParams, HttpServletResponse response){
		
		String jstr = "{\"categoryName\":\"Classics\",\"level\":1, \"position\":1, \"parentId\":2}";
		JSONObject test = jsonParams;
		
		ObjectMapper mapper = new ObjectMapper();
		CategoryMaster categoryMaster = new CategoryMaster();
		try {
			categoryMaster = mapper.readValue(jstr, CategoryMaster.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(categoryMaster.toString());
		int id = this.categoryProductsMasterService.addCategory(categoryMaster);
		
		JSONObject jsonObject = new JSONObject();
		if ( id > 0 ) {
			try {
				jsonObject.put("status", 1);
				jsonObject.put("id", id);
				jsonObject.put("message", "Category added successfully.");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
		}else{
			try {
				jsonObject.put("status", 0);
				jsonObject.put("message", "Failed to insert category.");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return jsonObject.toString();
	}
	
	
	@RequestMapping(value = "catalog/category", method = RequestMethod.GET)
	public @ResponseBody String listCategory(@RequestParam(required = false, value = "callback") String callback,
			@RequestParam(required = false, value = "params") JSONObject jsonParams, HttpServletResponse response){
		
		
		JSONObject test = new JSONObject();
		try {
			test.put("id", -1);
		} catch (JSONException e2) {
			e2.printStackTrace();
		}
		JSONObject jparams = jsonParams;
		int cid = -1;
		try {
			cid = jparams.getInt("id");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		List<CategoryMaster> categoryMasterList = null;
		if( cid > 0 ) {
			CategoryMaster categoryMaster = this.categoryProductsMasterService.getCategoryById(cid);
			List<CategoryMaster> cmList = new ArrayList<>();
			
			cmList.add(categoryMaster);
			categoryMasterList = cmList;
		}else if( cid == -1){
			categoryMasterList = this.categoryProductsMasterService.getAllCategories();
		}
		
		JSONObject categoryListObject = new JSONObject();
		JSONArray categories = new JSONArray();
		ObjectMapper mapper = new ObjectMapper();
		for(int i=0; i<categoryMasterList.size(); i++) {
			
			try {
				String strCategoryMasterObj = mapper.writeValueAsString(categoryMasterList.get(i));
				JSONObject jsonObj = new JSONObject(strCategoryMasterObj);
				
				categories.put(jsonObj);
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		try {
			categoryListObject.put("categories", categories);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
						
		
		return categoryListObject.toString();
	}
	
	@RequestMapping(value = "catalog/product/update", method = RequestMethod.GET)
	public @ResponseBody String updateProduct(@RequestParam(required = false, value = "callback") String callback,
			@RequestParam(required = false, value = "params") JSONObject jsonParams, HttpServletResponse response){
				
		String jstr = "{\"id\":4,\"name\":\"MicroSDCard\", \"description\":\"MicroSDCard\", \"shortDescription\":\"MicroSDCard\", \"sku\":\"microsdcard\", \"weight\":\"50.0\", \"fromDate\":\"19/01/2015\", \"toDate\":\"19/01/2015\", \"status\":1, \"urlKey\":\"microsdcard\", \"visibility\":1, \"country\":\"\", \"price\":300.0, \"image\":\"\", \"qty\":100, \"stock\":1, \"type\":\"Product\", \"hasMoreOption\":1, \"requiredOption\":1}";
		JSONObject test = jsonParams;
		
				
		ObjectMapper mapper = new ObjectMapper();
		ProductsMaster productsMaster = new ProductsMaster();
		try {
			productsMaster = mapper.readValue(jstr, ProductsMaster.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int status = this.productsMasterService.updateProduct(productsMaster);
		
		JSONObject jsonObject = new JSONObject();
		if ( status > 0 ) {
			try {
				jsonObject.put("status", status);
				jsonObject.put("message", "Product updated successfully.");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
		}else{
			try {
				jsonObject.put("status", 0);
				jsonObject.put("message", "Failed to update product.");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return jsonObject.toString();
		
	}
	
	@RequestMapping(value = "catalog/product/add", method = RequestMethod.GET)
	public @ResponseBody String addProduct(@RequestParam(required = false, value = "callback") String callback,
			@RequestParam(required = false, value = "params") JSONObject jsonParams, HttpServletResponse response){
				
		String jstr = "{\"name\":\"Othello\", \"description\":\"Othello\", \"shortDescription\":\"Othello\", \"sku\":\"othello\", \"weight\":\"50.0\", \"fromDate\":\"19/01/2015\", \"toDate\":\"19/01/2015\", \"status\":1, \"urlKey\":\"othello\", \"visibility\":1, \"country\":\"\", \"price\":100.0, \"image\":\"\", \"qty\":100, \"stock\":1, \"type\":\"Product\", \"hasMoreOption\":1, \"requiredOption\":1, \"categoryId\":18}";
		JSONObject test = jsonParams;
				
		ObjectMapper mapper = new ObjectMapper();
		ProductsMaster productsMaster = new ProductsMaster();
		try {
			productsMaster = mapper.readValue(jstr, ProductsMaster.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int insertedProductId = this.productsMasterService.addProduct(productsMaster);
		System.out.println("PRODUCT ID:"+insertedProductId);
		JSONObject jsonObject = new JSONObject();
		if ( insertedProductId > 0 ) {
			try {
				jsonObject.put("status", 1);
				jsonObject.put("id", insertedProductId);
				jsonObject.put("message", "Product added successfully.");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
		}else{
			try {
				jsonObject.put("status", 0);
				jsonObject.put("id", 0);
				jsonObject.put("message", "Failed to add product.");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return jsonObject.toString();
		
	}

	@RequestMapping(value = "catalog/product/delete", method = RequestMethod.GET)
	public @ResponseBody String deleteProduct(@RequestParam(required = false, value = "callback") String callback, 
			@RequestParam(required = false, value = "params") JSONObject jsonParams, HttpServletResponse response) {	
		
		JSONObject test = new JSONObject();
		try {
			test.put("id", -1);
		} catch (JSONException e2) {
			e2.printStackTrace();
		}
		JSONObject jparams = jsonParams;
		int pid = -1;
		try {
			pid = jparams.getInt("id");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		String json = "";
		json = this.productsMasterService.deleteProduct(pid);
		return json;
	}
	
	@RequestMapping(value = "catalog/product", method = RequestMethod.GET)
	public @ResponseBody String getProductById(@RequestParam(required = false, value = "callback") String callback, @RequestParam(required = false, value = "params") JSONObject jsonParams, HttpServletResponse response) {	
		
		JSONObject test = new JSONObject();
		try {
			test.put("id", -1);
		} catch (JSONException e2) {
			e2.printStackTrace();
		}
		
		JSONObject jparams = jsonParams;
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		String json = "";
		
		//System.out.println("TOTAL RECS1:"+this.productsMasterService.getAllProducts().size());
		
		int pid = -1;
		try {
			pid = jparams.getInt("id");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		List<ProductsMaster> productsList = null;
		if ( pid > 0 ) {
			List<ProductsMaster> pmList = new ArrayList<>();
			ProductsMaster productsMaster = this.productsMasterService.getProductById(pid);
			pmList.add(productsMaster);
			productsList = pmList;
		}else{
			productsList = this.productsMasterService.getAllProducts();
		}
		
		JSONObject productListObject = new JSONObject();
		JSONArray products = new JSONArray();
		ObjectMapper mapper = new ObjectMapper();
		for(int i=0; i<productsList.size(); i++) {
			
			try {
				String strProductMasterObj = mapper.writeValueAsString(productsList.get(i));
				JSONObject jsonObj = new JSONObject(strProductMasterObj);
				
				products.put(jsonObj);
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
		}
		try {
			productListObject.put("products", products);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		//productsList.add(arg0)
		json = productListObject.toString();
		return json;
		/*try {
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			if (null == callback) {
	    		json = ow.writeValueAsString(productListObject);
	    	} else {
	    		json = callback + "(" + ow.writeValueAsString(productListObject) + ")";
	    	}
	    	System.out.println(json);
	    	out.write(json.getBytes());
			response.setContentType("application/json;charset=UTF-8");
			response.setHeader("Connection", "Keep-Alive");
			response.setHeader("Keep-Alive", "timeout=5, max=100");
		    response.setContentLength(out.size());
		    response.getOutputStream().write(out.toByteArray());
		    response.getOutputStream().flush();
		 } catch (IOException e) {
				e.printStackTrace();
		 }*/
		
	}
	
	@RequestMapping(value = "catalog/categoryproduct", method = RequestMethod.GET)
	public @ResponseBody String getProductByCategory(@RequestParam(required = false, value = "callback") String callback, @RequestParam(required = false, value = "params") JSONObject jsonParams, HttpServletResponse response) {	
		
		
		JSONObject test = new JSONObject();
		try {
			test.put("id", -1);
		} catch (JSONException e2) {
			e2.printStackTrace();
		}
		
		JSONObject jparams = jsonParams;
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		String json = "";
		
		
		int cid = -1;
		try {
			cid = jparams.getInt("id");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		List<ProductsMaster> productsList = null;
		
		if ( cid > 0 ) {
			productsList = this.productsMasterService.getProductByCategory(cid);
		}else{
			productsList = this.productsMasterService.getAllProducts();
		}
		
		JSONObject productListObject = new JSONObject();
		JSONArray products = new JSONArray();
		ObjectMapper mapper = new ObjectMapper();
		for(int i=0; i<productsList.size(); i++) {
			
			try {
				String strProductMasterObj = mapper.writeValueAsString(productsList.get(i));
				JSONObject jsonObj = new JSONObject(strProductMasterObj);
				
				products.put(jsonObj);
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			productListObject.put("products", products);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		//productsList.add(arg0)
		json = productListObject.toString();
		return json;
	}
}
