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
package com.jvoid.quote.dao.impl;

import org.springframework.stereotype.Repository;

import com.jvoid.persistence.hibernate.GenericHibernateDAO;
import com.jvoid.quote.dao.CheckoutQuoteDAO;
import com.jvoid.quote.model.CheckoutQuote;

@Repository
public class CheckoutQuoteDAOImpl extends GenericHibernateDAO<CheckoutQuote, Integer> implements CheckoutQuoteDAO {
	
//	private static final Logger logger = LoggerFactory.getLogger(CheckoutQuoteDAOImpl.class);
//
//	private SessionFactory sessionFactory;
//	
//	public void setSessionFactory(SessionFactory sf){
//		this.sessionFactory = sf;
//	}
//
//	@Override
//	public void addCheckoutQuote(CheckoutQuote p) {
//		Session session = this.sessionFactory.getCurrentSession();
//		session.persist(p);
//		logger.info("CheckoutQuote saved successfully, CheckoutQuote Details="+p);
//	}
//
//	@Override
//	public void updateCheckoutQuote(CheckoutQuote p) {
//		Session session = this.sessionFactory.getCurrentSession();
//		session.update(p);
//		logger.info("CheckoutQuote updated successfully, CheckoutQuote Details="+p);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<CheckoutQuote> listCheckoutQuotes() {
//		Session session = this.sessionFactory.getCurrentSession();
//		List<CheckoutQuote> checkout_quotesList = session.createQuery("from CheckoutQuote").list();
//		for(CheckoutQuote p : checkout_quotesList){
//			logger.info("CheckoutQuote List::"+p);
//		}
//		return table_name_lowercase}sList;
//	}
//
//	@Override
//	public CheckoutQuote getCheckoutQuoteById(int id) {
//		Session session = this.sessionFactory.getCurrentSession();		
//		CheckoutQuote p = (CheckoutQuote) session.load(CheckoutQuote.class, new Integer(id));
//		logger.info("CheckoutQuote loaded successfully, CheckoutQuote details="+p);
//		return p;
//	}
//
//	@Override
//	public void removeCheckoutQuote(int id) {
//		Session session = this.sessionFactory.getCurrentSession();
//		CheckoutQuote p = (CheckoutQuote) session.load(CheckoutQuote.class, new Integer(id));
//		if(null != p){
//			session.delete(p);
//		}
//		logger.info("CheckoutQuote deleted successfully, CheckoutQuote details="+p);
//	}

}
