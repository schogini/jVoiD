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
import com.jvoid.products.product.dao.EntitiesDAO;
import com.jvoid.products.product.model.Entities;

@Repository
public class EntitiesDAOImpl extends GenericHibernateDAO<Entities, Integer> implements EntitiesDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	public List<Entities> getAttributesByType(String type) {

		return (List<Entities>) sessionFactory.getCurrentSession()
			.createQuery("from Entities where type=?")
			.setParameter(0, type)
			.list();
	}

	@Override
	public Entities getAttributesByNameAndType(String name, String type) {
		return(Entities) sessionFactory.getCurrentSession()
				.createQuery("from Entities where code=? and type=?")
				.setParameter(0, name)
				.setParameter(1, type)
				.uniqueResult();
	}

}