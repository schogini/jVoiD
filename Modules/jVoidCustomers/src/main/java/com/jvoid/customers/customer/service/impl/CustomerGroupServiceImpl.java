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

import com.jvoid.customers.customer.dao.CustomerGroupDAO;
import com.jvoid.customers.customer.model.CustomerGroup;
import com.jvoid.customers.customer.service.CustomerGroupService;

@Service
public class CustomerGroupServiceImpl implements CustomerGroupService {
	
	private CustomerGroupDAO customer_groupDAO;

	public void setCustomerGroupDAO(CustomerGroupDAO customer_groupDAO) {
		this.customer_groupDAO = customer_groupDAO;
	}

	@Transactional
	public void addCustomerGroup(CustomerGroup p) {
		this.customer_groupDAO.saveOrUpdate(p);
	}

	@Transactional
	public void updateCustomerGroup(CustomerGroup p) {
		this.customer_groupDAO.saveOrUpdate(p);
	}

	@Transactional
	public List<CustomerGroup> listCustomerGroups() {
		return this.customer_groupDAO.findAll();
	}

	@Transactional
	public CustomerGroup getCustomerGroupById(int id) {
		return this.customer_groupDAO.findById(id,false);
	}

	@Transactional
	public void removeCustomerGroup(int id) {
		CustomerGroup p = this.customer_groupDAO.findById(id, false);
		this.customer_groupDAO.removeEntity(p);
	}

}
