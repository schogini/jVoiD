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
package com.jvoid.attributes.attribute.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jvoid.attributes.attribute.dao.AttributesDAO;
import com.jvoid.attributes.attribute.model.Attributes;
import com.jvoid.attributes.attribute.service.AttributesService;

@Service
public class AttributesServiceImpl implements AttributesService {
	
	private AttributesDAO attributesDAO;

	public void setAttributesDAO(AttributesDAO attributesDAO) {
		this.attributesDAO = attributesDAO;
	}

	@Transactional
	public void addAttributes(Attributes p) {
		this.attributesDAO.saveOrUpdate(p);
	}

	@Transactional
	public void updateAttributes(Attributes p) {
		this.attributesDAO.saveOrUpdate(p);
	}

	@Transactional
	public List<Attributes> listAttributes() {
		return this.attributesDAO.findAll();
	}

	@Transactional
	public Attributes getAttributesById(int id) {
		return this.attributesDAO.findById(id,false);
	}

	@Transactional
	public void removeAttributes(int id) {
		Attributes p = this.attributesDAO.findById(id, false);
		this.attributesDAO.removeEntity(p);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Attributes> getAttributesByType(String type) {
		return this.attributesDAO.getAttributesByType(type);
	}

}
