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

import com.jvoid.products.product.dao.AttributeGroupDAO;
import com.jvoid.products.product.model.AttributeGroup;
import com.jvoid.products.product.service.AttributeGroupService;

@Service
public class AttributeGroupServiceImpl implements AttributeGroupService {
	
	private AttributeGroupDAO attribute_groupDAO;

	public void setAttributeGroupDAO(AttributeGroupDAO attribute_groupDAO) {
		this.attribute_groupDAO = attribute_groupDAO;
	}

	@Transactional
	public void addAttributeGroup(AttributeGroup p) {
		this.attribute_groupDAO.saveOrUpdate(p);
	}

	@Transactional
	public void updateAttributeGroup(AttributeGroup p) {
		this.attribute_groupDAO.saveOrUpdate(p);
	}

	@Transactional
	public List<AttributeGroup> listAttributeGroups() {
		return this.attribute_groupDAO.findAll();
	}

	@Transactional
	public AttributeGroup getAttributeGroupById(int id) {
		return this.attribute_groupDAO.findById(id,false);
	}

	@Transactional
	public void removeAttributeGroup(int id) {
		AttributeGroup p = this.attribute_groupDAO.findById(id, false);
		this.attribute_groupDAO.removeEntity(p);
	}

}
