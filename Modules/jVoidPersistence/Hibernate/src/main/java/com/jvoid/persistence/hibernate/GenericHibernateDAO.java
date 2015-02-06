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
 package com.jvoid.persistence.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;

import com.jvoid.persistence.persistenceapi.GenericDAO;

/**
 * Generic Hibernate DAO class
 * 
 * @author Shajir
 * @version 1.0
 */


public abstract class GenericHibernateDAO<T, ID extends Serializable>
		implements GenericDAO<T, ID> {

	private Class<T> persistentClass;
	@Autowired
    private SessionFactory sessionFactory;
//	private Transaction tx;

	@SuppressWarnings("unchecked")
	public GenericHibernateDAO() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
//		this.tx = getSession().beginTransaction();
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
//        this.tx = getSession().beginTransaction();
    }

//	public void setSession(Session s) {
//		this.session = s;
//	}

	protected Transaction getTransaction() {
		return getSession().getTransaction();
	}

	protected Session getSession() {  
//        if (session == null)  
//            throw new IllegalStateException("Session has not been set on DAO before usage");  
//        return session;  
		return this.sessionFactory.getCurrentSession();
    }  
  

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findById(ID id, boolean lock) {
		T entity;
		try {
			if (lock) {
				entity = (T) getSession().get(getPersistentClass(), id,
						LockMode.UPGRADE);
			} else {
				entity = (T) getSession().get(getPersistentClass(), id);
			}
			return entity;
		} catch (Exception e) {
			// e.printStackTrace();
			getTransaction().rollback();
			return null;
		}
	}

	@Override
	public List<T> findAll() {
		return findByCriteria();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAllByCriteria(Criterion criterion){
		try {
			Criteria crit = getSession().createCriteria(getPersistentClass());
			crit.add(criterion);
			return crit.list();
		} catch (Exception e) {
			// e.printStackTrace();
			getTransaction().rollback();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByExample(T exampleInstance, String... excludeProperty) {
		try {
			Criteria crit = getSession().createCriteria(getPersistentClass());
			Example example = Example.create(exampleInstance);
			for (String exclude : excludeProperty) {
				example.excludeProperty(exclude);
			}
			crit.add(example);
			return crit.list();
		} catch (Exception e) {
			// e.printStackTrace();
			getTransaction().rollback();
			return null;
		}
	}

	@Override
	public T saveOrUpdate(T entity) {
		try {
			getSession().saveOrUpdate(entity);
			return entity;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			getTransaction().rollback();
			return null;
		}
	}

	@Override
	public void removeEntity(T entity) {
		getSession().delete(entity);		
	}

	@Override
	public void flush() {
		getTransaction().commit();
		// getSession().flush();
	}

	@Override
	public void clear() {
		getSession().clear();
	}

	@SuppressWarnings("unchecked")
	protected List<T> findByCriteria(Criterion... criterion) {
		try {
			Criteria crit = getSession().createCriteria(getPersistentClass());
			for (Criterion c : criterion) {
				crit.add(c);
			}
			return crit.list();
		} catch (Exception e) {
			// e.printStackTrace();
			getTransaction().rollback();
			return null;
		}
	}
}