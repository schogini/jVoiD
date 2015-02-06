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
package com.jvoid.order.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jvoid.order.dao.CheckoutOrderDAO;
import com.jvoid.order.model.CheckoutOrder;
import com.jvoid.order.service.CheckoutOrderService;

@Service
public class CheckoutOrderServiceImpl implements CheckoutOrderService {
	
	private CheckoutOrderDAO checkout_orderDAO;

	public void setCheckoutOrderDAO(CheckoutOrderDAO checkout_orderDAO) {
		this.checkout_orderDAO = checkout_orderDAO;
	}

	@Transactional
	public void addCheckoutOrder(CheckoutOrder p) {
		this.checkout_orderDAO.saveOrUpdate(p);
	}

	@Transactional
	public void updateCheckoutOrder(CheckoutOrder p) {
		this.checkout_orderDAO.saveOrUpdate(p);
	}

	@Transactional
	public List<CheckoutOrder> listCheckoutOrders() {
		return this.checkout_orderDAO.findAll();
	}

	@Transactional
	public CheckoutOrder getCheckoutOrderById(int id) {
		return this.checkout_orderDAO.findById(id,false);
	}

	@Transactional
	public void removeCheckoutOrder(int id) {
		CheckoutOrder p = this.checkout_orderDAO.findById(id, false);
		this.checkout_orderDAO.removeEntity(p);
	}

	@Override
	@Transactional(readOnly=true)
	public List<CheckoutOrder> listCheckoutOrders(int customerId) {
		// TODO Auto-generated method stub
		return this.checkout_orderDAO.listCheckoutOrders(customerId);
	}

}
