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

import com.jvoid.products.product.dao.ProductAttributeValuesDAO;
import com.jvoid.products.product.model.ProductAttributeValues;
import com.jvoid.products.product.service.ProductAttributeValuesService;

@Service
public class ProductAttributeValuesServiceImpl implements ProductAttributeValuesService {
	
	private ProductAttributeValuesDAO product_attribute_valuesDAO;

	public void setProductAttributeValuesDAO(ProductAttributeValuesDAO product_attribute_valuesDAO) {
		this.product_attribute_valuesDAO = product_attribute_valuesDAO;
	}

	@Transactional
	public void addProductAttributeValues(ProductAttributeValues p) {
		this.product_attribute_valuesDAO.saveOrUpdate(p);
	}

	@Transactional
	public void updateProductAttributeValues(ProductAttributeValues p) {
		this.product_attribute_valuesDAO.saveOrUpdate(p);
	}

	@Transactional
	public List<ProductAttributeValues> listProductAttributeValues() {
		return this.product_attribute_valuesDAO.findAll();
	}

	@Transactional
	public ProductAttributeValues getProductAttributeValuesById(int id) {
		return this.product_attribute_valuesDAO.findById(id,false);
	}

	@Transactional
	public void removeProductAttributeValues(int id) {
		ProductAttributeValues p = this.product_attribute_valuesDAO.findById(id, false);
		this.product_attribute_valuesDAO.removeEntity(p);
	}

}
