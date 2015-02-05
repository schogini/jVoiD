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
 package com.jvoid.customers.customer.service;

import java.util.List;

import com.jvoid.customers.customer.model.CustomerEntityValues;

/**
 * Service class for CustomerEntityValues 
 * 
 * @author Shajir K, Rajeev Pillai
 * @version 1.0
 */

public interface CustomerEntityValuesService {

	public void addCustomerAttributeValues(CustomerEntityValues p);
	public void updateCustomerAttributeValues(CustomerEntityValues p);
	public List<CustomerEntityValues> listCustomerAttributeValues();
	public CustomerEntityValues getCustomerAttributeValuesById(int id);
	public void removeCustomerAttributeValues(int id);
	public List<CustomerEntityValues> getCustomerEntityValuesByCustomerId(int customerId);
	public CustomerEntityValues getCustomerEntityValuesByCustomerIdAndAttributeId(int customerId, int attributeId);
}
