package com.webcustomertracker.springdemo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.webcustomertracker.springdemo.entity.Customer;

public interface CustomerService {

	public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);
}
