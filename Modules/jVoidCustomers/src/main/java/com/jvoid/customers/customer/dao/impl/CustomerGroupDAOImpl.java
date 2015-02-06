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

import com.jvoid.customers.customer.dao.CustomerGroupDAO;
import com.jvoid.customers.customer.model.CustomerGroup;
import com.jvoid.persistence.hibernate.GenericHibernateDAO;

@Repository
public class CustomerGroupDAOImpl extends GenericHibernateDAO<CustomerGroup, Integer> implements CustomerGroupDAO {
	
//	private static final Logger logger = LoggerFactory.getLogger(CustomerGroupDAOImpl.class);
//
//	private SessionFactory sessionFactory;
//	
//	public void setSessionFactory(SessionFactory sf){
//		this.sessionFactory = sf;
//	}
//
//	@Override
//	public void addCustomerGroup(CustomerGroup p) {
//		Session session = this.sessionFactory.getCurrentSession();
//		session.persist(p);
//		logger.info("CustomerGroup saved successfully, CustomerGroup Details="+p);
//	}
//
//	@Override
//	public void updateCustomerGroup(CustomerGroup p) {
//		Session session = this.sessionFactory.getCurrentSession();
//		session.update(p);
//		logger.info("CustomerGroup updated successfully, CustomerGroup Details="+p);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<CustomerGroup> listCustomerGroups() {
//		Session session = this.sessionFactory.getCurrentSession();
//		List<CustomerGroup> customer_groupsList = session.createQuery("from CustomerGroup").list();
//		for(CustomerGroup p : customer_groupsList){
//			logger.info("CustomerGroup List::"+p);
//		}
//		return table_name_lowercase}sList;
//	}
//
//	@Override
//	public CustomerGroup getCustomerGroupById(int id) {
//		Session session = this.sessionFactory.getCurrentSession();		
//		CustomerGroup p = (CustomerGroup) session.load(CustomerGroup.class, new Integer(id));
//		logger.info("CustomerGroup loaded successfully, CustomerGroup details="+p);
//		return p;
//	}
//
//	@Override
//	public void removeCustomerGroup(int id) {
//		Session session = this.sessionFactory.getCurrentSession();
//		CustomerGroup p = (CustomerGroup) session.load(CustomerGroup.class, new Integer(id));
//		if(null != p){
//			session.delete(p);
//		}
//		logger.info("CustomerGroup deleted successfully, CustomerGroup details="+p);
//	}

}
