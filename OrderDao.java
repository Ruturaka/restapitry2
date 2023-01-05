package com.journaldev.spring.dao;

import com.journaldev.model.Customer;
import com.journaldev.model.Order;

import java.util.List;

public interface OrderDao {
    Order getOrderById(Integer orderid);

    List<Order> getAllOrders();

    int addOrder(Order order);

    int deleteOrderById(Integer id);

    int updateOrder(Order order);


}
