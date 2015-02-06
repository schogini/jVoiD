package com.jvoid.customers.customer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jvoid.customers.customer.dao.impl.CustomerDAOImpl;
import com.jvoid.customers.customer.model.Customer;
import com.jvoid.customers.customer.service.CustomerService;



@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerDAOImpl customerDAO;

	public void setCustomerDAO(CustomerDAOImpl customerDAO) {
		this.customerDAO = customerDAO;
	}

	@Transactional
	public void addcustomer(Customer p) {
		this.customerDAO.saveOrUpdate(p);
	}

	@Transactional
	public void updatecustomer(Customer p) {
		this.customerDAO.saveOrUpdate(p);
	}

	@Transactional
	public List<Customer> listcustomers() {
		return this.customerDAO.findAll();
	}

	@Transactional
	public Customer getcustomerById(int id) {
		return this.customerDAO.findById(id,false);
	}

	@Transactional
	public void removecustomer(int id) {
		Customer p = this.customerDAO.findById(id, false);
		this.customerDAO.removeEntity(p);
	}

	@Override
	@Transactional(readOnly=true)
	public Customer getCustomerByEmailId(String email) {
		return customerDAO.getCustomerByEmailId(email);
	}

}
