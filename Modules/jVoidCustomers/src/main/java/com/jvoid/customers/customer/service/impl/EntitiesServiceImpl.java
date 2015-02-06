package com.jvoid.customers.customer.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jvoid.customers.customer.dao.EntitiesDAO;
import com.jvoid.customers.customer.model.Entities;
import com.jvoid.customers.customer.service.EntitiesService;

@Service
public class EntitiesServiceImpl implements EntitiesService {
	
	private EntitiesDAO entitiesDAO;

	public void setEntitiesDAO(EntitiesDAO entitiesDAO) {
		this.entitiesDAO = entitiesDAO;
	}

	@Transactional
	public void addAttributes(Entities p) {
		this.entitiesDAO.saveOrUpdate(p);
	}

	@Transactional
	public void updateAttributes(Entities p) {
		this.entitiesDAO.saveOrUpdate(p);
	}

	@Transactional
	public List<Entities> listAttributes() {
		return this.entitiesDAO.findAll();
	}

	@Transactional
	public Entities getAttributesById(int id) {
		return this.entitiesDAO.findById(id,false);
	}

	@Transactional
	public void removeAttributes(int id) {
		Entities p = this.entitiesDAO.findById(id, false);
		this.entitiesDAO.removeEntity(p);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Entities> getAttributesByType(String type) {
		return this.entitiesDAO.getAttributesByType(type);
	}

}
