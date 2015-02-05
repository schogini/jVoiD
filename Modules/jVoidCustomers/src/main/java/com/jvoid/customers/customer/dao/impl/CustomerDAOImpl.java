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
package com.jvoid.customers.customer.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jvoid.customers.customer.dao.CustomerDAO;
import com.jvoid.customers.customer.model.Customer;
import com.jvoid.persistence.hibernate.GenericHibernateDAO;


@Repository
public class CustomerDAOImpl extends GenericHibernateDAO<Customer, Integer> implements CustomerDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public Customer getCustomerByEmailId(String email) {
		return (Customer) this.sessionFactory.getCurrentSession()
				.createQuery("from Customer where email=?")
				.setParameter(0, email)
				.uniqueResult();
	}
	
//	private static final Logger logger = LoggerFactory.getLogger(customerDAOImpl.class);
//
//
//	@Override
//	public void addcustomer(customer p) {
//		Session session = this.sessionFactory.getCurrentSession();
//		session.persist(p);
//		logger.info("customer saved successfully, customer Details="+p);
//	}
//
//	@Override
//	public void updatecustomer(customer p) {
//		Session session = this.sessionFactory.getCurrentSession();
//		session.update(p);
//		logger.info("customer updated successfully, customer Details="+p);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<customer> listcustomers() {
//		Session session = this.sessionFactory.getCurrentSession();
//		List<customer> customersList = session.createQuery("from customer").list();
//		for(customer p : customersList){
//			logger.info("customer List::"+p);
//		}
//		return table_name_lowercase}sList;
//	}
//
//	@Override
//	public customer getcustomerById(int id) {
//		Session session = this.sessionFactory.getCurrentSession();		
//		customer p = (customer) session.load(customer.class, new Integer(id));
//		logger.info("customer loaded successfully, customer details="+p);
//		return p;
//	}
//
//	@Override
//	public void removecustomer(int id) {
//		Session session = this.sessionFactory.getCurrentSession();
//		customer p = (customer) session.load(customer.class, new Integer(id));
//		if(null != p){
//			session.delete(p);
//		}
//		logger.info("customer deleted successfully, customer details="+p);
//	}

}
