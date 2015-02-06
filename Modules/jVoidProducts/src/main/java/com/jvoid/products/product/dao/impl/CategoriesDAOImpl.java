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
 package com.jvoid.products.product.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jvoid.persistence.hibernate.GenericHibernateDAO;
import com.jvoid.products.product.dao.CategoriesDAO;
import com.jvoid.products.product.model.Categories;

@Repository
public class CategoriesDAOImpl extends GenericHibernateDAO<Categories, Integer> implements CategoriesDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public List<Categories> getAllChildCategories(int categoryId) {
		return (List<Categories>) this.sessionFactory.getCurrentSession()
				.createQuery("from Categories where parent_id=?")
				.setParameter(0, categoryId)
				.list();
	}
	
	
//	private static final Logger logger = LoggerFactory.getLogger(CategoriesDAOImpl.class);
//
//	private SessionFactory sessionFactory;
//	
//	public void setSessionFactory(SessionFactory sf){
//		this.sessionFactory = sf;
//	}
//
//	@Override
//	public void addCategories(Categories p) {
//		Session session = this.sessionFactory.getCurrentSession();
//		session.persist(p);
//		logger.info("Categories saved successfully, Categories Details="+p);
//	}
//
//	@Override
//	public void updateCategories(Categories p) {
//		Session session = this.sessionFactory.getCurrentSession();
//		session.update(p);
//		logger.info("Categories updated successfully, Categories Details="+p);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Categories> listCategoriess() {
//		Session session = this.sessionFactory.getCurrentSession();
//		List<Categories> categoriessList = session.createQuery("from Categories").list();
//		for(Categories p : categoriessList){
//			logger.info("Categories List::"+p);
//		}
//		return table_name_lowercase}sList;
//	}
//
//	@Override
//	public Categories getCategoriesById(int id) {
//		Session session = this.sessionFactory.getCurrentSession();		
//		Categories p = (Categories) session.load(Categories.class, new Integer(id));
//		logger.info("Categories loaded successfully, Categories details="+p);
//		return p;
//	}
//
//	@Override
//	public void removeCategories(int id) {
//		Session session = this.sessionFactory.getCurrentSession();
//		Categories p = (Categories) session.load(Categories.class, new Integer(id));
//		if(null != p){
//			session.delete(p);
//		}
//		logger.info("Categories deleted successfully, Categories details="+p);
//	}

}
