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

import com.jvoid.products.product.dao.AttributeSetDAO;
import com.jvoid.products.product.model.AttributeSet;
import com.jvoid.products.product.service.AttributeSetService;

@Service
public class AttributeSetServiceImpl implements AttributeSetService {
	
	private AttributeSetDAO attribute_setDAO;

	public void setAttributeSetDAO(AttributeSetDAO attribute_setDAO) {
		this.attribute_setDAO = attribute_setDAO;
	}

	@Transactional
	public void addAttributeSet(AttributeSet p) {
		this.attribute_setDAO.saveOrUpdate(p);
	}

	@Transactional
	public void updateAttributeSet(AttributeSet p) {
		this.attribute_setDAO.saveOrUpdate(p);
	}

	@Transactional
	public List<AttributeSet> listAttributeSets() {
		return this.attribute_setDAO.findAll();
	}

	@Transactional
	public AttributeSet getAttributeSetById(int id) {
		return this.attribute_setDAO.findById(id,false);
	}

	@Transactional
	public void removeAttributeSet(int id) {
		AttributeSet p = this.attribute_setDAO.findById(id, false);
		this.attribute_setDAO.removeEntity(p);
	}

}
