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

import com.jvoid.order.dao.CheckoutOrderItemDAO;
import com.jvoid.order.model.CheckoutOrderItem;
import com.jvoid.order.service.CheckoutOrderItemService;

@Service
public class CheckoutOrderItemServiceImpl implements CheckoutOrderItemService {
	
	private CheckoutOrderItemDAO checkout_order_itemDAO;

	public void setCheckoutOrderItemDAO(CheckoutOrderItemDAO checkout_order_itemDAO) {
		this.checkout_order_itemDAO = checkout_order_itemDAO;
	}

	@Transactional
	public void addCheckoutOrderItem(CheckoutOrderItem p) {
		this.checkout_order_itemDAO.saveOrUpdate(p);
	}

	@Transactional
	public void updateCheckoutOrderItem(CheckoutOrderItem p) {
		this.checkout_order_itemDAO.saveOrUpdate(p);
	}

	@Transactional
	public List<CheckoutOrderItem> listCheckoutOrderItems() {
		return this.checkout_order_itemDAO.findAll();
	}

	@Transactional
	public CheckoutOrderItem getCheckoutOrderItemById(int id) {
		return this.checkout_order_itemDAO.findById(id,false);
	}

	@Transactional
	public void removeCheckoutOrderItem(int id) {
		CheckoutOrderItem p = this.checkout_order_itemDAO.findById(id, false);
		this.checkout_order_itemDAO.removeEntity(p);
	}

	@Override
	@Transactional(readOnly=true)
	public List<CheckoutOrderItem> listCheckoutOrderItems(int orderId) {
		// TODO Auto-generated method stub
		return this.checkout_order_itemDAO.listCheckoutOrderItems(orderId);
	}

}
