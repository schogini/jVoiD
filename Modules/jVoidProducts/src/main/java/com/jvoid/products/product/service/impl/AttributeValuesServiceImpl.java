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
 package com.jvoid.products.product.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jvoid.products.product.dao.AttributeValuesDAO;
import com.jvoid.products.product.model.AttributeValues;
import com.jvoid.products.product.service.AttributeValuesService;

@Service
public class AttributeValuesServiceImpl implements AttributeValuesService {
	
	private AttributeValuesDAO attribute_valuesDAO;

	public void setAttributeValuesDAO(AttributeValuesDAO attribute_valuesDAO) {
		this.attribute_valuesDAO = attribute_valuesDAO;
	}

	@Transactional
	public void addAttributeValues(AttributeValues p) {
		this.attribute_valuesDAO.saveOrUpdate(p);
	}

	@Transactional
	public void updateAttributeValues(AttributeValues p) {
		this.attribute_valuesDAO.saveOrUpdate(p);
	}

	@Transactional
	public List<AttributeValues> listAttributeValues() {
		return this.attribute_valuesDAO.findAll();
	}

	@Transactional
	public AttributeValues getAttributeValuesById(int id) {
		return this.attribute_valuesDAO.findById(id,false);
	}

	@Transactional
	public void removeAttributeValues(int id) {
		AttributeValues p = this.attribute_valuesDAO.findById(id, false);
		this.attribute_valuesDAO.removeEntity(p);
	}

}
