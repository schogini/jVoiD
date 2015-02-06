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
package com.jvoid.quote.controller;

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

import com.jvoid.quote.model.CheckoutAddToCart;
import com.jvoid.quote.model.CheckoutQuote;
import com.jvoid.quote.model.CheckoutQuoteItem;
import com.jvoid.quote.model.ProductsMaster;
import com.jvoid.quote.service.CheckoutQuoteItemService;
import com.jvoid.quote.service.CheckoutQuoteService;
import com.jvoid.quote.uriconstants.URIConstants;
import com.jvoid.quote.utils.Utilities;

@Controller
@RequestMapping("/")
public class JVoidQuoteController {	
	public static final String PRODUCT_SERVER_URI = "http://localhost:9080/jvoidproducts";
	
	@Autowired
	private CheckoutQuoteService checkoutQuoteService;
	
	@Autowired
	private CheckoutQuoteItemService checkoutQuoteItemService;
	
	public void setCheckoutQuoteService(CheckoutQuoteService checkoutQuoteService){
		this.checkoutQuoteService = checkoutQuoteService;
	}
	
	public void setCheckoutQuoteItemService(CheckoutQuoteItemService checkoutQuoteItemService) {
		this.checkoutQuoteItemService = checkoutQuoteItemService;
	}
	
	@RequestMapping
    public @ResponseBody String list() {
		return "Welcome to Jvoid Quote";
	}
	
	@RequestMapping(value = "quote/add", method = RequestMethod.GET)
	public @ResponseBody String addCart(@RequestParam(required = false, value = "callback") String callback,
			@RequestParam(required = false, value = "params") JSONObject jsonParams, HttpServletResponse response){
//		String jstr = "{\"cartId\":-1, \"productId\":2, \"attributeId\":1, \"quantity\":2}";
//		String jstr = "{\"cartId\":1, \"productId\":3, \"attributeId\":1, \"quantity\":2}";
		
		int cartId = -1;
		int productId = -1;
		int attributeId = -1;
		int quantity = -1;
		try {
			cartId = jsonParams.getInt("cartId");
			productId = jsonParams.getInt("productId");
			attributeId = jsonParams.getInt("attributeId");
			quantity = jsonParams.getInt("quantity");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
//		cartId = 1;
//		productId = 2;
//		attributeId = 1;
//		quantity = 2;
				
		CheckoutAddToCart checkoutAddToCart = new CheckoutAddToCart();
		checkoutAddToCart.setCartId(cartId);
		checkoutAddToCart.setProductId(productId);
		checkoutAddToCart.setAttributeId(attributeId);
		checkoutAddToCart.setQuantity(quantity);
		
		//Making entry to CheckoutQuote
		CheckoutQuote checkoutQuote = new CheckoutQuote();
		checkoutQuote.setId(checkoutAddToCart.getCartId());
		int insertedCartId = -1;
		if (checkoutQuote.getId() == -1) {
			System.out.println("Setting create id");
			checkoutQuote.setId(0);
			checkoutQuote.setCreatedAt(Utilities.getCurrentDateTime());
			checkoutQuote.setBaseGrandTotal(0);
			checkoutQuote.setBaseSubtotal(0);
			checkoutQuote.setGrandTotal(0);
			checkoutQuote.setSubtotal(0);
			checkoutQuote.setUpdatedAt(Utilities.getCurrentDateTime());
			this.checkoutQuoteService.addCheckoutQuote(checkoutQuote);
		}
		else {
			checkoutQuote = this.checkoutQuoteService.getCheckoutQuoteById(cartId);
//			checkoutQuote.setCreatedAt(this.checkoutQuoteService.getCheckoutQuoteById(checkoutQuote.getId()).getCreatedAt());
		}	
		insertedCartId = checkoutQuote.getId();
		System.out.println("CheckoutQuote ID: insertedCartId : "+insertedCartId);
		
		//Making entry to CheckoutQuoteItem
		ProductsMaster productItem = getJVoidProduct(checkoutAddToCart.getProductId());
		
		CheckoutQuoteItem checkoutQuoteItem = this.checkoutQuoteItemService.getCheckoutQuoteItem(cartId, productId);
		int addingNew = 0;
		if (checkoutQuoteItem == null) {
			addingNew = 1;
			checkoutQuoteItem = new CheckoutQuoteItem();
			checkoutQuote.setItemsCount(checkoutQuote.getItemsCount()+1);
			checkoutQuoteItem.setCreatedAt(Utilities.getCurrentDateTime());
		}
		
		checkoutQuoteItem.setWeight(productItem.getWeight());
		checkoutQuoteItem.setQuantity(checkoutQuoteItem.getQuantity()+checkoutAddToCart.getQuantity());
		checkoutQuoteItem.setSku(productItem.getSku());
		checkoutQuoteItem.setPrice(productItem.getPrice());
		checkoutQuoteItem.setBasePrice(productItem.getPrice());
		checkoutQuoteItem.setDescription(productItem.getDescription());
		checkoutQuoteItem.setName(productItem.getName());
		checkoutQuoteItem.setProductId(productItem.getId());
		checkoutQuoteItem.setQuoteId(insertedCartId);
		
//		if (checkoutAddToCart.getCartId() == -1) {
			checkoutQuote.setItemsQuantity(checkoutQuote.getItemsQuantity()+checkoutAddToCart.getQuantity());
//		}
//		else {
//			checkoutQuoteItem.setCreatedAt(this.checkoutQuoteItemService.getCheckoutQuoteItemById(checkoutQuoteItem.getId()).getCreatedAt());
//		}
		checkoutQuoteItem.setRowTotal(checkoutQuoteItem.getPrice()*checkoutQuoteItem.getQuantity());
		checkoutQuoteItem.setBaseRowTotal(checkoutQuoteItem.getBasePrice()*checkoutQuoteItem.getQuantity());
		checkoutQuoteItem.setRowWeight(checkoutQuoteItem.getWeight()*checkoutQuoteItem.getQuantity());
		checkoutQuoteItem.setUpdatedAt(Utilities.getCurrentDateTime());
		
		this.checkoutQuoteItemService.addCheckoutQuoteItem(checkoutQuoteItem);
		int insertedProductId = checkoutQuoteItem.getId();
		System.out.println("CheckoutItem ID: insertedProductId : "+insertedProductId);
		
		System.out.println("Abhi checkoutquote b4 = "+checkoutQuote.toString());
		
		if (addingNew == 0) {
			checkoutQuote.setBaseSubtotal(checkoutQuote.getBaseSubtotal()+checkoutQuoteItem.getBasePrice()*checkoutAddToCart.getQuantity());
			checkoutQuote.setSubtotal(checkoutQuote.getSubtotal()+checkoutQuoteItem.getPrice()*checkoutAddToCart.getQuantity());
		}
		else {
			checkoutQuote.setBaseSubtotal(checkoutQuote.getBaseSubtotal()+checkoutQuoteItem.getBaseRowTotal());
			checkoutQuote.setSubtotal(checkoutQuote.getSubtotal()+checkoutQuoteItem.getRowTotal());
		}
		
			checkoutQuote.setBaseGrandTotal(checkoutQuote.getBaseSubtotal()+50);
			checkoutQuote.setGrandTotal(checkoutQuote.getSubtotal()+50);
			checkoutQuote.setUpdatedAt(Utilities.getCurrentDateTime());
			
			
			this.checkoutQuoteService.addCheckoutQuote(checkoutQuote);
			System.out.println("Abhi checkoutquote after = "+checkoutQuote.toString());
//		}
		
		JSONObject jsonObj = new JSONObject();
		try {
			jsonObj.put("cartId", insertedCartId);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObj.toString();
		
	}
	
	public ProductsMaster getJVoidProduct(int productId) {	
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		JSONObject jsonObj = new JSONObject();
		try {
			jsonObj.put("id", productId);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("param jsonObj=>"+jsonObj.toString());
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(PRODUCT_SERVER_URI+URIConstants.GET_PRODUCT)
		        .queryParam("params", jsonObj);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		HttpEntity<String> returnString = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET, entity, String.class);
		System.out.println("returnString=>"+returnString);
		
		JSONObject returnJsonObj = null;
		try {
			returnJsonObj = new JSONObject(returnString.getBody());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONArray productsArr = null;
		ProductsMaster productsMaster = null;
        try {
        	productsArr = returnJsonObj.getJSONArray("products");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    	productsMaster = new ProductsMaster();
	    try {
			ObjectMapper mapper = new ObjectMapper();
			try {
				productsMaster = mapper.readValue(productsArr.getJSONObject(0).toString(), ProductsMaster.class);
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
		
		return productsMaster;
	}
	
	
	@RequestMapping(value = "quote/cart", method = RequestMethod.GET)
	public @ResponseBody String getCart(@RequestParam(required = false, value = "callback") String callback,
			@RequestParam(required = false, value = "params") JSONObject jsonParams, HttpServletResponse response){
		
		//System.out.println("TOTAL RECS1:"+this.productsMasterService.getAllProducts().size());
		
		int cartId = -1;
		try {
			cartId = jsonParams.getInt("cartId");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		JSONObject cartObject = new JSONObject();
		JSONArray quoteItems = new JSONArray();
		
		List<CheckoutQuoteItem> quoteItemsList = null;
		quoteItemsList = this.checkoutQuoteItemService.listCheckoutQuoteItems(cartId);
		System.out.println("ABHI Quote Item List Count= "+quoteItemsList.size());
		ObjectMapper mapper = new ObjectMapper();
		for(int i=0; i<quoteItemsList.size(); i++) {
			
			try {
				String strQuoteItemObj = mapper.writeValueAsString(quoteItemsList.get(i));
				JSONObject jsonObj = new JSONObject(strQuoteItemObj);
				
				quoteItems.put(jsonObj);
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
		
		CheckoutQuote currentQuote = this.checkoutQuoteService.getCheckoutQuoteById(cartId);
		
		try {
			cartObject.put("items", quoteItems);
			cartObject.put("total", currentQuote.getGrandTotal());
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
						
		
		return cartObject.toString();
		
	}
	
	@RequestMapping(value = "quote/delete", method = RequestMethod.GET)
	public @ResponseBody String deleteCart(@RequestParam(required = false, value = "callback") String callback,
			@RequestParam(required = false, value = "params") JSONObject jsonParams, HttpServletResponse response){
//		String jstr = "{\"cartId\":-1, \"productId\":2, \"attributeId\":1, \"quantity\":2}";
//		String jstr = "{\"cartId\":1, \"productId\":3, \"attributeId\":1, \"quantity\":2}";
		System.out.println("Inside delete");
		int cartId = -1;
		int productId = -1;
		try {
			cartId = jsonParams.getInt("cartId");
			productId = jsonParams.getInt("productId");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
//		cartId = 1;
//		productId = -1;
		
		
		CheckoutQuoteItem quoteItemToDelete = null;
		CheckoutQuote checkoutQuote = new CheckoutQuote();
		List<CheckoutQuoteItem> quoteItemsList = null;
		if (productId != -1) {
			quoteItemToDelete = this.checkoutQuoteItemService.getCheckoutQuoteItem(cartId, productId);
			quoteItemsList = new ArrayList<CheckoutQuoteItem>();
			quoteItemsList.add(quoteItemToDelete);
		}
		else {
			quoteItemsList = this.checkoutQuoteItemService.listCheckoutQuoteItems(cartId);
			System.out.println("ABHI Quote Item List Count= "+quoteItemsList.size());
		}
		
		checkoutQuote = this.checkoutQuoteService.getCheckoutQuoteById(cartId);
		for(int i=0; i<quoteItemsList.size(); i++) {
			CheckoutQuoteItem currentQuoteItemToDelete = quoteItemsList.get(i);
			
			checkoutQuote.setItemsCount(checkoutQuote.getItemsCount()-1);
			checkoutQuote.setItemsQuantity(checkoutQuote.getItemsQuantity()-currentQuoteItemToDelete.getQuantity());
			checkoutQuote.setBaseSubtotal(checkoutQuote.getBaseSubtotal()-currentQuoteItemToDelete.getBaseRowTotal());
			checkoutQuote.setSubtotal(checkoutQuote.getSubtotal()-currentQuoteItemToDelete.getRowTotal());
			this.checkoutQuoteItemService.removeCheckoutQuoteItem(currentQuoteItemToDelete.getId());
		}
		checkoutQuote.setBaseGrandTotal(checkoutQuote.getBaseSubtotal()+50);
		checkoutQuote.setGrandTotal(checkoutQuote.getSubtotal()+50);
		checkoutQuote.setUpdatedAt(Utilities.getCurrentDateTime());
		System.out.println("Abhi checkoutquote after = "+checkoutQuote.toString());
		
		int returnCartId = cartId;
		if (productId == -1) {
			this.checkoutQuoteService.removeCheckoutQuote(checkoutQuote.getId());
			returnCartId = -1;
		}
		else {
			this.checkoutQuoteService.addCheckoutQuote(checkoutQuote);
		}
		
		JSONObject jsonObj = new JSONObject();
		try {
			jsonObj.put("cartId", returnCartId);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObj.toString();
		
	}
}
