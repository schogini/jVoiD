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

import org.springframework.stereotype.Repository;

import com.jvoid.customers.customer.dao.CustomerAddressAttributeValuesDAO;
import com.jvoid.customers.customer.model.CustomerAddressAttributeValues;
import com.jvoid.persistence.hibernate.GenericHibernateDAO;

@Repository
public class CustomerAddressAttributeValuesDAOImpl extends GenericHibernateDAO<CustomerAddressAttributeValues, Integer> implements CustomerAddressAttributeValuesDAO {
	
//	private static final Logger logger = LoggerFactory.getLogger(CustomerAddressAttributeValuesDAOImpl.class);
//
//	private SessionFactory sessionFactory;
//	
//	public void setSessionFactory(SessionFactory sf){
//		this.sessionFactory = sf;
//	}
//
//	@Override
//	public void addCustomerAddressAttributeValues(CustomerAddressAttributeValues p) {
//		Session session = this.sessionFactory.getCurrentSession();
//		session.persist(p);
//		logger.info("CustomerAddressAttributeValues saved successfully, CustomerAddressAttributeValues Details="+p);
//	}
//
//	@Override
//	public void updateCustomerAddressAttributeValues(CustomerAddressAttributeValues p) {
//		Session session = this.sessionFactory.getCurrentSession();
//		session.update(p);
//		logger.info("CustomerAddressAttributeValues updated successfully, CustomerAddressAttributeValues Details="+p);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<CustomerAddressAttributeValues> listCustomerAddressAttributeValuess() {
//		Session session = this.sessionFactory.getCurrentSession();
//		List<CustomerAddressAttributeValues> customer_address_attribute_valuessList = session.createQuery("from CustomerAddressAttributeValues").list();
//		for(CustomerAddressAttributeValues p : customer_address_attribute_valuessList){
//			logger.info("CustomerAddressAttributeValues List::"+p);
//		}
//		return table_name_lowercase}sList;
//	}
//
//	@Override
//	public CustomerAddressAttributeValues getCustomerAddressAttributeValuesById(int id) {
//		Session session = this.sessionFactory.getCurrentSession();		
//		CustomerAddressAttributeValues p = (CustomerAddressAttributeValues) session.load(CustomerAddressAttributeValues.class, new Integer(id));
//		logger.info("CustomerAddressAttributeValues loaded successfully, CustomerAddressAttributeValues details="+p);
//		return p;
//	}
//
//	@Override
//	public void removeCustomerAddressAttributeValues(int id) {
//		Session session = this.sessionFactory.getCurrentSession();
//		CustomerAddressAttributeValues p = (CustomerAddressAttributeValues) session.load(CustomerAddressAttributeValues.class, new Integer(id));
//		if(null != p){
//			session.delete(p);
//		}
//		logger.info("CustomerAddressAttributeValues deleted successfully, CustomerAddressAttributeValues details="+p);
//	}

}
