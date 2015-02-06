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

import com.jvoid.customers.customer.dao.CustomerAddressAttributeValuesDAO;
import com.jvoid.customers.customer.model.CustomerAddressAttributeValues;
import com.jvoid.customers.customer.service.CustomerAddressAttributeValuesService;

@Service
public class CustomerAddressAttributeValuesServiceImpl implements CustomerAddressAttributeValuesService {
	
	private CustomerAddressAttributeValuesDAO customer_address_attribute_valuesDAO;

	public void setCustomerAddressAttributeValuesDAO(CustomerAddressAttributeValuesDAO customer_address_attribute_valuesDAO) {
		this.customer_address_attribute_valuesDAO = customer_address_attribute_valuesDAO;
	}

	@Transactional
	public void addCustomerAddressAttributeValues(CustomerAddressAttributeValues p) {
		this.customer_address_attribute_valuesDAO.saveOrUpdate(p);
	}

	@Transactional
	public void updateCustomerAddressAttributeValues(CustomerAddressAttributeValues p) {
		this.customer_address_attribute_valuesDAO.saveOrUpdate(p);
	}

	@Transactional
	public List<CustomerAddressAttributeValues> listCustomerAddressAttributeValues() {
		return this.customer_address_attribute_valuesDAO.findAll();
	}

	@Transactional
	public CustomerAddressAttributeValues getCustomerAddressAttributeValuesById(int id) {
		return this.customer_address_attribute_valuesDAO.findById(id,false);
	}

	@Transactional
	public void removeCustomerAddressAttributeValues(int id) {
		CustomerAddressAttributeValues p = this.customer_address_attribute_valuesDAO.findById(id, false);
		this.customer_address_attribute_valuesDAO.removeEntity(p);
	}

}
