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
package com.jvoid.customers.customer.dao.impl;

import org.springframework.stereotype.Repository;

import com.jvoid.customers.customer.dao.CustomerAddressDAO;
import com.jvoid.customers.customer.model.CustomerAddress;
import com.jvoid.persistence.hibernate.GenericHibernateDAO;

@Repository
public class CustomerAddressDAOImpl extends GenericHibernateDAO<CustomerAddress, Integer> implements CustomerAddressDAO {
	
//	private static final Logger logger = LoggerFactory.getLogger(CustomerAddressDAOImpl.class);
//
//	private SessionFactory sessionFactory;
//	
//	public void setSessionFactory(SessionFactory sf){
//		this.sessionFactory = sf;
//	}
//
//	@Override
//	public void addCustomerAddress(CustomerAddress p) {
//		Session session = this.sessionFactory.getCurrentSession();
//		session.persist(p);
//		logger.info("CustomerAddress saved successfully, CustomerAddress Details="+p);
//	}
//
//	@Override
//	public void updateCustomerAddress(CustomerAddress p) {
//		Session session = this.sessionFactory.getCurrentSession();
//		session.update(p);
//		logger.info("CustomerAddress updated successfully, CustomerAddress Details="+p);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<CustomerAddress> listCustomerAddresss() {
//		Session session = this.sessionFactory.getCurrentSession();
//		List<CustomerAddress> customer_addresssList = session.createQuery("from CustomerAddress").list();
//		for(CustomerAddress p : customer_addresssList){
//			logger.info("CustomerAddress List::"+p);
//		}
//		return table_name_lowercase}sList;
//	}
//
//	@Override
//	public CustomerAddress getCustomerAddressById(int id) {
//		Session session = this.sessionFactory.getCurrentSession();		
//		CustomerAddress p = (CustomerAddress) session.load(CustomerAddress.class, new Integer(id));
//		logger.info("CustomerAddress loaded successfully, CustomerAddress details="+p);
//		return p;
//	}
//
//	@Override
//	public void removeCustomerAddress(int id) {
//		Session session = this.sessionFactory.getCurrentSession();
//		CustomerAddress p = (CustomerAddress) session.load(CustomerAddress.class, new Integer(id));
//		if(null != p){
//			session.delete(p);
//		}
//		logger.info("CustomerAddress deleted successfully, CustomerAddress details="+p);
//	}

}
