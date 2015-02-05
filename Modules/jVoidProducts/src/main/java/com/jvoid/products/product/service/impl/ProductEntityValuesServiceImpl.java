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

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jvoid.products.product.dao.ProductEntityValuesDAO;
import com.jvoid.products.product.model.ProductEntityValues;
import com.jvoid.products.product.service.ProductEntityValuesService;

@Service
public class ProductEntityValuesServiceImpl implements ProductEntityValuesService {
	
	private ProductEntityValuesDAO product_entity_valuesDAO;

	public void setProductEntityValuesDAO(ProductEntityValuesDAO product_entity_valuesDAO) {
		this.product_entity_valuesDAO = product_entity_valuesDAO;
	}

	@Transactional
	public void addProductAttributeValues(ProductEntityValues p) {
		this.product_entity_valuesDAO.saveOrUpdate(p);
	}

	@Transactional
	public void updateProductAttributeValues(ProductEntityValues p) {
		this.product_entity_valuesDAO.saveOrUpdate(p);
	}

	@Transactional
	public List<ProductEntityValues> listProductAttributeValues() {
		return this.product_entity_valuesDAO.findAll();
	}
	
	@Transactional
	public List<ProductEntityValues> listProductAttributeValuesByProductId(int id){
		Criterion cr = Restrictions.eq("productId", id);
		return this.product_entity_valuesDAO.findAllByCriteria(cr);
	}

	@Transactional
	public ProductEntityValues getProductAttributeValuesById(int id) {
		return this.product_entity_valuesDAO.findById(id,false);
	}

	@Transactional
	public void removeProductAttributeValues(int id) {
		ProductEntityValues p = this.product_entity_valuesDAO.findById(id, false);
		this.product_entity_valuesDAO.removeEntity(p);
	}

	@Override
	@Transactional(readOnly=true)
	public ProductEntityValues getProductAttributeValuesByProductIdAndAttributeId(
			int pid, int aid) {
		System.out.println("Search by Pid and aid: " + pid+","+ aid);
		return this.product_entity_valuesDAO.getProductAttributeValuesByProductIdAndAttributeId(pid, aid);
	}

}
