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

import com.jvoid.products.product.dao.ProductDAO;
import com.jvoid.products.product.model.Product;
import com.jvoid.products.product.service.ProductService;


@Service
public class ProductServiceImpl implements ProductService {
	
	private ProductDAO productDAO;

	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	@Transactional
	public void addproduct(Product p) {
		this.productDAO.saveOrUpdate(p);
	}

	@Transactional
	public void updateproduct(Product p) {
		this.productDAO.saveOrUpdate(p);
	}

	@Transactional
	public List<Product> listproducts() {
		return this.productDAO.findAll();
	}

	@Transactional
	public Product getproductById(int id) {
		return this.productDAO.findById(id,false);
	}

	@Transactional
	public void removeproduct(int id) {
		Product p = this.productDAO.findById(id, false);
		this.productDAO.removeEntity(p);
	}

}
