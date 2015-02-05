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

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jvoid.persistence.hibernate.GenericHibernateDAO;
import com.jvoid.products.product.dao.CategoryProductsDAO;
import com.jvoid.products.product.model.CategoryProducts;
import com.jvoid.products.product.model.Entities;

@Repository
public class CategoryProductsDAOImpl extends GenericHibernateDAO<CategoryProducts, Integer> implements CategoryProductsDAO {

	@Autowired
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	public CategoryProducts getCategoryProductByCategoryIdAndProductId(
			int categoryId, int productId) {
		return(CategoryProducts) sessionFactory.getCurrentSession()
				.createQuery("from CategoryProducts where category_id=? and product_id=?")
				.setParameter(0, categoryId)
				.setParameter(1, productId)
				.uniqueResult();
	}

	@Override
	public List<CategoryProducts> getCategoryProductsByCategoryId(int categoryId) {
		return(List<CategoryProducts>) sessionFactory.getCurrentSession()
				.createQuery("from CategoryProducts where category_id=?")
				.setParameter(0, categoryId)
				.list();
	}
	
//	private static final Logger logger = LoggerFactory.getLogger(CategoryProductsDAOImpl.class);
//
//	private SessionFactory sessionFactory;
//	
//	public void setSessionFactory(SessionFactory sf){
//		this.sessionFactory = sf;
//	}
//
//	@Override
//	public void addCategoryProducts(CategoryProducts p) {
//		Session session = this.sessionFactory.getCurrentSession();
//		session.persist(p);
//		logger.info("CategoryProducts saved successfully, CategoryProducts Details="+p);
//	}
//
//	@Override
//	public void updateCategoryProducts(CategoryProducts p) {
//		Session session = this.sessionFactory.getCurrentSession();
//		session.update(p);
//		logger.info("CategoryProducts updated successfully, CategoryProducts Details="+p);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<CategoryProducts> listCategoryProductss() {
//		Session session = this.sessionFactory.getCurrentSession();
//		List<CategoryProducts> category_productssList = session.createQuery("from CategoryProducts").list();
//		for(CategoryProducts p : category_productssList){
//			logger.info("CategoryProducts List::"+p);
//		}
//		return table_name_lowercase}sList;
//	}
//
//	@Override
//	public CategoryProducts getCategoryProductsById(int id) {
//		Session session = this.sessionFactory.getCurrentSession();		
//		CategoryProducts p = (CategoryProducts) session.load(CategoryProducts.class, new Integer(id));
//		logger.info("CategoryProducts loaded successfully, CategoryProducts details="+p);
//		return p;
//	}
//
//	@Override
//	public void removeCategoryProducts(int id) {
//		Session session = this.sessionFactory.getCurrentSession();
//		CategoryProducts p = (CategoryProducts) session.load(CategoryProducts.class, new Integer(id));
//		if(null != p){
//			session.delete(p);
//		}
//		logger.info("CategoryProducts deleted successfully, CategoryProducts details="+p);
//	}

}
