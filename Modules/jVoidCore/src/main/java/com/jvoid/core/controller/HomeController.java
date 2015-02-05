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
package com.jvoid.core.controller;

/**
 * HomeController class
 * 
 * @author Shajir
 * @version 1.0
 */

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.jvoid.core.model.ProductsMaster;
import com.jvoid.core.uricostants.ServerUris;
import com.jvoid.core.uricostants.URIConstants;

@Controller
public class HomeController {   	
	public ArrayList<ProductsMaster> listOfProducts;
	
	@RequestMapping("/")
	public @ResponseBody String welcome() {
		return "Welcome to jvoid core";
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody String loginToJvoid(@RequestParam(required = false, value = "params") JSONObject jsonParams) {
		System.out.println("Login:jsonParams=>"+jsonParams.toString());
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ServerUris.CUSTOMER_SERVER_URI+URIConstants.GET_CUSTOMER_BY_EMAIL)
		        .queryParam("params", jsonParams);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		HttpEntity<String> returnString = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET, entity, String.class);
		return returnString.getBody();
	}
	
	@RequestMapping("/login-tester")
	public @ResponseBody String jvoidLoginTester(@RequestParam("params") String jsonParams) {
		System.out.println("login-tester: jsonParams=>"+jsonParams);
		
		JSONObject jsonObj = null;
		try {
			jsonObj = new JSONObject(jsonParams);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("jsonObj=>"+jsonObj);
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:9080/jvoidcore/login")
		        .queryParam("params", jsonObj);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		HttpEntity<String> returnString = restTemplate.exchange(builder.build().toUri(), HttpMethod.POST, entity, String.class);
		System.out.println("returnString=>"+returnString);
		
		JSONObject returnJsonObj = null;
		try {
			returnJsonObj = new JSONObject();
			returnJsonObj.put("result", returnString);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return returnJsonObj.toString();
	}
	
	@RequestMapping(value = "/sign-up", method = RequestMethod.POST)
	public @ResponseBody String jvoidSignUpNewUser(@RequestParam(required = false, value = "params") JSONObject jsonParams) {
		System.out.println("sign-up:jsonParams=>"+jsonParams.toString());
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		String uri = "";
		try {
			if (jsonParams.getInt("id")>0) {
				uri = ServerUris.CUSTOMER_SERVER_URI+URIConstants.UPDATE_CUSTOMER;
			}
			else{
				uri = ServerUris.CUSTOMER_SERVER_URI+URIConstants.ADD_CUSTOMER;
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri)
		        .queryParam("params", jsonParams);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		HttpEntity<String> returnString = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET, entity, String.class);
		return returnString.getBody();
	}
	
	@RequestMapping(value = "/password-reset", method = RequestMethod.POST)
	public @ResponseBody String jvoidResetPAssword(@RequestParam(required = false, value = "params") JSONObject jsonParams) {
		System.out.println("Login:jsonParams=>"+jsonParams.toString());
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ServerUris.CUSTOMER_SERVER_URI+URIConstants.RESET_CUSTOMER_PASSWORD)
		        .queryParam("params", jsonParams);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		HttpEntity<String> returnString = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET, entity, String.class);
		return returnString.getBody();
	}
	
	
	@RequestMapping("/jvoid-products")
	public @ResponseBody String listAllJVoidProductsForOutView() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		JSONObject jsonObj = new JSONObject();    
		try {
			jsonObj.put("id", -1);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ServerUris.PRODUCT_SERVER_URI+URIConstants.GET_PRODUCT)
		        .queryParam("params", jsonObj);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		HttpEntity<String> returnString = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET, entity, String.class);
		
		JSONObject returnJsonObj = null;
		try {
			returnJsonObj = new JSONObject(returnString.getBody());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return returnJsonObj.toString();
	}
	
	
	@RequestMapping("/jvoid-products-by-cat")
	public @ResponseBody String listAllJVoidProductsByCategoryForOutView(@RequestParam("catId") String catId) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		JSONObject jsonObj = new JSONObject();    
		try {
			jsonObj.put("id", Integer.parseInt(catId));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ServerUris.PRODUCT_SERVER_URI+URIConstants.GET_PRODUCTS_BY_CATEGORY)
		        .queryParam("params", jsonObj);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		HttpEntity<String> returnString = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET, entity, String.class);
		
		JSONObject returnJsonObj = null;
		try {
			returnJsonObj = new JSONObject(returnString.getBody());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return returnJsonObj.toString();
	}
	
	@RequestMapping("/jvoid-categories")
	public @ResponseBody String listAllJVoidCategoriesForOutView() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		JSONObject jsonObj = new JSONObject();    
		try {
			jsonObj.put("id", -1);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ServerUris.PRODUCT_SERVER_URI+URIConstants.GET_CATEGORY)
		        .queryParam("params", jsonObj);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		HttpEntity<String> returnString = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET, entity, String.class);
		
		JSONObject returnJsonObj = null;
		try {
			returnJsonObj = new JSONObject(returnString.getBody());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return returnJsonObj.toString();
	}
	
	@RequestMapping("/order")
	public @ResponseBody String orderProductNowById(@RequestParam("cartId") String cartId, @RequestParam("prodId") String productId) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		JSONObject jsonObj = new JSONObject();
		try {
			jsonObj.put("cartId", Integer.parseInt(cartId));
			jsonObj.put("productId", Integer.parseInt(productId));
			jsonObj.put("attributeId", 1);
			jsonObj.put("quantity", 1);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("param jsonObj=>"+jsonObj.toString());
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ServerUris.QUOTE_SERVER_URI+URIConstants.ADD_PRODUCT_TO_CART)
		        .queryParam("params", jsonObj);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		HttpEntity<String> returnString = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET, entity, String.class);
		return returnString.getBody();
	}
	
	@RequestMapping("/cart")
	public @ResponseBody String getCartById(@RequestParam("cartId") String cartId) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		JSONObject jsonObj = new JSONObject();
		try {
			jsonObj.put("cartId", Integer.parseInt(cartId));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("param jsonObj=>"+jsonObj.toString());
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ServerUris.QUOTE_SERVER_URI+URIConstants.GET_CART)
		        .queryParam("params", jsonObj);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		HttpEntity<String> returnString = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET, entity, String.class);
		return returnString.getBody();
	}
	
	@RequestMapping("/remove-cart")
	public @ResponseBody String removeItemsFromCartById(@RequestParam("cartId") String cartId, @RequestParam("prodId") String prodId) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		JSONObject jsonObj = new JSONObject();
		try {
			jsonObj.put("cartId", Integer.parseInt(cartId));
			jsonObj.put("productId", Integer.parseInt(prodId));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("param jsonObj=>"+jsonObj.toString());
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ServerUris.QUOTE_SERVER_URI+URIConstants.DELETE_CART)
		        .queryParam("params", jsonObj);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		HttpEntity<String> returnString = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET, entity, String.class);
		return returnString.getBody();
	}
	
	@RequestMapping(value = "/jvoid-checkout-cart", method = RequestMethod.POST)
	public @ResponseBody String jvoidChrckoutCart(@RequestParam("params") String jsonParams) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		
		System.out.println("jsonParams=>"+jsonParams);
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ServerUris.QUOTE_SERVER_URI+URIConstants.CHECKOUT_CART)
		        .queryParam("params", jsonParams);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		HttpEntity<String> returnString = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET, entity, String.class);
		
		JSONObject returnJsonObj = null;
		try {
			returnJsonObj = new JSONObject(returnString.getBody());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("returnJsonObj=>"+returnJsonObj);
		
		String result = "";
		try {
			result = returnJsonObj.getString("result");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String response = "";
		if(result.equals("Success")){
			UriComponentsBuilder builder1 = UriComponentsBuilder.fromHttpUrl(ServerUris.ORDER_SERVER_URI+URIConstants.ADD_ORDER)
			        .queryParam("params", jsonParams);	
			HttpEntity<?> entity1 = new HttpEntity<>(headers);
			HttpEntity<String> returnString1 = restTemplate.exchange(builder1.build().toUri(), HttpMethod.GET, entity1, String.class);
			response = returnString1.getBody();
		}
		
		return response;
	}
}