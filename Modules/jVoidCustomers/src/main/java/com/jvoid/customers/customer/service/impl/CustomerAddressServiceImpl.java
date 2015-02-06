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
package com.jvoid.customers.customer.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jvoid.customers.customer.dao.CustomerAddressDAO;
import com.jvoid.customers.customer.model.CustomerAddress;
import com.jvoid.customers.customer.service.CustomerAddressService;

@Service
public class CustomerAddressServiceImpl implements CustomerAddressService {
	
	private CustomerAddressDAO customer_addressDAO;

	public void setCustomerAddressDAO(CustomerAddressDAO customer_addressDAO) {
		this.customer_addressDAO = customer_addressDAO;
	}

	@Transactional
	public void addCustomerAddress(CustomerAddress p) {
		this.customer_addressDAO.saveOrUpdate(p);
	}

	@Transactional
	public void updateCustomerAddress(CustomerAddress p) {
		this.customer_addressDAO.saveOrUpdate(p);
	}

	@Transactional
	public List<CustomerAddress> listCustomerAddress() {
		return this.customer_addressDAO.findAll();
	}

	@Transactional
	public CustomerAddress getCustomerAddressById(int id) {
		return this.customer_addressDAO.findById(id,false);
	}

	@Transactional
	public void removeCustomerAddress(int id) {
		CustomerAddress p = this.customer_addressDAO.findById(id, false);
		this.customer_addressDAO.removeEntity(p);
	}

}
