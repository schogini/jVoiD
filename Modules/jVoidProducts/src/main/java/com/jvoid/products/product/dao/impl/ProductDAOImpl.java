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
 package com.jvoid.products.product.dao.impl;

import org.springframework.stereotype.Repository;

import com.jvoid.persistence.hibernate.GenericHibernateDAO;
import com.jvoid.products.product.dao.ProductDAO;
import com.jvoid.products.product.model.Product;


@Repository
public class ProductDAOImpl extends GenericHibernateDAO<Product, Integer> implements ProductDAO {
	
//	private static final Logger logger = LoggerFactory.getLogger(productDAOImpl.class);
//
//	private SessionFactory sessionFactory;
//	
//	public void setSessionFactory(SessionFactory sf){
//		this.sessionFactory = sf;
//	}
//
//	@Override
//	public void addproduct(product p) {
//		Session session = this.sessionFactory.getCurrentSession();
//		session.persist(p);
//		logger.info("product saved successfully, product Details="+p);
//	}
//
//	@Override
//	public void updateproduct(product p) {
//		Session session = this.sessionFactory.getCurrentSession();
//		session.update(p);
//		logger.info("product updated successfully, product Details="+p);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<product> listproducts() {
//		Session session = this.sessionFactory.getCurrentSession();
//		List<product> productsList = session.createQuery("from product").list();
//		for(product p : productsList){
//			logger.info("product List::"+p);
//		}
//		return table_name_lowercase}sList;
//	}
//
//	@Override
//	public product getproductById(int id) {
//		Session session = this.sessionFactory.getCurrentSession();		
//		product p = (product) session.load(product.class, new Integer(id));
//		logger.info("product loaded successfully, product details="+p);
//		return p;
//	}
//
//	@Override
//	public void removeproduct(int id) {
//		Session session = this.sessionFactory.getCurrentSession();
//		product p = (product) session.load(product.class, new Integer(id));
//		if(null != p){
//			session.delete(p);
//		}
//		logger.info("product deleted successfully, product details="+p);
//	}

}
