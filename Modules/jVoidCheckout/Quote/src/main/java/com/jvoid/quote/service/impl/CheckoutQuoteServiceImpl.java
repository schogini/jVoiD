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

import com.jvoid.quote.dao.CheckoutQuoteDAO;
import com.jvoid.quote.model.CheckoutQuote;
import com.jvoid.quote.service.CheckoutQuoteService;

@Service
public class CheckoutQuoteServiceImpl implements CheckoutQuoteService {
	
	private CheckoutQuoteDAO checkout_quoteDAO;

	public void setCheckoutQuoteDAO(CheckoutQuoteDAO checkout_quoteDAO) {
		this.checkout_quoteDAO = checkout_quoteDAO;
	}

	@Transactional
	public void addCheckoutQuote(CheckoutQuote p) {
		this.checkout_quoteDAO.saveOrUpdate(p);
	}

	@Transactional
	public void updateCheckoutQuote(CheckoutQuote p) {
		this.checkout_quoteDAO.saveOrUpdate(p);
	}

	@Transactional
	public List<CheckoutQuote> listCheckoutQuotes() {
		return this.checkout_quoteDAO.findAll();
	}

	@Transactional
	public CheckoutQuote getCheckoutQuoteById(int id) {
		return this.checkout_quoteDAO.findById(id,false);
	}

	@Transactional
	public void removeCheckoutQuote(int id) {
		CheckoutQuote p = this.checkout_quoteDAO.findById(id, false);
		this.checkout_quoteDAO.removeEntity(p);
	}

}
