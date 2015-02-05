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
package com.jvoid.attributes.attribute.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jvoid.attributes.attribute.dao.AttributesDAO;
import com.jvoid.attributes.attribute.model.Attributes;
import com.jvoid.persistence.hibernate.GenericHibernateDAO;

@Repository
public class AttributesDAOImpl extends GenericHibernateDAO<Attributes, Integer> implements AttributesDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	public List<Attributes> getAttributesByType(String type) {

		return (List<Attributes>) sessionFactory.getCurrentSession()
			.createQuery("from attributes where type=?")
			.setParameter(0, type)
			.uniqueResult();
	}

}
