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

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jvoid.customers.customer.dao.CustomerEntityValuesDAO;
import com.jvoid.customers.customer.model.CustomerEntityValues;
import com.jvoid.persistence.hibernate.GenericHibernateDAO;

@Repository
public class CustomerEntityValuesDAOImpl extends GenericHibernateDAO<CustomerEntityValues, Integer> implements CustomerEntityValuesDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	public List<CustomerEntityValues> getCustomerEntityValuesByCustomerId(int customerId) {
		return (List<CustomerEntityValues>) this.sessionFactory.getCurrentSession()
				.createQuery("from CustomerEntityValues where customer_id=?")
				.setParameter(0, customerId)
				.list();
	}

	@Override
	public CustomerEntityValues getCustomerEntityValuesByCustomerIdAndAttributeId(
			int customerId, int attributeId) {
		return (CustomerEntityValues) this.sessionFactory.getCurrentSession()
				.createQuery("from CustomerEntityValues where customer_id=? and attribute_id=?")
				.setParameter(0, customerId)
				.setParameter(1, attributeId)
				.uniqueResult();
	}

	
//	private static final Logger logger = LoggerFactory.getLogger(CustomerAttributeValuesDAOImpl.class);
//
//
//	@Override
//	public void addCustomerAttributeValues(CustomerAttributeValues p) {
//		Session session = this.sessionFactory.getCurrentSession();
//		session.persist(p);
//		logger.info("CustomerAttributeValues saved successfully, CustomerAttributeValues Details="+p);
//	}
//
//	@Override
//	public void updateCustomerAttributeValues(CustomerAttributeValues p) {
//		Session session = this.sessionFactory.getCurrentSession();
//		session.update(p);
//		logger.info("CustomerAttributeValues updated successfully, CustomerAttributeValues Details="+p);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<CustomerAttributeValues> listCustomerAttributeValuess() {
//		Session session = this.sessionFactory.getCurrentSession();
//		List<CustomerAttributeValues> customer_attribute_valuessList = session.createQuery("from CustomerAttributeValues").list();
//		for(CustomerAttributeValues p : customer_attribute_valuessList){
//			logger.info("CustomerAttributeValues List::"+p);
//		}
//		return table_name_lowercase}sList;
//	}
//
//	@Override
//	public CustomerAttributeValues getCustomerAttributeValuesById(int id) {
//		Session session = this.sessionFactory.getCurrentSession();		
//		CustomerAttributeValues p = (CustomerAttributeValues) session.load(CustomerAttributeValues.class, new Integer(id));
//		logger.info("CustomerAttributeValues loaded successfully, CustomerAttributeValues details="+p);
//		return p;
//	}
//
//	@Override
//	public void removeCustomerAttributeValues(int id) {
//		Session session = this.sessionFactory.getCurrentSession();
//		CustomerAttributeValues p = (CustomerAttributeValues) session.load(CustomerAttributeValues.class, new Integer(id));
//		if(null != p){
//			session.delete(p);
//		}
//		logger.info("CustomerAttributeValues deleted successfully, CustomerAttributeValues details="+p);
//	}

}
