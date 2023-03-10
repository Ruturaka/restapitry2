package com.journaldev.controller;


import com.journaldev.model.Customer;
import com.journaldev.model.Order;
import com.journaldev.spring.dao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    CustomerDao customerDao;

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers(@RequestParam(required = false) String name){
        //Find all Customers which name contains string ‘?’:
        try {
            List<Customer> customers = new ArrayList<Customer>();

            if (name == null)
                customerDao.getAllCustomers().forEach(customers::add);
            else
                customerDao.findByNameContaining(name).forEach(customers::add);
            if (customers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(customers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/customerOrders")
    public ResponseEntity<List<Customer>> getCustomerOrdersByName(@RequestParam(required = false) String name){
        //Find a Customer's all orders he has ordered by name which contains string ‘?’:
        try {
            List<Customer> customers = new ArrayList<Customer>();

            if (name == null)
                customerDao.getAllCustomerOrders().forEach(customers::add);
            else
                customerDao.findCustOrderByNameContaining(name).forEach(customers::add);
            if (customers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(customers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customerOrder")
    public ResponseEntity<List<Customer>> getCustomerOrderById(@RequestParam(required = false) Integer id){
        try{
            List<Customer> customers= new ArrayList<Customer>();

            if(id!=0)
                customerDao.getCustomerOrderById(id).forEach(customers::add);
            if(customers.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Integer id) {
        Customer customer = customerDao.getCustomerById(id);

        if (customer != null)
        {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/customers")
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
        try {
            customerDao.createCustomer(new Customer(customer.getName(), customer.getEmail(), customer.getNumber()));
            return new ResponseEntity<>("Customer was created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") Integer id) {
        try {
            int result = customerDao.deleteById(id);
            if (result == 0) {
                return new ResponseEntity<>("Cannot find Customer with id=" + id, HttpStatus.OK);
            }
            return new ResponseEntity<>("Customer was deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete customer.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<String> updateCustomer(@PathVariable("id") Integer id, @RequestBody Customer customer) {
        Customer _customer = customerDao.getCustomerById(id);

        if (_customer != null) {
            _customer.setName(customer.getName());
            _customer.setEmail(customer.getEmail());
            _customer.setNumber(customer.getNumber());


            customerDao.updateCustomer(_customer);
            return new ResponseEntity<>("Customer updated successfully.", HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>("Cannot find Customer with id=" + id, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/customers/setBill/{id}")
    public ResponseEntity<String> setAmntById(@PathVariable("id") Integer id) {
        Customer _customer = customerDao.getCustomerById(id);

        if (_customer != null) {
            customerDao.updateBillForId(_customer);
            return new ResponseEntity<>("Customer's bill updated successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot find customer with id=" + id, HttpStatus.NOT_FOUND);
        }
    }


}
