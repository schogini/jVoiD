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

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jvoid.persistence.hibernate.GenericHibernateDAO;
import com.jvoid.quote.dao.CheckoutQuoteItemDAO;
import com.jvoid.quote.model.CheckoutQuoteItem;

@Repository
public class CheckoutQuoteItemDAOImpl extends GenericHibernateDAO<CheckoutQuoteItem, Integer> implements CheckoutQuoteItemDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	public List<CheckoutQuoteItem> listCheckoutQuoteItems(int quoteId) {
		// TODO Auto-generated method stub
		return (List<CheckoutQuoteItem>) sessionFactory.getCurrentSession()
				.createQuery("from CheckoutQuoteItem where quote_id=?")
				.setParameter(0, quoteId)
				.list();
	}

	@Override
	public CheckoutQuoteItem getCheckoutQuoteItem(int quoteId,
			int productId) {
		// TODO Auto-generated method stub
		return (CheckoutQuoteItem) sessionFactory.getCurrentSession()
				.createQuery("from CheckoutQuoteItem where quote_id=? and product_id=?")
				.setParameter(0, quoteId)
				.setParameter(1, productId)
				.uniqueResult();
	}
	
//	private static final Logger logger = LoggerFactory.getLogger(CheckoutQuoteItemDAOImpl.class);
//
//	private SessionFactory sessionFactory;
//	
//	public void setSessionFactory(SessionFactory sf){
//		this.sessionFactory = sf;
//	}
//
//	@Override
//	public void addCheckoutQuoteItem(CheckoutQuoteItem p) {
//		Session session = this.sessionFactory.getCurrentSession();
//		session.persist(p);
//		logger.info("CheckoutQuoteItem saved successfully, CheckoutQuoteItem Details="+p);
//	}
//
//	@Override
//	public void updateCheckoutQuoteItem(CheckoutQuoteItem p) {
//		Session session = this.sessionFactory.getCurrentSession();
//		session.update(p);
//		logger.info("CheckoutQuoteItem updated successfully, CheckoutQuoteItem Details="+p);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<CheckoutQuoteItem> listCheckoutQuoteItems() {
//		Session session = this.sessionFactory.getCurrentSession();
//		List<CheckoutQuoteItem> checkout_quote_itemsList = session.createQuery("from CheckoutQuoteItem").list();
//		for(CheckoutQuoteItem p : checkout_quote_itemsList){
//			logger.info("CheckoutQuoteItem List::"+p);
//		}
//		return table_name_lowercase}sList;
//	}
//
//	@Override
//	public CheckoutQuoteItem getCheckoutQuoteItemById(int id) {
//		Session session = this.sessionFactory.getCurrentSession();		
//		CheckoutQuoteItem p = (CheckoutQuoteItem) session.load(CheckoutQuoteItem.class, new Integer(id));
//		logger.info("CheckoutQuoteItem loaded successfully, CheckoutQuoteItem details="+p);
//		return p;
//	}
//
//	@Override
//	public void removeCheckoutQuoteItem(int id) {
//		Session session = this.sessionFactory.getCurrentSession();
//		CheckoutQuoteItem p = (CheckoutQuoteItem) session.load(CheckoutQuoteItem.class, new Integer(id));
//		if(null != p){
//			session.delete(p);
//		}
//		logger.info("CheckoutQuoteItem deleted successfully, CheckoutQuoteItem details="+p);
//	}

}
