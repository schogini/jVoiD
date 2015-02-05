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

import com.jvoid.products.product.dao.CategoryProductsDAO;
import com.jvoid.products.product.model.CategoryProducts;
import com.jvoid.products.product.service.CategoryProductsService;

@Service
public class CategoryProductsServiceImpl implements CategoryProductsService {
	
	private CategoryProductsDAO category_productsDAO;

	public void setCategoryProductsDAO(CategoryProductsDAO category_productsDAO) {
		this.category_productsDAO = category_productsDAO;
	}

	@Transactional
	public void addCategoryProducts(CategoryProducts p) {
		this.category_productsDAO.saveOrUpdate(p);
	}

	@Transactional
	public void updateCategoryProducts(CategoryProducts p) {
		this.category_productsDAO.saveOrUpdate(p);
	}

	@Transactional
	public List<CategoryProducts> listCategoryProducts() {
		return this.category_productsDAO.findAll();
	}

	@Transactional
	public CategoryProducts getCategoryProductsById(int id) {
		return this.category_productsDAO.findById(id,false);
	}

	@Transactional
	public void removeCategoryProducts(int id) {
		CategoryProducts p = this.category_productsDAO.findById(id, false);
		this.category_productsDAO.removeEntity(p);
	}

	@Override
	@Transactional(readOnly=true)
	public CategoryProducts getCategoryProductByCategoryIdAndProductId(
			int categoryId, int productId) {
		return this.category_productsDAO.getCategoryProductByCategoryIdAndProductId(categoryId, productId);
	}

	@Override
	@Transactional(readOnly=true)
	public List<CategoryProducts> getCategoryProductsByCategoryId(int categoryId) {
		return this.category_productsDAO.getCategoryProductsByCategoryId(categoryId);
	}

}
