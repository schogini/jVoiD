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
package com.jvoid.order.controller;

import java.io.IOException;
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

import com.jvoid.order.model.CartItem;
import com.jvoid.order.model.CheckoutOrder;
import com.jvoid.order.model.CheckoutOrderAddress;
import com.jvoid.order.model.CheckoutOrderItem;
import com.jvoid.order.service.CheckoutOrderAddressService;
import com.jvoid.order.service.CheckoutOrderItemService;
import com.jvoid.order.service.CheckoutOrderService;
import com.jvoid.order.utils.Utilities;

@Controller
@RequestMapping("/")
public class JVoidOrderController {	
//	public static final String PRODUCT_SERVER_URI = "http://localhost:9080/jvoidproducts";
	
	@Autowired
	private CheckoutOrderService checkoutOrderService;
	
	@Autowired
	private CheckoutOrderAddressService checkoutOrderAddressService;
	
	@Autowired
	private CheckoutOrderItemService checkoutOrderItemService;
	
	public void setCheckoutOrderService(CheckoutOrderService checkoutOrderService){
		this.checkoutOrderService = checkoutOrderService;
	}
	
	public void setCheckoutOrderAddressService(
			CheckoutOrderAddressService checkoutOrderAddressService) {
		this.checkoutOrderAddressService = checkoutOrderAddressService;
	}

	public void setCheckoutOrderItemService(
			CheckoutOrderItemService checkoutOrderItemService) {
		this.checkoutOrderItemService = checkoutOrderItemService;
	}

	@RequestMapping
    public @ResponseBody String list() {
		return "Welcome to Jvoid Order";
	}
	
	@RequestMapping(value = "order/addaddress", method = RequestMethod.GET)
	public @ResponseBody String addAddress(@RequestParam(required = false, value = "callback") String callback,
			@RequestParam(required = false, value = "params") JSONObject jsonParams, HttpServletResponse response){
		//{"cartId":1,"user":{"firstName":"Harry","lastName":"Matt","company":"Schogini","emailAddress":"test@schogini.com"},"billing":{"address":"PTP - 292","streetAddress2":"PTP Nagar","city":"Trivandrum","state":"Kerala","zip":"695038","country":"India","telephone":"1234567890","fax":"0123456789"},"shipping":{"address":"Ship PTP - 292","streetAddress2":"Ship PTP Nagar","city":"Trivandrum","state":"Kerala","zip":"695038","country":"India","telephone":"1234567890","fax":"0123456789"},"checkoutMethod":"COD","checkoutComment":"New comment"}
		
		int cartId = -1;
		JSONObject user = null;
		int userId = -1;
		JSONObject billing = null;
		JSONObject shipping = null;
		String checkoutMethod = null;
		String checkoutComment = null;
		JSONArray cartItems = null;
		try {
			cartId = jsonParams.getInt("cartId");
			user = jsonParams.getJSONObject("user");
			userId = jsonParams.getInt("userId");
			billing = jsonParams.getJSONObject("billing");
			shipping = jsonParams.getJSONObject("shipping");
			checkoutMethod = jsonParams.getString("checkoutMethod");
			checkoutComment = jsonParams.getString("checkoutComment");
			cartItems = jsonParams.getJSONArray("cart");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		CheckoutOrder checkoutOrder = new CheckoutOrder();
		checkoutOrder.setStatus("Payment Complete");
		checkoutOrder.setCheckoutMethod(checkoutMethod);
		checkoutOrder.setCheckoutComment(checkoutComment);
		checkoutOrder.setCreatedAt(Utilities.getCurrentDateTime());
		checkoutOrder.setUpdatedAt(Utilities.getCurrentDateTime());
		checkoutOrder.setCustomerId(userId);
		this.checkoutOrderService.addCheckoutOrder(checkoutOrder);
		
		int insertedOrderId = checkoutOrder.getId();
		
		try {
			CheckoutOrderAddress checkoutOrderAddress = new CheckoutOrderAddress();
			checkoutOrderAddress.setOrderId(insertedOrderId);
			
			checkoutOrderAddress.setFirstname(user.getString("firstName"));
			checkoutOrderAddress.setLastname(user.getString("lastName"));
			checkoutOrderAddress.setCompany(user.getString("company"));
			checkoutOrderAddress.setEmail(user.getString("emailAddress"));
			
			checkoutOrderAddress.setAddressType("billing");
			checkoutOrderAddress.setStreet(billing.getString("address")+","+billing.getString("streetAddress2"));
			checkoutOrderAddress.setCity(billing.getString("city")+","+billing.getString("state"));
			checkoutOrderAddress.setCountryId(billing.getString("country"));
			checkoutOrderAddress.setPostcode(billing.getString("zip"));
			checkoutOrderAddress.setTelephone(billing.getString("telephone"));
			checkoutOrderAddress.setFax(billing.getString("fax"));
		
			checkoutOrderAddress.setCreatedAt(Utilities.getCurrentDateTime());
			checkoutOrderAddress.setUpdatedAt(Utilities.getCurrentDateTime());
			
			this.checkoutOrderAddressService.addCheckoutOrderAddress(checkoutOrderAddress);
			
			checkoutOrderAddress = null;
			checkoutOrderAddress = new CheckoutOrderAddress();
			checkoutOrderAddress.setOrderId(insertedOrderId);
			
			checkoutOrderAddress.setFirstname(user.getString("firstName"));
			checkoutOrderAddress.setLastname(user.getString("lastName"));
			checkoutOrderAddress.setCompany(user.getString("company"));
			checkoutOrderAddress.setEmail(user.getString("emailAddress"));
			
			checkoutOrderAddress.setAddressType("shipping");
			checkoutOrderAddress.setStreet(shipping.getString("address")+","+shipping.getString("streetAddress2"));
			checkoutOrderAddress.setCity(shipping.getString("city")+","+shipping.getString("state"));
			checkoutOrderAddress.setCountryId(shipping.getString("country"));
			checkoutOrderAddress.setPostcode(shipping.getString("zip"));
			checkoutOrderAddress.setTelephone(shipping.getString("telephone"));
			checkoutOrderAddress.setFax(shipping.getString("fax"));
			
			checkoutOrderAddress.setCreatedAt(Utilities.getCurrentDateTime());
			checkoutOrderAddress.setUpdatedAt(Utilities.getCurrentDateTime());
			
			this.checkoutOrderAddressService.addCheckoutOrderAddress(checkoutOrderAddress);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		///////ADDING ORDER ITEMS////////
		for(int i=0; i<cartItems.length(); i++) {
			CartItem cartItem = new CartItem();
			
			try {
				ObjectMapper mapper = new ObjectMapper();
				try {
					cartItem = mapper.readValue(cartItems.getJSONObject(0).toString(), CartItem.class);
				} catch (JsonParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			CheckoutOrderItem orderItem = new CheckoutOrderItem();
			orderItem.setOrderId(insertedOrderId);
			orderItem.setCreatedAt(Utilities.getCurrentDateTime());
			orderItem.setUpdatedAt(Utilities.getCurrentDateTime());
			orderItem.setParentId(cartId);
			
			orderItem.setProductId(cartItem.getProductId());
			orderItem.setAttributeId(cartItem.getAttributeId());
			orderItem.setStoreId(cartItem.getStoreId());
			orderItem.setIsVirtual(cartItem.getIsVirtual());
			orderItem.setSku(cartItem.getSku());
			orderItem.setName(cartItem.getName());
			orderItem.setDescription(cartItem.getDescription());
			orderItem.setFreeShipping(cartItem.getFreeShipping());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setPrice(cartItem.getPrice());
			orderItem.setBasePrice(cartItem.getBasePrice());
			orderItem.setRowTotal(cartItem.getRowTotal());
			orderItem.setBaseRowTotal(cartItem.getBaseRowTotal());
			orderItem.setRowWeight(cartItem.getRowWeight());
			orderItem.setProductType(cartItem.getProductType());
			
			this.checkoutOrderItemService.addCheckoutOrderItem(orderItem);
		}
		///////ADDING ORDER ITEMS////////
		
		JSONObject jsonObj = new JSONObject();
		try {
			jsonObj.put("orderId", insertedOrderId);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObj.toString();
		
//		return cartId+" **** "+user.toString()+" **** "+billing.toString()+" **** "+shipping.toString();
		
	}
	
	
	@RequestMapping(value = "order/list", method = RequestMethod.GET)
	public @ResponseBody String getCart(@RequestParam(required = false, value = "callback") String callback,
			@RequestParam(required = false, value = "params") JSONObject jsonParams, HttpServletResponse response){
		
		//System.out.println("TOTAL RECS1:"+this.productsMasterService.getAllProducts().size());
		
		int customerId = -1;
		try {
			customerId = jsonParams.getInt("customerId");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		JSONObject returnJsonObj = new JSONObject();
		JSONArray orderItems = new JSONArray();
		
		List<CheckoutOrder> ordersList = null;
		ordersList = this.checkoutOrderService.listCheckoutOrders(customerId);
		
		List<CheckoutOrderItem> orderItemsList = null;
		for(int i=0; i<ordersList.size(); i++) { 
			CheckoutOrder currentCheckoutOrder = ordersList.get(i);
			orderItemsList.addAll(this.checkoutOrderItemService.listCheckoutOrderItems(currentCheckoutOrder.getId()));
			System.out.println("orderItemsList size after iteration "+i+" = "+orderItemsList.size());
		}
		
		ObjectMapper mapper = new ObjectMapper();
		for(int i=0; i<orderItemsList.size(); i++) {
			try {
				String strQuoteItemObj = mapper.writeValueAsString(orderItemsList.get(i));
				JSONObject jsonObj = new JSONObject(strQuoteItemObj);
				
				orderItems.put(jsonObj);
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
			returnJsonObj.put("items", orderItems);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		
		return returnJsonObj.toString();
	}
	
}
