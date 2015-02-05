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

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jvoid.persistence.hibernate.GenericHibernateDAO;
import com.jvoid.products.product.dao.CategoryEntityValuesDAO;
import com.jvoid.products.product.model.CategoryEntityValues;

@Repository
public class CategoryEntityValuesDAOImpl extends GenericHibernateDAO<CategoryEntityValues, Integer> implements CategoryEntityValuesDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	public CategoryEntityValues getCategoryAttributesByCategoryId(
			int categoryId) {
		return(CategoryEntityValues) sessionFactory.getCurrentSession()
				.createQuery("from CategoryEntityValues where category_id=?")
				.setParameter(0, categoryId)
				.uniqueResult();
	}
	
//	private static final Logger logger = LoggerFactory.getLogger(CategoryAttributeValuesDAOImpl.class);
//
//	private SessionFactory sessionFactory;
//	
//	public void setSessionFactory(SessionFactory sf){
//		this.sessionFactory = sf;
//	}
//
//	@Override
//	public void addCategoryAttributeValues(CategoryAttributeValues p) {
//		Session session = this.sessionFactory.getCurrentSession();
//		session.persist(p);
//		logger.info("CategoryAttributeValues saved successfully, CategoryAttributeValues Details="+p);
//	}
//
//	@Override
//	public void updateCategoryAttributeValues(CategoryAttributeValues p) {
//		Session session = this.sessionFactory.getCurrentSession();
//		session.update(p);
//		logger.info("CategoryAttributeValues updated successfully, CategoryAttributeValues Details="+p);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<CategoryAttributeValues> listCategoryAttributeValuess() {
//		Session session = this.sessionFactory.getCurrentSession();
//		List<CategoryAttributeValues> category_attribute_valuessList = session.createQuery("from CategoryAttributeValues").list();
//		for(CategoryAttributeValues p : category_attribute_valuessList){
//			logger.info("CategoryAttributeValues List::"+p);
//		}
//		return table_name_lowercase}sList;
//	}
//
//	@Override
//	public CategoryAttributeValues getCategoryAttributeValuesById(int id) {
//		Session session = this.sessionFactory.getCurrentSession();		
//		CategoryAttributeValues p = (CategoryAttributeValues) session.load(CategoryAttributeValues.class, new Integer(id));
//		logger.info("CategoryAttributeValues loaded successfully, CategoryAttributeValues details="+p);
//		return p;
//	}
//
//	@Override
//	public void removeCategoryAttributeValues(int id) {
//		Session session = this.sessionFactory.getCurrentSession();
//		CategoryAttributeValues p = (CategoryAttributeValues) session.load(CategoryAttributeValues.class, new Integer(id));
//		if(null != p){
//			session.delete(p);
//		}
//		logger.info("CategoryAttributeValues deleted successfully, CategoryAttributeValues details="+p);
//	}

}
