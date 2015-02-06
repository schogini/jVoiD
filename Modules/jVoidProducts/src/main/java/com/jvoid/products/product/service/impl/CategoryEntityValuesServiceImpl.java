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

import com.jvoid.products.product.dao.CategoryEntityValuesDAO;
import com.jvoid.products.product.model.CategoryEntityValues;
import com.jvoid.products.product.service.CategoryEntityValuesService;

@Service
public class CategoryEntityValuesServiceImpl implements CategoryEntityValuesService {
	
	private CategoryEntityValuesDAO category_entity_valuesDAO;

	public void setCategoryEntityValuesDAO(CategoryEntityValuesDAO category_attribute_valuesDAO) {
		this.category_entity_valuesDAO = category_attribute_valuesDAO;
	}

	@Transactional
	public void addCategoryAttributeValues(CategoryEntityValues p) {
		this.category_entity_valuesDAO.saveOrUpdate(p);
	}

	@Transactional
	public void updateCategoryAttributeValues(CategoryEntityValues p) {
		this.category_entity_valuesDAO.saveOrUpdate(p);
	}

	@Transactional
	public List<CategoryEntityValues> listCategoryAttributeValues() {
		return this.category_entity_valuesDAO.findAll();
	}

	@Transactional
	public CategoryEntityValues getCategoryAttributeValuesById(int id) {
		return this.category_entity_valuesDAO.findById(id,false);
	}

	@Transactional
	public void removeCategoryAttributeValues(int id) {
		CategoryEntityValues p = this.category_entity_valuesDAO.findById(id, false);
		this.category_entity_valuesDAO.removeEntity(p);
	}

	@Override
	@Transactional(readOnly=true)
	public CategoryEntityValues getCategoryAttributesByCategoryId(
			int categoryId) {
		return this.category_entity_valuesDAO.getCategoryAttributesByCategoryId(categoryId);
		
	}

}
