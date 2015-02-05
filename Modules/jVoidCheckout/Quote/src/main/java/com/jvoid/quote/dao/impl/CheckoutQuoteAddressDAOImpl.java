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
package com.jvoid.quote.dao.impl;

import org.springframework.stereotype.Repository;

import com.jvoid.persistence.hibernate.GenericHibernateDAO;
import com.jvoid.quote.dao.CheckoutQuoteAddressDAO;
import com.jvoid.quote.model.CheckoutQuoteAddress;

@Repository
public class CheckoutQuoteAddressDAOImpl extends GenericHibernateDAO<CheckoutQuoteAddress, Integer> implements CheckoutQuoteAddressDAO {
	
//	private static final Logger logger = LoggerFactory.getLogger(CheckoutQuoteAddressDAOImpl.class);
//
//	private SessionFactory sessionFactory;
//	
//	public void setSessionFactory(SessionFactory sf){
//		this.sessionFactory = sf;
//	}
//
//	@Override
//	public void addCheckoutQuoteAddress(CheckoutQuoteAddress p) {
//		Session session = this.sessionFactory.getCurrentSession();
//		session.persist(p);
//		logger.info("CheckoutQuoteAddress saved successfully, CheckoutQuoteAddress Details="+p);
//	}
//
//	@Override
//	public void updateCheckoutQuoteAddress(CheckoutQuoteAddress p) {
//		Session session = this.sessionFactory.getCurrentSession();
//		session.update(p);
//		logger.info("CheckoutQuoteAddress updated successfully, CheckoutQuoteAddress Details="+p);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<CheckoutQuoteAddress> listCheckoutQuoteAddresss() {
//		Session session = this.sessionFactory.getCurrentSession();
//		List<CheckoutQuoteAddress> checkout_quote_addresssList = session.createQuery("from CheckoutQuoteAddress").list();
//		for(CheckoutQuoteAddress p : checkout_quote_addresssList){
//			logger.info("CheckoutQuoteAddress List::"+p);
//		}
//		return table_name_lowercase}sList;
//	}
//
//	@Override
//	public CheckoutQuoteAddress getCheckoutQuoteAddressById(int id) {
//		Session session = this.sessionFactory.getCurrentSession();		
//		CheckoutQuoteAddress p = (CheckoutQuoteAddress) session.load(CheckoutQuoteAddress.class, new Integer(id));
//		logger.info("CheckoutQuoteAddress loaded successfully, CheckoutQuoteAddress details="+p);
//		return p;
//	}
//
//	@Override
//	public void removeCheckoutQuoteAddress(int id) {
//		Session session = this.sessionFactory.getCurrentSession();
//		CheckoutQuoteAddress p = (CheckoutQuoteAddress) session.load(CheckoutQuoteAddress.class, new Integer(id));
//		if(null != p){
//			session.delete(p);
//		}
//		logger.info("CheckoutQuoteAddress deleted successfully, CheckoutQuoteAddress details="+p);
//	}

}
