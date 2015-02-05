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
import com.jvoid.products.product.dao.AttributeGroupDAO;
import com.jvoid.products.product.model.AttributeGroup;

@Repository
public class AttributeGroupDAOImpl extends GenericHibernateDAO<AttributeGroup, Integer> implements AttributeGroupDAO {
	
//	private static final Logger logger = LoggerFactory.getLogger(AttributeGroupDAOImpl.class);
//
//	private SessionFactory sessionFactory;
//	
//	public void setSessionFactory(SessionFactory sf){
//		this.sessionFactory = sf;
//	}
//
//	@Override
//	public void addAttributeGroup(AttributeGroup p) {
//		Session session = this.sessionFactory.getCurrentSession();
//		session.persist(p);
//		logger.info("AttributeGroup saved successfully, AttributeGroup Details="+p);
//	}
//
//	@Override
//	public void updateAttributeGroup(AttributeGroup p) {
//		Session session = this.sessionFactory.getCurrentSession();
//		session.update(p);
//		logger.info("AttributeGroup updated successfully, AttributeGroup Details="+p);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<AttributeGroup> listAttributeGroups() {
//		Session session = this.sessionFactory.getCurrentSession();
//		List<AttributeGroup> attribute_groupsList = session.createQuery("from AttributeGroup").list();
//		for(AttributeGroup p : attribute_groupsList){
//			logger.info("AttributeGroup List::"+p);
//		}
//		return table_name_lowercase}sList;
//	}
//
//	@Override
//	public AttributeGroup getAttributeGroupById(int id) {
//		Session session = this.sessionFactory.getCurrentSession();		
//		AttributeGroup p = (AttributeGroup) session.load(AttributeGroup.class, new Integer(id));
//		logger.info("AttributeGroup loaded successfully, AttributeGroup details="+p);
//		return p;
//	}
//
//	@Override
//	public void removeAttributeGroup(int id) {
//		Session session = this.sessionFactory.getCurrentSession();
//		AttributeGroup p = (AttributeGroup) session.load(AttributeGroup.class, new Integer(id));
//		if(null != p){
//			session.delete(p);
//		}
//		logger.info("AttributeGroup deleted successfully, AttributeGroup details="+p);
//	}

}
