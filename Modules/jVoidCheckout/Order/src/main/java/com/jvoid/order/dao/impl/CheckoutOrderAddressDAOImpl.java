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
package com.jvoid.order.dao.impl;

import org.springframework.stereotype.Repository;

import com.jvoid.order.dao.CheckoutOrderAddressDAO;
import com.jvoid.order.model.CheckoutOrderAddress;
import com.jvoid.persistence.hibernate.GenericHibernateDAO;

@Repository
public class CheckoutOrderAddressDAOImpl extends GenericHibernateDAO<CheckoutOrderAddress, Integer> implements CheckoutOrderAddressDAO {
	
//	private static final Logger logger = LoggerFactory.getLogger(CheckoutOrderAddressDAOImpl.class);
//
//	private SessionFactory sessionFactory;
//	
//	public void setSessionFactory(SessionFactory sf){
//		this.sessionFactory = sf;
//	}
//
//	@Override
//	public void addCheckoutOrderAddress(CheckoutOrderAddress p) {
//		Session session = this.sessionFactory.getCurrentSession();
//		session.persist(p);
//		logger.info("CheckoutOrderAddress saved successfully, CheckoutOrderAddress Details="+p);
//	}
//
//	@Override
//	public void updateCheckoutOrderAddress(CheckoutOrderAddress p) {
//		Session session = this.sessionFactory.getCurrentSession();
//		session.update(p);
//		logger.info("CheckoutOrderAddress updated successfully, CheckoutOrderAddress Details="+p);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<CheckoutOrderAddress> listCheckoutOrderAddresss() {
//		Session session = this.sessionFactory.getCurrentSession();
//		List<CheckoutOrderAddress> checkout_order_addresssList = session.createQuery("from CheckoutOrderAddress").list();
//		for(CheckoutOrderAddress p : checkout_order_addresssList){
//			logger.info("CheckoutOrderAddress List::"+p);
//		}
//		return table_name_lowercase}sList;
//	}
//
//	@Override
//	public CheckoutOrderAddress getCheckoutOrderAddressById(int id) {
//		Session session = this.sessionFactory.getCurrentSession();		
//		CheckoutOrderAddress p = (CheckoutOrderAddress) session.load(CheckoutOrderAddress.class, new Integer(id));
//		logger.info("CheckoutOrderAddress loaded successfully, CheckoutOrderAddress details="+p);
//		return p;
//	}
//
//	@Override
//	public void removeCheckoutOrderAddress(int id) {
//		Session session = this.sessionFactory.getCurrentSession();
//		CheckoutOrderAddress p = (CheckoutOrderAddress) session.load(CheckoutOrderAddress.class, new Integer(id));
//		if(null != p){
//			session.delete(p);
//		}
//		logger.info("CheckoutOrderAddress deleted successfully, CheckoutOrderAddress details="+p);
//	}

}
