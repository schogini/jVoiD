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
package com.jvoid.customers.customer.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jvoid.customers.customer.dao.CustomerEntityValuesDAO;
import com.jvoid.customers.customer.model.CustomerEntityValues;
import com.jvoid.customers.customer.service.CustomerEntityValuesService;

@Service
public class CustomerEntityValuesServiceImpl implements CustomerEntityValuesService {
	
	private CustomerEntityValuesDAO customer_attribute_valuesDAO;

	public void setCustomerEntityValuesDAO(CustomerEntityValuesDAO customer_attribute_valuesDAO) {
		this.customer_attribute_valuesDAO = customer_attribute_valuesDAO;
	}

	@Transactional
	public void addCustomerAttributeValues(CustomerEntityValues p) {
		this.customer_attribute_valuesDAO.saveOrUpdate(p);
	}

	@Transactional
	public void updateCustomerAttributeValues(CustomerEntityValues p) {
		this.customer_attribute_valuesDAO.saveOrUpdate(p);
	}

	@Transactional
	public List<CustomerEntityValues> listCustomerAttributeValues() {
		return this.customer_attribute_valuesDAO.findAll();
	}

	@Transactional
	public CustomerEntityValues getCustomerAttributeValuesById(int id) {
		return this.customer_attribute_valuesDAO.findById(id,false);
	}

	@Transactional
	public void removeCustomerAttributeValues(int id) {
		CustomerEntityValues p = this.customer_attribute_valuesDAO.findById(id, false);
		this.customer_attribute_valuesDAO.removeEntity(p);
	}

	@Override
	@Transactional(readOnly=true)
	public List<CustomerEntityValues> getCustomerEntityValuesByCustomerId(int customerId) {
		return this.customer_attribute_valuesDAO.getCustomerEntityValuesByCustomerId(customerId);
	}

	@Override
	@Transactional(readOnly=true)
	public CustomerEntityValues getCustomerEntityValuesByCustomerIdAndAttributeId(
			int customerId, int attributeId) {
		return this.customer_attribute_valuesDAO.getCustomerEntityValuesByCustomerIdAndAttributeId(customerId, attributeId);
	}

}
