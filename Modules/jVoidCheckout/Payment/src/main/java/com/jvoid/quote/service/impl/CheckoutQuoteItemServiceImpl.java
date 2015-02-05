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

import com.jvoid.quote.dao.CheckoutQuoteItemDAO;
import com.jvoid.quote.model.CheckoutQuoteItem;
import com.jvoid.quote.service.CheckoutQuoteItemService;

@Service
public class CheckoutQuoteItemServiceImpl implements CheckoutQuoteItemService {
	
	private CheckoutQuoteItemDAO checkout_quote_itemDAO;

	public void setCheckoutQuoteItemDAO(CheckoutQuoteItemDAO checkout_quote_itemDAO) {
		this.checkout_quote_itemDAO = checkout_quote_itemDAO;
	}

	@Transactional
	public void addCheckoutQuoteItem(CheckoutQuoteItem p) {
		this.checkout_quote_itemDAO.saveOrUpdate(p);
	}

	@Transactional
	public void updateCheckoutQuoteItem(CheckoutQuoteItem p) {
		this.checkout_quote_itemDAO.saveOrUpdate(p);
	}

	@Transactional
	public List<CheckoutQuoteItem> listCheckoutQuoteItems() {
		return this.checkout_quote_itemDAO.findAll();
	}

	@Transactional
	public CheckoutQuoteItem getCheckoutQuoteItemById(int id) {
		return this.checkout_quote_itemDAO.findById(id,false);
	}

	@Transactional
	public void removeCheckoutQuoteItem(int id) {
		CheckoutQuoteItem p = this.checkout_quote_itemDAO.findById(id, false);
		this.checkout_quote_itemDAO.removeEntity(p);
	}

	@Override
	@Transactional(readOnly=true)
	public List<CheckoutQuoteItem> listCheckoutQuoteItems(int quoteId) {
		// TODO Auto-generated method stub
		return this.checkout_quote_itemDAO.listCheckoutQuoteItems(quoteId);
	}

	@Override
	@Transactional(readOnly=true)
	public CheckoutQuoteItem getCheckoutQuoteItem(int quoteId, int productId) {
		// TODO Auto-generated method stub
		return this.checkout_quote_itemDAO.getCheckoutQuoteItem(quoteId, productId);
	}

}
