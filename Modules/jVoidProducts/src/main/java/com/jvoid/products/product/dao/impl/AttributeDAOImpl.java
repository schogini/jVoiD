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

import org.springframework.stereotype.Repository;

import com.jvoid.persistence.hibernate.GenericHibernateDAO;
import com.jvoid.products.product.dao.AttributeDAO;
import com.jvoid.products.product.model.Attribute;

@Repository
public class AttributeDAOImpl extends GenericHibernateDAO<Attribute, Integer> implements AttributeDAO {
	
//	private static final Logger logger = LoggerFactory.getLogger(AttributeDAOImpl.class);
//
//	private SessionFactory sessionFactory;
//	
//	public void setSessionFactory(SessionFactory sf){
//		this.sessionFactory = sf;
//	}
//
//	@Override
//	public void addAttribute(Attribute p) {
//		Session session = this.sessionFactory.getCurrentSession();
//		session.persist(p);
//		logger.info("Attribute saved successfully, Attribute Details="+p);
//	}
//
//	@Override
//	public void updateAttribute(Attribute p) {
//		Session session = this.sessionFactory.getCurrentSession();
//		session.update(p);
//		logger.info("Attribute updated successfully, Attribute Details="+p);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Attribute> listAttributes() {
//		Session session = this.sessionFactory.getCurrentSession();
//		List<Attribute> attributesList = session.createQuery("from Attribute").list();
//		for(Attribute p : attributesList){
//			logger.info("Attribute List::"+p);
//		}
//		return table_name_lowercase}sList;
//	}
//
//	@Override
//	public Attribute getAttributeById(int id) {
//		Session session = this.sessionFactory.getCurrentSession();		
//		Attribute p = (Attribute) session.load(Attribute.class, new Integer(id));
//		logger.info("Attribute loaded successfully, Attribute details="+p);
//		return p;
//	}
//
//	@Override
//	public void removeAttribute(int id) {
//		Session session = this.sessionFactory.getCurrentSession();
//		Attribute p = (Attribute) session.load(Attribute.class, new Integer(id));
//		if(null != p){
//			session.delete(p);
//		}
//		logger.info("Attribute deleted successfully, Attribute details="+p);
//	}

}
