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
package com.jvoid.core.uricostants;

/**
 * ProductsURIConstants class
 * 
 * @author Shajir
 * @version 1.0
 */

public class URIConstants {
	public static final String GET_PRODUCT = "/catalog/product";
	public static final String GET_CATEGORY = "/catalog/category";
	public static final String GET_PRODUCTS_BY_CATEGORY = "/catalog/categoryproduct";
	public static final String ADD_PRODUCT_TO_CART = "/quote/add";
	public static final String GET_CART = "/quote/cart";
	public static final String DELETE_CART = "/quote/delete";
	public static final String CHECKOUT_CART = "/quote/addaddress";
	public static final String ADD_ORDER = "/order/addaddress";
	public static final String ADD_CUSTOMER = "/customer/add";
	public static final String GET_CUSTOMER_BY_EMAIL = "/customer/getbyemail";
	public static final String UPDATE_CUSTOMER = "/customer/update";
	public static final String RESET_CUSTOMER_PASSWORD = "/customer/resetpassword";
}
