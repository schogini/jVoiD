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
package com.jvoid.quote.service;

import java.util.List;

import com.jvoid.quote.model.CheckoutQuoteAddress;

public interface CheckoutQuoteAddressService {

	public void addCheckoutQuoteAddress(CheckoutQuoteAddress p);
	public void updateCheckoutQuoteAddress(CheckoutQuoteAddress p);
	public List<CheckoutQuoteAddress> listCheckoutQuoteAddress();
	public CheckoutQuoteAddress getCheckoutQuoteAddressById(int id);
	public void removeCheckoutQuoteAddress(int id);
	
}
