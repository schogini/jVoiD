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
	private final String USER_AGENT = "Mozilla/5.0";
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
	
	/*@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody String loginToJvoid(@RequestParam(required = false, value = "callback") String callback, @RequestParam(required = false, value = "params") JSONObject jsonParams) {
		
		String clientid = "restapp";
		String clientsecret = "restapp";
		String username = null;
		String password = null;
		
		try {
			//clientid = jsonParams.getString("client_id");
			//clientsecret = jsonParams.getString("client_secret");
			username = jsonParams.getString("email");
			password = jsonParams.getString("password");
		} catch (JSONException e2) {
			e2.printStackTrace();
		}
		
		
		ArrayList<String> params = new ArrayList<String>();
		params.add("callback="+callback);
		params.add("client_id="+clientid);
		params.add("client_secret="+clientsecret);
		params.add("username="+username);
		params.add("password="+password);
		
		String url = "http://localhost:9080/jvoidcore/oauth/token?grant_type=password&client_id="+clientid+"&client_secret="+clientsecret+"&username="+username+"&password="+password;
		JSONObject jsonerr = new JSONObject();		
		try {
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", USER_AGENT);
	 
			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);
	 
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			System.out.println("Response : " + response);
			
			// Access Token Response from JBOSS
			// {"value":"f8ecf6f5-6c82-457d-9b1c-a9c24839ceb9","expiration":1418396633807,"tokenType":"bearer","refreshToken":{"value":"d5812c57-c8d7-49df-a482-510678457caa","expiration":1420988390872},"scope":[],"additionalInformation":{},"expired":false,"expiresIn":90}
			// Access Token Response from TomCat
			// {"access_token":"c9ee8e87-cffc-4c43-82b2-0a71e47393f2","token_type":"bearer","refresh_token":"9409069d-d4f4-4e04-9843-b679b54b8fbd","expires_in":119}
			JSONObject responseJsonObj = new JSONObject();
			try {
				JSONObject jsonObj = new JSONObject(response.toString());
				if (jsonObj.has("access_token")) {
					// TomCat Response. Do Nothing
					responseJsonObj.put("status", 0);
					responseJsonObj.put("details", jsonObj);
					
				} else {
					// JBOSS Response
					JSONObject refreshTokenObj = new JSONObject(jsonObj.getString("refreshToken"));
					JSONObject newJsonObj = new JSONObject();
					newJsonObj.put("access_token", jsonObj.getString("value"));
					newJsonObj.put("token_type", jsonObj.getString("tokenType"));
					newJsonObj.put("refresh_token", refreshTokenObj.getString("value"));
					if (jsonObj.has("expiresIn")) {
						newJsonObj.put("expires_in", jsonObj.getString("expiresIn"));
					} else {
						newJsonObj.put("expires_in", "-1");
					}
					
					responseJsonObj.put("status", 0);
					responseJsonObj.put("details", newJsonObj);
				}
				
				String returnjson = "";
				if (null == callback) {
					returnjson = responseJsonObj.toString(); ;
				} else {
					returnjson = callback + "(" + responseJsonObj.toString() + ")";					
				}
				
				return returnjson;
				
			} catch (JSONException e) {
				e.printStackTrace();
				try {
					jsonerr.put("status", 0);
					jsonerr.put("message", "JSONException: Invalid credentials");
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
				
			}
			
		} catch(IOException e) {
			System.out.println("Error!" + e.getMessage());
			try {
				jsonerr.put("status", 0);
				jsonerr.put("message", "JSONException: Invalid credentials");
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
		}
		
		String jsonerrString = "";
		if (null == callback) {
			jsonerrString = jsonerr.toString();
		} else {
			jsonerrString = callback + "(" + jsonerr.toString() + ")";				
		}
		
		return jsonerrString;
	}*/
	
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
		System.out.println("Login-tester:jsonObj=>"+jsonObj);
		
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