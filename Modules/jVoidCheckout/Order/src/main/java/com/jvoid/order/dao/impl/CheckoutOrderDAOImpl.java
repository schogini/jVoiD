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

import com.jvoid.order.dao.CheckoutOrderDAO;
import com.jvoid.order.model.CheckoutOrder;
import com.jvoid.persistence.hibernate.GenericHibernateDAO;

@Repository
public class CheckoutOrderDAOImpl extends GenericHibernateDAO<CheckoutOrder, Integer> implements CheckoutOrderDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	public List<CheckoutOrder> listCheckoutOrders(int customerId) {
		// TODO Auto-generated method stub
		return (List<CheckoutOrder>) sessionFactory.getCurrentSession()
				.createQuery("from CheckoutOrder where customer_id=?")
				.setParameter(0, customerId)
				.list();
	}
	
//	private static final Logger logger = LoggerFactory.getLogger(CheckoutOrderDAOImpl.class);
//
//	private SessionFactory sessionFactory;
//	
//	public void setSessionFactory(SessionFactory sf){
//		this.sessionFactory = sf;
//	}
//
//	@Override
//	public void addCheckoutOrder(CheckoutOrder p) {
//		Session session = this.sessionFactory.getCurrentSession();
//		session.persist(p);
//		logger.info("CheckoutOrder saved successfully, CheckoutOrder Details="+p);
//	}
//
//	@Override
//	public void updateCheckoutOrder(CheckoutOrder p) {
//		Session session = this.sessionFactory.getCurrentSession();
//		session.update(p);
//		logger.info("CheckoutOrder updated successfully, CheckoutOrder Details="+p);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<CheckoutOrder> listCheckoutOrders() {
//		Session session = this.sessionFactory.getCurrentSession();
//		List<CheckoutOrder> checkout_ordersList = session.createQuery("from CheckoutOrder").list();
//		for(CheckoutOrder p : checkout_ordersList){
//			logger.info("CheckoutOrder List::"+p);
//		}
//		return table_name_lowercase}sList;
//	}
//
//	@Override
//	public CheckoutOrder getCheckoutOrderById(int id) {
//		Session session = this.sessionFactory.getCurrentSession();		
//		CheckoutOrder p = (CheckoutOrder) session.load(CheckoutOrder.class, new Integer(id));
//		logger.info("CheckoutOrder loaded successfully, CheckoutOrder details="+p);
//		return p;
//	}
//
//	@Override
//	public void removeCheckoutOrder(int id) {
//		Session session = this.sessionFactory.getCurrentSession();
//		CheckoutOrder p = (CheckoutOrder) session.load(CheckoutOrder.class, new Integer(id));
//		if(null != p){
//			session.delete(p);
//		}
//		logger.info("CheckoutOrder deleted successfully, CheckoutOrder details="+p);
//	}

}
