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
import com.jvoid.products.product.dao.AttributeSetDAO;
import com.jvoid.products.product.model.AttributeSet;

@Repository
public class AttributeSetDAOImpl extends GenericHibernateDAO<AttributeSet, Integer> implements AttributeSetDAO {
	
//	private static final Logger logger = LoggerFactory.getLogger(AttributeSetDAOImpl.class);
//
//	private SessionFactory sessionFactory;
//	
//	public void setSessionFactory(SessionFactory sf){
//		this.sessionFactory = sf;
//	}
//
//	@Override
//	public void addAttributeSet(AttributeSet p) {
//		Session session = this.sessionFactory.getCurrentSession();
//		session.persist(p);
//		logger.info("AttributeSet saved successfully, AttributeSet Details="+p);
//	}
//
//	@Override
//	public void updateAttributeSet(AttributeSet p) {
//		Session session = this.sessionFactory.getCurrentSession();
//		session.update(p);
//		logger.info("AttributeSet updated successfully, AttributeSet Details="+p);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<AttributeSet> listAttributeSets() {
//		Session session = this.sessionFactory.getCurrentSession();
//		List<AttributeSet> attribute_setsList = session.createQuery("from AttributeSet").list();
//		for(AttributeSet p : attribute_setsList){
//			logger.info("AttributeSet List::"+p);
//		}
//		return table_name_lowercase}sList;
//	}
//
//	@Override
//	public AttributeSet getAttributeSetById(int id) {
//		Session session = this.sessionFactory.getCurrentSession();		
//		AttributeSet p = (AttributeSet) session.load(AttributeSet.class, new Integer(id));
//		logger.info("AttributeSet loaded successfully, AttributeSet details="+p);
//		return p;
//	}
//
//	@Override
//	public void removeAttributeSet(int id) {
//		Session session = this.sessionFactory.getCurrentSession();
//		AttributeSet p = (AttributeSet) session.load(AttributeSet.class, new Integer(id));
//		if(null != p){
//			session.delete(p);
//		}
//		logger.info("AttributeSet deleted successfully, AttributeSet details="+p);
//	}

}
