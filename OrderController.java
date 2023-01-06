package com.journaldev.controller;

import com.journaldev.model.Customer;
import com.journaldev.model.Order;
import com.journaldev.spring.dao.CustomerDao;
import com.journaldev.spring.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    OrderDao orderDao;

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllCustomers(@RequestParam(required = false) String dish){
        //Find all orders which dish contains string ‘?’:
        try {
            List<Order> orders = new ArrayList<Order>();

            if (dish == null)
                orderDao.getAllOrders().forEach(orders::add);
            else
                orderDao.findByDishContaining(dish).forEach(orders::add);
            if (orders.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/orders/{orderid}")
    public ResponseEntity<Order> getCustomerById(@PathVariable("orderid") Integer oid) {
        Order order = orderDao.getOrderById(oid);

        if (order != null) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/orders")
    public ResponseEntity<String> createCustomer(@RequestBody Order order) {
        try {
            orderDao.addOrder(new Order(order.getId(), order.getDish(), order.getQunt()));
            return new ResponseEntity<>("Order was created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") Integer id) {
        try {
            int result = orderDao.deleteOrderById(id);
            if (result == 0) {
                return new ResponseEntity<>("Cannot find Order with id=" + id, HttpStatus.OK);
            }
            return new ResponseEntity<>("Order was deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete order.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/orders/{orderid}")
    public ResponseEntity<String> updateCustomer(@PathVariable("orderid") Integer id, @RequestBody Order order) {
        Order _order = orderDao.getOrderById(id);

        if (_order != null) {
            _order.setDish(order.getDish());
            _order.setQunt(order.getQunt());

            orderDao.updateOrder(_order);
            return new ResponseEntity<>("Order updated successfully.", HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>("Cannot find Order with id=" + id, HttpStatus.NOT_FOUND);
        }
    }
}
