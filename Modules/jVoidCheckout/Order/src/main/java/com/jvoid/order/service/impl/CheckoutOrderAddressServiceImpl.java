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
package com.jvoid.order.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jvoid.order.dao.CheckoutOrderAddressDAO;
import com.jvoid.order.model.CheckoutOrderAddress;
import com.jvoid.order.service.CheckoutOrderAddressService;

@Service
public class CheckoutOrderAddressServiceImpl implements CheckoutOrderAddressService {
	
	private CheckoutOrderAddressDAO checkout_order_addressDAO;

	public void setCheckoutOrderAddressDAO(CheckoutOrderAddressDAO checkout_order_addressDAO) {
		this.checkout_order_addressDAO = checkout_order_addressDAO;
	}

	@Transactional
	public void addCheckoutOrderAddress(CheckoutOrderAddress p) {
		this.checkout_order_addressDAO.saveOrUpdate(p);
	}

	@Transactional
	public void updateCheckoutOrderAddress(CheckoutOrderAddress p) {
		this.checkout_order_addressDAO.saveOrUpdate(p);
	}

	@Transactional
	public List<CheckoutOrderAddress> listCheckoutOrderAddress() {
		return this.checkout_order_addressDAO.findAll();
	}

	@Transactional
	public CheckoutOrderAddress getCheckoutOrderAddressById(int id) {
		return this.checkout_order_addressDAO.findById(id,false);
	}

	@Transactional
	public void removeCheckoutOrderAddress(int id) {
		CheckoutOrderAddress p = this.checkout_order_addressDAO.findById(id, false);
		this.checkout_order_addressDAO.removeEntity(p);
	}

}
