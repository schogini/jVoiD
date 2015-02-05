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

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jvoid.order.dao.CheckoutOrderItemDAO;
import com.jvoid.order.model.CheckoutOrderItem;
import com.jvoid.persistence.hibernate.GenericHibernateDAO;

@Repository
public class CheckoutOrderItemDAOImpl extends GenericHibernateDAO<CheckoutOrderItem, Integer> implements CheckoutOrderItemDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	public List<CheckoutOrderItem> listCheckoutOrderItems(int orderId) {
		return (List<CheckoutOrderItem>) sessionFactory.getCurrentSession()
				.createQuery("from CheckoutOrderItem where order_id=?")
				.setParameter(0, orderId)
				.list();
	}
	
//	private static final Logger logger = LoggerFactory.getLogger(CheckoutOrderItemDAOImpl.class);
//
//	private SessionFactory sessionFactory;
//	
//	public void setSessionFactory(SessionFactory sf){
//		this.sessionFactory = sf;
//	}
//
//	@Override
//	public void addCheckoutOrderItem(CheckoutOrderItem p) {
//		Session session = this.sessionFactory.getCurrentSession();
//		session.persist(p);
//		logger.info("CheckoutOrderItem saved successfully, CheckoutOrderItem Details="+p);
//	}
//
//	@Override
//	public void updateCheckoutOrderItem(CheckoutOrderItem p) {
//		Session session = this.sessionFactory.getCurrentSession();
//		session.update(p);
//		logger.info("CheckoutOrderItem updated successfully, CheckoutOrderItem Details="+p);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<CheckoutOrderItem> listCheckoutOrderItems() {
//		Session session = this.sessionFactory.getCurrentSession();
//		List<CheckoutOrderItem> checkout_order_itemsList = session.createQuery("from CheckoutOrderItem").list();
//		for(CheckoutOrderItem p : checkout_order_itemsList){
//			logger.info("CheckoutOrderItem List::"+p);
//		}
//		return table_name_lowercase}sList;
//	}
//
//	@Override
//	public CheckoutOrderItem getCheckoutOrderItemById(int id) {
//		Session session = this.sessionFactory.getCurrentSession();		
//		CheckoutOrderItem p = (CheckoutOrderItem) session.load(CheckoutOrderItem.class, new Integer(id));
//		logger.info("CheckoutOrderItem loaded successfully, CheckoutOrderItem details="+p);
//		return p;
//	}
//
//	@Override
//	public void removeCheckoutOrderItem(int id) {
//		Session session = this.sessionFactory.getCurrentSession();
//		CheckoutOrderItem p = (CheckoutOrderItem) session.load(CheckoutOrderItem.class, new Integer(id));
//		if(null != p){
//			session.delete(p);
//		}
//		logger.info("CheckoutOrderItem deleted successfully, CheckoutOrderItem details="+p);
//	}

}
