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
package com.jvoid.quote.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jvoid.quote.dao.CheckoutQuoteAddressDAO;
import com.jvoid.quote.model.CheckoutQuoteAddress;
import com.jvoid.quote.service.CheckoutQuoteAddressService;

@Service
public class CheckoutQuoteAddressServiceImpl implements CheckoutQuoteAddressService {
	
	private CheckoutQuoteAddressDAO checkout_quote_addressDAO;

	public void setCheckoutQuoteAddressDAO(CheckoutQuoteAddressDAO checkout_quote_addressDAO) {
		this.checkout_quote_addressDAO = checkout_quote_addressDAO;
	}

	@Transactional
	public void addCheckoutQuoteAddress(CheckoutQuoteAddress p) {
		this.checkout_quote_addressDAO.saveOrUpdate(p);
	}

	@Transactional
	public void updateCheckoutQuoteAddress(CheckoutQuoteAddress p) {
		this.checkout_quote_addressDAO.saveOrUpdate(p);
	}

	@Transactional
	public List<CheckoutQuoteAddress> listCheckoutQuoteAddress() {
		return this.checkout_quote_addressDAO.findAll();
	}

	@Transactional
	public CheckoutQuoteAddress getCheckoutQuoteAddressById(int id) {
		return this.checkout_quote_addressDAO.findById(id,false);
	}

	@Transactional
	public void removeCheckoutQuoteAddress(int id) {
		CheckoutQuoteAddress p = this.checkout_quote_addressDAO.findById(id, false);
		this.checkout_quote_addressDAO.removeEntity(p);
	}

}
