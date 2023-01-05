package com.journaldev.spring.dao;

import com.journaldev.model.Customer;

import java.util.List;

public interface CustomerDao {

    List<Customer> getAllCustomers();

    List<Customer> CustOrderInfo();

    Customer getCustomerById(Integer id);

    int createCustomer(Customer customer);

    int updateCustomer(Customer customer);

    int deleteById(Integer id);

}
