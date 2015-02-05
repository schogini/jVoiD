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
import com.jvoid.products.product.dao.AttributeValuesDAO;
import com.jvoid.products.product.model.AttributeValues;

@Repository
public class AttributeValuesDAOImpl extends GenericHibernateDAO<AttributeValues, Integer> implements AttributeValuesDAO {
	
//	private static final Logger logger = LoggerFactory.getLogger(AttributeValuesDAOImpl.class);
//
//	private SessionFactory sessionFactory;
//	
//	public void setSessionFactory(SessionFactory sf){
//		this.sessionFactory = sf;
//	}
//
//	@Override
//	public void addAttributeValues(AttributeValues p) {
//		Session session = this.sessionFactory.getCurrentSession();
//		session.persist(p);
//		logger.info("AttributeValues saved successfully, AttributeValues Details="+p);
//	}
//
//	@Override
//	public void updateAttributeValues(AttributeValues p) {
//		Session session = this.sessionFactory.getCurrentSession();
//		session.update(p);
//		logger.info("AttributeValues updated successfully, AttributeValues Details="+p);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<AttributeValues> listAttributeValuess() {
//		Session session = this.sessionFactory.getCurrentSession();
//		List<AttributeValues> attribute_valuessList = session.createQuery("from AttributeValues").list();
//		for(AttributeValues p : attribute_valuessList){
//			logger.info("AttributeValues List::"+p);
//		}
//		return table_name_lowercase}sList;
//	}
//
//	@Override
//	public AttributeValues getAttributeValuesById(int id) {
//		Session session = this.sessionFactory.getCurrentSession();		
//		AttributeValues p = (AttributeValues) session.load(AttributeValues.class, new Integer(id));
//		logger.info("AttributeValues loaded successfully, AttributeValues details="+p);
//		return p;
//	}
//
//	@Override
//	public void removeAttributeValues(int id) {
//		Session session = this.sessionFactory.getCurrentSession();
//		AttributeValues p = (AttributeValues) session.load(AttributeValues.class, new Integer(id));
//		if(null != p){
//			session.delete(p);
//		}
//		logger.info("AttributeValues deleted successfully, AttributeValues details="+p);
//	}

}
