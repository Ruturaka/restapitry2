package com.journaldev.spring.dao;

import com.journaldev.model.Customer;
import com.journaldev.model.Order;

import java.util.List;

public interface CustomerDao {

    List<Customer> getAllCustomers(); //read

    int createCustomer(Customer customer); //post

    int updateCustomer(Customer customer); //update

    int updateBillForId(Customer customer); //set's customer bill for that particular id

    int deleteById(Integer id); //delete

    Customer getCustomerById(Integer id);

    List<Customer> getCustomerOrderById(Integer id);

    List<Customer> getAllCustomerOrders();

    List<Customer> findByNameContaining(String name);

    List<Customer> findCustOrderByNameContaining(String name);




}
