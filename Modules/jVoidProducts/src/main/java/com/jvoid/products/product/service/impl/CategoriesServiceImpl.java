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

import com.jvoid.products.product.dao.CategoriesDAO;
import com.jvoid.products.product.model.Categories;
import com.jvoid.products.product.service.CategoriesService;

@Service
public class CategoriesServiceImpl implements CategoriesService {
	
	private CategoriesDAO categoriesDAO;

	public void setCategoriesDAO(CategoriesDAO categoriesDAO) {
		this.categoriesDAO = categoriesDAO;
	}

	@Transactional
	public void addCategories(Categories p) {
		this.categoriesDAO.saveOrUpdate(p);
	}

	@Transactional
	public void updateCategories(Categories p) {
		this.categoriesDAO.saveOrUpdate(p);
	}

	@Transactional
	public List<Categories> listCategories() {
		return this.categoriesDAO.findAll();
	}

	@Transactional
	public Categories getCategoriesById(int id) {
		return this.categoriesDAO.findById(id,false);
	}

	@Transactional
	public void removeCategories(int id) {
		Categories p = this.categoriesDAO.findById(id, false);
		this.categoriesDAO.removeEntity(p);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Categories> getAllChildCategories(int categoryId) {
		return this.categoriesDAO.getAllChildCategories(categoryId);
	}

}
