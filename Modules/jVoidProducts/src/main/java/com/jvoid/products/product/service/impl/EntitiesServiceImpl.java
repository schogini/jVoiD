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

import com.jvoid.products.product.dao.EntitiesDAO;
import com.jvoid.products.product.model.Entities;
import com.jvoid.products.product.service.EntitiesService;

@Service
public class EntitiesServiceImpl implements EntitiesService {
	
	private EntitiesDAO entitiesDAO;

	public void setEntitiesDAO(EntitiesDAO attributesDAO) {
		this.entitiesDAO = attributesDAO;
	}

	@Transactional
	public void addAttributes(Entities p) {
		this.entitiesDAO.saveOrUpdate(p);
	}

	@Transactional
	public void updateAttributes(Entities p) {
		this.entitiesDAO.saveOrUpdate(p);
	}

	@Transactional
	public List<Entities> listAttributes() {
		return this.entitiesDAO.findAll();
	}

	@Transactional
	public Entities getAttributesById(int id) {
		return this.entitiesDAO.findById(id,false);
	}

	@Transactional
	public void removeAttributes(int id) {
		Entities p = this.entitiesDAO.findById(id, false);
		this.entitiesDAO.removeEntity(p);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Entities> getAttributesByType(String type) {
		return this.entitiesDAO.getAttributesByType(type);
	}

	@Override
	@Transactional(readOnly=true)
	public Entities getAttributesByNameAndType(String name, String type) {
		return this.entitiesDAO.getAttributesByNameAndType(name, type);
	}

}