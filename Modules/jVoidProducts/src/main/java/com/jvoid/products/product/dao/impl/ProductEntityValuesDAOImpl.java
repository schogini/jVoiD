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
 package com.jvoid.products.product.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jvoid.persistence.hibernate.GenericHibernateDAO;
import com.jvoid.products.product.dao.ProductEntityValuesDAO;
import com.jvoid.products.product.model.ProductEntityValues;

@Repository
public class ProductEntityValuesDAOImpl extends GenericHibernateDAO<ProductEntityValues, Integer> implements ProductEntityValuesDAO {

	@Autowired
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
		
	
	@Override
	public ProductEntityValues getProductAttributeValuesByProductIdAndAttributeId(
			int pid, int aid) {
		System.out.println("Inside ProductAttributeValues");
		return (ProductEntityValues) sessionFactory.getCurrentSession()
			.createQuery("from ProductEntityValues where product_id=? and attribute_id=?")
			.setParameter(0, pid)
			.setParameter(1, aid)
			.uniqueResult();
	}
	
//	private static final Logger logger = LoggerFactory.getLogger(ProductAttributeValuesDAOImpl.class);
//
//	private SessionFactory sessionFactory;
//	
//	public void setSessionFactory(SessionFactory sf){
//		this.sessionFactory = sf;
//	}
//
//	@Override
//	public void addProductAttributeValues(ProductAttributeValues p) {
//		Session session = this.sessionFactory.getCurrentSession();
//		session.persist(p);
//		logger.info("ProductAttributeValues saved successfully, ProductAttributeValues Details="+p);
//	}
//
//	@Override
//	public void updateProductAttributeValues(ProductAttributeValues p) {
//		Session session = this.sessionFactory.getCurrentSession();
//		session.update(p);
//		logger.info("ProductAttributeValues updated successfully, ProductAttributeValues Details="+p);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<ProductAttributeValues> listProductAttributeValuess() {
//		Session session = this.sessionFactory.getCurrentSession();
//		List<ProductAttributeValues> product_attribute_valuessList = session.createQuery("from ProductAttributeValues").list();
//		for(ProductAttributeValues p : product_attribute_valuessList){
//			logger.info("ProductAttributeValues List::"+p);
//		}
//		return table_name_lowercase}sList;
//	}
//
//	@Override
//	public ProductAttributeValues getProductAttributeValuesById(int id) {
//		Session session = this.sessionFactory.getCurrentSession();		
//		ProductAttributeValues p = (ProductAttributeValues) session.load(ProductAttributeValues.class, new Integer(id));
//		logger.info("ProductAttributeValues loaded successfully, ProductAttributeValues details="+p);
//		return p;
//	}
//
//	@Override
//	public void removeProductAttributeValues(int id) {
//		Session session = this.sessionFactory.getCurrentSession();
//		ProductAttributeValues p = (ProductAttributeValues) session.load(ProductAttributeValues.class, new Integer(id));
//		if(null != p){
//			session.delete(p);
//		}
//		logger.info("ProductAttributeValues deleted successfully, ProductAttributeValues details="+p);
//	}

}
