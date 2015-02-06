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
package com.jvoid.attributes.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jvoid.attributes.attribute.model.Attributes;
import com.jvoid.attributes.attribute.service.AttributesService;

@Controller
@RequestMapping("/")
public class JVoidAttributeController {	
	
	@Autowired
	private AttributesService attributesService;
	
	public void setAttributesService(AttributesService attributesService){
		this.attributesService = attributesService;
	}
	
	@RequestMapping
    public @ResponseBody String list() {							
		System.out.println("Helloo...jvoid-attributes");	
		return "Hello";
    }
	
	@RequestMapping(value = "listProductAttriburtes", method = RequestMethod.GET)
	public @ResponseBody String listProductAttriburtes() { 
		List<Attributes> listAttributes = this.attributesService.listAttributes();
		JSONObject attrs = getAttributesList(listAttributes, "Product");
		return attrs.toString();
	}
	
	@RequestMapping(value = "listCustomerAttriburtes", method = RequestMethod.GET)
	public @ResponseBody String listCustomerAttriburtes() { 
		List<Attributes> listAttributes = this.attributesService.listAttributes();
		JSONObject attrs = getAttributesList(listAttributes, "Customer");
		return attrs.toString();
	}
	
	public JSONObject getAttributesList(List<Attributes> listAttributes, String type){
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		for(int i=0; i<listAttributes.size(); i++) {			
			if ( listAttributes.get(i).getType().equalsIgnoreCase(type) ){
				try {
					JSONObject temp = new JSONObject();
					temp.put("id", listAttributes.get(i).getId());
					temp.put("code", listAttributes.get(i).getCode());
					arr.put(temp);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		try {
			obj.put("Attributes", arr);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return obj;
	}
}
