package com.webcustomertracker.springdemo.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.webcustomertracker.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
	
	//inject Hibernate SessionFactory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomers() {
		//get current session
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		//create query....sort by last name
		
		Query<Customer> theQuery = currentSession.createQuery
		("from Customer order by firstName",Customer.class);
		
		//execute query and get results
		
		List<Customer> customers= theQuery.getResultList();
		
		//return result
		
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(theCustomer);
		
	}

	@Override
	public Customer getCustomer(int theId) {
		Session session=sessionFactory.getCurrentSession();
		Customer theCustomer=session.get(Customer.class,theId);
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		Query theQuery=
				session.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId",theId);
		theQuery.executeUpdate();
	}
	

}
