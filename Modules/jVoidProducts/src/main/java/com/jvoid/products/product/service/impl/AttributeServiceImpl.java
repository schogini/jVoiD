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
 package com.jvoid.products.product.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jvoid.products.product.dao.AttributeDAO;
import com.jvoid.products.product.model.Attribute;
import com.jvoid.products.product.service.AttributeService;

@Service
public class AttributeServiceImpl implements AttributeService {
	
	private AttributeDAO attributeDAO;

	public void setAttributeDAO(AttributeDAO attributeDAO) {
		this.attributeDAO = attributeDAO;
	}

	@Transactional
	public void addAttribute(Attribute p) {
		this.attributeDAO.saveOrUpdate(p);
	}

	@Transactional
	public void updateAttribute(Attribute p) {
		this.attributeDAO.saveOrUpdate(p);
	}

	@Transactional
	public List<Attribute> listAttributes() {
		return this.attributeDAO.findAll();
	}

	@Transactional
	public Attribute getAttributeById(int id) {
		return this.attributeDAO.findById(id,false);
	}

	@Transactional
	public void removeAttribute(int id) {
		Attribute p = this.attributeDAO.findById(id, false);
		this.attributeDAO.removeEntity(p);
	}

}
